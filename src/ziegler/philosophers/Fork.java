package ziegler.philosophers;

public class Fork {
	private boolean inUse;
	private String name;

	public Fork(String name){
		this.name = name;
	}
	
	public void pickUp(){
		inUse = true;
	}
	
	public void putDown(){
		inUse = false;
	}
	
	public boolean inUse(){
		return inUse;
	}
}
