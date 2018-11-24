package lab.ride.concurrency.singleton;

import lab.ride.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式， 资源浪费
 * @author cwz
 * @date 2018/11/20
 */
@ThreadSafe
public class SingletonExample2 {
    private SingletonExample2(){

    }

    private static SingletonExample2 instance = new SingletonExample2();

    public static SingletonExample2 getInstance(){
        return instance;
    }
}
