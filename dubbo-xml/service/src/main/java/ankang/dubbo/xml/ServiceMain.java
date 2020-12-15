package ankang.dubbo.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-15
 */
public class ServiceMain {

    public static void main(String[] args) throws IOException {
        final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring.xml");
        ctx.start();

        System.in.read();
    }

}
