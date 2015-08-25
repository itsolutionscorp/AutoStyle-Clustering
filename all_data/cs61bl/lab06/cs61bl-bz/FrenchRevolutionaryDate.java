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
    	int newDayOfMonth = dayOfMonth() + 1;
    	int newMonth = month();
    	int newYear = year();
    	if (dayOfMonth() == 30) {
    		if (month() != 13) {
    			newDayOfMonth = 1;
    			newMonth += 1;
    		}
    	}
    	if (dayOfMonth() == 5 && month() == 13) {
    		newDayOfMonth = 1;
    		newMonth = 1;
    		newYear += 1;
    	}
    	return new FrenchRevolutionaryDate(newYear, newMonth, newDayOfMonth);
    }
}
