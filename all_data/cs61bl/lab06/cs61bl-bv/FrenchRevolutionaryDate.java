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
    		if (dayOfMonth() + 1 > 30) {
    			FrenchRevolutionaryDate new_date = 
    					new FrenchRevolutionaryDate(year(), month() + 1, 1);
    			return new_date;
    		} else {
    			FrenchRevolutionaryDate new_date = 
    					new FrenchRevolutionaryDate(year(), month(), dayOfMonth() + 1);
    			return new_date;
    		}
    	} else {
    		if (dayOfMonth() + 1 > 5) {
    			FrenchRevolutionaryDate new_date = 
    					new FrenchRevolutionaryDate(year() + 1, 1, 1);
    			return new_date;
    		} else {
    			FrenchRevolutionaryDate new_date = 
    					new FrenchRevolutionaryDate(year(), 13, dayOfMonth() + 1);
    			return new_date;
    		}
    	}
    }

    public static void main(String[] args) {
    	FrenchRevolutionaryDate a = new FrenchRevolutionaryDate(2015, 13, 4);
    	System.out.println(a.nextDate());
    	System.out.println(a.nextDate().nextDate());
    	System.out.println(a.nextDate().nextDate().nextDate());
    	
    	FrenchRevolutionaryDate b = new FrenchRevolutionaryDate(2015, 12, 29);
    	System.out.println(b.nextDate());
    	System.out.println(b.nextDate().nextDate());
    }
}
