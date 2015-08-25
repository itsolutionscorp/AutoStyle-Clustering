public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null) {
    		throw new IllegalArgumentException("null arguments passed in");
    	}
    	if (s.indexOf(" ") != -1) {
    		throw new IllegalArgumentException("space found in argument");
    	}
    	int colonPos = s.indexOf (":");
    	if (!(colonPos == 1 || colonPos == 2)) {
    		throw new IllegalArgumentException("illegal time format w/ respect to colon");
    	}
    	if ((s.substring (colonPos + 1)).length() != 2) {
    		throw new IllegalArgumentException("Wrong number of minute digits");
    	}
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        if (myHours < 0 || myMinutes < 0 || myHours > 23 || myMinutes > 59) {
    		throw new IllegalArgumentException("Hour or minute time values out of range for String Constructor");
    	}
    }
    
    public Time (int hours, int minutes) {
    	if (hours < 0 || minutes < 0 || hours > 23 || minutes > 59) {
    		throw new IllegalArgumentException("bad time for int constructor");
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
