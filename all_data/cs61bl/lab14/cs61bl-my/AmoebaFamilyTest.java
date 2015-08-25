import junit.framework.TestCase;


public class AmoebaFamilyTest extends TestCase {

	public void testmakeNamesLowercase() {
		AmoebaFamily myFamily = new AmoebaFamily("STEVE");
		myFamily.addChild("STEVE", "JIM");
		myFamily.addChild("STEVE", "CTHULU");
		myFamily.addChild("CTHULU", "JOSH PECK");
		myFamily.makeNamesLowercase();
		myFamily.print();
	}
	
	public void testreplaceName() {
		AmoebaFamily myFamily = new AmoebaFamily("Steve");
		myFamily.addChild("Steve", "Jim");
		myFamily.addChild("Steve", "Cthulu");
		myFamily.addChild("Cthulu", "Josh Peck");
		myFamily.replaceName("Cthulu", "Mysterion");
		myFamily.print();
	}
	
	public void testprintFlat() {
		AmoebaFamily myFamily = new AmoebaFamily("Steve");
		myFamily.addChild("Steve", "Jim");
		myFamily.addChild("Steve", "Cthulu");
		myFamily.addChild("Cthulu", "Josh Peck");
		myFamily.printFlat();
	}
	
	public void testprint() {
		AmoebaFamily myFamily = new AmoebaFamily("Steve");
		myFamily.addChild("Steve", "Jim");
		myFamily.addChild("Steve", "Cthulu");
		myFamily.addChild("Cthulu", "Josh Peck");
		myFamily.print();
	}
	
	public void testlongestNameLength() {
		AmoebaFamily myFamily = new AmoebaFamily("Steve");
		myFamily.addChild("Steve", "Jim");
		myFamily.addChild("Steve", "Cthulu");
		myFamily.addChild("Cthulu", "Josh Peck");
		assertTrue (myFamily.longestNameLength() == 9);
	}
	
	public void testlongestName() {
		AmoebaFamily myFamily = new AmoebaFamily("Steve");
		myFamily.addChild("Steve", "Jim");
		myFamily.addChild("Steve", "Cthulu");
		myFamily.addChild("Cthulu", "Josh Peck");
		assertTrue (myFamily.longestName() == "Josh Peck");
	}
	
	public void testsize() {
		AmoebaFamily myFamily = new AmoebaFamily("Steve");
		myFamily.addChild("Steve", "Jim");
		myFamily.addChild("Steve", "Cthulu");
		myFamily.addChild("Cthulu", "Josh Peck");
		assertTrue (myFamily.size() == 4);
	}
	
	public void testheight() {
		AmoebaFamily myFamily = new AmoebaFamily("Steve");
		myFamily.addChild("Steve", "Jim");
		myFamily.addChild("Steve", "Cthulu");
		assertTrue(myFamily.height() == 2);
		myFamily.addChild("Cthulu", "Josh Peck");
		assertTrue(myFamily.height() == 3);
	}
	
}
