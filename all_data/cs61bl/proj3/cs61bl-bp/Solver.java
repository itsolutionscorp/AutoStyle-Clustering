import java.util.*;
import java.io.*;
import java.awt.Point;

/**
 * Solver contains the graph structure that represents all the possible
 * configurations of the tray that can be made. Additionally, it handles the
 * input of configuration files for hte initial setup and goal configurations.
 * 
 * @author Albert Pham cs61bl-bp
 * @author Henry Gong cs61bl-bk
 * @author Patrick Zhang cs61bl-bo
 * @date August 11, 2015
 *
 */
public class Solver {
	// static variables since all trays share these traits
	static int HEIGHT;
	static int WIDTH;
	static int[][] DIRECTION = { { -1, 0 }, // up
			{ 0, -1 }, // left
			{ 0, 1 }, // right
			{ 1, 0 } // down
	};
	static HashMap<String, Block> allBlocks;
	static Block biggestGoal;
	TreeSet<Tray> seen;
	PriorityQueue<Tray> fringe;
	PriorityQueue<Block> goal;
	Tray goalTray;

	/**
	 * Creates a new solver with the given height and width from configuration
	 * file.
	 * 
	 * @param height
	 *            How tall the puzzle is.
	 * @param width
	 *            How wide the puzzle is.
	 */
	public Solver(int height, int width) {
		HEIGHT = height;
		WIDTH = width;
		allBlocks = new HashMap<String, Block>();
		biggestGoal = new Block(0, 0, 0, 0);
		fringe = new PriorityQueue<Tray>();
		goal = new PriorityQueue<Block>(5, Collections.reverseOrder());
		seen = new TreeSet<Tray>();
	}

	/**
	 * Determines if a coordinate is within the boundaries of the board.
	 * 
	 * @param x
	 *            Row to check.
	 * @param y
	 *            Column to check.
	 * @return true if the point is in the boundary.
	 */
	public static boolean inBoundary(int x, int y) {
		return x >= 0 && x < HEIGHT && y >= 0 && y < WIDTH;
	}

	/**
	 * Prints the sequence of steps necessary to go from the inital tray to the
	 * given tray.
	 * 
	 * @param t
	 *            Tray that matches the goal configuration.
	 */
	public static void printStep(Tray t) {
		Stack<Block> step = new Stack<Block>();
		Tray tr = t;
		while (tr.preTray != null) {
			step.push(tr.move);
			tr = tr.preTray;
		}
		while (!step.isEmpty()) {
			System.out.println(step.pop());
		}
	}

	/**
	 * Iterates through the empty spaces in the given tray and adds possible
	 * moves to the fringe if they have not already been seen.
	 * 
	 * @param tr
	 *            Tray to search through for moves.
	 */
	public void BFS(Tray tr) {
		for (Point p : tr.emptySpace) {
			for (int i = 0; i < 4; i++) {
				int x = p.x + Solver.DIRECTION[i][0];
				int y = p.y + Solver.DIRECTION[i][1];
				if (tr.canMove(x, y, 3 - i)) {
					Tray temp = new Tray(tr);
					temp.move(x, y, 3 - i);
					if (!seen.contains(temp)) {
						fringe.add(temp);
						seen.add(temp);
					}
				}
			}
		}
	}

	/**
	 * Determines if the given tray matches the goal configuration.
	 * 
	 * @param t
	 *            tray to test for goals.
	 * @return true if this tray is a winner.
	 */
	public boolean isGoal(Tray t) {
		for (Block b : goal) {
			if (!b.equals(t.tray[b.p1.x][b.p1.y])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Adds the block with the given coordinates as a goal block.
	 * 
	 * @param x1
	 *            Row of top-left point.
	 * @param y1
	 *            Column of top-left point.
	 * @param x2
	 *            Row of bottom-right point.
	 * @param y2
	 *            Column of bottom-right point.
	 * @return The new block generated.
	 */
	public Block addGoal(int x1, int y1, int x2, int y2) {
		Block b = goalTray.addBlock(x1, y1, x2, y2);
		goal.add(b);
		return b;
	}

	/**
	 * Handles the input of the configuration files from the terminal and begins
	 * the search for the solution.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			File initFile = new File(args[0]);
			File goalFile = new File(args[1]);
			Scanner init = new Scanner(initFile);
			Scanner goal = new Scanner(goalFile);
			Solver s = new Solver(init.nextInt(), init.nextInt());
			Tray t = new Tray();
			while (init.hasNextInt()) {
				t.addBlock(init.nextInt(), init.nextInt(), init.nextInt(),
						init.nextInt());
			}
			init.close();
			s.goalTray = new Tray();
			while (goal.hasNextInt()) {
				Block block = s.addGoal(goal.nextInt(), goal.nextInt(),
						goal.nextInt(), goal.nextInt());
				if (block.compareTo(biggestGoal) > 0) {
					biggestGoal = block;
				}
			}
			goal.close();
			for (Block block : t.blocks) {
				t.distance = Math.min(biggestGoal.distance(block), t.distance);
			}
			s.fringe.add(t);
			s.seen.add(t);
			while (!s.fringe.isEmpty()) {
				Tray tr = s.fringe.poll();
				if (s.isGoal(tr)) {
					printStep(tr);
					return;
				} else {
					s.BFS(tr);
				}
			}
		} catch (Exception e) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}
	}
}
