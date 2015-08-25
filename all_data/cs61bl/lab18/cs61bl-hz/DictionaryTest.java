import static org.junit.Assert.*;

import org.junit.Test;

public class DictionaryTest {

	@Test
	public void test() {
		Dictionary d = new Dictionary();
		d.addDefinition("wug", "It's a fucking wug.");
		System.out.println((d.lookupDefinition("wug")));
		d.addDefinition("Winguardium Leviosa", "The greatest spell ever.");
		System.out.println((d.lookupDefinition("Winguardium Leviosa")));
		d.addDefinition("wut", "topkek");
		System.out.println((d.lookupDefinition("wut")));
		d.addDefinition("lol", "Laugh out loud");
		System.out.println((d.lookupDefinition("lol")));
		d.addDefinition("lel", "Korean for Laugh out loud");
		System.out.println((d.lookupDefinition("lel")));
		d.addDefinition("lek", "Turkish food");
		System.out.println((d.lookupDefinition("lek")));
		System.out.println((d.lookupDefinition("lel")));
		System.out.println((d.lookupDefinition("lol")));
		System.out.println((d.lookupDefinition("DU MA!!!!")));
	}

}
