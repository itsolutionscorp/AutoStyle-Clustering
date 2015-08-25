
public class Block {

	private int height;
	private int width;

	/**
	 * Creates a new block with the given dimensions
	 * 
	 * @param height
     *            The height of the block
	 * @param width
     *            The width of the block
	 * @throws IllegalArgumentException
     *            If the given dimensions are invalid
	 * 
	 */
	public Block(int height, int width) throws IllegalArgumentException {
		this.height = height;
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public boolean equals(Object obj) {
		try {
			Block b = (Block) obj;
			return height == b.getHeight() && width == b.getWidth();
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Equals called on non-block.");
		}
	}

	/**
	 * Returns the hashcode of the block
	 * 
	 * The hashcode is based on a diagonal traversal of possible (height, width)
	 * pairs, made to minimize the average value for the blocks in a board
	 */
	public int hashCode() {
		return (width+height)*(width+height-1)/2 - width + 1;
	}

	public String toString() {
		return "[" + width + " " + height + "]";
	}
}
