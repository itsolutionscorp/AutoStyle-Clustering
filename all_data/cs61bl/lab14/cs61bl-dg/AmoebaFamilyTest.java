import junit.framework.TestCase;

public class AmoebaFamilyTest extends TestCase {
	
	public void testMakeNamesLowercase() {
		
		AmoebaFamily testAmFam = new AmoebaFamily("Bill");
		testAmFam.addChild("Bill", "Connor");
		testAmFam.addChild("Bill", "Samantha");
		testAmFam.addChild("Connor", "James");
		AmoebaFamily testAmFam2 = new AmoebaFamily("Andrew");
		testAmFam2.addChild("Andrew", "Connor");
		testAmFam2.addChild("Andrew", "Samantha");
		testAmFam2.addChild("Connor", "James");
		testAmFam2.addChild("Connor", "Christina");
		testAmFam.makeNamesLowercase();
		testAmFam2.makeNamesLowercase();
		assertEquals(testAmFam.longestName(),"samantha");
		assertEquals(testAmFam2.longestName(), "christina");
	}

	public void testReplaceName() {
		AmoebaFamily testAmFam = new AmoebaFamily("Bill");
		testAmFam.addChild("Bill", "Connor");
		testAmFam.addChild("Bill", "Samantha");
		testAmFam.addChild("Connor", "James");
		AmoebaFamily testAmFam2 = new AmoebaFamily("Andrew");
		testAmFam2.addChild("Andrew", "Greyson");
		testAmFam2.addChild("Andrew", "Samantha");
		testAmFam2.addChild("Greyson", "James");
		testAmFam2.addChild("Connor", "Christina");
		testAmFam.replaceName("Samantha","Christopher");
		testAmFam2.replaceName("Samantha","Bartholomew");
		assertEquals(testAmFam.longestName(),"Christopher");
		assertEquals(testAmFam2.longestName(), "Bartholomew");
		testAmFam.replaceName("Christopher","Jay");
		testAmFam2.replaceName("Bartholomew", "Mario");
		assertEquals(testAmFam.longestName(),"Connor");
		assertEquals(testAmFam2.longestName(), "Greyson");
	}
	
	public void testLongestName() {
		AmoebaFamily testAmFam = new AmoebaFamily("Bill");
		testAmFam.addChild("Bill", "Connor");
		testAmFam.addChild("Bill", "Samantha");
		testAmFam.addChild("Connor", "James");
		AmoebaFamily testAmFam2 = new AmoebaFamily("Andrew");
		testAmFam2.addChild("Andrew", "Connor");
		testAmFam2.addChild("Andrew", "Samantha");
		testAmFam2.addChild("Connor", "James");
		testAmFam2.addChild("Connor", "Christina");
		assertEquals(testAmFam.longestName(),"Samantha");
		assertEquals(testAmFam2.longestName(), "Christina");
	}
	
	public void testSize() { 
		AmoebaFamily testAmFam = new AmoebaFamily("Bill");
		testAmFam.addChild("Bill", "Connor");
		testAmFam.addChild("Bill", "Samantha");
		testAmFam.addChild("Connor", "James");
		AmoebaFamily testAmFam2 = new AmoebaFamily("Andrew");
		testAmFam2.addChild("Andrew", "Connor");
		testAmFam2.addChild("Andrew", "Samantha");
		testAmFam2.addChild("Connor", "James");
		testAmFam2.addChild("Connor", "Christina");
		assertEquals(testAmFam.size(),4);
		assertEquals(testAmFam2.size(),5);
		testAmFam.addChild("Connor", "Phyllis");
		testAmFam2.addChild("Andrew", "Peter");
		assertEquals(testAmFam.size(),5);
		assertEquals(testAmFam2.size(),6);
	}
	
	public void testHeight() {
		AmoebaFamily testAmFam = new AmoebaFamily("Bill");
		testAmFam.addChild("Bill", "Connor");
		testAmFam.addChild("Bill", "Samantha");
		testAmFam.addChild("Connor", "James");
		testAmFam.print();
		AmoebaFamily testAmFam2 = new AmoebaFamily("Andrew");
		testAmFam2.addChild("Andrew", "Connor");
		testAmFam2.addChild("Andrew", "Samantha");
		testAmFam2.addChild("Connor", "James");
		testAmFam2.addChild("Connor", "Christina");
		testAmFam2.print();
		assertEquals(testAmFam.height(),3);
		assertEquals(testAmFam2.height(),3);
	}
	
	
}
