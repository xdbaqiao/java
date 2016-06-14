package muilthread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadsRunnable implements Runnable {
	public BlockingQueue<Integer> name;
	public static final int THREADS_NUM = 10;
	public static final int MAX_CAP = 100000;
 
	public ThreadsRunnable(BlockingQueue<Integer> name){
		this.name = name;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 
			try {
				System.out.println("Number " + name.remove());
			} catch (java.util.NoSuchElementException e) {
				break;
			}
		}
	}

	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(MAX_CAP);

		for (int i = 0; i < 1000; i++) {
			queue.add(i);
		}

		for (int i = 0; i < THREADS_NUM; i++) {
			ThreadsRunnable tx = new ThreadsRunnable(queue);
			Thread te = new Thread(tx);
			te.start();
		}
	}
}

