import java.awt.Point;
import java.util.Arrays;

public class Board {
	Point[][] myBoard;

	public Board(Point[][] tray) {
		myBoard = tray;
	}

	@Override
	public int hashCode() {
		return Arrays.deepHashCode(myBoard);
	}

	@Override
	public boolean equals(Object b) {
		if (hashCode() != b.hashCode()) {
			return false;
		}
		try {
			Point[][] t = ((Board) b).myBoard;
			for (int y = 0; y < myBoard.length; y++) {
				for (int x = 0; x < myBoard[0].length; x++) {
					Point p1 = myBoard[y][x];
					Point p2 = t[y][x];
					if (p1 != null && p2 != null) {
						if (!p1.equals(p2)) {
							return false;
						}
					} else if (p1 == null && p2 != null) {
						return false;
					} else if (p1 != null && p2 == null) {
						return false;
					}
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String toString() {
		String k = "[";
		for (int y = 0; y < myBoard.length; y++) {
			k += "[ ";
			for (int x = 0; x < myBoard[0].length; x++) {
				k += myBoard[y][x] + " ";
			}
			k += "]";
		}
		k += "]";
		return k;
	}
}
