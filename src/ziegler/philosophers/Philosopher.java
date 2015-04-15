package ziegler.philosophers;

import java.util.Random;

public class Philosopher extends Thread {
	private final static Random RANDOM = new Random();

	private Fork rightFork;
	private Fork leftFork;
	//private Philosopher rightNeighbor;
	//private Philosopher leftNeighbor;
	//private boolean isEating;
	private String name;

	public Philosopher(Fork fork1, Fork fork2, String name) {
		rightFork = fork1;
		leftFork = fork2;
		this.name = name;
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
		synchronized (rightFork) {
			synchronized (leftFork) {
				rightFork.pickUp();
				leftFork.pickUp();
				System.out.println(name + " is eating");
				sleepRange(500,2000);
				System.out.println(name + " is done eating");
			}
		}
	}

	public void think() {
		rightFork.putDown();
		leftFork.putDown();
		System.out.println(name + " is thinking");
		sleepRange(500,2000);
	}

	public void sleepRange(int low, int high){
		try {
			sleep(RANDOM.nextInt(high-low) + low);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//private boolean getIsEating() {
		//	return isEating;
		//}
	
	//public void setNeighbors(Philosopher philo1, Philosopher philo2) {
	//	rightNeighbor = philo1;
	//	leftNeighbor = philo2;
	//}
}