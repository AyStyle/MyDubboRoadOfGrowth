package ankang.dubbo.xml.impl;

import ankang.dubbo.xml.HelloService;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-15
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello: " + name;
    }
}
