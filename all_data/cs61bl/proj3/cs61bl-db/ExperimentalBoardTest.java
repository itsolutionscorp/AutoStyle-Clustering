import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class ExperimentalBoardTest
{

	@Test
	public void testConstructor()
	{
		try
		{
			ExperimentalBoard b = new ExperimentalBoard("0 4");
			fail();
		}
		catch (Exception e)
		{}
		try
		{
			ExperimentalBoard b = new ExperimentalBoard("2 0");
			fail();
		}
		catch (Exception e)
		{}
		try
		{
			ExperimentalBoard b = new ExperimentalBoard("4 5");
			assertEquals(b.toString(), "4 5\n");
			b.addBlock("0 0 1 0");
			assertEquals(b.toString(), "4 5\n0 0 1 0\n");
		}
		catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void testPossibleMoves()
	{
		try
		{
			ExperimentalBoard b = new ExperimentalBoard("2 2");
			b.addBlock("0 0 0 0");
			b.finishAddingBlocks();
			LinkedList<String> moves = new LinkedList<String>();
			moves.add("0 0 1 0");
			moves.add("0 0 0 1");
			LinkedList<String> possible = b.possibleMoves();
			assertEquals(moves, possible);

			b = new ExperimentalBoard("2 2");
			b.addBlock("0 1 0 1");
			b.finishAddingBlocks();
			moves = b.possibleMoves();
			assertTrue(moves.size() == 2);
			assertTrue(moves.contains("0 1 0 0"));
			assertTrue(moves.contains("0 1 1 1"));

			b = new ExperimentalBoard("3 3");
			b.addBlock("1 1 1 1");
			b.addBlock("0 1 0 1");
			b.addBlock("1 2 1 2");
			b.finishAddingBlocks();
			moves = b.possibleMoves();
			assertTrue(moves.size() == 6);
			assertTrue(moves.contains("0 1 0 0"));
			assertTrue(moves.contains("0 1 0 2"));
			assertTrue(moves.contains("1 1 1 0"));
			assertTrue(moves.contains("1 1 2 1"));
			assertTrue(moves.contains("1 2 2 2"));
			assertTrue(moves.contains("1 2 0 2"));

			b = new ExperimentalBoard("4 4");
			b.addBlock("1 1 2 2");
			b.addBlock("0 2 0 2");
			b.addBlock("1 3 2 3");
			b.finishAddingBlocks();
			moves = b.possibleMoves();
			assertTrue(moves.size() == 6);
			assertTrue(moves.contains("0 2 0 1"));
			assertTrue(moves.contains("0 2 0 3"));
			assertTrue(moves.contains("1 1 1 0"));
			assertTrue(moves.contains("1 1 2 1"));
			assertTrue(moves.contains("1 3 0 3"));
			assertTrue(moves.contains("1 3 2 3"));

			b = new ExperimentalBoard("5 4");
			b.addBlock("0 1 1 2");
			b.finishAddingBlocks();
			moves = b.possibleMoves();
			assertFalse(moves.contains("0 1 0 3"));
		}
		catch (Exception e)
		{}
	}

	@Test
	public void testMove()
	{
		try
		{
			ExperimentalBoard b = new ExperimentalBoard("2 2");
			b.addBlock("0 0 0 0");
			b.finishAddingBlocks();
			ExperimentalBoard b1 = b.move("0 0 1 0");
			assertEquals(b.toString(), "2 2\n0 0 0 0\n");
			assertEquals(b1.toString(), "2 2\n1 0 1 0\n");
		}
		catch (Exception e)
		{}
	}

	@Test
	public void testHashCode()
	{
		try
		{
			ExperimentalBoard b = new ExperimentalBoard("4 4");
			b.addBlock("1 1 2 2");
			b.addBlock("0 2 0 2");
			b.addBlock("1 3 2 3");
			b.finishAddingBlocks();
			ExperimentalBoard b2 = new ExperimentalBoard("4 4");
			b2.addBlock("1 0 2 1");
			b2.addBlock("0 2 0 2");
			b2.addBlock("1 3 2 3");
			b2.finishAddingBlocks();
			assertTrue(b.hashCode() != b2.hashCode());
			b = b.move("1 1 1 0");
			assertEquals(b.hashCode(), b2.hashCode());
			ExperimentalBoard a1 = new ExperimentalBoard("3 4"), a2 = new ExperimentalBoard("3 4"), a3 = new ExperimentalBoard("3 4"), a4 = new ExperimentalBoard("3 4"), a5 = new ExperimentalBoard("3 4");
			b = new ExperimentalBoard("3 4");
			b.addBlock("1 2 1 3");
			b.addBlock("1 0 1 1");
			b.addBlock("2 1 2 2");
			b.addBlock("0 1 0 2");
			b.finishAddingBlocks();
			a1.addBlock("1 0 1 1");
			a1.addBlock("2 0 2 1");
			a1.addBlock("0 2 0 3");
			a1.addBlock("0 0 0 1");
			a1.finishAddingBlocks();
			a2.addBlock("1 2 1 3");
			a2.addBlock("1 0 1 1");
			a2.addBlock("2 1 2 2");
			a2.addBlock("0 2 0 3");
			a2.finishAddingBlocks();
			a3.addBlock("1 2 1 3");
			a3.addBlock("1 0 1 1");
			a3.addBlock("2 0 2 1");
			a3.addBlock("0 0 0 1");
			a3.finishAddingBlocks();
			a4.addBlock("1 2 1 3");
			a4.addBlock("1 0 1 1");
			a4.addBlock("2 0 2 1");
			a4.addBlock("0 2 0 3");
			a4.finishAddingBlocks();
			a5.addBlock("1 2 1 3");
			a5.addBlock("1 0 1 1");
			a5.addBlock("2 0 2 1");
			a5.addBlock("0 1 0 2");
			a5.finishAddingBlocks();
			assertFalse(b.equals(a1));
			assertFalse(b.equals(a2));
			assertFalse(b.equals(a3));
			assertFalse(b.equals(a4));
			assertFalse(b.equals(a5));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail();
		}
	}
}
