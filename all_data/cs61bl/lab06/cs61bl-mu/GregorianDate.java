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
    
    public Date nextDate(){
    	int dayinmonth = monthLengths[month()-1];
	    
	    if(month() == 12 && dayOfMonth() == dayinmonth){
	    	int dayOfMonth = 1;
	    	int month = 1;
	    	GregorianDate x = new GregorianDate(year()+1, month, dayOfMonth);
			return x;
	    }else if(  dayOfMonth() == dayinmonth){
			int dayOfMonths = 1;
			GregorianDate d = new GregorianDate(year(), month()+1, dayOfMonths);
			return d;
		}else{
			GregorianDate d1 = new GregorianDate(year(), month(), dayOfMonth()+1);
			return d1;
		}

}
}
