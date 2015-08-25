public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
        int colonPos = s.indexOf (":");
        if (s.substring(0, colonPos).length() > 2 || s.substring(colonPos+1).length() > 2) {
        	throw new IllegalArgumentException("Too many digits in arguments");
        } else if (Integer.parseInt (s.substring (0, colonPos)) > 23 || Integer.parseInt (s.substring (colonPos+1)) > 59) {
        	throw new IllegalArgumentException("Hours or minutes out of range");
        } else {
        	myHours = Integer.parseInt (s.substring (0, colonPos));
            myMinutes = Integer.parseInt (s.substring (colonPos+1));
        }
      
    }
    
    public Time (int hours, int minutes) {
    	if (Integer.toString(hours).length() > 2 || Integer.toString(minutes).length() > 2) {
    		throw new IllegalArgumentException("Too many digits in arguments");
    	} else if (hours > 23 || minutes > 59) {
    		throw new IllegalArgumentException("Hours or minutes out of range");
    	} else {
    		myHours = hours;
            myMinutes = minutes;
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
