import java.util.IllegalFormatException;

public class Time {

	private int myHours;
	private int myMinutes;

	public Time(String s) {
			
			int colonPos = s.indexOf(":");
			if(colonPos == -1) {
				throw new IllegalArgumentException("No colon");
			}
			String myHoursStr = s.substring(0, colonPos);
			if (myHoursStr.equals("")){
				throw new IllegalArgumentException("No hours");
			}
			try {
				myHours = Integer.parseInt(s.substring(0, colonPos));
			} catch(NumberFormatException e) {
				throw new IllegalArgumentException("Hours is not a number");
			}
			if (myHoursStr.contains("0") && myHours != 10){
				throw new IllegalArgumentException("hours does not have correct number of zeroes");
			}
			if(myHours > 12 || myHours < 0) {
				throw new IllegalArgumentException("Hours must be in between 0 and 12");
			}
			
			
			String myMinutesStr = s.substring(colonPos + 1);

			if (myMinutesStr.equals("")){
				throw new IllegalArgumentException("No minutes");
			}
			try {
				myMinutes = Integer.parseInt(s.substring(colonPos + 1));
			} catch(NumberFormatException e) {
				throw new IllegalArgumentException("Minutes is not a number");
			}
			if (myMinutes >= 100 || (myMinutes < 10 && myMinutesStr.length() != 2)){
				throw new IllegalArgumentException("Minutes has incorrect number of zeroes");
			}
			if(myMinutes > 60 || myMinutes < 0) {
				throw new IllegalArgumentException("minutes has must be between 0 and 60");
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
