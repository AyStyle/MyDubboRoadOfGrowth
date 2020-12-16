package ankang.dubbo.spi.impl;

import ankang.dubbo.spi.HelloService;
import org.apache.dubbo.common.URL;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-16
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello() {
        return "hello dubbo";
    }

    @Override
    public String sayHello(URL url) {
        return "hello url dubbo";
    }
}
