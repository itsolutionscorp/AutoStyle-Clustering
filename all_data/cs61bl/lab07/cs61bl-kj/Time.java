public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {

    	int colonPos = s.indexOf (":");
    	myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));


        
//    	String before = s.substring(0, colonPos);
    	if (myHours != (int) myHours) {
    		throw new IllegalArgumentException("Not a valid hour");
    	}
    	
    	if (myMinutes != (int) myMinutes) {
    		throw new IllegalArgumentException("Not a valid minute");
    	}
    	
    	if (myHours > 12) {
    		throw new IllegalArgumentException("Hours out of range");
    	}

    	if (myMinutes > 59) {
    		throw new IllegalArgumentException("Minutes out of range");
    		
    	}
//    	if (myMinutes / 100 == 0 && myMinutes / 10 > 0) {
//    		throw new IllegalArgumentException("Too many zeroes in hour");
//    	}
//    	
    	if (s.substring (0, colonPos).length() > 2) {
    		throw new IllegalArgumentException("Too many zeroes in hour");
        	
    		
    	}
    	if ((s.substring(colonPos+1)).length() > 2) {
    		throw new IllegalArgumentException("Too many zeroes in minutes");
    		
    	}
    	
    	if ((s.substring(colonPos+1)).length() < 2) {
    		throw new IllegalArgumentException("Not enough digits in minutes");
    		
    	}
    	else {

        	
    		
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
