package lab.ride.concurrency.publish;

import lab.ride.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 对象逸出：当一个对象还没有构造完成时，就使它被其它线程所见
 * 内部类中this引用逸出， 构造函数中this引用逸出
 * @author cwz
 * @date 2018/11/20
 */
@Slf4j
@NotThreadSafe
public class Escape {
    private int thisCanBeEscape = 0;

    public Escape(){
        new InnerClass();
    }

    private class InnerClass{
        public InnerClass(){
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
