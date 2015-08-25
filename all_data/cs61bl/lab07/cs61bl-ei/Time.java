public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null){
    		throw new IllegalArgumentException("Input null");
    	}
    	
    	String c = ":";
    	for (int k = 0; k <= s.length(); k++){
    		if (k==s.length()){
    			throw new IllegalArgumentException("No colon");
    		}
    		if (s.charAt(k) == c.charAt(0)){
    			break;
    		}
    	}
    	
    	
        int colonPos = s.indexOf (":"); //provided
        char b=s.charAt(0);
        if (b==c.charAt(0)){
        	throw new IllegalArgumentException("Put hour in front of colon ");
        }
        
        if (s.substring (0, colonPos)==null)
        	throw new IllegalArgumentException("Put hour");
  
        try{
        	myHours = Integer.parseInt (s.substring (0, colonPos));
            myMinutes = Integer.parseInt (s.substring (colonPos+1));
        } catch (NumberFormatException e){
        	throw new IllegalArgumentException("You should put numbers in the appropriate place jerk");
        }
        
        if ((((s.substring (0, colonPos)).length() != 1) || ((s.substring (0, colonPos)).length() != 2)) && ((s.substring (colonPos+1)).length() !=2)){
        	throw new IllegalArgumentException("Invalid hours and minutes input lengths");
        } else if (((s.substring (0, colonPos)).length() != 1) && ((s.substring (0, colonPos)).length() != 2)){ 
        	//check that hours is the right length, and then check that minutes is the right length
        	throw new IllegalArgumentException("Invalid hours (should between 0-24)");
        } 
        	
        if (((myHours > 23) || (myHours < 0)) && ((myMinutes <0) || (myMinutes >59))){
        	throw new IllegalArgumentException("Hours and Minutes invalid amount");
        } else if ((myHours > 23) || (myHours < 0)){
        	throw new IllegalArgumentException("Hours invalid amount");
        } else if ((myMinutes <0) || (myMinutes >59)){
        	throw new IllegalArgumentException("Minutes invalid amount");
        }
        
        if ((s.substring (colonPos+1)).length() !=2){
        	throw new IllegalArgumentException("Invalid minutes (should between 0-59");
        }
        
    }
    
    public Time (int hours, int minutes) {
        myHours = hours;
        myMinutes = minutes;
        if (((hours > 23) || (hours < 0)) && ((minutes <0) || (minutes >59))){
        	throw new IllegalArgumentException("Hours and Minutes invalid amount");
        } else if ((hours > 23) || (hours < 0)){
        	throw new IllegalArgumentException("Hours invalid amount");
        } else if ((minutes <0) || (minutes >59)){
        	throw new IllegalArgumentException("Minutes invalid amount");
        }
        
        
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
