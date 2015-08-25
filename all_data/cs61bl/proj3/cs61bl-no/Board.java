import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

public class Board {
	private int[] lastMove;
	private int[][] myGoal;
	private Piece[][] board;
	private ArrayList<Piece> pieces;
	private static HashSet<Board> myGraph;
	Board myPrev;

	public Board(int len, int wid, int[][] start, int[][] goal, int[] prevMove)
			throws IOException {
		board = new Piece[len][wid];
		myGoal = goal;
		lastMove = prevMove;
		pieces = new ArrayList<Piece>();
		for (int i = 0; i < start.length; i++) {
			Piece temp = new Piece(start[i]);
			pieces.add(temp);
			int[] coords = start[i];
			for (int j = coords[0]; j <= coords[2]; j++) {
				for (int k = coords[1]; k <= coords[3]; k++) {
					if (board[j][k] != null) {
						throw new IllegalArgumentException();
					}
					board[j][k] = temp;
				}
			}
		}
		myPrev = null;
		myGraph = new HashSet<Board>();
		myGraph.add(this);
	}

	public Board(Piece[][] content, int[][] goal, int[] prevMove, Board prev) {
		board = content;
		myGoal = goal;
		lastMove = prevMove;
		pieces = new ArrayList<Piece>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != null) {
					if (board[i][j].coords()[0] == i
							&& board[i][j].coords()[1] == j) {
						pieces.add(board[i][j]);
					}
				}
			}
		}
		myPrev = prev;
	}

	public Board startGame() {
		if (isGoal()) {
			return this;
		}
		HashSet<Board> moves = move();
		for (Board a : moves) {
			Board result = a.startGame();
			if (result != null) {
				return result;
			}
		}
		return null;
	}

	public Board findPrev() {
		return myPrev;
	}

	public String myMove() {
		String result = "";
		for (int i = 0; i < 4; i++) {
			result += String.valueOf(lastMove[i]);
			result += " ";
		}
		return result;
	}

	public int hashCode() {
		int result = 0;
		for (Piece a : pieces) {
			result += a.hashCode()*a.myCoords[0]*a.myCoords[1];
		}
		return result;
	}

	private void canMoveUp(int[] coords, ArrayList<int[][]> movable) {
		int x = coords[0] - 1;
		boolean empty = true;
		for (int j = coords[1]; j <= coords[3]; j++) {
			if (board[x][j] != null) {
				empty = false;
				break;
			}
		}
		if (empty) {
			int[][] toAdd = new int[2][2];
			toAdd[0] = new int[] { coords[0], coords[1] };
			toAdd[1] = new int[] { x - coords[0], 0 };
			movable.add(toAdd);
		}
	}

	private void canMoveDown(int[] coords, ArrayList<int[][]> movable) {
		int x = coords[2] + 1;
		boolean empty = true;
		for (int j = coords[1]; j <= coords[3]; j++) {
			if (board[x][j] != null) {
				empty = false;
				break;
			}
		}
		if (empty) {
			int[][] toAdd = new int[2][2];
			toAdd[0] = new int[] { coords[2], coords[3] };
			toAdd[1] = new int[] { x - coords[2], 0 };
			movable.add(toAdd);
		}
	}

	private void canMoveLeft(int[] coords, ArrayList<int[][]> movable) {
		int y = coords[1] - 1;
		boolean empty = true;
		for (int j = coords[0]; j <= coords[2]; j++) {
			if (board[j][y] != null) {
				empty = false;
				break;
			}
		}
		if (empty) {
			int[][] toAdd = new int[2][2];
			toAdd[0] = new int[] { coords[0], coords[1] };
			toAdd[1] = new int[] { 0, y - coords[1] };
			movable.add(toAdd);
		}
	}

	private void canMoveRight(int[] coords, ArrayList<int[][]> movable) {
		int y = coords[3] + 1;
		boolean empty = true;
		for (int j = coords[0]; j <= coords[2]; j++) {
			if (board[j][y] != null) {
				empty = false;
				break;
			}
		}
		if (empty) {
			int[][] toAdd = new int[2][2];
			toAdd[0] = new int[] { coords[2], coords[3] };
			toAdd[1] = new int[] { 0, y - coords[3] };
			movable.add(toAdd);
		}
	}

	private ArrayList<int[][]> canMove() {
		ArrayList<int[][]> movable = new ArrayList<int[][]>();
		for (int i = 0; i < pieces.size(); i++) {
			Piece toMove = pieces.get(i);
			int[] coords = toMove.coords();
			// down to up
			if (coords[0] != 0) {
				canMoveUp(coords, movable);
			}
			// right to left
			if (coords[1] != 0) {
				canMoveLeft(coords, movable);
			}
			// up to down
			if (coords[2] != board.length - 1) {
				canMoveDown(coords, movable);
			}
			//
			if (coords[3] != board[0].length - 1) {
				canMoveRight(coords, movable);
			}
		}
		return movable;
	}

	public HashSet<Board> move() {
		HashSet<Board> result = new HashSet<Board>();
		ArrayList<int[][]> movable = canMove();
		for (int[][] comb : movable) {
			int[] move = comb[1];
			Piece[][] newBoard = copyBoard();

			Piece toMove = newBoard[comb[0][0]][comb[0][1]];
			int[] pieceCoords = toMove.coords();
			int[] myMove = new int[] { pieceCoords[0], pieceCoords[1],
					pieceCoords[0] + move[0], pieceCoords[1] + move[1] };
			if (move[0] > 0) {
				moveUp(move, newBoard, toMove, pieceCoords);
			} else if (move[0] < 0) {
				moveDown(move, newBoard, toMove, pieceCoords);
			} else if (move[1] > 0) {
				moveRight(move, newBoard, toMove, pieceCoords);
			} else if (move[1] < 0) {
				moveLeft(move, newBoard, toMove, pieceCoords);
			}
			Board moved = new Board(newBoard, myGoal, myMove, this);
			if (!myGraph.contains(moved)) {
				myGraph.add(moved);
				result.add(moved);
			}
		}
		return result;
	}

	private void moveUp(int[] move, Piece[][] newBoard, Piece toMove,
			int[] pieceCoords) {
		int x = pieceCoords[0];
		for (int pos = 0; pos < move[0]; pos++) {
			for (int i = pieceCoords[1]; i <= pieceCoords[3]; i++) {
				newBoard[x + pos][i] = null;
				newBoard[pieceCoords[2] + pos + 1][i] = toMove;
			}
		}
		pieceCoords[2] = pieceCoords[2] + move[0];
		pieceCoords[0] = pieceCoords[0] + move[0];
	}

	private void moveDown(int[] move, Piece[][] newBoard, Piece toMove,
			int[] pieceCoords) {
		int x = pieceCoords[2];
		for (int pos = 0; pos > move[0]; pos--) {
			for (int i = pieceCoords[1]; i <= pieceCoords[3]; i++) {
				newBoard[x + pos][i] = null;
				newBoard[pieceCoords[0] + pos - 1][i] = toMove;
			}
		}
		pieceCoords[2] = pieceCoords[2] + move[0];
		pieceCoords[0] = pieceCoords[0] + move[0];
	}

	private void moveLeft(int[] move, Piece[][] newBoard, Piece toMove,
			int[] pieceCoords) {
		int y = pieceCoords[3];
		for (int pos = 0; pos > move[1]; pos--) {
			for (int i = pieceCoords[0]; i <= pieceCoords[2]; i++) {
				newBoard[i][y + pos] = null;
				newBoard[i][pieceCoords[1] + pos - 1] = toMove;
			}
		}
		pieceCoords[3] = pieceCoords[3] + move[1];
		pieceCoords[1] = pieceCoords[1] + move[1];
	}

	private void moveRight(int[] move, Piece[][] newBoard, Piece toMove,
			int[] pieceCoords) {
		int y = pieceCoords[1];
		for (int pos = 0; pos < move[1]; pos++) {
			for (int i = pieceCoords[0]; i <= pieceCoords[2]; i++) {
				newBoard[i][y + pos] = null;
				newBoard[i][pieceCoords[3] + pos + 1] = toMove;
			}
		}
		pieceCoords[3] = pieceCoords[3] + move[1];
		pieceCoords[1] = pieceCoords[1] + move[1];
	}

	private Piece[][] copyBoard() {
		Piece[][] copy = new Piece[board.length][board[0].length];
		for (int n = 0; n < pieces.size(); n++) {
			Piece a = pieces.get(n);
			int[] coords = a.myCoords;
			int[] copyCoords = new int[4];
			for (int i = 0; i < 4; i++) {
				copyCoords[i] = coords[i];
			}
			Piece copyPiece = new Piece(copyCoords);
			for (int i = coords[0]; i <= coords[2]; i++) {
				for (int j = coords[1]; j <= coords[3]; j++) {
					copy[i][j] = copyPiece;
				}
			}
		}
		return copy;
	}

	public boolean isGoal() {
		boolean[] value = new boolean[myGoal.length];
		for (Piece a : pieces) {
			int[] coords = a.coords();
			for (int k = 0; k < value.length; k++) {
				if (Arrays.equals(coords, myGoal[k])) {
					value[k] = true;
				}
			}
		}
		for (boolean check : value) {
			if (!check)
				return false;
		}
		return true;
	}

	@Override
	public boolean equals(Object a) {
		Board b = (Board) a;
		Piece[][] bBoard = b.board;
		if (bBoard.length != board.length
				|| bBoard[0].length != board[0].length) {
			return false;
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == null) {
					if (bBoard[i][j] != null)
						return false;
				} else {
					if (!board[i][j].equals(bBoard[i][j]))
						return false;
				}
			}
		}
		return true;
	}

	class Piece {
		private int[] myCoords;

		public Piece(int[] coords) {
			myCoords = coords;
		}

		public int[] coords() {
			return myCoords;
		}

		public int hashCode() {
			String result = new String();
			for (int i = 3; i >= 0; i--) {
				result += String.valueOf(myCoords[i]);
			}
			int toReturn = 0;
			try {
				toReturn = Integer.parseInt(result);
			} catch (NumberFormatException e) {
				long temp = Long.parseLong(result);
				while (temp > Integer.MAX_VALUE) {
					temp = temp / 10 + temp % 10;
				}
				toReturn = (int) temp;
			}
			return toReturn;
		}

		@Override
		public boolean equals(Object piece) {
			Piece a = (Piece) piece;
			if (a == null) {
				return false;
			}
			for (int i = 0; i < 4; i++) {
				if (a.coords()[i] != myCoords[i]) {
					return false;
				}
			}
			return true;
		}
	}

}