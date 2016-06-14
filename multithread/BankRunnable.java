package muilthread;

import java.util.ArrayList;
import java.util.List;

public class BankRunnable implements Runnable {
	public List<Integer> name;
	public static final int THREADS_NUM = 10;
	public static final int MAX_CAP = 100000;
	public Bank bank;

	public BankRunnable(Bank bank) {
		this.bank = bank;

	}

	public void run() {
		bank.Print();
	}

	public static void main(String[] args) {
		List<Integer> queue = new ArrayList<Integer>(MAX_CAP);
		for (int i = 0; i < 100; i++) {
			queue.add(i);
		}
		Bank bank = new Bank(queue);
		for (int i = 0; i < THREADS_NUM; i++) {
			BankRunnable tx = new BankRunnable(bank);
			Thread te = new Thread(tx);
			te.start();
		}
	}
}

