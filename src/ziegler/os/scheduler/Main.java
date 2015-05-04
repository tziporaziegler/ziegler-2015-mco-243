package ziegler.os.scheduler;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

	public Main() {
		// send in same number to all SA so always same seed for random
		Random random = new Random();
		int seed = random.nextInt();
		
		SchedulerAlgorithm algo = new PrioritySA(seed);
		newSchedule(algo, "Based on Priority");

		SchedulerAlgorithm algo1 = new FirstInFirstOutSA(seed);
		newSchedule(algo1, "First In First Out");

		SchedulerAlgorithm algo2 = new ShortestSA(seed);
		newSchedule(algo2, "Shortest Process First");
	}

	private void newSchedule(SchedulerAlgorithm algo, String name) {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

		Runnable timerRun = new Runnable() {
			@Override
			public void run() {
				int size = algo.getList().size();
				System.out.println(name + " completed " + (100 - size) + " processess in 500 milliseconds");
				executor.shutdown();
			}
		};

		executor.scheduleAtFixedRate(timerRun, 500, 1, TimeUnit.MILLISECONDS);

		long startTime = System.currentTimeMillis();
		new Scheduler(algo).run();
		long endTime = System.currentTimeMillis();
		System.out.println(name + " completed all processes in " + (endTime - startTime) + " millliseconds");
	}
	
	public static void main(String[] args) {
		new Main();
	}
}