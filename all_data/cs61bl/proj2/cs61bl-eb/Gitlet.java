import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author CS61BL-EC, EB, ES, EJ 
 *
 */
public class Gitlet implements Serializable {
	private static final long serialVersionUID = -3532639254716460386L;
	static final String GITLET_PATH = ".gitlet/";
	static final String STAGE_PATH = ".gitlet/stage/";
	static final String CONFLICT_PATH = ".conflicted";
	// branches: mapping from name to pointer
	// name is unique
	// accessed in Gitlet
	private HashMap<String, Branch> branches;
	// curr_branch: current branch pointer
	// accessed in Gitlet and Branch (constructor)
	Branch curr_branch;
	// commits_name_id: mapping from name to id
	// name is not unique, so need ArrayList of corresponding ids
	// accessed in
	HashMap<String, ArrayList<Integer>> commits_name_id;
	// commits_id_pointer: mapping from id to pointer
	// id is unique
	// accessed in Gitlet and Branch
	HashMap<Integer, Commit> commits_id_pointer;
	// removed: untracked files
	// reset to empty after each commit (in Branch)
	// accessed in Gitlet and Branch
	HashSet<String> removed;
	// num_of_commit: number of commits
	// accessed in Gitlet and Branch
	int num_of_commit;
	private boolean conflict;
	private static final DateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**************************************** Going Remote ****************************************/

	private HashMap<String, Remote> remotes;

	/**************************************** Going Remote ****************************************/

	/**
	 * Default constructor - constructs Gitlet 
	 */
	public Gitlet() {
		this.branches = new HashMap<String, Branch>();
		this.num_of_commit = 0;
		this.commits_name_id = new HashMap<String, ArrayList<Integer>>();
		this.commits_id_pointer = new HashMap<Integer, Commit>();
		// create a new (master) Branch object with initial commit
		this.curr_branch = new Branch("master", this);
		// put curr_branch into branches
		this.branches.put("master", curr_branch);
		this.removed = new HashSet<String>();
		this.conflict = false;
		/**************************************** Going Remote ****************************************/
		this.remotes = new HashMap<String, Remote>();
		/**************************************** Going Remote ****************************************/
	}

	/**
	 * Initiates the program and states what happens with respective command prompts  
	 * @param args
	 * 				array of command-line prompts  
	 */ 
	public static void main(String[] args) {
		Gitlet g = deserialize(GITLET_PATH + "berkeley.ser");
		if (args.length == 0) {
			printError("Please enter a command.");
		} else if ((args[0].equals("init"))) {
			g = init();
		} else if (args[0].equals("log")) {
			g.log();
		} else if (args[0].equals("add")) {
			g.add(args[1]);
		} else if (args[0].equals("commit")) {
			g.conflict = false;
			if (args.length == 1) {
				// every commit must have a non-blank message
				printError("Please enter a commit message.");
			}
			g.curr_branch.commit(args[1], g);
		} else if (args[0].equals("rm")) {
			g.curr_branch.remove(args[1], g);
		} else if (args[0].equals("global-log")) {
			g.globalLog();
		} else if (args[0].equals("status")) {
			g.status();
		} else if (args[0].equals("find")) {
			g.find(args[1]);
		} else if (args[0].equals("branch")) {
			if (g.conflict) {
				printError("Cannot do this command until the merge conflict has been resolved.");
			}
			g.branch(args[1]);
		} else if (args[0].equals("rm-branch")) {
			if (g.conflict) {
				printError("Cannot do this command until the merge conflict has been resolved.");
			}
			g.rmBranch(args[1]);
		} else if (args[0].equals("checkout")) {
			if (args.length == 2) {
				g.checkoutTwoArgs(args[1]);
			} else {
				g.checkoutThreeArgs(Integer.parseInt(args[1]), args[2]);
			}
		} else if (args[0].equals("reset")) {
			if (g.conflict) {
				printError("Cannot do this command until the merge conflict has been resolved.");
			}
			g.reset(Integer.parseInt(args[1]));
		} else if (args[0].equals("merge")) {
			if (g.conflict) {
				printError("Cannot do this command until the merge conflict has been resolved.");
			}
			g.merge(args[1]);
		} else if (args[0].equals("rebase")) {
			if (g.conflict) {
				printError("Cannot do this command until the merge conflict has been resolved.");
			}
			g.rebase(args[1]);
		} else if (args[0].equals("add-remote")) {
			if (g.conflict) {
				printError("Cannot do this command until the merge conflict has been resolved.");
			}
			g.addRemote(args[1], args[2], args[3], args[4]);
		} else if (args[0].equals("rm-remote")) {
			if (g.conflict) {
				printError("Cannot do this command until the merge conflict has been resolved.");
			}
			g.rmRemote(args[1]);
		} else if (args[0].equals("push")) {
			if (g.conflict) {
				printError("Cannot do this command until the merge conflict has been resolved.");
			}
			g.push(args[1], args[2]);
		} else if (args[0].equals("fetch")) {
			if (g.conflict) {
				printError("Cannot do this command until the merge conflict has been resolved.");
			}
			g.fetch(args[1], args[2]);
		} else if (args[0].equals("pull")) {
			if (g.conflict) {
				printError("Cannot do this command until the merge conflict has been resolved.");
			}
			g.pull(args[1], args[2]);
		} else if (args[0].equals("clone")) {
			clone(args[1], args[2], args[3], args[4]);
		} else {
			printError("No command with that name exists.");
		}
		serialize(g, GITLET_PATH + "berkeley.ser");
	}

	/**
	 * Creates a new gitlet version control system in the current directory. This
	 * system will automatically start with one commit: a commit that contains no
	 * files and has the commit message initial commit.
	 * @return new Gitlet version control system in the control directory 
	 */
	private static Gitlet init() {
		// check failure cases -- if .gitlet already existed
		Path gitlet = Paths.get(Gitlet.GITLET_PATH);
		if (Files.exists(gitlet)) {
			printError("A gitlet version control system already exists in the current directory.");
		}
		// at this point, there is no failure cases
		// make .gitlet
		File gitlet_folder = new File(Gitlet.GITLET_PATH);
		gitlet_folder.mkdir();
		// instantiate a new Gitlet object
		Gitlet g = new Gitlet();
		// create the staging area folder
		File staging_folder = new File(Gitlet.STAGE_PATH);
		staging_folder.mkdir();
		return g;
	}

	/**
	 * Starting at the current head commit, displays information about each commit
	 * backwards along the commit tree until the initial commit.
	 */
	private void log() {
		for (Commit c : curr_branch) {
			System.out.println(c);
		}
	}

	/**
	 * Same as marking for staging. Adds a copy of a file to staging area. If the
	 * file is untracked, it switches it to tracked but does not add it to the
	 * staging area.
	 * @param string 
	 * 					the name of the file to be added  
	 */
	private void add(String file) {
		// first, check if the file exists
		Path source = Paths.get(file);
		checkFileExists(source);
		// second, check if it file has been marked for untracking
		// if so, just mark it for tracking (without adding it to the staging area)
		boolean marked_for_tracking = curr_branch.addHelper_moveFromUntrackToTrack(
				file, this);
		if (marked_for_tracking) {
			return;
		}
		// third, add a copy of the file to the staging area (replacing the existing
		// if needed)
		curr_branch.addHelper_moveToStage(file);
	}

	/**
	 * Like log, except displays information about all commits ever made. The
	 * order of the commits does not matter.
	 */
	private void globalLog() {
		for (Map.Entry<Integer, Commit> entry : commits_id_pointer.entrySet()) {
			System.out.println(entry.getValue());
		}
	}

	/**
	 * Displays what branches currently exist, and marks the current branch with a
	 * Also displays what files have been staged or marked for untracking.
	 */
	private void status() {
		System.out.println("=== Branches ===");
		for (Map.Entry<String, Branch> myBranch : branches.entrySet()) {
			String name = myBranch.getKey();
			if (myBranch.getValue().equals(curr_branch))
				// check if this branch is the current one
			{
				System.out.print("*");
			}
			System.out.println(name);
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		File folder = new File(Gitlet.STAGE_PATH);
		File[] list_files = folder.listFiles();
		statusHelper(list_files);
		System.out.println();
		System.out.println("=== Files Marked For Untracking ===");
		for (String name : removed) {
			System.out.println(name);
		}
	}

	/**
	 * Print all files in list_files  
	 * @param list_files
	 * 				 array of list of files 
	 */
	private static void statusHelper(File[] list_files) {
		for (File file : list_files) {
			if (file.isFile()) {
				String shortPath = shortenPath(file.getPath());
				System.out.println(shortPath);
			} else {
				File[] file2 = file.listFiles();
				statusHelper(file2);
			}
		}
	}

	/**
	 * Shortens the path of a file in the staging area or the commit folder to the
	 * path relative to the working directory.
	 * @param fullPath
	 *          a path like ".gitlet/stage/test/testFile.txt" or
	 *          ".gitlet/7/test/testFile.txt"
	 * @return String of a path relative to ".gitlet/stage/" or ".gitlet/id/" like
	 *         "test/testFile.txt"
	 */
	static String shortenPath(String fullPath) {
		int secondSlash = fullPath.indexOf(File.separator, 8); // eighth index gets
		// rid of .gitlet/
		String shortPath = fullPath.substring(secondSlash + 1, fullPath.length());
		return shortPath;
	}

	/**
	 * Prints out the id of the commit that has the given commit message. If there
	 * are multiple such commits, it prints the ids out on separate lines.
	 * @param msg 
	 * 			A string of the commit message 
	 */
	private void find(String msg) {
		ArrayList<Integer> id_array = commits_name_id.get(msg);
		if (id_array == null) {
			printError("Found no commit with that message.");
		} else {
			for (Integer i : id_array) {
				System.out.println(i);
			}
		}
	}

	/**
	 * Creates a new branch with the given name, and points it at the current head
	 * node.
	 * @param msg 
	 * 			A string of the branch name 
	 */
	private void branch(String msg) {
		// check if the branch is already in branches
		if (branches.containsKey(msg)) {
			printError("A branch with that name already exists.");
		} else {
			Branch new_branch = new Branch(msg, this);
			this.branches.put(msg, new_branch);
		}
	}

	/**
	 * Deletes the branch with the given name.
	 * @param msg 
	 * 			A string of the branch name
	 */
	private void rmBranch(String msg) {
		// check if the branch is not in branches or the branch is the current
		// branch
		if (!branches.containsKey(msg)) {
			printError("A branch with that name does not exist.");
		} else if (curr_branch.name().equals(msg)) {
			printError("Cannot remove the current branch.");
		} else {
			// remove the branch
			branches.remove(msg);
		}
	}

	/**
	 * If name is a branch name, takes all files in the commit at the head of the
	 * given branch, and puts them in the working directory, ovewriting the
	 * versions of the files that are already there if they exist. Also, at the
	 * end of this command, the given branch will now be considered the current
	 * branch. 
	 * 
	 * If name is a file name, takes the version of the file as it exists
	 * in the head commit, the front of the current branch, and puts it in the
	 * working directory, overwriting the version of the file that's already there
	 * if there is one.
	 * @param name 
	 * 				String of file or branch name 
	 */
	private void checkoutTwoArgs(String name) {
		// check if name is a branch name because branch name takes precedence
		Branch destination_branch = branches.get(name);
		Integer file_location = curr_branch.head.tracked.get(name);
		if (destination_branch != null) {
			if (curr_branch.name().equals(name)) {
				printError("No need to checkout the current branch.");
			} else {
				// name is a non-current branch
				if (this.conflict) {
					printError("Cannot do this command until the merge conflict has been resolved.");
				}
				for (Map.Entry<String, Integer> entry : destination_branch.head.tracked
						.entrySet()) {
					// entry is the file location pair in the head commit of current
					// branch
					String file = entry.getKey();
					Integer location = entry.getValue();
					copyAndPasteFileToFolder(file, "", GITLET_PATH + location
							+ File.separator, false);
				}
				// set curr_branch
				curr_branch = destination_branch;
			}
		} else if (file_location != null) {
			// name is a file, e.g. "test/Fred_Zeitz_vehicle.pdf"
			String root = GITLET_PATH + file_location + File.separator;
			copyAndPasteFileToFolder(name, "", root, false);
		} else {
			// neither a branch nor a file
			printError("File does not exist in the most recent commit, or no such branch exists.");
		}
	}

	/**
	 * Takes the version of the file as it exists in the commit with the given id,
	 * and puts it in the working directory, overwriting the version of the file
	 * that's already there if there is one.
	 * @param id
	 * 				Integer of commit id 
	 * @param file
	 * 				String of file name  
	 */
	private void checkoutThreeArgs(Integer id, String file) {
		// check if the commit with the given id exists
		Commit c = commits_id_pointer.get(id);
		Integer file_location = null;
		if (c == null) {
			printError("No commit with that id exists.");
		} else {
			file_location = c.tracked.get(file);
			if (file_location == null) {
				printError("File does not exist in that commit.");
			}
		}
		String root = GITLET_PATH + file_location + File.separator;
		copyAndPasteFileToFolder(file, "", root, false);
	}

	/**
	 * Checks out all the files tracked by the given commit. Also moves the
	 * current branch's head to that commit node.
	 * @param id
	 * 					Integer of commit id  
	 */
	private void reset(Integer id) {
		// check if the commit with the given id exists
		Commit c = commits_id_pointer.get(id);
		if (c == null) {
			printError("No commit with that id exists.");
		}
		// switch curr_branch.head to c
		curr_branch.head = c;
		for (Map.Entry<String, Integer> entry : c.tracked.entrySet()) {
			// entry is the file location pair in the head commit of current branch
			String file = entry.getKey();
			Integer location = entry.getValue();
			copyAndPasteFileToFolder(file, "", GITLET_PATH + location
					+ File.separator, false);
		}
	}

	/**
	 * Copies and pastes files from a folder to a folder.
	 * @param src
	 *          the staging area
	 * @param dest
	 *          a commit folder
	 * @param new_commit
	 *          a newly created commit (variable argument)
	 */
	public static void copyAndPasteFolderToFolder(File src, File dest,
			Commit... new_commit) {
		if (src.isDirectory()) {
			// src is a folder, dest has to be a folder, so get all files
			// 2 cases to get here:
			// ADD ALL FILES FROM STAGING AREA TO COMMIT FOLDER
			// e.g. src is ".gitlet/stage/"
			// dest is ".gitlet/7/"
			// and it will finally get to the base case below 
			// if directory not exists, create it
			if (!dest.exists()) {
				dest.mkdir();
			}
			// list all the directory contents
			String files[] = src.list();
			for (String file : files) {
				// construct the src and dest file structure
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				// recursive copy
				copyAndPasteFolderToFolder(srcFile, destFile, new_commit);
			}
		} else {
			// src is a file, and dest is a file
			// then just copy (base case)
			try {
				Files.copy(src.toPath(), dest.toPath(),
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				/* Ignore EXCEPTIONNAME. */
			}
			for (Commit c : new_commit) {
				String fullPath = dest.toPath().toString();
				String shortPath = Gitlet.shortenPath(fullPath);
				c.tracked.put(shortPath, c.id());
				// Note: we store something like <wug.txt, 1>
				// <"test/Fred_Zeitz_vehicle.pdf", 1>
			}
		}
	}

	/**
	 * Copies and pastes a file to a folder.
	 * @param src
	 *          a relative file path like "test/deeper/anotherMiddleGuy/wug.txt"
	 * @param dest
	 *          a folder path like ".gitlet/stage/"
	 * @param root
	 *          root + src is the full path of the file to be copied
	 * @param conflict
	 *          true if used from merge method
	 */
	public static void copyAndPasteFileToFolder(String src, String dest,
			String root, boolean conflict) {
		// src is a file, but dest is a folder
		// so create sub-folders
		// 3 cases to get here:
		// ADD ONE FILE FROM WORKING DIRECTORY TO STAGING AREA,
		// e.g. src is "test/deeper/anotherMiddleGuy/wug.txt"
		// dest is ".gitlet/stage/"
		// root is ""
		// ADD A CONFLICTED FILE FROM COMMIT FOLDER TO WORKING DIRECTORY
		// e.g. src is "test/deeper/anotherMiddleGuy/wug.txt"
		// dest is ""
		// root is ".gitlet/commit_id/"
		// this iteratively create sub-folders
		String fullPath = src;
		int index = 0;
		while (fullPath.indexOf(File.separator, index) != -1) {
			int next_slash = fullPath.indexOf(File.separator, index);
			String sub_dir = fullPath.substring(0, next_slash);
			// Note: at each iteration, sub_dir becomes longer
			File sub_directory = new File(dest + sub_dir);
			// make the sub-folder
			sub_directory.mkdir();
			index = next_slash + 1;
		}
		// concatenate root and src.getPath() to get the correct target path
		dest = dest + src;
		if (conflict) {
			try {
				Files.copy(new File(root + src).toPath(),
						new File(dest + CONFLICT_PATH).toPath(),
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				/* Ignore EXCEPTIONNAME. */
			}
		} else {
			try {
				Files.copy(new File(root + src).toPath(), new File(dest).toPath(),
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				/* Ignore EXCEPTIONNAME. */
			}
		}
	}

	/**
	 * Merges files from the given branch into the current branch.
	 * @param branchName
	 * 					String of branch name 
	 * @throws IOException 
	 * 					When an I/O exception of some sort has occurred 				
	 */
	private void merge(String branchName) {
		// check if the branch is not in branches or the branch is the current
		// branch
		Branch given_branch = branches.get(branchName);
		if (given_branch == null) {
			printError("A branch with that name does not exist.");
		} else if (curr_branch.name().equals(branchName)) {
			printError("Cannot merge a branch with itself.");
		}
		// hold all the files that the given branch and current branch share
		HashSet<String> temp = new HashSet<String>();
		Commit split = findSplit(curr_branch.head, given_branch.head, false);
		// STEP 1: Go through all the files in the HEAD COMMIT of the current branch
		for (Map.Entry<String, Integer> entry : curr_branch.head.tracked.entrySet()) {
			// entry is the file location pair in the head commit of current branch
			String file = entry.getKey();
			Integer curr_location = entry.getValue();
			Integer given_location = given_branch.head.tracked.get(file);
			if (given_location != null) {
				// we can find the same name in the head commit of the given branch
				Integer split_location = split.tracked.get(file);
				temp.add(file);
				if (split_location != null) {
					// we find it in split point
					boolean not_modified_curr = false;
					boolean not_modified_given = false;
					not_modified_curr = compareFiles(split_location, curr_location, file);
					not_modified_given = compareFiles(split_location, given_location,
							file);
					// four sub-cases:
					// if (modified_curr && modified_given) {
					// SubCase 1- split_current && split_given == true: do nothing
					// (according to spirit)
					if (!not_modified_curr && !not_modified_given) {
						// SubCase 2- split_current && split_given == false: create
						// .conflict files and set
						// boolean conflict = true, print message “encounter merge conflict”
						conflict = true;
						System.out.println("Encountered a merge conflict.");
						copyAndPasteFileToFolder(file, "", GITLET_PATH + given_location
								+ File.separator, true);
					} else if (not_modified_curr && !not_modified_given) {
						// SubCase 3- split_current == true, split_given == false: stage
						// file ** If there is
						// already some file in the staging area, just include it later
						// (we will call commit anyways)
						// add a copy of the file to the staging area (replacing the
						// existing if needed)
						copyAndPasteFileToFolder(file, STAGE_PATH, Gitlet.GITLET_PATH
								+ given_location + File.separator, false);
					} // else {
					// SubCase 4- split_current == false, split_given == true: do nothing
					// }
				} else {
					// we do not find it in split point
					// compare file in the current branch with the file in the given
					// branch
					boolean curr_given_same = false;
					curr_given_same = compareFiles(curr_location, given_location, file);
					if (!curr_given_same) {
						// if different, create .conflict( ) (according to spirit)
						conflict = true;
						System.out.println("Encountered a merge conflict.");
						copyAndPasteFileToFolder(file, "", GITLET_PATH + given_location
								+ File.separator, true);
					} // Note: if equal to each other do nothing (according to spirit)
				} // we do not find it in split point
			} // else {
			// we cannot find the same name in the head commit of the given branch
			// three sub-cases:
			// not in split point : do nothing
			// in split point with same content as master : do nothing
			// in split point with different content as master : do nothing
			// } // try to find the same name in the head commit of the given branch
		} // go through all the files in the HEAD COMMIT of the current branch
		// STEP 2: Go through all files in the head commit of the given branch
		for (Map.Entry<String, Integer> entry : given_branch.head.tracked
				.entrySet()) {
			// for each one, check if the file in the given branch is in temp
			// only do something when the file in the given branch is not in temp
			if (!temp.contains(entry.getKey())) {
				String file_missed = entry.getKey();
				Integer given_location = given_branch.head.tracked.get(file_missed);
				// check if file_missed is in split point
				Integer split_location = split.tracked.get(file_missed);
				if (split_location != null) {
					// find in split point
					// check if they have the same content
					boolean not_modified_given = false;
					not_modified_given = compareFiles(split_location, given_location,
							file_missed);
					if (not_modified_given) {
						// if split and given branch have the same content
					} else {
						// if split and given branch have different content, then we stage
						// the file
						// add a copy of the file to the staging area (replacing the
						// existing if needed)
						copyAndPasteFileToFolder(file_missed, STAGE_PATH,
								Gitlet.GITLET_PATH + given_location + File.separator, false);
					}
				} else {
					// does not find in split point
					// stage the file (according to spirit)
					// add a copy of the file to the staging area (replacing the existing
					// if needed)
					copyAndPasteFileToFolder(file_missed, STAGE_PATH, Gitlet.GITLET_PATH
							+ given_location + File.separator, false);
				} // check if file_missed is in split point
			} // only do something when the file in the given branch is not in temp
		}
		if (!conflict) {
			this.curr_branch.commit("Merged " + curr_branch.name() + " with "
					+ branchName + ".", this);
		} else {
			// commit method can clear the staging area
			// so if we don't need to commit, we have to clear it here
			try {
				Branch.recursiveDelete(new File(STAGE_PATH));
			} catch (IOException e) {
				/* Ignore EXCEPTIONNAME. */
			}
		}
	}

	/**
	 * find the split point of the current branch and the given branch, then snaps
	 * off the current branch at this point, then reattaches the current branch to
	 * the head of the given branch.
	 * @param branchName
	 * 				String of branch name 
	 */
	private void rebase(String branchName) {
		// STEP 1: ERROR CHECK
		// check if the branch is not in branches or the branch is the current
		// branch
		Branch given_branch = branches.get(branchName);
		if (given_branch == null) {
			printError("A branch with that name does not exist.");
		} else if (curr_branch.name().equals(branchName)) {
			printError("Cannot rebase a branch onto itself.");
		}
		// check if input branch's head is within the history of the current
		// branch's head
		Commit split = findSplit(curr_branch.head, given_branch.head, true);
		if (split == null) {
			printError("Already up-to-date.");
		} else if (split == curr_branch.head) {
			curr_branch.head = given_branch.head; // rebase special case
			return;
		}
		// STEP 2: GIVEN VS SPLIT
		// Compares files in given branch head with the files in split point
		HashMap<String, Boolean> compare_split_and_given = compareSplitAndGiven(
				given_branch.head, split);
		// STEP 3: Create stack for cloning commits in the current branch
		Commit current_commit = curr_branch.head;
		Stack<Commit> rebase_commits = new Stack<Commit>();
		while (current_commit.id() != split.id()) {
			rebase_commits.push(current_commit);
			current_commit = current_commit.prev();
		}
		// STEP 4:
		Commit new_commit_prev = given_branch.head;
		while (!rebase_commits.empty()) {
			// cloning commits from given branch to current branch
			current_commit = rebase_commits.pop();
			Calendar cal = Calendar.getInstance();
			String init_timestamp = dateFormat.format(cal.getTime());
			int new_id = new IDGenerator(current_commit.getMsg(), init_timestamp,
					new_commit_prev.id()).commitID();
			Commit new_commit = new Commit(new_id, current_commit.getMsg(),
					init_timestamp, new_commit_prev);
			// Add commit to commit history
			commits_name_id.get(new_commit.getMsg()).add(new_commit.id());
			commits_id_pointer.put(new_commit.id(), new_commit);
			// Compares files in current branch with the files in split point
			for (Map.Entry<String, Integer> entry : current_commit.tracked.entrySet()) {
				// entry is the file location pair in the head commit of current branch
				String filename = entry.getKey();
				Integer curr_path = entry.getValue();
				Integer split_location = split.tracked.get(filename);
				Boolean not_modified_current = false;
				if (split_location != null) {
					not_modified_current = compareFiles(split_location, curr_path,
							filename);
				}
				Boolean not_modified_given = compare_split_and_given.get(filename);
				if (not_modified_given == null) {
					not_modified_given = false;
				}
				// 4 SubCases
				if (not_modified_given == true && not_modified_current == true) {
					new_commit.tracked.put(filename, curr_path);
				} else if (not_modified_given == false && not_modified_current == false) {
					new_commit.tracked.put(filename, curr_path);
					compare_split_and_given.put(filename, true); // should replace current
					// key, value pair
				} else if (not_modified_given == false && not_modified_current == true) {
					Integer given_path = given_branch.head.tracked.get(filename);
					if (given_path != null) {
						new_commit.tracked.put(filename, given_path); // propagating change
					}
				} else if (not_modified_given == true && not_modified_current == false) {
					new_commit.tracked.put(filename, curr_path);
					compare_split_and_given.put(filename, true);
				}
			}
			// update commit parameters
			num_of_commit++;
			new_commit_prev = new_commit;
			// update current branch pointer
			curr_branch.head = new_commit;
		}
	}
	/**
	 * Stores String and Boolean Pair to compare files between Split and Given Commit Head 
	 * @param givenBranchHead
	 * 					Commit of given Branch Head 
	 * @param split
	 * 					Commit of split ancestor 
	 * @return HashMap of String Boolean Pairs 
	 */

	private HashMap<String, Boolean> compareSplitAndGiven(Commit givenBranchHead,
			Commit split) {
		HashMap<String, Boolean> rtn = new HashMap<String, Boolean>();
		for (Map.Entry<String, Integer> entry : givenBranchHead.tracked.entrySet()) {
			String filename = entry.getKey();
			Integer curr_path = entry.getValue();
			Integer split_location = split.tracked.get(filename);
			Boolean not_modified_given = false;
			if (split_location != null) {
				not_modified_given = compareFiles(split_location, curr_path, filename);
			}
			rtn.put(filename, not_modified_given);
		}
		// SPLIT VS GIVEN
		// Checks if there are any files in split_location that is not in given
		// branch
		for (Map.Entry<String, Integer> entry : split.tracked.entrySet()) {
			String filename = entry.getKey();
			if (!rtn.containsKey(filename)) {
				rtn.put(filename, false);
			}
		}
		return rtn;
	}

	/**
	 * Compares two files with the same name but in different commit folders. 
	 * @param i1, i2
	 *         Integers of two different commit ids
	 * @param file
	 *         String of file name
	 *         @throw IOException 
	 * @return true if files have the same content
	 */
	private static boolean compareFiles(Integer i1, Integer i2, String file) {
		Path path1 = Paths.get(Gitlet.GITLET_PATH + i1 + File.separator + file);
		Path path2 = Paths.get(Gitlet.GITLET_PATH + i2 + File.separator + file);
		byte[] data1 = null;
		byte[] data2 = null;
		try {
			data1 = Files.readAllBytes(path1);
			data2 = Files.readAllBytes(path2);
		} catch (IOException e) {
			/* Ignore EXCEPTIONNAME. */
		}
		return Arrays.equals(data1, data2);
	}

	/**
	 * Finds the earliest common ancestor between commits c1 and c2.
	 * @param c1, c2
	 *        two Commit objects 
	 * @param rebase 
	 * 				returns true if 
	 * @return Commit 
	 * @throws none 
	 */
	private static Commit findSplit(Commit c1, Commit c2, boolean rebase) {
		HashSet<Integer> history = new HashSet<Integer>();
		while (c1.id() != 0) {
			history.add(c1.id());
			c1 = c1.prev();
		}
		// Adds the first commit ever to the history
		history.add(c1.id());
		if (rebase == true) {
			if (history.contains(c2.id())) {
				return null; // this means input branch head is in current branch
				// history
			}
		}
		while (!history.contains(c2.id())) {
			c2 = c2.prev();
		}
		return c2;
	}

	/**************************************** Going Remote ****************************************/

	/**
	 * Saves the given login information under the given remote name. Attempts to
	 * push or pull from the given remote name will then attempt to use this scp
	 * login information and go to the given location to look for a .gitlet
	 * folder.
	 * @param remote_name
	 * @param user_name
	 * @param server 
	 */
	private void addRemote(String remote_name, String user_name, String server,
			String locaation_on_server) {
		Remote r = remotes.get(remote_name);
		if (r != null) {
			printError("A remote with that name already exists.");
		}

		remotes
		.put(remote_name, new Remote(user_name, server, locaation_on_server));
	}

	/**
	 * Removes information associated with the given remote name.
	 * @param remote_name 
	 */
	private void rmRemote(String remote_name) {
		Remote r = remotes.get(remote_name);
		if (r == null) {
			printError("A remote with that name does not exist.");
		}

		remotes.remove(remote_name);
	}

	/**
	 * Attempts to append the current branch's commits to the end 
	 * of the given branch at the given remote
	 * @param remote_name
	 * @param remote_branch_name
	 * @throws IOException 
	 * 				when an I/O exception of some sort has occurred 
	 */
	private void push(String remote_name, String remote_branch_name) {
		Remote r = remotes.get(remote_name);
		// r.locaation_on_server() should have an ending "/"
		// if the ending character is not "/", add it
		String locaation_on_server = r.locaation_on_server();
		if (!locaation_on_server.substring(locaation_on_server.length() - 1)
				.equals(File.separator)) {
			locaation_on_server = locaation_on_server + File.separator;
		}

		// try to get the serialization file from the remote machine
		command("scp", r.user_name() + "@" + r.server() + ":" + locaation_on_server
				+ GITLET_PATH + "berkeley.ser", GITLET_PATH + "remote_berkeley.ser");
		if (!new File(GITLET_PATH + "remote_berkeley.ser").exists()) {
			// there is no gitlet system currently on the remote,
			// initialize it there
			command("scp", "-r", GITLET_PATH, r.user_name() + "@" + r.server() + ":"
					+ locaation_on_server);
		} else {
			// now remote_berkeley.ser is in the local .gitlet folder
			// so deserialize it first
			Gitlet remote_g = deserialize(GITLET_PATH + "remote_berkeley.ser");
			// compare remote_g
			// check if the gitlet system on the remote machine exists but does not
			// have the input branch
			boolean need_input_branch = !(remote_g.branches
					.containsKey(remote_branch_name));
			if (!need_input_branch) {
				// the input branch is already on the remote machine,
				// so just try to "fast-forward"
				Commit current_commit = curr_branch.head;
				Stack<Commit> missing_commits = new Stack<Commit>();
				while (current_commit != null
						&& current_commit.id() != remote_g.branches.get(remote_branch_name).head
						.id()) {
					missing_commits.push(current_commit);
					current_commit = current_commit.prev();
				}
				if (current_commit == null) {
					// the remote branch's head is not in the history of the current local
					// head
					printError("Please pull down remote changes before pushing.");
				}
				Commit new_commit_prev = current_commit;
				appendCommitsToRemote(missing_commits, new_commit_prev, remote_g, r,
						remote_branch_name);
			} else {
				// the input branch is missing on the remote machine,
				// so add the branch to the remote gitlet
				Commit current_commit = branches.get(remote_branch_name).head;
				Stack<Commit> missing_commits = new Stack<Commit>();
				while (!remote_g.commits_id_pointer.containsKey(current_commit.id())) {
					missing_commits.push(current_commit);
					current_commit = current_commit.prev();
				}
				Commit new_commit_prev = current_commit;
				appendCommitsToRemote(missing_commits, new_commit_prev, remote_g, r,
						remote_branch_name);
			}
			// serialize remote_g and scp it
			serialize(remote_g, GITLET_PATH + "remote_send_berkeley.ser");
			command("scp", GITLET_PATH + "remote_send_berkeley.ser", r.user_name()
					+ "@" + r.server() + ":" + locaation_on_server + GITLET_PATH
					+ "berkeley.ser");
			try {
				Files.delete(new File(GITLET_PATH + "remote_berkeley.ser").toPath());
				Files.delete(new File(GITLET_PATH + "remote_send_berkeley.ser")
				.toPath());
			} catch (IOException e) {
				/* Ignore EXCEPTIONNAME. */
			}
		}
	}

	/**
	 * Appends commits to the remote machine 
	 * @param missing_commits
	 * @param new_commit_prev
	 * @param remote_g
	 * @param r
	 * @param remote_branch_name
	 */
	private void appendCommitsToRemote(Stack<Commit> missing_commits,
			Commit new_commit_prev, Gitlet remote_g, Remote r,
			String remote_branch_name) {
		// append the missing commits one by one to the remote branch's head
		while (!missing_commits.empty()) {
			Commit missing_commit = missing_commits.pop();
			// first, modify instance variables of remote_g
			Commit new_commit = new Commit(missing_commit.id(),
					missing_commit.getMsg(), missing_commit.getTimeStamp(),
					new_commit_prev);
			// new_commit should be exactly the same as missing_commit, 
			// which means tracked should also be the same
			new_commit.tracked.putAll(missing_commit.tracked);
			new_commit_prev = new_commit;
			ArrayList<Integer> id_array = remote_g.commits_name_id.get(new_commit
					.getMsg());
			if (id_array == null) {
				id_array = new ArrayList<Integer>();
			}
			id_array.add(new_commit.id());
			remote_g.commits_id_pointer.put(new_commit.id(), new_commit);
			// then, scp the commit folder to the remote machine
			// r.locaation_on_server() should have an ending "/"
			// if the ending character is not "/", add it
			String locaation_on_server = r.locaation_on_server();
			if (!r.locaation_on_server()
					.substring(r.locaation_on_server().length() - 1)
					.equals(File.separator)) {
				locaation_on_server = r.locaation_on_server() + File.separator;
			}
			command("scp", "-r", GITLET_PATH + missing_commit.id(), r.user_name()
					+ "@" + r.server() + ":" + locaation_on_server + GITLET_PATH
					+ new_commit.id());
		}
		// the remote should reset to the front of the appended commits
		remote_g.branches.get(remote_branch_name).head = remote_g.commits_id_pointer
				.get(curr_branch.head.id());
		remote_g.curr_branch.head = remote_g.branches.get(remote_branch_name).head;
	}

	/**
	 * Brings down commits from the remote gitlet into the local gitlet  
	 * @param remote_name
	 * @param remote_branch_name
	 * @throw IOException 
	 * 				when an I/O exception of some sort has occurred 
	 */

	private void fetch(String remote_name, String remote_branch_name) {
		Remote r = remotes.get(remote_name);
		// r.locaation_on_server() should have an ending "/"
		// if the ending character is not "/", add it
		String locaation_on_server = r.locaation_on_server();
		if (!locaation_on_server.substring(locaation_on_server.length() - 1)
				.equals(File.separator)) {
			locaation_on_server = locaation_on_server + File.separator;
		}
		// try to get the serialization file from the remote machine
		command("scp", r.user_name() + "@" + r.server() + ":" + locaation_on_server
				+ GITLET_PATH + "berkeley.ser", GITLET_PATH + "remote_berkeley.ser");
		// now remote_berkeley.ser is in the local .gitlet folder
		// so deserialize it first
		Gitlet remote_g = deserialize(GITLET_PATH + "remote_berkeley.ser");
		Branch given_branch = remote_g.branches.get(remote_branch_name);
		// compare remote_g
		// check if the gitlet system on the remote machine exists but does not have
		// the input branch
		boolean input_branch_exists = (given_branch != null);
		if (!input_branch_exists) {
			try {
				Files.delete(new File(GITLET_PATH + "remote_berkeley.ser").toPath());
			} catch (IOException e) {
				/* Ignore EXCEPTIONNAME. */
			}
			printError("That remote does not have that branch.");
		} else {
			// check if the remote branch head is in the history of the local head
			Commit curr_local_commit = curr_branch.head;
			Commit remote_head_commit = given_branch.head;
			while (curr_local_commit != null) {
				if (curr_local_commit.id() == remote_head_commit.id()) {
					try {
						Files
						.delete(new File(GITLET_PATH + "remote_berkeley.ser").toPath());
					} catch (IOException e) {
						/* Ignore EXCEPTIONNAME. */
					}
					printError("Already up-to-date.");
				}
				curr_local_commit = curr_local_commit.prev();
			}
			// find the latest common commit(s)
			// and copy the remote commits down as a new branch off of it
			// overwrite this branch (move the pointer) if it already exists
			Commit latest_common_commit = findLatestCommonCommit(remote_head_commit);
			Commit current_remote_commit = remote_head_commit;
			Stack<Commit> missing_commits = new Stack<Commit>();
			while (current_remote_commit.id() != latest_common_commit.id()) {
				missing_commits.push(current_remote_commit);
				current_remote_commit = current_remote_commit.prev();
			}
			Commit new_commit_prev = latest_common_commit;
			appendCommitsToLocal(missing_commits, new_commit_prev,
					remote_branch_name, r);
		}
		try {
			Files.delete(new File(GITLET_PATH + "remote_berkeley.ser").toPath());
		} catch (IOException e) {
			/* Ignore EXCEPTIONNAME. */
		}
	}

	/**
	 * Finds the latest common commit 
	 * @param remote_head_commit
	 * @return the lastest common commit 
	 */
	private Commit findLatestCommonCommit(Commit remote_head_commit) {
		Commit curr_remote_commit = remote_head_commit;
		while (!commits_id_pointer.containsKey(curr_remote_commit.id())) {
			curr_remote_commit = curr_remote_commit.prev();
		}
		return curr_remote_commit;
	}

	/**
	 * Appends commits to local machine 
	 * @param missing_commits
	 * @param new_commit_prev
	 * @param remote_branch_name
	 * @param r
	 */
	private void appendCommitsToLocal(Stack<Commit> missing_commits,
			Commit new_commit_prev, String remote_branch_name, Remote r) {
		// append the missing commits one by one
		while (!missing_commits.empty()) {
			Commit missing_commit = missing_commits.pop();
			// first, modify instance variables of this
			Commit new_commit = new Commit(missing_commit.id(),
					missing_commit.getMsg(), missing_commit.getTimeStamp(),
					new_commit_prev);
			// new_commit should be exactly the same as missing_commit, 
			// which means tracked should also be the same
			new_commit.tracked.putAll(missing_commit.tracked);
			new_commit_prev = new_commit;
			ArrayList<Integer> id_array = commits_name_id.get(new_commit.getMsg());
			if (id_array == null) {
				id_array = new ArrayList<Integer>();
			}
			id_array.add(new_commit.id());
			commits_id_pointer.put(new_commit.id(), new_commit);
			// then, scp the commit folder to the local machine
			// r.locaation_on_server() should have an ending "/"
			// if the ending character is not "/", add it
			String locaation_on_server = r.locaation_on_server();
			if (!r.locaation_on_server()
					.substring(r.locaation_on_server().length() - 1)
					.equals(File.separator)) {
				locaation_on_server = r.locaation_on_server() + File.separator;
			}
			command("scp", "-r", r.user_name() + "@" + r.server() + ":"
					+ locaation_on_server + GITLET_PATH + new_commit.id(), GITLET_PATH
					+ missing_commit.id());
		}
		// create the new branch
		Branch new_branch = new Branch("remote/" + remote_branch_name, this);
		new_branch.head = new_commit_prev;
		branches.put("remote/" + remote_branch_name, new_branch);
	}

	/**
	 * Fetches, then merges the newly created branch with the current local branch.
	 * @param remote_name
	 * 					string of remote_name 
	 * @param remote_branch_name	
	 * 					string of remote_branch_name 
	 */
	private void pull(String remote_name, String remote_branch_name) {
		fetch(remote_name, remote_branch_name);
		merge("remote/" + remote_branch_name);
	}

	/**
	 * Copies the .gitlet directory at the remote's address into a new folder named 
	 * the same as the remote name. Also takes the snapshot of files in the head's 
	 * commit and puts them in the folder.
	 * @param remote_name
	 * @param user_name
	 * @param server
	 * @param locaation_on_server
	 */
	private static void clone(String remote_name, String user_name,
			String server, String locaation_on_server) {
		// locaation_on_server should have an ending "/"
		// if the ending character is not "/", add it
		if (!locaation_on_server.substring(locaation_on_server.length() - 1)
				.equals(File.separator)) {
			locaation_on_server = locaation_on_server + File.separator;
		}
		File folder = new File(remote_name);
		folder.mkdir();
		command("scp", "-r", user_name + "@" + server + ":" + locaation_on_server
				+ GITLET_PATH, remote_name);
		// Note: "locaation_on_server + GITLET_PATH" is why locaation_on_server
		// should have an ending "/"
		Gitlet remote_g = deserialize(remote_name + File.separator + GITLET_PATH
				+ "berkeley.ser");
		for (Map.Entry<String, Integer> entry : remote_g.curr_branch.head.tracked
				.entrySet()) {
			// entry is the file location pair in the head commit of current branch
			String file = entry.getKey();
			Integer location = entry.getValue();
			copyAndPasteFileToFolder(file, remote_name + File.separator, remote_name
					+ File.separator + GITLET_PATH + location + File.separator, false);
		}
	}

	/**
	 * Executes the given command on the terminal, and return what it prints out
	 * as a string.
	 * Example: If you want to call the terminal command `java Gitlet add wug.txt`
	 * you would call the method like so: `command("java", "Gitlet", "add",
	 * "wug.txt");` The `...` syntax allows you to pass in however many strings
	 * you want.
	 *  @throws IOException
	 * 						when an I/O exception of some sort has occurred 
	 *  @InterruptedException 
	 *  					Thrown when a thread is waiting, sleeping, or otherwise occupied, 
	 *  					and the thread is interrupted, either before or during the activity
	 */
	private static String command(String... args) {
		try {
			StringBuilder results = new StringBuilder();
			Process p = Runtime.getRuntime().exec(args);
			p.waitFor();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));) {
				String line = null;
				while ((line = br.readLine()) != null) {
					results.append(line).append(System.lineSeparator());
				}
				return results.toString();
			}
		} catch (IOException e) {
			return e.getMessage();
		} catch (InterruptedException e) {
			return e.getMessage();
		}
	}

	/**************************************** Going Remote ****************************************/

	/**
	 * Serializes (write to file) - converts object to stream of bytes to store object
	 * @param g
	 * 				Gitlet Object 			
	 * @param file 
	 * 				String of file name to be deserialized 
	 * @throws IOException 
	 * 				when an I/O exception of some sort has occurred 
	 */
	private static void serialize(Gitlet g, String file) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(g);
			fos.close();
		} catch (IOException i) {
			return;
		}
	}

	/**
	 * Deserializes - returns Object that was serialized at the path 
	 * 						specified by the argument 
	 * @param file
	 * 				String of file name to be deserialized 
	 * @return the deserialized file 
	 * @throws FileNotFoundException 
	 * 						when file is not found 
	 * @throws IOException
	 * 						when an I/O exception of some sort has occurred 
	 * @throws ClassNotFoundException 
	 * 						when no definition for the class with the specified name could be found 
	 */
	private static Gitlet deserialize(String file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Gitlet g = (Gitlet) ois.readObject();
			ois.close();
			return g;
		} catch (FileNotFoundException e) {
			// first time of running
			return null;
		} catch (IOException i) {
			return null;
		} catch (ClassNotFoundException c) {
			return null;
		}
	}

	/**
	 * Prints the error message error and exits the program.
	 * @param error 
	 * 			String of message error 
	 */
	static void printError(String error) {
		System.out.println(error);
		System.exit(-1);
	}

	/**
	 * Checks if the file check exists. If not, prints the error message and exits
	 * the program.
	 * @param check
	 * 			Path of a file to be checked 
	 */
	private static void checkFileExists(Path check) {
		if (!Files.exists(check)) {
			printError("File does not exist.");
		}
	}

}
