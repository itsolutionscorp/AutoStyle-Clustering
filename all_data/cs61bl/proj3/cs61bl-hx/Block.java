/**
 * A Block class to reprsent the Block Object, contains its coordinate and String presentation of this block
 */
public class Block {	
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private String myPresentation;
	/**
	 * a constuctor to store the value of block.
	 * @param x1 int top coordinate x
	 * @param y1 int top coordinate y
	 * @param x2 int down coordinate x
	 * @param y2 int down coordinate y
	 */
	public Block(int x1, int y1, int x2, int y2){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		myPresentation = ""+x1+y1+x2+y2;
	}	
	/**
	 * String representation of the Block. Currently set to the
	 * placement on the tray it currently is in.
	 */
	public String toString(){
		return myPresentation;
	}

	/**
	 * Unique hashCode that use Prime number 97 to generate its.
	 * Credit to Exam2, Checker Board.
	 */
	public int hashCode(){
		return 97* x1+ 97* (x2 + 97 * (y2 + 97 * y1));
	}

	/**
	 * Returns x1 value of this Block.
	 * @return int x1
	 */
	public int getX1(){
		return x1;
	}

	/**
	 * Returns x2 value of this Block.
	 * @return int x2
	 */
	public int getX2(){
		return x2;
	}

	/**
	 * Returns y1 value of this Block.
	 * @return int y1
	 */
	public int getY1(){
		return y1;
	}

	/**
	 * Returns y2 value of this Block.
	 * @return int y2
	 */
	public int getY2(){
		return y2;
	}

	/**
	 * Moves the current Block up.
	 * @param currentTray the Tray the current Block is in
	 * @return the new Block as a result of moving it up, or null if fails
	 */
	public Block moveUp(Tray currentTray){		
		boolean[][] configure = currentTray.getTrayConfiguration();

		if (x1 == 0) // you are the top of the tray
			return null;

		for (int i = y1; i <= y2; i++){
			if (configure[x1-1][i]){
				return null;
			}
		}		
		return new Block(x1-1,y1,x2-1,y2);		
	}

	/**
	 * Moves the current Block down.
	 * @param currentTray the Tray the current Block is in
	 * @return the new Block as a result of moving it down, or null if fails
	 */
	public Block moveDown(Tray currentTray){		
		boolean[][] configure = currentTray.getTrayConfiguration();

		if (x2 == configure.length-1) // you are the bottom of the tray
			return null;

		for (int i = y1; i <= y2; i++){
			if (configure[x2+1][i]){
				return null;
			}
		}		
		return new Block(x1+1,y1,x2+1,y2);		
	}

	/**
	 * Moves the current Block right.
	 * @param currentTray the Tray the current Block is in
	 * @return the new Block as a result of moving it right, or null if fails
	 */
	public Block moveRight(Tray currentTray){		
		boolean[][] configure = currentTray.getTrayConfiguration();

		if (y2 == configure[0].length-1) // you are the far right
			return null;

		for (int i = x1; i <= x2; i++){			
			if (configure[i][y2+1]){
				return null;
			}
		}		
		return new Block(x1,y1+1,x2,y2+1);		
	}

	/**
	 * Moves the current Block left.
	 * @param currentTray the Tray the current Block is in
	 * @return the new Block as a result of moving it left, or null if fails
	 */
	public Block moveLeft(Tray currentTray){		
		boolean[][] configure = currentTray.getTrayConfiguration();

		if (y1 == 0) // you are at the far left
			return null;

		for (int i = x1; i <= x2; i++){
			if (configure[i][y1-1]){
				return null;
			}
		}		
		return new Block(x1,y1-1,x2,y2-1);		
	}

}
