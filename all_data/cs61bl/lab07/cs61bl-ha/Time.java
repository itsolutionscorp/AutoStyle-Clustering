public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	try {
    		int colonPos = s.indexOf (":");
    		myHours = Integer.parseInt (s.substring (0, colonPos));
    		myMinutes = Integer.parseInt((s.substring (colonPos+1)));
    		if (myHours > 23 || myHours < 0 || myMinutes > 59 || myMinutes < 0){
    			throw new IllegalArgumentException("time is not valid");
    		}
    		if (colonPos > 2 || s.length() - colonPos > 1) {
    			throw new IllegalArgumentException("too many zeroes");
    		}
    	} catch(NullPointerException e) {
    		throw new IllegalArgumentException(e.getMessage());
    	
    	} catch(StringIndexOutOfBoundsException e) {
		throw new IllegalArgumentException(e.getMessage());
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
