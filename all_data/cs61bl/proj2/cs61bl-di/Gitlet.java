import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

class Gitlet implements Serializable {
	private Commit head; // The head. What's in our working directory
	private int commitCounter;
	private boolean isConflicted;
	private String currentBranch;

	private HashMap<String, Commit> branches; // name -> commit mapping
	private HashMap<Integer, Commit> commits; // commidID -> commit mapping
	private HashMap<String, LinkedList<Commit>> findCommitMessages;
	private HashSet<String> toUntrack;

	private LinkedList<String> toInheritFromTarget; // for merges
	private Commit mergeTarget; // for merges

	/* Constructor for Gitlet */
	private Gitlet() {
		branches = new HashMap<String, Commit>();
		commits = new HashMap<Integer, Commit>();
		toUntrack = new HashSet<String>();
		findCommitMessages = new HashMap<String, LinkedList<Commit>>();
		toInheritFromTarget = new LinkedList<String>();
		commitCounter = 0;
		isConflicted = false;
	}

	/**
	 * Sets up the directories for Gitlet.
	 */
	public void cmdInit() {
		String[] dirsToCreate = { ".gitlet", ".gitlet/commits", ".gitlet/stage" };
		for (String p : dirsToCreate) {
			new File(p).mkdir();
		}
	}

	public static boolean doesGitletExist() {
		Path checkPath = Paths.get(".gitlet");
		if (Files.exists(checkPath)) {
			return true;
		}
		return false;
	}

	/**
	 * Given a source and a destination, attempts to copy the src to the dest.
	 * 
	 * @param sourcePath
	 *            The file that we're trying to copy
	 * @param destPath
	 *            The path of the destination
	 */
	public static void copyFile(String sourcePath, String destPath) {
		// Makes sure that all intermediate directories exist.
		File sourceFile = new File(sourcePath);
		File destFile = new File(destPath);

		// delete old destFile
		File f = new File(destPath);
		if (f.exists()) {
			f.delete();
		}

		if (destFile.getParentFile() != null) {
			Commit.buildPath(destFile.getParentFile().toString());
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(
				sourceFile))) {
			FileWriter fw = new FileWriter(destFile, true);
			String curr;
			while ((curr = reader.readLine()) != null) {
				fw.write(curr);
			}
			fw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Prints out the id of the commit that has the given commit message. If
	 * there are multiple such commits, it prints the ids out on separate lines.
	 * 
	 * @param message
	 *            The message of the Commits that we're searching for.
	 */
	public void cmdFind(String message) {
		if (findCommitMessages.containsKey(message)) {
			for (Commit e : findCommitMessages.get(message)) {
				System.out.println(e.getID());
			}
		} else {
			System.out.println("Found no commit with that message");
			System.exit(1);
		}
	}

	/**
	 * Given a file, takes the version of the file pointed to by head, and
	 * checks it out into the working directory.
	 * 
	 * @param fileName
	 *            Our target file.
	 */
	public void cmdCheckout1(String fileName) {
		// we own it OR we inherit it
		if (head.getMyFiles().containsKey(fileName)) {
			String fileSourcePath = ".gitlet/commits/commit" + head.getID()
					+ "/" + fileName;
			Path checkPath = Paths.get(fileSourcePath);
			if (Files.exists(checkPath)) {
				copyFile(fileSourcePath, fileName);
			} else {
				String actualSource = head.getFileSource(fileName);
				copyFile(".gitlet/commits/" + actualSource + "/" + fileName,
						fileName);
			}
		} else {
			System.out
					.println("File does not exist in the most recent commit, or no such branch exists.");
			System.exit(1);
		}
	}

	/**
	 * Removes the given branch from the branches HashMap.
	 * 
	 * @param branchName
	 *            The name of the target branch.
	 */
	public void cmdRemoveBranch(String branchName) {
		if (isConflicted) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			System.exit(1);
		} else if (branchName.equals(currentBranch)) {
			System.out.println("Cannot remove the current branch.");
			System.exit(1);
		} else if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			System.exit(1);
		} else {
			branches.remove(branchName);
		}
	}

	/**
	 * Given a target commit, returns the commitID path to it from the root.
	 * 
	 * @param initialCommit
	 *            The commit we're trying to build a path to.
	 * @return The path from Commit0 to to the given commit. Returned as a
	 *         LinkedList of integers.
	 */
	public static LinkedList<Integer> buildAncestorPath(Commit initialCommit) {
		LinkedList<Integer> commitPath = new LinkedList<Integer>();
		Stack<Integer> commitStack = new Stack<Integer>();

		Commit curr = initialCommit;
		do {
			commitStack.push(curr.getID());
			curr = curr.getParentCommits().get(0);
		} while (!curr.getParentCommits().isEmpty());

		// add c0
		commitStack.push(curr.getID());

		while (!commitStack.isEmpty()) {
			commitPath.add(commitStack.pop());
		}

		return commitPath;
	}

	/**
	 * Given two commits, returns the most recent ancestor of the two.
	 * 
	 * @param c1
	 *            One of the commits.
	 * @param c2
	 *            The other commit.
	 * @return Returns the ID most recent commit ancestor that the two share.
	 */
	public int findAncestor(Commit c1, Commit c2) {

		Iterator<Integer> c1Iter = buildAncestorPath(c1).iterator();
		Iterator<Integer> c2Iter = buildAncestorPath(c2).iterator();

		// keep track of the last "match"
		int prev = 0;

		int c1Curr = 0;
		int c2Curr = 0;

		while (c1Iter.hasNext()) {
			c1Curr = c1Iter.next();
			c2Curr = c2Iter.next();

			if (c1Curr != c2Curr) {
				return prev;
			} else {
				prev = c1Curr;
			}
		}

		return prev;
	}

	/**
	 * Exclusively used for rebase. Similar to findSplit. Returns an ArrayList
	 * of IDs representing the commits that the rebase should "copy."
	 * 
	 * @param current
	 *            The current head.
	 * @param target
	 *            The target branch for the rebase.
	 * @return An ArrayList of ints representing the path.
	 */
	public static ArrayList<Integer> pathSinceSplit(Commit current,
			Commit target) {

		Iterator<Integer> currentIter = buildAncestorPath(current).iterator();
		Iterator<Integer> targetIter = buildAncestorPath(target).iterator();

		int prev = 0;
		int currentID;
		int targetID;

		do {
			currentID = currentIter.next();
			targetID = targetIter.next();

			if (currentID != targetID) {
				ArrayList<Integer> rtn = new ArrayList<Integer>();
				rtn.add(currentID);

				while (currentIter.hasNext()) {
					rtn.add(currentIter.next());
				}

				return rtn;
			} else {
				prev = currentID;
			}
		} while (currentIter.hasNext());

		return null;
	}

	/**
	 * Returns the path of the file according to Commit C. If the file is
	 * inherited, the file will be "owned" by a different commit.
	 * 
	 * @param c
	 *            Target commit.
	 * @param fileName
	 *            Target file.
	 * @return A String representing the actual path of the file (where it's
	 *         stored).
	 */
	public static String generateActualFilePath(Commit c, String fileName) {

		Set<String> files = c.getMyFiles().keySet();
		if (!files.contains(fileName)) {
			return null;
		}
		return ".gitlet/commits/" + c.fileSource(fileName) + "/" + fileName;
	}

	/**
	 * Used as a helper function. Are the contents of the two files exactly the
	 * same?
	 * 
	 * @param f1
	 *            The first file.
	 * @param f2
	 *            The second file
	 * @return Whether or not the two files are identical.
	 */
	public static boolean areFilesIdentical(File f1, File f2) {
		// Makes sure that all intermediate directories exist.
		BufferedReader f1Reader;
		BufferedReader f2Reader;

		try {
			f1Reader = new BufferedReader(new FileReader(f1));
			f2Reader = new BufferedReader(new FileReader(f2));

			String f1Curr;
			String f2Curr;
			int nulls;
			boolean toRtn = false;

			while (true) {
				f1Curr = f1Reader.readLine();
				f2Curr = f2Reader.readLine();
				nulls = 0;

				if (f1Curr == null) {
					nulls += 1;
				}
				if (f2Curr == null) {
					nulls += 1;
				}

				if (nulls == 2) {
					toRtn = true;
					break;
				} else if (nulls == 1) {
					break;
				} else if (!f1Curr.equals(f2Curr)) {
					break;
				}
			}
			f1Reader.close();
			f2Reader.close();
			return toRtn;

		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
			System.exit(1);
		}
		return false;
	}

	/**
	 * Merges files from the given branch into the current branch.
	 * 
	 * @param branchName
	 *            The target branch. What we're trying to merge with.
	 */
	public void cmdMerge(String branchName) {
		if (isConflicted) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			System.exit(1);
		} else if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			System.exit(1);
		} else if (branchName.equals(currentBranch)) {
			System.out.println("Cannot merge a branch with itself.");
			System.exit(1);
		}

		// make sure it's cleared.
		toInheritFromTarget = new LinkedList<String>();

		Commit currentCommit = branches.get(currentBranch);
		Commit targetCommit = branches.get(branchName);
		Commit splitPoint = commits.get(findAncestor(currentCommit,
				targetCommit));

		Set<String> currentCommitFiles = currentCommit.getMyFiles().keySet();
		Set<String> targetCommitFiles = targetCommit.getMyFiles().keySet();
		Set<String> splitPointFiles = splitPoint.getMyFiles().keySet();

		Set<String> overlap = new HashSet<String>(currentCommitFiles);
		overlap.retainAll(targetCommitFiles);

		Set<String> targetLeftovers = new HashSet<String>(targetCommitFiles);
		targetLeftovers.removeAll(overlap);

		File f1Path;
		File f2Path;
		File splitPath;
		boolean conflict = false;

		for (String fileName : overlap) {
			f1Path = new File(generateActualFilePath(currentCommit, fileName));
			f2Path = new File(generateActualFilePath(targetCommit, fileName));
			if (!areFilesIdentical(f1Path, f2Path)) {
				if (splitPointFiles.contains(fileName)) {
					splitPath = new File(generateActualFilePath(splitPoint,
							fileName));
					if (areFilesIdentical(f1Path, splitPath)) {
						toInheritFromTarget.add(generateActualFilePath(
								targetCommit, fileName));
						cmdCheckout2(targetCommit.getID(), fileName);
						// cmdAdd(fileName);
					} else if (areFilesIdentical(f2Path, splitPath)) {
						// do nothing
					} else {
						// create conflict
						Commit.createConflictedFile(fileName,
								".gitlet/commits/commit" + targetCommit.getID());
						conflict = true;
					}
				} else {
					// create conflict
					Commit.createConflictedFile(fileName,
							".gitlet/commits/commit" + targetCommit.getID());
					conflict = true;
				}
			}
		}

		for (String fileName : targetLeftovers) {
			cmdCheckout2(targetCommit.getID(), fileName);
			toInheritFromTarget.add(generateActualFilePath(targetCommit,
					fileName));
		}

		if (conflict) {
			System.out.println("Encountered a merge conflict.");
			mergeTarget = branches.get(branchName);
			isConflicted = true;
		} else {
			String msg = "Merged " + currentBranch + " with " + branchName
					+ ".";
			mergeTarget = branches.get(branchName);
			cmdCommit(msg);
		}
	}

	/**
	 * Used as a helper for merge. Helps us decide if the merge should be
	 * allowed, or if it should trigger a failure case or just move the head.
	 * 
	 * @param c1ID
	 *            The ID of c1
	 * @param c2
	 *            Commit 2
	 * @return Returns whether c1 is in c2's history.
	 */
	public static boolean branchIsInHistory(int c1ID, Commit c2) {
		Iterator<Integer> pathIter = buildAncestorPath(c2).iterator();

		while (pathIter.hasNext()) {
			if (pathIter.next() == c1ID) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Conceptually, what rebase does is find the split point of the current
	 * branch and the given branch, then snaps off the current branch at this
	 * point, then reattaches the current branch to the head of the given
	 * branch.
	 * 
	 * @param branchName
	 *            The target branch. What we're trying to rebase to.
	 */
	public void cmdRebase(String branchName) {

		if (isConflicted) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			System.exit(1);
		} else if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			System.exit(1);
		} else if (branchName.equals(currentBranch)) {
			System.out.println("Cannot rebase a branch onto itself.");
			System.exit(1);
		} else if (branchIsInHistory(branches.get(branchName).getID(),
				branches.get(currentBranch))) {
			System.out.println("Already up-to-date.");
			System.exit(1);
		}
		if (branchIsInHistory(branches.get(currentBranch).getID(),
				branches.get(branchName))) {
			branches.put(currentBranch, branches.get(branchName));
			return;
		}
		ArrayList<Integer> toAdd = pathSinceSplit(branches.get(currentBranch),
				branches.get(branchName));
		Iterator<Integer> toAddIter = toAdd.iterator();
		Commit currOriginal, curr = null;
		Commit tail = branches.get(branchName);
		Commit splitPoint = commits.get(findAncestor(
				branches.get(currentBranch), tail));
		Commit currParent = branches.get(branchName);
		Set<String> splitPointFiles = splitPoint.getMyFiles().keySet();
		Set<String> tailFiles = tail.getMyFiles().keySet();
		Set<String> currOriginalFiles, allFiles;
		Set<String> blacklist = new HashSet<String>();
		while (toAddIter.hasNext()) {
			currOriginal = commits.get(toAddIter.next());
			currOriginalFiles = currOriginal.getMyFiles().keySet();
			curr = new Commit(currOriginal.getCommitMsg(), commitCounter++);
			currOriginalFiles = currOriginal.getMyFiles().keySet();
			allFiles = new HashSet<String>(tailFiles);
			allFiles.addAll(currOriginalFiles);
			for (String fileName : allFiles) {
				boolean inCurrOrig = currOriginalFiles.contains(fileName);
				boolean inTail = tailFiles.contains(fileName);
				if (inCurrOrig && inTail) {
					if (blacklist.contains(fileName))
						curr.addFile(generateActualFilePath(currOriginal,
								fileName));
					else {
						if (!splitPointFiles.contains(fileName)) {
							blacklist.add(fileName);
							curr.addFile(generateActualFilePath(currOriginal,
									fileName));
						} else {
							File currOriginalFile = new File(
									generateActualFilePath(currOriginal,
											fileName));
							File splitPointFile = new File(
									generateActualFilePath(splitPoint, fileName));
							if (!areFilesIdentical(currOriginalFile,
									splitPointFile)) {
								blacklist.add(fileName);
								curr.addFile(generateActualFilePath(
										currOriginal, fileName));
							} else
								curr.addFile(generateActualFilePath(tail,
										fileName));
						}
					}
				} else if (inCurrOrig)
					curr.addFile(generateActualFilePath(currOriginal, fileName));
				else
					curr.addFile(generateActualFilePath(tail, fileName));

			}
			curr.addParent(currParent);
			currParent.addChild(curr);
			currParent = curr;
		}
		branches.put(currentBranch, curr);
		head = curr;
	}

	/**
	 * Given a commit ID and a fileName, pulls commit<cID>'s version of fileName
	 * into the working directory.
	 * 
	 * @param cID
	 *            The ID of the commit we're trying to pull the file from
	 * @param fileName
	 *            The name of the file we're trying to pull
	 */
	public void cmdCheckout2(int cID, String fileName) {
		if (!commits.containsKey(cID)) {
			System.out.println("No commit with that id exists.");
			System.exit(1);
		}

		Commit target = commits.get(cID);

		if (!target.getMyFiles().containsKey(fileName)) {
			System.out.println("File does not exist in that commit.");
			System.exit(1);
		}

		Commit oldHead = head;
		head = target;
		cmdCheckout1(fileName);
		head = oldHead;
	}

	/**
	 * Given a branch name, finds the Commit that the branch points to, and
	 * checks out all of the files that said Commit is tracking.
	 * 
	 * @param branchName
	 *            The branch we're checking out to
	 */
	public void cmdCheckout3(String branchName) {
		if (isConflicted) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			System.exit(1);
		} else if (!branches.containsKey(branchName)) {
			System.out
					.println("File does not exist in the most recent commit, or no such branch exists.");
			System.exit(1);
		} else if (branchName.equals(currentBranch)) {
			System.out.println("No need to checkout the current branch.");
			System.exit(1);
		}

		// switch head over
		head = branches.get(branchName);

		Set<String> filesToCheck = branches.get(branchName).getMyFiles()
				.keySet();

		for (String file : filesToCheck) {
			cmdCheckout1(file);
		}

		currentBranch = branchName;
	}

	/**
	 * The special case for cmdCommit. Called by cmdMerge, or by cmdCommit after
	 * being put into the "merge conflicted" state. Creates a new Commit that
	 * inherits from the current head and the merge target.
	 * 
	 * @param message
	 *            The commit message, duh
	 */
	public void mergeCommit(String message) {
		Commit newCommit = new Commit(message, commitCounter++, head);

		// add to the commits HashMap [id -> commit] mapping
		commits.put(newCommit.getID(), newCommit);

		// insert into findCommitMessages. deal with the two cases
		if (findCommitMessages.get(message) == null) {
			LinkedList<Commit> entry = new LinkedList<Commit>();
			entry.add(newCommit);
			findCommitMessages.put(message, entry);
		} else {
			findCommitMessages.get(message).add(newCommit);
		}

		// newCommit now contains a copy of head's myFiles.
		newCommit.inheritFiles(head);

		for (String file : toInheritFromTarget) {
			newCommit.addFile(file);
		}

		// add self as a child of head and mergeTarget
		head.addChild(newCommit);
		mergeTarget.addChild(newCommit);

		// update the head
		head = newCommit;

		// update currentBranch
		branches.put(currentBranch, head);

		// clear conflicts
		isConflicted = false;

		// clear mergeTarget
		mergeTarget = null;
	}

	/**
	 * Calls the correct commit method, depending on the state.
	 * 
	 * @param message
	 *            The Commit's commitMessage.
	 */
	public void cmdCommit(String message) {
		if (!toInheritFromTarget.isEmpty()) {
			mergeCommit(message);
		} else {
			normalCommit(message);
		}
	}

	/**
	 * Does a normal commit. Doesn't work when we're in a conflicted state.
	 * Inherits files from head, adds staged files, and untracks the files in
	 * toUntrack.
	 * 
	 * @param message
	 *            The commit's message!
	 */
	public void normalCommit(String message) {
		// bring all stagedFiles into LinkedList
		File stageDirectory = new File(".gitlet/stage");
		LinkedList<File> stagedFiles = new LinkedList<File>();
		collectFiles(stageDirectory.toString(), stagedFiles);

		if (stagedFiles.isEmpty() && toUntrack.isEmpty()) {
			System.out.println("No changes added to the commit.");
			System.exit(1);
		}

		// If the message is just whitespace, abort.
		if (message.trim().length() == 0) {
			System.out.println("Please enter a commit message.");
			System.exit(1);
		}

		Commit newCommit = new Commit(message, commitCounter++, head);

		// add to the commits HashMap [id -> commit] mapping
		commits.put(newCommit.getID(), newCommit);

		// insert into findCommitMessages. deal with the two cases
		if (findCommitMessages.get(message) == null) {
			LinkedList<Commit> entry = new LinkedList<Commit>();
			entry.add(newCommit);
			findCommitMessages.put(message, entry);
		} else {
			findCommitMessages.get(message).add(newCommit);
		}

		// newCommit now contains a copy of head's myFiles.
		newCommit.inheritFiles(head);

		Set<String> toIterate = head.myFileNames();

		for (String fileName : toIterate) {
			if (toUntrack.contains(fileName)) {
				newCommit.removeFile(fileName);
			}

		}

		// Save stagedFiles to .gitlet/commits/commit<commitID>/
		for (File f : stagedFiles) {
			newCommit.saveFileToCommit(f.toString());
		}

		// empty out the stage directory
		deleteDirectory(stageDirectory);
		stageDirectory.mkdir();

		// empty out toUntrack
		toUntrack = new HashSet<String>();

		// add self as a child of head
		head.addChild(newCommit);

		// update the head
		head = newCommit;

		// update currentBranch
		branches.put(currentBranch, head);

		// clear conflicts
		isConflicted = false;
	}

	/**
	 * Adds a copy of the file as it currently exists to the staging area. If
	 * the file had been marked for untracking, then add just unmarks the file,
	 * and does not also add it to the staging area.
	 * 
	 * @param toAdd
	 *            The name of the file that we're trying to stage.
	 */
	public void cmdAdd(String toAdd) {

		// Check if the file exists in the working directory.
		Path checkPath = Paths.get(toAdd);
		if (!Files.exists(checkPath)) {
			System.out.println("File does not exist.");
			System.exit(1);
		}

		if (toUntrack.contains(toAdd)) {
			toUntrack.remove(toAdd);
		} else {
			Commit.saveFile(toAdd, ".gitlet/stage/");
		}
	}

	/**
	 * Helper function for remove. Helps us figure out if
	 * .gitlet/stage/fileToRemove exists.
	 * 
	 * @param fileToRemove
	 *            Our target file
	 * @return Whether or not file is in .gitlet/stage
	 */
	public boolean isFileStaged(String fileToRemove) {
		Path checkPath = Paths.get(".gitlet/stage/" + fileToRemove);
		if (Files.exists(checkPath)) {
			return true;
		}
		return false;
	}

	/**
	 * For testing. Helped a lot.
	 */
	public void printCommitTree() {
		commits.get(0).printCommitTree(0);
	}

	/**
	 * Mark the file for untracking; this means it will not be included in the
	 * upcoming commit, even if it was tracked by that commit's parent. If the
	 * file had been staged, instead just unstage it, and don't also mark it for
	 * untracking.
	 * 
	 * @param toRemove
	 *            The name of the file that we're trying to stage.
	 */
	public void cmdRemove(String fileToRemove) {

		boolean staged = isFileStaged(fileToRemove);
		boolean tracked = head.getMyFiles().keySet().contains(fileToRemove);

		if (staged) {
			String targetPath = ".gitlet/stage/" + fileToRemove;
			File target = new File(targetPath);
			target.delete();

		} else {
			if (tracked) {
				toUntrack.add(fileToRemove);
			} else {
				System.out.println("No reason to remove the file.");
				System.exit(1);
			}
		}
	}

	public String cmdGlobalLog() {
		String rtn = "";
		Set<Integer> ks = commits.keySet();
		for (int c : ks) {
			rtn += commits.get(c).toString();
			rtn += "\n";
		}

		return rtn.substring(0, rtn.length() - 1);
	}

	/**
	 * From:
	 * http://stackoverflow.com/questions/3775694/deleting-folder-from-java
	 * Helps with testing.
	 * 
	 * @param directory
	 *            The target directory
	 * @return Whether the directory was successfully deleted
	 */
	public static boolean deleteDirectory(File directory) {
		if (directory.exists()) {
			File[] files = directory.listFiles();
			if (null != files) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory()) {
						deleteDirectory(files[i]);
					} else {
						files[i].delete();
					}
				}
			}
		}
		return (directory.delete());
	}

	/**
	 * Used in every call to Gitlet. Retrieves the serialized Gitlet.
	 * 
	 * @return The gitlet instance stored in .gitlet/gitlet.ser, or null if it
	 *         doesn't exist.
	 */
	public static Gitlet retrieveGitlet() {
		String path = ".gitlet/gitlet.ser";
		Path checkPath = Paths.get(path);

		FileInputStream fileIn = null;
		ObjectInputStream objectIn = null;
		Gitlet gitlet = null;

		// If path DNE, Gitlet hasn't been initialized yet. Return null.
		if (!Files.exists(checkPath)) {
			return null;
		} else {
			try {
				fileIn = new FileInputStream(path);
				objectIn = new ObjectInputStream(fileIn);
				gitlet = (Gitlet) objectIn.readObject();
				objectIn.close();
			} catch (IOException e) {
				return null;
			} catch (ClassNotFoundException e) {
				return null;
			}
		}
		return gitlet;
	}

	/**
	 * Checks out all the files tracked by the given commit. Also moves the
	 * current branch's head to that commit node. See the intro for an example
	 * of what happens to the head pointer after using reset.
	 * 
	 * @param commitID
	 *            The target Commit that we're trying to reset to.
	 */
	public void cmdReset(int commitID) {
		if (isConflicted) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			System.exit(1);
		} else if (!commits.containsKey(commitID)) {
			System.out.println("No commit with that id exists.");
			System.exit(1);
		} else {
			for (String fileName : commits.get(commitID).getMyFiles().keySet()) {
				cmdCheckout2(commitID, fileName);
			}
			// is this right?
			head = commits.get(commitID);
			branches.put(currentBranch, commits.get(commitID));
		}
	}

	/**
	 * Recursively searches through target directory, adding all found files
	 * into the filesLL LinkedList.
	 * 
	 * @param dir
	 *            The directory that we're searching in.
	 * @param filesLL
	 *            The list that we're inserting all found files into
	 */
	public static void collectFiles(String dir, LinkedList<File> filesLL) {
		LinkedList<Files> rtn = new LinkedList<Files>();

		File currDir = new File(dir);
		File[] files = currDir.listFiles();

		for (File f : files) {
			if (f.isDirectory()) {
				collectFiles(f.toString(), filesLL);
			} else {
				filesLL.add(f);
			}
		}
	}

	/**
	 * Writes the instance of Gitlet to .gitlet/gitlet.ser.
	 */
	public void serialize() {
		try {
			FileOutputStream fileOut = new FileOutputStream(
					".gitlet/gitlet.ser");
			ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
			outStream.writeObject(this);
			outStream.close();
			fileOut.close();
		} catch (IOException i) {
			System.out.println("Error in serialization of gitlet!");
			System.out.println(head);
			i.printStackTrace();
		}
	}

	/**
	 * Displays what branches currently exist, and marks the current branch with
	 * a *. Also displays what files have been staged or marked for untracking.
	 * 
	 */
	public String cmdStatus() {
		String rtn = "";

		rtn += "=== Branches ===";
		Set<String> branchNames = branches.keySet();
		for (String currBranch : branchNames) {
			rtn += "\n";
			if (currBranch.equals(currentBranch)) {
				rtn += "*";
			}
			rtn += currBranch;
		}

		rtn += "\n\n=== Staged Files ===";
		LinkedList<File> stagedFiles = new LinkedList<File>();
		collectFiles(".gitlet/stage", stagedFiles);
		if (stagedFiles.isEmpty()) {
			rtn += "\nNo branches or commits exist.";
		} else {
			for (File stagedFile : stagedFiles) {
				rtn += "\n";
				rtn += stagedFile.toString().split(".gitlet/stage/")[1];
			}
		}

		rtn += "\n\n=== Files Marked for Untracking ===";
		if (toUntrack.isEmpty()) {
			rtn += "\nNo files marked for untracking.";
		} else {
			for (String fileName : toUntrack) {
				rtn += "\n";
				rtn += fileName;
			}
		}

		rtn += "\n";
		return rtn;
	}

	/**
	 * Creates a new branch and adds it the the branches HashMap.
	 * 
	 * @param branchName
	 *            Name of the new branch.
	 */
	public void cmdBranch(String branchName) {
		if (isConflicted) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			System.exit(1);
		} else if (branches.containsKey(branchName)) {
			System.out.println("A branch with that name already exists.");
			System.exit(1);
		}

		branches.put(branchName, head);
	}

	/**
	 * Called once per repository session. Makes the initial commit and creates
	 * a branch called master. Head points to master after this is done.
	 */
	public void makeInitialCommit() {
		Commit c = new Commit("initial commit", commitCounter++);
		branches.put("master", c);
		head = c;
		currentBranch = "master";
		commits.put(0, c);
	}

	public static void main(String[] args) {

		if (emptyArgsList(args))
			return;

		// Try to retrieve the serialized instance from .gitlet/gitlet.ser
		Gitlet gitlet = retrieveGitlet();

		// Grab the first argument. It should be a valid command.
		String cmd = args[0];

		switch (cmd) {
		case ("init"):
			if (doesGitletExist()) {
				System.out
						.println("A gitlet version control system already exists in the current directory.");
			} else {
				gitlet = new Gitlet();
				gitlet.cmdInit();
				gitlet.makeInitialCommit();
			}
			break;

		case ("add"):
			gitlet.cmdAdd(args[1]);
			break;

		case ("rm"):
			gitlet.cmdRemove(args[1]);
			break;

		case ("commit"):
			if (args.length != 2) {
				throw new IllegalArgumentException(
						"Error: argument count is incorrect.");
			}
			gitlet.cmdCommit(args[1]);

			break;

		case ("global-log"):
			String globalLogString = gitlet.cmdGlobalLog();
			System.out.print(globalLogString);
			break;

		case ("merge"):
			gitlet.cmdMerge(args[1]);
			break;

		case ("rebase"):
			gitlet.cmdRebase(args[1]);
			break;

		case ("branch"):
			gitlet.cmdBranch(args[1]);
			break;

		case ("checkout"):
			if (args.length == 2) {
				if (gitlet.branches.containsKey(args[1])) {
					gitlet.cmdCheckout3(args[1]);
				} else {
					gitlet.cmdCheckout1(args[1]);
				}
			} else {
				gitlet.cmdCheckout2(Integer.parseInt(args[1]), args[2]);
			}
			break;

		case ("wipe"):
			deleteDirectory(new File(".gitlet"));
			gitlet = null;
			System.exit(1);
			break;

		case ("log"):
			String logString = gitlet.head.log();
			System.out.print(logString);
			break;

		case ("myFiles"):
			gitlet.head.printMyFilesInfo();
			break;

		case ("status"):
			String statusString = gitlet.cmdStatus();
			System.out.println(statusString);
			break;

		case ("rm-branch"):
			gitlet.cmdRemoveBranch(args[1]);
			break;

		case ("reset"):
			gitlet.cmdReset(Integer.parseInt(args[1]));
			break;

		case ("tree"):
			gitlet.printCommitTree();
			break;

		case ("cc"):
			Commit currCommit = gitlet.branches.get(gitlet.currentBranch);
			System.out
					.println("Currently on Commit" + currCommit.getID() + ".");
			break;

		case ("numbranches"):
			System.out.println(gitlet.branches.size());
			break;

		case ("find"):
			gitlet.cmdFind(args[1]);
			break;

		default:
			// System.out.println("Command not recognized!");
			break;
		}

		if (doesGitletExist()) {
			gitlet.serialize();
		}
	}

	public static boolean emptyArgsList(String[] args) {
		return args.length == 0;
	}
}
