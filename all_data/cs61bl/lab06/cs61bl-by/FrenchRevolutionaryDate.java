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

    public FrenchRevolutionaryDate nextDate(){
    	if (dayOfYear()==365){
    		return new FrenchRevolutionaryDate(this.year()+1, 1, 1);
    	} else if (this.dayOfMonth() == 30){
    		return new FrenchRevolutionaryDate(this.year(), this.month() +1, 1);
    	} else {
    		return new FrenchRevolutionaryDate(this.year(), this.month(), this.dayOfMonth() +1);
    	}
    }
    
    public static void main(String[] args){
    	FrenchRevolutionaryDate Jan1_2000 = new FrenchRevolutionaryDate(2000, 1, 1);
    	FrenchRevolutionaryDate Jan2_2000 = Jan1_2000.nextDate();
    	FrenchRevolutionaryDate Jan30_2000 = new FrenchRevolutionaryDate(2000, 1, 30);
    	FrenchRevolutionaryDate Feb1_2000 = Jan30_2000.nextDate();
    	FrenchRevolutionaryDate Thi5_2000 = new FrenchRevolutionaryDate(2000, 13, 5);
    	FrenchRevolutionaryDate Jan1_2001 = Thi5_2000.nextDate();
    	System.out.println(Jan1_2000);
    	System.out.println(Jan2_2000);
    	System.out.println(Jan30_2000);
    	System.out.println(Feb1_2000);
    	System.out.println(Thi5_2000);
    	System.out.println(Jan1_2001);
    }
}
