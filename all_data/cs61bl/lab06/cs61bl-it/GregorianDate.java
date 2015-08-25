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
    	// Date currentD= this;
    	Date nextD;
    	if (monthLengths[month()-1]> dayOfMonth()){
			nextD= new GregorianDate(year(), month(), dayOfMonth()+1);
    	}else if(monthLengths[month()-1]== dayOfMonth() && month()< 12 ){
    		nextD= new GregorianDate(year(), month()+1, 1);	
    	}else{
    		nextD= new GregorianDate(year()+1, 1, 1);	
    	}
    	return nextD;
    }
    
//    public static void main (String[] arg){
//    	Date D = new GregorianDate(2015,12,31);
//   
//    System.out.println(D);
//    System.out.println(""+D.month());
//    System.out.println(""+D.dayOfMonth());
//
//    System.out.println(""+D.year());
//    System.out.println(D.nextDate());
//    System.out.println(D);
//    }

}
