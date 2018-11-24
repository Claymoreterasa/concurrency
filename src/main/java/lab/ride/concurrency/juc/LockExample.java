package lab.ride.concurrency.juc;

import lab.ride.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cwz
 * @date 2018/11/21
 */
@Slf4j
@ThreadSafe
public class LockExample {
    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static int count = 0;

    private final static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch latch = new CountDownLatch(clientTotal);
        final Semaphore semaphore = new Semaphore(threadTotal);
        for(int i = 0; i < clientTotal; i++){
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }

            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }

        executorService.shutdown();
        log.info("count -> {}", count);

    }

    public static void add(){
        lock.lock();
        try{
            count++;
        }finally {
            lock.unlock();
        }

    }
}
