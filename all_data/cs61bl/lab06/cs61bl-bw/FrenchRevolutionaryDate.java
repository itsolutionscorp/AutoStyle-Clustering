public class FrenchRevolutionaryDate extends Date {

    // In a nonleap year in the French Revolutionary Calendar,
    // the first twelve months have 30 days and month 13 has five days.
    public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }
    public Date nextDate() {
    	return new FrenchRevolutionaryDate(year(), month(), dayOfMonth() +1);
    }
    @Override
    public int dayOfYear() {
        return (month() - 1) * 30 + dayOfMonth();
    }
}
