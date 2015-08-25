import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class Solver {
	private boolean valid = true;
	private Tray oGTray;
	private HashSet<Tray> seenConfigs;
	private Stack<Tray> searchFringe;
	private ArrayList<Block> goalBlocks;
	
	/**
	 * @return the private variable goalBlocks
	 */
	public ArrayList<Block> goalBlocks() {
		return goalBlocks;
	}

	/**
	 * Takes a fileName and returns an ArrayList of the integers from that line;
	 * @param fileName is the name of the file
	 * @return an ArrayList<ArrayList<Integer>> lines
	 */
	public ArrayList<ArrayList<Integer>> fileToIntArray(String fileName) {
		ArrayList<ArrayList<Integer>>lines = new ArrayList<ArrayList<Integer>>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = in.readLine()) != null) {
			    line = (line.toString());
			    Scanner sc = new Scanner(line);
			    ArrayList<Integer> coords = new ArrayList<Integer>();
				int k = 0;
				while (sc.hasNextInt()) {
					k = sc.nextInt();
					coords.add(k);
				}
			    lines.add(coords);
			}
			in.close();
		} catch (IOException e) {
			valid = false;
		}
		return lines;
	}

	/**
	 * Takes in a current tray and an arraylist of blocks that the goal should contain
	 * @param t
	 * @param goal
	 * @return true if goal has been met
	 */
	public static boolean goalMet(Tray t, ArrayList<Block> goal) {
		for (Block b : goal) {
			if (!t.blocks().contains(b)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * The constructor for the Solver class. Takes in two file names and sets instance variables.
	 * @param init
	 * @param goal
	 */
	public Solver(String init, String goal) {
		seenConfigs = new HashSet<Tray>();
		searchFringe = new Stack<Tray>();
		ArrayList<ArrayList<Integer>> lines = this.fileToIntArray(init);
		if (lines.get(0).size() != 2) {
			valid = false;
		} else {
			oGTray = new Tray(lines.get(0).get(0), lines.get(0).get(1));
			for (ArrayList<Integer> protoBlock : lines.subList(1, lines.size())) {
				if (protoBlock.size() != 4) {
					valid = false;
				} else {
					Block b = new Block(protoBlock.get(0), protoBlock.get(1), protoBlock.get(2), protoBlock.get(3));
					if (oGTray.canAdd(b)) {
						oGTray.addBlock(b);
					} else {
						valid = false;
					}
				}
			}
			ArrayList<ArrayList<Integer>> goalLines = fileToIntArray(goal);
			goalBlocks = new ArrayList<Block>();
			for (ArrayList<Integer> goalBlock : goalLines) {
				if (goalBlock.size() != 4) {
					valid = false;
				} else {
					Block g = new Block(goalBlock.get(0), goalBlock.get(1), goalBlock.get(2), goalBlock.get(3));
					goalBlocks.add(g);
				}
			}
			if (valid) {
				searchFringe.add(oGTray);
			}
		}
	}

	/**
	 * The solving method for the class. Implements a depth first search with a Tray fringe.
	 */
	public void solve() {
		while (!searchFringe.isEmpty()) {
			Tray popped = searchFringe.pop();
			if (!check(popped, goalBlocks)) {
				for (Tray.MoveObject move : popped.validMoves()) {
					Tray store = popped.blockMove(move.myBlock, move.newLeftX, move.newLeftY);
					if (!seenConfigs.contains(store)) {
						store.setMove(move);
						store.setParent(popped);
						searchFringe.add(store);
						seenConfigs.add(store);
					}
				}
			}
		}
		if (valid) {
			System.exit(0);
		}
	}

	/**
	 * Checks if the current tray matches the goal blocks
	 * @param curr
	 * @param goalBlocks
	 * @return boolean true or false
	 */
	public boolean check(Tray curr, ArrayList<Block> goalBlocks) {
		if(goalMet(curr, goalBlocks) && valid) {
			Stack<Tray.MoveObject> path = new Stack<Tray.MoveObject>();
			Tray.MoveObject m = curr.move();
			while (m != null) {
				path.push(m);
				curr = curr.parent();
				m = curr.move();
			}
			while (!path.isEmpty()) {
				System.out.println((Tray.MoveObject) path.pop());
			}
			System.exit(0);
		}
		return false;
	}

	/**
	 * calls the Solver constructor and the solve function. Bulletproof/airtight/cannot crash.
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 2) {
			Solver lilSolver = new Solver(args[0], args[1]);
			lilSolver.solve();
		}
		System.out.println("Invalid init and/or goal file.");
	}
}

