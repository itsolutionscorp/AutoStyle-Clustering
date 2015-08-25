public class FrenchRevolutionaryDate extends Date {

    // In a nonleap year in the French Revolutionary Calendar,
    // the first twelve months have 30 days and month 13 has five days.
	private int[] monthLengths = {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, // 1-10
						  30, 30, 5}; 							  // 11-13
	
    public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        return (month() - 1) * 30 + dayOfMonth();
    }
    
	@Override
	public Date nextDate() {
		int year = year();
		int month = month();
		int day = dayOfMonth() + 1;
		// whether day goes on to next month
		if(monthLengths[month - 1] < day) {
			day -= monthLengths[month - 1];
			month++;
			// whether month  goes on to next year
			if(month > monthLengths.length) { 
				month -= monthLengths.length;
				year++;
			}
		}
		return new FrenchRevolutionaryDate(year, month, day);
		
	}
	
	public static void main(String[] args) {
		FrenchRevolutionaryDate d = new FrenchRevolutionaryDate(1, 13, 5);
		FrenchRevolutionaryDate next = (FrenchRevolutionaryDate) d.nextDate();
		System.out.println(next.toString());
	}

}
