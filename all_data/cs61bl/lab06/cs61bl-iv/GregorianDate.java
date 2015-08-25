public class GregorianDate extends Date {

    public static int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
        31, 30, 31, 30, 31};

    public static void main (String[] args) {
    	GregorianDate a = new GregorianDate(1, 1, 1);
    	System.out.println(a.nextDate());
    	GregorianDate b = new GregorianDate(1, 1, 31);
    	System.out.println(b.nextDate());
    	GregorianDate c = new GregorianDate(1, 12, 31);
    	System.out.println(c.nextDate());
    	GregorianDate d = new GregorianDate(1, 11, 30);
    	System.out.println(d.nextDate());
    }
    
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

    public Date nextDate() {
    	int myYear;
    	int myMonth;
    	int myDayOfMonth;
    	
    	int myDay = this.dayOfYear() + this.year()*365;
    	myDay++;
    	
    	myYear = myDay/365;
    	myDay = myDay % 365;
    	
    	myMonth = 1;
    	int k = 0;
    	
    	while (myDay > monthLengths[k]) {
    		myDay = myDay - monthLengths[k];
    		k++;
    		myMonth++;
    	}
    	
    	myDayOfMonth = myDay;
    	
    	GregorianDate myNextDate = new GregorianDate(myYear,myMonth,myDayOfMonth);
    	return myNextDate;
    	
    }
    
}
