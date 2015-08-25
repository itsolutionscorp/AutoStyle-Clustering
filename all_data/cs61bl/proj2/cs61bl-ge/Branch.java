import java.io.Serializable;

public class Branch implements Serializable {

	private String myName;
	private int myCommit;
	private static final long serialVersionUID = 19960409L;

	public Branch(String Branchname, int myCommit) {
		this.myCommit = myCommit;
		myName = Branchname;
	}

	public int getCommitId() {
		return myCommit;
	}

	public void updateCommit(int newCommit) {
		myCommit = newCommit;
	}

	public String getName() {
		return myName;
	}

	public String toString() {
		return myName;
	}
}
