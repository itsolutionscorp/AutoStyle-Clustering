
import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Solver {
	public static int[][] getInit(int numberOfLine, BufferedReader textReader,
			int len, int wid) throws IllegalArgumentException, IOException {
		int[][] pieces = new int[numberOfLine - 1][4];
		for (int i = 0; i < numberOfLine - 1; i++) {
			String[] temp = textReader.readLine().split(" ");
			// init format error
			if (temp.length != 4) {
				throw new IllegalArgumentException();
			}
			for (int j = 0; j < 4; j++) {
				int toAdd = 0;
				try {
					toAdd = Integer.parseInt(temp[j]);
					if (toAdd < 0) {
						throw new IllegalArgumentException();
					}
					if (j == 0 || j == 2) {
						if (toAdd > len) {
							throw new IllegalArgumentException();
						}
					} else {
						if (toAdd > wid) {
							throw new IllegalArgumentException();
						}
					}
					pieces[i][j] = toAdd;
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException();
				}

			}
		}
		textReader.close();
		return pieces;
	}

	public static int[][] getGoal(String goalPath, int len, int wid)
			throws IllegalArgumentException, IOException {
		String line;
		int numberOfLine = 0;
		FileReader reader = new FileReader(goalPath);
		BufferedReader textReader = new BufferedReader(reader);
		while ((line = textReader.readLine()) != null) {
			numberOfLine++;
		}
		textReader.close();
		// goal format error
		reader = new FileReader(goalPath);
		textReader = new BufferedReader(reader);
		int[][] target = new int[numberOfLine][4];
		for (int i = 0; i < numberOfLine; i++) {
			String[] coords = textReader.readLine().split(" ");
			if (coords.length != 4) {
				throw new IllegalArgumentException("");
			}
			for (int j = 0; j < 4; j++) {
				int toAdd = 0;
				try {
					toAdd = Integer.parseInt(coords[j]);
					if (toAdd < 0) {
						throw new IllegalArgumentException();
					}
					if (j == 0 || j == 2) {
						if (toAdd > len) {
							throw new IllegalArgumentException();
						}
					} else {
						if (toAdd > wid) {
							throw new IllegalArgumentException();
						}
					}
					target[i][j] = toAdd;
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException();
				}
			}
		}

		// goal format error
		return target;
	}

	public static void printPath(Board start, Board end) {
		if (end != start) {
			printPath(start, end.myPrev);
			System.out.println(end.myMove());
		}
	}

	public static void main(String[] args) {
		// lack init/goal path
		try {
			if (args.length != 2) {

				System.out.println("Invalid init and/or goal file.");
				return;
			}
			String initPath = args[0];
			String goalPath = args[1];
			File init = new File(initPath);
			File goal = new File(goalPath);
			// init/goal file does not exist
			if ((!init.exists()) || (!goal.exists())) {
				System.out.println("Invalid init and/or goal file.");
				return;
			}
			FileReader reader = new FileReader(init);
			BufferedReader textReader = new BufferedReader(reader);
			String line;
			int numberOfLine = 0;
			// HAVE NOT HANDLED IOException YET!!!
			while ((line = textReader.readLine()) != null) {
				numberOfLine++;
			}
			textReader.close();
			// init format error
			if (numberOfLine <= 1) {
				System.out.println("Invalid init and/or goal file.");
				return;
			}
			reader = new FileReader(init);
			textReader = new BufferedReader(reader);
			String[] boardSize = textReader.readLine().split(" ");
			// init format error
			if (boardSize.length != 2) {
				System.out.println("Invalid init and/or goal file.");
				return;
			}
			int len = 0;
			int wid = 0;
			try {
				len = Integer.parseInt(boardSize[0]);
				wid = Integer.parseInt(boardSize[1]);
				if (len <= 0 || wid <= 0) {
					throw new IllegalArgumentException();
				}
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException();
			}
			try {
				int[][] pieces = new int[numberOfLine - 1][4];
				pieces = getInit(numberOfLine, textReader, len, wid);
				int[][] target = getGoal(goalPath, len, wid);
				Board firstBoard = new Board(len, wid, pieces, target,
						new int[4]);
				Board a = firstBoard.startGame();
				if (a != null) {
					printPath(firstBoard, a);
					return;
				}
				return;
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid init and/or goal file.");
				return;
			} catch (IOException e2) {
				System.out.println("Invalid init and/or goal file.");
				return;
			}
		} catch (IOException a) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}
	}
}
