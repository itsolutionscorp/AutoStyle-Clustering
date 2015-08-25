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
    	if (month() < 12) {
    		if (dayOfMonth()< monthLengths[month()-1]) {
    			return new GregorianDate(year(), month(), dayOfMonth()+1);
    		}else{
    			return new GregorianDate(year(), month() + 1, 1);
    		}
    	}else {
    		if (dayOfMonth()< monthLengths[month()-1]){
    			return new GregorianDate(year(), month(), dayOfMonth()+1);
    					
    		}else{
    			return new GregorianDate(year()+1, 1, 1);
    		}
    	}
    }
    
    public static void main(String[] arg){
    	///////test///////
    	GregorianDate date1 = new GregorianDate(1999,1,1); // extreme case
    	System.out.println(date1.nextDate().toString());
    	
    	GregorianDate date2 = new GregorianDate(1999,1,31); 
    	System.out.println(date2.nextDate().toString());
    	
    	GregorianDate date3 = new GregorianDate(1999,2,28); 
    	System.out.println(date3.nextDate().toString());
    	
    	GregorianDate date4 = new GregorianDate(1999,12,31);// extreme case
    	System.out.println(date4.nextDate().toString());
    	
    	GregorianDate date5 = new GregorianDate(1999,6,30); // extreme case
    	System.out.println(date5.nextDate().toString());
    	
    	GregorianDate date6 = new GregorianDate(1999,12,5);
    	System.out.println(date6.nextDate().toString());
    	
    	GregorianDate date7 = new GregorianDate(1999,3,31);
    	System.out.println(date7.nextDate().toString()); 
    	
    	GregorianDate date8 = new GregorianDate(1999,7,31);
    	System.out.println(date8.nextDate().toString());
    	
    	GregorianDate date9 = new GregorianDate(1999,8,31);
    	System.out.println(date9.nextDate().toString());
    	
    }
}
