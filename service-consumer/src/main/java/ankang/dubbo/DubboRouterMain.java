package ankang.dubbo;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.registry.Registry;
import org.apache.dubbo.registry.RegistryFactory;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-18
 */
public class DubboRouterMain {

    public static void main(String[] args) {
        // 注册中心工厂对象
        final RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();

        // 获取注册中心
        final URL registryUrl = URL.valueOf("zookeeper://localhost:2181");
        final Registry registry = registryFactory.getRegistry(registryUrl);

        // 注册条件路由
        final URL conditionUrl = URL.valueOf("condition://0.0.0.0/ankang.dubbo.service.HelloService?category=routers&force=true&dynamic=true&rule=" + URL.encode("=>host!=localhost"));
        registry.register(conditionUrl);

    }

}
