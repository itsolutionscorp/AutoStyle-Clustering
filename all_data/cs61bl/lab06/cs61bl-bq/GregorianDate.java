public class GregorianDate extends Date {

    public static int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
        31, 30, 31, 30, 31};

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }


    public int dayOfYear() {
        int rtnValue = 0;
        for (int m = 0; m < month() - 1; m++) {
            rtnValue += monthLengths[m];
        }
        return rtnValue + dayOfMonth();
    }
    
    public Date nextDate(){
    	
    	int newYear = year();
    	int newMonth = month();
    	int newDay = dayOfYear();
    	
    	if ( monthLengths[newMonth -1] == newDay ) {
    		
    		newMonth += 1;
    		newDay = 1;
    		
    		if (newMonth == 13 ) {
    			
    			newMonth =1;
    			newDay =1;
    			newYear += 1;
    		}

    		
    	}
    	
    	return new GregorianDate(newYear, newMonth, newDay);
    	
    	   	
    }

}
