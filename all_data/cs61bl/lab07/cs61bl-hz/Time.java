public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null) {
    		throw new NullPointerException("null pointer");
    	} else if (s.indexOf(':') == -1) {
    		throw new NullPointerException("no colon");
    	} else if (s.charAt(0) == ' ') {
    		throw new IllegalArgumentException("space before hours input");
    	} else if (s.charAt(0) == '0') {
    		throw new IllegalArgumentException("zero before hour");
    	} else if (s.length() < 4) {
        	throw new IllegalArgumentException ("too few characters");
    	}
        int colonPos = s.indexOf (":");
        
        if (isParsable(s.substring (0, colonPos)) == false || isParsable(s.substring (colonPos+1)) == false ) {
    		throw new IllegalArgumentException("Non-numerical input");
    	}
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        if (myHours < 0 || myHours > 23) {
        	throw new IllegalArgumentException ("Hours out of range");
        } else if (myMinutes < 0 || myMinutes > 59) {
        	throw new IllegalArgumentException ("Minutes out of range");
        } else if (s.charAt(colonPos+1) == ' ') {
        	throw new IllegalArgumentException ("space after colon");
        } else if (s.charAt(colonPos-1) == ' ') {
        	throw new IllegalArgumentException ("space before colon");
        } else if (s.charAt(s.length() - 1) != s.charAt(colonPos + 2)) {
        	throw new IllegalArgumentException ("too many digits on minutes");
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
    
    public static boolean isParsable(String input){ //from stackexchange
        boolean parsable = true;
        try{
            Integer.parseInt(input);
        }catch(NumberFormatException e){
            parsable = false;
        }
        return parsable;
    }
}
