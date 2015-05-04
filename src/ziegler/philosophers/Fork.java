package ziegler.philosophers;

public class Fork {
	private boolean inUse;
	private int forkNum;
	private String name;

	public Fork(int forkNum){
		this.forkNum = forkNum;
		name = String.valueOf(forkNum);
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
	
	public int getForkNum(){
		return forkNum;
	}
	
	public String getName(){
		return name;
	}
}
