package ankang.dubbo.homework.service.filter;

import org.apache.dubbo.rpc.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-23
 */
public class TPMonitorFilter implements Filter {

    private final ScheduledThreadPoolExecutor threadPool = new ScheduledThreadPoolExecutor(10);

    /**
     * 数组的每个格子记录，5秒内的每个请求的时长
     */
    private final LinkedList<LinkedList<Integer>> requestDurationsList = new LinkedList<>();

    public TPMonitorFilter() {
        // 创建一个初始的队列，存储请求的时长
        requestDurationsList.add(new LinkedList<>());

        // 每隔5秒计算一次TP90和TP99
        threadPool.scheduleAtFixedRate(() -> {
            // 创建一个新的队列，新的请求时长都写到这个队列
            requestDurationsList.add(new LinkedList<>());

            // 计算TP90指标和TP99指标
            if (requestDurationsList.size() > 12) {
                final List<LinkedList<Integer>> subList = requestDurationsList.subList(0 , 12);
                // 移除头元素，之后的指标都不会使用这个元素
                requestDurationsList.removeFirst();

                // 逆序排列所有数据
                final List<Integer> collect = subList.stream().parallel().flatMap(List::stream).sorted((i , j) -> j - i).collect(Collectors.toList());

                // 计算TP90和TP99数据的位置
                final int tp90Pos = (int) (collect.size() * 0.9);
                final int tp99Pos = (int) (collect.size() * 0.99);

                collect.get(tp90Pos);
                collect.get(tp99Pos);

            } else {

            }
        } , 5 , 5 , TimeUnit.SECONDS);
    }

    @Override
    public Result invoke(Invoker<?> invoker , Invocation invocation) throws RpcException {
        final long startTime = System.currentTimeMillis();

        final Result result = invoker.invoke(invocation);

        final long finishTime = System.currentTimeMillis();

        addRequestDuration(startTime , finishTime);

        return result;
    }

    /**
     * 将请求耗时添加到{@link this#requestDurationsList}中，这个添加过程是异步执行的
     *
     * @param startTime  请求开始时间
     * @param finishTime 请求完成时间
     */
    private void addRequestDuration(long startTime , long finishTime) {
        // 获取当前写入队列
        final LinkedList<Integer> requestDurations = requestDurationsList.getLast();

        // 异步的将耗时时长存入队列中
        threadPool.execute(() -> requestDurations.addLast((int) (finishTime - startTime)));
    }

    /**
     * 计算毫秒数对应的{@link this#requestDurationsList}队列位置
     *
     * @param milliseconds
     * @return
     */
    private int calculateRequestDurationsPosition(long milliseconds) {
        return (int) (milliseconds / 1000 % 60 / 5);
    }

}
