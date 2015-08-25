public class Time {

    private int myHours;
    private int myMinutes;    
    public Time (String s) {  
    	
    	try { 
        	int colonPos = s.indexOf (":");
        	myHours = Integer.parseInt (s.substring (0, colonPos));
        	myMinutes = Integer.parseInt (s.substring (colonPos+1));
        	if (s.substring (colonPos+1).length() > 2){
        		throw new IllegalArgumentException("Too many digits in minutes");
        	}
        	if (s.substring (0, colonPos).length() > 2){
        		throw new IllegalArgumentException("Too many digits in hours");
        	}
        	if (myHours > 23) {
        		throw new IllegalArgumentException("Hours must be in range");
        	}
        	if (myMinutes > 60) {
        		throw new IllegalArgumentException("Minutes must be in range");
        	}
        	if ((s.substring (0, 2)).equals("00")){
        		throw new IllegalArgumentException("too many 0's in the front of hour");
        	}
        	if ((s.substring (colonPos+1, colonPos+3)).equals("00") && s.substring (colonPos+1).length() > 2){
        		throw new IllegalArgumentException("too many 0's in the front of minutes");
        	}
	    } catch (NullPointerException e){
	    	throw new IllegalArgumentException("NullPointerException");
	    } catch (StringIndexOutOfBoundsException e){
	    	throw new IllegalArgumentException("StringIndexOutOfBoundsException");
	    } catch (NumberFormatException e){
	    	throw new IllegalArgumentException("NumberFormatException");
	    } catch (AssertionError e){
	    	throw new IllegalArgumentException("AssertionError");
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
