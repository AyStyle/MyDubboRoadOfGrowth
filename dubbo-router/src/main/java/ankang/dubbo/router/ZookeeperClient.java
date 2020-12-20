package ankang.dubbo.router;

import lombok.Getter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryForever;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-19
 */
public class ZookeeperClient {

    @Getter
    private static final ZookeeperClient INSTANCE = new ZookeeperClient();

    @Getter
    private final CuratorFramework CLIENT = CuratorFrameworkFactory.newClient("localhost:2181" , new RetryForever(3000));

    private ZookeeperClient() {

    }


}
