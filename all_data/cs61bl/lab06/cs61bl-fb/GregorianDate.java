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
    public Date nextDate() {
    	int returnDayofYear = this.dayOfYear()+1;
    	int returnMonth=1;
    	
    	if (this.month() == 12 && this.dayOfMonth() == 31) {
    		GregorianDate returnDate1 = new GregorianDate(this.year() +1, 1, 1);
    	    return returnDate1;
    	}else{
    	for(int i = 0; i<=monthLengths.length-1; i++){
    		if (monthLengths[i] < returnDayofYear){
    			returnDayofYear-=monthLengths[i];
    			returnMonth++; 
    		}	
    	}    	
    	GregorianDate returnDate = new GregorianDate(this.year(), returnMonth, returnDayofYear);
    	return returnDate;
    	}
    }
    public static void main(String[] args){
        GregorianDate today = new GregorianDate(2015,6,30);
        System.out.println(today.nextDate());
    }
   
}
