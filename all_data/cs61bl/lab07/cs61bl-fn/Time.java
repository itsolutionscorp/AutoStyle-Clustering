public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	try {
    	int colonPos = s.indexOf (":");
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));    		
    	} catch (NullPointerException ex) {
    		throw new IllegalArgumentException("null argument");
    	} catch (StringIndexOutOfBoundsException ex) {
    		throw new IllegalArgumentException("lack of minute or hour");
    	} catch (NumberFormatException ex) {
    		throw new IllegalArgumentException("wrong format");
    	}
    	if (s.indexOf (":")>2 || s.length()-s.indexOf (":")>1) {
    		throw new IllegalArgumentException("not correct length for time");
    	} else if (myHours>23 ||myHours<0 || myMinutes>59 || myMinutes<0) {
    		throw new IllegalArgumentException("incorrect time");
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
