import static org.junit.Assert.*;


import org.junit.Test;
import junit.framework.TestCase;

public class ResizableIntSequenceTest extends TestCase {

	public void testAdd() {
		ResizableIntSequence s = new ResizableIntSequence(5); 
		s.add(0); 
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4); 
		String str = s.toString(); 
		assertTrue (str.equals("0 1 2 3 4"));
		
		s.add(5); 
		
		String str2 = s.toString(); 
		assertTrue (str2.equals("0 1 2 3 4 5"));
		
		s.add(13); 
		String str3 = s.toString();
		assertTrue (str3.equals("0 1 2 3 4 5 13"));
	}
	
	public void testInsert() {
		ResizableIntSequence s = new ResizableIntSequence(5); 
		s.add(0); 
		s.add(1);
		
		s.insert(99, 0);
		String str = s.toString();
		assertTrue(str.equals("99 0 1"));
		
		s.insert(99, 2); 
		String str2 = s.toString();
		System.out.println(str2);
		assertTrue(str2.equals("99 0 99 1"));
		
		s.insert(99, 4);
		String str3 = s.toString();
		assertTrue(str3.equals("99 0 99 1 99"));
		
		s.insert(2, 0); 
		String str4 = s.toString();
		assertTrue(str4.equals("2 99 0 99 1 99"));
		
		s.insert(2, 3); 
		String str5 = s.toString();
		assertTrue(str5.equals("2 99 0 2 99 1 99"));
		
		s.insert(2, 7); 
		String str6 = s.toString();
		assertTrue(str6.equals("2 99 0 2 99 1 99 2"));
		
		int testcount = s.size(); 
		assertTrue (testcount == 8); 
	}
	
	public void testRemoved() {
		ResizableIntSequence s = new ResizableIntSequence(55); 
		s.add(0); 
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4); 
		
		s.remove(0); 
		System.out.println(s.getCapacity());
		assertTrue(s.getCapacity() == 15);
		
	}
}
