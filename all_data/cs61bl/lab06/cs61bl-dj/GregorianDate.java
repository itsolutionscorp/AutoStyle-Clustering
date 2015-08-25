public class GregorianDate extends Date {

	public static int[] monthLengths = { 31, 28, 31, 30, 31, 30, 31,

	31, 30, 31, 30, 31 };

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

	public Date nextDate() {

		int nextYear = super.year() + 1;

		int nextMonth = super.month() + 1;

		int tomorrow = super.dayOfMonth() + 1;

		if (super.month() == 12 && super.dayOfMonth() == 31) {

			GregorianDate d = new GregorianDate(nextYear, 1, 1);

			return d;

		} else if ((super.month() == 4 || super.month() == 6)
				|| (super.month() == 9 || super.month() == 11)
				&& super.dayOfMonth() == 30) {

			GregorianDate d = new GregorianDate(super.year(), nextMonth, 1);

			return d;

		} else if (super.month() == 2 && super.dayOfMonth() == 28) {

			GregorianDate d = new GregorianDate(super.year(), nextMonth, 1);

			return d;

		}

		else {

			GregorianDate d = new GregorianDate(super.year(), super.month(),
					tomorrow);

			return d;

		}

	}

}