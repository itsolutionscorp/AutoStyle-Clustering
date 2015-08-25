import java.io.File;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Stack;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;

public class Gitlet implements Serializable {
	private int id = 0;
	private boolean conflicted;
	private String curBranch = "master";
	private File gitDir;
	private File stageArea;
	private File serFile;
	private Commit currentCommit;
	private HashMap<String, File> stagedFiles = new HashMap<String, File>();
	private HashMap<Integer, Commit> mapOfCommits = new HashMap<Integer, Commit>();
	private HashMap<String, Commit> mapOfBranches = new HashMap<String, Commit>();
	private HashSet<String> removedFiles = new HashSet<String>();
	private HashMap<String, ArrayList<Integer>> messageID = new HashMap<String, ArrayList<Integer>>();
	
	/**
	 * Marks the file for untracking, meaning it will not be included in the upcoming commit
	 * even if it was tracked by that commit's parent. If the file had been staged, unstage
	 * it but don't also mark it for untracking. 
	 * 
	 * @param fileName
	 * 			a string that is the name of the file to be "removed" 			
	 */
	public void rm(String fileName) {
		if (stagedFiles.containsKey(fileName)) {
			stagedFiles.get(fileName).delete();
			stagedFiles.remove(fileName);
		} else {
			if (!currentCommit.getFiles().containsKey(fileName)) {
				System.out.println("No reason to remove the file.");
				return;
			}
			removedFiles.add(fileName);
		}
	}

	/**
	 * Returns true if a gitlet version control system does not already exist in the
	 * current directory. If one does not exist, creates a new gitlet version control 
	 * system in the current directory. This will automatically start with one commit 
	 * that does not contain any files and has the commit message "initial commit".
	 * 
	 * @return true if a gitlet version control system does not already exist in the 
	 * 		   current directory; false otherwise
	 */
	public boolean init() {
		if (!loadGit()) {
			gitDir = new File(".gitlet");
			gitDir.mkdirs();
			stageArea = new File(gitDir, "stagingArea");
			stageArea.mkdirs();
			(new File(gitDir, "files")).mkdirs();
			serFile = new File(gitDir, "git.ser");
			commit("initial commit");
			messageID.put("initial commit", new ArrayList<Integer>());
			messageID.get("initial commit").add(0);
			try {
				serFile.createNewFile();
			} catch (Exception e) {

			}
			conflicted = false;
			return true;
		} else {
			System.out.println("A gitlet version control system already exists in the current directory.");
			return false;
		}
	}

	/**
	 * Returns true if a gitlet version control system does not already exist in
	 * the current directory. 
	 * 
	 * @return true if a gitlet version control system does not already exist in the 
	 * 		   current directory; false otherwise			
	 */
	public boolean loadGit() {
		File temp = new File(".gitlet");
		if (temp.exists()) {
			return true;
		}
		return false;
	}

	/**
	 * Returns whether merge is conflicted or not.
	 * 
	 * @return whether merge is conflicted or not
	 */
	public boolean isConflicted() {
		return conflicted;
	}

	/**
	 * Adds a copy of the given file as it currently exists to the staging area. 
	 * If the file is marked for untracking, then unmarks the file but does not 
	 * add it to the staging area. 
	 * 
	 * @param fileName
	 * 			a string that is the name of the file to be added
	 */
	public void add(String fileName) {
		File temp = new File(fileName);
		if (!temp.exists()) {
			System.out.println("File does not exist.");
		} else {
			try {
				if (stagedFiles.containsKey(fileName) && !compareFiles(stagedFiles.get(fileName), temp)) {
					File destination = new File(stageArea, fileName);
					if (destination.getParentFile() != null) {
						destination.getParentFile().mkdirs();
					}
					Files.copy(temp.toPath(), destination.toPath());
					stagedFiles.put(fileName, destination);
				} else if (!stagedFiles.containsKey(fileName)) {
					File destination = new File(stageArea, fileName);
					if (destination.getParentFile() != null) {
						destination.getParentFile().mkdirs();
					}
					Files.copy(temp.toPath(), destination.toPath());
					stagedFiles.put(fileName, destination);
				} else {
					System.out.println("File name has not been modified since previous add. Please change, you scrub.");
				}
			} catch (Exception e) {
				e.printStackTrace();
				;
			}
		}
	}

	/**
	 * Creates a saved backup, a commit, of certain files with the given message.
	 * This commit tracks the files that are in it, keeps versions of these files exactly
	 * as they are, and does not update them.
	 *   
	 * @param message
	 * 			a string that is the commit message to be given to the upcoming commit
	 */
	public void commit(String message) {
		if (stagedFiles.size() == 0) {
			System.out.println("No changes added to the commit.");
		}
		Commit com = new Commit(currentCommit, stagedFiles, message, id, removedFiles);
		removedFiles = new HashSet<String>();
		currentCommit = com;
		mapOfBranches.put(curBranch, com);
		mapOfCommits.put(id, com);
		id++;
		stagedFiles = new HashMap<String, File>();
		conflicted = false;
		if (messageID.containsKey(message)) {
			messageID.get(message).add(id - 1);
		} else {
			messageID.put(message, new ArrayList<Integer>());
			messageID.get(message).add(id - 1);
		}
	}

	/**
	 * Prints the commit id, the time the commit was made, and the commit message of 
	 * each commit backwards along the commit tree until the initial commit. Commits
	 * are displayed with the most recent at the top. 
	 */
	public void log() {
		Commit temp = currentCommit;
		while (temp != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar c = temp.returnDate();
			System.out.println("===");
			System.out.println("Commit " + temp.returnID());
			System.out.println(dateFormat.format(c.getTime()));
			System.out.println(temp.getMessage());
			System.out.println("");
			temp = temp.getPrevCommit();
		}
	}

	/**
	 * Prints information, in no particular order, about all commits ever made.
	 */
	public void globalLog() {
		for (Integer i : mapOfCommits.keySet()) {
			Commit temp = mapOfCommits.get(i);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar c = temp.returnDate();
			System.out.println(dateFormat.format(c.getTime()));
			System.out.println("===");
			System.out.println("Commit " + temp.returnID());
			System.out.println(dateFormat.format(c.getTime()));
			System.out.println(temp.getMessage());
			System.out.println("");
			temp = temp.getPrevCommit();
		}
	}

	/**
	 * Prints the id of the commit that has the given commit message. If there are
	 * multiple commits with this same commit message, print the ids out on separate
	 * lines.
	 * 
	 * @param search
	 * 			a string that is the commit message to be found to be identified as 
	 * 			belonging to a certain commit
	 */
	public void find(String search) {
		if (messageID.get(search) == null) {
			System.out.println("Found no commit with that message.");
		}
		for (Integer i : messageID.get(search)) {
			System.out.println(i);
		}
	}

	/**
	 * Prints what branches currently exist and marks the current branch with a *.
	 * Also prints what files have been staged or marked for untracking. 
	 */
	public void status() {
		System.out.println("=== Branches ===");
		for (String s : mapOfBranches.keySet()) {
			Commit temp = mapOfBranches.get(s);
			if (s.equals(curBranch)) {
				System.out.println("*" + s);
			} else {
				System.out.println(s);
			}
		}
		System.out.println("");
		System.out.println("=== Staged Files ===");
		for (String s : stagedFiles.keySet()) {
			System.out.println(s);
		}
		System.out.println("");
		System.out.println("=== Files Marked for Untracking ===");
		for (String s : removedFiles) {
			System.out.println(s);
		}
	}

	/**
	 * Puts the version of the given file as it exists in the commit with the given
	 * id in the working directory, overwriting the version of the file that is 
	 * already there if there exists one. 
	 * 
	 * @param commitID
	 * 			a string that is the commit ID of the file to be checked out
	 * @param fileName
	 * 			a string that is the name of the file to be checked out
	 */
	public void checkout(String commitID, String fileName) {
		if (!mapOfCommits.containsKey(Integer.parseInt(commitID))) {
			System.out.println("No commit with that id exists.");
			return;
		} else {
			Commit temp = mapOfCommits.get(Integer.parseInt(commitID));
			HashMap<String, File> tempFiles = temp.getFiles();
			if (!tempFiles.containsKey(fileName)) {
				System.out.println("File does not exist in that commit.");
				return;
			} else {
				try {
					File f = tempFiles.get(fileName);
					File dest = new File(fileName);
					File temptemp = new File(dest.getParent());
					temptemp.mkdirs();
					Files.copy(f.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Puts the version of the given file as it exists in the front of the current
	 * branch in the working directory, overwriting the version of the file that is already 
	 * there if there exists one. 
	 * 
	 * @param name
	 * 			a string that is the name of the file to be checked out
	 */
	public void checkout(String name) {
		try {
			if (mapOfBranches.containsKey(name)) {
				if (curBranch.equals(name)) {
					System.out.println("No need to checkout the current branch.");
					return;
				} else {
					checkoutBranch(name);
				}
			} else if (currentCommit.getFiles().containsKey(name)) {
				checkoutFile(name);
			} else {
				System.out.println("File does not exist in the most recent commit, or no such branch exists.");
				return;
			}
		} catch (Exception e) {
			System.out.println("yolo strats!");
		}
	}

	/**
	 * Takes all files in the commit at the front of the given branch and puts them in the
	 * working directory, overwriting the versions of the files that are already there if 
	 * they exist. The given branch will also then be considered the current branch.
	 *  
	 * @param s
	 * 			a string that is the name of the branch to be checked out
	 * @throws Exception
	 * 			if a merge conflict occurs
	 */
	public void checkoutBranch(String s) throws Exception {
		if (conflicted) {
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		curBranch = s;
		currentCommit = mapOfBranches.get(s);
		for (String n : currentCommit.getFiles().keySet()) {
			File temp = new File(n);
			if (!temp.exists()) {
				temp.createNewFile();
			}
			File temptemp = new File(temp.getParent());
			temptemp.mkdirs();
			Files.copy(currentCommit.getFiles().get(n).toPath(), temp.toPath(), StandardCopyOption.REPLACE_EXISTING);

		}
	}

	/**
	 * Creates a copy of the checked out version of the given file. 
	 * 
	 * @param
	 * 			a string that is the name of the file to be checked out
	 * @throws Exception
	 * 			if the destination that the copy is to be copied to does not exist
	 */
	public void checkoutFile(String s) throws Exception {
		// System.out.println("HEFJKJD");
		File temp = currentCommit.getFiles().get(s);
		File dest = new File(s);
		if (!dest.exists()) {
			dest.createNewFile();
		}
		File temptemp = new File(dest.getParent());
		temptemp.mkdirs();
		Files.copy(temp.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}

	/**
	 * Creates a new branch with the given name, and points it at the current head
	 * node, but this newly created branch does not immediately switch to be the 
	 * current branch.
	 * 
	 * @param branchName
	 * 			a string that is the name of the branch to be created
	 */
	public void branch(String branchName) {
		if (mapOfBranches.containsKey(branchName)) {
			System.out.println("A branch with that name already exists.");
			return;
		} else {
			mapOfBranches.put(branchName, currentCommit);
		}
	}

	/**
	 * Deletes the pointer that points to the branch with the given name.
	 * 
	 * @param branchName
	 * 			a string that is the name of the branch to be deleted
	 */
	public void rmbranch(String branchName) {
		if (!mapOfBranches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (curBranch.equals(branchName)) {
			System.out.println("Cannot remove the current branch.");
			return;
		} else {
			mapOfBranches.remove(branchName);
			return;
		}
	}

	/**
	 * Checks out all the files tracked by the given commit and moves the 
	 * current branch's head to that commit node.
	 * 
	 * @param id
	 * 			a string that is the id of the commit to be reset
	 */
	public void reset(String id) {
		Commit checkNode = mapOfCommits.get(Integer.parseInt(id));
		if (checkNode == null) {
			System.out.print("No commit with that id exists.");
			return;
		}
		for (String key : mapOfCommits.get(Integer.parseInt(id)).getFiles().keySet()) {
			checkout(id, key);
		}
		mapOfBranches.put(curBranch, mapOfCommits.get(Integer.parseInt(id)));
		currentCommit = mapOfCommits.get(Integer.parseInt(id));
	}

	/**
	 * Creates a copy of the given file.
	 * 
	 * @param sourceFile
	 * 			a file that should already exist that is to be copied
	 * @param destFile
	 * 			a file that is the destination of where the copied file should
	 * 			be copied to
	 */
	private static void copyFile(File sourceFile, File destFile) {

		try {
			if (!sourceFile.exists()) {
				return;
			}
			if (!destFile.exists()) {
				destFile.createNewFile();
			}
			FileChannel source = null;
			FileChannel destination = null;
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			if (destination != null && source != null) {
				destination.transferFrom(source, 0, source.size());
			}
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}

	/**
	 * Returns true if the two given files to be compared are equal.
	 * 
	 * @param file1
	 * 			the first given file to be compared to the second given file
	 * @param file2
	 * 			the second given file to be compared to the first given file
	 * @return true if the two given files are equal; false otherwise
	 */
	public boolean compareFiles(File file1, File file2) {
		try {
			FileReader fR1 = new FileReader(file1);
			FileReader fR2 = new FileReader(file2);

			BufferedReader reader1 = new BufferedReader(fR1);
			BufferedReader reader2 = new BufferedReader(fR2);

			String line1 = null;
			String line2 = null;
			while (((line1 = reader1.readLine()) != null) && ((line2 = reader2.readLine()) != null)) {
				if (!line1.equalsIgnoreCase(line2))
					return false;
			}
			reader1.close();
			reader2.close();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("File does not exist.");
			return false;
		} catch (IOException e) {
			System.out.println("IO Exception");
			return false;
		}
	}

	/**
	 * Returns a gitlet version control system folder. 
	 * 
	 * @return the gitlet version control system folder
	 */
	public static Gitlet loadGitObject() {
		Gitlet temp = null;

		try {
			FileInputStream fis = new FileInputStream(new File(".gitlet", "git.ser"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			temp = (Gitlet) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return temp;
	}

	/**
	 * Saves the gitlet version control system in the current directory to the 
	 * serialization file.
	 * 
	 * @param git
	 * 			the gitlet version control system in the current directory
	 */
	public static void saveGit(Gitlet git) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(".gitlet", "git.ser"));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(git);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Merges files from the given branch into the current branch. Any files that have been
	 * modified in the given branch since the split point, but not modified in the current 
	 * branch since the split point should be changed to their versions in the given branch. 
	 * These files should then all be staged. 
	 * 
	 * @param branch
	 * 			a string that is the name of the branch to be merged into current branch
	 * @throws Exception
	 * 			if a file has been untracked between commits in a branch			
	 */
	public void merge(String branch) throws Exception {
		if (curBranch.equals(branch)) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}
		if (!mapOfBranches.containsKey(branch)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		boolean hasConflicted = false;
		Commit temp = currentCommit;
		HashMap<String, File> curFiles = currentCommit.getFiles();
		HashMap<String, File> givFiles = mapOfBranches.get(branch).getFiles();
		HashMap<String, File> splitFiles;
		TreeSet<Integer> commitsCurrentBranch = new TreeSet<Integer>();
		TreeSet<Integer> commitsGivenBranch = new TreeSet<Integer>();
		Commit splitPoint = null;
		HashSet<String> currentMod = new HashSet<String>();
		HashSet<String> givenMod = new HashSet<String>();
		while (temp != null) {
			commitsCurrentBranch.add(temp.returnID());
			temp = temp.getPrevCommit();
		}
		temp = mapOfBranches.get(branch);
		while (temp != null) {
			commitsGivenBranch.add(temp.returnID());
			temp = temp.getPrevCommit();
		}
		for (Integer i : commitsCurrentBranch) {
			if (commitsGivenBranch.contains(i)) {
				splitPoint = mapOfCommits.get(i);
			}
		}
		splitFiles = splitPoint.getFiles();
		for (String s : curFiles.keySet()) {
			if (!splitFiles.containsKey(s)) {
				currentMod.add(s);
			} else if (!compareFiles(curFiles.get(s), splitFiles.get(s))) {
				currentMod.add(s);
			}
		}
		for (String s : givFiles.keySet()) {
			if (!splitFiles.containsKey(s) == false) {
				givenMod.add(s);
			} else if (!compareFiles(givFiles.get(s), splitFiles.get(s))) {
				givenMod.add(s);
			}
		}

		for (String s : givenMod) {
			if (!currentMod.contains(s)) {
				checkout(Integer.toString(mapOfBranches.get(branch).returnID()), s);
				add(s);
			} else {
				File temp3 = new File(s + ".conflicted");
				temp3.createNewFile();
				copyFile(givFiles.get(s), temp3);
				hasConflicted = true;
			}
		}
		if (!hasConflicted) {
			commit("Merged " + curBranch + " with " + branch + ".");
		} else {
			System.out.println("Encountered a merge conflict.");
			conflicted = true;
		}
	}

	/**
	 * Attaches the current branch to the head of the given branch. 
	 * 
	 * @param branch
	 * 			a string that is the name of the branch to be attached to
	 * 			the current branch 
	 */
	public void rebase(String branch) {
		if (!mapOfBranches.containsKey(branch)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (branch.equals(curBranch)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}
		Commit temp = currentCommit;
		HashMap<String, File> splitFiles;
		TreeSet<Integer> commitsCurrentBranch = new TreeSet<Integer>();
		TreeSet<Integer> commitsGivenBranch = new TreeSet<Integer>();
		Commit splitPoint = null;
		while (temp != null) {
			commitsCurrentBranch.add(temp.returnID());
			temp = temp.getPrevCommit();
		}
		temp = mapOfBranches.get(branch);
		while (temp != null) {
			commitsGivenBranch.add(temp.returnID());
			temp = temp.getPrevCommit();
		}
		for (Integer i : commitsCurrentBranch) {
			if (commitsGivenBranch.contains(i)) {
				splitPoint = mapOfCommits.get(i);
			}
		}
		if (splitPoint == currentCommit) {
			currentCommit = mapOfBranches.get(branch);
			mapOfBranches.put(curBranch, currentCommit);
		}
		if (splitPoint == mapOfBranches.get(branch)) {
			System.out.println("Already up-to-date.");
			return;
		}
		splitFiles = splitPoint.getFiles();
		Commit temp3 = currentCommit;
		Stack<Commit> currentBranchStack = new Stack<Commit>();
		while (temp3 != splitPoint) {
			currentBranchStack.push(temp3);
			temp3 = temp3.getPrevCommit();
		}
		Commit prevBranch = currentCommit;
		currentCommit = mapOfBranches.get(branch);
		curBranch = branch;
		while (!currentBranchStack.empty()) {
			Commit temp4 = currentBranchStack.pop();
			ArrayList<String> currBranchSplitBranch = new ArrayList<String>();
			for (String s : temp4.getFiles().keySet()) {
				if (splitFiles.containsKey(s) && !compareFiles(splitFiles.get(s), temp4.getFiles().get(s))) {
					currBranchSplitBranch.add(s);
				}
			}
			ArrayList<String> giveBranchSplitBranch = new ArrayList<String>();
			for (String s : currentCommit.getFiles().keySet()) {
				if (splitFiles.containsKey(s) && !compareFiles(splitFiles.get(s), currentCommit.getFiles().get(s))) {
					giveBranchSplitBranch.add(s);
				}
			}
			for (String s : temp4.getFiles().keySet()) {
				if (currBranchSplitBranch.contains(s)) {
					checkout(Integer.toString(prevBranch.returnID()), s);
					add(s);
				} else if (!giveBranchSplitBranch.contains(s)) {
					checkout(Integer.toString(currentCommit.returnID()), s);
					add(s);
				}
			}
			commit(temp4.getMessage());
		}
		reset(Integer.toString(currentCommit.returnID()));
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please enter a command.");
			return;
		}
		String command = args[0];
		String[] allowed = { "add", "rm", "commit", "checkout", "log", "global-log", "find", "status" };
		Gitlet git = new Gitlet();
		if (git.loadGit()) {
			git = loadGitObject();
		}
		if (git.isConflicted() && !Arrays.asList(allowed).contains(command)) {
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		switch (command) {
		case "init":
			git.init();
			break;
		case "add":
			git.add(args[1]);
			break;
		case "rm":
			git.rm(args[1]);
			break;
		case "commit":
			if (args.length <= 1 || args[1].equals("")) {
				System.out.println("Please enter a commit message.");
				break;
			}
			git.commit(args[1]);
			break;
		case "log":
			git.log();
			break;
		case "global-log":
			git.globalLog();
			break;
		case "find":
			git.find(args[1]);
			break;
		case "status":
			git.status();
			break;
		case "branch":
			git.branch(args[1]);
			break;
		case "rm-branch":
			git.rmbranch(args[1]);
			break;
		case "checkout":
			if (args.length > 2) {
				git.checkout(args[1], args[2]);
			} else {
				git.checkout(args[1]);
			}
			break;
		case "reset":
			git.reset(args[1]);
			break;
		case "merge":
			try {
				git.merge(args[1]);
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		case "rebase":
			git.rebase(args[1]);
			break;
		default:
			System.out.println("No command with that name exists.");
			break;
		}
		saveGit(git);
	}
}