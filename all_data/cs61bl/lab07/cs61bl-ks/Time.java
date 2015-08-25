public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null){
        	throw new NullPointerException("time is null");
        }
    	if (s.length() < 4) {
    		throw new IllegalArgumentException("not a real time");
    	}
        int colonPos = s.indexOf (":");
        if (s.contains(" ")){
        	throw new IllegalArgumentException("spaces aren't allowed");
        }
        if (colonPos == 0) {
        	throw new IllegalArgumentException("Colon in wrong position");
        }
        if (colonPos != s.length() - 3){
        	throw new IllegalArgumentException("wrong minute value");
        }
        
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        if (myHours > 23){
        	throw new IllegalArgumentException("hour is greater than 23");
        	
        }
        if (myMinutes >59){
        	throw new IllegalArgumentException("Minutes is greater than 59");
        }
        if (s.startsWith("0") && s.length() != 5 ){
        	throw new IllegalArgumentException("wrong number of 0's");
        }
//        if ()
//        	throw new IllegalArgumentException("starts with 0");
//        	
//        }
        
    }
    
    public Time (int hours, int minutes) {
        myHours = hours;
        myMinutes = minutes;
        if (myHours >23){
        	throw new IllegalArgumentException("hour is greater than 23");
        	
        }
        if (myMinutes >59){
        	throw new IllegalArgumentException("Minutes is greater than 59");
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