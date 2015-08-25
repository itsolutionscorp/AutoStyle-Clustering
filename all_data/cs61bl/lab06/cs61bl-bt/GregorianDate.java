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
    
    public Date nextDate(){
    	int ourMonth = month();
    	int ourDayOfMonth = dayOfMonth();
    	int ourYear = year();
    	int ourDayOfYear = dayOfYear() + 1;
    	for(int i = 0; i<monthLengths.length; i++){
    		if (ourDayOfYear > monthLengths[i]){
    			ourDayOfYear -= monthLengths[i];
    		}
    		else{
    			ourDayOfMonth = ourDayOfYear;
    			ourMonth = i+1;
    			break;
    		}
    	}
    	if (ourDayOfYear == 1 && ourMonth == 12){
    		ourMonth = 1;
    		ourDayOfMonth = ourDayOfYear;
    		ourYear++;
    	}
    	return new GregorianDate(ourYear, ourMonth, ourDayOfMonth);
    }

}
