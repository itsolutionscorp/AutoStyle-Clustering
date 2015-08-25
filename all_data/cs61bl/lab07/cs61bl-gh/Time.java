public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null) {
    		throw new IllegalArgumentException("Argument must be a valid string.");
    	}
        int colonPos = s.indexOf (":");
        if (colonPos == -1) {
        	throw new IllegalArgumentException("Argument string must contain a colon.");
        }
        if (s.substring(0, colonPos) == "") {
        	throw new IllegalArgumentException("Need entry hour argument");
        } else if (s.substring(colonPos+1) == "") {
        	throw new IllegalArgumentException("Need entry minute argument");
        } else if (s.substring(0, colonPos).length()>2) {
        	throw new IllegalArgumentException("Too many digits in hours argument");
        } else if (s.substring(colonPos+1).length()!=2) {
        	throw new IllegalArgumentException("Invalid number of digits in minutes argument");
        } else {
        	try {
        		myHours = Integer.parseInt (s.substring (0, colonPos));
        		myMinutes = Integer.parseInt (s.substring (colonPos+1));
        	} catch (NumberFormatException e) {
        		throw new IllegalArgumentException("Invalid arguments for minutes or hours");
        	}
        } if (myHours<0 || myHours>23 || myMinutes<0 || myMinutes>59) {
        	throw new IllegalArgumentException("Hours or minutes out of range");
        }

    }
    
    public Time (int hours, int minutes) {
        myHours = hours;
        myMinutes += minutes;
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


