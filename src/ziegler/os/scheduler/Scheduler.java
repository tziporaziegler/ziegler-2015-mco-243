package ziegler.os.scheduler;

import java.util.List;

public class Scheduler {
	private static final int QUANTUM = 10;
	private List<FakeProcess> list;
	private SchedulerAlgorithm algorithm;

	public Scheduler(SchedulerAlgorithm algorithm) {
		this.algorithm = algorithm;
		this.list = algorithm.getList();
	}

	public void run() {
		while (!list.isEmpty()) {
			FakeProcess process = algorithm.getNextProcess(list);
			list.remove(process);
			process.run(QUANTUM);

			if (process.isStillRunning()) {
				list.add(process);
				algorithm.sort();
			}
		}
	}
}