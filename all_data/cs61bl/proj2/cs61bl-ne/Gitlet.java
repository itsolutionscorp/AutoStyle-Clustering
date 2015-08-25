import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Gitlet {

	/**
	 * The Gitlet class handles user input from the command line.
	 */
	private CommitTree myCommitTree;
	private final static String GITLET_DIR = System.getProperty("user.dir")
			+ "/.gitlet";
	public final static String commitFile = GITLET_DIR + "/commit#";
	private static File stagingArea = new File(GITLET_DIR + "/stagingArea");
	private static File gitlet = new File(GITLET_DIR);
	private static boolean conflicted=false;

	/**
	 * Create a new Gitlet system in the current directory.
	 */
	public Gitlet() {
		if (gitlet.exists()) {
			System.out
					.println("A gitlet version control system already exists in the current directory.");
			System.exit(0);
		}
		gitlet.mkdir();
		stagingArea.mkdir();
		myCommitTree = new CommitTree();
		conflicted = false;
		try {
			Files.createDirectories(new File(commitFile + "0").toPath());
			FileProcess
					.save(myCommitTree, new File(".gitlet/myCommitTree.obj"));
			FileProcess.save(new ArrayList<File>(), new File(
					".gitlet/unTracking.obj"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Commits the files in the staging area to the commit tree.
	 * 
	 * @param commitMessage
	 * 		A String message describing the commit.
	 */
	public static void commit(String commitMessage) {

		// get The snapshots of the current commit
		CommitTree myCommitTree = Gitlet.getMyCommitTree();
		File commitDir = new File(".gitlet/commit#" + myCommitTree.size());// eg:
																			// .gitlet/commit#1
		commitDir.mkdirs();
		// copy the files from the current commit to the
		// new commit area
		try {
			if (Gitlet.getStagedFiles().isEmpty()) {
				System.out.println("No changes added to the commit.");
				return;
			} else {
				FileProcess.copyFiles(
						myCommitTree.getSnapshot(myCommitTree.current()),
						commitDir);
			}
		} catch (IOException e) {
			commitDir.delete();
		}
		// move the files from staged Area to commit area
		// remove the files in the staged area
		try {
			if (Gitlet.getStagedFiles().isEmpty()) {
				System.out.println("No changes added to the commit.");
				return;
			} else {
				FileProcess.copyFiles(Gitlet.getStagedFiles(), commitDir);
				FileProcess.removeFiles(Gitlet.getStagedFiles());
			}
		} catch (IOException e) {
			commitDir.delete();
		}

		// remove the files if it is been untracked
		FileProcess.removeFiles(Gitlet.getUntrackingFiles(), commitDir);

		// make untracking files empty.
		saveUntrackingFiles(new ArrayList<File>());

		// create a snapshot of the files in the commit area
		// pass it as parameter to create a new commit to update myCommitTree
		myCommitTree.addCommit(
				new ArrayList<File>(Arrays.asList(commitDir.listFiles())),
				commitMessage);
		FileProcess.save(myCommitTree, new File(".gitlet/myCommitTree.obj"));
		if (conflicted) {
			conflicted = false;
		}

	}

	/**
	 * Add the file with the given name to the stagedArea
	 * 
	 * @param fileName The name of the file being staged
	 */
	public static void add(File file) {
		if (isInUntrackingFiles(file.getName())) {
			getUntrackingFiles().remove(file);
		} else {
			File stagedFile = new File(stagingArea.toString() + "/"
					+ file.toString());
			try {
				FileProcess.copyFile(file, stagedFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				stagedFile.delete();
				System.out.println("File does not exist.");
			}
		}

	}

	/**
	 * A helper function for removeGitlet. Recursively deletes directories and their contents
	 * @param f File to be deleted.
	 */
	private static void removeFiles(File f) {
		if (f.isDirectory()) {
			for (File p : f.listFiles()) {
				removeFiles(p);
			}
		} else {
			f.delete();
		}
	}

	/**
	 * Removes the current Gitlet version control system from the working directory.
	 * (Used for testing purposes)
	 */
	public static void removeGitlet() {
		removeFiles(gitlet);
		for (File f : gitlet.listFiles()) {
			f.delete();
		}
		gitlet.delete();
	}

	/**
	 * Overwrite the files in the working directory with files in the Commit
	 * files with the given Id
	 * 
	 * @param Id the Commit id to be checked out.
	 */
	public static void checkOut(int Id) {
		CommitTree myCommitTree = getMyCommitTree();
		ArrayList<File> myFiles = myCommitTree.getSnapshot(Id);
		File workingDirectory = new File(System.getProperty("user.dir"));
		try {
			FileProcess.copyFiles(myFiles, workingDirectory);
		} catch (IOException e) {
			System.out.println("failed checkout");
		}

	}

	/**
	 * Overload of checkOut that only checks out the File with the given name.
	 * 
	 * @param Id Commit to check the file out from
	 * @param fileName String name of the file to be checked out.
	 */
	public static void checkOut(int Id, String fileName) {
		CommitTree myCommitTree = getMyCommitTree();
		if (!myCommitTree.contains(Id)) {
			System.out.println("No commit with that id exists.");
			return;
		}
		ArrayList<File> myFiles = myCommitTree.getSnapshot(Id);
		File workingDirectory = new File(System.getProperty("user.dir"));
		for (File f : myFiles) {
			if (f.getName().equals(fileName)) {
				try {
					FileProcess.copyFile(
							f,
							new File(workingDirectory.toString() + "/"
									+ f.getName()));
				} catch (IOException e) {
					System.out.println("didnt work");
				}
				return;
			}
		}
		System.out.println("File does not exist in that commit.");
	}

	/**
	 * Checks out the file with the given name from the current commit.
	 * 
	 * @param fileName String name of the file.
	 */
	public static void checkOutByName(String fileName) {
		CommitTree myCommitTree = getMyCommitTree();
		ArrayList<File> myFiles = myCommitTree.getBranchSnapshot(myCommitTree
				.currentBranch());
		File workingDirectory = new File(System.getProperty("user.dir"));
		for (File f : myFiles) {
			if (f.getName().equals(fileName)) {
				try {
					FileProcess.copyFile(
							f,
							new File(workingDirectory.toString() + "/"
									+ f.getName()));
				} catch (IOException e) {
					System.out.println("failed checkout");
				}
				return;
			}
		}
		System.out
				.println("File does not exist in the most recent commit, or no such branch exists.");
	}

	/**
	 * Checks out all files from the given branch
	 * 
	 * @param branchName the String name of the given branch.
	 */
	public static void checkOutByBranch(String branchName) {
		if (!conflicted) {
			CommitTree myCommitTree = getMyCommitTree();
			if (!(myCommitTree.getBranches().containsKey(branchName))) {
				System.out
						.println("File does not exist in the most recent commit, or no such branch exists.");
			} else if (myCommitTree.isCurrentBranch(branchName)) {
				System.out.println("No need to checkout the current branch.");
			}
			ArrayList<File> myFiles = myCommitTree
					.getBranchSnapshot(branchName);
			File workingDirectory = new File(System.getProperty("user.dir"));
			try {
				FileProcess.copyFiles(myFiles, workingDirectory);
			} catch (IOException e) {
				System.out.println("failed checkout");
			}
			myCommitTree.setCurrentBranch(branchName);
			FileProcess
					.save(myCommitTree, new File(".gitlet/myCommitTree.obj"));
		} else {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
		}

	}

	/**
	 * Calls the commit tree to prints its global log.
	 */
	public static void printGlobalLog() {
		Gitlet.getMyCommitTree().printLog(true);
	}

	/**
	 * Calls the commit tree to recursively print the history of the current commit.
	 */
	public static void printBranchLog() {
		Gitlet.getMyCommitTree().printLog(false);
	}

	/**
	 * Returns the commit id of the commit with the given message.
	 * 
	 * @param message String message of the commit.
	 */
	public static void find(String message) {
		Gitlet.getMyCommitTree().findCommitWithMessage(message);
	}

	/**
	 * Read the commitTree from the file commitTree.obj
	 * 
	 * @return The commit tree.
	 */
	public static CommitTree getMyCommitTree() {
		return FileProcess.read(new File(".gitlet/mycommitTree.obj"));
	}

	/**
	 * Get the abstract Files in the staging area
	 * 
	 * @return An array list of staged files.
	 */
	public static ArrayList<File> getStagedFiles() {
		return new ArrayList<File>(Arrays.asList(stagingArea.listFiles()));
	}

	/**
	 * Returns the names of the files passed in as argument.
	 * 
	 * @param files An array list of Files
	 * @return An array list of file names.
	 */
	public static ArrayList<String> getStagedNames(ArrayList<File> files) {
		ArrayList<String> names = new ArrayList<String>();
		for (File file : files) {
			names.add(file.getName());
		}
		return names;
	}

	/**
	 * Save this file into the untracking file which is named untracking.er
	 * 
	 * @param f
	 */
	public static void saveUntrackingFiles(ArrayList<File> untrackingFiles) {
		FileProcess.save(untrackingFiles, new File(".gitlet/unTracking.obj"));

	}

	/**
	 * Get the File that the user has set untracked
	 * 
	 * @return
	 */
	public static ArrayList<File> getUntrackingFiles() {
		return FileProcess.read(new File(".gitlet/unTracking.obj"));
	}

	/**
	 * Prints information about the branch heads, files marked for untracking, and staged files.
	 */
	public static void printStatus() {
		CommitTree myCommitTree = Gitlet.getMyCommitTree();
		myCommitTree.printBranchStatus();
		System.out.println("=== Staged Files ===");
		for (File file : getStagedFiles()) {
			System.out.println(file.getName());
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (File file : getUntrackingFiles()) {
			System.out.println(file.getName());
		}
		System.out.println();
	}

	/**
	 * Makes a new branch with the given name.
	 * 
	 * @param name String name of the new branch.
	 */
	public static void makeBranch(String name) {
		if (!conflicted) {
			CommitTree myCommitTree = Gitlet.getMyCommitTree();
			myCommitTree.branch(name);
			FileProcess
					.save(myCommitTree, new File(".gitlet/myCommitTree.obj"));
		} else {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
		}

	}

	/**
	 * Removes a branch with the given name.
	 * 
	 * @param name String name of the branch to be removed.
	 */
	public static void removeBranch(String name) {
		if (!conflicted) {
			CommitTree myCommitTree = Gitlet.getMyCommitTree();
			myCommitTree.removeBranch(name);
			FileProcess
					.save(myCommitTree, new File(".gitlet/myCommitTree.obj"));
		} else {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
		}

	}

	/**
	 * Marks a file for untracking, or removes it from the staging area if it has
	 * already been staged.
	 * 
	 * @param rmFile String name of the file to be removed.
	 */
	public static void remove(String rmFile) {
		// check if the rmFile is in the stagedArea.
		// If so, delete it from the stagedArea.
		if (isInStagingArea(rmFile)) {
			new File(stagingArea + "/" + rmFile).delete();
			return;
		}
		// if the file is not in staged area,
		// add it in untracking arraylist
		ArrayList<File> untrackingFiles = getUntrackingFiles();
		untrackingFiles.add(new File(rmFile));
		// delete the original file, and then create a new one
		saveUntrackingFiles(untrackingFiles);

	}

	/**
	 * Checks to see if a file is marked for untracking
	 * 
	 * @param fName String name of the file
	 * @return true if this file is marked for untracking.
	 */
	public static boolean isInUntrackingFiles(String fName) {
		ArrayList<File> untrackingFiles = getUntrackingFiles();
		for (File f : untrackingFiles) {
			if (f.getName().equals(fName))
				return true;
		}
		return false;
	}

	/**
	 * Check if file with the given name is in the stagedArea
	 * 
	 * @param fName
	 *            name of the file
	 * @return true : inside false: not inside
	 */
	public static boolean isInStagingArea(String fName) {
		File[] stagedFiles = stagingArea.listFiles();
		for (int i = 0; i < stagedFiles.length; i++) {
			if (stagedFiles[i].getName().equals(fName))
				return true;
		}
		return false;
	}

	/**
	 * Checks out all the files tracked by the given commit. Also moves the
	 * current branch's head to that commit node
	 * 
	 * @param id
	 */
	public static void reset(int id) {
		if (!conflicted) {
			// if id doesnt' exsit throw an error
			CommitTree ct = Gitlet.getMyCommitTree();
			if (!ct.getCommits().containsKey(id)) {
				throw new IllegalArgumentException(
						"No commit with that id exists.");
			}
			checkOut(id);
			ct.setCurrent(id);
			FileProcess.save(ct, new File(".gitlet/myCommitTree.obj"));
		} else {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
		}

	}

	/**
	 * Merge the current branch with this given branch
	 * @param branch
	 * 		name of the given branch
	 */
	public static void merge(String branch){
		if (!conflicted){
			CommitTree ct=getMyCommitTree();
			//check failure cases
					if(branch.equals(ct.currentBranch())){
						System.out.println("Cannot merge a branch with itself.");
						return;
					}				
			int splitPointId=ct.findSplitPoint(ct.currentBranch(),branch);
			int branchId=ct.getBranchId(branch);		
			int curBranchId=ct.current();
			//iterate through all the files in the given branch
			ArrayList<File> filesInGiven=FileProcess.fileDirInArrayList(new File(commitFile+branchId));
			for(File f:filesInGiven){
					
					if(Gitlet.modificationState(f, splitPointId, curBranchId, branchId)==3){
					//no conflict
						//if this file is only modified in the given branch, check it out 
						//then stage it to make a new commit automatically
						Gitlet.checkOut(branchId, f.getName());
						
						try {
							FileProcess.copyFile(f,new File(stagingArea+"/"+f.getName()));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(Gitlet.modificationState(f, splitPointId, curBranchId, branchId)==2){
						//if this file is only modified in the current branch, 
						// don't touch 

						try {
							FileProcess.copyFile(new File(commitFile+curBranchId+"/"+f.getName())
													,new File(stagingArea+"/"+f.getName()));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					}else if(Gitlet.modificationState(f, splitPointId, curBranchId, branchId)==1){
					//conflict here
						//if the file is modified in both place move these two files to working dir
						// let the user decide which one to commit
						conflicted=true;
						try {
							FileProcess.copyFile(f, new File(f.getName()));
							FileProcess.copyFile(f, new File(f.getName().concat(".conflicted")));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}					
					}else if(Gitlet.modificationState(f, splitPointId, curBranchId, branchId)==4){
						// file is not modifed in both places
						try {
							FileProcess.copyFile(f,new File(stagingArea+"/"+f.getName()));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}														
					
			}		
			if(!conflicted){
				//if no conflict, make an automatic commit
				Gitlet.commit("Merged "+ct.currentBranch()+" with "+branch);
			}else{
				//if has conflict, 
				System.out.println("Encountered a merge conflict.");
				
			}	
		} else {
			System.out
			.println("Cannot do this command until the merge conflict has been resolved.");
		}
			
	}
	/**
	 * return the state of the file
	 * 4 not modified in both branch
	 * 3 only modified in given branch
	 * 2 only modified in the cur branch
	 * 1 modified in the two places
	 * @param f
	 * @param splitId
	 * 			id for splitPoint
	 * @param curId
	 * 			id for current branch
	 * @param branchId
	 * 			id for given branch
	 * @return
	 */
	public static int modificationState(File f, int splitId, int curId, int branchId ){
		// false:1)file only exist in the given branch 2) two files are different
		
		File fileIncur=new File(commitFile+curId+"/"+f.getName());
		File fileInSplit=new File(commitFile+splitId+"/"+f.getName());
		if(!fileIncur.exists()&&!fileInSplit.exists()){
			//if both of the files does not exist in cur and split return 3
			return 3;
		}else if(!fileIncur.exists()){
			// one of cur and split does not have the file ,so it is different versions
			return 3;
			
		}else if(!fileInSplit.exists()){
			
			return 1;
		}
		else{
			// cur and split both has the files
			boolean curToSplit=FileProcess.equals(fileIncur,fileInSplit);										
			boolean branchToSplit=FileProcess.equals(f,fileInSplit );// false if different
			if(curToSplit==true && branchToSplit==false){
				// only modified in given branch
				return 3;
			}else if(curToSplit==false && branchToSplit==true){
				return 2;
			}else if(curToSplit==false && branchToSplit==false){
				System.out.println("test");
				return 1;
			}else if(curToSplit==true && branchToSplit==true){
				return 3;
			}else{
				return 1;
			}
			
			
		}		
	}

	/**
	 * Rebases the commit tree to the current branch. Replays commits, propagatint 
	 * through files in the given branch that have been modified since the split point 
	 * until more recently modified files are encountered.
	 * 
	 * @param branch String name of the branch on which to be rebased. 
	 */
	public static void rebase(String branch) {
		CommitTree myCommitTree = Gitlet.getMyCommitTree();
		if (!myCommitTree.getBranches().containsKey(branch)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (!conflicted) {
			int splitID = myCommitTree.findSplitPoint(branch, myCommitTree.currentBranch());
			if (splitID == -1) {
				myCommitTree.setCurrent(myCommitTree.getBranchId(branch));
				FileProcess.save(myCommitTree, new File(
						".gitlet/myCommitTree.obj"));
				return;
			} else {
				ArrayList<File> branchFiles = myCommitTree
						.getBranchSnapshot(branch);
				ArrayList<File> splitFiles = myCommitTree.getSnapshot(splitID);
				ArrayList<File> propagatingFiles = new ArrayList<File>();
				for (File f : branchFiles) {
					for (File p : splitFiles) {
						if (!(FileProcess.equals(f, p)) && f.getName().equals(p.getName())) {
								propagatingFiles.add(f);
						}
					}
				}
				myCommitTree.rebase(propagatingFiles, branch, splitID);
			}
		} else {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
		}
	}

	/**
	 * A helper for rebase that copies the replayed commits into the gitlet folder.
	 * 
	 * @param files A Stack of file array lists that represents the snapshots of replayed commits.
	 * @param messages String messages of the replayed commits.
	 */
	public static void rebaseHelper(Stack<ArrayList<File>> files,
			Stack<String> messages) {
		CommitTree myCommitTree = getMyCommitTree();
		while (!files.isEmpty()) {
			ArrayList<File> l = files.pop();
			try {
				File copiedCommit = new File(".gitlet/commit#"
						+ myCommitTree.size());
				copiedCommit.mkdirs();
				FileProcess.copyFiles(l, copiedCommit);
			} catch (IOException e) {
				System.out.println("rebase failed");
			}
			myCommitTree.addCommit(l, messages.pop());
		}
		FileProcess.save(myCommitTree, new File(".gitlet/myCommitTree.obj"));
		reset(getMyCommitTree().current());
	}

	/**
	 * Handles commands from the user.
	 * @param args
	 */
	public static void main(String[] args) {
		if (args[0].equals("init")) {
			new Gitlet();
		} else if (args[0].equals("add")) {
			File temp = new File(args[1]);
			add(temp);
		} else if (args[0].equals("commit")) {
			if (args.length == 1) {
				System.out.println("Please enter a commit message.");
			} else {
				commit(args[1]);
			}
		} else if (args[0].equals("log")) {
			printBranchLog();
		} else if (args[0].equals("global-log")) {
			printGlobalLog();
		} else if (args[0].equals("find")) {
			if (args.length == 1) {
				System.out.println("Please enter a commit message.");
			} else {
				find(args[1]);
			}
		} else if (args[0].equals("status")) {
			printStatus();
		} else if (args[0].equals("branch")) {
			makeBranch(args[1]);
		} else if (args[0].equals("rm-branch")) {
			removeBranch(args[1]);
		} else if (args[0].equals("checkout")) {
			if (args.length == 3) {
				checkOut(Integer.parseInt(args[1]), args[2]);
			} else {
				if (Gitlet.getMyCommitTree().getBranches().containsKey(args[1])) {
					checkOutByBranch(args[1]);
				} else {
					checkOutByName(args[1]);
				}
			}
		} else if (args[0].equals("rm")) {
			remove(args[1]);
		} else if (args[0].equals("reset")) {
			reset(Integer.parseInt(args[1]));
		} else if (args[0].equals("merge")) {
			merge(args[1]);
		} else if (args[0].equals("rebase")) {
			rebase(args[1]);
		} else {
			System.out.println(args[0] + "is not a command.");
		}
	}

}
