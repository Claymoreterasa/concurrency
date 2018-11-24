package lab.ride.concurrency.atomic;

import lab.ride.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author cwz
 * @date 2018/11/19
 */
@ThreadSafe
@Slf4j
public class LongAddrTest {

    // 数组和组成Long，单点更新压力分布在各个数组位置，longAdder在统计的时候如果有并发更新，可能导致统计的数据有误差
    private static LongAdder count = new LongAdder();

    // 并发请求数
    private static final int clientCount = 5000;

    // 并发线程数量
    private static final int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadCount);
        final CountDownLatch latch = new CountDownLatch(clientCount);
        for(int i = 0; i < clientCount; i++){
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }

        latch.await();
        executorService.shutdown();
        log.info("count -> {}", count.longValue());
    }

    public static void add(){
        count.increment();
    }
}
