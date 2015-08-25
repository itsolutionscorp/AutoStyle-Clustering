import junit.framework.TestCase;


public class AmoebaFamilyTest extends TestCase {
	public void testAddChild() {
		AmoebaFamily t = new AmoebaFamily("1");
		t.myRoot.addChild(t.myRoot.myName, "2");
		t.myRoot.addChild(t.myRoot.myName, "3");
		t.addChild("2", "4");
		assertEquals("4", t.myRoot.myChildren.get(0).myChildren.get(0).myName);
	}
	
	public void testMakeNamesLowercase() {
		AmoebaFamily t = new AmoebaFamily("A");
		t.myRoot.addChild(t.myRoot.myName, "B");
		t.myRoot.addChild(t.myRoot.myName, "C");
		t.makeNamesLowercase();
		assertEquals("b", t.myRoot.myChildren.get(0).myName);
	}
	
	public void testReplaceName() {
		AmoebaFamily t = new AmoebaFamily("A");
		t.myRoot.addChild(t.myRoot.myName, "B");
		t.myRoot.addChild(t.myRoot.myName, "C");
		t.replaceName("B", "D");
		assertEquals("D", t.myRoot.myChildren.get(0).myName);
	}
	
	public void testPrintFlat() {
		AmoebaFamily t = new AmoebaFamily("A");
		t.myRoot.addChild(t.myRoot.myName, "B");
		t.myRoot.addChild(t.myRoot.myName, "C");
		t.replaceName("B", "D");
		t.printFlat();
	}
	
	public void testPrettyPrint() {
		AmoebaFamily t = new AmoebaFamily("A");
		t.myRoot.addChild(t.myRoot.myName, "B");
		t.myRoot.addChild(t.myRoot.myName, "C");
		t.myRoot.addChild("C", "CC");
		t.myRoot.addChild("CC", "D");
		t.myRoot.addChild("B", "BB");
		t.print();
	}
	
	public void testLongest() {
		AmoebaFamily t = new AmoebaFamily("A");
		t.myRoot.addChild(t.myRoot.myName, "B");
		t.myRoot.addChild(t.myRoot.myName, "C");
		t.myRoot.addChild("C", "CC");
		t.myRoot.addChild("CC", "D");
		t.myRoot.addChild("B", "BBB");
		assertEquals(t.longestName(), "BBB");
	}
	
	public void testSize() {
		AmoebaFamily t = new AmoebaFamily("A");
		t.myRoot.addChild(t.myRoot.myName, "B");
		t.myRoot.addChild(t.myRoot.myName, "C");
		t.myRoot.addChild("C", "CC");
		t.myRoot.addChild("CC", "D");
		t.myRoot.addChild("B", "BBB");
		assertEquals(t.size(), 6);
	}
	
	public void testHeight() {
		AmoebaFamily t = new AmoebaFamily("A");
		t.myRoot.addChild(t.myRoot.myName, "B");
		t.myRoot.addChild(t.myRoot.myName, "C");
		t.myRoot.addChild("C", "CC");
		t.myRoot.addChild("CC", "D");
		t.myRoot.addChild("B", "BBB");
		t.myRoot.addChild("BBB", "F");
		assertEquals(t.height(), 4);
		t.myRoot.addChild("C", "E");
		t.myRoot.addChild("E", "K");
		t.myRoot.addChild("K", "J");
		assertEquals(t.height(), 5);
		t.print();
	}
}
