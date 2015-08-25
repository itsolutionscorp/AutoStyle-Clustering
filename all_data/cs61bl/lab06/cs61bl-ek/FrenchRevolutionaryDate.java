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
    	int nextM = this.month(); 
    	int nextD = this.dayOfMonth();
    	int nextY = this.year(); 
    	if (this.month() == 13){
    		if(this.dayOfMonth() == 5){
    			nextD = 1;
    			nextM = 1;
    			nextY ++;
    			return new FrenchRevolutionaryDate(nextY, nextM, nextD);
    			
    		}
    		else{
    			nextD ++;
    			return new FrenchRevolutionaryDate(nextY, nextM, nextD);
    		}
    	}
    	if (this.dayOfMonth() == 30){
    		nextD = 1;
    		nextM ++;
    		return new FrenchRevolutionaryDate(nextY, nextM, nextD);
    	}
    	else{
    		nextD++;
    		return new FrenchRevolutionaryDate(nextY, nextM, nextD);
    	}
    }
}
