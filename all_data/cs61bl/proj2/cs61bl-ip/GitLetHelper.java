import java.io.IOException;
import java.io.Serializable;
import java.io.File;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.*;
import java.nio.file.StandardCopyOption;

/**
 * GitLetHelper, a helper class for gitlit, taking command from gitlet and
 * perform by the command, store all instance variables and data in here
 * 
 * @author Ki Ey Kouch (cs61bl-ht), Mitchell Ma (cs61bl-hx), Angela Kuo (cs61bl-io), 
 * 			BingQing Li (cs61bl-ip)
 */
public class GitLetHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private HashMap<String, CommitNode> branches;
	private String currentBranch = "";
	private CommitNode headPointer = null;
	private int idGenerator = 0;
	private HashMap<Integer, CommitNode> globalLog;
	private HashMap<String, File> stag;
	private HashMap<String, File> Untrack;
	private boolean flag = false;
	// CREDITS: http://stackoverflow.com/questions/4871051/getting-the-current-working-directory-in-java
	private final String Directory = System.getProperty("user.dir");
	private final String comitfolder = Directory + File.separator + ".gitlet"
			+ File.separator + "commit" + File.separator;
	private final String staging = Directory + File.separator + ".gitlet"
			+ File.separator + "stagging" + File.separator;

	/**
	 * Initialize the GitLet to running create 2 folders, inside the hidden folder gitlet.
	 * initialize branches, globalLog, (stag and Untrack) create first commit with id 0 
	 * and make headpoint point to it. store master branch in branches, commit in globallog
	 * increment idgenerator.
	 */
	private void init() {
		if (idGenerator != 0) {
			System.out.println("A gitlet version control system already "
					+ "exists in the current directory.");
			return;
		}
		new File(comitfolder).mkdirs();
		new File(staging).mkdirs();
		stag = new HashMap<String, File>();
		Untrack = new HashMap<String, File>();
		currentBranch = "master";
		headPointer = new CommitNode(idGenerator, "initial commit", null, null,
				null);
		globalLog = new HashMap<Integer, CommitNode>();
		globalLog.put(idGenerator, headPointer);
		idGenerator++;
		branches = new HashMap<String, CommitNode>();
		branches.put(currentBranch, headPointer);
	}

	/**
	 * Checking if the file is exist, print appropriate message using FileChannel, 
	 * open and close File, making a copy of file to staging area create the dest 
	 * file and transfer the byte of source to new destination.
	 * 
	 * CREDITS: http://www.journaldev.com/861/4-ways-to-copy-file-in-java
	 * @param filename the file name that need to add
	 */
	private void add(String filename) {
		if (filename == null) {
			System.out.println("File does not exist.");
			return;
		}
		File sourcefile = new File(Directory + File.separator + filename);
		File destFile = new File(staging + filename);

		if (Untrack.get(filename) != null)
			Untrack.remove(filename);
		else
		{
			try {
				if (sourcefile.exists() && !destFile.exists())
					destFile.mkdirs();
				Files.copy(sourcefile.toPath(), destFile.toPath(),
						StandardCopyOption.REPLACE_EXISTING);

			} catch (IOException e) {
				System.out.println("File does not exist.");
				return;
			}
			stag.put(filename, destFile);
		}
	}

	/**
	 * Perform commit, create new commitnode, where commitnode make trasfer
	 * files to new commit folder, and point it to its parent.
	 * 
	 * HeadPoint and currentBranch headpointer are pointing to this new commit,
	 * update idGenerator, add this commit to global log.
	 * 
	 * @param message String commit message
	 */
	private void commit(String message) {
		if (stag.size() == 0 && Untrack.size() == 0) {
			System.out.println("No changes added to the commit.");
			return;
		}
		headPointer = new CommitNode(idGenerator, message, headPointer, stag,
				Untrack);
		branches.put(currentBranch, headPointer);
		globalLog.put(idGenerator, headPointer);
		idGenerator++;

		stag = new HashMap<String, File>();
		Untrack = new HashMap<String, File>();

		File file = new File(staging);
		delete(file);
		new File(staging).mkdirs();
		flag = false;
	}

	/**
	 * This helper method will delete everything in this including itself.
	 * 
	 * @param f File location to delete
	 */
	private void delete(File f) {
		if (f.isDirectory()) {
			for (File c : f.listFiles())
				delete(c);
		}
		f.delete();
	}

	/**
	 * Remove the file from the stagging area if stagged if the file haven't
	 * stagged, then add it into untrack file otherwise, print the error message
	 * 
	 * @param filename name of the file that need to remove
	 */
	private void rm(String filename) {
		File sourcefile = new File(staging + filename);
		if (stag.containsKey(filename)) {
			stag.remove(filename);
			sourcefile.delete();
			return;
		} else if (!Untrack.containsKey(filename)) {
			sourcefile = new File(Directory + File.separator + filename);
			Untrack.put(filename, sourcefile);
			return;
		}
		System.out.println("No reason to remove the file.");
		return;
	}

	/**
	 * Print all commit from the current print until the initial commit
	 * using HashMap branches to get the currentbranch headPointer
	 * iteration throught and using commitnode print method
	 */
	private void log() {
		CommitNode temp = branches.get(currentBranch);
		while (temp != null) {
			temp.print();
			temp = temp.getNext();
		}
	}

	/**
	 * Print all commit that ever commit. Iteration through commit in the globalLog
	 * and using commitNode print method.
	 */
	private void globallog() {
		for (int id : globalLog.keySet()) {
			globalLog.get(id).print();
		}
	}

	/**
	 * Take message of commit and print it. If there are multiple, print all of
	 * them.
	 * 
	 * @param message a string message of commit you want to find
	 */
	private void find(String message) {
		boolean found = false;
		for (int id : globalLog.keySet()) {
			if (globalLog.get(id).getMessage().equals(message)) {
				System.out.println(id);
				found = true;
			}
		}
		if (!found)
			System.out.println("Found no commit with that message.");
	}

	/**
	 * Print the status of your current git, including name of branches, tracked
	 * and untracked file.
	 */
	private void status() {
		System.out.println("=== Branches ===");
		for (String key : branches.keySet()) {
			if (key.equals(currentBranch)) {
				System.out.println("*" + key);
				continue;
			}
			System.out.println(key);
		}
		System.out.println("\n=== Staged Files ===");
		for (String filenmae : stag.keySet())
			System.out.println(filenmae);

		System.out.println("\n=== Files Marked for Untracking ===");
		for (String filenmae : Untrack.keySet())
			System.out.println(filenmae);
	}

	/**
	 * Takes the version of the file as it exists in the head commit, the front
	 * of the current branch, and puts it in the working directory, overwriting
	 * the version of the file that's already there if there is one.
	 * 
	 * If name is a branch name, then checkout all the files in the branch.
	 * 
	 * @param name String of branch or file name
	 */
	private void checkout(String name) {
		if (branchExist(name)) {
			if (name.equals(currentBranch))
				System.out.println("No need to checkout the current branch.");
			else {
				currentBranch = name;
				headPointer = branches.get(currentBranch);
				for (File f : headPointer.getFile().values()) {
					copy(f);
				}
			}
		} else {
			File f = headPointer.getFile().get(name);
			if (f == null || !f.exists()) {
				System.out.println("File does not exist in the most recent "
						+ "commit, or no such branch exists.");
				return;
			}
			copy(f);
		}
	}

	/**
	 * Takes the version of the file as it exists in the commit with the given
	 * id, and puts it in the working directory, overwriting the version of the
	 * file that's already there if there is one.
	 * 
	 * @param id String of commit id
	 * @param filename String filename
	 */
	private void checkout(String id, String filename) {
		int numId;
		try {
			numId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			System.out.println("No commit with that id exists.");
			return;
		}
		CommitNode commit = globalLog.get(numId);
		if (commit == null) {
			System.out.println("No commit with that id exists.");
			return;
		}
		File getFile = commit.getFile().get(filename);
		if (getFile == null || !getFile.exists()) {
			System.out.println("File does not exist in that commit.");
			return;
		}
		copy(getFile);
	}

	/**
	 * Helper method to copy file name from commit folder to working directory,
	 * use only checkout and reset so far.
	 * 
	 * @param f File to copy
	 */
	private void copy(File f) {
		String filePath = f.getAbsolutePath().substring(
				f.getAbsolutePath().indexOf("commit"));
		filePath = filePath.substring(filePath.indexOf(File.separator) + 1);
		filePath = filePath.substring(filePath.indexOf(File.separator));

		File destFile = new File(Directory + filePath);

		if (f.exists() && !destFile.exists())
			destFile.mkdirs();

		try {
			Files.copy(f.toPath(), destFile.toPath(),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			return;
		}
	}

	/**
	 * Helper method to check if the given name branch exist.
	 * 
	 * @param branchname String branchname to check in HashMap branches
	 */
	private boolean branchExist(String branchname) {
		if (branches.get(branchname) != null)
			return true;
		return false;
	}

	/**
	 * Create branch by given name.
	 * 
	 * @param branchname String branch name to create
	 */
	private void branch(String branchname) {
		if (flag) {
			System.out.println("Cannot do this command until the merge "
					+ "conflict has been resolved.");
			return;
		}
		if (branchExist(branchname)) {
			System.out.println("A branch with that name already exists.");
			return;
		}
		branches.put(branchname, headPointer);
	}

	/**
	 * Remove branch from the GitletHelper object
	 * 
	 * @param branchname String branchname
	 */
	private void rmbranch(String branchname) {
		if (flag) {
			System.out.println("Cannot do this command until the merge "
					+ "conflict has been resolved.");
			return;
		}
		if (currentBranch.equals(branchname))
			System.out.println("Cannot remove the current branch.");
		else if (!branchExist(branchname))
			System.out.println("A branch with that name does not exist.");
		else {
			branches.remove(branchname);
		}
	}

	/**
	 * Get the commit id and change the commit head to this commit id, update
	 * currentBranch commitNode, and overwrite file.
	 * 
	 * @param id int id of commit
	 */
	private void reset(String id) {
		if (flag) {
			System.out.println("Cannot do this command until the merge "
					+ "conflict has been resolved.");
			return;
		}
		int numId;
		try {
			numId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			System.out.println("No commit with that id exists.");
			return;
		}
		CommitNode temp = globalLog.get(numId);
		if (temp == null) {
			System.out.println("No commit with that id exists.");
			return;
		}
		headPointer = temp;
		branches.put(currentBranch, headPointer);
		for (String name : headPointer.getFile().keySet()) {
			copy(headPointer.getFile().get(name));
		}
	}
	
	/**
	 * Merges files from the given branch into the current branch.
	 * 
	 * @param branchname String branch to merge with current
	 */
	private void merge(String branchname) {
		if (flag) {
			System.out.println("Cannot do this command until the merge "
					+ "conflict has been resolved.");
			return;
		}
		if (branchExist(branchname) == false) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (currentBranch.equals(branchname)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}

		Stack<CommitNode> split = SplitPoint(branchname, "giv");
		HashMap<String, File> currentFiles = headPointer.getFile();
		HashMap<String, File> mergingFiles = branches.get(branchname).getFile();
		HashMap<String, File> splitFiles = split.pop().getFile();

		HashMap<String, File> conflictedFiles = new HashMap<String, File>();
		for (String file : mergingFiles.keySet()) {
			mergeHelper(currentFiles, splitFiles, mergingFiles, conflictedFiles, file);
		}

		for (String name : currentFiles.keySet()) {
			if (!conflictedFiles.containsKey(name) && !stag.containsKey(name)) {
				stag.put(name, currentFiles.get(name));
			}
		}

		if (flag == false) {
			branches.put(branchname, headPointer);
			commit("Merged " + currentBranch + " with " + branchname);
			return;
		} else {
			System.out.println("Encountered a merge conflict.");
			return;
		}
	}
	
	/**
	 * Continuation of merge method.
	 * 
	 * @param currentFiles HashMap<String, File> current files of branch
	 * @param splitFiles HashMap<String, File> files from the splitpoint
	 * @param mergingFiles HashMap<String, File> files from merge branch
	 * @param conflictedFiles HashMap<String, File> conflicted files
	 * @param file String file of each merge branch
	 */
	private void mergeHelper(HashMap<String, File> currentFiles,
			HashMap<String, File> splitFiles, HashMap<String, File> mergingFiles,
			HashMap<String, File> conflictedFiles, String file) {

		if (currentFiles.containsKey(file) && splitFiles.containsKey(file)) {
			File SplitFile = splitFiles.get(file);
			File CurrentFile = currentFiles.get(file);
			File MergeFile = mergingFiles.get(file);

			if (!compare(SplitFile, MergeFile)
					&& compare(SplitFile, CurrentFile)) {
				copy(MergeFile);
				stag.put(file, MergeFile);
			} else if (compare(SplitFile, MergeFile)
					&& !compare(SplitFile, CurrentFile)) {
				stag.put(file, CurrentFile);

			} else if (!compare(SplitFile, MergeFile)
					&& !compare(SplitFile, CurrentFile)) {
				copy(MergeFile);
				renameConflict(file);
				copy(CurrentFile);
				flag = true;
				conflictedFiles.put(file, CurrentFile);
			}
		} else if (currentFiles.get(file) != null
				&& !compare(mergingFiles.get(file), currentFiles.get(file))) {
			copy(mergingFiles.get(file));
			renameConflict(file);
			copy(currentFiles.get(file));
			flag = true;
			conflictedFiles.put(file, currentFiles.get(file));
		}

		else {
			stag.put(file, mergingFiles.get(file));
		}
	}

	/**
	 *Find the split point of the current branch and the given branch, 
	 *then snaps off the current branch at this point, then reattaches 
	 *the current branch to the head of the given branch.
	 *
	 * @param branchname
	 */
	private void rebase(String branchname) {
		if (flag) {
			System.out.println("Cannot do this command until the merge conflict has "
							+ "been resolved.");
			return;
		}
		if (!branchExist(branchname)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (currentBranch.equals(branchname)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}
		Stack<CommitNode> split = SplitPoint(branchname, "cur");
		if (split == null) {
			System.out.println("Already up-to-date.");
			return;
		}
		if (split.size() == 1) {
			headPointer = branches.get(branchname);
			branches.put(currentBranch, headPointer);
			return;
		}
		CommitNode splitHead = split.pop();
		CommitNode copy = split.pop();
		headPointer = branches.get(branchname);
		while (copy != null) {
			Set<String> base = copy.getFile().keySet();
			HashMap<String, File> rtn = splitHead.getFile();
			for (String f : base) {
				if (rtn.containsKey(f) != true) {
					rtn.put(f, copy.getFile().get(f));
				} else if (compare(rtn.get(f), copy.getFile().get(f))
						&& !compare(rtn.get(f), branches.get(branchname)
								.getFile().get(f))) {
					rtn.put(f, branches.get(branchname).getFile().get(f));
				} else {
					rtn.put(f, copy.getFile().get(f));
				}
			}
			headPointer = new CommitNode(idGenerator, copy.getMessage(),
					headPointer, rtn, null);
			globalLog.put(idGenerator, headPointer);
			idGenerator++;
			if (split.size() != 0) {
				copy = split.pop();
			} else {
				copy = null;
			}
		}
		branches.put(currentBranch, headPointer);
	}

	/***
	 * Helper method to search for the split point between given branch and
	 * current branch, and return all the commitnodes of given branch til split
	 * point, in order of the head of given branch until commitnode before
	 * splitpoint.
	 * 
	 * Precondition: assuming given branch is exist.
	 * 
	 * @param branch String of branch to search
	 * @return ArrayList<CommitNodes> all node before split point or given
	 *         branch, or null if never find
	 */
	private Stack<CommitNode> SplitPoint(String branch, String select) {
		CommitNode curr = headPointer; // Current Branch commitnode
		CommitNode given = branches.get(branch);
		Stack<CommitNode> giv = new Stack<CommitNode>();
		Stack<CommitNode> cur = new Stack<CommitNode>();
		while (curr != null) {
			cur.add(curr);
			curr = curr.getNext();
		}
		while (given != null) {
			giv.add(given);
			given = given.getNext();
		}
		while (!cur.isEmpty() && !giv.isEmpty()) {
			if (cur.size() == 1 && select.equals("cur")) {
				return cur;
			} else if (giv.size() == 1 && select.equals("cur")) {
				return null;
			}
			if (giv.size() == 1 && select.equals("giv")) {
				return giv;
			} else if (cur.size() == 1 && select.equals("giv")) {
				return null;
			}
			if (cur.elementAt(cur.size() - 2).getId() != giv.elementAt(
					giv.size() - 2).getId()) {
				if (select.equals("cur")) {
					return cur;
				}
				if (select.equals("giv")) {
					return giv;
				}
			}
			cur.pop();
			giv.pop();
		}
		return null;
	}

	/***
	 * Compare two files if they are the same.
	 * 
	 * @param f1 File one
	 * @param f2 File two
	 * @return true if two file are identical with the content and size
	 */
	private boolean compare(File f1, File f2) {
		try {
			Path p1 = f1.toPath();
			Path p2 = f2.toPath();
			byte[] b1 = Files.readAllBytes(p1);
			byte[] b2 = Files.readAllBytes(p2);
			if (b1.length != b2.length) {
				return false;
			}
			for (int i = 0; i < b1.length; i++) {
				if (b1[i] != b2[i]) {
					return false;
				}
			}
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	/***
	 * Take filename and changing its name in the working directory to
	 * .conflicted, assume the file is already copy to working directory.
	 * 
	 * @param filename String filename (including subdirectory)
	 */
	private void renameConflict(String filename) {
		File file = new File(Directory + File.separator + filename);
		File newFile = new File(Directory + File.separator + filename
				+ ".conflicted");
		if (file.exists()) {
			file.renameTo(newFile);
		}
	}

	/**
	 * Taking a command and call appropriate method.
	 * 
	 * @param arg String, getting from Gitlet
	 */
	public void command(String... arg) {
		if (arg.length < 1) {
			System.out.println("Please enter a command.");
		} else if (arg[0].equals("init")) {
			init();
		} else if (idGenerator != 0) {
			String command = arg[0];
			switch (command) {
			case "reset":
				reset(arg[1]);
				break;
			case "merge":
				merge(arg[1]);
				break;
			case "rebase":
				rebase(arg[1]);
				break;
			case "checkout":
				if (arg.length >= 3)
					checkout(arg[1], arg[2]);
				else
					checkout(arg[1]);
				break;
			case "add":
				add(arg[1]);
				break;
			case "branch":
				branch(arg[1]);
				break;
			case "commit":
				if (arg.length <= 1 || arg[1].equals(" "))
					System.out.println("Please enter a commit message.");
				else
					commit(arg[1]);
				break;
			case "status":
				status();
				break;
			case "find":
				find(arg[1]);
				break;
			case "rm-branch":
				rmbranch(arg[1]);
				break;
			case "log":
				log();
				break;
			case "global-log":
				globallog();
				break;
			case "rm":
				rm(arg[1]);
				break;
			default:
				System.out.println("No command with that name exists.");
			}
		}
	}
}
