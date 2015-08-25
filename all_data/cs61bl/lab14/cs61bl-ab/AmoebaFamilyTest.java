import static org.junit.Assert.*;

import org.junit.Test;


public class AmoebaFamilyTest {

	@Test
	public void testheight() {
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		assertTrue(family.height()==1);
		family.addChild("Amos McCoy", "mom/dad");
		family.addChild("Amos McCoy", "auntie");
		// test when there is sibling
		assertTrue(family.height()==2);
		// test when the tree is not balanced
		family.addChild("mom/dad", "me");
		family.addChild("me", "Mike");
		assertTrue(family.height()==4);
	}

}
