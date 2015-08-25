import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.File;
import java.util.Date;
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.*;

public class Gitlet implements Serializable {

	private static final String[] regularCommands = { "init", "add", "commit",
			"rm", "log", "global-log", "find", "status", "checkout", "branch",
			"rm-branch", "reset", "merge", "rebase" };

	private static final String[] conflictedCommands = { "add", "rm", "commit",
			"checkout", "log", "global-log", "find", "status" };

	private GitletTree tree;
	private GitletTree activeTree;
	private String activeBranch;

	private HashMap<String, GitletTree> pointers;
	private HashMap<String, File> stage;
	private HashSet<File> unmark;

	private File gitletFolder;
	private File stagingArea;
	private File commitFolder;

	private int idcounter;
	private boolean conflictedState;

	/**
	 * Constructs a Gitlet.
	 * 
	 */
	public Gitlet() {
		pointers = new HashMap<String, GitletTree>();
		stage = new HashMap<String, File>();
		unmark = new HashSet<File>();
		idcounter = 0;
		conflictedState = false;

	}

	/**
	 * Initializes a gitlet. Creates an empty commit with an ID of 0. Creates an
	 * hidden .gitlet folder in the working directory. Inside the .gitlet
	 * folder, it creates a staging area folder and a commit folder.
	 */
	public void init() {
		gitletFolder = new File(".gitlet");
		stagingArea = new File(".gitlet/staging-area");
		commitFolder = new File(".gitlet/commits");
		File initialCommit = new File(".gitlet/commits/commit" + idcounter);

		Commit firstcommit = new Commit(idcounter, "initial commit", new Date());
		idcounter++;

		tree = new GitletTree(firstcommit);
		pointers.put("master", tree);
		activeTree = tree;
		activeBranch = "master";

		gitletFolder.mkdir();
		stagingArea.mkdir();
		commitFolder.mkdir();
		initialCommit.mkdir();

		System.out.println("Gitlet system initalized.");
	}

	/**
	 * Adds a file to the staging area. If the file does not exist, prints an
	 * error. If the file is already on the staging area, prints an error. If
	 * the file is marked, unmarks the file but doesn't add it to the staging
	 * area.
	 * 
	 * @param fileName
	 *            : the name of the fire to add.
	 */
	public void add(File fileName) {
		if (!fileName.exists()) {
			System.err.println("File does not exist.");
		} else if (stage.containsValue(fileName)) {
			return;
		} else if (unmark.contains(fileName)) {
			unmark.remove(fileName);
		} else {
			if (this.addToStage(fileName.getPath(), fileName)) {
				return;
			}
		}
	}

	/**
	 * Creates a new commit object and adds it to the commit tree. Takes the
	 * files from its parent commit and adds the files that are being tracked
	 * from the staging area. and stores them in a new commit with a new commit
	 * ID.
	 * 
	 * Clears the staging area when finished.
	 * 
	 * @param message
	 *            : A String object, the message associated with the commit.
	 */
	public void commit(String message) {
		if (stage.isEmpty() && unmark.isEmpty()) {
			System.out.println("No changes added to the commit.");
			return;
		}

		HashMap<String, File> commitFiles = new HashMap<String, File>();
		HashMap<String, File> oldFiles = activeTree.getCommit().getFiles();
		Iterator<Entry<String, File>> oldFilesIterator = oldFiles.entrySet()
				.iterator();

		while (oldFilesIterator.hasNext()) {
			Entry<String, File> entry = (Entry<String, File>) oldFilesIterator
					.next();
			if (!stage.containsKey(entry.getKey())
					&& !unmark.contains(entry.getValue())) {
				commitFiles.put(entry.getKey(), entry.getValue());
			}
		}

		File newCommitFolder = new File(".gitlet/commits/commit" + idcounter);
		newCommitFolder.mkdir();

		for (String s : stage.keySet()) {
			if (!unmark.contains(stage.get(s))) {
				File destination = new File(".gitlet/commits/commit" + idcounter + "/" + s);
				try {
					destination.mkdirs();
					Files.copy(stage.get(s).toPath(), destination.toPath(),
							REPLACE_EXISTING);
					commitFiles.put(s, destination);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		Commit newCommit = new Commit(commitFiles, idcounter, message,
				new Date());
		GitletTree newTree = new GitletTree(newCommit);
		activeTree.addBranch(newTree);
		activeTree = newTree;
		pointers.put(activeBranch, activeTree);
		cleanFolder(stagingArea);
		stage = new HashMap<String, File>();
		unmark = new HashSet<File>();
		idcounter++;
		if (conflictedState) {
			conflictedState = false;
		}
//		for (File f : activeTree.getCommit().getFiles().values()) {
//			System.out.println(f.toString());
//		}
//		tree.printTree();
	}

	/**
	 * Mark the file for untracking. If the file is staged, remove it from the
	 * staging area but it is not marked for untracking.
	 * 
	 * @param f
	 *            : a File object to be removed from the staging area or mark
	 *            for untracking.
	 */
	public void rm(File f) {
		HashMap<String, File> oldCommit = activeTree.getCommit().getFiles();
		if (stage.keySet().contains(f.getPath())) {
			stage.get(f.getPath()).delete();
			stage.remove(f.getPath());
			
		} else if (oldCommit.keySet().contains(f.getPath())) {
			if (unmark.contains(oldCommit.get(f.getPath()))) {
				System.out.println("No reason to remove the file");
			} else {
				unmark.add(oldCommit.get(f.getPath()));
			}
		} else {
			System.out.println("No reason to remove the file.");
		}
	}

	/**
	 * Starting from the current commit, displays each commit with their IDs,
	 * date stamp and messages all the way down to the initial commit in order.
	 * Called the commit history.
	 */
	public void log() {
		GitletTree pointer = activeTree;
		while (pointer != null) {
			System.out.println(pointer.getCommit().logHelper());
			pointer = pointer.getParent();
		}
	}

	/**
	 * Displays every commit ever made with their IDs, date stamp and messages.
	 * In no particular order.
	 */
	public void globalLog() {
		tree.globalLogHelper();
	}

	/**
	 * Prints the ID of the commit with the given commit message. If multiple
	 * commits exist with that message, it prints the IDs on separate lines.
	 * 
	 * @param message
	 *            : A String object, the commit message to be found.
	 */
	public void find(String message) {
		ArrayList<GitletTree> wantedIds = tree.findByMessage(message);
		if (wantedIds.isEmpty()) {
			System.out.println("Found no commit with that message.");
		} else {
			for (GitletTree s : wantedIds) {
				System.out.println(s.getCommit().getId());
			}
		}
	}

	/**
	 * Displays what branches currently exist and marks the current branch with
	 * a *. Displays what files have been staged or marked for untracking.
	 */
	public void status() {
		Set<String> branchNames = pointers.keySet();

		System.out.println("=== Branches ===");
		for (String s : branchNames) {
			if (activeBranch.equals(s)) {
				System.out.print("*");
			}
			System.out.println(s);
		}

		System.out.println('\n' + "=== Staged Files ===");
		for (String s : stage.keySet()) {
			System.out.println(s);
		}

		System.out.println('\n' + "=== Files Marked for Untracking ===");
		for (File f : unmark) {
			System.out.println(f.getPath());
		}
	}

	/**
	 * takes the version of the file from the head commit of the current branch
	 * and moves it to the working directory. If a file already exists in the
	 * working directory, it is overwritten.
	 * 
	 * @param f
	 *            : a File object to be checked out.
	 * 
	 * @throws IO
	 *             Exception when the file cannot be created as indicated.
	 */
	public void checkout(File f) {
		HashMap<String, File> recentCommitFiles = activeTree.getCommit()
				.getFiles();
		if (!recentCommitFiles.keySet().contains(f.getPath())) {
			System.out.println("File does not exist in the most recent commit, or no such branch exists.");
		} else {
			try {
				f.mkdirs();
				Files.copy(recentCommitFiles.get(f.getPath()).toPath(),
						f.toPath(), REPLACE_EXISTING);
				System.out.println("File successfully checked out.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Takes the version of the file in the given commit and puts it into the
	 * working directory. If the file already exists in the working directory,
	 * it is overwritten.
	 * 
	 * @param id
	 *            : an integer, the commit ID with the desired version of the
	 *            file.
	 * @param f
	 *            : a File object, the file to be checked out.
	 * 
	 * @throws IO
	 *             Exception when the file cannot be created as indicated.
	 */
	public void checkout(int id, File f) {
		GitletTree treeWithId = tree.findById(id);
		if (treeWithId == null) {
			System.out.println("No commit with that id exists.");
		} else if (!treeWithId.getCommit().getFiles().keySet()
				.contains(f.getPath())) {
			System.out.println("File does not exist in that commit");
		} else {
			try {
				f.mkdirs();
				Files.copy(treeWithId.getCommit().getFiles().get(f.getPath())
						.toPath(), f.toPath(), REPLACE_EXISTING);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Takes all the files from the commit at the head of the given branch and
	 * puts them in the working directory. If the file already exists, it is
	 * overwritten. Sets the given branch as the current branch.
	 * 
	 * @param branch
	 *            : A String object, the name of the branch with the files
	 *            desired.
	 * 
	 * @throws IO
	 *             Exception when the file cannot be created as indicated.
	 */
	public void checkout(String branch) {
		if (!pointers.containsKey(branch)) {
			System.out
					.println("File does not exist in the most recent commit, or no such branch exists.");
		} else if (activeBranch.equals(branch)) {
			System.out.println("No need to checkout the current branch");
		} else {
			GitletTree treeOfBranch = pointers.get(branch);
			HashMap<String, File> treeFiles = treeOfBranch.getCommit()
					.getFiles();
			if (treeFiles.isEmpty()) {
				activeBranch = branch;
				activeTree = treeOfBranch;
			} else {
				for (String s : treeFiles.keySet()) {
					File newFile = new File(s);
					try {
						newFile.mkdirs();
						Files.copy(treeFiles.get(s).toPath(), newFile.toPath(),
								REPLACE_EXISTING);
						activeBranch = branch;
						activeTree = treeOfBranch;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * Determines which checkout method to call based on the arguments inputed.
	 * 
	 * @param args
	 *            : String array of valid arguments for checkout.
	 */
	public void checkoutHelper(String[] args) {
		if (args.length == 3) {
			try {
				int id = Integer.parseInt(args[1]);
				checkout(id, new File(args[2]));
			} catch (IllegalArgumentException e) {
				System.out.println("Arguments are not correctly formatted.");
			}
		} else if (args.length == 2) {
			if (pointers.keySet().contains(args[1])) {
				checkout(args[1]);
			} else {
				checkout(new File(args[1]));
			}
		} else {
			System.out.println("Too many arguments.");
		}
	}

	/**
	 * Creates a pointer that points to the current head node. Adds it to the
	 * pointer HashMap associated with Gitlet.
	 * 
	 * @param branchName
	 *            : A String, the name of the new branch.
	 */
	public void branch(String branchName) {
		if (pointers.containsKey(branchName)) {
			System.out.print("A branch with that name already exists.");
		} else {
			pointers.put(branchName, activeTree);
		}
	}

	/**
	 * Deletes the pointer associated with the branch name. Removes from the
	 * pointers HashMap.
	 * 
	 * @param branchName
	 *            : A String, the name of the branch desired.
	 */
	public void rmBranch(String branchName) {
		if (!pointers.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
		} else if (branchName.equals(activeBranch)) {
			System.out.println("Cannot remove the current branch.");
		} else {
			pointers.remove(branchName);
		}
	}

	/**
	 * Checks out all the files tracked by the the given commit. Moves the head
	 * of the current branch to the given commit node.
	 * 
	 * @param id
	 *            : an integer, the ID of the commit desired.
	 * 
	 * @throws IO
	 *             Exception when the file cannot be created as indicated.
	 */
	public void reset(int id) {
		System.out.println("called reset");
		GitletTree treeWithId = tree.findById(id);
		if (treeWithId == null) {
			System.out.println("No commit with that id exists.");
		} else {
			HashMap<String, File> treeFiles = treeWithId.getCommit().getFiles();
			for (String s : treeFiles.keySet()) {
				File newFile = new File(s);
				try {
					newFile.mkdirs();
					Files.copy(treeFiles.get(s).toPath(), newFile.toPath(),
							REPLACE_EXISTING);
					activeTree = treeWithId;
					pointers.put(activeBranch, treeWithId);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Merges files from the given branch to the current branch. If the file is
	 * changed from the split point in one branch but not the other, take the
	 * modified version. If both the current and given branches have differently
	 * modified versions of the file, both files should stay as they are, but
	 * the version from the given branch should be copied to the working
	 * directory with the name [old file name].conflicted and puts the gitlet
	 * into a conflicted state.
	 * 
	 * 
	 * @param branchName
	 *            : String, name of the branch to be merged with the current.
	 * 
	 * @throws IO
	 *             Exception when the file cannot be created as indicated.
	 */
	public void merge(String branchName) {

		HashMap<String, File> activeFiles = activeTree.getCommit().getFiles();
		HashMap<String, File> givenFiles = pointers.get(branchName).getCommit()
				.getFiles();
		HashMap<String, File> splitFiles = GitletTree
				.findSplit(activeTree, pointers.get(branchName)).getCommit()
				.getFiles();
		HashMap<String, File> diffActive = new HashMap<String, File>();
		HashMap<String, File> diffGiven = new HashMap<String, File>();

		Set<String> activeKeys = activeFiles.keySet();
		Set<String> givenKeys = givenFiles.keySet();
		Set<String> splitKeys = splitFiles.keySet();

		for (String s : activeKeys) {
			File activeFile = activeFiles.get(s);
			if (!givenKeys.contains(s)) {
				this.addToStage(s, activeFile);
			} else {
				File givenFile = givenFiles.get(s);
				if (sameFile(activeFile, givenFile)) {
					this.addToStage(s, activeFile);
				} else {
					diffActive.put(s, activeFile);
					diffGiven.put(s, givenFile);
				}
			}
		}

		for (String s : givenKeys) {
			if (!activeKeys.contains(s)) {
				this.addToStage(s, givenFiles.get(s));
			}
		}

		for (String s : diffActive.keySet()) {
			if (splitKeys.contains(s)) {
				boolean sameActiveSplit = sameFile(diffActive.get(s),
						splitFiles.get(s));
				boolean sameGivenSplit = sameFile(diffGiven.get(s),
						splitFiles.get(s));
				if (!sameActiveSplit && sameGivenSplit) {
					this.addToStage(s, diffActive.get(s));
				} else if (sameActiveSplit && !sameGivenSplit) {
					this.addToStage(s, diffGiven.get(s));
				} else {
					mergeConflictHandler(s, diffActive.get(s));
				}
			} else {
				mergeConflictHandler(s, diffActive.get(s));
			}
		}

		if (conflictedState) {
			System.out.println("Encountered a merge conflict.");
		} else {
			commit("Merged " + activeBranch + " with " + branchName + ".");
		}
	}

	/**
	 * From the split point of the current branch and the given branch, the
	 * current branch is copied to the head of the given branch.
	 * 
	 * @param branchName
	 *            : String, the name of the branch to rebase.
	 * 
	 * @throws IO
	 *             Exception when the file cannot be created as indicated.
	 */
	public void rebase(String branchName) {
		GitletTree givenTree = pointers.get(branchName);
		if(!rebaseCheckFailures(branchName, givenTree)) {
			return; }
		GitletTree splitTree = GitletTree.findSplit(activeTree, givenTree);
		Stack<GitletTree> currentBranchStack = new Stack<GitletTree>();
		GitletTree tempTree = activeTree;
		while (tempTree != splitTree) {
			currentBranchStack.push(tempTree);
			tempTree = tempTree.getParent();
		}
		GitletTree firstActiveTree = currentBranchStack.pop();
		HashMap<String, File> givenFiles = givenTree.getCommit().getFiles();
		HashMap<String, File> firstFiles = firstActiveTree.getCommit().getFiles();
		HashMap<String, File> propFiles = new HashMap<String, File>();
		HashMap<String, File> commitFiles = new HashMap<String, File>();
		rebaseFileHandler(firstFiles, propFiles, commitFiles, givenFiles);
		for (String s : givenFiles.keySet()) {
			if (!firstFiles.keySet().contains(s)) {
				commitFiles.put(s, givenFiles.get(s));
				propFiles.put(s, givenFiles.get(s));
			}
		}
		tempTree = givenTree;
		Commit firstCommit = new Commit(commitFiles, idcounter, firstActiveTree.getCommit().getMessage(), new Date());
		idcounter++;
		GitletTree firstNewTree = new GitletTree(firstCommit);
		tempTree.addBranch(firstNewTree);
		tempTree = firstNewTree;
		while (!currentBranchStack.isEmpty()) {
			HashMap<String, File> currentCommitFiles = new HashMap<String, File>();
			GitletTree currentTree = currentBranchStack.pop();
			HashMap<String, File> currentFiles = currentTree.getCommit().getFiles();
			for (String s : currentFiles.keySet()) {
				File currentFile = currentFiles.get(s);
				if (propFiles.containsKey(s)) {
					File propFile = propFiles.get(s);
					if (!sameFile(currentFile, propFile)) {
						currentCommitFiles.put(s, currentFile);
						propFiles.remove(s);
					} else {
						currentCommitFiles.put(s, propFile);
					}
				} else {
					currentCommitFiles.put(s, currentFile);
				}
			}
			for (String s : propFiles.keySet()) {
				if (!currentFiles.containsKey(s)) {
					currentCommitFiles.put(s, propFiles.get(s));
				}
			}
			Commit nextCommit = new Commit(currentCommitFiles, idcounter,
					currentTree.getCommit().getMessage(), new Date());
			idcounter++;
			GitletTree newTree = new GitletTree(nextCommit);
			tempTree.addBranch(newTree);
			tempTree = newTree;
		}
		reset(idcounter - 1);
	}

	/**
	 * Checks for what cases rebase would fail. Prints an error if the branch
	 * with the given name does not exist, if the given branch is the same as
	 * the current, and if the input branch's head is in the history of the
	 * current branch's head. Changes the tree from the given to the active.
	 * 
	 * @param branchName
	 *            : String, name of the branch being rebased.
	 * @param givenTree
	 *            : GitletTree, GitletTree of the branch being rebased.
	 */
	public boolean rebaseCheckFailures(String branchName, GitletTree givenTree) {
		if (!pointers.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return false;
		}
		if (branchName.equals(activeBranch)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return false;
		}
		if (activeTree.containsChild(givenTree)) {
			System.out.println("Already up to date.");
			return false;
		}
		if (givenTree.containsChild(activeTree)) {
			pointers.put(activeBranch, givenTree);
			activeTree = givenTree;
			return false;
		}
		return true;
	}

	/**
	 * Compares files from the most recent commit of current Gitlet tree to
	 * given files. Places in propFiles if the file is propagating through the
	 * branch. Places in commitFiles if it is to be commited in the rebase.
	 * 
	 * @param firstFiles
	 *            : a HashMap with key String and value File objects, contains
	 *            the files of the most recent commit of current Gitlet tree.
	 * @param propFiles
	 *            : a HashMap with key String and value File objects, temporary
	 *            storage for files in the branch.
	 * @param commitFiles
	 *            ; a HashMap with key String and value File objects, files to
	 *            be put into a new commit
	 * @param givenFiles
	 *            : a HashMap with key String and value File objects, contains
	 *            files of the current GitletTree.
	 */
	public void rebaseFileHandler(HashMap<String, File> firstFiles,
			HashMap<String, File> propFiles, HashMap<String, File> commitFiles,
			HashMap<String, File> givenFiles) {
		for (String s : firstFiles.keySet()) {
			File activeFile = firstFiles.get(s);
			if (!givenFiles.keySet().contains(s)) {
				commitFiles.put(s, activeFile);
			} else {
				File givenFile = givenFiles.get(s);
				if (sameFile(activeFile, givenFile)) {
					commitFiles.put(s, givenFile);
					propFiles.put(s, givenFile);
				} else {
					commitFiles.put(s, activeFile);
				}
			}
		}
	}

	/**
	 * Copies the given file to the working director and adds '.conflicted' to
	 * the given file's name. Sets Gitlet into a conflicted state.
	 * 
	 * @param s
	 *            : String, name of the file.
	 * @param f
	 *            : File object.
	 * 
	 * @throws IO
	 *             Exception when the file cannot be created as indicated.
	 */
	public void mergeConflictHandler(String s, File f) {
		conflictedState = true;
		File destination = new File(f.getName() + ".conflicted");
		try {
			destination.createNewFile();
			Files.copy(f.toPath(), destination.toPath(), REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Moves file to the staging area.
	 * 
	 * @param s
	 *            : String, name of the file.
	 * @param f
	 *            : File object.
	 * @return true if the file was added; false otherwise.
	 * 
	 * @throws IOExpection
	 *             when the file cannot be created as indicated.
	 */
	public boolean addToStage(String s, File f) {
		File destination = new File(".gitlet/staging-area/" + s);
		try {
			destination.mkdirs();
			Files.copy(f.toPath(), destination.toPath(), REPLACE_EXISTING);
			stage.put(s, destination);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * checks if two files are the same.
	 * 
	 * @param f1
	 *            : File object
	 * @param f2
	 *            : File object
	 * 
	 * @return true if they are the same; false if otherwise.
	 * 
	 * @throws IOException
	 *             when the file cannot be created as indicated.
	 */
	public static boolean sameFile(File f1, File f2) {
		try {
			byte[] file1 = Files.readAllBytes(f1.toPath());
			byte[] file2 = Files.readAllBytes(f2.toPath());
			if (Arrays.equals(file1, file2)) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Clears the given directory.
	 * 
	 * @param folder
	 *            : File object, directory to be emptied.
	 */
	public static void cleanFolder(File folder) {
		for (File f : folder.listFiles()) {
			if (f.isDirectory()) {
				cleanFolder(f);
			}
			f.delete();
		}
	}

	/**
	 * Method that serializes an object.
	 * 
	 * @param o
	 *            : Object, the object to be serialized.
	 * 
	 * @throws IOException
	 *             when the file cannot be created as indicated.
	 */
	public static void serialize(Object o) {
		if (((Gitlet) o).isInitiated()) {
			try {
				FileOutputStream stateOutput = new FileOutputStream(
						".gitlet/state.ser");
				ObjectOutputStream gitletOutput = new ObjectOutputStream(
						stateOutput);
				gitletOutput.writeObject(o);
				gitletOutput.close();
				stateOutput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Deserializes the Gitlet.
	 * 
	 * @return a deserialized Gitlet if it exists; otherwise returns a new
	 *         instance of Gitlet.
	 * 
	 * @throws FileNotFoundException
	 *             when the file indicated cannot be found.
	 * @throws IOException
	 *             when the file cannot be created as indicated.
	 * @throws ClassNotFoundException
	 *             when the class indicated cannot be found.
	 * 
	 */
	public static Gitlet deserialize() {
		File state = new File(".gitlet/state.ser");
		Gitlet gitlet = new Gitlet();
		if (state.exists()) {
			try {
				FileInputStream stateInput = new FileInputStream(state);
				ObjectInputStream gitletIn = new ObjectInputStream(stateInput);
				gitlet = (Gitlet) gitletIn.readObject();
				stateInput.close();
				gitletIn.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return gitlet;
	}

	/**
	 * Checks if an instance of Gitlet currently exists.
	 * 
	 * @return true if an instance of Gitlet exists; false if it doesn't.
	 * 
	 */
	public static boolean isInitiated() {
		File gitletFolder = new File(".gitlet");
		return gitletFolder.exists();
	}

	/**
	 * Checks if the input is valid.
	 * 
	 * @param args
	 *            : a String array.
	 * @param message
	 *            : a String.
	 * 
	 * @return true if the input is valid; false otherwise.
	 */
	public static boolean inputOk(String[] args, String message) {
		if (args.length > 2) {
			System.out.println("Too many arguments.");
			return false;
		} else if (args.length == 1) {
			System.out.println(message);
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Handles the commands inputed by the user and calls the appropriate Gitlet
	 * method.
	 * 
	 * @param command
	 *            : String, one of the availible commands of Gitlet.
	 * @param args
	 *            : String array, inputs from the command line.
	 */
	public void commands(String command, String[] args) {
		switch (command) {
		case "init":
			if (!isInitiated()) {
				init();
			} else {
				System.err
						.println("A gitlet version control system already exists in the current directory.");
			}
			break;
		case "add":
			if (inputOk(args, "Please enter a file to add.")) {
				add(new File(args[1]));
			}
			break;
		case "commit":
			if (inputOk(args, "Please enter a message.")) {
				commit(args[1]);
			}
			break;
		case "rm":
			if (inputOk(args, "Please enter a file to remove.")) {
				rm(new File(args[1]));
			}
			break;
		case "log":
			log();
			break;
		case "global-log":
			globalLog();
			break;
		case "find":
			if (inputOk(args, "Please enter a message to find.")) {
				find(args[1]);
			}
			break;
		case "status":
			status();
			break;
		case "checkout":
			checkoutHelper(args);
			break;
		case "branch":
			if (inputOk(args, "Please enter a branch name.")) {
				branch(args[1]);
			}
			break;
		case "rm-branch":
			if (inputOk(args, "Please enter a branch to remove.")) {
				rmBranch(args[1]);
			}
			break;
		case "reset":
			if (inputOk(args, "Please enter an id number.")) {
				try {
					int id = Integer.parseInt(args[1]);
					reset(id);
				} catch (IllegalArgumentException e) {
					System.out.println("Please enter a valid id number.");
				}
			}
			break;
		case "merge":
			if (inputOk(args, "Please enter a branch to merge with.")) {
				merge(args[1]);
			}
			break;
		case "rebase":
			if (inputOk(args, "Please enter a branch to rebase with.")) {
				rebase(args[1]);
			}
			break;
		}
	}

	/**
	 * Handles the user input.
	 * 
	 * @param args
	 *            : String array, arguments the user can input.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Please enter a command.");
			return;
		}
		String command = args[0];
		if (!args[0].equals("init") && !isInitiated()) {
			System.err.println("Gitlet must be initiated first.");
			return;
		}
		Gitlet gitlet = deserialize();
		String[] commands;
		if (!gitlet.conflictedState) {
			commands = regularCommands;
		} else {
			commands = conflictedCommands;
		}
		if (Arrays.asList(commands).contains(command)) {
			gitlet.commands(command, args);
		} else if (gitlet.conflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
		} else {
			System.out.println("Command does not exist");
		}
		serialize(gitlet);
	}
}