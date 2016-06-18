package muilthread;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

	private Lock mylock = new ReentrantLock();
	private int index = 0;
	public List<Integer> name;

	public Bank(List<Integer> name) {
		this.name = name;
	}

	public synchronized void Print() {
		while (true) {
			//mylock.lock();
			try {
				Thread.sleep((long) (Math.random() * 100));
				index += 1;
				if (index < name.size())
					System.out.println(Thread.currentThread() + " Number " + name.get(index));
				else
					break;
			} catch (Exception e) {
			} finally {
				//mylock.unlock();
			}
		}
	}
}

