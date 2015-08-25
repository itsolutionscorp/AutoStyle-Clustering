public class Time {

	private int myHours;
	private int myMinutes;

	public Time (String s) {

		if (s == null) {
			throw new IllegalArgumentException("Null");
		} else if (s.length() < 4) {
			throw new IllegalArgumentException("Invalid input format");
		} else if (s.contains(" ")){
			throw new IllegalArgumentException("Wrong Format: Delete Space!");
		} else if (s.contains(":") == false){
			throw new IllegalArgumentException("Wrong Format: Need Colon!");
		} 
		
		int colonPos = s.indexOf (":");
		
		try {
            myHours = Integer.parseInt (s.substring (0, colonPos));
            myMinutes = Integer.parseInt (s.substring (colonPos+1));
        	} catch (IllegalArgumentException e) {
        		throw new IllegalArgumentException("Wrong Format: No Letters!");
    		}
				
		if (s.substring (0, colonPos).length() < 1 || s.substring (0, colonPos).length() > 2 ){
			throw new IllegalArgumentException("Wrong Format: Wrong hours format");
		} else if (Integer.parseInt (s.substring (0, colonPos)) > 23 || Integer.parseInt (s.substring (0, colonPos)) < 0) {
    		throw new IllegalArgumentException("Invalid hours!");
		} else if (s.substring (colonPos+1).length() != 2 ) {
			throw new IllegalArgumentException("Wrong Format: Wrong minutes format!!");
        } else if (Integer.parseInt (s.substring (colonPos+1)) > 59 || Integer.parseInt (s.substring (colonPos+1)) < 0) {
        	throw new IllegalArgumentException("Invalid minutes!");
        } 
		
    }
		

	public Time(int hours, int minutes) {
		myHours = hours;
		myMinutes = minutes;
	}

	public boolean equals(Object obj) {
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
