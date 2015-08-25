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
    	int month = month();
    	int day = dayOfMonth();
    	int year = year();
    	if(month!=13 && day==30){
    		month++;
    		day = 1;
    		
    	}
    	else if (month==13 && day==5){
    		month = 1;
    		day = 1;
    		year++;
    	}
    	else{
    		day++;
    	}
    	return new FrenchRevolutionaryDate(year, month, day);
    	
    }
    public static void main(String args[]){
    	FrenchRevolutionaryDate f = new FrenchRevolutionaryDate(2015, 13, 5);
    	System.out.println(f.nextDate().toString());
    }

}
