package threadpool;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolTest {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please input dir:");
        String dir = in.nextLine();

        System.out.println("Please input keyword:");
        String keyword = in.nextLine();

        ExecutorService pool = Executors.newCachedThreadPool();
        MatchCounter counter = new MatchCounter(new File(dir), keyword, pool);
        Future<Integer> result = pool.submit(counter);

        try {
            System.out.printf("result num: %s\n", result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        in.close();
        pool.shutdown();
        int poolsize = ((ThreadPoolExecutor) pool).getPoolSize();
        System.out.printf("The pool size is %s", poolsize);
    }
}

