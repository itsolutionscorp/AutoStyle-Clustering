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
    public GregorianDate nextDate() {
    	if (month() == 12 && dayOfMonth() == 31) {
    		return new GregorianDate(year()+1, 1, 1);
    	} else if (monthLengths[month()-1] == 31 && dayOfMonth() == 31){
    		return new GregorianDate(year(), month()+1, 1);
    	} else if (month() == 2 && dayOfMonth() == 28){
    		return new GregorianDate(year(), month()+1, 1);
    	} else if (monthLengths[month()-1] == 30 && dayOfMonth() == 30){
    		return new GregorianDate(year(), month()+1, 1);
    	} else {
    		return new GregorianDate(year(), month(), dayOfMonth()+1);
    	}
    }
    
    public static void main(String[] args){
    	GregorianDate lala = new GregorianDate(1, 5, 31);
    	lala.nextDate();
    	System.out.println(lala.nextDate());
    }

}
