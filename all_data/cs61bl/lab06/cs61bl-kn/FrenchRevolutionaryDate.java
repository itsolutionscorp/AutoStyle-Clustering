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
        int month = this.month();
        int year = this.year();
        int day = this.dayOfMonth() % 30;
        if (month == 13) {
        	day = this.dayOfMonth() % 5;
        }
        day++;
        if (day == 1) {
        	month = month % 13;
        	month++;
        	if (month == 1) {
        	year++;
        } }
    	return new FrenchRevolutionaryDate(year,month,day);
    }

    public static void main (String[] args) {
    	FrenchRevolutionaryDate pigu = new FrenchRevolutionaryDate(1990,1,1);
    	System.out.println(pigu.nextDate());		//should be 2/1/1990
    	pigu = new FrenchRevolutionaryDate(1990,1,29);
    	System.out.println(pigu.nextDate());		//should be 30/1/1990
    	pigu = new FrenchRevolutionaryDate(1990,1,30);
    	System.out.println(pigu.nextDate());		//should be 1/2/1990
    	pigu = new FrenchRevolutionaryDate(1990,12,29);
    	System.out.println(pigu.nextDate());		//should be 30/12/1990
    	pigu = new FrenchRevolutionaryDate(1990,12,30);
    	System.out.println(pigu.nextDate());		//should be 1/13/1990
    	pigu = new FrenchRevolutionaryDate(1990,13,1);
    	System.out.println(pigu.nextDate());		//should be 2/13/1990
    	pigu = new FrenchRevolutionaryDate(1990,13,4);
    	System.out.println(pigu.nextDate());		//should be 5/13/1990
    	pigu = new FrenchRevolutionaryDate(1990,13,5);
    	System.out.println(pigu.nextDate());		//should be 1/1/1991
    }
}
