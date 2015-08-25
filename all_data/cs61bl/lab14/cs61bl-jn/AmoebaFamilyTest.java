import static org.junit.Assert.*;

import org.junit.Test;


public class AmoebaFamilyTest {

	@Test
	public void test() {
		AmoebaFamily family = new AmoebaFamily("Ancient");
		family.addChild("Ancient", "old");
		family.addChild("Ancient", "Old2");
		family.addChild("old", "present");
		family.addChild("old", "present2");
		family.addChild("old", "present4");
		family.addChild("present", "future");
		family.addChild("present", "Future2");
		family.addChild("present", "future3");
		family.addChild("future", "Dead");
		family.addChild("future", "Dead1");
		family.addChild("future3", "Dead3");
		family.addChild("future3", "Dead31");
		family.print();
		family.makeNamesLowercase();
		family.print();
		family.replaceName("dead", "reallydead");
		family.replaceName("ancient", "Eldest");
		family.print();
		assertEquals(family.myRoot.myName,"Eldest");
		assertEquals(family.longestNameLength(),10);
		assertEquals(family.longestName(), "reallydead");
		assertEquals(family.height(),5);
		assertEquals(family.size(),13);
		family.addChild("reallydead", "jk");
		assertEquals(family.height(),6);
		family.addChild("reallydead", "qwertyuioplkjhgfdsa");
		assertEquals(family.longestNameLength(),19);
		assertEquals(family.longestName(), "qwertyuioplkjhgfdsa");
		assertEquals(family.size(),15);
		family.print();
		
	}

}
