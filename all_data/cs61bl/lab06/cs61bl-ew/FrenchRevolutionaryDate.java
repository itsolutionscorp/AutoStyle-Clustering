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
    
    public String nextDate() {
    	int nextYear = year();
    	int nextMonth = month();
    	int nextDay = dayOfMonth() + 1;
    	if (nextMonth < 13) {
    		if (nextDay== 31) {
    			nextMonth++;
    			nextDay =1;
    		}
    	} else if (nextDay == 6) {
    		nextYear++;
    		nextMonth = 1;
    		nextDay= 1;
    	}
    	return "" + nextDay + "/" + nextMonth + "/" + nextYear;
    }

}
