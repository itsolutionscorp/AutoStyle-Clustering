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
    
    public Date nextDate (){
    	FrenchRevolutionaryDate nextday;
    	int temp_year = super.year();
    	int temp_month = super.month();
    	int temp_day = super.dayOfMonth();
    	
    	if (temp_month == 13){
	    	if ((temp_day + 1)>5){
	    		temp_day = 1;
	    		temp_month = 1;
	    		temp_year += 1;
	    	}
	    } else {
	    	if ((temp_day + 1)>30){
	    		temp_day = 1;
	    		temp_month += 1;
	    	} else {
	    		temp_day += 1;
	    	}
    	}
    	nextday = new FrenchRevolutionaryDate (temp_year, temp_month, temp_day);
    	return nextday;
    }

}
