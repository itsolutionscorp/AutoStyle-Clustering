public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	
    	if (s == null) {   //null
    		throw new IllegalArgumentException("Argument cannot be null!");
    	}
    	
    	boolean atLeastOneLetter = s.matches(".*[a-zA-Z]+.*");
    	//from http://stackoverflow.com/questions/14278170/how-to-check-whether-a-string-contains-at-least-one-alphabet-in-java
    	
    	if (atLeastOneLetter) { //"x", "x:y", ":x", "x:"
    		throw new IllegalArgumentException("Argument cannot contain any letters!");
    	}
    	
    	int colonPos = s.indexOf(":");
    	
    	/*if (colonPos == -1) { 
    		throw new IllegalArgumentException("Argument must contain a colon!");
    	}*/
    	if (colonPos == 0 || colonPos == s.length()-1) {   // ":30", "1:"
    		throw new IllegalArgumentException("Colon cannot be first or last character of argument!");
    	}
    
    	if (s.contains(" ")) { //"4: 35", "4 :09", " 3:30"
    		throw new IllegalArgumentException("Argument cannot contain any spaces!");
    	}
    	
    	if (s.substring(0, colonPos).length() > 2) { //"00004:45",
    		throw new IllegalArgumentException("Hour value must be represented with at most two digits!");
		}
    	
    	if (s.substring(colonPos+1).length() != 2) { // "4:007", "4:7"
    		throw new IllegalArgumentException("Minute value must be represented with exactly two digits!");
    	}
    
    	myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        
        if (myHours < 0 || myHours > 23) {   //"55:00"
        	throw new IllegalArgumentException("Hour value must be between 0 and 23!");
        }
        
        if (myMinutes < 0 || myMinutes>59 ) { //"11:99"
        	throw new IllegalArgumentException("Minute value must be between 0 and 59!");
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
