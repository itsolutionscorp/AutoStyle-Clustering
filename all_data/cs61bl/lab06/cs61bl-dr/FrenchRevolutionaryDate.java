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
    public Date nextDate(){
    	if (month() < 13 && dayOfMonth() == 30){
    		return new FrenchRevolutionaryDate(year(), month() + 1, 1); // date is in next month
    	}
    	else if (dayOfMonth() == 5){ 
    		return new FrenchRevolutionaryDate(year() + 1, 1, 1); // date is in next year
    	}	
		return new FrenchRevolutionaryDate(year(), month(), dayOfMonth() + 1); // no wrap around
    }

}
