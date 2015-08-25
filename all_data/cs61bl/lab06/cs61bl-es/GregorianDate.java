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
    
    public String nextDate() {
    	if (monthLengths[month()-1] == dayOfMonth()) {
    		if (month() == 12) {
    			return "1/1/" + (year()+1);
    		}
    		else {
    			return "1/" + (month()+1) + "/" + year();
    		}
    	}
    	else {
    		return (dayOfMonth()+1) + "/" + month() + "/" + year();
    	}
    }
    
    public static void main(String[] args) {
    	GregorianDate d = new GregorianDate(1996, 12, 31);
    	System.out.println(d.nextDate());
    	d = new GregorianDate(1996, 2, 28);
    	System.out.println(d.nextDate());
    	d = new GregorianDate(1996, 5, 14);
    	System.out.println(d.nextDate());
    }
}
