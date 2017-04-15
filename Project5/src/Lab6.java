import java.io.*;
import java.util.*;

public class Lab6 {
	public static String getInitial(String fullName) {
		//WRITE THIS METHOD FIRST
		
		//fullName is a string with a person's first, middle, and last
		//names separated by spaces (like "Thomas Alva Edison").  You
		//should return the string with the first, middle, and last
		//initials of the person' with this fullName.  For example,
		//if fullName was "Thomas Alva Edison", you should return
		//the string "T.A.E."
		String initials = "";
		//use StringTokenizer or split method to parse name into pieces
		String [] Initials = fullName.split(" ");
		for (int i = 0; i < Initials.length; i++) {
			char temp = Initials[i].charAt(0);
			initials += temp + ".";
		}
		
        //use StringBuilder to 'build' the new string
		//StringBuilder string = new StringBuilder();
		//string.append(Initials[0]);
		return initials;
	} // end getInitial

	public static void printInitials(String[] allNames) {
		//WRITE THIS METHOD SECOND

		//allNames is an array of strings, where each element is
		//someone's full name (first, middle, last separated by spaces).
		for (int i = 0; i < allNames.length; i++) {
			System.out.println(getInitial(allNames[i]));
		}
		//Loop through allNames, and for each element, display the
		//initials corresponding to that name.  You should call
		//getInitial on each full name to get the initials.  Then,
		//display the return value you get back from printInitials.
	} // end printInitials

	public static String[] getNames(String filename) throws IOException {
		//THIS METHOD IS DONE FOR YOU

		Scanner inFile = new Scanner(new File(filename));
		int size = Integer.parseInt(inFile.nextLine());
		String[] arr = new String[size];

		for (int i = 0; i < size; i++) {
			arr[i] = inFile.nextLine();
		}

		inFile.close();

		return arr;
	} // end getNames

	public static void main(String[] args) throws IOException {
		//WRITE THIS METHOD LAST

		//Scanner s = new Scanner(System.in);
		//System.out.print("Enter the name of the input file: ");
		String fname = "";
		if (args.length == 0) {
			System.out.println("No arguments were given.");
			System.exit(0);
		} else {
			fname = args[0];
		}
		//This calls the getNames method to get back an array of
		//names from the file
		String[] names = getNames(fname);
		printInitials(names);
		//YOU DO THIS: call printInitials (passing the names
		//array) to print the initials for each name.  You should
		//have just ONE line of code to call that method
	} // end main
} // end class