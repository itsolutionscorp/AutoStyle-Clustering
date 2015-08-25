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
    
    public GregorianDate nextDate(){
    	int newDay;
    	int newMonth;
    	int newYear;
    	if(dayOfMonth() < monthLengths[month()-1]){
    		newDay = dayOfMonth() + 1;
    		newMonth = month();
    		newYear = year();
    	} 
    	else { 
    		newDay = 1;
    		if (month() == 12){
    			newMonth = 1;
    			newYear = year() + 1;
    		}
    		else {
    			newMonth = month() + 1;
    			newYear = year();
    			
    		}
    	}
    	
    	GregorianDate nxtDate= new GregorianDate(newYear, newMonth, newDay);
    	return nxtDate;
    }
    
    public static void main(String[] args) {
    	Date date = new GregorianDate(1999,4,12);
		date.nextDate();
		System.out.println(date);
	}

}
