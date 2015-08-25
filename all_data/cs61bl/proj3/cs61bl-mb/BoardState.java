
import java.util.ArrayList;

public class BoardState {
	
	private Block[][] board; 
	private ArrayList<Block> blocks; 
	private int xSize; 
	private int ySize; 
	private String previousMove;
	private BoardState previousBoardState;
	
	public BoardState(ArrayList<Block> blocks, int xSize, int ySize) {
		board = new Block[xSize][ySize];
		this.blocks = blocks;
		this.xSize = xSize;
		this.ySize = ySize; 
		for (Block b : blocks) {
			for (int i = b.getTopLeftX(); i <= b.getBotRightX(); i++) {
				for (int j = b.getTopLeftY(); j <= b.getBotRightY(); j++) {
					board[i][j] = b; 
				}
			}
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof BoardState) {
			BoardState otherBoardState = (BoardState) o; 
			if (otherBoardState.xSize != xSize || otherBoardState.ySize != ySize) {
				return false;
			} else {
				Block[][] otherBoard = otherBoardState.getBoard();
				for (int i = 0; i < otherBoardState.xSize; i++) {
					for (int j = 0; j < otherBoardState.ySize; j++) {
						if (board[i][j] == null) {
							if (otherBoard[i][j] != null) {
								return false;
							}
						} else {
							if (!board[i][j].equals(otherBoard[i][j])) {
								return false;
							}
						}
					}
				}
				return true;
			} 
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int sum = 1; 
		for (int i = 0; i < xSize; i++) {
			for (int j = 0; j < ySize; j++) {
				if ( board[i][j] != null) {
					sum += board[i][j].hashCode()*(i*j);
				}
			}
		}
		return sum; 
	}
	
	//returns arraylist of all possible states resulting from legally moving block b 
	public ArrayList<BoardState> possibleMoves(Block b) {
		int tlx = b.getTopLeftX();
		int tly = b.getTopLeftY();
		int brx = b.getBotRightX();
		int bry = b.getBotRightY();
		ArrayList<BoardState> states = new ArrayList<BoardState>();
		if ( !(tlx -1 < 0) ) {
			outer: 
				for (int i = tlx - 1; i >= 0; i--) {
					for (int j = tly; j <= bry; j++) {
						if (board[i][j] != null) {
							break outer;
						}
					}
					states.add(move(tlx, tly, i, tly));
				}
		}
		if ( !(bry + 1 > ySize - 1) ) {
			int count = 1; 
			outer:
				for (int i = bry + 1; i < ySize; i++) {
					for (int j = tlx; j <= brx; j++) {
						if (board[j][i] != null) {
							break outer;
						}
					}
					states.add(move(tlx, tly, tlx, tly + count));
					count++;
				}
		}
		if ( !(brx + 1 > xSize - 1) ) {
			int count = 1;
			outer: 
				for (int i = brx + 1; i < xSize; i++) {
					for (int j = tly; j <= bry; j++) {
						if (board[i][j] != null) {
							break outer;
						}
					}
					states.add(move(tlx, tly, tlx + count, tly));
					count++;
				}
		}
		if ( !(tly - 1 < 0) ) {
			outer:
				for (int i = tly -1; i >= 0; i--) {
					for (int j = tlx; j <= brx; j++) {
						if (board[j][i] != null) {
							break outer;
						}
					}
					states.add(move(tlx, tly, tlx, i));
				}
		}
		return states;
 	}
	
	public BoardState move(int tlx, int tly, int ntlx, int ntly) {
		Block blockRemoved = board[tlx][tly]; 
		ArrayList<Block> newBlocks = new ArrayList<Block>();
		for (Block b : blocks) {
			if (b != blockRemoved) {
				newBlocks.add(new Block(b)); 
			}
		}
		newBlocks.add(new Block(ntlx, ntly, ntlx + blockRemoved.getHeight(), ntly + blockRemoved.getWidth()));
		BoardState newBoardState = new BoardState(newBlocks, xSize, ySize); 
		newBoardState.setPreviousMove(tlx + " " + tly + " " + ntlx + " " + ntly);
		newBoardState.setPreviousBoardState(this);
		return newBoardState;
	}

	public void print() {
		for (int i = 0; i < xSize; i++) {
			for (int j = 0; j < ySize; j++) {
				if (board[i][j] == null) {
					System.out.print(" x ");
				} else {
					System.out.print(" * ");
				}
			}
			System.out.println();
		}
	}
	
	public Block[][] getBoard() {
		return board;
	}
	
	public int getXSize() {
		return xSize;
	}
	
	public int getYSize() {
		return ySize;
	}
	
	public ArrayList<Block> getBlocks() {
		return blocks;
	}
	
	public String getPreviousMove() {
		return previousMove;
	}
	
	public void setPreviousMove(String move) {
		previousMove = move; 
	}
	
	public BoardState getPreviousBoardState() {
		return previousBoardState;
	}
	
	public void setPreviousBoardState(BoardState state) {
		previousBoardState = state; 
	}
}
