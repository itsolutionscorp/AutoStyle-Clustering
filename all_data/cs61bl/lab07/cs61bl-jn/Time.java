public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
        // NullPointer
    		if (s == null) {
    			throw new IllegalArgumentException("string is null");
    		}
    		
    		// There is no :
    		if (s.contains(":") == false) {
    			throw new IllegalArgumentException("there is no colon");
    		}
    		
    		int colonPos = s.indexOf (":");
    		
    		String former = "";
    		String latter = "";
    		
    		
    		// all numbers aside from the colon
    		for (int i = 0; i < s.length(); i++) {
    			if (i < colonPos) {
    				former = former + s.charAt(i);
    			} else if (i > colonPos) {
    				latter = latter + s.charAt(i);
    			}
    		}
    		
    		int hour;
    		int minute;
    		try {
    			hour = Integer.parseInt(former);
    		} catch (NumberFormatException e){
    			throw new IllegalArgumentException("hours number is not composed of numbers");
    		}
    		
    		try {
    			minute = Integer.parseInt(latter);
    		} catch (NumberFormatException e) {
    			throw new IllegalArgumentException("minutes number is not composed of numbers");
    		}
    		
    		if (former.charAt(0) == '0') {
    			throw new IllegalArgumentException("hours contains leading 0");
    		}
    		
    		if (former.length() == 0 || former.length() > 2) {
    			throw new IllegalArgumentException("hours number contains incorrect number of digits");
    		}

    		
    		if (latter.length() != 2) {
    			throw new IllegalArgumentException("minutes number contains incorrect number of digits");
    		}
    		
    		if (hour > 12) {
    			throw new IllegalArgumentException("hours number too large");
    		}
    		
    		if (minute >= 60) {
    			throw new IllegalArgumentException("minutes number is too large");
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
}
