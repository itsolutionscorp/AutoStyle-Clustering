public class GregorianDate extends Date {

    public static int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
        31, 30, 31, 30, 31};
    
    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    //Basically returns a value from 1 - 365. 
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
    	if (this.dayOfYear() == 365) {
    		return ((Date) new GregorianDate(this.year() + 1, 1, 1));
    	}
    	int dayofyear = dayOfYear();
        for (int m = 0; m < month() - 1; m++) {
            dayofyear -= monthLengths[m];
        }
        if (month() == 1 || month() == 3 || month() == 5 || month() == 7 || month() == 8 || month() == 10 || month() == 12) {
        	if (dayofyear == 31) {
        		return ((Date) new GregorianDate(super.year(), super.month() + 1, 1));
        	}
        } else if (month() == 4 || month() == 6 || month() == 9 || month() == 11) {
        	if (dayofyear == 30) {
        		return ((Date) new GregorianDate(super.year(), super.month() + 1, 1));
        	}
        } else if (month() == 2) {
        	if (dayofyear == 28) {
        		return ((Date) new GregorianDate(super.year(), super.month() + 1, 1));
        	}
        }
    	return ((Date) new GregorianDate(super.year(), super.month(), super.dayOfMonth() + 1));
    }
}
