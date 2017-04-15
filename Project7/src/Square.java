/**
 * *******************************
 * **** ALL EXTRA CREDIT DONE ****
 * *******************************
 * 
 * Square represents one of the squares in the four-square
 * cipher algorithm.
 *
 * @author Tanner Driggers
 * @version 7
 */

public class Square {
	private char[][] matrix;

	/**
	 * Square constructs a matrix with the letters a-z, A-Z, '_' for a space, then placement characters
	 */
	public Square() {
		matrix = new char[8][8]; // new char double array of 8 by 8
		
		int count = 0; // keeps track of which letter we are at
		char a = 'a'; // starts the character at 'a'
		for (int i = 0; i < matrix.length/2; i++) { // goes through half of the rows
			for (int j = 0; j < matrix[i].length; j++) { // goes through the full length of the columns
				if (count == 26) matrix[i][j] = '_'; // if the count is after 'z' then make the next char '_'
				else matrix[i][j] = (char) (a + count); // increments though the alphabet then some placement holders at the end
				count++; // increments count
			}
		}
		for (int i = 0; i < matrix.length/2; i++) { // goes through half of the rows
			for (int j = 0; j < matrix[i].length; j++) { // goes through the full length of the columns
				matrix[i + (matrix.length/2)][j] = Character.toUpperCase(matrix[i][j]); // makes the rest of the 2D array the 
				                                                                        // same as the first half except upper case
			}
		}
	} // end no-arg constructor
	
	/**
	 * Square constructs a matrix with the letters A-Z, a-z, '_' for a space, then placement characters
	 */
	public Square(boolean start) {
		matrix = new char[8][8]; // new char double array of 8 by 8
		
		int count = 0; // keeps track of which letter we are at
		char a = 'A'; // starts the character at 'A'
		for (int i = 0; i < matrix.length/2; i++) { // goes through half of the rows
			for (int j = 0; j < matrix[i].length; j++) { // goes through the full length of the columns
				if (count == 26) matrix[i][j] = '_'; // if the count is after 'z' then make the next char '_'
				else matrix[i][j] = (char) (a + count); // increments though the alphabet then some placement holders at the end
				count++; // increments count
			}
		}
		for (int i = 0; i < matrix.length/2; i++) { // goes through half of the rows
			for (int j = 0; j < matrix[i].length; j++) { // goes through the full length of the columns
				matrix[i + (matrix.length/2)][j] = Character.toLowerCase(matrix[i][j]); // makes the rest of the 2D array the 
                																		// same as the first half except lower case
			}
		}
	}

	/**
	 * Square first puts the characters from key into
	 * the matrix, and then fills the matrix with the remaining
	 * letters (skipping Q).
	 *
	 * @param key One of the encryption keys
	 */
	
	private int keyRow; // creates a private global integer called keyRow
	private int keyCol; // creates a private global integer called keyCol
	public Square(String key) {
		matrix = new char[8][8]; // creates a new char 2D array that is 8 by 8
		key = removeDups(key); // calls the removeDups class with key as the parameter and sets it as key
		key = key.replace("\\s+", "_"); // takes out all the spaces in key
		int tempLength = key.length(); //creates an integer variable with the value of key length
		this.keyRow = 1;
		while (true) { // always runs
			if (tempLength - matrix[0].length > 0) { // executes if the tempLength minus the columns of matrix is greater than 0
				tempLength -= matrix[0].length; // takes out the column length of matrix from tempLength
				this.keyRow++; // adds 1 to keyRow
			} else {
				break; // breaks out of the while loop
			}
		}
		keyCol = tempLength; // sets the keyCol to the tempLength
		int tempCol = keyCol; // creates a new temp int variable to the keyCol
		int count = key.length() - 1; // sets the count to the length of the key minus 1
		for (int i = this.keyRow-1; i >= 0; i--) {
			for (int j = tempCol-1; j >= 0; j--) {
				matrix[i][j] = key.charAt(count--); // sets the first places in matrix to the key backwards
			}
			tempCol = matrix[0].length; // makes tempCol to the length of columns in matrix after the first time through
		}
		char[][] m1 = makeAlphabetMatrix('a', key); // sets m1 to the called method
		char[][] m2 = makeAlphabetMatrix('A', key); // sets m2 to the called method
		
		int offset = this.keyCol; // creates an offset and sets it to keyCol
		for (int i = this.keyRow - 1; i < m1.length; i++) {
			for (int j = 0 + offset; j < m1[i].length; j++) {
				matrix[i][j] = m1[i-(this.keyRow-1)][j]; // sets everything after the key into the m1 2D Array
			}
			offset = 0; // sets offset to 0 after the first time through
		}
		for (int i = 0; i < m2.length; i++) {
			for (int j = 0; j < m2[i].length; j++) {
				matrix[m1.length + i][j] = m2[i][j]; // sets everything after m1 ends to m2
			}
		}
	} // end one-arg constructor
	
	private char[][] makeAlphabetMatrix(char startLetter, String key) {
		char[][] tempMatrix = new char[matrix.length/2][matrix[1].length]; // creates a new 2D Array to half of matrix
		int offset2;
		int row;
		if (startLetter == 'a') {  // executes if the startLetter is 'a'
			offset2 = this.keyCol; // sets offset2 to keyCol
			row = this.keyRow - 1; // sets row to keyRow
		} else {                   // executes if the startLetter is not 'a'
			offset2 = 0;		   // sets offset2 to 0
			row = 0;			   // sets row to 0
		}
		
		int count = 0; // keeps track of which letter it is on
		for (int i = 0; i < tempMatrix.length - row; i++) {
			for (int j = 0 + offset2; j < tempMatrix[i].length; j++) {
				if (!strContains(key, (char) (startLetter + count))) { // executes if key does not contain the next letter
					tempMatrix[i][j] = (char) (startLetter + count);   // makes tempMatrix the next letter
					count++;                                           // increments count
				} else {           // executes if key contains the next letter
					count++;       // increments count
					j--;           // makes it go through j again
				}
			}
			offset2 = 0;           // sets offset2 to 0 after the first time through
		}
		return tempMatrix;
	}

	/**
	 * getChar returns the character at a given
	 * row and column in the matrix.
	 *
	 * @param row The row to look in
	 * @param col The column to look in
	 *
	 * @return The character at (row, col)
	 */
	public char getChar(int row, int col) {
		return matrix[row][col];
	} // end getChar

	/**
	 * getPos returns the [row,col] position
	 * of the given character.
	 *
	 * @param c The character to look for
	 *
	 * @return A 1D array holding the row and col of c stored in [0] and [1]
	 */
	public int[] getPos(char c) {
		int[] pos = new int[2]; // creates a new integer Array with length of 2
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == c) { // executes if the char letter is found in matrix
					pos[0] = i; // sets the first position of pos array to the first index i
					pos[1] = j; // sets the second position of pos array to the second index j
					return pos;
				}
			}
		}

		// character not found
		pos[0] = -1;
		pos[1] = -1;
		return pos;	 
	} // end getPos

	/**
	 * strContains returns whether the given string
	 * contains the given character
	 *
	 * @param str The string to search
	 * @param c The character to search for
	 *
	 * @return true if c is in str else false
	 */
	private boolean strContains(String str, char c) {
		return str.contains(Character.toString(c));
	} // end strContains
	
	/**
	 * removeDups removes all duplicate characters
	 * from the string
	 *
	 * @param key The string to remove duplicates from
	 *
	 * @return The string with all duplicates removed
	 */
	private String removeDups(String key) {
		String tempKey = "";
		for (int i = 0; i < key.length(); i++) {
			if (!tempKey.contains(String.valueOf(key.charAt(i)))) {
				tempKey += String.valueOf(key.charAt(i));
			}
		}
		return tempKey;
	} // end removeDups	

} // end class