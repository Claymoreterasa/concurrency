package lab.ride.concurrency.juc;

import com.google.common.collect.Maps;
import lab.ride.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author cwz
 * @date 2018/11/21
 */
@Slf4j
@ThreadSafe
public class LockExample1 {

    private final Map<String, Data> map = Maps.newTreeMap();

    private final  ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public Data get(String key){
        readLock.lock();
        try{
            return map.get(key);
        }finally {
            readLock.unlock();
        }
    }
    public Set<String> getAllKeys(){
        readLock.lock();
        try {
            return map.keySet();
        }finally {
            readLock.unlock();
        }
    }

    public void set(String key, Data data){
        writeLock.lock();
        try{
            map.put(key, data);
        }finally {
            writeLock.unlock();
        }
    }

    public void remove(String key){
        writeLock.lock();
        try{
            map.remove(key);
        }finally {
            writeLock.unlock();
        }
    }



    class Data {
    }

    public static void main(String[] args) {
    }
}
