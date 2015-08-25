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
    	int cDay = this.dayOfMonth();
    	int nMonth = this.month();
    	int nYear = this.year();
    	int nDay = cDay + 1;
    	if (nMonth==2 && nDay==29) {
    		nMonth++;
    		nDay = 1;
    	} else if (nMonth == 1 || nMonth == 3 || nMonth == 5 || nMonth == 7 || nMonth == 8 || nMonth == 10 || nMonth == 12) {		
    		if (nDay == 32) {
    			nMonth++;
    			nDay =1;
    		}
    		if (nMonth == 13) {
    			nMonth = 1;
    			nYear++;		
    		}
    	} else if (nMonth == 4 || nMonth == 6 || nMonth == 9 || nMonth == 11){
    		if (nDay == 31) {
    			nDay = 1;
    			nMonth ++;
    		}
    	}
    	
    	return new GregorianDate(nYear, nMonth, nDay);
    }
    
    public static void main (String[] args) {
    	GregorianDate d = new GregorianDate(2015, 12, 31);
    	System.out.println(d);
    	System.out.println(d.nextDate());
    	System.out.println(d);
    }
}
