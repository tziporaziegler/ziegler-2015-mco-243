package ziegler.microprocessorSimulation;

public class MicroprocessorSimulation {
	private char[] memArray;
	private char accumulatorA;
	private char accumulatorB;
	private boolean end;

	public MicroprocessorSimulation(String memory) {
		memArray = memory.toCharArray();
		accumulatorA = '0';
		accumulatorB = '0';
		process();
	}

	private void process() {
		int i = 0;
		int num1;
		int num2;
		int location;
		// The input programs will never “fall of the end of memory”, that is, you will never
		// execute an instruction that is located between addresses F0 and FF, inclusive.
		// So don't need loop to run while i < memArray.length
		while (!end) {
			switch (memArray[i]) {
				case '0': // LD: Load accumulator A with the contents of memory at the specified
							// argument. (uses 3 words)
					location = getLocation(memArray[++i], memArray[++i]);
					accumulatorA = memArray[location];
					break;
				case '1': // ST: Write the contents of accumulator A to the memory location
							// specified by the argument. (uses 3 words)
					location = getLocation(memArray[++i], memArray[++i]);
					memArray[location] = accumulatorA;
					break;
				case '2': // SWP: Swap the contents of accumulators A and B.
					char temp = accumulatorA;
					accumulatorA = accumulatorB;
					accumulatorB = temp;
					break;
				case '3': // ADD: Add the contents of accumulators A and B. The low word of the sum
							// is stored in A, and the high word in B.
					num1 = convertHexToBin(accumulatorA);
					num2 = convertHexToBin(accumulatorB);
					int total = num1 + num2;
					String hex = Integer.toHexString(total).toUpperCase();
					if (hex.length() > 1) {
						accumulatorA = hex.charAt(1);
						accumulatorB = hex.charAt(0);
					}
					else {
						accumulatorB = '0';
						accumulatorA = hex.charAt(0);
					}

					break;
				case '4': // INC: Increment accumulator A. Overflow is allowed; that is,
							// incrementing F yields 0.
					num1 = convertHexToBin(accumulatorA);
					if (num1 < 15) {
						num1++;
						accumulatorA = Integer.toHexString(num1).toUpperCase().charAt(0);
					}
					else {
						accumulatorA = '0';
					}

					break;
				case '5': // DEC: Decrement accumulator A. Underflow is allowed; that is,
							// decrementing 0 yields F.
					num1 = convertHexToBin(accumulatorA);
					if (num1 > 0) {
						num1--;
						accumulatorA = Integer.toHexString(num1).toUpperCase().charAt(0);
					}
					else {
						accumulatorA = 'F';
					}
					break;
				case '6': // BZ: If accumulator A is zero, the next command to be executed is at the
							// location specified by the argument. If A is not zero, the argument is
							// ignored and nothing happens. (uses 3 words)
					if (convertHexToBin(accumulatorA) == 0) {
						i = getLocation(memArray[++i], memArray[++i]) - 1;
					}
					else {
						i += 2;
					}
					break;
				case '7': // BR: The next command to be executed is at the location specified by the
							// argument. (uses 3 words)
					i = getLocation(memArray[++i], memArray[++i]) - 1;
					break;
				case '8': // stop execution of the program
					i = memArray.length;
					end = true;
					break;
			}
			i++;
		}
	}

	private int getLocation(char c1, char c2) {
		int num1 = convertHexToBin(c1);
		int num2 = convertHexToBin(c2);
		int location = (num1 * 16) + (num2 * 1);
		return location;
	}

	private int convertHexToBin(Character c) {
		switch (c) {
			case 'A':
				return 10;
			case 'B':
				return 11;
			case 'C':
				return 12;
			case 'D':
				return 13;
			case 'E':
				return 14;
			case 'F':
				return 15;
		}
		return Character.getNumericValue(c);
	}

	public String toString() {
		// dump the contents of memory to the output as a single string of 256 hex characters
		// followed by a newline character.
		StringBuilder builder = new StringBuilder();
		for (char c : memArray) {
			builder.append(c);
		}
		builder.append('\n');
		return builder.toString();
	}

	public static void main(String[] args) {
		MicroprocessorSimulation micro1 = new MicroprocessorSimulation(
				"0102011311321128FF0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
		System.out.println(micro1.toString());

		MicroprocessorSimulation micro2 = new MicroprocessorSimulation(
				"040563B14004220FF31FF041320FE31FE00C2042314200032041314170080000F03000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001");
		System.out.println(micro2.toString());
	}
}
