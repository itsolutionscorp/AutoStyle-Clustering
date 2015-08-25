

public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
        int colonPos = s.indexOf (":");
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        
        if (myHours < 0 || myHours > 23 || myMinutes < 0 || myMinutes > 59) {
        	throw new IllegalArgumentException("Minutes or hours are outside of valid range");
        } 
        else if (s == null) {
        	throw new IllegalArgumentException("Null value is invalid");
        }
        else if (colonPos<0) {
        	throw new IllegalArgumentException("Time is not formatted correctly");
        }
        else if (s.substring (0, colonPos).length()>2|| s.substring (colonPos+1).length()!=2){
        	throw new IllegalArgumentException("Hours or minutes have too many or too few digits");
        }
        else if (s.substring (0, colonPos) == ""|| s.substring (colonPos+1) == ""){
        	throw new IllegalArgumentException("Hours or minutes are empty");
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