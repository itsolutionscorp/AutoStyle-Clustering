import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

public class Tray {

	private HashSet<Block> myBlocks;
	private static ArrayList<Block> myGoal;
	private boolean[][] myBoard;
	private static int myWidth, myHeight;
	private ArrayList<String> myMoves;
	public PriorityQueue<Tray> myChildren;
	private static TrayComparator comp = new TrayComparator();
	public static PriorityQueue<Tray> fringe = new PriorityQueue<Tray>(100, comp);
	private boolean hasBeenSeen = false;
	private static HashSet<Tray> alreadySeen = new HashSet<Tray>();
	private int spaceMoved;
	public static boolean problemSolved = false;

	/**
	 * This constructor is only called when we build first Tray to start with.
	 * 
	 * @param init
	 * @param goal
	 * @param width
	 * @param height
	 */
	public Tray(HashSet<Block> init, ArrayList<Block> goal, int width, int height) {
		// TODO
		myBlocks = init;
		myGoal = goal;
		myWidth = width;
		myHeight = height;
		myMoves = new ArrayList<String>();
		TrayComparator comp = new TrayComparator();
		myChildren = new PriorityQueue<Tray>(100, comp);
		makeBoard();

	}

	/**
	 * This constructor cannot be called before first Tray is constructed with
	 * the constructor above.
	 * 
	 * @param blocks
	 * @param moves
	 * @param move
	 */
	public Tray(HashSet<Block> blocks, ArrayList<String> moves, String move, int spaceMoved) {
		// TODO is it right that you want to copy all the contents of moves
		// in constructor?
		myBlocks = blocks;
		myMoves = new ArrayList<String>();
		myMoves.addAll(moves);
		myMoves.add(move);
		TrayComparator comp = new TrayComparator();
		myChildren = new PriorityQueue<Tray>(100, comp);
		makeBoard();
		this.spaceMoved = spaceMoved;
	}

	/**
	 * Produces dummy Tray object for comparison.
	 * 
	 * @param blocks
	 */
	public Tray(HashSet<Block> blocks) {
		myBlocks = blocks;
		makeBoard();
	}

	/**
	 * Dummy
	 * 
	 * @param blocks
	 */
	public Tray(HashSet<Block> blocks, int width, int height) {
		myWidth = width;
		myHeight = height;
		myBlocks = blocks;
		makeBoard();
	}

	/**
	 * need to update Makes 2D array of booleans, which represents whether there
	 * is block at the position.
	 */
	private void makeBoard() {
		myBoard = new boolean[myHeight][myWidth];
		for (int i = 0; i < myHeight; i++) {
			for (int j = 0; j < myWidth; j++) {
				myBoard[i][j] = false;
			}
		}
		for (Block block : myBlocks) {
			int x1 = block.x1();
			int y1 = block.y1();
			int x2 = block.x2();
			int y2 = block.y2();
			for (int i = y1; i <= y2; i++) {
				for (int j = x1; j <= x2; j++) {
					myBoard[i][j] = true;
				}
			}
		}
	}

	/**
	 * Tries all the possible moves and creates Tray. The newly created Trays
	 * are stored in myChildren.
	 * 
	 * @param block
	 */
	private void move(Block b) {
		ArrayList<ArrayList<Integer>> possibleNewPos = possibleMoves(b);
		for (ArrayList<Integer> newPos : possibleNewPos) {

			Block movedBlock = b.move(newPos);

			myBlocks.remove(b);
			myBlocks.add(movedBlock);

			Tray t = new Tray(myBlocks);
			if (alreadySeen.contains(t)) {
				myBlocks.remove(movedBlock);
				myBlocks.add(b);
				continue;
			}
			myBlocks.remove(movedBlock);
			myBlocks.add(b);

			HashSet<Block> newBlocks = new HashSet<Block>();
			for (Block j : myBlocks) {
				if (j.equals(b)) {
					newBlocks.add(movedBlock);
				} else {
					newBlocks.add(j);
				}
			}
			Tray newTray = new Tray(newBlocks, myMoves, b.move(), 0);
			fringe.add(newTray);
			myChildren.add(newTray);
			alreadySeen.add(newTray);

		}
	}

	private ArrayList<ArrayList<Integer>> possibleMoves(Block b) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		for (ArrayList<Integer> a : checkUp(b)) {
			result.add(a);
		}
		for (ArrayList<Integer> a : checkDown(b)) {
			result.add(a);
		}
		for (ArrayList<Integer> a : checkLeft(b)) {
			result.add(a);
		}
		for (ArrayList<Integer> a : checkRight(b)) {
			result.add(a);
		}
		return result;
	}

	private ArrayList<ArrayList<Integer>> checkUp(Block b) {
		int y1 = b.y1();
		int y2 = b.y2();
		int x1 = b.x1();
		int x2 = b.x2();
		ArrayList<ArrayList<Integer>> moves = new ArrayList<ArrayList<Integer>>();
		if (y1 == 0)
			return moves;
		for (int i = y1 - 1, j = y2 - 1; i > -1; i--, j--) {
			boolean spaceIsEmpty = true;
			for (int k = x1; k <= x2; k++) {
				if (myBoard[i][k] == true)
					spaceIsEmpty = false;
			}
			if (spaceIsEmpty) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(i);
				temp.add(x1);
				temp.add(j);
				temp.add(x2);
				moves.add(temp);
			} else
				break;
		}
		return moves;
	}

	private ArrayList<ArrayList<Integer>> checkDown(Block b) {
		int y1 = b.y1();
		int y2 = b.y2();
		int x1 = b.x1();
		int x2 = b.x2();
		ArrayList<ArrayList<Integer>> moves = new ArrayList<ArrayList<Integer>>();
		if (y2 >= myHeight - 1)
			return moves;
		for (int i = y1 + 1, j = y2 + 1; j < myHeight; i++, j++) {
			boolean spaceIsEmpty = true;
			for (int k = x1; k <= x2; k++) {
				if (myBoard[j][k] == true)
					spaceIsEmpty = false;
			}
			if (spaceIsEmpty) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(i);
				temp.add(x1);
				temp.add(j);
				temp.add(x2);
				moves.add(temp);
			} else
				break;
		}
		return moves;
	}

	private ArrayList<ArrayList<Integer>> checkLeft(Block b) {
		int y1 = b.y1();
		int y2 = b.y2();
		int x1 = b.x1();
		int x2 = b.x2();
		ArrayList<ArrayList<Integer>> moves = new ArrayList<ArrayList<Integer>>();
		if (x1 == 0)
			return moves;
		for (int i = x1 - 1, j = x2 - 1; i > -1; i--, j--) {
			boolean spaceIsEmpty = true;
			for (int k = y1; k <= y2; k++) {
				if (myBoard[k][i] == true)
					spaceIsEmpty = false;
			}
			if (spaceIsEmpty) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(y1);
				temp.add(i);
				temp.add(y2);
				temp.add(j);
				moves.add(temp);
			} else
				break;
		}
		return moves;
	}

	private ArrayList<ArrayList<Integer>> checkRight(Block b) {
		int y1 = b.y1();
		int y2 = b.y2();
		int x1 = b.x1();
		int x2 = b.x2();
		ArrayList<ArrayList<Integer>> moves = new ArrayList<ArrayList<Integer>>();
		if (x2 >= myWidth - 1)
			return moves;
		for (int i = x1 + 1, j = x2 + 1; j < myWidth; i++, j++) {
			boolean spaceIsEmpty = true;
			for (int k = y1; k <= y2; k++) {
				if (myBoard[k][j] == true)
					spaceIsEmpty = false;
			}
			if (spaceIsEmpty) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(y1);
				temp.add(i);
				temp.add(y2);
				temp.add(j);
				moves.add(temp);
			} else
				break;
		}
		return moves;
	}

	/**
	 * @return whether if this Tray has reached goal configuration
	 */
	public boolean isGoal() {
		// TODO
		for (Block goal : myGoal) {
			if (!myBlocks.contains(goal))
				return false;
		}
		return true;
	}

	/**
	 * Prints out the log of moves
	 */
	public void printMoves() {
		// TODO edited 8/4 22:49 by Jayoung
		for (String s : myMoves) {
			System.out.println(s);
		}
	}

	/**
	 * @return the distance from goal configuration and itself
	 */
	public int distance() {
		// TODO
		int result = 0;
		int goalDistance;
		
		
		HashSet<Block> usedMyBlocks = new HashSet<Block>();
		HashSet<Block> doneGoals = new HashSet<Block>();
		
		for (Block goal : myGoal) {
			if (myBlocks.contains(goal)) {
				usedMyBlocks.add(goal);
				doneGoals.add(goal);
			}
		}
		for (Block goal : myGoal) {
			if (doneGoals.contains(goal)) continue;
			goalDistance = 256 * 256;
			for (Block bl : myBlocks) {
				if (usedMyBlocks.contains(bl))
					continue;
				if(bl.sameShape(goal)){
					if (goalDistance > bl.distance(goal)) {
						goalDistance = bl.distance(goal);
						usedMyBlocks.add(goal);
					}
				}
			}
			result += Math.pow(goalDistance,2);
		}
		return (int) Math.sqrt(result);
		
		
		
		/**
		if (myGoal.size() == myBlocks.size()) {
			HashSet<Block> usedGoalBlocks = new HashSet<Block>();
			for (Block bl : myBlocks) {
				goalDistance = 256 * 256;
				for (Block goal : myGoal) {
					if (usedGoalBlocks.contains(goal))
						continue;
					if (!(bl.width() == goal.width()) && bl.height() == goal.height())
						continue;
					if (goalDistance > bl.distance(goal)) {
						goalDistance = bl.distance(goal);
						usedGoalBlocks.add(goal);
					}
				}
				result += goalDistance;
			}
			return result;
		}
		for (Block goal : myGoal) {
			goalDistance = 256 * 256;
			for (Block current : myBlocks) {
				if (!(goal.width() == current.width() && goal.height() == current.height()))
					continue;
				if (goalDistance > current.distance(goal)) {
					goalDistance = current.distance(goal);
				}
			}
			result += goalDistance;
		}
		return result;*/
	}

	/**
	 * Returns hashCode of Tray : same if they are same config. But not
	 * neccessary different if they are different.
	 */
	@Override
	public int hashCode() {
		// TODO for this to be same for same config, with different Block
		// order...
		// myBlock has to be sorted...?
		int hash = 1;
		for (Block b : myBlocks) {
			hash *= b.hashCode();
		}
		return hash;
	}

	/**
	 * Returns if this and Tray O are in same configuration.
	 */
	@Override
	public boolean equals(Object O) {
		// TODO would this take forever..?
		if (O instanceof Tray) {
			if (myBlocks.size() != ((Tray) O).getBlocks().size())
				return false;
			for (Block b : myBlocks) {
				if (!((Tray) O).getBlocks().contains(b))
					return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * returns private myBlocks for comparison (see equals)
	 * 
	 * @return
	 */
	public HashSet<Block> getBlocks() {
		return myBlocks;
	}

	public void solve() {
		if (hasBeenSeen) {
			return;
		}
		ArrayList<Block> blockStack = new ArrayList<Block>();
		for (Block b : myBlocks) {
			blockStack.add(b);
		}
		for (Block temp : blockStack) {
			move(temp);
		}
		// for (Block b : myBlocks){
		// move(b);
		// }

		for (Tray t : myChildren) {
			if (t.isGoal()) {
				t.printMoves();
				problemSolved = true;
				break;
			}
		}
		 //while(!myChildren.isEmpty() && !problemSolved){
			 //Tray t = myChildren.poll();
			 //t.solve();
		 //}
	}

}




