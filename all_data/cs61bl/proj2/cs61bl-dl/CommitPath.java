import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Serializable - contains data about the present state of the system's history
 * of commits. General usage is for this class to have a single method called by
 * each possible Gitlet command. If the command requires manipulation of the
 * file system, this class will call the appropriate method of GitletController.
 *
 */

public class CommitPath implements Serializable {

	private HashMap<String, HashSet<Commit>> findCommitID;
	private HashMap<Integer, Commit> allCommits;
	private HashMap<String, Commit> myBranches;
	private String myHead;
	private HashSet<String> untracked;
	private HashSet<String> staged;
	private boolean isConflicted;

	/**
	 * Initializes instance variables. Creates the head commit and adds the
	 * first branch, assigning it to the commit.
	 */

	public CommitPath() {
		findCommitID = new HashMap<String, HashSet<Commit>>();
		allCommits = new HashMap<Integer, Commit>();
		HashMap<String, Commit> headFiles = new HashMap<String, Commit>();
		Commit head = new Commit(null, "initial commit", headFiles);
		myBranches = new HashMap<String, Commit>();
		untracked = new HashSet<String>();
		staged = new HashSet<String>();
		isConflicted = false;
		myBranches.put("master", head);
		myHead = "master";
	}

	/**
	 * Returns the CommitPath currently saved on the computer. If no such
	 * CommitPath exists, returns null.
	 */

	public static CommitPath serialRead() {
		File system = new File(".gitlet/.staging");
		if (!(system.exists())) {
			return new CommitPath();
		}
		CommitPath buff = null;
		try {
			ObjectInput input = new ObjectInputStream(new FileInputStream(
					".gitlet/CommitPath.ser"));
			try {
				buff = (CommitPath) input.readObject();
			} catch (ClassNotFoundException e2) {
				System.err.printf("Error: %s\n", e2.toString());
			}
		} catch (IOException e) {
			System.err.printf("Error: %s\n", e.toString());
		}
		return buff;
	}

	/**
	 * Writes the CommitPath to the hard drive.
	 *
	 * @param list
	 *            The new CommitPath to be saved on the computer.
	 */

	public static void serialWrite(CommitPath list) {
		File system = new File(".gitlet/.staging");
		try {
			ObjectOutput output = new ObjectOutputStream(new FileOutputStream(
					".gitlet/CommitPath.ser"));
			output.writeObject(list);
		} catch (IOException e) {
			System.err.printf("Error: %s\n", e.toString());
		}
	}

	/**
	 * Boolean function that returns if the CommitPath system has a merging
	 * conflict.
	 */

	public boolean checkConflicted() {
		if (isConflicted)
			System.out
					.println(" Cannot do this command until the merge conflict has been resolved.");
		return isConflicted;
	}

	/**
	 * Creates a new Gitlet system, including the gitlet directory and the
	 * nested staging directory. Calls GitletController to create the folders.
	 * If a system already exists, prints out a message saying so.
	 *
	 * Runs in constant time.
	 */

	public void init() {
		if (checkConflicted())
			return;
		File system = new File(".gitlet/.staging");
		if (system.exists()) {
			System.out
					.println("A gitlet version control system already exists in the current directory.");
			return;
		}
		GitletController.init(myBranches.get(myHead).id + "/");
	}

	/**
	 * Adds a file to the staging area. Starts tracking the file if it is
	 * currently untracked. If the file does not exist, prints out a message
	 * saying so.
	 *
	 * Runs in time proportional to the size of the file.
	 *
	 * @param file
	 *            The name of the file, as a string, being added to the staging
	 *            area.
	 */

	public void add(String file) {
		File toadd = new File(file);
		if (!(toadd.exists())) {
			System.out.println("File does not exist.");
			return;
		}
		if (untracked.contains(file)) {
			untracked.remove(file);
			return;
		}
		staged.add(file);
		GitletController.add(file);
	}

	/**
	 * Creates a new commit, with the current commit as its parent. Moves files
	 * from the staging area into the new commit. Reassigns the current branch
	 * to the new commit.
	 *
	 * @param message
	 *            A string affiliated with the new commit. In theory, describes
	 *            the qualities of the new commit.
	 */

	public void commit(String message) {
		if (untracked.isEmpty() && staged.isEmpty()) {
			System.out.println("No changes added to the commit.");
			return;
		}
		Commit current = myBranches.get(myHead);
		HashMap<String, Commit> files = new HashMap<String, Commit>();
		Commit thiscommit = new Commit(current, message, files);
		for (String file : current.myFiles.keySet()) {
			if (!(staged.contains(file)) && !(untracked.contains(file))) {
				files.put(file, current.myFiles.get(file));
			}
		}
		for (String file : staged) {
			files.put(file, thiscommit);
		}
		myBranches.put(myHead, thiscommit);
		staged.clear();
		untracked.clear();
		isConflicted = false;
		GitletController.commit(thiscommit.id + "/");
	}

	/**
	 * If the file is currently in the staging area, removes it. If the file is
	 * in the current commit, untracks it. Otherwise, prints out a message
	 * saying there is no reason to execute the function.
	 *
	 * Runs in constant time.
	 *
	 * @param file
	 *            Name of the file, as a string, being removed.
	 */

	public void remove(String file) {
		if (staged.contains(file)) {
			staged.remove(file);
			GitletController.remove(file);
		} else if (myBranches.get(myHead).myFiles.containsKey(file)) {
			untracked.add(file);
		} else {
			System.out.println("No reason to remove the file.");
		}
	}

	/**
	 * Prints the commit history of the current commit. Calls the printLog()
	 * helper function from the Commit class.
	 *
	 * Runs in linear time proportional to the number of commits in the history
	 * of the current commit.
	 */

	public void log() {
		for (Commit c = myBranches.get(myHead); c != null; c = c.myParent) {
			c.printLog();
			if (c.myParent != null) {
				System.out.println();
			}
		}
	}

	/**
	 * Prints the commit history of the entire CommitPath. Outputs the commits
	 * in a random order. Calls the printLog() helper function from the Commit
	 * class.
	 *
	 * Runs linear with respect to how many commits exist.
	 */

	public void globalLog() {
		int length = allCommits.size();
		for (Commit c : allCommits.values()) {
			c.printLog();
			length--;
			if (length != 0) {
				System.out.println();
			}
		}
	}

	/**
	 * Prints out the commit IDs that correspond to the message input into the
	 * function. If no commits correlate to the message, prints out a message
	 * saying so.
	 *
	 * Runs linear with respect to the amount of commits that correspond to the
	 * message.
	 *
	 * @param message
	 *            The message pertaining to the commits that are printed out.
	 */

	public void find(String message) {
		if (!(findCommitID.containsKey(message))) {
			System.out.println("Found no commit with that message.");
			return;
		}
		for (Commit c : findCommitID.get(message)) {
			System.out.println(c.id);
		}
	}

	/**
	 * Prints out every branch, staged file, and untracked file within the
	 * current CommitPath. The order of the branches and files is random.
	 *
	 * Follows a particular syntax in doing so:
	 *
	 * === Branches === master other-branch
	 *
	 * === Staged Files === wug.txt some_folder/wugs.txt
	 *
	 * === Files Marked for Untracking === goodbye.txt
	 *
	 * A single space separates each collection of branches/files.
	 *
	 * Runs in time proportional to the number of total files being printed out.
	 */

	public void status() {
		System.out.println("=== Branches ===");
		for (String branch : myBranches.keySet()) {
			if (branch.equals(myHead)) {
				System.out.println("*" + branch);
			} else {
				System.out.println(branch);
			}
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		for (String file : staged) {
			System.out.println(file);
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (String file : untracked) {
			System.out.println(file);
		}
	}

	/**
	 * Performs one of three tasks. If given two arguments, The first argument
	 * must be a commit ID and the second must be a file name. Calls
	 * GitletController to move the file with the input ID to the working
	 * directory. If such a file already exists in the working directory, it
	 * gets overwritten by the one being moved. Runs in linear time with respect
	 * to the size of the file.
	 *
	 * Otherwise, only one argument is input, which must be a string. Checks if
	 * the string is a branch. If it is, it moves every file within the head of
	 * the current branch into the working directory, overwriting files that
	 * exist there if necessary It then makes the provided branch the new
	 * current branch. Runs in linear time with respect to the number of files
	 * being moved.
	 *
	 * If the single argument is not a branch, checks to see if it is a file. If
	 * it is, moves the file as it exists in the head commit into the working
	 * directory, overwriting the file existing there if necessary. Runs in
	 * linear time with respect to the size of the file.
	 *
	 * @param args
	 *            : Either a single argument, which is a string that represents
	 *            either a branch or a file, or is two arguments: a string that
	 *            represents a commit ID followed by a string that represents a
	 *            file.
	 */

	public void checkout(String... args) {
		if (args.length == 2) {
			int id = Integer.parseInt(args[0]);
			if (allCommits.containsKey(id)) {
				if (allCommits.get(id).myFiles.containsKey(args[1])) {
					int cmtID = allCommits.get(id).myFiles.get(args[1]).id;
					GitletController.checkout(cmtID, args[1]);
				} else {
					System.out.println("File does not exist in that commit.");
				}
			} else {
				System.out.println("No commit with that id exists.");
			}
		} else if (myBranches.containsKey(args[0])) {
			if (checkConflicted())
				return;
			if (args[0].equals(myHead)) {
				System.out.println("No need to checkout the current branch.");
				return;
			} else {
				for (String file : myBranches.get(args[0]).myFiles.keySet()) {
					int cmtID = myBranches.get(args[0]).myFiles.get(file).id;
					GitletController.checkout(cmtID, file);
				}
				myHead = args[0];
			}
		} else if (myBranches.get(myHead).myFiles.containsKey(args[0])) {
			int cmtID = myBranches.get(myHead).myFiles.get(args[0]).id;
			GitletController.checkout(cmtID, args[0]);
		} else {
			System.out
					.println("File does not exist in the most recent commit, or no such branch exists.");
		}
	}

	/**
	 * Creates a new branch that refers to the current head node. Does NOT
	 * immediately assign the new branch as the current branch.
	 *
	 * Runs in constant time.
	 *
	 * @param name
	 *            : The name of the branch, as a string, being created.
	 */

	public void branch(String name) {
		if (checkConflicted())
			return;
		if (myBranches.containsKey(name)) {
			System.out.println("A branch with that name already exists.");
			return;
		}
		myBranches.put(name, myBranches.get(myHead));
	}

	/**
	 * Removes the input branch from the CommitPath. If the input branch is the
	 * current branch, it cannot be removed, and a message prints out saying so.
	 * If the branch does not exist, prints out a message saying so.
	 *
	 * Runs in constant time.
	 *
	 * @param branch
	 *            : The name of the branch, as a string, being removed.
	 */

	public void removeBranch(String branch) {
		if (checkConflicted())
			return;
		if (myHead.equals(branch)) {
			System.out.println("Cannot remove the current branch.");
			return;
		}
		Commit removed = myBranches.remove(branch);
		if (removed == null) {
			System.out.println("A branch with that name does not exist.");
		}
	}

	/**
	 * Checks out every file that is currently being tracked by the commit with
	 * the ID that was given into the function. Also makes that commit the head
	 * of the current branch. If no commit exists with the provided ID, a
	 * message is printed out saying so.
	 *
	 * Runs in linear time with respect to the number of files being tracked by
	 * the commit.
	 *
	 * @param idString
	 *            : The ID of the commit referred to above, as a string.
	 */

	public void reset(String idString) {
		if (checkConflicted())
			return;
		int id = Integer.parseInt(idString);
		if (!(allCommits.containsKey(id))) {
			System.out.println("No commit with that id exists.");
		} else {
			for (String file : allCommits.get(id).myFiles.keySet()) {
				int cmtID = allCommits.get(id).myFiles.get(file).id;
				GitletController.checkout(cmtID, file);
			}
			myBranches.put(myHead, allCommits.get(id));
		}
	}

	/**
	 * Combines files from the provided branch with those of the current branch.
	 * If the provided branch is the current branch, or if it does not exist, a
	 * message is printed out saying so. If there is a file within both the
	 * current branch and the provided branch, and both files have been updated
	 * since the splitting point of the two, the mergeConflicted method within
	 * the GitletController class is called, and a message conveying that there
	 * is a merge conflict is printed. Otherwise, the checkout and add methods
	 * within the GitletController class are called to merge the two branches,
	 * and the mergedParent instance variable of the current branch is set equal
	 * to the commit of the provided branch. Calls the findCommonAncestor()
	 * helper function within the Commit class to find the splitting point.
	 *
	 * Runs in linear time, relative to the commits in the history of each
	 * branch as well as the total number of files within the two.
	 *
	 * @param givenBranch
	 *            : The name of the branch, as a string, to be merged with the
	 *            current branch.
	 */

	public void merge(String givenBranch) {
		if (checkConflicted())
			return;
		if (myHead.equals(givenBranch)) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}
		Commit curr = myBranches.get(myHead);
		Commit given = myBranches.get(givenBranch);
		if (given == null) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		Commit split = curr.findCommonAncestor(given);
		boolean createdConflict = false;
		for (String file : given.myFiles.keySet()) {
			if (given.myFiles.get(file).myDate.compareTo(split.myDate) > 0) {
				if (curr.myFiles.containsKey(file)
						&& curr.myFiles.get(file).myDate
								.compareTo(split.myDate) > 0) {
					int cmtID = given.myFiles.get(file).id;
					GitletController.mergeConflicted(cmtID, file);
					createdConflict = true;
				} else {
					int cmtID = given.myFiles.get(file).id;
					GitletController.checkout(cmtID, file);
					staged.add(file);
					GitletController.add(file);
				}
			}
		}
		if (createdConflict) {
			System.out.println("Encountered a merge conflict.");
			isConflicted = true;
		} else {
			commit("Merged " + myHead + " with " + givenBranch + ".");
		}
	}

	/**
	 * Copies the current branch and appends the copy to the end of the provided
	 * branch. If the provided branch is the same as the current branch, the
	 * provided branch doesn't exist, or the splitting point between the current
	 * and provided branches is at either the provided or current branch,
	 * messages conveying these events are printed.
	 *
	 * Runs in time proportional to the histories of the current and given
	 * branches, as well as the total number of files being added.
	 *
	 * @param givenBranch
	 *            : A string representing the branch to be extended.
	 */

	public void rebase(String givenBranch) {
		if (checkConflicted())
			return;
		if (myHead.equals(givenBranch)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}
		Commit curr = myBranches.get(myHead);
		Commit given = myBranches.get(givenBranch);
		if (given == null) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		Commit split = curr.findCommonAncestor(given);
		if (split == given) {
			System.out.println("Already up-to-date.");
			return;
		}
		if (split == curr) {
			myBranches.put(myHead, given);
			return;
		}
		Stack<Commit> history = new Stack<Commit>();
		for (Commit c = curr; c != split; c = c.myParent) {
			history.push(c);
		}
		Commit mostRecent = given;
		while (!(history.empty())) {
			Commit toCopy = history.pop();
			HashMap<String, Commit> files = new HashMap<String, Commit>(
					mostRecent.myFiles);
			for (String file : mostRecent.myFiles.keySet()) {
				if (!(toCopy.myFiles.containsKey(file))) {
					files.remove(file);
				}
			}
			for (String file : toCopy.myFiles.keySet()) {
				if (toCopy.myFiles.get(file) == toCopy) {
					files.put(file, toCopy);
				}
			}
			Commit copy = new Commit(mostRecent, toCopy.myMessage, files);
			mostRecent = copy;
			GitletController.commit(copy.id + "/");
		}
		reset(String.valueOf(mostRecent.id));
	}

	/**
	 * A class representing a single commit, with a unique id, tracking of the
	 * commit's parent, a (possibly non-unique) message, a HashMap containing
	 * file names (as Strings) as keys and Commits (which represent the latest
	 * commit that that particular file was modified in) as values, and finally,
	 * the date and time that the commit was created.
	 *
	 */

	public class Commit implements Serializable {

		private int id;
		private Commit myParent;
		private String myMessage;
		private HashMap<String, Commit> myFiles;
		private Date myDate;

		public Commit(Commit parent, String message,
				HashMap<String, Commit> files) {
			myParent = parent;
			myMessage = message;
			myFiles = files;
			myDate = new Date();
			id = allCommits.size(); // replace with generateID
			if (!(findCommitID.containsKey(myMessage))) {
				findCommitID.put(myMessage, new HashSet<Commit>(2));
			}
			findCommitID.get(myMessage).add(this);
			allCommits.put(id, this);
		}

		/**
		 * Standard for the log() and globalLog() functions in the CommitPath
		 * class. Prints out the id, date, and message that corresponds to a
		 * particular commit.
		 *
		 * Runs in constant time.
		 */

		public void printLog() {
			System.out.println("===");
			System.out.println(id);
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(fmt.format(myDate));
			System.out.println(myMessage);
		}

		/**
		 * Finds where the current commit and the provided commit split apart
		 * from each other in terms of branching. Does so by recursively calling
		 * the function on either the current branch or the provided one based
		 * on their dates of creation. (Since all commands in Gitlet that
		 * involve copying commits give the commits new dates, this method will
		 * always find the correct common ancestor).
		 *
		 * Runs in time proportional to the number of commits being recursively
		 * traversed over. (The sum of the number of commits between the commit
		 * invoking the method and the common ancestor, and the number of
		 * commits between the argument of the method and the common ancestor).
		 *
		 * @param given
		 *            The commit whose splitting point with the current commit
		 *            is being found by this function.
		 */

		public Commit findCommonAncestor(Commit given) {
			if (this == given) {
				return this;
			}
			if (myDate.compareTo(given.myDate) > 0) {
				return this.myParent.findCommonAncestor(given);
			} else {
				return this.findCommonAncestor(given.myParent);
			}
		}

	}

}
