package ankang.dubbo.router;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-19
 */
public class ReadyRestartInstances implements PathChildrenCacheListener {

    private static final String LISTEN_PATH = "/ankang/dubbo/restart/instances";

    private final CuratorFramework zkClient;

    /**
     * 重启机器的信息列表
     */
    private volatile Set<String> restartInstances = new HashSet<>();

    private ReadyRestartInstances(CuratorFramework zkClient) {
        this.zkClient = zkClient;
    }

    public static ReadyRestartInstances create() {
        final CuratorFramework client = ZookeeperClient.getINSTANCE().getCLIENT();

        // 检查监听路径是否存在

        try {
            final Stat stat = client.checkExists().forPath(LISTEN_PATH);
            if (stat == null) {
                client.create().creatingParentsIfNeeded().forPath(LISTEN_PATH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        final ReadyRestartInstances readyRestartInstances = new ReadyRestartInstances(client);
        final PathChildrenCache cache = new PathChildrenCache(client , LISTEN_PATH , false);
        cache.getListenable().addListener(readyRestartInstances);

        return readyRestartInstances;
    }


    private String buildApplicationAndInstanceString(String applicationName , String host) {
        return applicationName + '_' + host;
    }

    /**
     * 增加重启实例的信息
     *
     * @param applicationName
     */
    public void addRestartingInstance(String applicationName , String host) throws Exception {
        zkClient.create().creatingParentsIfNeeded().forPath(LISTEN_PATH + '/' + buildApplicationAndInstanceString(applicationName , host));
    }

    /**
     * 删除重启实例的信息
     *
     * @param applicationName
     */
    public void removeRestartingInstance(String applicationName , String host) throws Exception {
        zkClient.delete().forPath(LISTEN_PATH + '/' + buildApplicationAndInstanceString(applicationName , host));
    }

    /**
     * 判断重启实例的信息是否存在
     *
     * @param applicationName
     */
    public boolean hasRestartingInstance(String applicationName , String host) {
        return restartInstances.contains(buildApplicationAndInstanceString(applicationName , host));
    }

    @Override
    public void childEvent(CuratorFramework client , PathChildrenCacheEvent event) throws Exception {
        // 查询需要重启的机器
        final List<String> restartInstance = zkClient.getChildren().forPath(LISTEN_PATH);

        restartInstances = restartInstance.isEmpty() ? Collections.emptySet() : restartInstances;
    }
}
