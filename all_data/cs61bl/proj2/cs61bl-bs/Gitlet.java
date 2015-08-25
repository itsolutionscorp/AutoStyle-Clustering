import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Gitlet implements Serializable {

	static FileObject gitlet = new FileObject(".gitlet");
	static FileObject stagingArea;
	static FileObject commits;
	static int branchID;
	static HashMap<String, FileObject> staged2 = new HashMap<String, FileObject>();
	static ArrayList<String> untracked = new ArrayList<String>();
	static ArrayList<Branch> allBranches = new ArrayList<Branch>();
	static ArrayList<Commit> allCommits = new ArrayList<Commit>();
	static ArrayList<String> allBranchNames = new ArrayList<String>();
	static CommitNode hstr;
	static Commit mostRecent = null;
	static Branch currentB = null;
	static boolean conflictedState = false;
	static String[] conflictedCommands = { "add", "rm", "commit", "checkout", "log", "global-log", "find", "status" };
	static int numOfCommits = 0;

	/**
	 * Takes in a command and runs Gitlet.
	 * 
	 * @param args
	 *            an array of strings that contain gitlet's commands and any
	 *            arguments(if necessary)
	 */
	public static void main(String[] args) {
		Deser();
		String command = args[0];
		if (conflictedState) {
			conflictedState(command, args);
		}
		if (command.equals("init")) {
			init();
		} else if (command.equals("add")) {
			add(args[1]);
		} else if (command.equals("commit")) {
			if (args.length == 1) {
				System.out.println("Please enter a commit message.");
			} else {
				commit(args[1]);
			}
		} else if (command.equals("rm")) {
			rm(args[1]);
		} else if (command.equals("log")) {
			log();
		} else if (command.equals("global-log")) {
			global_log();
		} else if (command.equals("find")) {
			find(args[1]);
		} else if (command.equals("status")) {
			Branch.printStatus();
		} else if (command.equals("checkout")) {
			checkoutCommand(args);
		} else if (command.equals("branch")) {
			String name = args[1];
			Branch myBranch = new Branch(name);
			branchID++;
			myBranch.setBranchId(branchID);
		} else if (command.equals("rm-branch")) {
			Branch.rmBranch(args[1]);
		} else if (command.equals("reset")) {
			reset(Integer.parseInt(args[1]));
		} else if (command.equals("merge")) {
			merge(args[1]);
		} else if (command.equals("rebase")) {
			rebase(args[1]);
		} else {
			System.out.println("No command with that name exists.");
		}
		myGitlet.Serialize();
	}

	/**
	 * Runs the Gitlet system when the command given is "checkout". If the
	 * command is valid, it runs checkout. If the command is not valid, it
	 * returns an error statement.
	 * 
	 * @param args
	 *            Array of arguments entered after running Gitlet
	 */
	public static void checkoutCommand(String[] args) {
		if (args.length == 2) {
			String name = args[1];
			if (allBranchNames.contains(name)) {
				for (Branch b : allBranches) {
					if (b.isCurrent()) {
						b.removeCurrentBranch();
					}
				}
				Branch.checkoutBranch(name);
			} else if (currentB.returnCommits().contains(name)) {
				for (Branch b : allBranches) {
					if (b.isCurrent()) {
						b.checkoutFile(name, currentB);
					}
				}
			} else {
				System.out.println("File does not exist in the most recent commit, or no such branch exists.");
			}
		} else if (args.length == 3) {
			int id = Integer.parseInt(args[1]);
			String name = args[2];
			Branch.checkoutFileinCommit(id, name);
		}
	}

	/**
	 * Limits the gitlet system if it is in a conflicted state. Only certain
	 * commands may be called until the conflicted state is resolved by a
	 * command to commit.
	 * 
	 * @param command
	 *            the command that is called while the gitlet system is
	 *            conflicted
	 * @param args
	 *            Array of arguments entered after running a conflicted Gitlet
	 */
	public static void conflictedState(String command, String[] args) {
		if (!Arrays.asList(conflictedCommands).contains(command)) {
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		} else {
			if (command.equals("checkout")) {
				if (args.length == 2) {
					if (allBranchNames.contains(args[1]) && !currentB.returnCommits().getFiles().containsKey(args[1])) {
						System.out.println("Cannot do this command until the merge conflict has been resolved.");
						return;
					}
				}
			}
		}
	}

	/**
	 * Creates a new Gitlet system in the current directory and creates an
	 * initial commit with no files. If a directory already exists, exits the
	 * program.
	 */
	public static void init() {
		if (gitlet.exists()) {
			System.out.println("A gitlet version control system already exists in the current directory.");
		} else {
			stagingArea = new FileObject(".gitlet/stagingArea");
			commits = new FileObject(".gitlet/commits");
			gitlet.mkdir();
			stagingArea.mkdir();
			commits.mkdir();
			// ****** work in progress******//
			mostRecent = new Commit("initial commit");
			hstr = new CommitNode(mostRecent);
			mostRecent.setCommitNode(hstr);
			allCommits.add(mostRecent);
			branchID = 1;
			Branch myBranch = new Branch("master");
			myBranch.setCurrentBranch();
			myBranch.setBranchId(branchID);
			currentB = myBranch;
			numOfCommits = 1;

		}
	}

	/**
	 * Adds files to the staging area when a valid add command is called
	 * 
	 * @param folderPaths
	 *            the file paths of where the file is supposed to go in the
	 *            staging area (but not including the file name)
	 * @param name
	 *            the name of the file, and only the file to be added to the
	 *            staging area
	 * @param pathToFile
	 *            the complete path of where the file is stored
	 */
	public static void addFilesToStagingArea(String folderPaths, String name, Path pathToFile) {
		FileObject copy = new FileObject(".gitlet/stagingArea/" + folderPaths);
		copy.mkdirs();
		FileObject stagedCopy = null;
		if (folderPaths.length() == 0) {
			stagedCopy = new FileObject(".gitlet/stagingArea/" + name);
		} else {
			stagedCopy = new FileObject(".gitlet/stagingArea/" + folderPaths + "/" + name);
		}
		try {
			Files.copy(pathToFile, stagedCopy.toPath());
		} catch (IOException e) {
		}
	}

	/**
	 * Adds a copy of the file to the staging area to be included in the next
	 * commit. If a file was marked for untracking, it removes the mark, but
	 * doesn't stage the file. If the file does not exist, it prints out an
	 * error message.
	 * 
	 * @param fileName
	 *            the name of the file to be staged
	 */
	public static void add(String fileName) {
		String name = FileObject.extractFileName(fileName);
		String folderPaths = FileObject.extractFolders(fileName);
		Path pathToFile = Paths.get(fileName);
		FileObject toAdd = new FileObject(fileName);
		if (toAdd.exists()) {
			if (currentB.returnCommits().contains(fileName)) {
				FileObject existingFile = currentB.returnCommits().getFiles().get(fileName);
				if (existingFile.isMarkedForUntracking()) {
					existingFile.unmark();
					untracked.remove(untracked.indexOf(fileName));
					try {
						if (!Arrays.equals(
								Files.readAllBytes(currentB.returnCommits().getFiles().get(fileName).toPath()),
								Files.readAllBytes(toAdd.toPath()))) {
							addFilesToStagingArea(folderPaths, name, pathToFile);
							toAdd.stage();
							staged2.put(fileName, toAdd);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					addFilesToStagingArea(folderPaths, name, pathToFile);
					toAdd.stage();
					staged2.put(fileName, toAdd);
				}
			} else {
				addFilesToStagingArea(folderPaths, name, pathToFile);
				toAdd.stage();
				staged2.put(fileName, toAdd);
			}
		} else {
			System.out.print("File does not exist.");
		}
	}

	/**
	 * Checks if a string is empty or contains only spaces.
	 * 
	 * @param message
	 *            the message that will be checked
	 * @return true if the message is empty or contains only spaces
	 */
	public static boolean isBlank(String message) {
		// code help from
		// http://www.java2s.com/Code/Java/Data-Type/ChecksiftheStringcontainsonlywhitespace.htm
		if (message.length() == 0) {
			return false;
		}
		for (int i = 0; i < message.length(); i++) {
			if ((Character.isWhitespace(message.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Deletes all the files in the folder.
	 * 
	 * @param folder
	 *            contains the to-be-deleted files
	 */
	public static void deleteFiles(File folder) {
		// code help from
		// http://www.codejava.net/java-se/file-io/clean-and-remove-a-non-empty-directory
		File[] files = folder.listFiles();
		if (files != null && files.length > 0) {
			for (File file : files) {
				if (file.isDirectory()) {
					deleteFiles(file);
					file.delete();
				} else {
					file.delete();
				}
			}

		}
	}

	/**
	 * Adds files to the commit that is being made when a valid commit command
	 * is called
	 * 
	 * @param folderPaths
	 *            the file paths of where the file is supposed to go in the
	 *            commit that is being made (but not including the file name)
	 * @param name
	 *            the name of the file, and only the file to be added to the
	 *            commit
	 * @param pathToFile
	 *            the complete path of where the file is stored
	 */
	public static void addFilesToCommits(String folderPaths, String name, Path pathToFile) {
		FileObject copy = new FileObject(".gitlet/commits/" + currentB.returnCommits().getID() + "/" + folderPaths);
		copy.mkdirs();
		FileObject stagedCopy = null;
		if (folderPaths.length() == 0) {
			stagedCopy = new FileObject(".gitlet/commits/" + currentB.returnCommits().getID() + "/" + name);
		} else {
			stagedCopy = new FileObject(
					".gitlet/commits/" + currentB.returnCommits().getID() + "/" + folderPaths + "/" + name);
		}
		try {
			Files.copy(pathToFile, stagedCopy.toPath());
		} catch (IOException e) {
		}
	}

	/**
	 * It saves a backup of files so that they may be restored later. A commit,
	 * by default, will track the same files as it's parent. When files are
	 * added to the staging area, the commit will update its tracked files to
	 * include the version of the file that was staged. If no files have been
	 * staged or no files have been marked for untracking, it prints an error.
	 * If no message is inputed, it will ask for a commit message.
	 * 
	 * @param message
	 *            newly made commit's message.
	 */
	private static void commit(String message) {
		if (isBlank(message)) {
			System.out.println("Please enter a commit message.");
			return;
		}
		if (staged2.size() == 0 && untracked.size() == 0) {
			System.out.println("No changes added to commit.");
			return;
		}
		CommitNode cn = new CommitNode(currentB.returnCommits().getCommitNode());
		Commit c = new Commit(message, cn);
		cn.setCommit(c);
		FileObject newDir = new FileObject(".gitlet/commits/" + c.getID());
		newDir.mkdir();
		currentB.setHead(c);
		conflictedState = false;
		for (String key : staged2.keySet()) {
			currentB.returnCommits().removeFile(key);
			currentB.returnCommits().addFile(key, staged2.get(key));
			c.removeFileDir(staged2.get(key));
			c.addFileDir(staged2.get(key), ".gitlet/commits/" + currentB.returnCommits().getID() + "/" + key);

			String name = FileObject.extractFileName(key);
			String folderPaths = FileObject.extractFolders(key);
			Path pathToFile = Paths.get(key);
			addFilesToCommits(folderPaths, name, pathToFile);
			if (untracked.contains(key)) {
				c.removeFileDir(c.getFiles().get(key));
				c.removeFile(key);
				FileObject stagedCopy = null;
				if (folderPaths.length() == 0) {
					stagedCopy = new FileObject(".gitlet/commits/" + currentB.returnCommits().getID() + "/" + name);
				} else {
					stagedCopy = new FileObject(
							".gitlet/commits/" + currentB.returnCommits().getID() + "/" + folderPaths + "/" + name);
				}
				try {
					Files.delete(stagedCopy.toPath());
				} catch (IOException e) {
				}
			}
		}
		for (String s : untracked) {
			c.removeFileDir(c.getFiles().get(s));
			c.removeFile(s);
		}
		for (Object obj : mostRecent.getFiles().values()) {
			((FileObject) obj).unmark();
		}
		stagingArea = new FileObject(".gitlet/stagingArea");
		staged2.clear();
		deleteFiles(stagingArea);
		stagingArea.mkdir();
		allCommits.add(c);
		hstr = cn;
		mostRecent = c;
		numOfCommits++;
	}

	/**
	 * Marks a file for untracking. If the file has been staged, it will unstage
	 * it, but not untrack it.
	 * 
	 * @param fileName
	 *            name of the file to be marked for untracking.
	 */
	public static void rm(String fileName) {
		if (staged2.containsKey(fileName)) {
			staged2.remove(fileName);
		} else if (!staged2.containsKey(fileName) && !currentB.returnCommits().getFiles().containsKey(fileName)) {
			System.out.println("No reason to remove the file.");
		} else {
			((FileObject) currentB.returnCommits().getFiles().get(fileName)).mark();
			untracked.add(fileName);
		}
	}

	/**
	 * Displays information (Commit id, date & time the commit was made, commit
	 * message) about each commit in the current branch, starting from the
	 * current head commit.
	 */
	public static void log() {
		Commit c = currentB.returnCommits();
		while (c != null) {
			System.out.println("=== \n" + "Commit" + c.getID() + "\n" + c.getTime() + "\n" + c.getMessage() + "\n");
			if (c.getParentCN() != null) {
				c = c.getParentCN().item();
			} else {
				c = null;
			}
		}
	}

	/**
	 * Displays information (Commit id, date & time the commit was made, commit
	 * message) about each commit ever made. The order does not matter.
	 */
	public static void global_log() {
		for (Commit c : allCommits) {
			System.out.println("=== \n" + "Commit" + c.getID() + "\n" + c.getTime() + "\n" + c.getMessage() + "\n");
		}
	}

	/**
	 * Prints the id of the commit (or commits) with the given message.
	 * 
	 * @param message
	 *            message that the commit will have
	 */
	public static void find(String message) {
		int count = 0;
		for (Commit c : allCommits) {
			if (c.getMessage().equals(message)) {
				System.out.println(c.getID());
				count++;
			}
		}
		if (count == 0) {
			System.out.println("Found no commit with that message.");
		}
	}

	/**
	 * Adds files to the working directory when a valid reset command is called.
	 * This essentially checkouts the commit.
	 * 
	 * @param folderPaths
	 *            the file paths of where the file is supposed to go in the
	 *            working directory (but not including the file name)
	 * @param name
	 *            the name of the file, and only the file to be added to the
	 *            working directory
	 * @param pathToFile
	 *            the complete path of where the file is stored
	 * @param x
	 *            tells whether a file with the same name exists. If it does, it
	 *            erases the file.
	 */
	public static void addFilesToReset(String folderPaths, String name, Path pathToFile, boolean x) {
		FileObject stagedCopy = null;
		if (folderPaths.length() == 0) {
			stagedCopy = new FileObject(name);
		} else {
			stagedCopy = new FileObject(folderPaths + "/" + name);
		}

		try {
			if (x == true) {
				Files.delete(stagedCopy.toPath());
			}
			Files.copy(pathToFile, stagedCopy.toPath());
		} catch (IOException e) {
		}
	}

	/**
	 * Goes through the given path and creates the path if it does not exist.
	 * 
	 * @param path
	 *            the file path that is created at the end of this function
	 * @param workingDirectory
	 *            String representation of the working directory
	 */
	public static void makeDirectory(String path, String workingDirectory) {
		ArrayList<String> folders = extractFolderAndFile(path);
		String workingDir = workingDirectory;
		for (int i = 0; i < folders.size(); i++) {
			File dir = new File(workingDir + "/" + folders.get(i));
			if (dir.exists()) {
				workingDir = workingDir + "/" + folders.get(i);
			} else {
				dir.mkdir();
				workingDir = workingDir + "/" + folders.get(i);
			}
		}
	}

	/**
	 * Creates an ArrayList where each folder in the given path is stored in an
	 * element. The more general the folder is, the larger its index number will
	 * be.
	 * 
	 * @param path
	 *            the file path that will be broken up and stored into an array.
	 * @return an ArrayList consisting of file directory names.
	 */
	public static ArrayList<String> extractFolderAndFile(String path) {
		ArrayList<String> folderFileName = new ArrayList<String>();
		int pathLength = path.length();
		while (pathLength > 0) {
			int lastSlashInd = path.lastIndexOf("/");
			folderFileName.add(path.substring(lastSlashInd + 1));
			if (lastSlashInd > 0) {
				path = path.substring(0, lastSlashInd);
			}
			pathLength = lastSlashInd - 1;
		}
		return folderFileName;
	}

	/**
	 * Checks out all the files tracked by the commit. Reassign's the current
	 * branch's head to the given commit.
	 * 
	 * @param id
	 *            the id of the commit that will be reset
	 */
	public static void reset(int id) {
		Commit c = Commit.returnCommit(id);
		if (c == null) {
			System.out.println("No commit with that id exists.");
		} else {
			for (String key : c.getFiles().keySet()) {
				String name = FileObject.extractFileName(key);
				String folderPaths = FileObject.extractFolders(key);
				makeDirectory(folderPaths, new String());
				Path pathToFile = Paths.get(c.getFileDir().get(c.getFiles().get(name)));
				File dest = new File(key);
				if (dest.exists()) {
					addFilesToReset(folderPaths, name, pathToFile, true);
				} else {
					addFilesToReset(folderPaths, name, pathToFile, false);
				}
			}
			currentB.setHead(c);
		}
	}

	/**
	 * A helper method of merge which checks out conflicted files into the
	 * working directory with the same file name and the tail ".conflicted".
	 * 
	 * @param c
	 *            the commit which tracks the conflicted file from the given
	 *            branch
	 * @param s
	 *            the name of the conflicted file
	 */
	public static void checkoutConflicted(Commit c, String s) {
		File source = new File(c.getFileDir().get(c.getFiles().get(s)));
		int lastPeriodIndex = s.lastIndexOf(".");
		String fileName = s.substring(0, lastPeriodIndex);
		File destination = new File(fileName + ".conflicted");
		try {
			Files.copy(source.toPath(), destination.toPath());
		} catch (IOException e) {

		}
	}

	/**
	 * A helper method of merge, which compares two files with the same name and
	 * determines if they are the exact same.
	 * 
	 * @param a
	 *            commit which holds the first instance of the file
	 * @param b
	 *            commit which holds the second instance of the file
	 * @param s
	 *            name of the files
	 * @return true if the files are equal
	 */
	public static boolean equalFile(Commit a, Commit b, String s) {
		if (a.getFileDir().get(a.getFiles().get(s)).equals(b.getFileDir().get(b.getFiles().get(s)))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Combines files from the given branch into the current branch through a
	 * new commit.
	 * 
	 * @param branchName
	 *            the name of the given branch. Its files will be merged with
	 *            that of the current branch's head commit's.
	 */
	public static void merge(String branchName) {
		if (!allBranchNames.contains(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (branchName.equals(currentB.returnName())) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}
		Commit splitPointCommit = currentB.findSplitPointCommit(branchName);
		Commit c1 = currentB.returnCommits();
		int index = Gitlet.allBranchNames.indexOf(branchName);
		Branch branch2 = Gitlet.allBranches.get(index);
		Commit c2 = branch2.returnCommits();
		int numOfConflictedFile = 0;
		for (String s : c2.getFiles().keySet()) {
			if (splitPointCommit.getFiles().containsKey(s) && !c1.getFiles().containsKey(s)) {
				branch2.checkoutFile(s, branch2);
				add(s);
			} else if (splitPointCommit.getFiles().containsKey(s) && c1.getFiles().containsKey(s)) {
				if (!equalFile(c2, splitPointCommit, s)) {
					if (equalFile(c1, splitPointCommit, s)) {
						branch2.checkoutFile(s, branch2);
						add(s);

					} else if (!equalFile(c1, splitPointCommit, s)) {
						checkoutConflicted(c2, s);
						numOfConflictedFile++;
					}
				}
			} else if (!splitPointCommit.getFiles().containsKey(s) && !c1.getFiles().containsKey(s)) {
				branch2.checkoutFile(s, branch2);
				add(s);
			}
		}
		if (numOfConflictedFile == 0) {
			commit("Merged " + currentB.returnName() + " with " + branchName + ".");
		} else {
			conflictedState = true;
			System.out.println("Encountered a merge conflict.");

		}
	}

	/**
	 * Makes a copy of this branch's commits starting from its split point
	 * with the given branch and adds them to the head of the given branch.
	 * This Branch's head is set to the newly copied commit.
	 * 
	 * @param branchName
	 * 			Name of the branch to attach the current branch to
	 */
	@SuppressWarnings("unchecked")
	public static void rebase(String branchName) {
		if (!allBranchNames.contains(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (branchName.equals(currentB.returnName())) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}
		Commit splitPointCommit = currentB.findSplitPointCommit(branchName);
		Commit c1 = currentB.returnCommits();
		int index = Gitlet.allBranchNames.indexOf(branchName);
		Branch branch2 = Gitlet.allBranches.get(index);
		Commit c2 = branch2.returnCommits();
		if (splitPointCommit.getID() == c1.getID()) {
			currentB.setHead(c2);
			return;
		}
		if (splitPointCommit.getID() == c2.getID()) {
			System.out.println("Already up-to-date.");
			return;
		}
		Commit tracker = c1;
		ArrayList<Commit> copyOfCommits = new ArrayList<Commit>();
		while (tracker.getID() != splitPointCommit.getID()) {
			copyOfCommits.add(tracker);
			tracker = tracker.getParentCN().item();
		}
		CommitNode node = branch2.returnCommits().getCommitNode();
		HashMap<String, String> modifiedFiles = getModifiedFiles(splitPointCommit, c1);

		for (int i = copyOfCommits.size() - 1; i >= 0; i--) {
			Commit copy = copyOfCommits.get(i);
			CommitNode cn = new CommitNode(node);
			Commit c = new Commit(copy, cn, cn.parent());
			cn.setCommit(c);
			HashMap<String, String> copyModifiedFiles = (HashMap<String, String>) modifiedFiles.clone();
			for (String s : modifiedFiles.keySet()) {
				if (copyModifiedFiles.get(s).equals("modified")) {
					if (c.getFileDir().get(c.getFiles().get(s)).equals((c1.getFileDir().get(c1.getFiles().get(s))))) {
						copyModifiedFiles.remove(s);
					} else {
						if (c.contains(s)) {
							c.removeFileDir(c.getFiles().get(s));
							c.removeFile(s);
						}
						c.addFileDir(c1.getFiles().get(s), c1.getFileDir().get(c1.getFiles().get(s)));
						c.addFile(s, c1.getFiles().get(s));
					}
				} else if (copyModifiedFiles.get(s).equals("added")) {
					if (c.contains(c1.getFiles().get(s).getName()) && c.getFileDir().get(c.getFiles().get(s))
							.equals((c1.getFileDir().get(c1.getFiles().get(s))))) {
						copyModifiedFiles.remove(s);
					} else {
						if (c.contains(s)) {
							c.removeFileDir(c.getFiles().get(s));
							c.removeFile(s);
						}
						c.addFileDir(c1.getFiles().get(s), c1.getFileDir().get(c1.getFiles().get(s)));
						c.addFile(s, c1.getFiles().get(s));
					}
				} else if (copyModifiedFiles.get(s).equals("removed")) {
					if (c.contains(s)) {
						c.removeFileDir(c.getFiles().get(s));
						c.removeFile(s);
					} else {
						copyModifiedFiles.remove(s);
					}
				}
				modifiedFiles = (HashMap<String, String>) copyModifiedFiles.clone();
			}
			hstr = cn;
			mostRecent = c;
			allCommits.add(c);
			currentB.setHead(c);
			node = cn;
		}
		reset(currentB.returnCommits().getID());
	}
	
	/**
	 * Goes through all the files in the current branch's head commit and determines whether they have been modified, added, or removed since the split point.
	 * 
	 * @param splitPoint
	 * 			the commit where the given commit of merge or rebase split
	 * @param branchHeadCommit
	 * 			The current branch's head commit
	 */
	public static HashMap<String, String> getModifiedFiles(Commit splitPoint, Commit branchHeadCommit) {
		HashMap<String, String> modifiedFiles = new HashMap<String, String>();
		for (String s : branchHeadCommit.getFiles().keySet()) {
			if (splitPoint.contains(s) && branchHeadCommit.contains(s)) {
				if (!equalFile(splitPoint, branchHeadCommit, s)) {
					modifiedFiles.put(s, "modified");
				}
			} else if (!splitPoint.contains(s) && branchHeadCommit.contains(s)) {
				modifiedFiles.put(s, "added");
			}
		}
		for (String s : splitPoint.getFiles().keySet()) {
			if (splitPoint.contains(s) && !branchHeadCommit.contains(s)) {
				modifiedFiles.put(s, "removed");
			}
		}
		return modifiedFiles;
	}

	/**
	 * Deserializes myGitlet so that the updated variables in Gitlet.java can be
	 * accessed over numerous calls to Gitlet.java
	 */
	public static void Deser() {
		myGitlet restored = myGitlet.Deserialize();
		if (restored != null) {
			staged2 = restored.staged2;
			untracked = restored.untracked;
			allBranches = restored.allBranches;
			hstr = restored.hstr;
			mostRecent = hstr.item();
			allCommits = restored.allCommits;
			allBranchNames = restored.allBranchNames;
			currentB = restored.currentB;
			conflictedState = restored.conflictedState;
			numOfCommits = restored.numOfCommits;
		}
	}

}