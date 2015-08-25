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
    
    @Override
    public Date nextDate(){
    	int day,month, year = 0;

    		if(this.dayOfMonth() == monthLengths[this.month()-1]){
    			month = this.month() +1;
    			day = 1;
    			year = this.year();
    		}else if(this.month() == 12 && this.dayOfMonth() == monthLengths[this.month()-1]){
    			month = 1;
    			day =1;
    			year = this.year()+1;
    		}else{
    			month = this.month();
    			year = this.year();
    			day = this.dayOfMonth() +1;
    		}
    	
    	return new GregorianDate(year, month, day);
    }

}
