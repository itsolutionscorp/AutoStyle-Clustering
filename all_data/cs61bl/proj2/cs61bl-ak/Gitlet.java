import java.io.File;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Gitlet implements Serializable {

	private static final long serialVersionUID = 12345L;
	private static int instance; // tracks how many instances of gitlet made
	private CommitBranch active; // the master branch
	private HashMap<String, CommitBranch> branches; // list of all branches, so
													// we can
	// switch active branches
	private HashMap<String, File> tracked; // all files that have been marked
											// for
											// untracking
	private HashMap<String, ArrayList<Commit>> commits; // map of all commits,
														// messages as
	// keys, id as values
	private ArrayList<Commit> commitsbyId;
	private int nextId; // the id of the next commit
	private DateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final String GITLET_DIR = ".gitlet/";
	private static final String STAGE_DIR = ".gitlet/stage/";
	private boolean conflicted = false;

	/**
	 * creates a gitlet version control system
	 * <p>
	 * check if another instance of Gitlet was made, abort if there is then make
	 * .gitlet directory and stage directory within .gitlet myDirectory is the
	 * path for .gitlet then make git.ser serializable in .gitlet make a new
	 * CommitBranch() and assign it to active calling CommitBranch() makes a new
	 * commit with message "initial commit" marked is the list of all files that
	 * are marked for untracking add the initial commit to commits add the
	 * active branch to branches myStage is the path to the stage directory in
	 * .gitlet myId will be the id of the upcoming commit, it increments each
	 * time a new commit is made then serialize "this" to git.ser in .gitlet
	 * <p>
	 * 
	 * @returns a Gitlet object
	 * @throws IOException
	 */
	public Gitlet() {
		if (instance != 0) {
			System.out
					.print("A gitlet version control system already exists in the current directory");
			System.exit(0);
		}
		instance = 1;
		File directory = new File(GITLET_DIR);
		directory.mkdir();
		File stage = new File(STAGE_DIR);
		stage.mkdir();
		File state = new File(".gitlet/git.ser");

		active = new CommitBranch("master");
		tracked = new HashMap<String, File>();
		commits = new HashMap<String, ArrayList<Commit>>();
		ArrayList<Commit> initial = new ArrayList<Commit>();
		initial.add(active.myHead);
		commits.put(active.myHead.myMessage, initial);
		branches = new HashMap<String, CommitBranch>();
		commitsbyId = new ArrayList<Commit>();
		commitsbyId.add(active.myHead);
		branches.put("master", active);
		nextId = 1;

		try {
			FileOutputStream stateOut = new FileOutputStream(state);
			ObjectOutputStream out = new ObjectOutputStream(stateOut);
			out.writeObject(this);
			stateOut.close();
			out.close();
		} catch (IOException e) {
			System.out.println("init failed");
		}
	}

	/**
	 * stages a file if it is not marked for untracking, removes mark if marked
	 * <p>
	 * copy the file to the stage directory if it is not marked a file is marked
	 * if its value is null if it is marked, change its value to reference the
	 * file tracked by its parents
	 * <p>
	 * 
	 * @param fileName
	 *            a string that will be used to find the staged file
	 * @throws IOException
	 */
	public void add(String fileName) throws IOException {
		File tobeadded = new File(fileName);
		if (tobeadded.exists()) {
			if (tracked.containsKey(fileName) && tracked.get(fileName) == null) {
				tracked.put(fileName, active.myHead.myFiles.get(fileName));
			} else {
				tracked.put(fileName, tobeadded);
				File staged = new File(STAGE_DIR + fileName);
				staged.getParentFile().mkdirs();
				Files.copy(Paths.get(fileName),
						Paths.get(STAGE_DIR + fileName),
						StandardCopyOption.REPLACE_EXISTING);
			}
		} else {
			System.out.println("File does not exist.");
		}
	}

	/**
	 * creates a commit, and moves all staged files to a unique commit directory
	 * <p>
	 * move all files in stage directory to a new directory with commit id as
	 * name, call active's commit method, which adds another node to the commit
	 * tree makes a commit that has references to all the same files as its
	 * previous commit the Gitlet commit should remove any marked files from the
	 * current commit clear marked list and stage directory when finished
	 * <p>
	 * 
	 * @param msg
	 *            a string used for the commit's message
	 * @throws IOException
	 */
	public void commit(String msg) throws IOException {
		File stage = new File(STAGE_DIR);
		if (stage.listFiles().length > 0
				|| !tracked.equals(active.myHead.myFiles)) {
			if (msg.isEmpty()) {
				System.out.println("Please enter a commit message.");
				return;
			}

			File newCommit = new File(GITLET_DIR + nextId);
			newCommit.mkdir();

			Set<String> trackedFiles = tracked.keySet();
			ArrayList<String> toRemove = new ArrayList<String>();
			for (String name : trackedFiles) {
				File staged = new File(STAGE_DIR + name);
				if (staged.exists()) {
					File moveTo = new File(GITLET_DIR + nextId + "/" + name);
					moveTo.getParentFile().mkdirs();
					Files.move(Paths.get(staged.getPath()),
							Paths.get(GITLET_DIR + "/" + nextId + "/" + name),
							StandardCopyOption.REPLACE_EXISTING);
					File tracking = new File(GITLET_DIR + nextId + "/" + name);
					tracked.put(name, tracking);
				}
				if (tracked.get(name) == null) {
					toRemove.add(name);
					continue;
				}
			}
			for (String removed : toRemove) {
				tracked.remove(removed);
			}
			for (File f : stage.listFiles()) {
				delete(f);
			}

			active.commit(nextId, msg, new HashMap<String, File>(tracked));
			if (commits.containsKey(msg)) {
				commits.get(msg).add(active.myHead);
			} else {
				ArrayList<Commit> added = new ArrayList<Commit>();
				added.add(active.myHead);
				commits.put(msg, added);
			}
			commitsbyId.add(active.myHead);
			nextId++;
		} else {
			System.out.println("No changes added to the commit.");
		}
	}

	/**
	 * removes a file from the staging area, or marks it for untracking
	 * 
	 * @param name
	 *            a String used find the file to be removed
	 * @throws IOException
	 */
	public void rm(String name) throws IOException {
		File toRemove = new File(STAGE_DIR + name);
		if (toRemove.exists()) {
			delete(toRemove);
			if (!toRemove.getParentFile().getName().equals(STAGE_DIR)
					&& toRemove.getParentFile().listFiles().length == 0) {
				toRemove.getParentFile().delete();
			}
			if (active.myHead.myFiles.containsKey(name)) {
				tracked.put(name, active.myHead.myFiles.get(name));
			} else {
				tracked.remove(name);
			}
		} else if (tracked.containsKey(name)) {
			if (tracked.get(name) == null) {
				System.out.println("No reason to remove the file.");
			} else {
				tracked.put(name, null);
			}
		} else {
			System.out.println("No reason to remove the file.");
		}
	}

	/**
	 * prints info of all commits in the active commit branch
	 */
	public void log() {
		active.log();
	}

	/**
	 * prints info of all commits made
	 */
	public void global_log() {
		for (Commit c : commitsbyId) {
			c.printMe();
		}
	}

	/**
	 * prints the id(s) of the commit(s) with corresponding message
	 * 
	 * @param message
	 *            a String used to find the commits(s) to print
	 */
	public void find(String message) {
		if (commits.containsKey(message)) {
			for (Commit c : commits.get(message)) {
				System.out.println(c.myId);
			}
		} else {
			System.out.println("Found no commit with that message.");
		}
	}

	/**
	 * prints all branches, staged files, and marked files
	 * <p>
	 * iterate through branches and print the names along the way iterate
	 * through the stage directory and print along the way iterate through
	 * marked and print along the way
	 * <p>
	 */
	public void status() {
		System.out.println("=== Branches ===");
		Set<String> branchNames = branches.keySet();
		System.out.println("*" + active.myName);
		for (String name : branchNames) {
			if (name.equals(active.myName)) {
				continue;
			}
			System.out.println(name);
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		Set<String> files = tracked.keySet();
		File stage = new File(STAGE_DIR);
		for (File f : stage.listFiles()) {
			printR(f);
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (String fileName : files) {
			if (tracked.get(fileName) == null) {
				System.out.println(fileName);
			}
		}
	}

	/**
	 * recursively prints the name of all files in a directory
	 * 
	 * @param parent
	 *            a string used to find the directory containing files to print
	 */
	private static void printR(File parent) {
		if (!parent.isDirectory()) {
			System.out.println(parent.getName());
		} else if (parent.listFiles().length == 0) {
			System.out.println(parent.getName());
		} else {
			for (File child : parent.listFiles()) {
				System.out.print(parent.getName() + "/");
				printR(child);
			}
		}
	}

	/**
	 * restores a current file or all the files at the head of the given branch
	 * <p>
	 * check whether name is a file name or branch name if file name, copy the
	 * file as it exists in the active branch to working directory if branch
	 * name, copy all files in its head to working directory, switch active to
	 * this branch
	 * <p>
	 * 
	 * @param name
	 *            a String used to find the branch or file to checkout
	 */
	public void checkout(String name) throws IOException {
		if (tracked.containsKey(name)) {
			Files.copy(Paths.get(tracked.get(name).getPath()), Paths.get(name),
					StandardCopyOption.REPLACE_EXISTING);
		} else if (branches.containsKey(name)) {
			if (conflicted == true) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				return;
			}
			if (name.equals(active.myName)) {
				System.out.println("No need to checkout the current branch.");
				return;
			}
			HashMap<String, File> branchFiles = branches.get(name).myHead.myFiles;
			Set<String> files = branchFiles.keySet();
			for (String file : files) {
				if (branchFiles.get(file).isDirectory()) {
					delete(new File(file));
					Files.copy(Paths.get(branchFiles.get(file).getPath()),
							Paths.get(file),
							StandardCopyOption.REPLACE_EXISTING);
					continue;
				}
				Files.copy(Paths.get(branchFiles.get(file).getPath()),
						Paths.get(file), StandardCopyOption.REPLACE_EXISTING);
			}
			active = branches.get(name);
		} else {
			System.out
					.println("File does not exist in the most recent commit, or no such branch exists.");
		}
	}

	/**
	 * restores a file in the active branch or all versions of files in a given
	 * commit branch
	 * <p>
	 * check whether name is a file name or branch name if file name, copy the
	 * file as it exists in the active branch to working directory if branch
	 * name, copy all files in its head to working directory, switch active to
	 * this branch
	 * <p>
	 * 
	 * @param name
	 *            a string used to find the file or the branch to checkout
	 * @throws IOException
	 */
	public void checkout(int id, String name) throws IOException {
		if (id < nextId) {
			if (commitsbyId.get(id).myFiles.containsKey(name)) {
				File checkout = commitsbyId.get(id).myFiles.get(name);
				delete(new File(name));
				Files.copy(Paths.get(checkout.getAbsolutePath()),
						Paths.get(name), StandardCopyOption.REPLACE_EXISTING);
			} else {
				System.out.println("File does not exist in that commit.");
			}
		} else {
			System.out.println("No commit with that id exists.");
		}
	}

	/**
	 * recursively deletes all files in a directory
	 * 
	 * @param file
	 *            a string used to find the files to delete
	 * @throws IOException
	 */
	public void delete(File file) throws IOException {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File i : files) {
				delete(i);
			}
		}
		file.delete();
	}

	/**
	 * creates a new branch a the head of the active branch
	 * <p>
	 * make a new branch object by calling branch() on active branch branch()
	 * will return a new branch object with the exact same head then add this
	 * new branch to ArrayList Branches
	 * <p>
	 * 
	 * @param branchName
	 *            a string used to give the new branch its name
	 */
	public void branch(String branchName) {
		if (branches.containsKey(branchName)) {
			System.out.println("A branch with that name already exists.");
		} else {
			branches.put(branchName, active.branch(branchName));
		}
	}

	/**
	 * removes the pointer to the given branch
	 * 
	 * @param branchName
	 *            a string used to find the branch to remove
	 * 
	 */
	public void rm_branch(String name) {
		if (branches.containsKey(name)) {
			if (branches.get(name) == active) {
				System.out.println("Cannot remove the current branch.");
			} else {
				branches.remove(name);
			}
		} else {
			System.out.println("A branch with that name does not exist.");
		}
	}

	/**
	 * checks out all files in given commit and reassigns active head to that
	 * commit
	 * 
	 * @param id
	 *            integer used to find the commit to reset to
	 * @throws IOException
	 */
	public void reset(int id) throws IOException {
		if (id < nextId && id >= 0) {
			Commit resetCommit = commitsbyId.get(id);
			active.reset(resetCommit);
			Set<String> files = resetCommit.myFiles.keySet();
			for (String file : files) {
				checkout(id, file);
			}
		} else {
			System.out.println("No commit with that id exists.");
		}
	}

	/**
	 * merges the heads of the current branch head and the given branch head
	 * <p>
	 * iterate through prevs of active and branchName until a common split node
	 * is reached compare the files of the split node with other files in the
	 * given branch, if the branch does not exist print error. if there are
	 * modifications, checkout the head of the given branch if there are
	 * modifications in the current branch as well copy the version in the given
	 * branch to the working directory with name [old file name].conflicted if
	 * there are no conflicted files, then commit with msg
	 * "Merged [current branch name] with [given branch name]." if conflicted
	 * files encountered, go into conflicted state
	 * <p>
	 * 
	 * @param branchName
	 *            a string used to find the branch to merge with
	 * @throws IOException
	 */
	public void merge(String branch_name) throws IOException {
		if (!branches.containsKey(branch_name)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (branch_name.equals(active.myName)) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}
		Commit split = findSplit(branches.get(branch_name).myHead,
				active.myHead);
		Set<String> split_files = split.myFiles.keySet();
		HashMap<String, File> given_files = branches.get(branch_name).myHead.myFiles;
		HashMap<String, File> current_files = active.myHead.myFiles;
		for (String file : split_files) {
			if (!current_files.containsKey(file)
					&& !given_files.containsKey(file)) {
				continue;
			} else if (current_files.containsKey(file)
					&& !given_files.containsKey(file)) {
				continue;
			} else if (given_files.containsKey(file)
					&& !current_files.containsKey(file)) {
				checkout(branches.get(branch_name).myHead.myId, file);
				add(file);
			} else if (split.myFiles.get(file) != current_files.get(file)
					&& split.myFiles.get(file) != given_files.get(file)) {
				File conflict = new File(file + ".conflicted");
				conflict.getParentFile().mkdirs();
				Files.copy(Paths.get(given_files.get(file).getPath()),
						Paths.get(conflict.getPath()),
						StandardCopyOption.REPLACE_EXISTING);
				conflicted = true;
			} else if (split.myFiles.get(file) != given_files.get(file)) {
				checkout(branches.get(branch_name).myHead.myId, file);
				add(file);
			}
		}
		if (conflicted == false) {
			commit(" Merged " + active.myName + " with " + branch_name + ".");
		} else {
			System.out.println("Encountered a merge conflict.");
		}
	}

	/**
	 * attaches the current branch to the head of the given branch
	 * <p>
	 * iterate through prev of Head until a node.myprev.split == true this
	 * node's myprev is now the node in branches that belongs to branchName then
	 * start at the head of the given branch and propagate its version of files
	 * at the head of the given branch, through the replayed branch until a node
	 * with its own version is encountered
	 * <p>
	 * 
	 * @param branchName
	 *            a String used to find the branch to rebase to
	 */
	public void rebase(String branch_name) {
		if (!branches.containsKey(branch_name)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (branch_name.equals(active.myName)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}
		CommitBranch given_branch = branches.get(branch_name);
		if (inHistory(given_branch.myHead)) {
			active.myHead = given_branch.myHead;
			return;
		}
		Commit given_head = given_branch.myHead;
		Commit split = findSplit(given_head, active.myHead);
		Commit newHead = active.replay(split);
		active.myHead = newHead;
		Commit end = newHead;
		while (end.myPrev != null) {
			end = end.myPrev;
		}
		end.myPrev = given_head;
		while (newHead != given_head) {
			Set<String> givenFiles = given_head.myFiles.keySet();
			for (String file : givenFiles) {
				if (split.myFiles.containsKey(file)) {
					if (!newHead.myFiles.containsKey(file)) {
						newHead.myFiles.put(file, given_head.myFiles.get(file));
					} else if (given_head.myFiles.get(file) != split.myFiles
							.get(file)
							&& newHead.myFiles.get(file) == split.myFiles
									.get(file)) {
						newHead.myFiles.put(file, given_head.myFiles.get(file));
					}
				} else if (!newHead.myFiles.containsKey(file)) {
					newHead.myFiles.put(file, given_head.myFiles.get(file));
				}
				newHead = newHead.myPrev;
			}
		}
	}

	/**
	 * find the first shared commit in the histories of c1 and c2
	 * 
	 * @param c1
	 *            commit that shares a splitpoint with c2
	 * @param c2
	 *            commit that shares a splitpoint with c2
	 */
	private Commit findSplit(Commit c1, Commit c2) {
		if (c1 == c2) {
			return c1;
		} else if (c1.myId > c2.myId) {
			return findSplit(c1.myPrev, c2);
		} else {
			return findSplit(c2.myPrev, c1);
		}
	}

	/**
	 * checks if the active branch head is in the history of the given commit
	 * 
	 * @param givenHead
	 *            a Commit the check the history of
	 */
	private boolean inHistory(Commit given_head) {
		while (given_head != null) {
			if (active.myHead == given_head) {
				return true;
			}
			given_head = given_head.myPrev;
		}
		return false;
	}

	/**
	 * decides what method to call when Gitlet is conflicted
	 * 
	 * @param args
	 *            a list of strings used to determine which method to call
	 * @throws IOException
	 */
	public void callConflicted(String[] args) throws IOException {
		if (args.length == 1) {
			callThis0(args[0]);
		} else if (args.length == 2) {
			switch (args[0]) {
			case "add":
				add(args[1]);
				break;
			case "commit":
				commit(args[1]);
				conflicted = false;
				break;
			case "rm":
				rm(args[1]);
				break;
			case "find":
				find(args[1]);
				break;
			case "checkout":
				checkout(args[1]);
				break;
			case "branch":
			case "rm-branch":
			case "reset":
			case "merge":
			case "rebase":
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			default:
				System.out.println("No command with that name exists.");
			}
		} else if (args.length == 3) {
			callThis2(args[0], args[1], args[2]);
		}
	}

	/**
	 * decides what 0 argument method to call
	 * 
	 * @param method
	 *            a String used to determine which method to call
	 */
	public void callThis0(String method) {
		switch (method) {
		case "log":
			log();
			break;
		case "global-log":
			global_log();
			break;
		case "status":
			status();
			break;
		default:
			System.out.println("No command with that name exists.");
		}
	}

	/**
	 * decides what 1 argument method to call
	 * 
	 * @param method
	 *            a String used to determine which method to call
	 * @param arg
	 *            a argument to pass into the method that is called
	 * @throws IOException
	 */
	public void callThis1(String method, String arg) throws IOException {
		switch (method) {
		case "add":
			add(arg);
			break;
		case "commit":
			commit(arg);
			break;
		case "rm":
			rm(arg);
			break;
		case "find":
			find(arg);
			break;
		case "checkout":
			checkout(arg);
			break;
		case "branch":
			branch(arg);
			break;
		case "rm-branch":
			rm_branch(arg);
			break;
		case "reset":
			reset(Integer.parseInt(arg));
			break;
		case "merge":
			merge(arg);
			break;
		case "rebase":
			rebase(arg);
			break;
		default:
			System.out.println("No command with that name exists.");
		}
	}

	/**
	 * decides what 2 argument method to call
	 * 
	 * @param method
	 *            a String used to determine which method to call
	 * @param arg
	 *            1 a argument to pass into the method that is called
	 * @param arg2
	 *            a argument to pass into the method that is called
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void callThis2(String method, String arg1, String arg2)
			throws NumberFormatException, IOException {
		if (method.equals("checkout")) {
			checkout(Integer.parseInt(arg1), arg2);
		} else {
			System.out.println("No command with that name exists.");
		}
	}

	private class CommitBranch implements Serializable {
		private static final long serialVersionUID = 1198456127087L;
		private Commit myHead;
		private String myName;
		private Commit mySplit;

		private CommitBranch(String name) {
			myHead = new Commit(0, "initial commit", null,
					new HashMap<String, File>());
			myName = name;
			mySplit = myHead;
		}

		private CommitBranch(Commit head, String name) {
			myHead = head;
			myName = name;
			mySplit = myHead;
		}

		/**
		 * creates a new commit and makes it the head of this branch
		 * 
		 * @param id
		 *            and integer denoting the id of the new commit
		 * @param msg
		 *            a string denoting the message of the new commit
		 * @param tracked
		 *            a hashmap of files that the new commit is tracking
		 */
		private void commit(int id, String msg, HashMap<String, File> tracked) {
			myHead = new Commit(id, msg, myHead, tracked);
		}

		/**
		 * creates a new branch with the same head as this branch
		 * 
		 * @param branchName
		 *            a String denoting the name of the new branch
		 * @return a CommitBranch with the same head as this branch
		 */
		private CommitBranch branch(String branchName) {
			return new CommitBranch(myHead, branchName);
		}

		/**
		 * switches the head of this branch to the given commit
		 * 
		 * @param resetCommit
		 *            a Commit that the myHead pointer will point to
		 */
		private void reset(Commit resetCommit) {
			myHead = resetCommit;
		}

		/**
		 * calls myHead's log method
		 */
		private void log() {
			myHead.log();
		}

		/**
		 * calls myHead's replay method
		 * 
		 * @param split
		 *            a Commit denoting the split point
		 * @return a replayed commit
		 */
		private Commit replay(Commit split) {
			return myHead.replay(split);
		}
	}

	private class Commit implements Serializable {
		private static final long serialVersionUID = 13639841L;
		private Commit myPrev; // pointer to previous commit
		private int myId; // this commit's id
		private String myMessage; // this commit's message
		private HashMap<String, File> myFiles; // files that this commit is
												// tracking
		private Commit split;
		private Date myDate;

		public Commit(int id, String msg, Commit prev,
				HashMap<String, File> tracked) {
			Date date = new Date();
			myPrev = prev;
			myId = id;
			myDate = date;
			myMessage = msg;
			split = null;
			myFiles = tracked;
		}

		/**
		 * calls printMe on this commit and calls log on its previous commit
		 */
		public void log() {
			printMe();
			if (myPrev == null) {
				return;
			}
			myPrev.log();
		}

		/**
		 * prints this commit's info
		 */
		public void printMe() {
			System.out.println("===");
			System.out.println("Commit " + myId);
			System.out.println(myFormat.format(myDate));
			System.out.println(myMessage);
			System.out.println();
		}

		/**
		 * copies this commit and recursively copies all commits up to split
		 * 
		 * @param split
		 *            a Commit used to determine when to stop copying
		 * @return a Commit that is a copy of this commit
		 */
		public Commit replay(Commit split) {
			if (myPrev == split) {
				Commit replayed = new Commit(nextId, myMessage, null, myFiles);
				if (commits.containsKey(myMessage)) {
					commits.get(myMessage).add(replayed);
				} else {
					ArrayList<Commit> added = new ArrayList<Commit>();
					added.add(replayed);
					commits.put(myMessage, added);
				}
				commitsbyId.add(replayed);
				nextId++;
				return replayed;
			}
			Commit prev = myPrev.replay(split);
			Commit replayed = new Commit(nextId, myMessage, prev, myFiles);
			if (commits.containsKey(myMessage)) {
				commits.get(myMessage).add(replayed);
			} else {
				ArrayList<Commit> added = new ArrayList<Commit>();
				added.add(replayed);
				commits.put(myMessage, added);
			}
			commitsbyId.add(replayed);
			nextId++;
			return replayed;
		}
	}

	/**
	 * deserializes gitlet's state calls methods then serializes gitlet's state
	 * 
	 * @param args
	 *            arguments the user passes in
	 */
	public static void main(String[] args) {
		File state = new File(".gitlet/git.ser");
		try {
			if (args[0].equals("init") && args.length == 1) {
				if (state.exists()) {
					System.out
							.println("A gitlet version control system already exists in the current directory.");
					return;
				} else {
					Gitlet init = new Gitlet();
					return;
				}
			}

			if (state.exists()) {
				FileInputStream in = new FileInputStream(state);
				ObjectInputStream stateIn = new ObjectInputStream(in);
				Gitlet init = (Gitlet) stateIn.readObject();
				in.close();
				stateIn.close();

				if (init.conflicted) {
					init.callConflicted(args);
				} else if (args.length == 0) {
					System.out.println("Please enter a command.");
				} else if (args.length == 1) {
					init.callThis0(args[0]);
				} else if (args.length == 2) {
					init.callThis1(args[0], args[1]);
				} else if (args.length == 3) {
					init.callThis2(args[0], args[1], args[2]);
				}

				FileOutputStream stateOut = new FileOutputStream(state);
				ObjectOutputStream out = new ObjectOutputStream(stateOut);
				out.writeObject(init);
				stateOut.close();
				out.close();
			} else {
				System.out.println("no gilet yet");
			}
		} catch (Exception e) {

		}
	}
}
