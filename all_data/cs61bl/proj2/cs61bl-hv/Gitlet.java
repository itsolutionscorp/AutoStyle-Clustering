/**
 * 
 * @author Jeffrey Mak Mason Scott David Tseng
 * @version 0.0
 * @since Jul 18, 2015
 * 
 * .gitlet FOLDER
 * staging folder 
 *        contains a copy of the files that are about to be committed.
 * gitlet.ser 
 *        saves the state of the entire program (including the commit object tree)
 * folder for each commit 
 *        name of the folder is the commitID + folder. Contains a snapshot/copy of each file at the time of the commit.
 */
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.sql.*;

public class Gitlet implements Serializable {

	protected HashMap<Integer, Commit> commitByID;
	protected HashMap<String, ArrayList<Commit>> commitByMessage;
	protected Commit head;
	protected Commit initial;
	protected HashMap<String, File> untracked;
	protected int lastCommitID = -1;
	protected HashMap<String, Commit> branches;
	protected String currentBranch;
	protected HashSet<String> stagedFileNames;
	protected boolean conflictedState;
	static final long serialVersionUID = -1621589084904952045L;

	/**
	 * The constructor for the Gitlet class. Initializes most of the above
	 * fields.
	 */
	public Gitlet() {
		conflictedState = false;
		commitByID = new HashMap<Integer, Commit>();
		commitByMessage = new HashMap<String, ArrayList<Commit>>();
		untracked = new HashMap<String, File>(30);
		branches = new HashMap<String, Commit>();
		head = null;
		stagedFileNames = new HashSet<String>();
	}

	/**
	 * Creates the .gitlet folder, initializes an initial commit object with the
	 * message "initial commit." Also appropriately sets the current branch and
	 * head commit.
	 */
	public void init() {
		try {
			Files.createDirectory(Paths.get(".gitlet"));
		} catch (IOException e) {
			System.out
					.println("A gitlet version control system already exists in the current directory.");
			return;
		}
		try {
			Files.createDirectory(Paths.get(".gitlet", "staging"));
		} catch (IOException e) {
			return;
		}
		currentBranch = "master";
		makeCommit("initial commit", true);
		initial = head;
		branches.put("master", head);
	}

	/**
	 * Adds a commit to both HashMaps involving commits: commitByMessage,
	 * commitByID.
	 * 
	 * @param commit
	 *            The commit to be added.
	 */
	public void addToHashMaps(Commit commit) {
		commitByID.put(commit.commitID, commit);
		if (commitByMessage.containsKey(commit.commitMessage)) {
			commitByMessage.get(commit.commitMessage).add(commit);
		} else {
			ArrayList<Commit> newCommitList = new ArrayList<Commit>();
			newCommitList.add(commit);
			commitByMessage.put(commit.commitMessage, newCommitList);
		}
	}

	/**
	 * Serializes Gitlet and its variables. We got some of this code from
	 * http://www.tutorialspoint.com/java/java_serialization.htm when we were
	 * researching about serializing!
	 */
	public void serialize() {
		try {
			FileOutputStream fileStream = new FileOutputStream(Paths
					.get(".gitlet", "gitlet.ser").toAbsolutePath().toString());
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
			objectStream.writeObject(this);
			objectStream.close();
			fileStream.close();
		} catch (IOException e) {
			return;
		}
	}

	/**
	 * Reads the information from the .ser file and deserializes it. We got some
	 * of this code from
	 * http://www.tutorialspoint.com/java/java_serialization.htm when we were
	 * researching about serializing objects!
	 * 
	 * @param path
	 *            The location of the .ser file.
	 * 
	 * @return An object containing the past information and variables of the
	 *         last Gitlet object.
	 */
	public static Gitlet deSerialize(Path path) {
		try {
			FileInputStream fileStream = new FileInputStream(path
					.toAbsolutePath().toString());
			ObjectInputStream objectStream = new ObjectInputStream(fileStream);
			Gitlet git = (Gitlet) objectStream.readObject();
			objectStream.close();
			fileStream.close();
			return git;
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	/**
	 * Adds this file to stage if the file is a directory, then add all files
	 * within the directory into staging folder. Also saves these files to the
	 * stage folder in .gitlet
	 * 
	 * @param file
	 *            The file that we are trying to add to the stage.
	 */
	public void add(String file) {
		Path to_copy = Paths.get(file);
		if (!Files.exists(to_copy)) {
			System.out.println("File does not exist.");
			return;
		}
		if (Files.isDirectory(to_copy)) {
			return;
		} else if (untracked.containsKey(Paths.get(file).toString())) {
			untracked.remove(Paths.get(file).toString());
		} else {
			Path destination = Paths.get(".gitlet", "staging").resolve(
					Paths.get(file));
			if (copyHelper(to_copy, destination))
				stagedFileNames.add(Paths.get(file).toString());
		}
	}

	/**
	 * Commits the stage variable by pointing its prev variable to the current
	 * head, and then points head to the new commit object. Clears the staging
	 * area afterwards. Also saves the date and time, and gives the new commit
	 * object an ID (based on the date and time of committing). Adds the commit
	 * to the commitByID HashMap
	 * 
	 * @param message
	 *            The commit message to be used
	 * @param initial
	 *            True only if we are creating the initial commit. In that case
	 *            no error messages should arise.
	 */
	public void makeCommit(String message, boolean initial) {
		if (stagedFileNames.size() == 0 && !initial && untracked.size() == 0) {
			System.out.println("No changes added to the commit.");
			return;
		}
		Commit newCommit = new Commit(message, this, head);
		if (head != null)
			head.children.add(newCommit);
		head = newCommit;
		File commitFolder = new File(Paths.get(".gitlet",
				newCommit.commitID.toString()).toString());
		File stageFolder = new File(Paths.get(".gitlet", "staging").toString());
		if (commitFolder.mkdir()) {
			for (String x : stagedFileNames) {
				Path from = stageFolder.toPath().resolve(Paths.get(x));
				Path to = commitFolder.toPath().resolve(Paths.get(x));
				if (copyHelper(from, to))
					head.committedFilePath.put(Paths.get(x).toString(),
							to.toString());
				completeDelete(new File(from.toString()));
			}
			stagedFileNames = new HashSet<String>();
			File[] files = new File(Paths.get(".gitlet", "staging").toString())
					.listFiles();
			for (File file : files)
				completeDelete(file);
			addToHashMaps(newCommit);
			branches.put(currentBranch, newCommit);
			untracked = new HashMap<String, File>();
			conflictedState = false;
		} else {
			return;
		}
	}

	/**
	 * Recursively deletes a file. If the file is a folder, it completely
	 * deletes everything inside it first.
	 * 
	 * @param toDelete
	 *            The file to be deleted.
	 */
	public static void completeDelete(File toDelete) {
		if (toDelete == null)
			return;
		if (toDelete.isDirectory()) {
			File[] fileList = toDelete.listFiles();
			for (File f : fileList) {
				completeDelete(f);
			}
		}
		try {
			Files.delete(toDelete.toPath());
		} catch (IOException e) {
			return;
		}
	}

	/**
	 * Marks the file for untracking or removes this file from stage.
	 * 
	 * @param remove
	 *            The file to be removed
	 */
	public void remove(String remove) {
		Path stagedPath = Paths.get(".gitlet", "staging", remove);
		if (Files.exists(stagedPath)) {
			try {
				Files.delete(stagedPath);
				stagedFileNames.remove(Paths.get(remove).toString());
			} catch (IOException e) {
				return;
			}
		} else if (!untracked.containsKey(Paths.get(remove).toString())) {
			untracked.put(Paths.get(remove).toString(), new File(remove));
		} else {
			System.out.println("No reason to remove the file.");
		}
	}

	/**
	 * Calls the logPrint() method of the HEAD pointer. Implements the Gitlet
	 * log functionality by printing out all of the commits in that branch.
	 */
	public void log() {
		head.logPrint();
	}

	/**
	 * Calls the globalLogPrint() method of the initial commit. Implements the
	 * Gitlet global-log functionality by printing out the information of all of
	 * the commits created.
	 */
	public void globalLog() {
		initial.globalLogPrint();
	}

	/**
	 * Checks out the given file at the specified commit. Reflects this change
	 * in the working directory (restores the file back to its condition when
	 * this commit was created.
	 * 
	 * @param commitID
	 *            The ID of the commit that the user wants to checkout to.
	 * @param file
	 *            The file that the user wants to reset back to.
	 */
	public void checkoutCommitID(String commitID, String file) {
		Path to = Paths.get(file);
		String FileName = to.toString();
		if (!commitByID.containsKey(Integer.parseInt(commitID))) {
			System.out.println("No commit with that id exists.");
			return;
		}
		Commit destCommit = commitByID.get(Integer.parseInt(commitID));
		if (destCommit.committedFilePath.containsKey(Paths.get(FileName)
				.toString())) {
			Path from = Paths.get(destCommit.committedFilePath.get(FileName));
			copyHelper(from, to);
		} else {
			System.out.println("File does not exist in that commit.");
			return;
		}
	}

	/**
	 * Checks out a certain branch. It takes the files as they were when the
	 * commit at the head of the branch was created and puts them in the working
	 * directory. Also sets the active head pointer to that branch's pointer.
	 * 
	 * @param name
	 *            The name of the branch to be checked out
	 */
	public void checkoutBranch(String name) {
		if (conflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		if (name.equals(currentBranch)) {
			System.out.println("No need to checkout the current branch.");
			return;
		}
		head = branches.get(name);
		currentBranch = name;
		copyToWorkingDirectory();
	}

	/**
	 * Copies files from the head commit to the working directory.
	 */
	public void copyToWorkingDirectory() {
		for (String key : head.committedFilePath.keySet()) {
			Path from = Paths.get(head.committedFilePath.get(key));
			Path to = Paths.get(key);
			copyHelper(from, to);
		}
	}

	/**
	 * This method copies files from one place to another while creating any
	 * necessary intermediate folders. For example, copying from Folder A/Folder
	 * B/Testing.txt to Testing1/Testing2/Folder A/Folder B/Testing.txt would
	 * create Folder A and Folder B in the Testing1/Testing2 directory if these
	 * folders do not already exist. Also, if there is already a file with the
	 * same name in the destination area, then that file would be replaced.
	 * 
	 * @param from
	 *            The original source of the file.
	 * @param to
	 *            The final destination of the file. This includes the file
	 *            name.
	 * @return True if the copying was successful, false if the copying was not
	 *         successful.
	 */
	public static boolean copyHelper(Path from, Path to) {
		try {
			if (to.getParent() != null)
				Files.createDirectories(to.getParent());
			Files.copy(from, to, REPLACE_EXISTING);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Creates a branch and adds the branch to the HashMap branches.
	 * 
	 * @param name
	 *            The name of the branch to be added.
	 */
	public void branch(String name) {
		if (conflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		if (branches.containsKey(name)) {
			System.out.println("A branch with that name already exists.");
		} else {
			branches.put(name, head);
		}
	}

	/**
	 * Removes branch pointer and deletes from branchByName HashMap.
	 * 
	 * @param branch
	 *            The name of the branch to be removed.
	 */
	public void removeBranch(String branch) {
		if (conflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		if (branches.containsKey(branch)) {
			if (currentBranch.equals(branch))
				System.out.println("Cannot remove the current branch.");
			else
				branches.remove(branch);
		} else
			System.out.println("A branch with that name does not exist.");
	}

	/**
	 * Does reset procedure. Resets the working directory back to what it was
	 * when this commit was created.
	 * 
	 * @param id
	 *            ID of the commit to be reset to.
	 */
	public void reset(String id) {
		if (conflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		if (!commitByID.containsKey(Integer.parseInt(id))) {
			System.out.println("No commit with that id exists.");
			return;
		}
		head = commitByID.get(Integer.parseInt(id));
		branches.put(currentBranch, head);
		copyToWorkingDirectory();
	}

	/**
	 * Prints out the status of the items. Prints out the branches, staged
	 * files, and the files marked for untracking.
	 */
	public void status() {
		System.out.println("=== Branches ===");
		for (String branch : branches.keySet()) {
			if (branch.equals(currentBranch))
				System.out.print("*");
			System.out.println(branch);
		}
		System.out.println("\n=== Staged Files ===");
		for (String staged : stagedFileNames)
			System.out.println(staged);
		System.out.println("\n=== Files Marked for Untracking ===");
		for (String file : untracked.keySet())
			System.out.println(file);
	}

	/**
	 * Finds a commit based on its message. Prints out a list of all commits
	 * with the same message.
	 * 
	 * @param message
	 *            The message that will be used to look for commits.
	 */
	public void find(String message) {
		ArrayList<Commit> commits = commitByMessage.get(message);
		if (commits != null) {
			for (Commit c : commits) {
				System.out.println(c.commitID);
			}
		} else {
			System.out.println("Found no commit with that message.");
		}
	}

	/**
	 * Implements the rebase functionality. It finds the split point between the
	 * two branches, then "attaches" (actually makes a copy) of one branch to
	 * the other branch, and propagates files changes through the attached
	 * branch.
	 * 
	 * @param given
	 *            The name of the given branch that we are adding our current
	 *            branch to.
	 */
	public void rebase(String given) {
		if (conflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		HashMap<Integer, Commit> givenParents = new HashMap<Integer, Commit>(5);
		Commit givenBranch = branches.get(given);
		Commit currBranch = branches.get(currentBranch);
		if (givenBranch == null) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (given.equals(currentBranch)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}
		for (Commit pointer = givenBranch.parent; pointer != null; pointer = pointer.parent) {
			if (pointer == currBranch) {
				branches.put(currentBranch, givenBranch);
				return;
			}
			givenParents.put(pointer.commitID, pointer);
		}
		Commit copyHead = null;
		Commit copyEnd = null;
		Commit ancestor = null;
		for (Commit pointer = currBranch; pointer.parent != null; pointer = pointer.parent) {
			if (pointer.parent.commitID == givenBranch.commitID) {
				System.out.println("Already up-to-date.");
				return;
			}
			Commit copy = new Commit(pointer, this, copyHead);
			addToHashMaps(copy);
			copyHead = copy;
			if (pointer == currBranch) {
				copyEnd = copy;
			}
			if (givenParents.containsKey(pointer.parent.commitID)) {
				ancestor = pointer.parent;
				break;
			}
		}
		copyHead.parent = givenBranch;
		givenBranch.children.add(copyHead);
		for (String f : givenBranch.committedFilePath.keySet()) {
			propagate(copyHead, copyEnd, f, givenBranch, ancestor);
		}
		branches.put(currentBranch, copyEnd);
		head = copyEnd;
		reset(copyEnd.commitID.toString());
	}

	/**
	 * Propagates changes from the given branch's file through the copied branch
	 * in the rebase function. It stops propagating once it reaches a commit
	 * that made modifications to that file.
	 * 
	 * @param begin
	 *            The commit to start propagating changes through.
	 * @param end
	 *            The last commit to propagate changes through.
	 * @param file
	 *            The file that we are propagating changes of.
	 * @param givenBranch
	 *            The branch that contains the file above. This is the branch
	 *            that we are propagating files from.
	 * @param ancestor
	 *            The split point between these two commits.
	 */
	public void propagate(Commit begin, Commit end, String file,
			Commit givenBranch, Commit ancestor) {
		for (Commit pointer = begin; pointer != null; pointer = pointer.children
				.get(0)) {
			if (pointer.committedFilePath.containsKey(file)) {
				Path toMergeFile = Paths.get(pointer.committedFilePath
						.get(file));
				if (ancestor.committedFilePath.get(file) == null)
					break;
				Path ancestorFile = Paths.get(ancestor.committedFilePath
						.get(file));
				if (!compareFiles(toMergeFile, ancestorFile))
					break;
				else {
					pointer.committedFilePath.put(file,
							givenBranch.committedFilePath.get(file));
				}
			} else {
				pointer.committedFilePath.put(file,
						givenBranch.committedFilePath.get(file));
			}
			if (pointer == end || pointer.children.size() == 0) {
				break;
			}
		}
	}

	/**
	 * Compares two files by comparing their bytes. Returns true if they contain
	 * the same contents, returns false if they contain different things.
	 * 
	 * @param a
	 *            First file to be compared.
	 * @param b
	 *            Second file to be compared.
	 * @return True if these files have the same contents, false if they don't.
	 */
	public static boolean compareFiles(Path a, Path b) {
		try {
			byte[] f1 = Files.readAllBytes(a);
			byte[] f2 = Files.readAllBytes(b);
			return Arrays.equals(f1, f2);
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Implements the merge functionality. Merges two branches -- if they both
	 * modified files since the split point, creates a .conflicted file.
	 * Otherwise if the given branch had modified a file but the current branch
	 * had not modified the file, then the given branch's file will replace that
	 * file. If there is a conflictthen the whole program is placed into a
	 * conflicted state. Otherwise automatically creates a commit to reflect the
	 * merge. Also changes the working directory to reflect theese merge
	 * changes.
	 * 
	 * @param given
	 *            The branch to merge from.
	 */
	public void merge(String given) {
		if (conflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		Commit givenBranch = branches.get(given);
		Commit currBranch = branches.get(currentBranch);
		if (givenBranch == null) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (given.equals(currentBranch)) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}
		Commit ancestor = findAncestor(currBranch, givenBranch);
		for (String f : givenBranch.committedFilePath.keySet()) {
			Path givenBranchFile = Paths.get(givenBranch.committedFilePath
					.get(f));
			if (!currBranch.committedFilePath.containsKey(f)) {
				copyHelper(givenBranchFile, Paths.get(f));
				add(f);
			} else {
				Path currBranchFile = Paths.get(currBranch.committedFilePath
						.get(f));
				Path ancestorFile = null;
				boolean givenBeenModified = false;
				boolean currBeenModified = false;
				if (ancestor.committedFilePath.get(f) != null) {
					ancestorFile = Paths.get(ancestor.committedFilePath.get(f));
					givenBeenModified = !compareFiles(givenBranchFile,
							ancestorFile);
					currBeenModified = !compareFiles(currBranchFile,
							ancestorFile);
				} else {
					givenBeenModified = true;
					currBeenModified = true;
				}
				if (givenBeenModified && currBeenModified) {
					Path workingFile = Paths.get(f);
					Path conflictFile;
					if (workingFile.getParent() != null)
						conflictFile = workingFile.getParent().resolve(
								Paths.get(workingFile.getFileName()
										+ ".conflicted"));
					else
						conflictFile = Paths.get(workingFile.getFileName()
								+ ".conflicted");
					copyHelper(givenBranchFile, conflictFile);
					System.out.println("Encountered a merge conflict.");
					conflictedState = true;
				} else if (givenBeenModified && !currBeenModified) {
					copyHelper(givenBranchFile, Paths.get(f));
					add(f);
				}
			}
		}
		checkUntracked(givenBranch, currBranch, ancestor, given);
	}

	/**
	 * Goes through the untracked files of the given branch and checks the files
	 * tracked by the current branch. If the current branch did not modify that
	 * file but it is untracked by the given branch, then the remove operation
	 * is performed on the file in the working directory. If the program is not
	 * in a conflicted state, it will automatically create a commit.
	 * 
	 * @param givenBranch
	 *            The branch that we are checking for untracked files. This is
	 *            also the branch that we are trying to merge from.
	 * @param currBranch
	 *            The current branch that we are on
	 * @param ancestor
	 *            The split point of the two branches.
	 * @param given
	 *            The name of the given branch.
	 */
	public void checkUntracked(Commit givenBranch, Commit currBranch,
			Commit ancestor, String given) {
		for (String file : givenBranch.untracked.keySet()) {
			if (currBranch.committedFilePath.containsKey(file)) {
				Path currBranchFile = Paths.get(currBranch.committedFilePath
						.get(file));
				Path ancestorFile = null;
				boolean currBeenModified = false;
				if (ancestor.committedFilePath.containsKey(file)) {
					ancestorFile = Paths.get(ancestor.committedFilePath
							.get(file));
					currBeenModified = !compareFiles(currBranchFile,
							ancestorFile);
				}
				if (!currBeenModified) {
					remove(file);
				}
			}
		}
		if (!conflictedState) {
			makeCommit("Merged " + currentBranch + " with " + given + ".",
					false);
		}
	}

	/**
	 * Finds the split point between two commits.
	 * 
	 * @param a
	 *            The first commit.
	 * @param b
	 *            The second commit.
	 * @return The split point (a commit object) between commits A and B.
	 */
	public Commit findAncestor(Commit a, Commit b) {
		HashMap<Integer, Commit> givenParents = new HashMap<Integer, Commit>(5);
		for (Commit pointer = b; pointer != null; pointer = pointer.parent) {
			givenParents.put(pointer.commitID, pointer);
		}
		Commit ancestor = null;
		for (Commit pointer = a; pointer != null; pointer = pointer.parent) {
			if (givenParents.containsKey(pointer.commitID)) {
				ancestor = pointer;
				break;
			}
		}
		return ancestor;
	}

	/**
	 * The main method where the Gitlet class will be run
	 * 
	 * @param args
	 *            An array of arguments from Terminal
	 */
	public static void main(String[] args) {
		Gitlet git;
		if (args.length == 0) {
			System.out.println("Please enter a command.");
			return;
		}
		Path serPath = Paths.get(".gitlet", "gitlet.ser");
		if (Files.exists(serPath))
			git = deSerialize(serPath);
		else
			git = new Gitlet();
		String request = args[0];
		if (request.equals("init")) {
			git.init();
		} else if (request.equals("add")) {
			git.add(args[1]);
		} else if (request.equals("rm")) {
			git.remove(args[1]);
		} else if (request.equals("commit")) {
			if (args.length < 2)
				System.out.println("Please enter a command.");
			else
				git.makeCommit(args[1], false);
		} else if (request.equals("log")) {
			git.log();
		} else if (request.equals("global-log")) {
			git.globalLog();
		} else if (request.equals("branch")) {
			git.branch(args[1]);
		} else if (request.equals("checkout")) {
			if (git.branches.containsKey(args[1]) || git.conflictedState)
				git.checkoutBranch(args[1]);
			else if (args.length < 3) {
				if (git.head.committedFilePath.containsKey(Paths.get(args[1])
						.toString()))
					git.checkoutCommitID(git.head.commitID.toString(), args[1]);
				else
					System.out
							.println("File does not exist in the most recent commit, or no such branch exists.");
			} else {
				git.checkoutCommitID(args[1], args[2]);
			}
		} else if (request.equals("reset")) {
			git.reset(args[1]);
		} else if (request.equals("status")) {
			git.status();
		} else if (request.equals("rm-branch")) {
			git.removeBranch(args[1]);
		} else if (request.equals("find")) {
			git.find(args[1]);
		} else if (request.equals("rebase")) {
			git.rebase(args[1]);
		} else if (request.equals("merge")) {
			git.merge(args[1]);
		} else {
			System.out.println("No command with that name exists.");
		}
		git.serialize();
	}
}