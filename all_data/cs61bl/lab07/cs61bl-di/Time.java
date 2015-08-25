public class Time {

	private int myHours;

	private int myMinutes;

	public Time(String s) {

		if (s == null) {

			throw new IllegalArgumentException("Can't have null string");

		} else if (!s.contains(":")) {

			throw new IllegalArgumentException("Missing colon");

		}

		int colonPos = s.indexOf(":");

		if (colonPos == (s.length() - 1)) {

			throw new IllegalArgumentException("Missing minutes");

		} else if (colonPos == 0) {

			throw new IllegalArgumentException("Missing hours");

		}

		myHours = Integer.parseInt(s.substring(0, colonPos));

		myMinutes = Integer.parseInt(s.substring(colonPos + 1));

		if (s.substring(colonPos + 1, colonPos + 2) == " ") {

			throw new IllegalArgumentException("Extra space after colon");

		} else if (myHours > 23) {

			throw new IllegalArgumentException("Hours out of range");

		} else if (myMinutes > 59) {

			throw new IllegalArgumentException("Minutes out of range");

		} else if (s.substring(1, colonPos) == " ") {

			throw new IllegalArgumentException("Extra space before colon");

		} else if (s.indexOf(" ") == 0) {

			throw new IllegalArgumentException("Extra space in beginning");

		} else if (s.substring(0, colonPos).length() > 2) {

			throw new IllegalArgumentException("Hours has too many 00's");

		} else if (s.substring(colonPos, s.length()).length() > 2) {

			throw new IllegalArgumentException("Minutes has too many 00's");

		} else if (s.substring(colonPos + 1, s.length()).length() != 2) {

			throw new IllegalArgumentException("Minutes must have two digits");

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