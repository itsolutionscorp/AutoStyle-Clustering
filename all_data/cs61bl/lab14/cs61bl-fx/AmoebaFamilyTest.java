import junit.framework.TestCase;


public class AmoebaFamilyTest extends TestCase {

	public void testToLowerCase() {
		AmoebaFamily dad = new AmoebaFamily("Dad");
		dad.addChild("Dad", "sOn");
		dad.addChild("Dad", "dauGHTER");
		dad.addChild("Dad", "BAby");
		dad.makeNamesLowercase();
		System.out.println(dad.myRoot.myName);
		System.out.println(dad.myRoot.myChildren.toString());
	}
	
	public void testReplaceName() {
		AmoebaFamily dad = new AmoebaFamily("Dad");
		dad.addChild("Dad", "sOn");
		dad.addChild("Dad", "dauGHTER");
		dad.addChild("Dad", "BAby");
		dad.replaceName("Dad", "Bob");
		dad.replaceName("BAby", "cat");
		System.out.println(dad.myRoot.myName);
		System.out.println(dad.myRoot.myChildren.toString());
	}
	
	public void testPrintFlat() {
		AmoebaFamily dad = new AmoebaFamily("Dad");
		dad.addChild("Dad", "sOn");
		dad.addChild("Dad", "dauGHTER");
		dad.addChild("Dad", "BAby");
		dad.printFlat();
	}
	
	public void testPrint() {
		AmoebaFamily dad = new AmoebaFamily("Dad");
		dad.addChild("Dad", "sOn");
		dad.addChild("Dad", "dauGHTER");
		dad.addChild("Dad", "BAby");
		dad.addChild("sOn", "GRANDson");
		dad.addChild("dauGHTER", "rascal");
		dad.addChild("rascal", "trouble");
		dad.addChild("trouble", "moretrouble");
		dad.print();
	}
	
	public void testLongestName() {
		AmoebaFamily dad = new AmoebaFamily("Dad");
		dad.addChild("Dad", "sOn");
		dad.addChild("Dad", "dauGHTER");
		dad.addChild("Dad", "BAby");
		dad.addChild("sOn", "GRAND");
		assertTrue (dad.longestName().equals("dauGHTER"));
		dad.addChild("dauGHTER", "rascal");
		dad.addChild("rascal", "trouble");
		dad.addChild("trouble", "moretrouble");
		assertTrue (dad.longestName().equals("moretrouble"));
	}
	
	public void testSize() {
		AmoebaFamily dad = new AmoebaFamily("Dad");
		dad.addChild("Dad", "sOn");
		dad.addChild("Dad", "dauGHTER");
		dad.addChild("Dad", "BAby");
		dad.addChild("sOn", "GRANDson");
		assertTrue(dad.size() == 5);
		dad.addChild("dauGHTER", "rascal");
		dad.addChild("rascal", "trouble");
		dad.addChild("trouble", "moretrouble");
		assertTrue(dad.size() == 8);
	}
	
	public void testHeight() {
		AmoebaFamily dad = new AmoebaFamily("Dad");
		AmoebaFamily foreveralone = new AmoebaFamily("");
		assertTrue (foreveralone.height() == 1);
		dad.addChild("Dad", "sOn");
		dad.addChild("Dad", "dauGHTER");
		dad.addChild("Dad", "BAby");
		assertTrue (dad.height() == 2);
		dad.addChild("sOn", "GRANDson");
		dad.addChild("GRANDson", "GRANDGRANDson");
		dad.addChild("dauGHTER", "granddauGHTER");
		dad.addChild("dauGHTER", "GRANDgranddauGHTER");
		assertTrue(dad.height() == 4);
		dad.addChild("dauGHTER", "rascal");
		dad.addChild("rascal", "trouble");
		dad.addChild("trouble", "moretrouble");
		assertTrue(dad.height() == 5);
		
	}
}
