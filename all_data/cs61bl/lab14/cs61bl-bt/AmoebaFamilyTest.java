import junit.framework.TestCase;

public class AmoebaFamilyTest extends TestCase {

		public void testHeight(){
			AmoebaFamily family = new AmoebaFamily("Grandfather");
			family.addChild("Grandfather", "mom/dad");
			family.addChild("Grandfather", "uncle");
			family.addChild("mom/dad", "me");
			family.addChild("mom/dad", "Jennifer");
			family.addChild("mom/dad", "Shinwoo");
			family.addChild("me", "Red");
			family.addChild("me", "Orange");
			family.addChild("me", "Yellow");
			family.addChild("Jennifer", "Part");
			family.addChild("Shinwoo", "Mona");
			family.addChild("Part", "Phil");
			family.addChild("Part", "Clinton");
			family.addChild("Clinton", "Monica");
			System.out.println("Here's the family:");
			//family.print();
			assertFalse(family.height() == 11);
			assertEquals(6, family.height());
			
			AmoebaFamily family2 = new AmoebaFamily("Grandmother");
			family2.addChild("Grandmother", "mother/father");
			family2.addChild("Grandmother", "uncle");
			family2.addChild("mother/father", "me");
			family2.addChild("mother/father", "Sister");
			family2.addChild("mother/father", "Brother");
			family2.addChild("me", "Daughter");
			family2.addChild("Sister", "Niece");
			family2.addChild("Niece", "Grand-nephew");
			//family2.print();
			assertEquals(5, family2.height());
		}
}
