package ziegler.compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Compiler {
	private char[] memory;

	public Compiler(String filename) throws FileNotFoundException {
		memory = new char[256];

		Scanner scanner = new Scanner(new File(filename));

		int i = 0;
		boolean add;
		while (scanner.hasNext() && i < 256) {
			add = true;
			String instruction = scanner.next().toUpperCase();
			switch (instruction) {
				case "LD":
					memory[i] = '0';
					i = addLocation(scanner, i, instruction);
					break;
				case "ST":
					memory[i] = '1';
					i = addLocation(scanner, i, instruction);
					break;
				case "SWP":
					memory[i] = '2';
					break;
				case "ADD":
					memory[i] = '3';
					break;
				case "INC":
					memory[i] = '4';
					break;
				case "DEC":
					memory[i] = '5';
					break;
				case "BZ":
					memory[i] = '6';
					i = addLocation(scanner, i, instruction);
					break;
				case "BR":
					memory[i] = '7';
					i = addLocation(scanner, i, instruction);
					break;
				case "STP":
					memory[i] = '8';
					break;
				case "DATA":
					int location = scanner.nextInt();
					char value = Integer.toHexString(scanner.nextInt()).toUpperCase().charAt(0);
					memory[location] = value;
					add = false;
					break;
				default:
					add = false;
					boolean invalid = false;
					if (instruction.length() > 1) {
						if (instruction.charAt(0) == '/' && instruction.charAt(1) == '/') {
							// if the instruction begins with // will ignore and then automatically
							// go to nextLine
						}
						else {
							invalid = true;
						}
					}
					else {
						invalid = true;
					}
					if (invalid) {
						System.out.println("invalid instruction");
						System.exit(0);
					}
			}

			if (scanner.hasNextLine()) {
				scanner.nextLine();
			}

			if (add) {
				i++;
			}
		}

		scanner.close();
	}

	private int addLocation(Scanner scanner, int i, String instruction) {
		if (instruction.length() == 2) {
			String hex = Integer.toHexString(scanner.nextInt()).toUpperCase();
			if (hex.length() > 1) {
				memory[++i] = hex.charAt(0);
				memory[++i] = hex.charAt(1);
			}
			else {
				memory[++i] = '0';
				memory[++i] = hex.charAt(0);
			}
		}
		return i;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < memory.length; i++) {
			char next = memory[i];
			if(next == 0){
				builder.append('0');
			}
			else{
			builder.append(next);
			}
		}
		return builder.toString();
	}

	public static void main(String[] args) {
		try {
			Compiler compiler = new Compiler("compileData");
			System.out.println(compiler.toString());
			Compiler compiler2 = new Compiler("compileData2");
			System.out.println(compiler2.toString());
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}