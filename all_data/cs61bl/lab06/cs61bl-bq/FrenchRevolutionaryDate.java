public class FrenchRevolutionaryDate extends Date {

    // In a nonleap year in the French Revolutionary Calendar,
    // the first twelve months have 30 days and month 13 has five days.
    public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }


    public int dayOfYear() {
        return (month() - 1) * 30 + dayOfMonth();
    }


    public Date nextDate() {
    	int newYear = year();
    	int newMonth = month();
    	int newDay = dayOfYear();
    	if (newMonth <= 12) {
    		if (newDay == 30) {
    			newMonth++;
    			newDay = 1;
    		}
    		else {
    			newDay++;
    		}
    	} else {
    		if (newDay == 5) {
    			newYear++;
    			newMonth = 1;
    			newDay = 1;
    		}
    		else {
    			newDay++;
    		}
    	}
    			
    	return new FrenchRevolutionaryDate(newYear, newMonth, newDay);
    }
}
