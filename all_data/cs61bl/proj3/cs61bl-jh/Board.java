import java.util.ArrayList;
import java.util.HashSet;

public class Board implements Comparable<Board> {
	public static final int UP = -1;
	public static final int DOWN = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int EMPTY = 0;
	public static final int EXIST = 1;
	public static int WIDTH;
	public static int HEIGHT;
	public static int TOTAL;
	public static ArrayList<Integer> dir;
	public final String boardStore;
	public HashSet<Piece> allPieces;
	public ArrayList<String> pathToGG;
	public int[][] tray;
	public int maxSizePieceWidth = 0;

	static {
		dir = new ArrayList<Integer>();
		dir.add(UP);
		dir.add(DOWN);
		dir.add(LEFT);
		dir.add(RIGHT);
	}

	public Board(int width, int height, HashSet<Piece> all,
			HashSet<Piece> goal, int total) {
		WIDTH = width;
		HEIGHT = height;
		TOTAL = total;
		tray = new int[height][width];
		allPieces = all;
		setAll(tray, EXIST);
		pathToGG = new ArrayList<String>();
		boardStore = this.toString();
	}

	public Board(HashSet<Piece> all, ArrayList<String> m, int[][] theTray) {
		allPieces = all;
		pathToGG = m;
		tray = theTray;
		boardStore = this.toString();
	}

	// for all piece to apply all move;
	public HashSet<Board> getNeighbors() {
		HashSet<Board> neighbors = new HashSet<Board>();
		HashSet<Piece> all = this.allPieces;
		if (one()) {
			for (Piece p : all) {
				for (Integer d : dir) {
					if (p.y1 == Solver.y && Solver.UD == 0) {
						if (Solver.LR == 0) {
							System.exit(0);
						} else if (Solver.LR > 0) {
							if (p.x1 <= Solver.x)
								if (d == LEFT)
									if (isLegalmove(p, d))
										neighbors.add(applyMove(p, d));
						} else {
							if (p.x1 >= Solver.x)
								if (d == RIGHT)
									if (isLegalmove(p, d))
										neighbors.add(applyMove(p, d));
						}
					} else if (p.y1 <= Solver.y && Solver.UD > 0) {
						if (Solver.LR == 0) {
							if (p.x1 == Solver.x)
								if (d == UP)
									if (isLegalmove(p, d))
										neighbors.add(applyMove(p, d));
						} else if (Solver.LR > 0) {
							if (p.x1 <= Solver.x)
								if (d == LEFT || d == UP)
									if (isLegalmove(p, d))
										neighbors.add(applyMove(p, d));
						} else {
							if (p.x1 >= Solver.x)
								if (d == UP || d == RIGHT)
									if (isLegalmove(p, d))
										neighbors.add(applyMove(p, d));
						}
					} else {
						if (p.y1 >= Solver.y)
							if (Solver.LR == 0) {
								if (p.x1 == Solver.x)
									if (d == DOWN)
										if (isLegalmove(p, d))
											neighbors.add(applyMove(p, d));
							} else if (Solver.LR > 0) {
								if (p.x1 <= Solver.x)
									if (d == DOWN || d == LEFT)
										if (isLegalmove(p, d))
											neighbors.add(applyMove(p, d));
							} else {
								if (p.x1 >= Solver.x)
									if (d == DOWN || d == RIGHT)
										if (isLegalmove(p, d))
											neighbors.add(applyMove(p, d));
							}
					}
				}
			}
		} else {
			for (Piece p : all) {
				for (Integer d : dir) {
					if (isLegalmove(p, d)) {
						neighbors.add(applyMove(p, d));
					}
				}
			}
		}
		return neighbors;
	}

	public void setAll(int[][] thetray, int change) {
		for (Piece p : allPieces) {
			setPiece(p, thetray, EXIST);
		}
	}

	public void setPiece(Piece p, int[][] thisTray, int change) {
		int x1 = p.x1;
		int y1 = p.y1;
		int x2 = p.x2;
		int y2 = p.y2;
		for (int i = 0; i < p.wide; i++)
			thisTray[y1][x1 + i] = change;
		for (int i = 0; i < p.high; i++)
			thisTray[y1 + i][x1] = change;
		for (int i = 0; i < p.wide; i++)
			thisTray[y2][x1 + i] = change;
		for (int i = 0; i < p.high; i++)
			thisTray[y1 + i][x2] = change;
	}

	public boolean isLegalmove(Piece p, int direction) {
		int x1 = p.x1;
		int y1 = p.y1;
		int x2 = p.x2;
		int y2 = p.y2;

		switch (direction) {
		case LEFT:
			if (x1 - 1 < 0) {
				return false;
			}
			for (int i = y1; i <= y2; i++) {
				if (this.tray[i][x1 - 1] == EXIST) {
					return false;
				}
			}
			return true;
		case RIGHT:
			if (x2 + 1 >= WIDTH) {
				return false;
			}
			for (int i = y1; i <= y2; i++) {
				if (this.tray[i][x2 + 1] == EXIST) {
					return false;
				}
			}
			return true;
		case UP:
			if (y1 - 1 < 0) {
				return false;
			}
			for (int i = x1; i <= x2; i++) {
				if (this.tray[y1 - 1][i] == EXIST) {
					return false;
				}
			}
			return true;
		case DOWN:
			if (y2 + 1 >= HEIGHT) {
				return false;
			}

			for (int i = x1; i <= x2; i++) {
				if (this.tray[y2 + 1][i] == EXIST) {
					return false;
				}
			}
			return true;
		default:
			return false;
		}
	}

	public Board applyMove(Piece p, int direction) {
		int x1 = p.x1;
		int y1 = p.y1;
		int x2 = p.x2;
		int y2 = p.y2;
		HashSet<Piece> newPieces = new HashSet<Piece>(this.allPieces);
		ArrayList<String> newpathToGG = new ArrayList<String>(this.pathToGG);
		int[][] newTray = new int[HEIGHT][WIDTH];

		for (int i = 0; i < tray.length; i++)
			System.arraycopy(tray[i], 0, newTray[i], 0, tray[0].length);

		setPiece(p, newTray, EMPTY);
		switch (direction) {
		case LEFT:
			x1 = x1 - 1;
			x2 = x2 - 1;
			break;
		case RIGHT:
			x1 = x1 + 1;
			x2 = x2 + 1;
			break;
		case UP:
			y1 = y1 - 1;
			y2 = y2 - 1;
			break;
		case DOWN:
			y1 = y1 + 1;
			y2 = y2 + 1;
			break;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(p.y1).append(" ");
		sb.append(p.x1).append(" ");
		sb.append(y1).append(" ").append(x1);
		Piece newPiece = new Piece(x1, y1, x2, y2, maxSide());
		newPieces.remove(p);
		newPieces.add(newPiece);
		newpathToGG.add(sb.toString());
		setPiece(newPiece, newTray, EXIST);
		return new Board(newPieces, newpathToGG, newTray);
	}

	public static int maxSide() {
		if (WIDTH > HEIGHT)
			return WIDTH;
		else
			return HEIGHT;
	}

	public int size() {
		return WIDTH * HEIGHT;
	}

	public int manhattanDistance() {
		int total = 0;
		if (one()) {
			total = Solver.ONE;
		} else
			for (Piece cur : allPieces)
				for (Piece goal : Solver.GOODGAME) {
					if (goal.size() == cur.size()) {
						int m = Math.abs(cur.x1 - goal.x1)
								+ Math.abs(cur.y1 - goal.y2);
						total += m;
					}
				}
		return total;
	}

	public boolean isAllSingle() {
		for (Piece p : allPieces)
			if (p.size() != 1)
				return false;
		return true;
	}

	public boolean one() {
		if (isAllSingle() && (size() - Solver.goNum == 1))
			return true;
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Piece p : allPieces)
			sb.append(p.toString());
		return sb.toString();
	}

	@Override
	public boolean equals(Object p) {
		return boardStore.equals(((Board) p).toString());
	}

	@Override
	public int hashCode() {
		return boardStore.hashCode();
	}

	Piece largestSquarePiece() {
		Piece p = new Piece(1, 1, 1, 1, 0);
		for (Piece piece : allPieces)
			if (piece.high == piece.wide)
				if (piece.size() >= p.size())
					p = piece;
		return p;
	}

	// ignore this
	Piece onlyLargest() {
		Piece p = largestSquarePiece();
		int i = 0;
		for (Piece piece1 : allPieces)
			if (piece1.size() == p.size())
				i++;
		maxSizePieceWidth = p.wide;
		if (i == 1) {
			return p;
		} else
			return null;
	}

	// ignore this
	boolean isKlotski() {
		int pieceTotalSize = 0;
		int dftSizeNum = 0;
		Piece max = onlyLargest();
		if (max == null)
			return false;
		else {
			int[] size = new int[maxSizePieceWidth];
			int[][] sizee = new int[maxSizePieceWidth + 1][maxSizePieceWidth + 1];
			for (Piece p : allPieces) {
				pieceTotalSize += p.size();
				if (p.isSquare()) {
					for (int i = 1; i < maxSizePieceWidth; i++) {
						if (size[i] != i && p.wide == i) {
							size[i] = i;
							dftSizeNum++;
						}
					}
				} else {
					for (int i = 0; i < maxSizePieceWidth; i++) {
						if (sizee[i][i + 1] != 1
								&& (p.wide == i + 1 && p.high == i)) {
							sizee[i][i + 1] = 1;
							dftSizeNum++;
						} else if (sizee[i + 1][i] != 1 && p.wide == i
								&& p.high == i + 1) {
							sizee[i][i + 1] = 1;
							dftSizeNum++;
						}
					}
				}
				if (p.equals(onlyLargest())) {
					maxSizePieceWidth = p.high;
				}
			}
			if (size() - pieceTotalSize != maxSizePieceWidth)
				return false;
			if (dftSizeNum <= maxSizePieceWidth)
				return false;
		}
		return true;
	}

	@Override
	public int compareTo(Board o) {
		int b1 = this.manhattanDistance();
		int b2 = o.manhattanDistance();
		if (b1 > b2)
			return 1;
		else if (b1 == b2)
			return 0;
		else
			return -1;
	}
}