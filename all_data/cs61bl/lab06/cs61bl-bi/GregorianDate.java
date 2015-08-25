public class GregorianDate extends Date {

    public static int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
        31, 30, 31, 30, 31};

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
        
    }

    @Override
    public int dayOfYear() {
        int rtnValue = 0;
        for (int m = 0; m < month() - 1; m++) {
            rtnValue += monthLengths[m];
        }
        return rtnValue + dayOfMonth();
    }

    
    public int lengthOfMonth(int month){
        if (month == 2){
        	return 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else {
            return 31;
        }
    }

    @Override
    public Date nextDate(){
 	   int myDate = dayOfMonth();
 	   int myMonth = month();
 	   int myYear = year() ;
 	   myDate += 1;
 	   if (myMonth == 12 && myDate > lengthOfMonth(myMonth)){
 				myDate = 1;
 				myMonth = 1;
 				myYear += 1;
 			} else if ( myDate > lengthOfMonth(myMonth)){
 				myDate = 1;
 				myMonth += 1;
 			}
 		return new GregorianDate(myYear, myMonth, myDate);
    }




}
