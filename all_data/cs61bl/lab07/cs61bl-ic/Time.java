public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
        int colonPos = s.indexOf(":");

        if (!s.contains(":") || s.indexOf(":") != s.lastIndexOf(":"))
            throw new IllegalArgumentException("Time must contain exactly one colon: " + s);

        String hourStr = s.substring(0, colonPos);
        String minuteStr= s.substring(colonPos + 1);

        // Check length of minute and hour input
        if (hourStr.length() == 0)
            throw new IllegalArgumentException("Missing hour");
        if (minuteStr.length() == 0)
            throw new IllegalArgumentException("Missing minute");
        if (hourStr.length() > 2)
            throw new IllegalArgumentException("Too many digits in hour: " + hourStr);
        if (minuteStr.length() != 2)
            throw new IllegalArgumentException("Minutes must have exactly two digits: " + minuteStr);

        int hour = Integer.parseInt(hourStr);
        int minute = Integer.parseInt(minuteStr);

        // Check that minute and hour are in range
        if (hour < 0 || hour > 23)
            throw new IllegalArgumentException("Hour out of range: " + hour);
        if (minute < 0 || minute > 59)
            throw new IllegalArgumentException("Minute out of range: " + minute);
        myHours = hour;
        myMinutes = minute;
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
