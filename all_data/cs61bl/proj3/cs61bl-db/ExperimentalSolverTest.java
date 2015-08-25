import static org.junit.Assert.*;

import org.junit.Test;

public class ExperimentalSolverTest
{
	@Test
	public void testMain()
	{
		Solver.main(new String[] { "init", "goal" });
	}

	@Test
	public void testEasy()
	{
		Solver.main(new String[] { "easy/init.from.handout", "easy/goal.2.from.handout" });
	}
}
