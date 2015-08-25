public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	
        if (s!=null) {
			int colonPos = s.indexOf(":");
			try {
				if (colonPos == -1)
					throw new IllegalArgumentException(
							"Thou need'st a colon. It is healthful for digestion and colonoscopies.");
				else {
					myHours = Integer.parseInt(s.substring(0, colonPos));
					myMinutes = Integer.parseInt(s.substring(colonPos + 1));
					if (myMinutes > 59)
						throw new IllegalArgumentException(
								"Bah! Minutes must be < 60");
					else if (myHours > 23)
						throw new IllegalArgumentException(
								"You idiot. Hours must be <= 23");
					else if (s.substring(0, colonPos).length() > 2)
						throw new IllegalArgumentException(
								"Imbecile! Hours may only be two digits max.");
					else if (s.substring(colonPos + 1).length() !=2)
						throw new IllegalArgumentException(
								"Fool! Minutes *must* be two digits. No more, no less.");
				}

			} catch (NumberFormatException e) {
				throw new IllegalArgumentException(
						"Oh you simpleton. Format should be hh:mm");
			}
		}
        else
        	throw new IllegalArgumentException("How about this? I declare YOU null and void! Let's see you return that!");
        
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
