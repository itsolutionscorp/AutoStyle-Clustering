import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;

public class Gitlet implements Serializable {
	/**
	 * Declares instance variables
	 */
	private Integer head;
	private String currentBranch;
	private HashMap<String, Integer> branchToID;
	private HashMap<String, Integer> fileToCommit;
	private HashSet<String> stagedFile;
	private HashSet<String> untrackedFile;
	private HashMap<String, HashSet<Integer>> messageToCommitID;
	private HashMap<Integer, Integer> commitToParent;
	private boolean isInit;
	private boolean isConflictedState;
	private boolean isReplay;

	/**
	 * Constructor called in init, initializes variables that keep track of the
	 * state of Gitlet.
	 */
	public Gitlet() {
		this.head = 0;
		this.currentBranch = "master";
		this.branchToID = new HashMap<String, Integer>();
		this.fileToCommit = new HashMap<String, Integer>();
		this.stagedFile = new HashSet<String>();
		this.untrackedFile = new HashSet<String>();
		this.messageToCommitID = new HashMap<String, HashSet<Integer>>();
		this.commitToParent = new HashMap<Integer, Integer>();
		this.isInit = true;
		this.isConflictedState = false;
		this.isReplay = false;
	}

	/**
	 * Debug methods
	 */
	/**
	 * Prints true if Gitlet is in a conflicted state, false otherwise.
	 */
	public void conflictedState() {
		System.out.println(isConflictedState);
	}

	/**
	 * Helper methods
	 */
	/**
	 * Computes a unique integer ID for each new commit using the time the
	 * commit is created, hashCode of the HashSet of files, and hashCode of the
	 * String commit message.
	 * 
	 * @param c
	 *            Calendar variable that keeps track of time the Commit was
	 *            made.
	 * @param file
	 *            HashSet of staged file names belonging to the Commit whose ID
	 *            is being computed.
	 * @param message
	 *            User-provided description of changes to files in the Commit
	 *            whose ID is being computed.
	 * @return A unique integer ID for the Commit.
	 */
	public static int computeCommitID(Calendar c, HashSet<String> file,
			String message) {
		int rtn = (int) c.getTimeInMillis();
		rtn = (int) (rtn - file.hashCode());
		rtn = (int) (rtn + message.hashCode());
		return Math.abs(rtn);
	}

	/**
	 * Copies the contents of one file to another file.
	 * 
	 * @param f1
	 *            The file whose contents are being copied to another file.
	 * @param f2
	 *            A file which will contain the contents of f1.
	 * @return true if fileCopy is successful, and false otherwise.
	 */
	public static boolean fileCopy(File f1, File f2) {
		try {
			Path parent = f2.toPath().getParent();
			if (parent != null) {
				parent.toFile().mkdirs();
			}
			Files.copy(f1.toPath(), f2.toPath(),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	/**
	 * Writes an object to a file (serializes the object).
	 * 
	 * @param fileName
	 *            String containing the name of file that object is written to.
	 * @param obj
	 *            Object that is to be written to file.
	 * @throws IOException
	 *             If there is an error in file creation.
	 */
	public static void infoOutput(String fileName, Object obj) {
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(obj);
			out.close();
			fileOut.close();
		} catch (IOException e) {

		}
	}

	/**
	 * Reads info from a .ser file containing the state of Gitlet (the Gitlet
	 * objext).
	 * 
	 * @param fileName
	 *            The name of the .ser file.
	 * @return The Gitlet object (not yet cast).
	 * @throws IOException
	 *             If the .ser file cannot be found.
	 * @throws ClassNotFoundException
	 *             If ObjectInputStream does not read in a valid Object.
	 */
	public static Object infoInput(String fileName) {
		Object input = null;
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			input = (Gitlet) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException e) {

		} catch (ClassNotFoundException e) {

		}
		return input;
	}

	/**
	 * Updates changes.
	 * 
	 * @param info
	 *            The Gitlet object whose information is being serialized.
	 */
	public static void autoSave(Gitlet info) {
		infoOutput(".gitlet/info.ser", info);
	}

	/**
	 * Loads instance of Gitlet.
	 * 
	 * @return The Gitlet object whose info is being deserialized.
	 */
	public static Gitlet autoLoad() {
		return (Gitlet) infoInput(".gitlet/info.ser");
	}

	/**
	 * Attempts to remove the file with the given file name from the staging
	 * area in .gitlet.
	 * 
	 * @param fileName
	 *            The name of the file that is being removed from the staging
	 *            area.
	 */
	public void rmStaging(String fileName) {
		File temp = new File(".gitlet/.staging/" + fileName);
		while (!temp.getPath().equals(".gitlet/.staging")) {

			try {
				Files.delete(temp.toPath());
			} catch (IOException e) {

			}
			temp = new File(temp.getParent());
		}
	}

	/**
	 * Gets the Commit object with the passed in integer ID.
	 * 
	 * @param ID
	 *            Unique Integer ID number identifying the Commit.
	 * @return The Commit object with the passed in ID.
	 */
	public Commit getCommitByID(Integer ID) {
		String dir = ".gitlet/.commit/" + Integer.toString(ID) + "/"
				+ Integer.toString(ID) + ".ser";
		Commit temp = Commit.commitInput(dir);
		return temp;
	}

	/**
	 * to be continued
	 * 
	 * @param currentBranch
	 *            name of the current branch.
	 * @param branchName
	 * 
	 * @return
	 */
	public Integer splitPoint(String currentBranch, String branchName) {
		Integer branchID = branchToID.get(branchName);
		Integer currID = branchToID.get(currentBranch);
		HashSet<Integer> checked = new HashSet<Integer>();
		while (currID != null) {
			checked.add(currID);
			currID = commitToParent.get(currID);
		}
		while (branchID != null) {
			if (!checked.add(branchID)) {
				break;
			}
			branchID = commitToParent.get(branchID);
		}
		return branchID;
	}

	/**
	 * Returns true if the given file has been modified between the passed in
	 * "old" commit and passed in "new" commit. False if the file has not been
	 * modified.
	 * 
	 * @param fileName
	 *            The name of the file being checked for modifications.
	 * @param oldCommit
	 *            The Integer ID number of the older commit.
	 * @param newCommit
	 *            The Integer ID number of the newer commit.
	 * @return true if the file has been modified, false if not.
	 * 
	 */
	public static boolean isModified(String fileName, Integer oldCommit,
			Integer newCommit) {
		File file1 = new File(".gitlet/.commit/" + Integer.toString(oldCommit)
				+ "/" + fileName);
		File file2 = new File(".gitlet/.commit/" + Integer.toString(newCommit)
				+ "/" + fileName);
		try {
			byte[] version1 = Files.readAllBytes(file1.toPath());
			byte[] version2 = Files.readAllBytes(file2.toPath());
			return !Arrays.equals(version1, version2);
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Check if toCheckID is in historyID's ancestry commits
	 * 
	 * @param toCheckID
	 * @param historyID
	 * @return true if toCheckID is in historyID's ancestry commits, false
	 *         otherwise
	 */
	public boolean isInHistory(Integer toCheckID, Integer historyID) {
		while (commitToParent.containsKey(historyID)) {
			if (toCheckID.equals(historyID)) {
				return true;
			}
			historyID = commitToParent.get(historyID);
		}
		return false;
	}

	/**
	 * Core function methods
	 */
	/**
	 * Creates a new gitlet version control system in the current directory.
	 * This system will automatically start with one commit: a commit that
	 * contains no files and has the commit message "initial commit"
	 */
	public static void init() {
		if (new File(".gitlet").exists()) {
			System.out
					.println("A gitlet version control system already exists in the current directory.");
		} else {
			new File(".gitlet").mkdirs();
			new File(".gitlet/.staging").mkdirs();
			new File(".gitlet/.commit").mkdirs();
			Gitlet git = new Gitlet();
			git.commit("initial commit");
		}

	}

	/**
	 * Adds a copy of the file as it exists in the working directory to the
	 * staging area.
	 * 
	 * @param fileName
	 *            String name of file whose copy is to be added to gitlet
	 *            staging area.
	 */
	public void add(String fileName) {
		if (!new File(fileName).exists()) {
			System.out.println("File does not exist.");
		} else if (untrackedFile.contains(fileName)) {
			untrackedFile.remove(fileName);
		} else {
			File toCopy = new File(fileName);
			File end = new File(".gitlet/.staging/" + fileName);
			fileCopy(toCopy, end);
			stagedFile.add(fileName);
			autoSave(this);
		}
	}

	/**
	 * Saves a backup of certain files so they can be restored at a later time.
	 * 
	 * @param message
	 *            The user-provided message describing the changes to the files
	 *            in the new commit.
	 * 
	 */
	public void commit(String message) {
		if (untrackedFile.isEmpty() && stagedFile.isEmpty() && !isInit
				&& !isReplay) {
			System.out.println("No changes added to the commit.");
		} else {
			this.isInit = false;
			this.isConflictedState = false;
			Calendar rightNow = Calendar.getInstance();
			Integer ID = computeCommitID(rightNow, stagedFile, message);
			String commitFolder = ".gitlet/.commit/" + Integer.toString(ID);
			new File(commitFolder).mkdirs();
			for (String file : stagedFile) {
				fileCopy(new File(file), new File(commitFolder + "/" + file));
				fileToCommit.put(file, ID);
				rmStaging(file);
			}
			for (String file : untrackedFile) {
				fileToCommit.remove(file);
			}
			untrackedFile.clear();

			Commit c = new Commit(head, ID, rightNow, message, fileToCommit);
			c.commitOutput(commitFolder + "/" + Integer.toString(ID) + ".ser");
			commitToParent.put(ID, head);
			head = ID;
			branchToID.put(currentBranch, ID);
			if (messageToCommitID.containsKey(message)) {
				messageToCommitID.get(message).add(ID);
			} else {
				HashSet<Integer> temp = new HashSet<Integer>();
				temp.add(ID);
				messageToCommitID.put(message, temp);
			}
			stagedFile.clear();
			autoSave(this);
		}
	}

	/**
	 * Ensures that the file with the passed in file name will not be included
	 * in the upcoming commit. Achieves this either by untracking the file or
	 * removing it from the staging area, if it was staged.
	 * 
	 * @param fileName
	 *            The name of the file to be removed.
	 */
	public void rm(String fileName) {
		if (!fileToCommit.keySet().contains(fileName)
				&& !stagedFile.contains(fileName)) {
			System.out.println("No reason to remove the file.");
		} else if (stagedFile.contains(fileName)) {
			rmStaging(fileName);
			stagedFile.remove(fileName);
		} else {
			untrackedFile.add(fileName);
		}
		autoSave(this);
	}

	/**
	 * Starting at the current head commit, display information about each
	 * commit backwards along the commit tree until the initial commit. Displays
	 * the commit id, the time the commit was made, and the commit message.
	 */
	public void log() {
		Commit temp = getCommitByID(head);
		temp.printLog();
		while (temp.getParentID() != 0) {
			temp = getCommitByID(temp.getParentID());
			temp.printLog();
		}
	}

	/**
	 * Displays information about all commits ever made in no particular order.
	 */
	public void globalLog() {
		for (Integer ID : commitToParent.keySet()) {
			getCommitByID(ID).printLog();
		}
	}

	/**
	 * Prints out the id of the commit that has the given commit message. If
	 * there are multiple such commits, prints the ids out on separate lines.
	 * 
	 * @param message
	 *            The user-provided message describing the changes to the files
	 *            in the given commit.
	 */
	public void find(String message) {
		if (!messageToCommitID.containsKey(message)) {
			System.out.println("Found no commit with that message.");
		} else {
			for (Integer ID : messageToCommitID.get(message)) {
				System.out.println(ID);
			}
		}
	}

	/**
	 * Displays what branches currently exist, and marks the current branch with
	 * a *. Also displays what files have been staged or marked for untracking.
	 */
	public void status() {
		System.out.println("=== Branches ===");
		System.out.println("*" + currentBranch);
		for (String branch : branchToID.keySet()) {
			if (branch.equals(currentBranch)) {
				continue;
			} else {
				System.out.println(branch);
			}
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		for (String fileName : stagedFile) {
			System.out.println(fileName);
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (String fileName : untrackedFile) {
			System.out.println(fileName);
		}
	}

	/**
	 * Checks out a file or branch. 1. If a file name is passed in: Takes the
	 * version of the file as it exists in the head commit, the front of the
	 * current branch, and puts it in the working directory, overwriting the
	 * version of the file that's already there if there is one. 2. If a branch
	 * name is passed in: Takes all files in the commit at the head of the given
	 * branch, and puts them in the working directory, ovewriting the versions
	 * of the files that are already there if they exist. Also, changes the
	 * current branch pointer to the given branch.
	 * 
	 * @param fileOrBranchName
	 *            The name of the file or branch being checked out.
	 */
	public void checkout(String fileorBranchName) {
		if (!branchToID.keySet().contains(fileorBranchName)
				&& !getCommitByID(head).getFileToCommit().containsKey(
						fileorBranchName)) {
			System.out
					.println("File does not exist in the most recent commit, or no such branch exists.");
		} else if (branchToID.keySet().contains(fileorBranchName)) {
			if (isConflictedState) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				return;
			}
			if (currentBranch.equals(fileorBranchName)) {
				System.out.println("No need to checkout the current branch.");
			}
			Commit temp = getCommitByID(branchToID.get(fileorBranchName));
			fileToCommit = temp.getFileToCommit();
			for (String fileName : temp.getFileToCommit().keySet()) {
				Integer ID = fileToCommit.get(fileName);
				fileCopy(new File(".gitlet/.commit/" + Integer.toString(ID)
						+ "/" + fileName), new File("./" + fileName));
			}
			currentBranch = fileorBranchName;
			head = branchToID.get(currentBranch);
			autoSave(this);
		} else if (getCommitByID(branchToID.get(currentBranch))
				.getFileToCommit().containsKey(fileorBranchName)) {
			Commit temp = getCommitByID(head);
			Integer ID = temp.getFileToCommit().get(fileorBranchName);
			fileCopy(new File(".gitlet/.commit/" + Integer.toString(ID) + "/"
					+ fileorBranchName), new File("./" + fileorBranchName));
		}
	}

	/**
	 * Takes the version of the file as it exists in the commit with the given
	 * id, and puts it in the working directory, overwriting the version of the
	 * file that's already there if there is one.
	 * 
	 * @param commit
	 *            String containing ID of commit in which file exists.
	 * @param fileName
	 *            String containing name of file to place in working directory
	 */
	public void checkout(String commit, String fileName) {
		Integer ID = Integer.parseInt(commit);
		if (!commitToParent.keySet().contains(ID)) {
			System.out.println("No commit with that id exists.");
		} else if (!getCommitByID(ID).getFileToCommit().containsKey(fileName)) {
			System.out.println("File does not exist in that commit.");
		} else {
			Commit temp = getCommitByID(ID);
			Integer newID = temp.getFileToCommit().get(fileName);
			fileCopy(new File(".gitlet/.commit/" + Integer.toString(newID)
					+ "/" + fileName), new File("./" + fileName));
		}
	}

	/**
	 * Adds a branch to the branchToID hashset (creates a new pointer from a
	 * branch name to a commit).
	 * 
	 * @param branchName
	 *            The name of the branch that will be added to the branchToID
	 *            hashset.
	 */
	public void branch(String branchName) {
		if (isConflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		if (branchToID.keySet().contains(branchName)) {
			System.out.println("A branch with that name already exists.");
		} else {
			branchToID.put(branchName, head);
		}
		autoSave(this);
	}

	/**
	 * Deletes the branch with the given name (removes the pointer associated
	 * with the branch). Does not delete commits created under the branch to be
	 * deleted.
	 * 
	 * @param branchName
	 *            The name of the branch being removed.
	 */
	public void rmBranch(String branchName) {
		if (isConflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		if (!branchToID.keySet().contains(branchName)) {
			System.out.println("A branch with that name does not exist.");
		} else if (currentBranch.equals(branchName)) {
			System.out.println("Cannot remove the current branch.");
		} else {
			branchToID.remove(branchName);
		}
		autoSave(this);
	}

	/**
	 * Reverts to the state of commit whose ID is passed in. Checks out all the
	 * files tracked by the commit whose ID is passed in, i.e. puts those files
	 * into the working directory (potentially overwriting files). Also moves
	 * the current branch's head to that commit node.
	 * 
	 * @param ID
	 *            String version of the unique Integer ID identifying the commit
	 *            whose state Gitlet is reverting to.
	 */
	public void reset(String ID) {
		if (isConflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		Integer cID = Integer.parseInt(ID);
		if (!commitToParent.keySet().contains(cID)) {
			System.out.println("No commit with that id exists.");
		} else {
			Commit temp = getCommitByID(cID);
			fileToCommit = temp.getFileToCommit();
			for (String fileName : fileToCommit.keySet()) {
				checkout(ID, fileName);
			}
			head = cID;
			branchToID.put(currentBranch, cID);
			autoSave(this);
		}
	}

	/**
	 * Merges modified files from the given branch into the current branch. If
	 * files have been modified both in the given branch and current branch
	 * since their split point (latest common ancestor), causes Gitlet to be in
	 * a conflicted state, during which only certain commands can be given.
	 * 
	 * @param branchName
	 *            The name of the branch to be merged with the current branch.
	 */
	public void merge(String branchName) {
		if (isConflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		if (!branchToID.keySet().contains(branchName)) {
			System.out.println("A branch with that name does not exist.");
		} else if (currentBranch.equals(branchName)) {
			System.out.println("Cannot merge a branch with itself.");
		} else {
			HashSet<String> modifiedOther = new HashSet<String>();
			HashSet<String> modifiedCurrent = new HashSet<String>();
			Commit other = getCommitByID(branchToID.get(branchName));
			Integer splitCommitID = splitPoint(currentBranch, branchName);
			for (String fileName : other.getFileToCommit().keySet()) {
				if (isModified(fileName, splitCommitID, other.getID())) {
					modifiedOther.add(fileName);
				}
			}
			for (String fileName : fileToCommit.keySet()) {
				if (isModified(fileName, splitCommitID, head)) {
					modifiedCurrent.add(fileName);
				}
			}
			HashSet<String> conflictedFile = new HashSet<String>();
			for (String fileName : modifiedOther) {
				if (!modifiedCurrent.contains(fileName)) {
					fileCopy(
							new File(".gitlet/.commit/"
									+ Integer.toString(other.getID()) + "/"
									+ fileName), new File("./" + fileName));
					add(fileName);
				} else if (modifiedCurrent.contains(fileName)) {
					fileCopy(
							new File(".gitlet/.commit/"
									+ Integer.toString(other.getID()) + "/"
									+ fileName), new File("./" + fileName
									+ ".conflicted"));
					conflictedFile.add(fileName);
				}
			}
			if (conflictedFile.isEmpty()) {
				commit("Merged " + currentBranch + " with " + branchName);
			} else {
				System.out.println("Encountered a merge conflict.");
				this.isConflictedState = true;
			}
			autoSave(this);
		}
	}

	/**
	 * Finds the split point of the current branch and the given branch, copies
	 * (replays) the current branch on top of the given branch, and changes the
	 * current branch pointer to point to the copy. Replayed commits are given
	 * new ID's and time stamps. If the current branch pointer is in the history
	 * of the given branch, moves the current branch to point to the same commit
	 * that the given branch points to. If the commit at the front of the given
	 * branch has files that have been modified since the split point, these
	 * these changes propagate through the replay.
	 * 
	 * @param branchName
	 *            The name of the branch to be rebased.
	 */
	public void rebase(String branchName) {
		if (isConflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		if (!branchToID.keySet().contains(branchName)) {
			System.out.println("A branch with that name does not exist.");
		} else if (currentBranch.equals(branchName)) {
			System.out.println("Cannot merge a branch with itself.");
		} else if (isInHistory(branchToID.get(branchName),
				branchToID.get(currentBranch))) {
			System.out.println("Already up-to-date.");
		} else if (isInHistory(branchToID.get(currentBranch),
				branchToID.get(branchName))) {
			reset(Integer.toString(branchToID.get(branchName)));
		} else {
			isReplay = true;
			Integer splitID = splitPoint(currentBranch, branchName);
			ArrayList<Integer> currBranch = new ArrayList<Integer>();
			Integer afterSplit = new Integer(head);
			currBranch.add(0, afterSplit);
			while (true) {
				if (commitToParent.get(afterSplit) == splitID) {
					break;
				} else {

					afterSplit = commitToParent.get(afterSplit);
					currBranch.add(0, afterSplit);
				}
			}
			Commit other = getCommitByID(branchToID.get(branchName));
			HashSet<String> modifiedOther = new HashSet<String>();
			for (String fileName : other.getFileToCommit().keySet()) {
				if (isModified(fileName, splitID, branchToID.get(branchName))) {
					modifiedOther.add(fileName);
				}
			}
			HashMap<String, Integer> currFileToCommit = new HashMap<String, Integer>();
			currFileToCommit.putAll(fileToCommit);
			this.head = branchToID.get(branchName);
			for (Integer ID : currBranch) {
				Commit temp = getCommitByID(ID);
				HashMap<String, Integer> tempFileToCommit = temp
						.getFileToCommit();
				for (String fileName : tempFileToCommit.keySet()) {
					if (!isModified(fileName, splitID, ID)
							&& modifiedOther.contains(fileName)) {
						fileToCommit.put(fileName,
								other.getFileToCommit().get(fileName));
					}
				}
				commit(temp.getMessage());
				fileToCommit.putAll(currFileToCommit);
			}
			isReplay = false;
			autoSave(this);
		}
	}

	/**
	 * main method that controls the interaction with user input
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please enter a command.");
			return;
		} else {
			switch (args[0]) {
			case "init":
				init();
				break;
			case "add":
				autoLoad().add(args[1]);
				break;
			case "commit":
				if (args.length == 1 || args[1].trim().isEmpty()) {
					System.out.println("Please enter a commit message.");
					break;
				}
				autoLoad().commit(args[1]);
				break;
			case "rm":
				autoLoad().rm(args[1]);
				break;
			case "log":
				autoLoad().log();
				break;
			case "global-log":
				autoLoad().globalLog();
				break;
			case "find":
				autoLoad().find(args[1]);
				break;
			case "status":
				autoLoad().status();
				break;
			case "checkout":
				if (args.length == 2) {
					autoLoad().checkout(args[1]);
				} else {
					autoLoad().checkout(args[1], args[2]);
				}
				break;
			case "branch":
				autoLoad().branch(args[1]);
				break;
			case "rm-branch":
				autoLoad().rmBranch(args[1]);
				break;
			case "reset":
				autoLoad().reset(args[1]);
				break;
			case "merge":
				autoLoad().merge(args[1]);
				break;
			case "rebase":
				autoLoad().rebase(args[1]);
				break;
			default:
				System.out.println("No command with that name exists.");
				break;
			}
		}
	}
}
