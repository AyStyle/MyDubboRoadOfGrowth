package ankang.dubbo.xml.impl;

import ankang.dubbo.xml.HelloService;
import lombok.SneakyThrows;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-15
 */
public class HelloServiceImpl implements HelloService {
    @SneakyThrows
    @Override
    public String sayHello(String name) {
        return "hello: " + name;
    }
}
