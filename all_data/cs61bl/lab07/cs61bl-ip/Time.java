public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	
    	if (s == null) {
        	throw new NullPointerException("No input");
        }
    	
        int colonPos = s.indexOf (":");
        
        if (colonPos == -1) {
        	throw new IllegalArgumentException("No colon");
        }
        
        String hr = s.substring(0, colonPos);
        String min = s.substring(colonPos+1);
        
        if ((hr.length() != 1 && hr.length() != 2) || min.length() != 2) {
        	throw new IllegalArgumentException("Incorrect Format");
        }
        
        String test1 = s.substring(0, 1);
        String test2 = s.substring(s.length()-1);
        
        if (test1.equals(":") || test2.equals(":")) {
        	throw new IllegalArgumentException("You need minutes and hours");
        }

        String test5 = s.substring(colonPos + 1, colonPos + 2);
        
        if (test1.equals("0") || test5.equals("0")) {
        	throw new IllegalArgumentException("Leading 0s");
        }

        String test3 = s.substring(colonPos - 1, colonPos);
        String test4 = s.substring(colonPos, colonPos + 1);

        if (test1.equals(" ") || test2.equals(" ")
    		|| test3.equals(" ") || test4.equals(" ")) {
        	throw new IllegalArgumentException("Extra spaces");
    	}
        
        
        
        try {
            myHours = Integer.parseInt (s.substring (0, colonPos));
            myMinutes = Integer.parseInt (s.substring (colonPos+1));
        } catch (NumberFormatException e) {
        	System.err.println("Not actual numbers");
        	throw e;
        }
        
        if (myHours < 0 || myHours > 23 || myMinutes < 0 
        		|| myMinutes > 59 ) 
        	throw new IllegalArgumentException("Hours and Minutes out of time");
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
