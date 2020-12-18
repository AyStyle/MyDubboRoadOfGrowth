package ankang.dubbo.bean;

import ankang.dubbo.service.impl.service.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-15
 */
@Component
public class ConsumerComponent {

    @DubboReference(async = true)
    private HelloService helloService;

    public String sayHello(String name) {
        return helloService.sayHello(name);
    }

    public String sayHello(String name , int timeWait) {
        return helloService.sayHello(name , timeWait);
    }

}
