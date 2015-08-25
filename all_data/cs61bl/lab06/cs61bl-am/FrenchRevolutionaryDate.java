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
    	int m = super.month();
    	int d = super.dayOfMonth();
    	int y = super.year();
    	if (super.month() < 13){
        	if(super.dayOfMonth() == 30) { 
        		m++;
        		d = 1;
        	}
        	else d++;
    	}
    	else{
    		if(super.dayOfMonth() == 5){
    			m = 1;
    			d = 1;
    			y++;
    		}
    		else d++;
    	}
    	
    	FrenchRevolutionaryDate nd = new FrenchRevolutionaryDate(y, m, d);
    	return nd;
    }
   
}




