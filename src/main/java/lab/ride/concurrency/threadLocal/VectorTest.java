package lab.ride.concurrency.threadLocal;

import java.util.Vector;

/**
 * @author cwz
 * @date 2018/11/20
 */
public class VectorTest {
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(0);
        vector.add(1);
        vector.add(2);

        for(int i = 0; i < vector.size(); i++){
            System.out.println(i);
            vector.remove(i);
            System.out.println(vector);
        }
    }
}
