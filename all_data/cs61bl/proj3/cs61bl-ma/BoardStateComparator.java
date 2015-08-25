import java.util.Comparator;


public class BoardStateComparator implements Comparator<BoardState>{
	BoardState endState; 
	
	public BoardStateComparator(BoardState endState) {
		this.endState = endState; 
	}
	
	@Override
	public int compare(BoardState bs1, BoardState bs2) {
		int bs1winNum = winNum(bs1);
		int bs2winNum = winNum(bs2);
		if (bs1winNum > bs2winNum) {
			return -1;
		} else if (bs1winNum < bs2winNum) {
			return 1;
		} else {
			return 0; 
		}
	}
	
	public int winNum(BoardState bs) {
		Block[][] board = bs.getBoard();
		int count = 0; 
		for (Block b : endState.getBlocks()) {
			boolean match = true;
			outer:
			for (int i = b.getTopLeftX(); i <= b.getBotRightX(); i++) {
				for (int j = b.getTopLeftY(); j <= b.getBotRightY(); j++) {
					if (!(b.equals(board[i][j]))) {
						match = false; 
						break outer;
					}
				}
			}
			if (match) {
				count++;
			}
		}
		return count;
	}
	
}
