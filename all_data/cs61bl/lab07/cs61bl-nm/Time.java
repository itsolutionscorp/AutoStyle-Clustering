public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null) {
    		throw new IllegalArgumentException("Null pointer given.");
    	}
        int colonPos = s.indexOf (":");
        String k = s.substring(0, colonPos);
        if (k.length() < 0) {
        	throw new IllegalArgumentException("Hours given is invalid");
        } else if (k.length() > 2) {
        	for (int i = 0; i < k.length() - 1; i++) {
        		if (k.charAt(i) == ' ') {
        			throw new IllegalArgumentException("Please don't enter spaces.");
        		} else if (k.charAt(i) == '0') {
        			throw new IllegalArgumentException("Remove all unnecesary 0's");
        		}
        	}
        } else if (k.contains("[a-zA-Z]+") == true) {
        	throw new IllegalArgumentException("Please remove characters that aren't integers.");
        }
        String p = s.substring(colonPos+1);
        if (p.length() < 0) {
        	throw new IllegalArgumentException("Minutes given is invalid");
        } else if (p.length() > 2) {
        	for (int i = 0; i < p.length() - 1; i++) {
        		if (p.charAt(i) == ' ') {
        			throw new IllegalArgumentException("Please don't enter spaces.");
        		} else if (p.charAt(i) == '0') {
        			throw new IllegalArgumentException("Remove all unnecesary 0's");
        		}
        	}
        } else if (p.contains("[a-zA-Z]+") == true) {
        	throw new IllegalArgumentException("Please remove characters that aren't integers.");
        }
        myHours = Integer.parseInt (s.substring (0, colonPos));
        if (myHours > 23 || myHours < 0) {
        	throw new IllegalArgumentException("Hours given is out of range.");
        }
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        if (myMinutes > 59 || myMinutes < 0) {
        	throw new IllegalArgumentException("Minutes given is out of range.");
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
