public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
        try {
        	int colonPos;
        	
           	colonPos = s.indexOf (":");
           	if (colonPos == -1) {
            	throw new IllegalArgumentException("No ':' in the String");
           	}
           	
        	myHours = Integer.parseInt (s.substring (0, colonPos));
        	myMinutes = Integer.parseInt (s.substring (colonPos+1));
        	
            if (myHours >= 24 || myHours < 0) {
            	throw new IllegalArgumentException("Hours should be [0 : 24)");
            }
            
            if (myMinutes >= 60 || myMinutes < 0) {
            	throw new IllegalArgumentException("Minutes should be [0 : 60)");
            }
        } catch(NumberFormatException e) {
        	throw new IllegalArgumentException("Number Format is wrong!");
        } catch(NullPointerException e) {
        	throw new IllegalArgumentException("Null!");
        }
    }
    
    public Time (int hours, int minutes) {
    	if (hours < 0 || hours >= 24) {
    		throw new IllegalArgumentException("hours should be [0 : 24)");
    	}
    	
    	if (minutes < 0 || minutes >= 60) {
    		throw new IllegalArgumentException("minutes should be [0 : 60)");
    	}
    	
        myHours = hours;
        myMinutes = minutes;
    }

    public boolean equals (Object obj) {
    	Time t = null;
    	
    	try {
    		t = (Time) obj;
    	} catch (NullPointerException e) {
    		throw new IllegalArgumentException("Null in equals method");
    	}
    	
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
