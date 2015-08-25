public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	String[] sp = s.split(":");

    	
    	if (sp[0].length() != 2 || sp[1].length() != 2) {
    		throw new IllegalArgumentException("Hours and minutes must be two digits"); 
    	}

        int colonPos = s.indexOf (":");
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        
        if (myHours > 23 || myMinutes > 59) {
    		throw new IllegalArgumentException("Input too large");
    	}
        
    	if (myHours < 0 || myMinutes < 0) {
    		throw new IllegalArgumentException("Hours and minutes must be positive");
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
