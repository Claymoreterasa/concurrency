package lab.ride.concurrency.singleton;

import lab.ride.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式
 * @author cwz
 * @date 2018/11/20
 */
@NotThreadSafe
public class SingletonExample1 {
    private SingletonExample1(){

    }

    private static SingletonExample1 instance = null;

    public static SingletonExample1 getInstance(){
        if(instance == null){
            instance = new SingletonExample1();
        }
        return instance;
    }
}
