public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) throws IllegalArgumentException{
    	if (s == null) {
    		throw new NullPointerException("no argument given");
    	}
    	if (s.indexOf(":") == -1) {
    		throw new IllegalArgumentException("argument must have colon :");
    	}
        int colonPos = s.indexOf (":");
        if (s.indexOf(" ") != -1) {
        	throw new IllegalArgumentException("argument must have no spaces");
        }
        
        
        if (s.substring(0, colonPos).equals("")) {
        	throw new IllegalArgumentException("no hours given");
        }
        String substringHour = s.substring(0, colonPos);
        myHours = Integer.parseInt (s.substring (0, colonPos));
        if (substringHour.length() > 3 || (myHours < 10 && !substringHour.substring(0).equals("" + myHours))) {
        	throw new IllegalArgumentException("too many zeroes in hour argument");
        }
        if (myHours < 0 || myHours > 23) {
        	throw new IllegalArgumentException("hours out of range");
        }
        
        
        if (s.substring(colonPos+1).equals("")) {
        	throw new IllegalArgumentException("no minutes given");
        }
        String substringMin = s.substring(colonPos+1);
        myMinutes = Integer.parseInt (substringMin);
        if (substringMin.length() > 2 || (myMinutes < 10 && !substringMin.substring(0).equals("0"))) {
        	throw new IllegalArgumentException("too many zeroes in minute argument");
        }
        if (myMinutes < 0 || myMinutes > 59) {
        	throw new IllegalArgumentException("minutes out of range");
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
