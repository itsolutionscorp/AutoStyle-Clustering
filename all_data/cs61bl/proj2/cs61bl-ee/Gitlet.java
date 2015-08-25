import java.io.*;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.nio.*;
//fix tracked and untracked
//fix file name so that Gitlet names them not the parent
//make hashmap that takes in id and commit object during commit for reset

/**
 * Gitlet
 * 
 * Everything in here to use git
 */
public class Gitlet implements Serializable {
	private HashSet<String> untrackedFiles = new HashSet<String>();
	private HashMap<String, Commit> branches = new HashMap<String, Commit>();
	private Set<String> stagedFiles = new HashSet<String>();
	private HashMap<String, Commit> resetCommits;
	private String current;
	private HashMap<String, ArrayList<Commit>> IDchecker;
	private int ID;
	private boolean isConflicted;
	private static final String gitDir = ".gitlet/";

	/**
	 * Serialized the Gitlet object provided
	 * 
	 * @param g
	 *            a Gitlet object used to be serialized
	 */
	public static void serializer(Gitlet g) {
		try {
			FileOutputStream fileIn = new FileOutputStream(gitDir
					+ "gitlet.ser");
			ObjectOutputStream objectIn = new ObjectOutputStream(fileIn);
			objectIn.writeObject(g);
		} catch (IOException e) {
			String msg = "IOException while serializing Gitlet.";
			System.out.println(msg);
		}
	}

	/**
	 * Main method of Gitlet, handling all commands
	 * 
	 * @param args
	 *            a string used to decide which command is called
	 */
	public static void main(String[] args) {

		if (args.length < 1) {
			System.out.println("Please enter a command.");
			return;
		}

		String command = args[0];
		if (command.equals("init")) {
			if (isGitletExist()) {
				System.out
						.println("A gitlet version control system already exists "
								+ "in the current directory.");
				return;

			}
			Gitlet git = new Gitlet();
			serializer(git);
			return;
		}

		Gitlet git = null;
		try {
			FileInputStream fileIn = new FileInputStream(gitDir + "gitlet.ser");
			@SuppressWarnings("resource")
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			git = (Gitlet) objectIn.readObject();
		} catch (IOException e) {
			// If not exist, need init first so that return;
			System.out.println("Please init first");
			return;
		} catch (ClassNotFoundException e) {
			System.out.println("Exception");
		}

		switch (command) {
		case "add":
			git.add(args[1]);
			break;
		case "commit":
			if (args.length < 2) {
				System.out.println("Please enter a commit message.");
				break;
			}
			git.commit(args[1]);
			break;
		case "rm":
			if (git.isConflicted) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			git.remove(args[1]);
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
		case "checkout":
			if (args.length == 2) {
				git.checkout(args[1]);
			} else {
				git.checkout(args[1], args[2]);
			}
			break;
		case "branch":
			if (git.isConflicted) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			git.branch(args[1]);
			break;
		case "rm-branch":
			if (git.isConflicted) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			git.rmbranch(args[1]);
			break;
		case "reset":
			if (git.isConflicted) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			git.reset(args[1]);
			break;
		case "merge":
			if (git.isConflicted) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			git.merge(args[1]);
			break;
		case "rebase":
			if (git.isConflicted) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			git.rebase(args[1]);
			break;

		default:
			System.out.println("No command with that name exists.");
			return;
		}

		serializer(git);
	}

	/**
	 * Constructor for Gitlet
	 */
	public Gitlet() {
		File gitFolder = new File(gitDir + "branches/");
		File stagedFolder = new File(gitDir + "staged/");
		gitFolder.mkdirs();
		stagedFolder.mkdirs();
		File commitFolder = new File(gitDir + "commits/");
		commitFolder.mkdirs();
		Commit firstCom = new Commit("initial commit");
		branches.put("master", firstCom);
		current = "master";
		ID = 1;
		ArrayList<Commit> newArrayList = new ArrayList<Commit>();
		newArrayList.add(firstCom);
		this.IDchecker = new HashMap<String, ArrayList<Commit>>();
		IDchecker.put("initial commit", newArrayList);
		resetCommits = new HashMap<String, Commit>();
		resetCommits.put("0", firstCom);
		isConflicted = false;
	}

	/**
	 * To check if the certain folder has been implemented Gitlet system before
	 *
	 * @return true if the folder has been initialized before
	 */
	private static boolean isGitletExist() {
		File gitFolder = new File(gitDir + "branches/");
		if (gitFolder.exists()) {
			return true;
		}
		return false;
	}

	/**
	 * Add the file into staging area, or staged folder
	 * 
	 * @param fileName
	 *            a string used for the file to be added
	 */
	public void add(String fileName) {
		File stagedFile = new File(fileName);
		if (this.untrackedFiles != null
				&& this.untrackedFiles.contains(fileName)) {
			this.untrackedFiles.remove(fileName);
			return;
		}
		if (!stagedFile.exists()) {
			System.out.println("File does not exist.");
			return;
		}
		stagedFiles.add(fileName);
		File source = new File(fileName);
		File dest = new File(gitDir + "staged/" + fileName);
		if (!dest.exists()) {
			dest.mkdirs();
		}
		try {
			// stackoverflow.com/questions/17169541/copy-file-in-java-and-replace-existing-target
			Files.copy(source.toPath(), dest.toPath(),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// http://stackoverflow.com/questions/5930087/how-to-check-if-a-directory-is-empty-in-java
	/**
	 * Create a new commit with the given message, tracked the file in the
	 * staging area
	 * 
	 * @param msg
	 *            a string used to quote for this commit
	 */
	public void commit(String msg) {
		if (msg.equals(null) || msg.equals("")) {
			System.out.println("Please enter a commit message.");
			return;
		}

		if (stagedFiles.isEmpty() && this.untrackedFiles.isEmpty()) {
			System.out.println("No changes added to the commit.");
			return;
		}

		Commit newCom = new Commit(msg, branches.get(current), ID);
		for (String a : branches.get(current).getTrack().keySet()) {
			if (!stagedFiles.contains(a) && !untrackedFiles.contains(a)) {
				newCom.add(a, branches.get(current).getTrack().get(a));
			}
		}
		branches.put(current, newCom);

		ArrayList<Commit> bucket = new ArrayList<Commit>();
		if (IDchecker.containsKey(msg)) {
			bucket = IDchecker.get(msg);
		}
		bucket.add(newCom);
		IDchecker.put(msg, bucket);

		for (String a : stagedFiles) {
			try {
				File pathto = new File(gitDir + "commits/" + newCom.getID()
						+ "/" + a);
				if (!pathto.exists()) {
					pathto.mkdirs();
				}
				Files.move(
						Paths.get(gitDir + "staged/" + a),
						Paths.get(gitDir + "commits/" + newCom.getID() + "/"
								+ a), StandardCopyOption.REPLACE_EXISTING);
				newCom.add(a, gitDir + "commits/" + newCom.getID() + "/" + a);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
		resetCommits.put("" + newCom.getID(), newCom);
		ID = ID + 1;
		stagedFiles = new HashSet<String>();
		untrackedFiles = new HashSet<String>();
		isConflicted = false;
	}

	/**
	 * removes file in stage. if not tries to untrack it. if not errors
	 *
	 * @param fileName
	 *            a String represents the file's name to be removed
	 */
	public void remove(String fileName) {
		File f = new File(gitDir + "staged/" + fileName);
		if (!f.exists()) {
			if (branches.get(current).getTrack() != null
					&& branches.get(current).getTrack().containsKey(fileName)) {
				this.untrackedFiles.add(fileName);
				return;
			} else {
				System.out.println("No reason to remove the file.");
				return;
			}
		}
		delete(f.getPath().toString());
		stagedFiles.remove(fileName);
	}

	/**
	 * Print out all commits have ever made within current branch with the
	 * information of commit id, date, and message
	 */
	public void log() {
		branches.get(current).logger();
		return;
	}

	/**
	 * Print out all commits have ever made within all branches with the
	 * information of commit id, date, and message
	 */
	public void globalLog() {
		for (ArrayList<Commit> a : IDchecker.values()) {
			for (Commit c : a) {
				c.globalLogger();
			}
		}
	}

	/**
	 * prints id of commit if multiple, separate lines
	 *
	 * @param msg
	 *            a String of commit massage
	 */
	public void find(String msg) {
		if (IDchecker.containsKey(msg)) {
			for (Commit c : IDchecker.get(msg)) {
				System.out.println(c.getID());
			}
		} else {
			System.out.println("Found no commit with that message.");
		}
	}

	/**
	 * Print out the current status with the information of branches, staged
	 * files, and untracked files
	 */
	public void status() {
		System.out.println("=== Branches ===");
		System.out.println("*" + current);
		for (String a : branches.keySet()) {
			if (!a.equals(current)) {
				System.out.println(a);
			}
		}
		System.out.println("");
		System.out.println("=== Staged Files ===");
		for (String a : stagedFiles) {
			System.out.println(a);
		}
		System.out.println("");
		System.out.println("=== Files Marked for Untracking ===");
		for (String a : untrackedFiles) {
			System.out.println(a);
		}
		System.out.println("");
	}

	/**
	 * Check out to the header commit of branch if the given name is branch's
	 * name, or checkout to the header commit of the current branch if the given
	 * name is file's name
	 * 
	 * @param name
	 *            a String can be used to represent branch's name or file's name
	 */
	public void checkout(String name) {
		if (current.equals(name)) {
			if (isConflicted) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				return;
			}
			System.out.println("No need to checkout the current branch.");
			return;
		}
		if (branches.containsKey(name)) {
			if (isConflicted) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				return;
			}
			for (String a : branches.get(name).getTrack().keySet()) {
				File source = new File(branches.get(name).getTrack().get(a));
				try {
					// stackoverflow.com/questions/17169541/copy-file-in-java-and-replace-existing-target
					Files.copy(source.toPath(), (new File(a)).toPath(),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			current = name;
			return;
		}

		// Handle if given is a file name
		if (!branches.get(current).getTrack().containsKey(name)) {
			System.out.println("File does not exist in the most recent commit,"
					+ "or no such branch exists.");
			return;
		}

		File source = new File(branches.get(current).getTrack().get(name));
		try {
			// stackoverflow.com/questions/17169541/copy-file-in-java-and-replace-existing-target
			Files.copy(source.toPath(), (new File(name)).toPath(),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checkout to the commit with the given id and the file name.
	 * 
	 * @param id
	 *            a string used for commit id
	 * @param name
	 *            a string used for file name
	 */
	public void checkout(String id, String name) {
		if (!resetCommits.containsKey(id)) {
			System.out.println("No commit with that id exists.");
			return;
		}
		if (!resetCommits.get(id).getTrack().containsKey(name)) {
			System.out.println("File does not exist in that commit.");
			return;
		}
		System.out.println(resetCommits.get(id).getTrack().get(name));
		File source = new File(resetCommits.get(id).getTrack().get(name));
		try {
			// stackoverflow.com/questions/17169541/copy-file-in-java-and-replace-existing-target
			Files.copy(source.toPath(), (new File(name)).toPath(),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create a new branch with the given branch name
	 * 
	 * @param branchName
	 *            a String used for the name of a new branch
	 */
	public void branch(String branchName) {
		if (branches.containsKey(branchName)) {
			System.out.println(" A branch with that name already exists.");

		} else {
			branches.put(branchName, branches.get(current));
		}
	}

	/**
	 * Remove the branch with the given name
	 * 
	 * @param branchName
	 *            a string used to remove the branch with the given name
	 */
	public void rmbranch(String branchName) {
		if (branches.containsKey(branchName)) {
			if (current.equals(branchName)) {
				System.out.println("Cannot remove the current branch.");
				return;
			}
			branches.remove(branchName);
			return;
		}
		System.out.println("A branch with that name does not exist.");
	}

	/**
	 * Reset
	 * 
	 * 
	 * 
	 * Checks out all the files tracked by the given commit. Also moves the
	 * current branch's head to that commit node.
	 * 
	 * 
	 * @param commitID
	 */

	public void reset(String commitID) {
		if (!resetCommits.containsKey(commitID)) {
			System.out.println("No commit with that id exists.");
			return;
		}
		for (String a : resetCommits.get(commitID).getTrack().keySet()) {
			File source = new File(branches.get(current).getTrack().get(a));
			try {
				// stackoverflow.com/questions/17169541/copy-file-in-java-and-replace-existing-target
				Files.copy(source.toPath(), (new File("")).toPath(),
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		branches.put(current, resetCommits.get(commitID));
	}

	/**
	 * Rebase
	 * 
	 * Rebase finds the split point of the current branch and the given branch,
	 * then snaps off the current branch at this point, then reattaches the
	 * current branch to the head of the given branch
	 * 
	 * 
	 * 
	 * 
	 * @param branchName
	 */

	public void rebase(String branchName) {
		if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (current.equals(branchName)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}
		if (SplitPoint(branches.get(current), branches.get(branchName)).equals(
				branches.get(branchName))) {
			System.out.println("Already up-to-date.");
			branches.put(current, branches.get(branchName));
			return;
		}
		Commit split = SplitPoint(branches.get(current),
				branches.get(branchName));
		ArrayList<Commit> toAdd = new ArrayList<Commit>();
		for (Commit a = branches.get(current); !a.equals(split); a = a
				.getParent()) {
			toAdd.add(0, a);
		}
		Commit headPointer = branches.get(branchName);
		for (Commit a : toAdd) {
			headPointer = rebaser(a, headPointer);
			ArrayList<Commit> bucket = new ArrayList<Commit>();
			if (IDchecker.containsKey(headPointer.message())) {
				bucket = IDchecker.get(headPointer.message());
			}
			bucket.add(headPointer);
			IDchecker.put(headPointer.message(), bucket);
			resetCommits.put("" + headPointer.getID(), headPointer);
			for (String b : headPointer.getTrack().keySet()) {
				if (split.getTrack().containsKey(b)) {
					if (split.getTrack().get(b)
							.equals(headPointer.getTrack().get(b))) {
						// <<<<<<< HEAD
						// headPointer.add(b, headPointer.getParent().getTrack()
						// .get(b));
						// =======
						if (headPointer.getParent().getTrack().containsKey(b)) {
							headPointer.add(b, headPointer.getParent()
									.getTrack().get(b));
						} else {
							headPointer.rm(b);
						}
					}
				}
			}
			branches.put(current, headPointer);
		}
	}
	/**
	 * Helper to find the SplitPoint for rebase
	 * @param current
	 * @param other
	 * @return
	 */
	private Commit SplitPoint(Commit current, Commit other) {
		if (current.getID().equals(other.getID())) {
			return current;
		} else if (current.getIDnum() > other.getIDnum()) {
			return SplitPoint(current.getParent(), other);
		} else {
			return SplitPoint(current, other.getParent());
		}
	}
	/**
	 * Helper for rebase
	 * @param a
	 * @param parent
	 * @return
	 */
	private Commit rebaser(Commit a, Commit parent) {
		Commit copiedCom = new Commit(a.message(), parent, ID);
		copiedCom.setTracked(a.getTrack());
		ID++;
		return copiedCom;

	}

	/**
	 * Merge the given branch and the current branch, and if the branches
	 * conflicted, it will generate a .confilcted file.
	 * 
	 * @param givenBranch
	 *            a string that represent the name of the given branch
	 */
	public void merge(String givenBranch) {
		if (!branches.containsKey(givenBranch)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (givenBranch.equals(current)) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}

		Commit curBranchHead = branches.get(current);
		Commit givenBranchHead = branches.get(givenBranch);
		Commit splitHead = getCommonParent(curBranchHead, givenBranchHead);

		Set<String> pTrackedFiles = splitHead.getTrack().keySet();
		Set<String> gTrackedFiles = givenBranchHead.getTrack().keySet();
		Set<String> cTrackedFiles = curBranchHead.getTrack().keySet();

		Map<String, Long> pLastModified = splitHead.getLastModified();
		Map<String, Long> gLastModified = givenBranchHead.getLastModified();
		Map<String, Long> cLastModified = curBranchHead.getLastModified();

		for (String gFileName : gTrackedFiles) {
			File gFile = getFile(givenBranchHead, gFileName);

			if (!cTrackedFiles.contains(gFileName)) {
				if (pTrackedFiles.contains(gFileName)
						&& pLastModified.get(gFileName).equals(
								gFile.lastModified())) {
					continue;
				}

				File src = gFile;
				File dst = new File(gFileName);
				try {
					GitUtil.copyFile(src, dst);
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}

				add(gFileName);
			} else {
				File cFile = new File(gFileName);
				if (pTrackedFiles.contains(gFileName)) {
					Long pl = pLastModified.get(gFileName);
					Long gl = gLastModified.get(gFileName);
					Long cl = cLastModified.get(gFileName);
					if (!gl.equals(pl) && cl.equals(pl)) {
						try {
							GitUtil.copyFile(gFile, cFile);
							add(gFileName);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else if (!pl.equals(gl) && !pl.equals(cl)) {
						File conflictedFile = new File(gFileName
								+ ".conflicted");
						try {
							GitUtil.copyFile(gFile, conflictedFile);
						} catch (IOException e) {
							e.printStackTrace();
						}
						isConflicted = true;
					}
				} else {
					File conflictedFile = new File(gFileName + ".conflicted");
					try {
						GitUtil.copyFile(gFile, conflictedFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
					isConflicted = true;
				}
			}
		}

		if (!isConflicted) {
			commit("Merged " + current + " with " + givenBranch + ".");
		} else {
			System.out.println("Encountered a merge conflict.");
		}
	}

	/* ======================= Utils =================== */
	/**
	 * This helper function is to check if the file has been modified
	 * 
	 * @param src
	 *            a string that represents the name of source file
	 * @param dst
	 *            a string that represents the name of destination file
	 * @return true if the file has been modified before
	 */
	public boolean isModified(String src, String dst) {
		File srcFile = new File(src);
		File dstFile = new File(dst);

		return srcFile.lastModified() == dstFile.lastModified();
	}

	/**
	 * Get the tracked file from the specific Commit
	 * 
	 * @param c
	 *            a Commit that will be used for getting its ID
	 * @param fileName
	 *            a String that represents the name of file
	 * @return File if the file exists
	 */
	public File getFile(Commit c, String fileName) {
		while (true) {
			File tmp = new File(gitDir + "commits/" + c.getID() + "/"
					+ fileName);
			if (tmp.exists()) {
				return tmp;
			}
			c = c.getParent();
		}
	}

	/**
	 * Get the split point for the branches
	 * 
	 * @param c1
	 *            a Commit in one branch
	 * @param c2
	 *            a Commit in another branch
	 * @return a Commit if it is the split point
	 */
	public Commit getCommonParent(Commit c1, Commit c2) {
		while (!c1.getTimeInMillis().equals(c2.getTimeInMillis())) {
			if (c1.getTimeInMillis() > c2.getTimeInMillis()) {
				c1 = c1.getParent();
			} else {
				c2 = c2.getParent();
			}
		}
		return c1;
	}

	/**
	 * Check if file has been modified
	 * 
	 * @param src
	 *            a File represents the source file
	 * @param dst
	 *            a File represents the destination file
	 * @return true if the file is modified
	 */
	public boolean isModified(File src, File dst) {
		return src.lastModified() != dst.lastModified();
	}

	/**
	 * Delete the file with the given path
	 * 
	 * @param path
	 *            a string used for delete the file with this path
	 */
	public static void delete(String path) {
		File d = new File(path);
		if (d.exists()) {
			d = getRoot(d);
			recursiveDelete(d);
		}
	}

	/**
	 * Get the folder for the given file if there is any under staged folder
	 * 
	 * @param d
	 *            a File used to find which folder it is in under the staged
	 *            folder
	 * 
	 * @return File the folder the given file locates if there is any
	 */
	public static File getRoot(File d) {
		if ((new File(d.getParent())).getName().equals("staged")) {
			return d;
		}
		return getRoot(new File(d.getParent()));
	}

	/**
	 * Delete the files in the folder
	 * 
	 * @param d
	 *            a File to be deleted
	 */
	public static void recursiveDelete(File d) {
		if (d.isDirectory()) {
			for (File f : d.listFiles()) {
				recursiveDelete(f);
			}
		}
		d.delete();
	}
}
