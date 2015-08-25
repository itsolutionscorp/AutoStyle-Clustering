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
    public String nextDate(){
    	int myDayofYear_next = dayOfYear() + 1;
    	int myYear = year();  
    	if(myDayofYear_next == 366){
    		return "" + 1 + "/" + 1 + "/" + (myYear+1);
    	}
    	else if (myDayofYear_next > 360){
    		return "" + myDayofYear_next%12 + "/" + 13 + "/" + myYear;
    	}
    	else{
    		return "" + myDayofYear_next%30 + "/" + (myDayofYear_next/30+1) + "/" + myYear;
    	}
    	 
    }}
  /*  public static void main(String[] args){
    	FrenchRevolutionaryDate a = new FrenchRevolutionaryDate(1999,6,30);
    	String test_date = a.nextDate();
    	System.out.println(test_date);
    }

}*/
