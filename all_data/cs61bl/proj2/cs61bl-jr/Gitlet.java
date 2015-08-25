import java.util.*;
import java.util.Map.Entry;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;

public class Gitlet implements Serializable {

	private static final long serialVersionUID = 1L;
	Commit head;
	String currentBranch;
	HashMap<Integer, Commit> commits; // Key = commit's id, Value = commit
	HashMap<String, Commit> branches; // Key = branch name, Value = pointer to a
										// commit
	HashMap<String, File> tempTracking; // Key = file name, Value = file
	ArrayList<String> unTracking; //
	HashMap<String, File> staged; // Key = file name, Value = file
	File stagingArea;
	int _myNumber;
	HashMap<String, ArrayList<Commit>> breakpoints;
	boolean mergeConflict;

	// *************CHANGE TO ".gitlet/" BEFORE SUBMITTING***************//
	private static final String GITLET_DIR = ".gitlet/";

	/**
	 * purgeDirectory deletes all the files in the given directory.
	 * 
	 * @param dir
	 *            All the files contained the the given dir directory will be
	 *            deleted.
	 */

	public static void purgeDirectory(File dir) {
		// source:
		// http://stackoverflow.com/revisions/f851d89b-431f-4e38-b055-9c272bcf2170/view-source
		for (File file : dir.listFiles()) {
			if (file.isDirectory())
				purgeDirectory(file);
			file.delete();
		}
	}

	/**
	 * The init method starts and creates a new Gitlet version control system in
	 * the current directory the user is in. This system will automatically
	 * begin with one commit that has the commit message "initial commit" and
	 * has no files in it.
	 */

	public void init() {
		// Creates the folder
		File dir = new File(GITLET_DIR);
		if (dir.exists()) {
			System.out
					.println("A gitlet version control system already exists in the current directory.");
			return;
		}
		dir.mkdir();
		// Instantiating variables
		head = new Commit("initial commit", null, null, GITLET_DIR, _myNumber);
		currentBranch = "master";
		commits = new HashMap<Integer, Commit>();
		branches = new HashMap<String, Commit>();
		tempTracking = new HashMap<String, File>();
		unTracking = new ArrayList<String>();
		staged = new HashMap<String, File>();
		breakpoints = new HashMap<String, ArrayList<Commit>>();
		mergeConflict = false;
		// Adds the initial commit to the HashMap of commits
		commits.put(head.getID(), head);
		branches.put("master", head);
		breakpoints.put("master", new ArrayList<Commit>());

		// Creates the staging area folder in the .gitlet folder
		stagingArea = new File(GITLET_DIR, "stagingArea");
		stagingArea.mkdir();
		// _myNumber = 0;
	}

	/**
	 * The add method adds a copy of the file as it currently exists to the
	 * staging area. The staging area should be stored in .gitlet. If the file
	 * had been marked for untracking, then this method would unmark the file
	 * and not add it to the staging area. On the other hand, if the file does
	 * not exist, the method prints out the following error message:
	 * "File does not exist."
	 * 
	 * @param fileName
	 *            String; the copy of the file with the given fileName will be
	 *            added to the staging area.
	 */

	public void add(String fileName) {
		// Checks if file exists
		File parentCopy = new File("./" + fileName);
		if (!parentCopy.exists()) {// Failure case
			System.out.println("File does not exist");
			return;
		}

		// Tracking
		if ((!tempTracking.containsKey(fileName) && (unTracking
				.contains(fileName)))) { // if the file has been marked
			tempTracking.put(fileName, (parentCopy));
			unTracking.remove(fileName);
			// Staging ACTUALLY WORKS NOW
		} else {
			File file = new File(fileName);
			File stagedLoc = new File(stagingArea.getPath(), fileName);
			stagedLoc.getParentFile().mkdirs();
			try {
				Files.copy(file.toPath(), stagedLoc.toPath(),
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			staged.put(fileName, file);
		}
	}

	/**
	 * The commit method saves a backup of certain files so they can be restored
	 * at a later time. By default, each commit's snapshot of files will be
	 * exactly the same as those of its parent commits; it will keep versions of
	 * files exactly as they are without updating anything. A commit will only
	 * update the version of a file it is tracking if that file had been staged
	 * at the time of commit, in which case the commit will now include the
	 * version of the file that was staged instead of the version it got from
	 * its parent. In addition, a commit will start tracking any files that were
	 * staged but weren't tracked by its parent.
	 * 
	 * @param message
	 *            String; the commit we make will have the given message.
	 */

	public void commit(String message) {
		// failure cases:
		if (staged.isEmpty() && unTracking.equals(head.unTracking)) {
			System.out.println("No changes added to the commit.");
			return;
		}
		// Makes sure there is a message
		if (message == null) {
			System.out.println("Please enter a commit message.");
			return;
		}

		// Committing
		// head.setNext(new Commit ("initial commit", head, tempTracking,
		// head.getFolder()+""));
		// head = head.getNext();
		_myNumber++;
		head = new Commit(message, head, tempTracking, head.getFolder() + "",
				_myNumber);
		commits.put(head.getID(), head);
		branches.put(currentBranch, head);

		Iterator<Entry<String, File>> stagedIt = staged.entrySet().iterator();
		while (stagedIt.hasNext()) {
			File temp = stagedIt.next().getValue();
			String tempName = temp.getPath();
			File dest = new File(head.getFolder().getPath(), tempName);
			dest.getParentFile().mkdirs();
			try {
				Files.copy(temp.toPath(), dest.toPath(),
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			head.tracking.put(tempName, dest);
		}

		// After committing we:
		// reset the tracking-list to reflect the current head's tracking-list
		// reset unTracking list
		// delete the old stagingArea
		// recreate a new empty stagingArea
		resetTempTracking();
		resetUnTracking();
		staged = new HashMap<String, File>();
		purgeDirectory(stagingArea);
		mergeConflict = false;
	}

	/*
	 * public void changeCommitPointers() { Iterator<Entry<String, File>>
	 * stagedIt = staged.entrySet().iterator(); while (stagedIt.hasNext()) {
	 * File temp = stagedIt.next().getValue(); head.tracking.put(temp.getName(),
	 * temp); } }
	 */

	/**
	 * The rm method marks the file for untracking, meaning the file will not be
	 * included in the upcoming commit, even if it was tracked by that commit's
	 * parent. If the file had been staged, the rm method would unstage it, and
	 * unmark it for untracking.
	 * 
	 * @param fileName
	 *            String; the file with the given fileName will be either marked
	 *            for untracking or be unstaged and unmarked for untracking if
	 *            the file had already been staged.
	 */

	public void rm(String fileName) {
		if (staged.containsKey(fileName)) {// if the file had been staged
			File fileToBeRemoved = new File("gitlet/stagedArea/" + fileName);
			fileToBeRemoved.delete();
			staged.remove(fileName);
		} else if (tempTracking.containsKey(fileName)) {// if the file had been
														// tracked
			unTracking.add(fileName);
			tempTracking.remove(fileName);
		} else {
			System.out.println("No reason to remove the file");// Failure case
		}
	}

	/*
	 * public static Gitlet number() { if (!aGitletExists()) { return new
	 * Gitlet(); } else { return serialRead(); } }
	 */

	/**
	 * The log method displays information about each commit backwards along the
	 * commit tree until the initial commit, with the most recent commit
	 * displayed on the very top. For every commit in this history, the
	 * information it should display is the commit id, the time the commit was
	 * made, and the commit message.
	 */

	public void log() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-M hh:mm:ss"); 
		Commit tempPointer = head;
		while (tempPointer != null) {
			System.out.println("===");
			System.out.println("Commit " + tempPointer.getID());
			System.out.println(dateFormat.format(tempPointer.getTime()));
			System.out.println(tempPointer.getMessage());
			System.out.println("");
			tempPointer = tempPointer.getPrev();
		}
	}

	/**
	 * The globalLog method is slightly different from the log method. This
	 * method displays information about ALL the commit history ever made,
	 * instead of going backwards along the commit tree and ignoring the other
	 * branches and the future. The order of the commits does not matter. The
	 * information of the commits are printed in the same format as the log
	 * method.
	 */

	public void globalLog() { 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-M hh:mm:ss");
		for (Commit c : commits.values()) {
			System.out.println("===");
			System.out.println("Commit " + c.getID());
			System.out.println(dateFormat.format(c.getTime()));
			System.out.println(c.getMessage());
			System.out.println("");
		}
	}

	/**
	 * The find method prints out the ID of the commit with the given commit
	 * message; if there are multiple commits with the same given message, the
	 * method prints out each ID on separate lines. Each commit's information
	 * should display commit id, the time the commit was made, and the commit
	 * message. If no such commit with the given message exists, the method
	 * prints the error message, "Found no commit with that message."
	 * 
	 * @param message
	 *            String; this method will search for the given commit message.
	 */

	public void find(String message) {
		for (Commit a : commits.values()) {
			if (a.getMessage().equals(message)) {
				System.out.println(a.getID());
				return;
			}
		}
		System.out.println("Found no commit with that message.");
	}

	/**
	 * The status method displays what branches currently exist, and marks the
	 * current branch with "*" beside it. In addition, the method displays which
	 * files have been staged or marked for untracking.
	 */

	public void status() {
		System.out.println("=== Branches ===");
		for (String key : branches.keySet()) {
			if (key.equals(currentBranch)) {
				System.out.println("*" + key);
			} else {
				System.out.println(key);
			}
		}
		System.out.println("");
		//
		System.out.println("=== Staged Files ===");
		for (String key : staged.keySet()) {
			System.out.println(key);
		}
		System.out.println("");
		//
		System.out.println("=== Files Marked for Untracking ===");
		for (int i = 0; i < unTracking.size(); i++) {
			System.out.println(unTracking.get(i));
		}
		System.out.println("");
	}

	/**
	 * The checkout method can execute different commands depending on what its
	 * arguments are. If it takes in a String, then it takes the version of the
	 * file as it exists in the head commit, the front of the current branch,
	 * and puts it in the working directory, overwriting the version of the file
	 * that's already there if there is one. If the file does not exist in the
	 * previous commit, aborts, printing the error message, "File does not exist
	 * in the most recent commit, or no such branch exists."
	 * 
	 * @param fileName
	 *            String; the method searches for the commit with the given
	 *            fileName to put in the working directory.
	 */

	public void checkout(String fileName) {
		// First if-statement checks if we're looking at a branch or not
		if (branches.containsKey(fileName)) {
			currentBranch = fileName; // sets current-branch to the branch we're
										// looking at
			head = branches.get(currentBranch); // sets head-pointer to head of
												// the branch
			resetTempTracking(); // sets tempTracking to new head's tracking
			resetUnTracking();

			// create an iterator
			Iterator<Entry<String, File>> trackingIt = tempTracking.entrySet()
					.iterator();
			while (trackingIt.hasNext()) {
				// copies each file in the tracking list into the working
				// directory
				File temp = trackingIt.next().getValue();
				File file = new File(temp.getName());
				if (!file.exists()) {
					file.mkdir();
				}
				try {
					Files.copy(tempTracking.get(temp.getName()).toPath(),
							file.toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// Second if-statement checks if we're looking at file that exists
		} else if (branches.get(currentBranch).tracking.containsKey(fileName)) {
			// copies the file in the tracking list into the working directory
			File file = new File(fileName);
			if (!file.exists()) {
				file.mkdir();
			}
			try {
				Files.copy(branches.get(currentBranch).tracking.get(fileName)
						.toPath(), file.toPath(),
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// if neither a file or branch exists with the given name
			System.out
					.println("File does not exist in the most recent commit, or no such branch exists.");
		}

	}

	/**
	 * If the checkout method takes in an int and a String as its parameters, it
	 * takes the version of the file as it exists in the commit with the given
	 * id, and puts it in the working directory, overwriting the version of the
	 * file that's already there if there is one. If no such commit with the
	 * given id exists, the method should print out the following error message,
	 * "No commit with that id exists." Otherwise, if the file does not exist in
	 * the given commit, print the following error message,
	 * "File does not exist in that commit."
	 * 
	 * @param commitID
	 *            int; looks for the commit with the given commitID
	 * 
	 * @param fileName
	 *            String; looks for the commit with the given fileName
	 */

	public void checkout(int commitID, String fileName) {
		if (!commits.containsKey(commitID)) { // checks if the commit exists
			System.out.println("No commit with that id exists.");
		} else if (commits.get(commitID).tracking.containsKey(fileName)) {
			// copies the file in the tracking list into the working directory
			System.out.println(commits.get(commitID).tracking.get(fileName));
			File file = new File(fileName);
			if (!file.exists()) {
				file.mkdir();
			}
			try {
				Files.copy(commits.get(commitID).tracking.get(fileName)
						.toPath(), file.toPath(),
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// if the file doesn't exist
			System.out.println("File does not exist in that commit.");
		}
	}

	/**
	 * The branch method creates a new branch with the given branchName, and
	 * points it at the current head node. The default branch should be called
	 * "master". If a branch with the given name already exists, the method
	 * prints the error message, "A branch with that name already exists."
	 * 
	 * @param branchName
	 *            String; the new branch we create will be named after the given
	 *            branchName.
	 */

	public void branch(String branchName) {
		if (branches.containsKey(branchName)) {
			System.out.println("A branch with that name already exists.");
			return;
		}
		branches.put(branchName, head);
		breakpoints.put(branchName, new ArrayList<Commit>());
		breakpoints.get(currentBranch).add(head);
		breakpoints.get(branchName).add(head);
	}

	/**
	 * The rm_branch removes the branch with the given name as well as the
	 * pointer associated with the branch. However, do not delete all commits
	 * that were created under the branch. If a branch with the given name does
	 * not exist, the method aborts and prints the following error message,
	 * "A branch with that name does not exist." If you try to remove the branch
	 * you're currently on, the method should aborts and print the error
	 * message, "Cannot remove the current branch."
	 * 
	 * @param branchName
	 *            String; the branch with the given branchName will be removed.
	 */

	public void rm_branch(String branchName) {
		if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (currentBranch.equals(branches.get(branchName))) {
			System.out.println("Cannot remove the current branch.");
		}
		branches.remove(branchName);
		breakpoints.remove(branchName);
	}

	/**
	 * The reset method checks out all the files tracked by the given commit.
	 * This method also moves the current branch's head to that commit node. If
	 * no commit with the given id exists, this method will print the following
	 * message, "No commit with that id exists."
	 * 
	 * @param commitID
	 *            int; the method will search for the commit with the given
	 *            commit ID to check out the files tracked by the commit.
	 */

	public void reset(int commitID) {
		if (!commits.containsKey(commitID)) {
			System.out.println("No commit with that id exists.");
			return;
		}
		try {
			Iterator<Entry<String, File>> trackingIt = commits.get(commitID).tracking
					.entrySet().iterator();
			while (trackingIt.hasNext()) {
				checkout(commitID, trackingIt.next().getKey());
			}
			head = commits.get(commitID);
			commits.put(head.getID(), head);
			branches.put(currentBranch, head);
		} catch (NullPointerException e) {
		}
	}

	/**
	 * Checks if the serialization of Gitlet is successful.
	 * 
	 * @return true if does serialize or false otherwise.
	 */

	private static boolean aGitletExists() {
		File savedNumber = new File("Gitlet.ser");
		return savedNumber.exists();
	}

	/**
	 * tempTracking is a hashmap of all the files its tracking.
	 * resetTempTracking creates a copy of the current head commit's tracking
	 * list so that they are separate data structures but with the same elements
	 * and then points Gitlet's tempTracking to the copies.
	 */

	public void resetTempTracking() {
		tempTracking = new HashMap<String, File>();
		if (head.tracking != null) {
			Iterator<Entry<String, File>> trackedIt = head.tracking.entrySet()
					.iterator();
			while (trackedIt.hasNext()) {
				File temp = trackedIt.next().getValue();
				tempTracking.put(temp.getName(), temp);
			}
		}
	}

	/**
	 * tempUntracking is an arraylist of files marked for untracking.
	 * resetUnTracking creates a copy of the current head commit's unTracking
	 * lists so that they are separate data structures but with the same
	 * elements and then points Gitlet's unTracking to the copies.
	 */

	public void resetUnTracking() {
		unTracking = new ArrayList<String>();
		for (int i = 0; i < head.unTracking.size(); i++) {
			unTracking.add(head.unTracking.get(i));
		}
	}

	/**
	 * This method searches for the breakpoint of the two given branches. A
	 * breakpoint is where the two branches split.
	 * 
	 * @param branch1
	 *            The first branch with the given name that the method searches
	 *            for.
	 * @param branch2
	 *            The second branch with the given name that the method searches
	 *            for.
	 * @return the breakpoint of the two branches.
	 */

	private Commit findBreakpoint(String branch1, String branch2) {
		ArrayList<Commit> array1 = breakpoints.get(branch1);
		ArrayList<Commit> array2 = breakpoints.get(branch2);
		Commit breakpt = null;
		for (Commit comm : array1) {
			if (array2.contains(comm)) {
				breakpt = comm;
				break;
			}
		}
		return breakpt;
	}

	/**
	 * The rebase method finds the split point of the current branch and the
	 * given branch, then snaps off the current branch at this point, then
	 * reattaches the current branch to the head of the given branch. If a
	 * branch with the given name does not exist, print the error message,
	 * "A branch with that name does not exist." Otherwise, if the given branch
	 * name does not match the current branch name, print the error message,
	 * "Cannot rebase a branch onto itself." If the input branch's head is in
	 * the history of the current branch's head, print the error message,
	 * "Already up-to-date."
	 * 
	 * @param branchName
	 *            String; the name of the branch that the method will search
	 *            for.
	 */

	public void rebase(String branchName) {
		// Failure cases
		if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (branchName.equals(currentBranch)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		} else if (branches.get(currentBranch).tracking.equals(branches
				.get(branchName).tracking)) {
			System.out.println("Already up-to-date.");
			return;
		}
		// Special case (If the current branch pointer is in the history of the
		// given branch.)
		Commit lastHeadGivenBranch = branches.get(branchName);
		Commit prevCommit = branches.get(branchName);
		Commit currentHead = branches.get(currentBranch);
		while (prevCommit != null) {
			if (prevCommit == currentHead) {
				currentBranch = branchName;
				return;
			}
			prevCommit = prevCommit.getPrev();
		}
		// Rebasing
		Commit tempCommit = branches.get(currentBranch);
		Commit tempCommitNext = null;
		Commit breakpoint = findBreakpoint(currentBranch, branchName);
		ArrayList<Commit> copyingCommits = new ArrayList<Commit>();

		while (tempCommit != breakpoint) {
			copyingCommits.add(tempCommit);// copy commits to the temporal list.
			tempCommitNext = tempCommit;
			tempCommit = tempCommit.getPrev();
		}

		// copy the commit right after the split and connect
		HashMap<String, File> tempHashMap = new HashMap<String, File>();

		for (String key : tempCommitNext.tracking.keySet()) {
			tempHashMap.put(key, tempCommitNext.tracking.get(key));
		}
		_myNumber++;
		Commit copyingCommit = new Commit(tempCommitNext.getMessage(),
				branches.get(branchName), tempHashMap, GITLET_DIR, _myNumber);
		trackedCrossChecker(copyingCommit, breakpoint, lastHeadGivenBranch);
		branches.put(currentBranch, copyingCommit);
		head = copyingCommit;
		commits.put(head.getID(), head);

		// creating new copies of the commits in the list
		for (int i = copyingCommits.size() - 2; i > -1; i--) {
			tempHashMap = new HashMap<String, File>();
			for (String key : copyingCommits.get(i).tracking.keySet()) {
				tempHashMap.put(key, copyingCommits.get(i).tracking.get(key));
			}
			_myNumber++;
			Commit newCommit = new Commit(copyingCommits.get(i).getMessage(),
					copyingCommit, tempHashMap, GITLET_DIR, _myNumber);
			trackedCrossChecker(newCommit, breakpoint, copyingCommit);
			copyingCommit = newCommit;
			branches.put(currentBranch, newCommit);
			head = newCommit;
			commits.put(head.getID(), head);
		}
	}

	/**
	 * trackedCrossChecker is a helper method to the rebase method. It
	 * propagates any changes made to any files in the given branch through the
	 * branch that has already been rebased.
	 * 
	 * @param current
	 *            The current branch that contains and tracks the files
	 * @param breakpoint
	 *            The node where the two (or more) branches stem from
	 * @param givenBranchHead
	 *            The given branch's head commit.
	 */

	public void trackedCrossChecker(Commit current, Commit breakpoint,
			Commit givenBranchHead) {
		for (String key : givenBranchHead.tracking.keySet()) {
			if (!current.tracking.containsKey(key)) {
				current.tracking.put(key, givenBranchHead.tracking.get(key));
			} else if (current.tracking.get(key) == breakpoint.tracking
					.get(key)) {
				System.out.println(current + " " + key);
				current.tracking.put(key, givenBranchHead.tracking.get(key));
			}
		}
		for (int i = 0; i < givenBranchHead.unTracking.size(); i++) {
			String unTrackedFile = givenBranchHead.unTracking.get(i);
			if (!current.unTracking.contains(unTrackedFile)) {
				current.unTracking.add(unTrackedFile);
				current.tracking.remove(unTrackedFile);
			}
		}
	}

	/**
	 * The merge method merges files from the given branch into the current
	 * branch. Any files that have been modified in the given branch since the
	 * split point, but not modified in the current branch since the split point
	 * should be changed to their versions in the given branch. If a branch with
	 * the given name does not exist, the method should print the error message,
	 * "A branch with that name does not exist." If attempting to merge a branch
	 * with itself, print the error message,
	 * "Cannot merge a branch with itself." If merge would generate an error
	 * because the commit that it does has no changes in it, just let the normal
	 * commit error message for this go through.
	 * 
	 * @param branchName
	 *            The branch with the given branchName will be merged with the
	 *            current branch.
	 */

	public void merge(String branchName) {
		if (branchName.equals(currentBranch)) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}
		if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
		}

		Commit mergeCommit = branches.get(branchName);
		Commit currCommit = head;
		Commit breakpt = findBreakpoint(currentBranch, branchName);

		for (String s : mergeCommit.tracking.keySet()) {
			File mergeCommitFile = mergeCommit.tracking.get(s);
			File currCommitFile = currCommit.tracking.get(s);
			File breakptFile = breakpt.tracking.get(s);
			String mergeCommitString = null;
			String currCommitString = null;
			String breakptString = null;
			try {
				if (mergeCommitFile != null) {
					mergeCommitString = mergeCommitFile.getCanonicalPath();
				}
				if (currCommitFile != null) {
					currCommitString = currCommitFile.getCanonicalPath();
				}
				if (breakptFile != null) {
					breakptString = breakptFile.getCanonicalPath();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (mergeCommitString == null || currCommitString == null
					|| breakptFile == null) {
				if (mergeCommitFile != null) {
					tempTracking.put(s, mergeCommitFile);
				}
				continue;
			}

			if (!mergeCommitString.equals(breakptString)
					&& currCommitString.equals(breakptString)) {
				File stagedLoc = new File(stagingArea.getPath(), s);
				stagedLoc.getParentFile().mkdir();
				try {
					Files.copy(mergeCommitFile.toPath(), stagedLoc.toPath(),
							StandardCopyOption.REPLACE_EXISTING);
					Files.copy(mergeCommitFile.toPath(), new File(s).toPath(),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
				staged.put(s, new File(s));
			} else if (!currCommitString.equals(breakptString)
					&& !mergeCommitString.equals(breakptString)) {
				mergeConflict = true;
				File stagedLoc = new File(stagingArea.getPath(), s
						+ ".conflicted");
				stagedLoc.getParentFile().mkdir();
				try {
					Files.copy(mergeCommitFile.toPath(), stagedLoc.toPath(),
							StandardCopyOption.REPLACE_EXISTING);
					Files.copy(mergeCommitFile.toPath(), new File(s
							+ ".conflicted").toPath(),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
				staged.put(s, new File(s));
			}
			tempTracking.put(s, mergeCommitFile);
		}
		for (String s : currCommit.tracking.keySet()) {
			if (!tempTracking.containsKey(s)) {
				tempTracking.put(s, currCommit.tracking.get(s));
			}
		}
		if (!mergeConflict) {
			mergeCommit("merge successful");
			System.out.println("Merged " + currentBranch + " with "
					+ branchName + ".");
		} else {
			System.out.println("Encountered a merge conflict.");
		}

	}

	/**
	 * Helper function for merge(); This method does the same things as the
	 * commit() method but without any failure cases.
	 */

	// identical to commit except it doesn't have failure cases
	public void mergeCommit(String message) {
		// failure cases:
		// if (staged.isEmpty() && unTracking.equals(head.unTracking)) {
		// System.out.println("No changes added to the commit.");
		// return;
		// }
		// //Makes sure there is a message
		// if (message == null) {
		// System.out.println("Please enter a commit message.");
		// return;
		// }

		// Committing
		// head.setNext(new Commit ("initial commit", head, tempTracking,
		// head.getFolder()+""));
		// head = head.getNext();
		_myNumber++;
		head = new Commit(message, head, tempTracking, head.getFolder() + "",
				_myNumber);
		commits.put(head.getID(), head);
		branches.put(currentBranch, head);

		Iterator<Entry<String, File>> stagedIt = staged.entrySet().iterator();
		while (stagedIt.hasNext()) {
			File temp = stagedIt.next().getValue();
			String tempName = temp.getPath();
			File dest = new File(head.getFolder().getPath(), tempName);
			dest.getParentFile().mkdirs();
			try {
				Files.copy(temp.toPath(), dest.toPath(),
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			head.tracking.put(tempName, dest);
		}

		// After committing we:
		// reset the tracking-list to reflect the current head's tracking-list
		// reset unTracking list
		// delete the old stagingArea
		// recreate a new empty stagingArea
		resetTempTracking();
		resetUnTracking();
		staged = new HashMap<String, File>();
		purgeDirectory(stagingArea);
		mergeConflict = false;
	}

	/**
	 * This method checks to see if there is a conflict with the merge method.
	 * 
	 * @param argument
	 *            String, the Gitlet command.
	 */

	private boolean checkIfMergeConflict(String argument) {
		HashSet<String> arg = new HashSet<String>();
		arg.add("add");
		arg.add("rm");
		arg.add("commit");
		arg.add("checkout");
		arg.add("log");
		arg.add("global-log");
		arg.add("find");
		arg.add("status");
		if (mergeConflict) {
			return !arg.contains(argument);
		} else {
			return false;
		}
	}

	/**
	 * The main method takes user input through the terminal and then calls each
	 * of the corresponding method.
	 */

	public static void main(String... args) {
		Gitlet a = serialRead();
		if (a.checkIfMergeConflict(args[0])) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		switch (args[0]) {
		case "init":
			a.init();
			serialWrite(a);
			break;
		case "add":
			a.add(args[1]);
			serialWrite(a);
			break;
		case "rm":
			a.rm(args[1]);
			serialWrite(a);
			break;
		case "commit":
			a.commit(args[1]);
			serialWrite(a);
			break;
		case "reset":
			a.reset(Integer.parseInt(args[1]));
			serialWrite(a);
			break;
		case "log":
			a.log();
			serialWrite(a);
			break;
		case "globallog":
			a.globalLog();
			serialWrite(a);
			break;
		case "find":
			a.find(args[1]);
			serialWrite(a);
			break;
		case "status":
			a.status();
			serialWrite(a);
			break;
		case "branch":
			a.branch(args[1]);
			serialWrite(a);
			break;
		case "rm-branch":
			a.rm_branch(args[1]);
			serialWrite(a);
			break;
		case "checkout":
			if (args.length == 3) {
				a.checkout(Integer.parseInt(args[1]), args[2]);
			} else {
				a.checkout(args[1]);
			}
			serialWrite(a);
			break;
		case "rebase":
			a.rebase(args[1]);
			serialWrite(a);
			break;
		case "merge":
			a.merge(args[1]);
			serialWrite(a);
			break;
		case "armani":
			System.out.println(a.branches.get(args[1]));
		}
	}

	// /////////////////////////////////////////////////////////////
	/************************ Serial Code ************************/
	// /////////////////////////////////////////////////////////////

	/**
	 * The method serialRead() makes a new instance of Gitlet if it didn't exist
	 * in the current directory. If it did, the method reads Gitlet file that
	 * was serialized previously.
	 */

	public static Gitlet serialRead() {

		File loadFile = new File(GITLET_DIR + "Gitlet.ser");
		if (!loadFile.exists()) {
			return new Gitlet();
		}
		Gitlet num = null;
		try {
			ObjectInput input = new ObjectInputStream(new FileInputStream(
					loadFile));
			num = (Gitlet) input.readObject();
		} catch (IOException e) {
			System.err.printf("Error: %s\n", e.toString());
		} catch (ClassNotFoundException e2) {
			System.err.printf("Error: %s\n", e2.toString());
		}

		return num;
	}

	/**
	 * Serializes HOLDER, an instance of a Gitlet, saving the file to the
	 * current directory.
	 */

	public static void serialWrite(Gitlet holder) {
		try {
			File saveFile = new File(GITLET_DIR + "Gitlet.ser");
			ObjectOutput output = new ObjectOutputStream(new FileOutputStream(
					saveFile));

			output.writeObject(holder);
		} catch (IOException e) {
			System.err.printf("Error: %s\n", e.toString());
		}

	}
}