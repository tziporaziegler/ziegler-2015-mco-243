package ziegler.philosophers;

import java.util.Random;

public class Philosopher extends Thread {
	private final static Random RANDOM = new Random();
	private String name;

	private Fork firstFork;
	private Fork secondFork;

	public Philosopher(Fork fork1, Fork fork2, String name) {
		int fork1Num = fork1.getForkNum();
		int fork2Num = fork2.getForkNum();
		if (fork1Num < fork2Num) {
			setForks(fork1, fork2);
		}
		else {
			setForks(fork2, fork1);
		}

		this.name = name;
	}

	public void setForks(Fork firstFork, Fork secondFork) {
		this.firstFork = firstFork;
		this.secondFork = secondFork;
	}

	@Override
	public void run() {
		while (true) {
			// randomly eat and think for a duration between 500-2000 milliseconds
			eat();
			think();
		}
	}

	public void eat() {
		synchronized (firstFork) {
			synchronized (secondFork) {
				firstFork.pickUp();
				secondFork.pickUp();
				System.out.println(name + " is eating");
				sleepRange(500, 2000);
				System.out.println(name + " is done eating");
			}
		}
	}

	public void think() {
		firstFork.putDown();
		secondFork.putDown();
		System.out.println(name + " is thinking");
		sleepRange(500, 2000);
	}

	public void sleepRange(int low, int high) {
		try {
			sleep(RANDOM.nextInt(high - low) + low);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}