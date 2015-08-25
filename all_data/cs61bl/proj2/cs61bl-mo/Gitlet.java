import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.CopyOption;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Gitlet {
	/**
	 * Creates new .gitlet in current directory with an initial commit
	 */
	public static void init() {
		File First = new File(".gitlet/");
		if (First.exists()) {
			System.out.println("A gitlet version control system already "
					+ "exists in the current directory.");
			return;
		}
		First.mkdirs();
		File staging = new File(".gitlet/staging");
		staging.mkdir();
		try {
			Tree myTree = new Tree();
			myTree.addNode(new HashMap<String, File>(), "initial commit");
			ObjectOutput output = new ObjectOutputStream(new FileOutputStream(
					new File(".gitlet/Tree.ser")));
			output.writeObject(myTree);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves serialized tree object
	 * 
	 */
	public static Tree getTree() {
		try {
			InputStream file = new FileInputStream(".gitlet/Tree.ser");
			ObjectInput out = new ObjectInputStream(file);
			Tree tempTree = (Tree) out.readObject();
			out.close();
			Tree myTree = tempTree.retrieveBranch();
			return myTree;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Serializes tree
	 * 
	 */
	public static void closeTree(Object tree) {
		try {
			OutputStream file = new FileOutputStream(".gitlet/Tree.ser");
			ObjectOutput output = new ObjectOutputStream(file);
			output.writeObject(tree);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a copy of file with fileName to the staging folder.
	 * 
	 */
	public static void add(String fileName) {
		Tree myTree = getTree();
		File temp = new File(fileName);
		if (!temp.exists()) {
			System.out.println("File does not exist.");
			return;
		}
		CopyOption[] copier = new CopyOption[1];
		copier[0] = StandardCopyOption.REPLACE_EXISTING;
		Path one = temp.toPath();
		Path two = Paths.get(".gitlet/staging/" + temp.getName());
		try {
			if (temp.getParent() != null) {
				File stagingfolder = new File(".gitlet/staging/"
						+ temp.getParent());
				if (!Files.exists(stagingfolder.toPath())) {
					stagingfolder.mkdir();
				}
				Path newfolder = Paths.get(stagingfolder.toPath().toString()
						+ "/" + temp.getName());
				if (myTree.staged.containsKey(fileName)) {
					Files.copy(one, newfolder, copier[0]);
					File toBeAdded = newfolder.toFile();

					myTree.staged.put(fileName, temp);
				} else {
					Files.copy(one, newfolder);
					File toBeAdded = new File(".gitlet/staging/"
							+ temp.getParent() + "/" + temp.getName());
					myTree.staged.put(fileName, temp);
				}
			} else if (myTree.staged.containsKey(fileName)) {
				Files.copy(one, two, copier[0]);
				File toBeAdded = new File(".gitlet/staging/" + temp.getName());
				myTree.staged.put(fileName, temp);
			} else {
				Files.copy(one, two);
				File toBeAdded = new File(".gitlet/staging/" + temp.getName());
				myTree.staged.put(fileName, temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeTree(myTree);
	}

	/**
	 * Saves a backup of files that have been staged, with corresponding commit
	 * message
	 * 
	 * @param message
	 *            message associated with current commit
	 */
	public static void commit(String message) {
		File staging = new File(".gitlet/staging/");
		File[] toBeCommitted = staging.listFiles();
		if (!staging.exists()) {
			System.out.println("FUCKING INIT GITLET");
			return;
		}
		if (message.length() == 0) {
			System.out.println("Please enter a commit message.");
			return;
		}

		if (toBeCommitted.length == 0) {
			System.out.println("No changes have been added to the commit.");
			return;
		}

		try {
			Tree myTree = getTree();
			File newcommit = new File(".gitlet/commit" + myTree.counter);
			newcommit.mkdir();
			for (File a : toBeCommitted) {
				Path from = a.toPath();
				Path to = Paths.get(".gitlet/commit" + myTree.counter + "/"
						+ a.getName());
				Files.copy(from, to);
				if (a.isDirectory()) {
					copydirectories(a, to.toFile());
				}
				// if(!a.isDirectory()){
				// a.delete();
				// }
			}
			for (String IWILLKILLYOU : myTree.staged.keySet()) {
				myTree.trackingFiles
						.put(new String(IWILLKILLYOU), new File(
								".gitlet/commit" + myTree.counter + "/"
										+ IWILLKILLYOU));
			}
			myTree.addNode(myTree.staged, message);
			myTree.staged.clear();
			myTree.removal.clear();
			for (File file : staging.listFiles()) {
				file.delete();
			}
			closeTree(myTree);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param directory
	 * @param too
	 */
	public static void copydirectories(File directory, File too) {

		File[] committing = directory.listFiles();
		try {
			Tree myTree = getTree();
			for (File a : committing) {
				Path from = a.toPath();
				Path to = Paths
						.get(too.toPath().toString() + "/" + a.getName());
				Files.copy(from, to);
				if (a.isDirectory()) {
					copydirectories(a, to.toFile());
				}
				if (!a.isDirectory()) {
					File sjow = new File(too.toPath().toString() + "/"
							+ a.getName());
					// System.out.print(a.getParent() + "/" + a.getName());
					myTree.trackingFiles.put(a.getParent() + "/" + a.getName(),
							sjow);
					// System.out.print(myTree.trackingFiles.containsKey(a.getParent()
					// + "/" + a.getName()));
					a.delete();
				}
			}
			closeTree(myTree);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Marks a file for untracking
	 * 
	 * @param fileName
	 *            the file to be untracked
	 */
	public static void remove(String fileName) {
		File removed = new File(fileName);
		Tree myTree = getTree();
		if (myTree.staged.containsKey(fileName)) {
			myTree.staged.remove(fileName);
			Path remove = Paths.get(".gitlet/staging/" + fileName);
			try {
				Files.delete(remove);
			} catch (Exception e) {
				e.printStackTrace();
			}
			closeTree(myTree);
		} else {
			if (myTree.trackingFiles.containsKey(fileName)
					&& !myTree.removal.contains(fileName)) {
				myTree.removal.add(fileName);
				closeTree(myTree);
			} else {
				System.out.println("No reason to remove the file.");
			}
		}
	}

	/**
	 * Displays each commit from most recent to initial.
	 */
	public static void log() {
		Tree myTree = getTree();
		myTree.print();
		closeTree(myTree);
	}

	/**
	 * Displays all commits
	 */
	public static void globallog() {
		Tree myTree = getTree();
		myTree.printAll();
		closeTree(myTree);
	}

	/**
	 * Prints the ID of the commit with the given message
	 * 
	 * @param commitmessage
	 *            the message associated with the ID that is printed.
	 */
	public static void find(String commitmessage) {
		Tree myTree = getTree();
		if (!myTree.commitMessage.containsKey(commitmessage)) {
			System.out.println("Found no commit with that message.");
			closeTree(myTree);
		} else {
			myTree.commitPrint(commitmessage);
			closeTree(myTree);
		}
	}

	/**
	 * Creates a new branch
	 * 
	 * @param branchName
	 *            the name of the new branch
	 */
	public static void makebranch(String branchName) {
		Tree myTree = getTree();
		myTree.makeBranch(myTree, branchName);
		closeTree(myTree);
	}

	/**
	 * Displays what branches currently exist, and marks the current branch with
	 * a *. Also displays what files have been staged or marked for untracking.
	 */
	public static void status() {
		Tree myTree = getTree();
		System.out.println("=== Branches ===");
		System.out.println("*" + myTree.currentBranch);
		for (String temp : myTree.branches.keySet()) {
			if (!temp.equals(myTree.currentBranch)) {
				System.out.println("other-" + temp);
			}
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		File staging = new File(".gitlet/staging/");
		File[] stagedfiles = staging.listFiles();
		for (File a : stagedfiles) {
			System.out.println(a.getName());
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (String a : myTree.removal) {
			System.out.println(a);
		}
		System.out.println("");
		closeTree(myTree);
	}

	/**
	 * Removes a branch
	 * 
	 * @param name
	 *            the branch to be removed
	 */
	public static void removeBranch(String name) {
		Tree myTree = getTree();
		if (name.equals(myTree.currentBranch)) {
			System.out.println("Cannot remove current branch.");
			closeTree(myTree);
		} else {
			if (myTree.branches.containsKey(name)) {
				System.out.println("removing" + name);
				myTree.branches.remove(name);
				closeTree(myTree);

			} else {
				System.out.println("A branch with that name does not exist.");
				closeTree(myTree);
			}
		}
	}

	/**
	 * checks out either branch or file
	 * 
	 * @param name
	 *            name of branch or file being checked out
	 */
	public static void checkout(String name) {
		Tree myTree = getTree();
		myTree.checkoutHead(name);
		closeTree(myTree);
	}

	/**
	 * Checks out specific file from a commit
	 * 
	 * @param commitID
	 *            the id of the commit
	 * @param fileName
	 *            the name of the file
	 */
	public static void checkout(String commitID, String fileName) {
		Tree myTree = getTree();
		myTree.getNode(commitID, fileName);
		closeTree(myTree);
	}

	/**
	 * Merges current branch with another
	 * 
	 * @param treeName
	 *            the name of the branch current is being merged with
	 */
	public static void merge(String treeName) {
		Tree myTree = getTree();
		myTree.merge(treeName);
		closeTree(myTree);
	}

	/**
	 * Resets the current pointer
	 * 
	 * @param one
	 *            the name of the new current branch
	 */
	public static void reset(String one) {
		Tree myTree = getTree();
		myTree.reset(one);
		closeTree(myTree);
	}

	/**
	 * adds one branch to the end of the current
	 * 
	 * @param branchName
	 *            the name of the branch being added to the current
	 */
	public static void rebase(String branchName) {
		Tree myTree = getTree();
		myTree.rebase(branchName);
		closeTree(myTree);
	}

	public static void main(String[] args) {
		if (args[0].equals("init")) {
			init();
		}
		if (args[0].equals("add") && args.length == 2) {
			System.out.println("adding");
			add(args[1]);

		}
		if (args[0].equals("commit")) {
			System.out.println("committing");
			if (args.length == 2) {
				commit(args[1]);
			} else {
				System.out.println("Please enter a commit message.");
			}
		}
		if (args[0].equals("rm") && args.length == 2) {
			remove(args[1]);
		}
		if (args[0].equals("log")) {
			log();
		}
		if (args[0].equals("find") && args.length == 2) {
			find(args[1]);
		}
		if (args[0].equals("global-log")) {
			globallog();
		}
		if (args[0].equals("branch") && args.length == 2) {
			makebranch(args[1]);
		}
		if (args[0].equals("status")) {
			status();
		}
		if (args[0].equals("rm-branch") && args.length == 2) {
			removeBranch(args[1]);
		}
		if (args[0].equals("reset") && args.length == 2) {
			reset(args[1]);
		}

		if (args[0].equals("checkout")) {
			if (args.length == 2) {
				checkout(args[1]);
			}
			if (args.length == 3) {
				checkout(args[1], args[2]);
			}
			if (args[0].equals("merge") && args.length == 2) {
				merge(args[1]);
			}
		}

		if (args[0].equals("rebase") && args.length == 2) {
			rebase(args[1]);
		}
	}
}