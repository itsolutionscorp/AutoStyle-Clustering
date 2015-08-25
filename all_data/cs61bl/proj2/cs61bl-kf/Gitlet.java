import java.util.*;
import java.io.Serializable;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class Gitlet implements Serializable {

	/**
	 * commitHolder is a HashMap of Integer keys to Commit values. It contains
	 * all commits made, and is mainly used by global-log.
	 * 
	 * pointers is a HashMap of String keys to Pointer values. It is used to
	 * locate the heads of different branches and the active pointer.
	 * 
	 * trackedFiles is a HashMap of String keys to File values. It contains all
	 * the currently tracked files.
	 * 
	 * activePointer is a Pointer object of the current active pointer.
	 * 
	 * myCommitTree is the Gitlet object created for this project.
	 * 
	 * commitID is an integer which tracks the number of Commits made.
	 * 
	 * unTrackedFiles is a HashSet of File references to all untracked files.
	 * 
	 * localUntracked is a HashSet of File references to files marked for
	 * untracking.
	 * 
	 * inStaging is a HashSet of File references to all files in the staging
	 * area.
	 * 
	 * conflictedState is a boolean which changes to true when there is a
	 * .conflicted file in the working directory after the merge command is
	 * called.
	 * 
	 * conflictCommands is a HashSet of Strings, of the only commands allowed
	 * during the conflicted state.
	 */

	private HashMap<Integer, Commit> commitHolder;
	private HashMap<String, Pointer> pointers;
	private HashMap<String, File> trackedFiles;
	private Pointer activePointer;
	private static Gitlet myCommitTree;
	private int commitID = 0;
	private HashSet<String> unTrackedFiles;
	private HashSet<String> localUntracked;
	private HashSet<File> inStaging;
	private boolean conflictedState;
	private final HashSet<String> conflictCommands;

	/**
	 * This is the constructor for Gitlet. It instantiates all variables above
	 * and creates a Staging Area directory and Commits directory in the .gitlet
	 * folder.
	 */
	private Gitlet() {
		Commit myCommit = new Commit("initial commit", null, null, commitID);
		commitHolder = new HashMap<Integer, Commit>();
		commitHolder.put(myCommit.getID(), myCommit);
		trackedFiles = new HashMap<String, File>();
		unTrackedFiles = new HashSet<String>();
		localUntracked = new HashSet<String>();
		inStaging = new HashSet<File>();
		Pointer master = new Pointer("master", myCommit);
		pointers = new HashMap<String, Pointer>();
		pointers.put(master.getName(), master);
		activePointer = pointers.get("master");
		conflictedState = false;
		conflictCommands = new HashSet<String>();
		initializeCC();
		new File(".gitlet/Commits").mkdirs();
		new File(".gitlet/StagingArea").mkdirs();
	}

	/**
	 * A helper method which adds the only methods allowed during the conflicted
	 * state to a HashSet, conflictCommands.
	 */
	private void initializeCC() {
		conflictCommands.add("add");
		conflictCommands.add("rm");
		conflictCommands.add("commit");
		conflictCommands.add("checkout");
		conflictCommands.add("log");
		conflictCommands.add("global-log");
		conflictCommands.add("find");
		conflictCommands.add("status");
	}

	/**
	 * Creates a new gitlet version control system in the current directory (the
	 * working directory). The system will automatically start with one commit
	 * that contains no files as has the commit message "initial commit."
	 * 
	 * precondition: canInit returns true
	 */
	public static void init() {
		if (canInit()) {
			myCommitTree = new Gitlet();
		}
	}

	/**
	 * Checks to see if there is a .gitlet directory in the working directory.
	 * 
	 * @return TRUE if a .gitlet directory not exist in the working directory
	 * @return FALSE if a .gitlet directory exists in the working directory
	 */
	public static boolean canInit() {
		File f = new File(".gitlet");
		if (f.exists() && f.isDirectory()) {
			System.out
					.println("A gitlet version control system already exists in the current directory.");
			return false;
		}
		return true;
	}

	/**
	 * Marks the file for tracking if it had been marked for untracking by the
	 * rm method. Adds the file to the staging area if it had already been
	 * marked for tracking.
	 * 
	 * @param fileName
	 *            the name of the file to mark for tracking or add to the
	 *            staging area
	 */
	public void add(String fileName) {
		File f = new File(fileName);
		if (!(f.exists())) {
			System.out.println("File does not exist.");
			return;
		} else if (unTrackedFiles.contains(getFile(fileName))) {
			unTrackedFiles.remove(getFile(fileName));
			localUntracked.remove(getFile(fileName));
		} else {
			inStaging.add(getFile(fileName));
			copyTo(getFile(fileName), ".gitlet/StagingArea/" + fileName);
		}
		if (!(trackedFiles.containsKey(fileName))) {
			trackedFiles.put(fileName, getFile(fileName));
		}
	}

	/**
	 * Copies a file from the working directory or a subdirectory within the
	 * working directory to the staging area. This method is called upon in the
	 * add method. This method also creates directories in which to place the
	 * copied file, if necessary
	 * 
	 * @param file
	 *            The File to copy to the staging area
	 * @param toFolder
	 *            The directory (Staging Area) to which the file is copied to,
	 *            as a String.
	 * 
	 * @throws IOException
	 *             If "file" or "toFolder" are null or do not exist.
	 */
	private void copyTo(File file, String toFolder) {
		Path p = file.toPath();
		Path t = Paths.get(toFolder);
		try {
			new File(toFolder).mkdirs();
			Files.copy(p, t, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
		}
	}

	/**
	 * A helper method which gets the File which corresponds to the given
	 * String.
	 * 
	 * @param fileName
	 *            The name of the file, as a String.
	 * @return File object
	 */
	private File getFile(String fileName) {
		Path p = Paths.get(fileName);
		return p.toFile();
	}

	/**
	 * Creates a new Commit object which contains all files added to the staging
	 * area, and inherits versions of files that are being tracked from the
	 * previous Commit(s). Adds the newly created Commit object to the
	 * commitHolder HashMap.
	 * 
	 * @param message
	 *            The unique message of the Commit.
	 * @param tracked
	 *            The HashMap<String, File> of the files which the Commit should
	 *            track.
	 */
	public void commit(String message, HashMap<String, File> tracked) {
		if (inStaging.isEmpty() && localUntracked.isEmpty()) {
			System.out.println("No changes added to the commit.");
		} else {
			commitID++;
			String toFolder = ".gitlet/Commits/Commit_" + commitID;
			new File(toFolder).mkdirs();
			for (File f : inStaging) {
				copyTo(f, toFolder + "/" + f.getPath());
				File newFile = new File(toFolder + "/" + f.getPath());
				trackedFiles.put(f.getPath(), newFile);
			}
			Commit newCommit = new Commit(message, activePointer.getMyCommit(),
					trackedFiles, commitID);
			newCommit.setUntracked(unTrackedFiles);
			commitHolder.put(newCommit.getID(), newCommit);
			activePointer.setMyCommit(newCommit);
			if (conflictedState) {
				conflictedState = false;
			}
			localUntracked.clear();
			clearStaging();
		}
	}

	/**
	 * A helper method which deletes all files and directories in the staging
	 * area, and re-instantiates inStaging as a new HashSet of Files. It is
	 * called upon after a Commit is made.
	 */
	private void clearStaging() {
		File stage = new File(".gitlet/StagingArea");
		for (File f : stage.listFiles()) {
			if (f.isDirectory()) {
				deleteDirectory(f);
			}
			f.delete();
		}
		inStaging = new HashSet<File>();
	}

	/**
	 * A helper method which implements recursion to delete a directory. It is
	 * called upon in quite a few methods, including clearStaging.
	 * 
	 * @param directory
	 *            The File directory to delete.
	 */
	private static void deleteDirectory(File directory) {
		for (File f : directory.listFiles()) {
			if (f.isDirectory()) {
				deleteDirectory(f);
			}
			f.delete();
		}
	}

	/**
	 * If the given file is in the staging area, this method removes that file
	 * from the staging area. If the given file is not in the staging area, this
	 * method marks that file for untracking. Unless the given file is remarked
	 * for tracking by the add method, the file will not be included in future
	 * Commits.
	 * 
	 * @param fileName
	 *            The name of the File (as a String) to remove from the staging
	 *            area or mark for untracking.
	 */
	public void rm(String fileName) {
		File check = new File(".gitlet/StagingArea/" + fileName);
		if (check.exists()) {
			inStaging.remove(getFile(fileName));
			check.delete();
		} else if (activePointer.getMyCommit().getFiles().containsKey(fileName) 
				&& (!unTrackedFiles.contains(fileName))) {
			localUntracked.add(fileName);
			unTrackedFiles.add(fileName);
			activePointer.getMyCommit().addToUntracked(fileName);
		} else {
			System.out.println("No reason to remove the file.");
		}
	}

	/**
	 * This method iterates starts at the current Commit and iterates through
	 * the previous Commits (if any), printing out the Commit's message, ID, and
	 * creation date and time. This method uses a helper method logPrint from
	 * the Commit class.
	 */
	public void log() {
		for (Commit temp = activePointer.getMyCommit(); temp != null; temp = temp
				.getMyPrev()) {
			temp.logPrint();
		}
	}

	/**
	 * This method prints the message, ID, and creation date and time of every
	 * commit, by iterating through the the keys of the commitHolder HashMap.
	 * This method holds no regard for order, and uses a helper method logPrint
	 * from the Commit class.
	 */
	public void globalLog() {
		for (Integer key : commitHolder.keySet()) {
			commitHolder.get(key).logPrint();
		}
	}

	/**
	 * Prints out the ID of the commit that has the given commit message. If
	 * there are multiple such commits, it prints the IDs on separate lines.
	 * 
	 * @param message
	 *            The potential message (as a String) of a Commit.
	 */
	public void find(String message) {
		Boolean found = false;
		for (Integer key : commitHolder.keySet()) {
			Commit tempCommit = commitHolder.get(key);
			if (tempCommit.getMessage().equals(message)) {
				found = true;
				System.out.println(tempCommit.getID());
			}
		}
		if (!found) {
			System.out.println("Found no commit with that message.");
		}
	}

	/**
	 * Displays what branches currently exist, marking the current branch with a
	 * Displays what files have been staged or marked for untracking.
	 */
	public void status() {
		String pName;
		System.out.println("=== Branches ===");
		for (Pointer branch : pointers.values()) {
			pName = branch.getName();
			if (pName.equals(activePointer.getName())) {
				System.out.println("*" + branch.getName());
			} else {
				System.out.println(pName);
			}
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		for (File f : inStaging) {
			System.out.println(f.getPath());
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (String key : localUntracked) {
			System.out.println(key);
		}
	}

	/**
	 * This helper method copies a File or directory to the working directory.
	 * It is mainly called upon when the user calls a checkout or reset command.
	 * 
	 * @param file
	 *            The File which will be copied to the working directory.
	 * @param destination
	 *            Where in the working directory the file is to go, represented
	 *            as a String.
	 * 
	 * @throws IOException
	 *             If file or the destination (working directory) is found to be
	 *             null or nonexistent.
	 */
	private void copyToWD(File file, String destination) {
		Path p = file.toPath();
		Path t = Paths.get(destination);
		try {
			new File(destination).mkdirs();
			Files.copy(p, t, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
		}
	}

	/**
	 * precondition: checkoutDispatch identifies the argument as the name of a
	 * File within the head Commit.
	 * 
	 * It takes the version of the file as it exists in the head commit at the
	 * front of the current branch, and puts it in the working directory,
	 * overwriting the version of the file that's already there (if there is
	 * one).
	 * 
	 * @param fileName
	 *            The name of the file
	 */
	public void checkoutFile(String fileName) {
		Commit headCommit = activePointer.getMyCommit();
		if (!(headCommit.getUntracked().contains(getFile(fileName)))) {
			copyToWD(headCommit.getFiles().get(fileName), fileName);
		}
	}

	/**
	 * precondition: checkout is called in the terminal with two arguments.
	 * 
	 * @param id
	 *            The ID of the Commit from which the user wants to check out a
	 *            file.
	 * @param fileName
	 *            The name of the File in the Commit that the user wants to
	 *            check out.
	 * 
	 * @throws NumberFormatException
	 *             If id the user passes cannot be parsed into an Integer.
	 */
	public void checkoutCommit(String id, String fileName) {
		int myID;
		try {
			myID = Integer.parseInt(id);
		} catch (NumberFormatException nfe) {
			System.out.println("No commit with that id exists.");
			return;
		}
		Commit myCommit = commitHolder.get(myID);
		if (myCommit == null) {
			System.out.println("No commit with that id exists.");
			return;
		}
		HashMap<String, File> myCommitFiles = myCommit.getFiles();
		if ((!myCommitFiles.containsKey(fileName)) || myCommit.getUntracked().contains(fileName)) {
			System.out.println("File does not exist in that commit.");
		} else {
			copyToWD(myCommitFiles.get(fileName), fileName);
		}
	}

	/**
	 * precondition: checkoutDispatch identifies the argument as the name of a
	 * branch.
	 * 
	 * Takes all files in the Commit at the head of the given branch, and puts
	 * them in the working directory, overwriting the versions of the files that
	 * are already there if they exist. At the end of this command, the given
	 * branch is considered to be the current branch.
	 * 
	 * @param branch
	 *            The name of the branch (as a String) of which the user wants
	 *            to check out all files.
	 */
	public void checkoutBranch(String branch) {
		if (activePointer.getName().equals(branch)) {
			System.out.println("No need to checkout the current branch.");
		} else {
			Commit check = pointers.get(branch).getMyCommit();
			HashMap<String, File> checkFiles = check.getFiles();
			activePointer = pointers.get(branch);
			for (String f : checkFiles.keySet()) {
				if (!(check.getUntracked().contains(f))) {
					copyToWD(checkFiles.get(f), f);
				}
			}
			trackedFiles.clear();
			trackedFiles.putAll(activePointer.getMyCommit().getFiles());
			unTrackedFiles.clear();
			unTrackedFiles.addAll(activePointer.getMyCommit().getUntracked());
		}
	}

	/**
	 * This helper method distinguishes the argument given to a checkout command
	 * as either a file, a branch, or neither. If the argument is found to be
	 * the name of a File in a Commit or the name of a branch, the corresponding
	 * checkout method is called with the argument. If the argument is found to
	 * be neither the name of a file or commit, an error message is printed.
	 * 
	 * @param mystery
	 *            The argument passed into the terminal when the checkout
	 *            command is called.
	 */
	private void checkoutDispatch(String mystery) {
		if (pointers.containsKey(mystery)) {
			checkoutBranch(mystery);
		} else if (activePointer.getMyCommit().getFiles().containsKey(mystery)
				&& !activePointer.getMyCommit().getUntracked().contains(mystery)) {
			checkoutFile(mystery);
		} else {
			System.out
					.println("File does not exist in the most recent commit, or no such branch exists.");
		}
	}

	/**
	 * Creates a new branch with the given name, and points it at the current
	 * head Commit. The new branch is NOT considered to be the current branch.
	 * 
	 * @param branchName
	 *            The name of the branch (as a String) which the user wants to
	 *            create.
	 */
	public void branch(String branchName) {
		if (pointers.containsKey(branchName)) {
			System.out.println("A branch with that name already exists.");
		} else {
			Pointer newBranch = new Pointer(branchName,
					activePointer.getMyCommit());
			pointers.put(branchName, newBranch);
		}
	}

	/**
	 * Deletes the branch pointer with the given name. If no branch with the
	 * given name exists, or if the given branch is the active one, this method
	 * prints an error message.
	 * 
	 * @param branchName
	 *            The branch pointer to be deleted.
	 */
	public void rmBranch(String branchName) {
		if (!pointers.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
		} else if (activePointer.getName().equals(branchName)) {
			System.out.println("Cannot remove the current branch.");
		} else {
			pointers.remove(branchName);
		}
	}

	/**
	 * Checks out all the files tracked by the given commit. It moves the
	 * current branch's head to that Commit. If no Commit with the given ID
	 * exists, an error message is printed.
	 * 
	 * @param id
	 *            The ID of the Commit, represented as a String.
	 * 
	 * @throws NumberFormatException
	 *             If the given id cannot be parsed into an Integer.
	 */
	public void reset(String id) {
		int tempID;
		try {
			tempID = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			System.out.println("No commit with that id exists.");
			return;
		}
		if (!(commitHolder.containsKey(tempID))) {
			System.out.println("No commit with that id exists.");
		} else {
			for (String fileName : commitHolder.get(tempID).getFiles().keySet()) {
				if (!(commitHolder.get(tempID).getUntracked().contains(fileName))) {
					checkoutCommit("" + tempID, fileName);
				}
			}
			activePointer.setMyCommit(commitHolder.get(tempID));
			trackedFiles.clear();
			trackedFiles.putAll(activePointer.getMyCommit().getFiles());
			unTrackedFiles.clear();
			unTrackedFiles.addAll(activePointer.getMyCommit().getUntracked());
		}
	}

	/**
	 * Merges files from the given branch into the current branch. The files in
	 * the current branch will include (1) files that were modified in the given
	 * branch but not in the current branch, (2) files that were modified in the
	 * current branch but not in given branch, (3) files modified in both the
	 * given and current branches - the files in the current branch will be
	 * included, but the files in the given branch will be copied into the
	 * working directory with the name "[name].conflicted". However, if a file
	 * was untracked in one branch and the contents of the file were changed in
	 * the other branch, the changed file will be included and no .conflicted
	 * file will be created. This method prints a success message if only files
	 * in situations (1) and (2) are included. If files in situation (3) are
	 * included, the Gitlet system enters a "conflicted state" and only allows
	 * certain commands. The system remains in this state until the user makes a
	 * Commit. An error message is printed if the given branch does not exist or
	 * is the current branch.
	 * 
	 * @param branchName
	 *            The name of the branch with which the user wants to merge with
	 *            the current branch.
	 */
	public void merge(String branchName) {
		Pointer given = pointers.get(branchName);
		if (given == null) {
			System.out.println("A branch with that name does not exist.");
		} else if (given.equals(activePointer)) {
			System.out.println("Cannot merge a branch with itself.");
		} else {
			Commit splitCommit = findSplitCommit(activePointer, given);
			mergeToStage(given.getMyCommit(), activePointer.getMyCommit(),
					splitCommit);
			if (!conflictedState) {
				String message = "Merged " + activePointer.getName() + " with "
						+ branchName + ".";
				commit(message, trackedFiles);
			}
		}
	}

	/**
	 * A helper method which finds the files which should be staged when the
	 * merge command is called. It updates the tracked files to be passed into
	 * the new Commit.
	 * 
	 * @param given
	 *            The head Commit of the branch passed into the merge command.
	 * @param current
	 *            The head Commit of the current branch.
	 * @param split
	 *            The most recent common Commit between the given branch and
	 *            current branch.
	 */
	private void mergeToStage(Commit given, Commit current, Commit split) {
		HashMap<String, File> givenHM = given.getFiles();
		HashMap<String, File> currentHM = current.getFiles();
		HashMap<String, File> splitHM = split.getFiles();
		ArrayList<String> givenChanged = new ArrayList<String>();
		ArrayList<String> currentChanged = new ArrayList<String>();
		ArrayList<String> specialCase = new ArrayList<String>();
		for (String givenK : givenHM.keySet()) {
			if (isChanged(givenHM.get(givenK).toPath(), splitHM.get(givenK))) {
				if (current.getUntracked().contains(givenK)) {
					specialCase.add(givenK);
				} else
					givenChanged.add(givenK);
			}
		}
		for (String currentK : currentHM.keySet()) {
			if (isChanged(currentHM.get(currentK).toPath(),
					splitHM.get(currentK))) {
				if (given.getUntracked().contains(currentK)) {
					specialCase.add(currentK);
				} else {
					currentChanged.add(currentK);
				}
			} else if (given.getUntracked().contains(currentK)) {
				unTrackedFiles.add(currentK);
				currentChanged.add(currentK);
			}
		}
		for (String s : givenChanged) {
			if (!currentChanged.contains(s) || specialCase.contains(s)) {
				copyTo(givenHM.get(s), ".gitlet/StagingArea/" + s);
				inStaging.add(getFile(s));
				trackedFiles.put(s, givenHM.get(s));
			} else {
				copyToWD(givenHM.get(s), s + ".conflicted");
				System.out.println("Encountered a merge conflict.");
				conflictedState = true;
			}
		}
	}

	/**
	 * A helper method which determines whether two files are the same or
	 * different, by parsing through each file and comparing their bytes.
	 * 
	 * @param check
	 *            The Path of one of the files.
	 * @param split
	 *            The second File.
	 * 
	 * @return TRUE if the two files are the same
	 * @return FALSE if the two files are different
	 */
	private boolean isChanged(Path check, File split) {
		if (split == null) {
			return true;
		}
		Path two = split.toPath();
		try {
			byte[] oneTime = Files.readAllBytes(check);
			byte[] twoTime = Files.readAllBytes(two);
			return !(Arrays.equals(oneTime, twoTime));
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * A helper method which finds the most recent Commit common to the two
	 * branches passed in.
	 * 
	 * @param current
	 *            The Pointer of the current branch.
	 * @param given
	 *            The Pointer of the given branch.
	 * 
	 * @return the most recent Commit common to the two branches.
	 */
	private Commit findSplitCommit(Pointer current, Pointer given) {
		Commit one = current.getMyCommit();
		Commit two = given.getMyCommit();
		ArrayList<Integer> store = new ArrayList<Integer>();
		for (Commit c = one; c != null; c = c.getMyPrev()) {
			store.add(c.getID());
		}
		if (store.contains(two.getID())) {
			return two;
		}
		for (Commit b = two.getMyPrev(); b != null; b = b.getMyPrev()) {
			if (store.contains(b.getID())) {
				return b;
			}
		}
		return commitHolder.get(0);
	}

	/**
	 * Makes a copy of the current branch at the point it splits from the given
	 * branch, and attaches this copy to the head of the given branch. This
	 * method prints an error message if the given branch does not exist, if the
	 * given branch and the current branch are the same, or the given branch's
	 * head is an older version of the head of the current branch.
	 * 
	 * @param branch
	 *            The name of the pointer of the branch, as a String, to which
	 *            the user wants to copy the Commits of the current branch.
	 */
	public void rebase(String branchName) {
		Pointer given = pointers.get(branchName);
		if (given == null) {
			System.out.println("A branch with that name does not exist.");
		} else if (given.equals(activePointer)) {
			System.out.println("Cannot rebase a branch onto itself.");
		} else if (currentIsNewer(given)) {
			System.out.println("Already up-to-date.");
		} else if (findSplitCommit(given, activePointer) == activePointer
				.getMyCommit()) {
			activePointer.setMyCommit(given.getMyCommit());
		} else {
			Commit current = activePointer.getMyCommit();
			Commit splitCommit = findSplitCommit(activePointer, given);
			Commit currentEnd = copyCommit(given.getMyCommit(), current,
					splitCommit);
			activePointer.setMyCommit(currentEnd);
			reset("" + currentEnd.getID());
		}
	}

	/**
	 * A helper method which copies certain files from two different Commits to
	 * produce a new Commit object.
	 * 
	 * @param c1
	 *            The Commit which will pass its message, files, and untracked
	 *            files into the new Commit.
	 * @param c2
	 *            The Commit which will be the previous Commit of this new
	 *            Commit.
	 * 
	 * @return a new Commit object with instances from c1 and whose myPrev is
	 *         c2.
	 */
	private Commit copyCommit(Commit given, Commit current, Commit split) {
		HashMap<String, File> newTrackedFiles = decider(given, current, split);
		Commit copy;
		if (current.getMyPrev().equals(split)) {
			copy = new Commit(given.getMessage(), given, newTrackedFiles,
					commitID + 1);
		} else {
			Commit copyPrev = copyCommit(given, current.getMyPrev(), split);
			copy = new Commit(current.getMessage(), copyPrev, newTrackedFiles,
					commitID + 1);
		}
		commitID++;
		commitHolder.put(commitID, copy);
		copy.setUntracked(current.getUntracked());
		return copy;
	}

	/**
	 * A helper method which decides files to copy over when rebase creates a
	 * new Commit.
	 * 
	 * @param given
	 *            The Commit being referenced by the branch passed into the
	 *            rebase command.
	 * @param current
	 *            The Commit being referenced by activePointer.
	 * @param split
	 *            The most recent Commit common to both the given and current
	 *            Commits.
	 * 
	 * @return A HashMap of String keys to File values which will become the
	 *         tracked files of the new Commit.
	 */
	private HashMap<String, File> decider(Commit given, Commit current,
			Commit split) {
		HashMap<String, File> result = current.getFiles();
		HashMap<String, File> givenHM = given.getFiles();
		HashMap<String, File> splitHM = split.getFiles();
		HashSet<String> fileNames = new HashSet<String>();
		fileNames.addAll(result.keySet());
		fileNames.addAll(givenHM.keySet());
		for (String s : fileNames) {
			if (!current.getUntracked().contains(s)) {
				if (!result.containsKey(s)) {
						result.put(s, givenHM.get(s));
				} else if (givenHM.containsKey(s)) {
						if (isChanged(givenHM.get(s).toPath(), splitHM.get(s))) {
							if (!isChanged(result.get(s).toPath(), splitHM.get(s))) {
								result.put(s, givenHM.get(s));
							}
						}
						else {
							result.put(s, result.get(s));
						}
				}
			}
		}
		return result;
	}

	/**
	 * A helper method that checks whether or not the given branch pointer is in
	 * the history of the current branch.
	 * 
	 * @param branch
	 *            The Pointer of the branch which may or may not be in the
	 *            history of the current branch.
	 * 
	 * @return true if the given branch is in the history of the current branch.
	 */
	private boolean currentIsNewer(Pointer branch) {
		Commit given = branch.getMyCommit();
		for (Commit active = activePointer.getMyCommit(); active.getMyPrev() != null; active = active
				.getMyPrev()) {
			if (given.equals(active)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * precondition: serExists (called upon in the main method) returns true
	 * 
	 * This method reads and enters the given Gitlet system.
	 * 
	 * @return the existing Gitlet system, called "CommitTree.ser" in the
	 *         .gitlet folder in the working directory.
	 * 
	 * @throws IOException
	 *             This method fails for an unknown reason.
	 * @throws ClassNotFoundException
	 *             If "CommitTree.ser" does not exist in the .gitlet directory
	 *             of the working directory.
	 */
	private static Gitlet serialRead() {
		Gitlet tree = null;
		try {
			ObjectInput input = new ObjectInputStream(new FileInputStream(
					".gitlet/CommitTree.ser"));

			tree = (Gitlet) input.readObject();
		} catch (IOException e) {
		} catch (ClassNotFoundException e2) {
		}
		return tree;
	}

	/**
	 * Serializes myCommitTree, an instance of a Gitlet system, saving the file
	 * within the .gitlet directory of the working directory.
	 * 
	 * @throws IOException
	 *             This method fails for an unknown reason.
	 */
	public static void serialWrite(Gitlet commitTree) {
		try {
			ObjectOutput output = new ObjectOutputStream(new FileOutputStream(
					".gitlet/CommitTree.ser"));
			output.writeObject(commitTree);
		} catch (IOException e) {
		}
	}

	/**
	 * A helper method which checks whether the .gitlet folder in the working
	 * directory contains "CommitTree.ser," the file representation of the
	 * Gitlet system.
	 * 
	 * @return TRUE if "CommitTree.ser" exists
	 * @return FALSE if "CommitTree.ser" does not exist
	 */

	private static boolean serExists() {
		File itExists = new File(".gitlet/CommitTree.ser");
		return itExists.exists();
	}

	/**
	 * precondition: conflictedState boolean is true.
	 * 
	 * A helper method which limits the commands passed into the terminal when
	 * Gitlet is in a conflicted state.
	 * 
	 * @param command
	 *            The command passed into the terminal.
	 */
	private boolean limitCommands(String[] command) {
		if (!conflictCommands.contains(command[0])) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return true;
		} else if (command[0].equals("checkout") && command.length == 2) {
			if (myCommitTree.pointers.containsKey(command[1])) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				return true;
			}
		}
		return false;
	}

	/**
	 * A helper method which checks which arguments were passed into the
	 * terminal. Calls the corresponding method with the given arguments.
	 * 
	 * @param args
	 *            The arguments that the user passed into the terminal,
	 *            formatted in a String array.
	 */
	public static void checkCommands(String[] args) {
		if (args[0].equals("init")) {
			init();
			return;
		} else if (myCommitTree.conflictedState && myCommitTree.limitCommands(args)) {
			return;
		}
		switch (args[0]) {
		case "add":
			myCommitTree.add(args[1]);
			break;
		case "commit":
			if (args.length < 2) {
				System.out.println("Please enter a commit message.");
				break;
			} else {
				myCommitTree.commit(args[1], myCommitTree.trackedFiles);
				break;
			}
		case "rm":
			myCommitTree.rm(args[1]);
			break;
		case "log":
			myCommitTree.log();
			break;
		case "global-log":
			myCommitTree.globalLog();
			break;
		case "find":
			myCommitTree.find(args[1]);
			break;
		case "status":
			myCommitTree.status();
			break;
		case "checkout":
			if (args.length == 3) {
				myCommitTree.checkoutCommit(args[1], args[2]);
				break;
			} else {
				myCommitTree.checkoutDispatch(args[1]);
				break;
			}
		case "branch":
			myCommitTree.branch(args[1]);
			break;
		case "rm-branch":
			myCommitTree.rmBranch(args[1]);
			break;
		case "reset":
			myCommitTree.reset(args[1]);
			break;
		case "merge":
			myCommitTree.merge(args[1]);
			break;
		case "rebase":
			myCommitTree.rebase(args[1]);
			break;
		default:
			System.out.println("No command with that name exists.");
		}
	}

	/**
	 * This is the main method of Gitlet. It is called whenever the user types
	 * "java Gitlet 'args'".
	 * 
	 * @param args
	 *            The arguments the user passes into the terminal, formatted
	 *            into a String array. The first string in the array is the
	 *            command, and the rest of the strings in the array (if any) are
	 *            the arguments passed into that method.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please enter a command.");
			return;
		} else if (serExists()) {
			myCommitTree = serialRead();
		} else if (!args[0].equals("init")) {
			return;
		}
		checkCommands(args);
		serialWrite(myCommitTree);
	}
}
