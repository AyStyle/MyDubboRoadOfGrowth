package ankang.duubo.spi.impl;

import ankang.dubbo.spi.HelloService;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-16
 */
public class DogHelloService implements HelloService {
    @Override
    public String sayHello() {
        return "wang wang !";
    }
}
