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
    public GregorianDate nextDate(){
    	int nextDay = this.dayOfYear() + 1;
    	int month = 1;
    	int year = this.year();
    	for (int i=0;i<monthLengths.length;i++){
    		if (nextDay > monthLengths[i]){
    			month++;
    			nextDay -= monthLengths[i];
    			if (month>12){
    				year++;
    				month = 1;
    			}
    		}
    	}
    	GregorianDate result = new GregorianDate(year,month,nextDay);
    	//System.out.println(result.toString());
    	return result;
    }
}
