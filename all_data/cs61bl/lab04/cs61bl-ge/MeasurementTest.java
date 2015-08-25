import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructor(){
		Measurement test = new Measurement();
		assertTrue(test.getFeet() ==0);
		assertTrue(test.getInches() == 0);
		}
	public void testConstructorOneArg(){
		Measurement test = new Measurement(2);
		assertTrue(test.getInches() == 0);
		assertTrue(test.getFeet() == 2);
	}
	public void testConstructorTwoArgs(){
		Measurement test = new Measurement(2,4);
		assertTrue(test.getFeet() == 2);
		assertTrue(test.getInches() == 4);
	
	}
	public void testGetfeet(){
		Measurement test = new Measurement(2,4);
		assertTrue(test.getFeet() == 2);
		Measurement test1 = new Measurement();
		assertTrue(test1.getFeet() == 0);
		Measurement test2 = new Measurement(1);
		assertTrue(test2.getFeet() == 1);
		
	}
	public void testgetInches(){
		Measurement test = new Measurement();
		assertTrue(test.getInches() ==0);
		Measurement test1 = new Measurement(1);
		assertTrue(test1.getInches() ==0);
		Measurement test2 = new Measurement(2,4);
		assertTrue(test2.getInches() == 4);
		
	}
	public void testplus(){
		Measurement test = new Measurement(3, 10);
		Measurement test2 = new Measurement(2,4);
		Measurement test3 = test.plus(test2);
		Measurement single = new Measurement(2);
		Measurement blank= new Measurement();
		Measurement small = new Measurement(1,1);
		Measurement SimRe = test.plus(small);
		Measurement ReverseSimRe = small.plus(test);
		Measurement SimRe1 = test2.plus(small);
		assertTrue(SimRe.getFeet() == ReverseSimRe.getFeet());
		assertTrue(SimRe.getInches() == ReverseSimRe.getInches());
		assertTrue(SimRe.getFeet()==4);
		assertTrue(SimRe.getInches()==11);
		assertTrue(SimRe1.getFeet()==3);
		assertTrue(SimRe1.getInches() == 5);
		
		assertTrue(test.getInches() == 10);
		assertTrue(test.getFeet() == 3);
		assertTrue(test2.getInches() == 4);
		assertTrue(test2.getFeet() == 2);
		assertTrue(test3.getInches() == 2);
		assertTrue(test3.getFeet() == 6);
		Measurement test4 = test.plus(blank);
		Measurement test5 = test.plus(single);
		assertTrue(test4.getFeet() == 3);
		assertTrue(test4.getInches() == 10);
		assertTrue(test5.getFeet() == 5);
		assertTrue(test5.getInches() == 10);
	}
	public void testminus(){
		Measurement test = new Measurement(3, 10);
		Measurement test2 = new Measurement(4,4);
		Measurement small = new Measurement(1,1);
		Measurement test3 = test2.minus(test);
		Measurement blank = new Measurement();
		Measurement single = new Measurement(4);
		Measurement simpleResult = test.minus(small);
		Measurement simpleResult1 = test2.minus(small);
		assertTrue(simpleResult.getFeet()==2);
		assertTrue(simpleResult.getInches()==9);
		assertTrue(simpleResult1.getFeet()==3);
		assertTrue(simpleResult1.getInches()==3);
		assertTrue(test.getInches() == 10);
		assertTrue(test.getFeet() == 3);
		assertTrue(test2.getInches() == 4);
		assertTrue(test2.getFeet() == 4);
		assertTrue(test3.getInches() == 6);
		assertTrue(test3.getFeet() == 0);
		Measurement res1 = test2.minus(blank);
		Measurement res2 = test2.minus(single);
		Measurement res3 = single.minus(test);
		assertTrue(res1.getFeet() ==4);
		assertTrue(res1.getInches() == 4);
		assertTrue(res2.getFeet()==0);
		assertTrue(res2.getInches() == 4);
		assertTrue(res3.getFeet() ==0);
		assertTrue(res3.getInches() == 2);
		
	}
	public void testmultiple(){
		Measurement test = new Measurement(2, 10);
		Measurement test2 = new Measurement(4);
		
		Measurement blank = new Measurement();
		Measurement test3 = test.multiple(3);
		Measurement orig = test.multiple(1);
		Measurement test4 = test2.multiple(10);
		Measurement pro = blank.multiple(1);
		assertTrue(test.getInches() == 10);
		assertTrue(test.getFeet() == 2);
		assertTrue(test2.getInches() == 0);
		assertTrue(test2.getFeet() == 4);
		assertTrue(test3.getInches() == 6);
		assertTrue(test3.getFeet() == 8);
		assertTrue(test4.getInches() == 0);
		assertTrue(test4.getFeet() == 40);
		assertTrue(pro.getFeet()==0);
		assertTrue(pro.getInches() == 0);
		assertTrue(orig.getFeet()==2);
		assertTrue(orig.getInches() ==10);
		
	}

}
