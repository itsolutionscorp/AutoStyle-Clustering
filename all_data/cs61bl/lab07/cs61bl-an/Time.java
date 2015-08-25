public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null) {
        	throw new IllegalArgumentException("Please enter a valid String!");
    	} else if (s.indexOf(":") == -1){
        	throw new IllegalArgumentException("Enter time in the manner of XX:YY");
    	}
        int colonPos = s.indexOf (":");
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        if (myHours > 12 || myHours < 1) {
        	throw new IllegalArgumentException("Hour range should be 1-12");
        } else if (myMinutes>60) {
        	throw new IllegalArgumentException("Minute range should be 0-59");
        }  else if (s.substring(0, colonPos).length() >2){
        	throw new IllegalArgumentException("Please give a proper hour time");
        } else if (s.substring (colonPos+1).length() != 2 ){
        	throw new IllegalArgumentException("Enter minutes in double digits");

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
