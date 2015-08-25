import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DictionaryTest {
	// CHANGE THIGNS BACK TO PRIVATE
	@Test
	public void testAddingEmpty() {
		Dictionary test = new Dictionary();
		test.addDefinition("wug", "This is a wug");
		assertTrue(test.myStartingLetters.containsKey('w'));
		assertTrue(test.myStartingLetters.get('w').myNextLetters
				.containsKey('u'));

		test.addDefinition("wag", "This is a wag, not a wug");
		test.addDefinition("wig", "Something you put on your hair");
		test.addDefinition("a", "a thing");
		test.addDefinition("aa", "aa thing");
		assertEquals("a thing", test.lookupDefinition("a"));
		assertEquals("aa thing", test.lookupDefinition("aa"));
		assertEquals("This is a wug", test.lookupDefinition("wug"));
		assertEquals("This is a wag, not a wug", test.lookupDefinition("wag"));
		assertEquals("Something you put on your hair",
				test.lookupDefinition("wig"));
	}

	@Test
	public void testAddingEmpty2() {
		Dictionary test = new Dictionary();
		// test.addDefinition("", "null definition"); //This throws an error
		// test.lookupDefinition("");
	}

}
