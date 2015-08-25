public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
        int colonPos = s.indexOf (":");
        if (colonPos == -1) {
        	// no colon found: you're so mean
        	throw new IllegalArgumentException("no colon found: you're so mean"); 
        }
        String h = s.substring (0, colonPos);
        String m = s.substring (colonPos+1);
        int myH = Integer.parseInt (h); // gives the substring [beginIndex, endIndex) 
        int myM = Integer.parseInt (m);
        if (myH < 0 || myH > 24	|| myM < 0 || myM > 60) {
        	// you're out of range 
        	throw new IllegalArgumentException("you're out of range"); 
        }
        if (h.length() > 2 || m.length() > 2) {
        	// you're definitely wrong: too long (too many leading 0s maybe)
        	throw new IllegalArgumentException("you're definitely wrong: too long (too many leading 0s maybe)");
        }
        if (h.length() == 2 && myH < 10) {
        	// hour's length looks fine but you are "04"-like
        	throw new IllegalArgumentException("your length looks fine but you are \"04\"-like");
        }
        if (m.length() != 2) {
        	// minute's length must be 2
        	throw new IllegalArgumentException("minute's length must be 2");
        }
        this.myHours = myH;
        this.myMinutes = myM;
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
