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

	@Override
	public Date nextDate() {
		if (super.month() != 13) {
			if ((super.dayOfMonth() + 1) / 30.0 > 1) {
				return new FrenchRevolutionaryDate(super.year(),
						super.month() + 1, super.dayOfMonth() - 29);
			}
			return new FrenchRevolutionaryDate(super.year(), super.month(),
					super.dayOfMonth() + 1);
		} else {
			
			if ((super.dayOfMonth() + 1) / 5.0 > 1.0) {
				return new FrenchRevolutionaryDate(super.year() + 1,
						super.month() - 12, super.dayOfMonth() - 4);
			} else {
				return new FrenchRevolutionaryDate(super.year(), super.month(),
						super.dayOfMonth() + 1);
			}
		}
	}

}
