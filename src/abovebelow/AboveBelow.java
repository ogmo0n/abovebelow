package abovebelow;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class AboveBelow {
	
    static final Scanner input = new Scanner(System.in); // scanner object to use throughout class
	
	// function to build any size array with user input or random numbers
	public static void main(String[] args) {
		int size = arraySize(); // call method to get size
		int[] array = new int[size]; // initialize array to size
		randomOrSpecific(size, array); // method call to determine array values
		//int testInt = getNumber(); // method call to get user's test int
		printNumbers(array, getNumber()); // method call to print count of values above/below test int
	} // end main
	
	// method to get array size
	private static int arraySize() {
		System.out.print("How long is your array? "); // prompt user
		while (true) { // check user input for error
			String s = input.nextLine(); // store input as string
			try {
				int size = Integer.parseInt(s); // convert to int
				return size;
			} catch (NumberFormatException exception) {
				System.out.println("Please enter the length as an integer...");
				System.out.print("How long is your array? "); // prompt user
			}
		}
	}
	
	// method to prompt user for random or specific array values
	// calls methods randomInts or specificInts
	private static void randomOrSpecific(int size, int[] array) {
		// ask user if they have integers or would prefer random integers
		System.out.println("Would you prefer random integers or to input specific integers?");
		int answer = 0; // variable to hold user's response
		do { // loop until proper input
			System.out.println("Press '1' for random integers, or '2' for specifc integers");
			try {
				answer = input.nextInt();
			} catch (InputMismatchException exception) {
				input.nextLine(); // throw out input
			}
		} while (answer != 1 && answer != 2); // loop condition
		
		if (answer == 1) { // if random
			System.out.println("Generating " + size + " random integers for array..."); // inform user of process
			randomInts(array); // call random method
		} else { // else specific
			specificInts(array); // call specific method
		}
	}
	
	// method to generate and fill array with random integers
	private static int[] randomInts(int[] array) {
		for (int i = 0; i < array.length; i++) { // loop length
			Random random = new Random(); // new Random
			int randomInt = random.nextInt(99) + 1; // arbitrary value to keep this more readable
			array[i] = randomInt; // add integer to array
		}
		input.nextLine();
		return array;
	}
	
	// method to allow user to input integers into an array
	private static int[] specificInts(int[] array) {
		int temp = 0; // initialize temporary variable
		for (int i = 0; i < array.length; i++) { // loop for user input
			if (i == 0) {
				System.out.print("Enter an integer you would like in your array: "); // prompt user for contents
			} else if (i < array.length - 1) {
				System.out.print("Enter the next integer for your array: "); // prompt user for contents
			} else {
				System.out.print("Enter the last integer for your array: "); // prompt user for contents
			}
			// check user input for error
			try {
				temp = input.nextInt();
				array[i] = temp;
			} catch (InputMismatchException exception) {
				i -= 1;
				System.out.println("Please only enter integers");
				input.nextLine(); // throw out input
				continue;
			}
		}
		input.nextLine();
		return array;
	}
	
	// method to get individual value to test against array values
	private static int getNumber() {
		System.out.print("Enter an integer to see how many values ");
		System.out.println("within the array are above it and how many are below it."); // inform user of process
		// check user input for error
		while (true) {
			String s = input.nextLine();
			try {
				int test = Integer.parseInt(s);
				return test;
			} catch (NumberFormatException exception) {}
			System.out.println("Please enter an integer..."); // re-prompt user
		}
	}
	
	// method to print total number of array values above/below test integer
	private static void printNumbers(int[] array, int test) {
		int aboveCount = 0; // initialize count for values above test value
		int belowCount = 0; // initialize count for values below test value
		System.out.print("Array = [ "); // print opening bracket
		for (int i = 0; i < array.length; i++) {
			if (array[i] > test) {
				aboveCount++; // count values above test input
			} else if (array[i] < test) {
				belowCount++; // count values below test input
			}
			System.out.print(array[i] + " "); // print array values with space
		}
		System.out.printf("%s%n%s %d%n","]", "Test Number:", test); // print closing bracket and test value
		System.out.printf("%s %d, %s %d%n","Above:", aboveCount, "Below:", belowCount); // print counts above/below
	}
}
