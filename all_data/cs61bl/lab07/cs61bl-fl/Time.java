public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	try{
	    	int colonPos = s.indexOf (":");
	        myHours = Integer.parseInt (s.substring (0, colonPos));
	        myMinutes = Integer.parseInt (s.substring (colonPos+1));
    	} catch (NullPointerException e) {
    		throw new IllegalArgumentException("cannot be null");
    	} catch (NumberFormatException e) {
    		throw new IllegalArgumentException("time values must be numbers");
    	} catch (Exception e){
    		System.out.println("0");
	        if (s.length()<4 || s.length() > 5){
	        	throw new IllegalArgumentException("time must have valid integer hours and minutes");
	        } else if (myMinutes<0 || myMinutes>59) {
	        	throw new IllegalArgumentException("Minutes must be between 0 and 59");
	        } else if (myHours<0 || myHours>23) {
	        	throw new AssertionError("Minutes must be between 0 and 59");
	        } else if (s.contains(" ")){
	        	throw new IllegalArgumentException("Time cannot have a space");
	        } else if (s.contains("00") && myMinutes != 00) {
	        	throw new IllegalArgumentException("Too many zeros");
	        } else if (s.equals(null)) {
	        	throw new IllegalArgumentException("Time cannot be a null value");
	        } else if (! s.contains(":")){
	        	throw new IllegalArgumentException("Time must contain a ':'");
	        }
	        
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
