public class Time {
	// spaces before or after the colon? could cause potential error
	// current issue i'm trying to figure out: 
	// how to catch a string with a alpha value before it parses to an integer

	private int myHours;
	private int myMinutes;

	public Time (String s) {
		if (s == null) {
			throw new IllegalArgumentException("null input");
		}
		// HI ERIC :) I WANT TO INSERT SOME SORT OF THING THAT WILL THROW A NEW ERROR
		// HERE THAT WILL KEEP A STRING WITH VAL "X"OR SOMETHING
		// FROM ATTEMPTING TO BE PARSED INTO AN INTEGER
		// BUT I CAN'T FIGURE IT OUT SORRY
		// // // i think that should also deal with times with spaces before the colons
		int colonPos = s.indexOf (":");
		if (colonPos < 0) {
			throw new IllegalArgumentException("must use :");
		}
		if (s.length() - 3 != colonPos) {
			throw new IllegalArgumentException("wrong input");
		}
	    if (colonPos == 0) {
	    	throw new IllegalArgumentException("must input hours");
	    }
	   if (colonPos > 2) {
		   throw new IllegalArgumentException("wrong input");
	   }
	    
		myHours = Integer.parseInt (s.substring (0, colonPos));
		myMinutes = Integer.parseInt (s.substring (colonPos+1));
		
		String hourString = ""+myHours;

		String minString = ""+myMinutes;
		if(hourString.length() < 1 || minString.length() < 1){
			throw new IllegalArgumentException("too few digits for a time");
		}
		if(hourString.length() > 2 || minString.length() > 2){
			throw new IllegalArgumentException("too many digits for a time");
		} if(myHours > 23 || myHours < 0){
			throw new IllegalArgumentException("that is not a legal hour");
		} if(myMinutes > 59 || myMinutes < 0){
			throw new IllegalArgumentException("that is not a legal minute");
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
