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

    	int nextM = this.month();
    	int nextD = this.dayOfMonth();
    	int nextY= this.year();
    	if (this.month() == monthLengths.length){
    		if(this.dayOfMonth() == monthLengths[this.month() - 1]){
    			nextD = 1;
    			nextM = 1;
    			nextY ++;
    			return new GregorianDate(nextY, nextM, nextD);
    		}
    		else{
    			nextD++;
    			return new GregorianDate(nextY, nextM, nextD);
    			}
    		}
    		
    	else if (this.dayOfMonth() == monthLengths[this.month()-1]){
    		nextM ++;
    		nextD = 1;
    		return new GregorianDate(nextY, nextM, nextD);
    	}
    	
    	else{
    		nextD++;
    		return new GregorianDate(nextY, nextM, nextD);
    	}
    }

}
