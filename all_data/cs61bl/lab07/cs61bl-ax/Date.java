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
		
		

		// YOUR CODE HERE
		if (myYear < 1900 || myYear > 2100) {
			throw new IllegalStateException("Year must be between 1900 and 2100");
		} else if (myMonth < 1 || myMonth > 12) {
			throw new IllegalStateException("Month must be between 1 and 12");
		} else if (myDateInMonth < 1) {
			throw new IllegalStateException("Day in month must be greater than 0.");
		} else if (myMonth == 2) {
			if (leapDay && myDateInMonth > 29) {
				throw new IllegalStateException("Day in month must be less than 29.");
			} else if (!leapDay && myDateInMonth > 28) {
				throw new IllegalStateException("Day in month must be less than 28.");
			}
		} else if ((myMonth == 4 || myMonth == 6 || myMonth == 9 || myMonth == 11) && myDateInMonth > 30) {
			throw new IllegalStateException("Day in month must be less than 30.");
		} else if (myDateInMonth > 31){
			throw new IllegalStateException("Day in month must be less than 31.");
		}

	}
}
