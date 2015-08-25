
public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null) {
    		throw new IllegalArgumentException ("cannot pass in a null argument");
    	}
    	int colonPos = s.indexOf (":");
        if (colonPos == -1) {
        	throw new IllegalArgumentException ("string does not contain a colon");
        }
        try {
        	myHours = Integer.parseInt (s.substring (0, colonPos));
        } catch(Exception e) {
        	throw new IllegalArgumentException ("bad hours value");
        }
        if (myHours < 0 || myHours > 12) {
    		throw new IllegalArgumentException ("hours value out of range");
    	}
        try {
        	myMinutes = Integer.parseInt (s.substring (colonPos+1));
        } catch(Exception e) {
        	throw new IllegalArgumentException ("bad minutes value");
        }
        if (myMinutes < 0 || myMinutes >= 60) {
    		throw new IllegalArgumentException ("minutes value out of range");
    	} else if (s.substring (0, colonPos).length() > 2) {
        	throw new IllegalArgumentException ("too many leading zeroes in hours");
        } else if (s.substring (colonPos+1).length() != 2) {
        	throw new IllegalArgumentException ("wrong number of digits in minutes value");
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
