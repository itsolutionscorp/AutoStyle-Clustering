import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

public class Gitlet {
	CommitTree cTree;

	/**
	 * Initializes .gitlet folder, creates .staging area, commits folder, and
	 * the initial commit 0
	 */
	public void initialize() {
		File gitlet = new File(".gitlet");
		if (gitlet.exists()) {
			System.out
					.println("A gitlet version control system already exists in the current directory.");
		} else {
			gitlet.mkdir();
			File stagingArea = new File(".gitlet/.stagingArea");
			stagingArea.mkdir();
			cTree = new CommitTree();
			File commit = new File(".gitlet/commits");
			commit.mkdir();
			cTree.newCommit("initial commit");
			commitSerialWrite(cTree);
		}
	}

	/**
	 * Adds a file to the staging area, if it was previously marked for removal
	 * then the file is unmarked instead
	 * 
	 * @param fileName
	 *            String filename to be added
	 */
	public void add(String fileName) {
		cTree = commitSerialRead();
		if (cTree.isMarked(fileName)) {
			cTree.unmarkFile(fileName);
			commitSerialWrite(cTree);
			return;
		}
		File stagingArea = new File(".gitlet/.stagingArea" + "/" + fileName);
		File toBeAdded = new File(fileName);
		File parent = new File(stagingArea.getParent());
		if (!toBeAdded.exists()) {
			System.out.println("File does not exist.");
		} else {
			try {
				parent.mkdirs();
				Files.copy(toBeAdded.toPath(), stagingArea.toPath());
				cTree.addToVirtualStage(fileName);
				commitSerialWrite(cTree);
			} catch (IOException e) {
			}
		}
	}

	/**
	 * Checks if files have been added or marked for removal before creating a
	 * new commit object with a message
	 * 
	 * @param message
	 *            String message associated with the commit
	 */
	public void commit(String message) {
		cTree = commitSerialRead();
		if (!cTree.hasStagedFiles() && !cTree.hasMarkedFiles()) {
			System.out.println("No changes added to the commit.");
		} else {
			cTree.newCommit(message);
			commitSerialWrite(cTree);
		}
	}

	/**
	 * Either removes a file from the staging area or marks it for removal if it
	 * is currently being tracked
	 * 
	 * @param fileName
	 *            String filename of file to be removed
	 */
	public void remove(String fileName) {
		File stage = new File(".gitlet/.stagingArea/");
		File stagedFile = new File(".gitlet/.stagingArea/" + fileName);
		File parent = stagedFile.getParentFile();
		cTree = commitSerialRead();
		if (stagedFile.exists()) {
			stagedFile.delete();
			cTree.removeFromVirtualStage(fileName);
			commitSerialWrite(cTree);
			while (!parent.equals(stage)) {
				if (parent.list().length == 0) {
					parent.delete();
				}
				parent = parent.getParentFile();
			}
		} else if (cTree.checkTrackedFiles(fileName)) {
			cTree.markFile(fileName);
			commitSerialWrite(cTree);
		} else {
			System.out.println("No reason to remove the file.");
		}
	}

	/**
	 * Serializes a commit tree object
	 * 
	 * @param c
	 *            commit tree object to be serialized
	 */
	private void commitSerialWrite(CommitTree c) {
		try {
			ObjectOutput output = new ObjectOutputStream(new FileOutputStream(
					".gitlet/commits/commitTreeSer.txt"));
			output.writeObject(c);
			output.close();
		} catch (IOException e) {
		}
	}

	/**
	 * Reads in a commit tree object through a serialized file
	 * 
	 * @return commit tree object
	 */
	private CommitTree commitSerialRead() {
		try {
			ObjectInput input = new ObjectInputStream(new FileInputStream(
					".gitlet/commits/commitTreeSer.txt"));
			CommitTree read = (CommitTree) input.readObject();
			input.close();
			return read;
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	/**
	 * Calls the log method in the commit tree class, prints out a log of all
	 * commits made in the current branch
	 */
	public void log() {
		cTree = commitSerialRead();
		cTree.log();
	}

	/**
	 * Calls global log method in commit tree class, prints out a log of all
	 * commits ever made
	 */
	public void globalLog() {
		cTree = commitSerialRead();
		cTree.globalLog();
	}

	/**
	 * Calls find method in commit tree class, prints out all the commit ids of
	 * commits that have the associated message
	 * 
	 * @param message
	 *            String to match with commit messages
	 */
	public void find(String message) {
		cTree = commitSerialRead();
		cTree.find(message);
	}

	/**
	 * Calls branch method in commit tree class, creates a new branch if it
	 * doesn't exist already
	 * 
	 * @param branchName
	 *            String of the new branch name
	 */
	public void branch(String branchName) {
		cTree = commitSerialRead();
		cTree.branch(branchName);
		commitSerialWrite(cTree);
	}

	/**
	 * Calls status method in commit tree class, prints branches, staged files,
	 * and files marked for removal
	 */
	public void status() {
		cTree = commitSerialRead();
		cTree.status();
	}

	/**
	 * Checks out a file in a commit, copies the version of the file in the
	 * commit to the working directory
	 * 
	 * @param ID
	 *            ID of commit
	 * @param fileName
	 *            String name of the file
	 */
	public void checkout(String ID, String fileName) {
		cTree = commitSerialRead();
		cTree.checkout(ID, fileName);
		commitSerialWrite(cTree);
	}

	/**
	 * Checks out a branch or file, either copies all tracked files in the head
	 * commit of the branch into the working directory or copies a file from
	 * head commimt into the working directory
	 * 
	 * @param name
	 *            String name of branch or file to checkout
	 */
	public void checkout(String name) {
		cTree = commitSerialRead();
		cTree.checkout(name);
		commitSerialWrite(cTree);
	}

	/**
	 * Deletes a branch and the pointer to its head commit
	 * 
	 * @param branchName
	 *            String name of branch to remove
	 */
	public void removeBranch(String branchName) {
		cTree = commitSerialRead();
		cTree.removeBranch(branchName);
		commitSerialWrite(cTree);
	}

	/**
	 * Resets the working directory and current branch to another commit
	 * 
	 * @param id
	 *            ID of commit to reset pointers and files to
	 */
	public void reset(String id) {
		cTree = commitSerialRead();
		cTree.reset(id);
		commitSerialWrite(cTree);
	}

	/**
	 * Calls commit tree rebase, copies commits after split point from current
	 * branch to given branch
	 * 
	 * @param branch
	 *            String of branchname to rebase
	 */
	public void rebase(String branch) {
		cTree = commitSerialRead();
		cTree.rebase(branch);
		commitSerialWrite(cTree);
	}

	/**
	 * Merges files from a given branch to the current branch
	 * 
	 * @param branch
	 *            branch to merge files from
	 * @throws IOException
	 */
	public void merge(String branch) throws IOException {
		cTree = commitSerialRead();
		ArrayList<String> toAdd = cTree.merge(branch);
		for (int i = 0; i < toAdd.size(); i++) {
			add(toAdd.get(i));
		}
		if (!cTree.hasConflict()) {
			commit("Merged " + cTree.currentBranch() + " with " + branch);
		} else {
			System.out.println("Encountered a merge conflict.");
		}
		commitSerialWrite(cTree);
	}

	/**
	 * Checks to see if gitlet is in a conflicted state
	 * 
	 * @return true if gitlet has a conflict
	 */
	public boolean hasConflict() {
		cTree = commitSerialRead();
		return cTree.hasConflict();
	}

	/**
	 * Runs gitlet commands
	 * 
	 * @param args
	 *            arguments used to run commands
	 */
	public static void main(String[] args) {
		Gitlet git = new Gitlet();
		if (args[0].equals("init")) {
			git.initialize();
			return;
		}
		List<String> conflictedCommands = Arrays.asList("add", "rm", "commit",
				"log", "global-log", "find", "status", "checkout");
		List<String> otherCommands = Arrays.asList("init", "rebase", "merge",
				"branch", "rm-branch", "reset");
		if (args.length > 0) {
			if (!conflictedCommands.contains(args[0])
					&& !otherCommands.contains(args[0])) {
				System.out.println("No command with that name exists.");
			} else {
				if (git.hasConflict() && !conflictedCommands.contains(args[0])) {
					System.out
							.println("Cannot do this command until the merge conflict has been resolved.");
				} else {
					if (args.length == 1) {
						mainHelpNoArg(git, args[0]);
					} else if (args.length == 2) {
						mainHelpOneArg(git, args);
					} else if (args.length == 3) {
						mainHelpTwoArg(git, args);
					}
				}
			}
		} else {
			System.out.println("Please enter a command.");
		}
	}

	/**
	 * Runs commands that don't require extra arguments
	 * 
	 * @param git
	 *            git object
	 * @param command
	 *            command to run
	 */
	public static void mainHelpNoArg(Gitlet git, String command) {
		switch (command) {
		case "init":
			git.initialize();
			break;
		case "log":
			git.log();
			break;
		case "global-log":
			git.globalLog();
			break;
		case "status":
			git.status();
			break;
		case "commit":
			System.out.println("Please enter a commit message.");
			break;
		}
	}

	/**
	 * Runs commands that require an extra string input after the command call
	 * 
	 * @param git
	 *            git object
	 * @param args
	 *            string array of arguments
	 */
	public static void mainHelpOneArg(Gitlet git, String[] args) {
		switch (args[0]) {
		case "add":
			git.add(args[1]);
			break;
		case "commit":
			git.commit(args[1]);
			break;
		case "rm":
			git.remove(args[1]);
			break;
		case "find":
			git.find(args[1]);
			break;
		case "branch":
			git.branch(args[1]);
			break;
		case "rm-branch":
			git.removeBranch(args[1]);
			break;
		case "reset":
			git.reset(args[1]);
			break;
		case "merge":
			try {
				git.merge(args[1]);
			} catch (IOException e) {
			}
			break;
		case "rebase":
			git.rebase(args[1]);
			break;
		case "checkout":
			git.checkout(args[1]);
			break;
		}
	}

	/**
	 * Runs checkout [commit id] [filename], only command that requires 3
	 * arguments
	 * 
	 * @param git
	 *            git object
	 * @param args
	 *            string array of arguments
	 */
	public static void mainHelpTwoArg(Gitlet git, String[] args) {
		git.checkout(args[1], args[2]);
	}
}
