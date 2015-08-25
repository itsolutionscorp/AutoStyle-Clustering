import java.sql.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import java.io.*;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;

public class Gitlet implements Serializable {
	private static File dir = new File(".gitlet");
	private static File commitDir = new File(".gitlet/commit/");
	private static File main = new File(".");
	private static String g = ".gitlet";
	private static String gitletFile = ".gitlet/gitlet/";
	private static String commitFile = ".gitlet/commit/";
	private static File currDir = new File(new File(".").getAbsolutePath());
	private static String n = "\n";
	private static StandardCopyOption[] options = new StandardCopyOption[] {
			StandardCopyOption.REPLACE_EXISTING,
			StandardCopyOption.COPY_ATTRIBUTES };
	private Chooby chooby;
	private boolean conflicted;

	/**
	 * Constructs an instance of Gitlet. A new instance of Gitlet will only be
	 * created if there is no existing instance of Gitlet in the directory.
	 * Calls the init method.
	 * 
	 */
	public Gitlet() {
		dir.mkdir();
		init();
	}

	/**
	 * Saves the current state of gitlet.
	 * 
	 * @param c
	 *            The object to be saved.
	 * @param f
	 *            The name of the file.
	 */
	private static void saver(Object c, String f) throws IOException,
			ClassNotFoundException {
		FileOutputStream saveFile = new FileOutputStream(f);
		ObjectOutputStream saved = new ObjectOutputStream(saveFile);
		saved.writeObject(c);
		saved.close();
		saveFile.close();
	}

	/**
	 * Opens/reads the saved state of gitlet.
	 * 
	 * @param f
	 *            The name of the file to be read/opened.
	 * @return the object that was read/opened.
	 * @throws IOException
	 *             if the file is not found.
	 * @throws ClassNotFoundException
	 *             if the class is not found.
	 */
	private static Object reader(String f) throws IOException,
			ClassNotFoundException {
		FileInputStream inFile = new FileInputStream(f);
		ObjectInputStream in = new ObjectInputStream(inFile);
		Object tmp = (Object) in.readObject();
		in.close();
		inFile.close();
		return tmp;
	}

	public static void main(String[] args) {
		/** Gitlet that exists. */
		Gitlet g = null;

		/** Chooby associated to Gitlet. */
		Chooby choobs = null;

		// No commands given.
		if (args.length == 0) {
			System.out.println("Command input required.");
			return;
		}

		// Reading back into the file.
		// If Gitlet has been initialized, return to most recent state.
		Path path = FileSystems.getDefault().getPath(gitletFile);
		if (Files.exists(path)) {
			try {
				g = (Gitlet) reader(gitletFile);
				choobs = g.chooby;
			} catch (IOException | ClassNotFoundException a) {
				return;
			}
		}

		String command = args[0];

		if (!Files.exists(path) && !command.equals("init")) {
			System.out
					.println("Gitlet version control system must be initialized!");
			return;
		}

		switch (command) {
		case "init":
			// Create a new directory if it doesn't already exist.
			if (!Files.exists(path)) {
				new Gitlet();
			} else {
				System.out
						.println("A gitlet version control system already exists in the current directory.");
			}
			return;

		case "add":
			if (args.length == 1) {
				return;
			}

			File toAdd = new File(args[1]);
			if (!toAdd.exists()) {
				System.out.println("File does not exist.");
			} else {
				g.add(args[1]);
			}
			return;

		case "commit":
			if (args.length == 1) {
				System.out.println("Please enter a commit message.");
				return;
			}

			if (choobs.stagedFiles.isEmpty()) {
				System.out.println("No changes added to the commit.");
				return;
			} else {
				g.commit(args[1], new HashMap<String, Integer>(
						choobs.current.files), "unique");
			}
			return;

		case "rm":
			if (args.length == 1) {
				return;
			}

			g.rm(args[1]);
			return;

		case "log":
			g.log();
			return;

		case "global-log":
			g.global_log();
			return;

		case "find":
			if (args.length == 1) {
				return;
			}

			ArrayList<Integer> ids = choobs.findMessage(args[1]);
			if (ids == null) {
				System.out.println("Found no commit with that message.");
			} else {
				g.find(ids);
			}
			return;

		case "status":
			g.status();
			return;

		case "checkout":
			// No branch, file, or id/file name given.
			if (args.length == 1) {
				return;
			}

			// Branch or file name checkout.
			if (args.length == 2) {
				// Branch checkout.
				if (choobs.branchExists(args[1])) {
					if (choobs.currBranch.equals(args[1])) {
						System.out
								.println("No need to checkout the current branch.");
					} else {

						g.branchCheckout(args[1]);
					}
				}

				// File checkout.
				else if (choobs.hasFile(args[1])) {
					g.fileCheckout(args[1]);
				}

				else {
					System.out
							.println("File does not exist in the most recent commit, or no such branch exists.");
				}
			}

			// ID/file name checkout.
			if (args.length == 3) {
				// Given id does not exist.
				if (Integer.parseInt(args[1]) > choobs.idCount) {
					System.out.println("No commit with that id exists.");
				}

				else {
					// File does not exist in given commit.
					Mooby atID = choobs.getById(args[1]);
					if (!atID.hasFile(args[2])) {
						System.out
								.println("File does not exist in that commit.");
					}

					else {
						g.idNameCheckout(args[1], args[2]);
					}
				}
			}

			try {
				saver(g, gitletFile);
			} catch (IOException | ClassNotFoundException a) {
				return;
			}
			return;

		case "branch":
			if (g.conflicted) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				return;
			}

			if (args.length == 1) {
				return;
			}

			if (choobs.branchExists(args[1])) {
				System.out.println("A branch with that name already exists.");
				return;
			} else {
				g.branch(args[1]);
			}
			return;

		case "rm-branch":
			if (g.conflicted) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				return;
			}

			if (args.length == 1) {
				return;
			}

			if (!choobs.branchExists(args[1])) {
				System.out.println("A branch with that name does not exist.");
			} else if (choobs.currBranch.equals(args[1])) {
				System.out.println("Cannot remove the current branch.");
			} else {
				g.rm_branch(args[1]);
			}
			return;

		case "reset":
			if (g.conflicted) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				return;
			}

			if (args.length == 1) {
				return;
			}

			if (Integer.parseInt(args[1]) > choobs.idCount) {
				System.out.println("No commit with that id exists");
			} else {
				g.reset(args[1]);
			}
			return;

		case "merge":
			if (g.conflicted) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				return;
			}

			if (args.length == 1) {
				return;
			}

			if (!choobs.branchExists(args[1])) {
				System.out.println("A branch with that name does not exist.");
			} else if (choobs.currBranch.equals(args[1])) {
				System.out.println("Cannot merge a branch with itself.");
			} else {
				g.merge(choobs.currBranch, args[1]);
			}
			return;

		case "rebase":
			if (g.conflicted) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				return;
			}

			if (args.length == 1) {
				return;
			}

			if (!choobs.branchExists(args[1])) {
				System.out.println("A branch with that name does not exist.");
			} else if (choobs.currBranch.equals(args[1])) {
				System.out.println("Cannot rebase a branch onto itself.");
			} else {
				Mooby pointer = choobs.current;
				while (pointer.previous != null) {
					if (choobs.branchMooby(args[1]).equals(pointer)) {
						System.out.println("Already up-to-date.");
					}
					pointer = pointer.previous;
				}

				g.rebase(args[1]);
			}
			return;

		default:
			System.out
					.println("'" + command + "'" + " is not a valid command.");
			return;
		}
	}

	/**
	 * Creates a new Chooby control system in the current directory. This system
	 * will automatically start with one commit: a commit that contains no files
	 * and has the commit message 'initial commit'.
	 * 
	 * If there is already a gitlet version control system in the current
	 * directory, it should abort. It should NOT overwrite the existing system
	 * with a new one. Should print the error message:
	 * "A gitlet version control system already exists in the current directory."
	 * 
	 * @throws IOException
	 *             if the file does not exist.
	 * @throws ClassNotFoundException
	 *             if the class does not exist.
	 */
	public void init() {
		chooby = new Chooby();
		commitDir.mkdir();
		new File(commitFile + chooby.idCount).mkdir();
		chooby.current = new Mooby(null, "initial commit", timestamp(),
				chooby.idCount, new HashMap<String, Integer>());
		chooby.updateBranches(chooby.currBranch, chooby.current);
		chooby.updateCommit("initial commit", chooby.idCount);
		chooby.updateHistory(chooby.idCount, chooby.current);
		chooby.idCount += 1;
		conflicted = false;

		try {
			saver(this, gitletFile);
		} catch (IOException | ClassNotFoundException a) {
			return;
		}
	}

	/**
	 * Adds a copy of the file as it currently exists to the staging area. If
	 * the file has been marked for untracking, add will add the file to the
	 * Untracked arraylist and not add it to the staging area.
	 *
	 * @param addFile
	 *            String of the name of the file to be added.
	 * @throws IOException
	 *             if the file does not exist.
	 * @throws ClassNotFoundException
	 *             if the class does not exist.
	 */
	public void add(String addFile) {
		if (chooby.inUntracking(addFile)) {
			chooby.rmUntracked(addFile);
		} else if (!chooby.inStaging(addFile)) {
			chooby.stageFile(addFile);
		}

		try {
			saver(this, gitletFile);
		} catch (IOException | ClassNotFoundException a) {
			return;
		}
	}

	/**
	 * Saves a backup of certain files so they can be restored at a later time.
	 * The collection of versions of files in a commit is sometimes called the
	 * commit's snapshot of files, and the commit is said to be tracking those
	 * versions of files.
	 *
	 * After the commit command, the new Mooby is added as a new node in the
	 * Chooby tree. The commit just made becomes the "current Mooby", and the
	 * head pointer now points to it. The previous head Mooby is this Mooby's
	 * parent Mooby. Each commit has a unique integer id number and has a
	 * timestamp of the time it was made.
	 *
	 * If no files have been staged or marked for untracking, the method aborts
	 * and prints the error message: "No changes added to the commit." Also,
	 * every commit must have a non-blank message. If it doesn't, the method
	 * will print the error message: "Please enter a commit message."
	 *
	 * This method also checks whether there are any split nodes that occur due
	 * to the commit, and stores them in an arraylist.
	 * 
	 * @param msg
	 *            String of the commit message associated with the commit.
	 * @throws IOException
	 *             if the file does not exist.
	 * @throws ClassNotFoundException
	 *             if the class does not exist.
	 */
	public void commit(String msg, HashMap<String, Integer> inherited, String s) {
		// Create a new folder for commit to save versions of staged files.
		File commitFolder = new File(commitFile + chooby.idCount.toString());
		commitFolder.mkdirs();

		// Find any split points that occur due to this commit.
		for (String b : chooby.branchMap.keySet()) {
			Mooby pointer = chooby.branchMooby(b);
			while (pointer != null) {
				if (pointer == chooby.current && !b.equals(chooby.currBranch)) {
					chooby.updateSplits(
							b.hashCode() * chooby.currBranch.hashCode(),
							chooby.current);
					break;
				}
				pointer = pointer.previous;
			}
		}

		// Saves a version of each staged file to the commit folder.
		for (String file : chooby.stagedFiles) {
			fileCommit(file, chooby.idCount.toString());
		}

		// Creates a new commit.
		// Updates branch pointers, commit message history for find,
		// history of all commits, increments commit ID, clears staging and
		// unmarking area. If Gitlet is in a conflicted state, resolves.
		chooby.current = new Mooby(chooby.current, msg, timestamp(),
				chooby.idCount, inherited);
		chooby.updateBranches(chooby.currBranch, chooby.current);
		chooby.updateCommit(msg, chooby.idCount);
		chooby.updateHistory(chooby.idCount, chooby.current);

		if (s.equals("unique")) {
			for (String each : chooby.stagedFiles) {
				chooby.addMFiles(each, chooby.idCount);
			}
			for (String each : chooby.untrackedFiles) {
				chooby.rmMFiles(each);
			}
		}

		chooby.idCount += 1;
		chooby.stagedFiles = new ArrayList<String>();
		chooby.untrackedFiles = new ArrayList<String>();
		conflicted = false;

		try {
			saver(this, gitletFile);
		} catch (IOException | ClassNotFoundException a) {
			return;
		}
	}

	/**
	 * Commits a single file.
	 * 
	 * @param each
	 *            String of the name of the file to be added.
	 * @param id
	 *            The ID of the commit.
	 * @throws IOException
	 *             if the file does not exist.
	 */
	public void fileCommit(String each, String id) {
		try {
			Path from = Paths.get(currDir.getCanonicalPath(), each);
			File c = new File(commitFile + id, each);
			if (!c.getParentFile().exists()) {
				c.getParentFile().mkdirs();
			}
			c.createNewFile();
			Path to = Paths.get(c.getCanonicalPath());
			Files.copy(from, to, options);
		} catch (IOException io) {
			return;
		}
	}

	/**
	 * Marks the file for untracking; this means it will not be included in the
	 * upcoming commit, even if it was tracked by that Mooby's parent. If the
	 * file had been staged, instead just unstage it; don't also mark it for
	 * untracking.
	 * 
	 * If the file is neither staged nor tracked by the head Mooby, print the
	 * error message: "No reason to remove the file."
	 *
	 * @param rmFile
	 *            String of the name of the file to be removed.
	 * @throws IOException
	 *             if the file does not exist.
	 * @throws ClassNotFoundException
	 *             if the class does not exist.
	 */
	public void rm(String rmFile) {
		if (!chooby.inStaging(rmFile) && !chooby.hasFile(rmFile)) {
			System.out.println("No reason to remove the file.");
		} else if (chooby.inStaging(rmFile)) {
			chooby.rmStaged(rmFile);
		} else if (!chooby.inUntracking(rmFile)) {
			chooby.untrackFile(rmFile);
		}

		try {
			saver(this, gitletFile);
		} catch (IOException | ClassNotFoundException a) {
			return;
		}
	}

	/**
	 * Starting at the current head Mooby, display information about each Mooby
	 * backwards along the Chooby until the initial commit. This set of Mooby
	 * nodes is called the Mooby’s history. For every node in this history, the
	 * information it should display is the commit id, the time the commit was
	 * made, and the commit message.
	 * 
	 * Note that it ignores other branches and the future.
	 *
	 */
	public void log() {
		Mooby pointer = chooby.current;
		while (pointer != null) {
			logPrinter(pointer);
			System.out.println("");
			pointer = pointer.previous;
		}
	}

	/**
	 * Just like log, except displays information about all commits ever made.
	 * The order of the commits does not matter.
	 */
	public void global_log() {
		for (Mooby each : chooby.moobyHistory.values()) {
			logPrinter(each);
			System.out.println("");
		}
	}

	/**
	 * The helper method for formatting the printing of the log and global log.
	 * 
	 * @param m
	 *            The Mooby whose information is to be displayed.
	 */
	public void logPrinter(Mooby m) {
		System.out.println("===" + n + "Commit " + m.id + "." + n + m.date + n
				+ m.message);
	}

	/**
	 * Prints out the id of the commit that has the given commit message. If
	 * there are multiple such commits, it prints the ids out on separate lines.
	 * 
	 * If no such commit exists, prints the error message:
	 * "Found no commit with that message."
	 * 
	 * @param idList
	 *            the arraylist of ID's of every Mooby in the Chooby.
	 */
	public void find(ArrayList<Integer> idList) {
		for (Integer each : idList) {
			System.out.println(each);
		}
	}

	/**
	 * Displays what branches currently exist, and marks the current branch with
	 * a *. Also displays what files have been staged or marked for untracking.
	 */
	public void status() {
		StringBuilder br = new StringBuilder();
		for (String each : chooby.branchMap.keySet()) {
			if (each.equals(chooby.currBranch)) {
				br.append("*");
			}
			br.append(each + n);
		}

		StringBuilder sf = new StringBuilder();
		for (String each : chooby.stagedFiles) {
			sf.append(each + n);
		}

		StringBuilder rm = new StringBuilder();
		for (String each : chooby.untrackedFiles) {
			rm.append(each + n);
		}

		System.out.println("=== Branches ===" + n + br + n
				+ "=== Staged Files ===" + n + sf + n
				+ "=== Files Marked for Removal ===" + n + rm);
	}

	/**
	 * Takes all files in the Mooby at the head of the given branch, and puts
	 * them in the working directory, ovewriting the versions of the files that
	 * are already there if they exist. Also, at the end of this command, the
	 * given branch will now be considered the current branch.
	 *
	 * @param b
	 *            The string of the name of the branch to be checked out.
	 * @throws IOException
	 *             if the file does not exist.
	 * @throws ClassNotFoundException
	 *             if the class does not exist.
	 *
	 */
	public void branchCheckout(String b) {
		Mooby current = chooby.branchMooby(b);
		for (String f : current.files.keySet()) {
			checkout(f, current.fileID(f).toString());
		}
		chooby.currBranch = b;
		chooby.current = current;
		try {
			saver(this, gitletFile);
		} catch (IOException | ClassNotFoundException a) {
			return;
		}
	}

	/**
	 * Takes the version of the file as it exists in the head Mooby, the front
	 * of the current branch, and puts it in the working directory, overwriting
	 * the version of the file that's already there if there is one.
	 *
	 * Calls the general checkout method.
	 * 
	 * @param f
	 *            The string of the name of the file to be checked out.
	 *
	 */
	public void fileCheckout(String f) {
		checkout(f, chooby.getMFile(f).toString());
	}

	/**
	 * Takes the version of the file as it exists in the Mooby with the given
	 * id, and puts it in the working directory, overwriting the version of the
	 * file that's already there if there is one.
	 *
	 * Calls the general checkout method.
	 * 
	 * @param n
	 *            The id of the Mooby to be checked out.
	 * @param f
	 *            The string of the name of the file to be checked out.
	 *
	 */
	public void idNameCheckout(String n, String f) {
		Mooby current = chooby.getById(n);
		checkout(f, current.fileID(f).toString());
	}

	/**
	 * Takes the version of the file as it exists in the Mooby, and copies it
	 * into the working directory, overwriting the version of the file that's
	 * already there if there is one.
	 *
	 * @param each
	 *            the name of the file to be checked out.
	 * @param id
	 *            The id of the Mooby to be checked out.
	 *
	 */
	public void checkout(String each, String id) {
		try {
			File origin = new File(commitFile, id);
			Path from = Paths.get(origin.getCanonicalPath(), each);
			Path to = Paths.get(currDir.getCanonicalPath(), each);
			Files.copy(from, to, options);
		} catch (IOException io) {
			return;
		}
	}

	/**
	 * Creates a new branch with the given name, and points it at the current
	 * head Mooby. A branch is nothing more than a name for a pointer to a Mooby
	 * node into the Chooby. Before you ever call branch, your code should be
	 * running with a default branch called "master". Note: Does NOT immediately
	 * switch to the newly created branch.
	 *
	 * If a branch with the given name already exists, print the error message:
	 * "A branch with that name already exists."
	 *
	 * @param b
	 *            the name of the branch to be added to the branchMap.
	 * @throws IOException
	 *             if the file does not exist.
	 * @throws ClassNotFoundException
	 *             if the class does not exist.
	 *
	 */
	public void branch(String b) {
		chooby.updateBranches(b, chooby.current);
		try {
			saver(this, gitletFile);
		} catch (IOException | ClassNotFoundException a) {
			return;
		}
	}

	/**
	 * Deletes the branch with the given name. This only means to delete the
	 * pointer associated with the branch.
	 *
	 * If a branch with the given name does not exist, aborts. Print the error
	 * message: "A branch with that name does not exist." If you try to remove
	 * the branch you're currently on, aborts, printing the error message:
	 * "Cannot remove the current branch."
	 *
	 * @param b
	 *            String of the name of the branch to be removed.
	 * @throws IOException
	 *             if the file does not exist.
	 * @throws ClassNotFoundException
	 *             if the class does not exist.
	 */
	public void rm_branch(String b) {
		chooby.branchRemove(b);
		try {
			saver(this, gitletFile);
		} catch (IOException | ClassNotFoundException a) {
			return;
		}
	}

	/**
	 * Checks out all the files tracked by the given Mooby. Also moves the
	 * current branch's head to that Mooby node.
	 *
	 * If no commit with the given id exists, print:
	 * "No commit with that id exists."
	 * 
	 * @param n
	 *            String of the Mooby ID to be reset to.
	 * @throws IOException
	 *             if the file does not exist.
	 * @throws ClassNotFoundException
	 *             if the class does not exist.
	 */
	public void reset(String n) {
		Mooby current = chooby.getById(n);
		for (String f : current.files.keySet()) {
			checkout(f, current.files.get(f).toString());
		}
		chooby.updateBranches(chooby.currBranch, current);
		try {
			saver(this, gitletFile);
		} catch (IOException | ClassNotFoundException a) {
			return;
		}
	}

	/**
	 * Merges files from the given branch into the current branch.
	 * 
	 * Any files that have been modified in the given branch since the split
	 * point, but not modified in the current branch since the split point
	 * should be changed to their versions in the given branch.
	 *
	 * Any files that have been modified in the current branch but not in the
	 * given branch since the split point should stay as they are.
	 *
	 * For files that have been modified in both branches since the split point,
	 * the files should stay as they are. However, in addition, the version of
	 * the file from the given branch should be copied into the working
	 * directory with the name [old file name].conflicted.
	 *
	 * 
	 * @param n
	 *            String of the Mooby ID to be reset to.
	 * @throws IOException
	 *             if the file does not exist.
	 * @throws ClassNotFoundException
	 *             if the class does not exist.
	 */
	public void merge(String a, String b) {
		Mooby sMoob = chooby.splitNode(a.hashCode() * b.hashCode());
		Mooby aMoob = chooby.branchMooby(a); // Current branch.
		Mooby bMoob = chooby.branchMooby(b); // Given branch.
		boolean confl = false;

		for (String each : sMoob.files.keySet()) {
			// Files that exist in given branch.
			if (!aMoob.hasFile(each) && bMoob.hasFile(each)) {
				chooby.stageFile(each);
				checkout(each, bMoob.fileID(each).toString());
			}

			// Files that exist in both branches.
			if (aMoob.hasFile(each) && bMoob.hasFile(each)) {
				try {
					File split = new File(commitFile, sMoob.fileID(each)
							.toString());
					Path base = Paths.get(split.getCanonicalPath(), each);
					File inB = new File(commitFile, bMoob.fileID(each)
							.toString());
					Path given = Paths.get(inB.getCanonicalPath(), each);
					File inA = new File(commitFile, aMoob.fileID(each)
							.toString());
					Path curr = Paths.get(inA.getCanonicalPath(), each);

					Path to = Paths.get(currDir.getCanonicalPath(), each);

					// Given and split different, current and split same.
					if (modified(base, given) && !modified(base, curr)) {
						chooby.stageFile(each);
						Files.copy(given, to,
								StandardCopyOption.REPLACE_EXISTING);
					}

					// Given and split different, current and split different.
					if (modified(base, given) && modified(base, curr)) {
						confl = true;
						Path toConflicted = Paths.get(
								currDir.getCanonicalPath(), each
										+ ".conflicted");
						Files.copy(given, toConflicted,
								StandardCopyOption.COPY_ATTRIBUTES);
					}
				} catch (IOException io) {
					return;
				}
			}
		}

		if (!confl) {
			System.out.println("Merged " + a + " with " + b + ".");
		} else {
			conflicted = true;
			System.out.println("Encountered a merge conflict.");
		}

		try {
			saver(this, gitletFile);
		} catch (IOException | ClassNotFoundException e) {
			return;
		}
	}

	/**
	 * Finds the split point of the current branch and the given branch, then
	 * snaps off the current branch at this point, and reattaches the current
	 * branch to the head of the given branch.
	 *
	 * @param b
	 *            the name of the given branch to which the current branch
	 *            should be attached to.
	 *
	 */
	public void rebase(String b) {
		Mooby sMoob = chooby.splitNode(chooby.currBranch.hashCode()
				* b.hashCode());
		Mooby head = chooby.branchMooby(b);
		ArrayList<Mooby> inOrder = new ArrayList<Mooby>();

		Mooby pointer = chooby.current;
		while (pointer != sMoob) {
			inOrder.add(0, pointer);
			pointer = pointer.previous;
		}

		// Makes changes to each node in current branch after split node.
		for (Mooby each : inOrder) {

			// Files that exist in split node.
			for (String file : sMoob.files.keySet()) {
				File split = new File(commitFile, sMoob.fileID(file).toString());
				File given = new File(commitFile, head.fileID(file).toString());
				File eFile = new File(commitFile, each.fileID(file).toString());

				// File exists in given branch head commit
				// and current branch head commit.
				if (head.hasFile(file) && each.hasFile(file)) {
					try {
						Path m = Paths.get(split.getCanonicalPath(), file);
						Path a = Paths.get(given.getCanonicalPath(), file);

						Path e = Paths.get(eFile.getCanonicalPath(), file);

						// Given branch modified since split node,
						// current branch not modified since split node.
						if (modified(m, a) && !modified(m, e)) {
							each.files.put(file, head.fileID(file));
						}
					} catch (IOException io) {
						return;
					}
				}
			}

			// File exists in given branch head commit but not split node
			// or current branch head commit.
			for (String file : head.files.keySet()) {
				if (!each.hasFile(file)) {
					each.files.put(file, head.fileID(file));
				}
			}
			commit(each.message, new HashMap<String, Integer>(each.files),
					"copy");
		}
	}

	/** Stores the state of gitlet. */
	private static class Chooby implements Serializable {
		/** Map of branches and the node at the head of the branch. */
		HashMap<String, Mooby> branchMap;

		/** Staged files, untracked files. */
		ArrayList<String> stagedFiles, untrackedFiles;

		/** Commit messages. */
		HashMap<String, ArrayList<Integer>> messageHistory;

		/** Mooby history by id and split nodes. */
		HashMap<Integer, Mooby> moobyHistory, splitNodes;

		/** Current Mooby. */
		Mooby current;

		/** Count for commit IDs. */
		Integer idCount;

		/** Current branch. */
		String currBranch;

		/** Initializing a Chooby. */
		private Chooby() {
			branchMap = new HashMap<String, Mooby>();
			stagedFiles = new ArrayList<String>();
			untrackedFiles = new ArrayList<String>();
			splitNodes = new HashMap<Integer, Mooby>();
			messageHistory = new HashMap<String, ArrayList<Integer>>();
			moobyHistory = new HashMap<Integer, Mooby>();
			current = null;
			idCount = 0;
			currBranch = "master";
		}

		/**
		 * Checks to see whether the Chooby has the given branch.
		 *
		 * @param b
		 *            name of the branch to be tested
		 * @return boolean
		 */
		private boolean branchExists(String b) {
			return branchMap.containsKey(b);
		}

		/**
		 * Checks to see whether the Chooby has the given file.
		 *
		 * @param f
		 *            name of the file to be checked
		 * @return boolean
		 */
		private boolean hasFile(String f) {
			return current.hasFile(f);
		}

		/**
		 * Gets the Mooby from the map of Moobys.
		 *
		 * @param n
		 *            name of the ID of the Mooby to be retrieved.
		 * @return Mooby
		 */
		private Mooby getById(String n) {
			return moobyHistory.get(Integer.valueOf(n));
		}

		/**
		 * Retrieves the message from the HashMap of messages.
		 *
		 * @param msg
		 *            the string of the msg to be retrieved.
		 * @return ArrayList<Integer>
		 */
		private ArrayList<Integer> findMessage(String msg) {
			return messageHistory.get(msg);
		}

		/**
		 * Adds the given file to the stagedFiles ArrayList.
		 *
		 * @param f
		 *            the name of the file to be added to the stagedFiles
		 *            ArrayList.
		 */
		private void stageFile(String f) {
			stagedFiles.add(f);
		}

		/**
		 * Marks the file for untracking by adding the given file to the
		 * untrackedFiles ArrayList.
		 *
		 * @param f
		 *            the name of the file to be added to the untrackedFiles
		 *            ArrayList.
		 */
		private void untrackFile(String f) {
			untrackedFiles.add(f);
		}

		/**
		 * Removes the file from the stagedFiles ArrayList.
		 *
		 * @param f
		 *            the name of the file to be removed from the stagedFiles
		 *            ArrayList.
		 */
		private void rmStaged(String f) {
			stagedFiles.remove(f);
		}

		/**
		 * Removes the file from the untrackedFiles ArrayList.
		 *
		 * @param f
		 *            the name of the file to be removed from the untrackedFiles
		 *            ArrayList.
		 */
		private void rmUntracked(String f) {
			untrackedFiles.remove(f);
		}

		/**
		 * Checks to see if the file is contained in the stagedFiles ArrayList.
		 *
		 * @param f
		 *            the name of the file to be checked in the stagedFiles
		 *            ArrayList.
		 * @return boolean
		 */
		private boolean inStaging(String f) {
			return stagedFiles.contains(f);
		}

		/**
		 * Checks to see if the file is contained in the untrackedFiles
		 * ArrayList.
		 *
		 * @param f
		 *            the name of the file to be checked in the untrackedFiles
		 *            ArrayList.
		 * @return boolean
		 */
		private boolean inUntracking(String f) {
			return untrackedFiles.contains(f);
		}

		/**
		 * Adds a new branch to the branchMap.
		 *
		 * @param b
		 *            the name of the branch to be added to the branchMap.
		 * @param curr
		 *            the current Mooby where the new branch begins.
		 */
		private void updateBranches(String b, Mooby curr) {
			branchMap.put(b, curr);
		}

		/**
		 * Retrieves a branch from the branchMap.
		 *
		 * @param b
		 *            the name of the branch to be retrieved from the branchMap.
		 * @return Mooby
		 */
		private Mooby branchMooby(String b) {
			return branchMap.get(b);
		}

		/**
		 * Removes a branch from the branchMap.
		 *
		 * @param b
		 *            the name of the branch to be removed from the branchMap.
		 * @return Mooby
		 */
		private Mooby branchRemove(String b) {
			return branchMap.remove(b);
		}

		/**
		 * Retrieves the splitNode from the splitNode Map.
		 *
		 * @param h
		 *            the hashcode associated with the splitNode to be
		 *            retrieved.
		 * @return Mooby
		 */
		private Mooby splitNode(Integer h) {
			return splitNodes.get(h);
		}

		/**
		 * Adds a new splitNode to the splitNode map.
		 *
		 * @param code
		 *            the hashcode associated with the splitNode to be added.
		 * @param m
		 *            the Mooby that is the splitNode.
		 */
		private void updateSplits(Integer code, Mooby m) {
			splitNodes.put(code, m);
		}

		/**
		 * Adds a new Mooby to the history of Moobys map.
		 *
		 * @param id
		 *            the id of the Mooby to be added to the historyMap
		 * @param m
		 *            the Mooby to be added to the history map of Moobys.
		 */
		private void updateHistory(Integer id, Mooby m) {
			moobyHistory.put(id, m);
		}

		/**
		 * Adds a new file to a the map of a given Moobys files.
		 *
		 * @param f
		 *            the name of the file to be added.
		 * @param id
		 *            the id of the commit from which the file came from.
		 */
		private void addMFiles(String f, Integer id) {
			current.files.put(f, id);
		}

		/**
		 * Removes a file from the map of the current Moobys files.
		 *
		 * @param f
		 *            the name of the file to be removed.
		 */
		private void rmMFiles(String f) {
			current.files.remove(f);
		}

		/**
		 * Retrieves a file from the map of the current Moobys files.
		 *
		 * @param f
		 *            the name of the file to be retrieved.
		 * @return Integer id the commit
		 */
		private Integer getMFile(String f) {
			return current.files.get(f);
		}

		/**
		 * Updates the commit message history map.
		 *
		 * @param str
		 *            the message string
		 * @param id
		 *            an Integer id of the commit
		 */
		private void updateCommit(String str, Integer id) {
			ArrayList<Integer> idList = messageHistory.get(str);
			if (idList == null) {
				idList = new ArrayList<Integer>();
				idList.add(id);
				messageHistory.put(str, idList);
			} else {
				idList.add(id);
			}
		}
	}

	/** Nodes for each commit. */
	private static class Mooby implements Serializable {
		/** Previous Mooby. */
		Mooby previous;

		/** Commit message and date. */
		String message, date;

		/** Commit ID. */
		Integer id;

		/** Inherited files and associated commit IDs. */
		HashMap<String, Integer> files;

		/** */
		private Mooby(Mooby p, String m, String d, Integer i,
				HashMap<String, Integer> f) {
			previous = p;
			message = m;
			date = d;
			id = i;
			files = f;
		}

		/**
		 * Checks to see whether the Mooby has a given file.
		 *
		 * @param f
		 *            name of the file to be tested.
		 * @return boolean
		 */
		private boolean hasFile(String f) {
			return files.containsKey(f);
		}

		/**
		 * Getter method for the idString.
		 *
		 * @return the string of the ID of the Mooby.
		 */
		private String idString() {
			return id.toString();
		}

		/**
		 * Getter method for the fileID of a file.
		 *
		 * @return the ID commit number of the file.
		 */
		private Integer fileID(String f) {
			return files.get(f);
		}
	}

	/**
	 * Creates a timestamp to be used in each commit.
	 *
	 * @return the timestamp
	 */
	private static String timestamp() {
		Calendar t = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = format.format(t.getTime());
		return time;
	}

	/**
	 * Use for merge. Return true if files are the same.
	 *
	 * @param a
	 *            the path of the first file to be compared.
	 * @param b
	 *            the path of the second file to be compared.
	 * @throws IOException
	 *             if the file does not exist.
	 */
	private boolean modified(Path a, Path b) {
		try {
			List<String> readA = Files
					.readAllLines(a, Charset.defaultCharset());
			List<String> readB = Files
					.readAllLines(b, Charset.defaultCharset());
			return !(readA.equals(readB));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}