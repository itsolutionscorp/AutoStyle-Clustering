public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	try {
    		s.indexOf (":");
    	} catch (NullPointerException e){
    		throw new IllegalArgumentException("string cant be empty");
    	}
    	int colonPos = s.indexOf (":");
        if (colonPos == -1) {
        	throw new IllegalArgumentException("time needs a colon");
        }
        if (s.contains(" ")) {
        	throw new IllegalArgumentException("time has a blank space");
        }
        try {
        	myHours = Integer.parseInt (s.substring (0, colonPos));
        } catch(NumberFormatException e){
        	throw new IllegalArgumentException("hours has nonnumerical characters or none at all");
        }
        try {
        	myMinutes = Integer.parseInt (s.substring (colonPos+1));
        } catch(NumberFormatException e){
        	throw new IllegalArgumentException("minutes has nonnumerical characters or none at all");
        }
        if (s.substring (0, colonPos).length()>2 || s.substring (0, colonPos).length()==0){
        	throw new IllegalArgumentException("hours has too many digits or too few");
        }
        if (s.substring (colonPos+1).length()>2 || s.substring (colonPos+1).length()<=1){
        	throw new IllegalArgumentException("minutes has too many digits or too few");
        }
        if (myHours>23 || myHours < 0) {
        	throw new IllegalArgumentException("number of hours not between 0 and 23");
        }
        if (myMinutes>59 || myMinutes < 0) {
        	throw new IllegalArgumentException("number of minutes not between 0 and 59");
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
