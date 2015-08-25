import java.util.Arrays;

public class SimpleDate implements Comparable<SimpleDate> {
	int month;
	int day;

	public SimpleDate(int x, int y) {
		month = x;
		day = y;
	}

	public String toString() {
		String result = new String();
		result = this.month + "/" + this.day;
		return result;
	}

	public int compareTo(SimpleDate date) {
		if (this.month > date.month) {
			return 10;
		} else if (this.month == date.month) {
			if (this.day > date.day) {
				return 5;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		SimpleDate[] dArray = new SimpleDate[4];
		dArray[0] = new SimpleDate(5, 2); // 5/2
		dArray[1] = new SimpleDate(2, 9); // 2/9
		dArray[2] = new SimpleDate(6, 3); // 6/3
		dArray[3] = new SimpleDate(1, 11); // 1/11
		Arrays.sort(dArray);
		for (int k = 0; k < dArray.length; k++) {
			System.out.println(dArray[k]);
		}
		// should print the dates in chronological order:
		// 1/11, 2/9, 5/2, 6/3
	}

}
