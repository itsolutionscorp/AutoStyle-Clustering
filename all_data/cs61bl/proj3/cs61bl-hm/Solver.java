import java.util.*;
import java.io.*;
import java.nio.file.Paths;
import java.awt.Point;

public class Solver {

	static int length = 0;
	static int width = 0;
	static boolean usingEmptyIterator;

	public static HashMap<State, State> fillSteps(State initial,
			Stack<String> reversePath, State goal) {
		State current = null;
		HashSet<State> visited = new HashSet<State>(10);
		Stack<State> fringe = new Stack<State>();
		fringe.push(initial);
		HashMap<State, State> steps = new HashMap<State, State>();
		State toCheck = null;
		outerloop: while (true) {
			if (fringe.isEmpty()) {
				return null;
			}
			current = fringe.pop();
			if (current.equals(goal)) {
				break;
			}
			visited.add(current);
			Iterator<State> iter;
			if (!usingEmptyIterator)
				iter = current.iterator();
			else
				iter = current.emptyIterator();
			while (true) {
				toCheck = iter.next();
				if (toCheck == null) {
					break;
				}
				if (!visited.contains(toCheck)) {
					visited.add(toCheck);
					steps.put(toCheck, current);
					if (toCheck.equals(goal)) {
						current = toCheck;
						break outerloop;
					}
					fringe.push(toCheck);
				}
			}
		}
		return steps;
	}

	/**
	 * We formatted this based on the example code given in the
	 * "Graphs and Priority Queues" lecture.
	 */
	public static void pathToGoal(State initial, State goal) {
		if (initial.equals(goal))
			return;
		Stack<String> reversePath = new Stack<String>();
		HashMap<State, State> steps = fillSteps(initial, reversePath, goal);
		if (steps == null)
			return;
		State currentStep = null;
		for (State s : steps.keySet()) {
			if (s.equals(goal)) {
				currentStep = steps.get(s);
				reversePath.push(s.moveString);
				if (!currentStep.moveString.equals(""))
					reversePath.push(currentStep.moveString);
				break;
			}
		}
		while (currentStep != null) {
			State previousStep = steps.get(currentStep);
			if (previousStep != null && !previousStep.equals(initial)) {
				reversePath.push(previousStep.moveString);
			}
			currentStep = previousStep;
		}
		while (!reversePath.isEmpty()) {
			System.out.println(reversePath.pop());
		}
	}

	/**
	 * Create initial state. Open file to be read, and set the height and width
	 * of the board.
	 */
	public static State scanInitial(String blockFileName) {
		Scanner scannerFile = new Scanner("");
		Scanner scanner = new Scanner("");
		HashMap<Point, Block> byTopLeft = new HashMap<Point, Block>(10);
		try {
			scannerFile = new Scanner(new File(Paths.get(blockFileName)
					.toString()));
			scanner = new Scanner(scannerFile.nextLine());
			length = Integer.parseInt(scanner.next());
			width = Integer.parseInt(scanner.next());
			if (scanner.hasNext()) {
				System.out.println("Invalid init and/or goal file.");
				scanner.close();
				scannerFile.close();
				return null;
			}
			HashMap<Integer, Point> byBlockID = new HashMap<Integer, Point>(5);
			int id = 1;
			int[][] initial = new int[length][width];
			if (length * width <= 100)
				usingEmptyIterator = true;
			else
				usingEmptyIterator = false;
			while (scannerFile.hasNext()) {
				scanner = new Scanner(scannerFile.nextLine());
				Point topLeftCoord = new Point(
						Integer.parseInt(scanner.next()),
						Integer.parseInt(scanner.next()));
				Point bottomRightCoord = new Point(Integer.parseInt(scanner
						.next()), Integer.parseInt(scanner.next()));
				Block newBlock = new Block(topLeftCoord, bottomRightCoord, id);
				byTopLeft.put(topLeftCoord, newBlock);
				byBlockID.put(id, topLeftCoord);
				if (bottomRightCoord.getX() < topLeftCoord.getX()
						|| bottomRightCoord.getY() < topLeftCoord.getY()
						|| scanner.hasNext()) {
					System.out.println("Invalid init and/or goal file.");
					scannerFile.close();
					scanner.close();
					return null;
				}

				for (int i = (int) topLeftCoord.getX(); i <= (int) bottomRightCoord
						.getX(); i++) {
					for (int j = (int) topLeftCoord.getY(); j <= (int) bottomRightCoord
							.getY(); j++) {
						if (initial[i][j] != 0) {
							System.out
									.println("Invalid init and/or goal file.");
							scanner.close();
							scannerFile.close();
							return null;
						}
						initial[i][j] = id;
					}
				}
				id++;
			}
			HashSet<Point> empties = new HashSet<Point>();
			for (int i = 0; i < length; i++) {
				for (int j = 0; j < width; j++) {
					if (initial[i][j] == 0) {
						empties.add(new Point(i, j));
					}
				}
			}
			State initialState;
			if (usingEmptyIterator)
				initialState = new State(byTopLeft, empties, length, width,
						initial, byBlockID);
			else
				initialState = new State(byTopLeft, empties, length, width);
			scanner.close();
			scannerFile.close();
			return initialState;
		} catch (InputMismatchException e) {
			System.out.println("Invalid init and/or goal file.");
			scanner.close();
			scannerFile.close();
			return null;
		} catch (IOException e) {
			System.out.println("Invalid init and/or goal file.");
			scanner.close();
			scannerFile.close();
			return null;
		}
	}

	/**
	 * Scan the goal file and save it into the State representation of the goal.
	 */
	public static State scanGoal(String targetFileName) {
		Scanner scannerFile = new Scanner("");
		Scanner scanner = new Scanner("");
		HashMap<Point, Block> byTopLeft = new HashMap<Point, Block>(10);
		try {
			scannerFile = new Scanner(new File(Paths.get(targetFileName)
					.toString()));
			int id = 1;
			while (scannerFile.hasNext()) {
				scanner = new Scanner(scannerFile.nextLine());
				Point topLeftCoord = new Point(
						Integer.parseInt(scanner.next()),
						Integer.parseInt(scanner.next()));
				Point bottomRightCoord = new Point(Integer.parseInt(scanner
						.next()), Integer.parseInt(scanner.next()));
				Block newBlock = new Block(topLeftCoord, bottomRightCoord, id);
				byTopLeft.put(topLeftCoord, newBlock);
				id++;
				if (bottomRightCoord.getX() < topLeftCoord.getX()
						|| bottomRightCoord.getY() < topLeftCoord.getY()
						|| scanner.hasNext()) {
					System.out.println("Invalid init and/or goal file.");
					scannerFile.close();
					scanner.close();
					return null;
				}
			}
			State goalState = new State(byTopLeft, length, width);
			scannerFile.close();
			scanner.close();
			return goalState;
		} catch (InputMismatchException e) {
			System.out.println("Invalid init and/or goal file.");
			scannerFile.close();
			scanner.close();
			return null;
		} catch (IOException e) {
			System.out.println("Invalid init and/or goal file.");
			scannerFile.close();
			scanner.close();
			return null;
		}
	}

	public static void main(String[] args) {
		boolean debug = false;
		Timer t = new Timer();
		if (debug) {
			t.start();
		}
		if (args.length != 2) {
			System.out.println("Invalid init and/or goal file.");
			return;
		} else {
			try {
				State initial = scanInitial(args[0]);
				if (initial == null)
					return;
				State goalState = scanGoal(args[1]);
				if (goalState == null)
					return;
				pathToGoal(initial, goalState);
			} catch (Exception e) {
				System.out.println("Invalid init and/or goal file.");
				return;
			}
		}
		if (debug) {
			long elapsedMs = t.stop();
			System.out.println(elapsedMs + " milliseconds elapsed.");
		}
	}
}
