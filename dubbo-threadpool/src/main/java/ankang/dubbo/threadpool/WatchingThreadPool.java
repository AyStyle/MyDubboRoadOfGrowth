package ankang.dubbo.threadpool;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.threadpool.support.fixed.FixedThreadPool;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-18
 */
public class WatchingThreadPool extends FixedThreadPool implements Runnable {


    private static final double WARNING_RATIO = 0.9;

    private final Map<URL, ThreadPoolExecutor> THREAD_POOLS = new ConcurrentHashMap<>();

    public WatchingThreadPool() {
        new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(this , 3000 , 3000 , TimeUnit.MILLISECONDS);
    }


    @Override
    public Executor getExecutor(URL url) {
        final Executor executor = super.getExecutor(url);
        if (executor instanceof ThreadPoolExecutor) {
            THREAD_POOLS.put(url , (ThreadPoolExecutor) executor);
        }


        return executor;
    }

    @Override
    public void run() {
        for (Map.Entry<URL, ThreadPoolExecutor> entry : THREAD_POOLS.entrySet()) {
            final URL url = entry.getKey();
            final ThreadPoolExecutor executor = entry.getValue();

            final int activeCount = executor.getActiveCount();
            final int poolSize = executor.getPoolSize();
            final double ratio = activeCount * 1.0 / poolSize * 100;

            System.err.println(String.format("%s thread pool use ratio: %5.2f%%" , url.toString() , ratio));
        }


    }

}
