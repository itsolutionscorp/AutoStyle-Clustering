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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 * This class implements a version control software called Gitlet, based on Git.
 * It will be our command interpreter and send work to the appropriate methods.
 * 
 * @author Kevin Wu, Cynthia Diaz, Jaehyun Sim, Evan Patel
 *
 */
public class Gitlet implements Serializable {
	private static final long serialVersionUID = -12345;
	private ArrayList<Log> myGlobalLog;
	private CommitTree myTree;
	private int currentID;
	private String currentBranch;
	private HashMap<String, String> tracked;
	private HashMap<String, CommitTreeNode> branches;
	private ArrayList<String> untracked;
	private CommitTreeNode myMaster;
	private HashMap<Integer, CommitTreeNode> myCommits;
	private ArrayList<String> addingFiles;
	private boolean merging;
	private boolean conflicted;

	/**
	 * addToLog adds the current log (which contains message, ID, and time made)
	 * to the global log, myGlobalLog.
	 * 
	 * @param currentLog
	 */
	public void addToLog(Log currentLog) {
		myGlobalLog.add(currentID, currentLog);
	}

	/**
	 * This method prints the global-log when given the command java Gitlet
	 * global-log
	 */
	public void printGlobalLog() {
		for (int i = 0; i < myGlobalLog.size(); i++) {
			System.out.println(myGlobalLog.get(i));
		}
	}

	/**
	 * This Method prints the log starting fromt he current node.
	 */
	public void log() {
		CommitTreeNode current = myTree.getHead();
		while (current != null) {
			System.out.println(myGlobalLog.get(current.getID()).toString());
			current = current.prev();
		}
	}

	// Date formatting from
	// http://www.tutorialspoint.com/java/java_date_time.htm
	/**
	 * This method constructs the date according to the yyyy-MM-dd HH:mm:ss
	 * format according to when it was created.
	 * 
	 * @return String which indicates time and date in which a log is created.
	 * 
	 */
	public String dateConstructor() {
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return ft.format(date);
	}

	/**
	 * This method returns whether or not a String represents a number
	 * 
	 * @param str
	 *            - str represents the string in question, whether or not it is
	 *            a number
	 * @return - returns a boolean of whether or not the string in question is a
	 *         number.
	 */
	public boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * This method creates a directory (folder) at the given location with the
	 * given name.
	 * 
	 * @param whereTo
	 *            - this is the location/path of the directory to be created
	 * @param fileName
	 *            - this is the name of the directory to be created.
	 */
	public void directoryMaker(String whereTo, String fileName) {
		String[] fileNameParts = fileName.split("/");
		String filePath = "";
		for (int i = 0; i < (fileNameParts.length) - 1; i++) {
			filePath += fileNameParts[i] + "/";
			File dir = new File(whereTo + "/" + filePath);
			if (!dir.exists()) {
				dir.mkdir();
			}
		}
	}

	/**
	 * This method deletes, recursively, all of the contents in a folder
	 * 
	 * @param d
	 *            - a File which represents a folder in which all its contents
	 *            are to be deleted
	 */
	private void recursiveDelete(File d) {
		if (d.isDirectory()) {
			for (File f : d.listFiles()) {
				recursiveDelete(f);
			}
		}
		if (!d.delete()) {
		}
	}

	// Init Methods
	/**
	 * the Init method does three things: 1) Makes an empty, initial node and
	 * have the Master pointer point to it and also give it an ID, and an
	 * initial message. 2) Makes a .gitlet folder where everything gitlet is
	 * stored 3) Stores a .ser file and stage(?) inside the .gitlet directory
	 */
	public void init() {
		File check = new File(".gitlet/");
		if (check.exists()) {
			System.out.println("A gitlet version control system already exists in the current directory.");
			return;
		}
		merging = false;
		conflicted = false;
		branches = new HashMap<String, CommitTreeNode>();
		currentBranch = "master";
		currentID = 0;
		myGlobalLog = new ArrayList<Log>();
		myTree = new CommitTree();
		myMaster = myTree.getHead();
		branches.put("master", myMaster);
		tracked = new HashMap<String, String>();
		untracked = new ArrayList<String>();
		addingFiles = new ArrayList<String>();
		myCommits = new HashMap<Integer, CommitTreeNode>();
		myCommits.put(currentID, myTree.getHead());
		File myGit = new File(".gitlet/");
		myGit.mkdir();
		Log initial = new Log(0, "initial commit", dateConstructor());
		addToLog(initial);
		currentID++;
		File myStage = new File(".gitlet/stage/");
		myStage.mkdir();
		serialWrite(this);
	}

	/**
	 * This method adds a file to the stage, a directory inside the .gitlet
	 * folder. If the file is marked for untracking, then it instead just
	 * unmarks it for untracking.
	 * 
	 * @param addingFile
	 *            - this is the File/path to File to be added. It has to be a
	 *            file and can not be a folder.
	 * 
	 */
	public void add(String addingFile) {
		try {
			File adding = new File(addingFile);
			if (adding.exists()) {
				directoryMaker(".gitlet/stage", addingFile);
			}
			File source = new File(addingFile);
			File dest = new File(".gitlet/stage/" + addingFile);
			if (untracked.contains(addingFile)) {
				tracked.put(addingFile, ".gitlet/commit/commit" + currentID + addingFile);
				untracked.remove(addingFile);
			} else {
				Files.copy(source.toPath(), dest.toPath());
				addingFiles.add(addingFile);
				serialWrite(this);
			}
		} catch (IOException e) {
			System.out.println("File does not exist.");
		}
	}

	/**
	 * This method commits the files on the stage into a new commit folder
	 * 
	 * @param msg
	 *            - this is the message that is associated with a commit. It is
	 *            used later for identification purposes
	 */
	public void commit(String msg) {
		if (msg == null) {
			System.out.println("Please enter a commit message.");
		} else {

			File stageFolder = new File(".gitlet/stage");
			File committedFiles = new File(".gitlet/commit");

			String[] files = stageFolder.list();
			// changed
			if (files.length == 0 && untracked.isEmpty()) {
				System.out.println("No changes added to the commit.");
			} else {

				Log newLog = new Log(currentID, msg, dateConstructor());
				CommitTreeNode newNode = new CommitTreeNode(newLog, currentBranch);
				newNode.setPrev(myTree.getHead());
				myTree.moveHead(newNode);
				branches.put(currentBranch, newNode);

				// Case for first commit
				if (!committedFiles.exists()) {
					committedFiles.mkdir();
				}

				File committingFile = new File(".gitlet/commit/commit" + currentID);
				committingFile.mkdir();
				for (String file : addingFiles) {
					tracked.put(file, committingFile + "/" + file);
					File source = new File(stageFolder + "/" + file);
					File dest = new File(committingFile + "/" + file);
					directoryMaker(".gitlet/commit/commit" + currentID, file);
					source.renameTo(dest);
					
				}
				
				for (String file : addingFiles) {
					String[] folder = file.split("/");
					if (folder.length > 1) {
						File tryDeleting = new File(".gitlet/stage/" + folder[0]);
						recursiveDelete(tryDeleting);
					}
				}
					
				newNode.setTrackedFiles(new HashMap<String, String>(tracked));
				addToLog(newLog);
				myCommits.put(currentID, myTree.getHead());
				currentID++;
				untracked = new ArrayList<String>();
				addingFiles = new ArrayList<String>();

				conflicted = false;
				serialWrite(this);
			}
		}
	}

	/**
	 * If a given file is on the stage, then it removes it from the stage. If it
	 * is not on the stage, but is being tracked, it is instead marked for
	 * untracking.
	 * 
	 * @param removingFile
	 *            - this is the path of the file to be removed/untracked.
	 */
	private void remove(String removingFile) {
		File check = new File(".gitlet/stage/" + removingFile);
		if (!addingFiles.contains(removingFile) && !tracked.containsKey(removingFile)) {
			System.out.println("No reason to remove the file.");
		} else if (check.exists()) {
			check.delete();
			String[] folder = removingFile.split("/");
			if (folder.length > 1) {
				File tryDeleting = new File(".gitlet/stage/" + folder[0]);
				recursiveDelete(tryDeleting);
			}
			addingFiles.remove(removingFile);
			serialWrite(this);
		} else if (tracked.containsKey(removingFile)) {
			untracked.add(removingFile);
			tracked.remove(removingFile);
			serialWrite(this);
		}
	}

	/**
	 * This method finds a node, associated with a commit message.
	 * 
	 * @param msg
	 *            - this represents a commit message.
	 */
	public void find(String msg) {
		boolean noneWithName = true;
		for (int node : myCommits.keySet()) {
			CommitTreeNode currNode = myCommits.get(node);
			if (currNode.getMsg().equals(msg)) {
				System.out.println(currNode.getID());
				noneWithName = false;
			}
		}
		if (noneWithName) {
			System.out.println("Found no commit with that message.");
		}
	}

	/**
	 * This method reports the status of the Gitlet system. It shows which
	 * branches are there and which is the active branch, as well as what files
	 * are on the stage and what files are marked for untracking.
	 */
	public void status() {
		System.out.println("=== Branches ===");
		for (String branchName : branches.keySet()) {
			if (branchName.equals(currentBranch)) {
				System.out.println("*" + branchName);
			} else {
				System.out.println(branchName);
			}
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		for (String file : addingFiles) {
			System.out.println(file);
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (String file : untracked) { // Untracked array
			System.out.println(file);
		}

	}

	/**
	 * This method creates a branch with the name specified, at the current
	 * node.
	 * 
	 * @param branchName
	 *            - this is the name of the branch to be created
	 */
	public void branch(String branchName) {
		if (branches.containsKey(branchName)) {
			System.out.println("A branch with that name already exists.");
		} else {
			CommitTreeNode newBranch = myTree.getHead();
			branches.put(branchName, newBranch);
			branches.get(currentBranch).setSplitPoint();
			branches.get(currentBranch).addSplit(branchName);
			serialWrite(this);
		}
	}

	/**
	 * This method removes the pointer of the specified branch. Once used, one
	 * can not access the branch again.
	 * 
	 * @param branchName
	 *            This is the name of the branch to be removed
	 */
	private void removeBranch(String branchName) {
		if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
		} else if (currentBranch.equals(branchName)) {
			System.out.println("Cannot remove the current branch.");
		} else {
			branches.remove(branchName);
			serialWrite(this);
		}
	}

	/**
	 * This method has three functions depending on the input. When the user
	 * chooses to checkout a branch, the most recent commit is restored and the
	 * current head pointer is moved to the given branch. When the user chooses
	 * to checkout a file, the file in the most recent commit will be checked
	 * out to the working directory. When the user chooses to checkout a file
	 * with the commit ID as well as the file name, the file with the given name
	 * will be checked out from the node associated with that ID.
	 * 
	 * @param firstArg
	 *            This tells Gitlet what to check out, either a branch, or file,
	 *            or commit node.
	 * @param secondArg
	 *            This is only used in case 3, in which case this string
	 *            represents the file name.
	 */
	public void checkout(String firstArg, String secondArg) {
		// java Gitlet checkout [branch name]
		if (branches.containsKey(firstArg) && secondArg == null) {
			if (!firstArg.equals(currentBranch)) {
				HashMap<String, String> trackedFiles = branches.get(firstArg).getTrackedFiles();
				for (String file : trackedFiles.keySet()) {
					File source = new File(trackedFiles.get(file));
					File dest = new File(file);
					directoryMaker(".", file);
					try {
						Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
					}
				}
				currentBranch = firstArg;
				myTree.resetHead(branches.get(currentBranch));
				this.tracked = new HashMap<String, String>(trackedFiles);
				serialWrite(this);
			} else {
				System.out.println("No need to checkout the current branch.");
			}
		}
		// java Gitlet checkout [file name]
		else if (!isNumber(firstArg) && secondArg == null) {
			try {
				File source = new File(branches.get(currentBranch).getTrackedFiles().get(firstArg));
				File dest = new File(firstArg);
				directoryMaker(".", firstArg);
				Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (NullPointerException | IOException e) {
				System.out.println("File does not exist in the most recent commit, or no such branch exists.");
			}
		}
		// java Gitlet checkout [commit id] [file name]
		else if (isNumber(firstArg) && secondArg != null) {
			int retrievingID = Integer.parseInt(firstArg);
			if (retrievingID > currentID) {
				System.out.println("No commit with that id exists.");
			} else {
				CommitTreeNode retrievingNode = myCommits.get(retrievingID);
				try {
					File source = new File(retrievingNode.getTrackedFiles().get(secondArg));
					File dest = null;
					if (!merging) {
						directoryMaker(".", secondArg);
						dest = new File(secondArg);
					} else {
						directoryMaker(".", secondArg);
						if (new File(secondArg).exists()) {
							dest = new File(secondArg + ".conflicted");
						} else {
							dest = new File(secondArg);
						}
					}
					Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (NullPointerException | IOException e) {
					System.out.println("File does not exist in that commit.");
				}
			}
		}
	}

	/**
	 * This method changes the current head pointer from the current node to the
	 * specified node.
	 * 
	 * @param commitID
	 *            This represents the ID of the node in which the user wants to
	 *            change the head pointer to.
	 */
	public void reset(String commitID) {
		CommitTreeNode commit = myCommits.get(Integer.parseInt(commitID));
		if (commit == null) {
			System.out.println("No commit with that id exists.");
		} else {
			for (String file : commit.getTrackedFiles().keySet()) {
				checkout(commitID, file);
			}

			myTree.resetHead(commit); 
			branches.put(currentBranch, myTree.getHead());
			untracked = new ArrayList<String>();
			tracked = myTree.getHead().getTrackedFiles();
			serialWrite(this);
		}

	}

	/**
	 * This helper method finds the split point common to the current branch and
	 * the given branch.
	 * 
	 * @param branchName
	 *            This represents the given branch's name.
	 * @return This returns the split node common to current and given branch.
	 */
	public CommitTreeNode findSplitPoint(String branchName) {
		CommitTreeNode current = branches.get(currentBranch);
		while (current != null) {
			if (current.isSplit()) {
				if (current.getBranches().contains(branchName)) {
					return current;
				}
			}
			current = current.prev();
		}
		return null;
	}

	// Copy Pasted from piazza solutions
	/**
	 * This method compares the files at the given paths by bytes to test for
	 * equivalence
	 * 
	 * @param file1Path
	 *            the path of the first file
	 * @param file2Path
	 *            the path of the second file
	 * @return This is a boolean, showing whether or not two files are
	 *         byte-equivalent.
	 */
	public boolean compareFiles(String file1Path, String file2Path) {
		Path path1 = new File(file1Path).toPath();
		Path path2 = new File(file2Path).toPath();
		try {
			return Arrays.equals(Files.readAllBytes(path1), Files.readAllBytes(path2));
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * This method compares the given branch from the split point of the current
	 * branch. If a file existed at the split point and changed in both the
	 * split branch and our current branch, then the file is brought to the
	 * working directory with a .conflicted suffix. If the file had not changed
	 * in the current branch but changed in the given branch, then the file is
	 * brought to the working directory. Afterwards, all the files are staged
	 * and if any .conflicted files were generated then certain functions are
	 * locked until a commit occurs.
	 * 
	 * @param branchName
	 *            The given branch is compared with the current branch for
	 *            changes in files from their common split point
	 */
	public void merge(String branchName) {
		if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
		} else if (branchName.equals(currentBranch)) {
			System.out.println("Cannot merge a branch with itself");
		} else {
			CommitTreeNode splitPoint = findSplitPoint(branchName);
			CommitTreeNode branchPoint = branches.get(branchName);
			Boolean changes = false;
			for (String file : splitPoint.getTrackedFiles().keySet()) {
				if (branchPoint.getTrackedFiles().containsKey(file)) {
					if (compareFiles(splitPoint.getTrackedFiles().get(file), branchPoint.getTrackedFiles().get(file))) {
						continue;
					} else {
						if (tracked.containsKey(file)) {
							if (compareFiles(tracked.get(file), splitPoint.getTrackedFiles().get(file))) {
								checkout(("" + branchPoint.getID()), file);
								add(file);
							} else {
								changes = true;
								merging = true;
								checkout(("" + branchPoint.getID()), file);
								merging = false;
								conflicted = true;
								add(file + ".conflicted");
							}
						} else {
							changes = true;
							checkout(("" + branchPoint.getID()), file);
							add(file);
						}
					}
				} else if (tracked.containsKey(file)) {
					if (compareFiles(tracked.get(file), splitPoint.getTrackedFiles().get(file))) {
						remove(file);
						changes = true;
					}
				}
			}
			for (String file : branchPoint.getTrackedFiles().keySet()) {
				if (!splitPoint.getTrackedFiles().containsKey(file) && !tracked.containsKey(file)) {
					changes = true;
					checkout("" + branchPoint.getID(), file);
					add(file);
				} else if (!splitPoint.getTrackedFiles().containsKey(file) && tracked.containsKey(file)) {
					changes = true;
					merging = true;
					checkout(("" + branchPoint.getID()), file);
					merging = false;
					conflicted = true;
					add(file + ".conflicted");
				}
			}
			if (!changes) {
			} else {
				if (!conflicted) {
					commit("Merged " + currentBranch + " with " + branchName + ".");
				} else {
					System.out.println("Encountered a merge conflict.");

				}
			}
		}
	}

	/**
	 * This method essentially creates a copy of the current branch and attaches
	 * the commits that differ from the given branch to the newest commit of the
	 * given branch. Rebase also propagates changes in the tracked files between
	 * the copied commits and the given branch's head, if the files in the
	 * copied commits do not differ from those in the split node, but do differ
	 * from those in the given branch. If no commits differ between the two
	 * branches, and the current branch is in the history of the given branch,
	 * rebase moves the current branch to be the same commit as the given
	 * branch.
	 * 
	 * @param branchName
	 *            - The name of the branch on which to append the copies of
	 *            commits in the current branch which differ from those commits
	 *            in the branchName.
	 */
	public void rebase(String branchName) {
		if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (currentBranch.equals(branchName)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}
		if (inHistory(currentBranch, branchName)) {
			System.out.println("Already up-to-date.");
			return;
		}
		if (inHistory(branchName, currentBranch)) {
			branches.put(currentBranch, branches.get(branchName));
			serialWrite(this);
			return;
		}
		CommitTreeNode splitNode = findSplitPoint(branchName);
		CommitTreeNode branchEnd = branches.get(currentBranch);
		while (branchEnd.prev() != splitNode) {
			branchEnd = branchEnd.prev();
		}
		CommitTreeNode branchBeginning = branches.get(currentBranch);
		CommitTreeNode branchCopy = copyBranch(branchBeginning, branchEnd);
		CommitTreeNode currNode = branchCopy;
		while (currNode.prev() != null) {
			currNode = currNode.prev();
		}
		currNode.setPrev(branches.get(branchName));
		CommitTreeNode newBegin = branchCopy;
		CommitTreeNode newEnd = currNode;
		branches.put(currentBranch, branchCopy);
		HashMap<String, String> splitTracked = splitNode.getTrackedFiles();
		HashMap<String, String> givenTracked = branches.get(branchName).getTrackedFiles();
		for (String file : splitTracked.keySet()) {
			if (givenTracked.containsKey(file)) {
				if (!compareFiles(splitTracked.get(file), givenTracked.get(file))) {
					propagateReplacement(newBegin, newEnd, file, splitTracked.get(file), givenTracked.get(file));
				}
			} else {
				propagateDelete(newBegin, newEnd, file, splitTracked.get(file));
			}
		}
		for (String file : givenTracked.keySet()) {
			if (!splitTracked.keySet().contains(file)) {
				propagatePut(newBegin, newEnd, file, givenTracked.get(file));
			}
		}
		splitNode.removeSplit(currentBranch);
		reset("" + (currentID - 1));
		serialWrite(this);
	}

	/**
	 * HELPER METHOD FOR REBASE This method replaces all versions of
	 * fileToReplace with fileReplacement in a rebased branch if the
	 * fileToReplace has not changed since the splitNode, but fileReplacement
	 * has.
	 * 
	 * @param begin
	 *            - newest node in the segment of nodes to apply the replacement
	 * @param end
	 *            - oldest node in the segment of nodes to apply the replacement
	 * @param fileKey
	 *            - the name of the file in the nodes' hash maps of files
	 * @param fileToReplace
	 *            - the name of the file to be replaced
	 * @param fileReplacement
	 *            - the name of the file to put in place of fileToReplace
	 */
	public void propagateReplacement(CommitTreeNode begin, CommitTreeNode end, String fileKey, String fileToReplace,
			String fileReplacement) {
		while (begin != end.prev()) {
			if (begin.getTrackedFiles().containsKey(fileKey)) {
				if (compareFiles(fileToReplace, begin.getTrackedFiles().get(fileKey))) {
					begin.getTrackedFiles().put(fileKey, fileReplacement);
				}
			}
			begin = begin.prev();
		}
	}

	/**
	 * HELPER METHOD FOR REBASE This method deletes all versions of fileToDelete
	 * in a rebased branch if the file exists in the splitnode but does not
	 * exist in the given branch
	 * 
	 * @param begin
	 *            - newest node in the segment of nodes to apply the deletion
	 * @param end
	 *            - oldest node in the segment of nodes to apply the deletion
	 * @param fileKey
	 *            - the name of the file in the nodes' hash maps of files
	 * @param fileToDelete
	 *            - the name of the file to delete
	 */
	public void propagateDelete(CommitTreeNode begin, CommitTreeNode end, String fileKey, String fileToDelete) {
		while (begin != end.prev()) {
			if (begin.getTrackedFiles().containsKey(fileKey)) {
				if (compareFiles(fileToDelete, begin.getTrackedFiles().get(fileKey))) {
					begin.getTrackedFiles().remove(fileKey);
				}
			}
			begin = begin.prev();
		}
	}

	/**
	 * HELPER METHOD FOR REBASE This method puts fileToPut in all nodes of a
	 * rebased branch if fileToPut exists in the given branch but not in the
	 * split node
	 * 
	 * @param begin
	 *            - newest node in the segment of nodes to apply the put
	 * @param end
	 *            - oldest node in the segment of nodes to apply the put
	 * @param fileKey
	 *            - the name of the file to put in the nodes' hash maps of files
	 * @param fileToPut
	 *            - the name of the file to put
	 */
	public void propagatePut(CommitTreeNode begin, CommitTreeNode end, String fileKey, String fileToPut) {
		while (begin != end.prev()) {
			if (!begin.getTrackedFiles().containsKey(fileKey)) {
				begin.getTrackedFiles().put(fileKey, fileToPut);
			}
			begin = begin.prev();
		}
	}

	/**
	 * HELPER METHOD FOR REBASE Tests whether the CommitTreeNode represented by
	 * String branch2 in the map of Strings to CTNs is part of the history of
	 * the CommitTreeNode represented by String branch1 in the map of Strings to
	 * CTNs
	 * 
	 * @param branch1
	 *            - String representing the CTN whose history may or may not
	 *            contain the CTN represented by branch2
	 * @param branch2
	 *            - String representing the CTN who may or may not be in the
	 *            history of the CTN represented by branch1
	 * @return - Boolean: True if branch2 is in branch1's history; else false.
	 */
	public boolean inHistory(String branch1, String branch2) {
		// checks if branch2 is in the history of branch1
		CommitTreeNode b1 = branches.get(branch2);
		CommitTreeNode currNode = branches.get(branch1);
		while (currNode != null) {
			if (currNode == b1) {
				return true;
			}
			currNode = currNode.prev();
		}
		return false;
	}

	/**
	 * HELPER METHOD FOR COPYBRANCH Determines the length of a segment of nodes
	 * beginning at begin and ending at end (inclusive)
	 * 
	 * @param begin
	 *            - CommitTreeNode which is the most recent in the sequence to
	 *            be counted
	 * @param end
	 *            - CommitTreeNode which is the oldest in the sequence to be
	 *            counted
	 * @return - An integer that is equal to the number of CommitTreeNodes in a
	 *         given sequence of nodes
	 */
	public int branchLength(CommitTreeNode begin, CommitTreeNode end) {
		int count = 1;
		while (begin != end) {
			count++;
			begin = begin.prev();
		}
		return count;
	}

	/**
	 * HELPER METHOD FOR REBASE Creates a sequence of CommitTreeNodes that is
	 * identical to the sequence of CommitTreeNodes beginning with begin and
	 * ending at end (inclusive). Gives every copied Node the same message as
	 * that of its corresponding node in the given sequence and the same files,
	 * but updates its time-stamp and ID.
	 * 
	 * 
	 * @param begin
	 *            - The node at the head of the given sequence
	 * @param end
	 *            - The oldest node in the sequence to be copied
	 * @return - A CommitTreeNode that is identical to begin as described above,
	 *         and that heads a sequence of CommitTreeNodes that is identical to
	 *         the one beginning at begin and ending at end as described above
	 */
	public CommitTreeNode copyBranch(CommitTreeNode begin, CommitTreeNode end) {
		int lengthOfBranch = branchLength(begin, end);
		int posToAdd = currentID;
		currentID += lengthOfBranch - 1;
		String thisDate = dateConstructor();
		Log newLog = new Log(currentID, begin.getMsg(), thisDate);
		myGlobalLog.add(posToAdd, newLog);
		CommitTreeNode copyBegin = new CommitTreeNode(newLog, currentBranch);
		copyBegin.setTrackedFiles(new HashMap<String, String>(begin.getTrackedFiles()));
		CommitTreeNode currNode = copyBegin;
		myCommits.put(currentID, copyBegin);
		int count = 1;
		while (begin != end) {
			begin = begin.prev();
			Log newerLog = new Log(currentID - count, begin.getMsg(), thisDate);
			myGlobalLog.add(posToAdd, newerLog);
			CommitTreeNode copyNode = new CommitTreeNode(newerLog, currentBranch);
			myCommits.put(currentID - count, copyNode);
			copyNode.setTrackedFiles(new HashMap<String, String>(begin.getTrackedFiles()));
			copyNode.makePrev(currNode);
			currNode = copyNode;
			count++;
		}
		currentID++;
		myTree.resize(myTree.getSize() + lengthOfBranch);
		serialWrite(this);
		return copyBegin;

	}

	/*******************************************************************
	 * COMMAND INTERPRETER***************************************************
	 * The main method of our Gitlet class will be used as a command
	 * interpreter. It appropriates work to the appropriate methods in our
	 * CommitTree data structure. Listed below are he commands whih Gitlet knows
	 * ow to take.
	 * 
	 * @param args
	 *            The args variable will be either a string, or a string
	 *            followed by a message or number.
	 ******************************************************************************************/
	private static final String commit = "commit";
	private static final String init = "init";
	private static final String add = "add";
	private static final String remove = "rm";
	private static final String log = "log";
	private static final String gLog = "global-log";
	private static final String find = "find";
	private static final String status = "status";
	private static final String checkout = "checkout";
	private static final String branch = "branch";
	private static final String rm_branch = "rm-branch";
	private static final String reset = "reset";
	private static final String merge = "merge";
	private static final String rebase = "rebase";

	/**
	 * Retrieves, if applicable, the second argument that a user inputs.
	 * 
	 * @param args
	 *            a string array of command(s) which the user inputs
	 * @return the second argument a user inputs
	 */
	private static String retrieveSecondArg(String[] args) {
		if (args.length == 2 || args.length == 3) {
			return args[1];
		} else {
			return null;
		}
	}

	/**
	 * Retrieves, when applicable, the third argument that a user inputs.
	 * 
	 * @param args
	 *            a string array of command(s) which the user inputs
	 * @return the third argument of the user input, when applicable
	 */
	private static String retrieveThirdArg(String[] args) {
		if (args.length == 3) {
			return args[2];
		} else {
			return null;
		}
	}

	/**
	 * Decides whether a version control software exists.
	 * 
	 * @return true if Gitlet already exists
	 */
	private static boolean aGitletExists() {
		File savedGitlet = new File(".gitlet/Gitlet.ser");
		return savedGitlet.exists();
	}

	/**
	 * Creates a gitlet when it does not already exists, otherwise reads the
	 * existing serialized Gitlet
	 * 
	 * @return a new or existing Gitlet object
	 */
	public static Gitlet gitlet() {
		if (!aGitletExists()) {
			return new Gitlet();
		} else {
			return serialRead();
		}
	}

	/**
	 * This method checks to make sure that the user input a command
	 * 
	 * @param args
	 *            are the commands (or lack of) which a user inputs
	 * @return true if the user did not input command
	 */
	public static Boolean checkMain(String[] args) {
		if (args.length == 0) {
			System.out.println("Please enter a command.");
			return true;
		}
		return false;
	}

	/**
	 * This method checks to see if the current Gitlet is conflicted.
	 * 
	 * @param args
	 *            the commands which a user puts in
	 * @return true if the system is conflicted and the user is not allowed to
	 *         use the methods he typed
	 */
	public static Boolean checkConflicted(String[] args) {
		Gitlet myGitlet = gitlet();
		String args2 = retrieveSecondArg(args);
		String args3 = retrieveThirdArg(args);
		if (myGitlet.conflicted) {
			if (args[0] == branch || args[0] == rm_branch || args[0] == reset || args[0] == merge
					|| args[0] == rebase) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
			} else if (args[0] == checkout && myGitlet.branches.containsKey(args2) && args3 == null) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
			}
			return true;
		}
		return false;
	}

	/**
	 * Main method. The user inputs a command and the command is executed.
	 * 
	 * @param args
	 *            the user input
	 */
	public static void main(String[] args) {
		Gitlet myGitlet = gitlet(); // new Gitlet
		Scanner input = new Scanner(System.in);
		String args2 = retrieveSecondArg(args);
		String args3 = retrieveThirdArg(args);
		if (checkConflicted(args) || checkMain(args)) {
			return;
		}
		switch (args[0]) {
		case init:
			myGitlet.init();
			break;
		case add:
			myGitlet.add(args2);
			break;
		case commit:
			myGitlet.commit(args2);
			break;
		case remove:
			myGitlet.remove(args2);
			break;
		case log:
			myGitlet.log();
			break;
		case gLog:
			myGitlet.printGlobalLog();
			break;
		case find:
			myGitlet.find(args2);
			break;
		case status:
			myGitlet.status();
			break;
		case checkout:
			myGitlet.checkout(args2, args3);
			break;
		case branch:
			myGitlet.branch(args2);
			break;
		case rm_branch:
			myGitlet.removeBranch(args2);
			break;
		case reset:
			myGitlet.reset(args2);
			break;
		case merge:
			myGitlet.merge(args2);
			break;
		case rebase:
			myGitlet.rebase(args2);
			break;
		default:
			System.out.println("No command with that name exists.");
			break;
		}
	}
	//////////////////////////////////////////////////////////////////
	/************************* SERIALIZABLE ***************************/

	//////////////////////////////////////////////////////////////////
	/**
	 * This code was adopted from Armani Ferrante, a CS61BL TA from his website
	 * http://armaniferrante.com/
	 * 
	 * Deserializes a Gitlet object from the current directory, returning the
	 * saved object.
	 */
	public static Gitlet serialRead() {
		Gitlet git = null;
		try {
			ObjectInput input = new ObjectInputStream(new FileInputStream(".gitlet/Gitlet.ser"));
			git = (Gitlet) input.readObject();
			input.close();
		} catch (IOException e) {
		} catch (ClassNotFoundException e2) {
		}
		return git;
	}

	/**
	 * Serializes HOLDER, an instance of a NumberHolder, saving the file to the
	 * current directory.
	 */
	public static void serialWrite(Gitlet git) {
		try {
			ObjectOutput output = new ObjectOutputStream(new FileOutputStream(".gitlet/Gitlet.ser"));
			output.writeObject(git);
			output.close();
		} catch (IOException e) {
		}
	}
}
