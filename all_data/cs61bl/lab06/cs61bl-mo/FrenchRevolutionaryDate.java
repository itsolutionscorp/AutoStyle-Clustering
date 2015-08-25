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
        if (super.month() == 13 && super.dayOfMonth() == 5) {
        	Date next1 = new FrenchRevolutionaryDate(super.year() + 1, 1, 1); 
        	return next1;
        }
        	
        if (super.dayOfMonth() == 30) {
        	Date next2 = new FrenchRevolutionaryDate(super.year(), super.month() + 1, 1); 
        	return next2;
        } else {
        	Date next2 = new FrenchRevolutionaryDate(super.year(), super.month(), super.dayOfMonth() + 1); 
        	return next2;
        }
    }
}
