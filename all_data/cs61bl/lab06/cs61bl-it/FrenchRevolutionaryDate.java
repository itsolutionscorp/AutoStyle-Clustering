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

    public Date nextDate(){
    	Date nextD;
    	if (month()<13){
    		if (30>dayOfMonth()){
    		nextD= new FrenchRevolutionaryDate(year(), month(), dayOfMonth()+1);
    		}else {
    			nextD= new FrenchRevolutionaryDate(year(), month()+1, 1);
    		}
    	}else if(5>dayOfMonth()){
    		nextD= new FrenchRevolutionaryDate(year(), month(), dayOfMonth()+1);
    	}else {
    		nextD= new FrenchRevolutionaryDate(year()+1, 1, 1);
    	}
    	return nextD;
    }
    
//    public static void main(String[] arg){
//    	Date D = new FrenchRevolutionaryDate(2015,13,4);
//        System.out.println(D.nextDate());
//        System.out.println(D);
//        }
    }

