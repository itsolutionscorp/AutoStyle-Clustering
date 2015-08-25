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
    	int ourMonth = month();
    	int ourDayOfMonth = dayOfMonth();
    	int ourYear = year();
    	if (dayOfMonth() == 5){
    		if (ourMonth == 13){
    			ourMonth = 1;
    			ourDayOfMonth = 1;
    			ourYear++;
    		}
    		else{
    			ourDayOfMonth++;
    			ourYear++;
    		}
    	}
    	if (dayOfMonth() == 30){
    		ourMonth++;
    		ourDayOfMonth = 1;
    	}
    	return new FrenchRevolutionaryDate(ourYear, ourMonth, ourDayOfMonth);
    }

}
