import java.util.HashMap;

public class Tray {

	/**
	 * myBlocks - a HashMap of String keys to Block values. The String keys
	 * represent the location of each Block value. Each Block is represented
	 * y-dimension multiplied by x-dimension times in this HashMap, in every
	 * unit which the Block covers.
	 * 
	 * myParent - the parent Tray of Tray which contains all original (not
	 * necessarily initial) Block locations.
	 * 
	 * theMove - a String which represents the movement of a single Block, which
	 * differentiates the Tray from its parent.
	 * 
	 * myMoved - the most recent Block that has moved from its parent Tray.
	 * 
	 * blocksInPlace - the number of blocks that are in correct configurations in this Tray.
	 */
	private HashMap<String, Block> myBlocks;
	private Tray myParent;
	private String theMove;
	private Block myMoved;
	private int blocksInPlace;

	/**
	 * The constructor of a Tray, called upon in the Solver constructor and the
	 * makeNewTray method in the Solver class.
	 * 
	 * @param newBlocks
	 *            The HashMap of the Tray's Block location, represented as
	 *            Strings, to Block values.
	 * @param parent
	 *            The parent of the Tray.
	 * @param move
	 *            A String of the movement of a single Block which
	 *            differentiates the Tray from its parent.
	 * @param myMoved
	 * 		      The Block that has moved its position in the parent Tray.     
	 */
	public Tray(HashMap<String, Block> newBlocks, Tray parent, String move, Block movedBlock) {
		myBlocks = newBlocks;
		myParent = parent;
		theMove = move;
		myMoved = movedBlock;
		blocksInPlace = 0;
	}

	/**
	 * A getter method for the Tray's myBlocks HashMap.
	 * 
	 * @return the Tray's myBlocks HashMap.
	 */
	public HashMap<String, Block> getMyBlocks() {
		return myBlocks;
	}

	/**
	 * A getter method for the Tray's blocksInPlace integer.
	 * 
	 * @return the Tray's blocksInPlace integer.
	 */
	public int getBlocksInPlace() {
		return blocksInPlace;
	}
	
	/**
	 * A setter method for the Tray's blocksInPlace integer.
	 * @param n - the number of blocks that are in correct configurations in this Tray.
	 */
	public void setBlocksInPlace(int n){
		blocksInPlace = n;
	}
	
	/**
	 * A getter method for the Tray's parent Tray.
	 * 
	 * @return the Tray's myParent Tray.
	 */
	public Tray getMyParent() {
		return myParent;
	}
	
	/**
	 * A getter method for the Tray's myMoved Block.
	 * 
	 * @return the Tray's myMoved Block.
	 */
	public Block getMyMoved() {
		return myMoved;
	}

	/**
	 * The overridden toString method, inherently called upon when each Tray is
	 * printed out in the retrace method in the Solver class.
	 * 
	 * @return the Tray's theMove.
	 */
	@Override
	public String toString() {
		return theMove;
	}

	/**
	 * The overridden equals method, called upon in the retrace method of the
	 * Solver class.
	 * 
	 * @param The
	 *            Object (Tray) to which this Tray compares itself.
	 * @return true if the Tray and the parameter Object (Tray) have equal
	 *         myBlocks HashMaps.
	 */
	@Override
	public boolean equals(Object obj) {
		Tray other = (Tray) obj;
		return myBlocks.equals(other.getMyBlocks());
	}

	/**
	 * The overridden hashCode method, called upon when hashing each Tray into
	 * the myTrays HashSet of the Solver class. The hash value is completely
	 * dependent on the hash values of the Tray's Blocks.
	 * 
	 * @return the hash value of the Tray.
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		for (Block b : myBlocks.values()) {
			hash += b.hashCode();
		}
		return hash;
	}
}
