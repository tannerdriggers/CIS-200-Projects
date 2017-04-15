/* 
 * Proj3
 * Tanner Driggers / Thursday / 6pm / Dennis Lang
 * 
 * This program asks for 2 points, gives the equation for the line between them, and graphs them
 */

import java.util.Scanner;
public class Proj3 {
	public static Scanner sc = new Scanner(System.in);
	
	public static double x1;
	public static double x2;
	public static double y1;
	public static double y2;
	
	public static boolean run = true; // Distinguishes when the user wants to run the program again or not
	public static boolean firstTimeRan = true; // Distinguishes between the first time ran
	
	public static void main(String[] args) {
		do {
			if (firstTimeRan){
				Start(); // calls the start function to ask the user the 4 x,y coordinates
				firstTimeRan = false;
			} else {
				StartAgain();
			}
			SlopeIntercept(); // calls the slopeIntercept function that does the calculations and prints the slope intercept form
			Graph(); // calls the graph function that creates the graph of their coordinates
			System.out.print("Run Program again? (y/n) "); // should the program run again?
				char answer = sc.nextLine().charAt(0);
				if (answer == 'y' || answer == 'Y') {
					run = true;
				} else {
					run = false;
				}
			System.out.println();
		} while (run);
	}
	
	public static void Start() {
		System.out.print("Enter x1: ");
		x1 = Double.parseDouble(sc.nextLine());
		System.out.print("Enter y1: ");
		y1 = Double.parseDouble(sc.nextLine());
		System.out.print("Enter x2: ");
		x2 = Double.parseDouble(sc.nextLine());
		System.out.print("Enter y2: ");
		y2 = Double.parseDouble(sc.nextLine());
		
		if (x1 == x2 && y1 == y2) { // makes sure the point values are different
			System.out.println("Point values must be different.");
			System.out.println();
			StartAgain();
		} else if (x1 < 0 || x1 > 10 || x2 < 0 || x2 > 10 || y1 < 0 || y1 > 10 || y2 < 0 || y2 > 10) { // points between 0 and 9
			System.out.println("All points must be between (0,0) and (9,9).");
			System.out.println();
			StartAgain();
		} else if (x1 == x2 || y1 == y2) { // makes sure there is a slope between the two points
			System.out.println("Line between points must have a slope");
			System.out.println("(i.e. x1 and x2 must differ)");
			System.out.println();
			StartAgain();
		}
	}
	
	public static void StartAgain() {
		System.out.print("Re-enter x1: ");
		x1 = Double.parseDouble(sc.nextLine());
		System.out.print("Re-enter y1: ");
		y1 = Double.parseDouble(sc.nextLine());
		System.out.print("Re-enter x2: ");
		x2 = Double.parseDouble(sc.nextLine());
		System.out.print("Re-enter y2: ");
		y2 = Double.parseDouble(sc.nextLine());
		
		if (x1 == x2 && y1 == y2) { // makes sure the point values are different
			System.out.println("Point values must be different.");
			System.out.println();
			StartAgain();
		} else if (x1 < 0 || x1 > 10 || x2 < 0 || x2 > 10 || y1 < 0 || y1 > 10 || y2 < 0 || y2 > 10) { // points between 0 and 9
			System.out.println("All points must be between (0,0) and (9,9).");
			System.out.println();
			StartAgain();
		} else if (x1 == x2 || y1 == y2) { // makes sure there is a slope between the two points
			System.out.println("Line between points must have a slope");
			System.out.println("(i.e. x1 and x2 must differ)");
			System.out.println();
			StartAgain();
		}
	}
	
	public static void SlopeIntercept() {
			System.out.println();
			double slope = (y2-y1)/(x2-x1); // calculates slope
			double yIntercept = (double) (y1 - (slope*x1)); // calculates yIntercept
			System.out.printf("y = %.2fx + %.2f\n", slope, yIntercept); // prints out the result
			System.out.println();
	}
	
	public static void Graph() {
		for (int i = 9; i >= -1; i--) { // y-axis
			if (i > 0) {
				System.out.print(i);
				if (i != y1 && i != y2) {
					System.out.println("|");
				} else if ((x1 == 0 && y1 == i) || (x2 == 0 && y2 == i)) { // if the x-axis is 0 then insert '*'
					System.out.println("*");
				} else if(i == y1 || i == y2) { // if the x-axis is not 0 then insert '|'
					System.out.print("|");
					if (i == y1) {
						for (int p = 1; p < x1; p++) { // x-axis for x1 before the '*'
							System.out.print(" ");
						}
						System.out.println("*"); // inserts * for x1 after spaces
					} else if (i == y2) {
						for (int p = 1; p < x2; p++) { // x-axis for x2 before the '*'
							System.out.print(" ");
						}
					System.out.println("*"); // inserts '*' for x2 after spaces
					}
				}
			} else if (i == 0) { // when Y = 0
				System.out.print(i);
				if ((x1 == 0 && y1 == i) || (x2 == 0 && y2 == i)) {
					System.out.println("*---------"); // insert '*' at (0,0)
				} else {
					for (int p = 0; p < 10; p++) {
						if ((y1 == 0 && p == x1) || (y2 == 0 && p == x2)) {
							System.out.print("*"); // print '*' when it gets to the x position
						} else {
							System.out.print("-"); // prints '-' every space that the x position isn't
						}
					}
					System.out.println();
				}
			} else { // end of graph
				System.out.println(" 0123456789");
				System.out.println();
			}
		}
	}
}