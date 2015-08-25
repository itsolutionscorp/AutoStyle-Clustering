public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
        if (s == null){
        	throw new NullPointerException("got a null pointer");
        }
    	int colonPos = s.indexOf (":");
    	if (s.indexOf(":") == -1) {
    		throw new StringIndexOutOfBoundsException("String Index out of bounds. Possibly missing colon?");
    	}
        if (s.substring(0,colonPos).length() > 2){
        	throw new IllegalArgumentException("Too many digits in hours");
        }
        if (s.substring(0,colonPos).length() < 1){
        	throw new IllegalArgumentException("Not enough digits in hours");
        }
        if (s.substring(colonPos+1).length() != 2){
        	throw new IllegalArgumentException("Need two digits in minutes");
        }
       	myHours = Integer.parseInt (s.substring (0, colonPos));        
       	myMinutes = Integer.parseInt (s.substring (colonPos+1));
        if (myHours>23) {
        	throw new IllegalArgumentException("Hours entered too large");
        }
        if (myMinutes>59) {
        	throw new IllegalArgumentException("Minutes entered too large");
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
