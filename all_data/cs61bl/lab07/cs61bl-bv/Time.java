public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) throws IllegalArgumentException {
    	if (s == null) {
    		throw new IllegalArgumentException("Null is not a string.");
    	}
        int colonPos = s.indexOf (":");
        try {
        	try {
        		if (s.substring(0, colonPos).length() > 2) {
        			throw new IllegalArgumentException("Too many leading zeroes in hours");
        		}
        		 myHours = Integer.parseInt (s.substring (0, colonPos));
        	} catch (StringIndexOutOfBoundsException e) {
        		throw new IllegalArgumentException("No colon given.");
        	}
        } catch (NumberFormatException e) {
        	throw new IllegalArgumentException("Hours are not integers");
        }
        try {
        	if (s.substring(colonPos+1).length() != 2 ) {
        		throw new IllegalArgumentException("Too many leading zeros in minutes.");
        	}
            myMinutes = Integer.parseInt (s.substring (colonPos+1));
        } catch (NumberFormatException e) {
        	throw new IllegalArgumentException("Minutes are not integers");
        }
        if ((int)myHours > 23 || (int)myMinutes > 59) {
        	throw new IllegalArgumentException("Values for hours and minutes that are out of range.");
        }
    }
    
    public Time (int hours, int minutes) throws IllegalArgumentException {
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
