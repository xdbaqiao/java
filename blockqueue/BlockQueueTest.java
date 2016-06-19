package javacoreprogramming;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockQueueTest {
	final static int FILE_QUEUE_SIZE = 10;
	final static int SEARCH_THREADS = 2;

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		System.out.println("Please input dir:");
		String dir = in.nextLine();

		System.out.println("Please input keyword:");
		String keyword = in.nextLine();

		BlockingQueue<File> queue = new ArrayBlockingQueue<File>(FILE_QUEUE_SIZE);
		new Thread(new FileEnumerateTask(queue, new File(dir))).start();
		for (int i = 0; i < SEARCH_THREADS; i++) {
			new Thread(new SearchTask(queue, keyword)).start();
		}
		System.out.println("Finish");
		in.close();
	}

}

