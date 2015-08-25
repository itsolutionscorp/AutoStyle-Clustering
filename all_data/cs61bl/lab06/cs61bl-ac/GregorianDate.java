public class GregorianDate extends Date {

    public static int [ ] monthLengths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public GregorianDate (int year, int month, int dayOfMonth) {
        super (year, month, dayOfMonth);
    }

    public int dayOfYear ( ) {
        int rtnValue = 0;
        for (int m=0; m<month()-1; m++) {
            rtnValue += monthLengths[m];
        }
        return rtnValue + dayOfMonth ( );
    }
    
    public Date nextDate () {
    	int new_day = this.dayOfMonth();
    	int new_month = this.month();
    	int new_year = this.year();
    	if (this.month() == 4 || this.month() == 6 || this.month() == 9 || this.month() == 11) {
    		if (this.dayOfMonth() == 30) {
    			new_day = 1;
    			new_month++;
    		} else {
    			new_day++;
    		}
    	} else if (this.month() == 2){
    		if (this.dayOfMonth() == 28) {
    			new_month++;
    			new_day = 1;
    		} else {
    			new_day++;
    		}
    	} else if (this.month() == 12) {
    		if (this.dayOfMonth() == 31) {
    			new_month = 1;
    			new_day = 1;
    			new_year++;
    		} else {
    			new_day++;
    		}
    	}else {
    		if (this.dayOfMonth() == 31) {
    			new_month++;
    			new_day = 1;
    		} else {
    			new_day++;
    		}
    	} 
    	Date d2 = new GregorianDate(new_year, new_month, new_day);
        return d2;
    	} 
    public static void main (String[] args) {
    	GregorianDate testDate = new GregorianDate(2001, 12, 31);
    	testDate.nextDate();
    }}
    