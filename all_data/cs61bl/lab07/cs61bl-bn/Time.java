public class Time {

	private int myHours;
	private int myMinutes;

	public Time(String s) {
		int colonPos = s.indexOf(":");

		if (s.equals(null)) {
			throw new IllegalArgumentException("time must not be null");
		} else if (s.indexOf(":") == -1) {
			throw new IllegalArgumentException("time must have :");
		} else if (s.indexOf(":") == 0) {
			throw new IllegalArgumentException("time must have hour");
		} else if (s.indexOf(":") == s.length() - 1) {
			throw new IllegalArgumentException("time must have minute");
		} else if (s.substring(0, colonPos).length() > 2) {
			throw new IllegalArgumentException(
					"hour must be at most two digits");
		} else if (s.substring(colonPos + 1).length() > 2) {
			throw new IllegalArgumentException(
					"minutes must be at most two digits");
		}
		for (int i = 0; i < s.length(); i++) {

			if (s.charAt(i) != ':' && !Character.isDigit(s.charAt(i))) {
				throw new IllegalArgumentException(
						"time must have only int not letter or space");
			}
		}

		myHours = Integer.parseInt(s.substring(0, colonPos));
		myMinutes = Integer.parseInt(s.substring(colonPos + 1));
		if (myHours < 0 || 23 < myHours) {
			throw new IllegalArgumentException("hours must be 0 to 23");
		}
		if (myMinutes < 0 || 59 < myMinutes) {
			throw new IllegalArgumentException("minutes must be 0 to 59");
		}

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

}
