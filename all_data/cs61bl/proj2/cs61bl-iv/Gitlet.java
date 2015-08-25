/*Gitlet.java*/

import java.text.SimpleDateFormat;
import java.util.*;
import java.nio.file.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * Represents a tree of the commits that the user
 * has created and runs the program.
 * @author
 */

public class Gitlet implements Serializable {
	
	/**
	 * Private instance variables associated
	 * with Gitlet.
	 */
	private HashMap<String, MyCommit> hashByBranch;
	private HashMap<String,ArrayList<Integer>> hashByMsg;
	private HashMap<Integer,MyCommit> hashByID;
	private File stage; // staging area after adding files
	private HashSet<String> stagedfiles;
	private HashSet<String> untracked;
	private MyCommit commitTree;
	private String currentBranch;
	private boolean conflicted;
	private File workDir; // to manipulate files in working directory
	private File gitlet; // our .gitlet folder
	private int counter; //counter for CommitID
	
	/**
	 * Initializes all variables & creates .gitlet & stage folders
	 * 
	 */
	public void init() {
		gitlet = new File(".gitlet/");
		stage = new File(".gitlet/stage");
		if (gitlet.exists()) {
			System.out.println("A gitlet version control system already exists in the current directory.");
			System.exit(1);
		}
		else {
			gitlet.mkdir();
		}
		
		workDir = new File(System.getProperty("user.dir"));
		stage.mkdir();
		counter++;
		commitTree = new MyCommit(null,"initial commit", null, counter);
		hashByBranch = new HashMap<String,MyCommit>();
		hashByMsg = new HashMap<String,ArrayList<Integer>>();
		hashByID = new HashMap<Integer,MyCommit>();
		stagedfiles = new HashSet<String>();
		untracked = new HashSet<String>();
		currentBranch = "master";
		conflicted = false;
		
		updateHashes(commitTree);
	}
	
	/**
	 * Associated command: java Gitlet add [file name]
	 * 
	 * @param fileName
	 * 		the path to the file in String
	 * 
	 */
	public void add(String fileName) {
		File myFile = new File(fileName);
		// if file does not exist, error.
		if (!myFile.exists()) {
			System.out.println("File does not exist.");
			System.exit(1);
		}
		//else if file is not untracked, copy it to the stage folder
		else if (!untracked.contains(fileName)) {
			Path stagePath = Paths.get(".gitlet/stage/" + fileName);
			Path filePath = Paths.get(fileName);
			File parent = new File(".gitlet/stage/" + fileName);
			parent.getParentFile().mkdirs();
			try {
				Files.copy(filePath, stagePath, StandardCopyOption.REPLACE_EXISTING);
				stagedfiles.add(fileName);
			} catch (IOException e) {
				// this should never happen
				e.printStackTrace();
			}
		}
		//if file is untracked, mark it for tracking "removing the untrack-status"
		else {
			untracked.remove(fileName);
		}
	}
	
	/**
	 * Associated command: java Gitlet commit [message]
	 *
	 * @param message
	 * 		Commit message attached to the commit
	 */
	public void commit(String message) {
		//if there has been no change since last commit, error
		if (stagedfiles.isEmpty() && untracked.isEmpty()) {
			System.out.println("No changes added to the commit.");
			System.exit(1);
		}
		// else if there is no message given, error
		else if ((message == null) || (message.length() == 0)) {
			System.out.println("Please enter a commit message.");
			System.exit(1);
		}
		else {
			ArrayList<String> filesToCommit = new ArrayList<String>();
			
			//add all file from staging area to an ArrayList
			for (String p: stagedfiles) {
				filesToCommit.add(p);
			}
			
			MyCommit prev = hashByBranch.get(currentBranch);
			
			//increased counter for CommitID purposes
			counter++;
			
			//create new commit with files from stage
			MyCommit newCommit = new MyCommit(filesToCommit, message, prev, counter);
			if (!untracked.isEmpty()){
				for (String p: untracked){
					newCommit.Untrack(p);
				}
			}
			//add the nwe commit to all hashmaps
			updateHashes(newCommit);
			
			//reset staging area && untracked
			stagedfiles = new HashSet<String>();
			untracked = new HashSet<String>();
			ClearDir(stage);
		}
		
	}
	
	
	/**
	 * Associated command: java Gitlet rm [file name]
	 * 
	 * @param fileName
	 * 		the path to the file in String
	 */
	public void rm(String fileName) {
		HashMap<String, String> trackedByPrev = hashByBranch.get(currentBranch).getTracked();
		
		//If the stage is neither staged nor tracked by the head commit, error.
		if ((!stagedfiles.contains(fileName)) && (!trackedByPrev.containsKey(fileName))) {
			System.out.println("No reason to remove the file.");
		}
		// if the file has been staged, unstage it
		else if (stagedfiles.contains(fileName)) {
			stagedfiles.remove(fileName);
			Path deletethis = Paths.get(".gitlet/stage/" + fileName);
			try{
				Files.deleteIfExists(deletethis);
			} catch(Exception e){
        		e.printStackTrace();
        	}
		}
		//else, untrack the file
		else  {
			untracked.add(fileName);
		}
	}
	
	/**
	 * Associated command: java Gitlet log
	 * 
	 */
	public void log() {
		MyCommit myHead = hashByBranch.get(currentBranch);
		String eq = "===";
		StringBuilder rtn = new StringBuilder(eq + "\n" + myHead.toString());
		while (myHead.getPrev() != null) {
			rtn.append("\n" + "\n" + eq + "\n" + myHead.getPrev().toString());
			myHead = myHead.getPrev();
		}
		System.out.println(rtn);
	}
	
	/**
	 * Associated command: java Gitlet global-log
	 * 
	 */
	public void globalLog() {
		String eq = "===";
		StringBuilder rtn = new StringBuilder();
		for(HashMap.Entry<Integer, MyCommit> entry : hashByID.entrySet()){ 
			rtn.append("\n" + "\n" + eq + "\n" + entry.getValue().toString()); 
		}
		
		//delete the first two Char (which are the first two "\n"'s
		rtn.deleteCharAt(0);
		rtn.deleteCharAt(0);
		System.out.println(rtn);
	}
	
	/**
	 * Associated command: java Gitlet find [commit message]
	 * 
	 * @param message
	 * 		commit message
	 */
	public void find(String message) {
		//If no such commit exists.
		if (!hashByMsg.containsKey(message)) {
			System.out.println("Found no commit with that message.");
		}
		else {
			ArrayList<Integer> temp = hashByMsg.get(message);
			for (int i: temp) {
				System.out.println(i);
			}
		}
	}
	
	/**
	 * Associated command: java Gitlet status
	 * 
	 */
	public void status() {
		StringBuilder rtn = new StringBuilder();
		rtn.append("=== Branches ===");
		for(HashMap.Entry<String, MyCommit> entry : hashByBranch.entrySet()){ 
			//If entry.getKey() is the current branch, add *
			if (entry.getKey().equals(currentBranch)) {
				rtn.append("\n" + "*" + entry.getKey().toString());
			}
			else {
				rtn.append("\n" + entry.getKey().toString()); 
			}
		}
		rtn.append("\n" + "\n" + "=== Staged Files ===");
		for (String p: stagedfiles) {
			rtn.append("\n" + p);
		}
		rtn.append("\n" + "\n" + "=== Files Marked for Untracking ===");
		for (String p: untracked) {
			rtn.append("\n" + p);
		}
		System.out.println(rtn);
	}
	
	/**
	 * Associated commands: java Gitlet [file/branch name]
	 * 
	 * @param String fileOrBranch
	 * 		fileOrBranch can be either a file or Branch in String
	 */
	public void checkout(String fileOrBranch) {
		// currentCommit is the most recent commit on our current branch
		MyCommit currentCommit = hashByBranch.get(currentBranch);
		
		// check if it is a branch; if so, checkout the branch
		if (hashByBranch.containsKey(fileOrBranch)) {
			
			// can't checkout the current branch
			if (fileOrBranch.equals(currentBranch)){ 
				System.out.println("No need to checkout the current branch.");
				return;
			}
			MyCommit branchToCheckout = hashByBranch.get(fileOrBranch);
			
			//see if current branch tracks any files
			if (!branchToCheckout.getTracked().isEmpty()){  
				
				//Copy files tracked by the branch's head commit into working Dir
				for(HashMap.Entry<String, String> entry : branchToCheckout.getTracked().entrySet()){
					Path workPath = Paths.get(workDir.toString() + "/" + entry.getKey());
					Path filePath = Paths.get(".gitlet/" + entry.getValue() + "/" + entry.getKey());
					File fileparent = new File(".gitlet/" + entry.getValue() + "/" + entry.getKey());
					File workparent = new File(workDir.toString() + "/" + entry.getKey());
					
					fileparent.getParentFile().mkdirs();
					workparent.getParentFile().mkdirs();
					try {
						Files.copy(filePath, workPath, StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
						// this should never happen
						e.printStackTrace();
					}
				}
				
			}
			//switch the current branch to the branch we checked out
			currentBranch = fileOrBranch;

		// if it is not a branch, checkout the file from most recent commit (if it exists)
		} else if (currentCommit.getTracked().containsKey(fileOrBranch)) {
			Path workPath = Paths.get(workDir.toString() + "/" + fileOrBranch);
			Path filePath = Paths.get(".gitlet/" + currentCommit.getTracked().get(fileOrBranch) + "/" + fileOrBranch);
			File fileparent = new File(".gitlet/" + currentCommit.getTracked().get(fileOrBranch) + "/" + fileOrBranch);
			File workparent = new File(workDir.toString() + "/" + fileOrBranch);
			fileparent.getParentFile().mkdirs();
			workparent.getParentFile().mkdirs();
			try {
				Files.copy(filePath, workPath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// this should never happen
				e.printStackTrace();
			}
		} else {
			//if file does not exist in the more recent commit, error
			System.out.println("File does not exist in the most recent commit, or no such branch exists.");
			return ;
		}
	}
	
	/**
	 * Associated command: java Gitlet [commitID] [fileName]
	 * 
	 * Takes the version of the file as it exists in the
	 * commit with the given ID and puts it in the working
	 * directory, overwriting the version of the file if
	 * it exists.
	 * 
	 * 
	 * @param commitID
	 * 		the ID of the commit
	 * @param fileName
	 * 		the path to file in String
	 */
	public void checkout(String commitID, String fileName) {
		//If the commit ID does not exist or if the
		if (!hashByID.containsKey(Integer.parseInt(commitID))){
			System.out.println("No commit with that id exists.");
			return;
		}
		MyCommit targetCommit = hashByID.get(Integer.parseInt(commitID));
		
		//Copy the file from target commit to working dir
		if (targetCommit.getTracked().containsKey(fileName)) {
			Path workPath = Paths.get(workDir.toString() + "/" + fileName);
			Path filePath = Paths.get(".gitlet/" + targetCommit.getTracked().get(fileName) + "/" + fileName);
			File fileparent = new File(".gitlet/" + targetCommit.getTracked().get(fileName) + "/" + fileName);
			File workparent = new File(workDir.toString() + "/" + fileName);
			fileparent.getParentFile().mkdirs();
			workparent.getParentFile().mkdirs();
			
			try {
				Files.copy(filePath, workPath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// this should never happen
				e.printStackTrace();
			}
		} else {
			//file does not exist in the given commit.
			System.out.println("File does not exist in that commit.");
			return;
		}
	}
	
	/**
	 * Associated command: java Gitlet branch [branch name]
	 * 
	 * @param branchName
	 * 		the name of the branch you want to create
	 */
	public void branch(String branchName) {
		if (!hashByBranch.containsKey(branchName)){
			hashByBranch.put(branchName, hashByBranch.get(currentBranch));
		} else{
			//If the branch name already exists.
			System.out.println("A branch with that name already exists.");
		}
	}
	
	/**
	 * Associated command: java Gitlet rm-branch [branch name]
	 *
	 * @param branchName
	 * 		name of the branch you want to remove
	 */
	public void rmBranch(String branchToRM) {
		//if attempt to delete current branch, error
		if(branchToRM.equals(currentBranch)){
			System.out.println("Cannot remove the current branch.");
		}
		else if (hashByBranch.containsKey(branchToRM)){
			hashByBranch.remove(branchToRM);
		} else{
			//If the branch name does not exist
			System.out.println("A branch with that name does not exist.");
		}
	}
	
	/**
	 * Associated command: java Gitlet reset [commit id]
	 *
	 * @param commitID
	 * 		the ID of the commit you want to reset to
	 */
	public void reset(String commitID) {
		int intcommitID = Integer.parseInt(commitID);
		
		//If no commit with the given ID exists, error
		if(!hashByID.containsKey(intcommitID)){
			System.out.println("No commit with that id exists.");
			return;
		}
		MyCommit commitToReset = hashByID.get(intcommitID);
		
		//check out all the files in the commitToReset
		for (HashMap.Entry<String, String> entry : commitToReset.getTracked().entrySet()) {
			checkout(entry.getKey());
		}
		hashByBranch.put(currentBranch, commitToReset);
			
	}
	
	/**
	 * Associated command: java Gitlet merge [branch name]
	 * 
	 * @param branchToMerge
	 * 		merging the branchToMerge into current branch
	 */
	public void merge(String branchToMerge) {
		
		//If a branch with the given name does not exist, error
		if (!hashByBranch.containsKey(branchToMerge)) {
			System.out.println("A branch with that name does not exist.");
			return;
			
		//If try to merge a branch to itself, error
		} else if (branchToMerge.equals(currentBranch)) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}
		
		// get the commits we want to compare
		MyCommit splitPoint = findSplitPoint(branchToMerge);
		MyCommit branchCommit = hashByBranch.get(branchToMerge);
		MyCommit currentCommit = hashByBranch.get(currentBranch);
		
		//Looping through all the files in BranchToMerge's head commit
		for (HashMap.Entry<String, String> entry : branchCommit.getTracked().entrySet()) { 
			String fileName = entry.getKey();
			
			// if the splitpoint contains the file, check if they are the same
			if (splitPoint.getTracked().containsKey(fileName)) {
				String splitFile = ".gitlet/" + splitPoint.getTracked().get(fileName) + "/" + fileName;
				String branchFile = ".gitlet/" + entry.getValue() + "/" + fileName;
				
				//check if branch commit's file is modified since splitpoint
				//if branch's file is modified --> check current's file;
				//if current's file isn't modified, ignore this file
				if (!SameContent(branchFile, splitFile)) {
					String currentFile = ".gitlet/" + currentCommit.getTracked().get(fileName) + "/" + fileName;
					
					// if the current file is not modified but branch's file is, use branch's file
					if (SameContent(currentFile, splitFile)) {
						checkout(entry.getValue(), fileName);
						add(fileName);
						
					// modified files in both branches, so we copy the file to the working directory as a .conflicted file
					} else { 
						String branchFile1 = ".gitlet/" + entry.getValue() + "/" + fileName;
						String workFile = workDir.toString() + "/" + fileName + ".conflicted";
						
						CopyFiles(branchFile1, workFile);
						conflicted = true;
					}
				}
			} else { // if the split point does not contain the file
				// if we are looking at the same file between the branch commit and the current commit, check if same content
				if (currentCommit.getTracked().containsKey(fileName)) {
					String branchFile = ".gitlet/" + entry.getValue() + "/" + fileName;
					String currentFile = ".gitlet/" + currentCommit.getTracked().get(fileName) + "/" + fileName;
					String workFile = workDir.toString() + "/" + fileName + ".conflicted";
					if (!SameContent(branchFile, currentFile)){
						CopyFiles(branchFile, workFile);
						conflicted = true;
					}
				}
				checkout(entry.getValue(), fileName);
				add(fileName);
			}
		}
		if (!conflicted) {
			commit("Merged " + currentBranch + " with " + branchToMerge + ".");
		} else {
			System.out.println("Encountered a merge conflict.");
		}
	}
	
	/**
	 * Associated commend: java Gitlet rebase [branch name]
	 * 
	 * @param baseBranch
	 * 		the branch to which we are moving our current branch to
	 */
	public void rebase(String baseBranch) {
		if (!hashByBranch.containsKey(baseBranch)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (currentBranch.equals(baseBranch)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		} else if (isInHistory(baseBranch)) {
			System.out.println("Already up-to-date.");
			return;
		}
		
		//temp used to check if current branch is history of branchAsRebase
		MyCommit temp = hashByBranch.get(baseBranch);
		//Commit that baseBranch is pointing to
		MyCommit baseCommit = hashByBranch.get(baseBranch);
		MyCommit splitPoint = findSplitPoint(baseBranch);
		//Get the history of commitIDs from current to split point
		ArrayList<Integer> currentHistory = historyFromSplit(currentBranch, splitPoint);				
		
		// if current branch is history of branchAsRebase
		while (temp != null) {
			if (temp.equals(hashByBranch.get(currentBranch))) {
				hashByBranch.put(currentBranch, hashByBranch.get(baseBranch));
				return;
			} else {
				temp = temp.getPrev();
			}
		}
		
		//move current branch to where baseBranch points to
		hashByBranch.put(currentBranch, hashByBranch.get(baseBranch));
		
		//i is the Commit ID
		for (int i : currentHistory) {
			for (HashMap.Entry<String, String> entry : hashByID.get(i).getTracked().entrySet()) {
				String fileName = entry.getKey();
				if (splitPoint.getTracked().containsKey(fileName)) {
					String splitPath = ".gitlet/" + splitPoint.getTracked().get(fileName) + "/" + fileName;
					String currentPath = ".gitlet/" + entry.getValue() + "/" + fileName;
					if (SameContent(splitPath, currentPath)){
						checkout(Integer.toString(baseCommit.getID()), fileName);
						add(fileName);
						break;
					}
					//if file in current is different from file in split, we use the updated copy
					checkout(Integer.toString(i), fileName);
					add(fileName);
				}
			}
			commit(hashByID.get(i).getMessage());
		}
	}
	
	// ===== Helper Methods Section =====
	
	/**
	 * Updates all HashMaps
	 * @param MyCommit newCommit
	 */
	public void updateHashes(MyCommit newCommit) {
		hashByBranch.put(currentBranch, newCommit);
		if (hashByMsg.containsKey(newCommit.getMessage())) {
			hashByMsg.get(newCommit.getMessage()).add(newCommit.getID());
		}
		else {
			ArrayList<Integer> IDs = new ArrayList<Integer>();
			IDs.add(newCommit.getID());
			hashByMsg.put(newCommit.getMessage(), IDs);
		}
		hashByID.put(newCommit.getID(), newCommit);
		
	}
	
	/**
	 * Takes in the given branch and finds the split point
	 * between the branch and the current branch.
	 * 
	 * @param branchName
	 * 		find the splitpoint from given branch name and current branch
	 */
	public MyCommit findSplitPoint(String branchName) {
		MyCommit branchCommit = hashByBranch.get(branchName);
		MyCommit currentCommit = hashByBranch.get(currentBranch);
		ArrayList<Integer> branchIDs = new ArrayList<Integer>();
		ArrayList<Integer> currentBranchIDs = new ArrayList<Integer>();
		
		// Putting all the IDs in their respective ArrayLists
		while (branchCommit != null) {
			branchIDs.add(branchCommit.getID());
			branchCommit = branchCommit.getPrev();
		}
		while (currentCommit != null) {
			currentBranchIDs.add(currentCommit.getID());
			currentCommit = currentCommit.getPrev();
		}
		
		// Sort the IDs, decreasing to increasing
		Collections.sort(branchIDs);
		Collections.sort(currentBranchIDs);
		
		// While the IDs are the same, increment until otherwise
		int branchIndex = 0;
		while (branchIndex < Math.min(branchIDs.size(), currentBranchIDs.size()) && 
				branchIDs.get(branchIndex) == currentBranchIDs.get(branchIndex)) {
			branchIndex++;
		}
		// Get the most recent common ancestor
		return hashByID.get(branchIDs.get(branchIndex - 1));
	}
	
	/**
	 * Returns an arraylist of commitID's starting right after
	 * the splitpoint up to the given branch's head commit
	 * 
	 * @param branch
	 * 		branch we want to find the history of
	 * @param splitPT
	 * 		the splitpoint between two branches
	 * @return
	 * 		Returns an arraylist of commitID's starting right after
	 * 		the splitpoint up to the given branch's head commit
	 */
	public ArrayList<Integer> historyFromSplit(String branch, MyCommit splitPT) {
		ArrayList<Integer> ourHistory = new ArrayList<Integer>();
		MyCommit temp = hashByBranch.get(branch);
		while (!temp.equals(splitPT)) {
			ourHistory.add(temp.getID());
			temp = temp.getPrev();
		}
		Collections.sort(ourHistory);
		return ourHistory;
	}
	
	/**
	 * Checks if the given branch is within the current branch's history
	 * @param branch
	 * 		the branch name you want to find in history
	 * @return
	 * 		whether the given branch is in the history of current branch
	 */
	
	public boolean isInHistory(String branch) {
		MyCommit temp = hashByBranch.get(currentBranch);
		while (temp != null) {
			if (temp.getID() == hashByBranch.get(branch).getID()) {
				return true;
			}
			temp = temp.getPrev();
		}
		return false;
	}
	
	/**given from the test folder to recurisvely delete folders within folders
	 * 
	 * @param d
	 * 		folder or file to delete
	 * @throws IOException
	 */
	private static void recursiveDelete(File d) throws IOException {
		if (d.isDirectory()) {
			for (File f : d.listFiles()) {
				recursiveDelete(f);
			}
		}
		if (!d.delete()) {
			throw new IOException("Failed to delete file " + d.getPath());
		}
	}

	/**
	 * Clear the given folder
	 * @param folder
	 * 		the folder you want to clear contents of
	 */
	public void ClearDir(File folder){
		File[] files = folder.listFiles();
		if(files!=null) { 
			for(File f: files) {
				try{
					recursiveDelete(f);
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Returns if two files have the same content, assuming these files exist
	 * @param p1 
	 * 		path to file1 in String
	 * @param p2 
	 * 		path to file2 in String
	 * @return 
	 * 		whether file1 and fiel2 have same content
	 */
	public boolean SameContent(String p1, String p2){
		Path Path1 = Paths.get(p1);
		Path Path2 = Paths.get(p2);
		try {
			byte[] File1 = Files.readAllBytes(Path1);
			byte[] File2 = Files.readAllBytes(Path2);
			return Arrays.equals(File1, File2);
		} catch (IOException e){
			//Shouldn't happen
			return false;
		}
	}

	/**
	 * Copy files from original location to  destination
	 * 
	 * @param from
	 * 		path to original file in String
	 * 
	 * @param to
	 * 		path to destination in String
	 */
	public void CopyFiles(String from, String to){
		Path fromPath = Paths.get(from);
		Path toPath = Paths.get(to);
		File fromparent = new File(from);
		File toparent = new File(to);
		fromparent.getParentFile().mkdirs();
		toparent.getParentFile().mkdirs();
		try {
			Files.copy(fromPath, toPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// should never happen
			e.printStackTrace();
		}
	}
	
	// ===== End Helper Methods Section =====
	
	public static void main(String[] args) {
		Gitlet myGitlet = null;
	      try {
	         FileInputStream fileIn = new FileInputStream(".gitlet/myGitlet.ser"); //ask Alice if we can save this outside .gitlet
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         myGitlet = (Gitlet) in.readObject();
	         in.close();
	         fileIn.close();
	      }
	      catch(Exception e) { //do we need ClassNotFound exception?
	         myGitlet = new Gitlet();
	      }
		// add, rm, commit, checkout file, checkout id, log, global log find, status
		if ((args == null) || (args.length == 0)) {
			System.out.println("Please enter a command.");
			return;
		}
		if (!myGitlet.conflicted) {
			switch (args[0]) {
			case "init":
				myGitlet.init();
				break;
			case "add": 
				if (args.length == 2) {
					myGitlet.add(args[1]);
				}
				break;
			case "commit":
				if (args.length < 2) {
					myGitlet.commit(null);
				} else {
					myGitlet.commit(args[1]);
				}
				break;
			case "rm": 
				if (args.length == 2) {
					myGitlet.rm(args[1]);
				}
				break;
			case "log": 
				myGitlet.log();
				break;
			case "global-log": 
				myGitlet.globalLog();
				break;
			case "find": 
				if (args.length == 2) {
					myGitlet.find(args[1]);
				}
				break;
			case "status": 
				myGitlet.status();
				break;
			case "checkout":
				if (args.length == 2) {
					myGitlet.checkout(args[1]);
				}
				else if (args.length == 3) {
					myGitlet.checkout(args[1], args[2]);
				}
				break;
			case "branch": 
				if (args.length == 2) {
					myGitlet.branch(args[1]);
				}
				break;
			case "rm-branch": 
				if (args.length == 2) {
					myGitlet.rmBranch(args[1]);
				}
				break;
			case "reset": 
				if (args.length == 2) {
					myGitlet.reset(args[1]);
				}
				break;
			case "merge": 
				if (args.length == 2) {
					myGitlet.merge(args[1]);
				}
				break;
			case "rebase": 
				if (args.length == 2) {
					myGitlet.rebase(args[1]);
				}
				break;
			default: 
				System.out.println("No command with that name exists.");
				break;
			}
		} else if (myGitlet.conflicted) {
			String[] badCommands = {"branch", "rm-branch", "merge", "rebase", "reset"};
			for (String s : badCommands) {
				if (s.equals(args[0])) {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
					return;
				}
			}

			switch (args[0]) {
				case "add": 
					if (args.length == 2) {
						myGitlet.add(args[1]);
					}
					break;
				case "commit":
					if (args.length < 2) {
						myGitlet.commit(null);
					} else {
						myGitlet.commit(args[1]);
					}
					break;
				case "rm": 
					if (args.length == 2) {
						myGitlet.rm(args[1]);
					}
					break;
				case "log": 
					myGitlet.log();
					break;
				case "global-log":
					myGitlet.globalLog();
					break;
				case "find": 
					if (args.length == 2) {
						myGitlet.find(args[1]);
					}
					break;
				case "status": 
					myGitlet.status();
					break;
				case "checkout":
					if (args.length == 2) {
						if (myGitlet.hashByBranch.containsKey(args[1])) {
							System.out.println("Cannot do this command until the merge conflict has been resolved.");
							break;
						}
						myGitlet.checkout(args[1]);
					}
					else if (args.length == 3) {
						myGitlet.checkout(args[1], args[2]);
					}
					break;
				default: 
					System.out.println("No command with that name exists.");
					break;
			}
		}
		
		try {
			FileOutputStream fileOut = new FileOutputStream(".gitlet/myGitlet.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(myGitlet);
			out.close();
			fileOut.close();
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}

}

