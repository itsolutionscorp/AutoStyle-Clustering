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
    public Date nextDate(){
    	int a; int b = month(); int c= year();
    	if (dayOfMonth() < monthLengths[month()-1]) {
    		a = dayOfMonth()+1;
    	} else if (month() < 12) {
    		b = month()+1;
    		a = 1;
    	} else {
    		c = year()+1;
    		b = 1;
    		a = 1;
    	}
    	return new GregorianDate(c, b, a);
    }

}
