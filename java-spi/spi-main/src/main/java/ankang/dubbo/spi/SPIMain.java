package ankang.dubbo.spi;

import java.util.ServiceLoader;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-16
 */
public class SPIMain {

    public static void main(String[] args) {
        final ServiceLoader<HelloService> services = ServiceLoader.load(HelloService.class);
        for (HelloService service : services) {
            System.out.println(service.getClass().getTypeName() + ": " + service.sayHello());
        }

    }

}
