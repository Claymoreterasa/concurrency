package lab.ride.concurrency.atomic;

import lab.ride.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author cwz
 * @date 2018/11/19
 */
@ThreadSafe
@Slf4j
public class AtomicBooleanTest {
    private static AtomicBoolean isHappened = new AtomicBoolean(false);
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
                    test();
                    semaphore.release();
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }

        latch.await();
        executorService.shutdown();
        log.info("isHappened -> {}", isHappened.get());
    }

    public static void test(){
        if(isHappened.compareAndSet(false, true)){
            log.info("set isHappened");
        }

    }
}
