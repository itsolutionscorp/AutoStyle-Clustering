import java.awt.Point; 
import java.util.Arrays;

public class TrayBoard {
	Point[][] myTracker;

	public TrayBoard(Point[][] tray) {
		myTracker = tray;
	}

	@Override
	public int hashCode() {
		return Arrays.deepHashCode(myTracker);
	}

	@Override
	public boolean equals(Object b) {
		if (hashCode() != b.hashCode()) {
			return false;
		}
		
		try {
			Point[][] t = ((TrayBoard)b).myTracker;
			for (int i = 0; i < myTracker.length; i++) {
				for (int j = 0; j < myTracker[0].length; j++) {
					Point point1 = myTracker[i][j];
					Point point2 = t[i][j];
					if (point1 != null && point2 != null) {
						if (!point1.equals(point2)) {
							return false;
						}
					} else if ((point1 == null && point2 != null)||(point1 != null && point2 == null)) {
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
		StringBuilder k = new StringBuilder();
		k.append("[ ");
		for (int i = 0; i < myTracker.length; i++) {
			k.append("[ ");
			for (int j = 0; j < myTracker[0].length; j++) {
				k.append(myTracker[i][j] + " ");
			}
			k.append("]");
		}
		k.append("]");
		return k.toString();
	}
}
