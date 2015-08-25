import java.io.Serializable;

public class CommitNode implements Serializable {
	private CommitNode myParent;
	private Commit myCommit;

	/**
	 * Creates a new CommitNode with the given parent CommitNode and the given
	 * Commit.
	 * 
	 * @param parent
	 *            the parent CommitNode of the commit tree.
	 * 
	 * @param self
	 *            the commit that this CommitNode represents
	 */
	public CommitNode(CommitNode parent, Commit self) {
		myParent = parent;
		myCommit = self;
	}

	/**
	 * Creates a new CommitNode with the given Commit. Since there is no given
	 * parent CommitNode, it assumes there is none.
	 * 
	 * @param self
	 *            the commit that this CommitNode represents
	 */
	public CommitNode(Commit self) {
		this(null, self);
	}

	/**
	 * Creates a new CommitNode with the given parent CommitNode. Since there is
	 * no given Commit, it assumes there is none.
	 * 
	 * @param parent
	 *            the parent CommitNode of the commit tree
	 */
	public CommitNode(CommitNode parent) {
		this(parent, null);
	}

	/**
	 * Sets the CommitNode's commit to the given Commit.
	 * 
	 * @param c
	 *            the Commit that the CommitNode's myCommit will be set to
	 */
	public void setCommit(Commit c) {
		this.myCommit = c;
	}

	/**
	 * Sets the CommitNode's parent to the given parent CommitNode.
	 * 
	 * @param cn
	 *            the CommitNode that the CommitNode's myParent will be set to
	 */
	public void setParent(CommitNode cn) {
		this.myParent = cn;
	}

	/**
	 * @return the parent CommitNode (myParent) of this CommitNode
	 */
	public CommitNode parent() {
		return myParent;
	}

	/**
	 * @return the Commit (myCommit) of this CommitNode
	 */
	public Commit item() {
		return myCommit;
	}
}
