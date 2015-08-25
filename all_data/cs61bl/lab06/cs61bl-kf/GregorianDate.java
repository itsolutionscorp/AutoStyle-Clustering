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
    
    public GregorianDate nextDate () {
    	if (this.dayOfMonth() == monthLengths[this.month() - 1]) {
    		GregorianDate newDate = new GregorianDate(this.year(), this.month() + 1, 1);
    		return newDate;
    	}
    	else if (this.month() == 12 && this.dayOfMonth() == 31) {
    		GregorianDate newDate = new GregorianDate(this.year() + 1, 1, 1);
        	return newDate;
    		
    	}
    	else {
    		GregorianDate newDate = new GregorianDate(this.year(), this.month(), this.dayOfMonth() + 1);
    		return newDate;
    	}
    }
    public static void main (String[] args) {
    	GregorianDate d1 = new GregorianDate(2015, 1, 31);
    	GregorianDate d2 = new GregorianDate(2015, 1, 2);
    	GregorianDate d3 = d1.nextDate();
    	System.out.println(d3.dayOfYear());
    	System.out.println(d3.dayOfMonth());
    	System.out.println(d3.month());
    }
}
