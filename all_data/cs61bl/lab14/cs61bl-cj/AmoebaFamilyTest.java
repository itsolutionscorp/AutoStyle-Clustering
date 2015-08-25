import static org.junit.Assert.*;

import org.junit.Test;


public class AmoebaFamilyTest {

	@Test
	public void testSize() {
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		assertTrue(family.size() == 1);
		
		family.addChild("Amos McCoy", "mom/dad");
		family.addChild("Amos McCoy", "auntie");
		assertTrue(family.size() == 3);
		
		family.addChild("mom/dad", "me");
		family.addChild("mom/dad", "Fred");
		family.addChild("mom/dad", "Wilma");
		assertTrue(family.size() == 6);
		
		family.addChild("me", "Mike");
		family.addChild("me", "Homer");
		family.addChild("Mike", "Marge");
		assertTrue(family.size() == 9);
		
		family.addChild("Marge", "Bill");
		family.addChild("Bill", "Hilary");
		assertTrue(family.size() == 11);
	}
	
	@Test
	public void testHeight() {
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		// the height of a leaf is 1
		assertTrue(family.height() == 1);

		family.addChild("Amos McCoy", "mom/dad");
		family.addChild("Amos McCoy", "auntie");
		assertTrue(family.height() == 2);
		
		family.addChild("mom/dad", "me");
		family.addChild("mom/dad", "Fred");
		family.addChild("mom/dad", "Wilma");
		assertTrue(family.height() == 3);
		
		family.addChild("me", "Mikeeeeeeeeeee");
		family.addChild("me", "Homer");
		family.addChild("me", "Marge");
		assertTrue(family.height() == 4);
		
		family.addChild("Fred", "Bart");
		family.addChild("Fred", "Lisa");
		// me and Fred are sibling, height shouldn't change
		assertTrue(family.height() == 4); 
		
		family.addChild("Marge", "Bill");
		family.addChild("Marge", "Hilary");
		assertTrue(family.height() == 5); 
		
		// the height of a null tree should be 0
		family.myRoot = null;
		assertTrue(family.height() == 0); 
	}
	
	@Test
	public void testLongestName() {
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		family.addChild("Amos McCoy", "mom/dad");
		family.addChild("Amos McCoy", "auntie");	
		family.addChild("mom/dad", "me");
		family.addChild("mom/dad", "Fred");
		family.addChild("me", "Mikeeeeeeeeeee");
		family.addChild("me", "Homer");
		family.addChild("me", "Marge");
		assertEquals(family.longestName(),"Mikeeeeeeeeeee");
		family.addChild("mom/dad", "Wilmaaaaaaaaaaaaaaaaaaaa");
		assertEquals(family.longestName(),"Wilmaaaaaaaaaaaaaaaaaaaa");
	}
	
	@Test
	public void testBusiest() {
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		family.addChild("Amos McCoy", "mom/dad");
		family.addChild("Amos McCoy", "auntie");	
		family.addChild("mom/dad", "me");
		family.addChild("me", "Mikeeeeeeeeeee");
		family.addChild("me", "Homer");
		family.addChild("me", "Marge");
		family.addChild("auntie", "Fred");
		assertEquals(family.busiest(),"me");
		family.addChild("auntie", "dennis");
		family.addChild("auntie", "kat");
		family.addChild("auntie", "john");
		assertEquals(family.busiest(),"auntie");
	}

}
