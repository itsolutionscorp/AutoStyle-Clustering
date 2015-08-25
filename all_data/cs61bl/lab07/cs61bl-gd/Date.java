public class Date {
    
    private int myMonth;        // months range from 1 (January) through 12 (December)
    private int myDateInMonth;  // dates-in-month range from 1 through the number of days in the month
    private int myYear;         // years are between 1900 and 2100 (arbitrary decision)

    public Date (int month, int dateInMonth, int year) {
        myMonth = month;
        myDateInMonth = dateInMonth;
        myYear = year;
    }
    
    // Determine if the date information is internally consistent.
    public void isOK() {
        boolean leapDay;
        if (myYear % 4 != 0) {
            leapDay = false;
        } else if (myYear % 100 != 0) {
            leapDay = true;
        } else if (myYear % 400 != 0) {
            leapDay = false;
        } else {
            leapDay = true;
        }

        // YOUR CODE HERE
        if(myYear<1900||myYear>2100){throw new IllegalStateException("Invalid Year");}
        if(myMonth<0||myMonth>12){throw new IllegalStateException("Invalid Month");}
       if(myMonth==2){
    	   if(leapDay)
    	   {if(myDateInMonth<0||myDateInMonth>29)
    	   {throw new IllegalStateException("Invalid Day");}
    		   }else{if(myDateInMonth<0||myDateInMonth>28)
    		   {throw new IllegalStateException("Invalid Day");}
    		   }
       }else{
        	if(myMonth==1||myMonth==3||myMonth==5||myMonth==7||myMonth==8||myMonth==10||myMonth==12){
        if(myDateInMonth<0||myDateInMonth>31){throw new IllegalStateException("Invalid Month");}
        }else{if(myDateInMonth<0||myDateInMonth>30){throw new IllegalStateException("Invalid Month");
        	
        }
    }
       }
}
    public void setToTomorrow ( ) {
        myDateInMonth++;
        // This may invalidate the invariant relationship
        // if tomorrow is the first day of the next month.
        if (myDateInMonth > monthLength (myMonth)) {
            myMonth++;
            if (myMonth == 13) {
                myMonth = 1;
            }
            myDateInMonth = 1;  // restore the invariant relationship
        }
    }
    public int monthLength(int Month){
    	if(Month==2){return 28;}
    	else
    	{if(Month==1||Month==3||Month ==5||Month==7||Month==8||Month==10||Month==12)
    	{return 31;}
    	else{return 30;}}
    	
    }

}
