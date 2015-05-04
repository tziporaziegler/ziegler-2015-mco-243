package ziegler.os.scheduler;

import java.util.Comparator;

public class ShortestComparator implements Comparator<FakeProcess> {

	@Override
	public int compare(FakeProcess o1, FakeProcess o2) {
		int compTime1 = o1.getTimeToCompletion();
		int compTime2 = o2.getTimeToCompletion();
		if (compTime1 == compTime2) {
			return 0;
		}
		return compTime1 < compTime2 ? -1 : 1;
	}

}
