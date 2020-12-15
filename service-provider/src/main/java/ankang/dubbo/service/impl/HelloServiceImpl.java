package ankang.dubbo.service.impl;

import ankang.dubbo.service.impl.service.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-14
 */
@DubboService
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello: " + name;
    }
}
