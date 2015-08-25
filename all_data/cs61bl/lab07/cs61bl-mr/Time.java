public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
        if (s ==null) {
        	throw new IllegalArgumentException("You must pass in an String as an argument!");
        }
    	if (!s.contains(":")) {
      		throw new IllegalArgumentException("You need a colon!");
        }
        if (s.contains(" ")) {
        	throw new IllegalArgumentException("You do not need a blank space!");
        }
        int colonPos = s.indexOf (":");
        String beforeCol = s.substring(0, colonPos);
        String afterCol = s.substring(colonPos+1);
        if (beforeCol.equals("")) {
        	throw new IllegalArgumentException("There must be an hour time!");
        }
        if (beforeCol.charAt(0)== '0') {
        	throw new IllegalArgumentException("The hour cannot start with a 0.");
        }
        if (afterCol.length()!=2) {
        	throw new IllegalArgumentException("The minute should have two digits.");
        }
        try {
        	myHours = Integer.parseInt (s.substring (0, colonPos));
        	myMinutes = Integer.parseInt (s.substring (colonPos+1));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Hour and minutes should be written as integers.");
        };
        
        if (myHours>=24) {
        	throw new IllegalArgumentException("The hour cannot be bigger than 24.");
        }
        if (myHours<0) {
        	throw new IllegalArgumentException("The hour must be greater than 0.");
        }
        if (myMinutes>=60) {
        	throw new IllegalArgumentException("The minute must be within 60.");
        }
        if (myMinutes<0) {
        	throw new IllegalArgumentException("The minute must be greater than 0.");
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
