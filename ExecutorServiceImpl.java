import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* Q1. Types of thread pool in java
 *  1. FixedThreadPool.
 *  2. cachedThreadPool. - creates thread pool as per demand and also kills it when not in used for 60sec.
 *  3. ScheduledThreadPool. - we can schedule a task AtFixedRate, AtFixedDelay.
 *  4. SingleThreadedPool. - In this case one task is dependent on another task, and it has only one
 *                           thread pool size.
 * */
public class ExecutorServiceImpl {
    public static void main(String[] args) {
        //Get count of available core CPU's.
        final int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("No. of cores " + cores);
        //ExecutorService service = Executors.newCachedThreadPool();
        //ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        //service.scheduleAtFixedRate(new MyTask(), 7, 5, TimeUnit.SECONDS);
        final ExecutorService service = Executors.newFixedThreadPool(cores);

        /* Everytime we are creating a new thread which a costly operation.
        for (int i = 0; i < 100; i++) {
            Thread t1 = new Thread(new MyTask());
            t1.start()
        }*/

        // 1. Instead of starting a new thread we can submit/execute the tasks to thread pool.
        // 2. ThreadPool executor internally uses a blocking queue, and it is storing the tasks.
        // 3. Blocking queue is thread safe that's the reason for using it.
        // 4. one java thread corresponds to one OS thread(no. of CPU's you have).
        // 5. Ideal pool size of thread pool is no. of core CPU's.
        // 6. If your task is IO operation then task bigger pool size ex. 100;
        for (int i = 0; i < 100; i++) {
            service.execute(new MyTask());
        }
    }
}

class MyTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread Name : " + Thread.currentThread().getName());
    }
}
