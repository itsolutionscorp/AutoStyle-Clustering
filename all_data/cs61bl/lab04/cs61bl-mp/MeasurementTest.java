import junit.framework.TestCase;


public class MeasurementTest extends TestCase {

	 public void testConstructor1() {
	        Measurement test1 = new Measurement();
	        assertTrue (test1.getFeet() == 0);
	        assertTrue (test1.getInches() == 0);
	        }
	 public void testConstructor2() {
	        Measurement test2 = new Measurement(2);
	        assertTrue (test2.getFeet() == 2);
	        assertTrue (test2.getInches() == 0);
	 }
	 public void testConstructor3() {
		    Measurement test3 = new Measurement(2, 6);
		    assertTrue (test3.getFeet() == 2);
		    assertTrue (test3.getInches() == 6);
		    Measurement test4 = new Measurement(2, 13);
		    assertTrue (test4.getFeet() == 3);
		    assertTrue (test4.getInches() == 1);
	 }
	 public void testPlus() {
		   Measurement m1 = new Measurement(2, 4);
		   Measurement m2 = new Measurement(2, 13);
		   Measurement m3 = m2.plus(m1);
		   assertTrue (m3.getFeet() == 5);
		   assertTrue (m3.getInches() == 5);
		   assertTrue (m1.getFeet() == 2);
		   assertTrue (m1.getInches() == 4);
		   assertTrue (m2.getFeet() == 3);
		   assertTrue (m2.getInches() == 1);
		   Measurement m4 = new Measurement(2, 0);
		   Measurement m5 = new Measurement(2);
		   Measurement m6 = m4.plus(m5);
		   assertTrue (m6.getFeet() == 4);
		   assertTrue (m6.getInches() == 0);
		   assertTrue (m4.getFeet() == 2);
		   assertTrue (m4.getInches() == 0);
		   assertTrue (m5.getFeet() == 2);
		   assertTrue (m5.getInches() == 0);
	 }
	 
	 public void testMinus() {
		   Measurement m1 = new Measurement(2, 6);
		   Measurement m2 = new Measurement(1, 13);
		   Measurement m3 = m1.minus(m2);
		   assertTrue (m3.getFeet() == 0);
		   assertTrue (m3.getInches() == 5);
		   assertTrue (m1.getFeet() == 2);
		   assertTrue (m1.getInches() == 6);
		   assertTrue (m2.getFeet() == 2);
		   assertTrue (m2.getInches() == 1);
		   Measurement m4 = new Measurement(2, 0);
		   Measurement m5 = new Measurement(2);
		   Measurement m6 = m4.minus(m5);
		   assertTrue (m6.getFeet() == 0);
		   assertTrue (m6.getInches() == 0);
		   assertTrue (m4.getFeet() == 2);
		   assertTrue (m4.getInches() == 0);
		   assertTrue (m5.getFeet() == 2);
		   assertTrue (m5.getInches() == 0);
	 }
	 
	 public void testMultiple() {
		   Measurement m1 = new Measurement(2, 4);
		   Measurement m2 = new Measurement(2, 3);
		   Measurement m6 = new Measurement(2, 13);
		   Measurement m3 = m1.multiple(3);
		   Measurement m4 = m2.multiple(2);
		   Measurement m5 = m2.multiple(0);
		   Measurement m7 = m6.multiple(2);
		   assertTrue (m3.getFeet() == 7);
		   assertTrue (m3.getInches() == 0);
		   assertTrue (m4.getFeet() == 4);
		   assertTrue (m4.getInches() == 6);
		   assertTrue (m1.getFeet() == 2);
		   assertTrue (m1.getInches() == 4);
		   assertTrue (m2.getFeet() == 2);
		   assertTrue (m2.getInches() == 3);
		   assertTrue (m5.getFeet() == 0);
		   assertTrue (m5.getInches() == 0);
		   assertTrue (m6.getFeet() == 3);
		   assertTrue (m6.getInches() == 1);
		   assertTrue (m7.getFeet() == 6);
		   assertTrue (m7.getInches() == 2);
	 }
	 
	 public void testtoString() {
		   Measurement m1 = new Measurement(2, 4);
		   Measurement m2 = new Measurement(2);
		   Measurement m3 = new Measurement();
		   Measurement m4 = new Measurement(3, 26);
		   m1.toString();
		   m2.toString();
		   m3.toString();
		   m4.toString();
		   assertTrue (m1.toString().equals("2\'4\""));
		   assertTrue (m2.toString().equals("2\'0\""));
		   assertTrue (m3.toString().equals("0\'0\""));
		   assertTrue (m4.toString().equals("5\'2\""));
	 }
}