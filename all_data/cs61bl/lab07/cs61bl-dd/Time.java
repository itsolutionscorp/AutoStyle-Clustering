public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	try{
    	int colonPos = s.indexOf (":");
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));}
       catch(IndexOutOfBoundsException e){
    	   throw new IllegalArgumentException("Index out of bounds exception");
       }
       catch(NullPointerException e){
    	   throw new IllegalArgumentException("Null argument exception");
       }
       catch(NumberFormatException e){
    	  throw new IllegalArgumentException("Invalid format. (non-numbers, blanck spaces etc.)");
       }
    	
    	if(myHours > 23 || myHours < 0){
    		throw new IllegalArgumentException("Hour out of range.");
    	}
    	else if(myMinutes > 59 || myMinutes < 0){
    		throw new IllegalArgumentException("Minute out of range.");
    	}
    	else if(s.length() - s.indexOf(":") < 3){
    		throw new IllegalArgumentException("Invalid minute input.");
    	}
    	else if(s.indexOf(":") > 2){
    		throw new IllegalArgumentException("Too many leading zeroes in the hours.");
    	}
    	else if(s.length() - s.indexOf(":") > 3){
    		throw new IllegalArgumentException("Too many leading zeroes in the minutes.");
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
