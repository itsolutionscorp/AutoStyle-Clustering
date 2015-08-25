public class FrenchRevolutionaryDate extends Date {

    // In a nonleap year in the French Revolutionary Calendar,
    // the first twelve months have 30 days and month 13 has five days.
	// public static int[] monthLengths = {30, 30, 30, 30, 30, 30, 30,
    // 30, 30, 30, 30, 30, 5};
	
	
    public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear ( ) {
        return (month()-1) * 30 + dayOfMonth ( );
    }

    public Date nextDate() {
    	
    	int newDayOfYear = this.dayOfYear() + 1;
    	
    	int newMonth = newDayOfYear / 30;
    	int newDay = newDayOfYear % 30;
    	int newYear = this.year();
    	if (newDayOfYear >= 365)
    		newYear = this.year() + 1;
    	
    	
    	FrenchRevolutionaryDate next 
    		= new FrenchRevolutionaryDate(newYear, newMonth, newDay);
    	return (next);
    }

}
