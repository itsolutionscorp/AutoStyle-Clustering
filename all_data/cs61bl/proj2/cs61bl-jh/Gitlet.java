import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * A simple version control system named Gitlet
 * 
 * @since TUE JUL 28 2015
 * 
 * @author Zihao Jing
 * @author Pingao Liu
 * @author Yuchao Gao
 * 
 * @version the last!
 * 
 * @category just go ahead
 * 
 */
public class Gitlet {

	private static final String GIT = ".gitlet/";
	private static final String INDEX = ".gitlet/.index/";
	private static final String INDEX_SER = ".gitlet/.index/.ser/";
	private static final String STAGE = ".gitlet/.stage/";
	private static final String BRANCH = ".gitlet/.branch/.map.ser";
	private static final String NOW_BRANCH_SER = ".gitlet/.branch/.nowName.ser";
	private static final String CURRENT_MODIFIED_FILES_SER = ".gitlet/.index/.ser/.current.ser";
	private static final String GIVEN_MODIFIED_FILES_SER = ".gitlet/.index/.ser/.given.ser";
	private static final String CFD_FILES_SER = ".gitlet/.index/.ser/.conflicted.ser";
	private static final String NOW_SER = ".gitlet/.ser/.now.ser";
	private static final String COMMIT_ID_SER = ".gitlet/.ser/.commitId.ser";
	private static final String COMMIT_MSG_SER = ".gitlet/.ser/.commitMsg.ser";
	private static final String IS_CONFLICTED = ".gitlet/.conflict.ed";
	private static final String INIT_COMMIT = "initial commit";
	private static final String MST = "master";

	private static final Path GIT_PATH = Paths.get(GIT);
	private static final Path INDEX_PATH = Paths.get(INDEX);
	private static final Path STAGE_PATH = Paths.get(STAGE);
	private static final Path INDEX_SER_PATH = Paths.get(INDEX_SER);
	private static final Path INIT_CMT_PATH = Paths.get(".gitlet/.index/.0/");
	private static final Path BRANCH_PATH = Paths.get(".gitlet/.branch/");
	private static final Path COMMIT_SER_PATH = Paths.get(".gitlet/.ser/");

	private static int nowCmtNum = 0;
	private static String nowBrchName = "";
	private static Node now = null;
	private Node zero = new Node(0, INIT_COMMIT, null);
	private boolean isConflicted = false;

	/**
	 * Gitlet constructor, set the current branch name and current node we are
	 * working on
	 * 
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 */
	Gitlet() throws ClassNotFoundException, IOException {
		setNowBranch(Node.getNowBranch());
		setNow(Node.getBranchNode(getNowBranch()));
		setConflicted(Stage.getText(IS_CONFLICTED).equals("true"));
		if (new File(INDEX).list() != null)
			nowCmtNum = new File(INDEX).list().length - 2;
	}

	/**
	 * Re-set the system, it's just for convenient
	 * 
	 * @param path
	 *            the directory path
	 */
	private static void clear(String path) {
		System.out.println("Are you sure? ");
		@SuppressWarnings("resource")
		String input = new Scanner(System.in).nextLine();
		if (input.equals("y")) {
			deleteDirectory(path);
			System.out.println("Done!");
		} else if (input.equals("n")) {
			System.exit(0);
		} else
			clear(path);
	}

	/**
	 * Delete a directory including itself
	 * 
	 * @param path
	 *            the directory path
	 */
	private static void deleteDirectory(String path) {
		File f = new File(path);
		if (f.isDirectory()) {
			File[] fileList = f.listFiles();
			for (File s : fileList) {
				if (s.isDirectory()) {
					deleteDirectory(s.getPath());
				} else {
					s.delete();
					System.out.println(s + " deleted");
				}
			}
			if (f.delete())
				System.out.println(f + " deleted");
		} else if (f.delete())
			System.out.println(f + " deleted");
	}

	/**
	 * Create a .gitlet/ directory, initialize a commit with id 0
	 * 
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 */
	private void init() throws IOException, ClassNotFoundException {
		if (Files.notExists(GIT_PATH) && Files.notExists(INIT_CMT_PATH)
				&& Files.notExists(INDEX_SER_PATH)
				&& Files.notExists(STAGE_PATH) && Files.notExists(BRANCH_PATH)
				&& Files.notExists(COMMIT_SER_PATH)) {
			Files.createDirectories(INIT_CMT_PATH);
			Files.createDirectories(INDEX_SER_PATH);
			Files.createDirectories(STAGE_PATH);
			Files.createDirectories(BRANCH_PATH);
			Files.createDirectories(COMMIT_SER_PATH);
			getZero().initCmtBrch();
		} else
			System.out.println("A gitlet version control system "
					+ "already exists in the current directory.");
	}

	/**
	 * Adds a copy of the file as it currently exists to the staging area
	 * 
	 * @param fileName
	 *            a file name string
	 * @param pathFrom
	 *            the path of the file
	 * @param pathCopyTo
	 *            the path we copy to
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 */
	private void add(String fileName, String pathFrom, String pathCopyTo)
			throws IOException, ClassNotFoundException {
		if (Stage.getUntracked() != null
				&& Stage.getUntracked().contains(fileName))
			Stage.rmUntrack(fileName);
		else {
			if ((!addHelper(fileName, pathFrom, pathCopyTo))
					|| new File(fileName).isDirectory())
				System.out.println("File does not exist.");
			else
				addToStage(fileName, pathFrom, pathCopyTo);
		}
	}

	/**
	 * also used in merge, simply add files to stage
	 * 
	 * @param fileName
	 *            a file name string
	 * @param pathFrom
	 *            the path of the file
	 * @param pathCopyTo
	 *            the path we copy to
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 */
	private void addToStage(String fileName, String pathFrom, String pathCopyTo)
			throws IOException, ClassNotFoundException {
		Stage.setCmtChange("true");
		Stage.setStaged(fileName);
		Files.copy(Paths.get(fileName), Paths.get(pathCopyTo + fileName),
				StandardCopyOption.REPLACE_EXISTING);
	}

	/**
	 * To judge if user can make copy
	 * 
	 * @param fileName
	 *            file name to copy
	 * @param pathFrom
	 *            the string path copy from
	 * @param pathCopyTo
	 *            the string path copy to
	 * @return true if added successfully else false if failed
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 */
	private boolean addHelper(String fileName, String pathFrom,
			String pathCopyTo) throws IOException {
		for (String a : new File(pathFrom).list()) {
			if (fileName.contains("/")) {
				String[] nameList = fileName.split("/");
				if (new File(pathFrom + a).isDirectory()
						&& a.equals(nameList[0])) {
					if (!Files.exists(Paths.get(pathCopyTo + a)))
						Files.createDirectories(Paths.get(pathCopyTo + a));
					if (addHelper(fileName.substring(nameList[0].length() + 1),
							pathFrom + nameList[0] + "/", pathCopyTo + a + "/"))
						return true;
				}
			} else if (fileName.equals(a))
				return true;
		}
		return false;
	}

	/**
	 * Commit method to create a new commit
	 * 
	 * @param msg
	 *            commit message string
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 */
	private void commit(String msg) throws IOException, ClassNotFoundException {
		if (msg.isEmpty()) {
			System.out.println("Please enter a commit message.");
		} else if (!Stage.hasCmtChange()) {
			System.out.println("No changes added to the commit.");
		} else {
			String addString = INDEX + "." + (nowCmtNum + 1);
			Path addPath = Paths.get(addString);
			if (Files.notExists(addPath)) {
				String[] fileList = Stage.getAllStaged();
				String[] fileListUt = Stage.getAllUntrack();
				if (fileList != null || fileListUt != null) {
					Node.setNow(getNow().addCmt(msg, fileList, fileListUt));
					Node.setBranch(getNowBranch(), Node.getNow());
					Stage.setCmtChange("false");
					Files.createDirectories(addPath);
					copyCommitFiles(fileList, fileListUt, addString);
					Stage.clear(STAGE);
				} else
					System.out.println("No changes added to the commit.");
			}
		}
	}

	/**
	 * To copy a directory to another directory and overwrite files existing in
	 * the destination directory, it never deletes files!
	 * 
	 * @param preString
	 *            string directory copy from
	 * @param addString
	 *            string directory copy to
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 */
	private static void copyDirectory(String preString, String addString)
			throws IOException {
		if (Files.isDirectory(Paths.get(preString))
				&& Files.isDirectory(Paths.get(addString))) {
			for (String s : new File(preString).list()) {
				if (Files.isDirectory(Paths.get(preString).resolve(s))) {
					Files.createDirectories(Paths.get(addString).resolve(s));
					copyDirectory(preString + "/" + s, addString + "/" + s);
				} else
					Files.copy(Paths.get(preString).resolve(s),
							Paths.get(addString).resolve(s),
							StandardCopyOption.REPLACE_EXISTING);
			}
		}
	}

	/**
	 * A simple method to copy files to a new commit
	 * 
	 * @param fileList
	 *            staged file name list
	 * @param fileListUt
	 *            untracking file name list
	 * @param addString
	 *            the commit filefolder path we are copy to
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 */
	private static void copyCommitFiles(String[] fileList, String[] fileListUt,
			String addString) throws IOException, ClassNotFoundException {
		if (Node.getNow() != null)
			copyDirectory(INDEX + "." + Node.getNow().getPrev().getId(),
					addString);
		if (fileList != null)
			copyDirectory(STAGE, addString);
		if (fileListUt != null)
			for (String t : fileListUt)
				Files.deleteIfExists(Paths.get(addString + "/" + t));
	}

	/**
	 * Just go ahead to add a commit in a merge case
	 * 
	 * @param msg
	 *            commit message
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 */
	private void addCommit(String msg) throws IOException,
			ClassNotFoundException {
		String addString = INDEX + "." + (nowCmtNum + 1);
		Path addPath = Paths.get(addString);
		if (Files.notExists(addPath)) {
			String[] fileList = Stage.getAllStaged();
			String[] fileListUt = Stage.getAllUntrack();
			Node.setNow(Node.getNow().addCmt(msg, fileList, fileListUt));
			Node.setBranch(getNowBranch(), Node.getNow());
			Stage.setCmtChange("false");
			Files.createDirectories(addPath);
			copyCommitFiles(fileList, fileListUt, addString);
			Stage.clear(STAGE);
		}
	}

	/**
	 * Remove method. Mark the file for untracking; this means it will not be
	 * included in the upcoming commit, even if it was tracked by that commit's
	 * parent. If the file had been staged, instead just unstage it, and don't
	 * also mark it for untracking.
	 * 
	 * @param f
	 *            file name
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 * @return if deleted return true else return false
	 */
	private void rm(String f) throws IOException, ClassNotFoundException {
		if (Stage.getStaged() != null && Stage.getStaged().contains(f))
			Stage.rmStaged(f);
		else if (Stage.getUntracked() == null
				|| !Stage.getUntracked().contains(f))
			Stage.setUntracked(f);
		else
			System.out.println("No reason to remove the file.");
	}

	/**
	 * Log. Starting at the current head commit, display information about each
	 * commit backwards along the commit tree until the initial commit. This set
	 * of commit nodes is called the commit’s history. For every node in this
	 * history, the information it should display is the commit id, the time the
	 * commit was made, and the commit message.
	 * 
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 */
	private void log() throws IOException, ClassNotFoundException {
		while (getNow() != null) {
			System.out.println("===\nCommit " + getNow().getId());
			System.out.println(getDateById(getNow().getId()));
			System.out.println(getNow().getMsg());
			if (getNow().getId() != 0)
				System.out.println();
			setNow(getNow().getPrev());
		}
	}

	/**
	 * Global-log. Like log, except displays information about all commits ever
	 * made.
	 * 
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 */
	private void globalLog() throws IOException, ClassNotFoundException {
		for (int i = 0; i <= nowCmtNum; i++) {
			System.out.println("===\nCommit " + i);
			System.out.println(getDateById(i));
			System.out.println(Node.getCommitById(i).getMsg());
			if (i != nowCmtNum)
				System.out.println();
		}
	}

	/**
	 * Find. Prints out the id of the commit that has the given commit message.
	 * If there are multiple such commits, it prints the ids out on separate
	 * lines.
	 * 
	 * @param msg
	 *            the commit message
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 */
	private void find(String msg) throws IOException, ClassNotFoundException {
		if (msg.isEmpty()) {
			System.out.println("Found no commit with that message.");
		} else {
			for (int i = 0; i <= nowCmtNum; i++)
				if (Node.getCommitById(i).getMsg().equals(msg))
					System.out.println(i);
		}
	}

	/**
	 * Displays what branches currently exist, and marks the current branch with
	 * a *. Also displays what files have been staged or marked for untracking.
	 * 
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 */
	private void status() throws IOException, ClassNotFoundException {
		// if (Node.getBranchMap().size()==1)
		// System.out.println("=== Branch ==="); else
		System.out.println("=== Branches ===");
		for (String s : Node.getBranchesName()) {
			if (s.equals(Node.getNowBranch()))
				System.out.print("*");
			System.out.println(s);
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		if (Stage.getAllStaged() != null)
			for (String name : Stage.getAllStaged())
				System.out.println(name);
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		if (Stage.getAllUntrack() != null)
			for (String name : Stage.getAllUntrack())
				System.out.println(name);
	}

	/**
	 * Checkout files.
	 * 
	 * @param ids
	 *            commits id in String type
	 * @param s
	 *            maybe branch name or file name
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 */
	private void checkout(String ids, String s) throws IOException,
			ClassNotFoundException {
		if (ids == null) {
			if (isBranch(s)) {
				if (Stage.getText(IS_CONFLICTED).equals("true"))
					System.out
							.println("Cannot do this command until the merge conflict has been resolved.");
				else {
					if (s.equals(getNowBranch()))
						System.out
								.println("No need to checkout the current branch.");
					else {
						Node.setNowBranch(s);
						copyDirectory(INDEX + "."
								+ Node.getBranchNode(s).getId(), getPathStr());
					}
				}
			} else if (Files.exists(Paths.get(INDEX + "." + getNow().getId()
					+ "/" + s))
					&& getNow() != null) {
				Files.copy(Paths.get(INDEX + "." + getNow().getId() + "/" + s),
						getPath().resolve(s),
						StandardCopyOption.REPLACE_EXISTING);
			} else
				System.out
						.println("File does not exist in the most recent commit, or no such branch exists.");
		} else {
			int id = Integer.valueOf(ids);
			if (Node.getCommitById(id) == null)
				System.out.println("No commit with that id exists.");
			else {
				Path filePath = Paths.get(INDEX + "." + id + "/" + s);
				if (Files.exists(filePath))
					Files.copy(filePath, getPath().resolve(s),
							StandardCopyOption.REPLACE_EXISTING);
				else
					System.out.println("File does not exist in that commit.");
			}
		}
	}

	/**
	 * To judge if the given name is a branch name
	 * 
	 * @param name
	 *            given name parameter
	 * @return true if it's a branch name else false
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 */
	private boolean isBranch(String name) throws ClassNotFoundException,
			IOException {
		if (Node.getBranchMap().containsKey(name))
			return true;
		return false;
	}

	/**
	 * Branch. Creates a new branch with the given name, and points it at the
	 * current head node. A branch is nothing more than a name for a pointer to
	 * a commit node into the commit tree. Before you ever call branch, your
	 * code should be running with a default branch called "master".
	 * 
	 * @param branch
	 *            the name of given branch
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 */
	private void branch(String branch) throws IOException,
			ClassNotFoundException {
		if (Node.getBranchMap().containsKey(branch))
			System.out.println("A branch with that name already exists.");
		else
			Node.getNow().addBranch(branch);
	}

	/**
	 * Deletes the branch with the given name. This only means to delete the
	 * pointer associated with the branch; it does not mean to delete all
	 * commits that were created under the branch, or anything like that.
	 * 
	 * @param branch
	 *            the name of given branch
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 */
	private void rmBranch(String branch) throws IOException,
			ClassNotFoundException {
		HashMap<String, Node> branchMap = Node.getBranchMap();
		if (!branchMap.containsKey(branch))
			System.out.println("A branch with that name does not exist.");
		else if (getNowBranch().equals(branch))
			System.out.println("Cannot remove the current branch.");
		else {
			branchMap.remove(branch);
			Node.setBranch(branchMap);
		}
	}

	/**
	 * Reset. Checks out all the files tracked by the given commit. Also moves
	 * the current branch's head to that commit node.
	 * 
	 * @param ids
	 *            commit id
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 */
	private void reset(String ids) throws IOException, ClassNotFoundException {
		int id = Integer.valueOf(ids);
		if (Node.getCommitById(id) == null)
			System.out.println("No commit with that id exists.");
		else {
			copyDirectory(INDEX + "." + id, getPathStr());
			Node.setBranch(Node.getNowBranch(), Node.getCommitById(id));
			Node.setNow(Node.getCommitById(id));
		}
	}

	/**
	 * Merges files from the given branch into the current branch. if there are
	 * files deleting it will ask you to input your password and then choose
	 * which file you want to keep and which you want to delete. your default
	 * password is "ucb", if you are asked to delete any files you will also be
	 * asked to enter your password, you can also change pwd
	 *
	 * <p>
	 * * * * * * * * * * * * * * * * * * * * * * * *
	 * </p>
	 * <p>
	 * from given branch into current branch
	 * </p>
	 * <p>
	 * 0.both are modified, the file from the given branch with a
	 * "[fileName].conflicted" name then Encountered a merge conflict. put it
	 * into a conflicted state
	 * </p>
	 * <p>
	 * 1.modified in the given branch but not in the current branch should be
	 * changed to files in the given branch, then STAGED these files modified
	 * means have different content, use getText()
	 * </p>
	 * <p>
	 * 2.modified in the current branch but not in the given branch just stay
	 * then commit message is going to be
	 * "Merged [current branch name] with [given branch name]."
	 * </p>
	 * <p>
	 * * * * * * * * * * * * * * * * * * * * * * * * *
	 * </p>
	 * 
	 * @param givenBranch
	 *            a given branch name
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 */
	private void merge(String givenBranch) throws IOException,
			ClassNotFoundException {
		HashMap<String, Node> branchMap = Node.getBranchMap();
		if (!branchMap.containsKey(givenBranch))
			System.out.println("A branch with that name does not exist.");
		else if (getNowBranch().equals(givenBranch))
			System.out.println("Cannot merge a branch with itself.");
		else {
			Node given = Node.getBranchNode(givenBranch);
			Node split = getSplit(given);
			if (split == null)
				System.exit(0);
			int givenId = given.getId();
			int currentId = Node.getBranchNode(getNowBranch()).getId();
			int splitId = split.getId();
			if (inSameLine(Node.getBranchNode(getNowBranch()), given)
					&& currentId > givenId)
				System.exit(0);
			int c = conflicted(givenId, currentId, splitId);
			if (c != 1 && c != 0) { /*----current was modified or nothing----*/
				copyDirectory(INDEX + "." + givenId, INDEX + "."
						+ (nowCmtNum + 1));
				addCommit("Merged " + getNowBranch() + " with " + givenBranch
						+ ".");
			} else if (c == 1) { /*----given was modified----done----*/
				Iterator<String> iter = Node.getGivenSet().iterator();
				while (iter.hasNext()) {
					String givenFile = iter.next();
					Path p = INDEX_PATH
							.resolve("." + givenId + "/" + givenFile);
					Files.copy(p, getPath().resolve(givenFile),
							StandardCopyOption.REPLACE_EXISTING);
					addToStage(givenFile, INDEX + "." + givenId, STAGE);
				}
				addCommit("Merged " + getNowBranch() + " with " + givenBranch
						+ ".");
			} else if (c == 0) { /*----current and given both modified----*/
				Stage.writeFile(IS_CONFLICTED, "true");
				System.out.println("Encountered a merge conflict.");
				Iterator<String> iter = Node.getGivenSet().iterator();
				while (iter.hasNext()) {
					String fileName = iter.next();
					Files.copy(
							Paths.get(INDEX + "." + givenId + "/" + fileName),
							getPath().resolve(fileName + ".conflicted"),
							StandardCopyOption.REPLACE_EXISTING);
				}
			}
		}
	}

	/**
	 * test if two branches are in the same line
	 * 
	 * @param current
	 *            current branch name
	 * @param given
	 *            given branch name
	 * @return true if the head commit of current branch and the head commit of
	 *         given branch are in the same line else false
	 */
	private static boolean inSameLine(Node current, Node given) {
		if (current.getId() >= given.getId())
			while (current.getPrev() != null) {
				if (current.equals(given))
					return true;
				current = current.getPrev();
			}
		else
			while (given.getPrev() != null) {
				if (given.equals(current))
					return true;
				given = given.getPrev();
			}
		return false;
	}

	/**
	 * If two branches are conflicted
	 * 
	 * @param givenId
	 *            given branch's commit id
	 * @param currentId
	 *            current branch's commit id
	 * @param splitId
	 *            split commit id
	 * @return some integers as you can see,
	 *         <p>
	 *         &nbsp;2<-current was modified
	 *         </p>
	 *         <p>
	 *         &nbsp;1<-given was modified
	 *         </p>
	 *         <p>
	 *         &nbsp;0<-both were modified
	 *         </p>
	 *         <p>
	 *         -1<-nothing was modified
	 *         </p>
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 */
	private static int conflicted(int givenId, int currentId, int splitId)
			throws IOException, ClassNotFoundException {
		HashSet<String> givenSet = Node.getFilesNameById(givenId);
		HashSet<String> currentSet = Node.getFilesNameById(currentId);
		HashSet<String> splitSet = Node.getFilesNameById(splitId);
		HashSet<String> currentFilesSet = new HashSet<String>();
		HashSet<String> givenFilesSet = new HashSet<String>();
		HashSet<String> conflictedFilesSet = new HashSet<String>();
		Iterator<String> iSplitFiles = splitSet.iterator();
		int j = 0, i = 0;
		while (iSplitFiles.hasNext()) {
			String fileName = iSplitFiles.next();
			if (currentSet.contains(fileName)) {
				if (!Stage.getText(INDEX + "." + splitId + "/" + fileName)
						.equals(Stage.getText(INDEX + "." + currentId + "/"
								+ fileName))) {
					currentFilesSet.add(fileName);
					j = 1;
				}
			}
			if (givenSet.contains(fileName)) {
				if (!Stage.getText(INDEX + "." + splitId + "/" + fileName)
						.equals(Stage.getText(INDEX + "." + givenId + "/"
								+ fileName))) {
					if (currentFilesSet.contains(fileName))
						conflictedFilesSet.add(fileName);
					givenFilesSet.add(fileName);
					i = 1;
				}
			}
		}
		Node.setFilesSet(currentFilesSet, givenFilesSet, conflictedFilesSet);
		if (j == 1 && i != 1)
			return 2; /*----current was modified----*/
		else if (i == 1 && j != 1)
			return 1; /*----given was modified------*/
		else if (i + j == 2)
			return 0; /*----both were modified------*/
		else
			return -1; /*---nothing was modified----*/
	}

	/**
	 * Get the split point node
	 * 
	 * @param given
	 *            the head node in the given branch
	 * @return split node
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 */
	private Node getSplit(Node given) throws IOException,
			ClassNotFoundException {
		Node current = Node.getBranchNode(getNowBranch());
		while (given.getPrev() != null) {
			while (current.getPrev() != null) {
				if (current.equals(given.getPrev()))
					return current;
				current = current.getPrev();
			}
			given = given.getPrev();
			current = Node.getBranchNode(getNowBranch());
		}
		return null;
	}

	/**
	 * Conceptually, what rebase does is find the split point of the current
	 * branch and the given branch, then snaps off the current branch at this
	 * point, then re-attaches the current branch to the head of the given
	 * branch.
	 * 
	 * @param givenBranch
	 *            a given branch name
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 */
	private void rebase(String givenBranch) throws ClassNotFoundException,
			IOException {
		HashMap<String, Node> branchMap = Node.getBranchMap();
		if (!branchMap.containsKey(givenBranch))
			System.out.println("A branch with that name does not exist.");
		else if (getNowBranch().equals(givenBranch))
			System.out.println("Cannot rebase a branch onto itself.");
		else {
			Node given = Node.getBranchNode(givenBranch);
			Node current = Node.getBranchNode(getNowBranch());
			Node split = getSplit(given);
			if (split == null) {
				System.out.println("Already up-to-date.");
			} else {
				int givenId = given.getId();
				int currentId = current.getId();
				int splitId = split.getId();
				if (inSameLine(current, given)) {
					if (currentId > givenId)
						System.out.println("Already up-to-date.");
					else
						Node.setBranch(getNowBranch(), given);
				} else {
					String givenPath = INDEX + "." + givenId + "/";
					String currentPath = INDEX + "." + currentId + "/";
					String splitPath = INDEX + "." + splitId + "/";
					int n = getNum(current, split);
					int i = conflicted(givenId, currentId, splitId);
					if (i != 1 && i != 0) /*----current was modified or nothing----*/
						twoOrNothing(n, given, currentId);
					else if (i == 1) /*----given was modified----*/
						one(n, given, currentId, givenPath);
					else if (i == 0) /*----both were modified----*/
						zero(n, given, currentId, givenPath, splitPath,
								currentPath);
					Stage.setCmtChange("false");
					copyDirectory(INDEX + "." + Node.getNow().getId(),
							getPathStr());
				}
			}
		}
	}

	/**
	 * the first situation, current was modified or nothing
	 * 
	 * @param n
	 *            the number of nodes from current commit to the split commit
	 * @param given
	 *            the head node in the given branch
	 * @param currentId
	 *            the id of the head node in the current branch
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 */
	private void twoOrNothing(int n, Node given, int currentId)
			throws ClassNotFoundException, IOException {
		for (int j = n; j > 0; j--) {
			if (j == n) {
				Node.setNow(given);
				copyCommit(Node.getCommitById(currentId - j + 1).getMsg(), j, n);
			} else {
				copyCommit(Node.getCommitById(currentId - j + 1).getMsg(), j, n);
			}
		}
	}

	/**
	 * the second situation, given was modified
	 * 
	 * @param n
	 *            the number of nodes from current commit to the split commit
	 * @param given
	 *            the head node in the given branch
	 * @param currentId
	 *            the id of the head node in the current branch
	 * @param givenPath
	 *            the head node file folder path
	 * @param path
	 *            the path copying to
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 */
	private void one(int n, Node given, int currentId, String givenPath)
			throws ClassNotFoundException, IOException {
		String path = INDEX + "." + (nowCmtNum + 1 + "/");
		for (int j = n; j > 0; j--) {
			if (j == n) {
				Node.setNow(given);
				copyCommit(Node.getCommitById(currentId - j + 1).getMsg(), j, n);
			} else {
				Iterator<String> iter = Node.getGivenSet().iterator();
				while (iter.hasNext())
					add(iter.next(), givenPath, STAGE);
				copyCommit(Node.getCommitById(currentId - j + 1).getMsg(), j, n);
				Iterator<String> iter2 = Node.getGivenSet().iterator();
				while (iter2.hasNext()) {
					String s = iter2.next();
					Files.copy(Paths.get(givenPath + s), Paths.get(path + s),
							StandardCopyOption.REPLACE_EXISTING);
				}
			}
		}
	}

	/**
	 * the three situation, both were modified
	 * 
	 * @param n
	 *            the number of nodes from current commit to the split commit
	 * @param given
	 *            the head node in the given branch
	 * @param currentId
	 *            the id of the head node in the current branch
	 * @param givenPath
	 *            the head node file folder path of the given branch
	 * @param path
	 *            the path copying to
	 * @param splitPath
	 *            the head node file folder path of the split point node
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 */
	private void zero(int n, Node given, int currentId, String givenPath,
			String splitPath, String currentPath)
			throws ClassNotFoundException, IOException {
		for (int j = n; j > 0; j--) {
			String path = INDEX + "." + (nowCmtNum + 1 + n - j + "/");
			if (j == n) {
				Node.setNow(given);
				copyCommit(Node.getCommitById(currentId - j + 1).getMsg(), j, n);
			} else {
				Iterator<String> iter = Node.getGivenSet().iterator();
				String fileName = iter.next();
				String splitText = Stage.getText(splitPath + fileName);
				String currentText = Stage.getText(INDEX + "."
						+ (currentId - j + 1) + "/" + fileName);
				while (iter.hasNext()) {
					if (splitText.equals(currentText))
						add(fileName, givenPath, STAGE);
				}
				copyCommit(Node.getCommitById(currentId - j + 1).getMsg(), j, n);
				Iterator<String> iter2 = Node.getGivenSet().iterator();
				while (iter2.hasNext()) {
					String s = iter2.next();
					if (splitText.equals(currentText)) {
						Files.copy(Paths.get(givenPath + s),
								Paths.get(path + s),
								StandardCopyOption.REPLACE_EXISTING);
					}
				}
				Iterator<String> iter3 = Node.getCurrentSet().iterator();
				while (iter3.hasNext()) {
					String s = iter3.next();
					if (!splitText.equals(currentText)) {
						Files.copy(Paths.get(currentPath + s),
								Paths.get(path + s),
								StandardCopyOption.REPLACE_EXISTING);
					}
				}
			}
		}
	}

	/**
	 * To get the number of commits from split node to current node
	 * 
	 * @param c
	 *            current node
	 * @param split
	 *            split node
	 * @return the number of nodes from current commit to the split commit
	 */
	private int getNum(Node c, Node split) {
		int n = 0;
		while (c.getPrev() != null) {
			if (c.equals(split))
				break;
			c = c.getPrev();
			n++;
		}
		return n;
	}

	/**
	 * to copy a commit in rebase method
	 * 
	 * @param msg
	 *            commit message
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 */
	private void copyCommit(String msg, int j, int n) throws IOException,
			ClassNotFoundException {
		String addString = INDEX + "." + (nowCmtNum + 1 + n - j);
		Path addPath = Paths.get(addString);
		if (Files.notExists(addPath)) {
			String[] fileList = Stage.getAllStaged();
			String[] fileListUt = Stage.getAllUntrack();
			Node.setNow(Node.getNow().addCmt(msg, fileList, fileListUt, j, n));
			Node.setBranch(getNowBranch(), Node.getNow());
			Files.createDirectories(addPath);
			copyCommitFiles(fileList, fileListUt, addString);
			Stage.clear(STAGE);
		}
	}

	/*****************************
	 * nested class, Commit node
	 * 
	 * @author Zihao Jing
	 * @author Pingao Liu
	 * @author Yuchao Gao
	 *****************************/
	public static class Node implements Serializable {

		/**
		 * UID = 2L serialVersionUID definition, for Node
		 */
		private static final long serialVersionUID = 2L;
		private int id;
		private String msg;
		private Date date;
		private Node prev;
		private ArrayList<Node> next;

		/**
		 * Commit node constructor
		 * 
		 * @param id
		 *            commit id
		 * @param msg
		 *            commit message
		 * @param prev
		 *            the previous node
		 */
		Node(int id, String msg, Node prev) {
			this.id = id;
			this.msg = msg;
			this.prev = prev;
			this.date = new Date();
			this.next = new ArrayList<Gitlet.Node>();
		}

		int getId() {
			return id;
		}

		String getMsg() {
			return msg;
		}

		Date getDate() {
			return date;
		}

		Node getPrev() {
			return prev;
		}

		boolean hasPrev() {
			return prev != null;
		}

		ArrayList<Node> getNext() {
			return next;
		}

		void setNext(ArrayList<Node> next) {
			this.next = next;
		}

		boolean hasNext() {
			return !next.isEmpty();
		}

		/**
		 * initialize a commit, with a "master" branch
		 * 
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		private void initCmtBrch() throws IOException, ClassNotFoundException {
			Node init = new Node(0, INIT_COMMIT, null);
			setNowCommit(nowCmtNum, MST, init);
			setNowBranch(MST);
			HashMap<String, Node> branchMap = new HashMap<String, Node>();
			branchMap.put(MST, init);
			setBranch(branchMap);
			writeCommitToSer(0, INIT_COMMIT, init);
		}

		/**
		 * make files name Serializable
		 * 
		 * @param id
		 *            commit id
		 * @param fileList
		 *            the list of files in stage area
		 * @param fileListUt
		 *            the list of files marked as untracking
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		private static void writeFilesNameById(int id, String[] fileList,
				String[] fileListUt) throws IOException, ClassNotFoundException {
			HashSet<String> fileSet = new HashSet<String>();
			FileOutputStream filesOutId;
			if (id == 0) {
				filesOutId = new FileOutputStream(INDEX_SER + ".1.ser");
				for (String s : fileList)
					fileSet.add(s);
			} else {
				filesOutId = new FileOutputStream(INDEX_SER + "."
						+ (nowCmtNum + 1) + ".ser");
				fileSet = getFilesNameById(id);
				if (fileList != null)
					for (String s : fileList)
						fileSet.add(s);
				if (fileListUt != null)
					for (String s : fileListUt)
						fileSet.remove(s);
			}
			ObjectOutputStream outsStream = new ObjectOutputStream(filesOutId);
			outsStream.writeObject(fileSet);
			outsStream.close();
			filesOutId.close();
		}

		/**
		 * only used in rebase
		 * 
		 * @param id
		 *            commit id
		 * @param fileList
		 *            the list of files in stage area
		 * @param fileListUt
		 *            the list of files marked as untracking
		 * @param j
		 *            (n-j) is the new commit id number to be adding
		 * @param n
		 *            (n-j) is the new commit id number to be adding
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		private static void writeFilesNameById(int id, String[] fileList,
				String[] fileListUt, int j, int n) throws IOException,
				ClassNotFoundException {
			HashSet<String> fileSet = new HashSet<String>();
			FileOutputStream filesOutId;
			if (id == 0) {
				filesOutId = new FileOutputStream(INDEX_SER + ".1.ser");
				for (String s : fileList)
					fileSet.add(s);
			} else {
				filesOutId = new FileOutputStream(INDEX_SER + "."
						+ (nowCmtNum + 1 + n - j) + ".ser");
				fileSet = getFilesNameById(id);
				if (fileList != null)
					for (String s : fileList)
						fileSet.add(s);
				if (fileListUt != null)
					for (String s : fileListUt)
						fileSet.remove(s);
			}
			ObjectOutputStream outsStream = new ObjectOutputStream(filesOutId);
			outsStream.writeObject(fileSet);
			outsStream.close();
			filesOutId.close();
		}

		/**
		 * get the name of files in the id-th commit
		 * 
		 * @param id
		 *            commit id
		 * @return a HashSet stores file name
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		private static HashSet<String> getFilesNameById(int id)
				throws IOException, ClassNotFoundException {
			FileInputStream fileIn = new FileInputStream(INDEX_SER + "." + id
					+ ".ser");
			ObjectInputStream inStream = new ObjectInputStream(fileIn);
			@SuppressWarnings("unchecked")
			HashSet<String> fileSet = (HashSet<String>) inStream.readObject();
			fileIn.close();
			inStream.close();
			return fileSet;
		}

		/**
		 * add a commit
		 * 
		 * @param msg
		 *            commit message
		 * @param fileList
		 *            the list of files in stage area
		 * @param fileListUt
		 *            the list of files marked as untracking
		 * @return added commit node
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		private Node addCmt(String msg, String[] fileList, String[] fileListUt)
				throws IOException, ClassNotFoundException {
			Stage.writeFile(IS_CONFLICTED, "false");
			writeFilesNameById(getId(), fileList, fileListUt);
			setNowCommit(nowCmtNum + 1, msg, this);
			return addCommitToSer(msg, this);
		}

		/**
		 * only used in rebase
		 * 
		 * @param msg
		 *            commit message
		 * @param fileList
		 *            the list of files in stage area
		 * @param fileListUt
		 *            the list of files marked as untracking
		 * @param j
		 *            (n-j) is the new commit id number to be adding
		 * @param n
		 *            (n-j) is the new commit id number to be adding
		 * @return added commit node
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		private Node addCmt(String msg, String[] fileList, String[] fileListUt,
				int j, int n) throws IOException, ClassNotFoundException {
			Stage.writeFile(IS_CONFLICTED, "false");
			writeFilesNameById(getId(), fileList, fileListUt, j, n);
			setNowCommit(nowCmtNum + 1 + n - j, msg, this);
			return addCommitToSer(msg, this, j, n);
		}

		/**
		 * make commit node Serializable
		 * 
		 * @param msg
		 *            commit message
		 * @param now
		 *            which node we are on now
		 * @return added commit node
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		private Node addCommitToSer(String msg, Node now) throws IOException,
				ClassNotFoundException {
			Node newCommit = new Node(nowCmtNum + 1, msg, now);
			now.getNext().add(newCommit);
			writeCommitToSer(now.getId(), now.getMsg(), now);
			writeCommitToSer(nowCmtNum + 1, msg, newCommit);
			return newCommit;
		}

		/**
		 * only used in rebase
		 * 
		 * @param msg
		 *            commit message
		 * @param now
		 *            which node we are on now
		 * @param j
		 *            (n-j) is the new commit id number to be adding
		 * @param n
		 *            (n-j) is the new commit id number to be adding
		 * @return
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		private Node addCommitToSer(String msg, Node now, int j, int n)
				throws IOException, ClassNotFoundException {
			Node newCommit = new Node(nowCmtNum + 1 + n - j, msg, now);
			now.getNext().add(newCommit);
			writeCommitToSer(now.getId(), now.getMsg(), now);
			writeCommitToSer(nowCmtNum + 1 + n - j, msg, newCommit);
			return newCommit;
		}

		/**
		 * make commit node Serializable
		 * 
		 * @param nowCommitNum
		 *            the number of now commit
		 * @param msg
		 *            commit message
		 * @param newCommit
		 *            commit to be added
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		private static void writeCommitToSer(int nowCommitNum, String msg,
				Node newCommit) throws IOException, ClassNotFoundException {
			HashMap<Integer, Node> commitsListId = new HashMap<Integer, Gitlet.Node>();
			if (nowCommitNum != 0)
				commitsListId = getCommitById();
			commitsListId.put(nowCommitNum, newCommit);
			FileOutputStream filesOutId = new FileOutputStream(COMMIT_ID_SER);
			ObjectOutputStream outsStream = new ObjectOutputStream(filesOutId);
			outsStream.writeObject(commitsListId);
			outsStream.close();
			filesOutId.close();
			HashMap<String, Node> commitsListMsg = new HashMap<String, Gitlet.Node>();
			if (!msg.equals(INIT_COMMIT))
				commitsListMsg = getNonRepeatCommitsByMsg();
			commitsListMsg.put(msg, newCommit);
			FileOutputStream filesOutMsg = new FileOutputStream(COMMIT_MSG_SER);
			ObjectOutputStream outsStreamMsg = new ObjectOutputStream(
					filesOutMsg);
			outsStreamMsg.writeObject(commitsListMsg);
			outsStreamMsg.close();
			filesOutMsg.close();
		}

		/**
		 * make current commit Serializable
		 * 
		 * @param nowCommitNum
		 *            the number of now commit
		 * @param msg
		 *            commit message
		 * @throws IOException
		 */
		private static void setNowCommit(int nowCommitNum, String msg, Node now)
				throws IOException {
			Node newCommit = new Node(nowCommitNum, msg, now);
			FileOutputStream fileOut = new FileOutputStream(NOW_SER);
			ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
			outStream.writeObject(newCommit);
			outStream.close();
			fileOut.close();
		}

		/**
		 * get commit from Serializable file
		 * 
		 * @return a HashMap stores commit whose key is commit id
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		private static HashMap<Integer, Node> getCommitById()
				throws IOException, ClassNotFoundException {
			if (Files.exists(Paths.get(COMMIT_ID_SER))) {
				FileInputStream fileIn = new FileInputStream(COMMIT_ID_SER);
				ObjectInputStream inStream = new ObjectInputStream(fileIn);
				@SuppressWarnings("unchecked")
				HashMap<Integer, Node> commitsList = (HashMap<Integer, Node>) inStream
						.readObject();
				fileIn.close();
				inStream.close();
				return commitsList;
			} else
				return null;
		}

		/**
		 * get one commit from Serializable file by commit id
		 * 
		 * @param id
		 *            commit id
		 * @return the commit node
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		private static Node getCommitById(int id) throws IOException,
				ClassNotFoundException {
			if (Files.exists(Paths.get(COMMIT_ID_SER))) {
				FileInputStream fileIn = new FileInputStream(COMMIT_ID_SER);
				ObjectInputStream inStream = new ObjectInputStream(fileIn);
				@SuppressWarnings("unchecked")
				HashMap<Integer, Node> commitsList = (HashMap<Integer, Node>) inStream
						.readObject();
				fileIn.close();
				inStream.close();
				return commitsList.get(id);
			} else
				return null;
		}

		/**
		 * get commit from Serializable file, with no repeated commit message
		 * 
		 * @return a HashMap stores commit whose key is commit message
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		private static HashMap<String, Node> getNonRepeatCommitsByMsg()
				throws IOException, ClassNotFoundException {
			if (Files.exists(Paths.get(COMMIT_MSG_SER))) {
				FileInputStream fileIn = new FileInputStream(COMMIT_MSG_SER);
				ObjectInputStream inStream = new ObjectInputStream(fileIn);
				@SuppressWarnings("unchecked")
				HashMap<String, Node> commitsList = (HashMap<String, Node>) inStream
						.readObject();
				fileIn.close();
				inStream.close();
				return commitsList;
			} else
				return null;
		}

		/**
		 * get current commit from Serializable file
		 * 
		 * @return current node
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		private static Node getNow() throws IOException, ClassNotFoundException {
			if (Files.exists(Paths.get(NOW_SER))) {
				FileInputStream fileIn = new FileInputStream(NOW_SER);
				ObjectInputStream inStream = new ObjectInputStream(fileIn);
				Node now = (Node) inStream.readObject();
				inStream.close();
				fileIn.close();
				return now;
			} else
				return null;
		}

		/**
		 * make current node Serializable
		 * 
		 * @param now
		 * @throws IOException
		 */
		private static void setNow(Node now) throws IOException {
			FileOutputStream fileOut = new FileOutputStream(NOW_SER);
			ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
			outStream.writeObject(now);
			outStream.close();
			fileOut.close();
		}

		/**
		 * save all branch names and node in a HashMap and store in a ser file
		 * adding a branch is to add a copy of now to the prev's next ArrayList
		 * checkingout a branch is to set now to that node save now branch name
		 * to a ser file and in Gitlet constructor setNow with a Node gotten by
		 * now branch name from that HashMap
		 * 
		 * @param branch
		 *            branch name to be added
		 * @throws ClassNotFoundException
		 *             we can not find this class
		 * @throws IOException
		 *             if file doesn't exist or file already existed there
		 */
		private void addBranch(String branch) throws ClassNotFoundException,
				IOException {
			setBranch(branch, this);
		}

		/**
		 * make all branches Serializable
		 * 
		 * @param branch
		 *            branch name
		 * @param now
		 *            current node
		 * @throws ClassNotFoundException
		 * @throws IOException
		 */
		private static void setBranch(String branch, Node now)
				throws ClassNotFoundException, IOException {
			HashMap<String, Node> branchMap = getBranchMap();
			branchMap.put(branch, now);
			setBranch(branchMap);
		}

		/**
		 * get all branches from Serializable file
		 * 
		 * @return a HashMap stores all branches
		 * @throws ClassNotFoundException
		 * @throws IOException
		 */
		private static HashMap<String, Node> getBranchMap()
				throws ClassNotFoundException, IOException {
			FileInputStream brchesIn = new FileInputStream(BRANCH);
			ObjectInputStream brchesInStream = new ObjectInputStream(brchesIn);
			@SuppressWarnings("unchecked")
			HashMap<String, Node> branchMap = (HashMap<String, Node>) brchesInStream
					.readObject();
			brchesInStream.close();
			brchesIn.close();
			return branchMap;
		}

		/**
		 * make the branch HashMap Serializable
		 * 
		 * @param branchMap
		 *            a HashMap is going to be Serializable
		 * @throws IOException
		 */
		private static void setBranch(HashMap<String, Node> branchMap)
				throws IOException {
			FileOutputStream brchesOut = new FileOutputStream(BRANCH);
			ObjectOutputStream brchesOutStream = new ObjectOutputStream(
					brchesOut);
			brchesOutStream.writeObject(branchMap);
			brchesOutStream.close();
			brchesOut.close();
		}

		/**
		 * make current branch node Serializable
		 * 
		 * @param branch
		 *            current branch name
		 * @throws IOException
		 */
		private static void setNowBranch(String branch) throws IOException {
			FileOutputStream brchOut = new FileOutputStream(NOW_BRANCH_SER);
			ObjectOutputStream brchStream = new ObjectOutputStream(brchOut);
			brchStream.writeObject(branch);
			brchStream.close();
			brchOut.close();
		}

		/**
		 * get current branch node from Serializable file
		 * 
		 * @return current commit node
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		private static String getNowBranch() throws IOException,
				ClassNotFoundException {
			if (Files.exists(Paths.get(NOW_BRANCH_SER))) {
				FileInputStream brchesIn = new FileInputStream(NOW_BRANCH_SER);
				ObjectInputStream brchesInStream = new ObjectInputStream(
						brchesIn);
				String branch = (String) brchesInStream.readObject();
				brchesInStream.close();
				brchesIn.close();
				return branch;
			} else
				return null;
		}

		/**
		 * get branch node by branch name from Serializable file
		 * 
		 * @param branch
		 *            a given branch name
		 * @return a commit node
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		private static Node getBranchNode(String branch) throws IOException,
				ClassNotFoundException {
			if (Files.exists(Paths.get(BRANCH))) {
				HashMap<String, Node> branchMap = getBranchMap();
				return branchMap.get(branch);
			} else
				return getNow();
		}

		/**
		 * get the name of all branches from Serializable file
		 * 
		 * @return branches name in string array
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		private static String[] getBranchesName() throws IOException,
				ClassNotFoundException {
			HashMap<String, Node> branches = getBranchMap();
			String[] names = new String[branches.size()];
			int i = 0;
			for (String s : branches.keySet()) {
				names[i] = s;
				i++;
			}
			return names;
		}

		/**
		 * overwrite hashCode method
		 */
		@Override
		public int hashCode() {
			return this.id;
		}

		/**
		 * overwrite equals method
		 */
		@Override
		public boolean equals(Object o) {
			if (this.hashCode() == o.hashCode())
				return true;
			else
				return false;
		}

		/**
		 * make conflicted files Serializable
		 * 
		 * @param currentFilesSet
		 *            in the head node of current branch, the set of files were
		 *            modified from split point node
		 * @param givenFilesSet
		 *            in the head node of given branch, the set of files were
		 *            modified from split point node
		 * @param conflictedFilesSet
		 *            in the head node of both given and current branch, the set
		 *            of files were modified from split point node
		 * @throws IOException
		 */
		private static void setFilesSet(HashSet<String> currentFilesSet,
				HashSet<String> givenFilesSet,
				HashSet<String> conflictedFilesSet) throws IOException {
			FileOutputStream currentOut = new FileOutputStream(
					CURRENT_MODIFIED_FILES_SER);
			ObjectOutputStream currentStream = new ObjectOutputStream(
					currentOut);
			currentStream.writeObject(currentFilesSet);
			currentStream.close();
			currentOut.close();
			FileOutputStream givenOut = new FileOutputStream(
					GIVEN_MODIFIED_FILES_SER);
			ObjectOutputStream giventStream = new ObjectOutputStream(givenOut);
			giventStream.writeObject(givenFilesSet);
			giventStream.close();
			givenOut.close();
			FileOutputStream conOut = new FileOutputStream(CFD_FILES_SER);
			ObjectOutputStream conStream = new ObjectOutputStream(conOut);
			conStream.writeObject(conflictedFilesSet);
			conStream.close();
			conOut.close();
		}

		/**
		 * get a HashSet stores conflicted files
		 * 
		 * @return the set of files were modified in current branch head node
		 *         from split point node
		 * @throws ClassNotFoundException
		 * @throws IOException
		 */
		private static HashSet<String> getCurrentSet()
				throws ClassNotFoundException, IOException {
			if (Files.exists(Paths.get(CURRENT_MODIFIED_FILES_SER))) {
				FileInputStream in = new FileInputStream(
						CURRENT_MODIFIED_FILES_SER);
				ObjectInputStream s = new ObjectInputStream(in);
				@SuppressWarnings("unchecked")
				HashSet<String> hs = (HashSet<String>) s.readObject();
				s.close();
				in.close();
				return hs;
			} else
				return null;
		}

		/**
		 * get a HashSet stores conflicted files
		 * 
		 * @return the set of files were modified in given branch head node from
		 *         split point node
		 * @throws ClassNotFoundException
		 * @throws IOException
		 */
		private static HashSet<String> getGivenSet()
				throws ClassNotFoundException, IOException {
			if (Files.exists(Paths.get(GIVEN_MODIFIED_FILES_SER))) {
				FileInputStream in = new FileInputStream(
						GIVEN_MODIFIED_FILES_SER);
				ObjectInputStream s = new ObjectInputStream(in);
				@SuppressWarnings("unchecked")
				HashSet<String> hs = (HashSet<String>) s.readObject();
				s.close();
				in.close();
				return hs;
			} else
				return null;
		}

		/**
		 * get a HashSet stores conflicted files, because user will edit these
		 * files in a conflicted state so we don't directly need the name of
		 * these files
		 * 
		 * @return the set of files were modified both in given and current
		 *         branch head node from split point node
		 * @throws ClassNotFoundException
		 * @throws IOException
		 */
		@SuppressWarnings("unused")
		private static HashSet<String> getConflictedSet()
				throws ClassNotFoundException, IOException {
			if (Files.exists(Paths.get(CFD_FILES_SER))) {
				FileInputStream in = new FileInputStream(CFD_FILES_SER);
				ObjectInputStream s = new ObjectInputStream(in);
				@SuppressWarnings("unchecked")
				HashSet<String> hs = (HashSet<String>) s.readObject();
				s.close();
				in.close();
				return hs;
			} else
				return null;
		}

	} /* -------- nested node class end -------- */

	/**
	 * GO Gitlet!
	 * 
	 * @param args
	 *            the inputed commands
	 */
	public static void main(String[] args) {
		try {
			Gitlet g = new Gitlet();
			if (g.isConflicted()) {
				switch (args.length) {
				case 0:
					System.out.println("Please enter a command.");
					break;
				case 1:
					g.oneargsC(args[0]);
					break;
				case 2:
					g.twoargsC(args[0], args[1]);
					break;
				case 3:
					g.threeargs(args[0], args[1], args[2]);
					break;
				default:
					System.out.println("No command with that name exists.");
					break;
				}
			} else {
				switch (args.length) {
				case 0:
					System.out.println("Please enter a command.");
					break;
				case 1:
					g.oneargs(args[0]);
					break;
				case 2:
					g.twoargs(args[0], args[1]);
					break;
				case 3:
					g.threeargs(args[0], args[1], args[2]);
					break;
				default:
					System.out.println("No command with that name exists.");
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * one argument command situation
	 * 
	 * @param argsZero
	 *            args[0]
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 */
	private void oneargs(String argsZero) throws IOException,
			ClassNotFoundException {
		switch (argsZero) {
		case "init":
			init();
			break;
		case "log":
			log();
			break;
		case "global-log":
			globalLog();
			break;
		case "status":
			status();
			break;
		case "0":
			clear(GIT);
			break;
		default:
			System.out.println("No command with that name exists.");
			break;
		}
	}

	/**
	 * two arguments command situation
	 * 
	 * @param argsZero
	 *            args[0]
	 * @param argsOne
	 *            args[1]
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 */
	private void twoargs(String argsZero, String argsOne) throws IOException,
			ClassNotFoundException {
		switch (argsZero) {
		case "add":
			add(argsOne, getPathStr(), STAGE);
			break;
		case "commit":
			commit(argsOne);
			break;
		case "rm":
			rm(argsOne);
			break;
		case "find":
			find(argsOne);
			break;
		case "checkout":
			checkout(null, argsOne);
			break;
		case "branch":
			branch(argsOne);
			break;
		case "rm-branch":
			rmBranch(argsOne);
			break;
		case "reset":
			reset(argsOne);
			break;
		case "merge":
			merge(argsOne);
			break;
		case "rebase":
			rebase(argsOne);
			break;
		default:
			System.out.println("No command with that name exists.");
			break;
		}
	}

	/**
	 * three arguments command situation
	 * 
	 * @param argsZero
	 *            args[0]
	 * @param argsOne
	 *            args[1]
	 * @param argsTwo
	 *            args[2]
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 */
	private void threeargs(String argsZero, String argsOne, String argsTwo)
			throws IOException, ClassNotFoundException {
		if (argsZero.equals("checkout"))
			checkout(argsOne, argsTwo);
		else
			System.out.println("No command with that name exists.");
	}

	/**
	 * get default path in String type
	 * 
	 * @return the default path of our working directory
	 */
	public String getPathStr() {
		return getClass().getResource("").getPath();
	}

	/**
	 * get default path in Path type
	 * 
	 * @return the default path of our working directory in Path type
	 */
	public Path getPath() {
		return Paths.get(getClass().getResource("").getPath());
	}

	public Node getZero() {
		return zero;
	}

	public static Node getNow() {
		return now;
	}

	public static void setNow(Node now) {
		Gitlet.now = now;
	}

	public Date getDateById(int id) throws ClassNotFoundException, IOException {
		return Node.getCommitById(id).getDate();
	}

	public String getNowBranch() {
		return nowBrchName;
	}

	public void setNowBranch(String nowBranchName) {
		Gitlet.nowBrchName = nowBranchName;
	}

	public boolean isConflicted() {
		return isConflicted;
	}

	public void setConflicted(boolean isConflicted) {
		this.isConflicted = isConflicted;
	}

	/**
	 * one argument command situation for Conflicted state
	 * 
	 * @param argsZero
	 *            args[0]
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 */
	private void oneargsC(String argsZero) throws IOException,
			ClassNotFoundException {
		switch (argsZero) {
		case "init":
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			break;
		case "log":
			log();
			break;
		case "global-log":
			globalLog();
			break;
		case "status":
			status();
			break;
		case "0":
			clear(GIT);
			System.out.println("Done!");
			break;
		default:
			System.out.println("No command with that name exists.");
			break;
		}
	}

	/**
	 * two arguments command situation for Conflicted state
	 * 
	 * @param argsZero
	 *            args[0]
	 * @param argsOne
	 *            args[1]
	 * @throws IOException
	 *             if file doesn't exist or file already existed there
	 * @throws ClassNotFoundException
	 *             we can not find this class
	 */
	private void twoargsC(String argsZero, String argsOne) throws IOException,
			ClassNotFoundException {
		switch (argsZero) {
		case "add":
			add(argsOne, getPathStr(), STAGE);
			break;
		case "commit":
			commit(argsOne);
			break;
		case "rm":
			rm(argsOne);
			break;
		case "find":
			find(argsOne);
			break;
		case "checkout":
			checkout(null, argsOne);
			break;
		case "branch":
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			break;
		case "rm-branch":
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			break;
		case "reset":
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			break;
		case "merge":
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			break;
		case "rebase":
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
			break;
		default:
			System.out.println("No command with that name exists.");
			break;
		}
	}

}