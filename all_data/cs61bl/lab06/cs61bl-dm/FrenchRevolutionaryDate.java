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
    	if(dayOfMonth() == 30){
    		// This case only happens for the first tweleve months, so the year does not change by advancing one day
    		return new FrenchRevolutionaryDate(year(), month() + 1, 1);
    	}
    	if(dayOfMonth() == 5 && month() == 13){
    		// This case advances to the first day of the next year
    		return new FrenchRevolutionaryDate(year() + 1, 1, 1);
    	}
    	return new FrenchRevolutionaryDate(year(), month(), dayOfMonth() + 1);
    }

}
