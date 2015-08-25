import junit.framework.TestCase;

import org.junit.Test;

public class AmoebaFamilyTest extends TestCase {

	@Test
	public void testLowerCase() {
		AmoebaFamily testFamily = new AmoebaFamily("Amos McCoy");
		testFamily.addChild("Amos McCoy", "mom/dad");
		testFamily.addChild("Amos McCoy", "auntie");
		testFamily.addChild("mom/dad", "me");
		testFamily.addChild("mom/dad", "Fred");
		testFamily.addChild("mom/dad", "Wilma");
		testFamily.addChild("me", "Mike");
		testFamily.addChild("me", "Homer");
		testFamily.addChild("me", "Marge");
		testFamily.addChild("Mike", "Bart");
		testFamily.addChild("Mike", "Lisa");
		testFamily.addChild("Marge", "Bill");
		testFamily.addChild("Marge", "Hilary");
		testFamily.makeNamesLowercase();
		testFamily.printFlat();
	}

	@Test
	public void testReplace() {
		AmoebaFamily testFamily = new AmoebaFamily("Amos McCoy");
		testFamily.addChild("Amos McCoy", "mom/dad");
		testFamily.addChild("Amos McCoy", "auntie");
		testFamily.addChild("mom/dad", "me");
		testFamily.addChild("mom/dad", "Fred");
		testFamily.addChild("mom/dad", "Wilma");
		testFamily.addChild("me", "Mike");
		testFamily.addChild("me", "Homer");
		testFamily.addChild("me", "Marge");
		testFamily.addChild("Mike", "Bart");
		testFamily.addChild("Mike", "Lisa");
		testFamily.addChild("Marge", "Bill");
		testFamily.addChild("Marge", "Hilary");
		testFamily.replaceName("me", "you");
		testFamily.replaceName("Amos McCoy", "Colleen McCoy");
		testFamily.replaceName("Hilary", "Monica");
		testFamily.printFlat();
	}

	@Test
	public void testPrint() {
		AmoebaFamily testFamily = new AmoebaFamily("Amos McCoy");
		testFamily.addChild("Amos McCoy", "mom/dad");
		testFamily.addChild("Amos McCoy", "auntie");
		testFamily.addChild("mom/dad", "me");
		testFamily.addChild("mom/dad", "Fred");
		testFamily.addChild("mom/dad", "Wilma");
		testFamily.addChild("me", "Mike");
		testFamily.addChild("me", "Homer");
		testFamily.addChild("me", "Marge");
		testFamily.addChild("Mike", "Bart");
		testFamily.addChild("Mike", "Lisa");
		testFamily.addChild("Marge", "Bill");
		testFamily.addChild("Marge", "Hilary");
		testFamily.print();
	}

	@Test
	public void testLonestName() {
		AmoebaFamily testFamily = new AmoebaFamily("Amos McCoy");
		testFamily.addChild("Amos McCoy", "mom/dad");
		testFamily.addChild("Amos McCoy", "auntie");
		testFamily.addChild("mom/dad", "me");
		testFamily.addChild("mom/dad", "Fred");
		testFamily.addChild("mom/dad", "Wilma");
		testFamily.addChild("me", "Mike");
		testFamily.addChild("me", "Homer");
		testFamily.addChild("me", "Marge");
		testFamily.addChild("Mike", "Bart");
		testFamily.addChild("Mike", "Lisa");
		testFamily.addChild("Marge", "Bill");
		testFamily.addChild("Marge", "Hilary");
		assertEquals("Amos McCoy", testFamily.longestName());
		testFamily.addChild("Marge", "SOMEHELLALONGASSNAME");
		assertFalse("Amos McCoy".equals(testFamily.longestName()));
		assertEquals("SOMEHELLALONGASSNAME", testFamily.longestName());
		testFamily.replaceName("me", "SOMEHELLALONGASSNAME_EVENLONGER");
		assertEquals("SOMEHELLALONGASSNAME_EVENLONGER",
				testFamily.longestName());

	}

	@Test
	public void testSize() {
		AmoebaFamily testFamily = new AmoebaFamily("Amos McCoy");
		assertEquals(1, testFamily.size());
		testFamily.addChild("Amos McCoy", "mom/dad");
		assertEquals(2, testFamily.size());
		testFamily.addChild("Amos McCoy", "auntie");
		assertEquals(3, testFamily.size());
		testFamily.addChild("mom/dad", "me");
		assertEquals(4, testFamily.size());
		testFamily.addChild("mom/dad", "Fred");
		assertEquals(5, testFamily.size());
		testFamily.addChild("mom/dad", "Wilma");
		assertEquals(6, testFamily.size());
		testFamily.addChild("me", "Mike");
		testFamily.addChild("me", "Homer");
		testFamily.addChild("me", "Marge");
		assertEquals(9, testFamily.size());
		testFamily.addChild("Mike", "Bart");
		testFamily.addChild("Mike", "Lisa");
		testFamily.addChild("Marge", "Bill");
		testFamily.addChild("Marge", "Hilary");
		assertEquals(13, testFamily.size());
	}

	// @Test
	// public void testNull() { //don't need null case tests
	// AmoebaFamily testFamily = new AmoebaFamily(null);
	// testFamily.makeNamesLowercase();
	// testFamily.replaceName("Hilary", "Monica");
	//
	// }
	//

	@Test
	public void testHeight() {
		AmoebaFamily testFamily = new AmoebaFamily("Amos McCoy");
		assertEquals(1, testFamily.height()); // Correct Behavior for both
		testFamily.addChild("Amos McCoy", "c1");
		assertEquals(2, testFamily.height()); // Correct Behavior for both
		testFamily.addChild("Amos McCoy", "c2");
		testFamily.addChild("Amos McCoy", "c3");
		testFamily.addChild("Amos McCoy", "Fred");
		assertEquals(2, testFamily.height()); // Buggy version incorrectly
												// returned 5
		testFamily.addChild("c1", "d1");
		testFamily.addChild("d1", "e1");
		testFamily.addChild("c2", "d2");
		assertEquals(4, testFamily.height());

	}
}