import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.nio.file.Paths;

/**
 * - Main class that interpret user commands and acts upon them 
 * - Note: only one instance of gitlet should exist for every directory where it is initialized in
 * - Instance Variables: 
 * workDir - a String reference to the current working directory path 
 * stageDir - a String reference to the path of the stage 
 * stage - a File object that refers to the staging area inside of .gitlet 
 * conflicted - notes whether gitlet is in a conflicted state or not 
 * currBranch - a reference to the current Branch of the commit tree 
 * commits - a HashMap that maps the id-number of every commit to the corresponding commit object 
 * branches - a HashMap of the branches that currently exist on the commit tree 
 * staged - a HashSet of the files currently held on the stage 
 * marked - a HashSet of files that are marked for untracking 
 * nextID - an int that stores the ID for the next commit (to avoid duplicate id numbers) 
 * serialVersionUID - I don't know why we need this, but we do so don't touch it. I warned you.
 * 
 * @author Johnny Le, Kevin Luong, Sijing Xin, Jia Guo
 *
 */
public class Gitlet implements Serializable {

	private String workDir;
	private String stageDir;
	private Branch currBranch;
	private boolean conflicted;
	private HashMap<Integer, Commit> commits;
	private HashMap<String, Branch> branches;
	private HashMap<File, String> staged;
	private HashSet<File> marked;
	private int nextID;
	private static final long serialVersionUID = 1L;

	private Gitlet() {

		// Initializing all the instance variables
		workDir = System.getProperty("user.dir");
		stageDir = workDir + "/.gitlet/stage";
		FileOps.mkdir(new File(stageDir));
		nextID = 10000;
		conflicted = false;
		branches = new HashMap<String, Branch>();
		commits = new HashMap<Integer, Commit>();
		staged = new HashMap<File, String>();
		marked = new HashSet<File>();

		// Adding the initial commit and initial "master" branch
		Commit firstCommit = new Commit(nextID, "initial commit");
		commits.put(nextID, firstCommit);
		FileOps.mkdir(new File(workDir + "/.gitlet/" + nextID));
		nextID += 1;
		currBranch = new Branch("master", firstCommit);
		branches.put("master", currBranch);
	}

	/**
	 * - Initializes Gitlet in the current directory
	 * 
	 * @return null - if gitlet has already been initiated, also prints an error message 
	 *         new Gitlet object - if gitlet has successfully initiated
	 *         	in this directory
	 */
	public static Gitlet init() {
		File gitletDir = new File(System.getProperty("user.dir") + "/.gitlet");
		if (gitletDir.exists()) {
			System.out.println("A gitlet version control system already exists in the current directory.");
			return null;
		} else {
			FileOps.mkdir(gitletDir);
			return new Gitlet();
		}
	}

	/**
	 * - Adds the given file to the staging area if it is not marked for untracking 
	 * - If the file is marked for untracking, method unmarks the file 
	 * - If the file doesn't exist, method prints an error message
	 * 
	 * @param filename
	 *            - name of the file the user wants to add
	 */
	public void add(String filename) {
		File toAdd = new File(workDir + "/" + filename);
		if (toAdd.exists() == false)
			System.out.println("File does not exist.");
		else if (marked.contains(toAdd))
			marked.remove(toAdd);
		else {
			staged.put(toAdd, filename);
			FileOps.copy(toAdd, new File(stageDir + "/" + filename));
		}
	}

	/**
	 * - Either removes the file from the stage, or marks the file for untracking 
	 * - If neither of the above apply, returns an error message
	 * 
	 * @param filename
	 *            - name of the file that you wish to remove
	 */
	public void rm(String filename) {
		File stageTarget = new File(workDir + "/.gitlet/stage/" + filename);
		File target = new File(workDir + "/" + filename);
		if (staged.containsKey(target)) {
			staged.remove(target);
			stageTarget.delete();
		} else if (currBranch.commit().tracking().containsKey(filename))
			marked.add(target);
		else
			System.out.println("No reason to remove the file.");
	}

	/**
	 * - Adds the files from the stage into a new folder in the gitlet directory
	 * - Files marked for untracking will not be included from the parent commit
	 * - Do not include the files that are inherited from the parent commit into the newly created folder 
	 * - Clears the staging area and removes marks from files marked for untracking 
	 * - Current branch will point to the new commit, previous commit becomes the parent to the new commit. 
	 * - If there are no changes to commit, prints an error message
	 * 
	 * @param message
	 *            - message associated with the new commit
	 */
	public void commit(String message) {
		if (marked.isEmpty() && staged.isEmpty()) {
			System.out.println("No changes added to the commit.");
		} else {
			HashMap<String, File> toCommit = new HashMap<String, File>();
			HashMap<String, Integer> toLocate = new HashMap<String, Integer>();
			File newCommitDir = new File(workDir + "/.gitlet/" + nextID);
			FileOps.mkdir(newCommitDir);
			for (Map.Entry<File, String> entry : staged.entrySet()) {
				File file = entry.getKey();
				String filename = entry.getValue();
				if (filename == null)
					continue;
				if (currBranch.commit().tracking().containsKey(filename))
					marked.add(file);
				toCommit.put(filename, file);
				toLocate.put(filename, nextID);
				File toMove = new File(stageDir + "/" + filename);
				File destination = new File(newCommitDir.getPath() + "/"
						+ filename);
				FileOps.copy(toMove, destination);
				toMove.delete();
			}
			for (Map.Entry<String, File> entry : currBranch.commit().tracking()
					.entrySet()) {
				String filename = entry.getKey();
				File file = entry.getValue();
				if (file == null || marked.contains(file))
					continue;
				toCommit.put(filename, file);
				toLocate.put(filename, currBranch.commit().location(filename));
			}
			Commit newCommit = new Commit(currBranch.commit(), toCommit,
					toLocate, nextID, message);
			nextID += 1;
			commits.put(newCommit.idNumber(), newCommit);
			currBranch.setCommit(newCommit);
			if (conflicted == true)
				conflicted = false;
			staged = new HashMap<File, String>();
			marked = new HashSet<File>();
		}
	}

	/**
	 * - Creates a new branch with name of the given name. 
	 * - Will not switch to the newly created branch.
	 * 
	 * @param branchName
	 *            The name of the new branch that the user wants to create.
	 */
	public void branch(String branchName) {
		if (branches.containsKey(branchName)) {
			System.out.println("A branch with that name already exists.");
		} else {
			Branch newBranch = new Branch(branchName, currBranch.commit());
			branches.put(branchName, newBranch);
		}
	}

	/**
	 * - Deletes the branch with the given name. 
	 * - Note: only deletes the pointer associated with the branch. 
	 * - Note: doesn't delete commits that were created under the branch.
	 * 
	 * @param branchname
	 *            The name of the branch that we intend to delete.
	 */
	public void rmBranch(String branchName) {
		if (branches.containsKey(branchName) == false)
			System.out.println("A branch with that name does not exist.");
		else if (currBranch.name().equals(branchName))
			System.out.println("Cannot remove the current branch.");
		else
			branches.remove(branchName);
	}

	/**
	 * - Displays the information of each commit, starting from the current
	 * 	commit, going backwards along the commit tree until the initial commit. 
	 * - Includes the commit id, the time the commit was made, and the commit
	 *	 message.
	 */
	public void log() {
		Commit currCommit = currBranch.commit();
		while (currCommit != null) {
			System.out.println("===");
			System.out.println(currCommit.idNumber());
			System.out.println(currCommit.timestamp());
			System.out.println(currCommit.description());
			System.out.println();
			currCommit = currCommit.parent();
		}
	}

	/**
	 * - Displays information about all commits ever made. 
	 * - Includes the commit id, the time the commit was made, and the commit message.
	 */
	public void global_log() {
		for (Commit commit : commits.values()) {
			System.out.println("===");
			System.out.println("Commit " + commit.idNumber());
			System.out.println(commit.timestamp());
			System.out.println(commit.description());
			System.out.println();
		}
	}

	/**
	 * This method print out the id of the commit that has the given commit
	 * message. If there are multiple such commits, it prints the IDs out on
	 * separate lines.
	 * 
	 * @param msg
	 *            The commit message of the commit that we want to find.
	 */
	public void find(String msg) { // This function does not run in the proper
									// runtime, but to make it linear, we'd need
									// a new variable...
		boolean found = false;
		for (Map.Entry<Integer, Commit> c : commits.entrySet()) {
			if (msg.equals(c.getValue().description())) {
				found = true;
				System.out.println(c.getValue().idNumber());
			}
		}
		if (found == false) {
			System.out.println("Found no commit with that message.");
		}
	}

	/**
	 * - Displays what branches currently exist and marks the current branch
	 * 	with a *. 
	 * - Also displays what files have been staged or marked for
	 * 	untracking.
	 */
	public void status() {
		System.out.println("=== Branches ===");
		for (String branch : branches.keySet()) {
			if (branch.equals(currBranch.name()))
				System.out.println("*" + branch);
			else
				System.out.println(branch);
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		for (File file : staged.keySet())
			System.out.println(file.getName());
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (File f : marked)
			System.out.println(f.getName());
	}

	/**
	 * - Checks out all files tracked by the given commit. 
	 * - Also moves the current branch's head to that commit node.
	 * 
	 * @param idName
	 *            The commit ID of the commit that we want to reset to.
	 */
	public void reset(int idName) {
		if (commits.containsKey(idName) == false) {
			System.out.println("No commit with that id exists.");
		} else {
			currBranch.setCommit(commits.get(idName));
			for (String filename : currBranch.commit().tracking().keySet()) {
				checkout(idName, filename);
			}
		}
	}

	/**
	 * Interprets the user input and decides which checkout method to call.
	 * 
	 * @param args
	 *            the string array passed into the main from the command line,
	 *            given by the user
	 */
	public void checkout(String[] args) {
		if (args.length == 2) {
			checkout(args[1]);
		}
		if (args.length == 3) {
			checkout(Integer.parseInt(args[1]), args[2]);
		}
	}

	/**
	 * - Either checks out the file with the given filename from the most recent
	 * 	commit, or checks out an entire commit tracked by a branch given by the
	 * 	filename. 
	 * - When applicable, branch name takes priority over file name.
	 * 
	 * @param filename
	 *            A string representing the file/branch that the user wants to
	 *            checkout
	 */
	public void checkout(String filename) {
		if ((conflicted == true) && branches.containsKey(filename)) {
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
		} else if (currBranch.name().equals(filename)) {
			System.out.println("No need to checkout the current branch.");
		} else if (branches.containsKey(filename)) {
			currBranch = branches.get(filename);
			Commit toCheckout = currBranch.commit();
			for (String fileName : toCheckout.tracking().keySet()) {
				File toCopy = new File(workDir + "/.gitlet/"
						+ toCheckout.location(fileName) + "/" + fileName);
				File destination = new File(workDir + "/" + fileName);
				FileOps.copyReplace(toCopy, destination);
			}
		} else if (currBranch.commit().tracking().containsKey(filename)) {
			File toCopy = new File(workDir + "/.gitlet/"
					+ currBranch.commit().location(filename) + "/" + filename);
			File destination = new File(workDir + "/" + filename);
			FileOps.copyReplace(toCopy, destination);
		} else {
			System.out.println("File does not exist in the most recent commit, or no such branch exists.");
		}
	}

	/**
	 * - Given a commitID and a filename, it checks out the given file from the
	 * 	given commit
	 * 
	 * @param commitID
	 *            The ID of the commit the user wishes to checkout
	 * @param filename
	 *            The name of the file the user wants to checkout
	 */
	public void checkout(int commitID, String filename) {
		if (commits.containsKey(commitID) == false) {
			System.out.println("No commit with that id exists.");
		} else if (commits.get(commitID).tracking().containsKey(filename) == false) {
			System.out.println("File does not exist in that commit.");
		} else {
			File toCopy = new File(workDir + "/.gitlet/"
					+ commits.get(commitID).location(filename) + "/" + filename);
			File destination = new File(workDir + "/" + filename);
			FileOps.copyReplace(toCopy, destination);
		}
	}

	/**
	 * Merges the commits of two different branches into one, by noting the
	 * files that have been changed since the split point and acting upon it. If
	 * files have been changed in both branches, gitlet is put into a conflicted
	 * state upon exit from the program, and certain commands cannot be used.
	 * Otherwise, it automatically commits after the changes have been made.
	 * 
	 * @param branchName
	 *            Name of the branch you want to merge with the current branch
	 */
	public void merge(String branchName) {
		if (branches.containsKey(branchName) == false)
			System.out.println("A branch with that name does not exist.");
		if (currBranch.name().equals(branchName))
			System.out.println("Cannot merge a branch with itself.");
		else {
			Branch toMerge = branches.get(branchName);
			HashSet<Commit> currCommitHistory = commitHistory(currBranch.commit());
			Commit splitPoint = toMerge.commit();
			while (currCommitHistory.contains(splitPoint) == false) {
				splitPoint = splitPoint.parent();
			}
			for (String filename : splitPoint.tracking().keySet()) {

				File originalFile = new File(workDir + "/.gitlet/"
						+ splitPoint.location(filename) + "/" + filename);
				File toMergeFile = new File(workDir + "/.gitlet/"
						+ toMerge.commit().location(filename) + "/" + filename);
				File currentFile = new File(workDir + "/.gitlet/"
						+ currBranch.commit().location(filename) + "/" + filename);

				boolean mergeChanged = FileOps.contentEquals(
						originalFile.toPath(), toMergeFile.toPath()) == false;
				boolean currentChanged = FileOps.contentEquals(
						originalFile.toPath(), currentFile.toPath()) == false;

				if ((mergeChanged == false) && (currentChanged == false)) {
					continue;
				} else if (((mergeChanged == false) && (currentChanged == true))) {
					continue;
				} else if (((mergeChanged == true) && (currentChanged == false))
						|| (toMergeFile.exists() == false)
						|| (currentFile.exists() == false)) {
					File workingDirFile = new File(workDir + "/" + filename);
					if (toMergeFile.exists() == false) {
						marked.add(workingDirFile);
					} else {
						checkout(toMerge.commit().idNumber(), filename);
						staged.put(workingDirFile, filename);
						FileOps.copy(workingDirFile, new File(stageDir + "/" + filename));
					}
				} else {
					String newFilePath = filename.substring(0, filename.lastIndexOf('.')) + ".conflicted";
					File destination = new File(workDir + "/" + newFilePath);
					FileOps.copy(toMergeFile, destination);
					conflicted = true;
				}
			}
			if (conflicted == false) {
				commit("Merged " + currBranch.name() + " with " + toMerge.name() + ".");
			} else {
				System.out.println("Encountered a merge conflict.");
			}
		}
	}

	/**
	 * Returns a hashset of all the commits in the given commit's history until the initial commit
	 * @param commit
	 * 		commit you want the history of
	 * @return
	 * 		A HashSet containing the files in the given commit's history
	 */
	private HashSet<Commit> commitHistory(Commit commit) {
		HashSet<Commit> toReturn = new HashSet<Commit>();
		while (commit != null) {
			toReturn.add(commit);
			commit = commit.parent();
		}
		return toReturn;
	}

	/**
	 * This method find the split point of the current branch and the given
	 * branch, then snaps off the current branch at this point, then reattaches
	 * the current branch to the head of the given branch.
	 * 
	 * @param branchName
	 *            The name of the branch that the current branch will reattach to.
	 */

	public void rebase(String branchName) {
		if (branches.get(branchName) == null) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (branchName.equals(currBranch.name())) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}
		HashMap<Commit, Commit> h = new HashMap<Commit, Commit>(); 
		// find split point
		Commit currCommit = currBranch.commit();
		Commit givenCommit = branches.get(branchName).commit();
		while (currCommit.parent() != null) {
			if (currCommit.idNumber() == givenCommit.idNumber()) {
				System.out.println("Already up-to-date.");
				return;
			}
			h.put(currCommit.parent(), currCommit);
			currCommit = currCommit.parent();
		}
		while (!h.containsValue(givenCommit)) {
			givenCommit = givenCommit.parent();
		}
		if (!h.containsKey(givenCommit)) { 
			currBranch.setCommit(branches.get(branchName).commit());
			return;
		}
		Commit splitPoint = givenCommit;
		// find which files need to be copied. and then make new commit.
		givenCommit = branches.get(branchName).commit();
		Commit currentCommit = h.get(splitPoint);
		boolean first = true;

		while (currentCommit != null) {
			HashMap<String, File> toCommit = new HashMap<String, File>();
			HashMap<String, Integer> toLocate = new HashMap<String, Integer>();
			compareHelper(toCommit, toLocate, splitPoint,
					commits.get(currentCommit.idNumber()), givenCommit);
			if (first == true) { // test whether we are copying the 1st node.
				currBranch.setCommit(branches.get(branchName).commit());
				first = false;
			}
			File newCommitDir = new File(workDir + "/.gitlet/" + nextID);
			FileOps.mkdir(newCommitDir);
			Commit newCommit = new Commit(currBranch.commit(), toCommit,
					toLocate, nextID, currentCommit.description());
			commits.put(newCommit.idNumber(), newCommit);
			currBranch.setCommit(newCommit);
			nextID++;
			splitPoint = currentCommit;
			currentCommit = h.get(currentCommit);
			givenCommit = newCommit;
		}
	}

	/**
	 * Compare the versions of file at split point, the commit after split point
	 * but on current branch and the commit at the head of given branch. and
	 * decide which version of file that is to be copied to new created commit.
	 * 
	 * @param filename
	 *            The name of the file that is being compared.
	 * @param split
	 *            The commit at split point.
	 * @param curr
	 *            The commit after split point but on current branch.
	 * @param given
	 *            The commit at the head of given branch
	 * @return The name of the file that will be copied to new created commit
	 */
	private void compareHelper(HashMap<String, File> beginPoint,
			HashMap<String, Integer> location, Commit split, Commit curr,
			Commit given) {
		for (Map.Entry<String, File> m : curr.tracking().entrySet()) {
			if (!split.tracking().containsKey(m.getKey())) {
				beginPoint.put(m.getKey(), m.getValue());
				location.put(m.getKey(), curr.location(m.getKey()));
			} else {
				if (FileOps.contentEquals(Paths.get(workDir + "/.gitlet/"
						+ split.location(m.getKey()) + "/" + m.getKey()), Paths
						.get(workDir + "/.gitlet/" + curr.location(m.getKey())
								+ "/" + m.getKey()))) {
					if (given.tracking().containsKey(m.getKey())) {
						beginPoint.put(m.getKey(),
								given.tracking().get(m.getKey()));
						location.put(m.getKey(), given.location(m.getKey()));
					}
				} else {
					beginPoint.put(m.getKey(), curr.tracking().get(m.getKey()));
					location.put(m.getKey(), curr.location(m.getKey()));
				}

			}
		}

	}

	/**
	 * - Interprets the user-inputed command & carries out the corresponding
	 * method in the Gitlet class
	 * 
	 * @param command
	 *            - the command the user has inputed
	 * @param args
	 *            - the complete array of arguments the user has passed in
	 */
	private void readCommand(String command, String[] args) {
		if (command.equals("init"))
			init();
		else if (command.equals("add"))
			add(args[1]);
		else if (command.equals("rm"))
			rm(args[1]);
		else if (command.equals("commit")) {
			try {
				commit(args[1]);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Please enter a commit message.");
			}
		} else if (command.equals("checkout"))
			checkout(args);
		else if (command.equals("branch") && conflicted == false)
			branch(args[1]);
		else if (command.equals("rm-branch") && conflicted == false)
			rmBranch(args[1]);
		else if (command.equals("log"))
			log();
		else if (command.equals("global-log"))
			global_log();
		else if (command.equals("find"))
			find(args[1]);
		else if (command.equals("status"))
			status();
		else if (command.equals("reset") && conflicted == false)
			reset(Integer.parseInt(args[1]));
		else if (command.equals("rebase") && conflicted == false)
			rebase(args[1]);
		else if (command.equals("merge") && conflicted == false)
			merge(args[1]);
		else if (conflicted == true)
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
		else
			System.out.println("No command with that name exists.");
	}

	/**
	 * - Takes in the user input and acts upon it. 
	 * - Also serializes and deserializes the gitlet object when appropriate
	 * 
	 * @param args
	 *      - user inputed command and arguments
	 */
	public static void main(String[] args) {
		try {
			String command = args[0];
			Gitlet gitlet = FileOps.readFromFile();
			gitlet.readCommand(command, args);
			FileOps.writeToFile(gitlet);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Please enter a command.");
		} catch (FileNotFoundException e) {
			if (args[0].equals("init") == true) {
				Gitlet gitlet = init();
				if (gitlet != null)
					FileOps.writeToFile(gitlet);
			} else
				System.out.println("You must initialize gitlet.");
		}
	}
}
