package ziegler.os.scheduler;

import java.util.Comparator;

public class PriorityComparator implements Comparator<FakeProcess> {

	@Override
	public int compare(FakeProcess o1, FakeProcess o2) {
		int priority1 = o1.getPriority();
		int priority2 = o2.getPriority();
		if (priority1 == priority2) {
			return 0;
		}
		return priority1 < priority2 ? -1 : 1;
	}
}