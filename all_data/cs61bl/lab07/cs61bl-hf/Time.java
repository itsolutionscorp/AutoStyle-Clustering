/*

Dno :
DPresence of any nondigits, including space, either alone or with digits
Dempty mins 
Dempty hours 
Dtoo many zeros
Dtoo large hours
Dtoo large minutes
Dmore than one or 2 digits for hours
Dless or more than 2 digits for mins
*/


public class Time {

    private int myHours;
    private int myMinutes;
    
    public Time (String s) {
    	
       	try{
       		int colonPos = s.indexOf (":");
       		if(colonPos == -1){
       			throw new IllegalArgumentException("No : give!");
       		}
       		
       		if(s.substring (0, colonPos).equals("")){
       			throw new IllegalArgumentException( "Empty hours field!" );
       		}
       		if(s.substring (colonPos+1).equals("")){
       			throw new IllegalArgumentException( "Empty hours field!" );
       		}

       		myHours = Integer.parseInt (s.substring (0, colonPos));
       		if(myHours>23 || myHours<0){
       			throw new IllegalArgumentException("Hours out of range!");
       		}

       		myMinutes = Integer.parseInt (s.substring (colonPos+1));
       		if(myMinutes>59 || myMinutes<0){
       			throw new IllegalArgumentException("Minutes out of range!");
       		}
       		
       		
       		if( colonPos >2 ){       			
       			throw new IllegalArgumentException("Too many zeros in hours field!");
       		}	
       		if( s.substring(colonPos+1).length()>2){       			
       			throw new IllegalArgumentException("Too many zeros in minutes field!");
       		}	

       		if( s.substring(colonPos+1).length()<2){       			 
       			throw new IllegalArgumentException("There must be two digits in minutes field, e.g., 09 instead of 9.");
       		}	

       	
       	}catch(NullPointerException e) {
       		throw new IllegalArgumentException("The time is null!");
       	}catch(StringIndexOutOfBoundsException e) {
       		throw new IllegalArgumentException("':' is missing!");
       	}catch(NumberFormatException e){
       		throw new IllegalArgumentException("Nondigit character is given in hours or minutes");
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
