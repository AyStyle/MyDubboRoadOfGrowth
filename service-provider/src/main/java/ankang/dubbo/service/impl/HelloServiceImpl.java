package ankang.dubbo.service.impl;

import ankang.dubbo.service.HelloService;
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

    @Override
    public String sayHello(String name , int timeWait) {
        final long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < timeWait) {
            Thread.yield();
        }

        return "hello: " + name;
    }
}
