public class FrenchRevolutionaryDate extends Date {

    // In a nonleap year in the French Revolutionary Calendar,
    // the first twelve months have 30 days and month 13 has five days.

    public FrenchRevolutionaryDate (int year, int month, int dayOfMonth) {
        super (year, month, dayOfMonth);
    }

    public int dayOfYear ( ) {
        return (month()-1) * 30 + dayOfMonth ( );
    }
    
    public Date nextDate () {
    	int new_day = this.dayOfMonth();
    	int new_month = this.month();
    	int new_year = this.year();
    	if (this.month() == 13) {
    		if (this.dayOfMonth() == 5) {
    			new_month = 1;
    			new_day = 1;
    			new_year++;
    		} else {
    			new_day++;
    		}
    	} else {
    		if (this.dayOfMonth() == 30) {
    			new_month++;
    			new_day = 1;
    		} else {
    			new_day++;
    		}
    	}
    	Date d2 = new FrenchRevolutionaryDate(new_year, new_month, new_day);
        return d2;
    }
    
    public static void main (String[] args) {
    	FrenchRevolutionaryDate testDate = new FrenchRevolutionaryDate(2001, 8, 30);
    	testDate.nextDate();
    }}