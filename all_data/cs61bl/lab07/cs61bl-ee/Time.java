public class Time {

    private int myHours;
    private int myMinutes;
    private int hours_input;
    private int minutes_input;
 
    
    
    public Time (String s) throws IllegalArgumentException{
    	
      	if (s == null){
    		throw new IllegalArgumentException("Null Input");
    	}
   	
	    else{
	    	int colonPos = s.indexOf (":");
	        if (colonPos == -1){
	        	throw new IllegalArgumentException("No Colon");
	        }
	        if(s.substring (0, colonPos).length() > 2 || s.substring (colonPos+1).length()> 2){
	        	throw new IllegalArgumentException("Yo! Your inputs probabaly have too many inputs!");
	        }else{
	        	try{
	        		hours_input = Integer.parseInt(s.substring (0, colonPos));
	        	}
	        	catch(IllegalArgumentException hour_input){
	        	  throw new IllegalArgumentException("Yo! Your hour input cannot be parsed into an integer");
	        	}
	        	try{
	        		minutes_input = Integer.parseInt(s.substring (colonPos+1));
	        	}
	        	catch(IllegalArgumentException minutes_input){
	        	  throw new IllegalArgumentException("Yo! Your minutes input cannot be parsed into an integer");
	        	}
	        	if(hours_input > 23 || hours_input < 0){
	        		throw new IllegalArgumentException("Yo! Your hours input out of bound");
	        	}
	        	if(minutes_input > 59 || minutes_input < 0){
	        		throw new IllegalArgumentException("Yo! Your minutes input out of bound");
	        	} else if(minutes_input < 10 && s.substring (colonPos+1).length() == 1){
	        		throw new IllegalArgumentException("Yo! Your minutes should have a leading zero");
	        	}

	        }
	        
    }
          myHours = hours_input;
          myMinutes = minutes_input;
          
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
