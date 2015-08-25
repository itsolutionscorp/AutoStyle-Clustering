public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if(s == null){
    		throw new IllegalArgumentException("Enter time");
    	}
    	else if(s.contains(":") == false){ 
        	throw new IllegalArgumentException("Input correct time format");
        }
    	
        int colonPos = s.indexOf (":");
        
        if (s.substring(0, colonPos).length() > 2){
        	throw new IllegalArgumentException("Not a valid time");
        }else if( s.substring(colonPos + 1).length() != 2){
        	throw new IllegalArgumentException("Not a valid time");
        	
        }
        
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
//        System.out.println(myHours);
        
        if( myHours < 0|| myMinutes < 0){
        	throw new IllegalArgumentException("Cannot have negitive time");
        }else if (myHours > 23 || myMinutes > 59){
        	throw new IllegalArgumentException("Too much time to handle");
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


