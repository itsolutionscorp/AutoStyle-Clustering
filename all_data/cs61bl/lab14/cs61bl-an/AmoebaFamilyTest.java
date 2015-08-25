import junit.framework.TestCase;


public class AmoebaFamilyTest extends TestCase {

	public void testHeight1() {
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		family.addChild("Amos McCoy", "mom/dad");
		family.addChild("Amos McCoy", "auntie");
		family.addChild("mom/dad", "me");
		family.addChild("mom/dad", "Fred");
		family.addChild("mom/dad", "Wilma");
		family.addChild("me", "Mike");
		family.addChild("me", "Homer");
		family.addChild("me", "Marge");
		family.addChild("Mike", "Bart");
		family.addChild("Mike", "Lisa");
		family.addChild("Marge", "Bill");
		family.addChild("Marge", "Hilary");
		
		assertTrue(family.height() == 5);
	}
	
	public void testHeight2() {
		AmoebaFamily family = new AmoebaFamily("A");
		family.addChild("A", "b");
		family.addChild("A", "b1");
		family.addChild("b", "c1");
		family.addChild("b", "c2");
		family.addChild("b", "c3");
		family.addChild("b1", "c4");
		family.addChild("b1", "c5");
		family.addChild("c1", "d1");
		family.addChild("c2", "d2");
		family.addChild("c3", "d3");
		family.addChild("d1", "e1");
		family.addChild("d1", "e2");
		family.addChild("d1", "e3");
		family.addChild("d1", "e4");
		family.addChild("d1", "e5");
		family.addChild("d1", "e6");
		family.addChild("d2", "e7");
		family.addChild("d2", "e8");
		family.addChild("d2", "e9");
		family.addChild("d2", "e10");
		family.addChild("d2", "e11");
		family.addChild("e11", "f1");
		family.addChild("e11", "f2");
		family.addChild("e10", "f3");
		family.addChild("e10", "f4");
		family.addChild("f2", "g1");
		family.addChild("f3", "g2");
		family.addChild("g2", "h1");
		
		assertTrue(family.height() == 8);
	}
}
