package lab.ride.concurrency.singleton;

import lab.ride.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式
 * @author cwz
 * @date 2018/11/20
 */
@ThreadSafe
public class SingletonExample3 {
    private SingletonExample3(){

    }

    private static SingletonExample3 instance = null;

    public synchronized static SingletonExample3 getInstance(){
        if(instance == null){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
