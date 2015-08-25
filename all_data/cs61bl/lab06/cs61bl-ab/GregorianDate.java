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
    	int month = month();
    	int date = dayOfMonth();
    	if (month == 12 & date == 31){
    		return new GregorianDate(year()+1,1,1);
    	}
    	if (monthLengths[month-1] == date){
    		return new GregorianDate(year(),month()+1,1);
     }else{
    	 return new GregorianDate(year(),month(),date+1);
     }
    }
}
