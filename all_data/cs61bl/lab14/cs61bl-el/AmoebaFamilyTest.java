import static org.junit.Assert.*;

import org.junit.Test;

public class AmoebaFamilyTest {

	@Test
	public void test() {
		AmoebaFamily fam = new AmoebaFamily("theGreat");
		fam.addChild("theGreat", "GrandA");
		fam.addChild("theGreat", "GrandB");
		fam.addChild("theGreat", "GrandC");
		fam.addChild("GrandC", "ParentC");
		fam.addChild("GrandB", "ParentB");
		fam.addChild("theGreat", "GrandD");
		fam.addChild("theFam", "dd");
		fam.addChild("GrandD", "ParentD");
		fam.addChild("ParentD", "ChildD");
		fam.printFlat();
		System.out.println("-------");
		fam.makeNamesLowercase();
		fam.print();
		System.out.println("-------");
		fam.replaceName("childd", "me");
		fam.print();
		assertEquals(9, fam.size());
	}

	@Test
	public void testHeight() {
		// problem with original code: added 1 for every child in the myChildren
		AmoebaFamily fam = new AmoebaFamily("theGreat");
		fam.addChild("theGreat", "GrandA");
		fam.addChild("theGreat", "GrandB");
		fam.addChild("theGreat", "GrandC");
		assertEquals(2, fam.height());
		fam.addChild("GrandC", "ParentC");
		fam.addChild("GrandB", "ParentB");
		fam.addChild("theGreat", "GrandD");
		fam.addChild("theFam", "dd");
		fam.addChild("GrandD", "ParentD");
		fam.addChild("ParentD", "ChildD");
		fam.print();
		assertEquals(9, fam.size());
		assertEquals(4, fam.height());
	}

}
