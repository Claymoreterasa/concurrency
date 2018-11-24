package lab.ride.concurrency.atomic;

import lab.ride.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author cwz
 * @date 2018/11/19
 */
@Slf4j
@ThreadSafe
public class AtomicReferenceTest {
    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0, 1);
        count.compareAndSet(0, 2);
        count.compareAndSet(1, 2);
        log.info("count -> {}", count.get());
    }
}
