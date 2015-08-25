public class GregorianDate extends Date {

    public static int [ ] monthLengths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public GregorianDate (int year, int month, int dayOfMonth) {
        super (year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear ( ) {
        int rtnValue = 0;
        for (int m=0; m<month()-1; m++) {
            rtnValue += monthLengths[m];
        }
        return rtnValue + dayOfMonth ( );
    }
    
    public Date nextDate(){
    	Date nextDate = new GregorianDate(0,0,0);
    	if (this.month() == 2 && this.dayOfMonth() == 28){
    		nextDate = new GregorianDate (this.year(), this.month() +1, 1);
    	} else if (this.month() == 12 && this.dayOfMonth() == 31){
    		nextDate = new GregorianDate (this.year()+1, 1, 1);
    	} else if (this.dayOfMonth() == 31){
    		nextDate = new GregorianDate (this.year(), this.month() +1, 1);
    	} else if (this.dayOfMonth() == 30){
    		nextDate = new GregorianDate (this.year(), this.month() +1, 1);
    	} else {
    		nextDate = new GregorianDate (this.year(), this.month(), this.dayOfMonth()+1);
    	}
    	return nextDate;
    }		
    		
 		
    
}
