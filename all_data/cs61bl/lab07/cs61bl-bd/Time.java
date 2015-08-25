public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	
    	for (int i = 0; i < s.length(); i++) {
    		if (s.charAt(i) == ' ') {
    			throw new NumberFormatException("argument should not contain space");
    		}
    	}
    	
    	if (s.length() != 5) {
         	throw new IllegalArgumentException();
        }
    	
    	int colonPos = s.indexOf (":");
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
                
        if (myHours > 23 || myHours < 0 || myMinutes > 59 || myMinutes < 0 || String.valueOf(myHours).length() != 2 || String.valueOf(myMinutes).length() != 2) {
        	throw new IllegalArgumentException("hours or minutes out of bounds");
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
