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
    
    public Date nextDay() {
    	if (dayOfYear() == 365) {
    		Date NewDate = new FrenchRevolutionaryDate(this.year()+1, 1, 1);
    		return NewDate;
    	} else if (dayOfYear()%30 == 0) {
    		Date NewDate = new FrenchRevolutionaryDate(this.year(),this.month()+1, 1);
    		return NewDate;
    	} else {
    		Date NewDate = new FrenchRevolutionaryDate(this.year(),this.month(), this.dayOfMonth()+1);
    		return NewDate;
    	}
    }

}
