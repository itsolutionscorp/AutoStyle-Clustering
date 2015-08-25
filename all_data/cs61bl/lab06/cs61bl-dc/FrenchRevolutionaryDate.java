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
    
    public Date nextDate()
    {
    	int day = this.dayOfMonth() + 1;
    	int month = this.month();
    	int year = this.year();
    	if (day > 30 || (day > 5 && month == 13))
    	{
    		month ++;
    		day = 1;
    	}
    	if (month > 13)
    	{
    		year ++;
    		month = 1;
    	}
    	return new FrenchRevolutionaryDate(year, month, day);
    }
}
