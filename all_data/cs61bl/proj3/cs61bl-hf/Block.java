import java.awt.Point;


public class Block {
	Point size;
	/**
	 * sets the size of this block
	 * @param yLength
	 *       Length of the block in the row direction
	 * @param xLength
	 *    Length of the block in the column direction
	 */
	
	public Block(int yLength, int xLength){
		size= new Point(xLength,yLength);
	}
	/**
	 * returns the size of this block
	 * @return
	 *     return a point object witch represents the block size
	 */
	public Point size(){
		return size;
	}
}
