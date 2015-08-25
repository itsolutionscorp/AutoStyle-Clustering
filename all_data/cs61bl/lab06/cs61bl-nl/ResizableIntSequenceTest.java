package lab06;

import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	ResizableIntSequence r=new ResizableIntSequence(10);
	
	public void testAdd(){
		r.add(1);
		r.add(2);
		r.add(3);
		r.add(4);
		assertTrue(r.myCount==4);
		assertTrue(r.myValues[3]==4);
		
	}
	
	public void testInsert() {
		r.add(1);
		r.add(2);
		r.add(3);
		r.insert(1, 1);
		assertTrue(r.myCount == 4);
		assertTrue(r.myValues[1] == 1);
		
	}
	
	public void testRemove(){
		for(int i=0;i<10;i++){
			r.add(i);
		}
		for(int i=9;i>4;i--){
			r.remove(i);
		}
		assertTrue(r.myCount==5);
		assertTrue(r.myValues.length==10);
		r.remove(4);
		assertTrue(r.myValues.length==10);
		r.remove(3);
		assertTrue(r.myValues.length==7);
		
		
	}

}
