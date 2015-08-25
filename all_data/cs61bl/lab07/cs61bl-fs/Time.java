public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null) {
    		throw new IllegalArgumentException("null input");
    	}
        int colonPos = s.indexOf (":");
        if (colonPos < 0) {
        	throw new IllegalArgumentException("no colon found");
        }
        try {
        	myHours = Integer.parseInt (s.substring (0, colonPos));
        	myMinutes = Integer.parseInt (s.substring (colonPos+1));
        }
        catch (Exception e) {
        	throw new IllegalArgumentException("non integer input");
        }
        if (myHours > 23 || myHours < 0 || myMinutes > 59 || myMinutes < 0) {
        	throw new IllegalArgumentException("out of range");
        }
        if (s.substring(0, colonPos).length() > 2 || s.substring(colonPos+1).length() > 2) {
        	throw new IllegalArgumentException("too many leading zeroes");
        }
        if (s.substring(colonPos+1).length() < 2) {
        	throw new IllegalArgumentException("improper display of minutes");
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
