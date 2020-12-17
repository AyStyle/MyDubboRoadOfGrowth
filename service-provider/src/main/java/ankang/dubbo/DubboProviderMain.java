package ankang.dubbo;

import ankang.dubbo.service.impl.service.HelloService;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-14
 */
public class DubboProviderMain {

    public static void main(String[] args) throws IOException {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProviderConfig.class);

        context.start();

        while (true) {
            Thread.yield();
        }
    }

    @Configuration
    @PropertySource("classpath:/dubbo-provider.properties")
    @EnableDubbo(scanBasePackages = "ankang.dubbo.service.impl")
    static class ProviderConfig {
        @Bean
        public RegistryConfig registryConfig() {
            final RegistryConfig config = new RegistryConfig();
            config.setAddress("zookeeper://127.0.0.1:2181");

            return config;
        }
    }
}
