public class GregorianDate extends Date {

    public static int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
        31, 30, 31, 30, 31};

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        int rtnValue = 0;
        for (int m = 0; m < month() - 1; m++) {
            rtnValue += monthLengths[m];
        }
        return rtnValue + dayOfMonth();
    }
    
    @Override
    public Date nextDate() {
        if (super.month() == 12 && super.dayOfMonth() == 31) {
        	Date next1 = new GregorianDate(super.year() + 1, 1, 1); 
        	return next1;
        }
        	
        if (super.dayOfMonth() == 28 || super.dayOfMonth() == 30 || super.dayOfMonth() == 31) {
        	Date next2 = new GregorianDate(super.year(), super.month() + 1, 1); 
        	return next2;
        	
        } else {
        	Date next3 = new GregorianDate(super.year(), super.month(), super.dayOfMonth() + 1);
        	return next3;
        }
        
  
        
        
        
        
    	
    }

}
