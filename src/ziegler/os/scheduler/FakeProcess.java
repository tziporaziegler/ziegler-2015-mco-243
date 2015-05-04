package ziegler.os.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FakeProcess {
	private boolean running;
	private int timeToCompletion;
	
	private int priority;
	private ScheduledExecutorService executor;
	private int amtTime;

	public FakeProcess(int priority, int timeToCompletion, boolean pri) {
		this.priority = priority;
		this.timeToCompletion = timeToCompletion;
		amtTime = 0;

		if (pri && priority > 0) {
			executor = Executors.newScheduledThreadPool(1);
			executor.scheduleAtFixedRate(timerRun, 0, 20, TimeUnit.MILLISECONDS);
		}
	}

	public void run(int quantum) {
		amtTime = 0;
		running = true;
		
		while (timeToCompletion > 0 && quantum > 0) {
			timeToCompletion--;
			quantum--;
			try {
				Thread.sleep(1);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (timeToCompletion == 0) {
			running = false;
		}
	}

	public boolean isStillRunning() {
		return running;
	}

	public int getTimeToCompletion() {
		return timeToCompletion;
	}

	public int getPriority() {
		return priority;
	}

	private Runnable timerRun = new Runnable() {
		@Override
		public void run() {
			amtTime++;
			if (amtTime == 50) {
				amtTime = 0;
				priority--;
			}
			if (priority == 0) {
				executor.shutdown();
			}
		}
	};

}
