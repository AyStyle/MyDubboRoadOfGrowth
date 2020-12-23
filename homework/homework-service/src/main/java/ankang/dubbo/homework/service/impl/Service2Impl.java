package ankang.dubbo.homework.service.impl;

import ankang.dubbo.homework.Service2;
import lombok.SneakyThrows;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-23
 */
public class Service2Impl implements Service2 {
    @SneakyThrows
    @Override
    public String methodB() {
        final long time = (long) (Math.random() * 101);
        Thread.sleep(time);
        return "this is methodB of Service2";
    }
}
