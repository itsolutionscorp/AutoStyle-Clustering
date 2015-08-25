import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * A class that executes the commands on the Gitlet object.
 */
public class Commands {

	public static Gitlet gitlet;

	/**
	 * Initializes a new Gitlet repository. Does nothing if a gitlet repository
	 * has already been created
	 */
	public static void init() {

		try {
			boolean success = gitlet.createRepository();
			if (!success) {
				System.out
						.println("A gitlet version control system already exists in the current directory.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds the file to the staged list and copies the file to the commit
	 * folder.
	 * 
	 * @param fileName
	 *            Name of file to be added when staged. Assumes path is
	 *            ./fileName
	 */
	public static void add(String fileName) {
		String path = "./" + fileName;
		add(fileName, path);

	}

	/**
	 * Add command where the path is not in the pwd. Used by rebase and merge
	 * 
	 * @param fileName
	 *            file name
	 * @param path
	 *            relative path of the file
	 */
	public static void add(String fileName, String path) {
		Commit next = gitlet.getNextCommit();
		if (!FileIO.pathExists(path)) {
			System.out.println("File does not exist.");

			return;
		} else if (!next.isUntracked(fileName)) { // marked for untracking,
													// change to mark for
													// tracking
			try {
				gitlet.addStagingArea(fileName, path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		next.startTracking(fileName);

	}

	/**
	 * removes a file from the staging area if it has been staged or marks the
	 * file for untracking if the file is being tracked but not staged.
	 * 
	 * @param fileName
	 *            Path of file
	 */
	public static void rm(String fileName) {
		Commit next = gitlet.getNextCommit();
		if (!gitlet.getStagingArea().contains(fileName)
				&& !next.isTracked(fileName)) {
			System.out.println("No reason to remove the file.");
		} else if (gitlet.isStaged(fileName)) {
			gitlet.getStagingArea().remove(fileName);
			if (!next.getParent().containsFile(fileName)) {
				next.stopTracking(fileName);
			}
		} else {
			next.startUntracking(fileName);
		}
	}

	public static void commit(String message) {
		commit(message, false);
	}

	/**
	 * Commits nextCommit. Will attach nextCommit as the head pointer of the
	 * current branch.
	 * 
	 * @param message
	 *            the Commit message.
	 * @param fromRebase
	 *            Whether the commit was from rebase. No changes would be added
	 *            if from rebase, because only making a copy
	 */
	public static void commit(String message, boolean fromRebase) {
		if (gitlet.getNextCommit().getId() != 0
				&& gitlet.getStagingArea().size() == 0
				&& gitlet.getNextCommit().getUntracked().size() == 0) {
			if (!fromRebase) {
				System.out.println("No changes added to the commit.");
				return;
			}
		}
		if (message.equals("")) {
			System.out.println("Please enter a commit message.");
			return;
		}
		boolean allBlanks = true;
		for (char c : message.toCharArray()) {
			if (c != ' ') {
				allBlanks = false;
				break;
			}
		}
		if (allBlanks == true) {
			System.out.println("Please enter a commit message.");
			return;
		}
		Commit next = gitlet.getNextCommit();
		Commit parent = next.getParent();
		if (parent != null)
			parent.addChildren(next);
		try {
			for (String file : gitlet.getStagingArea()) {
				String filePath = FileIO.GITLET_PATH + "/" + next.getId() + "/"
						+ file;
				next.addStaged(file);
				next.addFile(file, filePath);
				FileIO.copy(FileIO.STAGING_AREA + "/" + file, filePath);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (parent != null) {
			for(String file : parent.getTracked().keySet()) {
				String value = parent.getTracked().get(file);
				if(next.getUntracked().contains(file) || gitlet.getStagingArea().contains(file)) {
					continue;
				}
				next.addFile(file, value);
			}
		}
		gitlet.setConflicted(false);
		gitlet.resetStagingArea();
		next.setCommitted();
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
		String date = time.format((new Date()));
		next.setTime(date);
		next.setMessage(message);
		gitlet.newCommit();
	}

	/**
	 * Prints a log of the commits in the branch. Starts from the head pointer
	 * and descending to the first commit.
	 */
	public static void log() { // prints commits on a branch

		Commit current = gitlet.getCurrentBranch();
		Commit commit = current;
		// iterate through all commmits
		while (commit != null) {
			commit.helperPrintLog(commit.getParent() != null);
			commit = commit.getParent();
		}

	}

	/**
	 * Prints a log of all submitted commits. (Called on by Commands.commit())
	 * Iterates by id number. Starts at commit ID 0, and ends at the last
	 * commit.
	 */
	public static void global_log() {
		ArrayList<Commit> commits = gitlet.getCommits();
		for (Commit commit : commits) {
			if (!commit.hasCommitted())
				continue;
			commit.helperPrintLog(commit.getId() != commits.size() - 2);
		}
	}

	/**
	 * Prints out all of the ids with the given commit message
	 * 
	 * @param message
	 *            Message of commit.
	 */
	public static void find(String message) { // print IDs
		ArrayList<Commit> commits = gitlet.getCommits();
		boolean notFound = true;
		for (Commit commit : commits) {
			if (commit.hasCommitted()) {

				//System.out.println(commit.getMessage().equals(message) + " " + commit.getMessage() + " " + message);
				if (commit.getMessage().equals(message)) {
					System.out.println(commit.getId());
					notFound = false;
				}
			}
		}
		if (notFound) {
			System.out.println("Found no commit with that message.");
		}
	}

	/**
	 * prints branches, current branch w/ *. Displays staged, and untracked
	 * files
	 */
	public static void status() {
		Set<String> branches = gitlet.getBranches().keySet();
		System.out.println("=== Branches ===");
		for (String branch : branches) {
			if (branch.equals(gitlet.getCurrentBranchName())) {
				System.out.println("*" + branch);
			} else {
				System.out.println(branch);
			}
		}
		System.out.println("\n" + "=== Staged Files ===");
		Commit current = gitlet.getNextCommit();
		for (String file : gitlet.getStagingArea()) {
			System.out.println(file);
		}
		System.out.println("\n" + "=== Files Marked for Untracking ===");
		for (String file : current.getUntracked()) {
			System.out.println(file);
		}
	}

	/**
	 * Taken from Stackexchange. Checks if an string int is Int parsable
	 * 
	 * @param input
	 *            The String to check
	 * @return True if the String can be parsed as an integer.
	 */
	public static boolean isParsable(String input) {
		boolean parsable = true;
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException e) {
			parsable = false;
		}
		return parsable;
	}

	/**
	 * Switches branches, Brings current file at head, and places replaces in
	 * working directory Takes file at a given commit ID and places in working
	 * directory
	 * 
	 * @param args
	 *            Arguments for the checkout method.
	 */
	public static void checkout(String[] args) {
		// System.out.println(gitlet.getBranch("master"));
		if (gitlet.containsBranch(args[0])) {
			checkoutBranch(args[0]);
		} else if (isParsable(args[0]) && args.length == 2) { // to edit
			checkoutID(Integer.parseInt(args[0]), args[1]); // takes in ID
															// argument, and
															// filename argument
		} else { // files
			checkoutFile(args[0]);
		}
	}

	/**
	 * To be implemented in general checkout method this method takes in a
	 * commit ID and fileName, places the file w/ the fileName from the commit
	 * w/ the same ID and puts it in the working directory
	 * 
	 * @param id
	 *            ID of the file to checkout
	 * @param fileName
	 *            path of file relative to commit folder to place in PWD
	 */

	public static void checkoutID(Integer id, String fileName) {
		Commit commit = gitlet.getCommit(id);
		if (commit == null || !commit.hasCommitted()) {
			System.out.println("No commit with that id exists.");
			return;
		}
		if (!commit.containsFile(fileName)) {
			System.out.println("File does not exist in that commit.");
			return;
		}
		try {
			commit.placeFileInPWD(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * To implemented in the general checkout method, this method receives a
	 * single string representing a file name and takes the file from head
	 * commit, the front of the current branch and replaces the already existing
	 * file from the working directory if it is already there
	 * 
	 * @param fileName
	 *            - a String representing the file to be added
	 */
	// by Andrew

	public static void checkoutFile(String fileName) {
		HashMap<String, String> files = gitlet.getCurrentBranch().getFiles();

		if (!gitlet.getCurrentBranch().containsFile(fileName)) {
			System.out
					.println("File does not exist in the most recent commit, or no such branch exists.");
			return;
		}
		// gitlet.putDirectory(fileName,
		// gitlet.getCurrentCommit().getFile(fileName)); // replaces the working
		// directory file key/value with the checkedOut one
		try {
			gitlet.getCurrentBranch().placeFileInPWD(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * To be implemented in general checkout method this method receives a
	 * branch name and places all files at the head of the branch in the working
	 * directory
	 * 
	 * @param branchName
	 *            Name of branch
	 */

	public static void checkoutBranch(String branchName) {
		if (gitlet.isConflicted()) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}

		if (!gitlet.containsBranch(branchName)) {
			System.out
					.println("File does not exist in the most recent commit, or no such branch exists.");
			return;
		} else if (gitlet.getCurrentBranchName().equals(branchName)) {
			System.out.println(" No need to checkout the current branch.");
			return;
		}
		gitlet.switchCurrentBranch(branchName);
		try {
			if (gitlet.getCurrentBranch() != null) {

				gitlet.getCurrentBranch().replacePWD();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * creates a new branch w/ a new name
	 * 
	 * @param name
	 *            Name of branch to add. Makes the head pointer of the branch
	 *            the same as the current branch.
	 */
	public static void branch(String name) {
		if (gitlet.isConflicted()) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		if (gitlet.getBranches().containsKey(name)) {
			System.out.println("A branch with that name already exists.");
			return;
		}
		gitlet.addBranch(name, gitlet.getCurrentBranch());
	}

	/**
	 * deletes the pointer associated w/ branch does not mean to delete all
	 * commits
	 * 
	 * @param branchName
	 *            Name of branch to delete
	 */
	public static void rm_branch(String branchName) {
		if (!gitlet.containsBranch(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (branchName.equals(gitlet.getCurrentBranchName())) {
			System.out.println("Cannot remove the current branch.");
			return;
		} else {
			gitlet.removeBranch(branchName);
		}
	}

	/**
	 * Checks out all the files tracked by the given commit. Also moves the
	 * current branch's head to that commit node.
	 * 
	 * @param id
	 *            ID of the commit to reset to.
	 */
	public static void reset(int id) {
		Commit commit = gitlet.getCommit(id);
		if (commit == null || !commit.hasCommitted()) {
			System.out.println("No commit with that id exists.");
			return;
		}
		gitlet.switchCommit(commit);
		gitlet.moveBranch(gitlet.getCurrentBranchName(), commit);
		gitlet.getNextCommit().setParent(gitlet.getCurrentBranch());
		try {
			commit.replacePWD();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Merges the branch associated with the given branch name to the current
	 * branch.
	 * 
	 * @param branchName
	 *            Name of branch.
	 */
	public static void merge(String branchName) {
		Commit b2 = gitlet.getBranch(branchName);
		if (!gitlet.containsBranch(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (branchName.equals(gitlet.getCurrentBranchName())) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}
		Commit split = getSplitPoint(gitlet.getCurrentBranchName(), branchName);
		Commit currIter = gitlet.getCurrentBranch();
		HashSet<String> modifiedFiles = new HashSet<String>();
		while (currIter != split) {
			for (String file : currIter.getStaged()) {
				if (!modifiedFiles.contains(file))
					modifiedFiles.add(file);
				//System.out.println(file + " current branch");
			}
			currIter = currIter.getParent();
		}
		Commit b2Iter = gitlet.getBranch(branchName);
		/*
		 * Keeps track of which files have been seen to ensure most recent
		 * version. Tracked files?
		 */
		HashSet<String> files = new HashSet<String>();
		try {
			while (b2Iter != split) {
				for (String file : b2Iter.getStaged()) {
					//System.out.println(file + " branch 2");
					//System.out.println(file + " " + b2Iter.getId());
					if (files.contains(file))
						continue;
					files.add(file);
					String conflicted = "";
					/* Merge conflict. Modified file in both branches */
					if (modifiedFiles.contains(file)) {
						//System.out.println("conflicted " + file);
						conflicted = ".conflicted";
						gitlet.setConflicted(true);
					}
					FileIO.copy(b2Iter.getFiles().get(file), file + conflicted);
					add(file + conflicted);
				}
				b2Iter = b2Iter.getParent();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (!gitlet.isConflicted()) {
			commit("Merged " + gitlet.getCurrentBranchName() + " with "
					+ branchName + ".");
		} else {
			System.out.println("Encountered a merge conflict.");
		}
	}

	/**
	 * Retrieves the split point between two branches
	 * 
	 * @param b1
	 *            Branch 1
	 * @param b2
	 *            Branch 2
	 * @return The commit that is the split point between the two. Null if there
	 *         is none. (Should never be null)
	 */
	public static Commit getSplitPoint(String b1, String b2) {
		Commit currentCommit = gitlet.getBranch(b1);
		Commit b2Iter = gitlet.getBranch(b2);
		HashSet<Integer> trace = new HashSet<Integer>(); // trace.contains(i) if
															// currentCommit
															// owns commit i
		while (currentCommit != null) {
			trace.add(currentCommit.getId());
			currentCommit = currentCommit.getParent();
		}
		while (b2Iter != null) {
			if (trace.contains(b2Iter.getId()))
				return b2Iter; // found a common link
			b2Iter = b2Iter.getParent();
		}

		return null; // no split point found
	}

	/**
	 * Copies all commits since the split point in the given branch to the
	 * current branch. Copies have different timestamps and ids.
	 * 
	 * @param name
	 *            Name of branch to copy from.
	 */
	public static void rebase(String name) {
		if (gitlet.isConflicted()) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved");
			return;
		}

		Commit b2 = gitlet.getBranch(name);
		if (b2 == null) {
			System.out.println("A branch with that name does not exist.");
			return;
		}

		if (b2 == gitlet.getCurrentBranch()) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}
		Commit head = gitlet.getCurrentBranch();
		String headNode = gitlet.getCurrentBranchName();

		gitlet.switchCurrentBranch(name);
		getSplitPoint(name, headNode);
		Commit split = getSplitPoint(name, headNode);

		if (split == gitlet.getBranch(name)) {
			System.out.println("Already up-to-date.");
		}

		if (split == gitlet.getCurrentBranch()) {
			gitlet.moveBranch(headNode, gitlet.getBranch(name));
		}
		Stack<Commit> rebase = new Stack<Commit>();

		for (Commit commit = head; commit != split; commit = commit.getParent()) {
			rebase.push(commit);
		}
		boolean attached = false;
		for (Commit commit = b2; !rebase.isEmpty(); commit = commit
				.getChildren().get(commit.getChildren().size() - 1)) {
			Commit next = gitlet.getNextCommit();
			Commit copy = rebase.pop();

			//System.out.println("rebasing " + next.getId());
			next.createCopy(copy);
			if (!attached) {
				next.setParentRebase(b2);
				attached = true;
				//System.out.println(next.getParent().getId());
			}
			gitlet.setNextCommit(next);
			commit(copy.getMessage(), true);
		}
		Commit newHead = gitlet.getBranch(name);
		//System.out.println(newHead.getId());
		gitlet.moveBranch(name, b2);
		gitlet.moveBranch(headNode, newHead);
		gitlet.switchCurrentBranch(headNode);
	}

	/**
	 * Determines whether the command is a conflicted command (May be called in
	 * a conflicted state)
	 * 
	 * @param command
	 *            The command
	 * @return True if command may be called in a conflicted state
	 */
	private static boolean conflictedCommand(String command) {
		return command.equals("add") || command.equals("rm")
				|| command.equals("commit") || command.equals("log")
				|| command.equals("global-log") || command.equals("checkout")
				|| command.equals("find") || command.equals("status");
	}

	/**
	 * Executes the command or prints an error if the command does not exist.
	 * 
	 * @param command
	 *            The command
	 * @param args
	 *            Arguments associated to the command.
	 */
	public static void callCommand(String command, String args[]) {
		if (command.equals("init")) {
			init();
			return;
		}
		if (gitlet.isConflicted() && !conflictedCommand(command)) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}

		if (FileIO.pathExists(FileIO.GITLET_PATH)) {
			if (command.equals("add")) {
				add(args[0]);
			} else if (command.equals("rm")) {
				rm(args[0]);
			} else if (command.equals("commit")) {
				if (args.length < 1) {
					System.out.println("Please enter a commit message.");
					return;
				}
				commit(args[0]);
			} else if (command.equals("log")) {
				log();
			} else if (command.equals("global-log")) {
				global_log();
			} else if (command.equals("find")) {
				find(args[0]);
			} else if (command.equals("status")) {
				status();
			} else if (command.equals("checkout")) {
				checkout(args);
			} else if (command.equals("branch")) {
				branch(args[0]);
			} else if (command.equals("rm-branch")) {
				rm_branch(args[0]);
			} else if (command.equals("reset")) {
				reset(Integer.parseInt(args[0]));
			} else if (command.equals("merge")) {
				merge(args[0]);
			} else if (command.equals("rebase")) {
				rebase(args[0]);
			} else {
				System.out.println("No command with that name exists.");
			}

		}
	}

}
