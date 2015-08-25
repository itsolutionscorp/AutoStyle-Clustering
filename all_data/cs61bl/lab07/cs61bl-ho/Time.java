public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	if (s==null){
    		throw new IllegalArgumentException ("null pointer");
    	}
        int colonPos = s.indexOf (":");
        if (colonPos==-1){
        	throw new IllegalArgumentException ("no : found");
        } 
        for (int i=0; i<colonPos; i++){
        	if (s.charAt(i)!='0' && s.charAt(i)!='1' && s.charAt(i)!='2' && s.charAt(i)!='3' && s.charAt(i)!='4' && s.charAt(i)!='5' && s.charAt(i)!='6' && s.charAt(i)!='7' && s.charAt(i)!='8' && s.charAt(i)!='9'){
            	throw new IllegalArgumentException ("value for hours is of wrong format");
            }
        }
        for (int i=colonPos+1; i<s.length(); i++){
        	if (s.charAt(i)!='0' && s.charAt(i)!='1' && s.charAt(i)!='2' && s.charAt(i)!='3' && s.charAt(i)!='4' && s.charAt(i)!='5' && s.charAt(i)!='6' && s.charAt(i)!='7' && s.charAt(i)!='8' && s.charAt(i)!='9'){
            	throw new IllegalArgumentException ("value for minutes is of wrong format");
            }
        }
        if (colonPos+1==s.length()){
        	throw new IllegalArgumentException ("no minutes input");
        } else if (colonPos==0){
        	throw new IllegalArgumentException ("no hours input");
        } else if (colonPos+2==s.length()){
        	throw new IllegalArgumentException ("minutes input should contain two integers");
        }
        if (colonPos>2 && s.charAt(0)=='0'){
        	throw new IllegalArgumentException ("too many leading zeros in the hours");
        } else if (s.length()-colonPos>3 && s.charAt(colonPos+1)=='0'){
        	throw new IllegalArgumentException ("too many leading zeros in the minuites");
        }
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        if (myHours>24){
        	throw new IllegalArgumentException ("value for hours is out of range");
        }
        if (myMinutes>60){
        	throw new IllegalArgumentException ("value for minutes is out of range");
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
