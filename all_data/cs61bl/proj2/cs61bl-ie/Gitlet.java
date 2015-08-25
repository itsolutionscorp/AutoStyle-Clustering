import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.nio.file.*;

public class Gitlet implements Serializable {

	// Stores the file name as key and the file path as value
	private HashMap<String, File> trackFiles = new HashMap<String, File>();
	private HashMap<String, File> untrackFiles = new HashMap<String, File>();
	// Stores the branch name as key and the current branch node as value
	private HashMap<String, CommitNode> branches = new HashMap<String, CommitNode>();
	private HashMap<Integer, CommitNode> allNodes = new HashMap<Integer, CommitNode>();
	private HashMap<String, ArrayList<Integer>> messages = new HashMap<String, ArrayList<Integer>>();
	private int commitIDNumber = 0;
	private CommitNode head;
	private String currentBranch;
	private boolean conflicted;

	/**
	 * Takes in command line arguments and runs git
	 * 
	 * @param args
	 *            commands corresponding to git methods
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please enter a command.");
			return;
		}
		Gitlet git = loadGit();
		String command = args[0];
		if (!git.conflicted) {
			if (command.equals("init")) {
				git.init();
			} else if (command.equals("del")) {
				git.deleteDirectory(new File(".gitlet/stage/test_files/"));
			} else if (command.equals("add")) {
				git.add(args[1]);
			} else if (command.equals("commit")) {
				if (args.length == 1) {
					System.out.println("Please enter a commit message.");
					return;
				}
				git.commit(args[1]);
			} else if (command.equals("rm")) {
				git.rm(args[1]);
			} else if (command.equals("log")) {
				git.log(git.head);
			} else if (command.equals("global-log")) {
				git.globalLog();
			} else if (command.equals("find")) {
				git.find(args[1]);
			} else if (command.equals("status")) {
				git.status();
			} else if (command.equals("checkout")) {
				if (args.length == 3) {
					git.checkout2(args[1], args[2]);
				} else if (git.branches.containsKey(args[1])) {
					git.checkout3(args[1]);
				} else {
					git.checkout1(args[1]);
				}
			} else if (command.equals("branch")) {
				git.branch(args[1]);
			} else if (command.equals("rm-branch")) {
				git.rmBranch(args[1]);
			} else if (command.equals("reset")) {
				git.reset(args[1]);
			} else if (command.equals("merge")) {
				git.merge(args[1]);
			} else if (command.equals("rebase")) {
				git.rebase(args[1]);
			} else {
				System.out.println("No command with that name exists.");
			}
		} else {
			git.conflictedMoves(args, git);
			git.conflicted = false;
		}
		saveGit(git);
	}

	/**
	 * Gives conflicted commands that can be used
	 */
	public void conflictedMoves(String[] args, Gitlet git) {
		String command = args[0];
		if (command.equals("add")) {
			git.add(args[1]);
		} else if (command.equals("rm")) {
			git.rm(args[1]);
		} else if (command.equals("commit")) {
			if (args.length == 1) {
				System.out.println("Please enter a commit message.");
				return;
			}
			git.commit(args[1]);
		} else if (command.equals("checkout")) {
			if (args.length == 3) {
				git.checkout2(args[1], args[2]);
			} else {
				git.checkout1(args[1]);
			}
		} else if (command.equals("log")) {
			git.log(git.head);
		} else if (command.equals("global-log")) {
			git.globalLog();
		} else if (command.equals("find")) {
			git.find(args[1]);
		} else if (command.equals("status")) {
			git.status();
		} else {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
		}
	}

	/**
	 * Deserializes git and reads the file to be loaded
	 */
	private static Gitlet loadGit() {
		Gitlet git = new Gitlet();
		File myGitFile = new File(".gitlet" + File.separator + "myGit.ser");
		if (myGitFile.exists()) {
			try {
				FileInputStream fileIn = new FileInputStream(myGitFile);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				git = (Gitlet) objectIn.readObject();
				objectIn.close();
			} catch (IOException e) {
				String msg = "IOException while loading myGit.";
				System.out.println(msg);
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				String msg = "ClassNotFoundException while loading myGit.";
				System.out.println(msg);
			}
		}
		return git;
	}

	/**
	 * Serializes git and saves the file to be later loaded
	 * 
	 * @param git
	 *            a Gitlet object that is passed in to be serialized
	 */
	private static void saveGit(Gitlet git) {
		try {
			File myGitFile = new File(".gitlet" + File.separator + "myGit.ser");
			FileOutputStream fileOut = new FileOutputStream(myGitFile);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(git);
			objectOut.close();
		} catch (IOException e) {
			String msg = "IOException while saving myGit.";
			System.out.println(msg);
			e.printStackTrace();
		}
	}

	/**
	 * Initializes a gitlet directory as long as one does not already exist
	 * Within this gitlet directory creates an initial commit folder or node
	 * with id 0
	 */
	public void init() {
		if (Files.exists(Paths.get(".gitlet"))) {
			System.out
					.println("A gitlet version control system already exists in the current directory.");
		} else {
			File git = new File(".gitlet");
			git.mkdir();
			File stage = new File(".gitlet" + File.separator + "stage");
			stage.mkdir();
			File commit0 = new File(".gitlet" + File.separator + "Commit0");
			commit0.mkdir();
			head = new CommitNode("initial commit", commitIDNumber,
					LocalDateTime.now(), new HashMap<String, File>(), null);
			commitIDNumber++;
			branches.put("master", head);
			allNodes.put(head.getID(), head);
			currentBranch = "master";
			conflicted = false;
			messages.put("initial commit", new ArrayList<Integer>());
			messages.get("initial commit").add(0);
		}
	}

	/**
	 * Adds files to the stage to prepare for a commit
	 * 
	 * @param fileName
	 *            a String that corresponds to a file to be committed
	 */
	public void add(String fileName) {
		File addFile = new File(fileName);
		if (untrackFiles.containsKey(addFile.toString())) {
			untrackFiles.remove(addFile.toString());
		} else {
			Path target = Paths.get(".gitlet" + File.separator + "stage"
					+ File.separator + addFile.toString());
			if (addFile.getParent() != null) {
				File nestedDirs = new File(".gitlet" + File.separator + "stage"
						+ File.separator + addFile.getParent());
				nestedDirs.mkdirs();
			}
			try {
				Files.copy(addFile.toPath(), target);
				trackFiles.put(fileName, addFile);
			} catch (Exception e) {
				System.out.println("File does not exist.");
			}
		}
	}

	/**
	 * If there are files in the stage, copies the files from the stage to a new
	 * commit folder. If there are files in the untrackFiles hashset the commit
	 * does not store them.
	 * 
	 * @param message
	 *            a message to be saved with the commit node
	 */
	public void commit(String message) {
		File stage = new File(".gitlet" + File.separator + "stage");
		if (stage.listFiles().length != 0) {
			commitStageFull(stage, message);
		} else if (!untrackFiles.isEmpty()) {
			commitUntrack(message);
		} else {
			System.out.println("No changes added to the commit.");
		}
	}

	/**
	 * Helper function for commit when the stage has files present
	 * 
	 * @param stage
	 *            a file representing the stage
	 * @param message
	 *            a string representing the commit message
	 */
	public void commitStageFull(File stage, String message) {
		LocalDateTime time = LocalDateTime.now();
		HashMap<String, File> commitHashMap = new HashMap<String, File>();
		File commitFile = new File(".gitlet" + File.separator + "Commit"
				+ commitIDNumber);
		commitFile.mkdir();
		for (String s : head.getFileNames()) {
			commitHashMap.put(s, head.getFile(s));
		}
		for (String s : untrackFiles.keySet()) {
			commitHashMap.remove(s);
		}
		for (File file : trackFiles.values()) {
			Path source = Paths.get(".gitlet" + File.separator + "stage"
					+ File.separator + file.toString());
			File targetFile = new File(".gitlet" + File.separator + "Commit"
					+ commitIDNumber + File.separator + file.toString());
			if (file.getParent() != null) {
				File nestedDirs = new File(".gitlet" + File.separator
						+ "Commit" + commitIDNumber + File.separator
						+ file.getParent());
				nestedDirs.mkdirs();
			}
			try {
				Files.copy(source, targetFile.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
			commitHashMap.put(file.toString(), targetFile);
		}
		for (File f : stage.listFiles()) {
			deleteDirectory(f);
		}
		head = new CommitNode(message, commitIDNumber, time, commitHashMap,
				head);
		commitIDNumber++;
		branches.put(currentBranch, head);
		trackFiles = new HashMap<String, File>();
		untrackFiles = new HashMap<String, File>();
		allNodes.put(head.getID(), head);
		if (!messages.containsKey(message)) {
			messages.put(message, new ArrayList<Integer>());
			messages.get(message).add(head.getID());
		} else {
			messages.get(message).add(head.getID());
		}
	}

	/**
	 * Helper function for commit when no files are staged, but files are
	 * untracked
	 * 
	 * @param message
	 *            a string representing the commit message
	 */
	public void commitUntrack(String message) {
		LocalDateTime time = LocalDateTime.now();
		HashMap<String, File> commitHashMap = new HashMap<String, File>();
		File commitFile = new File(".gitlet" + File.separator + "Commit"
				+ commitIDNumber);
		commitFile.mkdir();
		for (String s : head.getFileNames()) {
			if (!untrackFiles.containsKey(s)) {
				commitHashMap.put(s, head.getFile(s));
			}
		}
		head = new CommitNode(message, commitIDNumber, time, commitHashMap,
				head);
		commitIDNumber++;
		trackFiles = new HashMap<String, File>();
		untrackFiles = new HashMap<String, File>();
		allNodes.put(head.getID(), head);
		if (!messages.containsKey(message)) {
			messages.put(message, new ArrayList<Integer>());
			messages.get(message).add(head.getID());
		} else {
			messages.get(message).add(head.getID());
		}
	}

	/**
	 * Marks a file for untracking, does not actually delete it
	 * 
	 * @param fileName
	 *            a String corresponding to a file that will be marked for
	 *            untracking
	 */
	public void rm(String fileName) {
		File rmFile = new File(fileName);
		if (Files.exists(Paths.get(".gitlet" + File.separator + "stage"
				+ File.separator + rmFile.toString()))) {
			try {
				Files.delete(Paths.get(".gitlet" + File.separator + "stage"
						+ File.separator + rmFile.toString()));
				trackFiles.remove(rmFile.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (head.getFile(rmFile.toString()) != null) {
			untrackFiles.put(rmFile.toString(),
					trackFiles.get(rmFile.toString()));
			trackFiles.remove(rmFile.toString());
		} else {
			System.out.println("No reason to remove the file.");
		}
	}

	/**
	 * Follows a certain commit's path in chronological order back towards the
	 * parent and returns a log of each of those commits
	 * 
	 * @param commit
	 *            a CommitNode that corresponds to the starting point of the
	 *            log's path
	 */
	public void log(CommitNode commit) {
		while (commit != null) {
			System.out.println("===");
			System.out.println("Commit " + commit.getID());
			System.out.println(commit.getTimeStamp());
			System.out.println(commit.getMessage());
			commit = commit.getParent();
			if (commit != null) {
				System.out.println();
			}
		}
	}

	/**
	 * Prints out all commits ever made, including ID, time stamp and message
	 */
	public void globalLog() {
		int index = 0;
		int size = allNodes.size();
		for (CommitNode node : allNodes.values()) {
			System.out.println("===");
			System.out.println("Commit " + node.getID());
			System.out.println(node.getTimeStamp());
			System.out.println(node.getMessage());
			if (index < size) {
				System.out.println("");
			}
		}
	}

	/**
	 * Given a certain message, will locate the corresponding commit's id
	 * 
	 * @param commitMessage
	 *            a String used to locate the correct commit
	 */
	public void find(String commitMessage) {
		if (messages.get(commitMessage) == null) {
			System.out.println("Found no commit with that message.");
		} else {
			for (int i : messages.get(commitMessage)) {
				System.out.println(i);
			}
		}
	}

	/**
	 * Displays the current status of the branches, staged files, and untracked
	 * files Also indicates which is the current active branch
	 */
	public void status() {
		System.out.println("=== Branches ===");
		for (String s : branches.keySet()) {
			if (currentBranch.equals(s)) {
				System.out.println("*" + s);
			} else {
				System.out.println(s);
			}
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		for (File f : trackFiles.values()) {
			System.out.println(f);
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (String s : untrackFiles.keySet()) {
			System.out.println(s);
		}
	}

	/**
	 * Checks out the file as it exists in the current head commit
	 * 
	 * @param fileName
	 *            a string representing the file to be checked out
	 */
	public void checkout1(String fileName) {
		File checkoutFile = new File(fileName);
		Path target = checkoutFile.toPath();
		if (head.getFile(checkoutFile.toString()) == null) {
			System.out
					.println("File does not exist in the most recent commit, or no such branch exists.");
		} else {
			Path source = head.getFile(checkoutFile.toString()).toPath();
			try {
				if (!checkoutFile.getParentFile().exists()) {
					File nestedDirs = new File(checkoutFile.getParent());
					nestedDirs.mkdirs();
				}
				Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
				if (conflicted) {
					conflicted = false;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Checkouts the given file as it exists in the commit with the given id
	 * 
	 * @param commitID
	 *            a string representing the id of a CommitNode
	 * @param fileName
	 *            a string representing the file to be checked out
	 */
	public void checkout2(String commitID, String fileName) {
		File checkoutFile = new File(fileName);
		CommitNode checkoutNode = allNodes.get(Integer.parseInt(commitID));
		if (checkoutNode == null) {
			System.out.println("No commit with that id exists.");
		} else if (checkoutNode.getFile(checkoutFile.toString()) == null) {
			System.out.println("File does not exist in that commit.");
		} else {
			Path source = checkoutNode.getFile(checkoutFile.toString())
					.toPath();
			Path target = checkoutFile.toPath();
			try {
				if (!checkoutFile.getParentFile().exists()) {
					File nestedDirs = new File(checkoutFile.getParent());
					nestedDirs.mkdirs();
				}
				Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
				if (conflicted) {
					conflicted = false;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Checks out all files in the commit at the head of the given branch
	 * 
	 * @param branchName
	 *            a string representing the branch to be checked out
	 */
	public void checkout3(String branchName) {
		CommitNode checkoutNode = branches.get(branchName);
		if (checkoutNode == null) {
			System.out
					.println("File does not exist in the most recent commit, or no such branch exists.");
		} else if (currentBranch.equals(branchName)) {
			System.out.println("No need to checkout the current branch.");
		} else {
			for (String s : checkoutNode.getFileNames()) {
				File checkoutFile = new File(s);
				Path source = checkoutNode.getFile(checkoutFile.toString())
						.toPath();
				Path target = checkoutFile.toPath();
				try {
					if (!checkoutFile.getParentFile().exists()) {
						File nestedDirs = new File(checkoutFile.getParent());
						nestedDirs.mkdirs();
					}
					Files.copy(source, target,
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		head = checkoutNode;
		currentBranch = branchName;
		branches.put(currentBranch, head);
	}

	/**
	 * Creates a new branch with the given name and points it at the current
	 * head CommitNode
	 * 
	 * @param branchName
	 *            a string representing the name of the branch to be created
	 */
	public void branch(String branchName) {
		if (branches.containsKey(branchName)) {
			System.out.println("A branch with that name already exists.");
		} else {
			branches.put(branchName, head);
		}
	}

	/**
	 * Removes the branch with the given name
	 * 
	 * @param branchName
	 *            a string representing the name of the branch to be removed
	 */
	public void rmBranch(String branchName) {
		if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
		} else if (currentBranch.equals(branchName)) {
			System.out.println("Cannot remove the current branch.");
		} else {
			branches.remove(branchName);
		}
	}

	/**
	 * Checks out all files tracked by the given commit id
	 * 
	 * @param commitID
	 *            a string representing the id of the CommitNode to check out
	 */
	public void reset(String commitID) {
		CommitNode resetNode = allNodes.get(Integer.parseInt(commitID));
		if (resetNode == null) {
			System.out.println("No commit with that id exists.");
		} else {
			for (String s : resetNode.getFileNames()) {
				File checkoutFile = new File(s);
				Path source = resetNode.getFile(checkoutFile.toString())
						.toPath();
				Path target = checkoutFile.toPath();
				try {
					Files.copy(source, target,
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			head = resetNode;
			branches.put(currentBranch, head);
		}
	}

	/**
	 * Merges files from the given branch to the current branch
	 * 
	 * @param branchName
	 *            a string representing the branch to be merged
	 */
	public void merge(String branchName) {
		if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
		} else if (branchName.equals(currentBranch)) {
			System.out.println("Cannot merge a branch with itself.");
		}
		int split = splitPoint(currentBranch, branchName);
		CommitNode splitNode = allNodes.get(split);
		CommitNode mergeNode = branches.get(branchName);
		HashSet<String> modifiedMerge = new HashSet<String>();
		HashSet<String> modifiedHead = new HashSet<String>();
		HashSet<String> removedHead = new HashSet<String>();
		for (String s : splitNode.getFileNames()) {
			if (mergeNode.getFileMap().containsKey(s)) {
				if (!sameFiles(mergeNode.getFile(s).toPath(), splitNode
						.getFile(s).toPath())) {
					modifiedMerge.add(s);
				}
			}
			if (head.getFileMap().containsKey(s)) {
				if (!sameFiles(head.getFile(s).toPath(), splitNode.getFile(s)
						.toPath())) {
					modifiedHead.add(s);
				}
			} else {
				removedHead.add(s);
			}
		}
		for (String f : modifiedMerge) {
			if (!modifiedHead.contains(f)) {
				checkout2(Integer.toString(mergeNode.getID()), f);
				add(f);
			} else if (modifiedHead.contains(f)) {
				File mergeFile = mergeNode.getFile(f);
				try {
					Files.copy(mergeFile.toPath(),
							Paths.get(f + ".conflicted"));
					conflicted = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (removedHead.contains(f)) {
				checkout2(Integer.toString(mergeNode.getID()), f);
				add(f);
			}
		}
		if (conflicted) {
			System.out.println("Encountered a merge conflict.");
		} else {
			commit("Merged " + currentBranch + " with " + branchName + ".");
		}
	}

	/**
	 * Reattaches the current branch to the head of the given branch
	 * 
	 * @param branchName
	 *            a string representing the branch to attach to
	 */
	public void rebase(String branchName) {
		if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
		} else if (currentBranch.equals(branchName)) {
			System.out.println("Cannot rebase a branch onto itself.");
		} else if (rebasehistory(branchName, currentBranch)) {
			System.out.println("Already up-to-date.");
		} else if (rebasehistory(currentBranch, branchName)) {
			head = branches.get(branchName);
			currentBranch = branchName;
		} else {
			int splitID = splitPoint(branchName, currentBranch);
			CommitNode givenNode = branches.get(branchName);
			CommitNode splitNode = allNodes.get(splitID);
			Stack<CommitNode> branchHistory = new Stack<CommitNode>();
			CommitNode copyNodes = head;
			while (splitID != copyNodes.getID()) {
				branchHistory.push(copyNodes);
				copyNodes = copyNodes.getParent();
			}
			HashSet<String> modifiedGiven = new HashSet<String>();
			for (String s : splitNode.getFileNames()) {
				if (givenNode.getFileMap().containsKey(s)) {
					if (!sameFiles(givenNode.getFile(s).toPath(), splitNode
							.getFile(s).toPath())) {
						modifiedGiven.add(s);
					}
				} else {
					modifiedGiven.add(s);
				}
			}
			head = givenNode;
			while (!branchHistory.empty()) {
				CommitNode copyNode = branchHistory.pop();
				HashMap<String, File> fileMap = new HashMap<String, File>();
				for (String s : copyNode.getFileNames()) {
					fileMap.put(s, copyNode.getFile(s));
				}
				HashSet<String> modifiedCopyNode = new HashSet<String>();
				for (String s : splitNode.getFileNames()) {
					if (fileMap.containsKey(s)) {
						if (!sameFiles(fileMap.get(s).toPath(), splitNode
								.getFile(s).toPath())) {
							modifiedCopyNode.add(s);
						}
					}
				}
				for (String s : modifiedGiven) {
					if (!modifiedCopyNode.contains(s)) {
						fileMap.put(s, givenNode.getFile(s));
					}
				}
				CommitNode newNode = new CommitNode(copyNode.getMessage(),
						commitIDNumber, LocalDateTime.now(), fileMap, head);
				head = newNode;
				allNodes.put(commitIDNumber, newNode);
				messages.get(newNode.getMessage()).add(head.getID());
				commitIDNumber++;
			}
			branches.put(branchName, head);
			reset(commitIDNumber - 1 + "");
		}
	}

	/**
	 * checks if branchName is in the history of currentBranch
	 * 
	 * @param branchName
	 *            a string representing the branchName rebase is calling on
	 * @param currentBranch
	 *            a string representing the current branch
	 * @return returns true if branchName is in the history of currentBranch
	 */
	public boolean rebasehistory(String branchName, String currentBranch) {
		CommitNode node1 = branches.get(branchName);
		CommitNode node2 = branches.get(currentBranch);
		while (node2 != null) {
			if (node1.getID() == node2.getID()) {
				return true;
			} else {
				node2 = node2.getParent();
			}
		}
		return false;
	}

	/**
	 * Recursively deletes all files and directories in a given directory
	 * 
	 * @param dir
	 *            a file that represents a directory to delete
	 */
	public void deleteDirectory(File dir) {
		if (dir.isDirectory()) {
			for (File f : dir.listFiles()) {
				deleteDirectory(f);
			}
		}
		dir.delete();
	}

	/**
	 * Determines the split point between two branches
	 * 
	 * @param branch1
	 *            a string that represents one branch
	 * @param branch2
	 *            a string that represents a second branch
	 * @return returns the id of the splitPoint between two branches
	 */
	public int splitPoint(String branch1, String branch2) {
		CommitNode node1 = branches.get(branch1);
		CommitNode node2 = branches.get(branch2);
		HashSet<Integer> hash1 = new HashSet<Integer>();
		while (node1 != null) {
			hash1.add(node1.getID());
			node1 = node1.getParent();
		}
		while (!hash1.contains(node2.getID())) {
			node2 = node2.getParent();
		}
		return node2.getID();
	}

	/**
	 * Determines whether the files in the two paths are the same files
	 * 
	 * @param path1
	 *            a path representing the first file
	 * @param path2
	 *            a path representing the second file
	 * @return true if the files are the same
	 * @throws IOException
	 */
	public boolean sameFiles(Path path1, Path path2) {
		byte[] byte1 = new byte[0];
		byte[] byte2 = new byte[0];
		try {
			byte1 = Files.readAllBytes(path1);
			byte2 = Files.readAllBytes(path2);
		} catch (IOException e) {
			return false;
		}
		return Arrays.equals(byte1, byte2);
	}
}