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
    	int gYear = year();
    	int gMonth = month();
    	int gdayOfMonth = dayOfMonth();
    	for (int i = 0; i < monthLengths.length; i++) {
    		if (dayOfMonth() == monthLengths[i]) {
    			if (month() == 12) {
    				gYear++;
    				gMonth = 1;
    				gdayOfMonth = 1;
    				break;
    			}
    			else if (month() == i + 1) {
    				gMonth++;
    				gdayOfMonth = 1;
    				break;
    		}
    		}
    		if (i == 11) {
    			gdayOfMonth++;
    	}	
    	}
    	GregorianDate result = new GregorianDate(gYear, gMonth, gdayOfMonth);
    	return result;
    }
}
