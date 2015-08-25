import static org.junit.Assert.*;

import org.junit.Test;


public class AmoebaFamilyTest {

	@Test
	public void testHeight1() {
		AmoebaFamily family = new AmoebaFamily("Amos Mccoy");
		family.addChild("Amos Mccoy", "mom/dad");
		family.addChild("Amos Mccoy", "auntie");
		family.addChild("mom/dad", "me");
		family.addChild("mom/dad", "Fred");
		family.addChild("mom/dad", "Wilma");
		family.addChild("me", "Mike");
		family.addChild("me", "Homer");
		family.addChild("me", "Marge");
		family.addChild("Mike", "Bart");
		family.addChild("Mike", "Lisa");
		family.addChild("Marge", "Bill");
		family.addChild("Marge", "Hillary");
		assertEquals(5, family.height());
		family.addChild("Bill", "Jimmy");
		assertEquals(6, family.height());
		family.addChild("Bill", "John");
		family.addChild("Marge", "Uncle Lewis");
		assertEquals(6, family.height());
	}
	
	@Test
	public void testHeight2(){
		AmoebaFamily family = new AmoebaFamily("Null Fam");
		assertEquals(1, family.height());
		family.myRoot = null; //reset
		assertEquals(0, family.height());
	}
	

}
