public class Time {

	private int myHours;
	private int myMinutes;

	public Time (String s) {
		
		String[] integers = new String[] {"0","1","2","3","4","5","6","7","8","9"};

		if (s == null)
			throw new IllegalArgumentException("Passed in a null string");

		int colonPos = s.indexOf (":");
		if (colonPos == -1)
			throw new IllegalArgumentException("Must have a ':' in the time");
		
		if (s.substring(0,colonPos).equals(""))
			throw new IllegalArgumentException("No hour given");
		
		if (s.substring(colonPos + 1).equals(""))
			throw new IllegalArgumentException("No minutes given");
		
		boolean hoursContainsInt = false;
		boolean minsContainsInt = false;
		for (int i = 0; i < 10; i++) {
			if (s.substring(0,colonPos).contains(integers[i]))
				hoursContainsInt = true;
			if (s.substring(colonPos + 1).contains(integers[i]))
				minsContainsInt = true;
		}
		if (!hoursContainsInt)
			throw new IllegalArgumentException("Enter an integer for hours");
		if (!minsContainsInt)
			throw new IllegalArgumentException("Enter an integer for minutes");
		
		if (s.substring(0, 1).equals(" "))
			throw new IllegalArgumentException("Extra space in beginning");
		
		if (s.substring(colonPos - 1, colonPos).equals(" "))
			throw new IllegalArgumentException("Trailing space in hours");
		
		if (s.substring(colonPos+1, colonPos+2).equals(" "))
			throw new IllegalArgumentException("Space between colon and minutes");
		
		if (s.substring(s.length() - 2).equals(" "))
			throw new IllegalArgumentException("Trailing space in minutes");
		
		
		myHours = Integer.parseInt (s.substring (0, colonPos));
		myMinutes = Integer.parseInt (s.substring (colonPos+1));

		if ( myHours < 1 || myHours > 12 )
			throw new IllegalArgumentException("Hours must be between 1 and 12");

		if (myMinutes < 0 || myMinutes > 60) 
			throw new IllegalArgumentException("Minutes must be between 1 and 60");

		if (s.substring(0, colonPos).length() > 2) 
			throw new IllegalArgumentException("Too many leading zeroes in hours");

		if (s.substring(colonPos + 1).length() < 2)
			throw new IllegalArgumentException("Single digit minutes must begin with a 0");


		if (s.substring(colonPos + 1).length() > 2)
			throw new IllegalArgumentException("Too many leading zeroes in minutes");

	}

	//    String[] timeArgs = {null, "x", "x:", ":x", "x:y", "1:", ":30",
	//	        "4: 35", "55:00", "11:99", " 3:30", "00004:45", "4:007", "4:7",
	//	        "4 :09", "3:30", "11:55"};

	public Time (int hours, int minutes) {
		myHours = hours;
		myMinutes = minutes;
	}

	public boolean equals (Object obj) {
		Time t = (Time) obj;
		return myHours == t.myHours && myMinutes == t.myMinutes;
	}

	public String toString() {
		if (myMinutes < 10) {
			return myHours + ":0" + myMinutes;
		} else {
			return myHours + ":" + myMinutes;
		}
	}
}
