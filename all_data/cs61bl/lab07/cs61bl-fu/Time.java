public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null) throw new IllegalArgumentException("No Nulls Allowed");
        int colonPos = s.indexOf (":");
        if (colonPos == -1) throw new IllegalArgumentException("No colon found");
        
        if (s.substring(0, colonPos).length() > 2) throw new IllegalArgumentException("Too many characters in hour");
        if (s.substring(colonPos+1, s.length()).length() > 2) throw new IllegalArgumentException("Too many characters in minutes"); 
        
        try {
        	myHours = Integer.parseInt (s.substring (0, colonPos));       	
        } catch (RuntimeException e) {
        	throw new IllegalArgumentException("Not a valid integer for hours");
        }
        try {
        	myMinutes = Integer.parseInt (s.substring (colonPos+1));
        } catch (RuntimeException e) {
        	throw new IllegalArgumentException("Not a valid integer for minutes");
        }
        
        if (myHours > 23 || myHours < 0) throw new IllegalArgumentException("Not a valid hour");
        if (myMinutes > 59 || myMinutes < 0) throw new IllegalArgumentException("Not a valid minute");

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
