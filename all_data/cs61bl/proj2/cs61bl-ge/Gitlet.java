import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;

public class Gitlet {
	/*
	 * should serialize status files marked for untracking (not sure how to
	 * implement this)
	 */

	/**
	 * create a new gitlet version control system in the current directiry
	 * create the .gitlet folder and comple an empty commit with the message
	 * "initial commit"
	 * 
	 * stop if there is already a gitlet in the current directory and print the
	 * error message "A gitlet version control system already exists in the
	 * current directory."
	 */
	/*
	 * generate the .gitlet folder create a folder inside inside .gitlet as a
	 * staging area; create the default branch master complete the commit 0
	 */
	public static void init() {
		if (FileOperation.exists(".gitlet")) {
			System.out
					.println("A gitlet version control system already exists "
							+ "in the current directory.");
		} else {
			FileOperation.makeNewFolder(".gitlet");
			FileOperation.makeNewFolder(".gitlet/stage");
			FileOperation.makeNewFolder(".gitlet/branch");
			FileOperation.makeNewFolder(".gitlet/commit0");
			Status myStatus = new Status();
			Commit a = new Commit("initial commit", myStatus);
			Branch myBranch = new Branch("master", a.getId());
			FileOperation.write(a, ".gitlet/commit0/commit");
			myStatus.updateActiveBranch(myBranch);
			myStatus.addBranch(myBranch);
			myStatus.serializeStatus();
		}
	}

	/**
	 * stage a file to working space
	 * 
	 * @param pathName
	 *            the path of the file that will be staged in this command
	 * 
	 *            if the file does not exist, print the error message
	 *            "File does not exist".
	 */
	/*
	 * Adds a copy of the file as it currently exists to the staging area If the
	 * file had been marked for untracking then add just unmarks the file,
	 */
	public static void add(String pathName) {
		Status myStatus = (Status) FileOperation.read(".gitlet/myStatus");
		if (FileOperation.exists(pathName)) {
			if (myStatus.doesUntrack(pathName)) {
				myStatus.recoverUntrack(pathName);
			} else {
				myStatus.addNewStageFilePath(pathName,
						myStatus.numOfCommitted());
				FileOperation.copy(pathName, ".gitlet/stage/" + pathName);
			}
			myStatus.serializeStatus();
		} else {
			System.out.println("File does not exist.");
		}

	}

	/**
	 * Saves a backup of certain files
	 * 
	 * @param message
	 *            the message that will be written into the log the best way
	 *            should be storing the message as instance variable of each
	 *            file committed.
	 */
	/*
	 * only update the version of a file it is tracking if that file had been
	 * staged at the time of commit, in which case the commit will now include
	 * the version of the file that was staged instead of the version it got
	 * from its parent. will start tracking any files that were staged but
	 * weren't tracked by its parent. staging area is cleared after a commit
	 * 
	 * If no files have been staged, aborts. Print the error message
	 * "No changes added to the commit." every commit must have a non-blank
	 * message. If it doesn't, print the error message
	 * "Please enter a commit message."
	 */
	public static void commit(String message) {
		Status myStatus = (Status) FileOperation.read(".gitlet/myStatus");
		if (isStageEmpty() && myStatus.isUntrackEmpty()) {
			System.out.println("No changes added to the commit.");
		} else {
			// do the commit
			Commit a = new Commit(message, myStatus.getActiveBranch()
					.getCommitId(), myStatus);
			FileOperation.rename(".gitlet/stage", ".gitlet/commit" + a.getId());
			FileOperation.makeNewFolder(".gitlet/stage");
			FileOperation.write(a, ".gitlet/commit" + a.getId() + "/commit");
			// change myStatus
			myStatus = (Status) FileOperation.read(".gitlet/myStatus");
			myStatus.resetFilePaths();
			myStatus.emptyUntrack();
			myStatus.getActiveBranch().updateCommit(a.getId());
			myStatus.serializeStatus();
		}
	}

	public static boolean isStageEmpty() {
		File dir = new File(".gitlet/stage");
		if (dir.isDirectory() && dir.list().length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Mark the file for untracking
	 * 
	 * @param name
	 *            make the file for untracking status
	 * 
	 *            it will not be included in the upcoming commit, even if it was
	 *            tracked by that commit's parent. If the file had been staged,
	 *            instead just unstage it, and don't also mark it for
	 *            untracking.
	 */
	public static void remove(String pathName) {
		Status myStatus = (Status) FileOperation.read(".gitlet/myStatus");
		Commit myCommit = (Commit) FileOperation.read(".gitlet/commit"
				+ myStatus.getActiveBranch().getCommitId() + "/commit");
		if (myCommit.doesTrack(pathName)) {
			myStatus.untrack(pathName);
		} else if (FileOperation.exists(".gitlet/stage/" + pathName)) {
			myStatus.removeStageFilePath(pathName);
			FileOperation.delete(".gitlet/stage/" + pathName);
			FileOperation.clearEmptyDirectory(".gitlet/stage");
		} else {
			System.out.println("No reason to remove the file.");
		}
		myStatus.serializeStatus();
	}

	/**
	 * display information about each commit backwards along the commit tree
	 * until the initial commit there is a === separating each commit. There is
	 * also an empty line between each commit. Also notice that commits are
	 * displayed with the most recent at the top.For every node in this history,
	 * the information it should display is the commit id, the time the commit
	 * was made, and the commit message.
	 *
	 * 
	 * @param status
	 * @return nothing
	 * 
	 */
	public static void log() {
		Status myStatus = (Status) FileOperation.read(".gitlet/myStatus");
		Commit myCommit = (Commit) FileOperation.read(".gitlet/commit"
				+ myStatus.getActiveBranch().getCommitId() + "/commit");
		while (myCommit.getParentId() != -1) {
			logOfOneCommit(myCommit);
			myCommit = (Commit) FileOperation.read(".gitlet/commit"
					+ myCommit.getParentId() + "/commit");
			System.out.println("");
		}
		logOfOneCommit(myCommit);
	}

	/**
	 * displays information about all commits ever made. The order of the
	 * commits does not matter.
	 * 
	 * @param status
	 * @return nothing
	 * 
	 */
	public static void globalLog() {
		Status myStatus = (Status) FileOperation.read(".gitlet/myStatus");
		HashSet<Integer> commitTable = myStatus.getCommitTable();
		Commit myCommit;
		for (int i : commitTable) {
			myCommit = (Commit) FileOperation.read(".gitlet/commit" + i
					+ "/commit");
			logOfOneCommit(myCommit);
			if (i < commitTable.size() - 1) {
				System.out.println("");
			}
		}
	}

	/**
	 * Prints out the id of the commit that has the given commit message. If
	 * there are multiple such commits, it prints the ids out on separate lines.
	 *
	 * @param message
	 *            a string that will be used to look for a commit to status
	 */
	public static void find(String message) {
		Status myStatus = (Status) FileOperation.read(".gitlet/myStatus");
		HashSet<Integer> commitTable = myStatus.getCommitTable();
		Commit myCommit;
		boolean hasTheMessage = false;
		for (int i : commitTable) {
			myCommit = (Commit) FileOperation.read(".gitlet/commit" + i
					+ "/commit");
			if (myCommit.getMessage().equals(message)) {
				System.out.println(myCommit.getId());
				hasTheMessage = true;
			}
		}
		if (!hasTheMessage) {
			System.out.println("Found no commit with that message.");
		}
	}

	public static void logOfOneCommit(Commit myCommit) {
		System.out.println("===");
		System.out.println("Commit " + myCommit.getId());
		System.out.println(myCommit.getDate());
		System.out.println(myCommit.getMessage());
	}

	public static void status() {
		Status myStatus = (Status) FileOperation.read(".gitlet/myStatus");
		statusBranch(myStatus);
		statusStage(myStatus);
		statusUntrack(myStatus);
	}

	public static void statusBranch(Status myStatus) {
		System.out.println("=== Branches ===");
		String activeBranchName = myStatus.getActiveBranch().getName();
		System.out.println("*" + activeBranchName);
		HashSet<String> myBranchTable = myStatus.getBranchTable();
		for (String i : myBranchTable) {
			if (!i.equals(activeBranchName)) {
				System.out.println(i);
			}
		}
	}

	public static void statusStage(Status myStatus) {
		HashMap<String, Integer> Files = myStatus.getFilePaths();
		System.out.println("");
		System.out.println("=== Staged Files ===");
		if (Files.size() != 0) {
			for (String file : Files.keySet()) {
				System.out.println(file);
			}
		}
	}

	public static void statusUntrack(Status myStatus) {
		HashSet<String> untrack = myStatus.getUntrack();
		System.out.println("");
		System.out.println("=== Files Marked for Untracking ===");
		if (untrack.size() != 0) {
			for (String i : untrack) {
				System.out.println(i);
			}
		}
	}

	/**
	 * checkout file according to the input message
	 * 
	 * @param args
	 *            input message from terminal
	 * @param myStatus
	 * 
	 *            return nothing
	 */
	public static void checkout(String[] args) {
		Status myStatus = (Status) FileOperation.read(".gitlet/myStatus");
		if (args.length == 3) {
			if (!myStatus.containsCommit(Integer.parseInt(args[1]))) {
				System.out.println("No commit with that id exists.");
			} else if (!checkoutFileFromCommit(Integer.parseInt(args[1]),
					args[2])) {
				System.out.println("File does not exist in that commit.");
			}
		} else if (args.length == 2) {
			if (myStatus.containsBranch(args[1])) {
				if (myStatus.getActiveBranch().getName().equals(args[1])) {
					System.out
							.println("No need to checkout the current branch.");
				} else {
					Branch retrievedBranch = (Branch) FileOperation
							.read(".gitlet/branch/" + args[1]);
					Commit current = (Commit) FileOperation
							.read(".gitlet/commit"
									+ retrievedBranch.getCommitId() + "/commit");
					HashMap<String, Integer> hashMapFileCollection = current
							.getFileCollection();
					for (Entry<String, Integer> entry : hashMapFileCollection
							.entrySet()) {
						String pathName = entry.getKey();
						int id = entry.getValue();
						checkoutFileFromCommit(id, pathName); // iterate over a
																// hashmap from
																// stackflow
					}
					myStatus.updateActiveBranch(retrievedBranch);
					myStatus.serializeStatus();
				}
			} else {
				if (!checkoutFileFromCommit(myStatus.getActiveBranch()
						.getCommitId(), args[1])) {
					System.out
							.println("File does not exist in the most recent commit,"
									+ " or no such branch exists.");
				}
			}
		}
	}

	public static boolean checkoutFileFromCommit(int commitId, String pathName) {
		Commit current = (Commit) FileOperation.read(".gitlet/commit"
				+ commitId + "/commit");
		if (current.doesTrack(pathName)) {
			FileOperation.copy(
					".gitlet/commit" + current.getFileCommitId(pathName) + "/"
							+ pathName, pathName);
			return true;
		} else
			return false;
	}

	/**
	 * create a new branch and make it the active branch if it does not exist at
	 * before
	 * 
	 * @param branchName
	 *            new branch's name
	 */
	public static void branch(String branchName) {
		Status myStatus = (Status) FileOperation.read(".gitlet/myStatus");
		if (myStatus.containsBranch(branchName)) {
			System.out.println("A branch with that name already exists.");
		} else {
			Branch newBranch = new Branch(branchName, myStatus
					.getActiveBranch().getCommitId());
			myStatus.addBranch(newBranch);
			myStatus.serializeStatus();
		}
	}

	/**
	 * remove a branch
	 * 
	 * @param branchName
	 *            name of the branch that is gonna be removed
	 */
	public static void removeBranch(String branchName) {
		Status myStatus = (Status) FileOperation.read(".gitlet/myStatus");
		if (!myStatus.containsBranch(branchName)) {
			System.out.println("A branch with that name does not exist.");
		} else if (myStatus.getActiveBranch().getName().equals(branchName)) {
			System.out.println("Cannot remove the current branch.");
		} else {
			myStatus.removeBranch(branchName);
			myStatus.serializeStatus();
		}
	}

	/**
	 * recover files from the required commit and move active pointer to that
	 * commit
	 * 
	 * @param message
	 *            commit ID to checkout
	 */
	public static void reset(String message) {
		Status myStatus = (Status) FileOperation.read(".gitlet/myStatus");
		int commitId = Integer.parseInt(message);
		if (myStatus.containsCommit(commitId)) {
			Branch currentBranch = myStatus.getActiveBranch();
			currentBranch.updateCommit(commitId);
			myStatus.serializeStatus();
			Commit current = (Commit) FileOperation.read(".gitlet/commit"
					+ commitId + "/commit");
			HashMap<String, Integer> hashMapFileCollection = current
					.getFileCollection();
			for (Entry<String, Integer> entry : hashMapFileCollection
					.entrySet()) {
				String pathName = entry.getKey();
				int id = entry.getValue();
				checkoutFileFromCommit(id, pathName); // iterate over a
														// hashmap from
														// stackflow
			}
		} else {
			System.out.println("No commit with that id exists.");
		}
	}

	/**
	 * Merges files from the given branch into the current branch.
	 *
	 * @param branch
	 *            name a string of the given branch's name
	 * @return no return value
	 * 
	 * @failure 1) if branch with given name does not exist
	 *          "A branch with that name does not exist." 2) if attempt to merge
	 *          a branch with itself "Cannot merge a branch with itself."
	 */
	/*
	 * if merge would generate an error message beause the commit id does has no
	 * changes in it just let the normal commit error for this go through
	 */
	public static void merge(String branchName) {
		Status myStatus = (Status) FileOperation.read(".gitlet/myStatus");
		int numOfConflicts = 0;
		if (!myStatus.containsBranch(branchName)) {
			System.out.println("A branch with that name does not exist.");
		} else if (myStatus.getActiveBranch().getName().equals(branchName)) {
			System.out.println("Cannot merge a branch with itself.");
		} else {
			// /get three commits
			int splitCommitId = findMergedCommitId(branchName, myStatus);
			HashMap<String, Integer> splitFiles = getFileCollectionFrombranchName(splitCommitId);
			int currentCommitId = myStatus.getActiveBranch().getCommitId();
			HashMap<String, Integer> currentFiles = getFileCollectionFrombranchName(currentCommitId);
			int mergedCommitId = ((Branch) FileOperation.read(".gitlet/branch/"
					+ branchName)).getCommitId();
			HashMap<String, Integer> mergedFiles = getFileCollectionFrombranchName(mergedCommitId);
			for (Entry<String, Integer> entry : mergedFiles.entrySet()) {
				myStatus = (Status) FileOperation.read(".gitlet/myStatus");
				String pathName = entry.getKey();
				int id = entry.getValue();
				if (!splitFiles.containsKey(pathName)) {
					if (!currentFiles.containsKey(pathName)) {// added in the
																// other branch
																// after split
						checkoutFileFromCommit(id, pathName);
						myStatus.addNewStageFilePath(pathName,
								myStatus.numOfCommitted());
						FileOperation.copy(".gitlet/commit" + id + "/"
								+ pathName, ".gitlet/stage/" + pathName);
						myStatus.serializeStatus();
					} else {// added in both branch after split
						numOfConflicts++;
						FileOperation.copy(".gitlet/commit" + id + "/"
								+ pathName, pathName + ".conflicted");
					}
				} else if (splitFiles.get(pathName) != id) {// files contained
															// in the split
					if (!currentFiles.containsKey(pathName)) {// but untracked
																// in the
																// current
																// branch
						checkoutFileFromCommit(id, pathName);
						myStatus.addNewStageFilePath(pathName,
								myStatus.numOfCommitted());
						FileOperation.copy(".gitlet/commit" + id + "/"
								+ pathName, ".gitlet/stage/" + pathName);
						myStatus.serializeStatus();
					} else if (currentFiles.get(pathName).equals(
							splitFiles.get(pathName))) {// never modified in
														// current
						checkoutFileFromCommit(id, pathName);
						myStatus.addNewStageFilePath(pathName,
								myStatus.numOfCommitted());
						FileOperation.copy(".gitlet/commit" + id + "/"
								+ pathName, ".gitlet/stage/" + pathName);
						myStatus.serializeStatus();
					} else {// modified in both
						numOfConflicts++;
						FileOperation.copy(".gitlet/commit" + id + "/"
								+ pathName, pathName + ".conflicted");
					}
				}
				myStatus.serializeStatus();
			}
			if (numOfConflicts == 0) {
				commit("Merged " + myStatus.getActiveBranch().getName()
						+ " with " + branchName);
			} else {
				myStatus.updateIsConflicted();
				myStatus.serializeStatus();
				System.out.println("Encountered a merge conflict");
			}
		}
	}

	public static HashMap<String, Integer> getFileCollectionFrombranchName(
			int id) {
		Commit myCommit = (Commit) FileOperation.read(".gitlet/commit" + id
				+ "/commit");
		HashMap<String, Integer> myFileCollection = myCommit
				.getFileCollection();
		return myFileCollection;
	}

	public static int findMergedCommitId(String branchName, Status myStatus) {
		ArrayList<Integer> currentAncestors = new ArrayList<Integer>();
		int currentCommitId = myStatus.getActiveBranch().getCommitId();
		Commit currentCommit = (Commit) FileOperation.read(".gitlet/commit"
				+ currentCommitId + "/commit");
		while (currentCommit.getParentId() != -1) {
			currentAncestors.add(currentCommit.getId());
			currentCommit = (Commit) FileOperation.read(".gitlet/commit"
					+ currentCommit.getParentId() + "/commit");
		}
		currentAncestors.add(0);
		Branch toMerge = (Branch) FileOperation.read(".gitlet/branch/"
				+ branchName);
		int mergedCommitId = toMerge.getCommitId();
		Commit mergedCommit = (Commit) FileOperation.read(".gitlet/commit"
				+ mergedCommitId + "/commit");
		while (mergedCommit.getParentId() != -1) {
			if (currentAncestors.contains(mergedCommit.getId())) {
				return mergedCommit.getId();
			} else {
				mergedCommit = (Commit) FileOperation.read(".gitlet/commit"
						+ mergedCommit.getParentId() + "/commit");
			}
		}
		return 0;
	}

	/**
	 * Find the split point of the current branch and the given branch, snap off
	 * the current branch and move the current branch to the head of the given
	 * branch.
	 *
	 * @param branch
	 *            name a string of the given branch's name
	 * @return return nothing but modify the given branch by attaching the
	 *         current branch to it
	 * @failure if branch with given name does not exist
	 *          "A branch with that name does not exist" if branch name is the
	 *          same as the current branch name
	 *          " cannot rebase a branch onto itself" if given branch's head is
	 *          in the history of the current branch's head "Already up-to-date"
	 */
	public static void rebase(String branchName) {
		Status myStatus = (Status) FileOperation.read(".gitlet/myStatus");
		if (!myStatus.containsBranch(branchName)) {
			System.out.println("A branch with that name does not exist.");
		} else if (myStatus.getActiveBranch().getName().equals(branchName)) {
			System.out.println("Cannot rebase a branch onto itself.");
		} else {
			int mergedCommitId = findMergedCommitId(branchName, myStatus);
			if (mergedCommitId == getCommitIdFromBranchName(branchName)) {
				System.out.println("Already up-to-date");
			} else if (mergedCommitId == myStatus.getActiveBranch()
					.getCommitId()) {
				myStatus.getActiveBranch().updateCommit(
						getCommitIdFromBranchName(branchName));
				myStatus.serializeStatus();
			} else {
				attachCommits(getCommitIdFromBranchName(branchName),
						mergedCommitId);
				myStatus = (Status) FileOperation.read(".gitlet/myStatus");
				reset(new Integer(myStatus.getActiveBranch().getCommitId())
						.toString());
			}
		}
	}

	public static void attachCommits(int targetCommitId, int splitId) {
		Status myStatus = (Status) FileOperation.read(".gitlet/myStatus");
		ArrayList<Integer> commitsToAttach = getCommitsFromSplit(splitId);
		int attachCommitId;
		myStatus.getActiveBranch().updateCommit(targetCommitId);
		myStatus.serializeStatus();
		HashMap<String, Integer> tempFilePaths = new HashMap<String, Integer>();
		for (int i = commitsToAttach.size() - 1; i >= 0; i--) {
			myStatus = (Status) FileOperation.read(".gitlet/myStatus");
			targetCommitId = myStatus.getActiveBranch().getCommitId();
			attachCommitId = commitsToAttach.get(i);
			HashMap<String, Integer> targetFileCollection = getFileCollectionFromCommitId(targetCommitId);
			Commit attachCommit = (Commit) FileOperation.read(".gitlet/commit"
					+ attachCommitId + "/commit");
			HashMap<String, Integer> attachedFileCollection = getFileCollectionFromCommitId(attachCommitId);
			for (Entry<String, Integer> entry : attachedFileCollection
					.entrySet()) {
				String pathName = entry.getKey();
				int id = entry.getValue();
				if (attachCommitId == id) {
					tempFilePaths.put(pathName, id);
				} else if (!targetFileCollection.containsKey(pathName)) {
					tempFilePaths.put(pathName, id);
				}
			}
			myStatus.serializeStatus();
			myStatus = (Status) FileOperation.read(".gitlet/myStatus");
			Commit a = new Commit(attachCommit.getMessage(), myStatus
					.getActiveBranch().getCommitId(), myStatus, tempFilePaths);
			FileOperation.makeNewFolder(".gitlet/commit" + a.getId());
			FileOperation.write(a, ".gitlet/commit" + a.getId() + "/commit");
			tempFilePaths = new HashMap<String, Integer>();
			myStatus.getActiveBranch().updateCommit(a.getId());
			myStatus.serializeStatus();
		}
	}

	public static HashMap<String, Integer> getFileCollectionFromCommitId(int id) {
		return ((Commit) FileOperation.read(".gitlet/commit" + id + "/commit"))
				.getFileCollection();
	}

	public static int getCommitIdFromBranchName(String branchName) {
		return ((Branch) FileOperation.read(".gitlet/branch/" + branchName))
				.getCommitId();
	}

	public static ArrayList<Integer> getCommitsFromSplit(int SplitId) {
		Status myStatus = (Status) FileOperation.read(".gitlet/myStatus");
		ArrayList<Integer> myCommitsId = new ArrayList<Integer>();
		Commit currentCommit = (Commit) FileOperation.read(".gitlet/commit"
				+ myStatus.getActiveBranch().getCommitId() + "/commit");
		while (currentCommit.getId() != SplitId) {
			myCommitsId.add(currentCommit.getId());
			currentCommit = (Commit) FileOperation.read(".gitlet/commit"
					+ currentCommit.getParentId() + "/commit");
		}
		return myCommitsId;
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please enter a command.");
		} else if (args.length == 1 && args[0].equals("init")) {
			init();
		} else {
			Status myStatus = (Status) FileOperation.read(".gitlet/myStatus");
			if (myStatus.isConflicted()) {
				ConflictedMode.execute(args);
			} else {
				if (args[0].equals("add") && args.length == 2) {
					add(args[1]);
				} else if (args[0].equals("commit")) {
					if (args.length == 1) {
						System.out.println("Please enter a commit message.");
					} else if (args.length != 2) {
						System.out.println("Incorrect commit command format");
					} else {
						commit(args[1]);
					}
				} else if (args[0].equals("rm") && args.length == 2) {
					remove(args[1]);
				} else if (args[0].equals("log") && args.length == 1) {
					log();
				} else if (args[0].equals("global-log") && args.length == 1) {
					globalLog();
				} else if (args[0].equals("find") && args.length == 2) {
					find(args[1]);
				} else if (args[0].equals("status") && args.length == 1) {
					status();
				} else if (args[0].equals("checkout")) {
					checkout(args);
				} else if (args[0].equals("branch") && args.length == 2) {
					branch(args[1]);
				} else if (args[0].equals("rm-branch") && args.length == 2) {
					removeBranch(args[1]);
				} else if (args[0].equals("reset") && args.length == 2) {
					reset(args[1]);
				} else if (args[0].equals("merge") && args.length == 2) {
					merge(args[1]);
				} else if (args[0].equals("rebase") && args.length == 2) {
					rebase(args[1]);
				} else {
					System.out.println("No command with that name exists.");
				}
			}
		}
	}
}
