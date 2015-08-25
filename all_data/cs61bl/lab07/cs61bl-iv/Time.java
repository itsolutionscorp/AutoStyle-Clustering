public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s==null) {			// if the string is null throw an error
        	throw new IllegalArgumentException("ERROR: NULL String!");
        }
        int colonPos = s.indexOf (":");
        if (colonPos==-1) {		// if no colon throw an error
        	throw new IllegalArgumentException("ERROR: no colon!");
        }
        if (s.substring (0, colonPos).isEmpty()||s.substring (colonPos+1).isEmpty()) {	// such as  :30  or  1:  cases
        	throw new IllegalArgumentException("ERROR: no hours or minutes number.");        	
        }
        if (s.substring (0, colonPos).contains(" ")||s.substring (colonPos+1).contains(" ")) {
        	throw new IllegalArgumentException("ERROR: there is space.");        	
        }

        for (int i=0; i<(s.substring (0, colonPos).length()); i++) {
	        if (Character.isLetter((s.substring (0, colonPos)).charAt(i))) {
	        	throw new IllegalArgumentException("ERROR: there are letters.");        	
	        }
        }
        for (int i=0; i<(s.substring (colonPos+1).length()); i++) {
	        if (Character.isLetter((s.substring (colonPos+1)).charAt(i))) {
	        	throw new IllegalArgumentException("ERROR: there are letters.");        	
	        }
        }

        int num = 0, num2 = 0;	// check for out of range and leading zeroes
        for (int i=1; i<25; i++) {
        	if (Integer.parseInt (s.substring (0, colonPos))==i) {
        		if (s.substring (0, colonPos).equals(i+"")) {
    	        	num = 1;
    	        } else { num = 2; }
        	}
    		if (s.substring (0, colonPos).equals(i+"")) { num = 1; }
        }
        if (num==0) {
			throw new IllegalArgumentException("ERROR: values for hours and minutes that are out of range.");
		} else if (num==2) {
			throw new IllegalArgumentException("ERROR: too many leading zeroes in the hours.");			
		}
        for (int j=1; j<61; j++) {
        	if (Integer.parseInt (s.substring (colonPos+1))==j) {
        		if (s.substring (colonPos+1).equals(j+"")) {
    	        	num2 = 1;
    	        } else { num2 = 2; }
        	}
	        if (s.substring (colonPos+1).equals(j+"")) {
	        	num2 = 1;
	        }
        }
        if (num2==0) {
			throw new IllegalArgumentException("ERROR: values for hours and minutes that are out of range.");
        } else if (num2==2) {
			throw new IllegalArgumentException("ERROR: too many leading zeroes in the minutes.");			
		}
        if (Integer.parseInt(s.substring (colonPos+1))<10 && (!s.substring (colonPos+1).equals(0))) {
			throw new IllegalArgumentException("ERROR: minute number smaller than 10 but without a 0!");
        }
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
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
    public int getHour() {
    	return myHours;
    }
    public int getMin() {
    	return myMinutes;
    }
}
