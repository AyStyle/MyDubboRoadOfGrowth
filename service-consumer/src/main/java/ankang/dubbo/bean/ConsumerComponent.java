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

    @DubboReference(loadbalance = "onlyFirst")
    private HelloService helloService;

    public String sayHello(String name) {
        return helloService.sayHello(name);
    }


}
