public class Time {

    private int myHours;
    private int myMinutes;

    public Time (String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input is null.");
        }
        if (!Character.isDigit(s.charAt(0))) {
            throw new IllegalArgumentException("Input must be numerical.");
        }
        if (s.length() < 4) {
            throw new IllegalArgumentException("Input is not long enough.");
        }
        else if ((s.charAt(s.indexOf(":")-1)) == ' ') {
            throw new IllegalArgumentException("Cannot have spaces in input before colon.");
        }
        else if ((s.charAt(s.indexOf(":")+1)) == ' ') {
            throw new IllegalArgumentException("Cannot have spaces in input after colon.");
        }
        else if (s.substring(s.indexOf(":")).length() != 3 || (s.charAt(2) != ':' && s.charAt(1) != ':')) {
            throw new IllegalArgumentException("Hours must be one or two digits, minutes must be two.");
        }
        else if (Character.getNumericValue(s.charAt(0)) >= 2 && s.charAt(1) != ':' && Character.getNumericValue(s.charAt(1)) >= 4) {
            throw new IllegalArgumentException("Cannot have more than 24 hours in a day.");
        }
        else if (Character.getNumericValue(s.charAt(3)) >= 6) {
            throw new IllegalArgumentException("Hours cannot have more than 59 minutes.");
        }
        int colonPos = s.indexOf (":");
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
