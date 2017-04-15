/**
 * *******************************
 * **** ALL EXTRA CREDIT DONE ****
 * *******************************
 * 
 * IO handles all input and output for the
 * four-square encryption algorithm
 *
 * @author Tanner Driggers
 * @version 7
 */

import java.util.*;

public class IO {
	private Scanner s;

	/**
	 * IO sets up a new Scanner to System.in
	 */
	public IO() {
		s = new Scanner(System.in);     // creates a scanner object
		
		String firstKey = firstKey();   // gets the first key
		String secondKey = secondKey(); // gets the second key
		
		Cipher cipher = new Cipher(firstKey, secondKey); // creates a new cipher object with both the keys		
		
		boolean encrypt;
		char choice = encryptOrDecrypt(); // gets the user's answer to encrypt or decrypt
		if (choice == 'E') {
			encrypt = true;
			printResults(cipher.encrypt(message(encrypt)), encrypt); // prints out the encrypted message
		} else {
			encrypt = false;
			printResults(cipher.decrypt(message(encrypt)), encrypt); // prints out the decrypted message
		}
	}

	/**
	 * firstKey returns the first key from the user
	 *
	 * @return The first key from the user
	 */
	public String firstKey() {
		System.out.print("Enter the first key: "); // Gets the User's first key
		String firstKey = s.nextLine().toLowerCase(); // gets key from user and makes it lower case
		firstKey = firstKey.replaceAll("\\s+", ""); // removes spaces from key
		return firstKey;
	} // end firstKey

	/**
	 * secondKey returns the second key from the user
	 *
	 * @return The second key from the user
	 */
	public String secondKey() {
		System.out.print("Enter the second key: "); // Gets the User's second key
		String secondKey = s.nextLine().toLowerCase(); // gets key from user and makes it lower case
		secondKey = secondKey.replaceAll("\\s+", ""); // removes spaces from key
		return secondKey;
	} // end secondKey

	/**
	 * gets and returns whether the user wants to encrypt or decrypt
	 *
	 * @return 'e' for encryption, and 'd' for decryption
	 */
	public char encryptOrDecrypt() {
		System.out.print("Enter (e)ncrypt or (d)ecrypt: "); // Gets the User's answer to encrypt or decrypt
		char choice = s.nextLine().toUpperCase().charAt(0);
		return choice;
	} // end encryptOrDecrypt

	/**
	 * message returns the message from the user
	 *
	 * @param encrypt True if encrypting, false if decrypting
	 *
	 * @return The message from the user
	 */
	public String message(boolean encrypt) {
		// Checks the users answer to encrypt or decrypt
		String crypt = "Decrypt";
		if (encrypt) crypt = "Encrypt";
		
		System.out.print("Enter the message to " + crypt + ": "); // Asks for the users encrypted/decrypted message
		String message = s.nextLine();
		return message;
	} // end message

	/**
	 * printResults prints the encrypted and decrypted messages
	 *
	 * @param msg The resulting message
	 * @param encrypt True if encrypting, false if decrypting
	 */
	public void printResults(String msg, boolean encrypt) {
		System.out.println();
		if (encrypt) {
			System.out.print("Encypted message: " + msg + "\n");
		} else {
			System.out.print("Decrypted message: " + msg + "\n");			
		}
	} // end printResults

	/*
	 * printError prints an error message (Never Used)
	 *
	 * @param err The error message to print
	 */
	public void printError(String err) {
		System.out.println(err);
	} // end printError

} // end class