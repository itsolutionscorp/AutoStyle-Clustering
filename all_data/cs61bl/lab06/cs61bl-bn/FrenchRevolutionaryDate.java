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
    public int nextDate(){
    	if(month() <= 12 && dayOfMonth() < 30) {
    		return dayOfMonth()+1;
    	} else if (month() <= 12 && dayOfMonth() == 30) {
    		return 1;
    	} else if (dayOfMonth() < 5) {
    		return dayOfMonth() + 1;
    	} else {
    		return 1;
    	}
    }
    public static void main(String[]args){
		Date f = new FrenchRevolutionaryDate(2015,12,30);
		int n = f.nextDate();
		System.out.println("nextdate: "+n);
		System.out.println("day of month:"+f.dayOfMonth());
		
    }
}
