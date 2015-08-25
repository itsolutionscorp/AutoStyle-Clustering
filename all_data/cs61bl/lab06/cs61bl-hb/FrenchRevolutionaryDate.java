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
    
    @Override
    public Date nextDate(){
    	int day, month, year = 0;
    	if(this.dayOfMonth() == 5 && this.month() == 13){
    		day = 1;
    		month = 1;
    		year = this.year() +1;
    	}else{
    		day = this.dayOfMonth();
    		month = this.month();
    		year = this.year();
    	}
    	return new FrenchRevolutionaryDate(year, month, day);
    }

}
