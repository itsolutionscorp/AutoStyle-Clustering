import java.util.HashSet;
import java.util.LinkedList;

public class Board {

	private int[][] board;
	private String move;
	private HashSet<String> config;
	private HashSet<String> goal;
	private Board myParent;

	public Board(int i, int j, String move, HashSet<String> config,
			HashSet<String> goal, Board myParent) {
		this.board = new int[i][j];
		this.move = move;
		this.config = config;
		this.goal = goal;
		this.myParent = myParent;
		processBoard(config);
	}
	
	public void processBoard(HashSet<String> config) {
		for (String s : config) {
			String[] splitBlock = s.split("\\s+");
			int[] blockInt = new int[4];
			for (int j = 0; j < 4; j++) {
				blockInt[j] = Integer.parseInt(splitBlock[j]);
			}
			for (int i = blockInt[0]; i <= blockInt[2]; i++) {
				for (int j = blockInt[1]; j <= blockInt[3]; j++) {
					this.board[i][j] = 1;
				}
			}
		}
	}
	
	public LinkedList<Board> buildChildren() {
		LinkedList<Board> children = new LinkedList<Board>();
		for (String s : config) {
			String[] block = s.split("\\s+");
			int[] blockInt = new int[4];
			for (int i = 0; i < 4; i++) {
				blockInt[i] = Integer.parseInt(block[i]);
			}
			if (validUpMove(blockInt)) {
				String upMove = blockInt[0] + " " + blockInt[1] + " " + (blockInt[0] - 1) + " " + blockInt[1];
				String newSpot = (blockInt[0] - 1) + " " + blockInt[1] + " " + (blockInt[2] - 1) + " " + blockInt[3];
				HashSet<String> newConfig = new HashSet<String>();
				for (String st : config) {
					newConfig.add(st);
				}
				newConfig.remove(s);
				newConfig.add(newSpot);
				Board upBoard = new Board(board.length, board[0].length, upMove, newConfig, goal, this);
				children.add(upBoard);
			}
			if (validDownMove(blockInt)) {
				String downMove = blockInt[0] + " " + blockInt[1] + " " + (blockInt[0] + 1) + " " + blockInt[1];
				String newSpot = (blockInt[0] + 1) + " " + blockInt[1] + " " + (blockInt[2] + 1) + " " + blockInt[3];
				HashSet<String> newConfig = new HashSet<String>();
				for (String st : config) {
					newConfig.add(st);
				}
				newConfig.remove(s);
				newConfig.add(newSpot);
				Board downBoard = new Board(board.length, board[0].length, downMove, newConfig, goal, this);
				children.add(downBoard);
			}
			if (validLeftMove(blockInt)) {
				String leftMove = blockInt[0] + " " + blockInt[1] + " " + blockInt[0] + " " + (blockInt[1] - 1);
				String newSpot = blockInt[0] + " " + (blockInt[1] - 1) + " " + blockInt[2] + " " + (blockInt[3] - 1);
				HashSet<String> newConfig = new HashSet<String>();
				for (String st : config) {
					newConfig.add(st);
				}
				newConfig.remove(s);
				newConfig.add(newSpot);
				Board leftBoard = new Board(board.length, board[0].length, leftMove, newConfig, goal, this);
				children.add(leftBoard);
			}
			if (validRightMove(blockInt)) {
				String rightMove = blockInt[0] + " " + blockInt[1] + " " + blockInt[0] + " " + (blockInt[1] + 1);
				String newSpot = blockInt[0] + " " + (blockInt[1] + 1) + " " + blockInt[2] + " " + (blockInt[3] + 1);
				HashSet<String> newConfig = new HashSet<String>();
				for (String st : config) {
					newConfig.add(st);
				}
				newConfig.remove(s);
				newConfig.add(newSpot);
				Board rightBoard = new Board(board.length, board[0].length, rightMove, newConfig, goal, this);
				children.add(rightBoard);
			}
		}
		return children;
	}
	
	public boolean validUpMove(int[] piece) {
		int row = piece[0] - 1;
		if (row >= 0) {
			for (int i = piece[1]; i <= piece[3]; i++) {
				if (board[row][i] != 0) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	public boolean validDownMove(int[] piece) {
		int row = piece[2] + 1;
		if (row < board.length) {
			for (int i = piece[1]; i <= piece[3]; i++) {
				if (board[row][i] != 0) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	public boolean validLeftMove(int[] piece) {
		int column = piece[1] - 1;
		if (column >= 0) {
			for (int i = piece[0]; i <= piece[2]; i++) {
				if (board[i][column] != 0) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	public boolean validRightMove(int[] piece) {
		int column = piece[3] + 1;
		if (column < board[0].length) {
			for (int i = piece[0]; i <= piece[2]; i++) {
				if (board[i][column] != 0) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isGoal() {
		for (String s : goal) {
			if (!config.contains(s)) {
				return false;
			}
		}
		return true;
	}
	
	public void getSolveMoves() {
		LinkedList<String> allMoves = new LinkedList<String>();
		Board pointer = this;
		while (pointer.myParent != null) {
			allMoves.add(0, pointer.move);
			pointer = pointer.myParent;
		}
		for (String s : allMoves) {
			System.out.println(s);
		}
	}
	
	@Override
	public boolean equals(Object o) {
		Board other = (Board) o;
		HashSet<String> otherConfig = other.config;
		for (String s : config) {
			if (!otherConfig.contains(s)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		for (String s : config) {
			hash += s.hashCode();
		}
		return hash % 1213;
	}
	
}
