package lab.ride.concurrency.singleton;

import lab.ride.concurrency.annoations.ThreadSafe;

/**
 * 双重检测
 * @author cwz
 * @date 2018/11/20
 */
@ThreadSafe
public class SingletonExample4 {
    private SingletonExample4(){

    }

    //禁止指令重排
    private volatile static SingletonExample4 instance = null;

    public static SingletonExample4 getInstance(){
        if(instance == null){
            synchronized (SingletonExample4.class){
                if(instance == null){   // B
                    // 1 memory = allocate();
                    // 2 ctorInstance();
                    // 3 instance = memory;

                    // JVM和CPU优化，发生了指令重排
                    // 1 3 2
                    instance = new SingletonExample4(); // A - 3
                }
            }
        }
        return instance; //B 还未初始化
    }
}
