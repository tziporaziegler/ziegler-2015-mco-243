package ziegler.os.scheduler;

public class PrioritySA extends SchedulerAlgorithm {

	public PrioritySA(int seed) {
		super(seed, true);
		comparator = new PriorityComparator();
		sort();
	}

}
