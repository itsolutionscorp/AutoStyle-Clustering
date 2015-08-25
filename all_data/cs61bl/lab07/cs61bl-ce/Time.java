public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
        int colonPos = s.indexOf (":");
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        if (colonPos != 2 || colonPos != 1) {
        	throw new IllegalArgumentException("nonvalid hours length");
        }
        if (s.substring(colonPos+1).length() != 2) {
        	throw new IllegalArgumentException("nonvalid minutes length");
        }
        if (myHours > 23) {
        	throw new IllegalArgumentException("value must be less than 24");
        }
        if (myMinutes > 59) {
        	throw new IllegalArgumentException("value must be less than 60");
        }
    }
    
    public Time (int hours, int minutes) {
        myHours = hours;
        myMinutes = minutes;
        if (myHours > 23) {
        	throw new IllegalArgumentException("value must be less than 24");
        }
        if (myMinutes >59) {
        	throw new IllegalArgumentException("value must be less than 60");
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
