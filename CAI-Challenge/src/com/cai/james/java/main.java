package com.cai.james.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class main {

	/*By James Floyd
	 * 
	 * Create an example for one, five, and ten How the file, CSV, should be
	 * formated: number, current degree type(F, desired conversion(Kelven)
	 * 
	 * String = Temperature Type, Double = Value
	 */
	static HashMap<String, Double> newTable = new HashMap<String, Double>();
	// For index on how many in the key, and type for temperature.
	// Type: 1: F, 2: C, 3 K.
	public static int index = 0, type = 0;
	public static temperatureConversion tC = new temperatureConversion();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		boolean onLoop = true;
		Scanner input = new Scanner(System.in);

		System.out.println("Welcome to JFloyd Emplyee Management System! \n" + "Please insert a number to continue");

		while (onLoop) {
			mainMenu();
			int indexPointer = input.nextInt();

			switch (indexPointer) {
			case 1:
				fileSelection();
				// openFile();
				printTable();

				break;
			case 2:
				findAverage();
				break;
			case 3:
				lowestValue();
				break;
			case 4:
				absoluteTempDrop();
				break;
			case 5:
				exportFile();
				break;
			case 6:
				refresh();
				break;
			case 7:
				System.out.println("Goodbye!");
				onLoop = false;
				break;
			default:
				System.out.println("ERROR! Please Enter a valid integer");
				break;

			}
		}
		input.close();

	}

	public static void mainMenu() {
		System.out.println("===Main Menu===");
		System.out.println("1. Open File");
		System.out.println("2. Print Average");
		System.out.println("3. Print Lowest value");
		System.out.println("4. Print Absolute temperature drop table");
		System.out.println("5. Export File");
		System.out.println("6. Refresh");
		System.out.println("7. Exit");

	}

	private static void fileSelection() {
		Scanner input = new Scanner(System.in);

		System.out.println("Select file \n" + "write down just the file itself \n" + "Test0.txt F to C \n"
				+ "Test1.txt C to F \n" + "Test2C.txt C to F \n" + "Test3K.txt K to F \n" + "Test4F.txt F to C \n"
				+ "Test5CK.txt C to K \n" + "Test6KC.txt K to C"

		);
		String export = input.nextLine();

		openFile(export);

	}

	// open up a 3 value line
	// 0 will be the number value
	// the current temperature type
	// the desired temperature to be converted
	// data will be saved.

	private static void openFile(String input) {

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("./src/com/cai/james/resources/" + input));
			String line = reader.readLine();
			while (line != null) {
				// System.out.println(line);
				translateLine(line);
				// read next line
				line = reader.readLine();
			}
			// System.out.println("Success");
			// addAll();

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to read file");

		}

	}

	private static void refresh() {
		newTable.clear();
		index = 0;
		System.out.println("Table is refreshed");
	}

	private static void findAverage() {
		int max = 0;
		for (String i : newTable.keySet()) {
			max += newTable.get(i);
		}
		System.out.println("Average: " + max / newTable.size());
	}

	private static void lowestValue() {
		String minKey = null;
		double minValue = Double.MAX_VALUE;
		for (String key : newTable.keySet()) {
			Double value = newTable.get(key);
			if (value < minValue) {
				minValue = value;
				minKey = key;
			}
		}
		System.out.println("The lowest input: " + minValue);
		toAbsoluteZero(minValue);

	}

	private static void absoluteTempDrop() {
		System.out.println("Table of Absolute Zero Degree");
		System.out.println("Fahrenheit: −459.67");
		System.out.println("Celsius: -273.15");
		System.out.println("Kelvin: 0");

	}

	private static void exportFile() {
		String outputFile = "./src/com/cai/james/resources/output.txt";
		File file = new File(outputFile);

		BufferedWriter bf = null;

		try {

			// create new BufferedWriter for the output file
			bf = new BufferedWriter(new FileWriter(file));

			// iterate map entries
			for (Map.Entry<String, Double> entry : newTable.entrySet()) {

				// put key and value separated by a colon
				bf.write(entry.getKey() + ":" + entry.getValue());

				// new line
				bf.newLine();
			}

			bf.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				System.out.println("File created!");

				// always close the writer
				bf.close();
			} catch (Exception e) {
			}
		}

	}

	// Support Handle Functions.

	private static void translateLine(String l) {
		l = l.replace(",", "");
		String before, after, temp;
		temp = l.replaceAll("\\d", "");
		// System.out.println(temp);

		l = l.replaceAll("[a-zA-Z]", "");
		// System.out.println(l);

		double n = Double.parseDouble(l);
		before = temp.substring(0, 1);
		after = temp.substring(1, 2);

		degreeConversion(n, before, after);
	}

	private static void degreeConversion(double n, String before, String after) {
		// TODO Auto-generated method stub
		String temp = Integer.toString(index);
		if (before.equals("C") && after.equals("F")) {
			n = tC.CelsiusToFahrenheit(n);
			newTable.put("Fahrenheit" + temp, n);
			type = 1;

		}

		if (before.equals("C") && after.equals("K")) {
			n = tC.CelsiusToKelvin(n);
			newTable.put("Kelvin" + temp, n);
			type = 3;

		}

		if (before.equals("F") && after.equals("C")) {
			n = tC.FahrenheitToCelsius(n);
			newTable.put("Celsius" + temp, n);
			type = 2;

		}

		if (before.equals("F") && after.equals("K")) {
			n = tC.FahrenheitToKelvin(n);
			newTable.put("Kelvin" + temp, n);
			type = 3;
		}

		if (before.equals("K") && after.equals("F")) {
			n = tC.KelvinToFahrenheit(n);
			newTable.put("Fahrenheit" + temp, n);
			type = 1;

		}

		if (before.equals("K") && after.equals("C")) {
			n = tC.KelvinToCelsius(n);
			newTable.put("Celsius" + temp, n);
			type = 2;

		}

		index++;

	}

	private static void printTable() {
		System.out.println("Table of Conversion");
		for (String i : newTable.keySet()) {
			System.out.println("Type | " + i + " temperature | " + newTable.get(i));

		}
	}

	private static void toAbsoluteZero(double n) {
		double limit = 0;
		switch (type) {

		case 1:
			limit = -457.0;
			break;
		case 2:
			limit = -273.15;
			break;
		case 3:
			limit = 0.0;
			break;

		}

		while (n >= limit) {
			n -= 10;
			System.out.println("Table: " + n);
		}
	}

}
