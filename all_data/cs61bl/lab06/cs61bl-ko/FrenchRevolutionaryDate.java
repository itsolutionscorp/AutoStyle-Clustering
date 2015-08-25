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
    	int fMonth = month();
    	int fYear = year();
    	int fDayOfMonth = dayOfMonth();
    	if (dayOfMonth() == 30) {
    		fMonth += 1;
    		fDayOfMonth = 1;
    	} else if (dayOfMonth() == 5 & month() == 13) {
    		fMonth = 1;
    		fYear += 1;
    		fDayOfMonth = 1;
    	} else {
    		fDayOfMonth += 1;    		
    		}
    	FrenchRevolutionaryDate result = new FrenchRevolutionaryDate(fYear, fMonth, fDayOfMonth);
    	return result;
    }

}
