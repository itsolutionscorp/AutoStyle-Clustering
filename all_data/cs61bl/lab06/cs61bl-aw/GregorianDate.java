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
    // my code
    //Bazz) but we are not suppose to return a string, we are suppose to return a date object
    //Bazz) we need to have a test class for this

    public GregorianDate nextDate() {
    	// test if it is the last day of the year
    	int year = 0;
    	int month = 0;
    	int day = 0; 
    	if(super.dayOfMonth() == 31 &&  super.month() == 12){
    		 year = super.year() + 1;
    		 day = 1;
    		 month = 1;
    		
    	} //if it is the last day of the month
    	else if (super.dayOfMonth() == monthLengths[super.month() - 1]){
    		day = 1;
    		month = (super.month() + 1);
    		year = super.year();
    	}else{
    		day = super.dayOfMonth() + 1;
    		month = super.month();
    		year = super.year();
    	}
    	
    	return new GregorianDate(year,month,day);
		
    }

}
