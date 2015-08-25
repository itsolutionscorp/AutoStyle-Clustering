import java.util.Stack;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * Our main class. Solves the puzzle if possible and returns the needed moves.
 */
public class Solver {
	HashSet<Tray> visited;
	PriorityQueue<Tray> fringe;
	Tray initialConfig;
	int[][] Initial;
	int[][] goal;
	boolean allGood;
	int width;
	int length;
	int blockNumber;
  
  /*
   * Constructor.
   * Basically just instantiates Instance Variables.
   */
	public Solver() {
		allGood = true;
		visited = new HashSet<Tray>();
		Comparator<Tray> comparator = new TrayComparator();
		fringe = new PriorityQueue<Tray>(10, comparator);
	}
	
	/*
	 * Main function. Reads inputs and finds solution.
	 * First checks to make sure the inputs are viable, and then operates.
	 * If the destination cannot be reached, ends with no return value.
	 * This was helpful for reading the text files :
	 * 		http://stackoverflow.com/questions/2788080/reading-a-text-file-in-java
	 * 
	 * @param args
	 * 		this is parsed to filenames that are read into an initial state and a goal state
	 */
	public static void main (String[] args) {
		Solver mine = new Solver();
		if (args.length == 2) {
			try {
				mine.makeInit(args[0]);
				mine.makeGoal(args[1]);
				mine.isGood();
				mine.Go();
			} catch (IOException e) {
				System.out.println("Failed to Initialize. Error : " + e.getMessage());
				mine.allGood = false;
			}
		} 
		if (args.length != 2){
			mine.allGood = false;
		}
		if (mine.allGood == false) {
			System.out.println("Invalid init and/or goal file");
		} 
		else {
			if (mine.check(mine.initialConfig)) {
				return;
			}
			mine.fringe.offer(mine.initialConfig);
			mine.allPossibleMoves();
		}
	}
	
	/*
	 * Further initializes.
	 */
	public void Go() {
		initialConfig = new Tray();
		initialConfig.trayMatrix = Initial;
		initialConfig.setConfig();
		visited.add(initialConfig);
	}
	
	/*
	 * Read the first fileName given and parses into an 2D array (Initial)
	 *   if possible.
	 * @param String
	 * 		FileName of init file.
	 * @throws IOException
	 * 		if the file is incorrect/things don't work.
	 */
	public void makeInit(String FileName) throws IOException {
		blockNumber = Files.readAllLines(Paths.get(FileName), StandardCharsets.UTF_8).size() - 1;
		Initial = new int[blockNumber][4];
		int k = 0;
		for (String line : Files.readAllLines(Paths.get(FileName), StandardCharsets.UTF_8)) {
			if (k == 0) {
				length = Integer.valueOf(line.split("\\s+")[0]);
				width = Integer.valueOf(line.split("\\s+")[1]);
	    	} else {
	    		int i = 0;
	    		for (String part : line.split("\\s+")) {
	    			Initial[k - 1][i] = Integer.valueOf(part);
			        i += 1;
	    		}
	    	}
			k++;
		}
	}
	
	/*
	 * Reads the second fileName given and parses into 2D array (goal)
	 *   if possible.
	 * @param String
	 * 		FileName of goal file.
	 * @throws IOException
	 * 		if things don't work/bad input.
	 */
	public void makeGoal(String FileName) throws IOException {
		goal = new int[Files.readAllLines(Paths.get(FileName), StandardCharsets.UTF_8).size()][4];
		int k = 0;
		for (String line : Files.readAllLines(Paths.get(FileName), StandardCharsets.UTF_8)) {
	    	int i = 0;
	    	for (String part : line.split("\\s+")) {
	    		goal[k][i] = Integer.valueOf(part);
			    i += 1;
	    	}
			k++;
		}
	}
	
	/*
	 * Checks to see if the goal and Initial trays are legal configurations.
	 */
	public void isGood() {
		if (length > 256 || width > 256 || goal.length > blockNumber) {
			allGood = false;
			return;
		}
		Tray goalTray = new Tray();
		goalTray.trayMatrix = goal;
		goalTray.setConfig();
	}
	
	/*
	 * Prints out all the moves to this Tray. The moves are stored in each Tray class,
	 *   and are accessed back through each Tray's parent. They are put into a stack and
	 *   then printed out so they come in the right order.
	 * @param Tray
	 * 		the last tray, or our goal state.
	 */
	public void printMoves(Tray last){
		Tray tray = last;
		Stack<int[]> stack = new Stack<int[]>();
		while(tray.myParent != null){
			stack.push(tray.move);
			tray = tray.myParent;
		}
		while( !stack.isEmpty()){
			int[] move = stack.pop();
			for(int i = 0; i<4 ; i++){
				System.out.print(move[i]+" ");
			}
			System.out.println("");
		}
	}
	
	/*
	 * Determines if the current tray(t) is at the goal state.
	 * @param Tray
	 * 		the current Tray under suspicion
	 */
	public boolean check(Tray t){
		int isThere;
		isThere = 0;
		for (int[] x : goal) {
			for (int[] y : t.trayMatrix) {
				if (Arrays.equals(x, y)) {
					isThere += 1;
					break;
				}
			}
		}
		if (isThere == goal.length) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Executes a move and adds it to the fringe if not already contained. Also determines
	 *   the distance from the goal, which is our priority when searching.
	 * @param Tray
	 * 		The parent tray this move is executed on.
	 * @param int[]
	 * 		The 4 length move array that represents the move we do.
	 */
	public boolean move(Tray parent, int[] moveArray ){
		Tray newTray = new Tray(parent);
		for (int i = 0; i < parent.trayMatrix.length ; i++) {
			for (int j = 0; j < 4 ; j++) {
				newTray.trayMatrix[i][j] = parent.trayMatrix[i][j];
			}
			if (parent.trayMatrix[i][0] == moveArray[0] && parent.trayMatrix[i][1] == moveArray[1]) {
				newTray.trayMatrix[i][2] = newTray.trayMatrix[i][2]+ moveArray[2] - moveArray[0];
				newTray.trayMatrix[i][3] = newTray.trayMatrix[i][3]+ moveArray[3] - moveArray[1];
				newTray.trayMatrix[i][0] = moveArray[2];
				newTray.trayMatrix[i][1] = moveArray[3];
				
			}
		}
		newTray.setConfig();
		if (!visited.contains(newTray)) {
			newTray.move = moveArray;
			int distance = 0;
			for (int i = 0; i < goal.length; i++) {
				int lengthGoal = goal[i][3] - goal[i][1];
				int widthGoal = goal[i][2] - goal[i][0];
				for (int j = 0; j < newTray.trayMatrix.length; j++) {
					int lengthNewTray = newTray.trayMatrix[i][3] - newTray.trayMatrix[i][1];
					int widthNewTray = newTray.trayMatrix[i][2] - newTray.trayMatrix[i][0];
					if (lengthGoal == lengthNewTray && widthGoal == widthNewTray) {
						distance += Math.abs(goal[i][0] - newTray.trayMatrix[i][0]) + Math.abs(goal[i][1] - newTray.trayMatrix[i][1]);
					}
				}
			}
			newTray.distanceFromGoal = distance;
			visited.add(newTray);
			if (check(newTray)) {
				printMoves(newTray);
				return true;
			}
			fringe.offer(newTray);
		}
		return false;
	}
	
	/*
	 * Finds all possible moves up. If each move is a legal move, it constructs a
	 *   move array and calls 'Move' on that array.
	 * @param Tray
	 * 		The current tray we are looking at.
	 */
   public boolean allPossibleMovesUp(Tray t) {
		for (int i = 0; i < t.trayMatrix.length; i++) {
			int initialX = t.trayMatrix[i][0];
			int initialY = t.trayMatrix[i][1];
			int width = t.trayMatrix[i][3] - t.trayMatrix[i][1] + 1;
			// moves up if possible
			if (initialX != 0) {
				PieceMove:
				for (int j = initialX -1 ; j >= 0; j--) {
					for (int k = initialY; k < initialY + width; k++) {			
						if (t.myConfig[j][k]) {
							break PieceMove;
						}
					}
					int[] newMove = new int[4];
					newMove[0] = initialX ;
					newMove[1] = initialY;
					newMove[2] = j;
					newMove[3] = initialY;
					if (move(t, newMove)) {
						return true;
					}					
				}
			}
		}
		return false;
	}
	
   /*
    * Finds all possible moves down. If each move is a legal move, it constructs a
	*   move array and calls 'Move' on that array.
	* @param Tray
	* 		The current tray we are looking at.
    */
	public boolean allPossibleMovesDown(Tray t) {
		for (int i = 0; i < t.trayMatrix.length; i++) {
			int length = t.trayMatrix[i][2] - t.trayMatrix[i][0] + 1;
			int bottomX = t.trayMatrix[i][0] + length -1;
			int bottomY = t.trayMatrix[i][1];
			int width = t.trayMatrix[i][3] - t.trayMatrix[i][1] + 1;
			// moves down if possible
			if (bottomX != t.myConfig.length - 1) {
				PieceMove:
				for (int j = 1; bottomX+j < t.myConfig.length; j++) {
					for (int k = bottomY; k < bottomY + width; k++) {
						if (t.myConfig[bottomX+j][k]) {
							break PieceMove;
						}
					}	
					int[] newMove = new int[4];
					newMove[0] = t.trayMatrix[i][0];
					newMove[1] = t.trayMatrix[i][1];
					newMove[2] = t.trayMatrix[i][0]+j;
					newMove[3] = t.trayMatrix[i][1];
					if (move(t, newMove)) {
						return true;
					}					
				}
			}
		}
		return false;
	}
	
   /*
	* Finds all possible moves left. If each move is a legal move, it constructs a
	*   move array and calls 'Move' on that array.
	* @param Tray
	* 		The current tray we are looking at.
	*/
	public boolean allPossibleMovesLeft(Tray t) {
		for (int i = 0; i < t.trayMatrix.length; i++) {
			int length = t.trayMatrix[i][2] - t.trayMatrix[i][0] + 1;
			int leftX = t.trayMatrix[i][0];
			int leftY = t.trayMatrix[i][1];
			// moves left if possible
			if (leftY != 0) {	
				PieceMove:
				for (int j = leftY-1; j >= 0; j--) {
					for (int k = leftX; k < leftX + length; k++) {
						if (t.myConfig[k][j]) {
							break PieceMove;
						}
					}
					int[] newMove = new int[4];
					newMove[0] = t.trayMatrix[i][0];
					newMove[1] = t.trayMatrix[i][1];
					newMove[2] = t.trayMatrix[i][0];
					newMove[3] = j;
					if (move(t, newMove)) {
						return true;
					}				
				}
			}
		}
		return false;
	}
	
	/*
	 * Finds all possible moves right. If each move is a legal move, it constructs a
	 *   move array and calls 'Move' on that array.
	 * @param Tray
	 * 		The current tray we are looking at.
	 */
	public boolean allPossibleMovesRight(Tray t) {
		for (int i = 0; i < t.trayMatrix.length; i++) {
			int length = t.trayMatrix[i][2] - t.trayMatrix[i][0] + 1;
			int width = t.trayMatrix[i][3] - t.trayMatrix[i][1] + 1;
			int rightX = t.trayMatrix[i][0];
			int rightY = t.trayMatrix[i][1] + width -1;			
			// moves right if possible
			if (rightY != t.myConfig[0].length - 1) {
				PieceMove:
				for (int j = 1; rightY+j < t.myConfig[0].length; j++) {
					for (int k = rightX; k < rightX + length; k++) {					
						if (t.myConfig[k][rightY+j]) {
							break PieceMove;
						}					
					}
					int[] newMove = new int[4];
					newMove[0] = t.trayMatrix[i][0];
					newMove[1] = t.trayMatrix[i][1];
					newMove[2] = t.trayMatrix[i][0];
					newMove[3] = t.trayMatrix[i][1] + j;
					if (move(t, newMove)) {
						return true;
					}				
				}
			}
		}
		return false;
	}
	
	/*
	 * Our main computational function. It continues as long as the goal state has not been
	 *   reached(which is terminated in Move), and the fringe(which contains Trays we
	 *   have yet to see) is not empty.
	 */
	public void allPossibleMoves() {
		while (!fringe.isEmpty()) {
			Tray currTray = fringe.poll();
			if (allPossibleMovesUp(currTray)) {
				return;
			}
			if (allPossibleMovesDown(currTray)) {
				return;
			}
			if (allPossibleMovesLeft(currTray)) {
				return;
			}
			if (allPossibleMovesRight(currTray)) {
				return;
			}
		}
	}	
	
	/*
	 * Our main data class. Thinking along the lines of a linked-list, this would be
	 *   a node in the list.
	 */
	private class Tray {
		private int[][] trayMatrix;
		private boolean[][] myConfig;
		private Tray myParent;
		private int[] move;
		private int distanceFromGoal;
		
		/*
		 * Constructor.
		 * Constructs.
		 */
		public Tray(){
			trayMatrix = new int[blockNumber][4];
			myConfig = new boolean[length][width];
			myParent = null;
			move = new int[]{0,0,0,0};
			distanceFromGoal = Integer.MAX_VALUE;
		}
		
		/*
		 * Constructor with parent.
		 * Constructs with parent.
		 */
		public Tray(Tray parent){
			trayMatrix = new int[blockNumber][4];
			myConfig = new boolean[length][width];
			myParent = parent;
			move = new int[4];
			distanceFromGoal = Integer.MAX_VALUE;
		}
		
		@Override
		public int hashCode() {
			return java.util.Arrays.deepHashCode(myConfig);
		}

		@Override
		public boolean equals(Object t){
			for (int  k=0 ; k < trayMatrix.length ; k++){
				boolean contains = false;
				for( int[] row:  ((Tray) t).trayMatrix){
					if(Arrays.equals(trayMatrix[k], row)){
						contains = true;
						break;
					}
				}
				if(contains == false){
					return false;
				}
			}
			return true;
		}
		
		/*
		 * Sets out myConfig boolean matrix. This is basically a representation of the
		 *   actual tray, instead of just block ID's.
		 */
		public void setConfig(){
			for (int i = 0; i < trayMatrix.length; i++) {
				int endX = trayMatrix[i][2];
				int endY = trayMatrix[i][3];				
				for (int startX = trayMatrix[i][0]; startX <= endX; startX++) {					
					for (int startY = trayMatrix[i][1]; startY <= endY; startY ++) {
						if (myConfig[startX][startY]) {
							allGood = false;
							return;
						} else {
							myConfig[startX][startY] = true;
						}
					}
				}
			}
		}		
	}
	
	/*
	 * Comparator for priotityQueue stuff.
	 * The compare compares distances from the goal.
	 */
	private class TrayComparator implements Comparator<Tray> {
		
		@Override
		public int compare(Tray t1, Tray t2) {
			if (t1.distanceFromGoal < t2.distanceFromGoal) {
				return -1;
			}
			if (t1.distanceFromGoal > t2.distanceFromGoal) {
				return 1;
			}
			return 0;
		}
	}

}