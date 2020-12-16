package ankang.dubbo.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;


/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-16
 */
@SPI
// @SPI("cat") 设置默认的扩展点
public interface HelloService {

    String sayHello();

    @Adaptive
    // @Adaptive("hello") 使用hello作为扩展点的名称，默认是接口名，且接口名大写字母替换.+小写字母，例如：DubboApacheOrg -> dubbo.apache.org
    String sayHello(URL url);

}
