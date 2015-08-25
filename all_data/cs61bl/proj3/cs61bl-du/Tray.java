import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Tray {
	public int row;
	public int col;
	public int myBoard[][];
	public Tray myParent;
	public ArrayList<Tray> myChildren;
	public HashMap<Integer, Piece> codeToPiece;
	public ArrayList<Integer> goalCode;
	public HashSet<Point> emptyPos;
	public String currentMove;
	public boolean isGoal = false;

	public Tray(ArrayList<String> init, ArrayList<String> goal) {
		myParent = null;
		myChildren = new ArrayList<Tray>();
		codeToPiece = new HashMap<Integer, Piece>();
		goalCode = new ArrayList<Integer>();
		emptyPos = new HashSet<Point>();
		currentMove = null;
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp = convert(init.get(0));
		// Check first init line input
		if (temp.size() != 2) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}
		row = temp.get(0);
		col = temp.get(1);
		// Construct empty board
		myBoard = new int[row][col];
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				myBoard[r][c] = 0;
				emptyPos.add(new Point(c, r));
			}
		}
		// Translate piece line to Tray configuration
		for (int i = 1; i < init.size(); i++) {
			ArrayList<Integer> line = convert(init.get(i));
			// Check the format of input
			if (line.size() != 4 || line.get(0) < 0 || line.get(0) > row - 1
					|| line.get(1) < 0 || line.get(1) > col - 1
					|| line.get(2) < 0 || line.get(2) > row - 1
					|| line.get(3) < 0 || line.get(3) > col - 1) {
				System.out.println("Invalid init and/or goal file.");
				return;
			}
			Piece p = new Piece(line.get(2) - line.get(0) + 1, line.get(3)
					- line.get(1) + 1, line.get(1), line.get(0));
			// Set occupied spot
			for (Point pt : p.occupied) {
				// Check new piece not on already occupied spot
				if (!emptyPos.contains(pt)) {
					System.out.println("Invalid init and/or goal file.");
					return;
				}
				myBoard[pt.y][pt.x] = computePieceCode(line);
				emptyPos.remove(pt);
			}
			Integer pCode = computePieceCode(line);
			codeToPiece.put(pCode, p);
		}
		// Check for big tray special case
		if (emptyPos.size() == 1 && codeToPiece.keySet().size() > 100) {
			Solver.bigTraySpecialCase = true;
		}
		// Check piece number and emptyPos number
		else if (emptyPos.size() > codeToPiece.keySet().size()) {
			Solver.checkPiece = true;
		}
		ArrayList<Point> goalPos = new ArrayList<Point>();
		if (Solver.bigTraySpecialCase) {
			for (Integer code : codeToPiece.keySet()) {
				Piece p = codeToPiece.get(code);
				goalPos.add(new Point(p.x, p.y));
			}
		}
		// Translate goal line to goalCode
		for (int i = 0; i < goal.size(); i++) {
			ArrayList<Integer> line = convert(goal.get(i));
			// Check the format of input
			if (line.size() != 4 || line.get(0) < 0 || line.get(0) > row - 1
					|| line.get(1) < 0 || line.get(1) > col - 1
					|| line.get(2) < 0 || line.get(2) > row - 1
					|| line.get(3) < 0 || line.get(3) > col - 1) {
				System.out.println("Invalid init and/or goal file.");
				return;
			}
			if (!Solver.bigTraySpecialCase) {
				goalCode.add(computePieceCode(line));
			} else {
				goalPos.remove(new Point(line.get(1), line.get(0)));
			}
		}
		if (Solver.bigTraySpecialCase) {
			goalCode = new ArrayList<Integer>();
			goalCode.add(goalPos.get(0).x);
			goalCode.add(goalPos.get(0).y);
		}
		// Add current board configuration to visitedBoard
		Solver.visitedBoard.add(computeBoardCode(codeToPiece));
	}

	public Tray(int row, int col, Tray parent,
			HashMap<Integer, Piece> codeToPiece, ArrayList<Integer> goalCode,
			String currentMove, Integer boardCode, int board[][],
			HashSet<Point> emptyPos) {
		this.row = row;
		this.col = col;
		this.myParent = parent;
		this.codeToPiece = codeToPiece;
		this.goalCode = goalCode;
		this.currentMove = currentMove;
		this.myChildren = new ArrayList<Tray>();
		this.myBoard = board;
		this.emptyPos = emptyPos;
		Solver.visitedBoard.add(boardCode);
	}

	// Convert an input line string to ArrayList<Integer>
	public static ArrayList<Integer> convert(String line) {
		ArrayList<Integer> rtn = new ArrayList<Integer>();
		for (String s : line.split(" ")) {
			try {
				rtn.add(Integer.parseInt(s));
			} catch (NumberFormatException e) {
			}
		}
		return rtn;
	}

	// Generate code for a piece configuration.
	public static Integer computePieceCode(ArrayList<Integer> input) {
		String s = "";
		Integer a = 0;
		for (Integer i : input) {
			s += i.toString();
			s += " ";
			a += i;
		}
		return s.hashCode() + a;
	}

	// Generate code for a board configuration.
	public static Integer computeBoardCode(HashMap<Integer, Piece> codeToPiece) {
		String s = "";
		Integer a = 0;
		for (Integer code : codeToPiece.keySet()) {
			s += codeToPiece.get(code).convert();
			a += code;
		}
		return s.hashCode() + a;
	}

	// Check if current pieceCode contains all the goalCode
	public static boolean isGoal(ArrayList<Integer> goalCode,
			Set<Integer> pieceCode) {
		int goalCount = goalCode.size();
		int count = 0;
		for (Integer code : goalCode) {
			if (pieceCode.contains(code)) {
				count += 1;
			} else {
				return false;
			}
		}
		if (count == goalCount) {
			return true;
		} else {
			return false;
		}
	}

	// Check if the board contains goal configuration. If not, make move and
	// make new Tray node.
	public static void solve(Tray t) {
		if (Solver.bigTraySpecialCase) {
			if (t.emptyPos.contains(new Point(t.goalCode.get(0), t.goalCode
					.get(1)))) {
				t.isGoal = true;
			} else {
				// Check emptyPos
				for (Point p : t.emptyPos) {
					// Up
					if (t.goalCode.get(1) < p.y) {
						t.checkUp(p);
					}
					// Down
					else if (t.goalCode.get(1) > p.y) {
						t.checkBottom(p);
					}
					// Left
					else if (t.goalCode.get(0) < p.x) {
						t.checkLeft(p);
					}
					// Right
					else if (t.goalCode.get(0) > p.x) {
						t.checkRight(p);
					}
				}
			}
		} else {
			if (isGoal(t.goalCode, t.codeToPiece.keySet())) {
				t.isGoal = true;
			} else {
				// Check Piece
				for (Integer code : t.codeToPiece.keySet()) {
					Piece p = t.codeToPiece.get(code);
					t.moveUp(p);
					t.moveDown(p);
					t.moveLeft(p);
					t.moveRight(p);
				}
			}
		}
	}

	// Check valid move. ONLY MOVE ONE SPOT
	public boolean isValidMove(Piece p, Integer x, Integer y) {
		if (x > col - 1 || x < 0 || y > row - 1 || y < 0) {
			return false;
		}
		// UP
		if (y - p.y == -1 && x == p.x) {
			int count = 0;
			for (int i = p.x; i < p.x + p.width; i++) {
				if (myBoard[y][i] == 0) {
					count += 1;
				}
			}
			if (count == p.width) {
				return true;
			} else {
				return false;
			}
		}
		// DOWN
		else if (y - p.y == 1 && x == p.x) {
			if (p.y + p.height > row - 1) {
				return false;
			}
			int count = 0;
			for (int i = p.x; i < p.x + p.width; i++) {
				if (myBoard[p.y + p.height][i] == 0) {
					count += 1;
				}
			}
			if (count == p.width) {
				return true;
			} else {
				return false;
			}
		}
		// LEFT
		else if (x - p.x == -1 && y == p.y) {
			int count = 0;
			for (int i = p.y; i < p.y + p.height; i++) {
				if (myBoard[i][x] == 0) {
					count += 1;
				}
			}
			if (count == p.height) {
				return true;
			} else {
				return false;
			}
		}
		// RIGHT
		else if (x - p.x == 1 && y == p.y) {
			if (p.x + p.width > col - 1) {
				return false;
			}
			int count = 0;
			for (int i = p.y; i < p.y + p.height; i++) {
				if (myBoard[i][p.x + p.width] == 0) {
					count += 1;
				}
			}
			if (count == p.height) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	// Convert old piece coordinates and new piece coordinates to output String
	public String convertMove(Integer oldX, Integer oldY, Integer newX,
			Integer newY) {
		String move = "";
		move += oldY.toString();
		move += " ";
		move += oldX.toString();
		move += " ";
		move += newY.toString();
		move += " ";
		move += newX.toString();
		return move;
	}

	public void checkUp(Point ep) {
		// Check up
		if (ep.y - 1 >= 0 && this.myBoard[ep.y - 1][ep.x] != 0) {
			Piece p = this.codeToPiece.get(this.myBoard[ep.y - 1][ep.x]);
			// Move down
			moveDown(p);
		}
	}

	public void checkBottom(Point ep) {
		// Check bottom
		if (ep.y + 1 < this.row && this.myBoard[ep.y + 1][ep.x] != 0) {
			Piece p = this.codeToPiece.get(this.myBoard[ep.y + 1][ep.x]);
			// Move up
			moveUp(p);
		}
	}

	public void checkLeft(Point ep) {
		// Check left
		if (ep.x - 1 >= 0 && this.myBoard[ep.y][ep.x - 1] != 0) {
			Piece p = this.codeToPiece.get(this.myBoard[ep.y][ep.x - 1]);
			// Move right
			moveRight(p);
		}
	}

	public void checkRight(Point ep) {
		// Check right
		if (ep.x + 1 < this.col && this.myBoard[ep.y][ep.x + 1] != 0) {
			Piece p = this.codeToPiece.get(this.myBoard[ep.y][ep.x + 1]);
			// Move left
			moveLeft(p);
		}
	}

	public void moveUp(Piece p) {
		// Move up
		if (isValidMove(p, p.x, p.y - 1)) {
			movePiece(this, p, p.x, p.y - 1);
		}
	}

	public void moveDown(Piece p) {
		// Move down
		if (isValidMove(p, p.x, p.y + 1)) {
			movePiece(this, p, p.x, p.y + 1);
		}
	}

	public void moveLeft(Piece p) {
		// Move left
		if (isValidMove(p, p.x - 1, p.y)) {
			movePiece(this, p, p.x - 1, p.y);
		}
	}

	public void moveRight(Piece p) {
		// Move right
		if (isValidMove(p, p.x + 1, p.y)) {
			movePiece(this, p, p.x + 1, p.y);
		}
	}

	public void movePiece(Tray t, Piece p, Integer newX, Integer newY) {
		Piece newPiece = new Piece(p.height, p.width, newX, newY);
		HashMap<Integer, Piece> newCodeToPiece = new HashMap<Integer, Piece>();
		newCodeToPiece.putAll(t.codeToPiece);
		newCodeToPiece.remove(computePieceCode(convert(p.convert())));
		Integer newPieceCode = computePieceCode(convert(newPiece.convert()));
		newCodeToPiece.put(newPieceCode, newPiece);
		Integer newBoardCode = computeBoardCode(newCodeToPiece);
		if (!Solver.visitedBoard.contains(newBoardCode)) {
			// Construct new board
			int newBoard[][] = new int[row][col];
			HashSet<Point> newEmptyPos = new HashSet<Point>();
			newEmptyPos.addAll(t.emptyPos);
			for (int r = 0; r < row; r++) {
				for (int c = 0; c < col; c++) {
					newBoard[r][c] = t.myBoard[r][c];
				}
			}
			// Operate new board and new emptyPos
			boardMove(newBoard, newEmptyPos, p, newPiece, newPieceCode);
			Tray child = new Tray(t.row, t.col, t, newCodeToPiece, t.goalCode,
					convertMove(p.x, p.y, newPiece.x, newPiece.y),
					newBoardCode, newBoard, newEmptyPos);
			t.myChildren.add(child);
		}
	}

	public void boardMove(int board[][], HashSet<Point> emptyPos, Piece oldP,
			Piece newP, Integer newPieceCode) {
		for (Point pt : oldP.occupied) {
			board[pt.y][pt.x] = 0;
			emptyPos.add(pt);
		}
		for (Point pt : newP.occupied) {
			board[pt.y][pt.x] = newPieceCode;
			emptyPos.remove(pt);
		}
	}

}
