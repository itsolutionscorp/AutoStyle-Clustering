public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	for (int i = 0; i < s.length(); i ++) {
			if (s.charAt(i) == ' ') {
				throw new IllegalArgumentException("No spaces in time");
			}
    	}
        int colonPos = s.indexOf (":");
        if (colonPos == -1) {
        	throw new IllegalArgumentException("There is no colon");
        }
        String h = s.substring (0, colonPos);
        String m = s.substring (colonPos+1);
        
        
        try {
        if (Character.isLetter(h.charAt(0))) {
        	
        	throw new IllegalArgumentException("Alphabetic letter is not a valid time representation");}        
        } catch (IndexOutOfBoundsException e){
        }
        try {
            if (Character.isLetter(m.charAt(0))) {
            	
            	throw new IllegalArgumentException("Alphabetic letter is not a valid time representation");}        
            } catch (IndexOutOfBoundsException e){
            }
        

        try {
	        myHours = Integer.parseInt(s.substring (0, colonPos));
	        myMinutes = Integer.parseInt (s.substring (colonPos+1));
	        if (h.length() == 0) {
	        	throw new IllegalArgumentException("No hours");
	        }
	        if (m.length() == 0) {
	        	throw new IllegalArgumentException("No minutes");
	        }
	        
        } catch (NumberFormatException e) {
        	throw new IllegalArgumentException("No hours or minute values; invalid time");
        }


        if (h.length() > 2) {
        	throw new IllegalArgumentException("Too many digits in hour");
        }
        if (m.length() > 2) {
        	throw new IllegalArgumentException("Too many digits in minutes");
        }
        if (myHours > 23 || myHours < 0) {
        	throw new IllegalArgumentException("Hours not between 0 and 23");
        }
        if (myMinutes > 59 || myMinutes < 0) {
        	throw new IllegalArgumentException("Minutes not between 0 and 59");
        }
        
    
    	if (s.length()  < 4) {
    		throw new IllegalArgumentException("Not enough digits in time. ");
    	}
    	else if (s.length() > 5) {
    		throw new IllegalArgumentException("Too many integers in time");
    	}
    }
    
    		
    	
    	
    

    
    public Time (int hours, int minutes) {
    	try {
        myHours = hours;
        myMinutes = minutes;
    	} catch (IllegalArgumentException e) {
    		System.out.println("Argument is not an integer");
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
