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
    	if (this.month() == 13 && this.dayOfMonth() == 5) {
    		FrenchRevolutionaryDate nextDay = new FrenchRevolutionaryDate(this.year() + 1, 1, 1);
    		return nextDay;
    	} else {
    		FrenchRevolutionaryDate nextDay = new FrenchRevolutionaryDate(this.year(), this.dayOfYear()/30 + 1, this.dayOfYear() % 30 + 1);
    		return nextDay;
    	}
    }

}
