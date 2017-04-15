
/**
 * Proj5.java
 * Tanner Driggers / Thursday/6pm / Dennis Lang
 * 
 * Conway's Game of Life (simple version)
 */

import java.io.File;
import java.io.*;
import java.util.*;

public class Proj5 {
	public static int size1 = 0;
	public static int size2 = 0;

	public static void main(String[] args) throws IOException {
		String fileName = "";
		if (args.length == 0) {
			System.out.println("No arguments were given.");
			System.exit(0);
		} else {
			fileName = args[0];
		}
		int[][] board1 = readBoard(fileName);
		while (true) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			board1 = update(board1);
			System.out.println(boardDisplay(board1));
		}

	}

	/**
	 * Reads in a given file and puts it into a double array
	 * 
	 * parameter filename is the name of the file specified by the user to read
	 * in returns a double array containing all of the elements of the file
	 */
	public static int[][] readBoard(String filename) throws IOException {
		Scanner s;
		int[][] board = {};
		try {
			s = new Scanner(new File(filename));

			for (int count = 0; count < 2; count++) {
				if (count == 0)
					size1 = Integer.parseInt(s.nextLine());
				else if (count == 1)
					size2 = Integer.parseInt(s.nextLine());
			}

			board = new int[size1][size2];

			for (int i = 0; i < size1; i++) {
				board[i] = (s.nextLine().chars().map(j -> j - '0').toArray());
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Could Not Be Found. Exiting.");
			System.exit(0);
		}
		return board;
	}

	/**
	 * Puts the elements of a double array into a string
	 * 
	 * parameter int[][] cells is used to put into a string a string containing
	 * all the elements of the array is returned
	 */
	public static String boardDisplay(int[][] cells) {
		String cellsArray = "";
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if (cells[i][j] == 0)
					cellsArray += "-";
				else if (cells[i][j] == 1)
					cellsArray += "*";
			}
			cellsArray += System.lineSeparator();
		}
		return cellsArray;
	}

	/**
	 * Checks all the neighboring array cells around a given point in an array
	 * 
	 * int[][] cells is the array being compared. int row is the row of the
	 * compared element. int col is the column of the compared element. returns
	 * the amount of neighboring cells that have a 1 in them.
	 */
	public static int neighbors(int[][] cells, int row, int col) {
		int neighbors = 0;
		try {
			if (cells[row - 1][col - 1] == 1) {
				neighbors += 1;
			}
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			if (cells[row - 1][col] == 1) {
				neighbors += 1;
			}
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			if (cells[row - 1][col + 1] == 1) {
				neighbors += 1;
			}
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			if (cells[row][col - 1] == 1) {
				neighbors += 1;
			}
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			if (cells[row][col + 1] == 1) {
				neighbors += 1;
			}
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			if (cells[row + 1][col - 1] == 1) {
				neighbors += 1;
			}
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			if (cells[row + 1][col] == 1) {
				neighbors += 1;
			}
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			if (cells[row + 1][col + 1] == 1) {
				neighbors += 1;
			}
		} catch (IndexOutOfBoundsException e) {
		}

		return neighbors;
	}

	/**
	 * Updates an array to a new array
	 * 
	 * int[][] cells is the array being accessed a new array with all of the
	 * changes is returned
	 */
	public static int[][] update(int[][] cells) {
		int[][] newCells = new int[size1][size2];
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				boolean value = false;
				if (cells[i][j] == 1)
					value = true;
				if (value && neighbors(cells, i, j) < 2) {
					newCells[i][j] = 0;
				} else if (value && neighbors(cells, i, j) > 3) {
					newCells[i][j] = 0;
				} else if (!value && neighbors(cells, i, j) == 3) {
					newCells[i][j] = 1;
				} else if (value && (neighbors(cells, i, j) == 2 || neighbors(cells, i, j) == 3)) {
					newCells[i][j] = 1;
				} else if (!value && neighbors(cells, i, j) != 3) {
					newCells[i][j] = 0;
				} else {
					newCells[i][j] = cells[i][j];
				}
			}
		}
		return newCells;
	}
}
