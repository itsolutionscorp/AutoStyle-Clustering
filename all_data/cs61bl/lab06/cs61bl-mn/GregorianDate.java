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
    	int daysInMonth = monthLengths[month()-1];
    	if (dayOfMonth() != daysInMonth) {
			return new GregorianDate(year(), month(), dayOfMonth()+1);
		} else {
			if (month() != 12) {	
				return new GregorianDate(year(), month()+1, 1);
			} else {
				return new GregorianDate(year()+1, 1, 1);
			}	
    	}
    }
    public static void main (String [] args)
    {
    	GregorianDate f1 = new GregorianDate(1111,12,31);
    	System.out.println(f1.nextDate());
    	GregorianDate f2 = new GregorianDate(1111,2,28);
    	System.out.println(f2.nextDate());
    	GregorianDate f3 = new GregorianDate(0,4,30);
    	System.out.println(f3.nextDate());
    }

}
