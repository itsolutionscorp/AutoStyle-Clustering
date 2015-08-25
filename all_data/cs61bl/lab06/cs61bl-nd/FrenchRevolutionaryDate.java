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
		int[] monthLengths = { 30, 30, 30, 30, 30, 30, 30, 30, 30,
			30, 30, 30, 5 };
		int newdayOfYear = this.dayOfYear() + 1;
		int m = 1,d = 1, y = year();

		Date result = new FrenchRevolutionaryDate(y, m, d);
		if (newdayOfYear == 366){
			y = year() + 1;
			result = new FrenchRevolutionaryDate(y, m, d);
			return result;
		}
		
		for(int i=0; i < monthLengths.length; i++){
			if(newdayOfYear <= monthLengths[i]){
				result = new FrenchRevolutionaryDate(y, m, newdayOfYear);
				return result;
			}
			else{
				newdayOfYear = newdayOfYear - monthLengths[i];
				m++;
			}
		}

		return result;
	}

}
