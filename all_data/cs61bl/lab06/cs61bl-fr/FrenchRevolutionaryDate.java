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
    
    public FrenchRevolutionaryDate nextDate() {
    	if (month() <= 12) {
    		if (dayOfMonth() < 30) {
    			FrenchRevolutionaryDate rv = new FrenchRevolutionaryDate(year(), month(), dayOfMonth() + 1);
    			return rv;
    		} else {
    			FrenchRevolutionaryDate rv = new FrenchRevolutionaryDate(year(), month() + 1, 1);
    			return rv;
    		}
    	} else {
    		if (dayOfMonth() < 5) {
    			FrenchRevolutionaryDate rv = new FrenchRevolutionaryDate(year(), month(), dayOfMonth() + 1);
    			return rv;
    		} else {
    			FrenchRevolutionaryDate rv = new FrenchRevolutionaryDate(year() + 1, 1, 1);
    			return rv;
    		}
    	}
    }

}
