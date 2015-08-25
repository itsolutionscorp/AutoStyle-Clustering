public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    		if (s == null) {
    			throw new IllegalArgumentException("Null is an invalid input.");
    		}
        	int colonPos = s.indexOf (":");
        	if (colonPos == -1) {
        		throw new IllegalArgumentException("Couldn't find colon.");
        	}
        	String hr = s.substring(0, colonPos);
        	String min = s.substring(colonPos+1);
        	if (hr.length() < 1 || min.length() < 1) {
        		throw new IllegalArgumentException("No hours or minutes have been put in.");
        	}
        	
        	if (s.length() < 4) {
        		throw new IllegalArgumentException("Not enough inputs");
        	}
        	if (hr.length() > 2 || min.length() > 2) {
        		throw new IllegalArgumentException("Too many leading zeros in front of hour or minutes.");
        	}
        	if (hr.length() == 2 && hr.contains("0")) {
        		throw new IllegalArgumentException("No zero needed if hour is less than 12.");
        	}
        	if (hr.contains(" ")|| min.contains(" ")) {
        		throw new IllegalArgumentException("No spacing necessary.");
        	}
        	
        	myHours = Integer.parseInt (s.substring (0, colonPos));
            myMinutes = Integer.parseInt (s.substring (colonPos+1));
        	if (myHours>23 || myMinutes>59) {
        		throw new IllegalArgumentException("Values for hours and minutes are out of range.");
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
