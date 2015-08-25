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
    
    public GregorianDate nextDate() {
    	GregorianDate date;
    	int monthLocationInArray = this.month() - 1;
    	if (monthLengths[monthLocationInArray] == 31) {
    		if (this.dayOfMonth() == 31) {
    			date = new GregorianDate(this.year(), this.month() + 1, 1);
    			return date;
    		} else {
    			date = new GregorianDate(this.year(), this.month(), this.dayOfMonth() + 1);
    			return date;
    		} 
    	} else if (monthLengths[monthLocationInArray] == 30) {
    		if (this.dayOfMonth() == 30) {
    			date = new GregorianDate(this.year(), this.month() + 1, 1);
    			return date;
    		} else {
    			date = new GregorianDate(this.year(), this.month(), this.dayOfMonth() + 1);
    			return date;
    		}
    	} else if (this.dayOfMonth() == 31 && this.month() == 12) {
    		date = new GregorianDate(this.year() + 1, 1, 1);
    		return date;
    	} else {
    		if (this.year() % 4 == 0) {
    			if (this.dayOfMonth() == 29) {
    				date = new GregorianDate(this.year(), this.month() + 1, 1);
    				return date;
    			} else {
    				date = new GregorianDate(this.year(), this.month(), this.dayOfMonth() + 1);
    				return date;
    			}
    		} else if (this.year() % 100 == 0 & this.year() % 400 == 0) {
    			if (this.dayOfMonth() == 29) {
    				date = new GregorianDate(this.year(), this.month() + 1, 1);
    				return date;
    			} else {
    				date = new GregorianDate(this.year(), this.month(), this.dayOfMonth() + 1);
    				return date;
    			}
    		} else {
    			if (this.dayOfMonth() == 28)  {
    				date = new GregorianDate(this.year(), this.month() + 1, 1);
        			return date;
    			} else {
    				date = new GregorianDate(this.year(), this.month(), this.dayOfMonth() + 1);
        			return date;
    			}
    		}
    	}
    	
    }

}
