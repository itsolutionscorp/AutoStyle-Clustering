import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Database implements Serializable {

	private static final long serialVersionUID = 1L;
	private HashMap<Integer, CommitGraph> dbase; // stores all commitGraphs in
													// the
	// database, keeps track of overall
	// structure
	private int headID; // Index of the branch indicated by the head pointer,
						// any commit will add a node to the end
	private HashSet<String> staged; // holds the filenames of files that
									// have been staged for the next
									// commit
	private HashSet<String> untrack; // holds the filenames of files that
										// will be untracked during the next
										// commit
	private String currentBranchName; // the current branch checked out most
										// recently
	private ArrayList<String> branchNames; // an ArrayList of branch names
	private HashMap<String, Integer> branchEndCommits; // stores the most recent
														// commits in each
														// branch
	private boolean isReset = false;
	private boolean isNewBranch = false;
	private  HashMap<String, Boolean> splitMemory; //keeps track of if a branch has just split
	private HashMap<Integer, String> commitMsgs;
	private boolean conflictedState;

	/**
	 * Database constructor that is only called when Gitlet is initialized.
	 * Database stores and keeps track of all relevant metadata, including which
	 * files have been untracked, which are staged, all of the past commits, the
	 * names of the branches, the end Nodes of each branch, the current branch,
	 * and the ID of the head branch. It also keeps track of commit messages, conflicted state, 
	 * resets and new branching.
	 * 
	 * Runtime: Constant
	 */
	public Database() {
		dbase = new HashMap<Integer, CommitGraph>();
		staged = new HashSet<String>();
		untrack = new HashSet<String>();
		branchNames = new ArrayList<String>();
		branchEndCommits = new HashMap<String, Integer>();
		commitMsgs = new HashMap<Integer, String>();
		headID = -1;
		currentBranchName = "master";
		conflictedState = false;
		splitMemory = new HashMap<String, Boolean>();
		splitMemory.put("master",false);
	}

	/**
	 * Adds a copy of the file given to the staging area and adds its name to
	 * stagedArray. If the file is in untrackedArray, it removes it but does not
	 * add it to stagedArray.
	 * 
	 * Runtime: the runtime is linear relative to the size of untrackArray
	 * 
	 * @param filename
	 *            a string that is typed from the command window and represents
	 *            the file name
	 * @throws IOException
	 *             if the file does not exist
	 */
	public void add(String filename) throws IOException {
		File myFile = new File(filename);
		if (isFile(filename)) {
			File dest = new File(".gitlet/staged/", filename);
			dest.getParentFile().mkdirs();
			Files.copy(myFile.toPath(), dest.toPath(),
					StandardCopyOption.REPLACE_EXISTING);
			if (untrack.contains(filename)) {
				untrack.remove(filename);
			} else if (!staged.contains(filename)) {
				staged.add(filename);
			}
		} else {
			System.out.println("File does not exist.");
		}
	}

	/**
	 * Performs a commit by saving updated versions of files or new files in a
	 * new CommitGraph in dbase. Adds the commit to end of the current branch
	 * and updates branchEndNodes and headID accordingly.
	 * 
	 * Runtime: Constant with respect to any measure of number of commits. 
	 * 			No worse than linear with respect to the total size of files in staged. 
	 * Memory Requirement: Committing must increase the size of the .gitlet folder by no
	 * 			 more than the total size of the staged files at the time of commit, not 
	 * 			including additional metadata (Database).
	 *  
	 * 
	 * @param msg
	 *       - the message to be associated with that Commit. Multi-word
	 *            messages should be surrounded with quotes.
	 * @throws IOException
	 */
	public void commit(String msg) throws IOException {
		if (msg.equals("initial commit")) {
			CommitGraph myCommit = new CommitGraph(this);
			headID = 0;
			dbase.put(myCommit.getCommitID(), myCommit);
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(".gitlet/commit0/commit0.ser"));
			out.writeObject(myCommit);
			out.close();
			branchEndCommits.put(currentBranchName, myCommit.getCommitID());
			commitMsgs.put(msg.hashCode(),
					Integer.toString(myCommit.getCommitID()));
		} else if (staged.size() == 0 && untrack.size() == 0) {
			System.out.println("No changes added to the commit.");
		} else {
			CommitGraph myCommit = new CommitGraph(this,
					branchEndCommits.get(currentBranchName), msg);
			headID++;
			branchEndCommits.put(currentBranchName, myCommit.getCommitID());
			dbase.put(myCommit.getCommitID(), myCommit);
			String stored = commitMsgs.put(msg.hashCode(),
					Integer.toString(myCommit.getCommitID()));
			String newLine = System.getProperty("line.separator").toString();
			if (stored != null) {
				commitMsgs.put(msg.hashCode(), commitMsgs.get(msg.hashCode())
						+ newLine + stored);
			}
			resetStaged();
			resetUntracked();
		}
		conflictedState = false;

	}

	/**
	 * Marks a file for untracking by adding it to the untrackArray. If the file
	 * is inside the staging area, we remove it from the stagedArray.
	 * 
	 * Runtime: Constant
	 * 
	 * @param filename
	 *            name of the file we want to remove
	 * @throws IOException
	 *             when IO methods don't work.
	 */

	public void rm(String filename) throws IOException {
		File f_staged = new File(".gitlet/staged/"
				+ Database.parsePath(filename));
		if (staged.contains(filename)) {
			staged.remove(filename);
			Files.delete(f_staged.toPath());
		} else {
			boolean helper = false;
			HashMap<String, Integer> tempFiles = dbase.get(this.getHeadID())
					.getFiles();
			helper = tempFiles.containsKey(filename);
			if (!helper) {
				System.out.println("No reason to remove the file.");
			} else {
				untrack.add(filename);
			}
		}
	}

	/**
	 * Starting at the current head commit, which we find through getHead,
	 * display information about each commit backwards along the commit tree
	 * until the initial commit. If should include three part of information:
	 * Commit ID, Commit Time, and Commit message. 
	 * 
	 * Calls logHelper.
	 * 
	 * Runtime: linear to number of commits
	 */
	public void log() {
		int id = branchEndCommits.get(currentBranchName);
		logHelper(id);
	}
	
	/**
	 * Helps the log method by allowing for recursion.
	 * 
	 * @param
	 *	currentID- ID of the commit getting printed, updates as it goes through a branch.
	 * 
	 * Runtime: linear to number of commits
	 */
	public void logHelper(int currentID) {
		System.out.println("===");
		System.out.println("Commit " + currentID);
		System.out.println(dbase.get(currentID).getCommitTime());
		System.out.println(dbase.get(currentID).getMessage());
		System.out.println("");
		if (dbase.get(currentID).getParent() >= 0) {
			logHelper(dbase.get(currentID).getParent());
		}
	}

	/**
	 * Like log, except displays information about all commits ever made. We
	 * order this by the time it was created.
	 * 
	 * Runtime: linear to number of commits
	 */
	public void global_log() {
		for (int i = 0; i < dbase.size(); i++) {
			CommitGraph temp = dbase.get(i);
			System.out.println("===");
			System.out.println("Commit " + temp.getCommitID());
			System.out.println(temp.getCommitTime());
			System.out.println(temp.getMessage());
			System.out.println("");
		}
	}

	/**
	 * Prints out the ID of the commit that has the given commit message. If
	 * there are multiple such commits, it prints the IDs out on separate lines.
	 * 
	 * @param msg
	 *            the message associated with the commit ID we are trying to
	 *            locate.
	 * 
	 *  Runtime: Linear relative to number of commits with given message.
	 */
	public void find(String msg) {
		if (commitMsgs.containsKey(msg.hashCode())) {
			System.out.println(commitMsgs.get(msg.hashCode()));
		} else {
			System.out.println("Found no commit with that message.");
		}

	}

	/**
	 * Displays what branches currently exist, and marks the current branch with
	 * a *. Displays what files have been staged. Displays what files have been
	 * marked for untracking.
	 * 
	 * Runtime: Linear relative to number of files in untracked and staged.
	 */
	public void status() {
		System.out.println("=== Branches ===");
		for (int i = 0; i < branchNames.size(); i++) {
			if (branchNames.get(i).equals(currentBranchName)) {
				System.out.print("*");
			}
			System.out.println(branchNames.get(i));
		}
		System.out.println("");
		System.out.println("=== Staged Files ===");
		Iterator<String> stagedIter = staged.iterator();
		while (stagedIter.hasNext()) {
			System.out.println(stagedIter.next());
		}
		System.out.println("");
		System.out.println("=== Files Marked for Untracking ===");
		Iterator<String> untrackIter = untrack.iterator();
		while (untrackIter.hasNext()) {
			System.out.println(untrackIter.next());
		}
	}

	/**
	 * Creates a new branch with the given name, and points it at the current
	 * head node We are not allowed different branch have same branch name. Does
	 * not change currentBranch to the new branch.
	 * 
	 * @param name
	 *            the name to be associated with the new branch
	 * 
	 *  Runtime: Constant
	 */
	public void branch(String name) {
		if (!branchNames.contains(name)) {
			branchNames.add(name);
			isNewBranch = true;
			splitMemory.put(currentBranchName, true);
			splitMemory.put(name, true);
			branchEndCommits.put(name, branchEndCommits.get(currentBranchName));
		} else {
			System.out.println("A branch with that name already exists.");
		}
	}

	/**
	 * Deletes the branch with the given name. This only means to delete the
	 * pointer associated with the branch; If you try to remove the branch
	 * you're currently, print error message.
	 * 
	 * @param branchName
	 *            the name of the branch to be removed
	 * 
	 *            Runtime: Constant
	 */
	public void rm_branch(String thebranchName) {
		if (currentBranchName.equals(thebranchName)) {
			System.out.println("Cannot remove the current branch.");
		} else {
			if (branchNames.contains(thebranchName)) {
				int index = branchNames.indexOf(thebranchName);
				branchNames.remove(index);
			} else {
				System.out.println("A branch with that name does not exist.");
			}
		}
	}

	/**
	 * There are three types of checkout, This one deals with type one and three.
	 * 	1. Check out the file as it exists in the current head commit.
	 *  Puts that version in working directory, overwrites old version if there.
	 * 		
	 *  2. Check out the file at given commit. 
	 *  
	 *  3. Check out takes in a branch name. Takes all files in the
	 *      commit at the head of the given branch, and puts them in the working
	 *       directory, overwriting the versions of the files that are already there etc.
	 *
	 * Also, at the end of this command, the given branch will now be considered the current branch.
	 * 
	 * @param fileORbranch 
	 * 	a name the user gives that may be a branch or file name, branch takes precedence.
	 * 
	 * Runtime: Linear relative to size of files.
	 * 
	 * @throws IOException
	 */
	public void checkout(String fileOrBranch) throws IOException {
		Integer id = branchEndCommits.get(currentBranchName);
		if (branchNames.contains(fileOrBranch)) {
			if (conflictedState) {
				System.out.println("Not allowed in conflicted state");
			} else {
				this.checkoutbranch(fileOrBranch);
			}
		} else {
			this.checkoutFileIndex(fileOrBranch, id);
		}
	}
	
	/**
	 * There are three types of checkout, This all of them, as they all call to here.
	 * 	1. Check out the file as it exists in the current head commit.
	 *  Puts that version in working directory, overwrites old version if there.
	 * 		
	 *  2. Check out the file at given commit. 
	 *  
	 *  3. Check out takes in a branch name. Takes all files in the
	 *      commit at the head of the given branch, and puts them in the working
	 *       directory, overwriting the versions of the files that are already there etc.
	 *
	 * Also, at the end of this command, the given branch will now be considered the current branch.
	 * 
	 * @param filename
	 * 	the name of a individual file
	 * 
	 * @param commitID
	 *  the commitID that file should first be looked for in
	 *  
	 *  Runtime: Linear relative to size of file.
	 * 
	 * @throws IOException
	 */
	public void checkoutFileIndex(String filename, int commitID)
			throws IOException {
		if (commitID > dbase.size() - 1 || commitID < 0) {
			System.out.print("No commit with that id exists.");
		} else {
			File f = new File(filename);
			CommitGraph current = dbase.get(commitID);
			if (!current.getFiles().containsKey(filename)) {
				System.out
						.println("File does not exist in the most recent commit, or no such branch exists.");
			} else {
				File f_commit = new File(".gitlet/commit"
						+ current.getFiles().get(filename) + "/" + filename);
				Files.copy(f_commit.toPath(), f.toPath(),
						StandardCopyOption.REPLACE_EXISTING);
			}
		}
	}

	/**
	 * There are three types of checkout, This one deals with type three. It is called with checkout, 
	 * it then calls checkoutFileIndex
	 * 
	 *  3. Check out takes in a branch name. Takes all files in the
	 *      commit at the head of the given branch, and puts them in the working
	 *       directory, overwriting the versions of the files that are already there etc.
	 *
	 * Also, at the end of this command, the given branch will now be considered the current branch.
	 * 
	 * @param aBranchName
	 * 			its the name of a branch.
	 * 
	 * Runtime: Linear relative to size of files.
	 * 
	 * @throws IOException
	 */
	public void checkoutbranch(String aBranchName) throws IOException {
		if (branchNames.contains(aBranchName)) {// is a branch checkout
			if (getCurrentBranch().equals(aBranchName) && isReset == false) {
				System.out.println("No need to checkout the current branch.");
			} else {
				int index = branchEndCommits.get(aBranchName);
				CommitGraph current = dbase.get(index);
				HashMap<String, Integer> fileMap = current.getFiles();
				for (Entry<String, Integer> entry : fileMap.entrySet()) {
					checkoutFileIndex(entry.getKey(), entry.getValue());
					//System.out.println("lolol" + entry.getKey());
				}
				isReset = false;
			}
			currentBranchName = aBranchName;
			headID = branchNames.indexOf(currentBranchName);
		}
	}

	/**
	 * Checks out all the files tracked by the given branch and changes that
	 * branch's head node to the given node.
	 * 
	 * Runtime: Linear relative to size of files. Constant to any measure involving number of commits.
	 * 
	 * @param ID
	 *            ID of the commit to checkout and make head
	 * @throws IOException
	 *             when IO methods don't work.
	 */
	public void reset(int ID) throws IOException {
		if (dbase.get(ID) != null) {
			CommitGraph temp = dbase.get(ID);
			String tempBranchName = temp.getBranchName();
			branchEndCommits.put(tempBranchName, ID);
			isReset = true;
			this.checkoutbranch(tempBranchName);
		} else {
			System.out.println("No commit with that id exists.");
		}
	}

	/**
	 * Merges two branches together. May cause collisions.
	 * 
	 * Runtime: linear in terms of length , linear to terms total size of new files added in commits
	 * 
	 * @param branchName
	 *           branchName to be grafted
	 * @throws IOException
	 *             when IO methods don't work.
	 */
	public void merge(String branchName) throws IOException {
		if (branchName.equals(currentBranchName)) {
			System.out.println("Cannot merge a branch with itself.");
		}
		CommitGraph branchEnd, headEnd, splitPoint;
		int headEndID = branchEndCommits.get(branchName);
		headEnd = dbase.get(headEndID);
		int index = branchNames.indexOf(branchName);
		if (index >= 0) {
			int branchEndID = branchEndCommits.get(branchNames.get(index));
			branchEnd = dbase.get(branchEndID);
		} else {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		ArrayList<Integer> branchEndList = new ArrayList<Integer>();
		int currentID = branchEnd.getCommitID();
		while (currentID > 0) {
			branchEndList.add(currentID);
			currentID = dbase.get(currentID).getParent();
		}
		branchEndList.add(0);
		ArrayList<Integer> headEndList = new ArrayList<Integer>();
		currentID = headEnd.getCommitID();
		while (currentID > 0) {
			headEndList.add(currentID);
			currentID = dbase.get(currentID).getParent();
		}
		headEndList.add(0);
		int count = 0;
		boolean splitPointFound = false;
		while (!splitPointFound && count < headEndList.size()
				&& count < branchEndList.size()) {
			splitPointFound = (headEndList.get(headEndList.size() - 1 - count) != branchEndList
					.get(branchEndList.size() - 1 - count));
			count++;
		}
		splitPoint = dbase.get(branchEndList.get(branchEndList.size() - count
				+ 1));
		// tempHead is a file name 
		Iterator<Entry<String, Integer>> it = headEnd.getFiles().entrySet().iterator();
		while(it.hasNext()){
			String tempHead =it.next().getKey();
			//null check;
			int placeHead;
			int placeBranch;
			int placeSplit;
			
			if(branchEnd.getFiles().containsKey(tempHead)){
				 placeHead = headEnd.getFiles().get(tempHead);
			}else{
				placeHead =-1;
			}
			if(branchEnd.getFiles().containsKey(tempHead)){
				placeBranch = branchEnd.getFiles().get(tempHead);
			}else{
				placeBranch = -2;
			}
			if(splitPoint.getFiles().containsKey(tempHead)){
				placeSplit = splitPoint.getFiles().get(tempHead);
			}else{
				placeSplit = -3;
			}

			if (branchEnd.getFiles().containsKey(tempHead)) {
				if (placeHead == placeSplit && placeBranch == placeSplit) { 
					checkoutFileIndex(tempHead, headEndID);
					add(tempHead);
				} else if (placeHead >= placeSplit && placeBranch == placeSplit) {
					checkoutFileIndex(tempHead, headEndID);
					add(tempHead);
				} else if (placeHead == placeSplit && placeBranch >= placeSplit) {												// changed
					checkoutFileIndex(tempHead, branchEnd.getCommitID());
					add(tempHead);
				} else {
					System.out.println("Encountered a merge conflict.");
					checkoutFileIndex(tempHead, branchEnd.getCommitID());
					File src_file = new File(tempHead);
					File dest_file = new File(tempHead + ".conflicted");
					Files.copy(src_file.toPath(), dest_file.toPath(),
							StandardCopyOption.REPLACE_EXISTING);
					checkoutFileIndex(tempHead, headEndID); 
					add(tempHead);
					conflictedState = true; 
				}
			} else {
				rm(tempHead);
			}
		}
		if (!conflictedState)
			commit("Merged " + currentBranchName + " with " + branchName + ".");
	}
	
	
	/**
	 * It finds the split point of the current branch, which we call splitID and
	 * the given branch, which we call given branch. then snaps off the current
	 * branch at this point and copies the new branch to the current one at its
	 * BranchEnd. Should also be linear relative to the total size of files added 
	 * to the given branch.Does not make any additional backup copies of files.
	 * 
	 * Runtime: linear relative to the size of the current branch and give
	 * branch. Linear in terms of number of files to be copied.
	 * 
	 * @param branchName
	 *            -the branch that will be moved
	 * 
	 **/ 
	
	public void rebase(String branchName) {
		int destID = branchEndCommits.get(currentBranchName); 
		int splitID = dbase.get(branchEndCommits.get(branchName)).getSplit(); 
		//checks failure cases
		if (branchName.equals(currentBranchName)) {
			System.out.println("Cannot merge a branch with itself.");
		} else if (!branchNames.contains(branchName)) {
			System.out.println("A branch with that name does not exist.");
		} else if (dbase.get(destID).getHistoryBranch().contains(branchName)){
			currentBranchName = branchName;
			System.out.println("Already up-to-date.");
			
		//runs command
		} else {
			
			int copyID = splitID; //where we first copy from
			int recentCopyID = copyCommit(copyID, destID);  //returns where we should now copy to
			System.out.println("first copy " + recentCopyID);
			while (dbase.get(copyID).getChildren().containsKey(branchName)) {
				int childID = dbase.get(copyID).getChildren().get(branchName);
				recentCopyID = copyCommit(childID, recentCopyID);
				copyID = childID;
			}
		}
	}

	private int copyCommit(int splitID, int destID) {
		CommitGraph cloneMom = dbase.get(splitID);
		CommitGraph copy = new CommitGraph(cloneMom, destID);
		headID = copy.getCommitID();
		dbase.put(copy.getCommitID(), copy);
		String msg = copy.getMessage();
		String stored = commitMsgs.put(msg.hashCode(),
				Integer.toString(copy.getCommitID()));
		String newLine = System.getProperty("line.separator").toString();
		if (stored != null) {
			commitMsgs.put(msg.hashCode(), commitMsgs.get(msg.hashCode())
					+ newLine + stored);
		}
		return copy.getCommitID();
	}
	
	
	/**
	 * Returns the name of a file from its path.
	 * 
	 * @param filepath
	 *            - the path to the file
	 * @return the new filename
	 */
	public static String parsePath(String filepath) {
		int index = filepath.lastIndexOf("\\");
		String filename = filepath.substring(index + 1);
		return filename;
	}

	/**
	 * Checks if something with that name exists and if that thing is a file,
	 * else prints an error message.
	 * 
	 * @param fileName
	 *            name of the file you want to check
	 * @return true if the file with that name exists
	 */
	public static boolean isFile(String fileName) {
		File myFile = new File(fileName);
		if (myFile.exists() && myFile.isFile()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Get method
	 * @return staged 
	 * 	- a HashSet<String> of the filenames in staged
	 */
	public HashSet<String> getStaged() {
		return staged;
	}
	
	/**
	 * Get method
	 * @return dbase
	 * 	- a HashSet<Integer, CommitGraph> the commit graphs and their CommitIDs
	 */
	public HashMap<Integer, CommitGraph> getDbase() {
		return dbase;
	}
	
	/**
	 * Get method
	 * @return untrack
	 * 	- a HashSet<String> of the filenames in untracked
	 */
	public HashSet<String> getUntracked() {
		return untrack;
	}

	/**
	 * Get method
	 * @return headID 
	 * 	- an int of current pointer to a commit.
	 */
	public int getHeadID() {
		return headID;
	}

	/**
	 * Get method
	 * @return staged 
	 * 	- a HashSet<Strings> of the filenames in staged
	 */
	public ArrayList<String> getBranchNames() {
		return branchNames;
	}

	/**
	 * Get method
	 * @return currentBranchName
	 * 	- a String of the current Branch's name
	 */
	public String getCurrentBranch() {
		return currentBranchName;
	}

	/**
	 * Get method
	 * @return conflictedState
	 * 	-boolean if it is conflictedState or no.
	 */
	public boolean getConflictedState() {
		return conflictedState;
	}

	/**
	 * Deletes a directory, for use in resetStaged. From stackOverflow
	 * @param file
	 */
	void deleteDir(File file) {
		File[] contents = file.listFiles();
		if (contents != null) {
			for (File f : contents) {
				deleteDir(f);
			}
		}
		file.delete();
	}
	
	/**
	 * Resets the staged folder and staged HashSet after a commit. Deletes all the files within.
	 *
	 * @throws IOException
	 *             when IO methods don't work.
	 */
	public void resetStaged() throws IOException {
		staged = new HashSet<String>();
		File staged = new File(".gitlet/staged");
		deleteDir(staged);
		staged.mkdirs();
	}

	/**
	 * Resets the Untracked HashSet after a commit.
	 */
	public void resetUntracked() {
		untrack = new HashSet<String>();
	}
	
	/**
	 * Old version
	 * Returns if there is a newBranch that hasn't been added to for use in CommitGraph.
	 */
	public boolean hasSplit() {
		return isNewBranch;
	}

	/**
	 * Old version
	 * Resets newBranch after a commit has been made to the new Branch or checkout has happened.
	 */
	public void setSplit(boolean b) {
		isNewBranch = b;
	}
	/**
	 * New Version
	 * Resets SplitMemory for rebase.
	 */
	public void setSplitMemory(String myBranchName, boolean b) {
		splitMemory.put(myBranchName, b);
		
	}
	/**
	 * New Version
	 * Gets SplitMemory for rebase.
	 */
	public boolean getSplitMemory(String myBranchName)
	{ return  splitMemory.get(myBranchName);}
}