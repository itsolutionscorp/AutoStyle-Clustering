import java.text.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class Gitlet implements Serializable {

	/**
	 * CS61BL Summer 2015 Project 2: Gitlet
	 *
	 * @authors: Stefan Lam, Soohee Lee, Nancy Li
	 * 
	 *           Some code adapted from the internet:
	 * 
	 *           http://www.mkyong.com/java/how-to-delete-directory-in-java/
	 *           http://www.tutorialspoint.com/java/java_serialization.htm
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, Commit> commitBranches;
	private HashMap<Integer, Commit> myCommits;
	private HashSet<File> stagedfiles;
	private HashSet<File> markedfiles;
	private Commit currentBranch;
	private File gitdir;
	private File stage;
	private String currentBranchName;
	private int commitCount = 0;
	private boolean conflictedState;
	private boolean samebranch;
	private static final String WORKING_DIR = System.getProperty("user.dir");
	private static final String GITLET_DIR = ".gitlet";
	private static final String STAGE_DIR = ".gitlet/staging-area";

	/**
	 * Constructs a Gitlet object only if a .gitlet directory does not already
	 * exist.
	 *
	 * Creates an initial commit with message "initial commit" and ID of 0. This
	 * commit is the default "master" branch.
	 */
	public Gitlet() {
		gitdir = new File(GITLET_DIR);
		if (gitdir.exists()) {
			System.out.println("A gitlet version control system already " + "exists in the current directory.");
			System.exit(0);
		}
		gitdir.mkdir();
		stage = new File(STAGE_DIR);
		stage.mkdir();
		markedfiles = new HashSet<File>();
		stagedfiles = new HashSet<File>();
		myCommits = new HashMap<Integer, Commit>();
		commitBranches = new HashMap<String, Commit>();
		currentBranchName = "master";
		commitinitial("initial commit");
		samebranch = false;
	}

	/**
	 * Copies files from the working director to the staging-area, unless it is
	 * marked for untracking by the user.
	 * 
	 * @param filename
	 *            a string referring to the name of the file to be added,
	 *            specified by the user
	 */
	public void add(String filename) {
		File toAdd = new File(filename);
		if (!toAdd.exists()) {
			System.out.println("File does not exist.");
			System.exit(0);
		}
		if (markedfiles.contains(toAdd)) {
			markedfiles.remove(toAdd);
			System.exit(0);
		} else {
			try {
				if (toAdd.getParentFile() != null) {
					Path parentfiles = Paths.get(STAGE_DIR, toAdd.getParentFile().toString());
					parentfiles.toFile().mkdirs();
				}
				Path copy = Paths.get(STAGE_DIR, filename);
				Files.copy(toAdd.toPath(), copy);
				stagedfiles.add(toAdd);

			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}

	/**
	 * Creates a new commit folder inside .gitlet that will store the files
	 * currently in the staging-area. Also updates the myCommits hashmap with
	 * the new Commit object, empties the staging-area, and redirects the
	 * current branch pointer and current branch name to the newly made commit.
	 * 
	 * Commits will inherit files from parent commits if that file has not been
	 * added to the staging area unless the file is "marked" for untracking by
	 * the user.
	 * 
	 * Resets Gitlet to normal state if the user commits while in a conflicted
	 * state.
	 * 
	 * @param message
	 *            a string message for each commit specified by the user
	 *
	 * @throws IOException
	 */
	public void commit(String message) {
		Commit newcommit = new Commit(commitCount, message, currentBranch);
		Path commitFolder = Paths.get(GITLET_DIR, "commit" + commitCount);
		commitFolder.toFile().mkdir();
		if (markedfiles.isEmpty() && stagedfiles.isEmpty()) {
			System.out.println("No changes added to the commit.");
			System.exit(0);
		}
		if (message.equals("")) {
			System.out.println("Please enter a commit message.");
			System.exit(0);
		}
		for (File file : newcommit.myPrev.myFiles.keySet()) {
			if (markedfiles.contains(file)) {
				newcommit.myUntracked.add(file);
			}
			else if (!stagedfiles.contains(file)) {
				newcommit.myFiles.put(file, newcommit.myPrev.myFiles.get(file));
			}
		}
		for (File file : stagedfiles) {
			newcommit.myFiles.put(file, newcommit.commitID);
			try {
				Path from = Paths.get(STAGE_DIR, file.toString());
				if (file.getParentFile() != null) {
					Path parentfiles = Paths.get(commitFolder.toString(), file.getParentFile().toString());
					parentfiles.toFile().mkdirs();
				}
				Files.move(from, commitFolder.resolve(file.toPath().toString()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String files[] = stage.list();
		for (String temp : files) {
			File fileDelete = new File(STAGE_DIR, temp);
			if (fileDelete.isDirectory()) {
				delete(fileDelete);
			}
		}
		currentBranch = newcommit;
		stagedfiles = new HashSet<File>();
		markedfiles = new HashSet<File>();
		myCommits.put(newcommit.commitID, newcommit);
		commitBranches.replace(currentBranchName, newcommit);
		commitCount++;
		conflictedState = false;
	}

	/**
	 * Special commit method for the very first commit, created by calling
	 * Gitlet init. This method will create a commit object with message
	 * "initial commit" and id of 0.
	 * 
	 * This commit will not be stored physically in a folder and cannot be
	 * retrieved.
	 * 
	 * @param message
	 *            a string message for the first commit
	 */
	public void commitinitial(String message) {
		Commit newcommit = new Commit(commitCount, message, null);
		myCommits.put(newcommit.commitID, newcommit);
		commitBranches.put(currentBranchName, newcommit);
		commitCount++;
		currentBranch = newcommit;
	}

	/**
	 * Code adapted from StackOverFlow.
	 *
	 * Deletes the file and all files inside it, if it is a directory.
	 * 
	 * @param file
	 *            the File to be deleted
	 */
	public static void delete(File file) {
		if (file.list().length == 0) {
			file.delete();
		} else {
			String files[] = file.list();
			for (String temp : files) {
				File toDelete = new File(file, temp);
				delete(toDelete);
			}
			if (file.list().length == 0) {
				file.delete();
			}
		}
	}

	/**
	 * Marks files that the user no longer wishes to track for the next commit.
	 * 
	 * @param file
	 *            the File object to be untracked
	 *
	 * @throws IOException
	 */
	public void remove(File file) {
		if (stagedfiles.contains(file)) {
			stagedfiles.remove(file);
			Path path = Paths.get(STAGE_DIR, file.toString());
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (currentBranch.myFiles.containsKey(file)) {
			markedfiles.add(file);
		} else {
			System.out.println("No reason to remove the file.");
		}
	}

	/**
	 * Displays ID, time stamp, and message of the current commit and all of its
	 * parents.
	 */
	public void log() {
		currentBranch.log();
	}

	/**
	 * Displays ID, time stamp, and message of all commits made after
	 * initializing Gitlet.
	 */
	public void globalLog() {
		for (Integer id : myCommits.keySet()) {
			System.out.println("===");
			System.out.println("Commit " + id);
			System.out.println(myCommits.get(id).myDate);
			System.out.println(myCommits.get(id).myMessage);
			System.out.println();
		}
	}

	/**
	 * Displays the IDs of commits with the given message. Will display multiple
	 * commits if they share the same message.
	 *
	 * @param message
	 *            a string message, specified by user
	 */
	public void find(String message) {
		String s = "";
		for (Integer id : myCommits.keySet()) {
			if (message.equals(myCommits.get(id).myMessage)) {
				s = message;
				System.out.println(id);
			}
		}
		if (!message.equals(s)) {
			System.out.println("Found no commit with that message.");
		}
	}

	/**
	 * Displays what branches currently exist, and marks the current branch with
	 * ' * ' . Also displays what files have been staged or marked for
	 * untracking.
	 *
	 */
	public void status() {
		System.out.println("=== Branches ===");
		for (String branchName : commitBranches.keySet()) {
			if (branchName.equals(currentBranchName)) {
				System.out.println("*" + branchName);
				continue;
			}
			System.out.println(branchName);
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		for (File file : stagedfiles) {
			System.out.println(file.toString());
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (File file : markedfiles) {
			System.out.println(file.toString());
		}
		System.out.println();
	}

	/**
	 * Creates a new branch with the given name, and points it at the current
	 * head commit. Does NOT switch to the newly created branch.
	 *
	 * @param branchName
	 *            string name of new branch, specified by user
	 */
	public void branch(String branchName) {
		if (conflictedState) {
			conflictedState();
		}
		if (commitBranches.containsKey(branchName)) {
			System.out.println("A branch with that name already exists.");
			System.exit(0);
		}
		Commit newbranch = currentBranch;
		commitBranches.put(branchName, newbranch);
	}

	/**
	 * Deletes the pointer associated with given branch. Does NOT delete the
	 * branch.
	 *
	 * @param branchName
	 *            string name of the branch to be deleted, specified by the user
	 */
	public void removebranch(String branchName) {
		if (conflictedState) {
			conflictedState();
		}
		if (!commitBranches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			System.exit(0);
		}
		if (branchName.equals(currentBranchName)) {
			System.out.println("Cannot remove the current branch.");
			System.exit(0);
		}
		commitBranches.remove(branchName);
	}

	/**
	 * Calls two helper methods; if s is the name of a branch, checkoutBranch
	 * will be called. Else if s is the name of a file, checkoutFile is called.
	 * Branch names will take precedence over file names if the two are the
	 * same.
	 *
	 * @param s
	 *            string name of the file or branch to be checked out
	 */
	public void checkout(String s) {
		if (commitBranches.containsKey(s)) {
			checkoutBranch(s);
		} else {
			checkoutFile(s);
		}
	}

	/**
	 * Takes files in the most recent commit, the front of the current branch,
	 * and copies them into the working directory, overwriting files that are
	 * already there.
	 * 
	 * @param toCheckout
	 *            a string name of the file to check out
	 * 
	 * @throws IOException
	 */
	private void checkoutFile(String toCheckout) {
		File f = new File(toCheckout);
		if (!currentBranch.myFiles.containsKey(f)) {
			System.out.println("File does not exist in the most recent commit, or no such branch exists.");
			System.exit(0);
		}
		Path from = getPath(f, currentBranch.myFiles.get(f));
		Path copypath = Paths.get(WORKING_DIR, toCheckout);
		copy(from, copypath, f);
	}

	/**
	 * Takes all files from the commit at the head of the specified branch, and
	 * copies them into the working directory, overwriting any files already
	 * there.
	 * 
	 * Updates the current branch to the given branch.
	 *
	 * @param branchName
	 *            string name of the branch specified by user
	 * 
	 * @throws IOException
	 */
	private void checkoutBranch(String branchName) {
		if (conflictedState) {
			conflictedState();
		}
		if (!commitBranches.containsKey(branchName)) {
			System.out.println("File does not exist in the most recent commit, or no such branch exists.");
			System.exit(0);
		}
		if (currentBranchName.equals(branchName)) {
			System.out.println("No need to checkout the current branch.");
			System.exit(0);
		}
		Commit temp = commitBranches.get(branchName);
		for (File file : temp.myFiles.keySet()) {
			Path from = getPath(file, temp.myFiles.get(file));
			Path copypath = Paths.get(WORKING_DIR, file.toString());
			copy(from, copypath, file);
		}
		currentBranch = temp;
		currentBranchName = branchName;
	}

	/**
	 * Takes a specified file in the commit with the given id, and copies it to
	 * the working directory, overwriting the file that may already exist.
	 *
	 * @param filename
	 *            string name of the file to be checked out
	 * 
	 * @param id
	 *            integer id referring to the commit that contains this file
	 */
	private void checkoutHelper(Integer id, String filename) {
		if (id > (commitCount - 1) || id < 1) {
			System.out.println("No commit with that id exists.");
			System.exit(0);
		}
		File f = new File(filename);
		Commit temp = myCommits.get(id);
		if (!temp.myFiles.containsKey(f)) {
			System.out.println("File does not exist in that commit.");
			System.exit(0);
		}
		Path from = getPath(f, myCommits.get(id).myFiles.get(f));
		for (File file : temp.myFiles.keySet()) {
			if (filename.equals(file.toString())) {
				Path copypath = Paths.get(WORKING_DIR, filename);
				copy(from, copypath, file);
			}
		}
	}

	/**
	 * Helper method for copying files into another directory.
	 *
	 * @param from
	 *            path of the original file
	 * @param to
	 *            path of file to copy to
	 * @param f
	 *            file to copy
	 */
	public static void copy(Path from, Path to, File f) {
		try {
			if (f.getParentFile() != null) {
				Path parentfiles = Paths.get(to.toString(), f.getParentFile().toString());
				parentfiles.toFile().mkdirs();
			}
			File copy = new File(to.toString());
			Files.copy(from, copy.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks out all the files tracked by the commit with the given id by
	 * calling checkoutHelper. Also moves the current branch's head pointer to
	 * that commit.
	 *
	 * @param id
	 *            integer id of a commit, specified by the user
	 */
	public void reset(String id) {
		if (conflictedState) {
			conflictedState();
		}
		Integer num = Integer.parseInt(id);
		if (num > (commitCount - 1) || num < 1) {
			System.out.println("No commit with that id exists.");
			System.exit(0);
		}
		for (File file : myCommits.get(num).myFiles.keySet()) {
			checkoutHelper(num, file.toString());
		}
		currentBranch = myCommits.get(num);
		commitBranches.replace(currentBranchName, myCommits.get(num));
	}

	/**
	 * A helper method used by merge and rebase to find the commit at the split
	 * point; the common ancestor between the commit at the head of the current
	 * branch and the commit at the head of given branch.
	 * 
	 * @param branchName
	 *            string name of the given branch
	 * 
	 * @return splitPoint
	 */
	public Commit findSplit(String branchName) {
		Commit current = currentBranch;
		Commit branch = commitBranches.get(branchName);
		Commit splitPoint;
		int currentLength = 0;
		int branchLength = 0;
		while (current != null) {
			currentLength++;
			current = current.myPrev;
		}
		while (branch != null) {
			branchLength++;
			branch = branch.myPrev;
		}
		current = currentBranch;
		branch = commitBranches.get(branchName);
		while (current != branch) {
			if (currentLength > branchLength) {
				current = current.myPrev;
				currentLength--;
			}
			if (branchLength > currentLength) {
				branch = branch.myPrev;
				branchLength--;
			} else {
				samebranch = true;
				if (current != branch) {
					current = current.myPrev;
					branch = branch.myPrev;
					currentLength--;
					branchLength--;
					samebranch = false;
				}
			}
		}
		splitPoint = current;
		return splitPoint;
	}

	/**
	 * Finds the split point of the current branch and the given branch, snaps
	 * off the current branch at this point, then reattaches the current branch
	 * to the given branch head.
	 * 
	 * If files in the current branch are not modified from the split point,
	 * then modified files at the front of the given branch copied into the new
	 * commits. No conflicted files are generated, however.
	 * 
	 * @param branchName
	 *            String name of branch to be merged
	 */
	public void rebase(String branchName) {
		if (conflictedState) {
			conflictedState();
		}
		if (!commitBranches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			System.exit(0);
		}
		if (commitBranches.get(branchName).equals(currentBranch)) {
			System.out.println("Cannot rebase a branch onto itself.");
			System.exit(0);
		}
		Commit afterSplit;
		Commit branchHead = commitBranches.get(branchName);
		Commit tempCur = currentBranch;
		Commit splitPoint = findSplit(branchName);
		if (samebranch == true && branchHead.commitID < currentBranch.commitID) {
			System.out.println("Already up-to-date.");
			System.exit(0);
		}
		if (samebranch == true && branchHead.commitID > currentBranch.commitID) {
			currentBranch = branchHead;
			commitBranches.replace(currentBranchName, currentBranch);
			System.exit(0);
		}
		int currentlength = 0;
		while (tempCur != splitPoint) {
			currentlength++;
			tempCur = tempCur.myPrev;
		}
		int copyid = currentlength + (commitCount - 1);
		Stack<Commit> mystack = new Stack<Commit>();
		for (afterSplit = currentBranch; afterSplit != splitPoint; afterSplit = afterSplit.myPrev) {
			Commit copy = new Commit(copyid, afterSplit.myMessage, null);
			for (File f : afterSplit.myFiles.keySet()) {
				if (FileCompare(getPath(f, afterSplit.myFiles.get(f)), getPath(f, splitPoint.myFiles.get(f)))
						&& !(branchHead.myFiles.containsKey(f))) {
					copy.myUntracked.add(f);
				}
				else if (FileCompare(getPath(f, afterSplit.myFiles.get(f)), getPath(f, splitPoint.myFiles.get(f)))
						&& !FileCompare(getPath(f, branchHead.myFiles.get(f)), getPath(f, splitPoint.myFiles.get(f)))) {
					copy.myFiles.put(f, branchHead.myFiles.get(f));
					if (branchHead.myUntracked.contains(f)) {
						copy.myUntracked.add(f);
					}
				}
				else {
					copy.myFiles.put(f, afterSplit.myFiles.get(f));
				}
			}
			mystack.push(copy);
			myCommits.put(copyid, copy);
			copyid--;
			commitCount++;
		}
		while (!mystack.isEmpty()) {
			Commit temp = mystack.pop();
			temp.myPrev = branchHead;
			branchHead = temp;
		}
		reset(String.valueOf(branchHead.commitID));
	}

	/**
	 * A helper method that returns the physical path location of a file. Used
	 * by merge and rebase.
	 * 
	 * @param f
	 *            File to be found
	 * 
	 * @param ID
	 *            the unique id number of the commit where the file resides
	 */
	public Path getPath(File f, int ID) {
		return Paths.get(GITLET_DIR, "commit" + myCommits.get(ID).myFiles.get(f), f.toString());
	}

	/**
	 * Merges files from the given branch into the current branch then creates a
	 * new commit.
	 * 
	 * Files that have been modified in the given branch since the split point,
	 * but not modified in the current branch are checked out from the head
	 * commit at the given branch. Files modified in the current branch but not
	 * in the given branch since the split point stay as they are. Files
	 * modified in both branches since the split point stay as they are, but in
	 * addition, file versions from the given branch are copied into the working
	 * directory with a .conflicted extension.
	 * 
	 * @param branchName
	 *            String name of branch to be merged
	 * 
	 * @throws IOException
	 */
	public void merge(String branchName) throws IOException {
		if (conflictedState) {
			conflictedState();
		} 
		if (!commitBranches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			System.exit(0);
		}
		Commit splitPoint = findSplit(branchName);
		int splitID = splitPoint.commitID;
		int branchID = commitBranches.get(branchName).commitID;
		boolean madeConflicted = false;
		for (File file : commitBranches.get(branchName).myFiles.keySet()) {
			if (splitPoint.myFiles.containsKey(file)) {
				if (!FileCompare(getPath(file, splitID), getPath(file, branchID))
						&& !(currentBranch.myFiles.containsKey(file))) {
					add(file.toString());
				} else if (!FileCompare(getPath(file, splitID), getPath(file, branchID))) {
					if (FileCompare(getPath(file, splitID), getPath(file, currentBranch.myFiles.get(file)))) {
						checkoutHelper(branchID, file.toString());
						add(file.toString());
					} else {
						Path copypath = Paths.get(WORKING_DIR, file.toString() + ".conflicted");
						File copy = new File(copypath.toString());
						Files.copy(getPath(file, branchID), copy.toPath());
						madeConflicted = true;
					}
				}
			} else {
				checkoutHelper(branchID, file.toString());
				add(file.toString());
			}
		}
		for (File file : commitBranches.get(branchName).myUntracked) {
			if (splitPoint.myFiles.containsKey(file)) {
				if (FileCompare(getPath(file, splitID), getPath(file, currentBranch.myFiles.get(file)))) {
					markedfiles.add(file);
				}
			}
		}
		if (!madeConflicted) {
			commit("Merged " + currentBranchName + " with " + branchName + ".");
		} else {
			System.out.println("Encountered a merge conflict.");
			conflictedState = true;
		}
	}

	/**
	 * A helper method that compares the contents of two different files. (Code
	 * adapted from serialization exercises on Piazza.) Used by merge and
	 * rebase.
	 * 
	 * @param path1
	 *            Path object representing the location of file 1
	 * 
	 * @param path2
	 *            Path object representing the location of other file, file2.
	 * 
	 * @return boolean
	 */
	public static boolean FileCompare(Path path1, Path path2) {
		try {
			return Arrays.equals(Files.readAllBytes(path1), Files.readAllBytes(path2));
		} catch (IOException e) {
			System.err.println(e);
			return false;
		}
	}

	/**
	 * Prints an error message and closes the program.
	 * 
	 * While in a conflicted state, Gitlet will only allow: rm, add, commit,
	 * checkout [file], checkout [id] [file], log, global-log, and status. Other
	 * methods must first verify that Gitlet is not in a conflicted state;
	 * otherwise, this method will be called.
	 */
	public void conflictedState() {
		System.out.println("Cannot do this command until the merge conflict has been resolved.");
		System.exit(0);
	}

	/**
	 * Executes the given command in terminal. Only one command is called each
	 * time.
	 * 
	 * If a .gitlet file already exists in the working directory, the previously
	 * saved Gitlet object is deserialized.
	 * 
	 * After running the command, Gitlet is serialized again.
	 */
	public static void main(String[] args) {
		String command = args[0];
		if (command == null) {
			System.out.println("Please enter a command.");
			return;
		}
		Gitlet myGitlet = null;
		File gitdir = new File(GITLET_DIR);
		String ser = ".gitlet/.gitlet.ser";
		FileOutputStream fileOut = null;
		ObjectOutputStream objectOut = null;
		FileInputStream fileIn = null;
		ObjectInputStream objectIn = null;
		if (gitdir.exists()) {
			try {
				fileIn = new FileInputStream(ser);
				objectIn = new ObjectInputStream(fileIn);
				myGitlet = (Gitlet) objectIn.readObject();
				objectIn.close();
			} catch (IOException e0) {
				System.out.println("IOException while deserializing gitlet.");
			} catch (ClassNotFoundException e1) {
				System.out.println("ClassNotFoundException while deserializing gitlet.");
			}
		}
		switch (command) {
		case "init":
			myGitlet = new Gitlet();
			break;
		case "add":
			String filename = args[1];
			myGitlet.add(filename);
			break;
		case "commit":
			String message = args[1];
			myGitlet.commit(message);
			break;
		case "rm":
			String message2 = args[1];
			Path path2 = Paths.get(message2);
			myGitlet.remove(path2.toFile());
			break;
		case "log":
			myGitlet.log();
			break;
		case "global-log":
			myGitlet.globalLog();
			break;
		case "find":
			String message3 = args[1];
			myGitlet.find(message3);
			break;
		case "status":
			myGitlet.status();
			break;
		case "branch":
			String message4 = args[1];
			myGitlet.branch(message4);
			break;
		case "rm-branch":
			String message5 = args[1];
			myGitlet.removebranch(message5);
			break;
		case "rebase":
			String message10 = args[1];
			myGitlet.rebase(message10);
			break;
		case "merge":
			String message9 = args[1];
			try {
				myGitlet.merge(message9);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		case "checkout":
			String message6 = args[1];
			try {
				Integer num = Integer.parseInt(args[1]);
				String message7 = args[2];
				myGitlet.checkoutHelper(num, message7);
			} catch (NumberFormatException e) {
				myGitlet.checkout(message6);
			}
			break;
		case "reset":
			String message8 = args[1];
			myGitlet.reset(message8);
			break;
		default:
			System.out.println("No command with that name exists.");
			break;
		}
		try {
			fileOut = new FileOutputStream(ser);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(myGitlet);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while serializing gitlet.");
		}
	}

	/**
	 * Class that creates Commit objects. Each Commit contains its unique
	 * integer id, its parent commit, a given message, and the time and date
	 * that it was created. Commits also store tracked files and remember the
	 * original commit ID where each file came from.
	 */
	public class Commit implements Serializable {

		private static final long serialVersionUID = 1L;
		private Commit myPrev;
		private int commitID;
		private String myMessage;
		private HashMap<File, Integer> myFiles;
		private HashSet<File> myUntracked;
		private String myDate;

		/**
		 * Constructor for Commit objects.
		 * 
		 * Initializes an empty HashMap to store its tracked files, and an empty
		 * HashSet to store untracked files.
		 */
		public Commit(int id, String message, Commit prev) {
			myPrev = prev;
			commitID = id;
			myMessage = message;
			myFiles = new HashMap<File, Integer>();
			myUntracked = new HashSet<File>();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date commitDate = new Date();
			myDate = dateFormat.format(commitDate);
		}

		/**
		 * Iterates through all parent commits of the current commit and
		 * displays information about their ids, time stamps, and messages.
		 */
		public void log() {
			Commit temp = this;
			while (temp != null) {
				System.out.println("===");
				System.out.println("Commit " + temp.commitID);
				System.out.println(temp.myDate);
				System.out.println(temp.myMessage);
				System.out.println();
				temp = temp.myPrev;
			}
		}
	}
}
