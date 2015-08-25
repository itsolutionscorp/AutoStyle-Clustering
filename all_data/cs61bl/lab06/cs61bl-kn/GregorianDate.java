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
    
    public Date nextDate() {
        int month = this.month();
        int year = this.year();
        int day = this.dayOfMonth() %  monthLengths[month-1];
        day++;
        if (day == 1) {
        	month = month % 12;
        	month++;
        	if (month == 1) {
        		year++;
        }
        }
    	return new GregorianDate(year,month,day);
    }
    
    public static void main (String[] args) {
    	GregorianDate pigu = new GregorianDate(1990,1,1);
    	System.out.println(pigu.nextDate());		//should be 2/1/1990
    	pigu = new GregorianDate(1990,1,30);
    	System.out.println(pigu.nextDate());		//should be 31/1/1990
    	pigu = new GregorianDate(1990,1,31);
    	System.out.println(pigu.nextDate());		//should be 1/2/1990							
    	pigu = new GregorianDate(1990,2,27);
    	System.out.println(pigu.nextDate());		//should be 28/2/1990
    	pigu = new GregorianDate(1990,2,28);
    	System.out.println(pigu.nextDate());		//should be 1/3/1990
    	pigu = new GregorianDate(1990,12,30);
    	System.out.println(pigu.nextDate());		//should be 31/12/1990
    	pigu = new GregorianDate(1990,12,31);
    	System.out.println(pigu.nextDate());		//should be 1/1/1991
    }
    
}
