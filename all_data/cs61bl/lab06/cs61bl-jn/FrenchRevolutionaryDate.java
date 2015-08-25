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
    
    public Date nextDate() {
		int tempDay = dayOfMonth();
		int tempMonth = month();
		int tempYear = year();
		if (tempMonth <= 12 ) {
			if (tempDay + 1 == 31) {
				tempDay = 1;
				tempMonth ++;
			} else {
				tempDay ++;
			}
		} else {
			if (tempDay + 1 == 6) {
				tempDay = 1;
				tempMonth = 1;
				tempYear ++;
			} else {
				tempDay++;
			}
		}
		
		Date date = new FrenchRevolutionaryDate(tempDay, tempMonth, tempYear);
		return date;
    }
    
    
    

}
