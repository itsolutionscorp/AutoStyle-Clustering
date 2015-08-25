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
    public String nextDate(){
    	int myMonth = month();
    	int myDay = dayOfMonth();
    	int myYear = year();
    	if(dayOfMonth()+1 > monthLengths[myMonth -1]){
    		if(myMonth < 12){
    		    return "" + 1 + "/" + (myMonth+1) + "/" + myYear;
    		    }
    		else{
    			return "" + 1 + "/" + 1 + "/" + (myYear+1);
    			}
    		}
    	else{
    		return "" + (myDay+1) + "/" + myMonth + "/" + myYear;
    	}
    }
    public static void main(String[] args){
    	GregorianDate a = new GregorianDate(1999,4,3);
    	String test_date = a.nextDate();
    	System.out.println(test_date);
    }
    	
    }


