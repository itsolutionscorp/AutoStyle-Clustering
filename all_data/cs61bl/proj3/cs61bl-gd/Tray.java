import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.Map.Entry;

public class Tray {
	static List<int[]> myGoal = new ArrayList<int[]>();
	static Set<Board> visited = new HashSet<Board>();
	static Map<Board, Edge> steps = new HashMap<Board, Edge>();
	static List<Point> targetEmpty = new ArrayList<Point>();
	static int yl, xl;
	Board myTray;
	Map<Point, Point> coorSize;
	Set<Point> space;
	double cost;

	public Tray(String path1, String path2) {
		coorSize = new HashMap<Point, Point>();
		myTray = new Board(initArray(path1, coorSize));
		finalArray(path2);
		checkIfGoalPossible();
		space = new HashSet<Point>();
		initEmpty(myTray);
		targetEmptySpace();
	}

	private Tray(Board myTray, Map<Point, Point> coorSize, Set<Point> space) {
		this.myTray = myTray;
		this.coorSize = coorSize;
		this.space = space;
	}

	private Point[][] initArray(String path, Map<Point, Point> coorSize) {
		try {
			Scanner sc = new Scanner(new File(path));
			Point[][] temp = new Point[sc.nextInt()][sc.nextInt()];
			yl = temp.length;
			xl = temp[0].length;
			while (sc.hasNext()) {
				int[] k = new int[4];
				for (int i = 0; i < 4; i++) {
					if (!sc.hasNext()) {
						sc.close();
						throw new IllegalArgumentException();
					}
					k[i] = sc.nextInt();
				}
				if (k[0] >= yl || k[0] < 0 || k[2] >= yl || k[2] < 0
						|| k[1] >= xl || k[1] < 0 || k[3] >= xl || k[3] < 0) {
					sc.close();
					throw new IllegalArgumentException();
				}
				if (k[1] > k[3] || k[0] > k[2]) {
					sc.close();
					throw new IllegalArgumentException();
				}
				Point p = new Point(k[1], k[0]);
				Point size = new Point(k[3] - k[1] + 1, k[2] - k[0] + 1);
				for (int y = k[0]; y <= k[2]; y++) {
					for (int x = k[1]; x <= k[3]; x++) {
						if (temp[y][x] != null) {
							sc.close();
							throw new IllegalArgumentException();
						}
						temp[y][x] = p;
						coorSize.put(p, size);

					}
				}
			}
			sc.close();
			return temp;
		} catch (Exception e) {
			System.out.println("Invalid init and/or goal file.");
			System.exit(1);
			return null;
		}
	}

	private void initEmpty(Board myTray) {
		Point[][] board = myTray.myBoard;
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[0].length; x++) {
				if (board[y][x] == null) {
					space.add(new Point(x, y));
				}
			}
		}
	}

	private static void finalArray(String path) {
		try {
			Scanner sc = new Scanner(new File(path));
			myGoal = new ArrayList<int[]>();
			while (sc.hasNext()) {
				int[] k = new int[4];
				for (int i = 0; i < 4; i++) {
					if (!sc.hasNext()) {
						sc.close();
						throw new IllegalArgumentException();
					}
					k[i] = sc.nextInt();
				}
				if (k[0] >= yl || k[0] < 0 || k[2] >= yl || k[2] < 0
						|| k[1] >= xl || k[1] < 0 || k[3] >= xl || k[3] < 0) {
					sc.close();
					throw new IllegalArgumentException();
				}
				if (k[1] > k[3] || k[0] > k[2]) {
					sc.close();
					throw new IllegalArgumentException();
				}
				myGoal.add(k);
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("Invalid init and/or goal file.");
			System.exit(1);
		}
	}

	private void checkIfGoalPossible() {
		try {
			Map<Point, Integer> blockFrequency = new HashMap<Point, Integer>();
			for (Entry<Point, Point> entry : coorSize.entrySet()) {
				if (!blockFrequency.containsKey(entry.getValue())) {
					blockFrequency.put(entry.getValue(), 1);
				} else {
					blockFrequency.put(entry.getValue(),
							blockFrequency.get(entry.getValue()) + 1);
				}
			}
			Map<Point, Integer> goalBlockFrequency = new HashMap<Point, Integer>();
			for (int[] k : myGoal) {
				Point temp = new Point(k[3] - k[1] + 1, k[2] - k[0] + 1);
				if (!goalBlockFrequency.containsKey(temp)) {
					goalBlockFrequency.put(temp, 1);
				} else {
					goalBlockFrequency.put(temp, blockFrequency.get(temp) + 1);
				}
			}
			for (Entry<Point, Integer> entry : goalBlockFrequency.entrySet()) {
				if (!blockFrequency.containsKey(entry.getKey())) {
					throw new Exception();
				} else if (blockFrequency.get(entry.getKey()) < entry
						.getValue()) {
					throw new Exception();
				}
			}
		} catch (Exception e) {
			System.out.println("Invalid init and/or goal file.");
			System.exit(1);
		}
	}

	private static boolean isFinished(Tray t) {
		for (int[] i : myGoal) {
			int y1, y2, x1, x2;
			y1 = i[0];
			x1 = i[1];
			y2 = i[2];
			x2 = i[3];
			Point p1 = new Point(x1, y1);
			if (!t.coorSize.containsKey(p1)) {
				return false;
			} else {
				Point size = t.coorSize.get(p1);

				if (((y2 != y1 + size.y - 1) || (x2 != x1 + size.x - 1))) {
					return false;
				}
			}
		}
		return true;
	}

	private void targetEmptySpace() {
		int[][] temp = new int[myTray.myBoard.length][myTray.myBoard[0].length];
		int m = 1;
		for (int[] k : myGoal) {
			for (int y = k[0]; y <= k[2]; y++) {
				for (int x = k[1]; x <= k[3]; x++) {
					if (temp[y][x] != 0) {
						throw new IllegalArgumentException("wrong board");
					}
					temp[y][x] = m;
				}
			}
			m++;
		}
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				if (temp[i][j] == 0) {
					targetEmpty.add(new Point(j, i));
				}
			}
		}
	}

	public static List<Move> Path(Tray tray) {
		Board t;
		if (targetEmpty.size() == 1) {
			t = pathAStarBigTray(tray);
		} else if (tray.space.size() > tray.coorSize.size()) {
			t = pathFromPiece(tray);
		} else {
			t = Tray.path(tray);
		}

		if (t != null) {
			Stack<Move> temp = new Stack<Move>();
			Edge k = Tray.steps.get(t);
			while (k.prevTray != null) {
				temp.push(k.move);
				t = k.prevTray.myTray;
				k = Tray.steps.get(t);
			}
			List<Move> moves = new ArrayList<Move>();
			while (!temp.isEmpty()) {
				moves.add(temp.pop());
			}
			return moves;
		}
		return new ArrayList<Move>();
	}

	private static Board path(Tray tray) {
		if (Tray.visited.contains(tray.myTray)) {
			return null;
		} else {
			steps.put(tray.myTray, new Edge(null, tray, null));
			List<Move> possibleMoves = possibleMove(tray);
			Stack<Edge> possibles = new Stack<Edge>();
			possibles.push(new Edge(null, tray, null));
			while (!possibles.isEmpty()) {
				Edge e = possibles.pop();
				tray = e.currentTray;
				if (!visited.contains(tray.myTray)) {
					visited.add(tray.myTray);
					if (Tray.isFinished(tray)) {
						return e.currentTray.myTray;
					}
					possibleMoves = possibleMove(tray);
					for (Move move : possibleMoves) {
						Tray temp = Tray.copyTray(tray);
						temp.movePiece(move);
						Edge tempEdge = new Edge(tray, temp, move);
						if (!steps.containsKey(temp.myTray)) {
							steps.put(temp.myTray, tempEdge);
							possibles.add(tempEdge);
						}
					}
				}
			}
			return null;
		}
	}

	private static Board pathAStarBigTray(Tray tray) {
		if (Tray.visited.contains(tray.myTray)) {
			return null;
		} else {
			steps.put(tray.myTray, new Edge(null, tray, null));
			tray.determineCostBigTray();
			Comparator<Edge> c = new EdgeComparator();
			List<Move> possibleMoves = possibleMove(tray);
			Queue<Edge> possibles = new PriorityQueue<Edge>(10, c);
			possibles.add(new Edge(null, tray, null));
			while (!possibles.isEmpty()) {
				Edge e = possibles.remove();
				tray = e.currentTray;
				if (!visited.contains(tray.myTray)) {
					visited.add(tray.myTray);
					if (Tray.isFinished(tray)) {
						return e.currentTray.myTray;
					}
					possibleMoves = possibleMove(tray);
					for (Move move : possibleMoves) {
						Tray temp = Tray.copyTray(tray);
						temp.movePiece(move);
						temp.determineCostBigTray();
						Edge tempEdge = new Edge(tray, temp, move);
						if (!steps.containsKey(temp.myTray)) {
							steps.put(temp.myTray, tempEdge);
							possibles.add(tempEdge);
						}
					}
				}
			}
			return null;
		}
	}

	private void determineCostBigTray() {
		double sum = 0;
		for (Point empty : targetEmpty) {
			for (Point currentEmpty : space) {
				sum += Math.abs(empty.getX() - currentEmpty.getX())
						+ Math.abs(empty.getY() - currentEmpty.getY());
			}
		}
		cost = sum;
	}

	private static Board pathFromPiece(Tray tray) {
		if (Tray.visited.contains(tray.myTray)) {
			return null;
		} else {
			steps.put(tray.myTray, new Edge(null, tray, null));
			tray.determineCostFromPiece();
			Comparator<Edge> c = new EdgeComparator();
			List<Move> possibleMoves = possibleMoveFromPiece(tray);
			Queue<Edge> possibles = new PriorityQueue<Edge>(10, c);
			possibles.add(new Edge(null, tray, null));
			while (!possibles.isEmpty()) {
				Edge e = possibles.remove();
				tray = e.currentTray;
				if (!visited.contains(tray.myTray)) {
					visited.add(tray.myTray);
					if (Tray.isFinished(tray)) {
						return e.currentTray.myTray;
					}
					possibleMoves = possibleMoveFromPiece(tray);
					for (Move move : possibleMoves) {
						Tray temp = Tray.copyTray(tray);
						temp.movePiece(move);
						temp.determineCostFromPiece();
						Edge tempEdge = new Edge(tray, temp, move);
						if (!steps.containsKey(temp.myTray)) {
							steps.put(temp.myTray, tempEdge);
							possibles.add(tempEdge);
						}
					}
				}
			}
			return null;
		}
	}

	private void determineCostFromPiece() {
		double sum = 0;
		for (int[] k : myGoal) {
			int v = k[2] - k[0] + 1;
			int h = k[3] - k[1] + 1;
			for (Entry<Point, Point> entry : coorSize.entrySet()) {
				Point coor = entry.getKey();
				Point size = entry.getValue();
				if (size.equals(new Point(h, v))) {
					sum += Math.abs(coor.getX() - k[1])
							+ Math.abs(coor.getY() - k[0]);
				}
			}
		}
		cost = sum;
	}

	private void movePiece(Move m) {
		Point[][] board = myTray.myBoard;
		int[] move = m.myMove;
		int x1, x2, y1, y2;
		y1 = move[0];
		x1 = move[1];
		y2 = move[2];
		x2 = move[3];
		Point p1 = new Point(x1, y1);
		Point p2 = new Point(x2, y2);
		Point size = coorSize.remove(p1);
		int v = (int) size.getY();
		int h = (int) size.getX();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < v; j++) {
				board[y1 + j][x1 + i] = null;
			}
		}
		if (y2 - y1 == 1) {
			for (int i = 0; i < h; i++) {
				space.add(new Point(x1 + i, y1));
				space.remove(new Point(x1 + i, y1 + v));
				for (int j = 0; j < v; j++) {
					board[y2 + j][x1 + i] = p2;
				}
			}
			coorSize.put(p2, size);
		} else if (y1 - y2 == 1) {
			for (int i = 0; i < h; i++) {
				space.add(new Point(x1 + i, y2 + v));
				space.remove(new Point(x1 + i, y2));
				for (int j = 0; j < v; j++) {
					board[y2 + j][x1 + i] = p2;
				}
			}
			coorSize.put(p2, size);
		} else if (x1 - x2 == 1) {
			for (int i = 0; i < v; i++) {
				space.add(new Point(x2 + h, y1 + i));
				space.remove(new Point(x2, y1 + i));
				for (int j = 0; j < h; j++) {
					board[y1 + i][x2 + j] = p2;
				}
			}
			coorSize.put(p2, size);
		} else if (x2 - x1 == 1) {
			for (int i = 0; i < v; i++) {
				space.add(new Point(x1, y1 + i));
				space.remove(new Point(x1 + h, y1 + i));
				for (int j = 0; j < h; j++) {
					board[y1 + i][x2 + j] = p2;
				}
			}
			coorSize.put(p2, size);
		}
	}

	private static List<Move> possibleMove(Tray tray) {
		Set<Move> moves = new HashSet<Move>();
		List<Move> toReturn = new ArrayList<Move>();
		for (Point spaceCoor : tray.space) {
			int ycoor = (int) spaceCoor.getY();
			int xcoor = (int) spaceCoor.getX();
			if (xcoor > 0) {
				if (tray.myTray.myBoard[ycoor][xcoor - 1] != null) {
					Point coor = tray.myTray.myBoard[ycoor][xcoor - 1];
					Point size = tray.coorSize.get(coor);
					boolean canMove = true;
					int vcoor = (int) coor.getY();
					for (int v = 0; v < size.getY(); v++) {
						try {
							if (tray.myTray.myBoard[vcoor + v][xcoor] != null) {
								canMove = false;
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							canMove = false;
						}
					}
					if (canMove) {
						int[] temp = new int[4];
						temp[0] = (int) coor.getY();
						temp[1] = (int) coor.getX();
						temp[2] = temp[0];
						temp[3] = temp[1] + 1;
						moves.add(new Move(temp));
					}
				}
			}
			if (xcoor + 1 < tray.myTray.myBoard[0].length) {
				if (tray.myTray.myBoard[ycoor][xcoor + 1] != null) {
					Point coor = tray.myTray.myBoard[ycoor][xcoor + 1];
					Point size = tray.coorSize.get(coor);
					boolean canMove = true;
					int vcoor = (int) coor.getY();
					for (int v = 0; v < size.getY(); v++) {
						try {
							if (tray.myTray.myBoard[vcoor + v][xcoor] != null) {
								canMove = false;
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							canMove = false;
						}
					}
					if (canMove) {
						int[] temp = new int[4];
						temp[0] = (int) coor.getY();
						temp[1] = (int) coor.getX();
						temp[2] = temp[0];
						temp[3] = temp[1] - 1;
						moves.add(new Move(temp));
					}
				}
			}
			if (ycoor > 0) {
				if (tray.myTray.myBoard[ycoor - 1][xcoor] != null) {
					Point coor = tray.myTray.myBoard[ycoor - 1][xcoor];
					Point size = tray.coorSize.get(coor);
					boolean canMove = true;
					int hcoor = (int) coor.getX();
					for (int h = 0; h < size.getX(); h++) {
						try {
							if (tray.myTray.myBoard[ycoor][hcoor + h] != null) {
								canMove = false;
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							canMove = false;
						}
					}
					if (canMove) {
						int[] temp = new int[4];
						temp[0] = (int) coor.getY();
						temp[1] = (int) coor.getX();
						temp[2] = temp[0] + 1;
						temp[3] = temp[1];
						moves.add(new Move(temp));
					}
				}
			}
			if (ycoor + 1 < tray.myTray.myBoard.length) {
				if (tray.myTray.myBoard[ycoor + 1][xcoor] != null) {
					Point coor = tray.myTray.myBoard[ycoor + 1][xcoor];
					Point size = tray.coorSize.get(coor);
					boolean canMove = true;
					int hcoor = (int) coor.getX();
					for (int h = 0; h < size.getX(); h++) {
						try {
							if (tray.myTray.myBoard[ycoor][hcoor + h] != null) {
								canMove = false;
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							canMove = false;
						}
					}
					if (canMove) {
						int[] temp = new int[4];
						temp[0] = (int) coor.getY();
						temp[1] = (int) coor.getX();
						temp[2] = temp[0] - 1;
						temp[3] = temp[1];
						moves.add(new Move(temp));
					}
				}
			}
		}
		for (Move i : moves) {
			toReturn.add(i);
		}
		return toReturn;
	}

	private static List<Move> possibleMoveFromPiece(Tray tray) {
		List<Move> moves = new ArrayList<Move>();
		for (Entry<Point, Point> entry : tray.coorSize.entrySet()) {
			Point coor = entry.getKey();
			Point size = entry.getValue();
			if (coor.getX() > 0) {
				int xcoor = (int) coor.getX() - 1;
				boolean canMove = true;
				int vcoor = (int) coor.getY();
				for (int v = 0; v < size.getY(); v++) {
					try {
						if (tray.myTray.myBoard[vcoor + v][xcoor] != null) {
							canMove = false;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						canMove = false;
					}
				}
				if (canMove) {
					int[] temp = new int[4];
					temp[0] = (int) coor.getY();
					temp[1] = (int) coor.getX();
					temp[2] = temp[0];
					temp[3] = temp[1] - 1;
					moves.add(new Move(temp));
				}
			}
			if (coor.getX() + size.getX() < tray.myTray.myBoard[0].length) {
				int xcoor = (int) coor.getX() + (int) size.getX();
				boolean canMove = true;
				int vcoor = (int) coor.getY();
				for (int v = 0; v < size.getY(); v++) {
					try {
						if (tray.myTray.myBoard[vcoor + v][xcoor] != null) {
							canMove = false;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						canMove = false;
					}
				}
				if (canMove) {
					int[] temp = new int[4];
					temp[0] = (int) coor.getY();
					temp[1] = (int) coor.getX();
					temp[2] = temp[0];
					temp[3] = temp[1] + 1;
					moves.add(new Move(temp));
				}
			}
			if (coor.getY() > 0) {
				int ycoor = (int) coor.getY() - 1;
				boolean canMove = true;
				int hcoor = (int) coor.getX();
				for (int h = 0; h < size.getX(); h++) {
					try {
						if (tray.myTray.myBoard[ycoor][hcoor + h] != null) {
							canMove = false;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						canMove = false;
					}
				}
				if (canMove) {
					int[] temp = new int[4];
					temp[0] = (int) coor.getY();
					temp[1] = (int) coor.getX();
					temp[2] = temp[0] - 1;
					temp[3] = temp[1];
					moves.add(new Move(temp));
				}
			}
			if (coor.getY() + size.getY() < tray.myTray.myBoard.length) {
				int ycoor = (int) coor.getY() + (int) size.getY();
				boolean canMove = true;
				int hcoor = (int) coor.getX();
				for (int h = 0; h < size.getX(); h++) {
					try {
						if (tray.myTray.myBoard[ycoor][hcoor + h] != null) {
							canMove = false;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						canMove = false;
					}
				}
				if (canMove) {
					int[] temp = new int[4];
					temp[0] = (int) coor.getY();
					temp[1] = (int) coor.getX();
					temp[2] = temp[0] + 1;
					temp[3] = temp[1];
					moves.add(new Move(temp));
				}
			}
		}
		return moves;
	}

	private static Tray copyTray(Tray tray) {
		return new Tray(Tray.copyBoard(tray.myTray),
				Tray.copyCoorsSize(tray.coorSize), Tray.copySpace(tray.space));
	}

	private static Board copyBoard(Board board) {
		Point[][] a = board.myBoard;
		Point[][] b = new Point[a.length][a[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				if (a[i][j] != null) {
					Point p = a[i][j];
					b[i][j] = new Point(p.x, p.y);
				}
			}
		}
		return new Board(b);
	}

	private static Set<Point> copySpace(Set<Point> space) {
		Set<Point> b = new HashSet<Point>();
		for (Point k : space) {
			b.add(new Point(k.x, k.y));
		}
		return b;
	}

	private static Map<Point, Point> copyCoorsSize(Map<Point, Point> coords) {
		Map<Point, Point> b = new HashMap<Point, Point>();
		for (Entry<Point, Point> entry : coords.entrySet()) {
			Point p = entry.getKey();
			Point k = entry.getValue();

			b.put(new Point(p.x, p.y), new Point((int) k.x, (int) k.y));
		}
		return b;
	}

	public String toString() {
		return myTray.toString();
	}

	@Override
	public int hashCode() {
		return myTray.hashCode();
	}

	@Override
	public boolean equals(Object m) {
		return myTray.equals(((Tray) m).myTray);
	}
}
