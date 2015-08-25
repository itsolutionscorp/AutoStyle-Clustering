import java.io.Serializable;

public class Branch implements Serializable {
	private Commit startOfBranch;
	private Commit branchHead;
	private final String branchName;
	private String branchPath;

	public Branch(String branchName, Commit startBranch) {
		this.branchName = branchName;
		this.startOfBranch = startBranch;
	}

	public Commit getStartOfBranch() {
		return startOfBranch;
	}

	public void setBranchHead(Commit currentCommit) {
		this.branchHead = currentCommit;
	}

	public Commit getBranchHead() {
		return this.branchHead;
	}

	public String name() {
		return this.branchName;
	}

	public void setBranchPath(String path) {
		branchPath = path;
	}

	public String getBranchPath() {
		return branchPath;
	}

}
