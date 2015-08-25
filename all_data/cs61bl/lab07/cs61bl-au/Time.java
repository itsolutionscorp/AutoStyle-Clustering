public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s == null)
    		throw new IllegalArgumentException("s is null!");
        int colonPos = s.indexOf (":");
        if (colonPos == -1)
        	throw new IllegalArgumentException("No colon!");
        for (int i = 0; i < s.length(); i++){
        	if(s.indexOf(i) != 0 || s.indexOf(i) != 1 || s.indexOf(i) != 2 || s.indexOf(i) != 2 || s.indexOf(i) != 3
        			 || s.indexOf(i) != 4 || s.indexOf(i) != 5 || s.indexOf(i) != 6 || s.indexOf(i) != 7
        			 || s.indexOf(i) != 8 || s.indexOf(i) != 9) {
        		throw new IllegalArgumentException("Input contains non-numbers!");
        	}
        }
        if (s.substring(0, colonPos).length() > 2 || s.substring(colonPos + 1, s.length()-1).length() > 2){
        	throw new IllegalArgumentException("Too long or short for arguments");
        }
        int mh = Integer.parseInt (s.substring (0, colonPos));
        int mm = Integer.parseInt (s.substring (colonPos+1));
        if (mh < 0 || mh > 23 || mm < 0 || mm > 59) {
    		throw new IllegalArgumentException("Out of bounds!");
    	}
    	        
        myHours = mh;
        myMinutes = mm;
    }
    
    public Time (int hours, int minutes) {
    	if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
    		throw new IllegalArgumentException("Caught illigal arguments!");
    	}
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
    public static void main(String[] args){
    	int a = 002;
    	int b = 2;
    	String c = " ";
    	if(a == b){
    	    System.out.println("equal");
        }
    	System.out.println(Integer.toString(a));
    	System.out.println("" + a);
    	int d = Integer.parseInt (c);
    	//int f = c.indexOf(":");

    }
}
