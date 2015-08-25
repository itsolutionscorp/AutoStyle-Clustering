import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.nio.file.*;
import java.nio.file.Paths;

/**
 * UCB CS61B Project 02 This class creates a version control system that is
 * similar to git.
 * 
 * @author Sam: EF, Frances: BQ, Alex: BR, Chris: EZ
 *
 */
public class Gitlet implements Serializable {

	// hash map that stores the messages
	HashMap<String, ArrayList<Integer>> messageMap = new HashMap<String, ArrayList<Integer>>();

	// hash map that stores the commits
	HashMap<Integer, Commit> commitMap = new HashMap<Integer, Commit>();

	// boolean to determine if system is in a conflicted state
	boolean conflictedState = false;

	// total number of commits
	int totalCommitCount;

	// the current commit id
	int currentCommitId;

	// the commit tree
	CommitTree myCommitTree;

	// hash map of that catalogs the commits
	HashMap<String, Commit> pointers = new HashMap<String, Commit>();

	// the current pointer name
	String currentPointerName;

	// hash map for the branches. Stores the head commit associated
	// with each branch
	HashMap<String, Commit> branches = new HashMap<String, Commit>();

	// the current/selected branch
	String currentBranch;

	// hashmap that contains the commit at the split point of two branches
	HashMap<String, Commit> splitPoints = new HashMap<String, Commit>();

	/**
	 * Creates a new gitlet version control in the current directory. This
	 * method will:
	 * 
	 * (1) Create an initial commit folder with the string "initial commit"
	 * 
	 * (2) Throw an error message if a .gitlet folder is already initialized
	 * 
	 * @throw IllegalArgumentException if there is already a .gitlet folder
	 *        initialized in the directory
	 */
	public void init() {

		// create a checkFile to determine if the .gitlet folder exists
		File checkFile = new File("./.gitlet");

		// if the file already exists throw an illegal argument exception
		if (checkFile.exists()) {
			System.out
					.println("A gitlet version control system already exists in the current directory.");
		}

		// otherwise create the .gitlet folder and subfolders
		else {
			File gitletFolder = new File(System.getProperty("user.dir")
					+ "/.gitlet");
			File stagingFolder = new File(System.getProperty("user.dir")
					+ "/.gitlet/staging");

			// create the first commit with the appropriate string
			myCommitTree = new CommitTree("initial commit");

			// assign values to the appropriate commit variables
			totalCommitCount = 0;
			currentCommitId = 0;

			// add the first commit to the commit hash map
			commitMap.put(currentCommitId, myCommitTree.myRoot);
			branches.put("master", myCommitTree.myRoot);
			messageMap.put(myCommitTree.myRoot.getMessage(),
					new ArrayList<Integer>(currentCommitId));
			currentBranch = "master";

			// create the first commit folder
			File commitFolder = new File(System.getProperty("user.dir")
					+ "/.gitlet/commit" + currentCommitId);

			// officially make the directories...
			gitletFolder.mkdirs();
			stagingFolder.mkdirs();
			commitFolder.mkdirs();
		}
	}

	/**
	 * Get the unique part of the path for a commit with the specified path
	 * 
	 * @param path
	 *            - the entire path of a file IN A COMMIT FOLDER or STAGING
	 *            FOLDER
	 * @return String representation of the entire file name (deals with nested
	 *         files)
	 */
	public static String subpathCommit(Path path) {
		Path home = FileSystems.getDefault().getPath(
				System.getProperty("user.dir"));
		int index = home.getNameCount();
		index += 2;
		return path.subpath(index, path.getNameCount()).toString();
	}

	/**
	 * Get the unique part of the working directory path
	 * 
	 * @param path
	 *            - the full path to a file IN A WORKING FOLDER
	 * @return String representation of the entire file name (deals with nested
	 *         files)
	 */
	public static String subpathWorkingDirectory(Path path) {
		Path home = FileSystems.getDefault().getPath(
				System.getProperty("user.dir"));
		int index = home.getNameCount();
		return path.subpath(index, path.getNameCount()).toString();
	}

	/**
	 * Deletes an entire directory by making sure it is empty first.
	 * 
	 * Taken from
	 * http://stackoverflow.com/questions/20281835/how-to-delete-a-folder
	 * -with-files-using-java
	 * 
	 * @param file
	 *            - the file you want to delete
	 **/
	public void deleteDir(File file) {
		File[] contents = file.listFiles();
		if (contents != null) {
			for (File f : contents) {
				deleteDir(f);
			}
		}
		file.delete();
	}

	/**
	 * Print nested file names
	 * 
	 * @param file
	 *            - the (possibly nested) file you want to print.
	 */
	public void printDir(File file) {
		File[] contents = file.listFiles();
		if (contents != null) {
			for (File f : contents) {
				printDir(f);
			}
		}
		if (file.isFile()) {
			System.out.println(subpathCommit(file.toPath()));
		}
	}

	/**
	 * Copy a file from the given location to the target location.
	 * 
	 * @param sourceLocation
	 *            - the given location
	 * @param targetLocation
	 *            - the target location
	 * @throws IOException
	 *             if either of the locations do not exist
	 */
	public void copy(File sourceLocation, File targetLocation)
			throws IOException {
		if (sourceLocation.isDirectory()) {
			copyDirectory(sourceLocation, targetLocation);
		} else {
			targetLocation.delete();
			Files.copy(sourceLocation.toPath(), targetLocation.toPath());
		}
	}

	/**
	 * Copy the file's directory from the source file to the target file
	 * 
	 * @param source
	 *            - the source file
	 * @param target
	 *            - the target file
	 * @throws IOException
	 *             if the source or target file does not exist
	 */
	private void copyDirectory(File source, File target) throws IOException {
		if (!target.exists()) {
			target.mkdir();
		}

		for (String f : source.list()) {
			copy(new File(source, f), new File(target, f));
		}
	}

	/**
	 * Add the file from the specified commit to the tracked files hashmap
	 * 
	 * @param commit
	 *            - the commit at which the file is located
	 * @param f
	 *            - the file to be tracked
	 */
	public void trackFile(Commit commit, File f) {
		File[] files = f.listFiles();
		if (files != null) {
			for (File file : files) {
				trackFile(commit, file);
			}
		}
		if (f.isFile()) {
			commit.trackedFiles.put(subpathCommit(f.toPath()), f);
		}
	}

	/**
	 * Delete the specified file
	 * 
	 * @param file
	 *            - the file to be deleted
	 */
	public void deleteFile(File file) {
		if (file.getName().equals("staging")) {
			return;
		}
		Path p = file.toPath();
		file.delete();
		File parent = new File(p.getParent().toString());
		File[] children = parent.listFiles();
		if (children.length == 0) {
			deleteFile(parent);
		}
	}

	/**
	 * Adds a copy of the file to the staging area. If the file is marked for
	 * untracking, unmark the file and do not add it to the staging area.
	 * 
	 * @param fileName
	 *            - the file to be added to the staging area
	 * @throws IOException
	 *             if the file parameter does not exist]
	 **/
	public void add(File fileName) throws IllegalArgumentException {
		Commit currentCommit = branches.get(currentBranch);
		String sub = subpathWorkingDirectory(fileName.toPath());
		int conflict = sub.indexOf(".conflicted");
		if (currentCommit.untrackedFiles.containsKey(sub)) {
			currentCommit.untrackedFiles.remove(sub);
		}

		else {
			if (conflict != -1) {
				String withoutConflicted = sub.substring(0, conflict);
				sub = withoutConflicted;
			}
			try {
				Path subpath = Paths.get(sub);
				if (subpath.getNameCount() > 1) {
					File directories = new File(System.getProperty("user.dir")
							+ "/.gitlet/staging/"
							+ subpath.subpath(0, subpath.getNameCount() - 1)
									.toString());
					directories.mkdirs();
				}
				File toStagingFolder = new File(System.getProperty("user.dir")
						+ "/.gitlet/staging/" + sub);
				Files.copy(fileName.toPath(), toStagingFolder.toPath());

			} catch (IOException e) {
				throw new IllegalArgumentException("check ");
			} catch (NullPointerException e) {
				throw new NullPointerException("check null");
			}
		}
	}

	/**
	 * Helper method for the merge class. It copies files to the staging folder
	 * for the merge class.
	 * 
	 * @param fileName
	 *            - the file to be copied
	 * @throws IllegalArgumentException
	 *             if the fileName is null or does not exist
	 */
	public void addFromCommit(File fileName) throws IllegalArgumentException {

		Commit currentCommit = branches.get(currentBranch);
		String sub = subpathCommit(fileName.toPath());
		if (currentCommit.untrackedFiles.containsKey(sub)) {
			currentCommit.untrackedFiles.remove(sub);
		} else {
			try {
				Path subpath = Paths.get(sub);
				if (subpath.getNameCount() > 1) {
					File directories = new File(System.getProperty("user.dir")
							+ "/.gitlet/staging/"
							+ subpath.subpath(0, subpath.getNameCount() - 1)
									.toString());
					directories.mkdirs();
				}
				File toStagingFolder = new File(System.getProperty("user.dir")
						+ "/.gitlet/staging/" + sub);
				Files.copy(fileName.toPath(), toStagingFolder.toPath());

			} catch (IOException e) {
				throw new IllegalArgumentException();
			} catch (NullPointerException e) {
				throw new NullPointerException();
			}
		}
	}

	/**
	 * Saves a backup of files so that they can restored later. Essentially,
	 * commit is the same as the parent commits, but with added files that were
	 * staged at the time of the commit.
	 * 
	 * (1) Create a commit object with an ID, message, time, and files from the
	 * staging area and with a pointer to the previous commit.
	 * 
	 * (2) A commit will only update the version of a file if that file has been
	 * staged at the time of the commit. These new files will be tracked (added
	 * to the tree), and the staging area is cleared
	 * 
	 * (3) Move the pointer to the new commit object.
	 * 
	 * @param message
	 *            - the string message for the file to be committed.
	 * @runTime - constant time.
	 **/
	public void commit(String message) {
		Commit parentCommit = branches.get(currentBranch);
		totalCommitCount++;
		Commit thisCommit = new Commit(message, totalCommitCount, parentCommit);

		currentCommitId = thisCommit.getId();
		if (messageMap.get(message) == null) {
			messageMap.put(message, new ArrayList<Integer>());
			messageMap.get(message).add(currentCommitId);
		} else {
			messageMap.get(message).add(currentCommitId);
		}
		commitMap.put(currentCommitId, thisCommit);

		branches.put(currentBranch, thisCommit);

		File stagingFolder = new File(System.getProperty("user.dir")
				+ "/.gitlet/staging");
		File[] stagingFiles = stagingFolder.listFiles();
		File newCommitFolder = new File(System.getProperty("user.dir")
				+ "/.gitlet/commit" + currentCommitId);
		newCommitFolder.mkdirs();
		if (!parentCommit.trackedFiles.isEmpty()) {
			for (String p : parentCommit.trackedFiles.keySet()) {
				if (!parentCommit.untrackedFiles.containsKey(p)) {
					thisCommit.trackedFiles.put(p,
							parentCommit.trackedFiles.get(p));
				}
			}
		}
		if (stagingFiles != null) {
			for (File aFile : stagingFiles) {
				try {
					File f = new File(newCommitFolder.toPath() + "/"
							+ subpathCommit(aFile.toPath()));
					copy(aFile, f);
					deleteDir(aFile);
					// Add to tracked files, replacing previous version!
					trackFile(thisCommit, f);
				} catch (IOException e) {
				}
			}
		} else {
			System.out.println("No changes added to the commit.");
		}
	}

	/**
	 * Determines whether the given directory contains the given file
	 * 
	 * @param directory
	 *            - the directory
	 * @param f
	 *            - the file
	 * @return true if the directory contains the given file, false otherwise
	 */
	public boolean containsFile(File directory, File f) {
		return fileFinder(directory).contains(subpathCommit(f.toPath()));
	}

	/**
	 * If given a directory that contains files, add all the files in the
	 * directory to the array list of strings. Otherwise, if given a file, add
	 * it to the array list of strings.
	 * 
	 * @param directory
	 *            - the file argument that is added to the array list of strings
	 * @return the array list of strings that contains the file(s) in the file
	 *         parameter
	 */
	public ArrayList<String> fileFinder(File directory) {
		ArrayList<String> files = new ArrayList<String>();
		if (directory.isFile()) {
			files.add(subpathCommit(directory.toPath()));
		} else {
			File[] filesInside = directory.listFiles();
			for (File f : filesInside) {
				files.addAll(fileFinder(f));
			}
		}
		return files;
	}

	/**
	 * Mark the selected file for untracking. If the file has been staged simply
	 * unstage it.
	 * 
	 * @param fileName
	 *            - the name of the file to be untracked or unstaged
	 * @throws Exception
	 *             if deleting files is not a valid operation
	 **/
	public void rm(File fileName) throws Exception {
		File stagingFolder = new File(System.getProperty("user.dir")
				+ "/.gitlet/staging");
		Commit currCommit = branches.get(currentBranch);
		String key = subpathCommit(fileName.toPath());
		if (containsFile(stagingFolder, fileName)) {
			deleteFile(fileName);
		} else if (currCommit.trackedFiles.containsKey(key)
				&& !currCommit.untrackedFiles.containsKey(key)) {
			branches.get(currentBranch).untrackedFiles.put(key,
					new File(System.getProperty("user.dir") + "/.gitlet/commit"
							+ currCommit.getId() + "/" + key));
		} else {
			System.err.println("No reason to remove the file.");
		}
	}

	/**
	 * Starting at head pointer, iterate backwards and print out Commit ID,
	 * Date, Time, commit message in the appropriate format
	 **/
	public void log() {
		Commit currentCommit = branches.get(currentBranch);
		while (currentCommit.parent() != null) {
			currentCommit.log();
			currentCommit = currentCommit.parent();
		}
		currentCommit.log();
	}

	/**
	 * Prints out the entire log entry that displays every commit ever made in
	 * the appropriate format
	 **/
	public void globallog() {
		Commit rootCommit = commitMap.get(0);
		CommitTree t = new CommitTree(rootCommit);
		Iterator<Commit> iter = t.iterator();
		while (iter.hasNext()) {
			Commit kid = (Commit) iter.next();
			kid.log();
		}
	}

	/**
	 * Prints out the ID of the commit with the corresponding message. If there
	 * are multiple IDs with the same message, print out each ID on a separate
	 * line.
	 * 
	 * @param message
	 *            - the commit's message
	 **/
	public void find(String message) {
		ArrayList<Integer> commitIds = messageMap.get(message);
		if (commitIds == null) {
			System.out.println("Found no commit with that message.");
		} else {
			for (Integer i : commitIds) {
				System.out.println(i);
			}
		}
	}

	/**
	 * Displays which branches currently exist and marks the selected branch
	 * with a *. Also displays the files that have been staged or marked for
	 * untracking.
	 **/
	public void status() {
		System.out.println("=== Branches ===");
		Set<String> thePointers = branches.keySet();
		for (String s : thePointers) {
			if (s.equals(currentBranch)) {
				System.out.println("*" + s);
			} else {
				System.out.println(s);
			}
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		File stagingFolder = new File(System.getProperty("user.dir")
				+ "/.gitlet/staging");
		File[] stagingFiles = stagingFolder.listFiles();
		for (File aFile : stagingFiles) {
			printDir(aFile);
		}
		System.out.println();
		System.out.println("===Files Marked for Untracking ===");
		for (String s : branches.get(currentBranch).untrackedFiles.keySet()) {
			System.out.println(s);
		}
	}

	/**
	 * Takes the file in the commit with the specified commitID and copies it
	 * into the working directory, overwriting the previous version of that file
	 * if there is one.
	 * 
	 * @param commitId
	 *            - the commit id
	 * @param fileName
	 *            - the file in the commit to be copied
	 **/
	public void checkout(int commitId, File fileName) {
		if (!commitMap.containsKey(commitId)) {
			System.out.println("No commit with that id exists.");
		} else {
			try {
				Path source = FileSystems.getDefault().getPath(
						System.getProperty("user.dir") + "/.gitlet/commit"
								+ commitId, fileName.getName());
				Path destination = fileName.toPath();
				if (!Files.exists(source)) {
					String key = subpathWorkingDirectory(fileName.toPath());
					Commit currCommit = commitMap.get(commitId);
					if (currCommit.trackedFiles.containsKey(key)) {
						Files.deleteIfExists(destination);
						Files.copy(currCommit.trackedFiles.get(key).toPath(),
								destination);
					} else {
						System.out
								.println("File does not exist in that commit.");
					}
				} else {
					Files.deleteIfExists(destination);
					Files.copy(source, destination);
				}
			} catch (IOException e) {
			}
		}
	}

	/**
	 * Makes a copy of the file in the head commit, copies it into the working
	 * directory and thus overwriting the preexisting file in the working
	 * directory if there is one.
	 * 
	 * @param FileName
	 *            - the file to be copied into the working directory
	 **/
	public void checkout(File FileName) {
		try {
			Commit currCommit = branches.get(currentBranch);
			Path source = FileSystems.getDefault().getPath(
					System.getProperty("user.dir") + "/.gitlet/commit"
							+ currCommit.getId(), FileName.getName());
			if (!Files.exists(source)) {
				String key = subpathWorkingDirectory(FileName.toPath());
				if (currCommit.trackedFiles.containsKey(key)) {
					Files.deleteIfExists(FileName.toPath());
					Files.copy(currCommit.trackedFiles.get(key).toPath(),
							FileName.toPath());
				} else {
					System.out
							.println("File does not exist in the most recent commit, or no such branch exists.");
				}
			} else {
				Path destination = FileName.toPath();
				Files.deleteIfExists(destination);
				Files.copy(source, destination);
			}
		} catch (IOException e) {
		}
	}

	/**
	 * Takes all the files in the commit at the head of the selected branch and
	 * copies them into the working directory, thus overwriting the preexisting
	 * files in the working directory with the same name. Also switch the
	 * current branch pointer to the selected branch.
	 * 
	 * @param branchName
	 *            - the branch with the commit that the files are copied from
	 */
	public void checkout(String branchName) {
		if (branchName.equals(currentBranch)) {
			System.out.println("No need to checkout the current branch.");
		} else if (!branches.containsKey(branchName)) {
			System.out
					.println("File does not exist in the most recent commit, or no such branch exists.");
		} else {
			Commit currCommit = branches.get(branchName);
			File[] files = new File(System.getProperty("user.dir")
					+ "/.gitlet/commit" + currCommit.getId() + "/").listFiles();
			if (files != null) {
				for (File f : files) {
					try {
						Path source = FileSystems.getDefault().getPath(
								System.getProperty("user.dir")
										+ "/.gitlet/commit"
										+ currCommit.getId(), f.getName());
						String destinationFile = f.getName();
						Path destination = FileSystems.getDefault()
								.getPath(System.getProperty("user.dir"),
										destinationFile);
						deleteDir(new File(destination.toString()));
						copy(new File(source.toString()),
								new File(destination.toString()));
					} catch (IOException e) {
					}
				}
			}
			for (File f : currCommit.trackedFiles.values()) {
				String name = subpathCommit(f.toPath());
				try {
					copy(f,
							new File(System.getProperty("user.dir") + "/"
									+ f.getName()));
				} catch (IOException e) {
				}
			}
			currentBranch = branchName;
		}
	}

	/**
	 * Creates a new branch, or a pointer object, and points it at the current
	 * head commit.
	 * 
	 * @param branchName
	 *            - the name of the new branch
	 **/
	public void branch(String branchName) {
		if (branches.containsKey(branchName)) {
			System.out.println("A branch with that name already exists.");
		} else {
			branches.put(branchName, commitMap.get(currentCommitId));
			splitPoints.put(branchName + currentBranch,
					commitMap.get(currentCommitId));
			splitPoints.put(currentBranch + branchName,
					commitMap.get(currentCommitId));

		}
	}

	/**
	 * Delete pointer associated with the branch argument
	 * 
	 * @param branchName
	 *            - the branch pointer which is deleted
	 **/
	public void rmbranch(String branchName) {
		if (currentBranch.equals(branchName)) {
			System.out.println("Cannot remove the current branch.");
		} else if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
		} else {
			branches.remove(branchName);
			if (!splitPoints.isEmpty()) {
				// adapted from StackOverflow to avoid concurrent modification
				// exception
				// http://stackoverflow.com/questions/18448671/
				// how-to-avoid-concurrentmodificationexception-while-removing-elements-from-arr
				Iterator<String> iter = splitPoints.keySet().iterator();
				while (iter.hasNext()) {
					String s = iter.next();
					if (s.contains(branchName)) {
						iter.remove();
					}
				}
			}
		}
	}

	/**
	 * Checks out all the files of a commit identified by a commitID. Also move
	 * the current branch's head to the commit node.
	 * 
	 * @param commitId
	 *            - the ID number associated with a commit
	 */
	public void reset(int commitId) throws IOException {
		if (commitMap.get(commitId) == null) {
			System.out.println("No commit with that id exists.");
			return;
		}
		Commit newHead = commitMap.get(commitId);
		branches.put(currentBranch, newHead);
		File[] files = new File(System.getProperty("user.dir")
				+ "/.gitlet/commit" + commitId + "/").listFiles();
		try {
			for (File f : files) {
				copy(f,
						new File(System.getProperty("user.dir") + "/"
								+ f.getName()));
			}
		} catch (IOException e) {
		}
	}

	/**
	 * Merges the files from the given branch into the current branch.
	 * 
	 * (1) Files that have been changed in the current branch, but not in the
	 * given branched are updated to the current branch's newer version and are
	 * then staged.
	 * 
	 * (2) Files that are modified in the current branch but not the given
	 * branch should stay as they are in the current branch.
	 * 
	 * (3) If both branches have the same updated file, that file should be stay
	 * as it is in the current branch, and the version from the given branch
	 * should be copied into the working directory as a .conflicted file.
	 * 
	 * If no conflicted files are generated, automatically commit. Otherwise,
	 * put Gitlet in a conflicted state.
   
	 * @param branchName
	 *            - the name of the given branch to which the currentBranch 
	 *             is merged with.
	 **/
	public void merge(String branchName) {
		if (!branches.containsKey(branchName)) {
			System.err.println("A branch with that name does not exist.");
			return;
		} else if (branchName.equals(currentBranch)) {
			System.err.println("Cannot merge a branch with itself.");
			return;
		}
		Commit splitPoint = splitPoints.get(branchName + currentBranch);
		HashMap<String, File> changedOnCurrent = new HashMap<String, File>();
		HashMap<String, File> changedOnGiven = new HashMap<String, File>();
		Commit currBranch = branches.get(currentBranch);
		Commit givenBranch = branches.get(branchName);
		// Find files changed since splitPoint on the given Branch
		for (String key : givenBranch.trackedFiles.keySet()) {
			if (splitPoint.trackedFiles.containsKey(key)) {
				File original = splitPoint.trackedFiles.get(key);
				File onGiven = givenBranch.trackedFiles.get(key);
				if (!original.equals(onGiven)) {
					changedOnGiven.put(key, onGiven);
				}
				if (currBranch.untrackedFiles.containsKey(key)) {
					currBranch.untrackedFiles.remove(key);
					addFromCommit(onGiven);
				}
			}
		}
		// Find files changed since splitPoint on the current Branch
		for (String key : currBranch.trackedFiles.keySet()) {
			if (splitPoint.trackedFiles.containsKey(key)) {
				File original = splitPoint.trackedFiles.get(key);
				File onCurrent = currBranch.trackedFiles.get(key);
				if (!original.equals(onCurrent)) {
					changedOnCurrent.put(key, onCurrent);
				}
			}
		}
		for (String key : changedOnGiven.keySet()) {
			File fromGiven = changedOnGiven.get(key);
			File fromCurrent = changedOnCurrent.get(key);
			if (fromCurrent != null) {
				conflictedState = true;
				File conflicted = new File(System.getProperty("user.dir") + "/"
						+ key + ".conflicted");
				try {
					copy(fromGiven, conflicted);
				} catch (IOException e) {
				}
			} else {
				File wdVersion = new File(System.getProperty("user.dir") + "/"
						+ key);
				checkout(givenBranch.getId(), wdVersion);
				currBranch.untrackedFiles.remove(key);
				add(wdVersion);
			}
		}
		// After everything print correct message
		if (conflictedState) {
			System.out.println("Encountered a merge conflict.");
		} else {
			String message = "Merged " + currentBranch + " with " + branchName;
			this.commit(message);
			System.out.println("Merged " + currentBranch + " with "
					+ branchName + ".");
		}
	}

	/**
	 * Make a copy of the current branch and move that copy to the last node of
	 * the given branch. Files that were changed after the split point are
	 * propagated through the copied commits until they encounter a version of
	 * the file that was changed in the copied commits. If the current branch
	 * pointer is in the history of the given branch, move the pointer to the to
	 * the head of the given branch.
	 *
	 * @param branchName
	 *            - the name of the branch to which the currentBranch's commits
	 *            are copied
	 **/
	public void rebase(String branchName) {

		if (!branches.containsKey(branchName)) {
			System.err.println("A branch with that name does not exist.");
			return;
		} else if (currentBranch.equals(branchName)) {
			System.err.println("Cannot rebase a branch onto itself.");
			return;
		} else if (branches.get(currentBranch).equals(branches.get(branchName))) {
			System.err.println("Already up-to-date.");
			return;
		}
		Commit givenBranchHead = branches.get(branchName);
		Commit currentBranchHead = branches.get(currentBranch);
		boolean specialCase = false;

		// special case: if currentBranch is a pointer to a commit
		// in the branchName's past
		Commit temp = currentBranchHead;
		while (temp != null) {
			if (temp.equals(givenBranchHead)) {
				branches.put(currentBranch, givenBranchHead);
				specialCase = true;
			}
			temp = temp.parent();
		}

		if (!specialCase) {
			// the currentBranch's commit
			temp = currentBranchHead;
			Commit splitPoint = splitPoints.get(branchName + currentBranch);

			Commit theCopy = rebaseHelper(temp, splitPoint, branchName);
			branches.put(currentBranch, theCopy);

			// now to make sure file history is accurate

			HashMap<String, File> updatedFiles = new HashMap<String, File>();
			for (String s : givenBranchHead.trackedFiles.keySet()) {
				if (splitPoint.trackedFiles.containsKey(s)) {
					if (splitPoint.trackedFiles.get(s).equals(
							givenBranchHead.trackedFiles.get(s))) {
						continue;
					} else {
						updatedFiles
								.put(s, givenBranchHead.trackedFiles.get(s));
					}
				} else {
					updatedFiles.put(s, givenBranchHead.trackedFiles.get(s));
				}
			}

			if (!updatedFiles.isEmpty()) {
				for (Commit c = branches.get(currentBranch); !c
						.equals(givenBranchHead); c = c.myParent) {
					for (String s : updatedFiles.keySet()) {
						if (!c.trackedFiles.containsKey(s)) {
							c.trackedFiles.put(s, updatedFiles.get(s));
						} else {
							if (c.trackedFiles.get(s).equals(
									splitPoint.trackedFiles.get(s))) {
								c.trackedFiles.put(s, updatedFiles.get(s));
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Helper method for the rebase method. Creates a head commit that contains
	 * child commits until the branch point.
	 * 
	 * @param daCommit
	 *            - the commit to be copied
	 * @param splitPoint
	 *            - the commit at which there was a split
	 * @param branchName
	 *            - the branch to which the commits are copied
	 * @return the head commit of the replayed branch
	 */
	private Commit rebaseHelper(Commit toBeCopied, Commit splitPoint,
			String branchName) {
		totalCommitCount++;

		Commit temp = toBeCopied.parent();
		// if the commit's parent equals the split point
		if (temp.equals(splitPoint)) {
			Commit last = new Commit(toBeCopied.getMessage(), totalCommitCount,
					branches.get(branchName));
			currentCommitId = last.getId();
			if (messageMap.get(last.getMessage()) == null) {
				messageMap.put(last.getMessage(), new ArrayList<Integer>());
				messageMap.get(last.getMessage()).add(currentCommitId);
			} else {
				messageMap.get(last.getMessage()).add(currentCommitId);
			}
			commitMap.put(currentCommitId, last);
			// copy file information
			for (String s : toBeCopied.trackedFiles.keySet()) {
				last.trackedFiles.put(s, toBeCopied.trackedFiles.get(s));
			}
			for (String s : toBeCopied.untrackedFiles.keySet()) {
				last.untrackedFiles.put(s, toBeCopied.untrackedFiles.get(s));
			}
			return last;
		}

		Commit rtnC = new Commit(toBeCopied.getMessage(), totalCommitCount,
				rebaseHelper(temp, splitPoint, branchName));
		currentCommitId = rtnC.getId();
		if (messageMap.get(rtnC.getMessage()) == null) {
			messageMap.put(rtnC.getMessage(), new ArrayList<Integer>());
			messageMap.get(rtnC.getMessage()).add(currentCommitId);
		} else {
			messageMap.get(rtnC.getMessage()).add(currentCommitId);
		}
		commitMap.put(currentCommitId, rtnC);
		// copy file information
		for (String s : toBeCopied.trackedFiles.keySet()) {
			rtnC.trackedFiles.put(s, toBeCopied.trackedFiles.get(s));
		}
		for (String s : toBeCopied.untrackedFiles.keySet()) {
			rtnC.untrackedFiles.put(s, toBeCopied.untrackedFiles.get(s));
		}
		return rtnC;
	}

	/**
	 * Main method in which the user interacts with the program. Also runs all
	 * the methods from the Gitlet class.
	 * 
	 * @param args
	 *            - the String commands from the user
	 */
	public static void main(String[] args) {
		Gitlet myGit = new Gitlet();
		String workingDirectory = System.getProperty("user.dir");
		File serFile = new File(workingDirectory + "/.gitlet/myGit.ser");
		// deserializing
		if (serFile.exists()) {
			try {
				FileInputStream fileIn = new FileInputStream(workingDirectory
						+ "/.gitlet/myGit.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				myGit = (Gitlet) in.readObject();
				in.close();
				fileIn.close();
			} catch (IOException i) {
				i.printStackTrace();
				return;
			} catch (ClassNotFoundException c) {
				System.out.println("Gitlet class not found.");
				return;
			}
		}
		String first = args[0];
		String rest = "";

		if (args.length > 1) {
			for (int i = 1; i < args.length; i++) {
				rest = rest + args[i];
			}
		}

		switch (first) {

		case "init":
			myGit.init();
			break;
		case "add":
			if (rest.isEmpty()) {
				System.out.println("File does not exist.");
				break;
			}
			File theFile = new File(workingDirectory + "/" + rest);
			try {
				myGit.add(theFile);
			} catch (Exception e) {
				System.out.println("File does not exist.");
			}
			break;
		case "commit":
			if (rest.isEmpty()) {
				System.out.println("Please enter a commit message.");
				break;
			}
			myGit.commit(rest);
			if (myGit.conflictedState) {
				myGit.conflictedState = false;
			}
			break;
		case "rm":
			File toStagingFolder = new File(System.getProperty("user.dir")
					+ "/.gitlet/staging/" + rest);
			try {
				myGit.rm(toStagingFolder);
			} catch (Exception e) {
			}
			break;
		case "log":
			myGit.log();
			break;
		case "global-log":
			myGit.globallog();
			break;
		case "find":
			myGit.find(rest);
			break;
		case "status":
			myGit.status();
			break;
		case "checkout":
			if (args.length == 2) {
				if (myGit.branches.containsKey(args[1])) {
					if (myGit.conflictedState) {
						System.err
								.println("Cannot do this command until the merge conflict has been resolved.");
						break;
					}
					myGit.checkout(args[1]);
				} else {
					String pathName = System.getProperty("user.dir") + "/"
							+ args[1];
					myGit.checkout(new File(pathName));
				}
			} else {
				myGit.checkout(
						Integer.parseInt(args[1]),
						new File(System.getProperty("user.dir") + "/" + args[2]));
			}
			break;
		case "branch":
			if (myGit.conflictedState) {
				System.err
						.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			myGit.branch(rest);
			break;
		case "rm-branch":
			if (myGit.conflictedState) {
				System.err
						.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			myGit.rmbranch(rest);
			break;
		case "reset":
			if (myGit.conflictedState) {
				System.err
						.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			if (rest.isEmpty()) {
				System.err.println("No commit with that id exists.");
				break;
			}
			try {
				int commitId = Integer.parseInt(rest);
				myGit.reset(commitId);
			} catch (Exception e) {
				System.err.println("No commit with that id exists.");
			}
			break;
		case "merge":
			if (myGit.conflictedState) {
				System.err
						.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			myGit.merge(rest);
			break;
		case "rebase":
			if (myGit.conflictedState) {
				System.err
						.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			try {
			myGit.rebase(rest);
			break;
			} catch (Exception e) {
			}
		}
		// serialize
		try {
			FileOutputStream fileOut = new FileOutputStream(workingDirectory
					+ "/.gitlet/myGit.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(myGit);
			out.close();
			fileOut.close();
		} catch (IOException i) {
		}
	}
}
