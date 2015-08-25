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
    public Date nextday(){
    	int day = 0;
    	int month = 0;
    	int year = 0;
    	if (this.month() % 2 == 1 && this.month() < 8 && this.dayOfMonth() ==31 ){
    		day = 1;
    		month = this.month() + 1;
    		year = this.year();
    	}else if (this.month() % 2 == 0 && this.month() < 11 && this.dayOfMonth() ==31 ){
    		day = 1;
    		month = this.month() + 1;
    		year = this.year();
    	}else if (this.month() == 12 && this.dayOfMonth() ==31){
    		day = 1;
    		month = 1;
    		year = this.year() + 1;
    	}else if (this.month() == 2 && this.dayOfMonth() == 28){
    		day = 1;
    		month = 3;
    		year = this.year();
    	}else if (this.dayOfMonth() == 30){
    		day = 1;
    		month = this.month() + 1;
    		year = this.year();
    	}else{
    		day = this.dayOfMonth() + 1;
    		month = this.month();
    		year = this.year();
    	}
    	return new GregorianDate(year, month, day);
    }

}
