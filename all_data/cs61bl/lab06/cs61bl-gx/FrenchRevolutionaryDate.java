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
    	int nextMonth, nextYear, nextDay;
    	nextMonth = super.month();
    	nextYear = super.year();
    	nextDay = super.dayOfMonth() + 1;
    	
    	if (nextMonth <= 12 && nextDay > 30) {
    		nextDay = 1;
    		nextMonth++;
    	}
    	
    	if (nextMonth == 13 && nextDay > 5) {
    		nextDay = 1;
    		nextMonth = 1;
    		nextYear++;
    	}
    	
    	return new FrenchRevolutionaryDate(nextYear, nextMonth, nextDay); 
    }
}
