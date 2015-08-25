
public class Move {

	private Block block;
	private int fromX;
	private int fromY;
	private int toX;
	private int toY;
	private Move prevMove;

	/**
     * Creates a new move based on the given move configuration.
     * 
     * @param block
     *            The block to move
     * @param fromLocation
     *            The location to move from
     * @param toLocation
     *            The location to move to
     */
	public Move(Block block, Move move, int fromX, int fromY, int toX, int toY) {
		this.block = block;
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
		this.prevMove = move;
	}
	
	public Block getBlock() {
		return block;
	}

	public int getFromX() {
		return fromX;
	}
	
	public int getFromY() {
		return fromY;
	}
	
	public int getToX() {
		return toX;
	}
	
	public int getToY() {
		return toY;
	}
	
	public String toString() {
		return fromX + " " + fromY + " " + toX + " " + toY;
	}
	
	public Move getPrevMove() {
		return prevMove;
	}
}