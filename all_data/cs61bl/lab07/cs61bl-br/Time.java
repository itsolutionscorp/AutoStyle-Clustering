public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
	    /* 1) NullPointerException
	     * 2) Not an Integer
	     * 3) No minutes
	     * 4) No hours
	     * 5) Hours > 23 or < 0
	     * 6) 0 < Minutes < 59
	     * 7) Too many 0's in the minutes
	     * 8) No leading 0 in minutes for single digit minute values
	     */
    	if (s == null) {
    		throw new IllegalArgumentException("Value cannot be null!");
    	}
        int colonPos = s.indexOf (":");
        if (colonPos == -1) {
        	throw new IllegalArgumentException("You didn't have a colon.");
        }
        if (s.charAt(0) == ':'){
        	throw new IllegalArgumentException("You didn't provide an hours value.");
        }
        for(int i = 0; i < s.length(); i++){
        	if(s.charAt(i) == ' ') {
        		throw new IllegalArgumentException("You put a space in your time value.");
        	}
        }
        if(s.length() >= 5) {
        	  throw new IllegalArgumentException("Too many numbers provided.");
        }
        if(s.length() <= 3) {
        	throw new IllegalArgumentException("Too few numbers provided.");
        }

        
        try {
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));

        }
        catch(NumberFormatException e) {
        	throw new IllegalArgumentException("The string you made contains non-integers.");
        }
        if((myHours > 23) || (myHours < 0)) {
        	throw new IllegalArgumentException("Invalid hour value.");
        }
        if((myMinutes> 59) || (myMinutes < 0)) {
        	throw new IllegalArgumentException("Invalid minutes value.");
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
