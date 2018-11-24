package lab.ride.concurrency.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;

/**
 * guava
 * @author cwz
 * @date 2018/11/20
 */
@Slf4j
public class ImmutableExample2 {
    private final static ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);

    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    public static void main(String[] args) {
        list.add(4);
    }
}
