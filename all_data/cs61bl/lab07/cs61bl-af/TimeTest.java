import static org.junit.Assert.*;

import org.junit.Test;


public class TimeTest {

	@Test
	public void volidinput(){
		
	}
	public void testConstructor() {
	    String[] timeArgs = {null, "x", "x:", ":x", "x:y", "1:", ":30",
	        "4: 35", "55:00", "11:99", " 3:30", "00004:45", "4:007", "4:7",
	        "4 :09", "3:30", "11:55"};
	    Time[] correctTimes = {null, null, null, null, null, null, null,
	        null, null, null, null, null, null, null, null,
	        new Time (3, 30), new Time (11, 55)};
	    //change the testConstructor 
	    
	    for (int k = 0; k < timeArgs.length; k++) {
	       Time t = null;

	      try{
	    	   t = new Time(timeArgs[k]);
	    	   assertEquals(correctTimes[k], t);
	    	  System.out.println("This line is never executed");
	      }
	      catch(NumberFormatException e){
	    	  System.err.println("number format problem");
	      }
	      catch(IllegalArgumentException e){
	    	  System.err.println("Got an exception!");
	    	  System.err.println(e.getMessage());
	    	  if(t == null){
	    		  System.err.println("null");
	    	  }
	      }
	      
	        //System.out.print(t.toString());
	        assertEquals(correctTimes[k], t);
	    }
	}

}
