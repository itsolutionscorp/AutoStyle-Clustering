public class Time{

    private int myHours;
    private int myMinutes;
    
    public Time (String s)  {
    		//1. check null
    		if(s==null)
    			throw new IllegalArgumentException("Illegal argument null !");    
    		
	        
	        try{
	        	//2. check :
	        	int colonPos = s.indexOf (":");
	        	
	        	//3. check hour have input
	        	if(s.substring(0, colonPos).length()==0)
		        	throw new IllegalArgumentException("Illegal argument No input for hours!");
	        	
	        	//4. check minute have input
	        	if(s.substring(colonPos+1).length()==0)
		        	throw new IllegalArgumentException("Illegal argument No input for minutes!");
	        	
	        	//5.check if hour are letter
	        	try{
	        		int hour=Integer.parseInt(s.substring(0, colonPos));
	        		
	        		//6.check if minutes are letters
		        	try{
		        		int minute=Integer.parseInt(s.substring(colonPos+1));
		        		
		        		//7.check digit number of hour
				        if(s.substring(0, colonPos).length()>2)
				        	throw new IllegalArgumentException("Illegal argument hours should no bigger than 2 digits!");	
				        
				        //8. check digit number of minute 
				        if(s.substring(colonPos+1).length()!=2)
				        	throw new IllegalArgumentException("Illegal argument minutes should be 2 digits!");
		        		
				        //9. check if hour is within range
				        if(hour>25||hour<0 )
				        	throw new IllegalArgumentException("Illegal argument hour  is out of range!");	
				        //10 check if minute is within range
				        if(minute>59||minute<0)
				        	throw new IllegalArgumentException("Illegal argument minute  is out of range!");
				        
				        myHours = hour;
				        myMinutes=minute;
		        		
		        	}catch(NumberFormatException e){
			        	throw new IllegalArgumentException("Illegal argument minutes should not have letters or space!");
			        }
	        	}catch(NumberFormatException e){
		        	throw new IllegalArgumentException("Illegal argument hour should not have letters or space!");
		        }
	        	
	        }catch(StringIndexOutOfBoundsException si){
	        	
	        	throw new IllegalArgumentException("Illegal argument does not have colon");
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
