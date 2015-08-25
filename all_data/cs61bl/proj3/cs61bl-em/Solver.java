import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Solver {

	protected stateNode myInit, myGoal;
	protected Set<stateNode> nodesSeen;
	protected int numRows, numCols;

	/* constructor
	 * initializes Solver object using initFileName and goalFileName from command prompt
	 * instantiates stateNodes myInit and myGoal from the files
	 */
	public Solver (String initFileName, String goalFileName) {
		try {
			myInit = new stateNode();
			myInit.initStateNode(initFileName);
			myGoal = new stateNode();
			myGoal.goalStateNode(goalFileName);
			nodesSeen = new HashSet<stateNode>();
			nodesSeen.add(myInit);
		}
		catch (Exception e) {}
	}

	/*validFile
	 * @param String fileName
	 * verifies that the file names from command prompt are valid (they exist in the directory)
	 */
	public static boolean validFile (String fileName) {
		File f = new File(fileName);
		if (f.exists()) {
			return true;
		}
		File[] subDir = new File(Paths.get(".").toAbsolutePath().normalize().toString()).listFiles(File::isDirectory);
		for (File dir : subDir) {
			File fileInDir = new File(dir, fileName);
			if (fileInDir.exists()) {
				File fileCopy = new File(fileName);
				try {
					Files.copy(fileInDir.toPath(), fileCopy.toPath(),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
				}
				return true;
			}
		}
		return false;
		//http://stackoverflow.com/questions/4871051/getting-the-current-working-directory-in-java
	}

	/*solve
	 *called from main to solve the puzzle
	 *sends myInit stateNode to other solve method
	 */
	public void solve() {
		solve(myInit);
	}

	/*solve
	 * @param stateNode start
	 * checks for a match by calling .equals
	 * goes through each child node to check for a match
	 * when match is found, prints all moves
	 */
	public void solve (stateNode start) {
		if (start.equals(myGoal) || start.contains(myGoal)) {
			if (start == myInit) {
				return;
			}
			else {
				start.printMoves();
				System.exit(0);
			}
		}
		else {
			start.generateChildren();
			//do child with highest rank!!!
			for (stateNode child : start.myChildren) {
					solve(child);
			}
//			while (!start.myChildren.isEmpty()) {
//				solve(start.myChildren.poll());
//			}
		}

	}

	/*block class
	 *
	 */
	public class Block {
		private int myX1, myY1, myX2, myY2;
		private int myWidth;
		private int myHeight;

		public Block (int y1, int x1, int y2, int x2) {
			myX1 = x1;
			myY1 = y1;
			myX2 = x2;
			myY2 = y2;
			myWidth = x2 - x1;
			myHeight = y2 - y1;
		}

		public int x1() {
			return myX1;
		}

		public int y1() {
			return myY1;
		}

		public int x2() {
			return myX2;
		}

		public int y2() {
			return myY2;
		}

		public void setX1 (int x) {
			myX1 = x;
		}

		public void setY1 (int y) {
			myY1 = y;
		}

		public void setX2 (int x) {
			myX2 = x;
		}

		public void setY2 (int y) {
			myY2 = y;
		}

		public int width() {
			return myWidth;
		}

		public int height() {
			return myHeight;
		}

	}

	/* stateNode class
	 * each instance represents a state of the puzzle
	 */
	public class stateNode implements Comparable {
		private int rank;
		private stateNode myParent;
		private List<int[]> myState;
		private Queue<int[]> myMoves;
		//private List<stateNode> myChildren;
		private Queue<stateNode> myChildren;
		private List<Block> myBlocks;
		private boolean[][] Tray;

		/*constructor
		 * @param none
		 * this constructor is only used for myInit and myGoal stateNodes
		 */
		public stateNode() {
			myMoves = new LinkedList<int[]>();
			myState = new ArrayList<int[]>();
			//myChildren = new ArrayList<stateNode>();
			myChildren = new PriorityQueue<stateNode>();
			myBlocks = new ArrayList<Block>();
		}

		/*
		 * @param stateNode parent, int[] move
		 * this constructor is used to initialize every stateNode that results from a move
		 */
		public stateNode(stateNode parent, int[] move) {
			myParent = parent;
			myMoves = new LinkedList<int[]>(parent.moves());
			myMoves.add(move);
			myState = new ArrayList<int[]>();
			//myChildren = new ArrayList<stateNode>();
			myChildren = new PriorityQueue<stateNode>();
			myBlocks = new ArrayList<Block>();
			for (int[] row : myParent.myState) {
				if (row[0] == move[0] && row[1] == move[1]) {
					int[] newRow = {move[2], move[3], row[2] + move[2] - move[0], row[3] + move[3] - move[1]};
					myState.add(newRow);
				}
				else {
					myState.add(row);
				}
			}
			for (Block b : myParent.myBlocks) {
				if (b.x1() == move[1] && b.y1() == move[0]) {
					Block newBlock = new Block (move[2], move[3], b.y2() + move[2] - move[0], b.x2() + move[3] - move[1]);
					myBlocks.add(newBlock);
				}
				else {
					myBlocks.add(b);
				}
			}
			Tray = new boolean[numRows][numCols];
			for (Block b: myBlocks) {
				for (int i = b.y1(); i <= b.y2(); i ++) {		//should be: for (int i = b.y1(); i <= b.y2(); i ++) {
					for (int j = b.x1(); j <= b.x2(); j ++) { 	//should be: for (int j = b.x1(); j <= b.x2(); j ++) {
						Tray[i][j] = true;
					}
				}
			}
			setRank();
		}

		/* initStateNode
		 * @param String initFileName
		 * initializes the myInit stateNode using the file name from command prompt
		 * initializes numRows and numCols
		 */
		public void initStateNode(String initFileName) {
				try {
					FileInputStream fstream;
					fstream = new FileInputStream(initFileName);
					DataInputStream in = new DataInputStream(fstream);
					BufferedReader br = new BufferedReader(new InputStreamReader(in));

					String strLine;
					try {
						strLine = br.readLine();
						String[] arr = strLine.split(" ");
						numRows = Integer.parseInt(arr[0]);
						numCols = Integer.parseInt(arr[1]);
						myState = new ArrayList<int[]>();
						while ((strLine = br.readLine()) != null)  {
							arr = strLine.split(" ");
							int intArr[] = new int[4];
							for (int i = 0; i < 4; i ++) {
								intArr[i] = Integer.parseInt(arr[i]);
							}
							myState.add(intArr);
							myBlocks.add(new Block(intArr[0], intArr[1], intArr[2], intArr[3]));
						}
						br.close();
						Tray = new boolean[numRows][numCols];
						for (Block b: myInit.myBlocks) {
							for (int i = b.y1(); i <= b.y2(); i ++) {
								for (int j = b.x1(); j <= b.x2(); j ++) {
									Tray[i][j] = true;
								}
							}
						}
						setRank();
					} catch (Exception e) {}
				}
				catch (FileNotFoundException e) {
					System.out.println("Invalid init and/or goal file.");
					System.exit(0);
				}
			}

		/* goalStateNode
		 * @param String goalFileName
		 * initializes the myGoal stateNode using the file name from command prompt
		 */
		public void goalStateNode(String goalFileName) {
			FileInputStream fstream;
			try {
				fstream = new FileInputStream(goalFileName);
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;
				String[] arr;
				myState = new ArrayList<int[]>();
				try {
					while ((strLine = br.readLine()) != null)  {
						arr = strLine.split(" ");
						int intArr[] = new int[4];
						for (int i = 0; i < 4; i ++) {
							intArr[i] = Integer.parseInt(arr[i]);
						}
						myState.add(intArr);
						myBlocks.add(new Block(intArr[0], intArr[1], intArr[2], intArr[3]));
						//setRank();
					}
					br.close();
					Tray = new boolean[numRows][numCols];
					for (Block b: myGoal.myBlocks) {
						for (int i = b.y1(); i <= b.y2(); i ++) {
							for (int j = b.x1(); j <= b.x2(); j ++) {
								Tray[i][j] = true;
							}
						}
					}
					setRank();
				} catch (Exception e) {}
			} catch (FileNotFoundException e) {
				System.out.println("Invalid init and/or goal file.");
			}

		}

		public List<int[]> possibleMoves() {
			List<int[]> myPossibleMoves = new ArrayList<int[]>();
			for (Block b : blocks()) {
				if ((b.x2() + 1 < numCols)) {
					boolean canMove = true;
					for (int i = b.y1(); i <= b.y2(); i ++) {
						if (Tray[i][b.x2() + 1]) {
							canMove = false;
						}
					}
					if (canMove) {
						int[] move = {b.y1(), b.x1(), b.y1(), b.x1() + 1};
						myPossibleMoves.add(move);
					}
				}
				if ((b.y2() + 1 < numRows)) {
					boolean canMove = true;
					for (int i = b.x1(); i <= b.x2(); i++) {
						if (Tray[b.y2() + 1][i]) {
							canMove = false;
						}
					}
					if (canMove) {
						int[] move = {b.y1(), b.x1(), b.y1() + 1, b.x1()};
						myPossibleMoves.add(move);
					}
				}
				if ((b.x1() - 1 >= 0)) {
					boolean canMove = true;
					for (int i = b.y1(); i <= b.y2(); i++) {
						if (Tray[i][b.x1() - 1]) {
							canMove = false;
						}
					}
					if (canMove) {
						int[] move = {b.y1(), b.x1(), b.y1(), b.x1() - 1};
						myPossibleMoves.add(move);
					}
				}

				if ((b.y1() - 1 >= 0)) {
					boolean canMove = true;
					for (int i = b.x1(); i <= b.x2(); i++) {
						if (Tray[b.y1() - 1][i]) {
							canMove = false;
						}
					}
					if (canMove) {
						int[] move = {b.y1(), b.x1(), b.y1() - 1, b.x1()};
						myPossibleMoves.add(move);
					}
				}
			}
			return myPossibleMoves;
		}

		/*blocks
		 *returns list of blocks belonging to the stateNode
		 */
		public List<Block> blocks() {
			return myBlocks;
		}

		/*generateChildren
		 *initializes and adds children to the myChildren List, adds children for each child,...
		 */
		public void generateChildren() {
			for (int[] move : possibleMoves()) {
				stateNode newChild = new stateNode(this, move);
				if (!nodesSeen.contains(newChild)) {
					nodesSeen.add(newChild);
					myChildren.add(newChild);
				}
			}
		}

		@Override
		public int compareTo (Object o) {
			stateNode other = (stateNode) o;
			if (rank < other.rank) {
				return -1;
			}
			else if (rank > other.rank) {
				return 1;
			}
			return 0;
		}

		/*setRank
		 * determines rank of a stateNode based on the proximity of its blocks to
		 * their corresponding blocks in the goalNode
		 */
		public void setRank() {
			for (Block a : myBlocks) {
				int height = a.height();
				int width = a.width();
				for (Block b : myGoal.myBlocks) {
					if (height == b.height() && width == b.width()) {
						rank += distance(a, b);
					}
				}
			}
		}


		public int distance (Block b1, Block b2) {
			int first = (int) Math.abs(Math.pow(
					b1.x1() - b2.x1(), 2));
			int next = (int) Math.abs(Math.pow(
					b1.y1() - b2.y1(), 2));
			return (int) Math.sqrt(first + next);
		}

		/*hashCode
		 *for nodesSeen HashSet
		 */
		@Override
		public int hashCode() {
			int hashCode = 1;
			   for (int i = 0; i < myState.size(); i ++) {
			     for (int j = 0; j < 4; j ++) {
			       hashCode += hashCode*7*myState.get(i)[j];
			     }
			   }
			return hashCode;
		}

		/*equals
		 * @param stateNode other
		 * checks the myState List of arrays is the same for two stateNodes
		 */
		@Override
		public boolean equals(Object o) {
			stateNode other = (stateNode) o;
			if (myState.size() != other.myState.size()) {
				return false;
			}
//			myState.get(i).equals(other.myState.get(i))
			int counter = 0;
			for (int i = 0; i<myState.size(); i++) {
				if (Arrays.equals(myState.get(i), other.myState.get(i))) {
					counter++;
				}
			}
//			int i = 0;
//			for (int[] row1 : myState) {
//				for (int[] row2 : other.myState) {
//					if (Arrays.equals(row1, row2)) {
//						i ++;
//					}
//				}
//			}
			if (counter == myState.size()) {
				return true;
			}
			return false;
		}

		/*contains
		 * @param stateNode other
		 * checks to see if the arrays in other's myState List are contained in this myState List
		 * necessary because the goal file might only specify where one block needs to be
		 */
		public boolean contains (stateNode other) {
			int i = 0;
			for (int[] row1 : myState) {
				for (int[] row2 : other.myState) {
					if (Arrays.equals(row1, row2)) {
						i ++;
					}
				}
			}

			if (i == other.myState.size()) {
				return true;
			}
			return false;
		}

		/*moves
		 * returns the moves stored by a stateNode (includes all moves from the beginning)
		 */
		public Queue<int[]> moves() {
			return myMoves;
		}

		/*printMoves
		 *prints all moves
		 *called when the puzzle is solved
		 */
		public void printMoves() {
			while (!myMoves.isEmpty()) {
				int[] move = myMoves.poll();
				System.out.println(move[0] + " " + move[1] + " " + move[2] + " " + move[3]);
			}
		}

		/*printMyState
		 * for testing, prints the array that shows the state of the puzzle board
		 */
		public void printMyState() {
			for (int[] array : myState) {
				for (int x : array) {
					System.out.print(x + " ");
				}
				System.out.println();
			}
		}
	}

	public static void main (String[] args) {
		if (args.length == 2 && validFile(args[0]) && validFile(args[1])) {
			Solver s = new Solver(args[0], args[1]);
			s.solve();
			return;
		}
		System.out.println("Invalid init and/or goal file.");


	}

}
