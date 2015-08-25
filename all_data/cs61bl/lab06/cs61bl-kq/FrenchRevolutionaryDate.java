

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
    	int a = year();
    	int b = month();
    	int c = dayOfMonth();
    	
    	if(c+1 > 30) {
    		b = b + 1;
    		c = 1; 
    			
    	}
    	else if ( b == 13 && c == 5) {
    		a ++;
    		b = 1;
    		c = 1;
    	}
    	
    	else {
    		c ++;
    	
    	}
    	FrenchRevolutionaryDate date = new FrenchRevolutionaryDate(a, b, c);
    	return date; 
    }

}
