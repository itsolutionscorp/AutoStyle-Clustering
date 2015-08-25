import java.io.File;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.io.FileInputStream;
//import java.io.*;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Gitlet implements Serializable {

	int commitID;
	private HashMap<String, String> unTrackedFiles; // <Name, Name> should be
													// hashset, too lazy
	private HashMap<String, String> stagedFiles; // should be hashset, too lazy
	private bLinkedList myLinkedList;
	private HashMap<Integer, bNode> allCommitsEver; // for global-log
	private HashMap<String, bNode> branches; // <BranchName, Latest Committed
												// Node>
	private HashMap<String, ArrayList<Integer>> findHelper;
	private String currentBranch;
	private static final String GITLET_DIR = ".gitlet/";
	private static final String STAGING_DIR = ".gitlet/StagingArea/";
	private File stagingArea;
	boolean conflictState;

	/**
	 * Constructor for the Gitlet object.
	 */
	public Gitlet() {
		findHelper = new HashMap<String, ArrayList<Integer>>();
		allCommitsEver = new HashMap<Integer, bNode>();
		stagedFiles = new HashMap<String, String>();
		myLinkedList = new bLinkedList();
		branches = new HashMap<String, bNode>();
		unTrackedFiles = new HashMap<String, String>();
		commitID = 0;
		currentBranch = "master";
		conflictState = false;
	}

	/**
	 * Creates a new gitlet version control system in the current directory.
	 * Automatically starts with one commit, which contains no files and has the
	 * commit message "initial commit." Worst Case: Should be constant time.
	 * 
	 * @return void
	 */
	public void init() {
		File git = new File(GITLET_DIR);
		stagingArea = new File(STAGING_DIR);
		git.mkdir();
		stagingArea.mkdir();
		branch("master");
		initialCommit();
	}

	/**
	 * Creates the initial commit.
	 * 
	 * @return void
	 */
	public void initialCommit() {
		int uniqueID = commitID;
		File commitDir = new File(GITLET_DIR + uniqueID);
		commitDir.mkdir();
		myLinkedList.initialListCommit("initial commit", uniqueID);
		branches.put(currentBranch, myLinkedList.getCurrent());
		ArrayList<Integer> first = new ArrayList<Integer>();
		first.add(uniqueID);
		findHelper.put("initial commit", first);
		allCommitsEver.put(uniqueID, myLinkedList.getCurrent());
	}

	/**
	 * Adds a copy of the file as it currently exists to the staging area (more
	 * on this in the description of the commit command). The staging area
	 * should be somewhere in .gitlet. If the file had been marked for
	 * untracking, then add just unmarks the file, and does not also add it to
	 * the staging area.
	 *
	 * Worst Case: It should run in linear time to the size of the file being
	 * added. Failure Cases: If the file does not exist, print the error message
	 * File does not exist.
	 * 
	 * @param fileName
	 *            A string of the path of the file to be added.
	 * @return void
	 * @throws IOException
	 *             If Files.copy() fails
	 */
	public void add(String fileName) {
		File input = new File(fileName);
		if (input.exists()) {
			if (unTrackedFiles.containsKey(fileName)) {
				unTrackedFiles.remove(fileName);
			} else {
				File copyOfFile = new File(STAGING_DIR + fileName);
				copyOfFile.getParentFile().mkdirs();
				try {
					Files.copy(input.toPath(), copyOfFile.toPath(),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					boolean style = true;
				}
				stagedFiles.put(fileName, STAGING_DIR + fileName);
			}
		} else {
			System.out.println("File does not exist");
		}
	}

	/**
	 * Commit tracks version of files. By default, each commit's snapshot of
	 * files will be exactly the same as parent commit's snapshot. Commit will
	 * only update the file if file had been staged. Commit will start tracking
	 * any files that were staged but weren't tracked by parent. Files marked
	 * for untracking lose mark after commit. Staging area cleared after commit.
	 * Staging area is inside .gitlet folder
	 * 
	 * @param message
	 *            A string of the message to be added with the commit.
	 * @return void
	 * @throws IOException
	 *             if Files.copy() does not work.
	 * 
	 */
	public void commit(String message) {
		conflictState = false;
		if (message == null || message.trim().isEmpty()) {
			System.out.println("Please enter a commit message.");
			return;
		} else if (stagedFiles.isEmpty() && unTrackedFiles.isEmpty()) {
			System.out.println("No changes added to the commit.");
			return;
		}
		commitID++;
		int uniqueID = commitID;
		File commitDir = new File(GITLET_DIR + uniqueID);
		commitDir.mkdir();
		// create tracked; this commit will know everything that its tracking
		HashMap<String, String> tracked = new HashMap<String, String>();
		if (branches.get(currentBranch).getMyTracked() != null) {
			tracked.putAll(branches.get(currentBranch).getMyTracked());
		}
		// Place staged files into commit folder, and into tracked
		for (String str : stagedFiles.keySet()) {
			File staged = new File(stagedFiles.get(str));
			File backup = new File(GITLET_DIR + uniqueID + "/" + str);
			String string2 = "" + GITLET_DIR + uniqueID + "/" + str;
			try {
				backup.mkdirs();
				Files.copy(staged.toPath(), backup.toPath(),
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				boolean style = true;
			}
			tracked.put(str, string2);
		}
		// remove untracked files and track the staged files
		for (String str : unTrackedFiles.keySet()) {
			if (tracked != null) {
				if (tracked.containsKey(str)) {
					tracked.remove(str);
				}
			}
		}
		// create the node and update branches, allcommitsever, and reset for
		// next commit;
		myLinkedList.listCommit(branches.get(currentBranch), message, uniqueID,
				tracked);
		branches.put(currentBranch, myLinkedList.getCurrent());
		allCommitsEver.put(myLinkedList.getCurrent().getID(),
				myLinkedList.getCurrent());
		stagedFiles = new HashMap<String, String>();
		unTrackedFiles = new HashMap<String, String>();
		for (File f : stagingArea.listFiles()) {
			HelperClass.deleteAll(f);
		}
		if (findHelper.containsKey(message)) {
			ArrayList<Integer> exists = findHelper.get(message);
			exists.add(commitID);
		} else {
			ArrayList<Integer> first = new ArrayList<Integer>();
			first.add(uniqueID);
			findHelper.put(message, first);
		}
	}

	/**
	 * Mark the file for untracking; this means it will not be included in the
	 * upcoming commit. If the file had been staged, instead just unstage it,
	 * and don't also mark it for untracking.
	 *
	 * @param fileName
	 *            A string that is the name of the file that you want to remove
	 * @return void
	 */
	public void rm(String fileName) {
		if (stagedFiles.containsKey(fileName)) {
			stagedFiles.remove(fileName);
			File deleted = new File(STAGING_DIR + fileName);
			if (deleted.exists()) {
				deleted.delete();
			}
		} else if (myLinkedList.getCurrent().getMyTracked() != null
				&& myLinkedList.getCurrent().getMyTracked()
						.containsKey(fileName)) {
			unTrackedFiles.put(fileName, myLinkedList.getCurrent()
					.getMyTracked().get(fileName));
		} else {
			System.out.println("No reason to remove the file.");
		}
	}

	/**
	 * Starting at the current head commit, display info about each commit
	 * backwards along commit tree until reaching initial commit. This set of
	 * commit nodes is called the commit's history. It should display the COMMIT
	 * ID, the TIME the commit was made, and the COMMIT MESSAGE. Worst Case:
	 * Linear with respect to the # of nodes. Failure Cases: None.
	 * 
	 * @return void
	 */
	public void log() {
		bNode node = branches.get(currentBranch);
		if (node == null) {
			return;
		}
		while (node != null) {
			System.out.println("===");
			System.out.println("Commit " + node.getID());
			System.out.println(node.getTime());
			System.out.println(node.getMsg());
			System.out.println("");
			node = node.getPrev();
		}
	}

	/**
	 * Just like log but it displays information about all commits ever made.
	 * The order of commit's doens't matter. SAME FORMAT AS LOG. Worst Case:
	 * Linear with respect to the number of commits ever made.
	 * 
	 * @return void
	 */
	public void global_log() {
		for (bNode node : allCommitsEver.values()) { // iterate through values
														// (bNodes)
			System.out.println("===");
			System.out.println("Commit " + node.getID());
			System.out.println(node.getTime());
			System.out.println(node.getMsg());
			System.out.println("");
		}
	}

	/**
	 * Prints out the ID of the commit that has the given COMMIT MESSAGE. If
	 * there are multiple such commits, it prints the ids out on separate lines.
	 * Worst Case: Should be linear relative to the number of commits that have
	 * the given message. Failure Case: If no such commit exists, print the
	 * error message "Found no commit with that message." Estimated Line count:
	 * 5
	 * 
	 * @param message
	 *            A string, the commit message, that the method searches for to
	 *            find corresponding commits.
	 * @return void
	 */
	public void find(String message) { // is linear relative to all commits not
										// just commits with given message.
		boolean found = false;
		for (String str : findHelper.keySet()) { // iterate through values
													// (bNodes)
			if (str.equals(message)) {
				ArrayList<Integer> list = findHelper.get(str);
				for (Integer commitid : list) {
					System.out.println(commitid);
				}
				found = true;
			}
		}
		if (found == false) {
			System.out.println("Found no commit with that message.");
		}
	}

	/**
	 * Displays what branches currently exist, and marks the current branch with
	 * a *. Also displays what files have been staged or marked for untracking.
	 *
	 * An example of the EXACT format it should follow is as follows. ===
	 * Branches === master other-branch
	 *
	 * === Staged Files === wug.txt some_folder/wugs.txt
	 *
	 * === Files Marked for Untracking === goodbye.txt
	 *
	 * Worst Case: Linear to the number of files that have been STAGED or MARKED
	 * for untracking and the number of existing branches. Failure Cases: None
	 * Estimated Line Count: 15
	 * 
	 * @return void
	 */
	public void status() {
		System.out.println("=== Branches ===");
		for (String branchName : branches.keySet()) {
			if (branchName.equals(currentBranch)) {
				System.out.println("*" + branchName);
			} else {
				System.out.println(branchName);
			}
		}
		System.out.println("");
		System.out.println("=== Staged Files ===");
		for (String key : stagedFiles.keySet()) {
			System.out.println(key);
		}
		System.out.println("");
		System.out.println("=== Files Marked for Untracking ===");
		for (String key : unTrackedFiles.keySet()) {
			System.out.println(key);
		}
	}

	/**
	 * [file name] Takes the version of the file as it exists in the head
	 * commit, the front of the current branch and puts it in the working
	 * directory, overwriting the version of the file that's already there if
	 * there is one. Should be linear relative to the size of the file being
	 * checked out.
	 *
	 * [branch name] Takes all files in the commit at the head of the given
	 * branch and puts them in the working directory, overwriting the versions
	 * of the files taht are already there if they exist. Also, at the end of
	 * this command, the given branch will not be considered the current branch.
	 *
	 * Linear with respect to total size of files in the commit's snapchat.
	 * Constant with respect to any measure involving number of commits.
	 * Constant to number of branches.
	 *
	 * @param name
	 *            String that is either a branch or file name.
	 * @return void
	 * @throws IOException
	 *             if Files.copy() does not work.
	 */

	public void checkout(String name) {
		if (currentBranch.equals(name)) {
			System.out.println("No need to checkout the current branch.");
		} else if (branches.containsKey(name) && conflictState == false) {
			bNode node = branches.get(name);
			for (String str : node.getMyTracked().keySet()) {
				File og = new File(str);
				File backup = new File(node.getMyTracked().get(str));
				try {
					if (og.getParent() != null) {
						og.getParentFile().mkdirs();
					}
					Files.copy(backup.toPath(), og.toPath(),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					boolean style = true;
				}
			}
			currentBranch = name;
		} else if (myLinkedList.getCurrent().getMyTracked().containsKey(name)) {
			File og = new File(name);
			File backup = new File(myLinkedList.getCurrent().getMyTracked()
					.get(name));
			try {
				if (og.getParent() != null) {
					og.getParentFile().mkdirs();
				}
				Files.copy(backup.toPath(), og.toPath(),
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				boolean style = true;
			}
		} else if (branches.containsKey(name) && conflictState == true) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
		} else {
			System.out
					.println("File does not exist in the most recent commit, or no such branch exists.");
		}
	}

	/**
	 * Takes the version of the file as it exists in the commit with the given
	 * id, and puts it in the working directory, overwriting the version of the
	 * file thats already there if there is one. Should be linear relative to
	 * the size of the file being checked out.
	 * 
	 * @param commit_id
	 *            A string that is parsed into an integer and used to locate the
	 *            needed commit.
	 * @param fileName
	 *            A string that is the file name of what you're trying to check
	 *            out.
	 * @return void
	 * @throws IOException
	 *             if Files.copy() does not work
	 */
	public void checkout(String commit_id, String fileName) {
		try {
			Integer parsed = Integer.parseInt(commit_id);
		} catch (NumberFormatException e) {
			return;
		}
		Integer parsed = Integer.parseInt(commit_id);
		if (allCommitsEver.containsKey(parsed)) {
			bNode node = allCommitsEver.get(parsed);
			if (node.getMyTracked().containsKey(fileName)) {
				File backup = new File(node.getMyTracked().get(fileName));
				File og = new File(fileName);
				try {
					if (og.getParent() != null) {
						og.getParentFile().mkdirs();
					}
					Files.copy(backup.toPath(), og.toPath(),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					boolean style = true;
				}
			} else {
				System.out.println("File does not exist in that commit.");
			}
		} else {
			System.out.println("No commit with that id exists.");
		}
	}

	/**
	 * Creates a new branch with the given name, and points it at the current
	 * head node. A branch is nothing more than a name for a pointer to a commit
	 * node into the commit tree. Before you ever call branch, your code should
	 * be running with a default branch called "master". Note: Does NOT
	 * immediately switch to the newly created branch.
	 * 
	 * Worst Case: Should be constant to any significant measure. Failure Cases:
	 * If a branch with the given name already exists, print the error message
	 * "A branch with that name already exists." Estimated Line Count: 5
	 * 
	 * @param branchName
	 *            A string, the name of the branch that you want to create.
	 * @return void
	 */
	public void branch(String branchName) {
		if (branches.containsKey(branchName) == false) {
			branches.put(branchName, myLinkedList.getCurrent());
		} else {
			System.out.println("A branch with that name already exists.");
		}
	}

	/**
	 * Deletes the branch with the given name. This only means to delete the
	 * pointer associated with the branch; it does not mean to delete all
	 * commits that were created under the branch, or anything like that.
	 *
	 * Worst Case: Constant to any significant measure. Failure Cases: If a
	 * branch with the given name does not exist, aborts. Print the error
	 * message "A branch with that name does not exist." If you try to remove
	 * the branch you're currently on, aborts, printing the error message
	 * "Cannot remove the current branch." Estimated Line Count: 5
	 * 
	 * @param branchName
	 *            A string, the name of the branch that you want to remove.
	 * @return void
	 */
	public void rm_branch(String branchName) {
		if (branches.containsKey(branchName)) {
			if (branchName.equals(currentBranch)) {
				System.out.println("Cannot remove the current branch.");
			} else {
				branches.remove(branchName);
			}
		} else {
			System.out.println("A branch with that name does not exist.");
		}
	}

	/**
	 * Checks out all the files tracked by the given commit. Also moves the
	 * current branch's head to that commit node. See the intro for an example
	 * of what happens to the head pointer after using reset.
	 *
	 * Worst Cast: Should be linear with the respect to the total size of files
	 * TRACKED by the given commit's snapshot. Should be constant with respect
	 * to any measure involving number of commits. Failure Cases: If no commit
	 * with the given ID exists, print "No commit with that id exists."
	 * Estimated line count: 10
	 * 
	 * @param commit_id
	 *            A string that is parsed into an int, it is used to locate the
	 *            commit that you want to reset to
	 * @return void
	 */
	public void reset(String commit_id) {
		try {
			Integer parsed = Integer.parseInt(commit_id);
		} catch (NumberFormatException e) {
			return;
		}
		Integer parsed = Integer.parseInt(commit_id);
		if (allCommitsEver.containsKey(parsed)) {
			bNode node = allCommitsEver.get(parsed);
			for (String ogPath : node.getMyTracked().keySet()) {
				checkout(commit_id, ogPath);
			}
			branches.put(currentBranch, node);
		} else {
			System.out.println("No commit with that id exists.");
		}
	}

	/**
	 * Merges files from the given branch into the current branch. A split point
	 * is the earliest common ancestor of the tree. Any modified files in given
	 * branch, but unmodified in current branch since split point should be
	 * checked out from the commit at the front of the given branch and auto
	 * staged. Modified means the version of the file as it exists in the commit
	 * at the front of the given branch has different content than the version
	 * of same file at split point. If a file has been modified in both
	 * branches, create "fileName.conflicted" If a file has been untracked =
	 * "modification". If untracked in one and contents changed in another, do
	 * not create .conflicted file. Keep changed file if it's in current branch
	 * or stage if changed file in given branch. After updating files, if no
	 * .conflicted files, automatically commit with message
	 * "Merged [current branch name] with [given branch name]." Else: Don't auto
	 * commit. Print "Encountered a merge conflict." Goes into conflicted state
	 * Only add,rm,commit,checkout[file],
	 * checkout[id][file],log,global-log,find,and status work. Conflicted state
	 * ends when user commits. If any other command is used, print
	 * "Cannot do this command until the merge conflict has been resolved."
	 *
	 * Runtime: Linear in terms of lengths of history of each branch. Linear in
	 * terms of total size of new files added in commits in each branch.
	 * Failure: Branch DNE: "A branch with that name does not exist." Merge with
	 * itself: "Cannot merge a branch with itself."
	 *
	 * @param branchName
	 *            Name of branch you want to merge with
	 * @return void
	 * @throws IOException
	 *             If Files.copy() fails to copy.
	 */
	public void merge(String branchName) {
		if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (branchName == currentBranch) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}
		bNode currentNode = branches.get(currentBranch);
		bNode otherNode = branches.get(branchName);
		bNode splitPoint = HelperClass.splitHelper(currentNode, otherNode);
		HashMap<String, String> currentModified = HelperClass.findModified(
				currentNode, splitPoint);
		HashMap<String, String> otherModified = HelperClass.findModified(
				otherNode, splitPoint);
		boolean generateConflicted = false; // determines if merge should auto
											// commit or not
		for (String og : otherModified.keySet()) {
			if (currentNode.getMyTracked().containsKey(og)
					&& HelperClass.fileEquals(otherModified.get(og),
							currentNode.getMyTracked().get(og))) {
				unTrackedFiles.put(og, currentNode.getMyTracked().get(og));
				continue;
			} else if (currentModified.containsKey(og)
					&& !currentNode.getMyTracked().containsKey(og)) {
				checkout("" + otherNode.getID(), og);
			} else if (currentModified.containsKey(og)) {
				File conflicted = new File(otherModified.get(og));
				File ogPath = new File(og + ".conflicted");
				try {
					Files.copy(conflicted.toPath(), ogPath.toPath(),
							StandardCopyOption.REPLACE_EXISTING);
					conflictState = true;
					generateConflicted = true;
				} catch (IOException e) {
					e.printStackTrace();
					boolean failed = true;
				}
			} else {
				checkout("" + otherNode.getID(), og);
			}
			stagedFiles.put(og, STAGING_DIR + og);
			File copyOfFile = new File(STAGING_DIR + og);
			File input = new File(otherModified.get(og));
			copyOfFile.getParentFile().mkdirs();
			try {
				Files.copy(input.toPath(), copyOfFile.toPath(),
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				boolean style = true;
			}
		}
		if (generateConflicted == false) {
			commit("Merged " + currentBranch + " with " + branchName + ".");
		} else {
			System.out.println("Encountered a merge conflict.");
		}
	}

	/**
	 * Find split point of current branch and given branch, copies current
	 * branch at that point and re-attaches it to head of given branch. Generate
	 * new commit ID and time stamps. If current branch pointer in history of
	 * given branch, rebase just moves the current branch to point to the same
	 * commit that the given branch points to. No commits are replayed. If
	 * commit at front of given branch has files modified since split point,
	 * these changes propagate through the replay. Version of files in given
	 * branch should take place of their counterparts replayed commits, up until
	 * one of replayed commits has a version of the file that had also been
	 * modified since the split point >> then keep current branch's copies of
	 * files.
	 *
	 * Runtime: Linear for all Do not need to make any additional backup copies
	 * of files Failure: Branch DNE: "A branch with that name does not exist."
	 * Itself: "Cannot rebase a branch onto itself." If input branch's head is
	 * in history of current branch's head, print "Already up-to-date."
	 *
	 * @param branchName
	 *            Name of branch to rebase with.
	 * @return void
	 */
	public void rebase(String branchName) {
		if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (branchName == currentBranch) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}
		bNode currentNode = branches.get(currentBranch);
		bNode otherNode = branches.get(branchName);
		bNode splitPoint = HelperClass.splitHelper(currentNode, otherNode);
		if (HelperClass.history(currentNode, otherNode)) {
			System.out.println("Already up-to-date.");
			return;
		} else if (HelperClass.history(otherNode, currentNode)) {
			branches.put(currentBranch, otherNode);
			return;
		}
		ArrayList<Integer> commitList = HelperClass.commitHistory(currentNode,
				splitPoint);
		branches.put(currentBranch, otherNode); // Head of current branch is now
												// the given branch's head;
		int lastID = 0;
		for (Integer id : commitList) {
			commitID++;
			int uniqueID = commitID;
			lastID = uniqueID;
			bNode oldNode = allCommitsEver.get(id);
			bNode other = branches.get(currentBranch);
			HashMap<String, String> tracked = new HashMap<String, String>();
			tracked.putAll(oldNode.getMyTracked());
			HashMap<String, String> currentModified = HelperClass.findModified(
					oldNode, splitPoint);
			HashMap<String, String> otherModified = HelperClass.findModified(
					branches.get(currentBranch), splitPoint);
			for (String og : otherModified.keySet()) {
				if (!currentModified.containsKey(og)) { // if modified in other
														// but not in current
					// removed in other but not in current
					if (!other.getMyTracked().containsKey(og)
							&& tracked.containsKey(og)) { // if og in
															// otherModified and
															// in other, then
															// its been removed.
						tracked.remove(og);
					} else {
						tracked.put(og, otherModified.get(og));
					}
				}
			}
			bNode newNode = new bNode(branches.get(currentBranch),
					oldNode.getMsg(), uniqueID, tracked);
			allCommitsEver.put(uniqueID, newNode);
			branches.put(currentBranch, newNode);
		}
		reset("" + lastID);
	}

	/**
	 * Reads a .ser file for a serialized Gitlet object
	 * 
	 * @param fileName
	 * @return Gitlet
	 * @throws IOException
	 *             For input/output exceptions.
	 * @throws ClassNotFoundException
	 *             If class of serialized object can't be found.
	 */
	public static Gitlet readFromFile(String fileName) {
		try {
			FileInputStream asdf = new FileInputStream(fileName);
			ObjectInputStream jkl = new ObjectInputStream(asdf);
			return (Gitlet) jkl.readObject();
		} catch (Exception e) {
			return null; // Or is it new GitLet() ?
		}
	}

	/**
	 * Serializes the object and writes it to fileName.
	 * 
	 * @param fileName
	 *            A String, Gitlet object is serialized and placed to fileName.
	 * @return void
	 * @throws IOException
	 *             For input/output exceptions.
	 * @throws ClassNotFoundException
	 *             If class of serialized object can't be found.
	 */
	public void writeToFile(String fileName) {
		File f = new File(".");
		try {
			FileOutputStream asdf = new FileOutputStream(fileName);
			ObjectOutputStream jkl = new ObjectOutputStream(asdf);
			jkl.writeObject(this);
		} catch (IOException e) {
			return;
		}
	}

	/**
	 * Takes in user's input and calls the correct methods accordingly.
	 * 
	 * @param args
	 *            The string arguments that the user inputs
	 * @return void
	 */
	public static void main(String[] args) {

		String arg0;
		String arg1;
		String arg2;
		// Base case for whether there are any arguments
		if (args.length == 0) {
			System.out.println("Please enter a command.");
			return;
		}
		arg0 = args[0];
		// Checks whether the first argument given is a valid command.
		if (!arg0.equals("init") && !arg0.equals("global-log")
				&& !arg0.equals("status") && !arg0.equals("add")
				&& !arg0.equals("commit") && !arg0.equals("rm")
				&& !arg0.equals("find") && !arg0.equals("checkout")
				&& !arg0.equals("branch") && !arg0.equals("merge")
				&& !arg0.equals("rebase") && !arg0.equals("reset")
				&& !arg0.equals("rm-branch") && !arg0.equals("log")) {
			System.out.println("No command with that name exists.");
			return;
		}
		Gitlet git = readFromFile(GITLET_DIR + "gitlet.ser"); // Saved gitlet
																// file
																// location.
		if (git == null) {
			if (!arg0.equals("init")) {
				return;
			}
		}
		if (git != null && git.conflictState == true) {
			if (!arg0.equals("global-log") && !arg0.equals("status")
					&& !arg0.equals("add") && !arg0.equals("commit")
					&& !arg0.equals("rm") && !arg0.equals("find")
					&& !arg0.equals("checkout") && !arg0.equals("log")) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				return;
			}
			if (args.length == 1) {
				if (arg0.equals("log")) {
					git.log();
				} else if (arg0.equals("global-log")) {
					git.global_log();
				} else if (arg0.equals("status")) {
					git.status();
				} else if (arg0.equals("commit")) {
					System.out.println("Please enter a commit message.");
					return;
				}
			} else if (args.length == 2) {
				arg1 = args[1];
				if (arg0.equals("add")) {
					git.add(arg1);
				} else if (arg0.equals("commit")) {
					git.commit(arg1);
				} else if (arg0.equals("rm")) {
					git.rm(arg1);
				} else if (arg0.equals("find")) {
					git.find(arg1);
				} else if (arg0.equals("checkout")) {
					git.checkout(arg1);
				}
			} else if (args.length == 3) {
				arg1 = args[1];
				arg2 = args[2];
				if (arg0.equals("checkout")) {
					git.checkout(arg1, arg2);
				}
			}
		} else {
			if (args.length == 1) {
				if (arg0.equals("log")) {
					git.log();
				} else if (arg0.equals("global-log")) {
					git.global_log();
				} else if (arg0.equals("status")) {
					git.status();
				} else if (arg0.equals("init")) {
					File oldGit = new File(GITLET_DIR);
					if (oldGit.exists()) {
						System.out
								.println("A gitlet version control system already exists in the current directory.");
						return;
					}
					git = new Gitlet();
					git.init();
				} else if (arg0.equals("commit")) {
					System.out.println("Please enter a commit message.");
					return;
				}
			} else if (args.length == 2) {
				arg1 = args[1];
				if (arg0.equals("add")) {
					git.add(arg1);
				} else if (arg0.equals("commit")) {
					git.commit(arg1);
				} else if (arg0.equals("rm")) {
					git.rm(arg1);
				} else if (arg0.equals("find")) {
					git.find(arg1);
				} else if (arg0.equals("checkout")) {
					git.checkout(arg1);
				} else if (arg0.equals("branch")) {
					git.branch(arg1);
				} else if (arg0.equals("rm-branch")) {
					git.rm_branch(arg1);
				} else if (arg0.equals("reset")) {
					git.reset(arg1);
				} else if (arg0.equals("merge")) {
					git.merge(arg1);
				} else if (arg0.equals("rebase")) {
					git.rebase(arg1);
				}
			} else if (args.length == 3) {
				arg1 = args[1];
				arg2 = args[2];
				if (arg0.equals("checkout")) {
					git.checkout(arg1, arg2);
				}
			}
		}
		git.writeToFile(GITLET_DIR + "gitlet.ser"); // Name of the file where
													// we'll write what we've
													// done.
	}
}
