public abstract class Date {

    public abstract int dayOfYear();
    private int myDayOfMonth;
    private int myMonth;
    private int myYear;

    public Date(int year, int month, int dayOfMonth) {
        myDayOfMonth = dayOfMonth;
        myMonth = month;
        myYear = year;
    }

    public int dayOfMonth() {
        return myDayOfMonth;
    }

    public int month() {
        return myMonth;
    }

    public int year() {
        return myYear;
    }

    public String toString() {
        return "" + myMonth+ "/" + myDayOfMonth  + "/" + myYear;
    }
    //my code
 /*   
    public String nextDate() {
    	// this is using the normal calendar 
    	int [ ] monthLengths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    	// to see if it the end of the year;
    	if(myDayOfMonth ==31 && myMonth ==12){
    		return "the next day is 1/1"+(myYear+1);	
    	}
    	else if ( myDayOfMonth== monthLengths[myMonth-1]){
    		return "the next day is" + "1" +"/"+(myMonth+1)+"/"+myYear;
    	}else{
    		return "" + (1+myDayOfMonth) + "/" + myMonth + "/" + myYear; 
    	}
		
    }
    */

}

