import junit.framework.TestCase;


public class AmoebaFamilyTest extends TestCase {
	public void testAdd(){
			AmoebaFamily test = new AmoebaFamily("Ancestor");
			
			test.addChild("Ancestor","Grandpa");
			test.addChild("Ancestor","Grandpa Alex");
			test.addChild("Ancestor","Grandpa Alan");
			test.addChild("Grandpa","Dad lee");
			test.addChild("Grandpa","Luke");
			test.addChild("Grandpa","Lawrance");
			test.addChild("Grandpa Alex","Lewis");
			test.addChild("Grandpa Alan","Linda");
			test.addChild("Grandpa Alan","Lesley");
			assertEquals(test.size(),10);
			assertEquals(test.height(),3);
			test.addChild("Dad lee","me");
			assertEquals(test.height(),4);
			assertEquals(test.size(),11);
			test.addChild("Dad lee","Mark");
			test.addChild("Mark","Brian");
			test.addChild("Mark","Brandon");
			test.addChild("Mark","Bradley");
			test.addChild("Mark","Betty");
			assertEquals(test.height(),5);
			assertEquals(test.size(),16);
			
	}
	public void testReplaceName(){
		AmoebaFamily test = new AmoebaFamily("Ancestor");
		test.addChild("Ancestor","Grandpa");
		test.addChild("Ancestor","Grandpa Alex");
		test.addChild("Ancestor","Grandpa Allan");
		test.addChild("Grandpa","Dad lee");
		test.addChild("Grandpa","Luke");
		test.addChild("Grandpa","Lawrance");
		test.addChild("Grandpa Alex","Lewis");
		test.addChild("Grandpa Allan","Linda");
		test.addChild("Grandpa Allan","Lesley");
		test.addChild("Grandpa", "Lewis");
		test.addChild("Grandpa Allan","Lewis");
		assertEquals(test.longestName(),"Grandpa Allan");
		//change single name
	    test.replaceName("Grandpa Allan","Allan");
	    test.print();
	    assertEquals(test.longestName(),"Grandpa Alex");
		test.print();
		//change multiple name
		test.replaceName("Lewis","LongestNameEverLouis");
		test.print();
		assertEquals(test.longestName(),"LongestNameEverLouis");
		
	}
	public void testToLowerCase(){
		AmoebaFamily test = new AmoebaFamily("Ancestor");
		test.addChild("Ancestor","Grandpa");
		test.addChild("Ancestor","Grandpa Alex");
		test.addChild("Ancestor","Grandpa Allan");
		test.addChild("Grandpa","Dad lee");
		test.addChild("Grandpa","Luke");
		test.addChild("Grandpa","Lawrance");
		test.addChild("Grandpa Alex","Lewis");
		test.addChild("Grandpa Allan","Linda");
		test.addChild("Grandpa Allan","Lesley");
		assertEquals(test.longestName(),"Grandpa Allan");
		test.makeNamesLowercase();
		test.print();
		assertEquals(test.longestName(),"grandpa allan");
		
		
	}
	public void testSize(){
		AmoebaFamily test = new AmoebaFamily("Ancestor");
		assertTrue(test.size()==1);
		test.addChild("Ancestor","Grandpa");
		assertTrue(test.size()==2);
		test.addChild("Grandpa Alex","Lewis");
		assertTrue(test.size()==2);
		test.addChild("Ancestor","Grandpa Alex");
		assertTrue(test.size()==3);
		test.addChild("Ancestor","Grandpa Allan");
		assertTrue(test.size()==4);
		test.addChild("Grandpa","Dad lee");
		test.addChild("Grandpa","Luke");
		test.addChild("Grandpa","Lawrance");
		test.addChild("Grandpa Alex","Lewis");
		test.addChild("Grandpa Allan","Linda");
		test.addChild("Grandpa Allan","Lesley");
		assertTrue(test.size()==10);
		test.addChild("","abd");
		assertTrue(test.size()==10);
		
	}
	public void testLongestNameLength(){
		AmoebaFamily test = new AmoebaFamily("Ancestor");
		assertTrue(test.longestNameLength()==8);
		test.addChild("Ancestor","Grandpa");
		assertTrue(test.longestNameLength()==8);
		test.addChild("Grandpa Alex","Lewis");
		assertTrue(test.longestNameLength()==8);
		test.addChild("Ancestor","Grandpa Alex");
		assertTrue(test.longestNameLength()==12);
		test.addChild("Ancestor","Grandpa Allan");
		assertTrue(test.longestNameLength()==13);
		assertTrue(test.size()==4);
		test.addChild("Grandpa","Dad lee");
		test.addChild("Grandpa","Luke");
		test.addChild("Grandpa","Child Lawrance");
		assertTrue(test.longestNameLength()==14);
		test.addChild("Grandpa Alex","Lewis");
		test.addChild("Grandpa Allan","Linda");
		test.addChild("Grandpa Allan","Lesley");
		assertTrue(test.longestNameLength()==14);
		
	}
	public void testLongestName(){
		AmoebaFamily test = new AmoebaFamily("Ancestor");
		assertEquals(test.longestName(),"Ancestor");
		test.addChild("Ancestor","Grandpa");
		test.addChild("Ancestor","Grandpa Alex");
		test.addChild("Ancestor","Grandpa Allan");
		test.addChild("Ancestor","Grandpa Allen");
		test.addChild("Grandpa","Dad lee");
		test.addChild("Grandpa","Luke");
		test.addChild("Grandpa","Lawrance");
		test.addChild("Grandpa Alex","Lewis");
		test.addChild("Grandpa Allan","Linda");
		test.addChild("Grandpa Allan","Lesley");
		assertEquals(test.longestName(),"Grandpa Allan");
		test.replaceName("Grandpa Allan","Allan");
		assertEquals(test.longestName(),"Grandpa Allen");
		test.replaceName("lewis","Grandso Lewis");
		assertEquals(test.longestName(),"Grandpa Allen");
		test.replaceName("Lewis","LongestNameEverLouis");
		
		assertEquals(test.longestName(),"LongestNameEverLouis");
		
	}
	public void testBusiest(){
		AmoebaFamily test = new AmoebaFamily("Ancestor");
		assertEquals(test.busiest(),"Ancestor");
		test.addChild("Ancestor","Grandpa");
		test.addChild("Ancestor","Grandpa Alex");
		test.addChild("Ancestor","Grandpa Allan");
		assertEquals(test.busiest(),"Ancestor");
		test.addChild("Grandpa","Dad lee");
		test.addChild("Grandpa","Luke");
		test.addChild("Grandpa","Lawrance");
		test.addChild("Grandpa","Lin");
		assertEquals(test.busiest(),"Grandpa");
		test.addChild("Grandpa Alex","Lewis");
		test.addChild("Grandpa Allan","Linda");
		test.addChild("Grandpa Allan","Lesley");
		assertEquals(test.busiest(),"Grandpa");
		test.addChild("Dad lee", "me");
		test.addChild("Dad lee","Brian");
		test.addChild("Dad lee","Bradon");
		test.addChild("Dad lee","Bradly");
		test.addChild("Dad lee","Bradson");
		assertEquals(test.busiest(),"Dad lee");
	}
	public void testHeight(){
		AmoebaFamily test = new AmoebaFamily("Ancestor");
		assertTrue(test.height()==1);
		test.addChild("Ancestor","Grandpa");
		test.addChild("Ancestor","Grandpa Alex");
		test.addChild("Ancestor","Grandpa Allan");
		assertTrue(test.height()==2);
		test.addChild("Grandpa","Dad lee");
		assertTrue(test.height()==3);
		test.addChild("Dad lee", "me");
		assertTrue(test.height()==4);
		test.addChild("Dad lee","Brian");
		assertTrue(test.height()==4);
		test.addChild("Dad lee","Bradon");
		assertTrue(test.height()==4);
		test.addChild("Dad lee","Bradly");
		assertTrue(test.height()==4);
		test.addChild("Dad lee","Bradson");
		assertTrue(test.height()==4);
		test.addChild("Grandpa","Luke");
		assertTrue(test.height()==4);
		test.addChild("Grandpa","Lawrance");
		assertTrue(test.height()==4);
		test.addChild("Grandpa","Lin");
		assertTrue(test.height()==4);
		test.addChild("Brian", "Chris");
		assertTrue(test.height()==5);
	}
}
