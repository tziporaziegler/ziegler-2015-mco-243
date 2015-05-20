package ziegler.scheduler;

import java.util.List;

public class FirstInFirstOutSA extends SchedulerAlgorithm {

	public FirstInFirstOutSA() {
		super(false);
	}

	@Override
	public FakeProcess getNextProcess(List<FakeProcess> list) {
		return list.get(0);
	}

}
