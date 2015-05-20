package ziegler.scheduler;

public class FakeProcess {
	private boolean running;
	private int timeToCompletion;

	private int priority;
	private int amtTime;

	public FakeProcess(int priority, int timeToCompletion, boolean pri) {
		this.priority = priority;
		this.timeToCompletion = timeToCompletion;
		amtTime = 0;

		if (pri && priority > 0) {
			timerRun.start();
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

	private Thread timerRun = new Thread() {
		@Override
		public void run() {
			while (true) {
				amtTime++;
				if (amtTime == 100) {
					amtTime = 0;
					priority--;
				}
				if (priority == 0) {
					break;
				}
				try {
					sleep(1);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};

}
