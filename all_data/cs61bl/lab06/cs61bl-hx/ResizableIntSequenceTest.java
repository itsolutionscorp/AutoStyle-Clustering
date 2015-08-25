import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	   
	    public void testAdd() {
	    	ResizableIntSequence s = new ResizableIntSequence(4);
	        assertTrue (s.isEmpty() == true);
	        s.add(0);
	        s.add(1);
	        assertTrue (s.size() == 2);
	        assertTrue (s.isEmpty() == false);
	        s.add(2);
	        s.add(3);
	        assertTrue (s.size() == 4);
	        assertTrue (s.isEmpty() == false);
	        assertTrue (s.elementAt(0)==0);
	        assertTrue (s.elementAt(2)==2);
	        assertTrue (s.toString().equals("0 1 2 3"));
	        s.add(4);
	        assertTrue (s.myValues.length == 8);
	        assertTrue (s.size() == 5);
	        assertTrue (s.elementAt(4)==4);
	        assertTrue (s.toString().equals("0 1 2 3 4"));
	        s.add(5);
	        s.add(6);
	        s.add(7);
	        s.add(8);
	        assertTrue (s.myValues.length == 16);
	        assertTrue (s.size() == 9);
	        assertTrue (s.elementAt(8)==8);
	        ResizableIntSequence s1 = new ResizableIntSequence(0);
	        s1.add(1);
	        assertTrue (s1.size() == 1);
	        assertTrue (s1.isEmpty() == false);
	        assertTrue (s1.myValues.length == 1);
	        
	        }
	        
	     public void testRemove() {
	    	 ResizableIntSequence s1 = new ResizableIntSequence(8);
	    	 s1.add(0);
	    	 s1.add(1);
	    	 s1.add(2);
	    	 s1.add(3);
	    	 s1.add(4);
	    	 s1.add(5);
	    	 s1.add(6);
	    	 s1.add(7);
	    	 s1.remove(7);
	    	 s1.remove(6);
	    	 s1.remove(5);
	    	 s1.remove(4);
	    	 assertTrue (s1.myValues.length == 4);
		     assertTrue (s1.size() == 4);
		     assertTrue (s1.elementAt(3)==3);
		     s1.remove(3);
		     s1.remove(2);
		     assertTrue (s1.myValues.length == 2);
		     assertTrue (s1.size() == 2);
		     assertTrue (s1.elementAt(1)==1);
		     
	    	 ResizableIntSequence s = new ResizableIntSequence(4);
	    	 s.remove(0);
	    	 assertTrue(s.isEmpty() == true);
	    	 s.add(1);
	    	 s.remove(0);
	    	 assertTrue(s.isEmpty() == true);
	    	 assertTrue (s.size() == 0);
	    	 s.add(2);
	         s.add(3);
	         s.remove(1);
	         assertTrue(s.isEmpty() == false);
	    	 assertTrue (s.size() == 1);
	    	 assertTrue (s.toString().equals("2"));
	    	 s.add(3);
	         s.add(4);
	         s.add(5);
	         s.remove(0);
	         assertTrue(s.isEmpty() == false);
	    	 assertTrue (s.size() == 3);
	    	 assertTrue (s.toString().equals("3 4 5"));
	    	 s.remove(1);
	    	 assertTrue(s.isEmpty() == false);
	    	 assertTrue (s.size() == 2);
	    	 assertTrue (s.toString().equals("3 5"));
	    	 s.remove(1);
	    	 assertTrue(s.isEmpty() == false);
	    	 assertTrue (s.size() == 1);
	    	 assertTrue (s.toString().equals("3"));
	     }
	     
	     
}
