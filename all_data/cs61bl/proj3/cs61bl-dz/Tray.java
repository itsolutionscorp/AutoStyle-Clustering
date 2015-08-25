import java.util.ArrayList;
import java.util.HashSet;


public class Tray {

	int height;  			//Height of tray
	int width;				//Width of tray
	Boolean[][] board;		//Board of true and false keeping track of where block are
	Tray prev;				//The previous tray
	String prevMove;		//The previous move
	ArrayList<Tray> pMoves;		//List of multiple possible trays
	HashSet<String> blocks;		//Hashset of blocks in string format



	/**
	 * Creates a tray.
	 * Tray contains a hashset of blocks in string format.
	 * Holds previous Tray.
	 * Holds tray height and width. 
	 * Sets up Board with all False values
	 * Sets position of pieces on board as true
	 */
	public Tray (HashSet<String> blcks, String prevInput, Tray p, int h, int w) { 
		blocks = blcks;	
		prev = p;
		prevMove = prevInput;
		height = h;
		width = w;
		board = new Boolean[height][width];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				board[i][j] = false;
			}
		}
		try {setPieces(); }
		catch(ArrayIndexOutOfBoundsException e){
			System.err.println ("Invalid init and/or goal file");
		}

	}


	/**
	 * Takes all the arrays in blocks and changes the boolean values
	 * to show the spaces already occupied in the board.
	 */
	public void setPieces(){
		for (String block : blocks){
			String[] blockCoords = block.split(" ");
			int y1 = Integer.parseInt(blockCoords[0]);
			int x1 = Integer.parseInt(blockCoords[1]);
			int y2 = Integer.parseInt(blockCoords[2]);
			int x2 = Integer.parseInt(blockCoords[3]);
			for (int i = y1; i < y2+1; i++){
				for (int j = x1; j < x2+1; j++){
					board[i][j] = true;
				}
			}
		}
	}


	/**
	 * Looks at all sides of each block to check to see if the block can move there.
	 * If the block can move there, then we add a new Tray with that move made to possible moves.
	 */
	public ArrayList<Tray> possibleMoves() {
		pMoves = new ArrayList<Tray>();
		for (String blockA : blocks) {
			String[] block = blockA.split(" ");
			int y1 = Integer.parseInt(block[0]);
			int x1 = Integer.parseInt(block[1]);
			int y2 = Integer.parseInt(block[2]);
			int x2 = Integer.parseInt(block[3]);
			if (canMoveUp(y1, x1, y2, x2)){
				String tempPrevMove = y1 +" "+ x1 +" "+ (y1-1) +" "+ x1;
				String removedBlock = y1 + " " + x1 + " " + y2 + " " + x2;
				String movedBlock = (y1-1) + " " + x1 + " " + (y2-1) + " " + x2;
				HashSet<String> blocksClone = (HashSet<String>) blocks.clone();
				blocksClone.remove(removedBlock);
				blocksClone.add(movedBlock);
				Tray newTray = new Tray(blocksClone, tempPrevMove, this, height, width);
				pMoves.add(newTray);
			}
			if (canMoveDown(y1, x1, y2, x2)){
				String tempPrevMove = y1 +" "+ x1 +" "+ (y1+1) +" "+ x1;
				String removedBlock = y1 + " " + x1 + " " + y2 + " " + x2;
				String movedBlock = (y1+1) + " " + x1 + " " + (y2+1) + " "+ x2;
				HashSet<String> blocksClone = (HashSet<String>) blocks.clone();
				blocksClone.remove(removedBlock);
				blocksClone.add(movedBlock);
				Tray newTray = new Tray(blocksClone, tempPrevMove, this, height, width);
				pMoves.add(newTray);
			}
			if (canMoveRight(y1, x1, y2, x2)){
				String tempPrevMove = y1 +" "+ x1 +" "+ y1 +" "+ (x1+1);
				String removedBlock = y1 + " " + x1 + " " + y2 + " " + x2;
				String movedBlock = y1 + " " + (x1+1) + " " + y2 + " " + (x2+1);
				HashSet<String> blocksClone = (HashSet<String>) blocks.clone();
				blocksClone.remove(removedBlock);
				blocksClone.add(movedBlock);
				Tray newTray = new Tray(blocksClone, tempPrevMove, this, height, width);
				pMoves.add(newTray);
			}
			if (canMoveLeft(y1, x1, y2, x2)){
				String tempPrevMove = y1 + " "+ x1 +" "+ y1 +" "+ (x1-1);
				String removedBlock = y1 + " " + x1 + " " + y2 + " " + x2;
				String movedBlock = y1 + " " + (x1-1) + " " + y2 + " " + (x2-1);
				HashSet<String> blocksClone = (HashSet<String>) blocks.clone();
				blocksClone.remove(removedBlock);
				blocksClone.add(movedBlock);
				Tray newTray = new Tray(blocksClone, tempPrevMove, this, height, width);
				pMoves.add(newTray);
			}
		}
		return pMoves;
	}


	/**
	 * Checks if block can move left.
	 * 
	 */
	public boolean canMoveLeft(int _y1, int _x1, int _y2, int _x2){
		if (_x1 - 1 > -1) {
			for (int i = _y1; i < _y2 + 1; i++){
				if (board[i][_x1 - 1] == true){
					return false;
				}
			} return true;
		}
		return false;
	}

	/**
	 * Checks if block can move right.
	 * 
	 */
	public boolean canMoveRight(int _y1, int _x1, int _y2, int _x2){
		if (_x2 + 1 < width){	
			for (int i = _y1; i < _y2 + 1; i++){
				if (board[i][_x2 + 1] == true){
					return false;
				}
			} return true;
		}
		return false;
	}

	/**
	 * Checks if block can move up.
	 * 
	 */
	public boolean canMoveUp(int _y1, int _x1, int _y2, int _x2){
		if (_y1 - 1 > -1) {	
			for (int i = _x1; i < _x2 + 1; i++){
				if (board[_y1 - 1][i] == true){
					return false;
				}
			} return true;
		}
		return false;
	}

	/**
	 * Checks if block can move down.
	 * 
	 */
	public boolean canMoveDown(int _y1, int _x1, int _y2, int _x2){
		if (_y2 + 1 < height) {	
			for (int i = _x1; i < _x2 + 1; i++){
				if (board[_y2 + 1][i] == true){
					return false;
				}
			} return true;
		}
		return false;
	}


	/**
	 * Check if to trays are equal.
	 */
	public boolean equals(Tray other) {
		return blocks.equals(other.blocks);
	}

	/**
	 * Checks blocks are matched with goal.
	 */
	public boolean containsGoal(Tray other) {
		return blocks.containsAll(other.blocks);
	}


	/**
	 * Hashes the block.
	 */
	public int hashCode() {
		return blocks.hashCode();
	}

}
