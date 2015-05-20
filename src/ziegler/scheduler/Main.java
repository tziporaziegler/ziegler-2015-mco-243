package ziegler.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

	public Main() {
		newSchedule(new PrioritySA(), "Based on Priority");
		newSchedule(new FirstInFirstOutSA(), "First In First Out");
		newSchedule(new ShortestSA(), "Shortest Process First");
	}

	private void newSchedule(SchedulerAlgorithm algo, String name) {
		// use executor to wait 500ms and then check how many processes each Scheduler completed
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