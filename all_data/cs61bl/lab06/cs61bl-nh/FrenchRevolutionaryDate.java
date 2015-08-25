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
    	int nextDayOfMonth = dayOfMonth() +1;
    	int nextMonth = month();
    	int nextYear = year();
    	if (nextMonth <13){
    		if(nextDayOfMonth >=30){
    			nextMonth ++;
    			nextDayOfMonth = nextDayOfMonth % 30;
    		}
    	}
    	else{
    		if (nextDayOfMonth >=5){
    			nextYear ++;
    			nextMonth ++;
    			nextMonth = nextMonth % 13;
    			nextDayOfMonth = nextDayOfMonth % 5;
    		}
    	}
    	FrenchRevolutionaryDate output;
    	output = new FrenchRevolutionaryDate(nextYear,nextMonth,nextDayOfMonth);
    	return output;
    }
    public static void main (String args[]){
    	FrenchRevolutionaryDate myDate1 = new FrenchRevolutionaryDate(1900,1,1);
    	System.out.println(myDate1.nextDate().toString());
    	FrenchRevolutionaryDate myDate2 = new FrenchRevolutionaryDate(1900,1,30);
    	System.out.println(myDate2.nextDate().toString());
    	FrenchRevolutionaryDate myDate3 = new FrenchRevolutionaryDate(1900,13,5);
    	System.out.println(myDate3.nextDate().toString());
    }

}
