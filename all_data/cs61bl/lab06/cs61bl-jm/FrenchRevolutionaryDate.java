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
    public Date nextDate(){
    	int a; int b = month(); int c = year();
    	if ((dayOfMonth() < 30 && month() != 13) || (dayOfMonth() < 5 && month() == 13)){
    		a = dayOfMonth()+1;
    	} else if (month() < 13){
    		b = month()+1;
    		a = 1;
    	} else {
    		c = year()+1;
    		a = 1;
    		b = 1;
    	}
    	return new FrenchRevolutionaryDate(c, b, a);
    }
}
