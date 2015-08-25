public class FrenchRevolutionaryDate extends Date {

    // In a nonleap year in the French Revolutionary Calendar,
    // the first twelve months have 30 days and month 13 has five days.
    public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {
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
    	
    	if (LeapYear() == true) {
    		if (month() == 2 && dayOfMonth() == 29 ){
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
        	
  	   } else {
  		   if (month() < 13 && dayOfMonth() == 30 ){
  			   nextDay = 1;
  			   nextMonth= currentMonth + 1;
  			   nextYear = currentYear;
  		   } else if (month() == 13 && dayOfMonth() == 5 ) {
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
    }
    


    @Override
    public int dayOfYear() {
        return (month() - 1) * 30 + dayOfMonth();
    }

}
