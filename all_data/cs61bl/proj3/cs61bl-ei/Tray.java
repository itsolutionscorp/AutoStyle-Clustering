
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Tray implements Comparable {

	// INSTANCE VARIABLES 
	
	HashMap<ArrayList<Integer>, Block> myBlocks;
	private Tray myParent;
	private int[] myMove;
	boolean goalReached;
	private Block[][] TRAY_MATRIX;			// HEIGHT BY WIDTH GODDAMIT 
	private int trayHeight;
	private int trayWidth;
	private int myPriority;
	private int myHashCode;
	private boolean goal;
	private Solver mySolver;

	public Tray(Tray parent, int height, int width, int[] move, Solver s) {
	    mySolver = s;
		trayWidth = width;
		trayHeight = height;
		myParent = parent;
		TRAY_MATRIX = new Block[height][width];
		myMove = move;
		myBlocks = new HashMap<ArrayList<Integer>, Block>();
		if (myParent != null && move.length == 4) {
			Set<ArrayList<Integer>> keys = myParent.myBlocks.keySet();
            Iterator<ArrayList<Integer>> iter = keys.iterator();
			while (iter.hasNext()) {
				ArrayList<Integer> b0 = iter.next();
				Block b = myParent.myBlocks.get(b0);
				Block copied = new Block(b.getTopLeftY(), b.getTopLeftX(), b.getBottRightY(), b.getBottRightX(), this);
			}
			Block to_move = TRAY_MATRIX[move[0]][move[1]];
			to_move.move(move[0], move[1], move[2], move[3]);
		}
			myPriority = this.priority();
			myHashCode = this.hash();
			goal = false;
			if(mySolver.getGoal() != null) {
				if(Math.abs(myPriority) == mySolver.getGoal().size()) {
					goal = true;
				}
			}
	}
	
	public int priority() { 
		if (myParent == null){
			return 0;
		}
		ArrayList<Block> g = mySolver.getGoal();
		int total = 0;
		int x = 0;
		int y = 0;
		for (Block b : g){
			x= b.getTopLeftX();
			y =b.getTopLeftY();
			
			if ((this.TRAY_MATRIX[y][x] != null) && (this.TRAY_MATRIX[y][x].equals(b))) {
				total++;
			}
		}
		return 0 - total;
	}

	// ACTION METHODS 
	
	public void createNeighbors(Solver s) {
		Set<ArrayList<Integer>> keys = myBlocks.keySet();
		Iterator<ArrayList<Integer>> iter = keys.iterator();
		while (iter.hasNext()) {
			ArrayList<Integer> curr = iter.next();
			Block b = myBlocks.get(curr);

			int tlX = b.getTopLeftX();
			int tlY = b.getTopLeftY();
			int brX = b.getBottRightX();
			int brY = b.getBottRightY();

			// MOVE UP 
			if (isUpEmpty(tlY-1, tlX, brY, brX)) {			// HEIGHT by WIDTH
				int[] move = new int[4];
				move[0] = tlY;
				move[1] = tlX;
				move[2] = tlY-1;
				move[3] = tlX;
				Tray up = new Tray(this, trayHeight, trayWidth, move, mySolver);	// HEIGHT by WIDTH
				if (!s.getVisited().contains(up)) {
					s.getFringe().add(up);
				}
			}

			// MOVE DOWN
			if (isDownEmpty(tlY, tlX, brY+1, brX)) {			// HEIGHT by WIDTH
				int[] move = new int[4];
				move[0] = tlY;
				move[1] = tlX;
				move[2] = tlY+1;
				move[3] = tlX;
				Tray down = new Tray(this, trayHeight, trayWidth, move, mySolver);	// HEIGHT by WIDTH
				if (!s.getVisited().contains(down)) {
					s.getFringe().add(down);
				}
			}

			// MOVE LEFT 
			if (isLeftEmpty(tlY, tlX-1, brY, brX)) {			// HEIGHT by WIDTH
				int[] move = new int[4];
				move[0] = tlY;
				move[1] = tlX;
				move[2] = tlY;
				move[3] = tlX-1;
				Tray left = new Tray(this, trayHeight, trayWidth, move, mySolver);	// HEIGHT by WIDTH
				if (!s.getVisited().contains(left)) {
					s.getFringe().add(left);
				}
			}

			// MOVE RIGHT 
			if (isRightEmpty(tlY, tlX, brY, brX+1)) {			// HEIGHT by WIDTH
				int[] move = new int[4];
				move[0] = tlY;
				move[1] = tlX;
				move[2] = tlY;
				move[3] = tlX+1;
				Tray right = new Tray(this, trayHeight, trayWidth, move, mySolver);	// HEIGHT by WIDTH
				if (!s.getVisited().contains(right)) {
					s.getFringe().add(right);
				}
			}
		}
	}

	public boolean isUpEmpty(int y0, int x0, int y1, int x1){
		int i = x0;
		int j = y0;
		if (x0 < 0 || x0 >= trayWidth || x1 < 0 || x1 >= trayWidth || y0 < 0 || y0 >= trayHeight || y1 < 0 || y1 >= trayHeight){
	        return false;
	    }		 
		while (i <= x1) {
    		if (TRAY_MATRIX[j][i] != null) {										// HEIGHT by WIDTH 
    			return false;
    		}
    		i++;
    	}
	    return true;
		
	}
	
	public boolean isDownEmpty(int y0, int x0, int y1, int x1){
		int i = x0;
		int j = y1;
		if (x0 < 0 || x0 >= trayWidth || x1 < 0 || x1 >= trayWidth || y0 < 0 || y0 >= trayHeight || y1 < 0 || y1 >= trayHeight){
	        return false;
	    }
		while (i <= x1) {
    		if (TRAY_MATRIX[j][i] != null) {										// HEIGHT by WIDTH 
    			return false;
    		}
    		i++;
    	}
	    return true;	
	}
	
	public boolean isLeftEmpty(int y0, int x0, int y1, int x1){
		int i = x0;
		int j = y0;
		if (x0 < 0 || x0 >= trayWidth || x1 < 0 || x1 >= trayWidth || y0 < 0 || y0 >= trayHeight || y1 < 0 || y1 >= trayHeight){
	        return false;
	    }
		while (j <= y1) {
    		if (TRAY_MATRIX[j][i] != null) {										// HEIGHT by WIDTH 
    			return false;
    		}
    		j++;
    	}
	    return true;
	}
	
	public boolean isRightEmpty(int y0, int x0, int y1, int x1){
		int i = x1;
		int j = y0;
		if (x0 < 0 || x0 >= trayWidth || x1 < 0 || x1 >= trayWidth || y0 < 0 || y0 >= trayHeight || y1 < 0 || y1 >= trayHeight){
	        return false;
	    }
		while (j <= y1) {
    		if (TRAY_MATRIX[j][i] != null) {										// HEIGHT by WIDTH 
    			return false;
    		}
    		j++;
    	}
	    return true;
	}

	public void setCoord(int tly, int tlx, int bry, int brx, Block b) {
		int i = 0;
		while (tlx + i <= brx) {
			int j = 0;
			while (tly + j <= bry) {
				TRAY_MATRIX[tly+j][tlx+i] = b;											// HEIGHT by WIDTH 
				j++;
			}
			i++;
		}
	}

	public void setNull(int tly, int tlx, int bry, int brx) {
		int i = 0;
		while (tlx + i <= brx) {
			int j = 0;
			while (tly + j <= bry) {
				TRAY_MATRIX[tly+j][tlx+i] = null;										// HEIGHT by WIDTH 
				j++;
			}
			i++;
		}
	}

	public boolean isGoal() {
		return goal;
	}

	public Tray getParent() {
		return this.myParent;
	}

	public HashMap<ArrayList<Integer>, Block> getBlocks() {
		return this.myBlocks;
	}

	public int[] getMove() {
		return this.myMove;
	}

	@Override
	public int hashCode() {
		return myHashCode;
	}
	
	public int getTrayHeight() {
		return trayHeight;
	}
	
	public int getTrayWidth() {
		return trayWidth;
	}
	
	public int hash() {
		Set<ArrayList<Integer>> key = myBlocks.keySet();
		Iterator<ArrayList<Integer>> iter = key.iterator();
		int code = 0;
		while (iter.hasNext()) {
			code += myBlocks.get(iter.next()).hashCode();
		}
		return code;
		
	}

	@Override
	public boolean equals(Object o) {
		Set<ArrayList<Integer>> keys = this.myBlocks.keySet();
		Iterator<ArrayList<Integer>> iter = keys.iterator();
		while (iter.hasNext()) {
			ArrayList<Integer> coords = iter.next();
			Block b = myBlocks.get(coords);
			Block c = ((Tray) o).myBlocks.get(coords);
			if (!b.equals(c)) {
				return false;
			}
		}
		return true;
	}

	@Override 
	public int compareTo(Object o) {
	    return this.myPriority - ((Tray)o).myPriority;
	}
}