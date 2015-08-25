public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null) {
        	throw new IllegalArgumentException("Time cannot be null");
        }
    	int colonPos = s.indexOf (":");
    	if (!s.contains(":")) {
    		throw new IllegalArgumentException("No colon in time format");
    	}
    	if (s.charAt(s.length()-1) == ':') {
    		throw new IllegalArgumentException("No minutes given");
    	}
    	if (s.charAt(0) == ':') {
    		throw new IllegalArgumentException("No hours given");
    	}
    	for (int i=0; i<s.length(); i++) {
    		String curDigit = "" + s.charAt(i);
    		String acceptable = "0123456789:";
    		if (!acceptable.contains(curDigit)) {
    			throw new IllegalArgumentException("Non-number in argument");
    		}
    	}
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        if (s.substring (0, colonPos).length() > 2) {
        	throw new IllegalArgumentException("Too many arguments in hours");
        }
        if (s.substring (colonPos+1).length() != 2) {
        	throw new IllegalArgumentException("Minutes should have two digits");
        }
        if (myHours < 0) {
        	throw new IllegalArgumentException("Hours shouldn't be less than 0");
        } 
        if (myHours > 23) {
        	throw new IllegalArgumentException("Hours shouldn't be greater than 23");
        }
        if (myMinutes < 0) {
        	throw new IllegalArgumentException("Minutes shouldn't be less than 0");
        }
        if (myMinutes > 59) {
        	throw new IllegalArgumentException("Minutes shouldn't be greater than 59");
        }
        
    }
    public Time (int hours, int minutes) {
        myHours = hours;
        myMinutes = minutes;
        if (myHours < 0) {
        	throw new IllegalArgumentException("Hours shouldn't be less than 0");
        } else if (myHours > 23) {
        	throw new IllegalArgumentException("Hours shouldn't be greater than 23");
        } else if (myMinutes < 0) {
        	throw new IllegalArgumentException("Minutes shouldn't be less than 0");
        } else if (myMinutes > 59) {
        	throw new IllegalArgumentException("Minutes shouldn't be greater than 59");
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
