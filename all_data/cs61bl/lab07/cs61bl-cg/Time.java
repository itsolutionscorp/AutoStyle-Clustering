public class Time {

    private int myHours;
    private int myMinutes;
    private boolean isNull;
    
    public Time (String s) {
        int colonPos = s.indexOf (":");
        if (s.length() - colonPos > 1){
        	isNull = true;
        }
        if (colonPos > 2){
        	isNull = true;
        }
        try{
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        }
        catch (StringIndexOutOfBoundsException | NumberFormatException e){
        	System.out.println("There is no : in the time.");
        	isNull = true;
        }
        if (myHours > 12) {
        	isNull = true;
        }
        else if (myMinutes > 60) {
        	isNull = true;
        }
    }
    
    public Time (int hours, int minutes) {
        	myHours = hours;
            myMinutes = minutes;
        if (myHours > 12) {
        	isNull = true;
        }
        else if (myMinutes > 60) {
        	isNull = true;
        }

        
    }

    public boolean equals (Object obj) {
        Time t = (Time) obj;
        return myHours == t.myHours && myMinutes == t.myMinutes;
    }

    public String toString() {
    	if (!isNull){
	        if (myMinutes < 10) {
	            return myHours + ":0" + myMinutes;
	        } else {
	            return myHours + ":" + myMinutes;
	        }
    	}
    	else{
    		return "I am null";
    	}
    }
}
