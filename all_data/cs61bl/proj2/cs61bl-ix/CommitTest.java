import static org.junit.Assert.*;

import org.junit.Test;

public class CommitTest {

	@Test
	public void constructor() {
		Commit c = new Commit();
		assertEquals(c.getMessage(), "initial commit");
		assertEquals(c.getID(), 0);
		assertEquals(c.previousCommit(), -1);
	}

}
