import junit.framework.TestCase;

public class MeasurementTest extends TestCase {
    public void testEmptyConstructor() {
        Measurement m = new Measurement();
        assertEquals(m.getFeet(), 0);
        assertEquals(m.getInches(), 0);
    }

    public void testFeetConstructor() {
        Measurement m = new Measurement(2);
        assertEquals(m.getFeet(), 2);
        assertEquals(m.getInches(), 0);
    }

    public void testBothConstructor() {
        Measurement m = new Measurement(2, 6);
        assertEquals(m.getFeet(), 2);
        assertEquals(m.getInches(), 6);
    }

    public void testBothConstructorWeird() {
        int initialFeet = 2;
        int initialInches = 123;
        Measurement m = new Measurement(initialFeet, initialInches);
        assertEquals(m.getFeet(), 12);
        assertEquals(m.getInches(), 3);
    }

    public void testPlusNoCarry() {
        Measurement m1 = new Measurement(3, 3);
        Measurement m2 = new Measurement(1, 6);
        Measurement sum = m1.plus(m2);
        assertEquals(sum.getFeet(), 4);
        assertEquals(sum.getInches(), 9);
    }

    public void testPlusCarry() {
        Measurement m1 = new Measurement(3, 7);
        Measurement m2 = new Measurement(1, 6);
        Measurement sum = m1.plus(m2);
        assertEquals(sum.getFeet(), 5);
        assertEquals(sum.getInches(), 1);
    }

    public void testMinusNoCarry() {
        Measurement m1 = new Measurement(3, 3);
        Measurement m2 = new Measurement(1, 1);
        Measurement difference = m1.minus(m2);
        assertEquals(difference.getFeet(), 2);
        assertEquals(difference.getInches(), 2);
    }

    public void testMinusCarry() {
        Measurement m1 = new Measurement(3, 3);
        Measurement m2 = new Measurement(1, 6);
        Measurement difference = m1.minus(m2);
        assertEquals(difference.getFeet(), 1);
        assertEquals(difference.getInches(), 9);
    }

    public void testMultipleNoCarry() {
        Measurement m = new Measurement(3, 3);
        m = m.multiple(3);
        assertEquals(m.getFeet(), 9);
        assertEquals(m.getInches(), 9);
    }

    public void testMultipleCarry() {
        Measurement m = new Measurement(4, 4);
        m = m.multiple(4);
        assertEquals(m.getFeet(), 17);
        assertEquals(m.getInches(), 4);
    }

    public void testToString() {
        int feet = 1;
        int inches = 10;
        Measurement m = new Measurement(feet, inches);
        assertEquals(m.toString(), "1'10\"");
    }
}