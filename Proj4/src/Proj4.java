/**
 * Project 4
 * Tanner Driggers / Thursday/6pm / Dennis Lang
 * 
 * This project deals 5 random cards from a deck and then tells you the classification
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Proj4 {
	public static Random r = new Random();
	public static ArrayList<Integer> Card_Faces = new ArrayList<Integer>();
	public static int [] value = new int [5];
	public static int [] suit = new int [5];
	public static boolean doAgain = false;
	public static String classification = "none";
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		System.out.println("** Welcome to the 2017 Las Vegas Poker Festival! **");
		System.out.println("    (Application developed by Tanner Driggers)");
		
		do {
			//PlannedDeal();
			RandomDeal();
			PrintYourCards();
			PrintYourHandClassification();
			PlayAgain();
		} while (doAgain);
		sc.close();
		System.exit(0);
	} // end main
	
	public static void PlannedDeal() {
		value[0] = 14;
		value[1] = 6;
		value[2] = 11;
		value[3] = 2;
		value[4] = 4;
		suit[0] = 2;
		suit[1] = 4;
		suit[2] = 1;
		suit[3] = 3;
		suit[4] = 1;
		System.out.println();
		Sleep(400);
		System.out.println("Shuffling cards...");
		Sleep(400);
		System.out.println("Dealing the cards...");
	}
  
	public static void RandomDeal() {
		Card_Faces.clear();
		System.out.println();
		Sleep(400);
		System.out.println("Shuffling cards...");
		Sleep(400);
		System.out.println("Dealing the cards...");
		int temp = 3;
		for (int i = 0; i < value.length; i++) {
			if (i != 0) {
				do {
					temp = r.nextInt(52) + 2;
					value[i] = temp % 13;
					if (value[i] == 0) {
						value[i] = 13;
					}
					if (temp != 52)
						suit[i] = (int) (Math.floor(temp / 13) + 1);
					else
						suit[i] = (int) (Math.floor(temp / 13));
				} while (Card_Faces.contains(temp));
				Card_Faces.add(temp);
			} else {
				temp = r.nextInt(52) + 2;
				Card_Faces.add(temp);
				value[i] = temp % 13;
				if (value[i] == 0) {
					value[i] = 13;
				}
				if (temp != 52)
					suit[i] = (int) (Math.floor(temp / 13) + 1);
				else
					suit[i] = (int) (Math.floor(temp / 13));
			}
		}
	}
	
	public static void PrintYourHandClassification() {
		Arrays.sort(value);
		Arrays.sort(suit);
		
		//  High Card (Ace) 
		classification = "a(n) " + PrintCard(4);
		
		// Two Pair and One Pair
		int count = 0; int Card1 = 0;
		for (int i = 0; i < value.length - 1; i++) {
			if (value[i] != Card1 && value[i] == value[i+1]) {
				Card1 = value[i];
				count++;
			}
			if (count == 1) classification = "One Pair";
			if (count == 2) classification = "Two Pairs";
		}
		
		// 3 of kind 
		for (int i = 0; i < value.length - 2; i++) {
			if(value[i] == value[i+2]){
				classification = "3 of a Kind";
			}
		}
		
		// Straight
		if (value[0] == value[1] - 1 && value[1] == value[2] - 1 && value[2] == value[3] - 1 && value[3] == value[4] - 1) {
			classification = "a Straight";
		}
		
		// Full House 
		int Card = 0; boolean threeOfAKind = false; boolean twoOfAKind = false;
		for (int i = 0; i < value.length - 3; i++) {
			if (value[i] == value[i+2]) {
				Card = value[i];
				threeOfAKind = true;
			}
		}
		for (int i = 0; i < value.length - 2; i++) {
			if (value[i] != Card && value[i] == value[i+1]) twoOfAKind = true;
		}
		if (threeOfAKind && twoOfAKind) classification = "a Full House";
		
		// 4 of kind
		for (int i = 0; i < value.length - 4; i++) {
			if(value[i] == value[i+1] && value[i+1] == value[i+2] && value[i+2] == value[i+3] && value[i+3] == value[i+4]){
				classification = "4 of a Kind";
			}
		}
		
		// Straight Flush and Flush
		if (suit[0] == suit[3] || suit[1] == suit[4]) {
			if (value[0] == value[1] - 1 && value[1] == value[2] - 1 && value[2] == value[3] - 1 && value[3] == value[4] - 1)
				classification = "a Straight Flush";
			else
				classification = "a Flush";
		}
		
		// Royal Flush 
		if ((suit[0] == suit[4]) && (value[0] >= 10 && value[1] >= 10 && value[2] >= 10 && value[3] >= 10 && value[4] >= 10)) {
			classification = "a Royal Flush";
		}
		Sleep(600);
		System.out.println();
		System.out.println("You were dealt " + classification + ".");
	}
  
	public static void PrintYourCards() {
		Sleep(500);
		System.out.println();
		System.out.println("Here are your five cards...");
		for (int i = 0; i < value.length; i++) {
			Sleep(300);
			System.out.print("       ");
			if (value[i] > 1 && value[i] <= 10) {
				System.out.println(value[i] + " of " + PrintSuit(i));
			} else {
				System.out.println(PrintCard(i) + " of " + PrintSuit(i));
			}
		}
	}
  
	public static String PrintCard(int index) {
		switch (value[index]) {
			case 14: return "Ace";
			case 11: return "Jack";
			case 12: return "Queen";
			case 13: return "King";
			default: return "" + value[index];
		}
	}
  
	public static String PrintSuit(int index) {
		switch (suit[index]) {
	  		case 1: return "Hearts";
	  		case 2: return "Diamonds";
	  		case 3: return "Clubs";
	  		case 4: return "Spades";
	  		default: return "";
		}
	}
	
	public static void Sleep(int milli) {
		try {
			Thread.sleep(milli);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void PlayAgain() {
		char answer;
		System.out.println();
		System.out.print("Play Again (Y or N)? ");
		answer = sc.nextLine().charAt(0);
		if (answer == 'y' || answer == 'Y') doAgain = true;
		else if (answer == 'n' || answer == 'N') doAgain = false;
		else {
			System.out.println("Please enter a 'Y' or 'N' only");
			PlayAgain();
		}
	}
  
} // end class