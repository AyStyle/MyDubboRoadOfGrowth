package ankang.dubbo.xml.mock;

import ankang.dubbo.xml.HelloService;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-16
 */
public class HelloServiceMock implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello mock";
    }
}
