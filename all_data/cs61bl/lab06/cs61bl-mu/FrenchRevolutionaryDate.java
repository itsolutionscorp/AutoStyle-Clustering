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
    	int dayinmonth;
	    if(month()<= 12){
	    	dayinmonth = 30;
	    }else{
	    	dayinmonth = 5;
	    }
	    if(month() == 13 && dayOfMonth() == dayinmonth){
	    	int dayOfMonth = 1;
	    	int month = 1;
			FrenchRevolutionaryDate x = new FrenchRevolutionaryDate(year()+1, month, dayOfMonth);
			return x;
	    }else if(  dayOfMonth() == dayinmonth){
			int dayOfMonths = 1;
			FrenchRevolutionaryDate d = new FrenchRevolutionaryDate(year(), month()+1, dayOfMonths);
			return d;
		}else{
			FrenchRevolutionaryDate d1 = new FrenchRevolutionaryDate(year(), month(), dayOfMonth()+1);
			return d1;
		}
	}
  
    
}
