public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s.length() < 4) {
    		throw new IllegalArgumentException("The string must be a minimum of 4 characters");
    	}
    	if (s.length() > 5) {
    		throw new IllegalArgumentException("This string is longer than 5 characters");
    	}
    	if (s.contains(" ")) {
    		throw new IllegalArgumentException("This string contains white space");
    	}
    	if (s.contains("00")) {
    		throw new IllegalArgumentException("This string contains too many 0's");
    	}
    	if (s.contains(":") != true) {
    		throw new IllegalArgumentException("There is no colon in the string");
    	}
        int colonPos = s.indexOf (":");
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        if (myHours > 12) {
    		throw new IllegalArgumentException("Hours are too large");
        }
        if (myMinutes > 59) {
    		throw new IllegalArgumentException("Minutes are greater than 59");
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
    public static void main(String[] args) {
    	Time t = new Time("3:30");
    	System.out.println(t);
    }
}
