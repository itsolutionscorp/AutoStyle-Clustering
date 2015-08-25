public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null) throw new IllegalArgumentException("Passing a null argument.");
        int colonPos = s.indexOf (":");
        if (colonPos == -1) throw new IllegalArgumentException("No colon detected.");
    	if (colonPos > 2) throw new IllegalArgumentException("Too many leading zeros in minutes.");
    	else if (s.length() > 5) throw new IllegalArgumentException("Too many leading zeros in hours.");
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        if (myHours > 23) throw new IllegalArgumentException("Only 24 hours in a day.");
        if (myHours < 0) throw new IllegalArgumentException("Cannot have negative number of hours without infinite energy.");
        if (myMinutes > 59) throw new IllegalArgumentException("Only 60 minutes in an hour");
        if (myMinutes < 0) throw new IllegalArgumentException("Trying to rupture the fabric of spacetime.");
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
