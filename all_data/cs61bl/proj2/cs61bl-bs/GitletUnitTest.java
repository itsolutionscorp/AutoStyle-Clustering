import junit.framework.TestCase;


public class GitletUnitTest extends TestCase {
	public void testGetandSetCommitNode(){
		Commit c = new Commit("this is a Commit.");
		CommitNode self = new CommitNode(c);
		c.setCommitNode(self);
		Commit parent = new Commit("I am c's parent Commit");
		CommitNode parentCN = new CommitNode(parent);
		self.setParent(parentCN);
		c.setParentCN(parentCN);
		assertEquals(c.getParentCN(), parentCN);
	}

}
