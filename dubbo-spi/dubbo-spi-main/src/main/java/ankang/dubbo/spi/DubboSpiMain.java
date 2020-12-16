package ankang.dubbo.spi;

import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.Set;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-16
 */
public class DubboSpiMain {

    public static void main(String[] args){
        // 获取dubbo扩展加载器
        final ExtensionLoader<HelloService> extensionLoader = ExtensionLoader.getExtensionLoader(HelloService.class);

        // 遍历所有支持的扩展点META-INF/dubbo
        final Set<HelloService> services = extensionLoader.getSupportedExtensionInstances();
        for (HelloService service : services) {
            final String hello = service.sayHello();
            System.out.println(hello);
        }


    }

}
