import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*Gitlet.java*/

/**
 * Gitlet
 * 
 * In which we frantically attempt to figure out how to use the git version
 * control software while simultaneously attempting to rip it off.
 * 
 * @authors Alan Kwok, Pratyusha Gogulapati, Ranju Subramani, Timothy Guan
 */
public class Gitlet implements Serializable {
	/**
	 * Class variables and helper methods will be defined here. STAGE_DIR -
	 * directory of stage.
	 */
	private static final String STAGE_DIR = ".gitlet" + File.separator
			+ "stage" + File.separator;

	/**
	 * Serializes a Gitlet object.
	 * 
	 * @param curr
	 *            The Gitlet object to be serialized.
	 */
	private static void serialize(Gitlet curr) {
		try {
			FileOutputStream fileOut = new FileOutputStream(".gitlet"
					+ File.separator + ".main" + File.separator + "gitlet.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(curr);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			return;
		}
	}

	/**
	 * Deletes the file and all files inside it, if it is a directory. Taken
	 * from GitletTest.java by Joseph Moghadam.
	 * 
	 * @throws IOException
	 */
	private static void recursiveDelete(File d) throws IOException {
		if (d.isDirectory()) {
			for (File f : d.listFiles()) {
				recursiveDelete(f);
			}
		}
		if (!d.delete()) {
			throw new IOException("Failed to delete file " + d.getPath());
		}
	}

	/**
	 * Helper function for printing the log.
	 */
	private static void printLog(CommitTreeNode y) {
		while (y != null) {
			System.out.println("===");
			System.out.println("Commit " + Integer.toString(y.getId()));
			System.out.println(y.getTime());
			System.out.println(y.getMessage());
			System.out.println("");
			y = y.getParent();
		}
	}

	/**
	 * Finds the most recent common ancestor of two CommitTreeNodes.
	 * 
	 * @param current
	 *            - branch used in working directory
	 * @param given
	 *            - branch that is given
	 * @return the first common ancestor for both branches
	 */
	private static CommitTreeNode splitPointFinder(CommitTreeNode current,
			CommitTreeNode given) {
		int currentTailID = current.getId();
		int givenTailID = given.getId();
		if (currentTailID == givenTailID) {
			return current;
		}
		if (currentTailID < givenTailID) {
			return splitPointFinder(current, given.getParent());
		} else {
			return splitPointFinder(current.getParent(), given);
		}
	}

	/**
	 * Instance variables will be defined here. 
	 * head - The current commit that you're working with. 
	 * currBranch - The current branch. 
	 * branchNames - A hashmap mapping branch names to Branch objects. 
	 * commitMessages - A hashmap mapping commit messages to ArrayLists of CommitTreeNodes.
	 * idHash - A hashmap mapping commit IDs to CommitTreeNode objects.
	 * staged - A hashmap mapping file names to staged files.
	 * tracking - A hashmap mapping file names to tracking files.
	 * untracked - A hashmap mapping file names to currently untracked files.
	 * lastID - the most recently assigned commit ID.
	 * conflicted - a boolean representing the state (conflicted or otherwise).
	 * merging - a boolean telling commit whether it is currently merging.
	 */
	private CommitTreeNode head;
	private Branch currBranch;
	private HashMap<String, Branch> branchNames;
	private HashMap<String, ArrayList<CommitTreeNode>> commitMessages;
	private HashMap<Integer, CommitTreeNode> idHash;
	private HashMap<String, File> staged;
	private HashMap<String, File> tracking;
	private HashMap<String, File> untracked;
	private int lastID = 0;
	private boolean conflicted;
	private boolean merging;

	/**
	 * Creates a new Gitlet version control system, initializes tree. Starts
	 * with the initial commit. If a Gitlet already exists, print and return.
	 * some of the following folders can be deleted. files will be the directory
	 * that stores all the files submitted. treeNodes keeps track of the Commit
	 * Tree. stage is the staging directory. logs keeps all the logs. main keeps
	 * all the stored objects. 
	 * Citation - http://www.tutorialspoint.com/java/java_serialization.htm
	 */
	public Gitlet() {
		File f = new File(".gitlet" + File.separator);
		if (f.exists()) {
			System.out
					.println("A gitlet version control system already exists in the current directory.");
			return;
		}
		f.mkdirs();
		f = new File(".gitlet" + File.separator + "files");
		f.mkdirs();
		f = new File(STAGE_DIR);
		f.mkdirs();
		f = new File(".gitlet" + File.separator + ".main");
		f.mkdirs();
		this.branchNames = new HashMap<String, Branch>();
		this.commitMessages = new HashMap<String, ArrayList<CommitTreeNode>>();
		this.staged = new HashMap<String, File>();
		this.tracking = new HashMap<String, File>();
		this.untracked = new HashMap<String, File>();
		this.head = new CommitTreeNode();
		ArrayList<CommitTreeNode> store = new ArrayList<CommitTreeNode>();
		store.add(head);
		this.commitMessages.put(head.getMessage(), store);
		this.currBranch = new Branch("master", this.head);
		this.branchNames.put("master", this.currBranch);
		this.idHash = new HashMap<Integer, CommitTreeNode>();
		this.idHash.put(this.head.getId(), this.head);
		this.conflicted = false;
		this.merging = false;
		return;
	}

	/**
	 * Adds a copy of the file as it currently exists to the staging area. Sets
	 * node boolean staged to true. Citation -
	 * https://docs.oracle.com/javase/tutorial/essential/io/copy.html
	 * 
	 * @param fileName
	 *            The name of the file to be added.
	 */
	private void add(String fileName) {
		File f = new File(fileName);
		if (this.untracked.containsKey(fileName)) {
			this.tracking.put(fileName, new File(this.untracked.get(fileName).toString()));
			this.untracked.remove(fileName);
			return;
		}
		if (!f.exists()) {
			System.out.println("File does not exist.");
			return;
		}
		try {
			File destination = new File(STAGE_DIR + fileName);
			if (!destination.exists()) { // throw error?
				destination.mkdirs();
			}
			Files.copy(f.toPath(), destination.toPath(), REPLACE_EXISTING);
			this.staged.put(fileName, destination);
		} catch (IOException e) {
			return;
		}
		return;
	}

	/**
	 * Saves backup for restoration at a later time. Uses multiple hashmaps in
	 * order to decrease runtime. Resets the state of Gitlet after every commit,
	 * besides storing information in CommitTreeNodes.
	 * 
	 * @param message
	 *            The identification message attached to a commit.
	 */
	private void commit(String message) {
		if (message.isEmpty()) {
			System.out.println("Please enter a commit message.");
			return;
		}
		if (!this.merging && this.staged.isEmpty() && this.untracked.isEmpty()) {
			System.out.println("No changes added to the commit.");
			return;
		}
		HashMap<String, File> commitFiles = new HashMap<String, File>();
		int id = this.lastID + 1;
		this.lastID++;
		String folderName = ".gitlet" + File.separator + "files"
				+ File.separator + Integer.toString(id);
		try {
			for (String x : this.staged.keySet()) {
				File destination = new File(folderName + File.separator + x);
				if (!destination.exists()) { // does htis mean error? Idk
					destination.mkdirs();
				}
				Files.copy(this.staged.get(x).toPath(), destination.toPath(),
						REPLACE_EXISTING);
				if (this.tracking.containsKey(x)) {
					this.tracking.remove(x);
				}
				commitFiles.put(x, destination);
			}
		} catch (IOException i) {
			return;
		}
		commitFiles.putAll(this.tracking);
		this.head = this.currBranch.setHead(new CommitTreeNode(this.head,
				commitFiles, id, message));
		ArrayList<CommitTreeNode> store = new ArrayList<CommitTreeNode>();
		if (this.commitMessages.containsKey(message)) {
			store = this.commitMessages.get(message);
		}
		store.add(this.head);
		this.commitMessages.put(message, store);
		try {
			for (File blah : new File(STAGE_DIR).listFiles()) {
				recursiveDelete(blah);
			}
		} catch (IOException i) {
			return;
		}
		(new File(STAGE_DIR)).mkdir();
		this.staged.clear();
		this.tracking.clear();
		this.tracking.putAll(commitFiles);
		// this.tracking.putAll(this.staged);
		this.untracked.clear();
		this.idHash.put(this.head.getId(), this.head);
		this.conflicted = false;
		this.merging = false;
		return;
	}

	/**
	 * Marks file for untracking. If it was staged, just remove it from staging.
	 * This means it will not be included in the upcoming commit.
	 * 
	 * @param fileName
	 *            The name of the file to be untracked.
	 */
	private void rm(String fileName) {
		File f = new File(STAGE_DIR + fileName);
		try {
			if (f.exists()) {
				Files.deleteIfExists(f.toPath());
				this.staged.remove(fileName);
				return;
			} else {
				if (this.tracking.containsKey(fileName)) {
					this.untracked.put(fileName,
							new File(this.tracking.get(fileName).toString()));
					this.tracking.remove(fileName);
					return;
				}
				System.out.println("No reason to remove the file.");
				return;
			}
		} catch (IOException e) {
			return;
		}
	}

	/**
	 * Starting at the current head commit, display information about each
	 * commit backwards along the commit tree until the initial commit.
	 */
	private void log() {
		printLog(this.currBranch.getHead());
		return;
	}

	/**
	 * Displays information about all commits ever made. Order does not matter.
	 * Basically does log for every branch.
	 */
	private void globalLog() {
		for (Branch b : this.branchNames.values()) {
			printLog(b.getHead());
		}
		return;
	}

	/**
	 * Prints out the id of the commit that has the given commit message. If
	 * there are multiple such commits, it prints the ids out on separate lines.
	 * 
	 * @param commitMessage
	 *            The commitMessage to search for.
	 */
	private void find(String commitMessage) {
		boolean found = false;
		if (this.commitMessages.containsKey(commitMessage)) {
			for (CommitTreeNode c : this.commitMessages.get(commitMessage)) {
				System.out.println(c.getId());
			}
			found = true;
		}
		if (!found) {
			System.out.println("Found no commit with that message.");
		}
		return;
	}

	/**
	 * Displays which branches currently exist. Marks the current branch with a
	 * star. Then displays staged files, and files marked for untracking.
	 *
	 */
	private void status() {
		System.out.println("=== Branches ===");
		for (Branch b : this.branchNames.values()) {
			if (b == this.currBranch) {
				System.out.print("*");
			}
			System.out.println(b.getBranchName());
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		for (String f : this.staged.keySet()) {
			System.out.println(f);
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (String x : this.untracked.keySet()) {
			System.out.println(x);
		}
		return;
	}

	/**
	 * Three versions of checkout: If given a file name, overwrite the file in
	 * the working directory with the copy of it in the head commit. If given a
	 * branch name, take all the files at the head of the given branch and puts
	 * them in the working directory. Sets the given branch to be the current
	 * branch.
	 * 
	 * @param objectName
	 *            Either a file name or branch name.
	 */

	private void checkout(String objectName) {
		if (this.branchNames.containsKey(objectName) && this.conflicted) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		if (this.currBranch.getBranchName().equals(objectName)) {
			System.out.println("No need to checkout current branch.");
			return;
		}
		try {
			if (this.branchNames.containsKey(objectName)) {
				CommitTreeNode branchHead = this.branchNames.get(objectName)
						.getHead();
				HashMap<String, File> checkoutFiles = branchHead.getFiles();
				for (String name : checkoutFiles.keySet()) {
					File destination = new File(name);
					if (!destination.exists()) { // this should be right?
						destination.mkdirs();
					}
					Files.copy(checkoutFiles.get(name).toPath(),
							destination.toPath(), REPLACE_EXISTING);
				}
				this.currBranch = this.branchNames.get(objectName);
				this.head = branchHead;
				this.tracking.clear();
				this.tracking.putAll(this.head.getFiles());
				return;
			}
			if (this.head.getFiles().containsKey(objectName)) {
				File rtn = this.head.getFiles().get(objectName);
				String destinationstr = objectName;
				File destination = new File(destinationstr);
				if (!destination.exists()) { // should be right? T_T
					destination.mkdirs();
				}
				Files.copy(rtn.toPath(), destination.toPath(), REPLACE_EXISTING);
				return;
			}
			System.out
					.println("File does not exist in the most recent commit, or no such branch exists.");
			return;
		} catch (IOException i) {
			return;
		}
	}

	/**
	 * This version of checkout takes the version of the file that exists with
	 * that id and puts it in the working directory.
	 * 
	 * @param commitID
	 *            The ID of the specific CommitTreeNode.
	 * @param fileName
	 *            The name of the file.
	 */
	private void checkout(int commitID, String fileName) {
		if (!this.idHash.containsKey(commitID)) {
			System.out.println("No commit with that id exists.");
			return;
		}
		CommitTreeNode c = this.idHash.get(commitID);
		if (c.getFiles().containsKey(fileName)) {
			File rtn = c.getFiles().get(fileName);
			File destination = new File(fileName);
			if (!destination.exists()) {
				destination.mkdirs();
			}
			try {
				Files.copy(rtn.toPath(), destination.toPath(), REPLACE_EXISTING);
			} catch (IOException e) {
				return;
			}
			return;
		} else {
			System.out.println("File does not exist in that commit.");
			return;
		}
	}

	/**
	 * Creates a new branch with the given name, and points it at the current
	 * head node. Does not change the current branch.
	 * 
	 * @param branchName
	 *            The name of the branch of the new branch.
	 */
	private void branch(String branchName) {
		if (this.branchNames.containsKey(branchName)) {
			System.out.println("A branch with that name already exists.");
			return;
		}
		this.branchNames.put(branchName, new Branch(branchName, this.head));
		return;
	}

	/**
	 * Deletes the branch with the given name. However, cannot remove the
	 * current branch.
	 * 
	 * @param branchName
	 *            The name of the branch to be removed.
	 */
	private void rmBranch(String branchName) {
		if (this.currBranch.getBranchName().equals(branchName)) {
			System.out.println("Cannot remove the current branch.");
			return;
		}
		if (this.branchNames.containsKey(branchName)) {
			this.branchNames.remove(branchName);
		}
		System.out.println("A branch with that name does not exist");
		return;
	}

	/**
	 * Checks out all the files tracked by the given commit. Also moves the
	 * current branch's head to that commit node. Partially resets the state of
	 * Gitlet. Should not be used in the middle of adding and untracking.
	 * 
	 * @param commitId
	 *            - the commit ID to which you should reset
	 */
	private void reset(int commitID) {
		if (commitID > this.lastID) {
			System.out.println("No commit with that id exists.");
			return;
		}
		CommitTreeNode c = this.idHash.get(commitID);
		for (String name : c.getFiles().keySet()) {
			this.checkout(commitID, name);
		}
		this.head = this.currBranch.setHead(c);
		this.tracking.clear();
		this.tracking.putAll(c.getFiles());
		return;
	}

	/**
	 * Merges files from the given branch into the current branch. Finds the
	 * split point of the two branches Files not modified in the current branch
	 * but modified in the given branch are changed to version in given branch
	 * head Files modified in the current branch but not modified in the given
	 * branch stay the same If files modified in both branches put into a
	 * conflicted state until user commits.
	 * 
	 * @param branchName
	 *            The name of the branch to be merged.
	 */
	private void merge(String branchName) {
		boolean conflictFiles = false;
		if (!this.branchNames.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (this.currBranch.getBranchName().equals(branchName)) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}
		Branch givenBranch = this.branchNames.get(branchName);
		CommitTreeNode givenHead = givenBranch.getHead();
		CommitTreeNode splitPoint = null;
		if (this.currBranch.getTail().getId() == givenBranch.getTail().getId()) {
			splitPoint = currBranch.getTail();
		} else if (this.currBranch.getTail().getId() < givenBranch.getTail()
				.getId()) {
			splitPoint = splitPointFinder(this.currBranch.getHead(),
					givenBranch.getTail());
		} else {
			splitPoint = splitPointFinder(this.currBranch.getTail(),
					givenBranch.getHead());
		}
		HashMap<String, File> splitPointFiles = splitPoint.getFiles();
		HashMap<String, File> currentFiles = this.head.getFiles();
		HashMap<String, File> givenFiles = givenHead.getFiles();
		for (String k : givenFiles.keySet()) {
			try {
				byte[] given = Files.readAllBytes(givenFiles.get(k).toPath());
				byte[] current = null;
				if (currentFiles.containsKey(k)) {
					current = Files.readAllBytes(currentFiles.get(k).toPath());
				}
				byte[] split = null;
				if (splitPointFiles.containsKey(k)) {
					split = Files.readAllBytes(splitPointFiles.get(k).toPath());
				}
				if (current == null) {
					this.checkout(givenHead.getId(), k);
					this.add(k);
				} else if (Arrays.equals(given, split)) {
				} else if (Arrays.equals(current, split)) {
					this.checkout(givenHead.getId(), k);
					this.add(k);
				} else if (!Arrays.equals(current, split)) {
					Files.copy(givenFiles.get(k).toPath(), new File(k
							+ ".conflicted").toPath(), REPLACE_EXISTING);
					conflictFiles = true;
				}
			} catch (IOException e) {
				return;
			}
		}
		if (!conflictFiles) {
			String mergeMessage = "Merged " + currBranch.getBranchName()
					+ "with" + givenBranch.getBranchName();
			this.merging = true;
			this.commit(mergeMessage);
		} else {
			System.out.println("Encountered a merge conflict.");
			this.conflicted = true;
		}
		return;
	}

	/**
	 * Find the split point of the current branch and the given branch, then
	 * snaps off the current branch at this point, then re-attaches the current
	 * branch to the head of the given branch.
	 * 
	 * @param branchName
	 *            The name of the branch to which to add the current branch.
	 */
	private void rebase(String branchName) {
		if (!this.branchNames.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (this.currBranch.getBranchName().equals(branchName)) {
			System.out.println("Cannot rebase a branch with itself.");
			return;
		}
		Branch given = this.branchNames.get(branchName);
		CommitTreeNode givenHead = given.getHead();
		CommitTreeNode splitpoint = splitPointFinder(this.currBranch.getHead(), given.getHead());
		CommitTreeNode curr = this.currBranch.getHead();
		CommitTreeNode x = curr;
		while (x.getParent() != null) {
			if (x.getId() == givenHead.getId()) {
				this.reset(givenHead.getId());
				return;
			}
			x = x.getParent();
		}
		this.rebaseHelper(curr, givenHead, splitpoint);
	}

	/**
	 * Recursively checks out and commits from each CommitTreeNode since the
	 * splitpoint of the current and given branch.
	 * 
	 * @param curr
	 *            The current head CommitTreeNode
	 * @param givenHead
	 *            The head of the branch rebasing from
	 * @param splitpoint
	 *            The most recent ancestor of the previous two params.
	 */
	private void rebaseHelper(CommitTreeNode curr, CommitTreeNode givenHead,
			CommitTreeNode splitpoint) {
		if (curr.getParent() != splitpoint) {
			this.rebaseHelper(curr.getParent(), givenHead, splitpoint);
		}
		try {
			for (String k : curr.getFiles().keySet()) {
				byte[] current = Files.readAllBytes(curr.getFiles().get(k)
						.toPath());
				byte[] given = null;
				if (givenHead.getFiles().containsKey(k)) {
					given = Files.readAllBytes(givenHead.getFiles().get(k)
							.toPath());
				}
				byte[] split = null;
				if (splitpoint.getFiles().containsKey(k)) {
					split = Files.readAllBytes(splitpoint.getFiles().get(k)
							.toPath());
				}
				if (Arrays.equals(given, split)) {
					this.checkout(curr.getId(), k);
					this.add(k);
				} else {
					if (Arrays.equals(current, split)) {
						this.checkout(givenHead.getId(), k);
						this.add(k);
					} else {
						this.checkout(curr.getId(), k);
						this.add(k);
					}
				}
			}
			for (String m : givenHead.getFiles().keySet()) {
				if (!this.staged.containsKey(m)) {
					this.checkout(givenHead.getId(), m);
				}
			}
			this.commit("rebasing commit " + curr.getId() + " to commit "
					+ givenHead.getId());
		} catch (IOException e) {
			return;
		}
	}

	/**
	 * Calls different functions based on the command. Parses Strings to ints
	 * where necessary. Chooses which checkout fn to call. Does nothing else.
	 * CURRENTLY DOES NOT HANDLE INVALID NUMBER OF ARGUMENTS to correct name.
	 * Citation -
	 * http://code.runnable.com/UpvRGuS4e6UcAAED/how-to-deserialize-objects
	 * -in-java-for-deserialization
	 * 
	 * @param args
	 *            The commands passed in to Gitlet.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please enter a command.");
			return;
		}
		if (args[0].equals("init")) {
			serialize(new Gitlet());
			return;
		}
		Gitlet lilgit = null;
		try {
			FileInputStream inputFileStream = new FileInputStream(".gitlet/.main/gitlet.ser");
			ObjectInputStream objectInputStream = new ObjectInputStream(inputFileStream);
			lilgit = (Gitlet) objectInputStream.readObject();
			objectInputStream.close();
		} catch (ClassNotFoundException e) {
			return;
		} catch (IOException i) {
			return;
		}
		if (args[0].equals("add")) {
			lilgit.add(args[1]);
		} else if (args[0].equals("commit")) {
			lilgit.commit(args[1]);
		} else if (args[0].equals("rm")) {
			lilgit.rm(args[1]);
		} else if (args[0].equals("log")) {
			lilgit.log();
		} else if (args[0].equals("global-log")) {
			lilgit.globalLog();
		} else if (args[0].equals("find")) {
			lilgit.find(args[1]);
		} else if (args[0].equals("status")) {
			lilgit.status();
		} else if (args[0].equals("checkout")) { // multiple cases
			if (args.length == 2) {
				lilgit.checkout(args[1]);
			} else if (args.length == 3) {
				lilgit.checkout(Integer.parseInt(args[1]), args[2]);
			}
		} else if (args[0].equals("branch")) {
			if (lilgit.conflicted) {
				System.out.println("Cannot do this command until merge conflict has been resolved.");
			} else {
				lilgit.branch(args[1]);
			}
		} else if (args[0].equals("rm-branch")) {
			if (lilgit.conflicted) {
				System.out.println("Cannot do this command until merge conflict has been resolved.");
			} else {
				lilgit.rmBranch(args[1]);
			}
		} else if (args[0].equals("reset")) {
			if (lilgit.conflicted) {
				System.out.println("Cannot do this command until merge conflict has been resolved.");
			} else {
				lilgit.reset(Integer.parseInt(args[1]));
			}
		} else if (args[0].equals("merge")) {
			if (lilgit.conflicted) {
				System.out.println("Cannot do this command until merge conflict has been resolved.");
			} else {
				lilgit.merge(args[1]);
			}
		} else if (args[0].equals("rebase")) {
			if (lilgit.conflicted) {
				System.out.println("Cannot do this command until merge conflict has been resolved.");
			} else {
				lilgit.rebase(args[1]);
			}
		} else {
			System.out.println("No command with that name exists.");
			return;
		}
		serialize(lilgit);
	}
}