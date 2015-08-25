import javax.swing.text.html.HTMLDocument.Iterator;

import junit.framework.TestCase;


public class AmoebaFamilyTest extends TestCase {
	
	public void testmakeNameLowerCase() {
		AmoebaFamily family = new AmoebaFamily("AMOS MCCOY");
		family.addChild("AMOS MCCOY", "MOM/DAD");
		family.addChild("AMOS MCCOY", "auntie");
		family.addChild("MOM/DAD", "ME");
		family.addChild("MOM/DAD", "Fred");
		family.addChild("MOM/DAD", "Wilma");
		family.addChild("ME", "MIKE");
		family.addChild("ME", "HOMER");
		family.addChild("ME", "MARGE");
		family.addChild("MIKE", "BART");
		family.addChild("MIKE", "LISA");
		family.addChild("MARGE", "BILL");
		family.addChild("MARGE", "Hilary");
		family.makeNamesLowercase();
		family.print();
		System.out.println();
	}
	
	public void testreplacename() {
		AmoebaFamily family = new AmoebaFamily("AMOS MCCOY");
		family.addChild("AMOS MCCOY", "MOM/DAD");
		family.addChild("AMOS MCCOY", "auntie");
		family.addChild("MOM/DAD", "ME");
		family.addChild("MOM/DAD", "Fred");
		family.addChild("MOM/DAD", "Wilma");
		family.addChild("ME", "MIKE");
		family.addChild("ME", "HOMER");
		family.addChild("ME", "MARGE");
		family.addChild("MIKE", "BART");
		family.addChild("MIKE", "LISA");
		family.addChild("MARGE", "BILL");
		family.addChild("MARGE", "Hilary");
		family.replaceName("AMOS MCCOY", "boss");
		family.replaceName("auntie", "asfaefaewf");
		family.replaceName("ME", "dude");
		family.replaceName("MARGE", "mmmmmmm");
		family.makeNamesLowercase();
		family.print();
		System.out.println();
		
	}
	
	public void testPrintflat() {
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		family.printFlat();
		System.out.println();
		family.addChild("Amos McCoy", "mom/dad");
		family.printFlat();
		System.out.println();
		family.addChild("Amos McCoy", "auntie");
		family.addChild("mom/dad", "me");
		family.addChild("mom/dad", "Fred");
		family.addChild("mom/dad", "Wilma");
		family.addChild("me", "Mike");
		family.addChild("me", "Homer");
		family.printFlat();
		System.out.println();
		family.addChild("me", "MARGE");
		family.addChild("Mike", "Bart");
		family.addChild("Mike", "Lisa");
		family.addChild("MARGE", "Bill");
		family.addChild("MARGE", "Hilary");
		family.printFlat();
		System.out.println();
	}
	
	public void testlongestname() {
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		family.addChild("Amos McCoy", "mom/dad");
		family.addChild("Amos McCoy", "auntie");
		family.addChild("mom/dad", "me");
		family.addChild("mom/dad", "Fred");
		family.addChild("mom/dad", "Wilma");
		family.addChild("me", "Mike");
		family.addChild("me", "Homer");
		family.addChild("me", "MARGE");
		family.addChild("Mike", "Bart");
		family.addChild("Mike", "Lisa");
		family.addChild("MARGE", "Bill");
		family.addChild("MARGE", "Hilary1234567890");
		assertEquals(family.longestNameLength(), "Hilary1234567890".length());
		assertEquals(family.longestName(), "Hilary1234567890");
		
		
	}
	
	 public void testsize () {
		 AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		 assertEquals(family.size(), 1);
			family.addChild("Amos McCoy", "mom/dad");
			assertEquals(family.size(), 2);
			family.addChild("Amos McCoy", "auntie");
			assertEquals(family.size(), 3);
			family.addChild("mom/dad", "me");
			assertEquals(family.size(), 4);
			family.addChild("mom/dad", "Fred");
			assertEquals(family.size(), 5);
			family.addChild("mom/dad", "Wilma");
			assertEquals(family.size(), 6);
			family.addChild("me", "Mike");
			assertEquals(family.size(), 7);
			family.addChild("me", "Homer");
			assertEquals(family.size(), 8);
			family.addChild("me", "Marge");
			assertEquals(family.size(), 9);
			family.addChild("Mike", "Bart");
			assertEquals(family.size(), 10);
			family.addChild("Mike", "Lisa");
			assertEquals(family.size(), 11);
			family.addChild("Marge", "Bill");
			assertEquals(family.size(), 12);
			family.addChild("Marge", "Hilary");
			assertEquals(family.size(), 13);
	 }
	 
	 public void testheight () {
		 AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		 assertEquals(family.height(), 1);
			family.addChild("Amos McCoy", "mom/dad");
			assertEquals(family.height(), 2);
			family.addChild("Amos McCoy", "auntie");
			assertEquals(family.height(), 2);
			family.addChild("mom/dad", "me");
			assertEquals(family.height(), 3);
			family.addChild("mom/dad", "Fred");
			assertEquals(family.height(), 3);
			family.addChild("mom/dad", "Wilma");
			assertEquals(family.height(), 3);
			family.addChild("me", "Mike");
			assertEquals(family.height(), 4);
			family.addChild("me", "Homer");
			assertEquals(family.height(), 4);
			family.addChild("me", "Marge");
			assertEquals(family.height(), 4);
			family.addChild("Mike", "Bart");
			assertEquals(family.height(), 5);
			family.addChild("Mike", "Lisa");
			assertEquals(family.height(), 5);
			family.addChild("Marge", "Bill");
			family.addChild("Marge", "Hilary");
			assertEquals(family.height(), 5);
	 }
	 
}
