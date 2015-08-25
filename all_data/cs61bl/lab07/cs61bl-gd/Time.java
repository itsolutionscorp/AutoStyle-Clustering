public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if(s==null){throw new IllegalArgumentException("invalid time");}
    	
        int colonPos = s.indexOf (":");
        if(colonPos!=1&&colonPos!=2){
        	throw new IllegalArgumentException("invalid time");
        }
        if(s.substring(colonPos+1).length()!=2||s.substring(0,colonPos-1).length() >2){
        	throw new IllegalArgumentException("invalid time");
        }
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        if(myHours<0||myHours>23||myMinutes<0||myMinutes>59){
    		throw new IllegalArgumentException("invalid time");
    	}
        
    }
    
    public Time (int hours, int minutes) {
    	if(hours<0||hours>23||minutes<0||minutes>59){
    		throw new IllegalArgumentException("invalid time");
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
