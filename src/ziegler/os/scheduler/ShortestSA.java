package ziegler.os.scheduler;

public class ShortestSA extends SchedulerAlgorithm {

	public ShortestSA(int seed) {
		super(seed, false);
		comparator = new ShortestComparator();
		sort();
	}
}
