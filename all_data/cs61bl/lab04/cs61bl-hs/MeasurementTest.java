import junit.framework.TestCase;


public class MeasurementTest extends TestCase {

	public void testConstructor() {
		Measurement a = new Measurement();
		assertTrue(a.getFeet() == 0);
		assertTrue(a.getInches() == 0);
		Measurement b = new Measurement(5);
		assertTrue(b.getFeet() == 5);
		assertTrue(b.getInches() == 0);
		Measurement c = new Measurement(5, 6);
		assertTrue(c.getFeet() == 5);
		assertTrue(c.getInches() == 6);
		Measurement d = new Measurement(5, 12);
		assertTrue(d.getFeet() == 6);
		assertTrue(d.getInches() == 0);
		Measurement e = new Measurement(5, 15);
		assertTrue(e.getFeet() == 6);
		assertTrue(e.getInches() == 3);
		Measurement f = new Measurement(5, 25);
		assertTrue(f.getFeet() == 7);
		assertTrue(f.getInches() == 1);
	}
	
	public void testGetter() {
		Measurement a = new Measurement(5, 6);
		assertTrue(a.getFeet() == 5);
		assertTrue(a.getInches() == 6);
		Measurement b = new Measurement(5, 15);
		assertTrue(b.getFeet() == 6);
		assertTrue(b.getInches() == 3);
	}
	
	public void testPlus() {
		Measurement a = new Measurement(5, 6);
		Measurement b = new Measurement(5, 3);
		Measurement c = a.plus(b);
		assertTrue(c.getFeet() == 10);
		assertTrue(c.getInches() == 9);
		Measurement d = new Measurement(5, 6);
		Measurement e = new Measurement(5, 6);
		Measurement f = d.plus(e);
		assertTrue(f.getFeet() == 11);
		assertTrue(f.getInches() == 0);
		Measurement g = new Measurement(5, 6);
		Measurement h = new Measurement(5, 10);
		Measurement i = g.plus(h);
		assertTrue(i.getFeet() == 11);
		assertTrue(i.getInches() == 4);
	}
	
	public void testMinus() {
		Measurement a = new Measurement(5, 6);
		Measurement b = new Measurement(2, 3);
		Measurement c = a.minus(b);
		assertTrue(c.getFeet() == 3);
		assertTrue(c.getInches() == 3);
		Measurement d = new Measurement(5, 6);
		Measurement e = new Measurement(2, 6);
		Measurement f = d.minus(e);
		assertTrue(f.getFeet() == 3);
		assertTrue(f.getInches() == 0);
		Measurement g = new Measurement(5, 6);
		Measurement h = new Measurement(2, 10);
		Measurement i = g.minus(h);
		assertTrue(i.getFeet() == 2);
		assertTrue(i.getInches() == 8);
		Measurement j = new Measurement(5, 6);
		Measurement k = new Measurement(4, 10);
		Measurement l = j.minus(k);
		assertTrue(l.getFeet() == 0);
		assertTrue(l.getInches() == 8);
		Measurement m = new Measurement(5, 6);
		Measurement n = new Measurement(5, 3);
		Measurement o = m.minus(n);
		assertTrue(o.getFeet() == 0);
		assertTrue(o.getInches() == 3);
	}
	
	public void testMultiple() {
		Measurement a = new Measurement(5, 5);
		Measurement b = a.multiple(2);
		assertTrue(b.getFeet() == 10);
		assertTrue(b.getInches() == 10);
		Measurement c = new Measurement(5, 6);
		Measurement d = c.multiple(2);
		assertTrue(d.getFeet() == 11);
		assertTrue(d.getInches() == 0);
		Measurement e = new Measurement(5, 6);
		Measurement f = e.multiple(3);
		assertTrue(f.getFeet() == 16);
		assertTrue(f.getInches() == 6);
		Measurement g = new Measurement(5, 5);
		Measurement h = g.multiple(5);
		assertTrue(h.getFeet() == 27);
		assertTrue(h.getInches() == 1);
	}
	
}
