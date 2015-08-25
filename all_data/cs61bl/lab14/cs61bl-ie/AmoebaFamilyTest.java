import static org.junit.Assert.*;

import org.junit.Test;


public class AmoebaFamilyTest {

	@Test
	public void testHeight() {
		System.out.println("height");
		AmoebaFamily family = new AmoebaFamily("Anna");
		family.addChild("Anna", "b");
		family.addChild("Anna", "c");
		family.addChild("Anna", "d");
		family.addChild("b", "bobbi");
		family.addChild("bobbi", "billy");
		System.out.println(family.height());
		System.out.println("");
		AmoebaFamily fam = new AmoebaFamily("Kang");
		fam.addChild("Kang", "x");
		fam.addChild("Kang", "y");
		fam.addChild("Kang", "z");
		fam.addChild("z", "ziggy");
		System.out.println(fam.height());
	}

}
