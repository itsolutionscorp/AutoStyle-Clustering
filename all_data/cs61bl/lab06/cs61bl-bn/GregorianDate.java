public class GregorianDate extends Date {

	public static int[] monthLengths = { 31, 28, 31, 30, 31, 30, 31, 31, 30,
			31, 30, 31 };

	public GregorianDate(int year, int month, int dayOfMonth) {
		super(year, month, dayOfMonth);
	}

	@Override
	public int dayOfYear() {
		int rtnValue = 0;
		for (int m = 0; m < month() - 1; m++) {
			rtnValue += monthLengths[m];
		}
		return rtnValue + dayOfMonth();
	}

	public int nextDate() {
		if (month() == 1 || month() == 3 || month() == 5 || month() == 7
				|| month() == 8 || month() == 10 || month() == 12) {
			if (dayOfMonth() < 31) {
				return dayOfMonth() + 1;
			} else {
				return 1;
			}
		} else if (month() == 2) {
			if (dayOfMonth() < 29) {
				return dayOfMonth() + 1;
			} else {
				return 1;
			}

		} else {
			if (dayOfMonth() < 30) {
				return dayOfMonth() + 1;
			} else {
				return 1;
			}
		}
	}

	public static void main(String[] args) {
		Date f = new GregorianDate(2015, 1, 1);
		int n = f.nextDate();
		System.out.println("nextdate: " + n);
		System.out.println("day of year:" + f.dayOfYear());
		Date f2 = new GregorianDate(2015, 2, 28);
		int n2 = f2.nextDate();
		System.out.println("nextdate: " + n2);
		System.out.println("day of year:" + f2.dayOfYear());
		Date f3 = new GregorianDate(2015, 2, 29);
		int n3 = f3.nextDate();
		System.out.println("nextdate: " + n3);
		System.out.println("day of year:" + f3.dayOfYear());
		Date f4 = new GregorianDate(2015, 3, 30);
		int n4 = f4.nextDate();
		System.out.println("nextdate: " + n4);
		System.out.println("day of year:" + f4.dayOfYear());
		Date f5 = new GregorianDate(2015, 12, 31);
		int n5 = f5.nextDate();
		System.out.println("nextdate: " + n5);
		System.out.println("day of year:" + f5.dayOfYear());
	}
}
