public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null)
    		throw new NullPointerException("No input");
    	if (! s.contains(":"))
    		throw new IllegalArgumentException("No colon detected");
        int colonPos = s.indexOf (":");
        if (s.substring(0, colonPos).length() > 2 || s.substring(0, colonPos).length() == 0 || s.substring(0, colonPos).contains(" "))
        	throw new IllegalArgumentException("Incorrect format for hours");
        if (s.substring(colonPos+1).length() != 2)
    		throw new IllegalArgumentException("Incorrect format for minutes");
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        if (myHours < 0 || myHours > 23)
        	throw new IllegalArgumentException("Hours out of range");
        if (myMinutes < 0 || myMinutes > 59)
        	throw new IllegalArgumentException("Minutes out of range");
        
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
