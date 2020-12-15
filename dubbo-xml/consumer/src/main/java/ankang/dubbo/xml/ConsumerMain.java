package ankang.dubbo.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-15
 */
public class ConsumerMain {

    public static void main(String[] args) {
        final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring.xml");
        ctx.start();


        final HelloService service = ctx.getBean("helloService" , HelloService.class);
        while (true) {
            System.out.println(service.sayHello("ankang"));
        }
    }

}
