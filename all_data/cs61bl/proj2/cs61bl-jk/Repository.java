import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;

public class Repository implements Serializable {
	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 164118045713364668L;
	
	private Commit head; //HEAD pointer points to a commit
	private int commitId; //GLOBAL commit ID, +1 when new commit is created
	private int fileId; //GLOBAL file object ID, +1 when new file object is created
	private boolean conflicted; //FLAG for merge conflicted state
	
	private ArrayList<Commit> commits; //LIST of commits from the repository
	private HashMap<String, Integer> branches; //MAP of "branch name" to commit ID
	
	// PATH constants
	public final static Path BASE_PATH = FileSystems.getDefault().getPath(".gitlet");
	public final static Path STAGING_PATH = BASE_PATH.resolve("StagingArea");
	public final static Path OBJECT_PATH = BASE_PATH.resolve("AllFiles");
	
	private ArrayList<String> stagingAreaNames; //LIST of file names in staging area
	private ArrayList<String> untrackedFiles; //LIST of files marked for untrack
	
	private FileTree current; //the current file tree (Concept of real git's index file)
	private String currentbranch = "master"; // the name of current branch. exist ONLY for handy use
	
	
	/**
	 * Constructor: Create new repository. 
	 * Does do anything yet, should run init first
	 */
	public Repository() {
		//Initialize instance variables
		commitId = 0; //The initial commit should have commit id 0
		fileId = 1; //The initial file id should have id 1
		conflicted = false; //Initially the repository is not in conflict
		
		// Initialize all the vaiables
		commits = new ArrayList<Commit>();
		branches = new HashMap<String, Integer>();
		stagingAreaNames = new ArrayList<String>();
		untrackedFiles = new ArrayList<String>();
	}
	
	/** git <init>
	 *  Create .gitlet/ and all necessary folders, make initial commit. 
	 * 
	 * @throws IOException
	 */
	public void init() throws IOException {
		if (Files.exists(BASE_PATH)) { //Already a .gitlet folder there, do nothing. 
			System.out.println("A gitlet version control system already exists in the current directory.");
			return;
		}
		
		//Create structures
		//.gitlet/
		//   |---StagingArea/
		//   |---AllFiles/
		Files.createDirectory(BASE_PATH);
		Files.createDirectory(STAGING_PATH);
		Files.createDirectory(OBJECT_PATH);
		
		head = new Commit(); //make Initial Commit
		commits.add(head); //Keep track of the commit
		commitId++; //This ALWAYS follows call to commits.add()
		branches.put("master", head.getID()); //Create master branch
		current = new FileTree(); // Index tree
	}
	/**
	 * Adds the file into the Staging Area and the FileTree
	 * @param file
	 * 		a string of the filename
	 * @throws IOException
	 */
	public void add(String file) throws IOException {
		Path addedPath = FileSystems.getDefault().getPath(file); //The path of the file to add
		
		if (!Files.exists(addedPath)) { //File does not exist, reject
			System.out.println("File does not exist.");
			return;
		}
		
		//Create directory structure in staging area if needed
		if (addedPath.getParent() != null) {
			Files.createDirectories(STAGING_PATH.resolve(addedPath.getParent()));
		}
		
		// may need to revert untrack
		if (untrackedFiles.contains(file)) { //Already Marked for untrack
			untrackedFiles.remove(file);
		} else { //add the file
			Files.copy(addedPath, STAGING_PATH.resolve(addedPath), 
					StandardCopyOption.REPLACE_EXISTING); //Copy to staging area
			stagingAreaNames.add(file); //add to list
			current.add(addedPath, fileId); //add to file tree (auto replace)
			fileId++; //global file id +
		}
	}
	
	/**Puts the files in the Staging Area into AllFiles, sets HeadPointer to the new Commit, and clears the staging area.
	 * 
	 * @param message
	 * 		a string of the commit message that the user enters
	 * @throws IOException
	 */
	public void commit(String message) throws IOException {

		if (message == "") { // message empty, reject
			System.out.println("Please enter a commit message");
			return;
		}
		
		if (stagingAreaNames.isEmpty()) { // no file to commit, reject
			System.out.println("No changes added to the commit.");
			return;
		}
		
		// the new commit object
		Commit temp = new Commit(message, commitId, head.getID(), current.Copy());
		commitId++; //global commit id +
		head = temp; //change head pointer
		
		commits.add(head); //track the commit
		branches.put(currentbranch, temp.getID()); //update branch
		
		for (String i : stagingAreaNames) {
			// Staging -> Object Store
			Files.copy(STAGING_PATH.resolve(i), current.getRealPath(i)); //to object store
		}
		clearDirectory(STAGING_PATH); // delete files
		Files.createDirectory(STAGING_PATH); //recreate staging area
		stagingAreaNames = new ArrayList<String>(); //clear staging list
		conflicted = false; //clear conflict
	}

	// recursively delete the directory
	// code adapted from Adam Bien's Weblog
	// After deletion, the directory is missing, may need to recreate
	private void clearDirectory(Path p) throws IOException {
		if (Files.notExists(p)) {
			return;
		}
		if (!Files.isDirectory(p)) {
			Files.delete(p);
		} else {
			Files.walkFileTree(p, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file,
						BasicFileAttributes attrs) throws IOException {
					if (Files.isDirectory(file)) {
						clearDirectory(file);
					}
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir,
						IOException exc) throws IOException {
					Files.delete(dir);
					return FileVisitResult.CONTINUE;
				}
			});
		}
	}
	
	/**
	 * If the staging area has the file, it is deleted from the staging area. Also resets the FileTree to contain
	 * the file from the previous Commit; otherwise, the file is added to UntrackedFiles.
	 * @param FileName
	 * 		a string of the file name
	 * @throws IOException
	 */
	public void rm(String FileName) throws IOException {
		if (stagingAreaNames.contains(FileName)) { //if staged, revert
			Files.delete(STAGING_PATH.resolve(FileName)); //delete file from staging area
			if (head.contains(FileName)) { //Retrieve version from previous commit
				current.add(FileName, head.getFileTree().getID(FileName));
			} else {
				current.remove(FileName);
			}
			stagingAreaNames.remove(FileName); //remove in staging list
			fileId--; //decrement file id...
		} else if (head.getFileTree().getID(FileName) >= 0) { //tracked, mark for untrack
			untrackedFiles.add(FileName); //mark for untrack
			current.remove(FileName); //remove from current file tree
		} else { //not staged and not tracked
			System.out.println("No reason to remove the file.");
		}
	}

	/**
	 * Using the loghelper method, this method prints the Commit history from the current branch.
	 */
	public void log() {
		if (head != null) {
			loghelper(head);
		}
	}

	/**
	 * Recursively prints out Commit history.
	 * @param node
	 * 		the Commit node
	 */
	private void loghelper(Commit node) {
		System.out.println("===");
		System.out.println("Commit " + node.getID());
		System.out.println(node.getDate());
		System.out.println(node.getMessage());
		System.out.println();
		if (node.getID() != 0) {
			loghelper(commits.get(node.previousCommit()));
		}
	}

	/**
	 * prints out the entire Commit history
	 */
	public void globalLog() {
		for (Commit i : commits) {
			System.out.println("===");
			System.out.println("Commit " + i.getID());
			System.out.println(i.getDate());
			System.out.println(i.getMessage());
			System.out.println();
		}
	}

	/**
	 * Finds the Commit Id based on the commit message entered.
	 * @param message
	 * 		the commit message
	 */
	public void find(String message) {
		int count = 0;
		for (Commit i : commits) {
			if (i.getMessage().equals(message)) {
				System.out.println(i.getID());
				count++;
			}
		}
		if (count == 0) {
			System.out.println("Found no commit with that message.");
		}
	}

	/**
	 * Prints out the branches based on mapOfBranches and stars the current branch.
	 * Also prints out the Staged Files from an ArrayList and prints the Files Marked for Untracking based on an Arraylist.
	 * 
	 */
	public void status() {
		System.out.println("=== Branches ===");
		for (String i : branches.keySet()) { //iterate through keys(branch names)
			System.out.println(i.equals("currentbranch") ? "*" : "" + i); // *active branch
		}
		System.out.println(); //new line
		System.out.println("=== Staged Files ===");
		for (String i : stagingAreaNames) { //iterate through staging files list
			System.out.println(i);
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (String i : untrackedFiles) { //iterate through untracked list
			System.out.println(i);
		}
	}
	/**
	 * Adds the branchName as the key into the HashMap mapOfBranches with its value being the CommitId of the HeadPointer.
	 * @param branchName
	 * 		name of the branch entered
	 */
	public void branch(String branchName) {
		if (branches.containsKey(branchName)) {
			System.out.println("A branch of this name already exists.");
			return;
		}
		branches.put(branchName, head.getID()); //create branch
	}

	/**
	 * If the given String is a branch name, sets the headpointer to that branch,
	 * copies all files from the head Commit of that branch into the working area
	 * 
	 * If the String is a filename 
	 * copies file from the current commit into the working area.
	 * @param Name
	 * 		A String of the branch name or file name
	 * @throws IOException
	 */
	public void checkout(String Name) throws IOException {
		if (branches.containsKey(Name)) { //checkout a branch
			head = commits.get(branches.get(Name));  //change head to that branch's commit
			currentbranch = Name;
			current = head.getFileTree().Copy(); //change current filetree to that branch's tree
			checkout(head.getID()); //copy everything to working directory
		} else if (head.contains(Name)) { //checkout a file from HEAD
			Files.copy(head.getRealFile(Name), FileSystems.getDefault()
					.getPath(Name), StandardCopyOption.REPLACE_EXISTING);
		} else { //
			System.out.println("File does not exist in the most recent commit, or no such branch exists.");
		}
	}
	/**copies the given file from the given commit (found using the ID), into the working area.
	 * 
	 * @param ID
	 * A commit ID
	 * @param FileName
	 * Which file to extract
	 * @throws IOException
	 */
	public void checkout(int ID, String FileName) throws IOException {
		Commit temp = commits.get(ID);
		if (temp == null) { //cannot find the commit
			System.out.println("No commit with that id exists");
			return;
		}
		if (temp.contains(FileName)) {
			Files.copy(temp.getRealFile(FileName), FileSystems.getDefault()
				.getPath(FileName), StandardCopyOption.REPLACE_EXISTING);
		} else { //cannot find the file
			System.out.println("File does not exist in that commit.");
		}

	}
	/**Checks out all files from a commit found using the commit ID
	 * 
	 * @param ID
	 * commit ID
	 * @throws IOException
	 */
	public void checkout(int ID) throws IOException {
		Commit temp = commits.get(ID);
		for (Path x : temp.getAllFiles()) {
			Files.copy(temp.getRealFile(x.toString()), x,
					StandardCopyOption.REPLACE_EXISTING);
		}
	}
	/**if the branch is not the current branch, remove it from the hashmap mapOfBranches.
	 * 
	 * @param name: branch name
	 */
	public void rmBranch(String name) {
		if (name.equals(currentbranch)) { //it's the current branch
			System.out.println("Cannot remove the current branch.");
			return;
		}
		if (!branches.containsKey(name)) { //no such branch
			System.out.println("A branch with that name does not exist.");
			return;
		}
		branches.remove(name);
	}
	/**checks out a commit, then sets the current branch to that commit
	 * 
	 * @param ID A commit ID
	 * @throws IOException
	 */
	public void reset(int ID) throws IOException {
		head = commits.get(ID); //head pointer
		branches.put(currentbranch, ID); //change branch ref
		checkout(ID); //copy files
	}
	
	
	/**Errors if branch doesn't exist or if the current and given branch are the same
	 * otherwise,
	 * first finds the split point between the current and given branch, then checksout the currentbranch
	 * then, for every file in the given branch
	 * if it has been modified in the given branch but not the current branch, it checks out and stages that file
	 * if it has been modified in both branches, it checksout that file with a new file name("old file name")
	 *  and put the system into a conflicted state
	 *  
	 *  if by the end of merge, there are no conflicted files, it automatically commits.
	 * 
	 * @param branchname: some branch
	 * @throws IOException
	 */
	public void merge(String branchname) throws IOException {
		
		if (!branches.containsKey(branchname)) { //No branch exist for merging, reject
			System.out.println("A branch with that name does not exist.");
			return;
		}
		
		if (branchname.equals(currentbranch)) { //branch is current branch, reject
			System.out.println("Cannot merge a branch with itself.");
			return;
		}
		
		Commit splitpoint = splitpoint(branchname); //find split point
		
		checkout(currentbranch); //reset the state of workspace
		for (String i : commits.get(branches.get(branchname)).getFileTree().returnallfiles()) {
			if (commits.get(branches.get(branchname)).getFileTree().getID(i) != splitpoint.getFileTree().getID(i)
					&& head.getFileTree().getID(i) == splitpoint.getFileTree().getID(i)) {
				checkout(branches.get(branchname), i);
				add(i);
			} else if (commits.get(branches.get(branchname)).getFileTree().getID(i) != splitpoint.getFileTree().getID(i)
					&& head.getFileTree().getID(i) != splitpoint.getFileTree().getID(i)) {
				conflicted = true;
				String temp = i+".conflicted";
				Files.copy(commits.get(branches.get(branchname)).getRealFile(i), FileSystems.getDefault().getPath(".", temp));
			}
			
		}
		
		if (!conflicted) {
			commit("Merged " + currentbranch + " with " + branchname + ".");
		} else {
			System.out.println("Encountered a merge conflict.");
		}
	}
/**first finds the commit history for one branch, then finds the most recent shared commit node with the current branch
 * 
 * @param branchname: a String with the name of the branch that you want to find the split point with(relative to the current branch)
 * @return
 */
	private Commit splitpoint(String branchname) {
		ArrayList<Integer> templist = new ArrayList<Integer>();
		Commit givenBranch = commits.get(branches.get(branchname));
		Commit currentBranch = head;
		while (givenBranch.getID() > 0) { //add all the history to templist
			templist.add(givenBranch.getID());
			givenBranch = commits.get(givenBranch.previousCommit());
		}
		
		while (currentBranch.getID() >= 0) { //check whether there is common commit
			if (templist.contains(currentBranch.getID())) {
				return commits.get(currentBranch.getID());
			}
			currentBranch = commits.get(currentBranch.previousCommit());
		}
		return commits.get(0);

	}
/**
 * checks to see if the system is currently in a conflicted state
 * @return
 */
	public boolean conflicted() {
		return conflicted;
	}
	
	
	/**
	 * finds the commit history from the given branch
	 * @param branch:  A string of the given branch name
	 * @return
	 */
	public ArrayList<Integer> history(Commit branch) {
		Commit temp2 = branch;
		ArrayList<Integer> temp = new ArrayList<Integer>();
		while (temp2.getID() >= 0) {
			temp.add(temp2.getID());
			temp2 = commits.get(temp2.previousCommit());
		}
		return temp;
	}
	
	
	/**if branch doesn't exist, print error message
	 * if the two branches point to the same thing, print error message
	 * 
	 * if the current is in the history of the given, moves the headPointer to the given.
	 * if the given is in the history of the current, print message
	 * 
	 * otherwise iteratively attaches current branch to given branch,
	 * replacing all unmodified files with the version in the given branch.
	 * 
	 * @param branchname: A string of branch name that you want to attach too.
	 * @throws IOException
	 */
	public void rebase(String branchname) throws IOException{
		
		if (!branches.containsKey(branchname)) { //no branch exist, reject
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (branchname.equals(currentbranch)) { //rebase to current branch, reject
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}
		
		Commit splitpoint = splitpoint(branchname);
		
		if (head == splitpoint) { //head itself is split point
			head = commits.get(branches.get(branchname)); //checkout to that branch
			currentbranch = branchname;
			return;
		}
		
		if (commits.get(branches.get(branchname)) == splitpoint) { //that branch is split point
			System.out.println("Already up-to-date."); //checkout this branch(do nothing)
			return;
		}
		
		
		Commit old_branch = commits.get(branches.get(currentbranch));  //the current branch
		Commit temp1 = new Commit(old_branch.getMessage(), commitId, -1, 
				old_branch.getFileTree().Copy()); //create a copy of the current branch [parent = -1]
		
		for(String i: temp1.getFileTree().returnallfiles()){ //retrieve current branch's files
			if(temp1.getFileTree().getID(i) == splitpoint.getFileTree().getID(i)){
				// the file is the same in current branch and split point
				if(commits.get(branches.get(branchname)).getFileTree().getID(i) >= 0 && //that file also exist in given branch
						commits.get(branches.get(branchname)).getFileTree().getID(i) != splitpoint.getFileTree().getID(i)) {
						//and it's not the same file (meaning from split point -> given branch, the file updated): 
						//we adapt the version in given branch (newer version)
					temp1.getFileTree().add(i, commits.get(branches.get(branchname)).getFileTree().getID(i));
				} else {
					// the file is untracked in the given branch, we remove
					temp1.getFileTree().remove(i);
				}
			}
		}
		
		Commit temp3;
		int idToReset = temp1.getID();
		
		commits.add(temp1);//commit modified given branch
		commitId++; //commitid ++
		
		// [old_branch]'s father is not split point
		// meaning travel from the current branch to the commit after split point
		while (old_branch.previousCommit() != splitpoint.getID()) {
			// create new tempory commit from [old_branch]
			temp3 = new Commit(old_branch.getMessage(), commitId, -1, old_branch.getFileTree().Copy());
			for(String i: temp3.getFileTree().returnallfiles()){ //same thing here
				if(temp3.getFileTree().getID(i)==splitpoint.getFileTree().getID(i)){
					if(commits.get(branches.get(branchname)).getFileTree().getID(i)>=0 &&
							commits.get(branches.get(branchname)).getFileTree().getID(i)
							!= splitpoint.getFileTree().getID(i)){
						temp3.getFileTree().add(i, commits.get(branches.get(branchname)).getFileTree().getID(i));
					} else {
						temp3.getFileTree().remove(i);
					}
				}
			}
			commits.add(temp3); //commit
			commitId++;
			temp1.setprev(temp3.getID()); //...
			old_branch = commits.get(old_branch.previousCommit());
			temp1 = temp3;
		} //end of while
		temp1.setprev(branches.get(branchname));
		reset(idToReset); //move the branch pointer
	}

}
