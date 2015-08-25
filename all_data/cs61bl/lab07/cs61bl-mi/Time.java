import static org.junit.Assert.assertEquals;

public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	try {
    	try{
    	try {
        int colonPos = s.indexOf (":");
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        if (myHours > 23) {
        	throw new IllegalArgumentException("Hours are out of bounds");
        }
        if (myMinutes > 59) {
        	throw new IllegalArgumentException("Minutes are out of bounds");
        }
        if (s.substring(0, colonPos).length() > 2) {
        	throw new IllegalArgumentException("Too many leading zeroes in hours");
        }
        if (s.substring(colonPos + 1).length() > 2) {
        	throw new IllegalArgumentException("Minutes have too many leading zeroes");
        }
        if (s.substring(colonPos + 1).length() < 2) {
        	throw new IllegalArgumentException("Minutes do not have 2 digits");
        }
    	} catch (NullPointerException e) {
    		System.out.println("got null pointer. Time must have value");
    		System.out.println(s);
    	}
    	}
    	catch (StringIndexOutOfBoundsException e) {
    		System.out.println("Time must be of the format hours:minutes");
    		System.out.println(s);
    	}
    	}
    	catch (IllegalArgumentException e) {
    		
    		if (s.indexOf(":") == 0) {
    			System.out.println("Hours must be input");
    			System.out.println(s);
    		}
    		else if (s.indexOf(":") == s.length() - 1) {
    			System.out.println("Minutes must be input");
    			System.out.println(s);
    		}
    		else if (s.contains(" ") == true) {
    			System.out.println("There should not be empty spaces in the time format");
    			System.out.println(s);
    		}
    		else if (myHours > 23) {
            	System.out.println("Hours are out of bounds");
            	System.out.println(s);
            }
    		else if (myMinutes > 59) {
            	System.out.println("Minutes are out of bounds");
            	System.out.println(s);
            }
    		else if (s.substring(0, s.indexOf(":")).length() > 2) {
    			System.out.println("Too many leading zeroes in hours");
    			System.out.println(s);
            }
    		else if (s.substring(s.indexOf(":") + 1).length() > 2) {
    			System.out.println("Minutes have too many leading zeroes");
    			System.out.println(s);
            }
    		else if (s.substring(s.indexOf(":") + 1).length() < 2) {
    			System.out.println("Minutes do not have 2 digits");
    			System.out.println(s);
            }
    		else {
    		System.out.println("value out of bounds");
    		System.out.println(s);}
    	}
        //myHours = Integer.parseInt (s.substring (0, colonPos));
        //myMinutes = Integer.parseInt (s.substring (colonPos+1));
    	
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
    
    public static void main (String [] args) {

        String[] timeArgs = {null, "x", "x:", ":x", "x:y", "1:", ":30",
            "4: 35", "55:00", "11:99", " 3:30", "00004:45", "4:007", "4:7",
            "4 :09", "3:30", "11:55"};
        Time[] correctTimes = {null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null,
            new Time (3, 30), new Time (11, 55)};
        for (int k = 0; k < timeArgs.length; k++) {
        	try {
            Time t = new Time(timeArgs[k]);
            }
        	catch (NullPointerException e) {
        		System.out.println("got null pointer");
        		//assertEquals(correctTimes[k], null);
        	}
        }

    }
}
