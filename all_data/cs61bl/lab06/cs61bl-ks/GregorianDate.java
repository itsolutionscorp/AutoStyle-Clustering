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
    public GregorianDate nextDate(){
    	int newYear;
    	int NewMonth;
    	int newDay;
    	
    	int currentDay = dayOfYear();
    	// end of year case
    	if (currentDay == 365){
    		newYear = year() + 1;
    		NewMonth = 1;
    		newDay = 1;
    		
    	}else 
    	
    	// end of month case
    	if (dayOfMonth() == monthLengths[month()-1]){
    		NewMonth = month() + 1;
    		newYear = year();
    		newDay = 1;
    		
    	}else{
    		NewMonth = month();
    		newYear = year();
    		newDay = dayOfMonth() +1;
    		
    	}
    	
    	GregorianDate newDate = new GregorianDate(newYear, NewMonth, newDay);
    	return newDate;
    }

}
