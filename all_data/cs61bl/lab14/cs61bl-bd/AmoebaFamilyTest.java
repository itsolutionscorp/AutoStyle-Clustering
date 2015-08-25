import static org.junit.Assert.*;

import org.junit.Test;


public class AmoebaFamilyTest {
	
	@Test
	public void testMakeNamesLowercase() {
		AmoebaFamily family = new AmoebaFamily("Jenny");
		family.addChild("Jenny", "Jennifer");
		family.addChild("Jenny", "Tiffany");
		family.addChild("Jenny", "Nancy");
		family.addChild("Jennifer", "SPROCKET");
		family.makeNamesLowercase();
		assertEquals("jenny", family.myRoot.myName);
		for (int i = 0; i < family.myRoot.myChildren.size(); i++) {
			if (i == 0) {
				assertEquals("jennifer", family.myRoot.myChildren.get(i).myName);
			}
			if (i == 1) {
				assertEquals("tiffany", family.myRoot.myChildren.get(i).myName);
			}
			if (i == 2) {
				assertEquals("nancy", family.myRoot.myChildren.get(i).myName);
			}
		}
		assertEquals("sprocket", family.myRoot.myChildren.get(0).myChildren.get(0).myName);
	}

	@Test
	public void testReplaceName() {
		AmoebaFamily family = new AmoebaFamily("Jenny");
		family.addChild("Jenny", "Jennifer");
		family.addChild("Jenny", "Tiffany");
		family.addChild("Jenny", "Nancy");
		family.addChild("Jennifer", "SPROCKET");
		family.replaceName("Jenny", "Bruce");
		assertEquals("Bruce", family.myRoot.myName);
		family.replaceName("SPROCKET", "Sadie");
		assertEquals("Sadie", family.myRoot.myChildren.get(0).myChildren.get(0).myName);
	}
	
	@Test
	public void testLongestName() {
		AmoebaFamily family = new AmoebaFamily("Jenny");
		family.addChild("Jenny", "Jennifer");
		family.addChild("Jenny", "Tiffany");
		family.addChild("Jenny", "Nancy");
		family.addChild("Jennifer", "Sadie");
		assertEquals("Jennifer", family.longestName());
	}
	
	@Test
	public void testSize() {
		AmoebaFamily family = new AmoebaFamily("Jenny");
		family.addChild("Jenny", "Jennifer");
		family.addChild("Jenny", "Tiffany");
		family.addChild("Jenny", "Nancy");
		family.addChild("Jennifer", "Sadie");
		assertEquals(5, family.size());
	}
	
	@Test
	public void testHeight() {
		AmoebaFamily family = new AmoebaFamily("Jenny");
		family.addChild("Jenny", "Jennifer");
		family.addChild("Jenny", "Tiffany");
		family.addChild("Jenny", "Nancy");
		family.addChild("Jennifer", "Sadie");
		assertEquals(3, family.height());
	}
}
