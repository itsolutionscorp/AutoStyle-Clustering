import java.util.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Gitlet {
	
	/**
	 * Implements a light-weight version control system
	 * 
	 * @author Andrew Hild (AH)
	 * @author Arshia Malkani (AX)
	 * @author Katie Jiang (BI)
	 * @author Nathaniel Low (AG)
	 */
	

	// ArrayList of all Commits 
	static ArrayList<Commit> commitTree;
	// ArrayList of files that have been marked for untracking
	static ArrayList<File> untracking;
	//ArrayList of files that are in the staging area
	static ArrayList<File> stagingArea = new ArrayList<File>();
	// the head Commit
	static Commit head;
	// String name of the current branch
	static String currentBranch;
	// HashMap containing the names of branches as keys and an array of Commits as values
	// The first element of the array is the splitpoint Commit of the branch
	// The second element of the array is the head Commit of the branch
	static HashMap<String, Commit[]> branches = new HashMap<String, Commit[]>();
	// Boolean indicating whether Gitlet is in a conflicted state
	static Boolean conflicted = false;

	/**
	 * Initializes an empty .gitlet folder in the current directory
	 */
	static void init() {
		File f = new File(".gitlet");
		if (f.exists()) {
			System.out.println("A gitlet version control system already exists in the current directory.");
		} else {
			commitTree = new ArrayList<Commit>();
			head = new Commit();
			commitTree.add(head);
			try {
				Path toCommit = Paths.get(".gitlet/" + head.getID() + "/");
				Files.createDirectories(toCommit);
				Path toStaging = Paths.get(".gitlet/staging/");
				Files.createDirectory(toStaging);
			} catch (IOException e) {
			}
			branches = new HashMap<String, Commit[]>();
			branch("master");
			currentBranch = "master";
			untracking = new ArrayList<File>();
			File gitlet = new File(".gitlet");
			gitlet.mkdir();
		}
	}

	/**
	 * Adds a file to the staging area.
	 * 
	 * @param filename
	 *            indicates the name of the file to add to the staging area Adds
	 *            the specified file to the staging area in preparation for
	 *            commit. If the file does not exist, the user is notified.
	 */
	static void add(String filename) {
		File f = new File(filename);
		if (f.exists()) {
			if (untracking.contains(f)) {
				untracking.remove(f);
				return;
			}
			if (!stagingArea.contains(f)) {
				try {
					Gitlet.stagingArea.add(f);
					// make correct directory in staging area
					makeFolders(filename, ".gitlet/staging/");
					Files.copy(Paths.get(filename),Paths.get(".gitlet/staging/" + filename));
				} catch (IOException e) {
				}
			}
		} else
			System.out.println("File does not exist.");
	}

	/**
	 * Commits a snapshot of the current working directory's status
	 * 
	 * @param msg
	 *            specifies the method with which to create the commit
	 */
	static void commit(String msg) {
		// Check to see if files have been added to the commit or if any files
		// have been marked for untracking. If not, fail.
		if (stagingArea.size() > 0 || untracking.size() > 0) {
			// Proceed with commit
			try {
				// Make a new commit and update Gitlet's head commit and the
				// active branch's head commit to this new commit.
				Commit c = new Commit(head, msg, commitTree.size());
				head = c;
				commitTree.add(head);
				// Tell this commit to track any files newly added
				for (File f : stagingArea) {
					if (!c.getTracked().contains(f)) {
						c.add(f);
					}
					// Add this commit to c's lastModified
					c.addLastModified(f);
				}
				// Tell this commit to untrack any files marked for untracking
				c.clearUntracked();
				for (File f : untracking) {
					c.untrack(f);
					if (!c.getTracked().contains(f)) {
						c.getTracked().remove(f);
					}
				}
				branches.put(currentBranch,new Commit[] { branches.get(currentBranch)[0], c });
				// Copy all files in staging area to new folder in .gitlet with
				// this commit's ID
				String toCommit = ".gitlet/" + c.getID() + "/";
				String toStaging = ".gitlet/staging/";
				Files.createDirectory(Paths.get(toCommit));
				for (File f : stagingArea) {
					makeFolders(f.getPath(), toCommit + "/");
					Files.move(Paths.get(toStaging + f.getPath()),
							Paths.get(toCommit + f.getPath()));
					String p = toStaging + f.getPath();
					File toDelete = new File(p);
					toDelete.delete();
				}
				conflicted = false;
				// Clear the staging area
				Gitlet.stagingArea.clear();
				// Clears untracking mark from untracked files
				for (File f : untracking) {
					if(head.getLastModified().containsKey(f.getPath()))
						head.addLastModified(f);
				}
				untracking.clear();
			} catch (IOException e) {
			}
		} else
			System.out.println("No changes added to the commit.");
	}
	
	/**
	 * Mark the file for untracking; this means it will not be included in the
	 * upcoming commit, even if it was tracked by that commit's parent. If the
	 * file had been staged, instead just unstage it, and don't also mark it for
	 * untracking.
	 * 
	 * @param file 
	 * 			specifies the file to be marked for untracking
	 */
	static void rm(File file) {
		if (!file.exists()) {
			System.out.println("No reason to remove the file.");
			return;
		}
		if (stagingArea.contains(file)) {
			stagingArea.remove(file);
			File f = new File(".gitlet/staging/" + file.getPath());
			f.delete();
		} else if (!untracking.contains(file) && head.getFilesList().contains(file)) {
			untracking.add(file);
		} else {
			System.out.println("No reason to remove the file.");
		}
	}
	
	/**
	 * Displays a printed representation of the 
	 * commits in the history of the current branch.
	 */
	static void log() {
		Commit c = head;
		if (c != null) {
			while (c.getParent() != null) {
				System.out.println(c);
				c = c.getParent();
				System.out.println();
			}
			System.out.println(c);
		}
	}

	/**
	 * Displays a printed representation of all 
	 * the commits in the history of the project.
	 */
	static void globalLog() {
		if (!commitTree.isEmpty()) {
			System.out.println(commitTree.get(0).toString());
			for (int i = 1; i < commitTree.size(); i++) {
				System.out.println();
				System.out.println(commitTree.get(i).toString());
			}
		}
	}
	
	/**
	 * Prints out the id of the commit that has the given commit message. If
	 * there are multiple such commits, it prints the ids out on separate lines.
	 * 
	 * @param message 
	 * 				indicates the desired commit comment
	 */
	static void find(String message) {
		boolean isEmpty = true;
		for (Commit com : commitTree) {
			if (com.getComment() != null && com.getComment().equals(message)) {
				System.out.println(com.getID());
				isEmpty = false;
			}
		}
		if (isEmpty)
			System.out.println("Found no commit with that message.");
	}
	
	/**
	 * Displays what branches currently exist, and marks the current branch with
	 * a *. Also displays what files have been staged or marked for untracking.
	 * An example of the exact format it should follow is as follows.
	 */
	static void status() {
		System.out.println("=== Branches ===");
		for (String key : branches.keySet()) {
			if (key.equals(currentBranch)) {
				System.out.print("*");
			}
			System.out.println(key);
		}
		System.out.println("\n=== Staged Files ===");
		for (File file : stagingArea) {
			System.out.println(file.toString());
		}
		System.out.println("\n=== Files Marked for Untracking ===");
		for (File file : untracking) {
			System.out.println(file.toString());
		}
	}
	
	/**
	 * Takes all files in the commit at the head of
	 * the given branch, and puts them in the working directory,
	 * overwriting the versions of the files that are already there if
	 * they exist. Also, at the end of this command, the given
	 * branch will now be considered the current branch. 
	 * 
	 * @param branchName 
	 * 					specifies the name of the branch to be checked out.
	 */
	static void checkout(String branchName) {
		if (conflicted) {
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		if (!currentBranch.equals(branchName)) {
			currentBranch = branchName;
			Commit incoming = branches.get(branchName)[1];
			head = incoming;
			for (File f : incoming.getTracked()) {
				if (!untracking.contains(f)) {
					checkout(incoming.getID(), f.getPath());
				}
			}
		} else {
			System.out.println("No need to checkout the current branch.");
		}
	}

	/**
	 * Takes the version of the file as it exists in the
	 * commit with the given id, and puts it in the working directory,
	 * overwriting the version of the file that's already there if there is one.
	 * Runtime: Should be linear relative to the size of the file being checked
	 * out.
	 * 
	 * @param filename 
	 * 				specifies the path of the file to be checked out
	 * @param ID 
	 * 			specifies the commit ID from which to checkout. If no 
	 * 			ID is specified, Gitlet will input the ID of the current head.
	 */
	static void checkout(int myID, String filename) {
		try {
			if (untracking.contains(new File(filename))) {
				System.out.println("File does not exist in that commit.");
				return;
			}
			Commit incoming = commitTree.get(myID);
			int fileID = incoming.getLastModified().get(filename);

			makeFolders(filename, "");
			Files.copy(Paths.get(".gitlet/" + fileID + "/" + filename), Paths.get(filename), StandardCopyOption.REPLACE_EXISTING);
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("No commit with that id exists.");
		} catch (NullPointerException npe) {
			System.out.println("File does not exist in that commit.");
		} catch (IOException ioe) {
			System.out.println("File does not exist in that commit.");
		}
	}
	
	/**
	 * Creates a new branch with the specified name unless it already exists.
	 * 
	 * @param name 
	 * 			specifies the name of the branch to create
	 */
	static void branch(String name) {
		if (branches.containsKey(name)) {
			System.out.println("A branch with that name already exists.");
		} else {
			branches.put(name, new Commit[] { head, head });
		}
	}
	
	/**
	 * Removes the specified branch if it exists.
	 * 
	 * @param name 
	 * 			specifies the branch to remove.
	 */
	static void rmBranch(String name) {
		if (currentBranch.equals(name)) {
			System.out.println("Cannot remove the current branch.");
		} else if (branches.containsKey(name)) {
			branches.remove(name);
		} else {
			System.out.println("A branch with that name does not exist.");
		}
	}
	
	/**
	 * Checks out the commit specified by ID and overwrites the files in the 
	 * current directory with that commit's version of those files.
	 * 
	 * @param ID 
	 * 			specifies the ID of the commit to which to reset
	 */
	static void reset(int ID) {
		try {
			Commit target = commitTree.get(ID);
			for (File f : target.getTracked()) {
				if (!target.getUntracked().contains(f)) {
					checkout(ID, f.getPath());
				}
			}
			head = target;
			branches.get(currentBranch)[1] = target;
		} catch (IndexOutOfBoundsException e) {
			System.out.println("No commit with that id exists.");
		}
	}
	
	/**
	 * Joins the commit histories of two branches by ensuring the most
	 * recent versions of all files are inserted in the working directory. In
	 * the case in which files have been modified in more than one branch, merge
	 * will generate conflicted files and restrict user interaction with Gitlet.
	 * 
	 * @param branch 
	 * 				specifies the branch to be merged into the current branch
	 */
	static void merge(String branch) {
		if (branch.equals(currentBranch)) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		} else if (branches.get(branch) == null) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else {
			// store commits of split point and incoming branch head
			Commit inHead = branches.get(branch)[1];
			Commit splitPoint = null;
			Commit a = inHead;
			Commit b = head;
			ArrayList<Integer> branchList = new ArrayList<Integer>();
			while (a != null) {
				branchList.add(a.getID());
				a = a.getParent();
			}
			while (splitPoint == null) {
				if (branchList.contains(new Integer(b.getID()))) {
					splitPoint = b;
				}
				b = b.getParent();
			}
			// For each file tracked by the head of the current branch, check to
			// see when the corresponding file was last modified in the incoming commit.
			for (File f : head.getTracked()) {
				if (inHead.getTracked().contains(f)) {
					// Fast-forward case
					if (head.getLastModified().get(f.getPath()) == splitPoint.getID()&& inHead.getLastModified().get(f.getPath()) > splitPoint.getID()) {
						checkout(inHead.getID(), f.getPath());
						add(f.getPath());
					}
					// Both branches modified since split
					else if (head.getLastModified().get(f.getPath()) > splitPoint.getID()&& inHead.getLastModified().get(f.getPath()) > splitPoint.getID()) {
						try {
							String filePath = f.getPath();
							Path toFile = Paths.get(".gitlet/"+ inHead.getLastModified().get(filePath)+ "/" + filePath);
							List<String> currentFile = Files.readAllLines(f.toPath(), Charset.defaultCharset());
							List<String> storedFile = Files.readAllLines(toFile, Charset.defaultCharset());
							Boolean same = true;
							if (currentFile.size() == storedFile.size()) {
								for (int q = 0; q < currentFile.size(); q++) {
									if (!currentFile.get(q).equals(storedFile.get(q))) {
										same = false;
										break;
									}
								}
							} else
								same = false;
							if (!same) {
								add(f.getPath());
								File conflictedFile = new File(filePath + ".conflicted");
								Files.copy(toFile, conflictedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
								conflicted = true;
							}
						} catch (IOException e) {
						}
					}
				}
			}
			// If new files have been added in the incoming branch (or if the
			// files have been untracked in the current branch), checkout those files 
			// and stage them.
			for (File f : inHead.getTracked()) {
				if (!head.getTracked().contains(f)) {
					checkout(inHead.getID(), f.getPath());
					add(f.getPath());
				}
			}
			// If no .conflicted files were generated, commit the contents of
			// the staging area.
			if (!conflicted) {
				commit("Merged " + currentBranch + " with " + branch + ".");
			} else
				System.out.println("Encountered a merge conflict.");
		}
	}
	
	/**
	 * Finds the common splitpoint of the current and given branches and moves
	 * the current branch from splitpoint to head onto the head of the given branch.
	 * 
	 * @param branchName 
	 * 					specifies the branch onto which to rebase
	 */
	static void rebase(String branchName) {
		if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (branchName.equals(currentBranch)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}
		Commit branchHead = branches.get(currentBranch)[1];
		Commit givenHead = branches.get(branchName)[1];
		Commit splitPoint = null;
		Commit a = givenHead;
		Commit b = branchHead;
		ArrayList<Integer> branchList = new ArrayList<Integer>();
		while (a != null) {
			branchList.add(a.getID());
			a = a.getParent();
		}
		while (splitPoint == null) {
			if (branchList.contains(new Integer(b.getID()))) {
				splitPoint = b;
			}
			b = b.getParent();
		}
		HashMap<String, Integer> newLastModified = givenHead.getLastModified();
		ArrayList<Commit> added = new ArrayList<>();
		for (Commit h = branchHead; h != null; h = h.getParent()) {
			if (givenHead.equals(h)) {
				System.out.println("Already up-to-date.");
				return;
			}
		}
		for (Commit h = givenHead; h != null; h = h.getParent()) {
			if (head.equals(h)) {
				head = branches.get(branchName)[1];
				branches.put(currentBranch, new Commit[] { branches.get(currentBranch)[0], head });
				return;
			}
		}
		for (Commit h = branchHead; h.getID() != splitPoint.getID(); h = h
				.getParent()) {
			Commit n = new Commit(commitTree.size(), h, newLastModified);
			commitTree.add(n);
			added.add(n);
		}
		for (int i = added.size() - 1; i >= 0; i--) {
			if (i == added.size() - 1) {
				added.get(i).setParent(givenHead);
			} else {
				added.get(i).setParent(added.get(i + 1));
			}
		}
		head = added.get(0);
		branches.put(currentBranch, new Commit[] { splitPoint, head });
		for (File f : splitPoint.getFilesList()) {
			if (givenHead.getLastModified().get(f.getPath()) > splitPoint.getLastModified().get(f.getPath())&& splitPoint.getLastModified().get(f.getPath()) == branchHead.getLastModified().get(f.getPath())) {
				checkout(givenHead.getID(), f.getPath());
			}
		}
	}

	/**
	 * Creates new folders from a path name in a specified parent directory
	 * 
	 * @param filename
	 *            path of a file with folders that may need to be created
	 * @param parentDirectory
	 *            path of the folder where these new folders should be made
	 */
	static void makeFolders(String filename, String parentDirectory) {
		if (filename.indexOf('/') == -1) {
			return;
		}
		String directoryName = filename.substring(0, filename.lastIndexOf('/'));
		filename = filename.substring(filename.lastIndexOf('/') + 1);
		Path directoryPath = Paths.get(parentDirectory + directoryName);
		try {
			Files.createDirectories(directoryPath);
		} catch (IOException e) {
		}
	}
	
	/**
	 * Accepts arguments to Gitlet and directs them to the appropriate method.
	 * 
	 * @param args 
	 * 			takes commands to Gitlet
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Please enter a command.");
			return;
		}
		// check whether command is valid
		String command = args[0].toLowerCase();
		// deserialize everything
		commitTree = Serialize.tryLoadingCommitTree();
		branches = Serialize.tryLoadingMyBranches();
		currentBranch = Serialize.tryLoadingMyCurrentBranch();
		head = Serialize.tryLoadingMyCommitHead();
		stagingArea = Serialize.tryLoadingStaging();
		untracking = Serialize.tryLoadingUntracked();
		conflicted = Serialize.tryLoadingConflicted();
		// handle commands
		switch (command) {
		case "init":
			init();
			break;
		case "add":
			if (args.length >= 2)
				add(args[1]);
			break;
		case "commit":
			try {
				commit(args[1]);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Please enter a commit message.");
			}
			break;
		case "rm":
			if (args.length>1)
				rm(new File(args[1]));
			break;
		case "log":
			log();
			break;
		case "global-log":
			globalLog();
			break;
		case "find":
			try {
				find(args[1]);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Found no commit with that message.");
			}
			break;
		case "status":
			status();
			break;
		case "checkout":
			if (args.length == 2) { // must be either file or branch checkout
				if (branches.containsKey(args[1])) { // there is a branch of this name
					checkout(args[1]);
				} else if (head.getFiles().containsKey(args[1])) {
					checkout(head.getID(), args[1]);
				} else {
					System.out.println("File does not exist in the most recent commit, or no such branch exists.");
				}
			} else { // must be a commit id & file checkout
				try {
					// will attempt to convert id string to integer
					int commitID = Integer.parseInt(args[1]); 
					checkout(commitID, args[2]);
				} catch (NumberFormatException e) {
					System.out.println("No commit with that id exists.");
				}
			}
			break;
		case "branch":
			if (conflicted) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			if (args[1] != null)
				branch(args[1]);
			break;
		case "rm-branch":
			if (conflicted)
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
			else if (args[1] != null)
				rmBranch(args[1]);
			break;
		case "reset":
			if (conflicted) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
				return;
			}
			try {
				reset(Integer.parseInt(args[1]));
			} catch (NumberFormatException e) {
				System.out.println("No commit with that id exists.");
			}
			catch(IndexOutOfBoundsException e){
			}
			break;
		case "merge":
			if (conflicted)
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
			else if (args.length > 1)
				merge(args[1]);
			break;
		case "rebase":
			if (conflicted)
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
			else {
				rebase(args[1]);
			}
			break;
		default:
			System.out.println("No command with that name exists.");
		}
		// Serialize everything
		Serialize.saveCommitTree(commitTree);
		Serialize.saveBranches(branches);
		Serialize.saveCurrentBranch(currentBranch);
		Serialize.saveCommitHead(head);
		Serialize.saveStaging(stagingArea);
		Serialize.saveUntracked(untracking);
		Serialize.saveConflicted(conflicted);
	}
}