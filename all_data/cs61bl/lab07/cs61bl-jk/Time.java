public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	String hourString;
    	String minuteString;
    	try {
    		int colonPos = s.indexOf (":");
    		hourString = s.substring (0, colonPos);
    		minuteString = s.substring (colonPos+1);
    		myHours = Integer.parseInt (hourString);
        	myMinutes = Integer.parseInt (minuteString);
    	} catch (NullPointerException e) {
    		throw new IllegalArgumentException("Null Pointer!");
    	} catch (StringIndexOutOfBoundsException e) {
    		throw new IllegalArgumentException("String Index Out Of Bounds! ");
    	}
    	
    	if (hourString.length() > 2 || hourString.length() < 1) {
    		throw new IllegalArgumentException("Invalid format for Hours");
    	}
    	if (minuteString.length() != 2) {
    		throw new IllegalArgumentException("Invalid format for Minutes");
    	}
    	if (s.indexOf(' ') != -1) {
    		throw new IllegalArgumentException("Space in the String!!");
    	}
    	
    	if (myHours < 0 || myHours > 23) {
    		throw new IllegalArgumentException("Invalid value for Hours");
    	}
    	if (myMinutes < 0 || myMinutes > 59) {
    		throw new IllegalArgumentException("Invalid value for Minutes");
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
