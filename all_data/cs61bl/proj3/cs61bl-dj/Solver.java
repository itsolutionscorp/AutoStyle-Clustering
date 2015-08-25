import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

class Solver {

	// Where the magic happens
	public static void main(String args[]) {

		if (args.length != 2) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}

		File inputFile = new File(args[0]);
		File goalFile = new File(args[1]);

		// Makes sure that the inputs are valid. Bulletproof (we hope).
		if (!inputsValid(inputFile, goalFile)) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}
		
		// Make sure that the goal is valid
		LinkedList<Goal> goals = (LinkedList<Goal>)generateGoals(goalFile);
		if (goals == null) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}

		List<Move> solution = solve(new Tray(inputFile),
				generateGoals(goalFile));
		printSolution(solution);
	}

	/**
	 * 
	 * @param inputFile
	 *            The initial Tray
	 * @param goalFile
	 *            Our goal Tray
	 * @return True if they're both valid. Otherwise, false.
	 */
	public static boolean inputsValid(File inputConfig, File goalConfig) {
		// check that arg files exist
		// check that their format is valid

		if (!inputConfig.exists() || !goalConfig.exists()) {
			return false;
		}

		try {
			Scanner sc = new Scanner(inputConfig);

			int width = sc.nextInt();
			int height = sc.nextInt();
			int pieces = 0;

			int[][] filled = new int[width][height];
			for (int j = 0; j < height; j++) {
				for (int i = 0; i < width; i++) {
					filled[i][j] = 0;
				}
			}
			
			if (width <= 0 || height <= 0) {
				return false;
			}

			int x1, x2;
			int y1, y2;

			while (sc.hasNext()) {
				x1 = sc.nextInt();
				y1 = sc.nextInt();
				x2 = sc.nextInt();
				y2 = sc.nextInt();
				
				if (x1 < 0 || x1 > x2 || x1 >= width) {
					return false;
				}
				if (x2 < 0 || x2 >= width) {
					return false;
				}
				if (y1 < 0 || y1 > y2 || y2 >= height) {
					return false;
				}
				if (y2 < 0 || y2 >= height) {
					return false;
				}
					
				// check for overlap
				for (int j = y1; j <= y2; j++) {
					for (int i = x1; i <= x2; i++) {
						if (filled[i][j] == 1) {
							return false;
						} 
						filled[i][j] = 1;
					}
				}
				
				pieces += 1;
			}

			sc = new Scanner(goalConfig);
			int goals = 0;
			
			for (int j = 0; j < height; j++) {
				for (int i = 0; i < width; i++) {
					filled[i][j] = 0;
				}
			}

			while (sc.hasNextInt()) {
				x1 = sc.nextInt();
				y1 = sc.nextInt();
				x2 = sc.nextInt();
				y2 = sc.nextInt();

				if (x1 < 0 || x1 > x2 || x1 >= width) {
					return false;
				}
				if (x2 < 0 || x2 >= width) {
					return false;
				}
				if (y1 < 0 || y1 > y2 || y2 >= height) {
					return false;
				}
				if (y2 < 0 || y2 >= height) {
					return false;
				}
				
				for (int j = y1; j <= y2; j++) {
					for (int i = x2; i <= x2; i++) {
						if (filled[i][j] == 1) {
							return false;
						}
						filled[i][j] = 1;
					}
				}
				
				goals += 1;
			}
			
			if (goals == 0 || goals > pieces || pieces == 0) {
				return false;
			}
			
			return true;

		} catch (FileNotFoundException e) {
			return false;
		} catch (InputMismatchException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Graceful exit.
	 */
	public static void exit() {
		System.out.println("Invalid init and/or goal file.");
	}

	/**
	 * Solves the problem or reports that there is no solution.
	 * 
	 * @param initialTray
	 *            The initial setup of the Tray.
	 * @return A list of moves leading us from the initial Tray to the goal. Or
	 *         null if there is no solution.
	 */
	public static List<Move> solve(Tray initialTray, List<Goal> goals) {
		Map<Integer, LinkedList<Tray>> seenTrays = new HashMap<Integer, LinkedList<Tray>>();
		Map<Tray, Tray> prevTray = new HashMap<Tray, Tray>();
		LinkedList<Tray> fringe = new LinkedList<Tray>();
		List<Move> tortn = new ArrayList<Move>();

		// Push the initial configuration onto the stack.
		fringe.add(initialTray);

		// Tray DFS -- the meat of the solution
		while (!fringe.isEmpty()) {
			Tray currTray = fringe.pop();
			int myHashCode = currTray.hashCode();

			if (!seenTrays.containsKey(myHashCode)) {
				seenTrays.put(myHashCode, new LinkedList<Tray>());
			}
			seenTrays.get(myHashCode).add(currTray);

			if (allGoalsSatisfied(currTray, goals)) {
				return generateMovePath(initialTray, currTray, prevTray);
			} else {
				List<Move> validMoves = currTray.generateValidMoves();
				List<Tray> validTrays = currTray.generateTrays(validMoves);
				for (Tray t : validTrays) {
					int tHash = t.hashCode();
					if (!seenTrays.containsKey(tHash)) {
						prevTray.put(t, currTray);
						fringe.push(t);
					} else {
						boolean add = true;
						for (Tray p : seenTrays.get(tHash)) {
							if (p.equals(t)) {
								add = false;
							}
						}
						if (add) {
							prevTray.put(t, currTray);
							fringe.push(t);
						}
					}
				}
			}
		}
		return tortn;
	}

	/**
	 * Given an initial Tray, the last Tray, and a Map of prev-pointers,
	 * generates the list of moves that got us from initial to goal.
	 * 
	 * @param initialTray
	 *            Initial board with starting piece positions
	 * @param lastTray
	 *            Final board with piece positions satisfying goal positions
	 * @param prevTrays
	 *            Collection that maps Trays to their previous Trays
	 */
	public static List<Move> generateMovePath(Tray initialTray, Tray lastTray,
			Map<Tray, Tray> prevTrays) {
		Tray currTray = lastTray;
		Stack<Move> movePath = new Stack<Move>();
		while (true) {
			if (currTray == initialTray) {
				break;
			}
			movePath.add(currTray.getMove());
			currTray = prevTrays.get(currTray);
		}

		ArrayList<Move> rtn = new ArrayList<Move>();

		while (!movePath.isEmpty()) {
			rtn.add(movePath.pop());
		}
		return rtn;
	}

	/**
	 * Correctly formats and prints the solution.
	 * 
	 * @param sln
	 *            A list of moves that led us to the correct solution.
	 */
	public static void printSolution(List<Move> sln) {
		if (sln == null) {
			return;
		} else {
			for (Move m : sln) {
				System.out.println(m.printX());
			}
		}
	}

	/**
	 * Given a File to the goals, creates a list of Goal objects, which each
	 * represent one line of the overall goal.
	 * 
	 * @param goalFile
	 *            The File input for goal
	 * @return The list of goals stored by Solver. Used to decide if we can stop
	 *         exploring new Tray objects.
	 */
	public static List<Goal> generateGoals(File goalFile) {
		try {
			LinkedList<Goal> myGoals = new LinkedList<Goal>();
			Scanner sc = new Scanner(goalFile);
			int x1, y1, x2, y2;

			while (sc.hasNext()) {
				x1 = sc.nextInt();
				y1 = sc.nextInt();
				x2 = sc.nextInt();
				y2 = sc.nextInt();

				myGoals.add(new Goal(x1, y1, x2, y2));
			}

			return myGoals;

		} catch (FileNotFoundException e) {
			System.out.println("Invalid init and/or goal file.");
			return null;
		}
	}

	/**
	 * Checks to see if all of the goals are satisfied.
	 * 
	 * @param t
	 *            The Tray that we're trying to check on
	 * @param goals
	 *            A list of goals that we're trying to see if they're satisfied.
	 * @return Whether or not ALL goals are satisfied
	 */
	public static boolean allGoalsSatisfied(Tray t, List<Goal> goals) {
		for (Goal g : goals) {
			if (!goalSatisfied(t, g)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Given one goal, checks if the given tray satisfies it.
	 * 
	 * @param t
	 *            The Tray of interest
	 * @param g
	 *            The goal that we're trying to check.
	 * @return Whether or not the tray satisfied the goal
	 */
	public static boolean goalSatisfied(Tray t, Goal g) {
		// make sure that the corners match up

		Piece target = t.pieceAtCoord(g.x1(), g.y1());

		if (target == null) {
			return false;
		}

		if (target.x1() != g.x1()) {
			return false;
		}

		if (target.y1() != g.y1()) {
			return false;
		}

		if (target.x2() != g.x2()) {
			return false;
		}

		if (target.y2() != g.y2()) {
			return false;
		}

		return true;
	}
}
