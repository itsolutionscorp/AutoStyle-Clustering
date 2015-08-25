public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null) {
    		throw new IllegalArgumentException("Input string must be a valid time; cannot be null.");
    	} 
    	if (s.indexOf (":") == -1) {
    		throw new IllegalArgumentException("Input string must be valid time; must contain a colon.");
    	}
    	if ((s.indexOf(":") == 0) || (s.indexOf(":") == s.length()-1)) {
    		throw new IllegalArgumentException("Input string must be a valid time; must have integers before and after colon.");
    	} 
    	if ((s.substring (0, s.indexOf (":")).indexOf(" ") != -1) || (s.substring (s.indexOf (":") + 1).indexOf(" ") != -1)) {
    		throw new IllegalArgumentException("Input string must be a valid time; string cannot contain spaces.");
    	}
    	
    	try {
    		Integer.parseInt (s.substring (0, s.indexOf (":")));
    		Integer.parseInt (s.substring (s.indexOf (":") + 1));
    	}
    	catch (NumberFormatException a) {
    		throw new IllegalArgumentException("Input string must be valid time; must be integer values.");
    	}

    	if ((Integer.parseInt (s.substring (0, s.indexOf (":"))) < 0) || (Integer.parseInt (s.substring (0, s.indexOf (":"))) > 23)) {
    		throw new IllegalArgumentException("Input string must be a valid time; hours must be between 0 and 23.");
    	}
    	if (((Integer.parseInt (s.substring (s.indexOf (":") + 1)) < 0) || (Integer.parseInt (s.substring (s.indexOf (":") + 1)) > 59))) {
    		throw new IllegalArgumentException("Input string must be a valid time; minutes must be between 0 and 59.");
    	}
    	if ((s.substring (0, s.indexOf (":")).length() > 2) || (s.substring (s.indexOf (":") + 1).length() != 2)) {
    		throw new IllegalArgumentException("Input string must be a valid time; hours must be no larger than two digits and minutes must be exactly two digits.");
    	}
    	
    	
        int colonPos = s.indexOf (":");
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
    }
    
    public Time (int hours, int minutes) {
        if ((hours < 0) || (hours > 23) || (minutes < 0) || (minutes > 59)) {
        	throw new IllegalArgumentException("Hours must be between 0 and 23 and minutes must be between 0 and 59.");
        }
    	
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
