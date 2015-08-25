public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
            int colonPos = s.indexOf (":");
            if (colonPos == 0 || colonPos == (s.length()-1)) {
            	throw new IllegalArgumentException("Please fill in values for both hour and minute");
            }
            String myHours_string = s.substring(0, colonPos);
        	String myMinutes_string = s.substring(colonPos + 1);
        	myHours = Integer.parseInt (s.substring (0, colonPos));
            myMinutes = Integer.parseInt (s.substring (colonPos+1));
        	if (myHours_string.contains(" ") ||myMinutes_string.contains(" ")) {
        		throw new IllegalArgumentException ("There should be no blank spaces in time");
        	}
        	else if (myHours_string.length() > 2 || myMinutes_string.length() != 2) {
            	
            	 if ((myHours_string.contains("00") && myHours_string.length() != 2) || (myMinutes_string.contains("00") && myMinutes_string.length() != 2)) {
            		throw new IllegalArgumentException("Too many leading zeroes");
            	}
            	throw new IllegalArgumentException ("Incorrect number of digits in time entered"); // customized message strings here
            }
            
            
            
            if (myHours < 0 || myHours > 23 || myMinutes < 0 || myMinutes > 59) {
            	throw new IllegalArgumentException ("Time out of range");
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
