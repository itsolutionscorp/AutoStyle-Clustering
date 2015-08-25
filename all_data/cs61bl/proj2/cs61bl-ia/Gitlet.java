import java.util.*;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Gitlet implements Serializable {
	private boolean conflicted;
	private File gitlet;
	private File stagingArea;	

	private CommitNode curr;
	private CommitNode currPointer;

	private ArrayList<CommitNode> glog;

	private ArrayList<String> markedPaths; 
	private ArrayList<String> stagedList;
	private HashMap<String, String> seenWorkingPaths; 

	private ArrayList<CommitNode> branchObj;
	private ArrayList<String> branchNames;
	private String currBranch;

	private int counter = 0;

	/**
	 * Serializes a Gitlet object to a file named .gitlet/.Gitlet.ser
	 * @param myGit 
	 * 			a gitlet object
	 */
	private static void serialize(Gitlet myGit) {
		try {
			File mySer = new File(".gitlet/.Gitlet.ser");
			FileOutputStream fileOut = new FileOutputStream(mySer);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(myGit);
			objectOut.close();
			fileOut.close();

		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Deserializes the .ser file to the Gitlet object
	 * @return the deserialized Gitlet object 
	 */
	private static Gitlet deserialize() {
		Gitlet myG = null;
		File mySer = new File(".gitlet/.Gitlet.ser");
		if (mySer.exists()) {
			try {
				FileInputStream fileIn = new FileInputStream(mySer);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				myG = (Gitlet) objectIn.readObject();

				objectIn.close();
				fileIn.close();
			} catch (IOException | ClassNotFoundException e) {
				
			}
		}
		return myG;
	}
	/**
	 * The constructor that initializes all the instance variables in Gitlet
	 * Also makes the .gitlet and staging-area folder
	 */
	public Gitlet() {
		currPointer = new CommitNode(curr, "master"); 
		branchObj = new ArrayList<CommitNode>();
		currBranch = currPointer.name;

		branchObj.add(currPointer);

		gitlet = new File(".gitlet");
		stagingArea = new File(Paths.get(".gitlet", "staging-area").toString());

		stagedList = new ArrayList<String>();
		seenWorkingPaths = new HashMap<String, String>();
		branchNames = new ArrayList<String>();
		markedPaths = new ArrayList<String>();
		glog = new ArrayList<CommitNode>();

		if (gitlet.exists()) {
			System.out
					.println("A gitlet version control system already exists in the current directory.");
		} else {
			try {
				gitlet.mkdirs(); 
				stagingArea.mkdirs();
				curr = comm();
				counter++;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	/**
	 * A helper function called by the commit method that 
	 * moves the files in the staging area to the appropriate 
	 * directory based on what commit the user is on
	 * @param c
	 * 		a new CommitNode that is created when the user calls commit
	 */
	public void moveFilesOver(CommitNode c) {
	
		for (int i = 0; i < stagedList.size(); i++) {

			Path pathToFile = Paths.get(".gitlet", "staging-area",
					stagedList.get(i));
			Path pathToDestination = Paths.get(".gitlet", "d" + counter);
			c.pathsToFiles.add(stagedList.get(i));
			try {

				Files.move(pathToFile,
						pathToDestination.resolve(pathToFile.getFileName()));
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
	/**
	 * A helper function called by the commit method that adds the 
	 * parent's "snapshots" by default to the argument's parentFiles 
	 * and snapshots arrayLists unless the file is marked or the same file is
	 * in the staging area(in which the modified file is tracked and not the ancestor's)
	 * 
	 * @param c
	 * 		a new CommitNode created when the user calls commit		
	 * 
	 */
	public void pathsFromParent(CommitNode c) {

		for (int j = 0; j < c.parentNode.parentFiles.size(); j ++) {                              
			if (stagedList.contains(c.parentNode.parentFiles.get(j)) || markedPaths.contains(c.parentNode.parentFiles.get(j))) {
				c.parentFiles.remove(c.parentNode.parentFiles.get(j));
				c.snapshots.remove(c.parentNode.snapshots.get(j));
			}
			else {
				c.parentFiles.add(c.parentNode.parentFiles.get(j));
				c.snapshots.add(c.parentNode.snapshots.get(j));
			}
		}
		for (int i = 0; i < c.parentNode.pathsToFiles.size(); i++) {
			if (!markedPaths.contains(c.parentNode.pathsToFiles.get(i)) && !stagedList.contains(c.parentNode.pathsToFiles.get(i)) && !c.parentFiles.contains(c.parentNode.pathsToFiles.get(i)) ) {

				c.snapshots.add(0, Paths.get(".gitlet","d" + c.parentNode.id, c.parentNode.pathsToFiles.get(i)).toString());
				c.parentFiles.add(0, c.parentNode.pathsToFiles.get(i));		
			}
		}
	}
	/**
	 * A function for the creation of the first default 
	 * commitNode when init() is called
	 * Its parentNode is null.
	 * @return CommitNode
	 * 			the first default commitNode
	 */
	public CommitNode comm() {
		CommitNode c = new CommitNode(null, "initial commit", counter);
		glog.add(c);
		return c;
	}
	/**
	 * Function called when user inputs the commit command.
	 * Creates a new commitNode and makes a corresponding
	 * commit directory where the files reside.
	 * -calls the helper function: moveFilesOver and pathsFromParent
	 * @param msg
	 * 		a string the user inputs to describe the commit
	 */
	public void commit(String msg) {
		if (stagedList.isEmpty()) {
			System.out.println("No changes added to the commit.");
			return;
		}
		CommitNode c = new CommitNode(curr, msg, counter);
		pathsFromParent(c);
		if (counter == 1) {
			currBranch = "master";
			branchNames.add(currBranch);
		}

		glog.add(c);
		File dir = new File(".gitlet", "d" + counter);
		dir.mkdirs();
		moveFilesOver(c);
		counter++;

		curr = c;
		currPointer.pointer = curr;

		stagedList.clear();
		markedPaths.clear();
		if (conflicted){
			conflicted = false;
		}
	}
	/**
	 * A helper function for the status method. 
	 * Iterates through a given arrayList and prints 
	 * the objects in the list. Prints an "*" next to the
	 * current Branch.
	 * @param a
	 * 		any ArrayList 
	 */
	public void print(ArrayList a) {
		for (int i = 0; i < a.size(); i++) {
			Object o = a.get(i);
			if (o.equals(currBranch)) {
				System.out.println("*" + o);
			} else {
				System.out.println(o);
			}
		}
	}
	/**
	 * Function called when user inputs the status command.
	 * Displays the branches, staged files, and files marked
	 * for untracking in a readable format.
	 * -calls the print helper method
	 */
	public void status() {
		System.out.println("=== Branches ===");
		print(branchNames);
		System.out.println();
		System.out.println("=== Staged Files ===");
		print(stagedList); 
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		print(markedPaths);
	}
	/**
	 * A helper function for the log method
	 * Prints the name of the commitNode(e.g. Commit 1),
	 * the time of the commit, and the message of the commit
	 * @param c
	 * 		a CommitNode
	 */
	public static void logPrint(CommitNode c) {
		System.out.println();
		System.out.println("===");
		System.out.println(c.nameOfNode);
		System.out.println(c.time);
		System.out.println(c.message);

	}
	/**
	 *Function called when user inputs the log command. 
	 *-Iterates through the branch of the current node 
	 *and prints name, time and message of all the nodes
	 *in the branch(current node is the head of the branch)
	 *-calls the logPrint helper method
	 */
	public void log() { 
		if (curr.parentNode != null) {
			CommitNode logpointer = new CommitNode(curr); 

			logpointer = logpointer.pointer;
			while (logpointer != null) {

				logPrint(logpointer);
				logpointer = logpointer.parentNode;
			}
		} else {
			return;
		}
	}
	/**
	 * Function called when user inputs the global-log command. 
	 * Prints the name, time, and message of all the 
	 * commits ever made.
	 * -calls the logPrint helper method.
	 */
	public void globalLog() { 
		for (int i = 0; i < glog.size(); i++) {
			logPrint(glog.get(i));
		}
	}
	/**
	 * Function called when user inputs the find command. 
	 * Prints out the id of the commit that has the argument's message.
	 * If there are multiple such commits, it prints all ids on separate lines.
	 * If none are found, prints the corresponding error message.
	 * @param msg
	 * 		the commit message 
	 */
	public void find(String msg) {
		boolean exist = false;
		for (int i = 0; i < glog.size(); i++) {
			if (msg.equals(glog.get(i).message)) {
				System.out.println("Commit " + i);
				exist = true;
			}
		}
		if (!exist) {
			System.out.println("Found no commit with that message.");
			return;
		}
	}
	/**
	 * Function called when user inputs the rm [filename] command.
	 * Marks the given file for untracking or if the file has been
	 * staged, removes the file from the staging-area.
	 *  
	 * @param filename
	 * 			a string of the path of a file
	 */
	public void rm(String filename) {
		String justFile = Paths.get(filename).getFileName().toString();

		if (!stagedList.contains(justFile) && (markedPaths.contains(justFile))) {
			System.out.println("No reason to remove the file.");
		} else if (stagedList.contains(justFile)) {
			stagedList.remove(justFile);
			Path pathToFile = Paths.get(".gitlet", "staging-area", justFile);
			try {
				Files.delete(pathToFile);

			} catch (IOException e) {
				System.out.println(e);
			}
		} else {
			markedPaths.add(justFile);

		}
	}
	/**
	 * Function called when user inputs the branch command.
	 * Creates a new branch pointer.
	 * @param branchName
	 * 			a string that represents the newly created branch name
	 */
	public void branch(String branchName) {
		if (branchNames.contains(branchName)) {
			System.out.println("A branch with that name already exists.");
		} else {
			CommitNode b = new CommitNode(curr, branchName);
			branchNames.add(branchName);
			branchObj.add(b);
		}
	}
	/**
	 * Function called when user inputs the rm-branch command.
	 * Removes a branch with the given branchName
	 * @param branchName
	 * 		a string that represents the name of a particular branch
	 */
	public void rmbranch(String branchName) {
		if (!branchNames.contains(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (branchName.equals(currBranch)) {
			System.out.println("Cannot remove the current branch.");
			return;
		}
		branchObj.remove(branchNames.indexOf(branchName));
		branchNames.remove(branchName);
	}
	/**
	 * Function called when user inputs the add command.
	 * Adds a copy of the given file to the staging-area.
	 * Also unmarks the file if it has been marked for untracking.
	 * @param FileName
	 * 			a string that represents the name of a file
	 */
	public void add(String FileName) { 
		Path stagingPath = Paths.get(".gitlet", "staging-area");
		if (!new File(FileName).isFile()) {
			System.out.println("File does not exist");
		} else if (markedPaths.contains(FileName)) {
			markedPaths.remove(FileName);
		} else {
			Path pathToFile = Paths.get(FileName);
			try {
				Files.copy(pathToFile,
						stagingPath.resolve(pathToFile.getFileName()));

				stagedList.add(pathToFile.getFileName().toString());
				seenWorkingPaths.put(pathToFile.getFileName().toString(),
						FileName);
			} catch (IOException e) {
			}
		}
	}
	/**
	 * Function called by checkout(String fileorBranchName) method when fileorBranchName is a branch name.
	 * Checks out the files in the given branch's head node to the working directory by
	 * iterating through and calling checkout(file) on all the node's files.
	 * This also changes the current pointer to the given branch.
	 * -calls checkout(String fileorBranchName)
	 *
	 * @param branchName
	 * 			a string that represents the name of the branch
	 */
	public void checkoutBranch(String branchName) {
		if (conflicted) {
			System.out.println("Cannot do this command until the merge conflict has been resolved."); 
			return;
		} else {
			for (int i = 0; i < branchObj.size(); i++) {
				if (branchName.equals(branchObj.get(i).name)) {
					currPointer = branchObj.get(i);
					curr = currPointer.pointer;
					for (int j = 0; j < curr.pathsToFiles.size(); j++) {
						checkout(curr.pathsToFiles.get(j));
					}
					for (int k = 0; k < curr.parentFiles.size(); k++) {
						if (!curr.pathsToFiles
								.contains(curr.parentFiles.get(k))) {
							checkout(curr.parentFiles.get(k));
						}
					}
				}
			}
			currBranch = branchName;
		}
	}
	/**
	 * Function called when user inputs the checkout [filename] or checkout [branch] command.
	 * If the argument is a branchName, calls the checkoutBranch(String branchName) method.
	 * If the argument is a fileName, checks out the given file in the current directory to 
	 * the working directory.
	 * -potentially calls checkoutBranch(String branchName)
	 * 
	 * @param fileorBranchName
	 * 			a string that represents either the branch of filename
	 */
	public void checkout(String fileorBranchName) { 
		Path pathToCommit;
		Path pathToDestination = Paths.get(fileorBranchName);

		if (branchNames.contains(fileorBranchName)) {
			checkoutBranch(fileorBranchName); 
		} else {
			String justFile = pathToDestination.getFileName().toString();
			if (pathToDestination.getNameCount() > 1) {
				fileorBranchName = justFile;
			} else {
				pathToDestination = Paths.get(seenWorkingPaths.get(fileorBranchName));
			}
			if (!curr.pathsToFiles.contains(fileorBranchName)&& !curr.parentFiles.contains(fileorBranchName)) {
				System.out.println("File does not exist in the most recent commit, or no such branch exist.");
				return;
			}
			if (curr.parentFiles.contains(fileorBranchName)&& !curr.pathsToFiles.contains(fileorBranchName)) {
				pathToCommit = Paths.get(curr.snapshots.get(curr.parentFiles.indexOf(fileorBranchName)));
			} else {
				pathToCommit = Paths.get(".gitlet", "d" + curr.id,fileorBranchName);
			}
			try {

				Files.copy(pathToCommit, pathToDestination,StandardCopyOption.REPLACE_EXISTING);

			} catch (FileAlreadyExistsException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
	/**
	 * Function called when user inputs the checkout [id] [filename] command.
	 * Checks out the file in the commit of the given commit id and filename to
	 * the working directory.
	 * 
	 * @param commitID
	 * 			an int that represents the id number of a commit
	 * @param filename
	 * 			a string that represents the name of a file
	 */
	public void checkout(int commitID, String filename) {
		Path pathToCommit;
		Path pathToDestination = Paths.get(filename);

		String justFile = pathToDestination.getFileName().toString();
		if (pathToDestination.getNameCount() > 1) {
			filename = justFile;

		} else {
			pathToDestination = Paths.get(seenWorkingPaths.get(justFile));
		}

		CommitNode c = glog.get(commitID);
		if (commitID > glog.size() - 1) {
			System.out.println("No commit with that id exists.");
			return;
		}
		if (!c.pathsToFiles.contains(filename)
				&& !c.parentFiles.contains(filename)) {
			System.out.println("File does not exist in that commit");
			return;
		}
		if (c.parentFiles.contains(filename)
				&& !c.pathsToFiles.contains(filename)) {
			pathToCommit = Paths.get(c.snapshots.get(c.parentFiles
					.indexOf(filename)));
		} else {
			pathToCommit = Paths.get(".gitlet", "d" + commitID, filename);
		}

		try {
			Files.copy(pathToCommit, pathToDestination,
					StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e) {
			System.out.println(e);
		}
	}
	/**
	 * Function called when user inputs the reset command.
	 * Moves the current branch pointer to the commitNode of
	 * the given commitID, and checks out all the node's 
	 * files by calling checkout [id] [filename].
	 * -calls checkout [id] [filename]
	 * @param commitID
	 * 			an int that represents the id number of the commit
	 */
	public void reset(int commitID) {
		if (commitID > glog.size() - 1) {
			System.out.println("No commit with that id exists.");
			return;
		}
		curr = glog.get(commitID);
		currPointer.pointer = curr;
		for (String files : curr.pathsToFiles) { 
			checkout(commitID, files);
		}
		for (String files : curr.parentFiles) {
			checkout(commitID, files);
		}
	}
	/**
	 * Function called when user inputs the rebase command.
	 * Makes a copy of the current branch and attaches this copy to 
	 * the head of the given branch. If a file in the given branch 
	 * has been modified since the split point, the changes propagate through
	 * the copies(replay) up until a replayed commit has a version of the file
	 * that has also been modified since the split point.
	 * @param branchName 
	 *          a string that represents the name of a branch
	 * 
	 */
	public void rebase(String branchName) {	
		if (!branchNames.contains(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (currBranch.equals(branchName)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}		               
		CommitNode g = branchObj.get(branchNames.indexOf(branchName)).pointer;		
		
		CommitNode baseCheck = new CommitNode(curr);         		
			while (baseCheck.pointer != null) {
				if (baseCheck.pointer == (g.pointer)) {
					System.out.println("Already up-to-date.");
					return;
						}
					baseCheck.pointer = baseCheck.pointer.parentNode;
				}						
		CommitNode splitPoint = splitPoint(currPointer.pointer, g);
		CommitNode branchCopy = replayMaker(splitPoint, g, curr.id+1); 
		
		ArrayList<String> filesToCompare = new ArrayList<String>();
		ArrayList<String> pFilesToCompare = new ArrayList<String>();
		ArrayList<String> snapsToCompare = new ArrayList<String>();
		
		for (String f : splitPoint.pathsToFiles) {
			Path sFile = Paths.get(".gitlet", "d" + splitPoint.id, f);
			if (g.pathsToFiles.contains(f)) {			
				Path gFile = Paths.get(".gitlet", "d" + g.id, f);
				if (!equalContents(sFile, gFile)) {
					filesToCompare.add(f);
				}
			} else if (g.parentFiles.contains(f)) {
				Path gPFile = Paths.get(g.snapshots.get(g.parentFiles.indexOf(f)));			
				if (!equalContents(sFile, gPFile)) {
					pFilesToCompare.add(f);
					snapsToCompare.add(gPFile.toString());
				}	
			}
		}
		for (String f : splitPoint.parentFiles) {
			Path sPFile = Paths.get(splitPoint.snapshots.get(splitPoint.parentFiles.indexOf(f)));			
			if (g.pathsToFiles.contains(f)) {
				Path gFile = Paths.get(".gitlet", "d" + g.id, f);
				if (!equalContents(sPFile, gFile)) {
					filesToCompare.add(f);					
				}					
			}else if (g.parentFiles.contains(f)) {
				Path gPFile = Paths.get(g.snapshots.get(g.parentFiles.indexOf(f)));
				if (!equalContents(sPFile, gPFile)) {
					pFilesToCompare.add(f);
					snapsToCompare.add(gPFile.toString());
					}
			}
		}
		CommitNode replayPointer = new CommitNode(glog.get(curr.id+1));
		replayPointer.pathsToFiles = filesToCompare;
		replayPointer.parentFiles = pFilesToCompare;
		replayPointer.snapshots = snapsToCompare;

		compareToReplays(splitPoint, replayPointer, g);
		currPointer.pointer = branchCopy;
		curr = branchCopy;
	}

	/**A helper method called rebase.
	 * Compares replays to splitnode and given branch so that changes can propagate though the replay.
	 * @param s
	 * 		a CommitNode that represents the splitPoint then just helps iterate during recursion.
	 * @param g 
	 * 		a CommitNode that represents the head of a branch given by rebase.
	 * @param p
	 * 		a replay pointer.
	 * @return CommitNode that is the splitNode of two branches.
	 * 
	 */
	private Object compareToReplays(CommitNode s, CommitNode p, CommitNode g) {
		
		for (String f : p.pathsToFiles){
			if (p.pointer.pathsToFiles.contains(f)){	
				Path sFile = Paths.get(".gitlet", "d" + s.id, f);
				if (s.pathsToFiles.contains(f)){					
				}else if (s.parentFiles.contains(f)){
					sFile = Paths.get(s.snapshots.get(s.parentFiles.indexOf(f)));
				}
					Path pFile = Paths.get(".gitlet", "d" + p.pointer.id, f);
					if (!equalContents(sFile, pFile)) {
						p.pathsToFiles.remove(f); 
					}else{
						Path gFile = Paths.get(".gitlet", "d" + g.id, f);
						try {
							Files.copy(gFile, pFile,StandardCopyOption.REPLACE_EXISTING);
						}catch(Exception e){
							System.out.println(e);
						}
					}
			}else if (p.pointer.parentFiles.contains(f)){
				Path sFile = Paths.get(".gitlet", "d" + s.id, f);
				if (s.pathsToFiles.contains(f)){					
				}else if (s.parentFiles.contains(f)){
					sFile = Paths.get(s.snapshots.get(s.parentFiles.indexOf(f)));
				}
					Path pFile = Paths.get(p.pointer.snapshots.get(p.pointer.parentFiles.indexOf(f)));
				if (!equalContents(sFile, pFile)) {
					p.pathsToFiles.remove(f); 
				}else{				
					p.pointer.snapshots.remove(p.pointer.parentFiles.indexOf(f));
					p.pointer.parentFiles.remove(f);
				}
			}
		}
		for (String f : p.parentFiles){
			if (p.pointer.pathsToFiles.contains(f)){	
				Path sFile = Paths.get(".gitlet", "d" + s.id, f);
				if (s.pathsToFiles.contains(f)){					
				}else if (s.parentFiles.contains(f)){
					sFile = Paths.get(s.snapshots.get(s.parentFiles.indexOf(f)));
				}
					Path pFile = Paths.get(".gitlet", "d" + p.pointer.id, f);
					if (!equalContents(sFile, pFile)) {
						p.parentFiles.remove(f);
					}else{
						p.pointer.pathsToFiles.remove(f);
						try{
							Files.delete(pFile);
						}catch(Exception e){
							System.out.println(e);
						}
					}
			}else if (p.pointer.parentFiles.contains(f)){
				Path sFile = Paths.get(".gitlet", "d" + s.id, f);
				if (s.pathsToFiles.contains(f)){					
				}else if (s.parentFiles.contains(f)){
					sFile = Paths.get(s.snapshots.get(s.parentFiles.indexOf(f)));
				}
					Path pFile = Paths.get(p.pointer.snapshots.get(p.pointer.parentFiles.indexOf(f)));
				if (!equalContents(sFile, pFile)) {
					p.parentFiles.remove(f);
				}else{				
					p.pointer.snapshots.remove(p.pointer.parentFiles.indexOf(f));
					p.pointer.snapshots.add(p.pointer.parentFiles.indexOf(f), p.snapshots.get(p.parentFiles.indexOf(f)));
					
				}
			}
		}
		if (p.pointer.id==glog.size()-1){
			return null;
		}else{
		p.pointer = glog.get(p.pointer.id+1);
		return compareToReplays(s, p, g);
		}
	}
	/**A helper method called by rebase.
	 * Makes a copy of the current branch and attaches it to rebases given branch.
	 * @param s
	 * 		a CommitNode that helps iterate through nodes
	 * @param cp 
	 * 		a CommitNode that represents copies
	 * @param newID
	 * 		an integer that helps set new ID's.
	 * @return CommitNode that is the splitNode of two branches.
	 * 
	 */
	
	private CommitNode replayMaker(CommitNode s, CommitNode cp, int newID){ 
		if (s==curr){
			return cp;
		}
		CommitNode copyPointer = new CommitNode(curr);	
		while (copyPointer.pointer.parentNode!=s){
			copyPointer.pointer= copyPointer.pointer.parentNode;
		}		
		CommitNode copy = new CommitNode(cp, copyPointer.pointer.message, newID);
		copy.parentFiles.addAll(copyPointer.pointer.parentFiles);
		copy.snapshots.addAll(copyPointer.pointer.snapshots);		
		
		if (copyPointer.pointer.pathsToFiles!= null){
			File dir = new File(".gitlet", "d" + copy.id);
			dir.mkdirs();
			copy.pathsToFiles.addAll(copyPointer.pointer.pathsToFiles);
			for (String file : copyPointer.pointer.pathsToFiles){
				Path comFile = Paths.get(".gitlet", "d" + copyPointer.pointer.id, file);
				Path copyDir = Paths.get(dir.toString(),file);		
				try {
					Files.copy(comFile, copyDir,
						StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
		glog.add(copy);	
		return replayMaker(copyPointer.pointer, copy, newID+1);
	}
	/**
	 * A helper function that compares two given files by their paths
	 * @param s
	 * 		a Path of a file
	 * @param p
	 * 		a Path of a file
	 * @return true if the two files given by the Paths have equal content
	 * -called by merge, rebase
	 * 
	 */
	private boolean equalContents(Path s, Path p) {
		try {		
			if (Files.readAllLines(s).equals(Files.readAllLines(p))) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}		
	}
	/**Function called when user inputs the merge command.
	 * Merges files from the given branch into the current branch.
	 * Any files that have been modified in the given branch but 
	 * not the current branch after the split node are automatically staged.
	 * Conflicts arise when a file has been modified in both branches
	 * since the split point and the version of the file from the given 
	 * branch is copied with the name [old file name].conflicted.
	 * Puts Gitlet in a conflicted state.
	 * @param branch
	 * 			a string that represents the name of the given branch.
	 * 
	 */
	public void merge(String branch) {
		if (!branchNames.contains(branch)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (currBranch.equals(branch)) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}
		CommitNode g = branchObj.get(branchNames.indexOf(branch)).pointer;
		CommitNode s = splitPoint(currPointer.pointer, g);
		CommitNode c = currPointer.pointer;
		for (String f : s.pathsToFiles) {
			Path sFile = Paths.get(".gitlet", "d" + s.id, f);
			
			if (g.pathsToFiles.contains(f)) {				
				Path gFile = Paths.get(".gitlet", "d" + g.id, f);
				if (!equalContents(sFile, gFile)) {
					if (!c.pathsToFiles.contains(f)&& !c.parentFiles.contains(f)) {
						checkout(g.id, f);
						add(seenWorkingPaths.get(f));
					} else {
						pathCompare(sFile, gFile, c, g, f);
					}
				}
			} else if (g.parentFiles.contains(f)) {
				Path gPFile = Paths.get(g.snapshots.get(g.parentFiles
						.indexOf(f)));
				if (!equalContents(sFile, gPFile)) {
					if (!c.pathsToFiles.contains(f)&& !c.parentFiles.contains(f)) {
						checkout(g.id, f);
						add(seenWorkingPaths.get(f));
					} else {
						pathCompare(sFile, gPFile, c, g, f);
					}
				}
			} else { 
				rm(f);
			}
		}
		for (String f : s.parentFiles) {
			Path sPFile = Paths.get(s.snapshots.get(s.parentFiles.indexOf(f)));
			
			if (g.pathsToFiles.contains(f)) {
				Path gFile = Paths.get(".gitlet", "d" + g.id, f);
				
				if (!equalContents(sPFile, gFile)) {
					if (!c.pathsToFiles.contains(f)&& !c.parentFiles.contains(f)) {
						checkout(g.id, f);
						add(seenWorkingPaths.get(f));
					} else {
						pathCompare(sPFile, gFile, c, g, f);
					}
				}
			} else if (g.parentFiles.contains(f)) {
				Path gPFile = Paths.get(g.snapshots.get(g.parentFiles.indexOf(f)));
				if (!equalContents(sPFile, gPFile)) {
					if (!c.pathsToFiles.contains(f)&& !c.parentFiles.contains(f)) {
						checkout(g.id, f);
						add(seenWorkingPaths.get(f));
					} else {
						pathCompare(sPFile, gPFile, c, g, f);
					}
				}
			} else { 
				rm(f);
			}
		}
		if (!conflicted) {
			commit("Merged " + currPointer.name + " with " + branch + ".");
		}else{
			System.out.println("Encountered a merge conflict.");
			return;
		}
	}
	/**
	 * A helper function called by merge. Compares the contents of files given by Paths
	 * and puts Gitlet in a conflicted state if the file contents differ. If set to 
	 * a conflicted state, copies the file of path g to the working directory.
	 * -called by merge  
	 * 
	 * @param s
	 * 		a path to a file
	 * @param g
	 * 		a path to a file
	 * @param c
	 * 		a CommitNode 
	 * @param gc
	 * 		a CommitNode
	 * @param f
	 * 		a string that represents a filename
	 */
	private void pathCompare(Path s, Path g, CommitNode c, CommitNode gc, String f) {
		boolean conflict = false;
		if (c.pathsToFiles.contains(f)) {
			Path cFile = Paths.get(".gitlet", "d" + c.id, f);
			if (!equalContents(cFile, s)) {
				conflict = true;
			}else{
				checkout(gc.id, f);
				add(seenWorkingPaths.get(f));
			}
		}
		if (c.parentFiles.contains(f)) {
			Path cPFile = Paths.get(c.snapshots.get(c.parentFiles.indexOf(f)));
			if (!equalContents(cPFile, s)) {
				
				conflict = true;
			}else{
				checkout(gc.id, f);
				add(seenWorkingPaths.get(f));
			}
		}
		if (conflict) {
			conflicted = true;
			Path wp = Paths.get(seenWorkingPaths.get(f) + ".conflicted");
			try {
				Files.copy(g, wp, StandardCopyOption.REPLACE_EXISTING); 
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	/**A helper method called by merge and rebase.
	 * Finds the splitNode(common node) of two branches.
	 * @param c
	 * 		a CommitNode that represents the head of a branch
	 * @param g 
	 * 		a CommitNode that represents the head of a branch
	 * @return CommitNode that is the splitNode of two branches.
	 * 
	 */
	private CommitNode splitPoint(CommitNode c, CommitNode g) {
		if (c.id > g.id) {
			return splitPoint(c.parentNode, g);
		} else if (c.id < g.id) {
			return splitPoint(c, g.parentNode);
		} else if (c.id == g.id) {
			return c;
		}
		return null;
	}

	public static void main(String[] args) {
		Gitlet g = deserialize();
		if (args.length == 0) {
			System.out.println("Please enter a command.");	
		}else if (args[0].equals("init")) {
			g = new Gitlet(); 	  
		} else if (args[0].equals("add")) {
			g.add(args[1]);
		} else if (args[0].equals("log")) {
			g.log();
		} else if (args[0].equals("global-log")) {
			g.globalLog();
		} else if (args[0].equals("commit")) {
			if (args.length==1){
				System.out.println("Please enter a commit message.");
			}else{
				g.commit(args[1]);
			}
		} else if (args[0].equals("find")) {
			g.find(args[1]);
		} else if (args[0].equals("status")) {
			g.status();
		} else if (args[0].equals("rm")) {
			g.rm(args[1]);
		} else if (args[0].equals("checkout")) {
			if (args.length == 3) {
				int n = Integer.parseInt(args[1]);
				g.checkout(n, args[2]);
			} else {
				g.checkout(args[1]);
			}
		}else if (args[0].equals("branch")) {
			if (g.conflicted) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
			} else {
				g.branch(args[1]);
			}
		} else if (args[0].equals("rm-branch")){
			if (g.conflicted) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
			} else {
				g.rmbranch(args[1]);
			}
		} else if (args[0].equals("reset")) {
			if (g.conflicted) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
			} else {
				int n = Integer.parseInt(args[1]);
				g.reset(n);	
			}
		}else if (args[0].equals("merge")){
			if (g.conflicted) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
			} else {
				g.merge(args[1]);
			}
			}else if (args[0].equals("rebase")){
				if (g.conflicted) {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
				} else {
					g.rebase(args[1]);
				}			
			}else{
			System.out.println("No command with that name exists.");
		}
		serialize(g);
	}

}
