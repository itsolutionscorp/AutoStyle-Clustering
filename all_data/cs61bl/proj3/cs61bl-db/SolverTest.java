import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

public class SolverTest
{

	// @Test
	public void testMain()
	{
		Solver.main(new String[] { "init", "goal" });
	}

	// @Test
	public void testEasy()
	{
		Solver.main(new String[] { "easy/init.from.handout", "easy/goal.2.from.handout" });
	}

//	@Test
	public void testBigBlock2()
	{
		Solver.main(new String[] { "easy/big.block.2", "easy/big.block.2.goal" });
	}

	// @Test
	public void testIsReverse()
	{
		assertTrue(Solver.isReverse("0 1 0 0", "0 0 0 1"));
		assertFalse(Solver.isReverse("0 1 0 2", "0 1 0 2"));
	}

	@Test
	public void test()
	{
		Solver.main(new String[] { "hard/c25", "hard/15.23-27.30.41.goal" });
	}
}
