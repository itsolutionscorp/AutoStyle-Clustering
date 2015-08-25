public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if(s == null){
        	throw new IllegalArgumentException("Argument is a null pointer.");
        }
        int colonPos = s.indexOf (":");
        if(colonPos == -1){
        	throw new IllegalArgumentException("There is no colon.");
        }
        if(colonPos == 0){
        	throw new IllegalArgumentException("No hours is inputed.");
        }
        if(colonPos == s.length()-1){      	
            throw new IllegalArgumentException("No minutes is input.");       
        }
        
        try{
		    myHours = Integer.parseInt (s.substring (0, colonPos));
		    myMinutes = Integer.parseInt (s.substring (colonPos+1));
        } catch (IllegalArgumentException ex) {
        	throw new IllegalArgumentException("Hours and minutes must be intergers, cannot contain space or other charactors");  
        }	
             
       
        if(myHours < 0 || myHours >23 || myMinutes < 0 || myMinutes > 59){
        	throw new IllegalArgumentException("Values for hours or minutes are out of range.");    	
        }
        if(myMinutes < 10 && s.substring (colonPos+1).length() == 1){
    		throw new IllegalArgumentException("Incorrect minute format.");
    	}   
        if(s.substring (0, colonPos).contains("00")|| s.substring (colonPos+1).contains("00")){
        	throw new IllegalArgumentException("Too many zeros.");  
        }       
    }
    
    public Time (int hours, int minutes) {
        myHours = hours;
        myMinutes = minutes;
        if(myHours < 0 || myHours >23 || myMinutes < 0 || myMinutes > 59){
        	throw new IllegalArgumentException("values for hours and minutes that are out of range.");
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
