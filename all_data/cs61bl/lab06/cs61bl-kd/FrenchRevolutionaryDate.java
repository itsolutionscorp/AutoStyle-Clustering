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

    public FrenchRevolutionaryDate nextDate() {
    	int tempMonth = month();
    	int tempDay = dayOfMonth();
    	int tempYear = year();
    	if (month() != 13) {
    		if (dayOfMonth() == 30) {
    			tempMonth++;
    			tempDay = 1;
    		} else {
    			tempDay++;
    		}
    	} else {
    		if (dayOfMonth() == 5) {
    			tempYear++;
    			tempMonth = 1;
    			tempDay = 1;
    		} else {
    			tempDay++;
    		}
    	}
    	return new FrenchRevolutionaryDate(tempYear, tempMonth, tempDay);
    }
}
