package ziegler.memoryAllocator;

import java.util.Random;

public class MainMemory {
	private int totalBytes;
	private int totalFree;
	private int[][] memory;
	private int numRows;

	private final static int BYTESPERROW = 128;

	public MainMemory(int numRows) {
		this.numRows = numRows;
		totalBytes = numRows * BYTESPERROW; // ensure that is divisible by 128
		totalFree = totalBytes;
		memory = new int[numRows][BYTESPERROW];
		randomPopulate();
	}

	// randomly place values in 1/4 of all memory for testing purposes
	private void randomPopulate() {
		Random random = new Random();
		int i = 0;
		while (i < totalBytes / 4) {
			memory[random.nextInt(numRows)][random.nextInt(BYTESPERROW)] = random.nextInt(10);
			i++;
		}
	}

	// processID is know as pid in UNIX
	public boolean allocated(int pid, int numBytes) {
		if (numBytes > totalFree) {
			return false;
		}

		for (int i = 0; i < memory.length; i++) {
			for (int j = 0; j < memory[0].length && numBytes > 0; j++) {
				if (memory[i][j] == 0) {
					memory[i][j] = pid;
					numBytes--;
					totalFree--;
				}
			}
		}
		return true;
	}

	public void free(int pid) {
		for (int i = 0; i < memory.length; i++) {
			for (int j = 0; j < memory[0].length; j++) {
				if (memory[i][j] == pid) {
					memory[i][j] = 0;
					totalFree++;
				}
			}
		}
	}

	public void print() {
		for (int i = 0; i < memory.length; i++) {
			for (int j = 0; j < memory[0].length; j++) {
				int next = memory[i][j];
				if (next == 0) {
					System.out.print('-');
				}
				else {
					System.out.print(next);
				}
			}
			System.out.println();
		}
	}

	public void defragment() {
		for (int pid = 1; pid <= 9; pid++) {
			for (int i = 0; i < memory.length; i++) {
				for (int j = 0; j < memory[0].length; j++) {
					int next = memory[i][j];
					if (next > pid) {
						boolean found = false;
						for (int k = 0; k < memory.length && !found; k++) {
							for (int l = 0; l < memory[0].length && !found; l++) {
								if (memory[k][l] == pid || memory[k][l] == 0) {
									swap(i, j, k, l);
									found = true;
								}
							}
						}
					}
				}
			}
		}
	}

	private void swap(int i, int j, int k, int l) {
		int temp = memory[i][j];
		memory[i][j] = memory[k][l];
		memory[k][l] = temp;
	}

	public static void main(String[] args) {
		MainMemory memory = new MainMemory(10);
		memory.print();

		memory.allocated(1, 34);
		System.out.println();
		memory.print();

		memory.allocated(2, 55);
		System.out.println();
		memory.print();

		memory.free(5);
		System.out.println();
		memory.print();

		memory.allocated(1, 45);
		System.out.println();
		memory.print();

		memory.defragment();
		System.out.println();
		memory.print();
	}
}
