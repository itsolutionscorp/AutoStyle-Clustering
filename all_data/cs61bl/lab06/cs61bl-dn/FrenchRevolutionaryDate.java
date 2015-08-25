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
    	int year = year();
    	int dayInMonth = month();
    	int month = month();
    	
    	if (month == 13){
    		if (dayInMonth == 5){
    			dayInMonth = 1;
    			month = 1;
    			year++;
    		}
    	} else {
    		if (dayInMonth == 30){
    			dayInMonth = 1;
    			month++;
    		} else {
    			dayInMonth++;
    		}
    	}
    	
    	Date nextDay = new FrenchRevolutionaryDate(year, month, dayInMonth);
    	return nextDay;
    }

}
