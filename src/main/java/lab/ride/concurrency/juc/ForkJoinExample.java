package lab.ride.concurrency.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author cwz
 * @date 2018/11/21
 */
@Slf4j
public class ForkJoinExample extends RecursiveTask<Integer> {

    private int threaThold = 2;
    private int start;
    private int end;

    public ForkJoinExample(int start, int end){
        this.start = start;
        this.end = end;
    }


    @Override
    protected Integer compute() {
        log.info("{}", Thread.currentThread().getName());
        int sum = 0;

        boolean canCompute = (end - start) <= threaThold;

        if(canCompute){
            for(int i = start; i <= end; i++){
                sum += i;
            }
        } else{
            int middle = (start + end) / 2;
            ForkJoinExample leftTask = new ForkJoinExample(start, middle);
            ForkJoinExample rightTask = new ForkJoinExample(middle + 1, end);

            int leftResult = 0;
            int rightResult = 0;
            try {
                leftResult = leftTask.fork().get();
                rightResult = rightTask.fork().get();
            } catch (Exception e) {
                e.printStackTrace();
            }

            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);

        ForkJoinExample task = new ForkJoinExample(1, 100);

        Future<Integer> result = forkJoinPool.submit(task);

        try {
            log.info("result: {}", result.get());
        } catch (Exception e) {
            log.error("excption", e);
        }
    }

}
