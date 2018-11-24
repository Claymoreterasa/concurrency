package lab.ride.concurrency.atomic;

import lab.ride.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author cwz
 * @date 2018/11/19
 */
@ThreadSafe
@Slf4j
public class AtomicFieldUpdaterTest {
    public volatile int count = 0;

    private static AtomicFieldUpdaterTest instance = new AtomicFieldUpdaterTest();
    private static AtomicIntegerFieldUpdater<AtomicFieldUpdaterTest> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicFieldUpdaterTest.class, "count");

    public static void main(String[] args) {
        updater.compareAndSet(instance, 0, 100);
        log.info("count -> {}", instance.count);
    }
}
