public class FrenchRevolutionaryDate extends Date {

    // In a nonleap year in the French Revolutionary Calendar,
    // the first twelve months have 30 days and month 13 has five days.
    public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }
    
    //Assuming year is not a leap year
    @Override
    public FrenchRevolutionaryDate nextDate() {
    	if (super.month() == 13) {
    		if (super.dayOfMonth() == 5) {
    			return new FrenchRevolutionaryDate(super.year() + 1, 1, 1);
    		} else {
    			return new FrenchRevolutionaryDate(super.year(), super.month(), super.dayOfMonth() + 1);
    		}
    	} else if (super.dayOfMonth() == 30) {
    		return new FrenchRevolutionaryDate(super.year(), super.month() + 1, 1);
    	} else {
    		return new FrenchRevolutionaryDate(super.year(), super.month(), super.dayOfMonth() + 1);
    	}
    }
    
    @Override
    public int dayOfYear() {
        return (month() - 1) * 30 + dayOfMonth();
    }

}
