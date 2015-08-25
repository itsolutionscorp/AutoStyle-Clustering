public class GregorianDate extends Date {

	public static int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
        31, 30, 31, 30, 31};

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    public String nextDate() {
    	String next;
    	if (month() == 12 && dayOfMonth() == 31) {
    		next = "" + 1 + "/" + 1 + "/" + (year() + 1);
    	} else if (monthLengths[month() - 1] == dayOfMonth()) {
    		next = "" + 1 + "/" + (month() + 1) + "/" + year();
    	} else {
    		next = "" + (dayOfMonth() + 1) + "/" + month() + "/" + year();
    	}
    	return next;
    }
    
    public int dayOfYear() {
        int rtnValue = 0;
        for (int m = 0; m < month() - 1; m++) {
            rtnValue += monthLengths[m];
        }
        return rtnValue + dayOfMonth();
    }
}
