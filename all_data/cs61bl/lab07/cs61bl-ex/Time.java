public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) throws NullPointerException, NumberFormatException, AssertionError{
    	if (s == null){
    		throw new NullPointerException("Please put something.");
    	}
    	int colonPos = s.indexOf (":");
    	try{
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        if (s.substring(colonPos+1).length() > 2 || s.substring(colonPos+1).length() < 2 || s.substring(0, colonPos).length() > 2)
        	throw new AssertionError("Incorrect time format");
        if (myHours > 12 || myMinutes > 60 || myHours < 0 || myMinutes < 0)
        	throw new IllegalArgumentException ("invalid time");
    	}
    	catch (StringIndexOutOfBoundsException e){
    		throw new StringIndexOutOfBoundsException("String too small");
    	}
    	catch (NumberFormatException e){
    		throw new NumberFormatException("Incorrect time format");
    	}
    }
    
    public Time (int hours, int minutes){
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
