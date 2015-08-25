public class Date
{
	private int	myMonth;		// months range from 1 (January) through 12
								// (December)
	private int	myDateInMonth;	// dates-in-month range from 1 through the
								// number of days in the month
	private int	myYear;		// years are between 1900 and 2100 (arbitrary
								// decision)

	public Date(int month, int dateInMonth, int year)
	{
		myMonth = month;
		myDateInMonth = dateInMonth;
		myYear = year;
	}

	// Determine if the date information is internally consistent.
	public void isOK()
	{
		boolean leapDay;
		if (myYear % 4 != 0)
		{
			leapDay = false;
		} else if (myYear % 100 != 0)
		{
			leapDay = true;
		} else if (myYear % 400 != 0)
		{
			leapDay = false;
		} else
		{
			leapDay = true;
		}
		int[] dayInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		if (leapDay)
			dayInMonth[1] = 29;
		if (myMonth > 12 || myMonth < 1)
			throw new IllegalStateException("Invalid month");
		if (myDateInMonth > dayInMonth[myMonth - 1] || myDateInMonth < 1)
			throw new IllegalStateException("Invalid date in month");
		if (myYear > 2100 || myYear < 1900)
			throw new IllegalStateException("Invalid year");
	}
}
