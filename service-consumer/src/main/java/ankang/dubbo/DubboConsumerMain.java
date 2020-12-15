package ankang.dubbo;

import ankang.dubbo.service.impl.service.HelloService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-15
 */
public class DubboConsumerMain {

    public static void main(String[] args) {

        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();

        final HelloService service = context.getBean(HelloService.class);
        while (true) {
            final String hello = service.sayHello("ankang");
            System.out.println(hello);
        }

    }

    @Configuration
    @PropertySource("classpath:/dubbo-consumer.properties")
    @ComponentScan(basePackages = "ankang.dubbo.bean")
    @EnableDubbo
    static class ConsumerConfiguration {

    }

}
