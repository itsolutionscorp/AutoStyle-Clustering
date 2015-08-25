import java.util.regex.Pattern;

public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if(s == null){
        	throw new IllegalArgumentException("Time cannot be null.");
        }
    	else if(s.contains(":") == false){
        	throw new IllegalArgumentException("Time must contain a colon.");
        }
        
        int colonPos = s.indexOf (":");
        if(((s.substring (0, colonPos)).contains(" ")) || (s.substring (colonPos+1)).contains(" ")){
        	throw new IllegalArgumentException("There can't be any spaces in time representation.");
        }
        else if(!Pattern.matches("[0-9]+", (s.substring (0, colonPos))) || !Pattern.matches("[0-9]+", s.substring(colonPos+1))){
        	throw new IllegalArgumentException("Time must be expressed in numbers.");
        }
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        if(myHours > 23 || myHours < 0){
        	throw new IllegalArgumentException("Hours must be between 0 and 23.");
        }
        else if(myMinutes < 0 || myMinutes > 59){
        	throw new IllegalArgumentException("Minutes must be between 0 and 59");
        }
        else if((s.substring (0, colonPos)).length() > 2){
        	throw new IllegalArgumentException("Hours cannot have more than 2 digits.");
        }
        else if((s.substring (colonPos+1)).length() != 2){
        	throw new IllegalArgumentException("Minutes must have exactly 2 digits.");
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
