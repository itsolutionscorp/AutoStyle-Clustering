import static org.junit.Assert.*;

import org.junit.Test;

public class AmoebaFamilyTest {

	@Test
	public void test() {
		AmoebaFamily family = new AmoebaFamily("Matt");
		family.addChild("Matt", "Stefan");
		family.addChild("Matt", "Keith");
		family.addChild("Stefan", "Gerald");
		family.addChild("Stefan", "Robin");
		family.addChild("Keith", "Stephen");
		family.addChild("Stephen", "Drew");
		family.makeNamesLowercase();
		family.replaceName("robin", "josh;klj;jjkl;jkl");
		//family.printFlat();
		family.print();
		//System.out.println(family.longestName());
		System.out.println(family.size());
		System.out.println(family.height());
	}

}
