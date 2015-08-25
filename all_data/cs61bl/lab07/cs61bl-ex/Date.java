public class Date {

	private int myMonth; // months range from 1 (January) through 12 (December)
	private int myDateInMonth; // dates-in-month range from 1 through the number
								// of days in the month
	private int myYear; // years are between 1900 and 2100 (arbitrary decision)

	public Date(int month, int dateInMonth, int year) {
		myMonth = month;
		myDateInMonth = dateInMonth;
		myYear = year;
	}

	// Determine if the date information is internally consistent.
	public void isOK() throws IllegalStateException {
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
		if (myYear < 1900 || myYear > 2100){
			throw new IllegalStateException("Enter a valid year");
		}
		if (myMonth < 1 || myMonth > 12){
			throw new IllegalStateException("Enter a valid month");
		}
		if (leapDay) {
			if (myMonth == 2) {
				if (myDateInMonth > 29 || myDateInMonth < 1) {
					throw new IllegalStateException("Enter a valid day of month");
				}
			}
		}
		if (!leapDay) {
			if (myMonth == 2) {
				if (myDateInMonth > 28 || myDateInMonth < 1) {
					throw new IllegalStateException("Enter a valid day of month");
				}
			}
		}
		switch (myMonth) {
		case 4:
		case 6:
		case 9:
		case 11:
			if (myDateInMonth > 30 || myDateInMonth < 1) {
				throw new IllegalStateException("Enter a valid day of month");
			}
			break;
		default:
			if (myDateInMonth > 31 || myDateInMonth < 1) {
				throw new IllegalStateException("Enter a valid day of month");
			}
		}
	}
}
