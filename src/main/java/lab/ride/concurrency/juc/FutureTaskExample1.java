package lab.ride.concurrency.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author cwz
 * @date 2018/11/21
 */
@Slf4j
public class FutureTaskExample1 {
    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("do sth in callable");
            Thread.sleep(5000);
            return "Done";
        }
    }
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        log.info("do sth in main");
        Thread.sleep(1000);
        log.info("result -> {}", future.get());
        executorService.shutdown();
    }
}
