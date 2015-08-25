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
    //Bazz) but we are not suppose to return a string, we are suppose to return a date object
    //my code 
    public FrenchRevolutionaryDate nextDate() {
    	int year = 0;
    	int month = 0;
    	int day = 0;
    	// test if it is the last day of the year
    	if(super.dayOfMonth() ==5 &&  super.month()==13){
    	 year = super.year() + 1;
   		 day = 1;
   		 month = 1;
   		    	} //if it is the last day of the month
    	else if (super.dayOfMonth() == 30){
    		year = super.year();
      		 day = 1;
      		 month = super.month() + 1;
    	}else{
    		year = super.year();
     		 day = super.dayOfMonth() + 1;
     		 month = super.month();
    	}
	return new FrenchRevolutionaryDate(year,month,day);
    }

}
