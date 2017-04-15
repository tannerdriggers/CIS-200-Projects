/**
* Proj1.java
* Tanner Driggers / Thursday / 6pm / Dennis J Lang
*
* This project calculates grade percentage based on 3 projects and 2 tests 
* and also calculates the number of pizzas to order based on the amount of people assumed to be there. 
*/

import java.text.DecimalFormat;
import java.util.Scanner;

public class Proj1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("###.##%");
		overallGradePercentage(sc, df);
		System.out.println();
		pizzasToOrder(sc);
		sc.close();
	}
	
	public static void overallGradePercentage(Scanner sc, DecimalFormat df) {
		final double TOTAL_POINTS_POSSIBLE = 290;
		double totalPointsAchieved = 0;
		
		System.out.print("Enter in Project score #1 (0-30): ");
		totalPointsAchieved += Double.parseDouble(sc.nextLine());
		System.out.print("Enter in Project score #2 (0-30): ");
		totalPointsAchieved += Double.parseDouble(sc.nextLine());
		System.out.print("Enter in Project score #3 (0-30): ");
		totalPointsAchieved += Double.parseDouble(sc.nextLine());
		System.out.println();
		System.out.print("Enter the midterm exam score (0-100): ");
		totalPointsAchieved += Double.parseDouble(sc.nextLine());
		System.out.print("Enter the final exam score (0-100): ");
		totalPointsAchieved += Double.parseDouble(sc.nextLine());
		System.out.println("Overall grade percentage: " + df.format(totalPointsAchieved / TOTAL_POINTS_POSSIBLE));
	}
	
	public static void pizzasToOrder(Scanner sc) {
		final int TOTAL_SLICES_PER_PIZZA = 20;
		final int TOTAL_SLICES_PER_PERSON = 2;
		int totalPizzasNeeded;
		boolean correctValue = false;
		
		while(!correctValue){
			System.out.print("What is the number of people expected at the pizza party? ");
			double expectedPeople = Integer.parseInt(sc.nextLine());
			if (expectedPeople >= 2 && expectedPeople <= 20) {
				correctValue = true;
				System.out.println();
				totalPizzasNeeded = (int) Math.ceil(expectedPeople / TOTAL_SLICES_PER_PIZZA * TOTAL_SLICES_PER_PERSON);
				int slicesLeftover = (int) Math.round((totalPizzasNeeded * TOTAL_SLICES_PER_PIZZA) % expectedPeople);
				System.out.println("For " + (int) expectedPeople + " people, that would be " + totalPizzasNeeded + 
						" pizza(s) with each having " + TOTAL_SLICES_PER_PERSON + " slices each.");
				System.out.println("There would be " + slicesLeftover + " slices(s) leftover.");
			}
		}
	}
}
