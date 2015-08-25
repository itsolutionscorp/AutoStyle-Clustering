public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
        int colonPos = s.indexOf (":");
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        if (s.substring(colonPos + 1).length() != 2){
        	throw new IllegalArgumentException("Zeros after colon");
        }else if( myHours<13 && s.substring(0, colonPos).length() >2
        		){
        	throw new IllegalArgumentException("Zeros before colon");
        }else if (myHours > 23 || myMinutes > 59){
        	throw new IllegalArgumentException("Time input illegal");
        }
    }
    
    public Time (int hours, int minutes) {
        if (hours > 23 || minutes > 59){
        	throw new IllegalArgumentException("Time input illegal");
        }else{
    	    myHours = hours;
            myMinutes = minutes;
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
