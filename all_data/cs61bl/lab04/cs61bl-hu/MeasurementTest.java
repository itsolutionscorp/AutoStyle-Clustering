import junit.framework.TestCase;

public class MeasurementTest extends TestCase {

public void testInit() {
	
Measurement M = new Measurement(10, 6);
assertTrue(M.feet == 10);
assertTrue(M.inches == 6);
}

public void testget() {
Measurement M = new Measurement(10, 6);
assertTrue(M.feet==10);
assertTrue(M.inches==6);

}

public void test_tostring() {
	
Measurement M = new Measurement(10 ,6 );
assertTrue(M.toString().equalsIgnoreCase("10'6\""));
}

public void testplus() {

Measurement M = new Measurement(10, 6);
Measurement M1 = new Measurement(5, 3);
Measurement M2 = M.plus(M1)	;
assertTrue(M2.inches== 9);
assertTrue(M2.feet== 15);

}

public void testminus() {

Measurement M = new Measurement(10, 6);
Measurement M1 = new Measurement(5, 3);
Measurement M2 = M.minus(M1);
assertTrue(M2.inches== 3);
assertTrue(M2.feet== 5);

}

public void testmultiple() {

Measurement M = new Measurement (10,6);
Measurement M2 = M.multiple(4);
assertTrue(M2.inches == 0);
assertTrue(M2.feet == 42);
}

public void testexceedingcase_and_convertingcase_in_plus() {

Measurement M = new Measurement (10,6);
Measurement M1 = new Measurement (10,7);
Measurement M2 = new Measurement (0,12);
Measurement M3 = M.plus(M1);
Measurement M4 = M1.plus(M2);
assertTrue(M3.inches == 1);
assertTrue(M3.feet == 21);
assertTrue(M4.feet == 11);
assertTrue(M4.inches == 7);

}

public void testexceedingcase_and_convertingcase_in_minus() {

Measurement M = new Measurement (10,6);
Measurement M1 = new Measurement (10,6);
Measurement M2 = new Measurement (0,11);
Measurement M3 = M.minus(M2);
assertTrue(M3.feet == 9);
assertTrue(M3.inches == 7);


}

}