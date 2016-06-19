package javacoreprogramming;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class FileEnumerateTask implements Runnable {
	private BlockingQueue<File> queue;
	private File dir;
	public static File DUMMY = new File("");

	public FileEnumerateTask(BlockingQueue<File> queue, File dir) {
		this.queue = queue;
		this.dir = dir;
	}

	@Override
	public void run() {
		try {
			enumerate(dir);
			queue.put(DUMMY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void enumerate(File dir) throws InterruptedException {
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isDirectory())
				enumerate(file);
			else{
				queue.put(file);
			}
		}
	}

}

