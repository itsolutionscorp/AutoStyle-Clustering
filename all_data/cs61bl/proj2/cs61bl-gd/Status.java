import java.util.*;
import java.io.Serializable;

public class Status implements Serializable {
	private static final long serialVersionUID = 19960408L;
	private int numOfCommit;
	private HashSet<String> branchTable;
	private HashSet<Integer> commitTable;
	private Branch activeBranch;
	private HashSet<String> untrackedFile;
	private boolean isConflicted;
	private HashMap<String, Integer> stageFilePaths;

	public Status() {
		numOfCommit = 0;
		branchTable = new HashSet<String>();
		commitTable = new HashSet<Integer>();
		untrackedFile = new HashSet<String>();
		isConflicted = false;
		stageFilePaths = new HashMap<String, Integer>();
	}

	// commit
	public void deleteCommit(int id) {
		commitTable.remove(id);
	}

	public void addCommit(Commit toAdd) {
		commitTable.add(toAdd.getId());
	}

	public boolean containsCommit(int id) {
		return commitTable.contains(id);
	}

	public HashSet<Integer> getCommitTable() {
		return commitTable;
	}

	public int numOfCommitted() {
		return numOfCommit;
	}

	public void addOneCommitTime() {
		numOfCommit++;
	}

	// branch
	public Branch getActiveBranch() {
		return activeBranch;
	}

	public void updateActiveBranch(Branch branchName) {
		activeBranch = branchName;
	}

	public void removeBranch(String branchName) {
		branchTable.remove(branchName);
	}

	public void addBranch(Branch toAdd) {
		FileOperation.write(toAdd, ".gitlet/branch/" + toAdd.getName());
		branchTable.add(toAdd.getName());
	}

	public boolean containsBranch(String branchName) {
		return branchTable.contains(branchName);
	}

	public HashSet<String> getBranchTable() {
		return branchTable;
	}

	public void deleteBranch(String BranchName) {
		FileOperation.delete(".gitlet/branch/" + BranchName + ".ser");
		branchTable.remove(BranchName);
	}

	// track
	public void untrack(String fileName) {
		untrackedFile.add(fileName);
	}

	public void recoverUntrack(String fileName) {
		if (untrackedFile.contains(fileName)) {
			untrackedFile.remove(fileName);
		} else {
			System.out.println("The file is not marked untrack.");
		}
	}
	
	public boolean isUntrackEmpty() {
		return untrackedFile.size() == 0;
	}

	public boolean doesUntrack(String fileName) {
		return untrackedFile.contains(fileName);
	}

	public void emptyUntrack() {
		untrackedFile = new HashSet<String>();
	}

	public HashSet<String> getUntrack() {
		return untrackedFile;
	}

	// stage operation
	public HashMap<String, Integer> getFilePaths() {
		return stageFilePaths;
	}

	public void addNewStageFilePath(String pathName, int futureCommitId) {
		stageFilePaths.put(pathName, futureCommitId);
	}

	public void removeStageFilePath(String pathName) {
		stageFilePaths.remove(pathName);
	}

	public void resetFilePaths() {
		stageFilePaths = new HashMap<String, Integer>();
	}

	// conflict
	public boolean isConflicted() {
		return isConflicted;
	}

	public void updateIsConflicted() {
		isConflicted = !isConflicted;
	}

	// serialize
	public void serializeStatus() {
		FileOperation.write(getActiveBranch(), ".gitlet/branch/"
				+ getActiveBranch().getName());
		FileOperation.write(this, ".gitlet/myStatus");
	}
}
