package proj2;

import java.io.*;
import java.nio.file.Files;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.*;

import static java.nio.file.StandardCopyOption.*;

public class Gitlet implements Serializable {

	private int numberOfCommit;
	// keep track of branches
	// key is the name of the branch
	// value is most recent GitletNode of branch
	private HashMap<String, GitletNode> branches;
	// HashMap of all commits
	// key is commit message
	// value is bucket of all commits that have the same message
	private HashMap<String, LinkedList<GitletNode>> commits;
	private final File STAGING_DIR = new File(".gitlet/staging");
	private final File COMMIT_DIR = new File(".gitlet/commits");
	private LinkedList<String> inStagingDir;
	private HashSet<String> untrack;
	private String currentBranch;
	public boolean isConflicting; // specific for merge
	private HashMap<String, GitletNode> tableOfCommitID;
	// modified for rebase
	// to contain each node that needs to be rebase
	private Stack<GitletNode> nodesToRebase;

	/**
	 * Gitlet constructor, initializes all the instance variables if its the
	 * first time Gitlet gets created, creates all the necessary directories
	 */
	public Gitlet() {
		File gitletDir = new File(".gitlet");
		numberOfCommit = 0;
		untrack = new HashSet<String>();
		branches = new HashMap<String, GitletNode>();
		commits = new HashMap<String, LinkedList<GitletNode>>();
		tableOfCommitID = new HashMap<String, GitletNode>();
		inStagingDir = new LinkedList<String>();

		// modified for rebase
		nodesToRebase = new Stack<GitletNode>();

		currentBranch = "master";
		branches.put(currentBranch, null);
		isConflicting = false;
		if (!gitletDir.exists()) {
			gitletDir.mkdir();
			STAGING_DIR.mkdir();
			COMMIT_DIR.mkdir();
			commit("initial commit");
		}
	}

	/**
	 * make a new commit with a message
	 * 
	 * @param message
	 *            message associated with the commit
	 */
	public void commit(String message) {
		// check if there is anything to commit
		if (numberOfCommit != 0 && inStagingDir.isEmpty() && untrack.isEmpty()) {
			System.out.println("No changes added to the commit.");
			return;
		}

		isConflicting = false;

		// make new GitletNode
		GitletNode commitNode = new GitletNode(message, numberOfCommit,
				branches.get(currentBranch));

		if (numberOfCommit > 0) {
			try {
				/*
				 * add reference of previous files that are not going to be
				 * removed to the new GitletNode folder
				 */
				copyToNewCommit(commitNode);
				/*
				 * at the end of commit staging folder should be empty so we
				 * move files from staging to the new commit
				 */
				moveFromStagingToNewCommit(commitNode);
				untrack.clear();
				for (File file : STAGING_DIR.listFiles())
					file.delete();
			} catch (IOException e) {

			}
		}

		numberOfCommit++;

		branches.put(currentBranch, commitNode);
		if (!commits.containsKey(commitNode.getMessage()))
			commits.put(commitNode.getMessage(), new LinkedList<GitletNode>());
		commits.get(commitNode.getMessage()).add(commitNode);
		tableOfCommitID.put(Integer.toString(commitNode.getID()), commitNode);
	}

	/**
	 * Copy all files from staging folder to the new commit folder and adds the
	 * file name to the list of files in node
	 * 
	 * @param node
	 *            GitletNode to add everything to
	 * @throws IOException
	 */
	private void moveFromStagingToNewCommit(GitletNode node) throws IOException {
		File newCommit = node.getFolder();
		while (!inStagingDir.isEmpty()) {
			String fileName = inStagingDir.pop();
			File file = new File(STAGING_DIR, fileName);
			node.addFile(fileName);
			File newCommitPath = new File(newCommit, fileName);
			newCommitPath.mkdirs();
			Files.move(file.toPath(), newCommitPath.toPath(), REPLACE_EXISTING);
		}
	}

	/**
	 * adds the names of all the files that will not be modified into the list
	 * of files in node, excluding the ones that have been untracked
	 * 
	 * @param node
	 *            node to add file names to
	 */
	private void copyToNewCommit(GitletNode node) {
		ArrayList<String> files = branches.get(currentBranch).getFiles();
		for (String fileName : files)
			if (!inStagingDir.contains(fileName) && !untrack.contains(fileName))
				node.addFile(fileName);
	}

	/**
	 * prints the ID of the commit that has the given message, if there multiple
	 * commits with the given message, then print each of their IDs on a
	 * different line
	 * 
	 * @param commitMessage
	 *            to find the IDs of the commit with the given message
	 */
	public void find(String commitMessage) {
		LinkedList<GitletNode> nodes = commits.get(commitMessage);
		if (nodes == null) {
			System.out.println("Found no commit with that message.");
			return;
		}
		for (GitletNode node : nodes)
			System.out.println(node.getID());
	}

	/**
	 * prints the history of the current branch's head commit
	 */
	public void log() {
		branches.get(currentBranch).printLog();
	}

	/**
	 * displays information of all the commits ever made
	 */
	public void global_log() {
		for (GitletNode node : tableOfCommitID.values())
			node.print();
	}

	/**
	 * add file into the staging folder for the next commit
	 * 
	 * @param fileName
	 *            fileName to add into the staging folder
	 */
	public void add(String fileName) {
		// fileName could be a path to the file
		File fileToAdd = new File(fileName);

		/*
		 * check if file exists in any directories or its subdirectries of the
		 * working directory or its in the working directory ( which is the same
		 * directory that contains the .gitlet folder )
		 */
		if (fileToAdd.isDirectory() || !fileToAdd.exists()) {
			System.out.println("File does not exist.");
			return;
		}

		// if it was marked for "untracking", just unmark it
		if (untrack.contains(fileName)) {
			untrack.remove(fileName);
			return;
		}

		// put it in the staging folder
		try {

			// what if file was in a directory?
			// need to get the file name and not the path
			// use File.getname()

			File toStagingDir = new File(".gitlet/staging/"
					+ fileToAdd.getPath());
			toStagingDir.getParentFile().mkdirs();
			copyFileUsingFileChannels(fileToAdd, toStagingDir);
			inStagingDir.add(fileToAdd.getPath());
		} catch (IOException e) {

		}

	}

	/**
	 * untracks the file for the next commit if it was in the staging folder,
	 * delete it
	 * 
	 * @param fileName
	 *            fileName to be untracked
	 */
	public void remove(String fileName) {
		// if file is not in staging folder
		// or it's not tracked by head commit

		if (!inStagingDir.contains(fileName)
				&& !branches.get(currentBranch).getFiles().contains(fileName)) {
			System.out.println("No reason to remove the file.");
			return;
		}

		// if fileName is in staging folder
		// remove it from staging folder
		if (inStagingDir.contains(fileName)) {
			unstageFile(STAGING_DIR, fileName);
			inStagingDir.remove(fileName);
		}

		// put it in untrack HashSet
		else if (!untrack.contains(fileName)) {
			untrack.add(fileName);
		}

	}

	/**
	 * remove the file from the staging folder
	 * 
	 * @param currentDir
	 *            path of the staging folder
	 * @param fileName
	 *            name of file to be removed
	 */
	private static void unstageFile(File currentDir, String fileName) {
		File file = new File(currentDir, fileName);
		file.delete();
	}

	/**
	 * helping method to copy files from source to dest
	 * 
	 * @param source
	 *            path of the source of the file to be copied
	 * @param dest
	 *            path of the destination of where the file should by copied to
	 * @throws IOException
	 */
	public static void copyFileUsingFileChannels(File source, File dest)
			throws IOException {
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		inputChannel = new FileInputStream(source).getChannel();
		outputChannel = new FileOutputStream(dest).getChannel();
		outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
		inputChannel.close();
		outputChannel.close();
	}

	/**
	 * merge files from the given branch into the current branch; finds the
	 * splitting point ( a commit ) which both the current branch and the given
	 * branch shares as its previous node, and check each corresponding commit
	 * after the splitting point for modified files in order to process the
	 * modified files for the new merged commit
	 * 
	 * @param branchName
	 *            branch name to merge files with from the current branch
	 */
	public void merge(String branchName) {
		// check to see if branch exists
		if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		// cannot merge with same branch
		if (currentBranch.equals(branchName)) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}

		// modified for rebase
		GitletNode splitPoint = getSplitPoint(currentBranch, branchName, false);
		LinkedList<String> modifiedHere = branches.get(currentBranch)
				.getModifiedFiles(splitPoint);
		LinkedList<String> modifiedThere = branches.get(branchName)
				.getModifiedFiles(splitPoint);
		for (String s : modifiedThere)
			if (!modifiedHere.contains(s))
				addForMergeAndRebase(branches.get(branchName).getFile(s));
			else {
				isConflicting = true;
				File toStage = new File(".gitlet/staging/", s + ".conflicting");
				try {
					toStage.createNewFile();
					inStagingDir.add(s + ".conflicting");
					copyFileUsingFileChannels(
							branches.get(branchName).getFile(s), toStage);
				} catch (IOException e) {

				}
			}
		if (!isConflicting)
			commit("Merged " + currentBranch + " with " + branchName);
	}

	/**
	 * Used for merge and rebase; adds the files to given branch name
	 * 
	 * @param branch
	 *            given branch name
	 * @param file
	 *            the file to be added
	 */
	private void addForMergeAndRebase(File file) {
		try {
			String fileName = file.getPath().substring(
					file.getPath().toString().indexOf("/", 16) + 1);
			File toStage = new File(STAGING_DIR, fileName);
			toStage.getParentFile().mkdirs();
			inStagingDir.add(fileName);
			copyFileUsingFileChannels(file, toStage);
		} catch (IOException e) {
		}

	}

	/**
	 * Gets the commit that both the current branch and the given branch's head
	 * commits have in common in both their histories
	 * 
	 * @param currentBranch
	 *            current branch name
	 * @param givenBranch
	 *            given branch name
	 * @param isRebasing
	 *            if we are calling this for rebasing, if so, saves all the
	 *            commits that was traversed
	 * @return split point commit of both branches's commit
	 */
	private GitletNode getSplitPoint(String currentBranch, String givenBranch,
			boolean isRebasing) {
		GitletNode currentBranchNode = branches.get(currentBranch);
		GitletNode givenBranchNode = branches.get(givenBranch);
		while (currentBranchNode != givenBranchNode) {
			if (currentBranchNode.getID() < givenBranchNode.getID())
				givenBranchNode = givenBranchNode.getPrevCommit();
			else {
				// modified for rebase
				if (isRebasing)
					nodesToRebase.add(currentBranchNode);

				currentBranchNode = currentBranchNode.getPrevCommit();
			}
		}
		return currentBranchNode;
	}

	/**
	 * displays what branches current exist, * for the current branch, and what
	 * files have been staged or marked for untracking
	 */
	public void status() {
		System.out.println("=== Branches ===");
		System.out.println("*" + currentBranch);
		for (String branch : branches.keySet()) {
			if (!branch.equals(currentBranch))
				System.out.println(branch);
		}
		System.out.println();

		System.out.println("=== Staged Files ===");
		for (File staged : STAGING_DIR.listFiles()) {
			System.out.println(staged.getPath());
		}
		System.out.println();

		System.out.println("=== Files Marked for Untracking ===");
		for (String untracked : untrack) {
			System.out.println(untracked);
		}
	}

	/**
	 * creating a new branch by creating a reference to the given branch name
	 * 
	 * @param branchName
	 *            branch name to be created
	 */
	public void branch(String branchName) {
		if (branches.containsKey(branchName)) {
			System.out.println("A branch with that name already exists.");
		} else {
			GitletNode curr = branches.get(currentBranch);
			branches.put(branchName, curr);
		}
	}

	/**
	 * remove the branch, so you cannot reference given branch anymore
	 * 
	 * @param branchName
	 *            name of branch to be removed
	 */
	public void removeBranch(String branchName) {
		if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
		}

		else if (currentBranch.equals(branchName)) {
			System.out.println("Cannot remove the current branch.");
		}

		else {
			branches.remove(branchName);
		}
	}

	/**
	 * if the given name is a branch name, then it has priority over the file
	 * name if this is the case, then takes all the files in the head commit of
	 * the current branch and put them in the working directory, overwriting
	 * preexisting files and sets the given branch as the current branch
	 * 
	 * if file name does not match the branch name, then take the file as it
	 * exists in the head commit of the current branch and put it in the working
	 * directory, overwriting preexisting versions
	 * 
	 * @param name
	 *            either a branch name, or file name
	 * @throws IOException
	 */
	public void checkout(String name) throws IOException {
		if (branches.containsKey(name)) {
			if (name.equals(currentBranch)) {
				System.out.println("No need to checkout the current branch.");
			} else {
				GitletNode curr = branches.get(name);
				for (String file : curr.getFiles()) {
					File requestedFile = curr.getFile(file);
					File toWorkingDir = new File(requestedFile.getName());
					copyFileUsingFileChannels(requestedFile, toWorkingDir);
				}
				currentBranch = name;
			}
		} else {
			GitletNode curr = branches.get(currentBranch);
			File toWorkingDir = new File(name);
			File requestedFile = curr.getFile(name);
			if (requestedFile == null) {
				System.out
						.println("File does not exist in the most recent commit, or no such branch exists.");
			} else {
				copyFileUsingFileChannels(requestedFile, toWorkingDir);
			}
		}

	}

	/**
	 * takes the version of each files as it exists in the given commit with the
	 * commit id and puts them in the working directory, overwriting files that
	 * were previously there
	 * 
	 * @param id
	 *            given commit id
	 * @param name
	 *            given fileName
	 * @throws IOException
	 */
	public void checkout(String id, String name) throws IOException {
		GitletNode curr = tableOfCommitID.get(id);
		File toWorkingDir = new File(name);
		if (curr == null) {
			System.out.println("No commit with that id exists.");
		} else {
			File requestedFile = curr.getFile(name);
			if (requestedFile == null) {
				System.out
						.println("File does not exist in the most recent commit, or no such branch exists.");
			} else {
				copyFileUsingFileChannels(requestedFile, toWorkingDir);
			}
		}
	}

	/**
	 * checks out all the files tracked by the commit corresponding to the given
	 * commit ID and set the current branch's head to point to that commit node
	 * 
	 * @param commitID
	 *            to find the corresponding commit node
	 * @throws IOException
	 *             because checkout throws IOException but no exception would be
	 *             thrown because each file exists
	 */
	public void reset(String commitID) throws IOException {
		if (!tableOfCommitID.containsKey(commitID)) {
			System.out.println("No commit with that id exists.");
			return;
		}

		// corresponding commit node of the given commit ID
		GitletNode toReset = tableOfCommitID.get(commitID);

		// need to get contents of node
		// then check out each file tracked by the node
		for (String fileName : toReset.getFiles()) {
			checkout(commitID, fileName);
		}

		// then move current branch's head to point to node
		branches.put(currentBranch, toReset);
	}

	// modified for rebase

	/**
	 * rebase the current branch to the given branch by getting the history
	 * commits of the current branch until the splitting point of current branch
	 * and given branch, then replay the history commits of the current branch
	 * to the given branch at the head of the given branch by the process of
	 * committing new commits
	 * 
	 * @param branchName
	 *            branch name for the current branch to rebase to
	 * @throws IOException
	 */
	public void rebase(String branchName) throws IOException {
		if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (currentBranch.equals(branchName)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}

		GitletNode givenBranchHead = branches.get(branchName);
		GitletNode currentBranchHead = branches.get(currentBranch);

		// actual rebase

		// in getSplitPoint, all the nodes to be rebase are saved in a linked
		// list
		// because last argument is true, indicating that we're rebasing
		GitletNode splitPoint = getSplitPoint(currentBranch, branchName, true);
		if (splitPoint.equals(givenBranchHead)) {
			System.out.println("Already up-to-date.");
			return;
		}
		// special case:
		// if current branch's head is in history of given branch's head,
		// just move current branch to point to give branch's commit
		if (splitPoint.equals(currentBranchHead)) {
			branches.put(currentBranch, givenBranchHead);
			return;
		}

		// get modified files of currentBranchHead
		// to propagate the modified files

		branches.put(currentBranch, givenBranchHead);
		LinkedList<String> toPropagate = givenBranchHead
				.getModifiedFiles(splitPoint);
		while (!nodesToRebase.isEmpty()) {
			// next node to be rebase
			GitletNode nextToRebase = nodesToRebase.pop();

			// add files from node to the staging directory
			for (String fileName : nextToRebase.getFiles())
				if (new File(nextToRebase.getFolder(), fileName).exists())
					addForMergeAndRebase(currentBranchHead.getFile(fileName));

			// propagate files
			for (String fileName : toPropagate) {
				if (!inStagingDir.contains(fileName))
					addForMergeAndRebase(givenBranchHead.getFile(fileName));
				else
					toPropagate.remove(fileName);
			}

			// commit
			commit(nextToRebase.getMessage());
		}

		// finally, reset to node at the front of the replayed branch, which
		// should be the latest commit ID
		reset(Integer.toString(numberOfCommit - 1));
	}

	/*****************************************************************************/
	/**
	 * The next methods are for testing purpose ONLY
	 */

	public HashMap<String, GitletNode> getBranches() {
		return branches;
	}

	public HashMap<String, LinkedList<GitletNode>> getCommits() {
		return commits;
	}

	public HashSet<String> getUntrack() {
		return untrack;
	}

	public String getCurrentBranch() {
		return currentBranch;
	}

	/*****************************************************************************/
	public static void main(String[] args) {
		Gitlet gitlet = null;
		try {
			FileInputStream fileIn = new FileInputStream(new File(".gitlet",
					"Gitlet.ser"));
			ObjectInputStream in = new ObjectInputStream(fileIn);
			gitlet = (Gitlet) in.readObject();
			in.close();
			fileIn.close();
			if (args.length == 0)
				System.out.println("Please enter a command.");
			else if (args[0].equals("commit")) {
				if (args.length == 2 && args[1].trim().length() != 0)
					gitlet.commit(args[1]);
				else
					System.out.println("Please enter a commit message.");
			} else if (args[0].equals("add"))
				gitlet.add(args[1]);
			else if (args[0].equals("rm"))
				gitlet.remove(args[1]);
			else if (args[0].equals("log"))
				gitlet.log();
			else if (args[0].equals("init")) {
				if (gitlet == null)
					gitlet = new Gitlet();
				else
					System.out
							.println("A gitlet version control system already exists in the current directory.");
			} else if (args[0].equals("merge"))
				gitlet.merge(args[1]);
			else if (args[0].equals("branch"))
				gitlet.branch(args[1]);
			else if (args[0].equals("status"))
				gitlet.status();
			else if (args[0].equals("rm-branch"))
				gitlet.removeBranch(args[1]);
			else if (args[0].equals("checkout")) {
				if (args.length == 2)
					gitlet.checkout(args[1]);
				else if (args.length == 3)
					gitlet.checkout(args[1], args[2]);
			} else if (args[0].equals("rebase")) {
				gitlet.rebase(args[1]);
			} else if (args[0].equals("global-log"))
				gitlet.global_log();
			else if (args[0].equals("reset"))
				gitlet.reset(args[1]);
			else
				System.out.println("No command with that name exists.");
			FileOutputStream fileOut = new FileOutputStream(new File(".gitlet",
					"Gitlet.ser"));
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(gitlet);
			out.close();
			fileOut.close();
		} catch (Exception e) {}
	}
}