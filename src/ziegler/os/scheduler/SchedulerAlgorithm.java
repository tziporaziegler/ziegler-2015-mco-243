package ziegler.os.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class SchedulerAlgorithm {
	protected List<FakeProcess> processList;
	private Random random;

	public SchedulerAlgorithm(boolean pri) {
		processList = new ArrayList<FakeProcess>();

		// start all random at same seed number for testing purposes
		// want separate Randoms since want to restart from the beginning
		// create new List in each SA instead of sending in same ArrayList Object to all the SAs and making a copy of the List's contents
		random = new Random(675);

		for (int i = 0; i < 100; i++) {
			int priority = random.nextInt(10);
			int timeToCompletion = random.nextInt(30);
			FakeProcess process = new FakeProcess(priority, timeToCompletion, pri);
			processList.add(process);
		}
	}

	// returns next process on list
	public abstract FakeProcess getNextProcess(List<FakeProcess> list);

	public List<FakeProcess> getList() {
		return processList;
	}
}
