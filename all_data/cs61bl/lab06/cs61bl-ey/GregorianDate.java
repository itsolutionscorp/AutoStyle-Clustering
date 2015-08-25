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
    
    @Override
    public Date nextDate(){
    	if ((super.dayOfMonth() + 1) / (double)(monthLengths[super.month() - 1]) > 1){
    		if ((super.month() + 1) / 12.0 > 1.0){
    			return new GregorianDate (super.year() + 1, super.month() - 11, super.dayOfMonth() - 30);
    		}
    		return new GregorianDate (super.year(), super.month() + 1, super.dayOfMonth() - (monthLengths[super.month() - 1] - 1));
    	}
    	return new GregorianDate (super.year(), super.month(), super.dayOfMonth() +1);
    }

}
