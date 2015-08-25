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
    	int day = this.dayOfMonth();
    	int month = this.month();
    	int year = this.year();
    	if (month == 13) {
    		if (day == 5) {
    			day = 1;
    			month = 1;
    			year++;
    		} else {
    			day++;
    		}
    	} else if (day == 30) {
    		day = 1;
    		month++;
    	} else {
    		day++;
    	}
    	return new FrenchRevolutionaryDate(year, month, day);
    }
    
}
