package lab.ride.concurrency.atomic;

import lab.ride.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cwz
 * @date 2018/11/19
 */
@ThreadSafe
@Slf4j
public class AtomicStampReferenceTest {
    public static void main(String[] args) {
        AtomicStampReferenceTest atomicStampReference;
    }
}
