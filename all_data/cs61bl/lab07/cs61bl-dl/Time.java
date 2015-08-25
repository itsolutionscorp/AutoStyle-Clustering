public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	try {
    		int colonPos = s.indexOf (":");
    		myHours = Integer.parseInt (s.substring (0, colonPos));
    		myMinutes = Integer.parseInt (s.substring (colonPos+1));
    	} catch (IllegalArgumentException e) {
    		System.out.println("User input must be a number!");
    	}
    	
    	int colonPos = s.indexOf (":");
    	if (myHours != 0 && String.valueOf(s.substring (0, colonPos)).length() > 2 || 
    			myHours != 0 && String.valueOf(s.substring (colonPos+1)).length() > 2) {
    		throw new IllegalArgumentException("Too many leading zeroes in input");
    	} else if (myHours > 23 || myHours < 0) {
    		throw new IllegalArgumentException("value for myHours that was parsed is "
    				+ "out of range (Must be between 0 and 23, inclusive");
    	} else if (myMinutes > 59 || myMinutes < 0) {
    		throw new IllegalArgumentException("value for myMinutes that was parsed is "
    				+ "out of range (Must be between 0 and 59, inclusive");
    	}
    	
        
    }
    
    public Time (int hours, int minutes) {
    	
    	if (hours > 23 || hours < 0 || minutes < 0 || minutes > 59) {
    		throw new IllegalArgumentException("value for hours or minutes are "
    				+ "out of range");
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
