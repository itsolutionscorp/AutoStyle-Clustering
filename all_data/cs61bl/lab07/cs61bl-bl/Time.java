public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
        int colonPos = s.indexOf (":");
        if (s.length() > 5 && s.length() < 4) {
            throw new IllegalArgumentException("Invalid time input.");
        }
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
    }
    
    public Time (int hours, int minutes) {
        if (hours < 24 && minutes < 60) {
            myHours = hours;
            myMinutes = minutes;
        } else {
            throw new IllegalArgumentException("Hours and/or minutes are out of bounds.");
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
