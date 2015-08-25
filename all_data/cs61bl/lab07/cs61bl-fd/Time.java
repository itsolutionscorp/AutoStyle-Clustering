public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
        try {
        	int colonPos = s.indexOf (":");

	        myHours = Integer.parseInt (s.substring (0, colonPos));
	        if (myHours < 0 || myHours > 23) {
	        	throw new IllegalArgumentException("Hours must be between 0 and 23.");
	        }
	        myMinutes = Integer.parseInt (s.substring (colonPos+1));
	        if (myMinutes < 0 || myMinutes > 59) {
	        	throw new IllegalArgumentException("Minutes must be between 00 and 59.");
	        }
	        if ((s.substring(colonPos, s.length())).length() <= 2) {
	        	throw new IllegalArgumentException("Minutes must be formatted with two digits.");
	        }

        	if ((s.substring(0, colonPos)).length() > 2) {
        		throw new IllegalArgumentException("Too many leading zeroes present.");
        	}
        	if ((s.substring(colonPos, s.length())).length() > 3) {
        		throw new IllegalArgumentException("Too many leading zeroes present.");
        	}
        	
        }
        catch (IndexOutOfBoundsException e) {
        	throw new IllegalArgumentException("Hours and minutes not separated (colon absent).");
        } catch (ArrayStoreException e) {
        	throw new IllegalArgumentException(e.getMessage());
        } catch (NullPointerException e) {
        	throw new IllegalArgumentException("You did not input a time representation.");
        } catch (ClassCastException e) {
        	throw new IllegalArgumentException(e.getMessage());
        } catch (NumberFormatException e) {
        	if ((s.substring(s.indexOf (":"), s.length())).length() == 1) {
	        	throw new IllegalArgumentException("You did not input a minute value.");
	        }
        	if ((s.substring(0, s.indexOf (":"))).length() == 0) {
	        	throw new IllegalArgumentException("You did not input an hour value.");
	        }
        	throw new IllegalArgumentException("You either did not input a number for both hours and minutes or you have an illegal space in your time input.");
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
