import java.util.*;

public class Tray implements Comparable{

	private int height;
	private int width;
	private Block[][] blocks;
	private int[][] emptySpaces;
	private String id;
	private Move move;
	private int heuristicDistance;

	/**
     * Creates a new tray based on moving a given move from the given tray.
     * 
     * @param prevState
     *            The previous tray state to move from
     * @param move
     *            The move to move
     * @throws IllegalArgumentException
     *            If it is impossible to move the given move from the given tray
     */
	public Tray(Tray prevState, Move move) throws IllegalArgumentException {
        height = prevState.height;
        width = prevState.width;
        
        int fromX = move.getFromX();
        int fromY = move.getFromY();
        int toX = move.getToX();
        int toY = move.getToY();
        
        Block moved = move.getBlock();
        int movedWidth = moved.getWidth();
        int movedHeight = moved.getHeight();
        
        // Create a copy of prevState.blocks, and change it to reflect the move
        blocks = new Block[prevState.blocks.length][prevState.blocks[0].length];
        for (int i = 0; i < prevState.blocks.length; i++) {
        	for (int j = 0; j < prevState.blocks[0].length; j++) {
        		blocks[i][j] = prevState.blocks[i][j];
        	}
        }
        
        blocks[fromX][fromY] = null;
        blocks[fromX + movedHeight-1][fromY + movedWidth-1] = null;
        
        blocks[toX][toY] = moved;
        blocks[toX + movedHeight-1][toY + movedWidth-1] = moved;
        
        // Create a copy of prevState.emptySpaces, and change it as well
        emptySpaces = new int[prevState.emptySpaces.length][prevState.emptySpaces[0].length];
        
        for (int i = 0; i < prevState.emptySpaces.length; i++) {
        	for (int j = 0; j < prevState.emptySpaces[0].length; j++) {
        		emptySpaces[i][j] = prevState.emptySpaces[i][j];
        	}
        }
        int xDelta = fromX + toX + movedHeight - 1;
        int yDelta = fromY + toY + movedWidth - 1;
        for (int[] space : emptySpaces) {
            if (toX <= space[0] && space[0] < toX + movedHeight &&
                toY <= space[1] && space[1] < toY + movedWidth) {
                space[0] = xDelta - space[0];
                space[1] = yDelta - space[1];
            }
        }
        generateID(prevState.getID(), move);
        this.move = move;
    }
	/**
	 * Creates a new tray from the given tray configuration.
	 * 
	 * @param height
     *            The height of the tray
	 * @param width
     *            The width of the tray
	 * @param blocks
     *            A Block[][] representing the blocks in the tray
     *            Each Block has a reference at their NW and SE corners
	 * @throws IllegalArgumentException
     *            If the given configuration is invalid
	 */
	public Tray(int height, int width)
		throws IllegalArgumentException {
		if (height > 256 || height < 0) {
			throw new IllegalArgumentException("height of " + height + " exceeds bounds");
		}
		if (width > 256 || width < 0) {
			throw new IllegalArgumentException("width of " + width + " exceeds bounds");
		}
		this.height = height;
		this.width = width;
		this.blocks = new Block[height][width];
		move = null;
	}

	public void generateEmptySpacesandID () {
		boolean[][] checked = new boolean[height][width];
		ArrayList<int[]> toBeEmptySpaces = new ArrayList<int[]>();
		int numEmpty = 0;
		int dimensions = (height) * (width);
		char[] intID = new char[dimensions];
		int c = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (blocks[y][x] != null) {
					intID[c] = (char) blocks[y][x].hashCode();
				}
				if (!checked[y][x]) {
					Block b = blocks[y][x];
					if (b != null) {
						intID[c] = (char) b.hashCode();
						for (int k = 0; k < b.getHeight(); k++) {
							for (int p = 0; p < b.getWidth(); p++) {
								checked[y + k][x + p] = true;
							}
						}
					} else {
						toBeEmptySpaces.add(new int[] {y, x});
						numEmpty++;
						intID[c] = 0;
					}
				}
				c++;
			}
		}
		emptySpaces = new int[numEmpty][];
		for (int i = 0; i < toBeEmptySpaces.size(); i++) {
			emptySpaces[i] = toBeEmptySpaces.get(i);
		}
		
	    id = new String(intID);
	}
	
	public void addBlock(int[] block) 
		throws IllegalArgumentException {
		if (block.length != 4) {
			throw new IllegalArgumentException("block must be an int array of length 4");
		} else if (block[2] < block[0]) {
			throw new IllegalArgumentException("upper left x must be above lower right x");
		} else if (block[3] < block[1]) {
			throw new IllegalArgumentException("upper left y must be above lower right y");
		}	
		int blockHeight = block[2] - block[0] + 1;
		int blockWidth = block[3] - block[1] + 1;
		if (blockHeight > 256 || blockWidth > 256) {
			throw new IllegalArgumentException("block dimensions exceed tray dimensions");
		}
		Block newBlock = new Block(blockHeight, blockWidth);

		// add pointers to the blocks array for the NW and SE corners
		if (blocks[block[0]][block[1]] != null && blocks[block[2]][block[3]] != null) {
			throw new IllegalArgumentException("a block already exists at this location");
		}
		blocks[block[0]][block[1]] = newBlock;
		blocks[block[2]][block[3]] = newBlock;
	}

	/**
     * Returns an ArrayList of the possible moves from the current tray.
     * 
     * @return
     *            An ArrayList of possible moves
     */
	private ArrayList<Move> checkR (int[] i) {
		ArrayList<Move> moves = new ArrayList<Move>();
		if (i[1] == width - 1) {
			return moves;
		}
		Block right = blocks[i[0]][i[1] + 1];
		boolean foundSpace = false;
		if (right != null) {
			if (right.getWidth() == 1 && right.getHeight() != 1) {
				if (i[0] - right.getHeight() + 1 >= 0){
					if (blocks[i[0] - right.getHeight() + 1][i[1] + 1] == right) {
						return moves;
					}
				}
			}
			for (int j = 0; j < right.getHeight(); j++) {
				foundSpace = false;
				for (int[] k: emptySpaces) {
					if (k[0] == i[0] + j && k[1] == i[1]) {
						foundSpace = true;
					}
				}
				if (!foundSpace) {
					break;
				}
			}
			if (foundSpace) {
				moves.add(new Move(right, move, i[0], i[1] + 1, i[0], i[1]));
			}
		}
		return moves;
	}

	private ArrayList<Move> checkL(int[] i) {
		ArrayList<Move> moves = new ArrayList<Move>();
		if (i[1] == 0) {
			return moves;
		}
		Block left = blocks[i[0]][i[1] - 1];
		boolean foundSpace = false;
		if (left != null) {
			if (left.getWidth() == 1 && left.getHeight() != 1) {
				if (i[0] + left.getHeight() - 1 < height){
					if (blocks[i[0] + left.getHeight() - 1][i[1] - 1] == left) {
						return moves;
					}
				}
			}
				int trackingCornerX = i[0] - left.getHeight() + 1;
				int trackingCornerY = i[1] - left.getWidth();
				for (int j = 0; j < left.getHeight(); j++) {
					foundSpace = false;
					for (int[] k: emptySpaces) {
						if (k[0] == i[0] - j && k[1] == i[1]) {
							foundSpace = true;
							break;
						}
					}
					if (!foundSpace) {
						break;
					}
				}
				if (foundSpace) {
					moves.add(new Move(left, move, trackingCornerX, trackingCornerY , trackingCornerX, trackingCornerY + 1));
				}
				return moves;
		}
		return moves;
	}
	private ArrayList<Move> checkB (int[] i) {
		ArrayList<Move> moves = new ArrayList<Move>();
		if (i[0] == height - 1) {
			return moves;
		}
		Block below = blocks[i[0] + 1][i[1]];
		if (below != null) {
			if (below.getHeight() == 1 && below.getWidth() != 1) {
				if (i[1] - below.getWidth() + 1 >= 0 && blocks[i[0]+1][i[1] - below.getWidth() + 1] == below) {
					return moves;
				}
			}
			boolean foundSpace = false;
			for (int j = 0; j < below.getWidth(); j++) {
				foundSpace = false;
				for (int[] k: emptySpaces) {
					if (k[0] == i[0] && k[1] == i[1] + j) {
						foundSpace = true;
						break;
					}
				}
				if (!foundSpace) {
					break;
				}
			}
			if (foundSpace) {
				moves.add(new Move(below, move, i[0] + 1, i[1], i[0], i[1]));
			}
		}
		return moves;
	}
	private ArrayList<Move> checkA (int[] i) {
		ArrayList<Move> moves = new ArrayList<Move>();
		if (i[0] == 0) {
			return moves;
		}
		Block above = blocks[i[0] - 1][i[1]];
		if (above != null) {
			if (above.getHeight() == 1 && above.getWidth() != 1) {
				if (i[1] + above.getWidth() - 1 < width && blocks[i[0]-1][i[1] + above.getWidth() - 1] == above) {
					return moves;
				}
			}
			boolean foundSpace = false;
			int trackingCornerX = i[0] - above.getHeight();
			int trackingCornerY = i[1] - above.getWidth() + 1;
				for (int j = 0; j < above.getWidth(); j++) {
					foundSpace = false;
					for (int[] k: emptySpaces) {
						if (k[0] == i[0] && k[1] == i[1] - j) {
							foundSpace = true;
							break;
						}
					}
					if (!foundSpace) {
						break;
					}
				} if (foundSpace) {
					moves.add(new Move(above, move, trackingCornerX, trackingCornerY, trackingCornerX + 1, trackingCornerY));
			}
		}
		return moves;
	}
	public ArrayList<Move> getPossibleMoves() {

		ArrayList<Move> moves = new ArrayList<Move>();
		
		// Iterate through the empty spaces
		// For each space, look in N,S,E,W to see if there is a block in blocks
		// If there is a row/column of spaces in position, add a move to moves
		// If there are further rows/columns further S/N/W/E, add them also
		for (int[] i: emptySpaces) {
			moves.addAll(checkR(i));
			moves.addAll(checkL(i));
			moves.addAll(checkA(i));
			moves.addAll(checkB(i));
		}
		return moves;
	}
	
	/**
	 * Generates a unique id for this tray.
	 * 
	 * This id is based on the hashcode of the blocks in lexicographical order
	 * (left to right, up to down)
	 * 
	 * @return
	 */
//	private String generateID() {
//		// Iterate through blocks uniquely in deterministic manner
//		// Create new id based on hashcode of each block
//		// Make sure to place empty spaces in correct place ("hashcode = 0")
//		int dimensions = (height-1) * (width-1);
//		intID = new int[dimensions];
//		int c = 0;
//		for (int i=0; i < height; i++) {
//			for (int j=0; j < width; j++) {
//				Block b = blocks[i][j];
//				if (b != null) {
//					intID[c] = b.hashCode();
//				//This modified conditional is Anders' proposed change. Kevin might just want to take this.
//				} else if (!Arrays.asList(emptySpaces).contains(new int[] {i, j})){
//					intID[c] = 0;
//				}
//				c++;
//			}
//		}
//		String strID = intID.toString();
//		return strID;
//	}
	
	private void generateID(String prevID, Move move) {
		
		// Modify prevID based on the given move
		int fromX = move.getFromX();
		int fromY = move.getFromY();
		int toX = move.getToX();
		int toY = move.getToY();
		Block b = move.getBlock();

		// get the 1D array location for a 2D block
		int[] oldBlock = generateIDHelper(b, fromX, fromY);
		int[] newBlock = generateIDHelper(b, toX, toY);

		int blockHash = b.hashCode();

		// set the old block to 0, and the new block to the block hashcode
		char[] idArray = prevID.toCharArray();
		idArray[oldBlock[0]] = '0';
		idArray[oldBlock[1]] = '0';
		idArray[newBlock[0]] = (char) blockHash;
		idArray[newBlock[1]] = (char) blockHash;

		// convert to a String and return
		id = new String(idArray);
		
	}

	/**
	 * returns a [2] array containing the 1D positions of the NW and SE corners of the block
	 */
	private int[] generateIDHelper(Block b, int row, int col) {
		int[] pos = new int[2];

		// get the location of the NW block pointer
		int pos1 = (width * row) + col;
		int hDist = (b.getHeight() - 1) * width;
		int wDist = b.getWidth() - 1;
		// get the location of the SE block pointer
		int pos2 = pos1 + hDist + wDist;

		pos[0] = pos1;
		pos[1] = pos2;
		return pos;
	}
	
	public void setHeuristicDistance(int distance) {
		heuristicDistance = distance;
	}
	
	/**
     * Compares whether the tray satisfies the configuration of 
     * the given goal tray.
     * 
     * @param goalTrayID
     *            The id of the goal tray to compare to
     * @return
     *            True if the tray fits the goal tray
     */
	public boolean equalsGoal(Tray goalTray) {
		// Compare using the tray ids
		boolean equalsGoal = true;
		Block[][] goalBlocks = goalTray.getBlocks();
		int goalHeight = goalTray.getHeight();
		int goalWidth = goalTray.getWidth();
		for (int y = 0; y < goalHeight; y++) {
			for (int x = 0; x < goalWidth; x++) {
				if (goalBlocks[y][x] != null) {
					if (blocks[y][x] == null || goalBlocks[y][x].hashCode() != blocks[y][x].hashCode()) {
						equalsGoal = false;
						break;
					}
				}
			}
		}
		return equalsGoal;
	}
	
	public int compareTo(Object arg0) {
		return heuristicDistance - ((Tray) arg0).heuristicDistance;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public String getID() {
		return id;
	}
	public Block[][] getBlocks() {
		return blocks;
	}
	public int[][] getEmptySpaces() {
		return emptySpaces;
	}

	/**
	 * intID stores an int array to make it easier to recalculate the String id
	 */
	
	public boolean equals(Object obj) {
		return id.equals(((Tray) obj).getID());
	}
	
	public Move getMove() {
		return move;
	}
	
	public int hashCode() {
		// Uses the String hashcode of the id
		return id.hashCode();
	}
	
	public String toString() {
		return id;
	}
}