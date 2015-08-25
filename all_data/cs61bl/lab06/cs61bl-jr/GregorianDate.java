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

    public Date nextDate (){
    	GregorianDate nextday;
    	int temp_year = super.year();
    	int temp_month = super.month();
    	int temp_day = super.dayOfMonth();
    	
    	if ((temp_day + 1) > monthLengths[temp_month-1]){
    		temp_month += 1;
    		temp_day = 1;
    		if (temp_month > 12){
        		temp_year += 1;
        		temp_month = 1;
        	}
    	} else {
    		temp_day += 1;
    	}
    	nextday = new GregorianDate(temp_year, temp_month, temp_day);
    	return nextday;
    }
}
