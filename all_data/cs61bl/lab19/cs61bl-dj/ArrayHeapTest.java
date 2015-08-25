import static org.junit.Assert.*;

import org.junit.Test;


public class ArrayHeapTest {

	@Test
	public void testHeap() {
		ArrayHeap<String> heap = new ArrayHeap<String>();
		heap.insert("c", 3);
		heap.insert("i", 9);
		heap.insert("g", 7);
		heap.insert("d", 4);
		heap.insert("a", 1);
		heap.insert("h", 8);
		heap.insert("e", 5);
		heap.insert("b", 2);
		heap.insert("t", 3);
		heap.insert("x", 4);
		System.out.println(heap);
		assertTrue(heap.lastNum() == 10); //check for proper sizing
		heap.removeMin(); //first remove
		System.out.println(heap); //heap after first remove
		//assertTrue(heap.removeMin().priority() == (2.0));
		heap.removeMin(); //second remove
		
		//assertTrue(heap.removeMin().priority() == (3.0));
		System.out.println(heap);
		heap.removeMin();
		System.out.println(heap);
		heap.removeMin();
		System.out.println(heap);
		heap.removeMin();
		System.out.println(heap);
		heap.removeMin();
		System.out.println(heap);
		heap.removeMin();
		System.out.println(heap);
		heap.removeMin();
		System.out.println(heap);
		heap.removeMin();
		System.out.println(heap);
		heap.removeMin();
		assertTrue(heap.lastNum() == 0);
		System.out.println(heap);
		
		
		
	    
		
		
	}

}