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

    public FrenchRevolutionaryDate nextDate(){
    	int newMonth = this.month();
    	int nextDayOfYear = this.dayOfYear()+1;
    	int newYear = (nextDayOfYear-1) / 365 + this.year(); //-1 because if its 365 out of 365 its still not the new year yet.
    	int newDay = this.dayOfMonth()+1;
    	if (this.month()==13 && this.dayOfMonth()==5){
    		newMonth = 1;
    		newDay = 1;
    	} else if(this.dayOfMonth()==30) {
    		newMonth=this.month()+1;
    		newDay = 1;
    	}
    	return new FrenchRevolutionaryDate(newYear, newMonth, newDay);
    	
    	//basically given some number, out put the proper months and shit. 
    }
    
}
