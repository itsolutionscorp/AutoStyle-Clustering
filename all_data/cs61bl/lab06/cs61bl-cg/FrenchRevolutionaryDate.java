public class FrenchRevolutionaryDate extends Date {

    // In a nonleap year in the French Revolutionary Calendar,
    // the first twelve months have 30 days and month 13 has five days.
    public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }
    
    public Date nextDate(){
    	int nextDay = dayOfMonth();
    	int nextMonth = month();
    	int nextYear = year();
    	if (nextDay == 30) {
    		nextMonth ++;
    		nextDay = 1;
    	} else{
    	if ((month() == 13) && (nextDay > 5)) {
    		nextYear ++;
    		nextMonth = 1;
    		nextDay = 1;
    	}
    	else{
    		nextDay++;
    	}
    	}
    	FrenchRevolutionaryDate next = new FrenchRevolutionaryDate(nextYear, nextMonth, nextDay);
    	return next;
    }

    @Override
    public int dayOfYear() {
        return (month() - 1) * 30 + dayOfMonth();
    }

}
