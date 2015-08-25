
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import junit.framework.TestCase;


public class DateTest extends TestCase {

	public void testGregorianDate() {
		GregorianDate G = new GregorianDate(1995, 4, 5);
		assertTrue(G.nextDate().toString().equals(new GregorianDate(1995, 4, 6).toString()));
		G = new GregorianDate(1995, 4, 30);
		assertTrue(G.nextDate().toString().equals(new GregorianDate(1995, 5, 1).toString()));
		G = new GregorianDate(1995, 12, 31);
		assertTrue(G.nextDate().toString().equals(new GregorianDate(1996, 1, 1).toString()));
	}
	
	public void testRevolutionaryDate() {
		FrenchRevolutionaryDate G = new FrenchRevolutionaryDate(1995, 4, 5);
		assertTrue(G.nextDate().toString().equals(new FrenchRevolutionaryDate(1995, 4, 6).toString()));
		G = new FrenchRevolutionaryDate(1995, 4, 30);
		assertTrue(G.nextDate().toString().equals(new FrenchRevolutionaryDate(1995, 5, 1).toString()));
		G = new FrenchRevolutionaryDate(1995, 13, 5);
		assertTrue(G.nextDate().toString().equals(new FrenchRevolutionaryDate(1996, 1, 1).toString()));
	}

}
