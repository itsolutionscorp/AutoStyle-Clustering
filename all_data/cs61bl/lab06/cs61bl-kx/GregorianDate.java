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
    	int month = month();
    	int day = dayOfMonth();
    	int year = year();
    	if(day==31 && month==12){
    		month = 1;
    		day = 1;
    		year++;
    	}
    	else if(day==monthLengths[month-1]){
    		month++;
    		day = 1;
    	}
    	else{
    		day++;
    	}
    	return new GregorianDate(year, month, day);
    	
    	
    	
    	
    }
    public static void main(String args[]){
    	GregorianDate g = new GregorianDate(2015, 4, 23);
    	System.out.println(g.nextDate().toString());
    }

}
