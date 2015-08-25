import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.Serializable;

/**
 * @authors Alex Yao: cs61bl-cc, Jennifer Chen: cs61bl-bx, Kai-li Yen: cs61bl-el
 * 
 *          Gitlet Project:
 *          http://cs61bl.github.io/materials/proj/proj2/proj2.html
 */

public class Gitlet implements Serializable {

	/**
	 * 
	 */

	// history: keeps track of all the commits ever made via HashMap
	// Key: commit ID| Value: CommitNode object
	HashMap<Integer, CommitNode> history;

	// branches: keeps track of all the branches ever made via HashMap
	// Key: name of branch | Value: head commit node of the branch
	HashMap<String, CommitNode> branches;

	// msgLog: keeps track of commit nodes of the same commit message via
	// HashMap;
	// Key: commit message | Value: an ArrayList of commit IDs that have the
	// commit message.
	HashMap<String, ArrayList<Integer>> msgLog;

	// splitPoints: keeps track of all the split points via HashMap;
	// Key: split node | Value: an ArrayList of branch names that share the
	// split point
	HashMap<CommitNode, ArrayList<String>> splitPoints;

	// initial: pointer to the first commit created at the time when init is
	// called
	CommitNode initial;

	// master: pointer that points at the head commit node of the master branch
	public CommitNode master;
	// curBranchName: name of the current branch
	public String curBranchName;

	// stage: an ArrayList of file names that are currently in the stage
	ArrayList<String> stage;
	// untrackedNames: an ArrayList of file names that are currently untracked
	ArrayList<String> untrackedNames;

	// gitPath: path to the git folder
	String gitPath;
	// curPath: path to the current working directory
	String curPath;
	// commitPath: path to the commit directory
	String commitPath;
	// stagePath: path to the stage directory
	String stagePath;

	// currentFolder: current working directory
	private File currentFolder;
	// gitFolder: git directory
	private File gitFolder;

	// commitFolder: commit directory
	private File commitFolder;
	// stageFolder: stage directory
	private File stageFolder;

	// serFile: serialized file that stores the data between each time the
	// program is run. Allows saving the gitlet state.
	private File serFile;

	// commitCount: keeps track of the number of commits made so far
	private int commitCount;
	// curNode: the current head commit node of the current branch
	private CommitNode curNode;

	// conflicted state : only commands add, rm, commit, checkout [file],
	// checkout [id] [file], log, global-log, find, status
	private boolean conflictedState;

	/**
	 * 
	 * @throws IOException
	 */

	public Gitlet() throws IOException {
	}

	/**
	 * Creates a new gitlet version control system. If the system already
	 * exists, a message is printed out. Initializes instance variables, creates
	 * directories for git, stage, and commit, creates initial commit.
	 * 
	 * @throws IOException
	 */

	public void init() throws IOException {
		// set up folders
		currentFolder = new File(".");
		gitFolder = new File(currentFolder, ".gitlet");
		serFile = new File(gitFolder, "git.ser");
		commitFolder = new File(gitFolder, "commits");
		stageFolder = new File(gitFolder, "stage");

		conflictedState = false;
		// creates directories for git, stage, and commit
		if (gitFolder.exists() && gitFolder.isDirectory()) {
			System.out.println("A gitlet version control system already exists in the current directory.");
			return;
		} else {
			gitFolder.mkdir();
			serFile.createNewFile();
		}
		if (!stageFolder.exists()) {
			stageFolder.mkdir();
		}
		if (!commitFolder.exists()) {
			commitFolder.mkdir();
		}

		// initiates hashmaps
		history = new HashMap<Integer, CommitNode>();
		branches = new HashMap<String, CommitNode>();
		msgLog = new HashMap<String, ArrayList<Integer>>();
		splitPoints = new HashMap<CommitNode, ArrayList<String>>();

		// initiates arraylists
		stage = new ArrayList<String>();
		untrackedNames = new ArrayList<String>();

		// initiates commitCount
		commitCount = 0;

		// find paths to directories
		commitPath = commitFolder.getCanonicalPath();
		curPath = currentFolder.getCanonicalPath();
		gitPath = gitFolder.getCanonicalPath();
		stagePath = stageFolder.getCanonicalPath();

		// creates initial commit, sets master branch
		initial = new CommitNode("initial commit", null, commitCount);
		master = initial;
		branches.put("master", initial);
		curBranchName = "master";
		history.put(0, initial);
		curNode = initial;
	}

	/**
	 * Puts a copy of the given file to the staging area. If the file already
	 * exists in the staging area, replace the old file with the new one. If the
	 * file had been previously marked for untracking, then retrack the file
	 * without adding it to the staging area.
	 * 
	 * @param filename
	 *            The name of the file to be added.
	 * @throws IOException
	 */

	public void add(String filename) throws IOException {
		File f = new File(currentFolder, filename);
		File staged = new File(stageFolder, filename);
		if (f.exists() && !f.isDirectory()) {
			if (curNode.getPrev() != null && curNode.getPrev().contains(filename)
					&& !curNode.getPrev().getTrack(filename)) {
				untrackedNames.remove(filename);
				curNode.retrack(filename);
				return;
			}
			if (staged.exists()) {
				staged.delete(); // update to the new version
			}
			stage.add(filename);
			toStage(filename);
		} else {
			System.out.println("File does not exist.");
		}
	}

	/**
	 * Saves a new commit via CommitNode in the commit folder. Removes files
	 * from staging area once committed. Each commit should have an unique int
	 * ID, commit time, and message.
	 * 
	 * Exceptions: if the message is empty, ask to enter a commit message. If
	 * the stage is empty, print that no changes have been added to commit.
	 * 
	 * @param message
	 *            Commit message associated with the desired commit.
	 * @throws IOException
	 */

	public void commit(String message) throws IOException {
		if (message == null || message.equals("")) {
			System.out.println("Please enter a commit message.");
			return;
		} else if (stage.isEmpty()) {
			System.out.println("No changes added to the commit.");
			return;
		}
		commitCount++;
		// put in history
		history.put(commitCount, new CommitNode(message, curNode, commitCount));
		curNode = history.get(commitCount);
		curNode.copyNodeFiles(curNode.getPrev());
		for (String f : stage) {
			if (!untrackedNames.contains(f)) {
				toCommit(f);
			}
		}
		// put in msgLog
		if (!msgLog.containsKey(message)) {
			msgLog.put(message, new ArrayList<Integer>());
		}
		untrackedNames.clear();
		msgLog.get(message).add(commitCount);
		// update branches
		branches.put(curBranchName, curNode);
		// clear stage
		stage.clear();
		conflictedState = false;
	}

	/**
	 * Adds files to the stage folder.
	 * 
	 * @param filename
	 *            Name of the file to be added.
	 * @throws IOException
	 */

	private void toStage(String filename) throws IOException {
		Path source = Paths.get(curPath + "/" + filename);
		Path target = Paths.get(gitPath + "/stage/" + filename);

		Path filePath = Paths.get(stagePath + "/" + filename);
		Files.createDirectories(filePath.getParent());
		Files.copy(source, target);
	}

	/**
	 * Adds file to the commit folder.
	 * 
	 * @param filename
	 *            Name of file to be added to commit folder.
	 * @throws IOException
	 */

	private void toCommit(String filename) throws IOException {
		Path source = Paths.get(stagePath + "/" + filename);
		Path target;
		if (Paths.get(filename).getParent() != null) {
			target = Paths.get(commitPath + "/" + filename);
			target = Paths.get(target.getParent() + "/" + parseName(target.getFileName().toString()));
		} else {
			target = Paths.get(commitPath + "/" + parseName(Paths.get(filename).getFileName().toString()));
		}

		curNode.addFile(filename, new File(target.toString()));

		try {
			Files.createDirectories(target.getParent());
			Files.copy(source, target);
		} catch (FileAlreadyExistsException e) {
			System.out.println("file exists already");
		}
		Files.deleteIfExists(Paths.get(stagePath + "/" + filename));
	}

	/**
	 * Adds in the commit count in parentheses to the given filename.
	 * 
	 * @param filename
	 *            Name of the file to be copied and changed.
	 * @return A new string of the given filename with the commit number in the
	 *         parentheses.
	 */

	private String parseName(String filename) {
		String delims = "[.]";
		String[] words = filename.split(delims);
		String rtn = words[0];
		rtn += "(" + commitCount + ")";
		for (int i = 1; i < words.length; i++) {
			rtn += "." + words[i];
		}
		return rtn;
	}

	/**
	 * Removes the number and parentheses in a parsed name.
	 * 
	 * @param filename
	 *            Name of the file to unparse.
	 * @return A new string that only has the name of the file (no commit
	 *         number)
	 */

	private String unparseName(String filename) {
		String delims = "[()]";
		String[] words = filename.split(delims);
		String rtn = words[0];
		for (int i = 2; i < words.length; i++) {
			rtn += words[i];
		}
		return rtn;
	}

	/**
	 * Untracks a file so that the next commit will not commit this file. If the
	 * file is staged, it should be unstaged instead of untracking.
	 * 
	 * @param filename
	 *            The name of the file to be removed.
	 */

	public void rm(String filename) {
		if (stage.contains(filename)) {
			stage.remove(filename);
			stage.remove(new File(stageFolder, filename));
			File f = new File(stageFolder, filename);
			f.delete();
		} else if (curNode.getTrack(filename)) {
			untrackedNames.add(filename);
			curNode.addUntracked(filename);
		} else {
			System.out.println("No reason to remove the file.");
		}
	}

	/**
	 * Displays information about all the commits ever made. This includes the
	 * commit ID, the time of the commit, and the commit message.
	 */

	public void globalLog() {
		for (Integer key : history.keySet()) {
			printLog(history.get(key));
		}
	}

	/**
	 * Displays information about all the commits on the current branch. This
	 * includes the commit ID, the time of the commit, and the commit message.
	 */

	public void log() {
		CommitNode temp = curNode;
		while (temp != null) {
			printLog(temp);
			temp = temp.getPrev();
		}
	}

	/**
	 * Prints out the divider ("==="), commit ID, time, and message.
	 * 
	 * @param c
	 *            CommitNode that keeps track of the commit to be printed.
	 */

	private void printLog(CommitNode c) {
		System.out.println("===");
		System.out.println("Commit " + c.getCommitID());
		System.out.println(c.getTime());
		System.out.println(c.getMsg());
		System.out.println();
	}

	/**
	 * Displays all the commit IDS of the commits with the given message. Uses
	 * the msgLog to find all the commit IDS.
	 * 
	 * @param message
	 *            The commit message to find.
	 */

	public void find(String message) {
		if (msgLog.containsKey(message)) {
			for (int id : msgLog.get(message)) {
				System.out.println(id);
			}
		} else {
			System.out.println("Found no commit with that message.");
		}
	}

	/**
	 * Displays the names of the existing branches, the files that have been
	 * staged, and the files that have been marked for untracking.
	 */

	public void status() {
		System.out.println("=== Branches ===");
		Set<String> branchSet = branches.keySet();

		for (String key : branchSet) {
			if (key.equals(curBranchName)) {
				System.out.print("*");
			}
			System.out.println(key);
		}

		System.out.println();
		System.out.println("=== Staged Files ===");
		for (String name : stage) {
			System.out.println(name);
		}
		System.out.println();

		System.out.println("=== Files Marked for Untracking ===");
		for (String name : untrackedNames) {
			System.out.println(name);
		}
		System.out.println();

	}

	/**
	 * Case 1 (branch name given): If the given name is the name of a branch,
	 * all the files in the head commit of the given branch is put in the
	 * working directory. If a version of the file already exists in the working
	 * directory, the files checked out from the branch should overwrite the
	 * existing files. Sets the current branch to the given branch.
	 * 
	 * Case 2 (file name given): If the given name is the name of the file in
	 * the head commit of the current branch, the file is put into the working
	 * directory. If a version of the file already exists in the working
	 * directory, the version from the head commit should overwrite the existing
	 * file. Case 1 takes precedence over Case 2.
	 * 
	 * @param name
	 *            Either the name of the branch to be checked out or the name of
	 *            the file to be checked out.
	 * @throws IOException
	 */

	public void checkout(String name) throws IOException {

		if (branches.containsKey(name)) {
			if (name.equals(curBranchName)) {
				System.out.println("No need to checkout the current branch.");
				return;
			}
			CommitNode commit = branches.get(name);
			Set<String> fileSet = commit.getKeySet();
			for (String fileKey : fileSet) {
				Path filePath = Paths.get(commit.getFile(fileKey).getCanonicalPath());
				Path tempPath = Paths.get(commitPath).relativize(Paths.get(filePath.toString()));
				Files.createDirectories(Paths.get(curPath + "/" + tempPath).getParent());
				Path target = Paths.get(curPath + "/" + tempPath);
				target = Paths.get(target.getParent() + "/" + unparseName(target.getFileName().toString()));
				Files.deleteIfExists(target);
				Files.copy(filePath, target);
			}
			curNode = commit;
			curBranchName = name;
			return;
		}
		if (curNode.contains(name)) {
			Path source = Paths.get(curNode.getFile(name).getParent() + "/" + curNode.getFile(name).getName());
			Path target = Paths.get(curPath + "/" + name);
			Files.deleteIfExists(target);
			Files.copy(source, target);
		} else {
			System.out.println("File does not exist in the most recent commit, or no such branch exists.");
		}
	}

	/**
	 * Case 3 (commitID and file name given): If the given name is a the name of
	 * a file in the commit of the given ID, put the file in the working
	 * directory. If a version of the file already exists in the working
	 * directory, the file from the commitID should overwrite the existing file.
	 * 
	 * @param commitID
	 *            The commit ID of the desired commit
	 * @param filename
	 *            The desired file
	 * @throws IOException
	 */

	public void checkout(int commitID, String filename) throws IOException {
		if (history.containsKey(commitID) == false) {
			System.out.println("No commit with that id exists.");
			return;
		}
		CommitNode commit = history.get(commitID);
		if (commit.contains(filename) && history.containsKey(commitID)) {
			Path filePath = Paths.get(commit.getFile(filename).getCanonicalPath());
			Path tempPath = Paths.get(commitPath).relativize(Paths.get(filePath.toString()));
			Files.createDirectories(Paths.get(curPath + "/" + tempPath).getParent());
			Path target = Paths.get(curPath + "/" + tempPath);
			target = Paths.get(target.getParent() + "/" + unparseName(target.getFileName().toString()));
			Files.deleteIfExists(target);
			Files.copy(filePath, target);
		} else {
			System.out.println("File does not exist in that commit.");
		}
	}

	/**
	 * Creates a new pointer with the given name at the current head node.
	 * 
	 * @param name
	 *            The name of the new branch.
	 */

	public void branch(String name) {
		if (branches.get(name) != null) {
			System.out.println("A branch with that name already exists.");
			return;
		} else {
			branches.put(name, curNode);
			if (!splitPoints.containsKey(curNode)) {
				splitPoints.put(curNode, new ArrayList<String>());
			}
			if (!splitPoints.get(curNode).contains(curBranchName)) {
				splitPoints.get(curNode).add(curBranchName);
			}
			splitPoints.get(curNode).add(name);
		}
	}

	/**
	 * Deletes the branch pointer of the given name. Only the pointer should be
	 * deleted, not the commits.
	 * 
	 * @param name
	 *            Name of the branch to be deleted.
	 */

	public void rmbranch(String name) {
		if (branches.get(name) != null && !curBranchName.equals(name)) {
			branches.remove(name);
			return;
		}
		if (curBranchName.equals(name)) {
			System.out.println("Cannot remove the current branch.");
			return;
		} else {
			System.out.println("A branch with that name does not exist.");
			return;
		}
	}

	/**
	 * Checks out all the files at the given commit that are tracked. Moves the
	 * current branch head to the commit node with the given commit ID.
	 * 
	 * If the commit with the given ID does not exist, print an error message.
	 * 
	 * @param commitID
	 *            ID of the commit to reset.
	 * @throws IOException
	 */

	public void reset(int commitID) throws IOException {
		if (!history.containsKey(commitID)) {
			System.out.println("No commit with that id exists.");
			return;
		} else {
			for (String c : history.get(commitID).getKeySet()) {
				if (history.get(commitID).getTrack(c)) {
					checkout(commitID, c);
				}
			}
			curNode = history.get(commitID);
			branches.put(curBranchName, curNode);
		}
	}

	/**
	 * Finds the split point between two commit nodes. The split point is where
	 * the nodes have branched off. The return value should be an int array of
	 * size 2 that keeps track of the ID of the split point node and the ID of
	 * the node from the other branch that comes directly after the split point.
	 * This is a helper method for merge/x, which needs to access both IDs.
	 * 
	 * @param cur
	 *            One of the commit nodes in question. This is a helper method
	 *            for rebase and merge. Usually at the first (non-recursive)
	 *            call, the commit node passed in as cur is the current head
	 *            commit at the current branch. For this reason, it is named
	 *            cur.
	 * @param other
	 *            The other commit node in question. Usually at the first
	 *            (non-recursive) call, the commit node passed in as other is
	 *            the head node of the target merge/rebase branch. For this
	 *            reason, it is named other.
	 * @return An int array of size 2. The first item in the array is the commit
	 *         ID of the split point node. The second item in the array is ID of
	 *         the commit from the 'other branch' that comes directly after the
	 *         split point.
	 */

	private int splitPoint(CommitNode cur, CommitNode other) {
		if (cur == other) {
			return cur.getCommitID();
		}
		if (cur.getPrev() == other.getPrev()) {
			return cur.getPrev().getCommitID();
		} else if (cur.getCommitID() > other.getCommitID()) {
			return splitPoint(cur.getPrev(), other);
		} else {
			return splitPoint(cur, other.getPrev());
		}
	}

	/**
	 * Merges the given branch to the current branch. Merge considers the split
	 * point (the node at which the two branches split off), and whether a file
	 * is modified or not. A modification is either a change in contents or
	 * tracking/untracking. Case 1: Files that have been modified in the given
	 * branch since the split point but not modified in the current branch
	 * should be changed to the version in the given branch (newer version takes
	 * precedence). The files will automatically stage. Case 2: Files that have
	 * been modified in the current branch but not in the given branch should
	 * not change. Case 3: Files that have been modified in both branches since
	 * the split point should not change, but the modified file from the given
	 * branch should be copied into the working directory with '.conflicted'
	 * appended to the file name.
	 * 
	 * If merge generated at least 1 conflicted file, merge should not
	 * automatically commit. It should print an error message and put gitlet in
	 * a conflicted state. During this state, only add, rm, commit,
	 * checkout(file), checkout(id, file), log, global log, find, and status can
	 * be called. All other methods called should generate an error message
	 * until the conflict state ends.
	 * 
	 * If merge did not generate any conflicted files, merge automatically
	 * commits with a specified commit message.
	 * 
	 * @param branchname
	 *            name of the branch that should merge with the current branch.
	 * 
	 * @throws IOException
	 */

	public void merge(String branchname) throws IOException {
		if (!branches.containsKey(branchname)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (curBranchName.equals(branchname)) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}

		CommitNode givenBranch = branches.get(branchname);
		CommitNode currentBranch = curNode;
		int splits = splitPoint(givenBranch, currentBranch);
		CommitNode splitNode = history.get(splits);

		HashMap<String, Boolean> modifiedCur = checkChanges(currentBranch, splitNode);
		HashMap<String, Boolean> modifiedGiven = checkChanges(givenBranch, splitNode);
		HashMap<String, Boolean> modConflicted = checkChanges(currentBranch, givenBranch);

		for (String filename : modifiedGiven.keySet()) {
			if (modifiedGiven.get(filename) == true && modifiedCur.get(filename) == false) {

				// File modified in the given branch,
				// but not modified in the current branch >>>
				// changed to versions in given branch
				// (checked out from
				// the commit at the front of the given branch)

				checkout(givenBranch.getCommitID(), filename);
				add(filename);

				// mergeAdd(givenBranch, givenBranch.getFile(filename),
				// filename);
			} else if (modifiedCur.get(filename) == true && modifiedGiven.get(filename) == false) {
				// if current has been modified, but given has not been modified
				// DO NOTHING
				continue;
			} else if (modConflicted.get(filename) == false && modifiedCur.get(filename) == true) {
				// if files both modified and are the SAME
				// checkout and stage the given file
				// checkout(givenBranch.getCommitID(), filename);
				// DO NOTHING
				// add(filename);
				continue;
			} else if (modifiedCur.get(filename) && modifiedGiven.get(filename) && modConflicted.get(filename)) {

				// checkout the givenBranch's file >>> conflicted
				Path filePathG = Paths.get(givenBranch.getFile(filename).getCanonicalPath());
				Path tempPathG = Paths.get(commitPath).relativize(Paths.get(filePathG.toString()));
				Files.createDirectories(Paths.get(curPath + "/" + tempPathG).getParent());
				Path targetG = Paths.get(curPath + "/" + tempPathG);
				targetG = Paths
						.get(targetG.getParent() + "/" + unparseName(targetG.getFileName().toString() + ".conflicted"));
				Files.deleteIfExists(targetG);
				Files.copy(filePathG, targetG);

				conflictedState = true;
			}
		}

		if (conflictedState == false) {
			commit("Merged " + curBranchName + " with " + branchname + ".");
		} else {
			System.out.println("Encountered a merge conflict.");

		}

	}

	/**
	 * Checks to see if the given commitNode c is within the history of the
	 * given commitNode branch. This is a helper method that checks whether or
	 * not a branch exists within a branch and has not yet split.
	 * 
	 * @param c
	 *            The commitNode in question.
	 * @param branch
	 *            Representative of a branch. At the first (non-recursive call)
	 *            to inBranch from other methods, 'branch' should be the head
	 *            node of the branch in question. Each recursive call traverses
	 *            down the branch, so that each recursive call passes the
	 *            previous node in as the argument 'branch'.
	 * @return Returns true if commitNode c is in the history of a branch where
	 *         the branch's head commit is the argument 'branch'.
	 */

	private boolean inBranch(CommitNode c, CommitNode branch) {

		if (c.getCommitID() == branch.getCommitID()) {
			return true;
		} else if (c.getCommitID() > branch.getCommitID()) {
			return false;
		} else {
			return inBranch(c, branch.getPrev());
		}

	}

	/**
	 * Checks to see if the files in the commits have changed. Stores a true
	 * value if the files are different and a false value if the files are
	 * identical.
	 * 
	 * @param cur
	 * @param other
	 * @return A HashMap that stores whether each file in the given CommitNodes
	 *         has been changed.
	 * 
	 * @throws IOException
	 */

	private HashMap<String, Boolean> checkChanges(CommitNode cur, CommitNode other) throws IOException {
		// cur has the most recent files - has more files
		HashMap<String, Boolean> changed = new HashMap<String, Boolean>();
		// true means that the files have been changed
		// false means that the files have not been changed - are the same

		for (String filename : cur.getKeySet()) {

			byte[] curFile = Files.readAllBytes(cur.getFile(filename).toPath());

			if (other.getFile(filename) == null) {
				changed.put(filename, true);
				continue;
			}

			byte[] otherFile = Files.readAllBytes(other.getFile(filename).toPath());

			if (cur.getTrack(filename) != other.getTrack(filename)) {
				changed.put(filename, true); // file changed
				continue;
			}

			if (Arrays.equals(curFile, otherFile)) {
				changed.put(filename, false); // file same
			} else {
				changed.put(filename, true); // file changed
			}
		}
		return changed;
	}

	/**
	 * Replays the commits of the current branch starting from the split point
	 * onto the given branch. If the current branch pointer is in the history of
	 * the given branch, rebase moves the current branch pointer to the the
	 * given branch's most recent commit.
	 * 
	 * If files in the given branch have been modified since the split point,
	 * the given branch's file should propagate through the replayed commits
	 * until the current branch has a modified version of the file. The current
	 * branch's modified files will always take precedence over the given
	 * branch's files.
	 * 
	 * 
	 * @param branchname
	 * @throws IOException
	 */

	public void rebase(String branchname) throws IOException {
		CommitNode otherBranch = branches.get(branchname);
		CommitNode myBranch = curNode;
		int splits = splitPoint(curNode, otherBranch);
		CommitNode splitNode = history.get(splits);
		Stack<CommitNode> track = new Stack<CommitNode>();
		if (!branches.containsKey(branchname)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (curBranchName.equals(branchname)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}
		if (curNode.getCommitID() == otherBranch.getCommitID()) {
			System.out.println("Already up-to-date.");
			return;
		}
		if (curNode.getCommitID() < otherBranch.getCommitID() && inBranch(curNode, otherBranch)) {
			branches.put(curBranchName, branches.get(branchname));
			return;
		}
		// else case
		while (myBranch != splitNode) {
			track.push(myBranch);
			myBranch = myBranch.getPrev();
		}
		CommitNode otherBranchPointer = otherBranch;
		while (!track.isEmpty()) {
			CommitNode temp = track.pop();
			HashMap<String, Boolean> myChange = checkChanges(temp, splitNode);
			commitCount++;
			history.put(commitCount, new CommitNode(temp.getMsg(), otherBranchPointer, commitCount));
			otherBranchPointer = history.get(commitCount);
			// check to see which file changes should propagate through replay
			otherBranchPointer.copyNodeFiles(otherBranch);
			for (String filename : temp.getKeySet()) {
				if (myChange.get(filename) == true) { // true = changed
					// my change takes precedence
					otherBranchPointer.addFile(filename, temp.getFile(filename));
				}
			}
		}
		curNode = otherBranchPointer;
		branches.put(curBranchName, otherBranchPointer);
		for (String filename : curNode.getKeySet()) {
			checkout(commitCount, filename);
		}
	}

	/**
	 * Looks in the serialized file for data to load the previous Gitlet state.
	 * 
	 * @return Returns the previous Gitlet state. If the previous Gitlet state
	 *         does not exist, returns null.
	 */

	private static Gitlet tryLoadingMyGit() {
		Gitlet myGit = null;
		File gitFolder = new File(".gitlet");
		File myGitFile = new File(gitFolder, "git.ser");

		if (myGitFile.exists()) {
			try {
				FileInputStream fileIn = new FileInputStream(myGitFile);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				myGit = (Gitlet) objectIn.readObject();
				fileIn.close();
				objectIn.close();
			} catch (IOException e) {
				System.out.println(e);
			} catch (ClassNotFoundException e) {
				String msg = "ClassNotFoundException while loading Gitlet.";
				System.out.println(msg);
			}
		}
		return myGit;
	}

	/**
	 * Saves the current Gitlet state in git.ser
	 * 
	 * @param myGit
	 *            The version of Gitlet to save.
	 */

	private static void saveMyGit(Gitlet myGit) {
		if (myGit == null) {
			return;
		}
		try {
			File myGitFile = new File(".gitlet/git.ser");
			FileOutputStream fileOut = new FileOutputStream(myGitFile);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

			objectOut.writeObject(myGit);
			objectOut.close();
			fileOut.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * CREDIT TO ARMANI FERRANTE - OVERFLOW.JAVA
	 * 
	 * Converts the second argument of a string array to an integer.
	 * 
	 * Exception: throws NumberFormatException if argument cannot be converted.
	 * 
	 * @param args
	 *            The array in which the second element should be converted.
	 * @return Returns the int if the argument can be parsed. If the argument
	 *         cannot be parsed, returns -1.
	 */

	private static int convertSecondArgumentToInt(String[] args) {
		if (args.length >= 2) {
			try {
				return Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				return -1;
			}
		} else {
			return -1;
		}
	}

	/**
	 * Main method. Allows Gitlet to run from the terminal. Maintains only one
	 * instance of Gitlet - loads and saves from Gitlet's previous state. Also
	 * handles the conflicted state.
	 * 
	 * @param args
	 * @throws IOException
	 */

	public static void main(String[] args) throws IOException {
		Gitlet savedGit = tryLoadingMyGit();
		if (savedGit == null) {
			savedGit = new Gitlet();
		}

		Scanner inp = new Scanner(System.in);
		String arg2;
		if (args.length == 0) {
			System.out.println("Please enter a command.");
			return;
		}
		if (args.length == 1) {
			arg2 = null;
		} else {
			arg2 = args[1];
		}

		switch (args[0]) {
		case "init":
			savedGit.init();
			break;
		case "add":
			savedGit.add(arg2);
			break;
		case "commit":
			savedGit.commit(arg2);
			break;
		case "rm":
			savedGit.rm(arg2);
			break;
		case "log":
			savedGit.log();
			break;
		case "global-log":
			savedGit.globalLog();
			break;
		case "find":
			savedGit.find(arg2);
			break;
		case "status":
			savedGit.status();
			break;
		case "checkout":
			if (args.length > 2) {
				int numArg2 = convertSecondArgumentToInt(args);
				String arg3 = args[2];
				savedGit.checkout(numArg2, arg3);
			} else {
				savedGit.checkout(arg2);
			}
			break;
		case "branch":
			if (savedGit.conflictedState) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			savedGit.branch(arg2);
			break;
		case "rm-branch":
			if (savedGit.conflictedState) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			savedGit.rmbranch(arg2);
			break;
		case "reset":
			if (savedGit.conflictedState) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			int numArg2 = convertSecondArgumentToInt(args);
			savedGit.reset(numArg2);
			break;
		case "merge":
			if (savedGit.conflictedState) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			savedGit.merge(arg2);
			break;
		case "rebase":
			if (savedGit.conflictedState) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			savedGit.rebase(arg2);
			break;
		default:
			System.out.println("No command with that name exists.");
			break;

		}

		saveMyGit(savedGit);
	}
}