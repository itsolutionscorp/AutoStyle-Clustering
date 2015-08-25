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
    	int tempMonth = super.month(); 
    	int tempDay = super.dayOfMonth();
    	int tempYear = super.year();
    	if (super.month() == 13 && super.dayOfMonth() == 5){
    		tempMonth = 1;
    		tempDay = 1;
    		tempYear += 1;
    	}
    	else if (super.month() != 13 && super.dayOfMonth() == 30){
    		tempMonth += 1;
    		tempDay = 1;
    	} else{
    		tempDay += 1;
    	}
    	FrenchRevolutionaryDate x = new FrenchRevolutionaryDate(tempYear, tempMonth, tempDay);
    	return x;
    	
    }

}
