public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
        int colonPos = s.indexOf (":");
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        if (myHours < 0 || myMinutes < 0) {
        	throw new IllegalArgumentException("Hours and minutes must be positive numbers");
        }
        if (myHours >= 24 || myMinutes >= 60) {
        	throw new IllegalArgumentException("Hours or minutes out of range");
        }
        if (s.length() > 5 || s.length() <=3) {
        	throw new IllegalArgumentException("Illegal time format");
        }
        if (s.length() == 5) {
        	if (colonPos != 3) {
        		throw new IllegalArgumentException("Illegal time format");
        	}
        if (s.length() == 4) {
        	if (colonPos !=2) {
        		throw new IllegalArgumentException("Illegal time format");
        	}
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
