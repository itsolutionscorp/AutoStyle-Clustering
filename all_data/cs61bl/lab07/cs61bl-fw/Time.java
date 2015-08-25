public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null) {
    		throw new IllegalArgumentException("Null pointer found!");
    	}
    	
        int colonPos = s.indexOf (":");
        
        if (colonPos == -1) {
        	throw new IllegalArgumentException("Missing ':' in the input!");
        }
        
        if (s.substring (0, colonPos).length() == 0) {
        	throw new IllegalArgumentException("Input is missing hour!");
        }
        
        try {
        	myHours = Integer.parseInt (s.substring (0, colonPos));
        	if (myHours >= 0 && myHours <= 9 && s.substring (0, colonPos).length() != 1) {
        		throw new IllegalArgumentException("Hour format is not correct!");
        	}
        	if (myHours >= 10 && myHours <= 23 && s.substring (0, colonPos).length() != 2) {
        		throw new IllegalArgumentException("Hour format is not correct!");
        	}
        } catch (NumberFormatException e) {
        	if (myHours >= 0 && myHours <= 9 && s.substring (0, colonPos).length() != 1) {
        		throw new IllegalArgumentException("Hour format is not correct!");
        	} else if (myHours >= 10 && myHours <= 23 && s.substring (0, colonPos).length() != 2) {
        		throw new IllegalArgumentException("Hour format is not correct!");
        	} else {
        		throw new IllegalArgumentException("Hour is not an integer!");
        	}
        }
        
        if (myHours < 0 || myHours > 23) {
    		throw new IllegalArgumentException("Input hour is out of range");
    	}
        
        if (s.length() == colonPos+1) {
        	throw new IllegalArgumentException("Input is Missing minutes ");
        }
        
        try {
        	myMinutes = Integer.parseInt (s.substring (colonPos+1));
        	if (myMinutes >= 0 && myMinutes <= 9 && s.substring (colonPos+1).length() != 2) {
        		throw new IllegalArgumentException("Minute format is not correct!");
        	}
        	if (myMinutes >= 10 && myMinutes <= 59 && s.substring (colonPos+1).length() != 2) {
        		throw new IllegalArgumentException("Minute format is not correct!");
        	}
        } catch (NumberFormatException e) {
        	if (myMinutes >= 0 && myMinutes <= 9 && s.substring (colonPos+1).length() != 2) {
        		throw new IllegalArgumentException("Minute format is not correct!");
        	} else if (myMinutes >= 10 && myMinutes <= 59 && s.substring (colonPos+1).length() != 2) {
        		throw new IllegalArgumentException("Minute format is not correct!");
        	} else {
        		throw new IllegalArgumentException("Minutes is not an integer!");
        	}
        }
        
        if (myMinutes < 0 || myMinutes > 59) {
    		throw new IllegalArgumentException("Input minute is out of range");
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
