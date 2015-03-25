package ziegler.concurrent;

public class Elevator {
	private int currentFloor;
	private int requestedFloor;

	public boolean isInUse() {
		return requestedFloor > 0;
	}

	//need to make method thread safe
	public void setRequestedFloor(int requestedFloor) {
		if(isInUse()){
			return;
		}
		this.requestedFloor = requestedFloor;
	}
}