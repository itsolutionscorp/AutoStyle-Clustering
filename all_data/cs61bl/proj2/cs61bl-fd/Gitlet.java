import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Creates a version control system, Gitlet, in the directory in which it it's
 * initialized. Keeps track of the structure and contents of Gitlet, and handles
 * user input to control versions of the files in the user's directory.
 * 
 * @author Rohan Nijhawan, Rocky Kamen-Rubio, Jeffrey Tang, and Mira Chiu
 * 
 */

public class Gitlet implements Serializable {

	/**
	 * Initializes an instance of the Gitlet class. Creates the initial internal
	 * structure of Gitlet.
	 * 
	 * @param g
	 *            a Gitlet object according to which the new Gitlet object is
	 *            initialized
	 */
	public Gitlet(Gitlet g) {
		id = g.id;
		branches = (HashMap<String, CommitNode>) g.branches.clone();
		globalLog = (HashMap<Integer, CommitNode>) g.globalLog.clone();
		messages = (HashMap<String, ArrayList<Integer>>) g.messages.clone();
		conflicted = g.conflicted;
		head = g.head;
		if (g.currBranch != null) {
			currBranch = new String(g.currBranch);
		} else {
			currBranch = g.currBranch;
		}
	}

	/**
	 * Initializes an instance of the Gitlet class. Creates the initial internal
	 * structure of Gitlet.
	 */
	public Gitlet() {
		id = 0;
		branches = new HashMap<String, CommitNode>();
		globalLog = new HashMap<Integer, CommitNode>();
		messages = new HashMap<String, ArrayList<Integer>>();
		conflicted = false;
		head = null;
		currBranch = null;
	}

	/**
	 * Runs when Gitlet is run in the command line. Calls other methods
	 * depending on the user input in the command line. Ties together different
	 * functionality of Gitlet. Serializes and loads previous Gitlet states, and
	 * uses them to create the new Gitlet state, upon which commands will be
	 * carried out.
	 * 
	 * @param args
	 *            A String representation of user input in the command line
	 *            after "java Gitlet". Will be parsed to determine which methods
	 *            to call.
	 */
	public static void main(String[] args) {
		Gitlet previousGit = tryLoadingMyState();
		Gitlet myGitlet;
		if (previousGit == null) {
			myGitlet = new Gitlet();
		} else {
			myGitlet = new Gitlet(previousGit);
		}
		if (args.length == 0) {
			System.out.println("Please enter a command.");
		} else if (args.length == 1) {
			argsLenOne(myGitlet, args[0]);
		} else if (args.length == 2) {
			argsLenTwo(myGitlet, args[0], args[1]);
		} else if (args.length == 3) {
			if (args[0].equals("checkout")) {
				myGitlet.checkout(Integer.parseInt(args[1]), args[2]);
			}
		} else {
			System.out.println("No command with that name exists.");
		}
		saveMyState(myGitlet);
	}

	/**
	 * Called by the main method to help enact the correct behaviors for Gitlet.
	 * Calls other methods and passes arguments to them depending on what
	 * zerothIndexContents is.
	 * 
	 * @param g
	 *            The Gitlet object instantiated in the main method. Used to
	 *            call methods to implement Git-like behavior for the version
	 *            control system in the directory the user is giving commands
	 *            from.
	 * @param zerothIndexContents
	 *            The String command line argument passed in when the Gitlet
	 *            class is called with a one-word String argument.
	 */
	private static void argsLenOne(Gitlet g, String zerothIndexContents) {
		Gitlet myGitlet = g;
		switch (zerothIndexContents) {
		case ("init"): {
			if (myGitlet.conflicted == true) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			myGitlet.init();
			break;
		}
		case ("log"): {
			myGitlet.log();
			break;
		}
		case ("global-log"): {
			myGitlet.globalLog();
			break;
		}
		case ("status"): {
			myGitlet.status();
			break;
		}
		case ("commit"): {
			System.out.println("Please enter a commit message.");
			break;
		}
		}
	}

	/**
	 * Called by the main method to help enact the correct behaviors for Gitlet.
	 * Calls other methods and passes arguments to them depending on what zeroth
	 * and first are.
	 * 
	 * @param g
	 *            The Gitlet object instantiated in the main method. Used to
	 *            call methods to implement Git-like behavior for the version
	 *            control system in the directory the user is giving commands
	 *            from.
	 * @param zeroth
	 *            A String that contains the first word of the command line
	 *            argument passed in when the Gitlet class is called with a
	 *            two-word String argument.
	 * @param first
	 *            A String that contains the second word of the command line
	 *            argument passed in when the Gitlet class is called with a
	 *            two-word String argument.
	 */
	private static void argsLenTwo(Gitlet g, String zeroth, String first) {
		Gitlet myGitlet = g;
		switch (zeroth) {
		case ("add"): {
			myGitlet.add(first);
			break;
		}
		case ("commit"): {
			myGitlet.commit(first);
			break;
		}
		case ("rm"): {
			myGitlet.rm(first);
			break;
		}
		case ("find"): {
			myGitlet.find(first);
			break;
		}
		case ("checkout"): {
			myGitlet.checkout(first);
			break;
		}
		case ("branch"): {
			myGitlet.branch(first);
			break;
		}
		case ("rm-branch"): {
			myGitlet.rmBranch(first);
			break;
		}
		case ("reset"): {
			if (myGitlet.conflicted == true) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			myGitlet.reset(Integer.parseInt(first));
			break;
		}
		case ("merge"): {
			if (myGitlet.conflicted == true) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			myGitlet.merge(first);
			break;
		}
		case ("rebase"): {
			if (myGitlet.conflicted == true) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			myGitlet.rebase(first);
			break;
		}
		}
	}

	/**
	 * Initializes a Gitlet version control system in the current directory.
	 */
	public void init() {
		File gitletFolder = new File(".gitlet");
		if (!gitletFolder.exists()) {
			gitletFolder.mkdir();
			new File(".gitlet/staged").mkdir(); // makes a new staging area
												// folder
			new File(".gitlet/marked").mkdir(); // makes a new folder for marked
												// for untracking
			new File(".gitlet/storage").mkdir();
			currBranch = "master";
			commit("initial commit");
		} else {
			System.out.println("A gitlet version control system already exists in the current directory.");
		}
	}

	/**
	 * Marks the file fileName for staging to be committed. If fileName was
	 * previously marked for removal, unmarks it for removal.
	 * 
	 * @param fileName
	 *            a String containing the name of the file to be added/unmarked
	 *            for removal
	 */
	public void add(String fileName) {
		File toBeAdded = new File(fileName);
		File present = new File(".gitlet/marked/" + fileName);
		if (present.exists()) {
			try {
				Files.delete(present.toPath());
				return;
			} catch (IOException e) {
				return;
			}
		}
		int indexOfLastSlash = fileName.lastIndexOf('/');
		File destination;
		if (indexOfLastSlash != -1) {
			destination = new File(".gitlet/staged/" + fileName.substring(0, indexOfLastSlash + 1));
			destination.mkdirs();
		}
		destination = new File(".gitlet/staged/" + fileName);
		try {
			Files.copy(toBeAdded.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.out.println("File does not exist.");
		}
	}

	/**
	 * Saves a backup of the files that have been staged when the method is
	 * called, and tracks these files throughout later commits. Commits can be
	 * accessed at a later time via commit IDs or the message with which the
	 * commit was saved.
	 * 
	 * @param m
	 *            a String containing the commit message for the commit that
	 *            will be made
	 */
	public void commit(String m) {
		String trimmed = new String(m).trim();
		if (trimmed.length() == 0) {
			System.out.println("Please enter a commit message.");
		}
		HashMap<File, File> stagedFiles = new HashMap<File, File>();
		File staged = new File(".gitlet/staged");
		ArrayList<File> filesStaged = getAllFiles(staged);
		File marked = new File(".gitlet/marked");
		ArrayList<File> filesMarked = getAllFiles(marked);
		if (filesMarked.size() == 0 && filesStaged.size() == 0 && id != 0) {
			System.out.println("No changes added to the commit.");
			return;
		}
		HashMap<File, File> combined = new HashMap<File, File>();
		if (id != 0) {
			HashMap<File, File> previous = (HashMap<File, File>) branches.get(currBranch).getFiles().clone();
			for (File file : filesMarked) {
				previous.remove(file);
				try {
					Files.delete(file.toPath());
				} catch (IOException e) {
					return;
				}
			}
			combined.putAll(previous);
		}
		for (File file : filesStaged) {
			try {
				int indexOfLastSlash = file.toString().lastIndexOf('/');
				File destination;
				if (indexOfLastSlash != -1) {
					destination = new File(".gitlet/storage/" + file.toString().substring(0, indexOfLastSlash + 1));
					destination.mkdirs();
				}
				destination = convertIntoHash(id, file);
				Files.copy(new File(".gitlet/staged/" + file.toString()).toPath(), destination.toPath(),
						StandardCopyOption.REPLACE_EXISTING);
				stagedFiles.put(new File(file.toString()), destination);
			} catch (IOException e) {
				return;
			}
		}
		deleteAllFiles(new File(".gitlet/staged"));
		combined.putAll(stagedFiles);
		CommitNode node = new CommitNode(id, branches.get(currBranch), m, combined);
		branches.put(currBranch, node);
		globalLog.put(id, node);
		if (messages.containsKey(m)) {
			ArrayList<Integer> idList = messages.get(m);
			idList.add(id);
		} else {
			ArrayList<Integer> newIDList = new ArrayList<Integer>();
			newIDList.add(id);
			messages.put(m, newIDList);
		}
		head = node;
		id++;
		if (conflicted == true) {
			conflicted = false;
		}
	}

	/**
	 * Marks the file fileName for untracking from future commits. If fileName
	 * was staged, removes fileName from the staging area.
	 * 
	 * @param fileName
	 *            a String containing the name of the file to be removed
	 */
	public void rm(String fileName) {
		File present = new File(".gitlet/staged/" + fileName);
		if (present.exists()) {
			try {
				Files.delete(present.toPath());
			} catch (IOException e) {
				return;
			}
		} else {
			if (!head.getFiles().containsKey(new File(fileName))) {
				System.out.println("No reason to remove the file.");
				return;
			}
			File toBeRemoved = new File(fileName);
			File destination;
			int indexOfLastSlash = fileName.lastIndexOf('/');
			if (indexOfLastSlash != -1) {
				destination = new File(".gitlet/staged/" + fileName.substring(0, indexOfLastSlash + 1));
				destination.mkdirs();
			}
			destination = new File(".gitlet/marked/" + fileName);
			try {
				Files.copy(toBeRemoved.toPath(), destination.toPath());
			} catch (IOException e) {
				System.out.println("No reason to remove the file.");
			}
		}
	}

	/**
	 * Starting at the head commit, prints information about each commit (commit
	 * ID, time commit was made, commit message) backwards along the commit tree
	 * until the initial commit.
	 */
	public void log() {
		CommitNode point = head;
		while (point != null) {
			System.out.println("===");
			System.out.println("Commit " + point.getID());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
			System.out.println(format.format(point.getDate()));
			System.out.println(point.getMessage());
			System.out.println();
			point = point.getPrev();
		}
	}

	/**
	 * Prints the commit ID, time the commit was made, and commit message for
	 * all commits ever made.
	 */
	public void globalLog() {
		Iterator<Integer> log = globalLog.keySet().iterator();
		while (log.hasNext()) {
			int toBePrinted = log.next();
			CommitNode node = globalLog.get(toBePrinted);
			System.out.println("===");
			System.out.println("Commit " + toBePrinted);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
			System.out.println(format.format(node.getDate()));
			System.out.println(node.getMessage());
			System.out.println();
		}
	}

	/**
	 * Prints out the IDs on separate lines of all commits that were made with
	 * the given commit message.
	 * 
	 * @param commitMsg
	 *            a String containing the commit message
	 */
	public void find(String commitMsg) {
		if (messages.containsKey(commitMsg)) {
			for (Integer id : messages.get(commitMsg)) {
				System.out.println(id);
			}
		} else {
			System.out.println("Found no commit with that message.");
		}
	}

	/**
	 * Displays what branches currently exist and marks the current branch with
	 * a *. Also displays all files staged and marked for untracking.
	 */
	public void status() {
		System.out.println("=== Branches ===");
		Iterator<String> branchesIter = branches.keySet().iterator();
		while (branchesIter.hasNext()) {
			String toBePrinted = branchesIter.next();
			if (currBranch.equals(toBePrinted))
				System.out.print("*");
			System.out.println(toBePrinted);
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		File staged = new File(".gitlet/staged");
		ArrayList<File> files = getAllFiles(staged);
		for (File file : files) {
			System.out.println(file.toString());
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		File marked = new File(".gitlet/marked");
		files = getAllFiles(marked);
		for (File file : files) {
			System.out.println(file.toString());
		}
	}

	/**
	 * If name is a file name, takes the version of the file in the head commit
	 * and puts it in the working directory, potentially overwriting the version
	 * of the file in the working directory, if there is one already there. If
	 * name is a branch name, takes all the files in the head commit of the
	 * given branch and puts them in the working directory, potentially
	 * overwriting files. The given branch will now be considered the current
	 * branch.
	 * 
	 * @param name
	 *            A String containing either the name of a file to be put into
	 *            the working directory or the name of a branch from which files
	 *            at the head of the branch are taken to be put into the working
	 *            directory.
	 */
	public void checkout(String name) {
		if (branches.containsKey(name)) {
			if (conflicted == true) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
				return;
			}
			if (name.equals(currBranch)) {
				System.out.println("No need to checkout the current branch.");
				return;
			}
			HashMap<File, File> files = branches.get(name).getFiles();
			Iterator<File> filesIter = files.keySet().iterator();
			while (filesIter.hasNext()) {
				File currentFile = filesIter.next();
				int indexOfLastSlash = name.toString().lastIndexOf('/');
				File destination;
				if (indexOfLastSlash != -1) {
					destination = new File(name.toString().substring(0, indexOfLastSlash + 1));
					destination.mkdirs();
				}

				destination = new File(name);
				try {
					Files.copy(files.get(currentFile).toPath(), currentFile.toPath(),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					return;
				}
			}
			currBranch = name;
			head = branches.get(name);

		} else {
			if (!name.contains(".")) {
				System.out.println("File does not exist in the most recent commit, or no such branch exists.");
				return;
			}
			File targetFile = head.getFiles().get(new File(name));
			if (!(targetFile == null) && targetFile.exists()) {
				int indexOfLastSlash = name.toString().lastIndexOf('/');
				File destination;
				if (indexOfLastSlash != -1) {
					destination = new File(name.toString().substring(0, indexOfLastSlash + 1));
					destination.mkdirs();
				}

				destination = new File(name);
				try {
					Files.copy(targetFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					return;
				}
			} else {
				System.out.println("File does not exist in the most recent commit, or no such branch exists.");
				return;
			}
		}
	}

	/**
	 * Takes the version of the file in the commit with the given ID and puts it
	 * in the working directory, potentially overwriting the version of the file
	 * in the working directory, if there is one already there.
	 * 
	 * @param id
	 *            An int representing the commit ID of the commit from which to
	 *            copy the version of the file given.
	 * @param name
	 *            A String containing the name of the file to be copied into the
	 *            working directory.
	 */
	public void checkout(int id, String name) {
		if (!globalLog.containsKey(id)) {
			System.out.println("No commit with that id exists.");
			return;
		}
		HashMap<File, File> files = globalLog.get(id).getFiles();
		File targetFile = files.get(new File(name));
		if (targetFile.exists()) {
			int indexOfLastSlash = name.toString().lastIndexOf('/');
			File destination;
			if (indexOfLastSlash != -1) {
				destination = new File(name.toString().substring(0, indexOfLastSlash + 1));
				destination.mkdirs();
			}

			destination = new File(name);
			try {
				Files.copy(targetFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				return;
			}
		} else {
			System.out.println("File does not exist in that commit.");
		}
	}

	/**
	 * Creates a new branch with the given name, branchName, and points it to
	 * the current head node. This method does not make the newly created branch
	 * the active branch.
	 * 
	 * @param branchName
	 *            A String containing the name of the new branch
	 */
	public void branch(String branchName) {
		if (conflicted == true) {
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		if (branches.containsKey(branchName)) {
			System.out.println("A branch with that name already exists.");
			return;
		}
		branches.put(branchName, head);
	}

	/**
	 * Deletes the branch with the given name.
	 * 
	 * @param branchName
	 *            A String containing the name of the branch to be deleted.
	 */
	public void rmBranch(String branchName) {
		if (conflicted == true) {
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		if (currBranch.equals(branchName)) {
			System.out.println("Cannot remove the current branch.");
			return;
		} else if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else {
			branches.remove(branchName);
			return;
		}
	}

	/**
	 * Checks out all the files tracked by the given commit. Moves the current
	 * branch's head to that commit.
	 * 
	 * @param id
	 *            An int representing the commit ID of the commit to reset to.
	 */
	public void reset(int id) {
		if (!globalLog.containsKey(id)) {
			System.out.println("No commit with that id exists.");
			return;
		}

		CommitNode resetHead = globalLog.get(id);
		HashMap<File, File> files = resetHead.getFiles();
		Iterator<File> filesIter = files.keySet().iterator();
		while (filesIter.hasNext()) {
			checkout(id, filesIter.next().toString());
		}
		head = resetHead;
	}

	/**
	 * Merges files from the given branch into the current branch. Files
	 * modified in the given branch but not in the current branch are changed to
	 * their versions in the given branch, then staged. Files modified in the
	 * current branch but not in the given branch remain the same. Files
	 * modified in both branches will stay as they are, but the version of the
	 * file from the given branch will be copied into the working directory with
	 * the name [old file name].conflicted. If no conflicted files are generated
	 * from the merge, a commit will automatically be made with a specified
	 * commit message. If the merge generated at least one .conflicted file,
	 * there will not be an automatic commit made, and Gitlet will be put into a
	 * conflicted state.
	 * 
	 * @param branch
	 *            A String representing the branch to be merged with the current
	 *            branch.
	 */
	public void merge(String branch) {
		if (!branches.containsKey(branch)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (currBranch.equals(branch)) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}
		CommitNode splitPoint = splitPointGetter(head, branches.get(branch));
		HashMap<File, File> currHeadFiles = head.getFiles();
		HashMap<File, File> branchHeadFiles = branches.get(branch).getFiles();
		HashMap<File, File> splitHeadFiles = splitPoint.getFiles();
		Iterator<File> iter = splitHeadFiles.keySet().iterator();
		while (iter.hasNext()) {
			File nextFile = iter.next();
			if (!branchHeadFiles.get(nextFile).equals(splitHeadFiles.get(nextFile))) {
				if (currHeadFiles.get(nextFile).equals(splitHeadFiles.get(nextFile))) {
					mergeHelperSuccess(branchHeadFiles.get(nextFile), nextFile);
				} else {
					mergeHelperFail(currHeadFiles.get(nextFile), nextFile);
				}
			} else if (!currHeadFiles.get(nextFile).equals(splitHeadFiles.get(nextFile))) {
				if (branchHeadFiles.get(nextFile).equals(splitHeadFiles.get(nextFile))) {
					mergeHelperSuccess(currHeadFiles.get(nextFile), nextFile);
				}
			} else {
				mergeHelperFail(currHeadFiles.get(nextFile), nextFile);
			}
		}
		if (conflicted == false) {
			commit("Merged " + currBranch + " with " + branch + ".");
		} else {
			System.out.println("Encountered a merge conflict.");
		}

	}

	/**
	 * Called by the merge method to carry out proper behavior if no files
	 * conflict. Files modified in the given branch but not in the current
	 * branch are changed to their versions in the given branch, then staged.
	 * Files modified in the current branch but not in the given branch remain
	 * the same.
	 * 
	 * @param toBeAdded
	 *            A File object containing the hashed file path of the file to
	 *            be staged.
	 * @param nextFile
	 *            A File object containing the original file path of the file to
	 *            be staged.
	 */
	public void mergeHelperSuccess(File toBeAdded, File nextFile) {
		int indexOfLastSlash = nextFile.toString().lastIndexOf('/');
		File destination;
		if (indexOfLastSlash != -1) {
			destination = new File(".gitlet/staged/" + nextFile.toString().substring(0, indexOfLastSlash + 1));
			destination.mkdirs();
		}
		destination = new File(".gitlet/staged/" + convertFromHash(toBeAdded));
		try {
			Files.copy(toBeAdded.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.out.println("File does not exist.");
		}
	}

	/**
	 * Called by the merge method to carry out proper behavior if files
	 * conflict. Files modified in both the current and given branches to merge
	 * will stay as they are, but the version of the file from the given branch
	 * is copied into the working directory with the name [old file
	 * name].conflicted. Gitlet is put into a conflicted state.
	 * 
	 * @param toBeAdded
	 *            A File object containing the hashed file path of the file to
	 *            be staged.
	 * @param nextFile
	 *            A File object containing the original file path of the file to
	 *            be staged.
	 */
	public void mergeHelperFail(File toBeAdded, File nextFile) {
		conflicted = true;
		File destination = new File(convertFromHash(toBeAdded, true).toString());
		try {
			Files.copy(toBeAdded.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.out.println("File does not exist.");
		}
	}

	/**
	 * A helper method for the merge and rebase methods that finds and returns
	 * the split point of two branches, which is a commit node.
	 * 
	 * @param one
	 *            the head commit of the current branch
	 * @param two
	 *            the head commit of the branch given to the merge/rebase method
	 * @return the CommitNode that is the split point of the two branches whose
	 *         heads are passed in as one and two.
	 */
	private CommitNode splitPointGetter(CommitNode one, CommitNode two) {
		if (one.getID() == two.getID()) {
			return one;
		} else if (one.getID() > two.getID()) {
			return splitPointGetter(one.getPrev(), two);
		} else {
			return splitPointGetter(one, two.getPrev());
		}
	}

	/**
	 * Replays the current branch to be based on the head node of the given
	 * branch. Moves the current branch's pointer to the copies that have been
	 * rebased to the given branch. If the commit at the front of the given
	 * branch has files that have been modified since the split point of the
	 * given and current branches, these modifications are propagated throughout
	 * the replay, up until a commit node is reached in the replay that has its
	 * own modified version of the file since the split point. If the current
	 * branch pointer is in the history of the given branch, rebase moves the
	 * current branch pointer to the commit node the given branch references,
	 * and does not replay any commits.
	 * 
	 * @param branch
	 *            A String containing the name of the branch to replay commits
	 *            to.
	 */
	public void rebase(String branch) {
		if (!branches.containsKey(branch)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (currBranch.equals(branch)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}
		CommitNode splitPoint = splitPointGetter(head, branches.get(branch));
		if (branches.get(branch).equals(splitPoint)) {
			System.out.println("Already up-to-date.");
			return;
		} else if (head.equals(splitPoint)) {
			head = branches.get(branch);
			branches.put(currBranch, head);
			return;
		}
		HashMap<File, File> currHeadFiles = head.getFiles();
		HashMap<File, File> branchHeadFiles = branches.get(branch).getFiles();
		HashMap<File, File> splitHeadFiles = splitPoint.getFiles();
		ArrayList<CommitNode> oldBranch = new ArrayList<CommitNode>();
		ArrayList<CommitNode> rebasedBranch = new ArrayList<CommitNode>();
		CommitNode initialCopyPointer = head;
		while (!initialCopyPointer.equals(splitPoint)) {
			oldBranch.add(initialCopyPointer);
			initialCopyPointer = initialCopyPointer.getPrev();
		}
		for (int index = 0; index < oldBranch.size(); index++) {
			CommitNode temp;
			if (rebasedBranch.isEmpty()) {
				CommitNode tempBranch = branches.get(branch);
				temp = new CommitNode(id, tempBranch, oldBranch.get(index).getMessage(), tempBranch.getFiles());
			} else {
				CommitNode tempBranch = oldBranch.get(index - 1);
				temp = new CommitNode(id, tempBranch, oldBranch.get(index).getMessage(), tempBranch.getFiles());
			}
			id++;
			rebasedBranch.add(temp);
		}
		CommitNode inputBranchNode = branches.get(branch);
		HashMap<File, File> inputBranchNodeFiles = branches.get(branch).getFiles();
		for (int index = 0; index < rebasedBranch.size(); index++) {
			Iterator<File> iter = splitHeadFiles.keySet().iterator();
			HashMap<File, File> rebasedNodeFiles = rebasedBranch.get(index).getFiles();
			while (iter.hasNext()) {
				File nextSplitNodeFile = iter.next();
				if (rebasedNodeFiles.get(nextSplitNodeFile).equals(splitHeadFiles.get(nextSplitNodeFile))) {
					if (!inputBranchNodeFiles.get(nextSplitNodeFile).equals(splitHeadFiles.get(nextSplitNodeFile))) {
						rebasedNodeFiles.put(nextSplitNodeFile, inputBranchNodeFiles.get(nextSplitNodeFile));
					}
				}
			}
		}
		head = rebasedBranch.get(rebasedBranch.size() - 1);
		branches.put(currBranch, head);
	}

	/**
	 * Tries to load in the state of the previously saved Gitlet instance from a
	 * serializable file.
	 * 
	 * @return a Gitlet instance that contains the contents of the previously
	 *         serialized Gitlet instance. Null if there is no previously
	 *         serialized Gitlet instance.
	 */
	private static Gitlet tryLoadingMyState() {
		Gitlet myState = null;
		File myStateFile = new File(".gitlet/Gitlet.ser");
		if (myStateFile.exists()) {
			try {
				FileInputStream fileIn = new FileInputStream(myStateFile);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				myState = (Gitlet) objectIn.readObject();
			} catch (IOException e) {
				String msg = "IOException while loading Gitlet.";
				System.out.println(msg);
			} catch (ClassNotFoundException e) {
				String msg = "ClassNotFoundException while loading Gitlet.";
				System.out.println(msg);
			}
		}
		return myState;
	}

	/**
	 * Tries to save the state of the current Gitlet instance to a serializable
	 * file.
	 * 
	 * @param myState
	 *            The current Gitlet instance that represents the state of the
	 *            version control system in the current directory.
	 */
	private static void saveMyState(Gitlet myState) {
		if (myState == null) {
			return;
		}
		try {
			File myStateFile = new File(".gitlet/Gitlet.ser");
			FileOutputStream fileOut = new FileOutputStream(myStateFile);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(myState);
		} catch (IOException e) {
			String msg = "IOException while saving myState.";
			System.out.println(msg);
		}
	}

	/**
	 * A helper method that converts a file name into a hashed version of the
	 * file name.
	 * 
	 * @param id
	 *            An int representing the commit ID of the commit node in which
	 *            the file will be stored.
	 * @param originalFile
	 *            A File representing the original file path to be converted to
	 *            a File with a hashed path name.
	 * @return the File with the hashed path
	 */
	public static File convertIntoHash(int id, File originalFile) {
		String rawFileName = originalFile.toString();
		int dot = rawFileName.toString().indexOf('.');
		String extension = rawFileName.substring(dot);
		rawFileName = rawFileName.substring(0, dot) + hash + id + extension;
		return new File(".gitlet/storage/" + rawFileName);
	}

	/**
	 * A helper method that converts a file name from the hashed version into
	 * the unhashed version of the name.
	 * 
	 * @param originalFile
	 *            A File representing the hashed file path to be converted to a
	 *            File with an unhashed path name.
	 * @return the File with the unhashed path
	 */
	public static File convertFromHash(File originalFile) {
		String rawFileName = originalFile.toString().substring(16);
		int koalaHead = rawFileName.indexOf(hash);
		int dot = rawFileName.toString().indexOf('.');
		String extension = rawFileName.substring(dot);
		return new File(rawFileName.substring(0, koalaHead) + extension);
	}

	/**
	 * A helper method that converts a file name from the hashed version into
	 * the unhashed version of the name. If Gitlet is in a conflicted state,
	 * adds .conflicted to the end of the file path.
	 * 
	 * @param originalFile
	 *            A File representing the hashed file path to be converted to a
	 *            File with an unhashed path name.
	 * @param conflicted
	 *            A boolean representing whether or not Gitlet is in a
	 *            conflicted state.
	 * @return the File with the unhashed path
	 */
	public static File convertFromHash(File originalFile, boolean conflicted) {
		String rawFileName = originalFile.toString().substring(16);
		int koalaHead = rawFileName.indexOf(hash);
		int dot = rawFileName.toString().indexOf('.');
		String extension = rawFileName.substring(dot);
		return new File(rawFileName.substring(0, koalaHead) + extension + ".conflicted");
	}

	/**
	 * Gets all files inside the specified directory, including files inside
	 * other folders in the directory.
	 * 
	 * @param startingDirectory
	 *            A File representing the directory in which to start getting
	 *            files.
	 * @return an ArrayList<File> containing all files in the directory
	 */
	public ArrayList<File> getAllFiles(File startingDirectory) {
		ArrayList<File> toReturn = new ArrayList<File>();
		File[] files = startingDirectory.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				toReturn.addAll(0, getAllFiles(f));
			} else {
				int indexOfSlash = f.toString().indexOf('/', 9);
				toReturn.add(new File(f.toString().substring(indexOfSlash + 1)));
			}
		}
		return toReturn;
	}

	/**
	 * Deletes the file and all files inside it, if it is a directory.
	 * 
	 * @param startingDirectory
	 *            A File representing the directory at which to begin
	 *            recursively deleting.
	 */
	public void deleteAllFiles(File startingDirectory) {
		File[] files = startingDirectory.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				deleteAllFiles(f);
				try {
					Files.delete(f.toPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					Files.delete(f.toPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private int id;
	private HashMap<String, CommitNode> branches;
	private HashMap<Integer, CommitNode> globalLog;
	private HashMap<String, ArrayList<Integer>> messages;
	private boolean conflicted;
	private CommitNode head;
	private String currBranch;
	private static final String hash = "(0_o_0)";

}