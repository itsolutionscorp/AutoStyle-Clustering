import static org.junit.Assert.*;

import org.junit.Test;


public class AmoebaFamilyTest {

	@Test
	public void test() {
		AmoebaFamily fam = new AmoebaFamily("Dad");
		
		fam.addChild("Dad", "child1");
		fam.addChild("Dad", "child2");
		fam.addChild("Dad", "child3");
		fam.addChild("Dad", "child4");
		fam.addChild("child1", "child11");
		fam.addChild("child1", "child12");
		fam.addChild("child2", "child21");
		fam.addChild("child2", "child22");
		fam.addChild("child22", "child221");
		fam.print();
		assertTrue(fam.longestName().equals("child11"));
		//fam.printFlat();
		assertTrue (fam.height() == 4);
		assertTrue (fam.size() == 10);
	}

}
