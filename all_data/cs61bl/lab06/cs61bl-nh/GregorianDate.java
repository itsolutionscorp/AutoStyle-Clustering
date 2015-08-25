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
    	int nextYear = year();
    	int nextMonth = month();
    	int nextDayOfMonth = dayOfMonth() +1;
    	if (nextMonth ==1 || nextMonth ==3 || nextMonth ==5 || nextMonth ==7 || nextMonth ==8|| nextMonth == 10 || nextMonth ==12){
    		if (nextDayOfMonth >=31){
    			nextMonth++;
    			nextDayOfMonth = nextDayOfMonth % 31;
    			if (nextMonth >12){
    				nextYear ++;
    				nextMonth = nextMonth %12;
    			}
    			
    		}
    	}
    	else if (nextMonth ==2){
    		if (nextDayOfMonth >=28){
    			nextMonth ++;
    			nextDayOfMonth = nextDayOfMonth % 28;
    		}
    		
    	}
    	else {
    		if (nextDayOfMonth >=30){
    			nextMonth ++;
    			nextDayOfMonth = nextDayOfMonth % 30;
    		}
    	}
    	GregorianDate output = new GregorianDate(nextYear,nextMonth,nextDayOfMonth);
    	return output;
    }
    public static void main (String args[]){
    	GregorianDate myDate1 = new GregorianDate(1900,4,30);
    	System.out.println(myDate1.nextDate().toString());
    	GregorianDate myDate2 = new GregorianDate(1900,2,28);
    	System.out.println(myDate2.nextDate().toString());
    	GregorianDate myDate3 = new GregorianDate(1900,12,31);
    	System.out.println(myDate3.nextDate().toString());
    }

    

}
