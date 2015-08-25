
//package solver;
// I love life third
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;


public class Solver {
	
	
	public static void main (String[] args) {

		try {
			parseArgs(args);
		}
		catch (ArrayIndexOutOfBoundsException e) {
	//		System.out.println("a"); // remove this once done
			System.out.println("Invalid init and/or goal file");
		}
		catch (IOException e1) {
			return;
		}
		catch (IllegalArgumentException e2) {
		//	System.out.println("b"); // remove this once done
			System.out.println("Invalid init and/or goal file");
		}
 
	}
	
	public static void parseArgs (String[] args) throws IndexOutOfBoundsException,IOException {
		String init = args[0];
		String goal = args[1];
		String current =  new java.io.File(".").getCanonicalPath();  // Gets the path to the project not the package
		Path initFile = Paths.get(current, init);
		Path goalFile = Paths.get(current, goal);
		if (!Files.exists(initFile) || !Files.exists(goalFile)) {
			throw new IllegalArgumentException();
		}
	    Solver gameSolver = new Solver();
	    try {
	    	gameSolver.processGoalandInit(initFile,goalFile);  // should look after exceptions etc
	    }
	    catch (IOException e) {
	    }
		
		
	}
	
	
	private void processGoalandInit(Path initFile, Path goalFile) throws IOException  { // maybe move this to trays and blocks
		Scanner initial = new Scanner(initFile); // line that throws IO Exception
		String dimensions = initial.nextLine();
		Scanner dim = new Scanner(dimensions);
		Integer height = dim.nextInt();
		Integer width = dim.nextInt();
		Tray start = new Tray(height,width);
		Scanner block;
		// Adding the blocks to the initial tray configuration
		LinkedList<Block> p = new LinkedList<Block>();		//EDITED BY NANCY
		while (initial.hasNextLine()) {
			block = new Scanner(initial.nextLine());
			int[] blockArr = new int[4];
			for (int i=0; block.hasNextInt(); i++) {
				blockArr[i] = block.nextInt();
			}
			Block blick = new Block(blockArr);
			start.addBlock(blick);
			p.add(blick);
		}
		start.bigX = Block.count;																												
		/*while(p != null) {
			start.addBlock(p.poll());
		} */
		Scanner goal = new Scanner(goalFile); // line that throws IO Exception
		ArrayList<Block> goalBlocks = new ArrayList<Block>();
		while (goal.hasNextLine()) {
			block = new Scanner(goal.nextLine());
			int[] blockArr = new int[4];
			for (int i=0; block.hasNextInt(); i++) {
				blockArr[i] = block.nextInt();
			}
			Block blick = new Block(blockArr);
			goalBlocks.add(blick);
		}
		start.addGoal(goalBlocks);
		start.addEmptySpaces(); // to add in all the empty spaces.
		try {
			LinkedList<move> output = start.go();
			processOutput(output);
		}
		catch (IllegalArgumentException e) {
			return;
		}
		
	}
	
private void processOutput(LinkedList<move> output) {
		if (output == null){
			return;
		}
		else {
			for (move item:output) {
				System.out.println(item);
			}
		}
	}

public static class Block { // change the implementation to just contain the height and width
	
	
	static int count = 0;
	private int[] BlockCoords;
	protected int height;
	protected int width;
	int personalCount;
	static HashMap<Block,Integer> myBlocks = new HashMap<Block,Integer>();				//EDITED BY NANCY
	static HashMap<Integer, Block> myMap = new HashMap<Integer, Block>();
	
	public Block(int heights, int widths) {
		height = heights;
		width = widths;
		if (myBlocks.get(this) != null) {				//EDITED BY NANCY
			personalCount = myBlocks.get(this);
		} else {
			personalCount = count;
			myBlocks.put(this, count);
			myMap.put(count, this);
		}
			count++;
	}
	
	public Block(int[] arr) {
		BlockCoords = arr;
		height = Math.abs(arr[2] - arr[0]);
		width = Math.abs(arr[3] - arr[1]);
		if (myBlocks.get(this) != null) {						//EDITED BY NANCY
			personalCount = myBlocks.get(this);
		} else {
			personalCount = count;
			myBlocks.put(this, count);
			myMap.put(count, this);
		}
			count++;
	}
	
	public int hashCode() {							//EDITED BY NANCY
		return personalCount;
	}
	
	public boolean equals(Object obj) {
		return (obj!= null && this.height() == ((Block)obj).height() && this.width() == ((Block)obj).width());
	}
	
	public Object clone() { // check this works. JUNIT TEST TO SEE IF IT ACTUALLY CLONES.
		return new Block(height,width);
	}
	
	
	public int height() {
		return height;
	}
	
	public int width() {
		return width;
	}
	

	public boolean isEmpty() {
		return false;
	}
	
	public int[] getCoords() {
		return BlockCoords;
	}
	// method not needed i think
	public int getCode() { 
		int x = 20;
		return (height()*x +  width()*(int)(Math.pow(x, 2)) + 1);
	}
	
	public int getCoordinate() {
		return ( (BlockCoords[1]*10) + BlockCoords[0]);
	}
	
}

public class Tray implements Iterable {

	public Block[][] initial;
	public Block[][] goalConfig;																					//EDITED BY NANCY
	double code;
	int bigX;
	double codeEmpty;
	int numEmpty;
	int height;
	int width;
	int numBlocks;
	
	public Tray (int w, int h) {
		width =  w;
		height = h;
		bigX = 0;
		initial = new Block[w][h];
		goalConfig = new Block[w][h]; ;																				
		code = 0;
		codeEmpty = 0;
		numBlocks = 0;
	}
	
	private Block[][] getInitial() {
		return initial;
	}
																										
	public void addGoal(ArrayList<Block> goals) { 																							//EDITED BY NANCY
		for (Block goalie : goals) {
			int[] corde = goalie.getCoords();
			for (int i = corde[0]; i <= corde[2]; i++) {
				for (int j = corde[1]; j <= corde[3] ;j++) {
					goalConfig[i][j] = goalie;
				}
			}
		}
		
	}
	

	
	public void addBlock (Block blick) {
		numBlocks++;
		int[] corde = blick.BlockCoords;
		for (int i = corde[0]; i <= corde[2]; i++) {
			for (int j = corde[1]; j <= corde[3] ;j++) {
				initial[i][j] = blick;
			}
		}
		code = code + blick.hashCode()*Math.pow(bigX, blick.getCoords()[0]*width+blick.getCoords()[1]);
  	}
  	
	
	public boolean reachGoal(miniTray little) {
		for (int i = 0; i< goalConfig.length;i++) {
			for (int j=0;j< goalConfig[i].length;j++) {
				if (goalConfig[i][j] != null)  { //&& getBlockGoal(i, j) 
					if (little.getConfig()[i][j] == null) {
						return false;
					}
					else if (goalConfig[i][j].getCode()!= (little.getConfig()[i][j]).getCode()) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	
	
	public void addEmptySpaces() {
		int counter =  0;
		for (int i = 0; i < initial.length;i ++) {
			for (int j = 0; j < initial[i].length; j ++) {
				if (initial[i][j] == null) {
					int coordinate = (j*width) + i;
					codeEmpty += (int) Math.pow(bigX, counter)*coordinate;
					numEmpty ++;
					counter ++;
				}
			}
		}
	}
	
	public LinkedList<move> go() {
		miniTray start = new miniTray(this);
		if (reachGoal(start)) {
			return null;
		}
		 Iterator<miniTray> iter = this.iterator();
		 miniTray currConfig = iter.next();
	     while (!reachGoal(currConfig) && iter.hasNext()) {
	        		currConfig = iter.next();
	        }
	     if (reachGoal(currConfig)) {
	        return currConfig.getPath();
	        }
	     else {
	        return null;
	        }
	}
/*	
	public boolean equalConfigs(Block[][] first, Block[][] second) {
		for (int i =0; i < first.length; i++) {
			for (int j=0; j < first[0].length;j ++) {
				if (first[i][j] == null) {
					System.out.println(i+","+ j);
				}
				else if (first[i][j] != null && !first[i][j].isEmpty() && first[i][j] != second[i][j]) { 
					return false;
				}
			}
		}
		return true;
	}
	*/
	 public Iterator<miniTray> iterator(){
	        return new BFSIterator(this);
	    }
	 
	 
private class BFSIterator implements Iterator<miniTray> {
		 	
		 	HashSet<Double> visited;																						//EDITED BY NANCY
	        private Queue<miniTray> fringe; 
	        
	        //MUST EDIT
	        public BFSIterator(Tray start) {
	            fringe = new LinkedList<miniTray>();					//later change to priority queue
	            fringe.add(new miniTray(start));
	            visited = new HashSet<Double>();
	            visited.add(fringe.peek().ConfigCode);
	        }  

	        public boolean hasNext() {
				return !fringe.isEmpty();
	        }
	        
	        public miniTray next() {
	        	miniTray parent = fringe.poll();
	        	ArrayList<move> nextMoves= parent.getLegalMoves();
				for (move moved: nextMoves) {
					miniTray child = new miniTray(parent, moved);
					if (!visited.contains(child.ConfigCode) ) {										//EDITED BY NANCY
						visited.add(child.ConfigCode);
						fringe.add(child);
					}
        		}
		        return parent;
	        }

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}

	    }
	 /*
	private class miniTrayMovePair {
		private miniTray trayKeeper;
		private move moveKeeper;
		
		public miniTrayMovePair(miniTray trays, move moves) {
			trayKeeper = trays;
			moveKeeper = moves;
		}
		
		public miniTray getTray() {
			return trayKeeper;
		}
		
		public move getMove() {
			return moveKeeper;
		}
		
		public boolean equals(Object obj) {
			return (obj != null /* && trayKeeper.equals(((miniTrayMovePair)obj).getTray()) && moveKeeper.equals(((miniTrayMovePair)obj).getMove()));
		}
	}
*/
	 
	public class miniTray {
		
		Block[][] Config; 
		LinkedList<move> path; 
		double ConfigCode;
		int X;
		int emptyCount;
		double emptyCode;
		int blockNum;
		int height;
		int width;
		
		public miniTray(Tray curr) {
			height = curr.height;
			width = curr.width;
			Block[][] temp = curr.getInitial();
			Config = cloneConfig(temp);
			path = new LinkedList<move>();
			ConfigCode = curr.code;
			X = (height*width) + 1;
			emptyCount = curr.numEmpty;
			emptyCode = curr.codeEmpty;
			blockNum = curr.numBlocks;
		}

		public miniTray(miniTray Prev, move nextMove) {
			Config = Prev.cloneConfig();
			X = Prev.X;
			height = Prev.height;
			width = Prev.width;
			path = (LinkedList<move>) Prev.getPath().clone();
			path.addLast(nextMove);
			mover(nextMove);
			ConfigCode = updateConfigEmpty(Prev.ConfigCode, nextMove);
			emptyCount = Prev.emptyCount;
			emptyCode = Prev.emptyCode;
			blockNum = Prev.blockNum;
		}
		
		public ArrayList<int[]> getEmptys() {
			ArrayList<int[]> empties = new ArrayList<int[]>();
			
			for (double vals = emptyCode ; vals > 0; vals = vals/X){
				empties.add(intToCoordinate(vals % X));
			}
			return empties;
		}
		
		public int[] intToCoordinate(double val) {
			int[] coords = new int[2];
			coords[0] = (int) val%height;
			coords[1] = (int) (val/height);
			return coords;
		}
		
		// SENT TO NANCY HERE
		public boolean equals(Object obj) {
			return ((ConfigCode==(((miniTray)obj).ConfigCode)));  /* && ((miniTray)obj).getPath().equals(path) */
		}
		
		
		 
		public double updateConfigEmpty(double currConfig, move made) {
			int [] coords = made.getMove();
			double toSub = (currConfig/Math.pow(X, coords[1]*width+coords[0]) % X)*Math.pow(X, (coords[1]*width)+coords[0]);
			double toAdd = (currConfig/Math.pow(X, coords[1]*width+coords[0]) % X)*Math.pow(X, (coords[3]*width)+coords[2]);
			currConfig = currConfig + toAdd - toSub;
			return currConfig;
		}
	
		// possible reason why empty blocks not updated/not working
		public void mover(move moveToMake) {
			int direction = moveToMake.dirOfMotion();
	//		System.out.println("direction of motion is:"+direction);
			Block toMove = getBlock(moveToMake.initial()[0], moveToMake.initial()[1]);
			int xPos = moveToMake.initial()[0];
			int yPos = moveToMake.initial()[1];
			if (direction == 1) {
				int xPos1 = xPos;
				while (xPos1 < (xPos + toMove.width())) {
					Block emp = getBlock(xPos1, yPos + toMove.height());
					Config[xPos1][yPos] = emp;
					// EMPTY COUNT
					int prevCoordinate = ((yPos + toMove.height())*width) + xPos1;
					int newCoordinate = xPos1 + (width*yPos);
					int empty = getEmptyID(codeEmpty, prevCoordinate);
					int toAdd =(int)Math.pow(X,empty)*newCoordinate;
					int toSubtract = (int)Math.pow(X,empty)*prevCoordinate;
					emptyCode = emptyCode + toAdd - toSubtract;
					//
					Config[xPos1][yPos + toMove.height()] = toMove;
					xPos1 ++;
				}
			}
			else if (direction == 2) {
				int xPos2 = xPos;
				while (xPos2 < (xPos + toMove.width())) {
					Block emp = getBlock(xPos2, yPos - 1);
					// Updating emptyCount
					int currCoordinate = ((yPos-1)*width) + xPos2;
					int newCoordinate = xPos2 + (width*(yPos + toMove.height() -1));
					int empty = getEmptyID(codeEmpty, currCoordinate);
					int toAdd =(int)Math.pow(X,empty)*newCoordinate;
					int toSubtract = (int)Math.pow(X,empty)*currCoordinate; 
					emptyCode = emptyCode + toAdd - toSubtract;
					//
					Config[xPos2][yPos + toMove.height() -1] = emp;
					Config[xPos2][yPos-1] = toMove;
					xPos2 ++;
				}

			}
			else if (direction == 3) {
				int yPos3 = yPos;
				while (yPos3 < (yPos + toMove.height()) ) {
					Block emp = getBlock(xPos - 1, yPos3);
					// Updating emptyCount
					int currCoordinate = xPos-1 + (yPos3*width);
					int newCoordinate = (xPos + toMove.width()-1) + (width*yPos3);
					int empty = getEmptyID(codeEmpty, currCoordinate);
					int toAdd = (int)Math.pow(X,empty)*newCoordinate;
					int toSubtract = (int) Math.pow(X,empty)*currCoordinate;
					emptyCode = emptyCode + toAdd - toSubtract;
					//
					Config[xPos + toMove.width() -1][yPos3] = emp;
					Config[xPos -1][yPos3] = toMove;
					yPos3 ++;
				}
			}
			else if (direction == 4) {
				int yPos4 = yPos;
				while (yPos4 < (yPos + toMove.height()) ) {
					Block emp = getBlock(xPos + toMove.width(), yPos4);
					// Updating emptyCount
					int currCoordinate = xPos + toMove.width() + (yPos4*width);
					int newCoordinate = xPos + (width*yPos4);
					int empty = getEmptyID(codeEmpty, currCoordinate);
					int toAdd = (int)Math.pow(X,empty)*newCoordinate;
					int toSubtract = (int) Math.pow(X,empty)*currCoordinate;
					emptyCode = emptyCode + toAdd - toSubtract;
					//
					Config[xPos][yPos4] = emp;
					Config[xPos + toMove.width()][yPos4] = toMove;
					yPos4 ++;
				}
			}
			else {
			}
			
		}
		
		private int getEmptyID(double codeEmpty, int coordinate) {
			int iD = 0;
			double val = codeEmpty;
			while(codeEmpty % X != coordinate) {
				iD++;
				val = val/X;
			}
			return iD;
		}

		public LinkedList<move> getPath() {
			return path;
		}
	
		
		public Block[][] cloneConfig(Block[][] initialConfig) {
			Block[][] replica = new Block[initialConfig.length][initialConfig[0].length];
			for (int i =0;i<initialConfig.length;i++) {
				for (int j= 0; j < initialConfig[i].length;j++) {
					replica[i][j] = initialConfig[i][j];
				}
			}
			return replica;
		}
		
		public Block[][] cloneConfig() {
			Block[][] replica = new Block[Config.length][Config[0].length];
			for (int i =0;i<Config.length;i++) {
				for (int j= 0; j < Config[i].length;j++) {
					if (Config[i][j] == null) {
			//			System.out.println("Why is there a null??!");   // remove this when done
					}
					else {
						replica[i][j] = Config[i][j];
					}
				}
			}
			return replica;
		}

		public Block[][] getConfig() {
			return Config;
		}
		
		public ArrayList<move> getLegalMoves() {
			ArrayList<move> allowedMoves = new ArrayList<move>();
			ArrayList<int[]> emptied = this.getEmptys(); // returns arrayList of coordinates of empty positions			
			for (int[] emp: emptied)  {
				ArrayList<move> possibleMove = getMovesHelp(emp);
				if (possibleMove != null || !possibleMove.isEmpty()) { // PSE: if nothing is added to an arrayList, is it empty or null?
					for (move potential: possibleMove) {
						if (!allowedMoves.contains(potential)) {
							allowedMoves.add(potential); 
						}
					}
				}
			}
			return allowedMoves;
		}
		
		public ArrayList<move> getMovesHelp(int[] empty) {
			ArrayList<move> possibleMoves = new ArrayList<move>();
			int[] emptyCoords = new int[2];
			emptyCoords[0] = empty[0];
			emptyCoords[1] = empty[1];
			Block mess = getTop(emptyCoords[0],emptyCoords[1]);
			if (mess != null) {
				move down = new move(getLeftTopMost(mess, moveUp(emptyCoords) ) ,moveDown(getLeftTopMost(mess, moveUp(emptyCoords)) ));
				if (canMove(mess,1,moveUp(emptyCoords)) ) {    // dir: 1 for moving down, 2 for up, 3 for left, 4 for right
					if (path.isEmpty()) {
						possibleMoves.add(down);
					}
					else if (!path.getLast().reverse(down)) {
						possibleMoves.add(down);
					}
				}
			}
			Block mess2 = getBottom(emptyCoords[0],emptyCoords[1]);
			if (mess2 != null) {
				move up = new move(getLeftTopMost(mess2, moveDown(emptyCoords) ),moveUp(getLeftTopMost(mess2, moveDown(emptyCoords)) ));
				if (canMove(mess2,2,moveDown(emptyCoords) ) ) {    // dir: 1 for moving down, 2 for up, 3 for left, 4 for right
					if (path.isEmpty()) {
						possibleMoves.add(up);
					}
					else if (!path.getLast().reverse(up)) {
						possibleMoves.add(up);
					}
				}
			}
			Block mess3 = getRight(emptyCoords[0],emptyCoords[1]);
			if (mess3 != null) {
		//		System.out.println( getLeftTopMost(mess3, moveRight(emptyCoords))[0] );
		//		System.out.println(moveLeft(getLeftTopMost(mess3, moveRight(emptyCoords)))[0]  );
				move left = new move( getLeftTopMost(mess3, moveRight(emptyCoords)) , moveLeft(getLeftTopMost(mess3, moveRight(emptyCoords))) );
				if (canMove(mess3,3,moveRight(emptyCoords)) ) {    // dir: 1 for moving down, 2 for up, 3 for left, 4 for right
					if (path.isEmpty()) {
						possibleMoves.add(left);
					}
					else if (!path.getLast().reverse(left)) {
						possibleMoves.add(left);
					}
				}
			}
			Block mess4 = getLeft(emptyCoords[0],emptyCoords[1]);
			if (mess4 != null) {
				move right = new move(getLeftTopMost( mess4,moveLeft(emptyCoords) ),moveRight(getLeftTopMost(mess4, moveLeft(emptyCoords) )));
				if (canMove(mess4,4,moveLeft(emptyCoords)) ) {    // dir: 1 for moving down, 2 for up, 3 for left, 4 for right
					if (path.isEmpty()) {
						possibleMoves.add(right);
					}
					else if (!path.getLast().reverse(right)) {
						possibleMoves.add(right);
					}
				}
			}
			return possibleMoves;
		}
		
		private int[] moveDown(int[] currCoords) {
			int[] newCoords = new int[2];
			newCoords[0] = currCoords[0];
			newCoords[1] = currCoords[1] +1;
			return newCoords;
		}
		
		private int[] moveUp(int[] currCoords) {
			int[] newCoords = new int[2];
			newCoords[0] = currCoords[0];
			newCoords[1] = currCoords[1] -1;
			return newCoords;
		}
		
		private int[] moveLeft(int[] currCoords) {
			int[] newCoords = new int[2];
			newCoords[0] = currCoords[0] - 1;
			newCoords[1] = currCoords[1];
			return newCoords;
		}
		
		private int[] moveRight(int[] currCoords){
			int[] newCoords = new int[2];
			newCoords[0] = currCoords[0] + 1;
			newCoords[1] = currCoords[1];
			return newCoords;
		}
		
		private int[] getLeftTopMost(Block blow, int[] currCoords) {
			int currX = currCoords[0];
			int currY = currCoords[1];
			while (withinBoundsX(currX) && withinBoundsY(currY) && initial[currX][currY] == blow) {
				currX --;
			}
			currX ++;
			while (withinBoundsX(currX) && withinBoundsY(currY) && initial[currX][currY] == blow) {
				currY --;
			}
			currY ++;
			int[] returnCoords = new int[2];
			returnCoords[0] = currX;
			returnCoords[1] = currY;
			return returnCoords;
		}
		
		private boolean withinBoundsX(int x) {
			return (x >= 0 && x < initial.length);
		}
		
		private boolean withinBoundsY(int y) {
			return (y >= 0 && y < initial[0].length);
		}
		
		
		private boolean canMove(Block mess, int dir, int[] currentCoords) { // dir: 1 for moving down, 2 for up, 3 for left, 4 for right
			int[] currCoords = getLeftTopMost(mess, currentCoords);
			int height = mess.height();
			int width = mess.width();
			if (dir == 1) {
				int xPos1 = currCoords[0];
				int yPos1 = currCoords[1] + height - 1;
				while (xPos1 < (currCoords[0] + width)) {
					if (getBottom(xPos1, yPos1) == null || !getBottom(xPos1, yPos1).isEmpty()) {
						return false;
					}
					xPos1 ++;
				}
				return true;
			}
			else if (dir == 2) {
				int xPos2 = currCoords[0];
				int yPos2 = currCoords[1];
				while (xPos2 < (currCoords[0] + width)) {
					if (getTop(xPos2,yPos2) == null || !getTop(xPos2, yPos2).isEmpty()) {
						return false;
					}
					xPos2 ++;
				}
				return true;
			}
			else if (dir == 3) {
				int xPos3 = currCoords[0];
				int yPos3 = currCoords[1];
				while (yPos3 < (currCoords[1] + height)) {
					if (getLeft(xPos3,yPos3) == null || !getLeft(xPos3, yPos3).isEmpty()) {
						return false;
					}
					yPos3 ++;
				}
				return true;
			}
			else if (dir == 4) {
				int xPos4 = currCoords[0] + width -1;
				int yPos4 = currCoords[1];
				while (yPos4 < (currCoords[1] + height)) {
					if (getRight(xPos4,yPos4) == null || !getRight(xPos4, yPos4).isEmpty()) {
						return false;
					}
					yPos4 ++;
				}
				return true;
			}
			return false;
		}

		public Block getLeft(int currX, int currY) {
			return getBlock(currX-1,currY);
		}
		
		public Block getRight(int currX, int currY) {
			return getBlock(currX+1, currY);
		}
		public Block getTop(int currX, int currY) {
			return getBlock(currX, currY-1);
		}
		
		public Block getBottom(int currX, int currY) {
			return getBlock(currX, currY+1);
		}

		public Block getBlock(int i, int j) {
			try {
			Block toBuild = Config[i][j];
			}
			catch (ArrayIndexOutOfBoundsException e) {
				return null;
			}
			return Config[i][j];
		}

		
	}

	
	
    
}

public class move {
	private int[] step = new int[4]; // maybe make this an int array instead?
	
	public move(int[] steps) {
		step = steps;
	}
	
	public move(int[] initial, int[] finale) {
		step[0] = initial[0];
		step[1] = initial[1];
		step[2] =  finale[0];
		step[3] = finale[1];
	}
	
	public int hashCode() {
		int x = 257;
		int polynomial = (int) Math.pow(x,3)*step[0] + (int) Math.pow(x, 2)*step[1] + (int) Math.pow(x, 1)*step[2] + (int) Math.pow(x, 0)*step[3];
		return polynomial;
	}

	public boolean reverse(move anotherMove) {
		if (this.initial().toString().equals(anotherMove.last().toString()) && this.last().toString().equals(anotherMove.initial().toString())) {
			return true;
		}
		return false;
	}
	
	public int dirOfMotion() {
		if (step[0] == step[2]) {
			if (step[3] > step[1]) {
				return 1; //down
			}
			else {
				return 2; //up
			}
		}
		else {
			if (step[2] > step[0]) {
				return 4; // right
			}
			else {
				return 3; //left
			}
		}
	}
	
	public String toString() {
		String s = "" + step[0] +" " + step[1] + " " + step[2] +" " + step[3] +" ";
		return s;
	}
	
	public int[] initial() {
		int[] initial = new int[2];
		initial[0] = step[0];
		initial[1] = step[1];
		return initial;
	}
	
	public int[] last() {
		 int[] last = new int[2];
		 last[0] = step[2];
		 last[1] = step[3];
		 return last;
	}
	
	public int[] getMove() {
		return step;
	}

	public boolean equals(Object obj) {
		return ( obj != null && (((move)obj).getMove())[0] == step[0] && ((move)obj).getMove()[1] == step[1] && step[2] == ((move)obj).getMove()[2] && step[3] == ((move)obj).getMove()[3] );
	}
}
}


