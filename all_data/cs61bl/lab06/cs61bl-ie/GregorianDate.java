public class GregorianDate extends Date {

    public static int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
        31, 30, 31, 30, 31};

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }
    
    @Override
    public Date nextDate(){
    	int tempMonth = super.month(); 
    	int tempDay = super.dayOfMonth();
    	int tempYear = super.year();
    	if(tempMonth == 12 && monthLengths[tempMonth-1] == tempDay){
    		tempMonth = 1;
    		tempDay = 1;
    		tempYear += 1;
    	} else if(monthLengths[tempMonth-1] == tempDay){
    		tempMonth += 1;
    		tempDay = 1;
    	} else{
    		tempDay += 1;
    	}
    	GregorianDate y = new GregorianDate(tempYear, tempMonth, tempDay);
    	return y;
    	
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
