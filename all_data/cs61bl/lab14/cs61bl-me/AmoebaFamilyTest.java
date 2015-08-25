import static org.junit.Assert.*;

import org.junit.Test;


public class AmoebaFamilyTest {

	@Test
	public void test() {
		AmoebaFamily test = new AmoebaFamily("Grandpap");
		test.addChild("Grandpap", "Dad");
		test.addChild("Grandpap", "Uncle");
		test.addChild("Grandpap", "Creepy Uncle");
		test.addChild("Dad", "me");
		assertEquals(3, test.height());
		test.addChild("me", "child");
		assertEquals(4, test.height());
		
	}

}
