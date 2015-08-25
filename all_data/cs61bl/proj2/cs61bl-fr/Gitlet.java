// these imports handle serializable
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.*;

import java.util.*;
import java.io.Serializable;
import java.io.FileNotFoundException;

public class Gitlet implements Serializable {

	private static final String GITLET_DIR = ".gitlet/";
	private static final String STAGED_DIR = GITLET_DIR + "staged/";
	private static final String COMMIT_DIR = GITLET_DIR + "commits/";
	private static final String STATE_FILE = GITLET_DIR + "state.ser";

	private ArrayList<String> staged;
	private ArrayList<String> toUntrack;

	private Commit head;
	private HashMap<Integer, Commit> commits;
	private HashMap<String, ArrayList<Integer>> commitMsgs;
	private String currentBranch;
	private HashMap<String, Integer> branches;

	private boolean conflicted;

	public Gitlet() {
		staged = new ArrayList<String>();
		toUntrack = new ArrayList<String>();

		head = null;
		commits = new HashMap<Integer, Commit>();
		commitMsgs = new HashMap<String, ArrayList<Integer>>();
		currentBranch = "master";
		branches = new HashMap<String, Integer>();

		conflicted = false;
	}

	/**
	 * Initializes the Gitlet version control system in the current directory.
	 * Gitlet automatically starts with an empty commit, containing no files and
	 * with the commit message "initial commit".
	 * 
	 * @throws IllegalStateException
	 *             If Gitlet has already been initialized (MESSAGE: A gitlet
	 *             version control system already exists in the current
	 *             directory.)
	 */
	public void init() throws IllegalStateException {
		checkNotConflicted();
		File gitletDir = new File(GITLET_DIR);
		if (gitletDir.exists()) {
			throw new IllegalStateException(
					"A gitlet version control system already exists in the current directory.");
		}
		gitletDir.mkdirs();

		Commit initialCommit = new Commit("initial commit", head, staged,
				toUntrack);
		commits.put(initialCommit.getID(), initialCommit);
		commitMsgs.put(initialCommit.getMessage(), new ArrayList<Integer>());
		commitMsgs.get(initialCommit.getMessage()).add(initialCommit.getID());
		File initialCommitDir = new File(COMMIT_DIR + initialCommit.getID()
				+ "/");
		initialCommitDir.mkdirs();

		head = initialCommit;
		branches.put(currentBranch, head.getID());
	}

	/**
	 * Adds a copy of the file as it currently exists to the staging area. If
	 * the file had been marked for untracking, then add just unmarks the file,
	 * and does not also add it to the staging area.
	 * 
	 * @param file
	 *            Name of file to add to staging area/ unmark from untracking.
	 * @throws IOException
	 *             If the file does not exist (MESSAGE: File does not exist.)
	 */
	public void add(String fileName) throws IOException {
		File source = new File(fileName);
		if (!source.exists()) {
			throw new IOException("File does not exist.");
		}
		if (toUntrack.contains(fileName)) {
			toUntrack.remove(fileName);
		} else {
			String stagedPath = STAGED_DIR + source;
			copyFile(fileName, stagedPath);
			staged.add(fileName);
		}
	}

	/**
	 * Updates files that have been staged. A commit will also start tracking
	 * any files that were staged but weren't tracked by its parent.
	 * 
	 * @param message
	 *            describes the changes to the files in the commit
	 * @throws IllegalStateException
	 *             If no files have been staged/ marked for untracking (MESSAGE:
	 *             No changes added to the commit.)
	 * @throws IllegalArgumentException
	 *             If the commit message is blank (MESSAGE: Please enter a
	 *             commit message.)
	 */
	public void commit(String message) throws IllegalStateException,
			IllegalArgumentException {
		if (staged.size() == 0 && toUntrack.size() == 0) {
			throw new IllegalStateException("No changes added to the commit");
		}
		if (message.length() == 0) {
			throw new IllegalArgumentException("Please enter a commit message.");
		}
		Commit newCommit = new Commit(message, head, staged, toUntrack);
		for (String fileName : staged) {
			String locationToTarget = COMMIT_DIR + newCommit.getID() + "/"
					+ fileName;
			copyFile(fileName, locationToTarget);

		}
		emptyStaged();
		toUntrack.clear();

		commits.put(newCommit.getID(), newCommit);
		if (commitMsgs.get(newCommit.getMessage()) == null) {
			commitMsgs.put(newCommit.getMessage(), new ArrayList<Integer>());
		}
		commitMsgs.get(newCommit.getMessage()).add(newCommit.getID());
		head = newCommit;
		branches.put(currentBranch, newCommit.getID());
		conflicted = false;
	}

	/**
	 * If passed the name of a file, takes the version of the file as it exists
	 * in the head commit and puts it in the working directory, overwriting any
	 * previous version.
	 * 
	 * Else if passed the name of a branch, takes all files in the commit at the
	 * head of the given branch and puts them in the working directory,
	 * overwriting any previous versions, additionally changing the current
	 * branch to the given branch.
	 * 
	 * @param name
	 *            The name of either the file or branch to be checked out (with
	 *            preference to the branch if both exist)
	 * @throws IllegalStateException
	 *             If the file does not exist in the previous commit, or if no
	 *             branch with that name exists, (MESSAGE: File does not exist
	 *             in the most recent commit, or no such branch exists.) or if
	 *             that branch is the current branch (MESSAGE: No need to
	 *             checkout the current branch.)
	 */
	public void checkout(String name) {
		if (branches.containsKey(name)) {
			checkNotConflicted();
			if (name.equals(currentBranch)) {
				throw new IllegalStateException(
						"No need to checkout the current branch.");
			}
			Commit branchHead = commits.get(branches.get(name));
			for (String fileName : branchHead.getTrackedFiles()) {
				checkout(branchHead.getID(), fileName);
			}
			head = branchHead;
			currentBranch = name;
			return;
		}

		if (!head.isTracked(name)) {
			throw new IllegalStateException(
					"File does not exist in the most recent commit,"
							+ " or no such branch exists.");
		}
		String toReturn = COMMIT_DIR + head.getTrackedCommitID(name) + "/"
				+ name;
		copyFile(toReturn, name);
	}

	/**
	 * Takes the version of the file as it exists in the commit with the given
	 * id and puts it in the working directory, overwriting any previous
	 * version.
	 * 
	 * @param id
	 *            The id of the commit to check out from
	 * @param name
	 *            The name of either the file to be checked out
	 * @throws IllegalStateException
	 *             If no commit with the given id exists, (MESSAGE: No commit
	 *             with that id exists.) or if the file does not exist in the
	 *             given commit (MESSAGE: File does not exist in that commit.)
	 */
	public void checkout(int id, String fileName) throws IllegalStateException {
		Commit target = commits.get(id);
		if (target == null) {
			throw new IllegalStateException("No commit with that id exists.");
		}
		if (!target.isTracked(fileName)) {
			throw new IllegalStateException(
					"File does not exist in that commit.");
		}
		String toReturn = COMMIT_DIR + target.getTrackedCommitID(fileName)
				+ "/" + fileName;
		copyFile(toReturn, fileName);
	}

	/**
	 * Marks the file for untracking; this means it will not be included in the
	 * upcoming commit, even if it was tracked by that commit's parent. If the
	 * file had been staged, it will instead just unstage it, and will not also
	 * mark it for untracking.
	 * 
	 * @param fileName
	 *            Name of the file to be untracked/unstaged
	 * @throws IllegalStateException
	 *             If the file is neither staged nor tracked by the head commit
	 *             (MESSAGE: No reason to remove the file.)
	 */
	private void rm(String fileName) throws IllegalStateException {
		boolean isStaged = staged.contains(fileName);
		boolean isTracked = head.isTracked(fileName);
		if (!isTracked && !isStaged) {
			throw new IllegalStateException("No reason to remove the file.");
		} else if (isStaged) {
			deleteFromStaged(fileName);
			staged.remove(fileName);
		} else {
			toUntrack.add(fileName);
		}
	}

	private void deleteFromStaged(String fileName) {
		File source = new File(STAGED_DIR + fileName);
		try {
			Files.deleteIfExists(source.toPath());
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Starting at the current head commit, displays information about each
	 * commit backwards along the commit tree until the initial commit.
	 */
	private void log() {
		Commit current = head;
		do {
			System.out.println("===");
			System.out.println(current);
			System.out.println("");
			current = current.getParent();
		} while (current != null);
	}

	/**
	 * Like log, except displays information about all commits ever made.
	 * Unordered.
	 */
	private void globalLog() {
		for (Commit commit : commits.values()) {
			System.out.println("===");
			System.out.println(commit);
			System.out.println("");
		}
	}

	/**
	 * Prints out the id of the commit that has the given commit message. If
	 * there are multiple such commits, it prints the ids out on separate lines.
	 * 
	 * @param message
	 *            The commit message to search for
	 * @throws IllegalStateException
	 *             If no such commit exists (MESSAGE: Found no commit with that
	 *             message.)
	 */
	private void find(String message) throws IllegalStateException {
		ArrayList<Integer> matched = commitMsgs.get(message);
		if (matched == null) {
			throw new IllegalStateException(
					"Found no commit with that message.");
		}
		for (int i : matched) {
			System.out.println(i);
		}
	}

	/**
	 * Displays what branches currently exist, and marks the current branch with
	 * a *. Also displays what files have been staged or marked for untracking.
	 */
	private void status() {
		System.out.println("=== Branches ===");
		for (String s : branches.keySet()) {
			if (currentBranch.equals(s)) {
				System.out.print("*");
			}
			System.out.println(s);
		}
		System.out.println("");
		System.out.println("=== Staged Files ===");
		for (String s : staged) {
			System.out.println(s);
		}
		System.out.println("");
		System.out.println("=== Files Marked For Untracking ===");
		for (String s : toUntrack) {
			System.out.println(s);
		}
		System.out.println("");
	}

	/**
	 * Creates a new branch with the given name and points it at the current
	 * head commit. Does not automatically switch to the newly created branch.
	 * 
	 * @param name
	 *            The name of branch to be created
	 * @throws IllegalStateException
	 *             If a branch with the given name already exists (MESSAGE: A
	 *             branch with that name already exists.)
	 */
	public void branch(String name) throws IllegalStateException {
		checkNotConflicted();
		if (branches.containsKey(name)) {
			throw new IllegalArgumentException(
					"A branch with that name already exists.");
		}
		branches.put(name, head.getID());
	}

	/**
	 * Deletes the branch with the given name.
	 * 
	 * @param name
	 *            The name of branch to be deleted
	 * @throws IllegalStateException
	 *             If a branch with the given name does not exist, (MESSAGE: A
	 *             branch with that name does not exist) or if the branch is the
	 *             current branch (MESSAGE: Cannot remove the current branch.)
	 */
	public void rmBranch(String branchName) throws IllegalStateException {
		checkNotConflicted();
		if (!branches.keySet().contains(branchName)) {
			throw new IllegalStateException(
					"A branch with that name does not exist.");
		}
		if (branchName.equals(currentBranch)) {
			throw new IllegalStateException("Cannot remove the current branch.");
		}
		branches.remove(branchName);
	}

	/**
	 * Checks out all the files tracked by the given commit. Also moves the
	 * current branch's head to that commit node.
	 * 
	 * @param id
	 *            The id of the commit to be reset to
	 * @throws IllegalStateException
	 *             If no commit with the given id exists (MESSAGE: No commit
	 *             with that id exists.)
	 */
	public void reset(int id) throws IllegalStateException {
		checkNotConflicted();
		if (!commits.containsKey(id)) {
			throw new IllegalStateException("No commit with that id exists.");
		}
		Commit target = commits.get(id);
		for (String fileName : target.getTrackedFiles()) {
			checkout(id, fileName);
		}
		branches.put(currentBranch, target.getID());
		head = target;
	}

	/**
	 * Merges files from the given branch into the current branch.
	 * 
	 * @param name
	 *            The name of branch to be merged from
	 * @throws IllegalStateException
	 *             If a branch with the given name does not exist, (MESSAGE: A
	 *             branch with that name does not exist.) or if the branch is
	 *             the current branch (MESSAGE: Cannot merge a branch with
	 *             itself) or if the commit has no changes (MESSAGE: [Just let
	 *             the no changes commit error message go through.)
	 */
	public void merge(String branchName) throws IllegalStateException {
		checkNotConflicted();
		Integer branchHeadID = branches.get(branchName);
		if (branchHeadID == null) {
			throw new IllegalStateException(
					"A branch with that name does not exist.");
		}
		Commit branchHead = commits.get(branchHeadID);
		Commit commonAncestor = earliestCommonAncestor(head, branchHead);

		if (commonAncestor == null || commonAncestor.equals(head)
				|| commonAncestor.equals(branchHead)) {
			throw new IllegalStateException(
					"Cannot merge a branch with itself.");
		}

		ArrayList<String> currentModified = head
				.getModificationDiff(commonAncestor);
		for (String fileName : branchHead.getModificationDiff(commonAncestor)) {
			// File modified in branch
			int commitID = branchHead.getTrackedCommitID(fileName);
			if (currentModified.contains(fileName)) {
				// File modified in current
				copyFile(COMMIT_DIR + commitID + "/" + fileName, fileName
						+ ".conflicted");
				conflicted = true;
			} else { // File removed or unmodified in current
				checkout(commitID, fileName);
				try {
					add(fileName);
					add(fileName);
				} catch (IOException e) {
					//Makes sure file is added to staged/not untracked
				}
			}
		}
		for (String fileName : branchHead.getRemovalDiff(commonAncestor)) {
			if (!currentModified.contains(fileName)) {
				// File removed in branch and unmodified in current
				try {
					rm(fileName);
					rm(fileName);
				} catch (IllegalStateException e) {
					//Makes sure file is removed from staged/untracked
				}
			}
		}

		if (!conflicted) {
			commit("Merged " + currentBranch + " with " + branchName + ".");
		}
	}

	/**
	 * Takes the commits of the current branch from the split point from the
	 * given branch and attaches them to the head of the given branch
	 * 
	 * @param name
	 *            The name of branch to be rebased to
	 * @throws IllegalStateException
	 *             If a branch with the given name does not exist, (MESSAGE: A
	 *             branch with that name does not exist.) or if the branch is
	 *             the current branch (MESSAGE: Cannot rebase a branch onto
	 *             itself.) or if the branch's head is in the history of the
	 *             current branch's head (MESSAGE: Already up-to-date.)
	 */
	public void rebase(String branchName) throws IllegalStateException {
		checkNotConflicted();
		Integer branchHeadID = branches.get(branchName);
		if (branchHeadID == null) {
			throw new IllegalStateException(
					"A branch with that name does not exist.");
		}
		Commit branchHead = commits.get(branchHeadID);
		if (branchHead.equals(head)) {
			throw new IllegalStateException(
					"Cannot rebase a branch onto itself.");
		}
		Commit commonAncestor = earliestCommonAncestor(head, branchHead);
		if (commonAncestor == null || commonAncestor.equals(branchHead)) {
			throw new IllegalStateException("Already up-to-date.");
		}
		if (commonAncestor.equals(head)) {
			branches.put(currentBranch, branchHeadID);
			checkout(branchName);
			return;
		}

		head = replay(branchHead, head, commonAncestor);
		branches.put(currentBranch, head.getID());
	}

	/* ///////////////////////// HELPER FUNCTIONS /////////////////////////// */

	/**
	 * Creates a new file at the given location.
	 * 
	 * @param locationToCreate
	 *            The filepath/location to create the file
	 */
	private void createFile(String locationToCreate) {
		File dst = new File(locationToCreate);
		if (dst.getParentFile() != null) {
			dst.getParentFile().mkdirs();
		}
		try {
			dst.createNewFile();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Copies a file from the given source location to the given target
	 * location.
	 * 
	 * @param locationToCreate
	 *            The filepath/location of the file to be copied
	 * @param locationToTarget
	 *            The filepath/location of the place to be copied to
	 */
	private void copyFile(String locationToSource, String locationToTarget) {
		File src = new File(locationToSource);
		File dst = new File(locationToTarget);
		if (dst.getParentFile() != null) {
			dst.getParentFile().mkdirs();
		}
		try {
			Files.copy(src.toPath(), dst.toPath(), REPLACE_EXISTING);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Empties the staging directory in the .gitlet folder.
	 * 
	 */
	private void emptyStaged() {
		for (String fileName : staged) {
			deleteFromStaged(fileName);
		}
		staged.clear();
	}

	/**
	 * Finds the earliest(most recent) common ancestor between two commits
	 * 
	 * @param CommitA
	 *            The first commit
	 * @param CommitB
	 *            The second commit
	 * @return The earliest(most recent) common ancestor of CommitA and CommitB
	 */
	private Commit earliestCommonAncestor(Commit commitA, Commit commitB) {
		if (commitA.equals(commitB)) {
			return commitA;
		}
		long aTime = commitA.getCommitTimeMilliseconds();
		long bTime = commitB.getCommitTimeMilliseconds();
		if (aTime > bTime) {
			return earliestCommonAncestor(commitA.getParent(), commitB);
		} else {
			return earliestCommonAncestor(commitA, commitB.getParent());
		}
	}

	/**
	 * Checks to make sure the gitlet control system does not have a merge
	 * conflict.
	 * 
	 * @throws IllegalStateException
	 *             If the gitlet control system has a merge conflict
	 */
	private void checkNotConflicted() throws IllegalStateException {
		if (conflicted) {
			throw new IllegalStateException("Cannot do this command"
					+ " until the merge conflict has been resolved.");
		}
	}

	/**
	 * Replays the commits between sourceBranchStart (inclusive) and
	 * sourceBranchEnd(exclusive) onto the destBranchHead commit
	 * 
	 * @param destBranchHead
	 *            The commit to replay to
	 * @param sourceBranchStart
	 *            The commit to begin replaying at (inclusive)
	 * @param sourceBranchEnd
	 *            The commit to stop replaying at (exclusive)
	 * @return The new head commit of the replayed branch
	 */
	private Commit replay(Commit destBranchHead, Commit sourceBranchStart,
			Commit sourceBranchEnd) {
		if (sourceBranchStart.equals(sourceBranchEnd)) {
			return destBranchHead;
		}
		Commit newHead = replay(destBranchHead, sourceBranchStart.getParent(),
				sourceBranchEnd);
		ArrayList<String> modificationDiff = sourceBranchStart
				.getModificationDiff(sourceBranchStart.getParent());
		Commit replayedStart = new Commit(sourceBranchStart.getMessage(),
				newHead, modificationDiff,
				sourceBranchStart.getRemovalDiff(sourceBranchStart.getParent()));
		
		for (String fileName : modificationDiff) {
			String locationToCopy = COMMIT_DIR + sourceBranchStart.getID()
					+ "/" + fileName;
			String locationToTarget = COMMIT_DIR + replayedStart.getID() + "/"
					+ fileName;
			copyFile(locationToCopy, locationToTarget);
		}
		commits.put(replayedStart.getID(), replayedStart);
		return replayedStart;
	}

	/**
	 * Serializes the given object to the given location.
	 * 
	 * @param obj
	 *            The object to serialize
	 * @param locationToSerialize
	 *            The location to serialize to
	 */
	private static void serialize(Object obj, String locationToSerialize) {
		try (FileOutputStream fileOut = new FileOutputStream(
				locationToSerialize);
				ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
			objectOut.writeObject(obj);
		} catch (FileNotFoundException e) {
			System.out
					.println("FileNotFoundException during serialization (Gitlet possibly uninitialized)");
		} catch (IOException e) {
			System.out.println("IOException during serialization.");
		}
	}

	/**
	 * Deserializes the object at a given location.
	 * 
	 * @param locationOfSerializedObject
	 *            The location of the serialized object
	 */
	private static Object deserialize(String locationOfSerializedObject) {
		File myStrFile = new File(locationOfSerializedObject);
		if (myStrFile.exists()) {
			try (FileInputStream fileIn = new FileInputStream(myStrFile);
					ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
				return objectIn.readObject();
			} catch (IOException e0) {
				System.out.println("IOException during deserialization");
			} catch (ClassNotFoundException e1) {
				System.out
						.println("ClassNotFoundException during deserialization");
			}
		}
		return null;
	}

	/**
	 * testing functions
	 */

	public Commit getHead() {
		return head;
	}

	/* /////////////////////////////// MAIN ///////////////////////////////// */

	public static void main(String[] args) {
		if (args.length == 0) { // Does nothing if there are no arguments
			return;
		}
		Gitlet gitlet = new Gitlet();

		if ((new File(STATE_FILE)).exists()) {
			gitlet = (Gitlet) deserialize(STATE_FILE);
		} else if (!args[0].equals("init")) {
			// Gitlet is uninitialized - returns if command is not init
			System.out.println("No Gitlet version control system "
					+ "has been initialized in this directory.");
			return;
		}

		try {
			switch (args[0]) {
			case "init":
				gitlet.init();
				break;
			case "add":
				gitlet.add(args[1]);
				break;
			case "commit":
				if (args.length < 2) {
					throw new IllegalArgumentException(
							"Please enter a commit message.");
				}
				gitlet.commit(args[1]);
				break;
			case "checkout":
				try {
					int id = Integer.parseInt(args[1]);
					gitlet.checkout(id, args[2]);
				} catch (NumberFormatException e) {
					gitlet.checkout(args[1]);
				}
				break;
			case "rm":
				gitlet.rm(args[1]);
				break;
			case "log":
				gitlet.log();
				break;
			case "global-log":
				gitlet.globalLog();
				break;
			case "find":
				gitlet.find(args[1]);
				break;
			case "status":
				gitlet.status();
				break;
			case "branch":
				gitlet.branch(args[1]);
				break;
			case "rm-branch":
				gitlet.rmBranch(args[1]);
				break;
			case "reset":
				try {
					int id = Integer.parseInt(args[1]);
					gitlet.reset(id);
				} catch (NumberFormatException e) {
					System.out.println("ERROR: Command 'reset' argument '"
							+ args[1] + "' is not a number.");
				}
				break;
			case "merge":
				gitlet.merge(args[1]);
				break;
			case "rebase":
				gitlet.rebase(args[1]);
				break;
			default:
				System.out.println("ERROR: Command '" + args[0]
						+ "' is unknown.");
				break;
			}
		} catch (IllegalStateException | IOException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("ERROR: Command '" + args[0]
					+ "' needs at least " + e.getMessage() + " arguments.");
		}

		serialize(gitlet, STATE_FILE);
	}
}
