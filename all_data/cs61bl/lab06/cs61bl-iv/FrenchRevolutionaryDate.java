public class FrenchRevolutionaryDate extends Date {

    // In a nonleap year in the French Revolutionary Calendar,
    // the first twelve months have 30 days and month 13 has five days.
    public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }
    
    public static void main (String[] args) {
    	FrenchRevolutionaryDate a = new FrenchRevolutionaryDate(1, 1, 1);
    	System.out.println(a.nextDate());
    	FrenchRevolutionaryDate b = new FrenchRevolutionaryDate(1, 13, 5);
    	System.out.println(b.nextDate());
    	FrenchRevolutionaryDate c = new FrenchRevolutionaryDate(1, 12, 30);
    	System.out.println(c.nextDate());
    	FrenchRevolutionaryDate d = new FrenchRevolutionaryDate(1, 11, 30);
    	System.out.println(d.nextDate());
    }
    
    @Override
    public int dayOfYear() {
        return (month() - 1) * 30 + dayOfMonth();
    }
    public Date nextDate() {
    	int myYear;
    	int myMonth;
    	int myDayOfMonth;
    	
    	int myDay = this.dayOfYear() + this.year()*365;
    	myDay++;
    	
    	myYear = myDay/365;
    	myDay = myDay % 365;
    	if (myDay > 360) {
    		myMonth = 13;
    		myDayOfMonth = myDay - 360;
    	}
    	else {
    		myMonth = (myDay/30) + 1;
    		myDayOfMonth = myDay % 30; 
    	}
    	
    	FrenchRevolutionaryDate myNextDate = new FrenchRevolutionaryDate(myYear,myMonth,myDayOfMonth);
    	return myNextDate;
    	
    }

}
