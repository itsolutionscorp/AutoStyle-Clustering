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
    
    @Override
    public Date nextDate () {
    	int nextday = dayOfMonth();
    	int nextmonth = month();
    	int nextyear = year();
    	if (dayOfMonth() == monthLengths[month() - 1]) {
    		if (month() != 12) {
    			nextmonth ++;
    			nextday = 1;
    		}
    		else {
    			nextyear ++;
    			nextmonth = 1;
    			nextday = 1;
    		}
    	}
    	else {
    		nextday ++;
    	}    	
    	
    	Date next = new GregorianDate (nextyear, nextmonth, nextday);
    	return next;
    }

    public static void main (String[] args) {
    	GregorianDate d1 = new GregorianDate(2015, 12, 31);
    	System.out.println("day/month/year form: " + d1);
    	System.out.println("next day: " + d1.nextDate());
    	
    	GregorianDate d2 = new GregorianDate(2015, 2, 28);
    	System.out.println("day/month/year form: " + d2);
    	System.out.println("next day: " + d2.nextDate());
    	
    	GregorianDate d3 = new GregorianDate(2015, 5, 31);
    	System.out.println("day/month/year form: " + d3);
    	System.out.println("next day: " + d3.nextDate());
    	
    	
    }
}
