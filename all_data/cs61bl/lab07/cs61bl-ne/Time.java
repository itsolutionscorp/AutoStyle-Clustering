public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if(s==null){
    		throw new IllegalArgumentException("S cannot be null");
    	}
    	if (!s.contains(":")){
    		throw new IllegalArgumentException("Missing colon.");
    	}
    	if (s.contains(" ")){
    		throw new IllegalArgumentException("Space is in string.");
    	}
    	int colonPos = s.indexOf (":");
	    myHours = Integer.parseInt (s.substring (0, colonPos));
	    if(myHours == (int)myHours){
	    	throw new IllegalArgumentException("Hours must be an integer.");
	    }
	    Integer m = new Integer(myHours);
    	int x =Integer.numberOfLeadingZeros(m);
	    if (x >1){
	    	throw new IllegalArgumentException("Too many leading zeros in front of hours");
	    }
	    if(myHours>24||myHours<0){
	    	throw new IllegalArgumentException("Hours are too large");
	    }
    	myMinutes = Integer.parseInt (s.substring (colonPos+1));
	    if(myMinutes == (int)myMinutes){
	    	throw new IllegalArgumentException("Minutes must be an integer.");
	    }
    	Integer mm = new Integer(myMinutes);
    	int y = Integer.numberOfLeadingZeros(mm);
    	if(y>1){
    		throw new IllegalArgumentException ("Too many leading zeros in front of minutes");
    	}
	    if(myMinutes>60||myMinutes<0){
	    	throw new IllegalArgumentException("Minutes are too large");
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
