public class Time {

	private int myHours;
	private int myMinutes;

	public Time(String s) {
	
		if (s == null) {
			throw new NullPointerException();
		}

		else if (s.length() == 0) {
			throw new IllegalArgumentException("String cannot be empty");
		}
		
		
		if (!s.contains(":")){
			throw new IllegalArgumentException("Does not contain a colon");
		}
		int colonPos = s.indexOf(":");
		String hourString = s.substring(0, colonPos);
		String minuteString = s.substring(colonPos + 1);
				
		try{
		myHours = Integer.parseInt(s.substring(0, colonPos));
		}catch(NumberFormatException e){
			throw new IllegalArgumentException("Illegal hour format - not a number");
		}
		
		try{
		myMinutes = Integer.parseInt(s.substring(colonPos + 1));
		}catch(NumberFormatException e) {
			throw new IllegalArgumentException("Illegal minute format - not a number");
		}
		
		if(myHours > 23){
			throw new IllegalArgumentException("Invalid hours - hours can't be more than 23");
		}
		else if(hourString.length() > 2){
			throw new IllegalArgumentException("too many leading zeros");
		}
		
		if(myMinutes > 59){
			throw new IllegalArgumentException("Invalid minutes - minutes can't be more than 59");
		}
		else if(minuteString.length() > 2){
			throw new IllegalArgumentException("too many leading zeros");
		}
		
		
		if(myHours < 0){
			throw new IllegalArgumentException("Hours can't be negative");
		}
		if(myMinutes<0){
			throw new IllegalArgumentException("Minutes can't be negative");
		}
		
		if(minuteString.length()!= 2){
			throw new IllegalArgumentException("Minutes must be 2 digits");
		}
	
	}

	
	
	public Time(int hours, int minutes) {
		myHours = hours;
		myMinutes = minutes;

		if (hours > 23) {
			throw new IllegalArgumentException(
					"Hours input cannot be greater than 23");
		}
		if (minutes > 59) {
			throw new IllegalArgumentException(
					"Minutes input cannot be greater than 29");
		}
		if (hours < 0) {
			throw new IllegalArgumentException("Hours cannot be less than 0");
		}
		if (minutes < 0) {
			throw new IllegalArgumentException("Minutes cannot be less than 0");
		}

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
