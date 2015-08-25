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
    
    public GregorianDate nextDate(){
    	int newYear = (this.dayOfYear()) / 365 + this.year();
    	int newMonth=this.month();
    	int newDay = this.dayOfMonth()+1;
    	if (monthLengths[this.month()-1]==this.dayOfMonth()){
    		if (newMonth==12){
    			newMonth=1;
    		} else {
    			newMonth++;
    		}
    		newDay = 1;
    	}
    	return new GregorianDate(newYear,newMonth,newDay);
    }

}
