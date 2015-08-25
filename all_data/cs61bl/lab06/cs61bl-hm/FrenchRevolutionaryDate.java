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
    public Date nextDate(){
    	int[] monthLengths = {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 5};
    	int tempYear, tempMonth, tempDay;
    	tempYear = year();
    	tempMonth = month();
    	tempDay = dayOfMonth();
    	tempDay++;
    	if(tempDay>monthLengths[tempMonth-1]){
    		tempDay = 1;
    		tempMonth++;
    		if(tempMonth>13){
    			tempYear++;
    			tempMonth=1;
    		}
    	}
    	Date nextDay = new GregorianDate(tempYear, tempMonth, tempDay);
    	return nextDay;
    }
    

}
