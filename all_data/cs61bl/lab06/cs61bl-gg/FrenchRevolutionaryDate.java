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
	public FrenchRevolutionaryDate nextDate(){
		int nextDay = this.dayOfYear() + 1;
		int month = 1;
		int year = this.year();
		for (int i=0;i<13;i++){
			if (nextDay > 30 && i<12){
				month++;
				nextDay -= 30;
			}
			else if(nextDay>5 && i==12){
				nextDay -= 5;
				year++;
				month = 1;
			}
		}
		FrenchRevolutionaryDate result = new FrenchRevolutionaryDate(year,month,nextDay);
		//System.out.println(result.toString());
		return result;
	}

}
