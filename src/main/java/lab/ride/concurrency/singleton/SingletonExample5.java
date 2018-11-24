package lab.ride.concurrency.singleton;

import lab.ride.concurrency.annoations.Recommend;
import lab.ride.concurrency.annoations.ThreadSafe;

/**
 * 枚举模式：最安全
 * @author cwz
 * @date 2018/11/20
 */
@ThreadSafe
@Recommend
public class SingletonExample5 {
    private SingletonExample5(){

    }

    private static SingletonExample5 instance = null;

    public static SingletonExample5 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private SingletonExample5 singleton;

        // JVM保证只调用一次
        Singleton(){
            singleton = new SingletonExample5();
        }

        public SingletonExample5 getInstance(){
            return singleton;
        }
    }



}
