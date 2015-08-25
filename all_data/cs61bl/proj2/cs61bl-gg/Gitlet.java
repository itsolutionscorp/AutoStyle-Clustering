import java.io.BufferedReader;
import java.io.DataInput;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.lang.Object;

	/**
	 * 
	 * @author Ignatius Ho, Madeline Wu, Tailai Lu
	 *
	 */
public class Gitlet implements Serializable {

	/** Gitlet Instance variables
	 * 
	 * current - Most recent commit GitNode of the currBranch
	 * currBranch - String of name of the current branch
	 * stagingArea - ArrayList of all files currently in the staging area
	 * branches - HashMap of all existing branches
	 * untracked - ArrayList of all currently untracked files
	 * conflicted - Returns true if the current state is conflicted, default is false
	 * commitNum - Returns next sequential commit ID numbers 
	 * allCommitsMessage - HashMap of all commit GitNodes ever made, looked up by their commit messages
	 * allCommitsByID - HashMap of all commit GitNodes ever made, looked up by their commit ID number
	 * branchMap - HashMap of branches and the IDs of GitNodes in their path that are split points
	 * branchHeads - HashMap of branches to their GitNode head, the first commit after the "split point"
	 */

	private GitNode current;
	private String currBranch;
	private ArrayList<File> stagingArea;
	private HashMap<String, GitNode> branches;
	private ArrayList<File> untracked;
	private boolean conflicted;
	private Integer commitNum;
	private HashMap<String, Collection<GitNode>> allCommitsByMessage;
	private HashMap<Integer, GitNode> allCommitsByID;
	private HashMap<String, Collection<GitNode>> branchMap;
	private HashMap<String, GitNode> branchHeads;


	/**
	 * Creates a new Gitlet object.
	 * 
	 */

	public Gitlet() {
		current = null;
		currBranch = "master";
		stagingArea = new ArrayList<File>();
		branches = new HashMap<String, GitNode>();
		untracked = new ArrayList<File>();
		conflicted = false;
		commitNum = 0;
		allCommitsByMessage = new HashMap<String, Collection<GitNode>>();
		allCommitsByID = new HashMap<Integer, GitNode>();
		branchMap = new HashMap<String, Collection<GitNode>>();
		branchHeads = new HashMap<String, GitNode>();
	}

	/**
	 * Command line argument that initializes a gitlet object .
	 * Checks for and makes a .gitlet folder if it does not exist.
	 * Automatically makes an initial commit with no files in it.
	 * Automatically creates a new branch and designates it as master.
	 */
	public void init() {
		File gitlet = new File(".gitlet/");
		if (gitlet.exists()) {
			System.out.println("A gitlet version control system already exists in the current directory.");
		} else {
			gitlet.mkdir();
			this.commit("initial commit");
			this.branches.put("master", current);
		}
	}

	/**
	 * Adds a file in the working directory to the staging area in for the upcoming commit.
	 * If a file from the previous commit was marked for untracking, adding it removes the untracked mark. 
	 * 
	 * @param fileName - name of the file to be added (e.g text1.txt).
	 */
	public void add(String fileName) {
		File f = new File(fileName);
		String parent = f.getParent();
		if (!f.exists()) {
			System.out.println("File does not exist.");
		} else if (addContainsHelper(fileName, untracked)) {
			removeHelper(fileName, untracked);			
		}else{
			File stagingArea = new File(".gitlet/StagingArea/");	
			File fileWithinStagingArea = new File (".gitlet/StagingArea/"+fileName);
			if (! stagingArea.exists())
				stagingArea.mkdir();
			File stagingParent = new File(stagingArea + "/"+ parent + "/");
			if (! stagingParent.exists()) {
				stagingParent.mkdir();
			}
			try {
				copyFile(f, fileWithinStagingArea);
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.stagingArea.add(f);				
		}
	}
	/**
	 * Helper method to determine if a file has already been added to the staging area.
	 * 
	 * @param filename - name of file to be added.
	 * @param X - ArrayList<File> to be checked if the file has already been added.
	 * 
	 */
	private boolean addContainsHelper(String filename, ArrayList<File> X) {
		for (int i=0; i<X.size(); i++) {
			if (( X.get(i)).getName()==filename) {
				return true;
			}
		}return false;
	}

	/**
	 * Removes file with the given String filename from the given ArrayList<File> 
	 * 
	 * @param filename - name of file to be removed
	 * @param x - ArrayList<File> to be checked
	 */
	private void removeHelper(String filename, ArrayList<File> x) {
		for (int i=0; i<x.size(); i++) {
			if (x.get(i).getName().equals(filename)) {
				x.remove(x.get(i));
			}
		}
	}

	/**
	 * Method for copying a file to a destination file.
	 * 
	 * @param sourceFile - file to be copied
	 * @param destFile - destintion to copy the file to
	 * @throws IOException
	 */

	@SuppressWarnings("resource")
	public static void copyFile(File sourceFile, File destFile) throws IOException {
		if(!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;

		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		}
		finally {
			if(source != null) {
				source.close();
			}
			if(destination != null) {
				destination.close();
			}
		}
	}

	/**
	 * Checks that staging area is not empty and there are no untracked files
	 * If the both of the above is true, returns an error message
	 * Makes a new Commit folder named "Commit-X" where X is the commit's id
	 * Adds all files in staging area into the commit folder
	 * Adds all files in parent commit, less those that were untracked, or updated and added to staging area
	 * Clears staging area
	 * 
	 * @param message - message that goes with the commit, cannot be left empty.
	 * the main method checks if there is an empty message
	 */
	public void commit(String message) {
		if (this.stagingArea.isEmpty() && this.untracked.isEmpty() && this.commitNum > 0) {
			System.out.println("No changes added to the commit.");
		} else{
			GitNode newCommit = new GitNode(this.current, this.commitNum, message, this.stagingArea);
			this.current = newCommit;
			// change branch pointer
			branches.put(this.currBranch, this.current);
			// add myCommit to the allCommitsByMessage HashMap
			if (! allCommitsByMessage.containsKey(message)) {
				allCommitsByMessage.put(message, new ArrayList<GitNode>());
			}
			allCommitsByMessage.get(message).add(current);
			// add myCommit to the allCommitsByID HashMap
			allCommitsByID.put(commitNum, newCommit);
			File commit = new File(".gitlet/Commit-"+commitNum+"/");
			try{
				commit.mkdir(); 
			} catch(Exception e){
				e.printStackTrace();
			} 
			if (current.myPrev != null) {
				for (File f:this.current.myPrev.files) {
					if (! this.stagingArea.contains(f) && ! this.untracked.contains(f)) {
						this.stagingArea.add(f);
						File source = new File(".gitlet/Commit-" + this.current.myPrev.id + "/" + f.getPath());
						File target = new File(".gitlet/StagingArea/" + f.getPath());
						try{
							copyFile(source, target);
						} catch (IOException e){
							e.printStackTrace();
						}
					}
				}
			}

			for (File f:this.stagingArea){
				String parent = f.getParent();
				File commitParent = new File(commit + "/" + parent + "/");
				if (! commitParent.exists())
					commitParent.mkdir();
				File source = new File(".gitlet/StagingArea/" + f.getPath());
				File target = new File(".gitlet/Commit-"+ commitNum +"/"+ f.getPath());
				try{
					copyFile(source, target);
				} catch (IOException e){
					e.printStackTrace();
				}
				File delete = new File(".gitlet/StagingArea/" + f.getPath());
				delete.delete();
			}

			this.current.files = stagingArea;
			this.stagingArea = new ArrayList<File>();
			this.commitNum++;
			this.conflicted = false;
		}
	}




	/**
	 * Removes file from the staging area.
	 * If it is not in the staging area, checks if it is in the parent commit.
	 * If it exists in parent commit, marks it for untracked and adds it the untracked ArrayList<File>.
	 * Prevents said file from being added to upcoming commit.
	 * 
	 * @param fileName - name of the file to be removed or untracked.
	 */
	public void rm(String fileName) {
		File reference = new File(fileName);
		File file = new File(".gitlet/StagingArea/" + fileName);
		File stagingArea = new File(".gitlet/StagingArea/");

		if (this.stagingArea.contains(reference)) {
			this.stagingArea.remove(reference);

			if (stagingArea.exists() && stagingArea.isDirectory()){
				file.delete();
			}

		}else{
			if (untracked.contains(reference)) {
				System.out.println("No reason to remove the file.");
			}else{
				untracked.add(reference);
			}
		}

	}


	/**
	 * Prints out commit history of each GitNode in the current branch, tracing back to the initial commit (Commit 0).
	 * Starts from the Commit that the current branch pointer refers to.
	 * For each GitNode, print out the ID, time, message.
	 */

	public void log() {
		GitNode curr = this.current;
		curr.print();
	}

	/**
	 * Prints out the commit history of every single commit, regardless of branch.
	 */
	public void global_log() {
		for (int i=0;i<this.commitNum;i++){
			GitNode g = this.allCommitsByID.get(i);
			if (g != null) {
				g.printMeOnly();
			}
		}
	}

	/**
	 * Retrieves the commit ID of the commit that matches the given commit message.
	 * Returns "Found no commit with that message." if no commit is found.
	 * 
	 * @param commitMessage - message to find.
	 */
	public void find(String commitMessage) {

		int counter=0;
		ArrayList<GitNode> find = new ArrayList<GitNode>();

		for (int i = 0; i<find.size(); i++) {
			if (find.get(i).message==commitMessage) {
				System.out.println(find.get(i).id);
				counter++;
			}
		}

		if (counter==0) {
			System.out.println("Found no commit with that message.");
		}
	}

	/**
	 * Prints out all existing branches and marks the current branch with a *
	 * Prints out list of staged files, if any.
	 * Prints out list of untracked files, if any.
	 * All information is printed out in console and in new lines.
	 * Each section, Branches, Staged Files, Untracked Files are separated by a new line.
	 */
	public void status() {
		System.out.println("=== Branches ===");
		if (! branches.isEmpty()) {
			for (String branch: branches.keySet()) {
				if (branch.equals(currBranch))
					System.out.println("*" + branch);
				else
					System.out.println(branch);
			}
		}
		System.out.println();

		System.out.println("=== Staged Fies ===");
		for (File file: stagingArea) {
			System.out.println(file.getName());
		}
		System.out.println();

		System.out.println("=== Files Marked for Untracking ===");
		for (File file: untracked) {
			System.out.println(file.getName());
		}
	}

	/**
	 * Finds the corresponding file in the recent commit and copies it into the working directory.
	 * Overwrites the current file, if it exists, in the working directory.
	 * Returns "File does not exist in the most recent commit, or no such branch exists." is the file does not exist in recent commit.
	 * 
	 * @param thisFile - file to be checked out.
	 */
	public void checkout(File thisFile) {
		String fileName = thisFile.getName();
		File replace = current.getFile(fileName);
		if(replace == null)
			System.out.println("File does not exist in the most recent commit, or no such branch exists.");
		else{
			try{
				copyFile(thisFile, replace);
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * Finds the commit that corresponds to the id given.
	 * Finds the corresponding file within that commit and copies it into the working directory.
	 * Overwrites the current file, if it exists, in the working directory.
	 * Returns "No commit with that id exists." if no commit with the given id exists.
	 * Returns "File does not exist in that commit." if file is not found.
	 * Returns "Please check that you have enter a valid number for the commitID." if id given is not an Integer.
	 * 
	 * @param id - id of commit that file is to be checked out from, input as a String before being parsed as an Integer.
	 * @param fileName - name of file to be checked out.
	 */
	public void checkout(String id, String fileName) {
		try{
			Integer commitID = Integer.parseInt(id);
			if(!allCommitsByID.containsKey(commitID))
				System.out.println("No commit with that id exists.");
			else{
				File replace = new File(".gitlet/Commit-"+id+"/" + fileName);
				if (replace.exists()) {
					checkout(replace);
				} else {
					System.out.println("File does not exist in that commit.");
				}
			}
		} catch(NumberFormatException e){
			e.printStackTrace();
			System.out.println("Please check that you have enter a valid number for the commitID.");
		}
	}

	/**
	 * Finds the branch that corresponds to the given branch name.
	 * Find the head commit that the branch points to.
	 * Copies all files in that commit into the working directory.
	 * Overwrites any existing files that were in the working directory.
	 * Changes current branch to the given branch.
	 * Returns "No need to checkout the current branch." if current branch is already the given branch.
	 * Returns "File does not exist in the most recent commit, or no such branch exists." if given branch does not exist
	 * 
	 * @param branchName - name of the branch to be checked out.
	 */
	public void checkout(String branchName) {
		if (branchName.equals(currBranch)) {
			System.out.println("No need to checkout the current branch.");
			return;
		}
		if (! this.branches.containsKey(branchName)) {
			File f = new File(".gitlet/Commit-"+current.id+"/"+branchName);
			if(f.exists()){
				this.checkout(f);
				return;
			}
			System.out.println("File does not exist in the most recent commit, or no such branch exists.");
			return;
		}
		GitNode searchMe = this.branches.get(branchName); // gives you a GitNode
		for (File f:searchMe.files){
			File source = new File(".gitlet/Commit-" + searchMe.id + "/" + f.getPath());
			try{
				copyFile(source, f);
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		currBranch = branchName;
		this.current = branches.get(currBranch);
	}

	/**
	 * Adds a new branch with the given name
	 * Sets new branch to be the current working branch.
	 * New branch points towards the GitNode/commit that the previous working branch points to.
	 * Returns "A branch with that name already exists." if a branch with the given name already exists.
	 * 
	 * @param branchName - name of the new branch.
	 */
	public void branch(String branchName) {
		if (branches.containsKey(branchName)) {	
			System.out.println("A branch with that name already exists.");
			return;
		}
		branches.put(branchName, current);
		if (! branchMap.containsKey(branchName)) {
			branchMap.put(branchName, new ArrayList<GitNode>());
		}
		branchMap.get(branchName).add(current);
		if (! branchMap.containsKey(currBranch)) {
			branchMap.put(currBranch, new ArrayList<GitNode>());
		}
		branchMap.get(currBranch).add(current);
		// need this for rebase method
		branchHeads.put(branchName, current);

	}

	/**
	 * Removes the pointer that the branch with the given name points towards
	 * Does not remove/delete commits
	 * Returns "A branch with that name does not exist." if no such branch exists
	 * Returns "Cannot remove the current branch." if the given branch is the current working branch
	 * 
	 * @param branchName - name of branch to be removed.
	 */
	public void rm_Branch(String branchName) {
		if (! branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} 
		if (branchName.equals(currBranch)) {
			System.out.println("Cannot remove the current branch.");
			return;
		}
		branches.remove(branchName);
	}

	/**
	 * Find the commit that matches the given id.
	 * Checks out all the files in that commit.
	 * Moves current branch head pointer to that commit
	 * Returns "No commit with that id exists." if no commit with that id exists.
	 * Returns "Please check that you have enter a valid number for the commitID." if input cannot be parsed to Integer.
	 * 
	 * @param id - id of the commit to reset to.
	 */
	public void reset(String id) {
		// 		check if the id exists
		try{
			int num = Integer.parseInt(id);
			if (allCommitsByID.containsKey(num)) {
				//		set the head pointer			
				branches.put(currBranch, allCommitsByID.get(num));
				current = allCommitsByID.get(num);
				//		Checkingout all the files in that GitNode
				for (int i=0; i<current.files.size();i++) {
					checkout(current.files.get(i).getPath());
				}
				//		if not exists return error message	
			}else{
				System.out.println("No commit with that id exists.");
			}
		} catch(NumberFormatException e){
			e.printStackTrace();
			System.out.println("Please check that you have enter a valid number for the commitID.");
		}


	}

	/**
	 * Merges given branch with the current branch
	 * Compares version of files in both branches against version at split point
	 * If a file in the current branch has not been modified, overwrite with version of file in given branch.
	 * If a file in the current branch has been modified, but not modified in the given branch, retain the version in current branch
	 * If the given branch has any additional files, automatically stage them
	 * If the given branch has had any files remove, automatically mark them for removal
	 * If a file has been modified in both branches, the files will be marked with .conflicted and a message will be printed out
	 * The message will read "Encountered a merge conflict.".
	 * The gitlet is now in a conflicted state where only certain commands are allowed
	 * These commands are add, rm, commit, checkout [file], checkout [id] [file], log, global-log, find, and status. 
	 * Should any other commands be attempted, "Cannot do this command until the merge conflict has been resolved." will be printed
	 * The user will have to choose between which version of the file to commit.
	 * If there are no conflicts, the merge occurs automatically.
	 * Returns "A branch with that name does not exist." if no branch of the given name exists.
	 * Returns "Cannot merge a branch with itself." if current branch matches given branch.
	 * 
	 * @param branchName - name of branch to be merged to current branch
	 */

	public void merge(String branchName){
		// error message 1
		if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
		} else if (currBranch.equals(branchName)) {
			//error message 2
			System.out.println("Cannot merge a branch with itself.");
		} else {
			//check if the files have been modified
			GitNode Current = branches.get(currBranch);
			GitNode Given = branches.get(branchName);
			GitNode Splitpoint = findSplitpoint(currBranch, branchName);
			//Merging Process
			for (File f: Current.files) {
				File currBranchFile = new File(".gitlet/Commit-" + Current.id + "/" + f);
				File givenBranchFile = new File(".gitlet/Commit-" + Given.id + "/" + f);
				File splitpointFile = new File(".gitlet/Commit-" + Splitpoint.id + "/" + f);
				if (givenBranchFile.exists() && splitpointFile.exists()) {
					// case Current not modified, Given Branch modified, should overwrite;	
					try {
						if (! checkModified(currBranchFile, splitpointFile) && checkModified(givenBranchFile, splitpointFile)) {
							checkout(givenBranchFile);
							add(f.getPath());

							//case conflicted
						} else if (checkModified(currBranchFile, splitpointFile) && checkModified(givenBranchFile, splitpointFile)) {
							this.conflicted = true;
							File FileToAdd = new File(f +".conflicted");
							if (!FileToAdd.exists()) {
								try {
									copyFile(givenBranchFile, FileToAdd);
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			for (File f: Given.files) {
				File currBranchFile = new File(".gitlet/Commit-" + Current.id + "/" + f);
				File givenBranchFile = new File(".gitlet/Commit-" + Given.id + "/" + f);
				File splitpointFile = new File(".gitlet/Commit-" + Splitpoint.id + "/" + f);
				// not tracked by current branch
				boolean Modified = false;
				try {
					Modified = checkModified(givenBranchFile, splitpointFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (! currBranchFile.exists() && Modified) {
					try {
						copyFile(givenBranchFile, f);
					} catch (IOException e) {
						e.printStackTrace();
					}
					add(f.getPath());
				}
			}
			if (this.conflicted == false) {
				commit("Merged " + currBranch + " with " + branchName+ ".");
			}else {
				System.out.println("Encountered a merge conflict.");
			}
		}
	}
	
	/**
	 * Compares the curr File to the file at the splithead and returns whether
	 * the curr is modified as compared to the splithead.
	 * 
	 * @param curr - File that is being compared to the Splithead File
	 * @param Splithead - File at the splitpoint between the two branches in question
	 * @return true if the two files have different content
	 * @throws IOException if File is not found 
	 * @throws FileNotFoundException if File does not exist
	 */
	public static boolean checkModified(File curr, File Splithead) throws IOException, FileNotFoundException{

		String realCurrContent = getFileContent(curr);
		String realSplitContent = getFileContent(Splithead);

		if (realCurrContent.equals(realSplitContent))
			return false;
		return true;
	} 

	/**
	 * Detaches the branch from its split point and attaches it to the head of the current branch
	 * 
	 * Returns "A branch with that name does not exist." if the given branch does not exist.
	 * Returns "Cannot rebase a branch onto itself." if the given branch is the current branch.
	 * Returns "Already up-to-date." if given branch is within current branch's history.
	 * 
	 * @param branchName - name of branch to be rebased.
	 */
	public void rebase(String branchName) {
		// error handling
		if (! branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (branchName.equals(currBranch)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}

		if (rebaseCheckOverlap(currBranch, branchName)) {
			System.out.println("Already up-to-date.");
			return;
		}
		if (rebaseCheckOverlap(branchName, currBranch)) {
			GitNode rebased = branches.get(branchName);
			branches.put(currBranch, rebased);
			return;
		}

		// save given branch's pointer
		GitNode reference = branches.get(branchName);

		GitNode starting = current;
		String restoreBranch = currBranch;
		checkout(branchName);

		// restructuring the commit tree
		rebuild(starting, restoreBranch, reference);

		// repoint given branch pointer
		branches.put(branchName, reference);
		checkout(restoreBranch);

		String lastCommitID = ((Integer) (commitNum - 1)).toString();
		reset(lastCommitID);
	}

	/**
	 * Helper method
	 * Copies GitNodes/commits from given branch to the current branch
	 * 
	 * @param selected - GitNode that is being copied
	 * @param givenBranch - branch that GitNodes are being rebased onto
	 * @param restore - GitNode that givenBranch points to
	 */

	public void rebuild(GitNode selected, String givenBranch, GitNode restore) {
		GitNode splitPoint = findSplitpoint(currBranch, givenBranch);

		if (selected.myPrev != splitPoint) {
			rebuild(selected.myPrev, givenBranch, restore);
			rebaseCommit(selected, splitPoint, restore);

		} else {
			rebaseCommit(selected, splitPoint, restore);
		}
		branchMap.get(currBranch).remove(splitPoint);
		branchMap.get(givenBranch).remove(splitPoint);

	}

	/**
	 * Helper method
	 * Commits files for the replayed commits, with the correct files.
	 * 
	 * @param x - GitNode being copied
	 * @param splitPoint - GitNode of the splitpoint
	 * @param last - GitNode that the givenBranch points to
	 */

	public void rebaseCommit(GitNode x, GitNode splitPoint, GitNode last) {

		ArrayList<File> tempStagingArea = stagingArea;
		stagingArea = new ArrayList<File>();

		for (File f : x.files) {
			stagingArea.add(f);
			File fileAdd = new File(".gitlet/Commit-" + x.id + "/" + f.getPath());
			String parent = f.getParent();
			File stageParent = new File(".gitlet/StagingArea/" + parent + "/");
			if (! stageParent.exists())
				stageParent.mkdir();
			try {
				File target = new File(".gitlet/StagingArea/" + f.getPath());
				copyFile(fileAdd, target);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		commit(x.message);

		checkNewer(x, splitPoint, last);

		for (File f: tempStagingArea) {
			add(f.getPath());
		}
		stagingArea = tempStagingArea;

	}
	
	/**
	 * Compares x and restore to the file at splitPoint. If the file x has not been modified, 
	 * but the file in restore has, replace the file in x with the file in restore.
	 * 
	 * @param x - GitNode that is being compared with the splitPoint
	 * @param splitPoint - GitNode that is the splitpoint of the two branches
	 * @param restore - GitNode that the replayed GitNodes are being rebased onto
	 */

	private void checkNewer(GitNode x, GitNode splitPoint, GitNode restore) {
		GitNode checking = x;
		GitNode split = splitPoint;
		GitNode compare = restore; // what you're trying to add it to
		for (File f:current.files) {
			File realChecking = new File(".gitlet/Commit-" + checking.id + "/" + f.getPath());
			File realSplit = new File(".gitlet/Commit-" + split.id + "/" + f.getPath());
			File realCompare = new File(".gitlet/Commit-" + compare.id + "/" + f.getPath()); 
			File realCurr = new File(".gitlet/Commit-" + this.current.id + "/" + f.getPath()); 

			// compare realChecking file to splitpoint file
			if (! realSplit.exists())
				return;
			if (! realCompare.exists())
				return;
			String realCheckingContent = getFileContent(realChecking);
			String realSplitContent = getFileContent(realSplit);
			String realCompareContent = getFileContent(realCompare);

			// replayed file is same as splitpoint file
			if (realCheckingContent.equals(realSplitContent)) {
				// current branch file is modified, replace replayed files
				if (! realCompareContent.equals(realSplitContent)) {
					try {
						copyFile(realCompare, realCurr);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				// current branch file is also same, no change
			}

		}
	}
	
	/**
	 * Reads and returns contents of file.
	 * 
	 * @param f - file to be read
	 * @return contents of file
	 */

	public static String getFileContent(File f) {
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(f.getPath()));
			return new String(encoded, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("error");
			return "";
		}
	}

	/**
	 * Determines each branch's history and sees is they overlap
	 * 
	 * @param branch1 - name of branch to be checked
	 * @param branch2 - name of branch to be checked if it exists within branch1
	 * @return true if branch1 contains the branch2
	 */
	public boolean rebaseCheckOverlap(String branch1, String branch2) {

		GitNode node1 = branches.get(branch1);
		GitNode node2 = branches.get(branch2);
		String str1 = node1.id.toString();
		String str2 = node2.id.toString(); 
		while (node1.id != 0) {
			str1 += ", " + node1.myPrev.id.toString();
			node1 = node1.myPrev;
		}
		while (node2.id != 0) {
			str2 += ", " + node2.myPrev.id.toString();
			node2 = node2.myPrev;

		}

		return str1.contains(str2);
	}
	
	/**
	 * Determines where 2 branches split off from each other
	 * 
	 * @param current - current working branch
	 * @param given - other branch to be used to find split point
	 * @return GitNode/commit where branches branched off
	 */

	public GitNode findSplitpoint(String current, String given) {
		GitNode c = branches.get(current);
		GitNode g = branches.get(given);
		while (c.myPrev != null) {
			while (g.myPrev != null) {
				if (c.equals(g))
					return c;
				g = g.myPrev;
			}
			g = branches.get(given);
			c = c.myPrev;
		}
		return null;
	}

	/**
	 * Retrieves inputs from command line
	 * Runs the appropriate method depending on what args[0] is
	 *
	 * @param args - user input
	 */
	public static void main(String[] args) {
		Gitlet g = new Gitlet();

		// load in previous .ser file
		// following if statement referenced from http://avajava.com/tutorials/lessons/how-do-i-write-an-object-to-a-file-and-read-it-back.html
		File check = new File(".gitlet/myState.ser");
		if (check.exists()) {
			try {
				FileInputStream inFile = new FileInputStream(".gitlet/myState.ser");
				ObjectInputStream inObject = new ObjectInputStream(inFile);
				g = (Gitlet) inObject.readObject();
				inObject.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		if (g.conflicted) {
			String[] allowed = {"add", "rm", "commit", "checkout", "log", 
					"global-log", "find", "status"};
			if (! Arrays.asList(allowed).contains(args[0]) ||
					(args[0].equals("checkout") && g.branches.containsKey(args[1]))) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
				return;
			}
		}

		if (args.length == 1) {
			switch (args[0]) {
			case "init":
				g.init();
				break;
			case "commit":
				System.out.println("Please enter a commit message.");
				break;
			case "log":
				g.log();
				break;
			case "global-log":
				g.global_log();
				break;
			case "status":
				g.status();
				break;
			case "get-branch":
				g.printCurrentBranch();
				break;
			}

		} else if (args.length == 2) {
			switch (args[0]) {
			case "add":
				g.add(args[1]);
				break;
			case "commit":
				g.commit(args[1]);
				break;
			case "rm":
				g.rm(args[1]);
				break;
			case "find":
				g.find(args[1]);
				break;
			case "checkout":
				g.checkout(args[1]);
				break;
			case "branch":
				g.branch(args[1]);
				break;
			case "rm-branch":
				g.rm_Branch(args[1]);
				break;
			case "reset":
				g.reset(args[1]);
				break;
			case "merge":
				g.merge(args[1]);
				break;
			case "rebase":
				g.rebase(args[1]);
				break;
			case "testBranch":
				g.testBranch(args[1]);
				break;
			case "getFileText":
				g.getFileText(args[1]);
			}
		} else if (args.length == 3) {
			if (args[0].equals("checkout")) {
				g.checkout(args[1], args[2]);
			}
		}

		// load out finished .ser file
		// following code referenced from http://avajava.com/tutorials/lessons/how-do-i-write-an-object-to-a-file-and-read-it-back.html
		try {
			FileOutputStream outFile = new FileOutputStream(".gitlet/myState.ser");
			ObjectOutputStream outObject = new ObjectOutputStream(outFile);
			outObject.writeObject(g);
			outObject.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**********************************************
	 * Getter methods for testing
	 *
	 *********************************************/

	public void printCurrentBranch(){
		System.out.println(this.currBranch);
	}

	public int getNumberOfCommits(){
		return this.commitNum;
	}


	public void testBranch(String branchName) {
		GitNode result = branches.get(branchName);
		System.out.println(result.id);
	}

	public void getFileText(String num) {
		int id = Integer.parseInt(num);
		GitNode result = allCommitsByID.get(id);
		for (File f:result.files) {
			File wanted = new File(".gitlet/Commit-" + id + "/" + f.getPath());
			try {
				byte[] encoded = Files.readAllBytes(Paths.get(wanted.getPath()));
				System.out.println( new String(encoded, StandardCharsets.UTF_8));
			} catch (IOException e) {
				return;
			}
		}
	}

	/*
	 * LinkedList structure to track commit versions
	 * Each node can only point backwards and not forwards
	 */
	private static class GitNode implements Serializable {

		/** GitNode Instance Variables
		 * 
		 * myPrev - Parent GitNode
		 * files - ArrayList of all files contained in the GitNode
		 * id - Commit ID number
		 * message - User's commit message
		 * myTime - Time commit was made
		 */

		GitNode myPrev;
		ArrayList<File> files;
		Integer id;
		String message;
		Date myDate;
		Timestamp myTime;
		
		/**
		 * GitNode a.k.a commit, where all the data is stored for a specific commit.
		 * 
		 * @param parent - the commit's parent
		 * @param id - the commit's ID
		 * @param message - the commit's message
		 * @param getStagingArea - files to be added on top of files in parent commit
		 */
		public GitNode(GitNode parent, int id, String message, ArrayList<File> getStagingArea) {
			myPrev = parent;
			files = getStagingArea;
			this.id = id;
			this.message = message;
			myDate = new Date();
			myTime = new Timestamp(myDate.getTime());
		}
		
		/**
		 * Recursive call thats prints the ID, time and date, message of each GitNode.
		 */
		private void print(){
			if(this.myPrev == null){
				System.out.println("===");
				System.out.println("Commit " + id);
				System.out.println(myTime);
				System.out.println(message);
				System.out.println();
			}
			else{
				System.out.println("===");
				System.out.println("Commit " + id);
				System.out.println(myTime);
				System.out.println(message);
				System.out.println();
				myPrev.print();
			}
		}
		
		/**
		 * Prints the ID, time and date, message of each GitNode.
		 */
		private void printMeOnly() {
			System.out.println("===");
			System.out.println("Commit " + id);
			System.out.println(myTime);
			System.out.println(message);
			System.out.println();
		}
		
		/**
		 * Get the file that matches the given file name from within the GitNode.
		 * Returns null if no such file exists.
		 * 
		 * @param fileName - name of file to be retrieved.
		 * @return returns file if found, null if not found.
		 */
		private File getFile(String fileName){
			for (File f:this.files){
				if(f.getName().equals(fileName))
					return f;
			}
			return null;
		}
	}
}
