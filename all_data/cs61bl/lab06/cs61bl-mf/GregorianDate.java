public class GregorianDate extends Date {

    public static int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
        31, 30, 31, 30, 31};

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    public GregorianDate nextDate() {
    	if((super.month() == 12) && (super.dayOfMonth() == 31)) {
    		return new GregorianDate(super.year() + 1, 1, 1);
    	} else if(monthLengths[super.month() -1] == super.dayOfMonth()) {
    		return new GregorianDate(super.year(), super.month() + 1, 1);
    	} else {
    		return new GregorianDate(super.year(), super.month(), super.dayOfMonth() + 1);
    			
    		}
    }

    	
    	
    	/*if (super.month() == 13) {
    		if (super.dayOfMonth() == 5) {
    			return new GregorianDate(super.year() + 1, 1, 1);
    		} else {
    			return new GregorianDate(super.year(), super.month(), super.dayOfMonth() + 1);
    		}
    	} else if (super.dayOfMonth() == 30) {
    		return new GregorianDate(super.year(), super.month() + 1, 1);
    	} else {
    		return new GregorianDate(super.year(), super.month(), super.dayOfMonth() + 1);
    	}*/
    
    @Override
    public int dayOfYear() {
        int rtnValue = 0;
        for (int m = 0; m < month() - 1; m++) {
            rtnValue += monthLengths[m];
        }
        return rtnValue + dayOfMonth();
    }

}
