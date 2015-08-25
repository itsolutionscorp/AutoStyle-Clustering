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
    public Date nextday(){
    	int day = 0;
    	int month = 0;
    	int year = 0;
    	if (this.dayOfMonth() == 5 && this.month() == 13){
    		day = 1;
    		month = 1;
    		year = this.year() + 1;
    	}else if (this.dayOfMonth()==30){
    		day = 1;
    		month = this.month() + 1;
    		year = this.year();
    	}else{
    		day = this.dayOfMonth()+1;
    		month = this.month();
    		year = this.year();
    	}
    	return new FrenchRevolutionaryDate(year, month, day);
    }

}
