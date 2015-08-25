import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructor() {
        Measurement measure = new Measurement();
        assertTrue(measure.getFeet() == 0);
        assertTrue(measure.getInches() == 0);
        
        measure = new Measurement(5);
        assertTrue(measure.getFeet() == 5);
        assertTrue(measure.getInches() == 0);
        
        measure = new Measurement(5, 2);
        assertTrue(measure.getFeet() == 5);
        assertTrue(measure.getInches() == 2);
        
        measure = new Measurement(5, 25);
        assertTrue(measure.getFeet() == 7);
        assertTrue(measure.getInches() == 1);
    }

    public void testGetters() {
        Measurement measure = new Measurement(5, 2);
        assertTrue(measure.getFeet() == 5);
        assertTrue(measure.getInches() == 2);
    }

    public void testPlus() {
        Measurement measure = new Measurement(5, 2);
        Measurement measure2 = new Measurement();
        Measurement plusMeasure = measure.plus(measure2);
        assertTrue(measure.getFeet() == 5);
        assertTrue(measure.getInches() == 2);
        assertTrue(measure2.getFeet() == 0);
        assertTrue(measure2.getInches() == 0);
        assertTrue(plusMeasure.getFeet() == 5);
        assertTrue(plusMeasure.getInches() == 2);
        
        measure2 = new Measurement(1, 9);
        plusMeasure = measure.plus(measure2);
        assertTrue(measure.getFeet() == 5);
        assertTrue(measure.getInches() == 2);
        assertTrue(measure2.getFeet() == 1);
        assertTrue(measure2.getInches() == 9);
        assertTrue(plusMeasure.getFeet() == 6);
        assertTrue(plusMeasure.getInches() == 11);

        measure2 = new Measurement(1, 10);
        plusMeasure = measure.plus(measure2);
        assertTrue(measure.getFeet() == 5);
        assertTrue(measure.getInches() == 2);
        assertTrue(measure2.getFeet() == 1);
        assertTrue(measure2.getInches() == 10);
        assertTrue(plusMeasure.getFeet() == 7);
        assertTrue(plusMeasure.getInches() == 0);

        measure2 = new Measurement(10, 25);
        plusMeasure = measure.plus(measure2);
        assertTrue(measure.getFeet() == 5);
        assertTrue(measure.getInches() == 2);
        assertTrue(measure2.getFeet() == 12);
        assertTrue(measure2.getInches() == 1);
        assertTrue(plusMeasure.getFeet() == 17);
        assertTrue(plusMeasure.getInches() == 3);
    }
    
    public void testMinus() {
        Measurement measure = new Measurement(5, 2);
        Measurement measure2 = new Measurement();
        Measurement minusMeasure = measure.minus(measure2);
        assertTrue(measure.getFeet() == 5);
        assertTrue(measure.getInches() == 2);
        assertTrue(measure2.getFeet() == 0);
        assertTrue(measure2.getInches() == 0);
        assertTrue(minusMeasure.getFeet() == 5);
        assertTrue(minusMeasure.getInches() == 2);

        measure2 = new Measurement(1, 2);
        minusMeasure = measure.minus(measure2);
        assertTrue(measure.getFeet() == 5);
        assertTrue(measure.getInches() == 2);
        assertTrue(measure2.getFeet() == 1);
        assertTrue(measure2.getInches() == 2);
        assertTrue(minusMeasure.getFeet() == 4);
        assertTrue(minusMeasure.getInches() == 0);

        measure2 = new Measurement(1, 3);
        minusMeasure = measure.minus(measure2);
        assertTrue(measure.getFeet() == 5);
        assertTrue(measure.getInches() == 2);
        assertTrue(measure2.getFeet() == 1);
        assertTrue(measure2.getInches() == 3);
        assertTrue(minusMeasure.getFeet() == 3);
        assertTrue(minusMeasure.getInches() == 11);

        measure2 = new Measurement(5, 2);
        minusMeasure = measure.minus(measure2);
        assertTrue(measure.getFeet() == 5);
        assertTrue(measure.getInches() == 2);
        assertTrue(measure2.getFeet() == 5);
        assertTrue(measure2.getInches() == 2);
        assertTrue(minusMeasure.getFeet() == 0);
        assertTrue(minusMeasure.getInches() == 0);
    }
    
    public void testMultiple() {
        Measurement measure = new Measurement(5, 2);
        Measurement multipleMeasure = measure.multiple(0);
        assertTrue(measure.getFeet() == 5);
        assertTrue(measure.getInches() == 2);
        assertTrue(multipleMeasure.getFeet() == 0);
        assertTrue(multipleMeasure.getInches() == 0);

        multipleMeasure = measure.multiple(1);
        assertTrue(measure.getFeet() == 5);
        assertTrue(measure.getInches() == 2);
        assertTrue(multipleMeasure.getFeet() == 5);
        assertTrue(multipleMeasure.getInches() == 2);

        multipleMeasure = measure.multiple(2);
        assertTrue(measure.getFeet() == 5);
        assertTrue(measure.getInches() == 2);
        assertTrue(multipleMeasure.getFeet() == 10);
        assertTrue(multipleMeasure.getInches() == 4);

        multipleMeasure = measure.multiple(10);
        assertTrue(measure.getFeet() == 5);
        assertTrue(measure.getInches() == 2);
        assertTrue(multipleMeasure.getFeet() == 51);
        assertTrue(multipleMeasure.getInches() == 8);
    }
    
    public void testToString() {
        Measurement measure = new Measurement();
        assertEquals(measure.toString(), "0\'0\"");
        measure = new Measurement(5, 2);
        assertEquals(measure.toString(), "5\'2\"");
        measure = new Measurement(100, 4);
        assertEquals(measure.toString(), "100\'4\"");
    }
}
