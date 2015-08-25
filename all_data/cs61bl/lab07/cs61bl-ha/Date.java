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
        
        if (myYear < 1900 || myYear > 2100) {
        	throw new IllegalStateException ("years not in bound");
        }
        if (myYear < 0 || myDateInMonth < 0 || myMonth < 0) {
        	throw new IllegalStateException ("can't be negative numbers");
        }
        if (myMonth == 2 && myDateInMonth > 29) {
        	throw new IllegalStateException ("can't have more than 29 days in february");
        }
        switch (myMonth) {
        	case 1:
        	case 3:
        	case 5:
        	case 7:
        	case 8:
        	case 10:
        	case 12:
        		myDateInMonth = 31;
        		break;
        	case 4:
        	case 6:
        	case 9:
        	case 11:
        		myDateInMonth = 30;
        		break;
        	case 2:
        		if (leapDay == true) {
            		myDateInMonth = 29;
        		} else {
            		myDateInMonth = 28;
        		}
        		break;
        	default:
        		throw new IllegalStateException ("invalid month");
        	}
        
        }
    }