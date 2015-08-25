import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CommitList implements Serializable {

	private CommitNode currentHead;
	
	private String currentBranch;
	private HashMap<String, CommitNode> branchMap; // map branch names to
													// headCommits of each
													// branch
	private HashMap<String, Integer> branchLevels = new HashMap<String,Integer>();

	private HashMap<String, ArrayList<CommitNode>> allNodes;
	private boolean conflictedState;
	public HashMap<String, File> staging; 
	private int commitCount;
	private HashMap<Integer, CommitNode> IDNodes;

	private HashMap<String, File> tracking;
	private HashMap<String, File> untracking; // The key is the the fileName,
												// and the value is the file
												// itself.
												// If the fileName is stored
												// here, then the file is being
												// untracked
												// Add and remove methods are
												// able to alter this HashMap
	

	
	CommitList() {
		branchMap = new HashMap<String, CommitNode>();
		allNodes = new HashMap<String, ArrayList<CommitNode>>();
		conflictedState = false;
		staging = new HashMap<String, File>();
		untracking = new HashMap<String, File>();
		tracking = new HashMap<String, File>();
		IDNodes = new HashMap<Integer, CommitNode>();
		branchLevels.put("master", 0);
		currentBranch = "master";
	}

	/**
	 * returns branchMap HashMap
	 * 
	 */
	public HashMap<String, CommitNode> getBranches() {
		return branchMap;
	}

	/**
	 * returns the name of currentBranch
	 * 
	 */
	public String currentBranch(){
		return currentBranch;
	}

	/**
	 * returns the branchLevel of the given branch
	 * 
	 *  
	 */
	public int getBranchLevel(String branchName) {
		return branchLevels.get(branchName);
	}
	
	/**
	 * initializing the .gitlet directory, and creates an empty commit in that directory
	 * A staging folder is also created in  .gitlet
	 * @throws IOException
	 */
	public void init() throws IOException {

		File git = new File(".gitlet");
		if (git.exists()) {
			System.out.println("A gitlet version control system already exists in the current directory.");
			return;
		}
		git.mkdir();

		commit("initial commit", true);


		File staging = new File(".gitlet/Staging");
		staging.mkdir();
		
	}

	/**
	 * Creates a commit with the given message. There must be some file in the staging area to commit
	 * Files that are being tracked are also included in the new commit
	 * A new commit folder is also created in the .gitlet folder. However, only files that were in the staging
	 * area are included in the new commit folder.
	 * The staging area is cleared at the end of this method
	 * @param message - this commit's message
	 * @param initialCommit - whether this commit is called by init()
	 * @throws IOException
	 */
	public void commit(String message, boolean initialCommit)
			throws IOException {

		if (staging.size() == 0 && !initialCommit) {
			System.out.println("No changes added to the commit");
			return;
		}
		
		if (message == null) {
			System.out.println("Please enter a commit message.");
			return;
		}

		CommitNode newHead = new CommitNode(message, commitCount, currentBranch, branchLevels.get(currentBranch));
		System.out.println(commitCount);
		IDNodes.put(commitCount, newHead);
		commitCount++;

		if (!initialCommit) {
			newHead.setParent(currentHead);
			branchMap.put(currentBranch, newHead);
		} else {
			branchMap = new HashMap<String, CommitNode>();
			branchMap.put(currentBranch, newHead); // add commitNode to													// branchMap hashMap
		}
		CommitNode oldHead = currentHead; 
		currentHead = newHead;
		if (!allNodes.containsKey(message)) {
			ArrayList<CommitNode> newValue = new ArrayList<CommitNode>();
			newValue.add(newHead);
			allNodes.put(message, newValue);
		} else {
			ArrayList<CommitNode> existingValue = allNodes.get(message);
			existingValue.add(newHead);
		}

		File newCommit = new File(".gitlet/Commit_" + newHead.ID());
		newCommit.mkdir();

		// Copy files from staging area to the new commit
		for (String fileName : staging.keySet()) {
			File commitFile = new File(".gitlet/Commit_" + newHead.ID() + "/" + fileName);
			File stagingFile = staging.get(fileName);
			Path commitLocation = commitFile.toPath();
			Path stagingLocation = stagingFile.toPath();
			Files.createDirectories(commitLocation.getParent());
			Files.copy(stagingLocation, commitLocation);
			currentHead.addFile(fileName, commitFile);
			tracking.put(fileName, commitFile);
			Files.delete(stagingLocation);
		}

		for (String fileName : tracking.keySet()) {
			File currentCommitLocation = new File(".gitlet/Commit_"+ newHead.ID() + "/" + fileName);
			File trackingLocation = tracking.get(fileName);
			if (!currentCommitLocation.exists()) {
				currentHead.addFile(fileName, trackingLocation);
			}
		}
		currentHead.setTracking(tracking);
		staging.clear();
		untracking.clear();
	}

	
	/**
	 * If the specified file is being tracked, then is placed in the staging area
	 * If it is not being tracked, then the method will track it
	 * @param fileName
	 * @throws IOException
	 */
	public void add(String fileName) throws IOException {
		// make sure file object exists in the current location
		File fileToCopy = new File(fileName);
		if (!fileToCopy.exists()) {
			System.out.println("File does not exist.");
			return;
		}
		
		if (untracking.containsKey(fileName)) {
			tracking.put(fileName, untracking.remove(fileName));
			currentHead.setTracking(tracking);
			currentHead.setUntracking(untracking);
		} else {
			File stagingLocation = new File(".gitlet/Staging/" + fileName);
			Files.deleteIfExists(stagingLocation.toPath());
			Files.createDirectories(stagingLocation.toPath().getParent());
			Files.copy(fileToCopy.toPath(), stagingLocation.toPath());
			staging.put(fileName, stagingLocation);
		}
	}

	/**
	 * If the specified fileName is in the staging area, it is removed from the staging area
	 * Otherwise, it is untracked, and will not be included in the next commit
	 * @param fileName
	 */
	public void remove(String fileName) {
		File fileToRemove = new File(".gitlet/Staging/" + fileName);
		if (staging.containsKey(fileName)) {
			System.out.println("was staging");
			staging.remove(fileName);
			fileToRemove.delete();
		} else if (tracking.containsKey(fileName)) {
			System.out.println("was tracking.");
			tracking.remove(fileName);
			untracking.put(fileName, fileToRemove);
			currentHead.setTracking(tracking);
			currentHead.setUntracking(untracking);
		} else {
			System.out.println("No reason to remove the file.");
			return;
		}
		System.out.println("calling remove");
		for (String f : staging.keySet()) {
			System.out.println(f);
		}
	}

	/**
	 * Prints the ID of the commit with the given message
	 * @param message
	 */
	public void find(String message) {
		if (allNodes.get(message) == null) {
			System.out.println("Found no commit with that message.");
			return;
		} else {
			Iterator iter = allNodes.get(message).iterator();
			while (iter.hasNext()) {
				System.out.println(((CommitNode) iter.next()).ID());
			}
		}
	}

	/**
	 * Creates a new branch. This branch will point to the currentHead.
	 * The next commit will still belong to the current branch, i.e. this method does not divert 
	 * control over to the new branch
	 * @param branchName
	 */
	public void branch(String branchName) {
		if(conflictedState) {
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		if (branchMap.containsKey(branchName)) {
			System.out.println("A branch with that name already exists.");
		} else { 
			CommitNode newBranch = currentHead;
			branchMap.put(branchName, newBranch);
			if(currentHead == null){
				System.out.println("what");
			}
			branchLevels.put(branchName, currentHead.branchLevel() + 1);
			branchLevels.put(currentBranch, currentHead.branchLevel() + 1);
		}
	}

	
	/**
	 * Deletes the given branch. This only deletes the reference to the commitNode this branch is currently at
	 * It does not actually delete any commitNodes, files, ect.
	 * @param branchName
	 */
	public void rm_branch(String branchName) {
		if(conflictedState) {
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		
		if (!branchMap.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
		} else if (branchName.equals(currentBranch)) {
			System.out.println("Cannot remove the current branch.");
		} else {
			branchMap.remove(branchName);
		}
		
	}

	/**
	 * Delete all the files in the commit with the given ID
	 * @param ID
	 * @throws IOException
	 */
    public void reset(int ID) throws IOException{
    	if(conflictedState) {
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
    	
        CommitNode commit = IDNodes.get(ID);
        if (commit == null) {
            System.out.println("No commit with that id exists.");
        } else {
            currentHead = commit;
			HashMap<String, File> files = currentHead.myFiles();
			for (String f : files.keySet()) {
				System.out.println(f);
				File file2 = files.get(f);
				File file = new File(f);
				Files.deleteIfExists(file.toPath());
				Files.copy(file2.toPath(), file.toPath());
			}
        }
    }
	


    /**
     * Return information on all nodes that are parents of the currentHead commitNode (including the currentHead)
     */
	public void log() {
		CommitNode temp = currentHead;
		while (temp != null) {
			temp.Print();
			temp = temp.parent();
		}
	}
	
	/**
	 * Returns information on all of the nodes
	 */
	public void globalLog() {
		for(String s: allNodes.keySet()){
			ArrayList<CommitNode> haji = allNodes.get(s);
			for (CommitNode x : haji) {
				x.Print();
			}
		}
	}

	/**
	 * Returns information on all of the existing branches, staged files,  and fiels marked for untracking
	 */
	public void status() {
		System.out.println("=== Branches ===");
		Iterator iter = branchMap.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			if (key.equals(currentBranch)) {
				System.out.print("*");
			}
			System.out.println(key); // Need a branch instance variable accessed
									// by getter method.
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		for (String fileName : staging.keySet()) {
			System.out.println(fileName); // Need to give files a name method
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for(String s: untracking.keySet()){
			System.out.println(s);
		}
	}

	/**
	 * Find the file with the given name in the currentHead commitNode.
	 * Copy this file to the working directory, replacing any file that exists in the working directory
	 * with the same name
	 * @param fileName
	 * @throws IOException
	 */
	public void checkout_filename(String fileName) throws IOException {
		System.out.println("filename checkout");
		HashMap<String, File> files = currentHead.myFiles();
		System.out.println(fileName);
		System.out.println(files.isEmpty());
		if (!files.containsKey(fileName)) {
			System.out.println("File does not exist in the most recent commit, or no such branch exists");
			return;
		}
		System.out.println("All fileNames in current commitNode");
		for(String s: files.keySet()){
			System.out.println(s);
			File f = files.get(s);
			System.out.println(f);
		}
		// return the file in the working directory
		Path sourcePath = files.get(fileName).toPath();
		Path targetPath = Paths.get(fileName);
		recursiveDelete(targetPath.toFile());
		Files.copy(sourcePath, targetPath); // FIXME THROWS IOEXCEPTION
	}
	
	
	/**
	 * Delete the given file/directory. If it's a directory, delete an of its encompassed files/directories
	 * @param d
	 * @throws IOException
	 */
	private static void recursiveDelete(File d) throws IOException {
		if (d.isDirectory()) {
			for (File f : d.listFiles()) {
				recursiveDelete(f);
			}
		}
		if (!d.delete()) {
			throw new IOException("Failed to delete file." + d.getPath());
		}
	}

	/**
	 * Find the file with the given name in the commitNode with the given ID.
	 * Copy this file to the working directory, replacing any file that exists in the working directory
	 * with the same name
	 * @param ID
	 * @param fileName
	 * @throws IOException
	 */
	public void checkout_ID(int ID, String fileName) throws IOException {
		if (!IDNodes.containsKey(ID)) {
			System.out.println("No commit with that id exists.");
			return;
		}
		CommitNode curr = IDNodes.get(ID);
		HashMap<String, File> files = curr.myFiles();
		if (!files.containsKey(fileName)) {
			System.out.println("File does not exist in that commit.");
			return;
		}
		File f = new File(".gitlet/Commit_" + ID + "/" + fileName);
		File sourceFile = files.get(fileName);
		File targetFile = new File(fileName);
		Path sourcePath = sourceFile.toPath();
		Path targetPath = targetFile.toPath();
		Files.deleteIfExists(targetPath);
		Files.copy(sourcePath, targetPath);
	}

	
	public void checkout_branch(String branchname) throws IOException {
		if(conflictedState) {
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		
		if (!branchMap.containsKey(branchname)) {
			System.out.println("File does not exist in the most recent commit, or no such branch exists.");
		} else if (currentBranch.equals(branchname)) {
			System.out.println("No need to checkout the current branch.");
		} else {
			CommitNode curr = branchMap.get(branchname);
			HashMap<String, File> files = curr.myFiles();
			for (String f : files.keySet()) {
				File file2 = files.get(f);
				File file = new File(f);
				Files.deleteIfExists(file.toPath());
				Files.copy(file2.toPath(), file.toPath());
			}
			currentBranch = branchname;
			currentHead = branchMap.get(currentBranch);
		}
	}
	
	


	/**
	 * Find the closes split point (point at which to branches interesect) traversing backwards from
	 * the head of the given branch
	 * @param branchName
	 * @return
	 */
	private CommitNode findSplitPoint(String branchName) {
		CommitNode branchCommit = branchMap.get(branchName);
		HashSet<CommitNode> wholeBranch = new HashSet<CommitNode>();
		while (branchCommit != null) {
			wholeBranch.add(branchCommit);
			branchCommit = branchCommit.parent();
		}
		CommitNode c = currentHead;
		while (c != null) {
			if (wholeBranch.contains(c)) {
				return c;
			}
			c = c.parent();
		}
		System.out.println("No split point found");
		return null;
	}

	/**
	 * Merge two branches together. If there is a conflict bewtween the two branches, enter a conflicted state
	 * 
	 * @param branchName
	 * @throws IOException
	 */
    public void merge(String branchName) throws IOException { 
    	if(conflictedState) {
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
        
    	if (!branchMap.containsKey(branchName)) {
            throw new IllegalArgumentException(
                    " A branch with that name does not exist.");
        }
        if (branchName.equals(currentBranch)) {
            throw new IllegalArgumentException(
                    "Cannot merge a branch with itself.");
        }

        CommitNode splitNode = findSplitPoint(branchName);
        CommitNode branchNode = branchMap.get(branchName);
        if (splitNode == null) {
        	return;
        }
        
        HashMap<String, File> givenbranchModified = findDifferent(branchNode,
                splitNode);

        HashMap<String, File> currModified = findDifferent(currentHead,
                splitNode);
        HashMap<String, File> currUnModified = findSame(currentHead,
                splitNode);

        for (Map.Entry<String, File> pair : currUnModified.entrySet()) {
            if (!branchNode.myFiles().containsKey(pair.getKey())) {
                untracking.put(pair.getKey(), pair.getValue());
                tracking.remove(pair.getKey());
            }
        }

        for (Map.Entry<String, File> pair : givenbranchModified.entrySet()) {
            if (!currModified.containsKey(pair.getKey())) { // modified in the
                                                            // given branch but
                                                            // not modified in
                                                            // the current
                                                            // branch
                checkout_ID(branchNode.ID(), pair.getKey());
                File stagingLocation = new File(".gitlet/Staging/" + pair.getKey());
    			Files.deleteIfExists(stagingLocation.toPath());
    			Files.createDirectories(stagingLocation.toPath().getParent());
    			Files.copy(pair.getValue().toPath(), stagingLocation.toPath());
            } else {
                if (isModified(pair.getValue(),
                        currentHead.myFiles().get(pair.getKey()))) {
                    Path from = pair.getValue().toPath(); // branchNode.myFiles().get(pair.getKey());
                    Path to = Paths.get(pair.getKey() + ".conflicted");
                    if (to.getParent() != null) {
                        Files.createDirectories(to.getParent());
                    }
                    Files.copy(from, to);
                    conflictedState = true;
                }
            }
        }
        if (!conflictedState) {
            commit("Merged " + currentBranch + " with " + branchName, false);
        } else {
            System.out.println("Encountered a merge conflict.");
        }
    }

	/**
	 * Find all of the files that are different between the to given CommitNodes
	 * @param from
	 * @param to
	 * @return
	 * @throws IOException
	 */
    public HashMap<String, File> findDifferent(CommitNode from, CommitNode to)
            throws IOException {
        HashMap<String, File> diff = new HashMap<String, File>();
        for (String fileName : from.myFiles().keySet()) {
            File f = from.myFiles().get(fileName);
            if (to.myFiles().containsKey(fileName)) {
                if (isModified(f, to.myFiles().get(fileName)))
                    diff.put(fileName, f);
            } else {
                diff.put(fileName, f);
            }
        }
        return diff;
    }
    
    /**
     * Find all of the files that are the same between the two given CommitNodes
     * @param from
     * @param to
     * @return
     * @throws IOException
     */
    public HashMap<String, File> findSame(CommitNode from, CommitNode to)
            throws IOException {
        HashMap<String, File> same = new HashMap<String, File>();
        for (String fileName : from.myFiles().keySet()) {
            File f = from.myFiles().get(fileName);
            if (to.myFiles().containsKey(fileName)) {
                if (!isModified(f, to.myFiles().get(fileName)))
                    same.put(fileName, f);
            } else {
                same.put(fileName, f);
            }
        }
        return same;
    }

    /**
	 * Determine if the two given files had the same content
	 * @param f1
	 * @param f2
	 * @return
	 * @throws IOException
	 */
    private boolean isModified(File f1, File f2) throws IOException {
        return !Arrays.equals(  Files.readAllBytes(f1.toPath())  ,
                                Files.readAllBytes(f2.toPath())  );
    }


	
	/**
	 * Copy files from the current branch (all the way back to the split point with the given branch)
	 * to the top of given branch. In the case where versions of the file in the given branch are different 
	 * from those in the current branch, the versions from the given branch will take priority
	 * @param branchName
	 * @throws IOException
	 */
	public void rebase(String branchName) throws IOException{
		if(conflictedState) {
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
		if (!branchMap.containsKey(branchName)) {
			System.out.println(" A branch with that name does not exist.");
			return;
    	} else if (branchName.equals(currentBranch)){
    		System.out.println("Cannot rebase a branch onto itself.");
    		return;
    	} else {
    		CommitNode temp = currentHead;
    		while (currentHead.parent() != null){ 			//Consider how this loop will affect runtime 
    			if (currentHead.equals(branchMap.get(branchName))){
    				System.out.println("Already up-to-date");
    				return;
    			}
    			currentHead = currentHead.parent();
    		}
    		currentHead = temp;
    		CommitNode oghead = branchMap.get(branchName); 
    		CommitNode haj = branchMap.get(branchName);
    		while (haj.parent() != null){ 			 
    			if (currentHead.equals(haj)){
    				System.out.println("special case");
    				currentHead = oghead;
    				return;
    			}
    			 haj = haj.parent();
    		}
    		CommitNode split = findSplitPoint(branchName);
    		CommitNode current = currentHead;
    		Stack<CommitNode> mystack = new Stack();
    		for (CommitNode parent = current; parent != split; parent = parent.parent()){
    			mystack.push(parent);
    		}
			while (!mystack.isEmpty()){	
				CommitNode haji = mystack.pop();
				for(String key: haji.myFiles().keySet()){
					File topFile = haji.myFiles().get(key);
					Path filePath = topFile.toPath();
					byte[] fileBytes = Files.readAllBytes(filePath);
					File splitFile = split.myFiles().get(topFile.getName());
					if (splitFile != null){
						Path splitPath = splitFile.toPath();
						byte[] splitBytes = Files.readAllBytes(splitPath);
						File rebaseHead = branchMap.get(branchName).myFiles().get(topFile.getName());
						if (rebaseHead != null){	
							Path rebasePath = rebaseHead.toPath();
							byte[] rebaseBytes = Files.readAllBytes(rebasePath);
							if (Arrays.equals(splitBytes, fileBytes)){
								System.out.println("TITSSSSSSSSS");
								if (!Arrays.equals(splitBytes, rebaseBytes)){
									System.out.println("FUCKTHISSHITFUCKKKKKKK");
									topFile = rebaseHead;
								}
								
							}
						}
					}
					staging.put(topFile.getName(), topFile);
				}
				String message = haji.getMessage();
				CommitNode temp2 = currentHead;
				currentHead = branchMap.get(branchName);
				commit(message,false);
				currentHead = temp2;
			}
    	}
	}
		   

}