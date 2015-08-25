public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null) {
    		throw new IllegalArgumentException("must enter a time");
    	}
    	if (!s.contains(":")) {
    		throw new IllegalArgumentException("invalid format");
    	} else if (s.contains(" ")) {
    		throw new IllegalArgumentException("time cannot contain spaces");
    	}
        int colonPos = s.indexOf (":");
        if (colonPos == 0) {
        	throw new IllegalArgumentException("must enter hours");
        } else if (colonPos == s.length()) {
        	throw new IllegalArgumentException("must enter minutes");
        } else if (colonPos > 2) {
        	throw new IllegalArgumentException("too many digits in hours");
        } else if (s.length() - colonPos > 3) {
        	throw new IllegalArgumentException("too many digits in minutes");
        } else if (s.length() - colonPos < 3) {
        	throw new IllegalArgumentException("too few digits in minutes");
        }
        try {
        	myHours = Integer.parseInt (s.substring (0, colonPos));
        	myMinutes = Integer.parseInt (s.substring (colonPos+1));
        } catch (NumberFormatException e) {
        	throw new IllegalArgumentException("must enter digits");
        }
        if (myHours < 0 || myHours > 23) {
        	throw new IllegalArgumentException("illegal hours");
        } else if (myMinutes < 0 || myMinutes > 59) {
        	throw new IllegalArgumentException("illegal minutes");
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
