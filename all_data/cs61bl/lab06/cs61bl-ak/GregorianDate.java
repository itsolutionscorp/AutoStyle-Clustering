public class GregorianDate extends Date {

    public static int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
        31, 30, 31, 30, 31};

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    public int dayOfYear() {
        int rtnValue = 0;
        for (int m = 0; m < month() - 1; m++) {
            rtnValue += monthLengths[m];
        }
        return rtnValue + dayOfMonth();
    }
    
    
    public String nextDate() {
    	return "" + (dayOfYear() + 1);
    }
    
    public static void main(String args[]) {
    	GregorianDate date1 = new GregorianDate(2000, 12, 15);
    	System.out.println(date1.dayOfYear());
    	System.out.println(date1.nextDate());
    	System.out.println(date1.dayOfYear());
    }
}
