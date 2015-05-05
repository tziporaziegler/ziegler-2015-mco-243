package ziegler.os.scheduler;

import java.util.List;

public class PrioritySA extends SchedulerAlgorithm {

	public PrioritySA() {
		super(true);
	}

	@Override
	public FakeProcess getNextProcess(List<FakeProcess> list) {
		FakeProcess next = list.get(0);
		int nextPriority = next.getPriority();
		for(FakeProcess process: list){
			int processPriority = process.getPriority();
			if(processPriority < nextPriority){
				next = process;
				nextPriority = processPriority;
			}
		}
		return next;
	}


}
