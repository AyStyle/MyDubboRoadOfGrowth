package ankang.dubbo;

import ankang.dubbo.service.impl.service.HelloService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-15
 */
public class DubboConsumerMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();

        final HelloService service = context.getBean(HelloService.class);
        while (true) {
            final String hello = service.sayHello("ankang" , 30);
            final Future<Object> future = RpcContext.getContext().getFuture();

            System.out.println(String.format("result: %s, future result: %s" , hello , future.get()));
        }

    }

    @Configuration
    @PropertySource("classpath:/dubbo-consumer.properties")
    @ComponentScan(basePackages = "ankang.dubbo.bean")
    @EnableDubbo
    static class ConsumerConfiguration {

    }

}
