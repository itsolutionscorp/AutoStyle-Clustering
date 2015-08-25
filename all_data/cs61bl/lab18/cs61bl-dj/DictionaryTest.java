import static org.junit.Assert.*;

import org.junit.Test;


public class DictionaryTest {

	@Test
	public void testDictionary() {
		Dictionary d = new Dictionary();
		d.addDefinition("cat", "a feline animal");
		d.addDefinition("coma", "a vegetative state");
		d.addDefinition("burrito", "something that tastes good");
		System.out.println(d.lookupDefinition("cat"));
		System.out.println(d.lookupDefinition("coma"));
		System.out.println(d.lookupDefinition("burrito"));
		
	}

}
