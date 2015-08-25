public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	try{
            int colonPos = s.indexOf (":");
            myHours = Integer.parseInt (s.substring (0, colonPos));
            myMinutes = Integer.parseInt (s.substring (colonPos+1));
            if ((myHours > 23 || myHours < 0) || (myMinutes > 59 || myMinutes < 0)){
        	    throw new IllegalArgumentException ("Values for hours and minutes that are out of range.");
            }
            if (s.substring(0,colonPos).length() > 2 || s.substring(colonPos+1).length() > 2){
        	    throw new IllegalArgumentException ("Too many leading zeroes in the hours or minutes");
            }
            if (s.substring(colonPos+1).length()!=2){
        	    throw new IllegalArgumentException("Minutes should be represented by two digits");
            }
            }catch (NullPointerException e){
    		    throw new IllegalArgumentException ("Null");
    	    }catch (StringIndexOutOfBoundsException e){
    		    throw new IllegalArgumentException ("There is no colon");
    	    }catch (NumberFormatException e){
    		    throw new IllegalArgumentException ("Wrong number format");
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
