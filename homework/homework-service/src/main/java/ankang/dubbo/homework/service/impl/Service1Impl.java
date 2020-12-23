package ankang.dubbo.homework.service.impl;

import ankang.dubbo.homework.Service1;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-23
 */
@DubboService(interfaceClass = Service1.class)
@Service
public class Service1Impl implements Service1 {
    @SneakyThrows
    @Override
    public String methodA() {
        final long time = (long) (Math.random() * 101);
        Thread.sleep(time);
        return "this is methodA of Service1";
    }
}
