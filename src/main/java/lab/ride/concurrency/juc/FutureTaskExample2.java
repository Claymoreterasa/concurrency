package lab.ride.concurrency.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author cwz
 * @date 2018/11/21
 */
@Slf4j
public class FutureTaskExample2 {

    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do sth in callable");
                Thread.sleep(5000);
                return "Done";
            }
        });

        new Thread(futureTask).start();
        log.info("do sth in main");
        Thread.sleep(1000);
        log.info("result -> {}", futureTask.get());
    }

}
