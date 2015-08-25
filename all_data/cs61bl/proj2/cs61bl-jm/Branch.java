import java.io.Serializable;

public class Branch implements Serializable {

	/**
	 * Represents a branch pointer
	 * 
	 * Instance Variables: 
	 * myName - Name of this branch 
	 * myCommit - The commit node this branch is currently pointing to
	 * serialVersionUID: Used for serialization, not very important
	 * 
	 * @author Johnny Le, Kevin Luong, Sijing Xin, Jia Guo
	 */

	private String myName;
	private Commit myCommit;
	private static final long serialVersionUID = 3L;

	public Branch(String name, Commit commit) {
		myName = name;
		myCommit = commit;
	}

	/**
	 * Returns the name of this branch
	 * @return
	 * 		name of this branch
	 */
	public String name() {
		return myName;
	}

	/**
	 * Returns the commit this branch is pointing to
	 * @return
	 * 		The commit this branch is pointing to
	 */
	public Commit commit() {
		return myCommit;
	}

	/**
	 * Sets this branch's myCommit to newCommit
	 * 
	 * @param newCommit
	 * 		the commit that you want this branch to point to
	 */
	public void setCommit(Commit newCommit) {
		myCommit = newCommit;
	}

}
