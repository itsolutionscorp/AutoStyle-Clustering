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
    @Override
    public FrenchRevolutionaryDate nextDate() {
    	int newDay;
    	int newYear;
    	int NewMonth;
    	// three cases to worry about: end of month, end of year, or neither. 
    	int currentDate = dayOfYear();
    	double divided = 1.00 * currentDate%30.0;
    	if (currentDate == 365){
    		newYear = year() + 1;
    		NewMonth = 1;
    		newDay = 1;
    	}else if (currentDate<=360 && divided == 0.0){ // last day of month but not end of year
    		NewMonth = month() + 1;
    		newDay = 1;
    		newYear = year();
    		
    	}
    	
    	else { 
    		NewMonth = month();
    		newYear = year();
    		newDay = dayOfMonth()+1;}
    	
		FrenchRevolutionaryDate newDateObj = new FrenchRevolutionaryDate(newYear, NewMonth, newDay);
    	return newDateObj;
    	
    	}


	}

