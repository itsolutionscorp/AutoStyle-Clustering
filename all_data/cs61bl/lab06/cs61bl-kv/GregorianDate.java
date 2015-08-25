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
    	if (this.dayOfYear() == 365) {
    		GregorianDate tmr = new GregorianDate(this.year() + 1, 1, 1);
    		return tmr;
    	} else if (this.dayOfMonth() == monthLengths[this.month() - 1]){
    			GregorianDate tmr = new GregorianDate(this.year(), this.month() + 1, 1);
    			return tmr;
    	} else {
    		GregorianDate tmr = new GregorianDate(this.year(), this.month(), this.dayOfMonth() + 1);
    		return tmr;
    	}
    }

}
