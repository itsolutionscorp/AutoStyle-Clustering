public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null) {
    		throw new IllegalArgumentException ("String is a null");
    	}
    	if (s.length() > 5) {
    		throw new IllegalArgumentException("too many numbers");
    			
    	}
    	if (s.length() < 4) {
        		throw new IllegalArgumentException("not enough numbers");

    		
    	}
        int colonPos = s.indexOf (":");
        
     	String lasts = s.substring(s.lastIndexOf(' ') + 1);
        if (lasts.length() != 2) {
    		throw new IllegalArgumentException("too many numbers in digits");
        }
        try {
        	 myHours = Integer.parseInt (s.substring (0, colonPos));
        	 
        } catch (NumberFormatException e) {
        	throw new IllegalArgumentException ("Hour is not a number");
        	
        
        
        }
        try {
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        
        } catch (IndexOutOfBoundsException e) {
        	throw new IllegalArgumentException("No colon");
        }
   
        if (myHours > 23 || myHours < 0) {
        	throw new IllegalArgumentException ("Military hours are from 0 to 23");
        }
        if (myMinutes > 59 || myMinutes < 0) {
        	throw new IllegalArgumentException ("Minutes have to be from 0 to 59");
        }
       
    }
    
    public Time (int hours, int minutes) {
        myHours = hours;
       
        myMinutes = minutes;
        if (myHours > 23 || myHours < 0) {
        	throw new IllegalArgumentException ("Military hours are from 0 to 23");
        }
        if (myMinutes > 59 || myMinutes < 0) {
        	throw new IllegalArgumentException ("Minutes have to be from 0 to 23");
        }
        
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
