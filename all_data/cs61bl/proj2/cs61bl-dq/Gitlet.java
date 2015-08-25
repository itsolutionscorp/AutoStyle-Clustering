

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;

public class Gitlet implements Serializable{
	// Instance Variable
	static CommitTree myTree;
	static File gitlet;
	
    /**
	 * Creates a new instance of Gitlet, a version control system.
	 */
	public Gitlet() {
		gitlet = new File(".gitlet");
	}
  
	/**
	 * Parses input into function calls to control Gitlet.
     * @param args
     *        input that determines what function will be called.
	 */
	public static void main (String[] args) {
		Gitlet g = new Gitlet();
		g.findTree();
		if (args.length == 0) {
			System.out.print("Need an argument");
			System.exit(1);
		}
		switch (args[0]) {
			case "init": 
				if (myTree.isConflicted) {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
					break;
				} else {
					g.init();
					break;
				}
			case "add":
				try {
					g.add(args[1]);
				} catch (IOException e){
					e.printStackTrace();
				}
				break;
			case "commit": 
				try {
					g.commit(args[1]);
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Please enter a commit message");
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "rm":
				try {
					g.rm(args[1]);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "log": g.log();
				break;
			case "global-log": g.globalLog();
				break;
			case "find": g.find(args[1]);
				break;
			case "status": g.status();
				break;
			case "checkout": 
				try {
					if (args.length == 3) {
						g.checkout(Integer.parseInt(args[1]), args[2]);
					}
					if (args.length == 2) {
						if (myTree.pointerTable.containsKey(args[1]) && myTree.isConflicted) {
							System.out.println("Cannot do this command until the merge conflict has been resolved.");
							break;
						} else {
							g.checkout(args[1]);
							break;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "branch": 
				if (myTree.isConflicted) {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
					break;
				} else {
					g.branch(args[1]);
					break;
				}
			case "rm-branch": 
				if (myTree.isConflicted) {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
					break;
				} else {
					g.rmbranch(args[1]);
					break;
				}
			case "reset": 
				if (myTree.isConflicted) {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
					break;
				} else {
					g.reset(args[1]);
					break;
				}
			case "merge":
				if (myTree.isConflicted) {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
					break;
				} else {
					try {
						g.merge(args[1]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}
			case "rebase": 
				if (myTree.isConflicted) {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
					break;
				} else {
					try {
						g.rebase(args[1]);
					} catch (IOException e){
						System.out.println("Failed to rebase. Errored : " + e.getMessage());
					}
					break;
				}
		}
		serialWrite(g.myTree);
	}
	/**
	 * Initializes Gitlet and creates a new .gitlet folder.
	 */
	public void init() {
		File gitDir = new File(".gitlet");
		// if the directory does not exist, create it
		if (!gitDir.exists()) {
			gitDir.mkdir();
			String initialCommitTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
			// call to commit function which would make the commit folder
			myTree.addFirst(initialCommitTime, "initial commit");
			myTree.headName = "master";
			myTree.pointerTable.put("master", myTree.myHead);
			File firstCommit = new File(".gitlet/commit#0");
			firstCommit.mkdir();
			myTree.commitIDTable.put(0, myTree.myHead);
			myTree.stagingArea = new File(".gitlet/staging-area");
			if (!myTree.stagingArea.exists()) {
				myTree.stagingArea.mkdirs();
			}
		} else {
			System.out.println("A gitlet version control system already exists in the current directory.");
		}
	}
  
	/**
	 * Takes the input file name and either sets the file for tracking, or stages it.
     * @param fileName
     *        a String of the name of the file that will be tracked or staged.
     * @throws IOException
     *        when add fails to execute.
	 */
	public void add(String fileName) throws IOException {
		File fileToAdd = new File(fileName);
		if (!fileToAdd.exists()) {
			System.out.println("File does not exist.");
			System.exit(1);
		}
		else if (myTree.myHead.untrackedItems != null && myTree.myHead.untrackedItems.contains(fileName)) {
			myTree.myHead.untrackedItems.remove(fileName);
		}
		else {
			if (myTree.stagingArea.exists()) {
				Path destination = Paths.get(".gitlet/staging-area/" + fileName);
				File placeholder = new File(".gitlet/staging-area/" + fileName);
				if (!placeholder.exists()) {
					Files.createDirectories(destination);
				}
				Files.copy(fileToAdd.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
			}
		} 
	}
	
	/**
	 * Creates a new CommitNode with the updated files that were in the staging-area
	 * This post helped us: 
     * http://stackoverflow.com/questions/5282177/how-to-move-a-folderincluding-subdirectories-and-files-into-a-new-folder-using
     * @param message
     *        A string of the message that will correspond with the newest commit.
     * @throws IOException
     *       when commit fails to execute.
	*/
	public void commit(String message) throws IOException {
		boolean doAll = true;
		if (myTree.stagingArea.list().length == 0 && myTree.myHead.untrackedItems.equals(myTree.myHead.myPrev.untrackedItems)) {
			System.out.println("No changes added to the commit.");
		} else {//new files were added
			if (myTree.stagingArea.list().length == 0 && !myTree.myHead.untrackedItems.equals(myTree.myHead.myPrev.untrackedItems)) {//tracking different files
				doAll = false;
			}
			myTree.mySize += 1;
			String myCommitTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
			CommitTree.CommitNode mine = myTree.makeNode((myTree.mySize), myCommitTime, message, myTree.myHead);
			myTree.myHead = mine;
			File newcommit = new File(".gitlet/commit#" + (mine.myCommitID));
			newcommit.mkdirs();
			if (doAll) {
				CommitHelper(myTree.stagingArea, newcommit);
				purgeDirectory(myTree.stagingArea);
				myTree.stagingArea = new File(".gitlet/staging-area");
				if (!myTree.stagingArea.exists()) {
					myTree.stagingArea.mkdirs();
				}
			}
			if (myTree.commitMessageTable.containsKey(myTree.myHead.myCommitMessage)) {
				myTree.commitMessageTable.get(myTree.myHead.myCommitMessage).add(myTree.myHead);
			} else {
				ArrayList<CommitTree.CommitNode> x = new ArrayList<CommitTree.CommitNode>();
				x.add(myTree.myHead);
				myTree.commitMessageTable.put(myTree.myHead.myCommitMessage, x);
			}
			myTree.commitIDTable.put(mine.myCommitID, mine);
			myTree.pointerTable.put(myTree.headName,mine);
		}
	}
	
	/**
	 * Helper method that helps with moving the unstaged files from the old path into
     * the new directory for the latest commit.
     * @param currentStage
     *        the current directory that contains the current files
     * @param commitStage
     *        the new commit directory that the current files will be moved to.
     * @throws IOException
     *        when this commitHelper fails to execute.
     * 
	 */
	public void CommitHelper(File currentStage, File commitStage) throws IOException {
		for (File x : currentStage.listFiles()) {
			if (x.isDirectory()) {
				File newfolder = new File(commitStage.getPath() + "/" + x.getName());
				newfolder.mkdirs();
				CommitHelper(x, newfolder);
			} else {
				File temp = new File(commitStage.getPath() + "/" + x.getName());
				Files.copy(x.toPath(), temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
		} 
	}
  
  	 /**
	 * Recursive helper function that deletes directories.
     * @param dir
     *        the File directory that will be deleted.
	 */
	private void purgeDirectory(File dir) {
	    for (File file: dir.listFiles()) {
	        if (file.isDirectory()) purgeDirectory(file);
	        file.delete();
	    }
	}
	
	/**
	 * Either removes a file from the staging list, or marks for untracking.
     * @param fileName
     *        a String of the name of the file that will be unstaged or untracked.
     * @throws IOException
     *        when remove fails to execute.
	 */
	public void rm(String fileName) throws IOException{
		File fileToRemove = new File(".gitlet/staging-area/" + fileName);
		if (!fileToRemove.exists() && myTree.myHead.untrackedItems.contains(fileName)) {
			System.out.println("No reason to remove the file.");
		}
		else if (fileToRemove.exists()) {
				Files.delete(Paths.get(".gitlet/staging-area/" + fileName));
		} else {
			myTree.myHead.untrackedItems.add(fileName);
		}
	}

	/**
	 * Prints out the information of current head commit and its commit history.
	 */
	public void log() {
        CommitTree.CommitNode sentnode = myTree.myHead;
        while (sentnode != null) {
            System.out.println("===");
            System.out.println("Commit " + sentnode.myCommitID);
            System.out.println(sentnode.myTimeCommitted);
            System.out.println(sentnode.myCommitMessage);
            sentnode = sentnode.myPrev;
            if (sentnode != null) {
                System.out.println();
            }
        }
    }

	/**
	 * Prints out the information of all the commits made in the current Gitlet instansiation.
	 */
	public void globalLog() {
		boolean firstline = true;
		for (CommitTree.CommitNode everynode : myTree.commitIDTable.values()) {
				if (firstline) {
					firstline = false;
				} else {
					System.out.println();
				}
			System.out.println("===");
			System.out.println("Commit " + everynode.myCommitID);
			System.out.println(everynode.myTimeCommitted);
			System.out.println(everynode.myCommitMessage); 	        
		}
	}

	/**
	 * Prints out the CommitID of all the CommitNodes corresponding to the input commit message.
     * @param commitMessage
     *        a String of the message of the commit that the user is searching for.
	 */
	public void find(String commitMessage) {
		if (myTree.commitMessageTable.containsKey(commitMessage)) {
			for (String message : myTree.commitMessageTable.keySet()) {
				if (message == commitMessage) {
					for (int i = 0; i < myTree.commitMessageTable.get(message).size(); i++) {
						System.out.println(myTree.commitMessageTable.get(message).get(i).myCommitID);
					}
				}
			}
		}
		
		else {
			System.out.println("Found no commit with that message.");
		}
	}
	
	/**
     * Creates a new branch with the given name, and points it at the current head node.
     * @param branchName
     *        a String of the name of the new branch being created.
     */
	public void branch(String branchName) {
		if (myTree.pointerTable.containsKey("master")) {
			if (myTree.pointerTable.containsKey(branchName)) {
				System.out.println("A branch with that name already exists.");
			}
			else {
				myTree.pointerTable.put(branchName, myTree.myHead);
			}
		}
	}
	
	/**
     * Deletes the branch with the given name
     * @param branchName
     *        a String of the name of the new branch being deleted.
     */
	public void rmbranch(String branchName) {
		if (!myTree.pointerTable.containsKey(branchName)) {
			System.out.println("A branch with that name does not exit.");
		}
		else if (branchName == myTree.headName) {
			System.out.println("Cannot remove the current branch.");
		}
		else {
			myTree.pointerTable.remove(branchName);
		}
	}
	
	/**
     * Displays what branches currently exist, and marks the current branch with a (*) 
     * and displays what files have been staged or marked for untracking.
     */
	public void status() {
		System.out.println("=== Branches ===");
		for (String s : myTree.pointerTable.keySet()) {
			if (s.equals(myTree.headName)) {
				System.out.print("*");
			}
			System.out.println(s);
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		File stagedFiles = new File(".gitlet/staging-area");
		File[] stagedFilesArr = stagedFiles.listFiles();
		for (File f : stagedFilesArr) {
			System.out.println(f.getName());
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (String s : myTree.myHead.untrackedItems) {
			System.out.println(s);
		}
	}
	

	/**
     * Takes the version of the file as it exists in the head commit, the front of the current branch, 
     * and puts it in the working directory, overwriting the version of the file that's already there 
     * if there is one, or takes all files in the commit at the head of the given branch, and puts 
     * them in the working directory, ovewriting the versions of the files that are already there if they exist.
     * @param fileOrBranchName
     *        a String of the name of the file or branch that is being used to overwrite files.
     */
	public void checkout(String fileOrBranchName) throws IOException {
		if (fileOrBranchName.equals(myTree.headName)) {//is current branch
			System.out.println("No need to checkout the current branch.");
			return;
		}
		if (myTree.pointerTable.containsKey(fileOrBranchName)) {//is branch
			myTree.headName = fileOrBranchName;
			myTree.myHead = myTree.pointerTable.get(fileOrBranchName);
			File filesToCopy = new File(".gitlet/commit#" + myTree.pointerTable.get(fileOrBranchName).myCommitID);
			for (File x : filesToCopy.listFiles()) {
				File source = new File(filesToCopy.getAbsolutePath() + "/" + x);
				if (!x.exists()) {
					Files.createDirectories(x.toPath());
				}
				Files.copy(source.toPath(), x.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
		} else {//Either a file or not a branch name
			if (myTree.myHead.myPrev.untrackedItems != null && myTree.myHead.myPrev.untrackedItems.contains(fileOrBranchName)) {
				return;
			}
			CommitTree.CommitNode currentCommit = myTree.myHead;
			while (currentCommit != null) {
				File temp = new File(".gitlet/commit#" + currentCommit.myCommitID + "/" + fileOrBranchName);
				if (temp.exists()) {
					File working = new File(fileOrBranchName);
					if (!working.exists()) {
						Files.createDirectories(working.toPath());
					}
					Files.copy(temp.toPath(), working.toPath(), StandardCopyOption.REPLACE_EXISTING);
					return;
				}
				currentCommit = currentCommit.myPrev;
			}
			System.out.println("File does not exists in current commit or Branch does not exist.");	
		}
	}

	/**
     * Takes the version of the file as it exists in the commit with the given id, 
     * and puts it in the working directory, overwriting the version of the file 
     * that's already there if there is one.
     * @param commitID
     *        an int of the commitID of the CommitNode wanted for overwriting the current version.
     * @param fileName
     *        a String of the name of the file needed to check out.
     */
	public void checkout(int commitID, String fileName) throws IOException {
		File commitFolder = new File(".gitlet/commit#" + commitID);
		if (!commitFolder.exists()) {
			System.out.println("No commit with that id exists.");
		} else {
			CommitTree.CommitNode currentCommit = myTree.commitIDTable.get(commitID);
			if (currentCommit.myPrev.untrackedItems != null && currentCommit.myPrev.untrackedItems.contains(fileName)) {
				return;
			}
			while (currentCommit != null) {
				File temp = new File(".gitlet/commit#" + currentCommit.myCommitID + "/" + fileName);
				if (temp.exists()) {
					File working = new File(fileName);
					if (!working.exists()) {
						Files.createDirectories(working.toPath());
					}
					Files.copy(temp.toPath(), working.toPath(), StandardCopyOption.REPLACE_EXISTING);
					return;
				}
				currentCommit = currentCommit.myPrev;
			}
			System.out.println("File does not exist in that commit.");
		}
	}
	
	/**
     * Checks out all the files tracked by the given commit. 
     * Also moves the current branch's head to that commit node.
     * @param commitID
     *        an String of the commitID of the CommitNode being resetted to.
     */
	public void reset(String commitID) {
		if (!myTree.commitIDTable.containsKey(Integer.parseInt(commitID))) {
			System.out.println("No commit with that id exists.");
		}
		// checks out all files, moves current branch's head to that commit node
		for(int i = 1 ; i <= Integer.parseInt(commitID) ; i++){
			File folder = new File (".gitlet/commit#"+ i +"/");
			File[] listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
				if (file.isFile()) {
				    if(! myTree.commitIDTable.get(i).untrackedItems.contains(file.getName())){
					    try{
					    checkout(i,file.getName());
					    } catch (IOException e) {
							e.printStackTrace();
						}
			    	}
			    }
			}
		}		
		myTree.myHead = myTree.commitIDTable.get(Integer.parseInt(commitID));
	}
	
	
    /**
     * Checks out two File objects contain the same content.
     * Citation: http://stackoverflow.com/questions/27379059/determine-if-two-files-store-the-same-content
     * @param file1
     *        a File object to be compared to.
     * @param file2
     *        the second File object that is compared with the first File Object.
     * @return true if they contain the same content.
     * @throws IOException
     *         if it fails to compare.
     */
   	private boolean sameContent(Path file1, Path file2) throws IOException {
	    final long size = Files.size(file1);
	    if (size != Files.size(file2)) {
	        return false;
	    }
	    else {
	    	return Arrays.equals(Files.readAllBytes(file1), Files.readAllBytes(file2));
	    }
	}
	
	/**
     * Finds the splitpoint Node between the current branch and the input String of another branch.
     * @param branchName
     *        a String of the branch name for searching it's split node with the current branch.
     * @return the split point (of type CommitNode) between two branches.
     */
   	private CommitTree.CommitNode findSplitPoint(CommitTree.CommitNode givenBranchNode, CommitTree.CommitNode currentBranchNode) {
   		CommitTree.CommitNode splitPoint = null;
   		while (givenBranchNode != null && currentBranchNode != null) {
   			if (givenBranchNode.getID() > currentBranchNode.getID()) {
   				givenBranchNode = givenBranchNode.getPrev();
   			}
   			else if (currentBranchNode.getID() > givenBranchNode.getID()) {
   				currentBranchNode = currentBranchNode.getPrev();
   			}
   			else if (givenBranchNode.getID() == currentBranchNode.getID()) {
   				break;
   			}
   		}
   		splitPoint = givenBranchNode;
   		return splitPoint;
   	}
	
    /**
     * Merges files from the given branch into the current branch.
     * citation: http://stackoverflow.com/questions/27379059/determine-if-two-files-store-the-same-content
     * @param branchName
     *        a String of the branch name that is being merged with the current branch.
     * @throws IOException
     *        if it fails to merge.
     */
   	public void merge(String branchName) throws IOException {
   		if (branchName.equals(myTree.headName)) {
   			System.out.println("Cannot merge a branch with itself.");
   			return;
   		}
   		if (!myTree.pointerTable.containsKey(branchName)) {
   			System.out.println("A branch with that name does not exist.");
   			return;
   		}
   		CommitTree.CommitNode splitNode = findSplitPoint(myTree.pointerTable.get(branchName), myTree.myHead);
   		System.out.println(splitNode.myCommitID);
   		File splitPointFiles = new File(".gitlet/commit#" + splitNode.myCommitID);
   		String currentNodeFiles = ".gitlet/commit#" + myTree.myHead.myCommitID;
   		String branchNodeFiles = ".gitlet/commit#" + myTree.pointerTable.get(branchName).myCommitID;
   		File[] splitPointFilesArr = splitPointFiles.listFiles();
   		
   		boolean currentBranchModified = false;
   		boolean givenBranchModified = false;
   		
   		boolean untrackedFile = false;
   		for (File splitPointFile : splitPointFilesArr) {
   			File currentFile = new File(currentNodeFiles, splitPointFile.getName());
   			if (currentFile.exists()) {
   				if (!sameContent(splitPointFile.toPath(), currentFile.toPath())) {
   					currentBranchModified = true;
   				}
   			}
   			System.out.println(currentBranchModified);
   			File branchFile = new File(branchNodeFiles, splitPointFile.getName());
   			if (branchFile.exists()) {
   				if (!sameContent(splitPointFile.toPath(), branchFile.toPath())) {
   					givenBranchModified = true;
   				}
   			}
   			System.out.println(givenBranchModified);
   			if (givenBranchModified == true && currentBranchModified == false) {
   				checkout(myTree.pointerTable.get(branchName).myCommitID, branchFile.getName());
   				add(branchFile.getName());
   			}
   			if (givenBranchModified == true && currentBranchModified == true) {
   				if (!sameContent(currentFile.toPath(), branchFile.toPath())) {
   					File copyFromFile = new File(branchNodeFiles + "/" + branchFile.getName());
   					File conflictedFile = new File(branchFile.getName());
   					myTree.isConflicted = true;
   					Files.copy(copyFromFile.toPath(), conflictedFile.toPath());
   					if (conflictedFile.exists()) {
   						Files.move(conflictedFile.toPath(), new File(branchFile.getName() + ".conflicted").toPath());
   					}
   				}
   			}
   			if (givenBranchModified == false && currentBranchModified == false) {
   				if (currentFile.exists() && !branchFile.exists()) {
   	   				rm(currentFile.getName());
   	   				untrackedFile = true;
   				}
   			}
   		}
   		
   		System.out.println("this should be true: " + untrackedFile);
   		
   		File[] currentNodeFilesArr = new File(currentNodeFiles).listFiles();
   		for (File currentFile : currentNodeFilesArr) {
   			File branchFile = new File(branchNodeFiles, currentFile.getName());
   	   		File splitFile = new File(".gitlet/commit#" + splitNode.myCommitID, currentFile.getName());
   			if (branchFile.exists() && !splitFile.exists()) {
   				if (!sameContent(currentFile.toPath(), branchFile.toPath())) {
   					File copyFromFile = new File(branchNodeFiles + "/" + branchFile.getName());
   					File conflictedFile = new File(branchFile.getName());
   					myTree.isConflicted = true;
   					Files.copy(copyFromFile.toPath(), conflictedFile.toPath());
   					if (conflictedFile.exists()) {
   						Files.move(conflictedFile.toPath(), new File(branchFile.getName() + ".conflicted").toPath());
   					}
   				}
   			}
   		}
   		
   		File[] givenNodeFilesArr = new File(branchNodeFiles).listFiles();
   		if (givenBranchModified == false && currentBranchModified == false && untrackedFile == false) {
   			for (File branchFile : givenNodeFilesArr) {
   				File currentFile = new File(currentNodeFiles, branchFile.getName());
   				if (!currentFile.exists()) {
   					checkout(myTree.pointerTable.get(branchName).myCommitID, branchFile.getName());
   	   				add(branchFile.getName());
   				}
   			}
   		}
   		
   		System.out.println(currentBranchModified);
   		
   		System.out.println(givenBranchModified);
   		
   		System.out.println(myTree.isConflicted);
   		
		if (myTree.isConflicted == false && (!(currentBranchModified == true && givenBranchModified == false))) {
			commit("Merged " + myTree.headName + " with " + branchName + ".");
		}
		if (myTree.isConflicted == true) {
			System.out.println("Encountered a merge conflict.");
		}
	}

	/*
	 * If a CommitTree already exists, returns it. 
	 * Else, reads the current CommitTree
	 */
	public void findTree() {
		if (!gitlet.exists()) {
			File TreePath = new File("CommitTree.ser");
			if (TreePath.exists()) {
				try {
					Files.delete(TreePath.toPath());
				} catch (IOException e) {
					System.out.println("Trouble with the CommitTree");	
				}
			}
		}
		if (!treeExists()) {
			myTree = new CommitTree();
		} else {
			myTree = serialRead();
		}
	}

	/**
	 * Determines if a CommitTree exists
     * @return true if the tree exists.
	 */
	private static boolean treeExists() {
		File saved = new File("CommitTree.ser");
		return saved.exists();
	}

	/**
     * Finds the split point of the current branch and the given branch, then snaps off the 
     * current branch at this point, then reattaches the current branch to the head of the given branch.
     * @param givenBranchName
     *        a String of the name of the given branch that the current branch will reattach itself to
     * @throws IOException
     *        if method fails to rebase.
     */
	public void rebase(String givenBranchName) throws IOException{
		if (! myTree.pointerTable.containsKey(givenBranchName)){
			System.out.println("A branch with that name does not exist.");
			System.exit(1);
		}
		if (givenBranchName.equals(myTree.headName)){
			System.out.println("Cannot rebase a branch onto itself.");
			System.exit(1);
		}
		CommitTree.CommitNode givenBranch = myTree.pointerTable.get(givenBranchName);
		CommitTree.CommitNode currentBranch;
		CommitTree.CommitNode splitNode;
		String currentBranchName = myTree.headName;
		int numCommitsInBranch = 0;
		boolean toBreak = false;
		int level = 0;		
		while (givenBranch.myPrev != null){ //find the split point 
			currentBranch = myTree.myHead;
			if (givenBranch.equals(myTree.myHead)){ //special case #1
				myTree.myHead = myTree.pointerTable.get(givenBranchName);
				return;
			}
			numCommitsInBranch = 0;
			while (currentBranch.myPrev != null){
				if(currentBranch.myPrev.equals(givenBranch) && level == 0){ //
					System.out.println("Already up-to-date.");
					System.exit(1);
				}
				if(currentBranch.myPrev.equals(givenBranch.myPrev)){
					splitNode = currentBranch.myPrev;
					toBreak = true;
					break;
				}
				
				currentBranch = currentBranch.myPrev;
				numCommitsInBranch ++;
			}
			if(toBreak){
				break;
			}
			level++;
			givenBranch = givenBranch.myPrev;
		}
		myTree.myHead = copyFiles(numCommitsInBranch,givenBranchName);
		myTree.headName = currentBranchName ;
	}
  	
  	/**
  	 * Copy Helper Method.
     * Copies all the files in given branch to the current branch and returns new head.
     * @param numFiles
     *       an int of the number of files to be copied
     * @param branchName 
     *       a String of the name of the branch files are being copied from
     * @return The new head is returned after copying all of the files in the given branch to the current branch.
     * @throws IOException
     *       when the method fails to copy files.
  	 */
  	public CommitTree.CommitNode copyFiles(int numFiles ,String branchName) throws IOException {
  		String myCommitTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
  		CommitTree.CommitNode tempHead ;
  		CommitTree.CommitNode newHead = myTree.pointerTable.get(branchName);
  		while (numFiles >= 0){ // gets to file that should be committed
			tempHead = myTree.myHead;
			for (int i = 0; i< numFiles ; i++){ //
				tempHead = tempHead.myPrev; 
			}
			newHead = myTree.makeNode(newHead.myCommitID +1, myCommitTime, tempHead.myCommitMessage , newHead);
			File filesToCopy = new File(".gitlet/commit#" + tempHead.myCommitID) ;
			for (File x : filesToCopy.listFiles()) { // copy all the files in the node being copied
				File newcommit = new File(".gitlet/commit#" + (newHead.myCommitID) + "/" + x.getName());
				newcommit.mkdirs();
				Files.copy(x.toPath(), newcommit.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
			myTree.commitIDTable.put(newHead.myCommitID, newHead);
			myTree.mySize++;	
			numFiles --;
		}
  		
  		return newHead;
  	}


	/**
	 * Data structure for Gitlet. A tree that contains of all of the CommitNodes.
	 */
	public class CommitTree implements Serializable {

		// instance variables
		private CommitNode myHead;
		private int mySize;
		int numCommitted;
		HashMap<String, ArrayList<CommitNode>> commitMessageTable;
		HashMap<String, CommitNode> pointerTable;
		HashMap<Integer, CommitNode> commitIDTable;
		HashMap<String, File> fileTable = new HashMap<String, File>();
		
		boolean isConflicted;
		boolean newBranch;
		File stagingArea;
		String headName;

		/**
		 * Constructor
		 */
		public CommitTree() {
			myHead = null;
			mySize = 0;
			pointerTable = new HashMap<String, CommitNode>();
			commitMessageTable = new HashMap<String, ArrayList<CommitNode>>();
			commitIDTable = new HashMap<Integer, CommitNode>();
			newBranch = false;
			isConflicted = false;
		}
		
		/**
		 * Makes a node.
         * @param ID
         *      an int of the commit's ID.
         * @param timeCommitted
         *      a String of when the commit was created.
         * @param commitMessage
         *      a String of the commit's message.
         * @param myPrev
         *      a CommitNode of the current commit's predecessor.
         * @return the newly made CommitNode.
		 */
		public CommitNode makeNode(int ID, String timeCommitted, String commitMessage, CommitNode myPrev) {
			return new CommitNode(ID, timeCommitted, commitMessage, myPrev);
		}

		/** 
		 * Adds a method to the staging area.
         * @param time
         *   a String of when the commit node was created.
         * @param message
         *   a String of the commit's message.
		 */
		public void addFirst(String time, String message) {
			CommitNode toAdd = new CommitNode(0, time, message, myHead);
			myHead = toAdd;
		}

		/**
         * Getter method for the CommitTree's size.
         * @return an int of the size of the CommitTree.
         */
		public int size() {
			return mySize;
		}

		/**
		 * The Nodes within the CommitTree data structure.
		 */
		public class CommitNode implements Serializable{
			// instance variables
			private int myCommitID;
			private String myTimeCommitted;
			private String myCommitMessage;
			private CommitNode myPrev;
			private HashMap<String, String> fileTable;
			ArrayList<String> untrackedItems;

			/**
			 * Constructor for the CommitNode.
             * @param ID
             *      an int of the CommitNode's ID.
             * @param timeCommitted
             *      a String of the time when the node was committed.
             * @param commitMessage
             *      a String of the CommitNode's message.
             * @param prev
             *      a CommitNode of this newly created commit's predecessor.
			 */
			public CommitNode(int ID, String timeCommitted, String commitMessage, CommitNode prev) {
				myCommitID = ID;
				myTimeCommitted = timeCommitted;
				myCommitMessage = commitMessage;
				myPrev = prev;
				fileTable = new HashMap<String, String>();
				untrackedItems = new ArrayList<String>();
				if( prev != null){
					for (String name : prev.untrackedItems){
						untrackedItems.add(name);
					}
				}
				
			}

			/**
             * Getter method to attain a CommitNode's ID.
             * @return the CommitNode's ID.
             */
			public int getID() {
				return myCommitID;
			}
			
			/**
             * Getter method to attain a CommitNode's previous commit.
             * @return the CommitNode's previous commit.
             */
			public CommitNode getPrev() {
				return myPrev;
			}

			/**
             * Getter method to attain a CommitNode's message.
             * @return the CommitNode's message.
             */
			public String getMessage() {
				return myCommitMessage;
			}

			/**
             * Getter method to attain a CommitNode's time stamp.
             * @return the CommitNode's time of when it was committed.
             */
			public String getTimeStamp() {
				return myTimeCommitted;
			}
		}
	}

	/**
     * Reads a serializable file.
     */
	private static CommitTree serialRead() {
		CommitTree temp = null;
		try {
			ObjectInput input = new ObjectInputStream (
                    new FileInputStream(
                    "CommitTree.ser"));
			temp = (CommitTree) input.readObject();
		} catch (IOException e) {
			System.err.printf("Error: %s\n", e.toString());
		} catch (ClassNotFoundException e2) {
			System.err.printf("Error: %s\n", e2.toString());
		}
		return temp;
	}

	/**
     * Writes a Serializable file.
     */
	public static void serialWrite(CommitTree needsSaving) {
		try {
			ObjectOutput output = new ObjectOutputStream(new FileOutputStream("CommitTree.ser"));
			output.writeObject(needsSaving);
		} catch (IOException e) {
			System.err.printf("Error: %s\n", e.toString());
		}
	}
}