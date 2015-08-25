public class Time {

	private int myHours;
	private int myMinutes;

	public Time(String s) {
		if (s == null){
			throw new IllegalArgumentException ("got null pointer");
		}
		String minutes = s.substring(s.lastIndexOf(' ')+1);
		if (minutes.length()!=2){
			throw new IllegalArgumentException("illegal time");
		}
		if (!(s.length() == 5 || s.length() == 4)) {
			throw new IllegalArgumentException("illegal time");
		}
		int colonPos = s.indexOf(":");
		myHours = Integer.parseInt(s.substring(0, colonPos));
		myMinutes = Integer.parseInt(s.substring(colonPos + 1));
//		if (Integer.toString(myMinutes).length() > 2){
//			throw new IllegalArgumentException("wrong minutes value");
//	}
		if (!(myHours >=0 && myHours < 24)) {
			throw new IllegalArgumentException("wrong hour value");
		}
		if (!(myMinutes >=0 && myMinutes < 60)) {
			throw new IllegalArgumentException("wrong minutes value");
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
