import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * GitTree is a simple implementation of git using a tree structure of GitNodes
 * that represents the commit tree.
 * 
 * @author Albert Pham cs61bl-bp
 * @author Henry Gong cs61bl-bk
 * @author Patrick Zhang cs61bl-bo
 */
public class GitTree implements Serializable {
	private HashMap<Integer, GitNode> allNodes; // hash map for all nodes
	private HashMap<String, String> nodeMessages; // hash map for find
	private HashMap<String, GitNode> branches; // last node in each branch
	private HashSet<String> stagingArea; // represent files in stage.
	private HashSet<String> untracking; // represents files to be untracked.
	private String currentBranch; // active branch
	private Integer nextCommitID; // next available ID
	private static String stagingFolder = ".gitlet/staging/"; // staging path
	private static String rootFolder = ".gitlet/"; // folder path
	private GitNode head; // pointer to head.
	private boolean conflicted;

	/**
	 * Creates a new gitlet version control system in the current directory.
	 * Begin with one commit with no files and message "initial commit."
	 * Precondition: .gitlet does not already exist.
	 */
	public GitTree() {
		try {
			Files.createDirectory(Paths.get(rootFolder));
			Files.createDirectory(Paths.get(rootFolder + 0));
			Files.createDirectory(Paths.get(stagingFolder));
		} catch (IOException e) {
			e.printStackTrace();
		}
		allNodes = new HashMap<Integer, GitNode>();
		nodeMessages = new HashMap<String, String>();
		branches = new HashMap<String, GitNode>();
		stagingArea = new HashSet<String>();
		untracking = new HashSet<String>();
		nextCommitID = 1;
		currentBranch = "master";
		GitNode initial = new GitNode(0, "initial commit");
		branches.put(currentBranch, initial);
		allNodes.put(0, initial);
		nodeMessages.put(initial.message(), "0");
		head = initial;
		conflicted = false;
	}

	/**
	 * Adds a copy of specified file to staging area. If file has been marked
	 * for untracking, unmark the file but do not add to staging.
	 * 
	 * @param file
	 *            name of file to be added.
	 */
	public void add(String file) {
		Path fromPath = Paths.get(file);
		if (!Files.exists(fromPath)) {
			System.out.println("File does not exist.");
		} else if (untracking.contains(file)) {
			untracking.remove(file);
		} else {
			Path toPath = Paths.get(stagingFolder + file);
			if (Files.exists(toPath)) {
				try {
					Files.delete(toPath);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			copy(fromPath.toString(), toPath.toString());
			stagingArea.add(file);
		}
	}

	/**
	 * Commits all staged files to the tree as a new node. Clear the staging
	 * area. Most recent commit becomes current commit and head. Assigns an
	 * integer ID to the commit.
	 * 
	 * Adds the commit to both the ID and messages HashMap.
	 * 
	 * @param message
	 *            Describes the changes made to the files to commit.
	 * 
	 *            If no files have been staged or marked for untracking, do
	 *            nothing and print error. If there is no message, do nothing
	 *            and print error.
	 */
	public void commit(String message) {
		if (message.length() == 0) {
			System.out.println("Please enter a commit message.");
			return;
		}
		if (stagingArea.isEmpty() && untracking.isEmpty()) {
			System.out.println("No changes added to the commit.");
			return;
		}
		HashMap<String, Integer> fileNames = new HashMap<String, Integer>();
		try {
			Files.createDirectory(Paths.get(rootFolder + nextCommitID));
			// populate map of file names to commit contained in
			copy(stagingFolder, rootFolder + nextCommitID);
			for (String fileName : stagingArea) {
				fileNames.put(fileName, nextCommitID);
			}
			delete(stagingFolder);
			Files.createDirectory(Paths.get(stagingFolder));
		} catch (IOException e) {
			e.printStackTrace();
		}
		stagingArea = new HashSet<String>();
		for (String s : head.fileNames().keySet()) {
			if (!fileNames.containsKey(s) && !untracking.contains(s)) {
				fileNames.put(s, head.fileNames().get(s));
			}
		}
		GitNode node = new GitNode(nextCommitID, head, message, fileNames);
		allNodes.put(nextCommitID, node);
		addMessage(message);
		branches.put(currentBranch, node);
		head = node;
		nextCommitID++;
		untracking = new HashSet<String>();
		conflicted = false;
	}

	/**
	 * Adds a message to the HashMap keeping track of all messages.
	 * 
	 * @param message
	 *            Message of the commit.
	 */
	private void addMessage(String message) {
		String s = nodeMessages.get(message);
		if (s == null) {
			s = nextCommitID + "";
		} else {
			s = s + '\n' + nextCommitID;
		}
		nodeMessages.put(message, s);
	}

	/**
	 * Marks file for untracking. If specified file is neither stages nor
	 * tracked, print error.
	 * 
	 * @param file
	 *            File name to be untracked.
	 */
	public void rm(String file) {
		if (!head.fileNames().containsKey(file) && !stagingArea.contains(file)) {
			System.out.println("No reason to remove the file.");
		} else if (stagingArea.contains(file)) {
			stagingArea.remove(file);
			Path p = Paths.get(stagingFolder + file);
			if (Files.exists(p)) {
				try {
					Files.delete(p);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			untracking.add(file);
		}
	}

	/**
	 * Starting from the current head commit, display commit history until
	 * initial commit. See project spec for format of information.
	 */
	public void log() {
		GitNode node = branches.get(currentBranch);
		while (node != null) {
			node.log();
			node = node.prev();
		}
	}

	/**
	 * Display information of every commit made by traversing GitTree.
	 */
	public void globalLog() {
		for (int i = nextCommitID - 1; i >= 0; i--) {
			allNodes.get(i).log();
		}
	}

	/**
	 * Search all commits for the specified message and displays their ids. If
	 * there is more than one match, print on separate lines.
	 * 
	 * @param message
	 *            Desired commit message to search for.
	 */
	public void find(String message) {
		String s = nodeMessages.get(message);
		if (s == null) {
			System.out.println("Found no commit with that message.");
		} else {
			System.out.println(s);
		}
	}

	/**
	 * Displays the name of all branches that exist, marking current branch with
	 * *. Also display what files have been staged/marked for untracking. See
	 * project spec for formatting.
	 */
	public void status() {
		System.out.println("=== Branches ===");
		Set<String> keys = branches.keySet();
		for (String s : keys) {
			if (s.equals(currentBranch)) {
				System.out.print("*");
			}
			System.out.println(s);
		}
		System.out.println(); // extra line
		System.out.println("=== Staged Files ===");
		for (String s : stagingArea) {
			System.out.println(s);
		}
		System.out.println(); // extra line
		System.out.println("=== Files Marked for Untracking ===");
		for (String s : untracking) {
			System.out.println(s);
		}
	}

	/**
	 * Takes the specified file in the head commit and put it in the working
	 * directory. Overwrite any existing file.
	 * 
	 * @param file
	 *            Desired file to checkout from head commit. If file does not
	 *            exist in previous commit, print error.
	 */
	public void checkout(String file) {
		if (branches.containsKey(file)) {
			checkout(file, true);
		} else {
			HashMap<String, Integer> fileNames = head.fileNames();
			if (fileNames.containsKey(file)) {
				Path filePath = Paths.get(rootFolder + fileNames.get(file)
						+ "/" + file);
				Path toPath = Paths.get(file);
				if (Files.exists(toPath)) {// overwrite existing file
					try {
						Files.delete(toPath);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				copy(filePath.toString(), toPath.toString());
			} else {
				System.out
						.println("File does not exist in the most recent commit, or"
								+ " no such branch exists.");
			}
		}
	}

	/**
	 * Takes version of the file with the given commit id and put it in working
	 * directory. Overwrite any existing file.
	 * 
	 * @param id
	 *            Commit id to checkout from. Print error if it can't be found.
	 * @param file
	 *            File name to checkout. Print error if it can't be found.
	 */
	public void checkout(int id, String file) {
		if (!allNodes.containsKey(id)) {
			System.out.println("No commit with that id exists.");
			return;
		}

		// maybe a file is tracking by this commit but doesn't changed, so the
		// file exists in another commit's folder.
		GitNode node = allNodes.get(id);
		if (!node.fileNames().containsKey(file)) {
			System.out.println("File does not exist in that commit.");
			return;
		}

		Path filePath = Paths.get(rootFolder + node.fileNames().get(file) + "/"
				+ file);
		Path toPath = Paths.get(file);
		if (Files.exists(toPath)) {
			try {
				Files.delete(toPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		copy(filePath.toString(), toPath.toString());
	}

	/**
	 * Takes all files in the head of specified branch and put them in working
	 * directory. Sets current branch as the given branch.
	 * 
	 * @param branch
	 *            Name of the branch to checkout from. If branch does not exist,
	 *            print error. If specified branch is the current branch, print
	 *            error.
	 */
	public void checkout(String branch, boolean isBranch) {
		if (currentBranch.equals(branch)) {
			System.out.println("No need to checkout the current branch.");
		}
		if (conflicted)
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
		else {
			currentBranch = branch; // update branch
			head = branches.get(currentBranch);
			GitNode commit = branches.get(branch);
			for (String fileName : commit.fileNames().keySet()) {
				Path fromPath = Paths.get(rootFolder
						+ commit.fileNames().get(fileName) + "/" + fileName);
				Path toPath = Paths.get(fileName);
				if (Files.exists(toPath)) {
					try {
						Files.delete(toPath);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				copy(fromPath.toString(), toPath.toString());
			}
		}
	}

	/**
	 * Creates a new branch and points it at the current head node. Default
	 * branch is "master." Note: does not automatically switch to the new
	 * branch.
	 * 
	 * @param branch
	 *            Branch name. If it already exists, prints error message.
	 */
	public void branch(String branch) {
		if (branches.containsKey(branch)) {
			System.out.println("A branch with that name already exists.");
		} else {
			branches.put(branch, head);
		}
	}

	/**
	 * Deletes the branch with the given name by removing the pointer.
	 * 
	 * @param branch
	 *            name. If it can't be found, prints error message. Also cannot
	 *            remove current branch.
	 */
	public void rmBranch(String branch) {
		if (currentBranch.equals(branch)) {
			System.out.println("Cannot remove the current branch.");
		} else if (!branches.containsKey(branch)) {
			System.out.println("A branch with that name does not exist.");
		} else {
			branches.remove(branch);
		}
	}

	/**
	 * Checks out all the files tracked by the given commit. Change the current
	 * branch head to that commit node.
	 * 
	 * @param id
	 *            Commit ID to reset to. If there is no commit with that id,
	 *            print error.
	 */
	public void reset(int id) {
		if (!allNodes.containsKey(id)) {
			System.out.println("No commit with that id exists.");
		} else {
			GitNode commit = allNodes.get(id);
			for (String s : commit.fileNames().keySet()) {
				checkout(id, s);
			}
			branches.put(currentBranch, commit);
			head = commit;
		}
	}

	/**
	 * Merges files from given branch into the current branch. Any files
	 * modified in given branch but not in current should be checked out. Any
	 * files modified in current branch but not given branch should not change.
	 * If files are modified in both, stay as they are in current. Conflicts
	 * will be copied as well as [old file name].conflicted.
	 * 
	 * @param branch
	 *            name of branch to merge with
	 */
	public void merge(String branch) {
		// check existence of branch
		if (!branches.containsKey(branch)) {
			System.out.println("A branch with that name does not exist.");
			// check branch is not current branch
		} else if (branch.equals(currentBranch)) {
			System.out.println("Cannot merge a branch with itself.");
		} else {
			// find split point
			GitNode branchHead = branches.get(branch);
			HashMap<String, Integer> branchHeadFiles = branchHead.fileNames();
			HashMap<String, Integer> splitPointFiles = findSplit(head,
					branchHead).fileNames();
			// collect files in given branch modified since split (commit older
			// than split)
			HashSet<String> branchModified = mergeHelper(splitPointFiles,
					branchHeadFiles);
			// collect files in current branch modified since split (commit
			// older than split)
			HashMap<String, Integer> currentHeadFiles = head.fileNames();
			HashSet<String> currentModified = mergeHelper(splitPointFiles,
					currentHeadFiles);
			// find difference of modified files in given branch from modified
			// files in current branch
			HashSet<String> conflictedFiles = new HashSet<String>(
					currentModified);
			HashSet<String> unconflictedFiles = new HashSet<String>(
					branchModified);
			HashSet<String> toAdd = new HashSet<String>();
			conflictedFiles.retainAll(branchModified); // finds intersection,
														// i.e. conflicted files
			unconflictedFiles.removeAll(currentModified); // branchModified now
															// contains
															// non-conflicted
															// files to copy
			// copy appropriate set of files from given branch to current branch
			for (String fileToCopy : branchHeadFiles.keySet()) {
				Path fileToCopyPath = Paths.get(rootFolder
						+ branchHeadFiles.get(fileToCopy) + "/" + fileToCopy);
				if (unconflictedFiles.contains(fileToCopy)) {
					copy(fileToCopyPath.toString(), Paths.get(fileToCopy)
							.toString());
					toAdd.add(fileToCopy);
				} else if (conflictedFiles.contains(fileToCopy)) {
					copy(fileToCopyPath.toString(), Paths.get(fileToCopy)
							.toString() + ".conflicted");
					conflicted = true;
				}
			}
			// print appropriate message
			if (conflicted)
				System.out.println("Encountered a merge conflict.");
			else {
				for (String s : toAdd) 
					add(s);
				commit("Merged " + branch + " with " + currentBranch + ".");
			}
		}
	}

	/**
	 * Finds the files modified in the head node's files since the split node's
	 * files.
	 * 
	 * @param splitPointFiles
	 *            The files tracked by the commit at the split point.
	 * @param headFiles
	 *            The files tracked by the commit at the branch head.
	 * 
	 * @return A set of filenames that have been modified since the split point
	 *         commit.
	 */
	private HashSet<String> mergeHelper(
			HashMap<String, Integer> splitPointFiles,
			HashMap<String, Integer> headFiles) {
		HashSet<String> headModified = new HashSet<String>();
		Set<String> headFilenames = headFiles.keySet();
		for (String file : headFilenames) {
			if (splitPointFiles.get(file) != headFiles.get(file)) {
				// commit's are different; thus modified
				headModified.add(file);
			}
		}
		return headModified;
	}

	/**
	 * Copy the given branch from the split point and attach it to the end of
	 * current branch. Copies have new ids and new time stamps.
	 * 
	 * @param branch
	 *            Branch to which to merge the current branch.
	 */
	public void rebase(String branch) {
		if (!branches.containsKey(branch)) {
			System.out.println("A branch with that name does not exist.");
		} else if (branch.equals(currentBranch)) {
			System.out.println("Cannot rebase a branch onto itself.");
		} else {
			GitNode split = findSplit(branches.get(currentBranch),
					branches.get(branch));
			if (split == branches.get(currentBranch)) {
				branches.put(currentBranch, branches.get(branch));
			} else if (split == branches.get(branch)) {
				System.out.println("Already up-to-date.");
			} else {
				Stack<GitNode> nodeStack = new Stack<GitNode>();
				GitNode index = branches.get(currentBranch);
				while (index != split) {
					nodeStack.push(index);
					index = index.prev();
				}
				head = branches.get(branch);
				while (!nodeStack.isEmpty()) {
					GitNode node = nodeStack.pop();
					HashMap<String, Integer> fileNames = new HashMap<String, Integer>(node.fileNames());
					for (String s: fileNames.keySet()) {
						if (!head.fileNames().containsKey(s)
								&& fileNames.get(s) <= split.id()){
							fileNames.remove(s);
						}
					}
					// the propagation rule
					for (String s : head.fileNames().keySet()) {
						if (!fileNames.containsKey(s)) {
							fileNames.put(s, head.fileNames().get(s));
						} else if (fileNames.get(s) <= split.id()
								&& head.fileNames().get(s) > split.id()) {
							fileNames.put(s, head.fileNames().get(s));
						}
					}
					GitNode nodeCopy = new GitNode(nextCommitID, head,
							node.message(), fileNames);

					allNodes.put(nextCommitID, nodeCopy);
					addMessage(nodeCopy.message());
					// we already have a stack storing nodes to be copied, so
					// just move the current branch to the newly created node.
					branches.put(currentBranch, nodeCopy);
					head = nodeCopy;
					nextCommitID++;
				}
			}
		}
	}

	/**
	 * Finds the split point between commit a and commit b based on timestamps.
	 * 
	 * @param a
	 *            GitNode representing the first commit.
	 * @param b
	 *            GitNode representing the second commit.
	 * @return GitNode that is the common split point between a and b.
	 */
	private static GitNode findSplit(GitNode a, GitNode b) {
		// if a and b are the same node, then that's the split point.
		if (a == b) {
			return a;
		}
		int timeComp = a.timestamp().compareTo(b.timestamp());
		if (timeComp < 0) {
			return findSplit(a, b.prev());
		} else {
			return findSplit(a.prev(), b);
		}
	}

	/**
	 * Getter method for the conflicted state.
	 * 
	 * @return Whether or not this GitTree is in a conflicted state.
	 */
	public boolean getConflicted() {
		return conflicted;
	}

	/**
	 * Deletes all files from a folder. Used to clear staging area.
	 * 
	 * @param directory
	 *            path of folder
	 */
	public static void delete(String directory) {
		File f = new File(directory);
		Path p = f.toPath();
		if (Files.isDirectory(p)) {
			for (String s : f.list()) {
				delete(directory + "/" + s);
			}
		}
		try {
			Files.delete(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a directory at the given string. if the parent folder of this
	 * directory doesn't exist, recursively create the parent folder.
	 * 
	 * @param s
	 */
	public static void createDirectory(String s) {
		File file = new File(s);
		if (Files.exists(file.toPath())) {
			return;
		}
		File parentfile = file.getParentFile();
		if (parentfile != null && !Files.exists(parentfile.toPath())) {
			createDirectory(parentfile.toString());
		}
		try {
			Files.createDirectory(Paths.get(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Copies the contents of a folder to another folder.
	 * 
	 * @param fromDirect
	 *            Folder with files to be copied.
	 * @param toDirect
	 *            Folder to copy files to.
	 */
	public static void copy(String fromDirect, String toDirect) {
		File toDirectory = new File(toDirect);
		File fromDirectory = new File(fromDirect);
		if (fromDirectory.isFile()) {
			File parentfile = toDirectory.getParentFile();
			if (parentfile != null && !Files.exists(parentfile.toPath())) {
				createDirectory(parentfile.toString());
			}
			try {
				Files.copy(Paths.get(fromDirect), Paths.get(toDirect),
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			createDirectory(toDirect);
			for (String s : fromDirectory.list()) {
				copy(fromDirect + "/" + s, toDirect + "/" + s);
			}
		}
	}
}
