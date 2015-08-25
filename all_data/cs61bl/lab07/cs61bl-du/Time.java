public class Time {

	private int myHours;
	private int myMinutes;

	public Time(String s) {
		isOK(s);
		int colonPos = s.indexOf(":");
		myHours = Integer.parseInt(s.substring(0, colonPos));
		myMinutes = Integer.parseInt(s.substring(colonPos + 1));
	}

	public Time(int hours, int minutes) {
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

	public static void isOK(String s) {
		if (s == null)
			throw new IllegalArgumentException("Argument can not be null");
		if (!s.contains(":"))
			throw new IllegalArgumentException("Argument can not be of the form 'x'");
		int colonPos = s.indexOf(":");
		if (colonPos == 0)
			throw new IllegalArgumentException("Argument can not be of the form ':x'");
		if (colonPos == s.length() - 1)
			throw new IllegalArgumentException("Argument can not be of the form 'x:'");
		if (colonPos != 1 && colonPos != 2)
			throw new IllegalArgumentException("Argument has to be of the form 'x:yy' or 'xx:yy'");
		if (s.length() - colonPos < 3)
			throw new IllegalArgumentException("Argument can not be of the form 'x:y'");
		if (s.length() - colonPos > 3)
			throw new IllegalArgumentException("Argument can not be of the form 'x:yyy..'");
		int myHours;
		int myMinutes;
		try {
			myHours = Integer.parseInt(s.substring(0, colonPos));
			myMinutes = Integer.parseInt(s.substring(colonPos + 1));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Argument has to be of the form 'xx:yy' where x and y have to numbers");
		}
		if (myHours < 10 && colonPos == 2)
			throw new IllegalArgumentException("Argument can not be of the form ' x:yy");
		if (myHours < 0 || myHours >= 24)
			throw new IllegalArgumentException("Hours in argument outside valid range [0, 23]");
		if (myMinutes < 0 || myMinutes >= 60)
			throw new IllegalArgumentException("Minutes in argument outside valid range [0, 59]");
	}
}
