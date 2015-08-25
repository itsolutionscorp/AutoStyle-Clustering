public class FrenchRevolutionaryDate extends Date {

    // In a nonleap year in the French Revolutionary Calendar,
    // the first twelve months have 30 days and month 13 has five days.
    public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        return (month() - 1) * 30 + dayOfMonth();
    }

    public Date nextDate(){
    	int newDay;
    	int newMonth;
    	int newYear;
    	if((month() < 12 && dayOfMonth() < 30) || (month()== 13 && dayOfMonth() < 5)){
    		newDay = dayOfMonth() + 1;
    		newMonth = month();
    		newYear = year();
    	} 
    	else {
    		newDay = 1;
    		if (month() == 13){
    			newMonth = 1;
    			newYear = year() + 1;
    		}
    		else {
    			newMonth = month() + 1;
    			newYear = year();
    			
    		}
    	}
    	
    	FrenchRevolutionaryDate nxtDate= new FrenchRevolutionaryDate(newYear, newMonth, newDay);
    	return nxtDate;
    }

}
