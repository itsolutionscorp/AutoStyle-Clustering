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

    public String nextDate(){
    	if(month()==13){
    		if(dayOfMonth()==5){
    			return "" + 1 + "/" + 1 + "/" + (year()+1);
    		}else{
    			return "" + (dayOfMonth()+1) + "/" + month() + "/" + year();
    		}
    	}
    	if(dayOfMonth()==30){
    		return "" + 1 + "/" + month() + "/" + year();
    	}
    	return "" + (dayOfMonth()+1) + "/" + month() + "/" + year();
    }
}
