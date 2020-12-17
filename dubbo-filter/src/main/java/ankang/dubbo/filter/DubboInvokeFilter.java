package ankang.dubbo.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import java.time.LocalDateTime;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-17
 */
@Activate(group = {CommonConstants.PROVIDER , CommonConstants.CONSUMER})
public class DubboInvokeFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker , Invocation invocation) throws RpcException {
        System.out.println(LocalDateTime.now());

        return invoker.invoke(invocation);
    }
}
