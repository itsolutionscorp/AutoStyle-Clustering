public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null)
    		throw new IllegalArgumentException("null input");
    	int colonPos = s.indexOf(":");
    	if (colonPos == -1)
    		throw new IllegalArgumentException("input does not contain ':'");
    	else if(!(s.contains("0") || s.contains("1") || s.contains("2")
    			|| s.contains("3") || s.contains("4") || s.contains("5")
    			 || s.contains("6") || s.contains("7") || s.contains("8")
    			 || s.contains("9")))
    		throw new IllegalArgumentException("s does not contain any numbers");
    	try {
    		if (s.substring(0,colonPos).length() == 0)
        		throw new IllegalArgumentException("Missing hour");
        	if (s.substring(colonPos+1).length() < 2)
        		throw new IllegalArgumentException("Missing digit(s) of minutes.");
    		myHours = Integer.parseInt (s.substring (0, colonPos));
            myMinutes = Integer.parseInt (s.substring (colonPos+1));
    	} catch(NumberFormatException e) {
    		throw new IllegalArgumentException("Unnecessary space or non-number used.");
    	}
    	if (myHours>12)
    		throw new IllegalArgumentException("Hour is too big.");
    	if (myHours<0)
    		throw new IllegalArgumentException("Hour is too small." );
    	if (myMinutes>=60)
    		throw new IllegalArgumentException("Minute is too big.");
    	if (myMinutes<0)
    		throw new IllegalArgumentException("Minute is too small.");
    	if (s.substring(0,colonPos).length() > 2)
    		throw new IllegalArgumentException("Trailing zeros in hour");
    	if (s.substring(colonPos+1).length() > 2)
    		throw new IllegalArgumentException("Trailing zeros in minutes");
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
