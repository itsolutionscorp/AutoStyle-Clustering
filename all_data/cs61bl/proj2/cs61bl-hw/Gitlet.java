// add import here
import java.io.*;
import java.nio.file.*;
// add import here
import java.util.*;

/**
 * Main class of Gitlet. Controls all of the other classes.
 */
public class Gitlet implements Serializable {

	private HashMap<String, Commit> branches;
	private Commit masterBranch;
	private int commitIDSerialize; // unique int id. Same as commitID but saved
									// when program exits
	public static int commitID;
	private Commit nextCom;
	private boolean conflicted;
	private String currentBranchName;
	static BufferedWriter w;
	private ArrayList<Commit> commits; // commits.get(i) returns commit with id
										// i
	private HashSet<String> stagingArea;

	
	
	
	
	/**
	 * Creates a gitlet repository. returns false if .gitlet folder has already
	 * been created.
	 * 
	 * @return True if successfully created repository (hasn't created
	 *         repository yet)
	 * @throws IOException
	 *             If there was an error creating the .gitlet folder
	 */
	public boolean createRepository() throws IOException {
		if (FileIO.pathExists(FileIO.GITLET_PATH))
			return false;

		FileIO.createGitletFolder();
		stagingArea = new HashSet<String>();
		commits = new ArrayList<Commit>();
		branches = new HashMap<String, Commit>();
		branches.put("master", null);
		switchCurrentBranch("master");
		resetNextCommit(); // would be the same as init next commit in this case
		Commands.commit("initial commit");
		return true;
	}

	/**
	 * Writes out the serialized object (Gitlet) to it's location in the .gitlet
	 * folder (.gitlet/gitlet.ser)
	 * 
	 * @param g
	 *            The Gitlet object to write out
	 */
	public static void writeOut(Gitlet g) {
		g.commitIDSerialize = commitID;
		try {
			FileOutputStream fileOut = new FileOutputStream(
					FileIO.SERIALIZED_LOCATION);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(g);
			out.close();
			fileOut.close();
		} catch (Exception e) {

		}
	}

	/**
	 * Reads the serialized Gitlet object back in and returns it. If the Gitlet
	 * object has not been serialized at .gitlet/gitlet.ser, Will return a new
	 * Gitlet object.
	 * 
	 * @return Gitlet object that was read in.
	 */
	public static Gitlet readIn() {
		Path path = Paths.get(FileIO.SERIALIZED_LOCATION);
		Gitlet result = null;
		if (Files.exists(path)) {
			try {
				FileInputStream fileIn = new FileInputStream(
						FileIO.SERIALIZED_LOCATION);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				result = (Gitlet) in.readObject();
				commitID = result.commitIDSerialize;
				in.close();
				fileIn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			result = new Gitlet();
		}
		return result;
	}
	
	
	
	
	
	/**
	 * Returns the name of the current branch
	 * 
	 * @return name of the current branch
	 */
	public String getCurrentBranchName() {
		return currentBranchName;
	}

	/**
	 * Returns a commit with the given ID.
	 * 
	 * @param id
	 *            Id of the commit
	 * @return the Commit that corresponds to the ID
	 */
	public Commit getCommit(int id) {
		if (id >= commits.size() || id < 0)
			return null;

		return commits.get(id);
	}

	/**
	 * Returns the commit that corresponds to the name. Note that two different
	 * branches may point to the same commit, so getBranch(b1) == getBranch(b2)
	 * does not mean b1 and b2 are the same branches
	 * 
	 * @param name
	 *            Name of the branch
	 * @return Branch (commit pointer) that corresponds to the name
	 */
	public Commit getBranch(String name) {
		return branches.get(name);
	}

	/**
	 * Determines whether the given branch exists.
	 * 
	 * @param name
	 *            Name of the branch
	 * @return True if there exists a branch with the given name
	 */
	public boolean containsBranch(String name) {
		return branches.containsKey(name);
	}

	/**
	 * Adds a branch at the commit headPointer. Basically the same as moveBranch
	 * 
	 * @param name
	 *            Name of the branch
	 * @param headPointer
	 *            Commit to be associated with the branch
	 */
	public void addBranch(String name, Commit headPointer) {
		branches.put(name, headPointer);
	}

	/**
	 * Removes the branch from the list of branches. The commits inside the
	 * branch remain even after the branch is deleted.
	 * 
	 * @param name
	 *            name of the branch
	 */
	public void removeBranch(String name) {
		branches.remove(name);
	}

	/**
	 * Same as addBranch. May delete
	 * 
	 * @param name
	 *            Name of the branch
	 * @param newPointer
	 *            new commit pointer for the branch
	 */
	public void moveBranch(String name, Commit newPointer) {
		branches.put(name, newPointer);
	}

	/**
	 * Switches the current branch to branch name. If there were staged files or
	 * anything that made the commit 'unclean', the parent of the commit will be
	 * switched from the head of the old branch to head of the new branch.
	 * 
	 * @param branchName
	 *            Name of the branch to switch to.
	 */
	public void switchCurrentBranch(String branchName) {
		currentBranchName = branchName;
		if (nextCom != null) {
			nextCom.setParent(getCurrentBranch());
		}
		// resetNextCommit();

	}

	/**
	 * Returns the next commit (unsubmitted commit) The next commit will become
	 * the head pointer for the current branch after calling Commands.commit()
	 * 
	 * @return The next commit
	 */
	public Commit getNextCommit() {
		return nextCom;
	}

	/**
	 * Overwrites the next commit and replaces it with the commit passed into
	 * the function. Used only by rebase.
	 * 
	 * @param nextCommit
	 *            The commit to replace the old nextCommit with.
	 */
	public void setNextCommit(Commit nextCommit) {
		this.nextCom = nextCommit;
	}


	/**
	 * Generates the next commit ID. Will automatically prepare itself for the
	 * next time it is called.
	 * 
	 * @return the next commit ID.
	 */
	public static int nextCommitID() {
		int id = commitID;
		commitID++;
		return id;
	}

	/**
	 * Creates a new commit and sets the old nextCommit as the head pointer of
	 * the current branch.
	 */
	public void newCommit() {
		branches.put(currentBranchName, nextCom);
		resetNextCommit();
	}

	/**
	 * switches the head of the current branch to commit
	 * 
	 * @param commit
	 *            Commit that the current branch should now point at
	 */
	public void switchCommit(Commit commit) {
		branches.put(currentBranchName, commit);
	}

	/**
	 * Resets the head commit. Different from newCommit() as it doesn't set the
	 * old nextCommit as the head pointer of the current branch.
	 */
	private void resetNextCommit() {
		nextCom = new Commit(getCurrentBranch(), nextCommitID());
		commits.add(nextCom);
	}

	/**
	 * Retrieves the head commit of the current branch.
	 * 
	 * @return The head commit of the current branch.
	 */
	public Commit getCurrentBranch() {
		return branches.get(currentBranchName);
	}

	/**
	 * Determines whether gitlet is currently in a conflicted state (merge
	 * conflicts)
	 * 
	 * @return True if gitlet is currently conflicted.
	 */
	public boolean isConflicted() {
		return conflicted;
	}

	/**
	 * Sets gitlet's conflicted state to the value of parameter conflicted.
	 * 
	 * @param conflicted
	 *            Whether gitlet should be in a conflicted state
	 */
	public void setConflicted(boolean conflicted) {
		this.conflicted = conflicted;
	}

	/**
	 * Adds a file to the staging area. Assumes the file is in the PWD.
	 * 
	 * @param name
	 *            Path of file
	 * @throws IOException
	 *             If there was an error copying the file to the staging area.
	 */
	public void addStagingArea(String name) throws IOException {
		addStagingArea(name, name);
	}

	/**
	 * Determines if file is currently staged.
	 * 
	 * @param file
	 *            Path of file
	 * @return True if the file is currently staged
	 */
	public boolean isStaged(String file) {
		return stagingArea.contains(file);
	}

	/**
	 * Name != path in the case of merge and rebase
	 * 
	 * @param name
	 *            Path of file relative to the folder the file exists in.
	 * @param path
	 *            Path of file relative to the PWD
	 * @throws IOException
	 *             If there was an error copying the file to the staging area.
	 */
	public void addStagingArea(String name, String path) throws IOException {
		FileIO.copy(path, FileIO.STAGING_AREA + "/" + name);
		stagingArea.add(name);
	}

	/**
	 * Resets the staging area for the next commit. Deletes the entire
	 * stagingArea folder
	 */
	public void resetStagingArea() {
		try {
			FileIO.delete(new File(FileIO.STAGING_AREA));
		} catch (IOException e) {
			e.printStackTrace();
		}
		stagingArea = new HashSet<String>();
	}

	/**
	 * Retrieves a HashMap of branches that currently exist.
	 * 
	 * @return HashMap of branches
	 */
	public HashMap<String, Commit> getBranches() {
		return branches;
	}

	/**
	 * Retrieves the list of all of the commits. (including unsubmitted commits)
	 * 
	 * @return ArrayList of all commits
	 */
	public ArrayList<Commit> getCommits() {
		return commits;
	}

	/**
	 * Returns a set of paths that currently exist in the staging area
	 * 
	 * @return the set of paths inside the staging area
	 */
	public HashSet<String> getStagingArea() {
		return stagingArea;
	}

	/**
	 * Main method of gitlet
	 * 
	 * @param args
	 *            args[0] is the command, and args[1:] are the args for the
	 *            command
	 */
	public static void main(String[] args) {
		Gitlet g = readIn();

		Commands.gitlet = g;
		if (args.length == 0) {
			System.out.println("Please enter a command.");
			return;
		}
		String command = args[0];

		String args2[] = new String[args.length - 1];
		for (int i = 1; i < args.length; i++) {
			args2[i - 1] = args[i];
		}
		Commands.callCommand(command, args2);
		writeOut(g);
	}

}
