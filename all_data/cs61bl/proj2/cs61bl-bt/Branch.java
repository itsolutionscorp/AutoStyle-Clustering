import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;

import static java.nio.file.StandardOpenOption.*;

public class Branch implements Serializable {

	// instance variables
	private String name;
	private int branchId;
	private boolean currentBranch = false;
	private Commit myHead;
	// private static ArrayList<String> allBranchNames = new
	// ArrayList<String>();
	// private static ArrayList<Branch> allBranches = new ArrayList<Branch>();

	/**
	 * 
	 * creates a new branch with given name and points it at the current head
	 * commit. before branch is ever called, there should be a default branch
	 * called "master"
	 */

	public Branch(String name) {
		if (Gitlet.allBranches.contains(name)) {
			System.out.println("A branch with that name already exists.");
			return;
		}
		this.name = name;
		currentBranch = false;
		// set to point at the current head node in commit tree
		if (Gitlet.currentB != null) {
			myHead = Gitlet.currentB.returnCommits();
		} else {
			myHead = Gitlet.mostRecent;
		}
		Gitlet.allBranchNames.add(name);
		Gitlet.allBranches.add(this);
	}

	/**
	 * @return the name of this branch
	 */
	public String returnName() {
		return name;
	}

	/**
	 * @return true if this branch is the current branch in use
	 */
	public boolean isCurrent() {
		return currentBranch;
	}

	/**
	 * Sets the first element of this branch
	 * 
	 * @param c 
	 * 			the Commit to be the head of this branch
	 */
	public void setHead(Commit c) {
		myHead = c;
	}

	/**
	 * Sets the branch as no longer current if it is 
	 */
	public void removeCurrentBranch() {
		currentBranch = false;
	}
	
	public void setBranchId(int id) {
		this.branchId = id;
	}


	/** 
	 * Finds the last common parent shared by this branch and given branch
	 * 
	 * @return Id of the Commit at the split point of this branch and given branch
	 */
	public int findSplitId(String branchName) {
		if (branchName != null) {

		}
		return 0;
	}

	/**
	 * Gets the head Commit of this branch
	 */
	public Commit returnCommits() {
		return myHead;
	}

	/**
	 * Sets this branch as the current branch
	 */
	public void setCurrentBranch() {
		currentBranch = true;
		Gitlet.currentB = this;
	}

	/**
	 * Checks out the branch with given name, replacing all files
	 * in the working directory with the version in the head of this branch.
	 *
	 * @param name
	 * 			the name of the branch to be checked out
	 */
	public static void checkoutBranch(String name) {

		if (Gitlet.currentB.returnName().equals(name)) {
			System.out.println("No need to checkout the current branch.");
			return;
		}
		int index = Gitlet.allBranchNames.indexOf(name);
		Branch myB = Gitlet.allBranches.get(index);

		Commit headC = myB.returnCommits();
		HashMap<String, FileObject> files = headC.getFiles();
		for (String key : files.keySet()) {
			myB.checkoutFile(key, myB);
		}
		for (Branch b : Gitlet.allBranches) {
			b.removeCurrentBranch();
		}
		myB.setCurrentBranch();

	}

	/**
	 * Checks out a file from the head of current branch.
	 * 
	 * @param name
	 * 			the name of the file to be checked out
	 * @param cb
	 * 			the current branch
	 */
	public void checkoutFile(String name, Branch cb) {

		Commit headC = cb.returnCommits();
		HashMap<String, FileObject> files = headC.getFiles();

		if (files.containsKey(name)) {
			String fileName = FileObject.extractFileName(name);
			String folderPaths = FileObject.extractFolders(name);
			makeDirectory(folderPaths, new String());
			Path pathToFile = Paths.get(headC.getFileDir().get(headC.getFiles().get(name)));
			File dest = new File(name);
			if (dest.exists()){
				addFilesToReset(folderPaths, fileName, pathToFile, true);
			} else{
				addFilesToReset(folderPaths, fileName, pathToFile, false);
			}
		} else {
			System.out.println("File does not exist in the most recent commit, or no such branch exists.");
		}

	}

	/**
	 * Checks out a file from the Commit with given ID.
	 * 
	 * @param id
	 * 			the ID of Commit to get the file from
	 * @param name
	 * 			the name of file to be checked out
	 */
	public static void checkoutFileinCommit(int id, String name) {
		if (Commit.commitExists(id) == true) {
			Commit c = Commit.returnCommit(id);
			HashMap<String, FileObject> files = c.getFiles();
			if (files.containsKey(name)) {
				String fileName = FileObject.extractFileName(name);
				String folderPaths = FileObject.extractFolders(name);
				makeDirectory(folderPaths, new String());
				Path pathToFile = Paths.get(c.getFileDir().get(c.getFiles().get(name)));
				File dest = new File(name);
				if (dest.exists()){
					addFilesToReset(folderPaths, fileName, pathToFile, true);
				} else{
					addFilesToReset(folderPaths, fileName, pathToFile, false);
				}
			} else {
				System.out.println("File does not exist in that commit.");
				return;
			}
		} else {
			System.out.println("No commit with that id exists.");
			return;
		}
	}
	
	/**
	 * Prints the current state of Gitlet, with all branches, a list of
	 * files in the staging area, and a list of files that are
	 * marked for untracking. The current branch is marked with a *.
	 */
	public static void printStatus() {
		System.out.println("=== Branches ===");
		for (Branch b : Gitlet.allBranches) {
			if (b.isCurrent())
				System.out.print("*");
			System.out.println(b.name);
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		for (String file : Gitlet.staged2.keySet()) {
			System.out.println(file);
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (String s : Gitlet.untracked) {
			System.out.println(s);
		}
	}
	
	/**
	 * Removes the branch with given name so that it can no longer be
	 * referenced.
	 * 
	 * @param name
	 *            The name of branch to be removed
	 */
	public static void rmBranch(String name) {
		if (!Gitlet.allBranchNames.contains(name)) {
			System.out.println("A branch with that name does not exist.");
		} else {
			Branch rmBranch = null;
			for (Branch b : Gitlet.allBranches) {
				if (b.returnName().equals(name))
					if (b.isCurrent()) {
						System.out.println("Cannot remove the current branch.");
						return;
					} else {
						rmBranch = b;
						Gitlet.allBranchNames.remove(name);
					}
			}
			Gitlet.allBranches.remove(rmBranch);
		}
	}
	
	/**
	 * Finds the Commit at the split point of this branch and the given branch.
	 * 
	 * @param name
	 *            the name of branch whose split point with the current branch
	 *            will be found
	 */
	public Commit findSplitPointCommit(String name) {
		Commit c1 = Gitlet.currentB.returnCommits();
		int index = Gitlet.allBranchNames.indexOf(name);
		Branch branch2 = Gitlet.allBranches.get(index);
		Commit c2 = branch2.returnCommits();
		while (c1.getParentCN() != null) {
			while (c2.getParentCN() != null){
				if (c1.getID() == c2.getID()){
					return c2;
				}
				c2 = c2.getParentCN().item();
			}
			c2 = branch2.returnCommits();
			c1 = c1.getParentCN().item();
		}
		return c1;
	}
	
	/**
	 * Adds files to the working directory when a valid reset command is called.
	 * This essentially checkouts the commit.
	 * 
	 * @param folderPaths
	 *            the file paths of where the file is supposed to go in the
	 *            working directory (but not including the file name)
	 * @param name
	 *            the name of the file, and only the file to be added to the
	 *            working directory
	 * @param pathToFile
	 *            the complete path of where the file is stored
	 * @param x
	 *            tells whether a file with the same name exists. If it does, it
	 *            erases the file.
	 */
	public static void addFilesToReset(String folderPaths, String name, Path pathToFile, boolean x){
		FileObject stagedCopy = null;
		if (folderPaths.length() == 0){
			stagedCopy = new FileObject(name);
		} else {
			stagedCopy = new FileObject(folderPaths +"/"+ name);
		}

		try {
			if (x == true){
				Files.delete(stagedCopy.toPath());
			}
			Files.copy(pathToFile, stagedCopy.toPath());
		} catch (IOException e) {
		}
	}
	
	
	/**
	 * Goes through the given path and creates the path if it does not exist.
	 * 
	 * @param path
	 *            the file path that is created at the end of this function
	 * @param workingDirectory
	 *            String representation of the working directory
	 */
	public static void makeDirectory(String path, String workingDirectory){
		ArrayList<String> folders = extractFolderAndFile(path);
		String workingDir = workingDirectory;
		for (int i = 0; i < folders.size(); i++){
			File dir = new File (workingDir + "/" + folders.get(i));
			if (dir.exists()){
				workingDir = workingDir + "/" + folders.get(i);
			} else{
				dir.mkdir();
				workingDir = workingDir + "/" + folders.get(i);
			}
		}
	}

	
	/**
	 * Creates an ArrayList where each folder in the given path is stored in an
	 * element. The more general the folder is, the larger its index number will
	 * be.
	 * 
	 * @param path
	 *            the file path that will be broken up and stored into an array.
	 * @return an ArrayList consisting of file directory names.
	 */
	public static ArrayList<String> extractFolderAndFile(String path){
		ArrayList<String> folderFileName = new ArrayList<String>();
		int pathLength = path.length();
		while (pathLength > 0){
			int lastSlashInd = path.lastIndexOf("/");
			folderFileName.add(path.substring(lastSlashInd+1));
			if (lastSlashInd > 0){
				path = path.substring(0, lastSlashInd);
			}
			pathLength = lastSlashInd - 1;
		}
		return folderFileName;
	}
	

}
