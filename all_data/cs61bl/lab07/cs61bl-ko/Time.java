public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
        int colonPos = s.indexOf (":");
        try {
        if (s.substring(0, colonPos).length() > 2) {
        	throw new IllegalArgumentException ("Hours can only have a maximum of one leading zero");
        }
        if (s.substring(colonPos + 1).charAt(0) == ' ') {
        	throw new IllegalArgumentException ("Must have minutes input without empty spaces");
        }
        if (s.substring(colonPos + 1).length() != 2) {
        	throw new IllegalArgumentException ("Minutes must have two digits");
        }
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        if (myHours < 0 || myHours > 23) {
        	throw new IllegalArgumentException ("Hours must be between 0 and 23");
        }
        if (myMinutes < 0 || myMinutes > 59 ) {
        	throw new IllegalArgumentException ("Minutes must be between 0 and 59");
        } }
        catch (NullPointerException e) {
        	System.out.println("null");
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
