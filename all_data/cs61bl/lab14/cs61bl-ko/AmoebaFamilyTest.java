import static org.junit.Assert.*;

import org.junit.Test;

public class AmoebaFamilyTest {

	@Test
	public void testCase1() {
		AmoebaFamily family = new AmoebaFamily("Parent");
		family.addChild("Parent", "The");
		family.addChild("Parent", "height");
		family.addChild("Parent", "should");
		family.addChild("Parent", "be");
		family.addChild("Parent", "three");
		family.addChild("Parent", "but");
		family.addChild("Parent", "it's");
		family.addChild("Parent", "the");
		family.addChild("Parent", "number");
		family.addChild("Parent", "of");
		family.addChild("Parent", "ameobas");
		family.addChild("The", "in");
		family.addChild("The", "the");
		family.addChild("The", "whole");
		family.addChild("The", "family.");
		System.out.println(family.height());
		family.print();
	}
	
	@Test
	public void testCase2() {
		AmoebaFamily family = new AmoebaFamily("Should");
		family.addChild("Should", "be");
		family.addChild("be", "7");
		family.addChild("7", "and");
		family.addChild("and", "not");
		family.addChild("not", "14");
		family.addChild("14", "since");
		family.addChild("14", "the");
		family.addChild("14", "bug");
		family.addChild("14", "is");
		family.addChild("14", "counting");
		family.addChild("14", "the");
		family.addChild("14", "children");
		family.addChild("14", "array length.");
		System.out.println(family.height());
		family.print();
	}

}
