public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	try {
	        int colonPos = s.indexOf (":");
	        if(colonPos < 0){
	        	System.out.println("input needs a colon"); 
	        	throw new IllegalArgumentException();
	        }
	        int hours = Integer.parseInt (s.substring (0, colonPos));
	        int minutes = Integer.parseInt (s.substring (colonPos+1));
	        if (hours < 0 || hours > 23) {
	  		   System.out.println("invalid hours");
	  		   throw new IllegalArgumentException();
	  	   	}
	     	else if (minutes < 0 || minutes > 59) {
	   		   System.out.println("invalid minutes");
	   		 throw new IllegalArgumentException();
	     	}
	     	
	     	else if (s.split(":")[0].length() > 2) {
	   		   System.out.println("too many zeroes");
	   		 throw new IllegalArgumentException();
	   	   	}
	      	else if (s.split(":")[1].length() != 2) {
	    		   System.out.println("too many zeroes");
	    		throw new IllegalArgumentException();
	    	   	}
	        myHours = hours;
		    myMinutes = minutes;
    	}
    	
        catch (NumberFormatException n) {
        	System.out.println("input is not a number");       	
        	throw new IllegalArgumentException();
        }
        catch(NullPointerException m) {
        	System.out.println("input is null");
        	throw new IllegalArgumentException();
        }
        
    }
    
    public Time (int hours, int minutes) {
    	System.out.println(new Integer(hours).toString());
    	if (hours < 0 || hours > 23) {
 		   System.out.println("invalid hours");
 		   throw new IllegalArgumentException();
 	   	}
    	else if (minutes < 0 || minutes > 59) {
  		   System.out.println("invalid minutes");
  		 throw new IllegalArgumentException();
    	}
    	
    	else if (new Integer(hours).toString().length() > 2) {
  		   System.out.println("too many zeroes");
  		 throw new IllegalArgumentException();
  	   	}
     	else if (new Integer(minutes).toString().length() > 2) {
   		   System.out.println("too many zeroes");
   		throw new IllegalArgumentException();
   	   	}
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
