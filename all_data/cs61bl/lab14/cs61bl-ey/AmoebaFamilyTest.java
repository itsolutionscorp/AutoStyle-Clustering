import junit.framework.TestCase;


public class AmoebaFamilyTest extends TestCase {

		public void testPrintFlat(){
//			System.out.println("printFlat()/ makeNamesLowercase()");
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
			System.out.println("Here's the family:");
			family.makeNamesLowercase();
			family.printFlat();
			System.out.println();
		}
		
		public static void testReplaceName(){
//			System.out.println("replaceName() / prettyPrint()");
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
			System.out.println("Here's the family:");
			
			family.replaceName("Hilary", "Lynda");
			family.replaceName("Lisa", "Jen");
			family.replaceName("Bart", "Parth");
			family.replaceName("Bill", "Chucky");
			family.replaceName("Amos McCoy", "pizza");
			family.print();
//			System.out.println();
			
		}
		
		public void testLongest(){
//		System.out.println("longestName()");
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
//		System.out.println("Here's the longest name in the family:");
		assertTrue(family.longestName().equals("Amos McCoy"));
		
//		System.out.println(family.longestName());
		family.replaceName("Lisa", "Millennium Falcon");
//		family.print();
		assertTrue(family.longestName().equals("Millennium Falcon"));
		assertFalse(!family.longestName().equals("Millennium Falcon"));
//		System.out.println(family.longestName());
		
		}
		
		
		public void testSize(){
//			System.out.println("size()");
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
			System.out.println("Family is all here! :)");
			assertFalse(family.size() == 55);
			assertTrue(family.size() == 13);
//			System.out.println(family.size());
			family.addChild("me", "Lynda");
			family.addChild("Mike", "Parth");
			family.addChild("mom/dad", "Jenn");
			assertTrue(family.size() == 16);
			assertTrue(family.height() == 5);
//			System.out.println(family.height());
		}
		
}