import java.util.Arrays;

public class Block {

	/**
	 * location - an int array of size 4 which represents the location of the
	 * Block. The first two indices represent the y and x values of the Block's
	 * top left corner, and the 3rd and 4th indices represent the y (vertical)
	 * and x (horizontal) values of the Block's bottom left corner.
	 * 
	 * dimension - an int array of size 2 which represents the size of the
	 * Block. The first index is the Block's length in the y-direction, and the
	 * second index is the Block's length in the x-direction.
	 * 
	 * sameAsWinning - a boolean value that will be changed to true if the Block
	 * has the same dimensions as a Block in the goal configurations. This
	 * boolean will be used to prioritize blocks that can fulfill goals over
	 * those that cannot.
	 */
	private int[] location;
	private int[] dimension;
	private boolean sameAsWinning;

	/**
	 * The constructor of a Block, called upon in the setInitialConfig and
	 * setGoalConfig methods in the Solver class. It calls upon the setDimension
	 * and setLocation methods.
	 * 
	 * @param y1
	 *            The y value of the Block's top left corner.
	 * @param x1
	 *            The x value of the Block's top left corner.
	 * @param y2
	 *            The y value of the Block's bottom right corner.
	 * @param x2
	 *            The x value of the Block's bottom right corner.
	 */
	public Block(int y1, int x1, int y2, int x2) {
		dimension = setDimension(y1, x1, y2, x2);
		location = setLocation(y1, x1, y2, x2);
		sameAsWinning = false;
	}

	/**
	 * A method which sets the dimension of the Block.
	 * 
	 * @param y1
	 *            The y value of the Block's top left corner.
	 * @param x1
	 *            The x value of the Block's top left corner.
	 * @param y2
	 *            The y value of the Block's bottom right corner.
	 * @param x2
	 *            The x value of the Block's bottom right corner.
	 * @return an int array of the Block's dimensions.
	 */
	private int[] setDimension(int y1, int x1, int y2, int x2) {
		int[] temp = { y2 - y1 + 1, x2 - x1 + 1 };
		return temp;
	}

	/**
	 * A method which sets the location of the Block.
	 * 
	 * @param y1
	 *            The y value of the Block's top left corner.
	 * @param x1
	 *            The x value of the Block's top left corner.
	 * @param y2
	 *            The y value of the Block's bottom right corner.
	 * @param x2
	 *            The x value of the Block's bottom right corner.
	 * @return an int array of the Block's location.
	 */
	private int[] setLocation(int y1, int x1, int y2, int x2) {
		int[] temp = { y1, x1, y2, x2 };
		return temp;
	}

	/**
	 * A getter method for the coordinates of the Block's top left corner.
	 * 
	 * @return the coordinates of the Block's top left corner, represented as an
	 *         int array of size 2.
	 */
	public int[] getTopLeft() {
		int[] temp = { location[0], location[1] };
		return temp;
	}

	/**
	 * A getter method for the coordinates of the Block's bottom right corner.
	 * 
	 * @return the coordinates of the Block's bottom right corner, represented
	 *         as an int array of size 2.
	 */
	public int[] getBottomRight() {
		int[] temp = { location[2], location[3] };
		return temp;
	}

	/**
	 * A getter method for the Block's location.
	 * 
	 * @return the Block's location, represented as an int array of size 4.
	 */
	public int[] getLocation() {
		return location;
	}

	/**
	 * A getter method for the Block's dimension.
	 * 
	 * @return the Block's dimension, represented as an int array of size 2.
	 */
	public int[] getDimension() {
		return dimension;
	}

	/**
	 * A setter method that will assign the Block's sameAsWinning boolean to
	 * true to denote that it matches the dimensions of a goal block.
	 */
	public void setWinnerSize() {
		sameAsWinning = true;
	}

	/**
	 * A getter method for the Block's status as a goal Block.
	 * 
	 * @return the boolean of whether the Block matches the dimensions of a goal
	 *         block.
	 */
	public boolean getWinnerSize() {
		return sameAsWinning;
	}

	/**
	 * The overridden toString method which represents a Block by its location.
	 */
	@Override
	public String toString() {
		String ret = "";
		for (int i : location) {
			ret = ret + "" + i + " ";
		}
		return ret;
	}

	/**
	 * The overridden hashCode method which produces a hash value calculated by
	 * the Block's location.
	 * 
	 * @return the hash value of the Block.
	 */
	@Override
	public int hashCode() {
		int hash = 31 * ((19 * location[0]) + (11 * location[1])
				+ (13 * location[2]) + (17 * location[3]));
		return hash;
	}

	/**
	 * The overridden equals method which compares itself to the specified
	 * Object (Block), irrespective of Tray.
	 * 
	 * @param The
	 *            Object (Block) to which the Block compares itself.
	 * @return true if the Block and the parameter Object (Block) have the same
	 *         dimension and are in the same location.
	 */
	@Override
	public boolean equals(Object obj) {
		Block other = (Block) obj;
		boolean case1 = Arrays.equals(location, other.getLocation());
		boolean case2 = Arrays.equals(dimension, other.getDimension());
		return (case1 && case2);
	}
}
