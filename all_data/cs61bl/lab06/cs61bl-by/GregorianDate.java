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

    public GregorianDate nextDate(){
    	if (this.dayOfYear() == 365){
    		return new GregorianDate(this.year() +1, 1, 1);
    	} else if (this.dayOfMonth() == monthLengths[this.month() - 1]){
    		return new GregorianDate(this.year(), this.month() +1, 1);
    	} else {
    		return new GregorianDate(this.year(), this.month(), this.dayOfMonth() +1);
    	}
    }
    
    public static void main(String[] args){
    	GregorianDate Dec31_2000 = new GregorianDate(2000, 12, 31);
    	GregorianDate Jan1_2001 = Dec31_2000.nextDate();
    	System.out.println(Jan1_2001); // 1/1/2001
    	System.out.println(Dec31_2000); //31/12/2000
    	GregorianDate Jan2_2001 = Jan1_2001.nextDate();
    	GregorianDate Jan31_2001 = new GregorianDate(2001, 1, 31);
    	GregorianDate Feb1_2001 = Jan31_2001.nextDate();
    	System.out.println(Jan2_2001); //  2/1/2001
    	System.out.println(Jan31_2001); //  31/1/2001
    	System.out.println(Feb1_2001); //  1/2/2001
    }
}
