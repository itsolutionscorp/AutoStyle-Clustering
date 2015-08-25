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
    	int nextday = dayOfMonth();
    	int nextmonth = month();
    	int nextyear = year();
    	if (month() != 13) {
    		if (dayOfMonth() != 30) {
    			nextday ++;
    		}
    		else {
    			nextmonth++;
    			nextday = 1;
    		}
    	}
    	else if (dayOfMonth() != 5) {
    		nextday++;
    	}
    	else {
    		nextyear++;
    		nextmonth = 1;
    		nextday = 1;
    	}
    	Date next = new FrenchRevolutionaryDate(nextyear, nextmonth, nextday);
    	return next;
    }
    
    public static void main (String[] args) {
    	FrenchRevolutionaryDate d1 = new FrenchRevolutionaryDate(2015, 13, 5);
    	System.out.println("day/month/year form: " + d1);
    	System.out.println("next day: " + d1.nextDate());
    	
    	FrenchRevolutionaryDate d2 = new FrenchRevolutionaryDate(2014, 12, 30);
    	System.out.println("day/month/year form: " + d2);
    	System.out.println("next day: " + d2.nextDate());
    	
    }

}
