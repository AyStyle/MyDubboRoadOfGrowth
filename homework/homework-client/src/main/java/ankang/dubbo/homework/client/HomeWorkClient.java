package ankang.dubbo.homework.client;

import ankang.dubbo.homework.Service1;
import ankang.dubbo.homework.Service2;
import ankang.dubbo.homework.Service3;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-24
 */
@Component
public class HomeWorkClient {

    @DubboReference
    private Service1 service1;
    @DubboReference
    private Service2 service2;
    @DubboReference
    private Service3 service3;

    public void start(){
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(6 , 6 , 15 , TimeUnit.SECONDS,new ArrayBlockingQueue<>(1000));
        while (true){
            executor.execute(service1::methodA);
            executor.execute(service2::methodB);
            executor.execute(service3::methodC);
        }
    }

}
