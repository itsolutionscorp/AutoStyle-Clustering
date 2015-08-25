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
    
    public FrenchRevolutionaryDate nextDate () {
    	if (this.dayOfMonth() == 30 && this.month() <= 12) {
    		FrenchRevolutionaryDate newDate = new FrenchRevolutionaryDate(this.year(), this.month() + 1, 1);
    		return newDate;
    	}
    	else if (this.dayOfMonth() == 5 && this.month() == 13) {
    		FrenchRevolutionaryDate newDate = new FrenchRevolutionaryDate(this.year() + 1, 1, 1);
    		return newDate;
    		
    	}
    	else {
    		FrenchRevolutionaryDate newDate = new FrenchRevolutionaryDate(this.year(), this.month(), this.dayOfMonth() + 1);
    		return newDate;
    	}
    }
    
    public static void main (String[] args) {
    	FrenchRevolutionaryDate d1 = new FrenchRevolutionaryDate(2015, 2, 30);
    	FrenchRevolutionaryDate d2 = new FrenchRevolutionaryDate(2015, 1, 2);
    	FrenchRevolutionaryDate d3 = d1.nextDate();
    	System.out.println(d3.dayOfMonth());
    	System.out.println(d3.month());
    	
    }
}
