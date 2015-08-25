import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Gitlet implements Serializable {
	private String currentBranch;
	private Commit head;
	private int numOfCommits;
	private HashMap<String, File> currentlyStaged;
	private HashMap<String, File> currentlyTracked;
	private HashMap<String, File> currentlyUntracked;
	private HashMap<String, Commit> branchMap;
	private HashMap<Integer, Commit> allCommits;
	private HashMap<File, Integer> fileMap;
	private HashMap<String, ArrayList<Integer>> messageMap;
	private File stagingArea;
	private boolean conflicted = false;

	/**
	 * Constructor - initializes Gitlet object
	 */
	public Gitlet() {
		numOfCommits = 0;
		currentlyStaged = new HashMap<String, File>();
		currentlyTracked = new HashMap<String, File>();
		currentlyUntracked = new HashMap<String, File>();
		branchMap = new HashMap<String, Commit>();
		allCommits = new HashMap<Integer, Commit>();
		fileMap = new HashMap<File, Integer>();
		messageMap = new HashMap<String, ArrayList<Integer>>();
	}

	/**
	 * Serializes Gitlet object
	 * 
	 * @param object
	 *            to serialize
	 */
	private static void serialize(Gitlet g) {
		try {
			FileOutputStream fileStream = new FileOutputStream(
					".gitlet/Gitlet.ser");
			ObjectOutputStream os = new ObjectOutputStream(fileStream);
			os.writeObject(g);
			os.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Deserializes Gitlet object
	 * 
	 * @return Gitlet object
	 */
	private static Gitlet deserialize() {
		try {
			FileInputStream fileStream = new FileInputStream(
					".gitlet/Gitlet.ser");
			ObjectInputStream os = new ObjectInputStream(fileStream);
			Object o = os.readObject();
			Gitlet gitlet = (Gitlet) o;
			os.close();
			return gitlet;
		} catch (Exception e) {
			return new Gitlet();
		}
	}

	/**
	 * Initializes the Gitlet version control system
	 */
	public void init() {
		File f = new File(".gitlet");
		if (f.exists()) {
			System.out
					.println("A gitlet version control system already exists in the current directory.");
			return;
		}
		stagingArea = new File(".gitlet/staging area/");
		stagingArea.mkdirs();
		head = new Commit("initial commit", numOfCommits, null, null);
		currentBranch = "master";
		branchMap.put(currentBranch, head);
		allCommits.put(new Integer(head.getID()), head);
		numOfCommits = 1;
	}

	/**
	 * Adds a file - either stages it or marks it for tracking again
	 * 
	 * @param fileName
	 *            - name of the file
	 */
	public void add(String fileName) {
		File f = new File(fileName);
		if (!f.exists()) {
			System.out.println("File does not exist.");
			return;
		}
		if (currentlyUntracked.containsKey(fileName)) {
			currentlyTracked.put(fileName, f);
			currentlyUntracked.remove(fileName);
		} else {
			File fParent = f.getParentFile();
			File toPlace;
			if (fParent == null) {
				toPlace = new File(".gitlet/staging area/");
				toPlace.mkdirs();
			} else {
				toPlace = new File(".gitlet/staging area/" + fParent);
				toPlace.mkdirs();
			}
			try {
				File copy = new File(toPlace, f.getName());
				Files.copy(f.toPath(), copy.toPath(),
						StandardCopyOption.COPY_ATTRIBUTES);
				currentlyStaged.put(fileName,
						new File(toPlace + "/" + f.getName()));
			} catch (IOException e) {
			}
		}
	}

	/**
	 * Creates a new commit instance
	 * 
	 * @param message
	 *            - message of the commit
	 */
	public void commit(String message) {
		if (currentlyStaged.isEmpty() && currentlyUntracked.isEmpty()) {
			System.out.println("No changes added to the commit.");
			return;
		}
		Commit c = new Commit(message, numOfCommits, head, null);
		numOfCommits++;
		File commitFolder = new File(".gitlet/commit" + c.getID());
		commitFolder.mkdir();
		if (!messageMap.containsKey(message)) {
			messageMap.put(message, new ArrayList<Integer>());
		}
		messageMap.get(message).add(c.getID());
		for (String s : currentlyUntracked.keySet()) {
			if (currentlyTracked.containsKey(s)) {
				currentlyTracked.remove(s);
			}
		}
		File fParent = null;
		for (File f : currentlyStaged.values()) { // add staged files
			fParent = f.getParentFile();
			String sub = "";
			if (fParent.getPath().length() > 20) {
				sub = fParent.getPath().substring(21);
			}
			File toPlace = new File(".gitlet/commit" + c.getID() + "/" + sub);
			toPlace.mkdirs();
			File commitCopy = new File(toPlace, f.getName());
			try {
				Files.copy(f.toPath(), commitCopy.toPath(),
						StandardCopyOption.COPY_ATTRIBUTES);
			} catch (IOException e) {
			}
			if (sub.equals("")) {
				currentlyTracked.put(commitCopy.getName(), commitCopy);
			} else {
				currentlyTracked.put(sub + "/" + commitCopy.getName(),
						commitCopy);
			}
			fileMap.put(commitCopy, c.getID());
			f.delete();
		}
		for (String key : currentlyTracked.keySet()) { // add tracked files
			c.addFile(key, currentlyTracked.get(key));
		}
		head = c;
		branchMap.put(currentBranch, c);
		allCommits.put(c.getID(), c);
		currentlyStaged = new HashMap<String, File>();
		currentlyUntracked = new HashMap<String, File>();
		currentlyTracked = (HashMap<String, File>) c.getFiles().clone();
		if (conflicted) {
			conflicted = false;
		}
	}

	/**
	 * Removes a file - either unstages it or marks it for untracking
	 * 
	 * @param fileName
	 *            - name of the file
	 */
	public void rm(String fileName) {
		File f = new File(fileName);
		if (!currentlyStaged.containsKey(fileName)
				&& !currentlyTracked.containsKey(fileName)) {
			System.out.println("No reason to remove the file.");
			return;
		}
		if (currentlyStaged.containsKey(fileName)) {
			currentlyStaged.get(fileName).delete();
			currentlyStaged.remove(fileName);
		} else if (currentlyTracked.containsKey(fileName)) {
			currentlyTracked.remove(fileName);
			currentlyUntracked.put(fileName, f);
		}
	}

	/**
	 * Prints out a log of the current head and its parent commits
	 */
	public void log() {
		for (Commit c = head; c != null; c = c.getParent()) {
			System.out.println("===");
			System.out.println("Commit " + c.getID());
			System.out.println(c.getTime());
			System.out.println(c.getMessage());
			System.out.println();
		}
	}

	/**
	 * Prints out a log of all commits ever made
	 */
	public void globalLog() {
		for (Integer i : allCommits.keySet()) {
			Commit c = allCommits.get(i);
			System.out.println("===");
			System.out.println("Commit " + c.getID());
			System.out.println(c.getTime());
			System.out.println(c.getMessage());
			System.out.println();
		}
	}

	/**
	 * Finds a commit with given message - prints out its ID(s)
	 * 
	 * @param message
	 *            - message of the commit
	 */
	public void find(String message) {
		if (!messageMap.containsKey(message)) {
			System.out.println("Found no commit with that message.");
			return;
		}
		for (Integer i : messageMap.get(message)) {
			System.out.println(i);
		}
	}

	/**
	 * Prints out the status of Gitlet - branches, staged files, files marked
	 * for untracking
	 */
	public void status() {
		System.out.println("=== Branches ===");
		for (String s : branchMap.keySet()) {
			if (s.equals(currentBranch)) {
				System.out.print("*");
			}
			System.out.println(s);
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		for (String s : currentlyStaged.keySet()) {
			System.out.println(s);
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (String s : currentlyUntracked.keySet()) {
			System.out.println(s);
		}
	}

	/**
	 * Reverts the files in the working directory to the versions in the head of
	 * the branch, or the head commit
	 * 
	 * @param name
	 *            - name of the branch or file
	 */
	public void checkout(String name) {
		if (branchMap.containsKey(name)) {
			if (conflicted) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				return;
			}
			if (name.equals(currentBranch)) {
				System.out.println("No need to checkout the current branch.");
				return;
			}
			for (String s : branchMap.get(name).getFiles().keySet()) {
				File commitFile = branchMap.get(name).getFiles().get(s);
				File currentDirFile = new File(s);
				moveTo(commitFile, currentDirFile, fileMap.get(commitFile), s);
			}

			if (branchMap.get(currentBranch) != branchMap.get(name)) {
				currentlyTracked = (HashMap<String, File>) branchMap.get(name)
						.getFiles().clone();
			}
			head = branchMap.get(name);
			currentBranch = name;

		} else if (head.getFiles().keySet().contains(name)) {
			File commitFile = head.getFiles().get(name);
			File currentDirFile = new File(name);
			moveTo(commitFile, currentDirFile, fileMap.get(commitFile), name);
		} else {
			System.out
					.println("File does not exist in the most recent commit, or no such branch exists.");
		}
	}

	/**
	 * Reverts the file in the working directory to the version in the commit
	 * with the given ID
	 * 
	 * @param commitID
	 *            - ID of the commit
	 * @param fileName
	 *            - name of the file
	 */
	public void checkout(String commitID, String fileName) {
		int iD = Integer.parseInt(commitID);
		if (!allCommits.containsKey(iD)) {
			System.out.println("No commit with that id exists.");
			return;
		}
		Commit c = allCommits.get(iD);
		if (!c.getFiles().containsKey(fileName)) {
			System.out.println("File does not exist in that commit.");
			return;
		}
		File commitFile = c.getFiles().get(fileName);
		File currentDirFile = new File(fileName);
		moveTo(commitFile, currentDirFile, fileMap.get(commitFile), fileName);
	}

	/**
	 * Copies the file to working directory, creates another copy in the commit
	 * folder
	 * 
	 * @param commitFile
	 *            - name of the file in the commit folder
	 * @param currentDirFile
	 *            - file to be copied into in the working directory
	 * @param iD
	 *            - iD of the commit whose folder contains the commited file
	 * @return - newly moved to file
	 */
	private File moveTo(File commitFile, File currentDirFile, int iD,
			String fileName) {
		try {
			if (currentDirFile.getParentFile() != null) {
				currentDirFile.getParentFile().mkdirs();
			}
			Files.move(commitFile.toPath(), currentDirFile.toPath(),
					StandardCopyOption.REPLACE_EXISTING);
			File commitReplacement = new File(".gitlet/commit" + iD + "/"
					+ fileName);
			Files.copy(currentDirFile.toPath(), commitReplacement.toPath(),
					StandardCopyOption.COPY_ATTRIBUTES);

		} catch (IOException e) {
		}
		return currentDirFile;
	}

	/**
	 * Creates a new branch at the current commit
	 * 
	 * @param branchName
	 *            - name of the branch
	 */
	public void branch(String branchName) {
		if (branchMap.containsKey(branchName)) {
			System.out.println("A branch with that name already exists.");
			return;
		}
		branchMap.put(branchName, head);
	}

	/**
	 * Removes the branch pointer at the current commit
	 * 
	 * @param branchName
	 *            - name of the branch
	 */
	public void rmBranch(String branchName) {
		if (!branchMap.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (branchName.equals(currentBranch)) {
			System.out.println("Cannot remove the current branch.");
			return;
		}
		branchMap.remove(branchName);
	}

	/**
	 * Checks out the commit with the given ID and sets current branch's head to
	 * that commit
	 * 
	 * @param commitID
	 *            - ID of the commit
	 */
	public void reset(String commitID) {
		int iD = Integer.parseInt(commitID);
		if (!allCommits.containsKey(iD)) {
			System.out.println("No commit with that id exists.");
			return;
		}
		Commit c = allCommits.get(iD);
		for (String s : c.getFiles().keySet()) {
			checkout(commitID, s);
		}
		if (iD != head.getID()) {
			currentlyTracked = (HashMap<String, File>) c.getFiles().clone();
		}
		branchMap.put(currentBranch, c);
		head = c;
	}

	/**
	 * Merges the current branch with the branchName
	 * 
	 * @param branchName
	 *            - branch to merge the current branch with
	 */
	public void merge(String branchName) {
		if (!branchMap.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (branchName.equals(currentBranch)) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}
		Commit givenCommit = branchMap.get(branchName);
		Commit currentCommit = branchMap.get(currentBranch);
		Commit split = splitPoint(givenCommit, currentCommit);
		HashSet<String> commonFiles = new HashSet<String>();
		HashSet<String> givenFiles = new HashSet<String>();
		for (String s : split.getFiles().keySet()) {
			if (givenCommit.getFiles().containsKey(s)
					&& currentCommit.getFiles().containsKey(s)) {
				commonFiles.add(s);
			}
			if (givenCommit.getFiles().containsKey(s)
					&& !currentCommit.getFiles().containsKey(s)) {
				givenFiles.add(s);
			}
		}
		for (String s : commonFiles) {
			File f = split.getFiles().get(s);
			File f1 = givenCommit.getFiles().get(s);
			File f2 = currentCommit.getFiles().get(s);
			if (isModified(f, f1) && isModified(f, f2)) {
				conflicted = true;
				moveTo(f1, new File(s + ".conflicted"), fileMap.get(f1), s);
				System.out.println("Encountered a merge conflict.");
			} else if (isModified(f, f1) && !isModified(f, f2)) {
				checkout(Integer.toString(givenCommit.getID()), s);
				currentlyStaged.put(
						s,
						moveTo(f1, new File(".gitlet/staging area" + "/" + s),
								fileMap.get(f1), s));
			}
		}
		checkForSpecialCase(givenFiles, givenCommit, split);
		if (!conflicted) {
			commit("Merged " + currentBranch + " with " + branchName);
		}
	}

	/**
	 * Checks if a file has been modified relative to another
	 * 
	 * @param f1
	 *            - file to check
	 * @param f2
	 *            - file to compare to
	 * @return
	 */
	private boolean isModified(File f1, File f2) {
		byte[] b1 = null;
		byte[] b2 = null;
		try {
			b1 = Files.readAllBytes(f1.toPath());
			b2 = Files.readAllBytes(f2.toPath());
		} catch (IOException e) {
			System.out.println(e);
		}
		return !Arrays.equals(b1, b2);
	}

	/**
	 * Executes the special case for merge
	 * 
	 * @param givenFiles
	 *            - files unique to the given commit
	 * @param givenCommit
	 *            - head of the given branch
	 * @param split
	 *            - commit of the split point
	 */
	private void checkForSpecialCase(HashSet<String> givenFiles,
			Commit givenCommit, Commit split) {
		for (String s : givenFiles) {
			File f = givenCommit.getFiles().get(s);
			if (isModified(givenCommit.getFiles().get(s),
					split.getFiles().get(s))) {
				currentlyStaged.put(
						s,
						moveTo(f, new File(".gitlet/staging area" + "/" + s),
								fileMap.get(f), s));
			}
		}
	}

	/**
	 * Determines the commit of the split point of two branches
	 * 
	 * @param givenCommit
	 *            - head of the given branch
	 * @param currentCommit
	 *            - head of the current branch
	 * @return
	 */
	private Commit splitPoint(Commit givenCommit, Commit currentCommit) {
		HashSet<Integer> set1 = new HashSet<Integer>();
		HashSet<Integer> set2 = new HashSet<Integer>();
		ArrayList<Integer> a1 = new ArrayList<Integer>();
		ArrayList<Integer> a2 = new ArrayList<Integer>();
		while (givenCommit != null) {
			set1.add(givenCommit.getID());
			a1.add(givenCommit.getID());
			givenCommit = givenCommit.getParent();
		}
		while (currentCommit != null) {
			set2.add(currentCommit.getID());
			a2.add(currentCommit.getID());
			currentCommit = currentCommit.getParent();
		}
		if (set1.size() > set2.size() || set1.size() == set2.size()) {
			for (Integer iD : a2) {
				if (set1.contains(iD)) {
					return allCommits.get(iD);
				}
			}
		} else {
			for (Integer iD : a1) {
				if (set2.contains(iD)) {
					return allCommits.get(iD);
				}
			}
		}
		return null;
	}

	/**
	 * Rebase the given branch and the current branch
	 * 
	 * @param branchName
	 *            - name of the given branch
	 */
	public void rebase(String branchName) {
		if (!branchMap.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (branchName.equals(currentBranch)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}
		Commit givenCommit = branchMap.get(branchName);
		Commit currentCommit = branchMap.get(currentBranch);
		for (Commit c = currentCommit; c != null; c = c.getParent()) {
			if (c == givenCommit) {
				System.out.println("Already up-to-date.");
				return;
			}
		}
		for (Commit c = givenCommit; c != null; c = c.getParent()) {
			if (c == currentCommit) {
				if (givenCommit.getID() != currentCommit.getID()) {
					currentlyTracked = (HashMap<String, File>) c.getFiles()
							.clone();
				}
				branchMap.put(currentBranch, c);
				head = c;
				return;
			}
		}
		Commit tempPointer = null;
		Commit split = splitPoint(givenCommit, currentCommit);
		HashSet<String> modFiles = getModFiles(split, givenCommit);
		int count = numOfCommits - 1;
		boolean futureHead = true;
		Commit end = null;
		for (Commit c = currentCommit; c != split; c = c.getParent()) {
			count++;
			numOfCommits++;
		}
		for (Commit c = currentCommit; c != split; c = c.getParent()) {
			Commit commitCopy = new Commit(c.getMessage(), count, givenCommit,
					tempPointer);
			if (tempPointer != null) {
				tempPointer.setParent(commitCopy);
			}
			if (futureHead) {
				end = commitCopy;
				futureHead = false;
			}
			count--;
			commitCopy.setFiles((HashMap<String, File>) c.getFiles().clone());
			allCommits.put(commitCopy.getID(), commitCopy);
			tempPointer = commitCopy;
		}
		replayPropagate(tempPointer, split, givenCommit, modFiles);
		reset(Integer.toString(end.getID()));
	}

	/**
	 * Returns a HashSet of the modified files of two commits
	 * 
	 * @param split
	 *            - commit of the split
	 * @param givenCommit
	 *            - commit of the given branch
	 * @return
	 */
	private HashSet<String> getModFiles(Commit split, Commit givenCommit) {
		HashSet<String> modFiles = new HashSet<String>();
		for (String s : split.getFiles().keySet()) {
			if (givenCommit.getFiles().containsKey(s)) {
				if (isModified(split.getFiles().get(s), givenCommit.getFiles()
						.get(s))) {
					modFiles.add(s);
				}
			}
		}
		return modFiles;
	}

	/**
	 * Performs the replay propagations of rebase
	 * 
	 * @param replayFront
	 *            - first node of the replay
	 * @param split
	 *            - commit of the split node
	 * @param givenCommit
	 *            - head of the given branch
	 * @param modFiles
	 *            - files that were modified between the split point and
	 *            givenCommit
	 */
	private void replayPropagate(Commit replayFront, Commit split,
			Commit givenCommit, HashSet<String> modFiles) {
		for (Commit c = replayFront; c != null; c = c.getNext()) {
			boolean remove = false;
			String removeFile = "";
			for (String s : modFiles) {
				if (c.getFiles().containsKey(s)) {
					if (isModified(c.getFiles().get(s), split.getFiles().get(s))) {
						remove = true;
						removeFile = s;
					} else {
						c.getFiles().put(s, givenCommit.getFiles().get(s));
					}
				}
			}
			if (remove) {
				modFiles.remove(removeFile);
				remove = false;
			}
		}
	}

	/**
	 * Main method - decides which method to call based on the user input
	 * 
	 * @param args
	 *            - String array holding user input
	 */
	public static void main(String[] args) {
		Gitlet gitlet = deserialize();
		if (gitlet.conflicted) {
			if (args[0].equals("init") || args[0].equals("branch")
					|| args[0].equals("rm-branch") || args[0].equals("reset")
					|| args[0].equals("merge") || args[0].equals("rebase")) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				return;
			}
		}
		if (args.length == 0) {
			System.out.println("Please enter a command.");
		} else if (args[0].equals("init")) {
			gitlet.init();
		} else if (args[0].equals("add")) {
			gitlet.add(args[1]);
		} else if (args[0].equals("commit")) {
			if (args.length == 1) {
				System.out.println("Please enter a commit message.");
			} else {
				gitlet.commit(args[1]);
			}
		} else if (args[0].equals("rm")) {
			gitlet.rm(args[1]);
		} else if (args[0].equals("log")) {
			gitlet.log();
		} else if (args[0].equals("global-log")) {
			gitlet.globalLog();
		} else if (args[0].equals("find")) {
			gitlet.find(args[1]);
		} else if (args[0].equals("status")) {
			gitlet.status();
		} else if (args[0].equals("checkout")) {
			if (args.length == 3) {
				gitlet.checkout(args[1], args[2]);
			} else {
				gitlet.checkout(args[1]);
			}
		} else if (args[0].equals("branch")) {
			gitlet.branch(args[1]);
		} else if (args[0].equals("rm-branch")) {
			gitlet.rmBranch(args[1]);
		} else if (args[0].equals("reset")) {
			gitlet.reset(args[1]);
		} else if (args[0].equals("merge")) {
			gitlet.merge(args[1]);
		} else if (args[0].equals("rebase")) {
			gitlet.rebase(args[1]);
		} else {
			System.out.println("No command with that name exists.");
		}
		serialize(gitlet);
	}
}