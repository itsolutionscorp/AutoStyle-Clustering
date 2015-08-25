public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	
    	if (s==null){
    		throw new IllegalArgumentException("you have entered String of length zero");
    	}
    	
        int colonPos = s.indexOf (":");
        if (colonPos == -1) {
        	throw new IllegalArgumentException("there has to be : included as argument");
        }
        
        if (s.substring(0,colonPos).length() >2){
        	throw new IllegalArgumentException("hours have to be two digits");
        	
        }
        if (s.substring(colonPos+1).length() !=2 ){
        	throw new IllegalArgumentException("minutes have to be two digits");
        }
        try { 
        	myHours = Integer.parseInt (s.substring (0, colonPos));
        	myMinutes = Integer.parseInt (s.substring (colonPos+1));
    	} catch (IllegalArgumentException e) {
    		throw new IllegalArgumentException("incorrect input for Hours and Minutes");
    	}
        if (myHours > 23) {
        	throw new IllegalArgumentException("Hours cannot be greater than 23");
        }
        if (myMinutes > 59) {
        	throw new IllegalArgumentException("Minutes cannot be greater than 59");
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
    public static void main(String[] args){
    	Time k = new Time("4 :45");
    	System.out.println(k);
    }
}
