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
    
    public Date nextDate() {
    	int newDay = this.dayOfMonth() + 1;
    	int newMonth = this.month();
    	int newYear = this.year();
    	if (newDay > monthLengths[newMonth]) {
    		newMonth++;
    		newDay = 1;
    	}
    	if (newMonth == 13) {
    		newMonth = 1;
    		newYear++;
    	}
    	
    	return (new GregorianDate(newYear, newMonth, 
    			newDay));
    }

}
