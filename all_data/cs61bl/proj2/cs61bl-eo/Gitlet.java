import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Gitlet implements Serializable{
	private int lastCommitID;
	private String curBranch;
	private Map<String, commitNode> branchHead;
	private Map<Integer, commitNode> gitMap;
	private List<String> stagedFiles;
	private List<String> untrackedFiles;
	private boolean conflict=false;

	/**
	 * This is the gitlet constructor
	 * what this thing does is to init everything before it start
	 */
	public Gitlet() {
		lastCommitID = -1;
		curBranch = "master";
		branchHead = new HashMap<String, commitNode>();
		gitMap = new HashMap<Integer, commitNode>();
		stagedFiles = new LinkedList<String>();
		untrackedFiles = new LinkedList<String>();
	}


	/**
	 * Creates a new gitlet version control system in the current directory.
	 * This system will automatically start with one commit: a commit that contains no files
	 * @print when a folder .gitlet exist print "A gitlet version control system already exists in the current directory."
	 * make a new direction of stage in .gitlet
	 * make a starter commit node 
	 * update the branchHead and gitMap
	 */
	public void init() {
		File dir = new File(".gitlet");
		if (dir.exists()) {
			System.out.println("A gitlet version control system already exists in the current directory.");
			return;
		} else {
			dir.mkdir();
		}
		commitNode initialCommit = new commitNode(0, -1, "initial commit", currentTime());
		lastCommitID += 1;
		branchHead.put(curBranch, initialCommit);
		gitMap.put(lastCommitID, initialCommit);
	}

	/**
	 * copy the file to the staging area or unmark the file for untracking 
	 * @param	fileName the path of the file intended to add.
	 * @print  If the file does not exist, print the error message File does not exist.	
	 */
	public void add(String fileName) {
		if (fileName.equals("")) {
			System.out.println("Please enter a file name.");
			return;
		}
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("File does not exist.");
			return;
		} else if (untrackedFiles.contains(fileName)){
			untrackedFiles.remove(fileName);
		}else if (!stagedFiles.contains(fileName)) {
			stagedFiles.add(fileName);
		}
		copyFile(fileName,".gitlet/stage/" + fileName);
	}

	/**
	 * Saves a snapshot of certain files that can be viewed or restored at a later time.
	 * the files should be inheriated from the old commit and updated for the stagedFiles and untracked Files;
	 * after that update branchHead, lastCommitID, gitMap, stagedFiles and untrackedFiles
	 * @param message a message to save during commit
	 * @print If no files have been staged (or marked for untracking: more on that next), 
	 * aborts. Print the error message No changes added to the commit.
	 * @print Also, every commit must have a non-blank message. 
	 * If it doesn't, print the error message Please enter a commit message.
	 * There is a special case in the merge that even nothing is changed but we have to create a new commit, 
	 * that is the force boolean come from 
	 */
	public void commit(String message,boolean force) {
		conflict = false;
		if (stagedFiles.size() == 0&&untrackedFiles.size()==0&&!force) {
			System.out.println("No changes added to the commit.");
			return;
		}
		int curCommitID = lastCommitID + 1;
		commitNode curCommit = new commitNode(curCommitID, branchHead.get(curBranch).getCommitID(), message, currentTime());
		Map<String, String> lastCommitedFiles =branchHead.get(curBranch).getAllCommitedFiles();
		for (String file: lastCommitedFiles.keySet()) {
			if (!stagedFiles.contains(file)) {
				curCommit.addFile(file, lastCommitedFiles.get(file));
			}
		}
		for (String file: stagedFiles) {
			curCommit.addFile(file, ".gitlet/" + curCommitID + "/" + file);
			copyFile(".gitlet/stage/"+file,".gitlet/" + curCommitID + "/" + file);
			deleteFile(".gitlet/stage/"+file);
		}
		for (String fileName : untrackedFiles){
			curCommit.addFile(fileName, null);
		}
		gitMap.put((Integer)curCommitID, curCommit);
		branchHead.put(curBranch, curCommit);
		lastCommitID ++;
		stagedFiles = new LinkedList<String>();
		untrackedFiles = new LinkedList<String>();
	}

	/**
	 * remove the file from staging area or mark it for untracking 
	 * @param fileName the path of the file intended to rm
	 * @print If the file is neither staged nor tracked by the head commit, 
	 * print the error message No reason to remove the file.
	 */
	public void rm(String fileName) {
		if (!stagedFiles.contains(fileName)&&!branchHead.get(curBranch).containsFile(fileName)){
			System.out.println("No reason to remove the file.");
		}else if (stagedFiles.contains(fileName)){
			stagedFiles.remove(fileName);
			deleteFile(".gitlet/stage/"+fileName);
		}else{
			if (!untrackedFiles.contains(fileName)) {
				untrackedFiles.add(fileName);
			}
		}
	}

	/**
	 * Starting at the current head pointer, display information about each commit backwards
	 * along the commit tree until the initial commit.
	 * This set of commit nodes is called the commit’s history.
	 * For every node in this history, the information it should display is the commit id,
	 * the time the commit was made, and the commit message.
	 */
	public void log() {
		int curNode = gitMap.get(lastCommitID).getCommitID();
		while (curNode != -1) {
			System.out.println("====");
			System.out.println("Commit " + curNode);
			System.out.println(gitMap.get(curNode).getCommitTime());
			System.out.println(gitMap.get(curNode).getCommitMessage());
			System.out.println();
			curNode = gitMap.get(curNode).getPrevNodeID();
		}
	}

	/**
	 * Like log, except displays information about all commits ever made. The order of the
	 * commits does not matter.
	 */
	public void globalLog() {
		int curNode = lastCommitID;
		while (curNode != -1) {
			System.out.println("====");
			System.out.println("Commit " + curNode);
			System.out.println(gitMap.get(curNode).getCommitTime());
			System.out.println(gitMap.get(curNode).getCommitMessage());
			System.out.println();
			curNode = curNode - 1;
		}
	}

	/**
	 * Prints out the id of the commit that has the given commit message.
	 * If there are multiple such commits, it prints the ids out on separate lines.
	 *
	 * @param targetMessage
	 * @print If no such commit exists, prints the error message Found no commit with that message.
	 */
	public void find(String targetMessage) {
		int curNode = lastCommitID;
		boolean hasFind = false;
		while (curNode != -1) {
			if (gitMap.get(curNode).getCommitMessage().equals(targetMessage)) {
				System.out.println(curNode);
				hasFind = true;
			}
			curNode = curNode - 1;
		}
		if (!hasFind) {
			System.out.println("Found no commit with that message.");
		}
	}

	/**
	 * Displays what branches currently exist, and marks the current branch with a *.
	 * Also displays what files have been staged or marked for removal.
	 */
	public void status() {
		System.out.println("=== Branches ===");
		System.out.println("*" + curBranch);
		for (String branch: branchHead.keySet()) {
			if (!branch.equals(curBranch)) {
				System.out.println(branch);
			}
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		for (int i = 0; i < stagedFiles.size(); i++) {
			System.out.println(stagedFiles.get(i));
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (int i = 0; i < untrackedFiles.size(); i++) {
			System.out.println(untrackedFiles.get(i));
		}
	}
	/**
	 * this is the function for the first and the third case check out the file or the other brach
	 * @param arg the string for file name or branch name 
	 * @print If no branch with that name exists, print File does not exist in the most recent commit, or no such branch exists.
	 * @print If that branch is the current branch, print No need to checkout the current branch.
	 */


	public void checkout1(String arg) {
		if (branchHead.get(curBranch).containsFile(arg)){
			copyFile(branchHead.get(curBranch).getFilePath(arg),arg);
		}else if (branchHead.containsKey(arg)) {
			if (conflict){
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
				return;
			}
			if (curBranch.equals(arg)) {
				System.out.println("No need to checkout the current branch.");
				return;
			}else{
				for (Map.Entry<String, String> entry : branchHead.get(arg).getAllCommitedFiles().entrySet()){
					copyFile(entry.getValue(),entry.getKey());
				}
				curBranch = arg;
			}
		}else{
			System.out.println("File does not exist in the most recent commit, or no such branch exists.");
		}
	}

	/**
	 *this is the second case of the check out command
	 * @param arg1 commit id 
	 * @param arg2 file name 
	 * @print If no commit with the given id exists, print No commit with that id exists.
	 * @print if the file does not exist in the given commit, print File does not exist in that commit.
	 */
	public void checkout2(int arg1, String arg2) {
		if(!gitMap.containsKey(arg1)){
			System.out.println("No commit with that id exists.");
		}else if(!gitMap.get(arg1).containsFile(arg2)){
			System.out.println("File does not exist in that commit.");
		}else{
			copyFile(gitMap.get(arg1).getFilePath(arg2),arg2);
		}
	}

	/**
	 * Creates a new branch with the given name.
	 * A branch is nothing more than the name of a head pointer in the commit graph.
	 * Before you ever call branch, you are still running a default branch called "master".
	 *
	 * @param branchName to added
	 * @print no Failure case
	 */
	public void branch(String branchName) {
		if (conflict){
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		if (branchHead.containsKey(branchName)) {
			System.out.println("A branch with that name already exists.");
		}else {
			branchHead.put(branchName, branchHead.get(curBranch));
		}
	}

	/**
	 * Deletes the branch with the given name.
	 *
	 * @param branchName to remove
	 * @print If a branch with the given name does not exist, aborts. 
	 * Print the error message A branch with that name does not exist.
	 * @print If you try to remove the branch you're currently on, aborts, 
	 * printing the error message Cannot remove the current branch.
	 */
	public void rmbranch(String branchName) {
		if (conflict){
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		if (curBranch.equals(branchName)) {
			System.out.println("Cannot remove the current branch.");
		}else if (!branchHead.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
		}else{
			branchHead.remove(branchName);
		}
	}

	/**
	 *Checks out all the files tracked by the given commit. Also moves the current branch's head to that commit node.
	 * @param commitID commit with the number that set as the given curBranch
	 * @print If no commit with the given id exists, print No commit with that id exists.
	 */
	public void reset(int commitID) {
		if (conflict){
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		if(!gitMap.containsKey(commitID)){
			System.out.println("No commit with that id exists.");
		}else{
			for (Map.Entry<String, String> entry : gitMap.get(commitID).getAllCommitedFiles().entrySet()){
				copyFile(entry.getValue(),entry.getKey());
			}
			branchHead.put(curBranch, gitMap.get(commitID));
		}
	}

	/**
	 * one case to copy file is that the current file unchanged but the give file changed, so we iterater throught the
	 * current file to make sure it enforced the copy, and also deal with the stagedfile and untrackedfile
	 * the other is that when to generate conflict, the only case is that both file has changed, (but not removed)
	 * 
	 * there is a special case is that the file from curbranch is untracked but, the given file has changed. nothing added to
	 * the stage and untracked. but we still need to force a new commit, hope this idea is right
	 * 
	 * @param branchName the given branch 
	 * @print two faliure case, nothing to talk about just make sure the boolean get the right value
	 */

	public void merge(String branchName){
		if(!branchHead.containsKey(branchName)){
			System.out.println("A branch with that name does not exist.");
		}else if(curBranch.equals(branchName)){
			System.out.println("Cannot merge a branch with itself.");
		}else{
			int split = splitNode(branchName);
			for (Map.Entry<String, String> entry : branchHead.get(curBranch).getAllCommitedFiles().entrySet()){
				if (entry.getValue()==null&&gitMap.get(split).getFilePath(entry.getKey())==null&&
						branchHead.get(branchName).getFilePath(entry.getKey())!=null){
					copyFile(branchHead.get(branchName).getFilePath(entry.getKey()),entry.getKey());
					copyFile(branchHead.get(branchName).getFilePath(entry.getKey()),".gitlet/stage/"+entry.getKey());
					if (!stagedFiles.contains(entry.getKey())) {
						stagedFiles.add(entry.getKey());
					}
					untrackedFiles.remove(entry.getKey());
				}else if (entry.getValue().equals(gitMap.get(split).getFilePath(entry.getKey()))&&
						!entry.getValue().equals(branchHead.get(branchName).getFilePath(entry.getKey()))){
					copyFile(branchHead.get(branchName).getFilePath(entry.getKey()),entry.getKey());
					copyFile(branchHead.get(branchName).getFilePath(entry.getKey()),".gitlet/stage/"+entry.getKey());
					if (!stagedFiles.contains(entry.getKey())) {
						stagedFiles.add(entry.getKey());
					}
					untrackedFiles.remove(entry.getKey());
				}
			}
			for (Map.Entry<String, String> entry : branchHead.get(branchName).getAllCommitedFiles().entrySet()){
				if (branchHead.get(curBranch).getFilePath(entry.getKey())==null){
				}else if(entry.getValue()==null){
				}else if ((!entry.getValue().equals(gitMap.get(split).getFilePath(entry.getKey())))&&
						(!branchHead.get(curBranch).getFilePath(entry.getKey())
								.equals(gitMap.get(split).getFilePath(entry.getKey())))){
					copyFile(entry.getValue(),entry.getKey()+".conflicted");
					conflict = true;
				}
			}
			if (!conflict){
				commit("Merged " +curBranch +" with " +branchName+".",true);
			}else {
				System.out.println("Encountered a merge conflict.");
			}
		}
	}
	
	/**
	 * find the split point of the current branch and the given branch, 
	 * then snaps off the current branch at this point, 
	 * then reattaches the current branch to the head of the given branch. 
	 * @param branchName the branch that rebase to
	 * @print error message, nothing to say
	 */

	public void rebase(String branchName){
		if(!branchHead.containsKey(branchName)){
			System.out.println("A branch with that name does not exist.");
		}else if(curBranch.equals(branchName)){
			System.out.println("Cannot rebase a branch onto itself.");
		}else if(history(curBranch,branchName)){
			System.out.println("Already up-to-date.");
		}else if(history(branchName,curBranch)){
			branchHead.put(curBranch, branchHead.get(branchName));	
		}else{
			int split = splitNode(branchName);
			ArrayList<Integer> nums = new ArrayList<Integer>();
			for (int i = branchHead.get(curBranch).getCommitID();i>split;i = gitMap.get(i).getPrevNodeID()){
				nums.add(i);
			}
			Collections.reverse(nums);
			int abc = lastCommitID;
			for (int i :nums){
				int curCommitID = lastCommitID + 1;
				commitNode curCommit = 
						new commitNode(curCommitID, lastCommitID, gitMap.get(i).getCommitMessage(), currentTime());
				Map<String, String> propagateFilesToReplay =branchHead.get(branchName).getAllCommitedFiles();
				for (String file: propagateFilesToReplay.keySet()) {
					curCommit.addFile(file, propagateFilesToReplay.get(file));
				}
				Map<String, String> oldFilesToReplay = gitMap.get(i).getAllCommitedFiles();
				for(String file1 :oldFilesToReplay.keySet()){
					if (gitMap.get(i).getFilePath(file1)==null){
						curCommit.addFile(file1, null);
					}else if (gitMap.get(split).getFilePath(file1)==null&&gitMap.get(i).getFilePath(file1)!=null){
						curCommit.addFile(file1, gitMap.get(i).getFilePath(file1));
					}else if (!gitMap.get(split).getFilePath(file1).equals(gitMap.get(i).getFilePath(file1))){
						curCommit.addFile(file1, oldFilesToReplay.get(file1));
					}
				}
				System.out.println(curCommit.getAllCommitedFiles());
				gitMap.put((Integer)curCommitID, curCommit);
				lastCommitID++;
				branchHead.put(curBranch, curCommit);
			}
			gitMap.get(abc+1).changePreID(branchHead.get(branchName).getCommitID());
		}
	}
	/**
	 * return the slipnode from given branch to current branch
	 * @param branchName
	 * @return slipnode number 
	 */
	public int splitNode(String branchName){
		LinkedList<Integer> a = new LinkedList<Integer>();
		for (int i=branchHead.get(curBranch).getCommitID();i>-1;i = gitMap.get(i).getPrevNodeID()){
			a.add(i);
		}
		for (int i=branchHead.get(branchName).getCommitID();i>-1;i = gitMap.get(i).getPrevNodeID()){
			if (a.contains(i)){
				return i;
			}
		}
		return 0;
	}
	/** 
	 * this method is to test if branch2 is branch1's history
	 */

	public boolean history(String branchName1,String branchName2){
		LinkedList<Integer> a = new LinkedList<Integer>();
		for (int i=branchHead.get(branchName1).getCommitID();i>-1;i = gitMap.get(i).getPrevNodeID()){
			a.add(i);
		}
		return a.contains(branchHead.get(branchName2).getCommitID());
	}

	/**
	 * this method is delete the file from the given file path
	 * @param a file path
	 */

	public static void deleteFile(String a){
		File file = new File(a);
		file.delete();
	}

	/**
	 * this method is to return a current time with a requirted format
	 * @return a String of current time
	 */
	public String currentTime(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		return curDate;
	}
	/**
	 * This method is to copy the from given file path a to given file path b
	 * @param a source
	 * @param b destination 
	 */

	public void copyFile(String a, String b){
		File sourceFile = new File(a);
		File targetFile = new File(b);
		try {
			targetFile.mkdirs();
			Files.copy(sourceFile.toPath(), targetFile.toPath(),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}
	/**
	 * this method is to convert the String array into a single String
	 * @param args String array
	 * @return a singe String 
	 */

	private static String wrapArg(String[] args){
		String result = "";
		for (int i = 1; i<args.length;i++){
			result = result + " " + args[i];
		}
		return result.substring(1);
	}
	/**
	 * this method is to desirlized the .ser file and load it
	 */

	private static Gitlet tryLoadingData() {
		Gitlet mySmallGitlet = null;
		File myCatFile = new File(".gitlet/gitlet.ser");
		if (myCatFile.exists()) {
			try {
				FileInputStream fileIn = new FileInputStream(myCatFile);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				mySmallGitlet = (Gitlet) objectIn.readObject();
				objectIn.close();
			} catch (IOException e) {
				System.out.println("IOException while loading DATA.");
			} catch (ClassNotFoundException e) {
				System.out.println("ClassNotFoundException while loading DATA.");
			}
		}
		return mySmallGitlet;
	}
	/**
	 * this method is to take the Gitlet object and write it into a .ser file
	 * @param mySmallGitlet the Gitlet Object pass in
	 */

	private static void trySavingData(Gitlet mySmallGitlet) {
		if (mySmallGitlet == null) {
			return;
		}
		try {
			File myCatFile = new File(".gitlet/gitlet.ser");
			FileOutputStream fileOut = new FileOutputStream(myCatFile);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(mySmallGitlet);
			objectOut.close();
		} catch (IOException e) {
			System.out.println("IOException while saving DATA.");
		}
	}
	/**
	 * the main method of Gitlet class, every command should be "distribute" into method from here. also it use serilization to 
	 * make sure all the information is properly stored.
	 * it returns nothing.
	 * @param args
	 * @print If a user doesn't input any arguments, print the message: Please enter a command.
	 * @print If a user inputs a command that doesn't exist, please print the message: No command with that name exists.
	 */
	public static void main(String[] args) {
		Gitlet myGitlet = tryLoadingData();
		if (args.length == 0) {
			System.out.println("Please enter a command.");
		} else {
			if (args[0].equals("init")){
				myGitlet = new Gitlet();
				myGitlet.init();
			}else if(args[0].equals("add")){
				myGitlet.add(args[1]);
			}else if (args[0].equals("commit")){
				if (args.length==1) {
					System.out.println("Please enter a commit message.");
				}else{
					myGitlet.commit(wrapArg(args),false);
				}
			}else if (args[0].equals("rm")){
				myGitlet.rm(args[1]);
			}else if (args[0].equals("log")){
				myGitlet.log();
			}else if (args[0].equals("globalLog")){
				myGitlet.globalLog();
			}else if (args[0].equals("find")){
				if (args.length==1) {
					System.out.println("Found no commit with that message.");
				}else {
					myGitlet.find(wrapArg(args));
				}
			}else if (args[0].equals("status")){
				myGitlet.status();
			}else if (args[0].equals("checkout")){
				if (args.length==2){
					myGitlet.checkout1(args[1]);
				}else if (args.length==3){
					myGitlet.checkout2(Integer.parseInt(args[1]), args[2]);
				}
			}else if (args[0].equals("reset")){
				myGitlet.reset(Integer.parseInt(args[1]));
			}else if (args[0].equals("branch")){
				myGitlet.branch(args[1]);
			}else if (args[0].equals("rm-branch")){
				myGitlet.rmbranch(args[1]);
			}else if (args[0].equals("merge")){
				myGitlet.merge(args[1]);
			}else if (args[0].equals("rebase")){
				myGitlet.rebase(args[1]);
			}else {
				System.out.println("No command with that name exists.");
			}
		}
		trySavingData(myGitlet);
	}
}