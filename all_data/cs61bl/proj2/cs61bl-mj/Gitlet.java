import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Implements a version control system with the basic features of git
 * 
 * @authors Nikat Patel 	cs61bl-do
 * 			Rowan Sandhu 	cs61bl-de
 * 			Ryan Panwar 	cs61bl-dk
 *          Shaswat Shah 	cs61bl-mj
 */

public class Gitlet implements Serializable {

	///////////////////////////////////////////////////////////////
	/********************** Gitlet object ************************/
	///////////////////////////////////////////////////////////////

	/**
	 * Gitlet instance variables
	 * 
	 * Classes:
	 * CommitTree: Handles commits, branches and related operations
	 * Commit: Represents a commit with all the relevant information
	 */
	private CommitTree commitTree;
	private HashSet<String> staged;
	private HashSet<String> untracked;
	private Commit currCommit;
	private boolean isConflicted;

	/**
	 * Constants to store directory paths for Gitlet metadata, staging area and
	 * serialization
	 */
	private static final String METADATA_DIR = ".gitlet";
	private static final String STAGING_DIR = METADATA_DIR + "/.staging";
	private static final String G_PATH = ".gitlet/gitlet.ser";
	
	/**
	 * Constants to check for valid commands
	 */
	private static final Set<String> VALID_COMMANDS = new HashSet<String>(Arrays.asList("init", "add", "commit", "rm",
			"log", "global-log", "find", "status", "checkout", "branch", "rm-branch", "reset", "merge", "rebase"));
	private static final Set<String> VALID_CONFLICTED_COMMANDS = new HashSet<String>(
			Arrays.asList("init", "add", "commit", "rm", "log", "global-log", "find", "status", "checkout"));

	/**
	 * Pseudo-constructor, deserialzes Gitlet instance if it exists, otherwise
	 * creates new Gitlet object
	 * 
	 * @return deserialized Gitlet instance if it exists, otherwise new Gitlet
	 *         instance
	 * @throws NonGitletException
	 *             in case of errors in serialization / deserialization
	 */
	private static Gitlet initialize() throws NonGitletException {
		
		if (isGitletInitialized()) {
			return serialRead();
		} else {
			return new Gitlet();
		}
	}

	///////////////////////////////////////////////////////////////
	/********************* Gitlet commands ***********************/
	///////////////////////////////////////////////////////////////

	/**
	 * Initializes a Gitlet repository
	 * 
	 * 1. Creates .gitlet and .gitlet/.staging folder
	 * 
	 * 2. Initializes instance variables
	 * 
	 * 3. Creates master branch and makes initial commit through CommitTree
	 *  constructor
	 * 
	 * @throws GitletException
	 *             in case a Gitlet repository already exists at the run
	 *             location
	 * @throws NonGitletException
	 */
	public void init() throws GitletException, NonGitletException {

		if (isExistingDir(METADATA_DIR))
			throw new GitletException("A gitlet version control system already exists in the current directory.");

		createDir(METADATA_DIR);
		createDir(STAGING_DIR);

		commitTree = new CommitTree();
		currCommit = commitTree.getCommit(0);
		isConflicted = false;
		staged = new HashSet<String>();
		untracked = new HashSet<String>();
	}

	/**
	 * 1. If a file has been marked for untracking in the previous commit,
	 * unmarks the file
	 * 
	 * 2. Else creates a copy of the file as it exists in the staging area
	 * and stages the file for the next commit
	 * 
	 * @param fileToAdd
	 *            path of the file to be added
	 * @throws GitletException
	 *             if a file does not exist at the specified path
	 * @throws NonGitletException
	 *             in case of IOException in copying file to staging area
	 */
	public void add(String fileToAdd) throws GitletException, NonGitletException {

		if (!isExistingFile(fileToAdd))
			throw new GitletException("File does not exist.");

		if (untracked.contains(fileToAdd)) {
			untracked.remove(fileToAdd);
			return;
		}

		copy(fileToAdd, STAGING_DIR + "/" + fileToAdd);
		staged.add(fileToAdd);
	}

	/**
	 * 1. Creates a new commit which tracks the files tracked in the previous
	 * commit except for those marked for untracking in addition to newly staged
	 * files
	 * 
	 * 2. Renames staging area to commit id and creates new empty staging area
	 * for next commit
	 * 
	 * 3. Resets the list with files marked for untracking and isConflicted state
	 * 
	 * @param message
	 *            the commit message for the new commit
	 * @throws NonGitletException
	 *             in case of IOException in case of renaming staging directory
	 *             to commit id or creating new staging directory
	 */
	public void commit(String message) throws NonGitletException {

		HashMap<String, String> newTracking = new HashMap<String, String>();
		newTracking.putAll(currCommit.getTracking());
		newTracking.keySet().removeAll(untracked);
		
		Integer id = generateNextCommitId(newTracking, currCommit, message);
		String newDir = METADATA_DIR + "/" + id.toString();
		for (String a : staged)
			newTracking.put(a, newDir + "/" + a);

		Commit tmp = new Commit(newTracking, currCommit, message, id);
		commitTree.addCommit(tmp);
		currCommit = tmp;

		move(STAGING_DIR, newDir);
		createDir(STAGING_DIR);

		staged = new HashSet<String>();
		untracked = new HashSet<String>();
		isConflicted = false;
	}

	/**
	 * 1. Removes file from staging area, if it has been staged
	 * 
	 * 2. Marks file for untracking, if it was tracked in the previous commit
	 * 
	 * @param fileToRemove
	 *            path of file to be removed
	 * @throws GitletException
	 *             if the file is not staged or tracked in the previous commit
	 * @throws NonGitletException
	 *             in case of IOException in deleting fileToRemove
	 */
	public void rm(String fileToRemove) throws GitletException, NonGitletException {

		if (staged.remove(fileToRemove)) {
			delete(STAGING_DIR + "/" + fileToRemove);
			return;
		}

		if (currCommit.getTracking().containsKey(fileToRemove)) {
			untracked.add(fileToRemove);
			return;
		}
		
		throw new GitletException("No reason to remove file.");
	}

	/**
	 * Prints the branches in the commit tree while highlighting the current
	 * branch, the list of staged file and the list of files marked for
	 * untracking in the specified format
	 */
	public void status() {

		System.out.println("=== Branches ===");
		commitTree.printBranches();

		System.out.println("\n=== Staged Files ===");
		for (String a : staged)
			System.out.println(a);

		System.out.println("\n=== Files Marked for Untracking ===");
		for (String a : untracked)
			System.out.println(a);
	}

	/**
	 * Covers two cases
	 * 
	 * A. Switches to given branch and replaces all files in the working
	 * directory tracked by the head commit of the branch with the tracked
	 * versions in that commit
	 * 
	 * B. Replaces the given file in the working directory with the version
	 * tracked in the most recent commit
	 * 
	 * @param name
	 *            branch to switch to / file to replace with tracked version in
	 *            the latest commit
	 * @throws GitletException
	 *             in case no branch with the given name exists and the file is
	 *             not tracked in the most recent commit
	 * @throws NonGitletException
	 *             in case of IOException while copying file from commit folder
	 *             to working directory
	 */
	public void checkout(String name) throws GitletException, NonGitletException {

		if (commitTree.isBranch(name)) {
			
			if(isConflicted)
				throw new GitletException("Cannot do this command until the merge conflict has been resolved.");
			
			if (name.equals(commitTree.getCurrBranch()))
				throw new GitletException("No need to checkout the current branch.");

			commitTree.setCurrBranch(name);
			currCommit = commitTree.getBranchHead(name);
			HashMap<String, String> t = currCommit.getTracking();

			for (String a : t.keySet())
				copy(t.get(a), a);

			return;
		}

		HashMap<String, String> t = currCommit.getTracking();
		String p = t.get(name);

		if (p == null)
			throw new GitletException("File does not exist in the most recent commit, or no such branch exists.");

		copy(p, name);
	}

	/**
	 * Replaces the given file with the tracked version of that file in the
	 * commit with the given commit id
	 * 
	 * @param id
	 *            the id of the commit with the desired version of the file
	 * @param file
	 *            the file to replace with the tracked version
	 * @throws GitletException
	 *             in case no commit with that id exists, in case file is not
	 *             tracked in the commit with the given id
	 * @throws NonGitletException
	 *             in case of IOException while copying file from commit folder
	 *             to working directory
	 */
	public void checkout(int id, String file) throws GitletException, NonGitletException {

		Commit c = commitTree.getCommit(id);
		if (c == null)
			throw new GitletException("No commit with that id exists.");

		HashMap<String, String> t = c.getTracking();
		String p = t.get(file);

		if (p == null)
			throw new GitletException("File does not exist in that commit.");

		copy(p, file);
	}

	/**
	 * Replaces all files in the working directory tracked in the given commit
	 * with the tracked versions from the commit
	 * 
	 * @param id
	 *            the id of the commit the user wants to reset to
	 * @throws GitletException
	 *             in case no commit with the given id exists
	 * @throws NonGitletException
	 *             in case of IOException while copying files from commit folder
	 *             to working directory
	 */
	public void reset(int id) throws GitletException, NonGitletException {

		Commit c = commitTree.getCommit(id);
		if (c == null)
			throw new GitletException("No commit with that id exists.");

		HashMap<String, String> t = c.getTracking();
		for (String p : t.keySet())
			copy(t.get(p), p);

		commitTree.setCurrCommit(c);
	}

	/**
	 * 1. Merges current branch with the given branch
	 * 
	 * 2. Replaces all files modified after split point in given branch but not in
	 * current branch with the versions from the commit at the head of the given
	 * branch and automatically stages them for the next commit
	 * 
	 * 3. If one or more files have been modified after the split point in both the
	 * current branch and the given branch, copies the version from the commit at
	 * the head of the given branch to the working directory with a .conflicted
	 * suffix and puts the repository into a conflicted state
	 * 
	 * 4. Keeps the current versions of all files which have been modified in the
	 * current branch after split point but not in the given branch
	 * 
	 * @param branchName
	 *            branch to merge with
	 * @throws GitletException
	 *             in case the given name is the current branch, in case no
	 *             branch with the given name exists, in case a merge conflict
	 *             was encountered
	 * @throws NonGitletException
	 *             in case of IOException in copying files from commit at the
	 *             head of the given branch to the working directory or copying
	 *             files to staging area
	 */
	public void merge(String branchName) throws GitletException, NonGitletException {

		if (branchName.equals(commitTree.getCurrBranch()))
			throw new GitletException("Cannot merge a branch with itself.");

		if (!commitTree.isBranch(branchName))
			throw new GitletException("A branch with that name does not exist.");

		Commit split = commitTree.getSplitPoint(branchName);
		HashMap<String, String> branchTracking = commitTree.getBranchHead(branchName).getTracking();
		HashMap<String, String> currTracking = currCommit.getTracking();
		Date splitTime = split.getTime();

		for (String file : branchTracking.keySet()) {
			
			if(!currTracking.containsKey(file)) {
				copy(branchTracking.get(file), file);
				add(file);
				continue;
			}
			
			if (lastModified(branchTracking.get(file)).compareTo(splitTime) > 0) {
				if (currTracking.containsKey(file) && lastModified(currTracking.get(file)).compareTo(splitTime) > 0) {
					isConflicted = true;
					copy(branchTracking.get(file), file + ".conflicted");
				} else {
					copy(branchTracking.get(file), file);
					add(file);
				}
			}
		}

		if (!isConflicted)
			commit("Merged " + commitTree.getCurrBranch() + " with " + branchName + ".");
		else
			throw new GitletException("Encountered a merge conflict.");

	}
	
	/**
	 * 1. Rebases current branch after split point at the end of the given branch
	 * 
	 * 2. Replays commits in current after split point in the order the commits were
	 * created
	 * 
	 * 3. If files were modified in the given branch after split point, then keeps file
	 * snapshots from head commit of given branch in the replayed commits if the files
	 * were not modified in the replayed commit. In case both were modified after split
	 * point, keeps replayed commit's version
	 * 
	 * 4. Resets to new head commit after replaying all commits
	 * 
	 * @param branchName
	 *            branch to rebase from
	 * @throws GitletException
	 *             in case the given name is the current branch, in case no
	 *             branch with the given name exists, in case the given branch is in
	 *             the history of the current branch
	 * @throws NonGitletException
	 *             in case of IOException in getting the last modified time of file
	 *             snapshots from various commits or in the reset command
	 */
	public void rebase(String branchName) throws GitletException, NonGitletException {
		
		if(branchName.equals(commitTree.getCurrBranch()))
			throw new GitletException("Cannot rebase a branch onto itself.");
		
		if (!commitTree.isBranch(branchName))
			throw new GitletException("A branch with that name does not exist.");

		Commit split = commitTree.getSplitPoint(branchName);
		Commit c = commitTree.getBranchHead(branchName);
		
		if(c.equals(split))
			throw new GitletException("Already up-to-date.");
		
		if(currCommit.equals(split)) {
			commitTree.setCurrCommit(c);
			reset(currCommit.getId());
			return;
		}
		
		HashMap<String, String> branchTracking = c.getTracking();
		HashMap<String, String> currTracking = currCommit.getTracking();
		c = currCommit;
		currCommit = commitTree.getBranchHead(branchName);		
		Stack<Commit> toAdd = new Stack<Commit>();
		
		while (c != split) {
			toAdd.push(c);
			c = c.getParent();
		}
		
		Date splitTime = split.getTime();
		HashMap<String, String> modifiedInBranch = new HashMap<String, String>();
		for (String file : branchTracking.keySet()) {
			if (!currTracking.containsKey(file) || lastModified(branchTracking.get(file)).compareTo(splitTime) > 0)
				modifiedInBranch.put(file, branchTracking.get(file));
		}
		
		while (!toAdd.isEmpty()) {
			
			c = toAdd.pop();	
			HashMap<String, String> cTracking = c.getTracking();
			HashMap<String, String> tmpTracking = new HashMap<String, String>();
			tmpTracking.putAll(cTracking);
			
			for (String file : modifiedInBranch.keySet()) {
				if (tmpTracking.containsKey(file) && lastModified(tmpTracking.get(file)).compareTo(splitTime) > 0) {
					modifiedInBranch.remove(file);
				} else {
					tmpTracking.put(file, modifiedInBranch.get(file));
				}
			}
			
			int id = generateNextCommitId(tmpTracking, currCommit, c.getMessage());
			Commit temp = new Commit(tmpTracking, currCommit, c.getMessage(), id);
			commitTree.addCommit(temp);
			currCommit = temp;
		}
		
		reset(currCommit.getId());
	}

	///////////////////////////////////////////////////////////////
	/*********************** Main method *************************/
	///////////////////////////////////////////////////////////////

	/**
	 * 1. Interprets the given arguments as Gitlet commands
	 * 
	 * 2. Serializes / deserializes an instance of Gitlet
	 * 
	 * 3. Catches GitletException and NonGitletException while printing error
	 * messages only in case of GitletException
	 * 
	 * @param args
	 *            array of strings to be interpreted as Gitlet commands
	 */
	public static void main(String[] args) {

		try {
			
			Gitlet git = Gitlet.initialize();
			
			if (args.length == 0)
				throw new GitletException("Please enter a command.");
			
			if(!VALID_COMMANDS.contains(args[0]))
				throw new GitletException("No command with that name exists.");

			if(git.isConflicted && !VALID_CONFLICTED_COMMANDS.contains(args[0]))
				throw new GitletException("Cannot do this command until the merge conflict has been resolved.");
				
			git.commandParse(args);
			
			Gitlet.serialWrite(git);
			
		} catch (GitletException e1) {
			System.out.println(e1.getMessage());
		} catch (NonGitletException e2) {
			// System.out.println(e2.getMessage());
		} catch (Exception e3) {
			// System.out.println(e3.getMessage());
		}
	}

	///////////////////////////////////////////////////////////////
	/********************** Command parser ***********************/
	///////////////////////////////////////////////////////////////	
	
	/**
	 * Method to parse the given command and call the corresponding
	 * method. Assumes invalid state commands have been filtered
	 * 
	 * @param args
	 *            commands to be parsed
	 * @throws GitletException
	 *             in case any of the commands throws a GitletExcetion, in case
	 *             of no changes for commit, in case of empty commit message, in
	 *             case checkout is given a non-integer commit id
	 * @throws NonGitletException
	 *             in case any of the commands throws a NonGitletExcetion
	 */
	private void commandParse(String[] args) throws GitletException, NonGitletException {
		
		int argsLength = args.length;
		
		if (args[0].equals("init")) {
			init();
		} else if (args[0].equals("add")) {
			if (argsLength == 2)
				add(args[1]);
		} else if (args[0].equals("commit")) {
			if (staged.isEmpty() && untracked.isEmpty())
				throw new GitletException("No changes added to the commit.");
			if (argsLength == 1)
				throw new GitletException("Please enter a commit message.");
			if (argsLength == 2)
				commit(args[1]);
		} else if (args[0].equals("rm")) {
			if (argsLength == 2)
				rm(args[1]);
		} else if (args[0].equals("log")) {
			if (argsLength == 1)
				commitTree.log();
		} else if (args[0].equals("global-log")) {
			if (argsLength == 1)
				commitTree.globalLog();
		} else if (args[0].equals("find")) {
			if (argsLength == 2)
				commitTree.find(args[1]);
		} else if (args[0].equals("status")) {
			if (argsLength == 1)
				status();
		} else if (args[0].equals("checkout")) {
			if (argsLength == 2)
				checkout(args[1]);
			if (argsLength == 3) {
				try {
					checkout(Integer.parseInt(args[1]), args[2]);
				} catch (NumberFormatException e) {
					throw new GitletException("No commit with that id exists.");
				} 
			}
		} else if (args[0].equals("branch")) {
			if (argsLength == 2)
				commitTree.createBranch(args[1]);
		} else if (args[0].equals("rm-branch")) {
			if (argsLength == 2)
				commitTree.rmBranch(args[1]);
		} else if (args[0].equals("reset")) {
			if (argsLength == 2)
				reset(Integer.parseInt(args[1]));
		} else if (args[0].equals("merge")) {
			if (argsLength == 2)
				merge(args[1]);
		} else if (args[0].equals("rebase")) {
			if (argsLength == 2)
				rebase(args[1]);
		}
	}
	
	///////////////////////////////////////////////////////////////
	/******************* Commit ID generator *********************/
	///////////////////////////////////////////////////////////////	
	
	/**
	 * Generates next commit id based on given parameters and the current system
	 * time
	 * 
	 * @param tracking
	 * 			  the tracking list of the intended commit
	 * @param parent
	 * 			  the intended parent of the commit
	 * @param message
	 * 			  the intended message of the commit
	 * @return the new commit id based on hash codes of the given parameters and
	 * current time
	 */
	private static int generateNextCommitId(HashMap<String, String> tracking, Commit parent, String message) {
		return Math.abs(tracking.hashCode() + parent.hashCode() + message.hashCode() + new Date().hashCode());
	}
	
	///////////////////////////////////////////////////////////////
	/********************* File operations ***********************/
	///////////////////////////////////////////////////////////////

	/**
	 * Checks if the given path points to an existing file
	 * 
	 * @param path
	 *            the file whose existence is to be checked
	 * @return true if the given path is an existing file, false otherwise
	 */
	private static boolean isExistingFile(String path) {
		return Files.exists(Paths.get(path)) && !Files.isDirectory(Paths.get(path));
	}

	/**
	 * Checks if the given path points to an existing directory
	 * 
	 * @param path
	 *            the directory whose existence is to be checked
	 * @return true if the given path is an existing directory, false otherwise
	 */
	private static boolean isExistingDir(String path) {
		return Files.isDirectory(Paths.get(path));
	}

	/**
	 * Creates the required directory structure for the given path
	 * 
	 * @param toCreate
	 *            path which conatins the required directory structure
	 * @throws NonGitletException
	 *             in case of IOException
	 */
	private static void createDir(String toCreate) throws NonGitletException {
		Path dir = Paths.get(toCreate);
		if (Files.exists(dir))
			return;
		try {
			Files.createDirectories(dir);
		} catch (IOException e) {
			throw new NonGitletException("IOException in createDir");
		}
	}

	/**
	 * Moves or renames a file/folder at a given source path to the given
	 * destination path if it exists while created the required directory
	 * structure if it does not exist
	 * 
	 * @param source
	 *            path of file to moved
	 * @param dest
	 *            path to where the file is to be moved
	 * @throws NonGitletException
	 *             in case of IOException @param source
	 */
	private static void move(String source, String dest) throws NonGitletException {
		if (!Files.exists(Paths.get(source)))
			return;
		Path moveSource = Paths.get(source);
		Path moveDest = Paths.get(dest);
		createDir(dest);
		try {
			Files.move(moveSource, moveDest, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new NonGitletException("IOException in move");
		}
	}

	/**
	 * Copies a file at a given source path to the given destination path if it
	 * exists while created the required directory structure if it does not
	 * exist
	 * 
	 * @param source
	 *            path of file to copied
	 * @param dest
	 *            path to where the file is to be copied
	 * @throws NonGitletException
	 *             in case of IOException
	 */
	private static void copy(String source, String dest) throws NonGitletException {
		if (!isExistingFile(source))
			return;
		Path copySource = Paths.get(source);
		Path copyDest = Paths.get(dest);
		createDir(dest);
		try {
			Files.copy(copySource, copyDest, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new NonGitletException("IOException in copy");
		}
	}

	/**
	 * Deletes a file at the given path if it exists
	 * 
	 * @param fileToDelete
	 *            file to be deleted
	 * @throws NonGitletException
	 *             in case of IOException
	 */
	private static void delete(String fileToDelete) throws NonGitletException {
		try {
			Files.deleteIfExists(Paths.get(fileToDelete));
		} catch (IOException e) {
			throw new NonGitletException("IOException in delete");
		}
	}

	/**
	 * Gets last modified time of the file at the path in the String file
	 * 
	 * @param file
	 *            file whose last modified time is required
	 * @return the last modified time of the file at the path
	 * @throws NonGitletException
	 *             in case the file does not exist at the given path, in case of
	 *             IOException
	 */
	private static Date lastModified(String file) throws NonGitletException {
		if (!isExistingFile(file))
			throw new NonGitletException("lastModified: File does not exist");
		Path fileAccess = Paths.get(file);
		try {
			return new Date(Files.getLastModifiedTime(fileAccess).toMillis());
		} catch (IOException e) {
			throw new NonGitletException("IOException in lastModified");
		}
	}

	///////////////////////////////////////////////////////////////
	/*********************** Serial Code *************************/
	///////////////////////////////////////////////////////////////

	/**
	 * Checks if there is a serialized version of Gitlet
	 * 
	 * @return true if there is a serialized instance of Gitlet, false otherwise
	 */
	private static boolean isGitletInitialized() {
		return Files.exists(Paths.get(G_PATH));
	}

	/**
	 * Deserializes the preserved instance of the Gitlet object to restore the
	 * state of the data structures in Gitlet
	 * 
	 * @return the deserialized Gitlet instance
	 * @throws NonGitletException
	 *             in case of IOException in reading the object in case of, in
	 *             case of ClassNotFoundException in the read object
	 */
	private static Gitlet serialRead() throws NonGitletException {
		Gitlet buff = null;
		try {
			ObjectInput input = new ObjectInputStream(new FileInputStream(G_PATH));
			try {
				buff = (Gitlet) input.readObject();
				input.close();
			} catch (ClassNotFoundException e2) {
				input.close();
				throw new NonGitletException("ClassNotFoundException in serialRead");
			}
		} catch (IOException e) {
			throw new NonGitletException("IOException in serialRead");
		}
		return buff;
	}

	/**
	 * Serializes the current version of the Gitlet object so as to preserve the
	 * state of the data structures in Gitlet
	 * 
	 * @param g
	 *            the Gitlet instance to serialized
	 * @throws NonGitletException
	 *             in case of IOException in writing the object
	 */
	private static void serialWrite(Gitlet g) throws NonGitletException {
		try {
			ObjectOutput output = new ObjectOutputStream(new FileOutputStream(G_PATH));
			output.writeObject(g);
			output.close();
		} catch (IOException e) {
			throw new NonGitletException("IOException in serialWrite");
		}
	}
}