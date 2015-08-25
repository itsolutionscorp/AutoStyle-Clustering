public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	
        if (s == null) {
        	throw new IllegalArgumentException("String is null.");
        }
        
        try {
	    int colonPos = s.indexOf (":");
	    myHours = Integer.parseInt (s.substring (0, colonPos));
	    myMinutes = Integer.parseInt (s.substring (colonPos+1));
        }
        
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Incorrect format of time.");
        }
        
    	if (myHours > 23 || myHours < 0 || myMinutes < 0 || myMinutes > 59){
    	throw new IllegalArgumentException("Values for hours and minutes are out of range.");
    	}
    	
        if (s.length() > 4) {
        	throw new IllegalArgumentException("Length of time is too long.");
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
