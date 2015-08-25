public class Date {

	private int myMonth;        // months range from 1 (January) through 12 (December)
	private int myDateInMonth;  // dates-in-month range from 1 through the number of days in the month
	private int myYear;         // years are between 1900 and 2100 (arbitrary decision)

	public Date (int month, int dateInMonth, int year) {
		myMonth = month;
		myDateInMonth = dateInMonth;
		myYear = year;
	}

	// Determine if the date information is internally consistent.
	public void isOK() {
		boolean leapDay;
		if (myYear % 4 != 0) {
			leapDay = false;
		} else if (myYear % 100 != 0) {
			leapDay = true;
		} else if (myYear % 400 != 0) {
			leapDay = false;
		} else {
			leapDay = true;
		}
		if(myMonth > 12 || myMonth < 1){
			throw new IllegalStateException("Illegal month");
		}
		if(myDateInMonth < 1){
			throw new IllegalStateException("Negative Day");
		}
		else if(myDateInMonth > 31){
			throw new IllegalStateException("Illegal day for this month");
		}
		else if(myDateInMonth > 30 && myMonth%2 == 0 && myMonth != 2){
			throw new IllegalStateException("Illegal day for this month");
		}
		else if(myMonth == 2){
			if(myDateInMonth > 28 && leapDay == false || myDateInMonth >29 && leapDay == true){
				throw new IllegalStateException("Illegal day for this month");
			}
		}
		if(myYear > 2100 || myYear < 1900){
			throw new IllegalStateException("Illegal Year");
		}

	}
}
