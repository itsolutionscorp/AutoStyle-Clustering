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
    	int nextDayOfYear = dayOfYear() + 1;
		int newYear = year();
		int newMonth = month();
		int newDayOfMonth = nextDayOfYear % 30;
		if (dayOfYear() % 30 == 0) {
			newMonth = month() + 1;
		} else if (nextDayOfYear > 365) {
    		newYear = year() + 1;
    		newMonth = 1;
    		newDayOfMonth = (nextDayOfYear - 365) % 30;
    	}
    	return new FrenchRevolutionaryDate(newYear, newMonth, newDayOfMonth);
    }
    
}
