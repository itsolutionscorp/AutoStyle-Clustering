public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	int colonPos;
    	try {
    		colonPos = s.indexOf (":");
    	} catch (NullPointerException e) {
    		throw new IllegalArgumentException("Time must include a colon.");
    	}
    	colonPos = s.indexOf (":");
    	
    	try {
    		myHours = Integer.parseInt (s.substring (0, colonPos));
    	} catch (Exception e) {
    		if (e instanceof NumberFormatException) {
    			throw new IllegalArgumentException("Hours must be an integer, properly formatted, or not blank.");
    		}
    		if (e instanceof StringIndexOutOfBoundsException) {
    			throw new IllegalArgumentException("Time must include a colon.");
    		}
    	}
    	myHours = Integer.parseInt (s.substring (0, colonPos));
    	
    	try {
    		 myMinutes = Integer.parseInt (s.substring (colonPos+1));
    	} catch (NumberFormatException e) {
    		throw new IllegalArgumentException("Minutes must be an integer, properly formatted, or not blank.");
    	}
    	myMinutes = Integer.parseInt (s.substring (colonPos+1));
    	
    	if (s.substring(0, colonPos).length() > 2) {
    		throw new IllegalArgumentException("Hours cannot exceed two digits.");
    	}
    	
    	if (s.substring(colonPos + 1, s.length()).length() != 2) {
    		throw new IllegalArgumentException("Minutes display must be two digits.");
    	}
    	
    	if (myHours > 23 || myHours < 0) {
    		throw new IllegalArgumentException("Military Time uses only 0 to 23 hours.");
    	}
    	
    	if (myMinutes > 59 || myMinutes < 0) {
    		throw new IllegalArgumentException("Military Time uses only 0 to 59 minutes.");
    	}
    }
    
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
