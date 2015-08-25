public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	
    	
	    	if(s==null){
	    		throw new IllegalArgumentException("inputted empty string");
	    	}
	    	int colonPos = s.indexOf (":");
	    	if(colonPos==-1){
	    		throw new IllegalArgumentException("no colon in input");
	    	}
	    	String hour = s.substring(0, colonPos);
	    	String minutes = s.substring(colonPos+1);
	    	for(int i=0;i<hour.length();i++){
	    		if(!Character.isDigit(hour.charAt(i))){
	    			throw new IllegalArgumentException("input has non numeric character (includes extraneous spaces)");	
	    		}
	    	}
	    	for(int i=0;i<minutes.length();i++){
	    		if(!Character.isDigit(minutes.charAt(i))){
	    			throw new IllegalArgumentException("input has non numeric character (includes extraneous spaces)");	
	    		}
	    	}
	    	if(colonPos==0){
	    		throw new IllegalArgumentException("no hour in input");
	    	}
	    	if(colonPos==s.length()-1){
	    		throw new IllegalArgumentException("no minutes in input");
	    	}
	    	
	    	if((hour.length()>2 && hour.charAt(0)=='0') || (minutes.length()>2 && minutes.charAt(0)=='0')){
	    		throw new IllegalArgumentException("input has invalid zeroes");
	    		
	    	}
	    	if(minutes.length()==1){
	    		throw new IllegalArgumentException("invalid mintues - missing digits");
	    	}
	    	int h = Integer.parseInt (s.substring (0, colonPos));
			int m = Integer.parseInt (s.substring (colonPos+1));
			if(h<0 || h>23){
				throw new IllegalArgumentException("hours out of range");
				}
			else{
				myHours = h;
				}
			if(m<0 || m>59){
				throw new IllegalArgumentException("minutes out of range");
				
			}
			else{
				myMinutes = m;
			}
    	
    	
    }
    
    public Time (int hours, int minutes) {
    	if(hours<0 || hours>23){
			throw new IllegalArgumentException("hours out of range");
			}
		else{
			myHours = hours;
			}
		if(minutes<0 || minutes>59){
			throw new IllegalArgumentException("minutes out of range");
			
		}
		else{
			myMinutes = minutes;
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
}
