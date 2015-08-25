public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
        if (s == null) {
        	throw new IllegalArgumentException("The supplied string is null");
        }
    	if (s.indexOf(":") == -1) {
        	throw new IllegalArgumentException("There is no ':'");
        }
        if (s.contains(" ")) {
        	throw new IllegalArgumentException("The input string contains spaces");
        }
        int colonPos = s.indexOf (":");
    	if (s.substring(0, colonPos).length() > 2) {
    		throw new IllegalArgumentException("The number of hours must be two digits or less.");
    	}
    	if (s.substring(colonPos + 1).length() != 2) {
    		throw new IllegalArgumentException("The number of minutes must be two digits");
    	}
        try {
    	myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        }
        catch (NumberFormatException e) {
        	throw new IllegalArgumentException("The input string contains one or more letters.");
        }
        if (myHours > 23) {
        	throw new IllegalArgumentException("The number of hours given, " + myHours + " is greater than 23.");
        }
        if (myMinutes > 59) {
        	throw new IllegalArgumentException("The number of minutes given, " + myMinutes + " is greater than 59.");
        } else {
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
