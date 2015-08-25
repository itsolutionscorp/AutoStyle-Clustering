public class FrenchRevolutionaryDate extends Date {

    // In a nonleap year in the French Revolutionary Calendar,
    // the first twelve months have 30 days and month 13 has five days.
    public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        return (month() - 1) * 30 + dayOfMonth();
    }
    
    @Override
    public Date nextDate() {
    	if (month() <13) {
    		if (dayOfMonth()<30) {
    			return new FrenchRevolutionaryDate(year(), month(), dayOfMonth()+1);
    		}else{
    			return new FrenchRevolutionaryDate(year(), month() + 1, dayOfMonth()-29);
    		}
    	}else {
    		if (dayOfMonth()<5){
    			return new FrenchRevolutionaryDate(year(), month(), dayOfMonth()+1);
    					
    		}else{
    			return new FrenchRevolutionaryDate(year()+1, month()-(month()-1), dayOfMonth()-4);
    		}
    	}
    	
    }
    public static void main(String[] arg){
    	///////test///////
    	FrenchRevolutionaryDate date1 = new FrenchRevolutionaryDate(2000,1,1); // extreme case
    	System.out.println(date1.nextDate().toString());
    	
    	FrenchRevolutionaryDate date2 = new FrenchRevolutionaryDate(2000,1,30); 
    	System.out.println(date2.nextDate().toString());
    	
    	FrenchRevolutionaryDate date3 = new FrenchRevolutionaryDate(2000,2,30); 
    	System.out.println(date3.nextDate().toString());
    	
    	FrenchRevolutionaryDate date4 = new FrenchRevolutionaryDate(2000,12,30);// extreme case
    	System.out.println(date4.nextDate().toString());
    	
    	FrenchRevolutionaryDate date5 = new FrenchRevolutionaryDate(2000,13,5); // extreme case
    	System.out.println(date5.nextDate().toString());
    	
    	FrenchRevolutionaryDate date6 = new FrenchRevolutionaryDate(2000,12,5);
    	System.out.println(date6.nextDate().toString());
    	
    	FrenchRevolutionaryDate date7 = new FrenchRevolutionaryDate(2000,10,26);
    	System.out.println(date7.nextDate().toString());
    	
    }

}
