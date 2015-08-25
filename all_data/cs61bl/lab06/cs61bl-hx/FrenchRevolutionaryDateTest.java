import junit.framework.TestCase;


public class FrenchRevolutionaryDateTest extends TestCase {
	public void testNextDate(){
		FrenchRevolutionaryDate g = new FrenchRevolutionaryDate(1, 1, 1);
		assertTrue (g.nextDate().toString().equals((new FrenchRevolutionaryDate (1, 1, 2)).toString()));
		assertTrue (g.toString().equals((new FrenchRevolutionaryDate (1, 1, 1)).toString()));
		FrenchRevolutionaryDate g1 = new FrenchRevolutionaryDate (1, 2, 28);
		assertTrue (g1.nextDate().toString().equals((new FrenchRevolutionaryDate (1, 2, 29)).toString()));
		FrenchRevolutionaryDate g2 = new FrenchRevolutionaryDate (1, 3, 30);
		assertTrue (g2.nextDate().toString().equals((new FrenchRevolutionaryDate (1, 4, 1)).toString()));
		FrenchRevolutionaryDate g3 = new FrenchRevolutionaryDate (1, 4, 30);
		assertTrue (g3.nextDate().toString().equals((new FrenchRevolutionaryDate (1, 5, 1)).toString()));
		FrenchRevolutionaryDate g4 = new FrenchRevolutionaryDate (1, 13, 5);
		assertTrue (g4.nextDate().toString().equals((new FrenchRevolutionaryDate (2, 1, 1)).toString()));
	}
}



