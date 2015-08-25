import java.util.*;
public class Date {
    
    private int myMonth;        // months range from 1 (January) through 12 (December)
    private int myDateInMonth;  // dates-in-month range from 1 through the number of days in the month
    private int myYear;         // years are between 1900 and 2100 (arbitrary decision)

    public Date (int month, int dateInMonth, int year) {
        myMonth = month;
        myDateInMonth = dateInMonth;
        myYear = year;
    }
    
    // Determine if the date information is internally consistent.
    public void isOK() {
        boolean leapDay;
        if (myYear % 4 != 0) {
            leapDay = false;
        } else if (myYear % 100 != 0) {
            leapDay = true;
        } else if (myYear % 400 != 0) {
            leapDay = false;
        } else {
            leapDay = true;
        }
        
       if (myYear > 2100 || myYear < 1900) {
    	   throw new IllegalStateException("Your years are out of range!");
       }

       if ( myMonth >12  || myMonth <0) {
    	   throw new IllegalStateException("Your months are off!");
       }
       
       if (this.myMonth == 1 || this.myMonth == 3 || this.myMonth == 5 || this.myMonth == 7 || this.myMonth == 8 || this.myMonth == 10 || this.myMonth == 12) {
    	   if(myDateInMonth > 31 || myDateInMonth < 1) {
    		   throw new IllegalStateException("Invalid date value.");
    	   }
        }  
       
            
       
       else if (this.myMonth == 4 || this.myMonth == 6 || this.myMonth == 9 || this.myMonth == 11) {
        	if(myDateInMonth > 30 || myDateInMonth < 1) {
        		throw new IllegalStateException("Invalid date value.");
        	}
    	}
    	else {
    		if(leapDay){
    			if(myDateInMonth > 29 || myDateInMonth < 1) {
    				throw new IllegalStateException("Invalid date value.");
    			}
    		}
    		else {
    			if(myDateInMonth > 28 || myDateInMonth < 1) {
    				throw new IllegalStateException("Invalid date value.");
    			}
    		}
    	}
    }
}
