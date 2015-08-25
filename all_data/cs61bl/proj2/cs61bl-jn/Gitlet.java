import java.io.*;
import java.nio.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import java.util.*;

/**
 * Class for a Gitlet version control object
 * 
 * @author Anish Balaji
 * @author Jerry Li
 * @author MingFang Zhang
 * 
 *         Some code adapted from Joseph Moghadam's Gitlet Test:
 * 
 *         - Recursive File Delete
 * 
 * 
 */

public class Gitlet implements Serializable {

	/* Gitlet instance variables */
	private HashSet<String> stagedFiles;
	private int currId;
	private HashMap<String, Node> branchHeads;
	private HashMap<Integer, Node> nodes;
	private HashMap<String, ArrayList<Integer>> logs;
	private String wd;
	private String gitletDir;
	private Node head;
	private boolean conflictedState;
	private static final String folderName = ".gitlet";
	private String currentBranch;
	private HashSet<String> Untracking;
	private String serializedFile;

	/* METHODS */

	/**
	 * Gitlet Constructor, create objects from the class blueprint.
	 */
	public Gitlet() {
		stagedFiles = new HashSet<String>();
		branchHeads = new HashMap<String, Node>();
		logs = new HashMap<String, ArrayList<Integer>>();
		wd = System.getProperty("user.dir");
		gitletDir = wd + "/" + folderName;
		head = null;
		serializedFile = gitletDir + "/stateSaver.ser";
		currId = 1;
		nodes = new HashMap<Integer, Node>();
		currentBranch = "master";
		Untracking = new HashSet<String>();
		conflictedState = false;
	}

	/**
	 * Check to see if a Gitlet folder has been made. if yes: restores the old
	 * one, if no: makes and returns new Gitlet object.
	 */
	public static Gitlet makeOrRestore() {
		if (!aGitExists()) {
			return new Gitlet();
		} else {
			return readState();
		}
	}

	/**
	 * check whether a file exist
	 * 
	 * @return true if the file stateSaver.ser exist.
	 */
	private static boolean aGitExists() {
		File savedGit = new File(folderName + "/stateSaver.ser");
		return savedGit.exists();
	}

	/**
	 * Save the current state to the stateSaver.ser file
	 * 
	 * @param holder
	 *            A Gitlet that need to be saved
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public static void saveState(Gitlet holder) {
		try {
			ObjectOutput output = new ObjectOutputStream(new FileOutputStream(
					folderName + "/stateSaver.ser"));
			output.writeObject(holder);
		} catch (IOException e) {
			System.err.printf("Error: %s\n", e.toString());
		}
	}

	/**
	 * Get the current state recorded in the sateSaver.ser file
	 * 
	 * @throws IOException
	 *             if an I/O error occurs
	 * @throws ClassNotFoundException
	 *             if an application tries to load in a class that couldn't be
	 *             found
	 */
	public static Gitlet readState() {
		Gitlet git = null;
		try {
			ObjectInput input = new ObjectInputStream(new FileInputStream(
					folderName + "/stateSaver.ser"));
			git = (Gitlet) input.readObject();
		} catch (IOException e) {
			System.err.printf("Error: %s\n", e.toString());
		} catch (ClassNotFoundException e2) {
			System.err.printf("Error: %s\n", e2.toString());
		}
		return git;
	}

	/* COMMAND METHODS */

	/**
	 * Creates a new gitlet version control system in the current directory. If
	 * there is currently a .gitlet file in the working directory, print
	 * "A gitlet version control system already exists in the current directory."
	 */
	public void init() {
		if (conflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		File dir = new File(folderName);
		if (dir.exists()) {
			System.out
					.println("A gitlet version control system already exists in the current directory.");
			return;
		} else {
			dir.mkdir();
			head = new Node(null, 0, "initial commit");
			head.setTracking(new HashMap<String, Boolean>());
			branchHeads.put("master", head);
			nodes.put(0, head);
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(0);
			logs.put("initial commit", list);
			File stage = new File(folderName + "/" + "stage");
			stage.mkdir();
		}
	}

	/**
	 * Adds a copy of the file as it currently exists to the staging area.
	 * 
	 * @param file
	 *            a string that stores the name of the file which need to be
	 *            added.
	 */
	public void add(String file) {
		File dir = new File(file);
		if (dir.exists()) {
			if (Untracking.contains(file) == false) {
				stagedFiles.add(file);
				head.getTracking().put(file, true);
				for (String f : stagedFiles) {
					if (head.getTracking().get(f)) {
						copyHelper(wd + "/" + f, folderName + "/stage/" + f,
								true);
					}
				}
			} else if (Untracking.contains(file)) {
				Untracking.remove(file);
				head.getTracking().put(file, true);
			}
		} else {
			System.out.println("File does not exist.");
		}

	}

	/**
	 * Makes a copy of all files currently in the staging area and puts them in
	 * a commit node folder within the .gitlet folder.
	 * 
	 * @param message
	 *            a string that describes the changes to the files in the
	 *            commit.
	 */
	public void commit(String message) {
		if (stagedFiles.isEmpty()) {
			System.out.println("No changes added to the commit.");
			return;
		}
		if (message.equals("")) {
			System.out.println("Please enter a commit message.");
			return;
		}
		Node commit = new Node(head, currId, message);
		commit.setFiles(stagedFiles);
		HashMap<String, String> allTracked = new HashMap<String, String>();
		if (head == null) {
			for (String file : stagedFiles) {
				allTracked.put(file, folderName + "/" + currId + "/" + file);
			}
		} else {
			HashMap<String, String> referencePaths = head.getFilePaths();
			for (String file : referencePaths.keySet()) {
				if (head.getTracking().get(file) == null
						|| head.getTracking().get(file)) {
					allTracked.put(file, referencePaths.get(file));
				}
			}
			for (String file : stagedFiles) {
				allTracked.put(file, folderName + "/" + currId + "/" + file);
			}
		}
		commit.setFilePaths(allTracked);
		head = commit;
		File dir = new File(folderName + "/" + currId);
		dir.mkdir();
		for (String file : stagedFiles) {
			copyHelper(folderName + "/stage/" + file, folderName + "/" + currId
					+ "/" + file, true);
		}
		branchHeads.put(currentBranch, commit);
		if (logs.containsKey(message)) {
			logs.get(message).add(currId);
		} else {
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(currId);
			logs.put(message, list);
		}
		nodes.put(currId, commit);
		currId += 1;
		stagedFiles = new HashSet<String>();
		File stage = new File(folderName + "/stage");
		recursiveDelete(stage);
		stage.mkdir();
		conflictedState = false;
		Untracking = new HashSet<String>();
	}

	/**
	 * Mark the file for untracking. If the file had been staged, instead just
	 * unstage it, and don't also mark it for untracking.
	 * 
	 * @param file
	 *            a string that stores the name of the file which need to be
	 *            removed.
	 */
	public void remove(String file) {
		if (stagedFiles.contains(file)) {
			Path a = Paths.get(folderName + "/stage/" + file);
			stagedFiles.remove(file);
			try {
				Files.deleteIfExists(a);
			} catch (IOException e) {
				// Do nothing
			}
		} else if (head.getTracking().get(file) == null) {
			System.out.println("No reason to remove the file.");
			return;
		} else if (Untracking.contains(file) == false) {
			Untracking.add(file);
			head.getTracking().put(file, false);
		} else {
			System.out.println("No reason to remove the file.");
		}
	}

	/**
	 * Display information about each commit backwards along the commit tree
	 * from the current head commit to the initial commit.
	 */
	public void log() {
		Node pointer = head;
		while (pointer != null) {
			pointer.printLog();
			pointer = pointer.getParent();
		}
	}

	/**
	 * Some code adapted from Joseph Moghadam: Deletes the file, and all files
	 * inside it if it is a directory.
	 *
	 * @param name
	 *            File name to be deleted.
	 * 
	 */
	private static void recursiveDelete(File name) {
		try {
			if (name.isDirectory()) {
				for (File f : name.listFiles()) {
					recursiveDelete(f);
				}
			}
			if (!name.delete()) {
				throw new IOException("Failed to delete file " + name.getPath());
			}
		} catch (IOException e) {
			// Do Nothing
		}
	}

	/**
	 * Display information about all commits ever made
	 */
	public void globalLog() {
		for (int i = currId - 1; i >= 0; i--) {
			nodes.get(i).printLog();
		}
	}

	/**
	 * Prints out the id of the commit that has the given commit message.
	 * 
	 * @param message
	 *            a string that records the message input by each commit
	 */
	public void find(String message) {
		ArrayList<Integer> ids = logs.get(message);
		if (ids != null) {
			for (int i : ids) {
				System.out.println(i);
			}
		} else {
			System.out.println("Found no commit with that message.");
		}
	}

	/**
	 * Display branches, Staged Files and Files Marked for Untracking in the
	 * prescribed form.
	 */
	public void status() {
		System.out.println("=== Branches ===");
		Set<String> keys = branchHeads.keySet();
		for (String key : keys) {
			if (currentBranch.equals(key)) {
				System.out.print("*");
			}
			System.out.println(key);
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		for (String file : stagedFiles) {
			System.out.println(file);
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (String file : Untracking) {
			System.out.println(file);
		}
		System.out.println();
	}

	/**
	 * Puts the version of the file that exists in the head commit to the
	 * working directory, and overwrite the version of the file that's already
	 * there if there is one.
	 * 
	 * @param file
	 *            a string that stores the name of the file.
	 */
	public void checkoutA(String file) {
		if (!head.getFilePaths().keySet().contains(file)) {
			System.out
					.println("File does not exist in the most recent commit, or no such branch exists.");
			return;
		}
		copyHelper(head.getFilePaths().get(file), wd + "/" + file, false);
	}

	/**
	 * Put version of the file with the given id to the working directory, and
	 * overwrite the version of the file that's already there if there is one
	 * 
	 * @param file
	 *            a string that stores the name of the file.
	 * @param id
	 *            a integer that stores the given commit's ID number
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public void checkoutB(int id, String file) {
		if (!nodes.keySet().contains(id)) {
			System.out.println("No commit with that id exists.");
			return;
		}
		if (!nodes.get(id).getFilePaths().keySet().contains(file)) {
			System.out.println("File does not exist in that commit.");
			return;
		}
		Node commit = nodes.get(id);
		copyHelper(commit.getFilePaths().get(file), wd + "/" + file, false);
	}

	/**
	 * Puts all files that at the head of the given branch in the working
	 * directory, overwrite the versions of the files that are already there if
	 * they exist, and the given branch will now be considered the current
	 * branch.
	 * 
	 * @param branch
	 *            a string that stores the name of the branch.
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public void checkoutC(String branch) {
		if (!branchHeads.keySet().contains(branch)) {
			System.out
					.println("File does not exist in the most recent commit, or no such branch exists.");
			return;
		}
		if (branch.equals(currentBranch)) {
			System.out.println("No need to checkout the current branch.");
			return;
		}
		Node commit = branchHeads.get(branch);
		for (String file : commit.getFilePaths().keySet()) {
			copyHelper(commit.getFilePaths().get(file), wd + "/" + file, false);
		}
		head = commit;
		currentBranch = branch;
	}

	/**
	 * Moves the current branch's head to that commit node and checks out all
	 * the files tracked by the given commit.
	 * 
	 * @param id
	 *            an integer that stores the given commit's ID number
	 */
	public void reset(int id) {
		if (conflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		Node commit = nodes.get(id);
		if (commit != null) {
			for (String file : commit.getFiles()) {
				checkoutA(file);
			}
			head = commit;
			branchHeads.put(currentBranch, commit);
		} else {
			System.out.println("No commit with that id exists.");
		}

	}

	/**
	 * Creates a new branch with the given name, and points it at the current
	 * head node.
	 * 
	 * @param name
	 *            a string that describes the name of the new branch.
	 */
	public void branch(String name) {
		if (conflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		if (branchHeads.keySet().contains(name)) {
			System.out.println("A branch with that name already exists.");
			return;
		}
		branchHeads.put(name, head);
	}

	/**
	 * Deletes the branch with the given name
	 * 
	 * @param name
	 *            A string that records the name of the branch which need to be
	 *            removed.
	 */
	public void removeBranch(String name) {
		if (conflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		if (branchHeads.keySet().contains(name)) {
			if (currentBranch.equals(name)) {
				System.out.println("Cannot remove the current branch.");
			} else {
				branchHeads.remove(name);
			}
		} else {
			System.out.println("A branch with that name does not exist.");
		}
	}

	/**
	 * Merge the current Branch with the input branch name. If there are files
	 * that have been modified in both the current branch and the given branch;
	 * copy the files in with a .confliced ending. If they have just been
	 * modified in the current branch; copy the checkout the file.
	 * 
	 * @param branch
	 *            The branch to be merged with the currentBranch.
	 */
	public void merge(String branch) {
		if (conflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		if (!branchHeads.keySet().contains(branch)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (branch.equals(currentBranch)) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}
		Node bHead = branchHeads.get(branch);
		Node split = splitFinder(branch, currentBranch);
		int bId = bHead.getID();
		HashMap<String, String> splitFiles, bFiles, currFiles, modB, modCurr;
		splitFiles = split.getFilePaths();
		bFiles = bHead.getFilePaths();
		currFiles = head.getFilePaths();
		modB = hasModSince(splitFiles, bFiles);
		modCurr = hasModSince(splitFiles, currFiles);
		boolean genConflicted = false;
		for (String f : modB.keySet()) {
			if (!modCurr.keySet().contains(f)) {
				checkoutB(bId, f);
				add(f);
			} else {
				genConflicted = true;
				copyHelper(bHead.getFilePaths().get(f), wd + "/" + f
						+ ".conflicted", false);
			}
		}
		if (!genConflicted) {
			commit("Merged " + currentBranch + " with " + branch + ".");
		} else {
			System.out.println("Encountered a merge conflict.");
			conflictedState = true;
		}
	}

	/**
	 * rebase the input branch in front of the current branch by replaying all
	 * of the commits since the split.
	 * 
	 * @param branch
	 *            The desired branch to rebase.
	 */
	public void rebase(String branch) {
		if (conflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		} else if (!branchHeads.keySet().contains(branch)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (branch.equals(currentBranch)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		} else if (isInHistory(currentBranch, branch)) {
			System.out.println("Already up-to-date.");
			return;
		} else if (isInHistory(branch, currentBranch)) {
			branchHeads.put(currentBranch, branchHeads.get(branch));
			head = branchHeads.get(branch);
			return;
		}
		Node bHead = branchHeads.get(branch);
		Node split = splitFinder(branch, currentBranch);
		HashMap<String, String> splitFiles, bFiles, modB;
		splitFiles = split.getFilePaths();
		bFiles = bHead.getFilePaths();
		modB = hasModSince(splitFiles, bFiles);
		Stack<Node> startingFromSplit = new Stack<Node>();
		Node temp = head;
		while (temp != split) {
			startingFromSplit.push(temp);
			temp = temp.getParent();
		}
		Node prev = branchHeads.get(branch);
		while (!startingFromSplit.isEmpty()) {
			Node replay = startingFromSplit.pop();
			Node commit = new Node(prev, currId, replay.getCommitMessage());
			commit.setFiles(replay.getFiles());
			commit.setFilePaths(replay.getFilePaths());
			File dir = new File(folderName + "/" + currId + "");
			dir.mkdir();
			for (String file : replay.getFiles()) {
				String start = folderName + "/" + replay.getID() + "/" + file;
				String end = folderName + "/" + currId + "/" + file;
				if (modB.keySet().contains(file)) {
					start = modB.get(file);
				}
				copyHelper(start, end, true);
			}
			logs.get(replay.getCommitMessage()).add(currId);
			nodes.put(currId, commit);
			prev = commit;
			head = commit;
			currId += 1;
		}
		reset(currId - 1);
	}

	/* HELPER METHODS */

	/**
	 * @param outBranch
	 *            the branch that may contain the inner branch in its history.
	 * @param inBranch
	 *            the alleged branch within the outer's history.
	 * @return true if inBranch is in the history of outBranch
	 */
	public boolean isInHistory(String outBranch, String inBranch) {
		Node out = branchHeads.get(outBranch);
		Node in = branchHeads.get(inBranch);
		while (out != null) {
			if (out == in) {
				return true;
			}
			out = out.getParent();
		}
		return false;
	}

	/**
	 * finds file that have been modified between two sets of files
	 * 
	 * @param splitFiles
	 *            HashMap that has the file to path pairs tracked by the
	 *            splitPoint
	 * @param branchFiles
	 *            HashMap that has the file to path pairs tracked by a
	 *            branchHead
	 * @return A HashMap with the all of the files that are tracked in
	 *         splitPoints and have been modified in the given branchHead.
	 */
	public HashMap<String, String> hasModSince(
			HashMap<String, String> splitFiles,
			HashMap<String, String> branchFiles) {
		HashMap<String, String> mod = new HashMap<String, String>();
		for (String f : splitFiles.keySet()) {
			if (branchFiles.keySet().contains(f)) {
				try {
					Path s = Paths.get(splitFiles.get(f));
					byte[] sBytes = Files.readAllBytes(s);
					Path b = Paths.get(branchFiles.get(f));
					byte[] bBytes = Files.readAllBytes(b);
					if (!Arrays.equals(sBytes, bBytes)) {
						mod.put(f, branchFiles.get(f));
					}
				} catch (IOException e) {
					System.out.println("Couldn't read bytes");
				}
			}
		}
		return mod;
	}

	/**
	 * Copy a file from the start place's path to the destination path.
	 * 
	 * @param start
	 *            String representing starting point path
	 * @param end
	 *            String representing destination point path
	 * @param mkdir
	 *            boolean that represents whether parents dirs shoudl be made in
	 *            the copy
	 */
	public void copyHelper(String start, String end, boolean mkdir) {
		Path a = Paths.get(start);
		Path b = Paths.get(end);
		if (mkdir) {
			File local = new File(end);
			local.getParentFile().mkdirs();
		}
		try {
			Files.copy(a, b, REPLACE_EXISTING, COPY_ATTRIBUTES);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/**
	 * Find the node that joins two branches
	 * 
	 * @param b1
	 *            The first branch name
	 * @param b2
	 *            The second branch name
	 * @return The node that is splitPoint between the two branches.
	 */
	public Node splitFinder(String b1, String b2) {
		HashSet<Node> history = new HashSet<Node>();
		Node reference1 = branchHeads.get(b1);
		while (reference1 != null) {
			history.add(reference1);
			reference1 = reference1.getParent();
		}
		Node reference2 = branchHeads.get(b2);
		while (reference2 != null) {
			if (history.contains(reference2)) {
				return reference2;
			}
			reference2 = reference2.getParent();
		}
		return null;
	}

	/**
	 * Figure out which type of checkout to use based on the String[] from the
	 * main method, then call the appropriate method.
	 * 
	 * @param args
	 *            The String[] passed in from the main method
	 */
	public void checkoutHelper(String[] args) {
		if (args.length == 2) {
			if (branchHeads.keySet().contains(args[1])) {
				if (conflictedState) {
					System.out
							.println("Cannot do this command until the merge conflict has been resolved.");
					return;
				}
				checkoutC(args[1]);
			} else {
				checkoutA(args[1]);
			}
		} else if (args.length == 3) {
			try {
				checkoutB(Integer.parseInt(args[1]), args[2]);
			} catch (NumberFormatException e) {
			}
		}
	}

	/**
	 * Responds to user CLI commands, and executes appropriate gitlet commands.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please enter a command.");
			return;
		}
		Gitlet git = makeOrRestore();
		String command = args[0];
		if (command.equals("init")) {
			git.init();
		} else if (command.equals("add")) {
			git.add(args[1]);
		} else if (command.equals("rm")) {
			git.remove(args[1]);
		} else if (command.equals("commit")) {
			try {
				git.commit(args[1]);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Please enter a commit message.");
			}
		} else if (command.equals("log")) {
			git.log();
		} else if (command.equals("global-log")) {
			git.globalLog();
		} else if (command.equals("find")) {
			git.find(args[1]);
		} else if (command.equals("status")) {
			git.status();
		} else if (command.equals("checkout")) {
			git.checkoutHelper(args);
		} else if (command.equals("branch")) {
			git.branch(args[1]);
		} else if (command.equals("rm-branch")) {
			git.removeBranch(args[1]);
		} else if (command.equals("reset")) {
			git.reset(Integer.parseInt(args[1]));
		} else if (command.equals("merge")) {
			git.merge(args[1]);
		} else if (command.equals("rebase")) {
			git.rebase(args[1]);
		} else {
			System.out.println("No command with that name exists.");
		}
		saveState(git);
	}
}
