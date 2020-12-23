package ankang.dubbo.homework.service.impl;

import ankang.dubbo.homework.Service3;
import lombok.SneakyThrows;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-23
 */
public class Service3Impl implements Service3 {
    @SneakyThrows
    @Override
    public String methodC() {
        final long time = (long) (Math.random() * 101);
        Thread.sleep(time);
        return "this is methodC of Service3";
    }
}
