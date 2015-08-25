public class FrenchRevolutionaryDate extends Date {

	// In a nonleap year in the French Revolutionary Calendar,
    // the first twelve months have 30 days and month 13 has five days.
    public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    public String nextDate() {
    	String next; 
    	if (month() == 13 && dayOfMonth() == 5) {
    		next = "" + 1 + "/" + 1 + "/" + (year() + 1);
    	} else if (dayOfMonth() == 30) {
    		next = "" + 1 + "/" + (month() + 1) + "/" + year();
    	} else {
    		next = "" + (dayOfMonth() + 1) + "/" + month() + "/" + year();
    	}
    	return next;
    }
    
    public int dayOfYear() {
        return (month() - 1) * 30 + dayOfMonth();
    }
}
