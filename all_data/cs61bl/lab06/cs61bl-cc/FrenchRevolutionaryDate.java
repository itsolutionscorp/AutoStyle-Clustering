public class FrenchRevolutionaryDate extends Date {

    // In a nonleap year in the French Revolutionary Calendar,
    // the first twelve months have 30 days and month 13 has five days.
    public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        return (month() - 1) * 30 + dayOfMonth();
    }
    
    public Date nextDate() {
    	int cDay = this.dayOfMonth();
    	int nMonth = this.month();
    	int nYear = this.year();
    	int nDay = cDay + 1;
    	if (nDay == 31 && nMonth < 13) {
    		nMonth++;
    		nDay = 1;
    	} else if (nMonth == 13 && nDay == 6) {
    		nMonth = 1;
    		nDay = 1;
    		nYear ++;
    	}
    	return new FrenchRevolutionaryDate(nYear, nMonth, nDay);
    }
    
    public static void main (String[] args) {
    	FrenchRevolutionaryDate d = new FrenchRevolutionaryDate(2015, 12, 30);
    	System.out.println(d);
    	System.out.println(d.nextDate());
    	System.out.println(d);
    }
}
