package ankang.dubbo.homework.service.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-23
 */
@Activate(group = "provider")
public class TPMonitorFilter implements Filter {

    private final ScheduledThreadPoolExecutor threadPool = new ScheduledThreadPoolExecutor(10);

    /**
     * 数组的每个格子记录，5秒内的每个请求的时长
     */
    private final LinkedList<LinkedList<Integer>> requestDurationsList = new LinkedList<>();

    public TPMonitorFilter() {
        // 创建一个初始的队列，存储请求的时长
        requestDurationsList.addLast(new LinkedList<>());

        // 每隔5秒计算一次TP90和TP99
        threadPool.scheduleAtFixedRate(() -> {
            // 创建一个新的队列，新的请求时长都写到这个队列
            requestDurationsList.addLast(new LinkedList<>());

            // 近一分钟请求耗时集合
            final List<Integer> integers = new ArrayList<>(1 << 32);
            final int batch = requestDurationsList.size();
            // 如果batch大于12说明已经有了近一分钟的数据
            if (batch > 12) {
                // 移除头元素，计算完本次指标之后，后面的指标计算都不会使用这个元素
                final LinkedList<Integer> first = requestDurationsList.removeFirst();
                integers.addAll(first);
            }
            // batch大于12，则：把剩下的11个集合数据存入
            // batch小于12，此时不足一分钟，除了最后一个batch，剩下的全部数据都计算
            for (int i = 0 ; i < (batch > 12 ? 11 : batch - 1) ; i++) {
                integers.addAll(requestDurationsList.get(i));
            }

            // 计算TP90指标和TP99指标
            // 将集合转换为数组
            final Integer[] collect = integers.toArray(new Integer[0]);

            // 计算总请求数、TP90请求数、TP99请求数
            int totalNum = collect.length;
            int tp90Num = (int) Math.ceil(totalNum * 0.1);
            int tp99Num = (int) Math.ceil(totalNum * 0.01);

            // 计算TP90和TP99数据的位置
            final int tp90Pos = tp90Num - 1;
            final int tp99Pos = tp99Num - 1;

            // 逆序排列数据，只排列大于TP90的请求
            sort(collect , tp90Num , true);

            final int tp90Value = collect[tp90Pos];
            final int tp99Value = collect[tp99Pos];

            System.err.println(String.format("TP90监控阈值：%10d，慢请求数：%10d，总请求数：%10d" , tp90Value , tp90Num , totalNum));
            System.err.println(String.format("TP99监控阈值：%10d，慢请求数：%10d，总请求数：%10d" , tp99Value , tp99Num , totalNum));
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
     * 全局排序部分数组，使用选择排序算法
     *
     * @param ints   被排序的数组
     * @param num    排序元素的个数
     * @param isDesc 是否降序排序
     */
    private void sort(Integer[] ints , int num , boolean isDesc) {
        for (int i = 0 ; i < num ; i++) {

            for (int j = i + 1 ; j < ints.length ; j++) {
                final int out = ints[i];
                final int in = ints[j];
                if (out != in) {
                    ints[i] = isDesc ? Math.max(out , in) : Math.min(out , in);
                    ints[j] = isDesc ? Math.min(out , in) : Math.max(out , in);
                }
            }
        }
    }

}
