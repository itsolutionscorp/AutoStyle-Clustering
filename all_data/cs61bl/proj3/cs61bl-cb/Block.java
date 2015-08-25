/**
 * CS61BL - UC Berkeley Summer 2015 Project 3 - Sliding Block Puzzles
 * 
 * Class 3/3:
 * 
 * Solver.java
 * 
 * Tray.java
 * 
 * Block.java <<<
 * 
 * 
 * @authors Jessica Larson, Brian Sakhuja, Eifu Tomita, Alex Yao
 *
 */

public class Block {
	private int topP;
	private int leftP;
	private int botP;
	private int rightP;
	
	/**
	 * Block
	 * 
	 * Block constructor 1/2. Creates a block object from the following
	 * parameters:
	 * 
	 * @param topP
	 *            - the topmost left coordinate of the block.
	 * @param leftP
	 *            - the leftmost top coordinate of the block.
	 * @param botP
	 *            - the bottommost right coordinate of the block.
	 * @param rightP
	 *            - the rightmost bottom coordinate of the block.
	 */
	public Block(int topP, int leftP,int botP,int rightP) {
		this.topP = topP;
		this.leftP = leftP;
		this.botP = botP;
		this.rightP = rightP;
	}
	
	/**
	 * Block
	 * 
	 * Block constructor 2/2. Duplicates block @param b from its coordinates.
	 */
	public Block(Block b){
		this.topP = b.topP;
		this.leftP = b.leftP;
		this.botP = b.botP;
		this.rightP = b.rightP;
	}
	
	
	/**
	 * getTop
	 * 
	 * @returns the top H coordinate for top left of block
	 */
	public int getTop(){
		return topP;
	}
	
	/**
	 * getLeft
	 * 
	 * @returns the left W coordinate for top left of block
	 */
	public int getLeft(){
		return leftP;
	}
	
	/**
	 * getBottom
	 * 
	 * @returns the bottom H coordinate for bottom right of block
	 */
	public int getBottom(){
		return botP;
	}
	
	/**
	 * getRight
	 * 
	 * @returns the right W coordinate for bottom right of block
	 */
	public int getRight(){
		return rightP;
	}

	/**
	 * toString
	 * 
	 * Prints out a blocks coordinates in the following format: uppermost left
	 * point (H,W), bottommost right point (H,W).
	 */
	public String toString(){
		return "("+topP + ", "+leftP+", "+botP+", " + rightP+")";
	}
	
	
	/**
	 * equals
	 * 
	 * Takes in block @param b and checks if it is equal to another block via
	 * the hashcode.
	 * 
	 * @returns true if the above condition is met. False if the block @param b
	 *          is null or the hashcodes don't match.
	 */
	public boolean equals(Object b){
		if(b == null){
			return false;
		}
		Block bb = (Block) b;
		return topP == bb.getTop() && leftP == bb.getLeft() && botP == bb.getBottom() && rightP == bb.getRight();
	}
	
	/**
	 * hashCode
	 * 
	 * @returns the hashcode for a block. Calculated by doing the following:
	 *          Each position (top, left, bottom, right) is multiplied by 1, 3,
	 *          9, and 27, respectively. The hashcode is the sum of these
	 *          integers.
	 */
	public int hashCode(){		
		return topP + leftP*5+botP*7+rightP*13; 
	}
}
