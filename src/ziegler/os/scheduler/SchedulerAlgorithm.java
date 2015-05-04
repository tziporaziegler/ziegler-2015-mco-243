package ziegler.os.scheduler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public abstract class SchedulerAlgorithm {
	protected List<FakeProcess> processList;
	protected Comparator<FakeProcess> comparator;
	private Random random;

	public SchedulerAlgorithm(int seed, boolean pri) {
		processList = new ArrayList<FakeProcess>();
		random = new Random(seed);
		
		for (int i = 0; i < 100; i++) {
			int priority = random.nextInt(10);
			int timeToCompletion = random.nextInt(30);
			FakeProcess process = new FakeProcess(priority, timeToCompletion, pri);
			processList.add(process);
		}
	}

	// returns next process on list
	public FakeProcess getNextProcess(List<FakeProcess> list) {
		return processList.get(0);
	}

	public void sort() {
		processList.sort(comparator);
	}

	public List<FakeProcess> getList() {
		return processList;
	}
}
