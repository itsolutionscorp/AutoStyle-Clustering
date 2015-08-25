import java.util.Arrays;

public class IntArray2D {
	int[][] myItem;
	int length;

	public IntArray2D(int h, int w) {
		myItem = new int[h][w];
		length = myItem.length;
	}

	public int[] getPiece(int i) {
		return myItem[i];
	}

	public void setPiece(int i, int[] piece) {
		myItem[i] = piece;
	}

	public void setCoord(int i, int j, int value) {
		myItem[i][j] = value;
	}

	public int getCoord(int i, int j) {
		return myItem[i][j];
	}

	@Override
	public int hashCode() {
		return Arrays.deepHashCode(myItem);
	}

	@Override
	public boolean equals(Object o) {
		IntArray2D other = (IntArray2D) o;
		return Arrays.deepEquals(myItem, other.myItem);
	}

	@Override
	public String toString() {
		return Arrays.deepToString(myItem);
	}

}
