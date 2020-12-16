package ankang.duubo.spi.impl;

import ankang.dubbo.spi.HelloService;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-16
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello() {
        return "hello spi";
    }
}
