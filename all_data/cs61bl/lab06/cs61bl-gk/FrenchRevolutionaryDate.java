public class FrenchRevolutionaryDate extends Date {

    // In a nonleap year in the French Revolutionary Calendar,
    // the first twelve months have 30 days and month 13 has five days.
    public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    
    public int dayOfYear() {
        return (month() - 1) * 30 + dayOfMonth();
    }
    
    public Date nextDate () {
    	int year = year();
    	int month = month();
    	int day = dayOfMonth();
    	if (month <= 12) {
    		//30 day month
    		if (day + 1 > 30) {
    			month ++;
    			day = 1;
    		}
    		else {
    			day ++;
    		}
    	}
    	else {
    		if (day + 1 > 5) {
    			year ++;
    			month = 1;
    			day = 1;
    		}
    		else {
    			day ++;
    		}
    	}
    	return new FrenchRevolutionaryDate(year, month, day);
    }

}
