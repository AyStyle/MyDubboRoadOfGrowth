package ankang.dubbo.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-17
 */
public class DubboSpiAdaptiveMain {

    public static void main(String[] args){
        // 重点是url参数部分，可以简写为：?hello.service=human
        // url参数hello.service是接口HelloService将大写字母转为.+小写字母的结果
        // url参数hello.service的结果human是Dubbo SPI扩展点的key
        final URL url = URL.valueOf("test://localhost:4321?hello.service=human");

        final HelloService service = ExtensionLoader.getExtensionLoader(HelloService.class).getAdaptiveExtension();

        final String hello = service.sayHello(url);

        System.out.println(hello);
    }

}
