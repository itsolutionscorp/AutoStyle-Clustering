public class FrenchRevolutionaryDate extends Date {

	// In a nonleap year in the French Revolutionary Calendar,

	// the first twelve months have 30 days and month 13 has five days.

	public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {

		super(year, month, dayOfMonth);

	}

	@Override
	public int dayOfYear() {

		return (month() - 1) * 30 + dayOfMonth();

	}

	public Date nextDate() {

		int nextYear = super.year() + 1;

		int nextMonth = super.month() + 1;

		int tomorrow = super.dayOfMonth() + 1;

		if (super.month() == 13 && super.dayOfMonth() == 5) {

			FrenchRevolutionaryDate d = new FrenchRevolutionaryDate(nextYear,
					1, 1);

			return d;

		}

		else if (super.dayOfMonth() == 30) {

			FrenchRevolutionaryDate d = new FrenchRevolutionaryDate(
					super.year(), nextMonth, 1);

			return d;

		} else {

			FrenchRevolutionaryDate d = new FrenchRevolutionaryDate(
					super.year(), super.month(), tomorrow);

			return d;

		}

	}

}