public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
        int colonPos = s.indexOf (":");
        if (s.substring (0, colonPos).length()>2){
        	throw new IllegalArgumentException("Hours can not be larger than 2 bytes");
        }
        if (s.substring (colonPos+1).length()!=2){
        	throw new IllegalArgumentException("Minutes can only be 2 bytes");
        }
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        if (myHours>23){
        	throw new IllegalArgumentException("Hours needs to be less than 23");
        }
        if (myMinutes>59){
        	throw new IllegalArgumentException("Minutes needs to be less than 59");
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
