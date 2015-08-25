public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null){
    		throw new IllegalArgumentException("Null argument.");
    	}
             
        int colonPos = s.indexOf (":");      
        if (colonPos == -1){
        	throw new IllegalArgumentException("A time argument should be formatted \"hours:minutes\".");
        }
        
        String hours = s.substring (0, colonPos);
        String minutes = s.substring (colonPos+1);
        
        if (hours.equals("") || hours.charAt(0) < 48 || hours.charAt(0) > 57){
        	throw new IllegalArgumentException("Hours must be numbers, with no trailing spaces.");
        }
        if (minutes.equals("") || minutes.charAt(0) < 48 || minutes.charAt(0) > 57){
        	throw new IllegalArgumentException("Minutes must be numbers, with no trailing spaces.");
        }       
        if(hours.charAt(0) == '0'){
        	throw new IllegalArgumentException("Hours should not have trailing 0\'s.");
        }
        if(minutes.length() != 2){
        	throw new IllegalArgumentException("Minutes must be from 00 to 59. Minutes should not have trailing 0\'s.");
        }
        
        myHours = Integer.parseInt (hours);
        myMinutes = Integer.parseInt (minutes);
        
        if (myHours > 23 || myHours < 0){
        	throw new IllegalArgumentException("Hours must be between 0 and 23."); 
        }
        if (myMinutes >= 60 || myMinutes < 0){
        	throw new IllegalArgumentException("Minutes must be between 00 and 59.");
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
