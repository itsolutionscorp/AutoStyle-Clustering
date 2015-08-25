import junit.framework.TestCase;


public class AmoebaFamilyTest extends TestCase {

		public void testPrintFlat(){
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
			family.printFlat();
			System.out.println();
		}
		
		public static void testReplaceName(){
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
			family.replaceName("Mike", "Lynda");
			family.replaceName("Lynda", "Jen");
			family.replaceName("me", "Parth");
			family.replaceName("mom/dad", "Chucky");
			family.replaceName("Chucky", "pizza");
			family.print();

			
		}
		
		public void testLongest(){

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
		assertTrue(family.longestName().equals("Amos McCoy"));
		family.replaceName("Lisa", "Darth Vader");
//		family.print();
		assertTrue(family.longestName().equals("Darth Vader"));
		assertFalse(!family.longestName().equals("Darth Vader"));
//		System.out.println(family.longestName());
		family.addChild("Mike", "hellomynameisthelongestname");
		assertFalse(!family.longestName().equals("hellomynameisthelongestname"));
		
		}
		
		
		public void testSize(){

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
			family.addChild("mom/dad", "Lynda");
			family.addChild("Wilma", "Jenn");
			family.addChild("me", "Joe");
			assertTrue(family.size() == 16);
//			System.out.println("height :) " + family.height());
//			family.print();
			assertTrue(family.height() == 6);
			family.addChild("Lisa", "Alex");
			family.addChild("Lisa", "Milhouse");
			assertTrue(family.size()== 18);
			assertTrue(family.height() == 7);
			family.addChild("Alex", "Mary");
			assertTrue(family.size() == 19);
			assertTrue(family.height() == 8);
			

		}
		
}
