public class GregorianDate extends Date {

    public static int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
        31, 30, 31, 30, 31};

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }
    
    public Date nextDate(){
    	int currentDay;
    	int currentMonth;
    	int currentYear;
    	int nextDay;
    	int nextMonth;
    	int nextYear;
    	
    	currentDay = dayOfMonth();
    	currentMonth = month();
    	currentYear = year();
    	
    	
    	if (month() == 2 && dayOfMonth() == 28) {
    		nextDay = 1;
    		nextMonth= currentMonth + 1;
    		nextYear = currentYear;
    	}
    	else if (month() == 4 || month() == 6 || month() == 9 || month() == 11 && dayOfMonth() == 30) {
    		nextDay = 1;
    		nextMonth= currentMonth + 1;
    		nextYear = currentYear;
  	   } else if (month() == 1 || month() == 3 || month() == 5 || month() == 7 || month() == 8 || month() == 10 && dayOfMonth() == 31) {
  		   nextDay = 1;
  		   nextMonth= currentMonth + 1;
  		   nextYear = currentYear;
  	   } else if (month() == 12 && dayOfMonth() == 31 ) {
  		   nextDay = 1;
  		   nextMonth = 1;
  		   nextYear = currentYear + 1;
  	   }
  	   else {
  		   nextDay = currentDay + 1;
  		   nextMonth = currentMonth;
  		   nextYear = currentYear;
  	   }
    	
    	FrenchRevolutionaryDate returnDate = new FrenchRevolutionaryDate(nextYear, nextMonth, nextDay);
    	return returnDate;
    	
    	
    }
    

    @Override
    public int dayOfYear() {
        int rtnValue = 0;
        for (int m = 0; m < month() - 1; m++) {
            rtnValue += monthLengths[m];
        }
        return rtnValue + dayOfMonth();
    }

}
