package ankang.dubbo.balance;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.LoadBalance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-17
 */
public class DubboLoadBalance implements LoadBalance {
    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers , URL url , Invocation invocation) throws RpcException {
        final List<Invoker<T>> list = new ArrayList<>(invokers);

        // 按照IP + 端口排序，取第一个
        list.sort((i1 , i2) -> {
            int compare = i1.getUrl().getIp().compareToIgnoreCase(i2.getUrl().getIp());
            if (compare == 0) {
                compare = Integer.compare(i1.getUrl().getPort() , i2.getUrl().getPort());
            }

            return compare;
        });

        return list.get(0);
    }
}
