import java.util.ArrayList;

/**
 * *******************************
 * **** ALL EXTRA CREDIT DONE ****
 * *******************************
 * 
 * Cipher handles the encryption and decryption of
 * messages according to the four-square cipher algorithm.
 *
 * @author Tanner Driggers
 * @version 7
 */

public class Cipher {
	private Square plain1;
	private Square plain2;
	private Square cipher1;
	private Square cipher2;

	/**
	 * Cipher creates the four squares based on
	 * two keys for the four-square cipher algorithm.
	 *
	 * @param key1 The first key (no Q's)
	 * @param key2 The second key (no Q's)
	 */
	public Cipher(String key1, String key2) {
		plain1 = new Square();      // creates the first plain square
		plain2 = new Square(true);  // creates the second plain square
		cipher1 = new Square(key1); // creates the first cipher square with key1 in it
		cipher2 = new Square(key2); // creates the second cipher square with key2 in it
	} // end 2 arg constructor

	/**
	 * encrypt returns the encrypted message using the
	 * four-square cipher algorithm
	 *
	 * @param message The message to encrypt
	 *
	 * @return The encrypted message
	 */
	public String encrypt(String message) {
		String finalMessage = ""; // sets finalMessage to nothing
		
		ArrayList<String> grouped = new ArrayList<String>(); // creates an array list to store the grouped letters in
		
		int count = 0; // count to keep track of how many times we go through the message
		
		while (count < message.length()) { // while count is less than the message length
			if (message.length()-count != 1) { // executes if the message length minus the count does not equal 1
				grouped.add(message.substring(count, count + 2)); // puts the next 2 letters into the array list grouped
				count+=2; // adds 2 to count
			} else { // executes if there is only 1 letter left in the word
				grouped.add(message.substring(count)); // puts the remaining letter in grouped
				count++; // adds 1 to count
			}
		}
		
		for (int i = 0; i < grouped.size(); i++) { // goes through all of the grouped letters 
			char first = grouped.get(i).charAt(0); // sets first equal to the first letter of grouped at index i
			char second = '_'; // sets second to a default value of '_'
			
			if (grouped.get(i).length() > 1) { // only if there is a second value in grouped will this execute
				second = grouped.get(i).charAt(1); // sets second equal to the second letter of grouped at index i
			}
			
			// sets a letter to '_' if it equals ' '
			if (first == ' ') first = '_';
			if (second == ' ') second = '_';
			
			// gets the positions of the characters in plain
			int[] firstPosition = plain1.getPos(first);
			int[] secondPosition = plain2.getPos(second);

			// gets the chars from the the corresponding position in cipher
			char char1 = cipher1.getChar(firstPosition[0], secondPosition[1]);
			char char2 = cipher2.getChar(secondPosition[0], firstPosition[1]);
			
			// adds the letters to the finalMessage
			finalMessage += char1;
			finalMessage += char2;
		}
		return finalMessage;
	} // end encrypt

	/**
	 * decrypt returns the decrypted message using the
	 * four-square cipher algorithm
 	 *
	 * @param message The message to decrypt
	 *
	 * @return The decrypted message
	 */
	public String decrypt(String message) {
		String finalMessage = ""; // sets the finalMessage to nothing
		
		message = message.replaceAll("\\s+", "_"); // replaces all the spaces in message to '_'
		
		ArrayList<String> grouped = new ArrayList<String>(); // creates an array list to store the grouped letters in
		
		int count = 0; // count to keep track of how many times we go through the message
		
		while (count < message.length()) { // while count is less than the message length
			if ((message.length())-count != 1) { // executes if the message length minus the count does not equal 1
				grouped.add(message.substring(count, count + 2)); // puts the next 2 letters into the array list grouped
				count+=2; // adds 2 to count
			} else { // executes if there is only 1 letter left in the word
				grouped.add(message.substring(count)); // puts the remaining letter in grouped
				count++; // adds 1 to count
			}
		}
		
		for (int i = 0; i < grouped.size(); i++) { // goes through all of the grouped letters 
			char first = grouped.get(i).charAt(0); // sets first equal to the first letter of grouped at index i
			char second = ' '; // sets second to a default value of ' '
			
			if (grouped.get(i).length() > 1) { // only if there is a second value in grouped will this execute
				second = grouped.get(i).charAt(1); // sets second equal to the second letter of grouped at index i
			}
			
			// gets the positions of the characters in cipher
			int[] firstPosition = cipher1.getPos(first);
			int[] secondPosition = cipher2.getPos(second);
			
			// gets the chars from the the corresponding position in plain
			char char1 = plain1.getChar(firstPosition[0], secondPosition[1]);
			char char2 = plain2.getChar(secondPosition[0], firstPosition[1]);
			
			// sets a letter to ' ' if it equals '_'
			if (char1 == '_') char1 = ' ';
			if (char2 == '_') char2 = ' ';
			
			// adds the letters to the finalMessage
			finalMessage += char1;
			finalMessage += char2;
		}
		return finalMessage;
	} // end decrypt
} // end class