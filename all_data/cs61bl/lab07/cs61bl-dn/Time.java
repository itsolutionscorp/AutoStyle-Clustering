public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null){
    		throw new IllegalArgumentException("cannot pass null agument");
    	}
    	int colonPos = s.indexOf (":");	
        try{
        	myHours = Integer.parseInt (s.substring (0, colonPos));
        } catch (NumberFormatException e){
        	throw new IllegalArgumentException("hours input not an integer");
        } catch (StringIndexOutOfBoundsException e0){
        	throw new IllegalArgumentException("missing hours input");
        }
        if (myHours > 23){
        	throw new IllegalArgumentException("invalid hours input, hours must be less than 23");
        }
        if (colonPos > 2){
        	throw new IllegalArgumentException("not valid hours format.");
        }
        try{
        	myMinutes = Integer.parseInt (s.substring (colonPos+1));
        } catch (NumberFormatException e){
        	throw new IllegalArgumentException("minute input not an integer");
        } catch (StringIndexOutOfBoundsException e0){
        	throw new IllegalArgumentException("missing minutes input");
        }
        if (myMinutes > 59){
        	throw new IllegalArgumentException("invalid minutes input, minutes must be less than 60 minutes. ");
        }
        if((s.substring(colonPos+1)).length() != 2){
        	throw new IllegalArgumentException("invalid minutes format");
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
