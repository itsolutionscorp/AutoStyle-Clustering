/**
 * Project 3: Puzzle Solver Written for CS61bl Summer 2015
 * 
 * @author Gayatri Sabne (dr), Kristin Ho (gl), Kevin Liu (hj)
 *
 *         Tray Codeshare: https://codeshare.io/ErmHJ 
 * 				Testing Codeshare:
 *         https://codeshare.io/GPA1C
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;
import java.util.Arrays;

public class Solver {

	// TESTING SECTION
	public boolean debug = false;

	public Solver(Tray t) {
		seen = new HashSet<Tray>();
		toVisit = new Stack<Tray>();
		Tray start = t;
		toVisit.push(start);
		myGoal = null;
	}

	// VARIABLES
	private HashSet<Tray> seen;
	private Stack<Tray> toVisit; // fringe
	private ArrayList<int[]> myGoal;

	// CONSTRUCTOR
	public Solver(int height, int width, ArrayList<int[]> initConfig,
			ArrayList<int[]> goalConfig) {
		seen = new HashSet<Tray>();
		toVisit = new Stack<Tray>();
		Tray start = new Tray(height, width, initConfig);
		toVisit.push(start);
		myGoal = goalConfig;
	}

	// METHODS

	public void solve() {
		while (!toVisit.empty()) {
			Tray tray = toVisit.pop();
			tray.calculateMoves();
			if (isGoal(tray)) {
				// DEBUG STUFF
				if (debug) {
					System.out.println("\n\nSOLUTION FOUND\n");
				}
				outputSolution(tray);
				break;
			} else {
				for (String move : tray.getNextMoves()) {
					Tray child = new Tray(move, tray);
					if (!seen.contains(child)) {
						toVisit.push(child);
						seen.add(child);
					}
				}
			}
		}
	}

	public boolean isGoal(Tray tray) {
		ArrayList<int[]> blocks = tray.getBlocks();
		int numSucceeded = 0;
		for (int g = 0; g < myGoal.size(); g++) {
			for (int t = 0; t < blocks.size() - 1; t++) {
				if (Arrays.equals(myGoal.get(g), blocks.get(t + 1))) {
					numSucceeded++;
				}
			}
		}
		return numSucceeded == myGoal.size();
	}

	public void outputSolution(Tray end) {
		Stack<String> solution = new Stack<String>();
		Tray curr = end;
		while (curr.getPrev() != null) {
			solution.add(curr.getMove());
			curr = curr.getPrev();
		}

		// DEBUG STUFF
		if (debug) {
			System.out.println("Original Tray was : ");
			curr.printTray();

			System.out.println("\nGoal Tray was : ");
			for (int i = 0; i < myGoal.size(); i++) {
				for (int j : myGoal.get(i))
					System.out.print(j + " ");
				System.out.println("\n");
			}

			System.out.println("\nEnd tray was: \n");
			end.printTray();
			System.out.println("\n\n");
			System.out.println("And the list of moves is: ");
		}

		while (!solution.empty()) {
			System.out.println(solution.pop());
		}
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}
		File initFile = new File(args[0]);
		File goalFile = new File(args[1]);

		// CHECKING THAT FILES EXIST IN DIRECTORY
		if (!initFile.exists() || !goalFile.exists()) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}

		// CHECK THAT THEY CAN BE SCANNED
		Scanner scanner = null, goalScanner = null;
		try {
			scanner = new Scanner(initFile);
			goalScanner = new Scanner(goalFile);
		} catch (FileNotFoundException e) {
			System.out.println("Invalid init and/or goal file.");
		}

		// CHECKING THAT FILE HAS A LINE FOR DIMENSIONS
		String dimensions = null;
		if (scanner.hasNextLine()) {
			dimensions = scanner.nextLine();
		} else {
			System.out.println("Invalid init and/or goal file.");
			return;
		}

		// CHECKING THAT THERE ARE VALID DIMENSIONS
		String[] dimensionNums = dimensions.split(" ");
		if (dimensionNums.length != 2) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}

		int height, width;
		try {
			height = Integer.parseInt(dimensionNums[0]);
			width = Integer.parseInt(dimensionNums[1]);
		} catch (NumberFormatException e) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}

		// CHECKING VALID PIECE CONFIGURATION AND ADDING TO TRAY
		ArrayList<int[]> initTrayConfiguration = new ArrayList<int[]>();
		initTrayConfiguration.add(null);

		int currentLine = 1;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] nums = line.split(" ");
			if (nums.length != 4) {
				System.out.println("Invalid init and/or goal file.");
				return;
			} else {
				initTrayConfiguration.add(new int[4]);

				try {
					for (int i = 0; i < 4; i++) {
						int coord = Integer.parseInt(nums[i]);
						initTrayConfiguration.get(currentLine)[i] = coord;
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid init and/or goal file.");
					return;
				}
				currentLine++;
			}
		}
		scanner.close();

		// CHECK GOAL CONFIGURATION AND REPRESENT AS 2D ARRAY
		ArrayList<int[]> goalConfiguration = new ArrayList<int[]>();
		int currentGoalLine = 0;
		while (goalScanner.hasNextLine()) {
			String line = goalScanner.nextLine();
			String[] nums = line.split(" ");
			if (nums.length != 4) {
				System.out.println("Invalid init and/or goal file.");
				return;
			} else {
				goalConfiguration.add(new int[4]);
				try {
					for (int i = 0; i < 4; i++) {
						int coord = Integer.parseInt(nums[i]);
						goalConfiguration.get(currentGoalLine)[i] = coord;
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid init and/or goal file.");
					return;
				}
			}
			currentGoalLine++;
		}
		goalScanner.close();

		if (currentGoalLine == 0) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}
		goalScanner.close();

		if (currentLine - 1 < currentGoalLine) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}
		if (currentLine == 1) {
			System.out.println("Invalid init and/or goal file.");
			return;
			
		}

		Solver s = new Solver(height, width, initTrayConfiguration,
				goalConfiguration);
		s.solve();

	}

}
