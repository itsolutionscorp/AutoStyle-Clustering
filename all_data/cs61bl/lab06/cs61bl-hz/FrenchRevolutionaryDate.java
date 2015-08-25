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
    	if (5 == dayOfMonth() && month() == 13) {
    		return new FrenchRevolutionaryDate(year() + 1, 1, 1);
    	} else if (30 == dayOfMonth()) {
    		return new FrenchRevolutionaryDate(year(), month() + 1, 1);
    	} else {
    		return new FrenchRevolutionaryDate(year(), month(), dayOfMonth() + 1);
    	}
    }
}
