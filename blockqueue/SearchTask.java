package javacoreprogramming;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class SearchTask implements Runnable {
	private BlockingQueue<File> queue;
	private String keyword;

	public SearchTask(BlockingQueue<File> queue, String keyword) {
		this.queue = queue;
		this.keyword = keyword;
	}

	@Override
	public void run() {
		boolean done = false;
		while (!done) {
			File file;
			try {
				file = queue.take();
				if (file != FileEnumerateTask.DUMMY) {
					search(file);
				} else {
					done = true;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void search(File file) {
		Scanner in;
		
		try {
			int linenumber = 0;
			in = new Scanner(new FileInputStream(file));
			while (in.hasNextLine()) {
				linenumber += 1;
				String line = in.nextLine();
				if (line.contains(keyword)) {
					System.out.printf("Line number %s, Find %s in %s\n", linenumber, keyword, file.getPath());
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}

