/**
 * CS61BL - UC Berkeley Summer 2015
 * Project 2
 * Gitlet
 * 
 * Class 1/2
 * Gitlet.java <<<
 * Commit.java
 * 
 * Project description:
 * A simpler version of git.
 * Full project details at http://cs61bl.github.io
 * 
 * @authors Jessica Larson, Brian Sakhuja, Eifu Tomita
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Gitlet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<File> stagingAreaForFileList;
	private ArrayList<String> stagingAreaForFileNameList;
	private ArrayList<String> branchNameList;
	private HashMap<String, Commit> branchNameToHeadMap;
	private HashMap<Integer, Commit> idToCommitMap;
	private HashMap<String, List<Commit>> messageToCommitMap;
	private HashMap<Commit, HashSet<String>> commitToTrackingSetMap;
	private HashSet<String> untrackingSet;
	private Commit currentBranchHead;
	private String currentBranchName;
	private ArrayList<Commit> globalLogList = new ArrayList<Commit>();
	private int idSoFar;
	private boolean isConflictedState;

	/**
	 * Main takes in a varied number of args and sends them to three different
	 * functions depending on length, to be parsed and called. After the
	 * appropriate methods are called, the file is then serialized.
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		File gitletFile = new File(".gitlet");
		Gitlet gitlet;
		if (gitletFile.exists()) {
			gitlet = (Gitlet) deserialize(".gitlet/source.ser");
		} else {
			gitlet = new Gitlet();
		}
		if (args.length == 0) {
			System.out.println("Please enter a command.");
		} else {
			if (args.length == 1) {
				gitlet.parseOne(args[0]);
			} else if (args.length == 2) {
				gitlet.parseTwo(args[0], args[1]);
			} else if (args.length == 3) {
				gitlet.parseThree(args[0], args[1], args[2]);
			} else {
				System.out.println("No command with that name exists.");
			}
			serialize(gitlet, ".gitlet/source.ser");
		}
	}

	/**
	 * This takes one argument from the command line and determines which
	 * no-argument method to call.
	 * 
	 * @param arg1
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void parseOne(String arg1) throws FileNotFoundException,
			ClassNotFoundException, IOException {
		if (arg1.equals("init")) {
			init();
		} else if (arg1.equals("log")) {
			log();
		} else if (arg1.equals("global-log")) {
			global_log();
		} else if (arg1.equals("status")) {
			status();
		} else if (arg1.equals("commit")) {
			System.out.println("Please enter a commit message.");
		} else {
			System.out.println("No command with that name exists.");
		}

	}

	/**
	 * This takes in two arguments from the command line. The first argument is
	 * the name for a function, and the second argument will be an argument said
	 * function takes in.
	 * 
	 * @param arg1
	 * @param arg2
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void parseTwo(String arg1, String arg2)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		if (arg1.equals("add")) {
			add(arg2);
		} else if (arg1.equals("rm")) {
			rm(arg2);
		} else if (arg1.equals("commit")) {
			commit(arg2);
		} else if (arg1.equals("checkout")) {
			checkout(arg2);
		} else if (arg1.equals("find")) {
			find(arg2);
		} else if (arg1.equals("branch")) {
			branch(arg2);
		} else if (arg1.equals("rm-branch")) {
			rmBranch(arg2);
		} else if (arg1.equals("reset")) {
			reset(arg2);
		} else if (arg1.equals("merge")) {
			merge(arg2);
		} else if (arg1.equals("rebase")) {
			rebase(arg2);
		} else {
			System.out.println("No command with that name exists.");
		}

	}

	/**
	 * this function takes in three arguments from the command line and calls
	 * checkout using the last two arguments. Checkout is currently the only
	 * method that requires two arguments.
	 * 
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @throws IOException
	 */
	public void parseThree(String arg1, String arg2, String arg3)
			throws IOException {
		if (arg1.equals("checkout")) {
			checkout(arg2, arg3);
		} else {
			System.out.println("No command with that name exists.");
		}
	}

	/**
	 * Init must be called before anything else happens in Gitlet. This function
	 * sets everything up for Gitlet to run properly. In addition to setting up
	 * all of the data structures, it makes the initial commit.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */

	public void init() throws FileNotFoundException, IOException {

		File directoryGitlet = new File(".gitlet");
		if (directoryGitlet.exists()) {
			System.out
					.println("A gitlet version control system already exists in the current directory.");
		} else {
			stagingAreaForFileList = new ArrayList<File>();
			stagingAreaForFileNameList = new ArrayList<String>();
			branchNameList = new ArrayList<String>();
			branchNameToHeadMap = new HashMap<String, Commit>();
			commitToTrackingSetMap = new HashMap<Commit, HashSet<String>>();
			messageToCommitMap = new HashMap<String, List<Commit>>();
			idToCommitMap = new HashMap<Integer, Commit>();
			untrackingSet = new HashSet<String>();
			idSoFar = 0;
			isConflictedState = false;
			directoryGitlet.mkdir();
			File directoryStagingArea = new File(".gitlet/stagingArea");
			directoryStagingArea.mkdir();
			Commit initialCommit = new Commit(idSoFar);
			idSoFar++;
			currentBranchHead = initialCommit;
			branchNameList.add("master");
			globalLogList.add(initialCommit);
			branchNameToHeadMap.put("master", initialCommit);
			commitToTrackingSetMap.put(currentBranchHead,
					currentBranchHead.getTrackingSet());
			idToCommitMap.put(initialCommit.getId(), initialCommit);
			addMessageToMap("initial commit", initialCommit);
			currentBranchName = "master";
		}
	}

	/**
	 * The add function takes in a file name, which may be a simple file name,
	 * or it may be for a file located within a subdirectory. For a file in a
	 * subdirectory, it converts the '/' to '_' so it may be copied to the
	 * staging area appropriately. This is done to ensure that two files from
	 * different directories with the same name, are still differentiable.
	 * 
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void add(String fileName) throws FileNotFoundException, IOException,
			ClassNotFoundException {
		if (untrackingSet.contains(fileName)) {
			untrackingSet.remove(fileName);
		} else {
			File fileToBeAdded = new File(fileName);
			String fileNameWithoutDirectory = fileToBeAdded.getName();
			String fileNameWithUnderscore = removeSlash(fileName);
			File inStagingAreaFile = new File(".gitlet/stagingArea/"
					+ fileNameWithUnderscore);
			if (!fileToBeAdded.exists()) {
				System.out.println("File does not exist.");
			} else if (inStagingAreaFile.exists()) {
				return;
			} else {
				Files.copy(fileToBeAdded.toPath(), inStagingAreaFile.toPath(),
						StandardCopyOption.REPLACE_EXISTING);
				stagingAreaForFileList.add(fileToBeAdded);
				stagingAreaForFileNameList.add(fileNameWithUnderscore);
			}
		}
	}

	/**
	 * Commit takes a commit object, adds the current time, and the message
	 * given, and serializes all files, clearing the staging area. Then that
	 * commit is added to the current branch, and added to the global log. If
	 * the commit has no files, it prints "No changes added to the commit." Then
	 * it creates a new commit to be committed.
	 * 
	 * It resets isConflictedState to false, ending a conflicted state if there
	 * was one, and restoring all the methods to normal functioning.
	 * 
	 * @param message
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void commit(String message) throws ClassNotFoundException,
			IOException {
		File stagingArea = new File(".gitlet/stagingArea");
		if (stagingArea.list().length == 0) {
			System.out.println("No changes added to the commit.");
		} else {
			Commit newHead = new Commit(stagingAreaForFileList,
					stagingAreaForFileNameList, message, currentBranchHead,
					untrackingSet, idSoFar);
			globalLogList.add(newHead);
			File commitFile = new File(".gitlet/commit" + newHead.getId());
			commitFile.mkdir();
			idSoFar++;
			currentBranchHead = newHead;
			branchNameToHeadMap.put(currentBranchName, newHead);
			HashSet<String> currentTrackingSet = newHead.getTrackingSet();
			commitToTrackingSetMap.put(currentBranchHead, currentTrackingSet);
			idToCommitMap.put(newHead.getId(), newHead);
			addMessageToMap(message, newHead);
			for (String s : stagingAreaForFileNameList) {
				File f = new File(".gitlet/stagingArea/" + s);
				f.delete();
			}
			isConflictedState = false;
			untrackingSet = new HashSet<String>();
			stagingAreaForFileList = new ArrayList<File>();
			stagingAreaForFileNameList = new ArrayList<String>();

		}

	}

	/**
	 * This pulls the list from the hashmap if the message is already in the map
	 * and adds the new commit to the list, and puts it back into
	 * messageToCommitMap with: key: message and value: List<Commit>
	 * 
	 * @param message
	 * @param commitToBeAdded
	 */
	private void addMessageToMap(String message, Commit commitToBeAdded) {
		List<Commit> commitList = new ArrayList<Commit>();
		if (messageToCommitMap.containsKey(message)) {
			commitList = messageToCommitMap.get(message);
			commitList.add(commitToBeAdded);
			messageToCommitMap.put(message, commitList);
		} else {
			commitList.add(commitToBeAdded);
			messageToCommitMap.put(message, commitList);
		}
	}

	/**
	 * commitHelper checks to see if the files have been modified.
	 * 
	 * 
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public boolean commitHelper() throws ClassNotFoundException, IOException {
		int previousId = currentBranchHead.getId();
		HashSet<String> previousTrackingSet = commitToTrackingSetMap
				.get(currentBranchHead);
		ArrayList<File> previousList = ((Commit) deserialize(".gitlet/commit"
				+ previousId + "/commit.ser")).getFileList();
		for (String s : stagingAreaForFileNameList) {
			if (previousTrackingSet.contains(s)) {
				File sOfFile = stagingAreaForFileList
						.get(stagingAreaForFileNameList.indexOf(s));
				for (File previous : previousList) {
					if (previous.toString().equals(sOfFile.toString())) {
						return false; // no change
					}
				}

			}
		}
		return false;
	}

	/**
	 * rm takes a file from the staging area if it was there, otherwise prints
	 * "No reason to remove the file."
	 * 
	 * if the filename is within a directory, it replaces slashes with
	 * underscores, to match the add method.
	 * 
	 * @param fileName
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void rm(String fileName) throws ClassNotFoundException, IOException {
		String fileNameWithAngle = removeSlash(fileName);
		if (stagingAreaForFileNameList.contains(fileNameWithAngle)) {
			stagingAreaForFileList.remove(stagingAreaForFileNameList
					.indexOf(fileNameWithAngle));
			stagingAreaForFileNameList.remove(stagingAreaForFileNameList
					.indexOf(fileNameWithAngle));
		} else if (commitToTrackingSetMap.get(currentBranchHead).contains(
				fileNameWithAngle)) {
			untrackingSet.add(fileNameWithAngle);
		} else {
			System.out.println("No reason to remove the file.");
		}
	}

	/**
	 * The log method calls the log method within the Commit class. --which then
	 * iterates through all the commits following the given commit and prints
	 * them.
	 * 
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void log() throws ClassNotFoundException, IOException {
		currentBranchHead.log();

	}

	/**
	 * Iterates through the arraylist of all commits ever made printing each
	 * with its information.
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void global_log() throws ClassNotFoundException, IOException {
		for (Commit c : globalLogList) {
			System.out.println(c.toString());
		}
	}

	/**
	 * prints out the current list of branches, the staged files, and the files
	 * marked for untracking.
	 * 
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void status() throws ClassNotFoundException, IOException {

		System.out.println("=== Branches ===");
		for (String s : branchNameList) {
			if (currentBranchName.equals(s)) {
				System.out.print("*");
			}
			System.out.println(s);
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		for (String s : stagingAreaForFileNameList) {
			System.out.println(s);
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (String s : untrackingSet) {
			System.out.println(s);
		}
		System.out.println();

	}

	/**
	 * Locates all the commits with given message, only looking at the commits
	 * with that message. Pulls the List of commits with given message from
	 * messageToCommitMap.
	 * 
	 * @param message
	 */
	private void find(String message) {
		boolean foundCommit = false;
		if (messageToCommitMap.containsKey(message)) {
			List<Commit> commitList = messageToCommitMap.get(message);
			for (Commit c : commitList) {
				System.out.println(c.getId());
			}
			foundCommit = true;
		}
		if (!foundCommit) {
			System.out.println("Found no commit with that message.");
		}
	}

	/**
	 * This checkout only takes in one argument. The argument is a string, but
	 * could represent one of two things, a branch, or a commit. In the first
	 * case, it checks to see if the string is a branch, in that case it would
	 * iterate through the files in the branch to add to working directory.
	 * otherwise, it adds the given commit to the current working directory.
	 * 
	 * @param input
	 * @throws IOException
	 */
	private void checkout(String input) throws IOException {
		// Figure out if branch or file name (file will have
		// a .ser or .txt
		// then add to working directory.
		if (branchNameList.contains(input)) {
			// branch case
			if (isConflictedState) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
			} else {
				if (currentBranchName.equals(input)) {
					System.out
							.println("No need to checkout the current branch.");
				} else {
					currentBranchName = input;
					currentBranchHead = branchNameToHeadMap.get(input);
					HashSet<String> currentBranchHeadTrackingSet = commitToTrackingSetMap
							.get(currentBranchHead);
					Iterator<String> iter = currentBranchHeadTrackingSet
							.iterator();
					while (iter.hasNext()) {
						String currentBranchFileName = iter.next();
						checkout(currentBranchFileName);
					}
				}
			}
		} else {
			// file case
			Commit c = currentBranchHead;
			HashSet<String> currentTrackingSet = commitToTrackingSetMap.get(c);
			ArrayList<String> fileNameList = c.getFileNameList();
			File fileToBeCheckedOut = new File(input);
			String fileNameWithoutDirectory = fileToBeCheckedOut.toPath()
					.getFileName().toString();
			String fileNameWithAngle = removeSlash(input);
			if (currentTrackingSet == null
					|| !currentTrackingSet.contains(fileNameWithAngle)) {
				System.out
						.println("File does not exist in the most recent commit, or no such branch exists.");
			} else {
				while (!fileNameList.contains(fileNameWithAngle)) {
					c = c.getParent();
					fileNameList = c.getFileNameList();
				}
				File srcFile = new File(".gitlet/commit" + c.getId() + "/"
						+ fileNameWithAngle);
				for (String f : fileNameList) {
					if (f.equals(fileNameWithAngle)) {
						String destinationWithDirectory = addSlash(input);
						Path dst = Paths.get(destinationWithDirectory);
						Files.copy(srcFile.toPath(), dst,
								StandardCopyOption.REPLACE_EXISTING);
					}
				}
			}
		}
	}

	/**
	 * This replaces all instances of a slash in a string with an underscore.
	 * This is used for files that are located within a directory, so they may
	 * be copied, removed, or etc.
	 * 
	 * @param s
	 * @return
	 */
	private static String removeSlash(String s) {
		String result = s;
		if (result.contains("/")) {
			result = "";
			for (int k = 0; k < s.length(); k++) {
				if (s.charAt(k) == '/') {
					result += "!";
				} else {
					result += s.charAt(k);
				}
			}
		}
		return result;
	}

	/**
	 * This method does the opposite of the first method, to restore the file
	 * name to its original.
	 * 
	 * @param s
	 * @return
	 */
	private static String addSlash(String s) {
		String result = s;
		if (result.contains("!")) {
			result = "";
			for (int k = 0; k < s.length(); k++) {
				if (s.charAt(k) == '!') {
					result += "/";
				} else {
					result += s.charAt(k);
				}
			}

		}
		return result;
	}

	/**
	 * This method is the checkout that takes in two arguments, the commitID and
	 * the fileName.
	 * 
	 * It then checks out the file at that given commit.
	 * 
	 * @param commitId
	 * @param fileName
	 * @throws IOException
	 */
	private void checkout(String commitId, String fileName) throws IOException {
		if (idSoFar <= Integer.parseInt(commitId)) {
			System.out.println("No commit with that id exists.");
		} else {
			Commit c = idToCommitMap.get(Integer.parseInt(commitId));
			ArrayList<String> fileNameList = c.getFileNameList();
			HashSet<String> trackingFileNameSet = commitToTrackingSetMap.get(c);
			String fileNameWithAngle = removeSlash(fileName);
			if (!trackingFileNameSet.contains(fileNameWithAngle)) {
				System.out.println("File does not exist in that commit.");
			} else {
				while (!fileNameList.contains(fileNameWithAngle)) {
					c = c.getParent();
					fileNameList = c.getFileNameList();
				}
				String fileNameWithDirectory = addSlash(fileNameWithAngle);
				Path dst = Paths.get(fileNameWithDirectory);
				Path src = Paths.get(".gitlet/commit" + c.getId() + "/"
						+ fileNameWithAngle);
				Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
				return;

			}
		}
	}

	// BRANCH METHODS BELOW

	/**
	 * ADD BRANCH This makes a branch with string branch name
	 */
	private void branch(String BranchName) {
		if (isConflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
		} else {
			String branchName = BranchName;
			if (branchNameList.contains(branchName)) {
				System.out.println("A branch with that name already exists.");
			} else {
				branchNameList.add(branchName);
				branchNameToHeadMap.put(branchName, currentBranchHead);
			}
		}
	}

	/**
	 * RM BRANCH This removes a branch with name: BranchName
	 */
	private void rmBranch(String branchName) {
		if (isConflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
		} else {
			if (!branchNameList.contains(branchName)) {
				System.out.println("A branch with that name does not exist.");
			} else if (branchName.equals(currentBranchName)) {
				System.out.println("Cannot remove the current branch.");
			} else {
				branchNameList.remove(branchName);
				branchNameToHeadMap.remove(branchName);
			}
		}
	}

	/**
	 * If the program is not in a conflicted state, it checksout all files with
	 * that given commitID.
	 * 
	 * 
	 * @param commitId
	 * @throws IOException
	 */
	private void reset(String commitId) throws IOException {
		if (isConflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
		} else {
			if (idSoFar <= Integer.parseInt(commitId)) {
				System.out.println("No commit with that id exists.");
			} else {
				Commit c = idToCommitMap.get(Integer.parseInt(commitId));
				HashSet<String> setOfC = commitToTrackingSetMap.get(c);
				Iterator<String> iter = setOfC.iterator();
				while (iter.hasNext()) {
					String trackingFileName = iter.next();
					checkout(commitId, trackingFileName);
				}
				currentBranchHead = c;
			}
		}
	}

	/**
	 * Find split point determines the node at which the two branches merge.
	 * 
	 * 
	 * @param branchHead1
	 * @param branchHead2
	 * @return
	 */
	private static Commit findSplitPoint(Commit branchHead1, Commit branchHead2) {
		Stack<Commit> stack1 = new Stack<Commit>();
		Stack<Commit> stack2 = new Stack<Commit>();
		Commit placeHolder = branchHead1;
		while (placeHolder != null) {
			stack1.push(placeHolder);
			placeHolder = placeHolder.getParent();
		}
		placeHolder = branchHead2;
		while (placeHolder != null) {
			stack2.push(placeHolder);
			placeHolder = placeHolder.getParent();
		}
		Commit pop1 = stack1.pop();
		Commit pop2 = stack2.pop();
		while (pop1.equals(pop2)) {
			if (!stack1.isEmpty() && !stack2.isEmpty()) {
				pop1 = stack1.pop();
				pop2 = stack2.pop();
			} else {
				break;
			}
		}
		Commit result;
		if ((stack1.isEmpty() || stack2.isEmpty())
				&& !pop1.getParent().equals(pop2.getParent())) {
			result = pop1;
		} else {
			result = pop1.getParent();
		}
		return result;
	}

	/**
	 * Merges two branches, if a file has been modified in both branches, the
	 * program goes into a conflicted state.
	 * 
	 * 
	 * 
	 * @param givenBranchName
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void merge(String givenBranchName) throws IOException,
			ClassNotFoundException {
		if (isConflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
		} else {
			if (!branchNameList.contains(givenBranchName)) {
				System.out.println("A branch that name does not exist.");
			} else if (currentBranchName.equals(givenBranchName)) {
				System.out.println("Cannot marge a branch with itself");
			} else {
				Commit givenBranchHead = branchNameToHeadMap
						.get(givenBranchName);
				for (String s : givenBranchHead.getFileNameList()) {
					if (currentBranchHead.getFileNameList().contains(s)) {

						isConflictedState = true;
						File conflictedFile = new File(".gitlet/commit"
								+ givenBranchHead.getId() + "/" + s);
						String sWithDirectory = addSlash(s);
						File dst = new File(sWithDirectory + ".conflicted");
						Files.copy(conflictedFile.toPath(), dst.toPath(),
								StandardCopyOption.REPLACE_EXISTING);

					} else {
						String path = ".gitlet/commit"
								+ givenBranchHead.getId() + "/" + s;
						add(path);
					}
				}
				if (isConflictedState) {
					System.out.println("Encountered a merge conflict.");
				} else {
					commit("Merge " + currentBranchName + " with "
							+ givenBranchName);
				}
			}
		}
	}

	/**
	 * REBASE
	 * 
	 * @param givenBranchName
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void rebase(String givenBranchName) throws ClassNotFoundException,
			IOException {
		if (isConflictedState) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
		} else {
			if (!branchNameList.contains(givenBranchName)) {
				System.out.println("A branch with that name does not exist.");
			} else if (currentBranchName.equals(givenBranchName)) {
				System.out.println("Cannot rebase a branch onto itself");
			} else {
				Commit givenBranchHead = branchNameToHeadMap
						.get(givenBranchName);

				Commit splitPoint = findSplitPoint(currentBranchHead,
						givenBranchHead);
				if (splitPoint.equals(givenBranchHead)) {
					System.out.println("Already up-to-date.");
				} else {
					Stack<Commit> commitToBeRebasedStack = new Stack<Commit>();
					Commit placeHolder = currentBranchHead;
					while (!placeHolder.equals(splitPoint)) {
						commitToBeRebasedStack.push(placeHolder);
						placeHolder = placeHolder.getParent();
					}
					while (!commitToBeRebasedStack.isEmpty()) {
						givenBranchHead = commitForRebase(
								commitToBeRebasedStack.pop(), givenBranchHead);
					}
				}
			}
		}
	}

	/**
	 * COMMITFORREBASE
	 * 
	 * @param c
	 * @param givenBranchHead
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private Commit commitForRebase(Commit c, Commit givenBranchHead)
			throws ClassNotFoundException, IOException {

		Commit newHead = new Commit(c.getFileList(), c.getFileNameList(),
				c.getMessage(), givenBranchHead, untrackingSet, idSoFar,
				c.getId());
		File commitFile = new File(".gitlet/commit" + newHead.getId());
		commitFile.mkdir();
		idSoFar++;
		currentBranchHead = newHead;
		globalLogList.add(newHead);
		addMessageToMap(c.getMessage(), newHead);
		branchNameToHeadMap.put(currentBranchName, newHead);
		HashSet<String> currentTrackingSet = newHead.getTrackingSet();
		commitToTrackingSetMap.put(currentBranchHead, currentTrackingSet);
		idToCommitMap.put(newHead.getId(), newHead);
		return newHead;
	}

	/**
	 * SERIALIZE
	 * 
	 * @param o
	 * @param placeToSerialize
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void serialize(Object o, String placeToSerialize)
			throws FileNotFoundException, IOException {
		File ser = new File(placeToSerialize);
		FileOutputStream fos = new FileOutputStream(ser);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(o);
	}

	/**
	 * DESERIALIZE
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object deserialize(String path) throws IOException,
			ClassNotFoundException {
		FileInputStream fis = new FileInputStream(path);
		ObjectInputStream ois = new ObjectInputStream(fis);
		return ois.readObject();
	}

}