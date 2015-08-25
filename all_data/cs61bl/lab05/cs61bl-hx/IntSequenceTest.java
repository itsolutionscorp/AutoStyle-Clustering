import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	
    public void testConstructor() {
        IntSequence s = new IntSequence(0);
        assertTrue (s.size() == 0);
        assertTrue (s.isEmpty() == true);
        IntSequence s1 = new IntSequence(5);
        assertTrue (s1.size() == 0);
        assertTrue (s1.isEmpty() == true);
    }
    
    public void testAdd() {
        IntSequence s = new IntSequence(6);
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
//        System.out.println(s.toString());
        assertTrue (s.toString().equals("0 1 2 3"));
        }
        
     public void testRemove() {
    	 IntSequence s = new IntSequence(4);
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
     
       public void testInsert() {
    	  IntSequence s = new IntSequence(6);
    	  s.insert(1, 0);
    	  assertTrue(s.isEmpty() == false);
       	  assertTrue (s.size() == 1);
      	  assertTrue (s.toString().equals("1"));
          s.insert(2, 1);
  	      assertTrue(s.isEmpty() == false);
     	  assertTrue (s.size() == 2);
    	  assertTrue (s.toString().equals("1 2"));
    	  s.insert(0, 0);
  	      assertTrue(s.isEmpty() == false);
     	  assertTrue (s.size() == 3);
    	  assertTrue (s.toString().equals("0 1 2"));
    	  s.insert(3, 3);
  	      assertTrue(s.isEmpty() == false);
     	  assertTrue (s.size() == 4);
    	  assertTrue (s.toString().equals("0 1 2 3"));
    	  s.insert(0, 2);
  	      assertTrue(s.isEmpty() == false);
     	  assertTrue (s.size() == 5);
    	  assertTrue (s.toString().equals("0 1 0 2 3"));
    	   
       }
     
       public void testContains() {
    	   IntSequence s = new IntSequence(6);
    	   assertTrue(s.contains(0) == false);
    	   s.add(3);
           s.add(4);
           s.add(5);
           assertTrue(s.contains(3) == true);
           assertTrue(s.contains(4) == true);
           assertTrue(s.contains(5) == true);
           s.remove(1);
           assertTrue(s.contains(4) == false);
           assertTrue(s.contains(3) == true);
           assertTrue(s.contains(5) == true);
           assertTrue(s.contains(7) == false);
       }
    
}
