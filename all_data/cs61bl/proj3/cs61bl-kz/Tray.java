import java.util.ArrayList;
import java.util.*;

public class Tray {
	private Block[][] myTray;
	private HashSet<MoveObject> possibleMoves;
	private int trayHeight, trayWidth;
	private ArrayList<Block> blocks;
	private Tray myParent;
	private MoveObject myMove;
	private int depth;
	private int priority;
	private int myHashCode;

	/**
	 * constructs a tray with the first line of a text file
	 * @param size: the dimensions of the tray
	 */
	public Tray(int width, int height) {
		myParent = null;
		possibleMoves = new HashSet<MoveObject>();
		myMove = null;
		blocks = new ArrayList<Block>();
		trayHeight = height;
		trayWidth = width;
		depth = 0;
		priority = 0;
		myTray = new Block[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				myTray[i][j] = null;
			}
		}
		myHashCode = 0;
	}

	public int getMyHashCode() {
		return myHashCode;
	}
	
	public int depth() {
		return depth;
	}
	
	public void setDepth(int d) {
		this.depth = d;
	}
	
	public int priority() {
		return priority;
	}
	
	public void setPriority(int p) {
		this.priority = p;
	}

	public ArrayList<Block> blocks() {
		return blocks;
	}

	public MoveObject move() {
		return myMove;
	}

	public Tray parent() {
		return myParent;
	}

	public void setMove(MoveObject move) {
		myMove = move;
	}

	public void setParent(Tray parent) {
		myParent = parent;
	}

	/**
	 * Checks whether you can add a block into a location
	 * @param b
	 * @return
	 */
	public boolean canAdd(Block b) {
		for (int i = b.upperLeftX(); i < b.lowerRightX() + 1; i++) {
			for (int j = b.upperLeftY(); j < b.lowerRightY() + 1; j++) {
				if (myTray[i][j] != null) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * removes a block from its position and sets the spaces where the block was to null
	 * @param b
	 */
	public void removeBlock(Block b) {
		for (int i = b.upperLeftX(); i < b.lowerRightX() + 1; i++) {
			for (int j = b.upperLeftY(); j < b.lowerRightY() + 1; j++) {
					myTray[i][j] = null;
			}
		}
		blocks.remove(b);
	}

	/**
	 * adds a block from a line from a text file
	 * @param line
	 */
	public void addBlock(Block b) {
		for (int i = b.upperLeftX(); i < b.lowerRightX() + 1; i++) {
			for (int j = b.upperLeftY(); j < b.lowerRightY() + 1; j++) {
					myTray[i][j] = b;
			}
		}
		blocks.add(b);
	}


	/**
	 * overrides the equals method to check if the hashcodes are the same.
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Tray)) {
			return false;
		}
		if (((Tray) other).blocks.size() != blocks.size()) {
			return false;
		}
		if (this.blocks.size() > 100) {
			if (this.hashCode() != ((Tray) other).hashCode()) {
				return false;
			}
		} else {
		for (Block k : blocks) {
			if (!((Tray) other).blocks.contains(k)) {
				return false;
			}
		}
		}
		return true;
	}

	/**
	 * Overrides the hashcode method by summing the blocks.
	 */
	@Override
	public int hashCode() {
		int rtn = 0;
		for (Block b: blocks) {
			rtn += b.hashCode();
		}
		return rtn;
	}

	/**
	 * Overrides the toString method using a Stringbuilder to improve runtime
	 */
	@Override
	public String toString() {
		StringBuilder results = new StringBuilder("");
		for (Block b: blocks) {
			results.append(b.upperLeftX());
			results.append(b.upperLeftY());
		}
		return results.toString();
	}

	/**
	 * returns a HashSet of MoveObjects that are valid for the current tray.
	 * @return
	 */
	public HashSet<MoveObject> validMoves() {
		for (Block b: blocks) {
			//try sliding up
			if ((b.upperLeftY() - 1) > -1) {
				validMoveHelper(b, b.upperLeftX(), b.upperLeftY() - 1, 0);
			}
			//try sliding down
			if ((b.lowerRightY() + 1) < trayHeight) {
				validMoveHelper(b, b.upperLeftX(), b.lowerRightY() + 1, 1);
			}
			//try sliding left
			if ((b.upperLeftX() - 1) > -1) {
				validMoveHelper(b, b.upperLeftX() - 1, b.upperLeftY(), 2);
			}
			//try sliding right
			if ((b.lowerRightX() + 1) < trayWidth) {
				validMoveHelper(b, b.lowerRightX() + 1, b.upperLeftY(), 3);
			}
		}
		return possibleMoves;
	}

	/**
	 * Attempts moves in 4 directions for a given block and adds valid ones to the possibleMoves HashSet
	 * @param b
	 * @param startX
	 * @param startY
	 * @param movement
	 */
	public void validMoveHelper(Block b, int startX, int startY, int movement) {
		boolean multipleMoves = true;
		//Slide up
		if (movement == 0) {
			for (int j = startY; j > -1; j--) {
				if (!multipleMoves) {
					return;
				}
				for (int i = startX; i < startX + b.width(); i++) {
					//if above for loop initialized use j instead of startY below
					if (myTray[i][j] != null){
						multipleMoves = false;
						return;
					}
				}
				//if above for loop initialized use j instead of startY below
				MoveObject moveUp = new MoveObject(b, startX, startY);
				this.possibleMoves.add(moveUp);
			}
		//Slide down
		}
		if (movement == 1) {
			for (int j = startY; j < trayHeight; j++) {
				if (!multipleMoves) {
					return;
				}
				for (int i = startX; i < startX + b.width(); i++) {
					//if above for loop initialized use j instead of startY below
					if (myTray[i][j] != null){
						multipleMoves = false;
						return;
					}
				}
				//if above for loop initialized use j instead of startY below
				MoveObject moveDown = new MoveObject(b, startX, startY - b.height() + 1);
				this.possibleMoves.add(moveDown);
			}
		//Slide left
		}
		if (movement == 2) {
			for (int j = startX; j > -1; j--) {
				if (!multipleMoves) {
					return;
				}
				for (int i = startY; i < startY + b.height(); i++) {
					//if above for loop initialized use j instead of startX below
					if (myTray[j][i] != null) {
						multipleMoves = false;
						return;
					}
				}
				//if above for loop initialized use j instead of startX below
				MoveObject moveL = new MoveObject(b, startX, startY);
				this.possibleMoves.add(moveL);
			}
		//Slide right
		}
		if (movement == 3) {
			for (int j = startX; j < trayWidth; j++) {
				if (!multipleMoves) {
					return;
				}
				for (int i = startY; i < startY + b.height(); i++) {
					//if above for loop initialized use j instead of startX below
					if (myTray[j][i] != null){
						multipleMoves = false;
						return;
					}
				}
				//if above for loop initialized use j instead of startX below
				MoveObject moveR = new MoveObject(b, startX - b.width() + 1, startY);
				this.possibleMoves.add(moveR);
			}
		}
	}

	/**
	 * Executes a block move on block b to the new upper left coordinates given.
	 * @param b
	 * @param newLeftX
	 * @param newLeftY
	 * @return a new Tray after the move
	 */
	public Tray blockMove(Block b, int newLeftX, int newLeftY) {
		Tray newTray = new Tray(trayWidth, trayHeight);
		newTray.setDepth(this.depth() + 1);
		for (Block d: blocks) {
			newTray.addBlock(new Block(d.upperLeftX(), d.upperLeftY(), d.lowerRightX(), d.lowerRightY()));
		}
		Block toBeChanged = newTray.myTray[b.upperLeftX()][b.upperLeftY()];
		newTray.removeBlock(toBeChanged);
		toBeChanged.changeCoordinates(newLeftX, newLeftY);
		newTray.addBlock(toBeChanged);
		return newTray;
	}
	
	/**
	 * A class of that contains information for a block move
	 * @author Tim Guan, Ranju Subramani, Pratyusha Gogulapati, Yiksai Wong
	 */
	public class MoveObject {
		Block myBlock;
		int newLeftX;
		int newLeftY;
		public MoveObject(Block b, int x, int y) {
			myBlock = b;
			newLeftX = x;
			newLeftY = y;
		}

		/**
		 * Overrides the toString using a stringbuilder to improve runtime.
		 */
		@Override
		public String toString() {
			StringBuilder results = new StringBuilder("");
			results.append(myBlock.upperLeftX());
			results.append(" ");
			results.append(myBlock.upperLeftY());
			results.append(" ");
			results.append(newLeftX);
			results.append(" ");
			results.append(newLeftY);
			return results.toString();
		}
	}
}