import static org.junit.Assert.*;

import org.junit.Test;


public class AmoebaFamilyTest {

	@Test
	public void test() {
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
		assertEquals(family.height(), 5);
		AmoebaFamily jeffreyFamily = new AmoebaFamily("Jeffrey");
		assertEquals(jeffreyFamily.height(), 1);
		jeffreyFamily.addChild("Jeffrey", "Margaret");
		jeffreyFamily.addChild("Jeffrey", "Wug");
		jeffreyFamily.addChild("Margaret", "Pangolin");
		assertEquals(jeffreyFamily.height(), 3);
		jeffreyFamily.addChild("Pangolin", "Penguin");
		assertEquals(jeffreyFamily.height(), 4);
	}
	
	@Test
	public void size() {
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
		assertEquals(family.size(), 13);
		
		AmoebaFamily jeffrey = new AmoebaFamily("Jeffrey Jeffrey");
		jeffrey.myRoot = null;
		assertEquals(jeffrey.size(), 0);
	}

}
