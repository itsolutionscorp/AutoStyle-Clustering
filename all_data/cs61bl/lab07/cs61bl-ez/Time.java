public class Time {

	private int myHours;
	private int myMinutes;

	public Time(String s) {
		if (s == null) {
			throw new IllegalArgumentException(
					"You need to enter a string that isn't null");
		}

		int colonPos = s.indexOf(":");
		try {
			myHours = Integer.parseInt(s.substring(0, colonPos));
		} catch (NumberFormatException e) {
			System.out.println("Number Format Exception: Please enter a number for the hours");
		} 
		catch (StringIndexOutOfBoundsException e) {
			System.out.println("String out of bounds exception");
		}
		if (myHours > 23) {
			throw new IllegalArgumentException(
					"Please enter a valid number for hours");
		}

		try {
			myMinutes = Integer.parseInt(s.substring(colonPos + 1));
		} catch (NumberFormatException e) {
			System.out.println("you need to enter numbers for the minutes");
		}
		if (myMinutes > 60) {
			throw new IllegalArgumentException(
					"Please enter a valid number for minutes");
		}
		if (!s.contains(":")) {
			throw new IllegalArgumentException("You need a : in your string");
		}
		if (s.substring(s.indexOf(":"), s.length()).length() != 3) {
			throw new IllegalArgumentException(
					"You need to enter a number without two zeros");
		}
		if (s.length() < 4) {
			throw new IllegalArgumentException("Your string is too small!");
		}
		if (s.contains(" ")) {
			throw new IllegalArgumentException("Your string has a space!");
		}
		
		if (s.length() > 5) {
			throw new IllegalArgumentException("String is too big");
		}
		

	}

	public Time(int hours, int minutes) {
		if (hours > 23) {
			throw new IllegalArgumentException(
					"Your hours must be less than 24");
		}
		if (minutes > 60) {
			throw new IllegalArgumentException(
					"Your minutes must be less than 60");
		}

		myHours = hours;
		myMinutes = minutes;
	}

	public boolean equals(Object obj) {
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
