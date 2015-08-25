public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (!s.contains(":")) {
    		throw new IllegalArgumentException("Time must contain a \":\"");
    	}
        int colonPos = s.indexOf (":");
        String left = s.substring(0, colonPos); 
        String right = s.substring(colonPos+1); 
        if (left.length() > 2) {
        	throw new IllegalArgumentException("Inputs to the left of the colon must be one or two numbers."); 
        }
        if (right.length() != 2) {
        	throw new IllegalArgumentException("Inputs to the right of the colon must be two numbers"); 
        }
        try { 
        myHours = Integer.parseInt (left);
        myMinutes = Integer.parseInt (right);
        } catch (NumberFormatException e) {
        	throw new IllegalArgumentException("Entered time should only contain numbers."); 
        }
        if (myHours < 0 || myMinutes < 0 || myHours > 23 || myMinutes > 59) {
        	throw new IllegalArgumentException("Hours must be between 0 and 23 while minutes must be between 0 and 59"); 
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
    public static void main(String[] args) {
    	//int x = Integer.parseInt(" ");
    }
}
