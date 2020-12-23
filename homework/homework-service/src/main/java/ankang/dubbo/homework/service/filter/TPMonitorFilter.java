package ankang.dubbo.homework.service.filter;

import org.apache.dubbo.rpc.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-23
 */
public class TPMonitorFilter implements Filter {

    /**
     * 数组的每个格子记录，5秒内的每个请求的时长
     */
    private final BlockingQueue<Integer>[] requestDurationsList = new BlockingQueue[12];

    @Override
    public Result invoke(Invoker<?> invoker , Invocation invocation) throws RpcException {
        final long startTime = System.currentTimeMillis();

        final Result result = invoker.invoke(invocation);

        final long finishTime = System.currentTimeMillis();


        return result;
    }

    /**
     * 将请求耗时添加到{@link this#requestDurationsList}中，这个添加过程是异步执行的
     *
     * @param startTime  请求开始时间
     * @param finishTime 请求完成时间
     */
    private void addRequestDuration(long startTime , long finishTime) {
        // 计算该请求应该存放到的队列
        final int position = (int) (startTime / 1000 % 60 / 5);
        requestDurationsList[position] = requestDurationsList[position] == null ? new LinkedBlockingQueue<>() : requestDurationsList[position];
        BlockingQueue<Integer> requestDurations = requestDurationsList[position];

        requestDurations.offer((int) (finishTime - startTime));
    }

}
