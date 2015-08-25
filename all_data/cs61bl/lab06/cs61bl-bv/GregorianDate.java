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
    
    public GregorianDate nextDate() {
    	if (dayOfMonth() + 1 > monthLengths[month() - 1]) {
    		if (month() == 12) {
    			GregorianDate new_date = new GregorianDate(year() + 1, 1, 1);
    			return new_date;
    		} else {
    			GregorianDate new_date = new GregorianDate(year(), month() + 1, 1);
    			return new_date;
    		}
    	} else {
    		GregorianDate new_date = new GregorianDate(year() , month(), dayOfMonth() + 1);
			return new_date;
    	}
    }
    
    public static void main(String[] args) {
    	GregorianDate a = new GregorianDate(2015, 12, 30);
    	System.out.println(a.nextDate());
    	System.out.println(a.nextDate().nextDate());
    	System.out.println(a.nextDate().nextDate().nextDate());
    	
    }

}
