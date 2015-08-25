import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	 public void testGetFeet() {
	        Measurement TestGetFeet = new Measurement(3, 4);
	        assertTrue (TestGetFeet.getFeet() == 3);
	    }
	 public void testGetInches() {
	        Measurement TestGetInches = new Measurement(3,4);
	        assertTrue (TestGetInches.getInches() == 4);
	    }
	 public void testMeasurement1() {
	        Measurement Test1 = new Measurement();
	        assertTrue (Test1.getFeet() == 0);
	        assertTrue (Test1.getInches() == 0);
	    }
	 public void testMeasurement2() {
	        Measurement Test2 = new Measurement(2);
	        assertTrue (Test2.getFeet() == 2);
	        assertTrue (Test2.getInches() == 0);
	    }
	 public void testMeasurement3() {
	        Measurement Test3 = new Measurement(3,4);
	        assertTrue (Test3.getFeet() == 3);
	        assertTrue (Test3.getInches() == 4);
	    }
	 public void testPlusNoConversion() {
	        Measurement TestPlusNoConversion = new Measurement(3,4);
	        Measurement TestPlusNoConversion2 = new Measurement(2,4);
	        TestPlusNoConversion.plus(TestPlusNoConversion2);
	        assertTrue (TestPlusNoConversion.getFeet() == 5);
	        assertTrue (TestPlusNoConversion.getInches() == 8);
	    }
	 public void testPlus() {
	        Measurement TestPlus = new Measurement(3,4);
	        Measurement TestPlus2 = new Measurement(2,10);
	        TestPlus.plus(TestPlus2);
	        assertTrue (TestPlus.getFeet() == 6);
	        assertTrue (TestPlus.getInches() == 2);
	    }
	 
	 public void testMinusNoConversion() {
	        Measurement TestMinusNoConversion = new Measurement(3,4);
	        Measurement TestMinusNoConversion2 = new Measurement(2,4);
	        TestMinusNoConversion.minus(TestMinusNoConversion2);
	        assertTrue (TestMinusNoConversion.getFeet() == 1);
	        assertTrue (TestMinusNoConversion.getInches() == 0);
	    }
	 public void testMinus() {
	        Measurement TestMinus = new Measurement(3,4);
	        Measurement TestMinus2 = new Measurement(2,10);
	        TestMinus.minus(TestMinus2);
	        assertTrue (TestMinus.getFeet() == 0);
	        assertTrue (TestMinus.getInches() == 6);
	    }
	 
	 public void testMultiple() {
	        Measurement TestMultiple = new Measurement(3,10);
	        TestMultiple.multiple(2);
	        assertTrue (TestMultiple.getFeet() == 7);
	        assertTrue (TestMultiple.getInches() == 8);
	    }
	 
	 public void testString() {
	        Measurement TestString = new Measurement(3,10);
	        TestString.toString();
	        assertTrue (TestString.toString().equals("3' 10\"") );
	    }
	 

 
}
