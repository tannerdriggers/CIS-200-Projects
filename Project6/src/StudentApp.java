/**
 * StudentApp.java
 * Tanner Driggers / Thursday/6pm / Dennis Lang
 * 
 * *************EXTRA CREDIT*************
 * This class is used as the main class as it asks for input from the user that then
 *   creates instances of the Student Object accordingly, sorts all the students,
 *   and prints the required fields in order from greatest overall score to lease overall score 
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StudentApp {
	
	private final static int MAX_STUDENTS = 50;
	public static int totalLabPoints, totalProjectPoints;
	public static int totalCodelabPoints = 225;
	public static int totalExamPoints = 150;
	public static int totalFinalExamPoints = 100;
	private static boolean ContinueStudents;
	private static DecimalFormat df = new DecimalFormat("###.#%");
	
	private static List<Student> students = new ArrayList<Student>();
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		System.out.print("Enter total points possible for LABS: ");
		totalLabPoints = Integer.parseInt(s.nextLine());
		System.out.print("Enter total points possible for PROJECTS: ");
		totalProjectPoints = Integer.parseInt(s.nextLine());
		System.out.println();
		
		int count = 0;
		do {
			System.out.print("Enter the student's name (firstname lastname): ");
			String tempName = s.nextLine();
			System.out.print("Enter the student's WID: ");
			String tempWID = s.nextLine();
			System.out.println();
			
			System.out.print("Enter students total for all LABS: ");
			double tempLabs = Double.parseDouble(s.nextLine());
			System.out.print("Enter students total for all PROJECTS: ");
			double tempProjects = Double.parseDouble(s.nextLine());
			System.out.print("Enter students score for CODELAB: ");
			double tempCodelabs = Double.parseDouble(s.nextLine());
			System.out.print("Enter students total for the 3 CLASS EXAMS: ");
			double tempClassExams = Double.parseDouble(s.nextLine());
			System.out.print("Enter students score for the FINAL EXAM: ");
			double tempFinalExams = Double.parseDouble(s.nextLine());
			
			Student tempStudent = new Student(tempName, tempWID, tempLabs, tempProjects, tempCodelabs, tempClassExams, tempFinalExams);
			students.add(tempStudent);
			
			System.out.println();
			System.out.println((count + 1) + " Student(s) entered so far.");
			System.out.println("Up to " + MAX_STUDENTS + " students can be entered.");
			
			System.out.print("Would you like to enter another student? ('Y' or 'N'): ");
			char answer = s.nextLine().toUpperCase().charAt(0);
			if (answer == 'Y') {
				count++;
				ContinueStudents = true;
			}
			else if (answer == 'N') {
				ContinueStudents = false;
			}
			System.out.println();
		} while (ContinueStudents && count <=50);
		
		Collections.sort(students, Comparator.comparing(Student::OverallPercent));
		int index = students.size() - 1;
		while(index >= 0) {
			String FullName = students.get(index).getName();
			String FirstName = FullName.substring(0, FullName.indexOf(" "));
			String LastName = FullName.substring(FullName.indexOf(" ") + 1);
			System.out.println("Student Name: " + LastName + ", " + FirstName);
			System.out.println("WID: " + students.get(index).getWID());
			double overallPercent = students.get(index).OverallPercent();
			System.out.println("Overall %: " + df.format(overallPercent));
			System.out.println("Final Grade: " + FinalGrade(overallPercent * 100));
			System.out.print("        Press enter to display next student...");
			s.nextLine();
			System.out.println();
			index--;
		}
		System.out.println("All students displayed...");
		System.out.println();
		s.close();
	}
	
	/**
	 * This method is used to determine which Grade a student gets
	 * @param percent is used to compare which grade the student should get
	 * @return returns a Letter Grade
	 */
	private static String FinalGrade(Double percent) {
		if (percent >= 89.5) return "A";
		else if (percent >= 79.5) return "B";
		else if (percent >= 69.5) return "C";
		else if (percent >= 59.5) return "D";
		else return "F";
	}

}