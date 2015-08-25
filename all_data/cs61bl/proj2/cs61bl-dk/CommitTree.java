import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * A class for representing the tree of Commits in Gitlet. Contains the branches
 * of the tree, the Commits, and various methods to manipulate the tree and
 * retrieve data.
 * 
 * @authors Nikat Patel 	cs61bl-do
 * 			Rowan Sandhu 	cs61bl-de
 * 			Ryan Panwar 	cs61bl-dk
 *          Shaswat Shah 	cs61bl-mj
 */

public class CommitTree implements Serializable {

	///////////////////////////////////////////////////////////////
	/******************** CommitTree object **********************/
	///////////////////////////////////////////////////////////////

	/**
	 * CommitTree instance variables
	 * 
	 * Classes: Commit: Represents a commit with all the relevant information
	 */
	private HashMap<String, Commit> branches;
	private ArrayList<Commit> commits;
	private String currBranch;

	/**
	 * Constructor for the CommitTree class Creates initial commit and master
	 * branch pointing to initial commit
	 */
	public CommitTree() {

		Commit initial = new Commit(new HashMap<String, String>(), null, "initial commit", 0);
		commits = new ArrayList<Commit>();
		commits.add(initial);
		currBranch = "master";
		branches = new HashMap<String, Commit>();
		branches.put(currBranch, initial);
	}

	///////////////////////////////////////////////////////////////
	/********************* Gitlet commands ***********************/
	///////////////////////////////////////////////////////////////

	/**
	 * Prints information about each commit starting at the current head commit
	 * and tracking backwards along the commit tree until the initial commit
	 */
	public void log() {

		Commit c = branches.get(currBranch);
		while (true) {
			System.out.println("===\nCommit " + c.getId() + "\n" + c.getTimeString() + "\n" + c.getMessage());

			c = c.getParent();
			if (c == null)
				break;

			System.out.println();
		}
	}

	/**
	 * Prints information about all commits in no particular order
	 */
	public void globalLog() {

		for (Commit c : commits) {
			System.out.println("===\nCommit " + c.getId() + "\n" + c.getTimeString() + "\n" + c.getMessage());

			if (commits.indexOf(c) == commits.size() - 1)
				break;

			System.out.println();
		}
	}

	/**
	 * Prints the id(s) of the commit(s) in the tree with the given message
	 * 
	 * @param message
	 *            commit message to find in the commit tree
	 * @throws GitletException
	 *             in case no commit with the given message is found
	 */
	public void find(String message) throws GitletException {

		int found = 0;
		for (Commit c : commits) {
			if (c.getMessage().equals(message)) {
				System.out.println(c.getId());
				found = 1;
			}
		}

		if (found == 0)
			throw new GitletException("Found no commit with that message.");
	}

	/**
	 * Creates a new branch with the given name which points to the current
	 * commit
	 * 
	 * @param newBranch
	 *            name of the new branch
	 * @throws GitletException
	 *             if a branch with name already exists
	 */
	public void createBranch(String newBranch) throws GitletException {

		if (branches.containsKey(newBranch))
			throw new GitletException("A branch with that name already exists.");

		branches.put(newBranch, branches.get(currBranch));
	}

	/**
	 * Removes the given branch pointer from the commit tree
	 * 
	 * @param branchToRemove
	 *            name of branch to remove
	 * @throws GitletException
	 *             if attempting to remove the current branch
	 * @throws GitletException
	 *             if attempting to remove a branch that does not exist
	 */
	public void rmBranch(String branchToRemove) throws GitletException {

		if (branchToRemove.equals(currBranch))
			throw new GitletException("Cannot remove the current branch.");

		if (branches.remove(branchToRemove) == null)
			throw new GitletException("A branch with that name does not exist.");
	}

	///////////////////////////////////////////////////////////////
	/****************** Commit tree operations *******************/
	///////////////////////////////////////////////////////////////

	/**
	 * Adds a new commit to the commit tree, updates current branch pointer to
	 * the added commit
	 *
	 * @param c
	 *            Commit to add
	 */
	public void addCommit(Commit c) {
		
		commits.add(c);
		branches.put(currBranch, c);
	}

	/**
	 * Moves the current branch pointer to the given commit. Used in reset
	 * 
	 * @param c
	 *            new head of the current branch
	 */
	public void setCurrCommit(Commit c) {

		branches.put(currBranch, c);
	}
	
	/**
	 * Sets the current branch to the branch with the given name. Used in
	 * checkout [branch name]
	 * 
	 * @param branchName
	 *            name of commit to set currBranch to
	 */
	public void setCurrBranch(String branchName) throws GitletException {

		currBranch = branchName;
	}

	/**
	 * Checks if given int is the id of a commit in the commit tree
	 * 
	 * @param commitId
	 *            name of the branch to look for
	 * 
	 * @return true if branch found, false otherwise
	 */
	public Commit getCommit(int id) {
		
		for (Commit c : commits)
			if (c.getId() == id)
				return c;
		
		return null;
	}
	
	/**
	 * Getter for current branch
	 * 
	 * @return the name of the current branch
	 */
	public String getCurrBranch() {
		
		return currBranch;
	}

	/**
	 * Getter for the head commit of the given branch
	 * 
	 * @return the head commit of the given branch, if it exists, else null
	 */
	public Commit getBranchHead(String branch) {
		
		return branches.get(branch);
	}

	/**
	 * Finds the split point between the current branch and the specified
	 * branch
	 * 
	 * @param otherBranch
	 *            the branch to trace back from
	 */
	public Commit getSplitPoint(String otherBranch) {
		
		HashSet<Commit> currentBranchCommits = new HashSet<Commit>();
		Commit c = branches.get(currBranch);
		
		while (c != null) {
			currentBranchCommits.add(c);
			c = c.getParent();
		}
		
		c = branches.get(otherBranch);
		while (c != null) {
			if (currentBranchCommits.contains(c))
				break;
			c = c.getParent();
		}
		
		return c;
	}

	/**
	 * Helper method for status command
	 * Prints all branches with new lines after each branch
	 * Marks current branch with "*"
	 */
	public void printBranches() {
		
		for (String s : branches.keySet()) {
			if (s.equals(currBranch))
				System.out.print("*");
			System.out.println(s);
		}
	}
	
	/**
	 * Helper method for merge and checkout commands
	 * Checks if given string is the name of a branch in the commit tree
	 * 
	 * @param branchName
	 *            name of the branch to look for
	 * 
	 * @return true if branch found, false otherwise
	 */
	public boolean isBranch(String branchName) {
		
		return branches.containsKey(branchName);
	}
}
