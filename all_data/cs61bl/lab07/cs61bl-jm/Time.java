public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null) {
    		throw new IllegalArgumentException("Error - Given string is empty (null argument)");
    	}
    	if (s.contains(":") == false) {
    		throw new IllegalArgumentException("Error - Given time lacks a colon between the hour and minutes");
    	}
        int colonPos = s.indexOf (":");
        if (s.substring (0, colonPos).contains("00") && Integer.parseInt (s.substring (0, colonPos)) != 0) {
        	throw new IllegalArgumentException("Error - Given hour value has excess zeroes");
        } else if (s.substring (colonPos+1).contains("00") && Integer.parseInt (s.substring (colonPos+1)) != 0) {
        	throw new IllegalArgumentException("Error - Given minute value has excess zeroes");
        }
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        if (myHours < 10 && s.indexOf(myHours) == 0) {
        	throw new IllegalArgumentException("Error - Given hours value must include 0 before value");
        } else if (myMinutes < 10 && s.charAt(colonPos+1) != '0') {
        	throw new IllegalArgumentException("Error - Given minutes value must include 0 before value");
        } else if (myHours > 23 || myHours < 0) {
        	throw new IllegalArgumentException("Error - Given hour value is out of range (Must be between 0 and 23)");
        } else if (myMinutes > 59 || myMinutes < 0) {
        	throw new IllegalArgumentException("Error - Given minutes value is out of range (Must be between 0 and 59)");
        }

    }
    
    public Time (int hours, int minutes) {
    	myHours = hours;
        myMinutes = minutes;
        if (myHours > 23 || myHours < 0) {
        	throw new IllegalArgumentException("Error - Given hour value is out of range...");
        } else if (myMinutes > 59 || myMinutes < 0) {
        	throw new IllegalArgumentException("Error - Given minutes value is out of range...");
        }
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
