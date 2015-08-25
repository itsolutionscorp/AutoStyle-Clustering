public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null){
    		throw new IllegalArgumentException("1. nothing is inputted");
    	}else{
    		int colonPos = s.indexOf (":");	
    		if(colonPos ==-1){
    			throw new IllegalArgumentException("2. : is not inputted");
    		}else if(colonPos ==0){
    			throw new IllegalArgumentException("3. no hour is inputted");
    		}else if(colonPos > 2 || s.length()> 5){
    			throw new IllegalArgumentException("4. oo many leading zeroes in the hours or minutes");
    		}
    		else{			
    			 myHours = Integer.parseInt(s.substring (0, colonPos));
    		     myMinutes = Integer.parseInt (s.substring (colonPos+1));
    		     //check if input hour is an integer
    		     if(myHours >23){
    		    	 throw new IllegalArgumentException("4. hour input is too large ");
    		     }else if(myMinutes > 59){
    		    	 throw new IllegalArgumentException("5. minute input is too large");
    		     }else if(myHours <0){
    		    	 throw new IllegalArgumentException("6. hour input is too small");
    		     }else if(myMinutes <0){
    		    	 throw new IllegalArgumentException("7. Minutes input is too small");
    		     }
    		    	 
    		}
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
