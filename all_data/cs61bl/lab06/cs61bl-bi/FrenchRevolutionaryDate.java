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
       
 
   public int lengthOfMonth(int month){
   	if (month == 13) {
           return 5;
    } return 30;
   }


   @Override
   public Date nextDate(){
	   int myDate = dayOfMonth();
	   int myMonth = month();
	   int myYear = year() ;
	   myDate += 1;
	   if (myMonth == 13 && myDate > lengthOfMonth(myMonth)){
			myDate = 1;
			myMonth = 1;
			myYear += 1;
		} else if ( myDate > lengthOfMonth(myMonth)){
			myDate = 1;
			myMonth += 1;
		}
		return new FrenchRevolutionaryDate(myYear, myMonth, myDate);
   }
	
	
   
   
 }

