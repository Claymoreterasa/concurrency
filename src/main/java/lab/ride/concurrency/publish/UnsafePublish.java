package lab.ride.concurrency.publish;

import lab.ride.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 对象发布：使一个对象被当前范围之外的代码所使用， 如类方法返回对象，公有静态对象，将对象引用传递给外部方法
 * @author cwz
 * @date 2018/11/20
 */
@NotThreadSafe
@Slf4j
public class UnsafePublish {
    private String[] states = {"a", "b", "c"};

    public String[] getStates(){
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
        unsafePublish.getStates()[0] = "d";
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
    }
}
