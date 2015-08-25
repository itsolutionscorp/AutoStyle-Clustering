public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	try {
    		int colonPos = s.indexOf (":");
            myHours = Integer.parseInt (s.substring (0, colonPos));
            myMinutes = Integer.parseInt (s.substring (colonPos+1));
            String myHoursString = s.substring(0, colonPos);
            if (myHoursString.startsWith("00") && myHoursString.length() > 2) {
            	throw new IllegalArgumentException("Too many leading zeros in hours");
            }
            if (myHoursString.startsWith("0") && myHours > 9) {
            	throw new IllegalArgumentException("Too many leading zeros in hours");
            }
            String myMinutesString = s.substring(colonPos + 1);
            if (myMinutesString.startsWith("00") && myMinutesString.length() > 2) {
            	throw new IllegalArgumentException("Too many leading zeros in minutes");
            }
            if (myMinutesString.startsWith("0") && myMinutes > 9) {
            	throw new IllegalArgumentException("Too many leading zeros in minutes");
            }
            if (myHours > 23 || myHours < 0) {
            	throw new IllegalArgumentException("Hours have to be between 0 and 23 inclusive");
            }
            if (myMinutes > 59 || myMinutes < 0) {
            	throw new IllegalArgumentException("Minutes have to be between 0 and 59 inclusive");
            }
            if (myMinutesString.length() != 2) {
            	throw new IllegalArgumentException("Minutes must have two digits");
            }
    	} catch (NullPointerException e) {
    		throw new IllegalArgumentException("Time is null object");
    	} catch (StringIndexOutOfBoundsException e) {
    		throw new IllegalArgumentException("Time does not contain a colon");
    	} catch (NumberFormatException e) {
    		throw new IllegalArgumentException("Time does not contain numbers or contains a blank for hours or minutes");
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
