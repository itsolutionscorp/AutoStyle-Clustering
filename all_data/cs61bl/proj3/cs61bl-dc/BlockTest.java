import static org.junit.Assert.*;

import org.junit.Test;


public class BlockTest
{

	@Test
	public void test()
	{
		Block b = new Block("0 0 1 0");
		assertTrue(b.x1 == 0);
		assertTrue(b.x2 == 1);
		assertTrue(b.y1 == 0);
		assertTrue(b.y2 == 0);
		b = new Block("2 3 4 5");
		assertTrue(b.x1 == 2);
		assertTrue(b.x2 == 4);
		assertTrue(b.y1 == 3);
		assertTrue(b.y2 == 5);
	}

}
