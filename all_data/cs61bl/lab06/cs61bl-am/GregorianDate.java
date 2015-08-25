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
    	int m = super.month();
    	int d = super.dayOfMonth();
    	int y = super.year();
    	if(super.dayOfMonth() != monthLengths[m-1]){
    		d++;	
    	}
    	else{
    		if(m != 12){
    			m++;
    			d = 1;
    		}
    		else{
    			m = d = 1;
    			y++;
    		}
    	}
    	GregorianDate nd = new GregorianDate(y, m, d);
    	return nd;
    }
}


