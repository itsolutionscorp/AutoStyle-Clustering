public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	
    	int colPos = s.indexOf(":");
    	if (colPos == -1) {
    		throw new IllegalArgumentException("no colon found");
    	}
    	if (s.substring(0, colPos).length() > 2) {
    		throw new IllegalArgumentException("invalid format");
    	}
    	if (s.substring(colPos+1).length() != 2) {
    		throw new IllegalArgumentException("invalid format");
    	}
    	int hours = Integer.parseInt(s.substring(0, colPos));
    	int minutes = Integer.parseInt(s.substring(colPos+1));
    	if (hours < 0 || hours > 23) {
    		throw new IllegalArgumentException("invalid hours");
    	} 
    	if (minutes < 0 || minutes > 59) {
    		throw new IllegalArgumentException("invalid minutes");
    	}
        myHours = hours;
        myMinutes = minutes;

    }
    
    public Time (int hours, int minutes) {
    	if (hours < 0 || hours > 23) {
    		throw new IllegalArgumentException("invalid hours");
    	} 
    	if (minutes < 0 || minutes > 59) {
    		throw new IllegalArgumentException("invalid minutes");
    	}
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
