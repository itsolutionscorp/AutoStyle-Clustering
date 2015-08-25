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
    	int tempYear, tempMonth, tempDay;
    	tempYear = year();
    	tempMonth = month();
    	tempDay = dayOfMonth();
    	tempDay++;
    	if(tempDay>monthLengths[tempMonth-1]){
    		tempDay = 1;
    		tempMonth++;
    		if(tempMonth>12){
    			tempYear++;
    			tempMonth=1;
    		}
    	}
    	Date nextDay = new GregorianDate(tempYear, tempMonth, tempDay);
    	return nextDay;
    }
    public static void main(String args[]){
    	GregorianDate date = new GregorianDate(2000,4,28);
    	Date date2 = date.nextDate();
    	System.out.println(date2);
    }

}
