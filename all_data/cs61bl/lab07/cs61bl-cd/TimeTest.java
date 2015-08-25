import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;


public class TimeTest extends TestCase{

	public void testConstructor() {
	    String[] timeArgs = {null, "x", "x:", ":x", "x:y", "1:", ":30",
	        "4: 35", "55:00", "11:99", " 3:30", "00004:45", "4:007", "4:7",
	        "4 :09", "3:30", "11:55"};
	    Time[] correctTimes = {null, null, null, null, null, null, null,
	        null, null, null, null, null, null, null, null,
	        new Time (3, 30), new Time (11, 55)};
	    for (int k = 0; k < timeArgs.length; k++) {
	        try {
	        	System.out.println(k);
	        	int colonPos = timeArgs[k].indexOf (":");
		        if(colonPos < 0){
		        	System.out.println("input needs a colon"); 
		        	throw new IllegalArgumentException();
		        }
	        	Time t = new Time(timeArgs[k]);
	        	assertEquals(correctTimes[k], t);
	        }
	        catch (NumberFormatException n) {
	        	System.out.println("input is not a number");       	
	        	assertEquals(correctTimes[k], null);
	        }
	        catch(NullPointerException m) {
	        	System.out.println("input is null");
	        	assertEquals(correctTimes[k], null);
	        }
	        catch (IllegalArgumentException x){
	        	System.out.println("hours or minutes are not valid");
	        	assertEquals(correctTimes[k], null);
	        }
	    }
}
}