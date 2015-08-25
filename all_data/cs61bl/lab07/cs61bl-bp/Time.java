public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) throws IllegalArgumentException {
        int colonPos = s.indexOf (":");
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        if(myHours > 23 || myHours < 0 || myMinutes < 0 || myMinutes > 59) {
        	throw new IllegalArgumentException("myHours or myMinutes out of bounds");
        }
        if(colonPos > 2) {
        	throw new IllegalArgumentException("Too many leading 0s in hours.");
        }
        if(s.length() - colonPos > 3) {
        	throw new IllegalArgumentException("Too many leading 0s in minutes");
        }
        if (s.substring (colonPos+1).length() != 2) {
        	throw new IllegalArgumentException("Improper minutes format");
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
