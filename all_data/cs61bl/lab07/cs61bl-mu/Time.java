public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null) {
        	throw new IllegalArgumentException("Pointer is null");
        }
        int colonPos = s.indexOf (":");      
        if (colonPos == -1) {
    		throw new IllegalArgumentException ("No semicolon");
    	}
        if (s.substring (0, colonPos).length() == 0) {
        	 try {
	    		 myMinutes = (Integer)(Integer.parseInt (s.substring (colonPos+1)));
	    	 }
	          catch(NumberFormatException e){
	        	  throw new IllegalArgumentException ("No hours given and Minutes is not valid");
	          }
    		throw new IllegalArgumentException ("No hours given");
    	}
        if (s.substring (colonPos+1).length() == 0) {
        	 try {
	    		 myHours = (Integer)(Integer.parseInt (s.substring (0, colonPos)));
	    	 }
	          catch(NumberFormatException e){
	        	  throw new IllegalArgumentException ("No minutes given and Hours is not valid");
	          }
    		throw new IllegalArgumentException ("No minutes given");
    	}
        
        try {
   		 myHours = (Integer)(Integer.parseInt (s.substring (0, colonPos)));
   	 	}
         catch(NumberFormatException e){
       	  throw new IllegalArgumentException ("Hours is not valid");
         }
        
        try {
   		 myMinutes = (Integer)(Integer.parseInt (s.substring (colonPos+1)));
   	 	}
         catch(NumberFormatException e){
       	  throw new IllegalArgumentException ("Minutes is not valid");
         }
        
        
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1)); 
        
        if ((myHours < 0 || myHours > 23) && (myMinutes < 0 || myMinutes > 59)) {
    		throw new IllegalArgumentException ("Hours and Minutes not valid");
    	}
        if (myHours < 0 || myHours > 23) {
    		throw new IllegalArgumentException ("Hours not valid");
    	}
    	if (myMinutes < 0 || myMinutes > 59) {
    		throw new IllegalArgumentException ("Minutes not valid");
    	}
    	String hour = s.substring (0, colonPos);
    	int digit = 0;
    	if(myHours<10){
    		digit =1;
    	}else{
    		digit = 2;
    	}
    	if (hour.length() != digit) {
    		throw new IllegalArgumentException ("Hours not in valid format");
    	}
    	String minute = s.substring (colonPos+1);
    	if (minute.length() != 2) {
    		throw new IllegalArgumentException ("Minutes not in valid format");
    	}
    }
    
    public Time (int hours, int minutes) {
    	if (hours < 0 || hours > 23) {
    		throw new IllegalArgumentException ("Hours not valid");
    	}
    	if (minutes < 0 || minutes > 59) {
    		throw new IllegalArgumentException ("Minutes not valid");
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
