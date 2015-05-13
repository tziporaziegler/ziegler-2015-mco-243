package ziegler.memoryAllocator;

import java.util.Random;

public class MainMemory {
	private int totalBytes;
	private int totalFree;
	private char[][] memory;
	private int numRows;

	private final static int BYTESPERROW = 128;

	public MainMemory(int numRows) {
		this.numRows = numRows;
		totalBytes = numRows * BYTESPERROW; // ensure that is divisible by 128
		totalFree = totalBytes;
		memory = new char[numRows][BYTESPERROW];
		clear();
		randomPopulate();
	}

	// set all values to '-'
	private void clear() {
		for (int i = 0; i < memory.length; i++) {
			for (int j = 0; j < memory[0].length; j++) {
				memory[i][j] = '-';
			}
		}
	}

	// randomly place values in 1/4 of all memory for testing purposes
	private void randomPopulate() {
		Random random = new Random();
		int i = 0;
		while (i < totalBytes / 4) {
			char next = Character.forDigit(random.nextInt(10), 10);
			memory[random.nextInt(numRows)][random.nextInt(BYTESPERROW)] = next;
			i++;
		}
	}

	// processID is know as pid in UNIX
	public boolean allocate(char pid, int numBytes) {
		if (numBytes > totalFree) {
			return false;
		}

		for (int i = 0; i < memory.length; i++) {
			for (int j = 0; j < memory[0].length && numBytes > 0; j++) {
				if (memory[i][j] == '-') {
					memory[i][j] = pid;
					numBytes--;
					totalFree--;
				}
			}
		}
		return true;
	}

	public void free(char pid) {
		for (int i = 0; i < memory.length; i++) {
			for (int j = 0; j < memory[0].length; j++) {
				if (memory[i][j] == pid) {
					memory[i][j] = '-';
					totalFree++;
				}
			}
		}
	}

	public void print() {
		for (int i = 0; i < memory.length; i++) {
			for (int j = 0; j < memory[0].length; j++) {
				System.out.print(memory[i][j]);
			}
			System.out.println();
		}
	}

	public void defragment() {
		// each place in the array can be 0-9 process or 10 if '-'
		for (int pid = 0; pid <= 10; pid++) {

			// look through the array until find first instance of a value greater than the current pid
			for (int i = 0; i < memory.length; i++) {
				for (int j = 0; j < memory[0].length; j++) {
					if (getValue(memory[i][j]) > pid) {

						boolean found = false;

						// loop through the array until find a value equal to the pid
						for (int k = memory.length - 1; k >= 0 && !found; k--) {
							for (int l = memory[0].length - 1; l >= 0 && !found; l--) {
								if (getValue(memory[k][l]) == pid) {

									// swap
									char temp = memory[i][j];
									memory[i][j] = memory[k][l];
									memory[k][l] = temp;

									found = true;
								}
							}
						}
					}
				}
			}
		}
	}

	public int getValue(char next) {
		if (next == '-') {
			return 10;
		}
		return Character.getNumericValue(next);
	}

	public static void main(String[] args) {
		MainMemory memory = new MainMemory(10);
		System.out.println("randomly fill:");
		memory.print();

		memory.allocate('1', 34);
		System.out.println();
		System.out.println("add 1:");
		memory.print();

		memory.allocate('2', 55);
		System.out.println();
		System.out.println("add 2:");
		memory.print();

		memory.free('5');
		System.out.println();
		System.out.println("free 5:");
		memory.print();

		memory.allocate('1', 45);
		System.out.println();
		System.out.println("add 1:");
		memory.print();

		memory.defragment();
		System.out.println();
		System.out.println("defragment:");
		memory.print();
	}
}
