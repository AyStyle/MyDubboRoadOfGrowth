package ankang.dubbo.spi.impl;

import ankang.dubbo.spi.HelloService;
import org.apache.dubbo.common.URL;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-16
 */
public class CatHelloServiceImpl  implements HelloService {
    @Override
    public String sayHello() {
        return "miao miao !";
    }

    @Override
    public String sayHello(URL url) {
        return "url miao miao !";
    }
}
