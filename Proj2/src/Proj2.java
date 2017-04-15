/**
 * Proj2.java
 * Tanner Driggers / Thursday / 6pm / Dennis Lang
 * 
 * This project takes in education level, current year, current age, and retire age.
 * It then Outputs yearly salary for your education level and all the education levels above you.
 */

import java.text.DecimalFormat;
import java.util.Scanner;

public class Proj2 {
	public static int educationLevel;
	public static int currentYear;
	public static int currentAge;
	public static int retireAge;
	public static int weeklySalary = 0;
	public static int annualSalary = 0;
	public static int retireYear = 0;
	public static int totalMoneyMade = 0;
	public static String degree;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("$###,###,###,###,###,###");
		InitialQuestions(sc);
		MathSolving(df);
		sc.close();
	}
	
	public static void InitialQuestions(Scanner sc) {
		System.out.println("\t1 = No Degree");
		System.out.println("\t2 = High School Degree");
		System.out.println("\t3 = Bachelor's Degree");
		System.out.println("\t4 = Masters Degree");
		System.out.println("\t5 = Doctorate Degree");
		System.out.println();
		System.out.print("Enter in the number that corresponds to your education level: ");
		educationLevel = Integer.parseInt(sc.nextLine());
		degree = MyDegree(educationLevel);
		weeklySalary = EducationToWeekly(educationLevel);
		
		System.out.print("Enter in the current year: ");
		currentYear = Integer.parseInt(sc.nextLine());
		
		System.out.print("Enter in your current age: ");
		currentAge = Integer.parseInt(sc.nextLine());
		
		System.out.print("Enter in the age you plan on retiring: ");
		retireAge = Integer.parseInt(sc.nextLine());
		
		if(retireAge < currentAge) {
			System.out.println("Your Retirement Age is Lower than your Current Age. Please re-run the program and enter in a valid choice.");
			System.exit(0);
		}
		
		System.out.println();
	}
	
	public static void MathSolving(DecimalFormat df) {
		annualSalary = weeklySalary * 52;
		retireYear = (currentYear - currentAge) + retireAge;
		totalMoneyMade = annualSalary * (retireAge - currentAge);
		
		System.out.println("Based on current statistics and your " + degree + ":");
		System.out.println("Your annual salary will be " + df.format(annualSalary));
		System.out.println("Retiring at age " + retireAge + " in " + retireYear + " you will make a total of " 
				+ df.format(totalMoneyMade));
		if (educationLevel != 1) {
			for (int i = educationLevel - 1; i > 0; i--) {
				OtherDegreesLess(i, df);
			}
		}
		
		for (int i = educationLevel + 1; i <= 5; i++) {
			OtherDegreesMore(i, df);
		}
	}
	
	public static int EducationToWeekly(int Level) {
		switch (Level) {
			case 1: return 493;
			case 2: return 678;
			case 3: return 1137;
			case 4: return 1341;
			case 5: return 1623;
			default: return 0;
		}
	}
	
	public static String MyDegree(int dg) {
		switch (dg) {
			case 1: return "No Degree";
			case 2: return "High School Degree";
			case 3: return "Bachelor's Degree";
			case 4: return "Masters Degree";
			case 5: return "Doctorate Degree";
			default: System.out.println("You entered an invalid menu choice. Please re-run the program and enter in a valid choice.");
					System.exit(0); return "";
		}
	}
	
	public static void OtherDegreesLess(int i, DecimalFormat df) {
		System.out.println("That is " + df.format((RetireAmount(retireAge - currentAge,
				EducationToWeekly(educationLevel)) - RetireAmount(retireAge - currentAge, EducationToWeekly(i)))) 
				+ " more than if you only had a " + MyDegree(i));
	}
	
	public static void OtherDegreesMore(int i, DecimalFormat df) {
		System.out.println("With a " + MyDegree(i) + ", you can earn " + df.format((RetireAmount(retireAge - currentAge,
				EducationToWeekly(i)) - RetireAmount(retireAge - currentAge, EducationToWeekly(educationLevel)))) 
				+ " more throughout your life than if you just had your " + degree);
	}
	
	public static int AmountMore(int first, int second) {
		return second - first;
	}
	
	public static int RetireAmount(int retirementAge, int salary) {
		return (salary * 52) * retirementAge;
	}
	
}