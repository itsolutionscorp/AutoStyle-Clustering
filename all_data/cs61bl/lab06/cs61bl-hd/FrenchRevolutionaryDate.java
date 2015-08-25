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
    
    public Date nextDate() {
    	int year, month, date;
    	year = year();
    	month = month();
    	date = dayOfMonth();
    	if(month() <= 12){
    		//days of month = 30
    		if(dayOfMonth() == 30){
    			month++;
    			date = 1;
    		}
    		else date++;
    	}
    	else if(month()==13) {
    		if(dayOfMonth() == 5) {
    			month = 1;
    			year++;
    			date = 1;
    		}
    		else date++;
    	}
    	
    	Date frd = new FrenchRevolutionaryDate(year, month, date);
    	return frd;
    }

}
