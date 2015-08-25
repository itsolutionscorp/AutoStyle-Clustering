import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class Gitlet implements Serializable {

	private File myStage;                                                               // keeps track of all staged files
	private File commits_folder;                                                        // contains all our commits
	private Commit myHead;                                                              // points to current commit
	private HashMap<String, Branch> myBranches;                                         // map that associates branch names to branches
	private HashMap<Integer, Commit> myCommits;                                         // map that associates commit hashcodes to commits
	private int commits;                                                              // counter of all commits
    private HashMap<String, LinkedList<Integer>> commitsByMessage;
    private Branch currentBranch;
    private String work_dir;
    private String currDir;
    private String stageDir;
    private String commitsDir;
    private HashMap<String, File> myStages; // ADDED for STATUS TODO probably need to make this a hashmap as well
    private boolean conflicted; //TODO

    
    
    ///////////////////////////////////////////////////////////////////////////////////
    /////////////////////////// GET METHODS ///////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////
	
	/**
	 *  Get method that returns the myStage file.
	 */
	public File getMyStage(){
	    return this.myStage;
	}
	
	/**
	 *  Get method that returns the commits_folder file.
	 */
	public File getCommits_folder(){
	    return this.commits_folder;
	}
	
	/**
	 *  Get method that returns the current commit being pointed to.
	 */
	public Commit getMyHead(){
	    return this.myHead;
	}
	
	/**
	 *  Get method that returns a branch from the myBranches map.
	 *  @param key 
	 *      the name of a branch
	 */
	public Branch getBranch(String key){
	    return this.myBranches.get(key);
	}
	
	 /**
     * Get method that returns currentBranch
     */
    public Branch getCurrentBranch(){
    	return this.currentBranch;
    }
    
    /**
	 * Returns the hashmap that represents all branches of this Gitlet
	 */
	 public HashMap<String, Branch> getMyBranches(){
		 return myBranches;
	 }
	 
	 /**
	 *  Returns the hashmap for mycommitsbyID
	 *  
	 */
	public HashMap<Integer, Commit> returnMyCommits(){
	    return myCommits;
	}
	
	/**
	 *  Get method that returns a commit from the myCommits map.
	 *  @param key
	 *      the hashcode of a specific commit 
	 */
	public Commit getMyCommits(Integer key){
	    return this.myCommits.get(key);
	}
	
	/**
	 * Get method that returns the hashmap commitsByMessage
	 */
	 public HashMap<String, LinkedList<Integer>> getCommitsByMessage() {
	     return this.commitsByMessage;
	 }
	 
	 /**
	  * Get method that returns the ArrayList myStages.
	  */
	 public HashMap<String, File> getMyStages() {//ADDED for STATUS
	     return this.myStages;
	 }
	 
	 /**
	  * Get method that returns the string of the Stage directory
	  */
	 public String getstageDir() {
		 return stageDir;
	 }
	 
	 /**
	  * Get method that returns the string of the Commits directory
	  */
	 public String getcommitsDir() {
		 return commitsDir;
	 }
	 
	 
	 /**
	  * Get method that returns the string of the Commits directory
	  */
	 public String getcurrDir() {
		 return currDir;
	 }
	 
	 public boolean isConflicted() {
	 	return conflicted;
	 }
	 
	////////////////////////////////////////////////////////////////////
	
	/**
	 * Saves the Gitlet instance in a file called gitlet.ser
	 * @param work_dir
	 * 		  User's working directory/where the .gitlet folder is
	 * @param g
	 * 		  Instance of Gitlet, either new or from gitlet.ser
	 */
	public static void serialize(String work_dir, Gitlet g) {
		try {
			FileOutputStream fileOut = new FileOutputStream(work_dir + "/.gitlet/gitlet.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(g);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			return;
		}
	}
	
	/**
	 * Reads and imports Gitlet instance from a file called gitlet.ser
	 * @param work_dir
	 * 		  User's working directory/where the .gitlet folder is
	 * @return
	 * 		  Should return the Gitlet object from gitlet.ser or
	 * 		  returns a new Gitlet object
	 */
	public static Gitlet deserialize(String work_dir) {
	    try {
	    	FileInputStream fileIn = new FileInputStream(work_dir + "/.gitlet/gitlet.ser");
		    ObjectInputStream in = new ObjectInputStream(fileIn);
		    return (Gitlet) in.readObject();	
	    } catch (IOException | ClassNotFoundException e) {
	    	return new Gitlet();
	    }
	}

	/**
	 * Calls a Gitlet method based on user command
	 * @param g
	 * 		  Instance of Gitlet
	 * @param command
	 *        User's command
	 * @param args
	 * 		  User's argument(s) for command
	 */
	public static void do_command(Gitlet g, String command, String[] args) {
		List<String> conflicted_commands = Arrays.asList("branch", "rm-branch", "reset",
		"merge", "rebase");
		if (g.isConflicted() && conflicted_commands.contains(command)) {
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		if (command.equals("init")) {
			g.init();
		} else if (!new File(System.getProperty("user.dir") + "/.gitlet").exists()) { //Must run init first.
			return;
		} else if (command.equals("add")) {
			g.add(args[0]);
		} else if (command.equals("commit")) {
			g.commit(args[0]);
		} else if (command.equals("rm")) {
			g.rm(args[0]);
		} else if (command.equals("log")) {
			g.log();
		} else if (command.equals("global-log")) {
			g.globalLog();
		} else if (command.equals("find")) {
			g.find(args[0]);
		} else if (command.equals("status")) {
			g.status();
		} else if (command.equals("checkout")) {
			if (args.length == 2) {
				int id = Integer.parseInt(args[0]);
				g.checkout(id, args[1]);
			} else {
				g.checkout(args[0]);
			}
		} else if (command.equals("branch")) {
			g.branch(args[0]);
		} else if (command.equals("rm-branch")) {
			g.rm_branch(args[0]);
		} else if (command.equals("reset")) {
			int id = Integer.parseInt(args[0]);
			g.reset(id);
		} else if (command.equals("merge")) {
			g.merge(args[0]);	
		} else if (command.equals("rebase")) {
			g.rebase(args[0]);
		} else {
		    System.out.println("No command with that name exists.");
		    return;
		}
	}
	
	/**
	 * Set up the .gitlet folder, create the initial commit with commit message
	 * “initial commit”, set the head to this commit, create “master” branch
	 * with myRoot set to null and myLeaf set to initial commit
	 * 
	 **/
	public void init() {
		currDir = System.getProperty("user.dir") + "/";
		work_dir = new File(currDir).getName();
		stageDir = currDir + ".gitlet" + "/stage/";
		commitsDir = currDir + ".gitlet" + "/commits/";
		File gitlet = new File(currDir + ".gitlet");
		if (gitlet.exists()) { //Don't override if gitlet already exists
	        System.out.println("A gitlet version control system "
	        		+ "already exists in the current directory.");
	        return;
		}
		myStage = new File(stageDir);
		commits_folder = new File (commitsDir);
		gitlet.mkdir(); // makes .gitlet directory
		myStage.mkdir(); // makes staging directory
		commits_folder.mkdir(); //makes commits directory
        myBranches = new HashMap<String, Branch>();
		myStages = new HashMap<String, File>(); 
        myCommits = new HashMap<Integer, Commit>();
		currentBranch = new Branch("master", null, this); //makes master branch, root set to null, only branch with null root
		commitsByMessage = new HashMap<String, LinkedList<Integer>>();
        myHead = new Commit(myHead, 0, "initial commit", this);
        commits = 1;
        conflicted = false;
	}
	 
	/**
	 * Adds copy of the file to the staging area.
	 * If file was marked for untracking then it simply untracks the file.
	 * @param String to_add
	 * 			file that will be staged or marked for untracking
	 */
	public void add (String to_add) {
		File a = new File(to_add); //gets file that user inputed
	    if (!a.exists() || a.isDirectory()) { //Checks if file actually exists
	        System.out.println("File does not exist.");
	        return;
	    }
		if (myHead.getUntracked().containsKey(to_add)) {
			myHead.getMyTracked().put(to_add, myHead.getUntracked().remove(to_add));
		} else {
			String sub_folder = "";
			if (to_add.length() > a.getName().length()) {
				sub_folder = to_add.substring(0, to_add.length() - a.getName().length() - 1);
			}
			File stage = new File(stageDir + to_add); //Creates file instance to copy
			FileTools.copyFile(a, stage, stageDir + sub_folder);
			myStages.put(to_add,a); //ADDED
		}
	}
	
	/**
	 * Makes a commit 
	 * 
	 * @param message
	 *          input message by user for this specific commit 
	 */
	public void commit (String message) {
		if (message == null) {
			System.out.println("Please enter a commit message.");
			return;
		} else if (myStage.list().length == 0 && myHead.getUntracked().isEmpty())  { //Check for marked for untracking files
			System.out.println("No changes added to the commit.");
			return;
		} else {
            myHead = new Commit(myHead, commits, message, this);
            conflicted = false;
			commits++;
		}
	}
	
	/**
	 * Marks the file for untracking or if staged, unstages file.
	 * @param file
	 * 		  File to be untracked/unstaged
	 */
	public void rm (String file) { 
		File in_stage = new File(myStage.getAbsolutePath()+ "/" + file);
	    if (in_stage.exists()) {
	    	in_stage.delete();
	    	myStages.remove(file);
	    	return;
	    } else if (myHead.getMyTracked().containsKey(file)) { 
	    	myHead.getUntracked().put(file, myHead.getMyTracked().get(file));
	    } else {
	    	System.out.println("No reason to remove the file.");
	    }
	}
		
	/**
	 * Starting at the current head commit,
	 * print information about each commit backwards along 
	 * the commit tree until the initial commit. 
	 * 
	 */
	public void log(){
		myHead.print();
	    Commit currentCommit = myHead.getmyParent();
	    while(currentCommit != null){
	        System.out.println();
	        currentCommit.print();
	        currentCommit=currentCommit.getmyParent();
	    }
	}
	
	/**
	 * Like log, except displays information about all commits ever made.
	 * 
	 */
	public void globalLog(){
		Set<Integer> myCommitsSet = myCommits.keySet();
        Iterator<Integer> iter = myCommitsSet.iterator();
        getMyCommits(iter.next()).print();
        while (iter.hasNext()){
        	System.out.println();
        	getMyCommits(iter.next()).print();
        }	
	}
	
	/**
	 * Displays what branches currently exist, and marks the current branch with a *. 
	 * Also displays what files have been staged or marked for untracking.
	 *
	 */
	public void status() {
		String stat="=== Branches ===\n";
		Set<String> branchNames = myBranches.keySet();
		Iterator<String> iter = branchNames.iterator();
		String br;
		while (iter.hasNext()){
			br = iter.next();
			if (currentBranch.getName().equals(br)){
				stat+="*";
			}
			stat+= br+"\n";
		}
		stat+="\n=== Staged Files ===\n";
		
		Set<String> stageNames = myStages.keySet();
		Iterator<String> iter1 = stageNames.iterator();
		String st;
		while (iter1.hasNext()){
			 st = iter1.next();
			 stat+= st +"\n";
		}	
		
		stat+="\n=== Files Marked for Untracking ===\n";
		
		Set<String> myCommitsSet = myHead.getUntracked().keySet(); 
        Iterator<String> iter2 = myCommitsSet.iterator();
        while (iter2.hasNext()){
        	stat+=iter2.next()+"\n";
        }
        
		System.out.print(stat);
	}
	
	/**
	 * Checkout method for when a branch or file name is inputed. If the name is valid (exists)
	 * then checkout will call a helper method to copy all the file(s).
	 * @param name
	 * 		  Name of the branch or file to checkout.
	 */
	public void checkout(String name) {
		if (myBranches.containsKey(name)) {
			if (conflicted) {
				System.out.println("Cannot do this command until the merge" + 
			    " conflict has been resolved.");
				return;
			}
			if (currentBranch.getName().equals(name)) {
				System.out.println("No need to checkout the current branch.");
				return;
			}
			Branch the_branch = getBranch(name);
			HashMap<String, File> tracked  = the_branch.getLeaf().getMyTracked();
			Set<String> trackedset = tracked.keySet();
			Iterator<String> iter = trackedset.iterator();
			while (iter.hasNext()) {
				String file = iter.next();
				File source = tracked.get(file);
				File destination = new File(currDir + file);
				FileTools.copyFile(source, destination, file);
			}
			currentBranch = the_branch;
			myHead = currentBranch.getLeaf();
			return;
		}
		if (myHead.getMyTracked().containsKey(name)) {
			File source = myHead.getMyTracked().get(name);
			File destination = new File(currDir + name);
			FileTools.copyFile(source, destination, name);
			return;
		} else {
			System.out.println("File does not exist in the most recent commit, or no such branch exists.");
			return;
		}
	}
	
	/**
	 * Checkout method for when a commit id and file name is inputed. If the name is valid (exists)
	 * then checkout will call a helper method to copy all the file.
	 * @param id
	 *        ID of the commit
	 * @param file_name
	 *        Name of the file to checkout
	 */
	public void checkout(int id, String file_name) {
		File commit_folder = new File(commits_folder.getAbsolutePath() + "/" + id);
		if (!commit_folder.exists()) {
			System.out.println("No commit with that id exists.");
			return;
		}
		Commit commit = myCommits.get(id);
		if (!commit.getMyTracked().containsKey(file_name)) {
			System.out.println("File does not exist in that commit.");
			return;
		} else {
			File source = commit.getMyTracked().get(file_name);
			File destination = new File(currDir + file_name);
			FileTools.copyFile(source, destination, file_name);
		}	
	}

	/**
	 * Deletes the given branch if it exists.
	 * @param br
	 */
	public void rm_branch(String branch){
		if (currentBranch.getName().equals(branch)){
			System.out.println("Cannot remove the current branch.");
		} else if (myBranches.containsKey(branch)){
			myBranches.remove(branch);
		} else {
			System.out.println("A branch with that name does not exist.");
		}
	}
	
	/**
	 * Creates new branch that points at the current head node.
	 * @param name
	 * 		  Branch name
	 */
	public void branch(String name){
		if (this.getMyBranches().containsKey(name)){
			System.out.println("A branch with that name already exists.");
			return;
		} else {
			new Branch(name, myHead, this);
		}
	}
	
	/**
	 * Reset method to checkout all the files tracked by the given commit ID.
	 * Moves current branch's head node to that commit.
	 * @param id
	 * 		  The ID of the commit to checkout.
	 */
	public void reset(int id) {
		File commit_id = new File(commitsDir + id);
		if (!commit_id.exists()) {
			System.out.println("No commit with that id exists.");
			return;
		} else {
			HashMap<String, File> tracked = myCommits.get(id).getMyTracked();
			Set<String> trackedset = tracked.keySet();
			Iterator<String> iter = trackedset.iterator();
			while (iter.hasNext()) {
				checkout(id, iter.next());
			}
			currentBranch.setLeaf(myCommits.get(id));
			myHead = myCommits.get(id);
		}
	}
	
	/**
	 * Prints out all commit id's associated with argument message
	 *
	 * @param   message
	 * 				Potential message associated with a commit 
	 */
	public void find(String message) {
		if (commitsByMessage.containsKey(message)) {
			LinkedList<Integer> list = commitsByMessage.get(message);
			Iterator<Integer> iter = list.iterator();
			while(iter.hasNext()) {
				System.out.println(iter.next());
			}
		} else {
			System.out.println("Found no commit with that message.");
		}
	}
	
	public void merge(String branch) {
		if (!myBranches.containsKey(branch)) {
			System.out.println("A branch with that name does not exist.");
		} else if (currentBranch.getName().equals(branch)) {
			System.out.println("Cannot merge a branch with itself.");
		} else {
			Commit currentleaf = currentBranch.getLeaf();
			Branch br = myBranches.get(branch);
			Commit givenleaf = br.getLeaf();
			Commit split = splitPoint(currentleaf, givenleaf);			
			mergeFiles(currentleaf, givenleaf, split, br);
		}
	}
	
	private void mergeFiles(Commit curr, Commit given, Commit split, Branch branch){
		HashMap<String, File> splitHash = split.getMyTracked();
		HashMap<String, File> currHash = curr.getMyTracked();
		HashMap<String, File> givenHash = given.getMyTracked();
		Set<String> givenSet = givenHash.keySet();
		Iterator<String> givenIter = givenSet.iterator();
		while (givenIter.hasNext()) {
			String file = givenIter.next();
			if (splitHash.containsKey(file) && currHash.containsKey(file)) { //All three have the file
				File currFile = currHash.get(file);
				File givenFile = givenHash.get(file);
				File splitFile = splitHash.get(file);
				//Compare both to split, then to each other
				if (FileTools.compareFiles(currFile, splitFile) && !FileTools.compareFiles(givenFile, splitFile)) {
					//Current hasn't changed, but given has
					//Replace current with given
					Commit branch_commit = branch.getLeaf();
					checkout(branch_commit.getMyID(), file);
					add(file);
				} else if (!FileTools.compareFiles(currFile, splitFile) && !FileTools.compareFiles(givenFile, splitFile)) {
					//Both have changed --> CONFLICTED
					conflicted = true;
					File destination = new File(file + ".conflicted");
					FileTools.copyFile(givenFile, destination, file);
				}
			} else if (!splitHash.containsKey(file) && currHash.containsKey(file)) { //Split doesnt have the file
				File currFile = currHash.get(file);
				File givenFile = givenHash.get(file);
				if (!FileTools.compareFiles(givenFile, currFile)) {
					//Not the same file
					conflicted = true;
					File destination = new File(file + ".conflicted");
					FileTools.copyFile(givenFile, destination, file);
				}
			} else if (splitHash.containsKey(file) && !currHash.containsKey(file)) { //Current doesnt have the file
				File givenFile = givenHash.get(file);
				File splitFile = splitHash.get(file);
				if (!FileTools.compareFiles(givenFile, splitFile)) {
					//Modified in given branch
					Commit branch_commit = branch.getLeaf();
					checkout(branch_commit.getMyID(), file);
					add(file);
				}
			} else if (!splitHash.containsKey(file) && !currHash.containsKey(file)) { //Both dont have the file
				Commit branch_commit = branch.getLeaf();
				checkout(branch_commit.getMyID(), file);
				add(file);
			}
			//Untracked in given case
		}
		if (conflicted) {
			System.out.println("Encountered a merge conflict.");
			return;
		} else {
			String message = "Merged " + currentBranch.getName() + " with " + branch.getName() + ".";
			commit(message);
		}	
	}
	
	/**
	 * Find the split point of two commits, order of input should not matter
	 * returns the commit at the split point
	 */ 
	private static Commit splitPoint(Commit a, Commit b) {
		int currentdepth = 0;
		int givendepth = 0;
		Commit curr = a;
		Commit given = b;
		Commit curr2 = a;
		Commit given2 = b;
		while (curr.getmyParent()!=null){
			currentdepth++;
			curr = curr.getmyParent();
		}
		while (given.getmyParent()!=null){
			givendepth++;
			given = given.getmyParent();
		}
		while (givendepth>currentdepth){
			given2 = given2.getmyParent();
			givendepth--;
		}
		while (currentdepth>givendepth){
			curr2 = curr2.getmyParent();
			currentdepth--;
		}
		while ( curr2.getMyID() != given2.getMyID()){
				given2 = given2.getmyParent();
				curr2 = curr2.getmyParent();
			}
		return curr2;
	}
	
public void rebase(String branch) {
		if (!myBranches.containsKey(branch)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (currentBranch.getName().equals(branch)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		} else {
			Commit curr = currentBranch.getLeaf();
			Commit given = myBranches.get(branch).getLeaf();
			Commit split = splitPoint(given, curr);
			if (split.getMyID() == given.getMyID() || split.getMyID() == curr.getMyID()){
				System.out.println("Already up-to-date.");
				return;
			}
			Commit curr1 = curr;
			Commit curr2 = null;
			Commit prev = null;
			while(curr1.getMyID() != split.getMyID()){
				curr2 = new Commit(curr1, split, given, commits, this);
				commits++;
				if (prev != null){
					prev.setmyParent(curr2);
				} else {
					//currentBranch = myBranches.get(branch);
					currentBranch.setLeaf(curr2);
					myHead = curr2;
				}
				prev = curr2;
				curr1 = curr1.getmyParent();
			}
			curr2.setmyParent(myBranches.get(branch).getLeaf());
		}
	}
	
	/**
	 * MAIN METHOD!!!
	 * 
	 * @param args
     *          list of String arguments that determines Gitlet command - will be parsed below and in Gitlet methods
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
		    System.out.println("Please enter a command.");
		    return;
		} 
		String work_dir = System.getProperty("user.dir");
		Gitlet g = deserialize(work_dir); //Reads gitlet.ser if it exists
		String command = args[0];
		String[] arguments;
		if (args.length > 1) { //Argument(s) for command
			arguments = Arrays.copyOfRange(args, 1, args.length);
		} else {
			arguments = new String[1]; //No arguments given, index 0 should be null
		}
		do_command(g, command, arguments);
		if (new File(work_dir + "/.gitlet").exists()) {
			serialize(work_dir, g); //Writes gitlet.ser	
		}
	}
 }


