public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	//throw new IllegalArgumentException("There is a letter in your input");
        //throw new IllegalArgumentException("There is a space in your input");
        int colonPos = s.indexOf (":");
        if(colonPos == -1) {
        	throw new IllegalArgumentException("You forgot a colon");
        }
        else if(colonPos == 0) {
        	throw new IllegalArgumentException("You need something before the colon");
        }
        else if(colonPos == s.length() - 1) {
        	throw new IllegalArgumentException("You need something after the colon");
        }
        char[] temp = new char[s.length()];
        s.getChars(0, s.length(), temp, 0);
        for (char c : temp) {
        	if ((int)c > 64) {
        		throw new IllegalArgumentException("You done goofed. No characters allowed");
        	}
        	else if((int)c == 32) {
            	throw new IllegalArgumentException("You stupid. Don't put a space");
            }
        }
        
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        
        if (myHours > 23) {
        	throw new IllegalArgumentException("Hours are too big! Must be less than 24");
        }
        
        if (myMinutes > 59) {
        	throw new IllegalArgumentException("Minutes are too big! Must be less than 60");
        }
        
        if (s.substring(0,colonPos).length()>2){
        	throw new IllegalArgumentException("There are too many digits in your hour slot");
        }
        
        if (s.substring(colonPos,s.length()-1).length()>2) {
        	throw new IllegalArgumentException("There are too many digits in your minut slot");
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
