package threadpool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MatchCounter implements Callable<Integer> {
    public File dir;
    public String keyword;
    public ExecutorService pool;

    public MatchCounter(File dir, String keyword, ExecutorService pool) {
        this.dir = dir;
        this.keyword = keyword;
        this.pool = pool;
    }

    public Integer call() throws Exception {
        int count = 0;
        File[] files = dir.listFiles();
        ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();

        for (File file : files) {
//          System.out.println(file.getPath());
            if (file.isDirectory()) {
                MatchCounter counter = new MatchCounter(file, keyword, pool);
                Future<Integer> result = pool.submit(counter);
                results.add(result);
            } else {
                if (search(file))
                    count++;
            }
        }
        return count;
    }

    private boolean search(File file) {
        Scanner in;
        boolean result = false;
        try {
            in = new Scanner(new FileInputStream(file));

            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.contains(keyword)) {
                    result = true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}

