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
	public void isOK() {
		int[] monthLengths = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
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
		if (myYear < 1900 || myYear > 2100) {
			throw new IllegalStateException("year must bee between 1900-2100");
		} else if (myMonth < 1 || myMonth > 12) {
			throw new IllegalStateException("Month must bee between 1-12");
		} else if (myMonth == 2 && myDateInMonth > 28) {
			if (leapDay == false) {
				throw new IllegalStateException(myYear
						+ " is not a leap year thus feb only has 28 days");
			}
		} else if (monthLengths[myMonth-1] < myDateInMonth) {

			throw new IllegalStateException("the month " + myMonth
					+ " only has only " + myDateInMonth + " Days");
		} else if (1 > myDateInMonth) {
			int a = monthLengths[myMonth-1];
			if (leapDay) {
				a = a + 1;
			}
			throw new IllegalStateException("Date should be between 1-" + a);
		}
	}
}




