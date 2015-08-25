public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
        int colonPos = s.indexOf (":");
        if (s.charAt(0) == '0' && colonPos > 1) {
        	throw new IllegalArgumentException("inserted zero before time");
        }
        if (s.substring(colonPos+1).length() > 2) {
        	throw new IllegalArgumentException("too many numbers");
        }
        if (s.substring(colonPos+1).length() == 1) {
        	throw new IllegalArgumentException("too few numbers");
        }
        	myHours = Integer.parseInt (s.substring (0, colonPos));
        	myMinutes = Integer.parseInt (s.substring (colonPos+1));
        	if (myHours > 23 || myHours < 0) {
        		throw new IllegalArgumentException("hours must be between 0 - 23");
        	}
        	if (myMinutes > 59 || myMinutes < 0) {
        		throw new IllegalArgumentException("minutes must be between 0 - 59");
        	}
    }
    
    public Time (int hours, int minutes) {
    	if (hours > 23 || hours < 0) {
    		throw new IllegalArgumentException("hours must be between 0 - 23");
    	}
    	if (minutes > 59 || minutes < 0) {
    		throw new IllegalArgumentException("minutes must be between 0 - 59");
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
