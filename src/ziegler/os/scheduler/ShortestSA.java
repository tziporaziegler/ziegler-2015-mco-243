package ziegler.os.scheduler;

import java.util.List;

public class ShortestSA extends SchedulerAlgorithm {

	public ShortestSA() {
		super(false);
	}

	@Override
	public FakeProcess getNextProcess(List<FakeProcess> list) {
		FakeProcess next = list.get(0);
		int nextTime = next.getTimeToCompletion();
		for(FakeProcess process: list){
			int processTime = process.getTimeToCompletion();
			if(processTime < nextTime){
				next = process;
				nextTime = processTime;
			}
		}
		return next;
	}
}
