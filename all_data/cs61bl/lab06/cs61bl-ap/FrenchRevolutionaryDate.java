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

    public FrenchRevolutionaryDate nextDate() {
    	int nextDay = dayOfYear() + 1;
    	int nextMonth = 1;
    	if (nextDay == 366) { // happy new year!!!
    		FrenchRevolutionaryDate answer = new FrenchRevolutionaryDate(year() + 1, 1, 1);
			return answer;
    	}
    	while (nextDay > 30){
    		nextDay -= 30;
    		nextMonth +=1;
    	}
    	FrenchRevolutionaryDate answer = new FrenchRevolutionaryDate(year(), nextMonth, nextDay);
		return answer;
    }	
}
