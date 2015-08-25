import java.util.Arrays;

public class SimpleDate implements Comparable<SimpleDate> {
	String date;
	float xxx;

	public static void main(String[] args) {
		SimpleDate[] dArray = new SimpleDate[4];
		dArray[0] = new SimpleDate(5, 2); // 5/2
		dArray[1] = new SimpleDate(2, 9); // 2/9
		dArray[2] = new SimpleDate(6, 3); // 6/3
		dArray[3] = new SimpleDate(1, 11); // 1/11
		Arrays.sort(dArray);
		for (int k = 0; k < dArray.length; k++) {
			System.out.print(dArray[k].date);
			if (k < dArray.length - 1) {
				System.out.print(',');
			}
		}
		// should print the dates in chronological order:
		// 1/11, 2/9, 5/2, 6/3
	}

	public SimpleDate(int month, int date) {
		this.xxx = (float) (month + date * 0.01);
		this.date = Integer.toString(month) + "/" + Integer.toString(date);
	}

	@Override
	public int compareTo(SimpleDate o) {
		// TODO Auto-generated method stub
		return Integer.compare((int) this.xxx, (int) o.xxx);
	}
}
