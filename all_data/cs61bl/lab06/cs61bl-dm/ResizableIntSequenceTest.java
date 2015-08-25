import junit.framework.TestCase;

public class ResizableIntSequenceTest extends TestCase {

    public void testConstructor(){
    	ResizableIntSequence i = new ResizableIntSequence(20);
    	assertTrue (i.isEmpty());
    	assertTrue (i.size() == 0);
    }
	
    public void testIsEmpty(){
    	ResizableIntSequence i = new ResizableIntSequence(20);
    	assertTrue (i.isEmpty());
    	i.add(1);
    	assertFalse (i.isEmpty());
    }
    
    public void testSize() {
    	ResizableIntSequence i = new ResizableIntSequence(20);
    	assertTrue (i.size() == 0);
    	i.add(1);
    	assertTrue (i.size() == 1);
    	i.add(3);
    	assertTrue (i.size() == 2);
    }
    
    public void testElementAt(){
    	ResizableIntSequence i = new ResizableIntSequence(20);
    	i.add(1);
    	i.add(3);
    	assertTrue (i.elementAt(0) == 1);
    	assertTrue (i.elementAt(1) == 3);
    }
    
    public void testAdd() {
    	ResizableIntSequence i = new ResizableIntSequence(20);
    	i.add(1);
    	assertTrue (i.elementAt(0) == 1);
    	i.add(3);
    	assertTrue (i.elementAt(1) == 3);
    }
    
    public void testRemove(){
    	ResizableIntSequence i = new ResizableIntSequence(20);
    	i.add(1);
    	i.add(3);
    	i.add(2);
    	i.remove(0);
    	ResizableIntSequence j = new ResizableIntSequence(20);
    	j.add(3);
    	j.add(2); 	
    	assertTrue (i.toString().compareTo(j.toString()) == 0);
    	i.remove(1);
    	j = new ResizableIntSequence(20);
    	j.add(3);
    	assertTrue (i.toString().compareTo(j.toString()) == 0);
    	i = new ResizableIntSequence(20);
    	i.remove(2);
    	j = new ResizableIntSequence(20);
    	assertTrue (i.toString().compareTo(j.toString()) == 0);
    }
    
    public void testInsert(){
    	ResizableIntSequence i = new ResizableIntSequence(20);
    	i.add(1);
    	i.add(3);
    	i.insert(5, 0);
    	ResizableIntSequence j = new ResizableIntSequence(20);
    	j.add(5);
    	j.add(1);
    	j.add(3);
    	assertTrue (i.toString().compareTo(j.toString()) == 0);
    	i.insert(5, 3);
    	j.add(5);
    	assertTrue (i.toString().compareTo(j.toString()) == 0);
    	i.insert(2,2);
    	j.remove(3);
    	j.remove(2);
    	j.add(2);
    	j.add(3);
    	j.add(5);
    	assertTrue (i.toString().compareTo(j.toString()) == 0);
    	i = new ResizableIntSequence(20);
    	i.insert(5, 0);
    	j = new ResizableIntSequence(20);
    	j.add(5);
    	assertTrue (i.toString().compareTo(j.toString()) == 0);
    }

    public void testContains() {
    	ResizableIntSequence i = new ResizableIntSequence(20);
    	i.add(1);
    	i.add(3);
    	assertTrue (i.contains(1));
    	assertTrue (i.contains(3));
    	assertFalse (i.contains(5));   	
    }
    
    public void testToString() {
    	ResizableIntSequence i = new ResizableIntSequence(20);
    	i.add(1);
    	i.add(3);
    	assertTrue (i.toString().compareTo("1 3") == 0);	
    }
    
}
