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
    	int returnDayofYear = this.dayOfYear()+1;
    	int returnMonth = 1;
    	
    	if(this.month() ==13 && this.dayOfMonth() ==5){
    		FrenchRevolutionaryDate returnDate2 = new FrenchRevolutionaryDate(this.year()+1,1,1);
    		return returnDate2;
    	}
    	while (returnDayofYear > 30) {
    		returnDayofYear -= 30;
    		returnMonth++;
    	}
    	FrenchRevolutionaryDate returnDate = new FrenchRevolutionaryDate(this.year(),returnMonth, returnDayofYear);
    	return returnDate;
    	
    
    }
    public static void main(String[] args){
    	FrenchRevolutionaryDate today1 = new FrenchRevolutionaryDate(2015,13,5);
        System.out.println(today1.nextDate());
    }

}
