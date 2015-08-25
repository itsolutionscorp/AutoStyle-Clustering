import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class Gitlet implements Serializable {
	private static File GITLET_FOLDER = new File(".gitlet/");
	private static File STAGING_AREA = new File(".gitlet/staging/");
	private static File PERMANENT = new File(".gitlet/permanent/");
	private int IDCounter;
	private Map<String, commitNode> myBranchHeads;
	private static final long serialVersionUID = 1L;
	private String myCurrBranch;
	private ArrayList<File> myMarkedForUntracking;
	private Map<Integer, commitNode> myCommitIDs;
	private boolean conflictedState;

	/**
	 * Gitlet constructor
	 * 
	 * Creates new Gitlet object.
	 * 
	 * @param (none)
	 * @return (none)
	 * @throws (none)
	 */
	public Gitlet() {
		GITLET_FOLDER.mkdir();
		STAGING_AREA.mkdir();
		PERMANENT.mkdir();
		IDCounter = 0;
		myBranchHeads = new HashMap<String, commitNode>();
		myCommitIDs = new HashMap<Integer, commitNode>();
		commitNode initialCommit = new commitNode(null, null, "initial commit",
				IDCounter);
		myBranchHeads.put("master", initialCommit);
		myCommitIDs.put(IDCounter, initialCommit);
		myCurrBranch = "master";
		IDCounter++;
		myMarkedForUntracking = new ArrayList<File>();
		conflictedState = false;
	}

	/**
	 * add method
	 * 
	 * Adds a copy of the file as it currently exists to the staging area.
	 * 
	 * @param fileName
	 *            a string designating the name of a file
	 * @return (none)
	 * @throws (none)
	 */
	public void add(String fileName) {
		File fileToAdd = new File(fileName);
		if (fileToAdd.getParentFile() != null) {
			File newFolder = new File(".gitlet/staging/"
					+ fileToAdd.getParent());
			newFolder.mkdirs();
		}
		if (!fileToAdd.exists()) {
			System.out.println("File does not exist.");
			return;
		}
		if (myMarkedForUntracking.contains(fileToAdd)) {
			myMarkedForUntracking.remove(fileToAdd);
		} else {
			File fileCopy = new File(STAGING_AREA, fileName);
			copyFile(fileToAdd, fileCopy);
		}
	}

	/**
	 * commit method
	 * 
	 * Moves files from staging area to permanent folder. Creates a new
	 * commitNode.
	 * 
	 * @param message
	 *            a string designating a message that will be associated with
	 *            the new commitNode that will be created
	 * @return (none)
	 * @throws (none)
	 */
	public void commit(String message) {
		File[] filesToCommit = STAGING_AREA.listFiles();
		if (filesToCommit.length == 0 && myMarkedForUntracking.isEmpty()) {
			System.out.println("No changes added to the commit.");
			return;
		}
		if (message == null) {
			System.out.println("Please enter a commit message.");
			return;
		}
		commitNode newCommit = new commitNode(myBranchHeads.get(myCurrBranch),
				filesToCommit, message, IDCounter);
		for (File f : filesToCommit) {
			f.renameTo(new File(newCommit.commitFolder, f.getName()));
		}
		myCommitIDs.put(IDCounter, newCommit);
		IDCounter++;
		myBranchHeads.put(myCurrBranch, newCommit);
		myMarkedForUntracking.clear();
	}

	/**
	 * rm method
	 * 
	 * Marks the file for untracking. If the file has been staged, only unstages
	 * the file (and does not also mark it for untracking).
	 * 
	 * @param fileName
	 *            a string designating the name of a file
	 * @return (none)
	 * @throws (none)
	 */
	public void rm(String fileName) {
		File checkIfStaged = new File(STAGING_AREA, fileName);
		if (checkIfStaged.exists()) {
			checkIfStaged.delete();
			return;
		}
		commitNode currNode = myBranchHeads.get(myCurrBranch);
		if (currNode.myFiles.containsKey(currNode.IDNum + "/" + fileName)) {
			myMarkedForUntracking.add(currNode.myFiles.get(currNode.IDNum + "/"
					+ fileName));
			return;
		}
		System.out.println("No reason to remove the file.");
	}

	/**
	 * log method
	 * 
	 * Displays information about all commits in the current head commit's
	 * history.
	 * 
	 * @param (none)
	 * @return (none)
	 * @throws (none)
	 */
	public void log() {
		commitNode pointer = myBranchHeads.get(myCurrBranch);
		while (pointer != null) {
			displayInfo(pointer);
			pointer = pointer.parentNode;
		}
	}

	/**
	 * globallog method
	 * 
	 * Displays information about all commits ever made.
	 * 
	 * @param (none)
	 * @return (none)
	 * @throws (none)
	 */
	public void globallog() {
		for (commitNode commit : myCommitIDs.values()) {
			displayInfo(commit);
		}
	}

	/**
	 * displayInfo method (helper method)
	 * 
	 * Displays information for commitNode n
	 * 
	 * @param n
	 *            a commitNode
	 * @return (none)
	 * @throws (none)
	 */
	public void displayInfo(commitNode n) {
		System.out.println("===");
		System.out.println("Commit " + n.IDNum);
		String properDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(n.timeMade);
		System.out.println(properDateFormat);
		System.out.println(n.myMessage);
		System.out.println();
	}

	/**
	 * find method
	 * 
	 * Prints id of the commit(s) with the given message.
	 * 
	 * @param commitMessage
	 *            a string designating the message associated with zero or more
	 *            commits
	 * @return (none)
	 * @throws (none)
	 */
	public void find(String commitMessage) {
		boolean messageNotFound = true;
		for (commitNode commit : myCommitIDs.values()) {
			if (commitMessage.equals(commit.myMessage)) {
				System.out.println(commit.IDNum);
				messageNotFound = false;
			}
		}
		if (messageNotFound) {
			System.out.println("Found no commit with that message.");
		}

	}

	/**
	 * status method
	 * 
	 * Prints currently existing branches, staged files, and files marked for
	 * untracking. Marks the current branch with a *.
	 * 
	 * @param (none)
	 * @return (none)
	 * @throws (none)
	 */
	public void status() {
		System.out.println("=== Branches ===");
		for (String key : myBranchHeads.keySet()) {
			if (key.equals(myCurrBranch)) {
				System.out.println("*" + key);
			} else {
				System.out.println(key);
			}
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		File[] stagedFiles = STAGING_AREA.listFiles();
		for (File f : stagedFiles) {
			if (f.isDirectory()) {
				for (File fDir : f.listFiles()) {
					System.out.println(f.getName() + "/" + fDir.getName());
				}
			} else {
				System.out.println(f.getName());
			}
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (File f : myMarkedForUntracking) {
			System.out.println(f.getName());
		}
	}

	/**
	 * checkout method (overloaded method)
	 * 
	 * Takes the version of the file as it exists in the commit with the given
	 * id and puts it in the working directory. Overwrites the version of the
	 * file that's already there if there is one.
	 * 
	 * @param commitID
	 *            an int designating the id of a commitNode
	 * @param fileName
	 *            a string designating the name of a file
	 * @return (none)
	 * @throws (none)
	 */
	public void checkout(int commitID, String fileName) {
		if (myCommitIDs.get(commitID) == null) {
			System.out.println("No commit with that id exists.");
			return;
		}
		File file = new File(myCommitIDs.get(commitID).commitFolder, fileName);
		if (file.exists()) {
			File fileCopy = new File(fileName);
			copyFile(file, fileCopy);
			return;
		}
		File[] subDir = new File("" + myCommitIDs.get(commitID).commitFolder)
				.listFiles(File::isDirectory);
		for (File dir : subDir) {
			File fileInDir = new File(dir, fileName);
			if (fileInDir.exists()) {
				File fileCopy = new File(fileName);
				copyFile(fileInDir, fileCopy);
				return;
			}
		}
		System.out.println("File does not exist in that commit.");
	}

	/**
	 * checkout method (overloaded method)
	 * 
	 * Parameter will be either a branch name or file name.
	 * 
	 * If branch name: Takes all files in the commit at the head of the given
	 * branch and puts them in the working directory. Overwrites the versions of
	 * the files that are already there if they exist. At the end of this
	 * command, the given branch will now be considered the current branch.
	 * 
	 * If file name: Takes the version of the file as it exists in the the front
	 * of the current branch and puts it in the working directory. Overwrites
	 * the version of the file that's already there if there is one.
	 * 
	 * @param branchOrFileName
	 *            a string designating the name of either a branch or file
	 * @return (none)
	 * @throws (none)
	 */
	public void checkout(String branchOrFileName) {
		if (myBranchHeads.containsKey(branchOrFileName)) {
			if (myCurrBranch.equals(branchOrFileName)) {
				System.out.println("No need to checkout the current branch.");
				return;
			} else {
				for (File f : myBranchHeads.get(branchOrFileName).myFiles
						.values()) {
					if (f.isFile()) {
						File file = new File(
								myBranchHeads.get(branchOrFileName).commitFolder,
								f.getName());
						File fileCopy = new File(f.getName());
						copyFile(file, fileCopy);
					} else if (f.isDirectory()) {
						File[] filesInSubDir = f.listFiles();
						for (File subFile : filesInSubDir) {
							File file = new File(f, subFile.getName());
							File fileCopy = new File(subFile.getName());
							copyFile(file, fileCopy);
						}
					}
				}
				myCurrBranch = branchOrFileName;
				return;
			}
		}
		commitNode currNode = myBranchHeads.get(myCurrBranch);
		File file = new File(currNode.commitFolder, branchOrFileName);
		if (file.exists()) {
			File fileCopy = new File(branchOrFileName);
			copyFile(file, fileCopy);
			return;
		}
		File[] subDir = new File("" + currNode.commitFolder)
				.listFiles(File::isDirectory);
		for (File dir : subDir) {
			File fileInDir = new File(dir, branchOrFileName);
			if (fileInDir.exists()) {
				File fileCopy = new File(branchOrFileName);
				copyFile(fileInDir, fileCopy);
				return;
			}
		}
		System.out
				.println("File does not exist in the most recent commit, or no such branch exists.");
	}

	/**
	 * copyFile method (helper method)
	 * 
	 * Copies a file at a pathname to another pathname. If a version of the file
	 * already exists in the copy location, overwrites it.
	 * 
	 * @param from
	 *            a file designating pathname of original file
	 * @param to
	 *            a file designating pathname of copied file
	 * @return (none)
	 * @throws (none)
	 */
	public void copyFile(File from, File to) {
		try {
			Files.copy(from.toPath(), to.toPath(),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
		}
	}

	/**
	 * branch method
	 * 
	 * Creates a new branch and points it at the current head node.
	 * 
	 * @param branchName
	 *            a string designating the name of the new branch
	 * @return (none)
	 * @throws (none)
	 */
	public void branch(String branchName) {
		if (!myBranchHeads.containsKey(branchName)) {
			myBranchHeads.put(branchName, myBranchHeads.get(myCurrBranch));
		} else {
			System.out.println("A branch with that name already exists.");
		}
	}

	/**
	 * rmbranch method
	 * 
	 * Deletes the pointer associated with the given branch.
	 * 
	 * @param branchName
	 *            a string designating the name of the given branch
	 * @return (none)
	 * @throws (none)
	 */
	public void rmbranch(String branchName) {
		if (!myBranchHeads.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
		} else if (myCurrBranch.equals(branchName)) {
			System.out.println("Cannot remove the current branch.");
		} else {
			myBranchHeads.remove(branchName);
		}
	}

	/**
	 * reset method
	 * 
	 * Checks out all the files tracked by the given commit and moves the
	 * current branch's pointer to that commit.
	 * 
	 * @param commitID
	 *            an int designating the id of a commitNode
	 * @return (none)
	 * @throws (none)
	 */
	public void reset(int commitID) {
		if (myCommitIDs.get(commitID) == null) {
			System.out.println("No commit with that id exists.");
			return;
		}
		for (File f : myCommitIDs.get(commitID).myFiles.values()) {
			if (f.isFile()) {
				File file = new File(myCommitIDs.get(commitID).commitFolder,
						f.getName());
				File fileCopy = new File(f.getName());
				copyFile(file, fileCopy);
			} else if (f.isDirectory()) {
				File[] filesInSubDir = f.listFiles();
				for (File subFile : filesInSubDir) {
					File file = new File(f, subFile.getName());
					File fileCopy = new File(subFile.getName());
					copyFile(file, fileCopy);
				}
			}
		}
		myBranchHeads.put(myCurrBranch, myCommitIDs.get(commitID));
	}

	/**
	 * merge method
	 * 
	 * Merges files from the given branch into the current branch.
	 * 
	 * @param branchName
	 *            a string designating the name of a branch
	 * @return (none)
	 * @throws (none)
	 */
	public void merge(String branchName) {
		String fileNameToUse = null;

		if (!myBranchHeads.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (myCurrBranch == branchName) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}
		for (File f1 : myBranchHeads.get(myCurrBranch).myFiles.values()) {
			for (File f2 : findSplitPoint(branchName).myFiles.values()) {
				for (File f3 : myBranchHeads.get(branchName).myFiles.values()) {
					if (f1.getName().equals(f2.getName())
							&& equalContents(f1, f2) && !equalContents(f2, f3)) {
						f1 = f3;
					} else if (f1.getName().equals(f2.getName())
							&& !equalContents(f1, f2) && !equalContents(f2, f3)) {
						System.out.println("Encountered a merge conflict.");
						conflictedState = true;

						String naming = f3.toPath().toString();
						for (int i = 0, count = 0; i < naming.length(); i++) {
							char c = naming.charAt(i);
							if (c == '/') {
								count++;
							}
							if (count == 3) {
								fileNameToUse = naming.substring(i + 1,
										naming.length());
							}
						}

						File fileToAdd = new File(fileNameToUse + ".conflicted");
						try {
							Files.copy(f3.toPath(), fileToAdd.toPath(),
									StandardCopyOption.REPLACE_EXISTING);
						} catch (IOException e) {
						}

					} else if (!myBranchHeads.get(branchName).myFiles.values()
							.contains(f2)
							&& myBranchHeads.get(branchName).myFiles.values()
									.contains(f2)) {
						myMarkedForUntracking.add(f2);
					}
				}
			}
		}
		if (!conflictedState) {
			commit("Merged " + myCurrBranch + " with " + branchName + ".");
		}
	}

	/**
	 * method listAllFiles (helper method)
	 * 
	 * Returns a HashMap of all file name and file pairs in a directory and its
	 * sub-directories.
	 * 
	 * @param pathToDir
	 *            a string designating the path to a directory
	 * @return HashMap<String, File>
	 * @throws (none)
	 */
	public HashMap<String, File> listAllFiles(String pathToDir) {
		HashMap<String, File> allFiles = new HashMap<String, File>();
		File dir = new File(pathToDir);
		for (File file : dir.listFiles()) {
			if (file.isFile()) {
				allFiles.put(file.getName(), file);
			} else if (file.isDirectory()) {
				for (File subFile : file.listFiles()) {
					allFiles.put(subFile.getName(), subFile);
				}
			}
		}
		return allFiles;
	}

	/**
	 * rebase method
	 * 
	 * Finds the split point of the current branch and the given branch, replays
	 * the current branch (up to the split point) on top of the given branch,
	 * and resets to the node at the front of the replayed branch. If the commit
	 * at the front of the given branch has files that have been modified since
	 * the split point, these changes should propagate through the replay until
	 * one of the replayed commits has a version of the file that had also been
	 * modified since the split point. If the current branch pointer is in the
	 * history of the given branch, just moves the current branch to point to
	 * the same commit that the given branch points to (no commits are
	 * replayed).
	 * 
	 * @param branchName
	 *            a string designating the name of a branch
	 * @return (none)
	 * @throws (none)
	 */
	public void rebase(String branchName) {
		ArrayList<commitNode> currBranchCommits = branchCommits(myCurrBranch,
				null);
		commitNode branchNamePointer = myBranchHeads.get(branchName);
		if (!myBranchHeads.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (myCurrBranch.equals(branchName)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		} else if (currBranchCommits.contains(branchNamePointer)) {
			System.out.println("Already up-to-date.");
			return;
		}
		commitNode splitPoint = findSplitPoint(branchName);
		if (splitPoint == null) {
			myBranchHeads.put(myCurrBranch, myBranchHeads.get(branchName));
		} else {
			ArrayList<File> modifiedFiles = new ArrayList<File>();
			ArrayList<File> originalFiles = new ArrayList<File>();
			HashMap<String, File> splitMap = listAllFiles(""
					+ splitPoint.commitFolder);
			for (File f1 : listAllFiles("" + branchNamePointer.commitFolder)
					.values()) {
				if (splitMap.containsKey(f1.getName())
						&& !equalContents(splitMap.get(f1.getName()), f1)) {
					modifiedFiles.add(f1);
					originalFiles.add(splitMap.get(f1.getName()));
				}
			}
			ArrayList<commitNode> commitsToReplay = branchCommits(myCurrBranch,
					splitPoint);
			int index = commitsToReplay.size() - 1;
			commitNode newParentNode = branchNamePointer;
			while (index >= 0) {
				File[] replayFiles = new File[listAllFiles(
						"" + commitsToReplay.get(index).commitFolder).values()
						.size()];
				replayFiles = listAllFiles(
						"" + commitsToReplay.get(index).commitFolder).values()
						.toArray(replayFiles);
				for (File f : replayFiles) {
					for (int i = 0; i < originalFiles.size(); i++) {
						File splitPointFile = originalFiles.get(i);
						if (splitPointFile != null) {
							if (f.getName().equals(splitPointFile.getName())
									&& equalContents(f, splitPointFile)) {
								f = modifiedFiles.get(i);
							} else if (f.getName().equals(
									splitPointFile.getName())
									&& !equalContents(f, splitPointFile)) {
								modifiedFiles.set(i, null);
								originalFiles.set(i, null);
							}
						}
					}
				}
				commitNode replayedNode = new commitNode(newParentNode,
						replayFiles, commitsToReplay.get(index).myMessage,
						IDCounter);
				newParentNode = replayedNode;
				myCommitIDs.put(IDCounter, replayedNode);
				IDCounter++;
				index--;
			}
			reset(IDCounter - 1);
		}
	}

	/**
	 * branchCommits method (helper method)
	 * 
	 * Puts commitNodes of branchName into an ArrayList, with the first element
	 * being the head commitNode of the branch and the last element being the
	 * commitNode to the right of stoppingPoint.
	 * 
	 * @param branchName
	 *            a string designating the name of a branch
	 * @param stoppingPoint
	 *            a commitNode
	 * @return ArrayList<commitNode>
	 * @throws (none)
	 */
	public ArrayList<commitNode> branchCommits(String branchName,
			commitNode stoppingPoint) {
		ArrayList<commitNode> branchNameCommits = new ArrayList<commitNode>();
		commitNode givenBranchPointer = myBranchHeads.get(branchName);
		while (givenBranchPointer != stoppingPoint) {
			branchNameCommits.add(givenBranchPointer);
			givenBranchPointer = givenBranchPointer.parentNode;
		}
		return branchNameCommits;
	}

	/**
	 * findSplitPoint method (helper method)
	 * 
	 * Given a branch, finds the split point of that branch and the current
	 * branch, and returns the node.
	 * 
	 * @param givenBranch
	 *            a string designating the name of a branch
	 * @return null if current branch pointer is in history of given branch
	 * @return commitNode otherwise
	 * @throws (none)
	 */
	public commitNode findSplitPoint(String givenBranch) {
		ArrayList<commitNode> givenBranchCommits = branchCommits(givenBranch,
				null);
		commitNode currBranchPointer = myBranchHeads.get(myCurrBranch);
		if (givenBranchCommits.contains(currBranchPointer)) {
			return null;
		} else {
			currBranchPointer = currBranchPointer.parentNode;
			while (currBranchPointer != null) {
				if (givenBranchCommits.contains(currBranchPointer)) {
					return currBranchPointer;
				}
				currBranchPointer = currBranchPointer.parentNode;
			}
			return null;
		}

	}

	/**
	 * tracking method (overloaded helper method)
	 * 
	 * Prints files being tracked by current branch's head commit.
	 * 
	 * @param (none)
	 * @return (none)
	 * @throws (none)
	 */
	public void tracking() {
		System.out.println("=== Files being tracked by head pointer ===");
		for (File f : myBranchHeads.get(myCurrBranch).myFiles.values()) {
			System.out.println(f.getName());
		}
	}

	/**
	 * tracking method (overloaded helper method)
	 * 
	 * Prints filed being tracked by commit with given id.
	 * 
	 * @param ID
	 *            an int designating the commit id of a commitNode
	 */
	public void tracking(int ID) {
		System.out.println("=== Files being tracked by commitNode with ID #"
				+ ID + " ===");
		if (myCommitIDs.get(ID) != null) {
			for (File f : myCommitIDs.get(ID).myFiles.values()) {
				System.out.println(f.getName());
			}
		}

	}

	/**
	 * equalContents method (helper method)
	 * 
	 * Checks whether two files have the same contents.
	 * 
	 * @param file1
	 *            a File
	 * @param file2
	 *            a File
	 * @return true if the parameters' contents are the same
	 * @return false otherwise
	 */
	public boolean equalContents(File file1, File file2) {
		Path path1 = file1.toPath();
		Path path2 = file2.toPath();
		try {
			return Arrays.equals(Files.readAllBytes(path1),
					Files.readAllBytes(path2));
		} catch (IOException e) {
		}
		return false;
	}

	/**
	 * deserialize method
	 * 
	 * Reads from saved .ser file.
	 * 
	 * @param Gitlet
	 *            g
	 * @return Gitlet g
	 * @throws IOException
	 */
	public static Gitlet deserialize() throws IOException {
		FileInputStream in = new FileInputStream(".gitlet/git.ser");
		ObjectInputStream ois = new ObjectInputStream(in);
		try {
			Gitlet g = (Gitlet) ois.readObject();
			ois.close();
			return g;
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * serialize method
	 * 
	 * Writes to .ser file.
	 * 
	 * @param Gitlet
	 *            g
	 * @return (none)
	 * @throws IOException
	 */
	public static void serialize(Gitlet g) throws IOException {
		try {
			FileOutputStream out = new FileOutputStream(".gitlet/git.ser");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(g);
			oos.close();
		} catch (Exception e) {
		}
	}

	/**
	 * interpretCommand method
	 * 
	 * Interprets command and sends to correct method.
	 * 
	 * @param args
	 *            an array of strings from main
	 * @return (none)
	 * @throws (none)
	 */
	public void interpretCommand(String[] args) {
		if (args.length == 0) {
			System.out.println("Please enter a command.");
			return;
		}
		try {
			String command = args[0];
			switch (command) {
			case "add":
				add(args[1]);
				return;
			case "commit":
				if (args.length == 1) {
					commit(null);
				} else if (args.length == 2) {
					commit(args[1]);
				}
				return;
			case "rm":
				rm(args[1]);
				return;
			case "log":
				log();
				return;
			case "global-log":
				globallog();
				return;
			case "find":
				find(args[1]);
				return;
			case "status":
				status();
				return;
			case "checkout":
				if (args.length == 3) {
					int num = Integer.parseInt(args[1]);
					checkout(num, args[2]);
				} else if (args.length == 2) {
					checkout(args[1]);
				}
				return;
			case "branch":
				if (!conflictedState) {
					branch(args[1]);
					return;
				} else {
					System.out
							.println("Cannot do this command until the merge conflict has been resolved.");
					return;
				}
			case "rm-branch":
				if (!conflictedState) {
					rmbranch(args[1]);
					return;
				} else {
					System.out
							.println("Cannot do this command until the merge conflict has been resolved.");
					return;
				}
			case "reset":
				if (!conflictedState) {
					int num = Integer.parseInt(args[1]);
					reset(num);
					return;
				} else {
					System.out
							.println("Cannot do this command until the merge conflict has been resolved.");
					return;
				}
			case "merge":
				if (!conflictedState) {
					merge(args[1]);
					return;
				} else {
					System.out
							.println("Cannot do this command until the merge conflict has been resolved.");
					return;
				}
			case "rebase":
				if (!conflictedState) {
					rebase(args[1]);
					return;
				} else {
					System.out
							.println("Cannot do this command until the merge conflict has been resolved.");
					return;
				}
			case "tracking":
				if (args.length == 1) {
					tracking();
				} else if (args.length == 2) {
					tracking(Integer.parseInt(args[1]));
				}
				return;
			}
			System.out.println("No command with that name exists.");
		} catch (Exception e) {
		}
	}

	public class commitNode implements Serializable {
		private int IDNum;
		private Date timeMade;
		private commitNode parentNode;
		private String myMessage;
		private Map<String, File> myFiles;
		private static final long serialVersionUID = 2L;
		private File commitFolder;

		/**
		 * commitNode constructor
		 * 
		 * Creates new commitNode object.
		 * 
		 * @param parent
		 *            a commitNode designating the parent of the commitNode that
		 *            is currently being created
		 * @param files
		 *            a File array
		 * @param message
		 *            a String designating a message that will be associated
		 *            with the commitNode
		 * @param num
		 *            an int designating a unique id that will be associated
		 *            with the commitNode
		 * @return (none)
		 * @throws (none)
		 */
		public commitNode(commitNode parent, File[] files, String message,
				int num) {
			parentNode = parent;
			IDNum = num;
			commitFolder = new File(".gitlet/permanent/" + IDNum);
			commitFolder.mkdir();
			timeMade = new Date();
			myMessage = message;
			myFiles = new HashMap<String, File>();
			if (parentNode != null) {
				for (File f : parentNode.myFiles.values()) {
					if (!myMarkedForUntracking.contains(f)) {
						myFiles.put(IDNum + "/" + f.getName(), f);
					}
				}
			}
			if (files != null) {
				for (File f : files) {
					myFiles.put(IDNum + "/" + f.getName(), f);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		File gitFile = new File(".gitlet/git.ser");
		if (gitFile.exists()) {
			Gitlet g = deserialize();
			try {
				if (args[0].equals("init")) {
					System.out
							.println("A gitlet version control system already exists in the current directory.");
				} else {
					g.interpretCommand(args);
					serialize(g);
				}
			} catch (Exception e) {
			}
		} else {
			try {
				if (args[0].equals("init")) {
					Gitlet g = new Gitlet();
					serialize(g);
				}
			} catch (Exception e) {
			}
		}
	}
}