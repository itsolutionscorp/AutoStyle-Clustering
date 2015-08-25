import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class CommitList implements Serializable {
	
	private CommitNode head;
	private HashSet<String> staged;
	private HashSet<String> untracked;
	private int size;
	private HashMap<String, CommitNode> branches;
	private HashMap<Integer, CommitNode> commitIDs;
	private HashMap<String, HashSet<Integer>> commitMessages;
	private String currentBranch;
	private boolean conflicted;
	private HashSet<String> allFiles;
	
	/**
	 * Constructs a new CommitList with an initial commit.
	 */
	public CommitList() {
		conflicted = false;
		size = 0;
		head = new CommitNode("initial commit", size, null);
		staged = new HashSet<String>();
		untracked = new HashSet<String>();
		branches = new HashMap<String, CommitNode>();
		commitIDs = new HashMap<Integer, CommitNode>();
		commitMessages = new HashMap<String, HashSet<Integer>>();
		HashSet<Integer> ids = new HashSet<Integer>();
		ids.add(size);
		commitMessages.put("initial commit", ids);
		commitIDs.put(size, head);
		currentBranch = "master";
		branches.put(currentBranch, head);
		allFiles = new HashSet<String>();
	}
	
	/**
	 * Print all the commit messages from the current head to the first commit node.
	 */
	public void printLog() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (CommitNode current = head; current != null; current = current.prev) {
            System.out.println("====");
            System.out.println("Commit " + current.id);
            System.out.println(sdf.format(current.time.getTime()));
            System.out.println(current.message);
            if (current.prev != null) {
                System.out.println("");
            }
        }
	}
	
	/**
	 * Print all the commit messages from the first commit node to the last commit node
	 * by iterating through the hashMap commitIDs.
	 */
	public void printGlobalLog() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int count = 0;
		for (int id : commitIDs.keySet()) {
			CommitNode current = commitIDs.get(id);
			System.out.println("====");
            System.out.println("Commit " + current.id);
            System.out.println(sdf.format(current.time.getTime()));
            System.out.println(current.message);
            count++;
            if (count != commitIDs.size()) {
            	System.out.println("");
            }
		}
	}
	
	/**
	 * Print all the ids of commit nodes that have the same message.
	 * 
	 * @param message
	 * 			a String that should be shared by all the commitnodes as their message whose ids are printed
	 */
	public void printFind(String message) {
		if (!commitMessages.containsKey(message)) {
			System.out.println("Found no commit with that message.");
			return;
		}
		
		for (int id : commitMessages.get(message)) {
			System.out.println(id);
		}
	}
	
	/**
	 * Print all the names of the branches, staged files and files marked for untracking.
	 * 
	 */
	public void printStatus() {
		System.out.println("=== Branches ===");
		for (String branch : branches.keySet()) {
			if (branch.equals(currentBranch)) {
				System.out.print("*");
			}
			System.out.println(branch);
		}
		System.out.println("");
		System.out.println("=== Staged Files ===");
		for (String file : staged) {
			System.out.println(file);
		}
		System.out.println("");
		System.out.println("=== Files Marked for Untracking ===");
		for (String file : untracked) {
			System.out.println(file);
		}
	}
	
	/**
	 * Checks if there is no staged files.
	 * @return
	 * 		true if the staged hashset is empty
	 */
	public boolean nothingStaged() {
		return staged.isEmpty();
	}
	
	/**
	 * Checks if there is no untracked files.
	 * @return
	 * 		true if the untracked hashset is empty.
	 */
	public boolean nothingUntracked() {
		return untracked.isEmpty();
	}
	
	/**
	 * Gives the size of the CommitList.
	 * @return
	 * 		the number of nodes in the CommitList
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Gives the names of the files that are currently staged.
	 * 
	 * @return the HashSet of the names of staged files; 
	 */
	public HashSet<String> stagedFiles() {
		return staged;
	}
	
	/**
	 * Stages the file that has the name filename for the next call of commit.
	 * Also adds the file to the allFiles hashset.
	 * 
	 * @param filename
	 * 			a String that is the name of the file that should be staged;
	 */
	public void stageFile(String filename) {
		allFiles.add(filename);
		staged.add(filename);
	}
	
	/**
	 * Marks a file for untracking for the next call of commit.
	 * 
	 * @param filename
	 * 			a String that is the name of the file that should be untracked;
	 */
	public void untrackFile(String filename) {
		untracked.add(filename);
	}
	
	/**
	 * Returns if a file is currently staged by checking if the filename is in staged HashSet.
	 * 
	 * @param filename
	 * 			a String that is the name of the file that needs to be checked;
	 * 
	 * @return true if the filename is contained in the hashSet staged;
	 */
	public boolean isStaged(String filename) {
		return staged.contains(filename);
	}
	
	/**
	 * Returns if a file is marked untracking by checking if the filename is in untracked HashSet.
	 * 
	 * @param filename
	 * 			a String that is the name of the file that needs to be checked;
	 * 
	 * @return true if the filename is contained in the hashSet untracked;
	 */
	public boolean isUntracked(String filename) {
		return untracked.contains(filename);
	}
	
	/**
	 * Returns if a file is tracked in the previous commit call.
	 * 
	 * @param filename
	 * 			a String that is the name of the file that needs to be checked;
	 * 
	 * @return true if the isTracked method in head returns true(the file 
	 * is tracked in the previous commit node of head);
	 */
	public boolean isTracked(String filename) {
		return head.isTracked(filename);
	}
	
	/**
	 * Removes a file from the staged HashSet so that new copy of the file will not
	 * be made in the next call to commit.
	 * 
	 * @param filename
	 * 			a String that is the name of the file that needs to be unstaged;
	 */
	public void unstageFile(String filename) {
		staged.remove(filename);
	}
	
	/**
	 * Removes a file from the untracked hashSet so that the file will still be tracked
	 * in the next call to commit.
	 * 
	 * @param filename
	 * 			a String that is the name of the file that needs to be unmarked untracking;
	 */
	public void unmarkUntracked(String filename) {
		untracked.remove(filename);
	}
	
	/**
	 * Returns the set of string that stores the names of the file tracked by the previous commitnode of head.
	 * 
	 * @return the set of string that stores the names of the file tracked by the previous commitnode of head;
	 */
	public Set<String> prevCommitFiles() {
		return head.prevCommitFiles();
	}
	
	/**
	 * Returns the file stored in the previous commit node of head that has the name filename.
	 * 
	 * @param filename
	 * 			a String that is the name of the file that is stored in the previous commit node of 
	 * 			head and should be returned;
	 * 
	 * @return the file stored in the previous commit node of head that has the name filename;
	 * 
	 */
	public File prevCommitGitlet(String filename) {
		return head.prevCommitGitlet(filename);
	}
	
	/**
	 * Stores the file gitlet into the new commit node.
	 * 
	 * @param filename
	 * 			a String that is the name of the file that should be stored into the new commit node;
	 * 
	 * @param gitlet
	 * 			The version of the file that should be stored into the new commit node;
	 */
	public void commitFile(String filename, File gitlet) {
		head.commitFile(filename, gitlet);
	}
	
	/**
	 * Creates a new commit node with the size of the whole list as its id, the current head as 
	 * its previous node and the input message. Stores the new node into branches and commitIDs.
	 * 
	 * @param message
	 * 			a String that is the message of the new commit call.
	 *  
	 */
	public void commit(String message) {
		size++;
		head = new CommitNode(message, size, head);
		commitIDs.put(size, head);
		branches.put(currentBranch, head);
		if (!commitMessages.containsKey(message)) {
			commitMessages.put(message, new HashSet<Integer>());
		}
		commitMessages.get(message).add(size);
	}	
	
	/**
	 * Clears the files in staged and untracked at the end of every commit call.
	 */
	public void clearFiles() {
		staged.clear();
		untracked.clear();
	}
	
	/**
	 * Returns the file in the current node that has the name filename. This method
	 * should be called with the checkout [filename] command.
	 * @param filename
	 * 			The name of the file that needs to be checked out.
	 * @return the version of the file stored in the current node that has the name filename 
	 */
	public File checkoutFile(String filename) {
		return head.files.get(filename);
	}
	
	/**
	 * Returns if a branch with the input name exists.
	 * @param name
	 * 			A string that is the name of the branch that needs to be looked up.
	 * @return true if a branch with this name exists.
	 */
	public boolean branchExists(String name) {
		return branches.containsKey(name);
	}
	
	/**
	 * Returns the HashMap from file names to the corresponding files stored in the head of
	 * the branch with the given name.
	 * @param name
	 * 			A string that is the name of the branch that should be checked out.
	 * @return null if the branch with the given name is the current branch;
	 * 		the HashMap from file names to files stored in the branch with the given name in other cases;
	 */
	public HashMap<String, File> checkoutBranch(String name) {
		if (name.equals(currentBranch)) {
			return null;
		}
		currentBranch = name;
		head = branches.get(name);
		return head.files;
	}
	
	/**
	 * Makes a branch with the given name and stores it into branches.
	 * @param name
	 * 			A string that is the name of the branch that is going to be created;
	 */
	public void makeBranch(String name) {
		if (branches.containsKey(name)) {
			System.out.println("A branch with that name already exists.");
			return;
		}
		branches.put(name, head);
	}
	
	/**
	 * Removes the branch with the given name.
	 * @param name
	 * 			A string that is the name of the branch that should be removed;
	 */
	public void removeBranch(String name) {
		if (currentBranch.equals(name)) {
			System.out.println("Cannot remove the current branch.");
			return;
		}
		if (branches.remove(name) == null) {
			System.out.println("A branch with that name does not exist.");
		}
	}
	
	/**
	 * Returns if a commit node with the given id exists.
	 * @param id
	 * 			An integer that is the id of the commit node that should be checked.
	 * @return true if a commit node with the given name exists.
	 */
	public boolean idExists(int id) {
		return commitIDs.containsKey(id);
	}
	
	/**
	 * Returns the version of the file with the given name stored in the commit
	 * node with the given id.
	 * @param id
	 * 			An integer that is the id of the commit node that should be checked out.
	 * @param filename
	 * 			An string that is the name of the file that should be checked out.
	 * @return null if the file with the given name is not tracked in the commit node with the given id;
	 * 			the version of the file in the commit node with the given id.
	 */
	public File checkoutID(int id, String filename) {
		CommitNode toCheckout = commitIDs.get(id);
		if (!toCheckout.isTracked(filename)) {
			return null;
		}
		return toCheckout.files.get(filename);
	}
	
	/**
	 * Resets the head to the commit node with the given id.
	 * Returns a HashMap from file names to files stored in 
	 * the commit code with the given id.
	 * @param id
	 * 			An integer that is the id of the commit node that the list should be reset to. 
	 * @return a HashMap from file names to files stored in the commit code with the given id.
	 */
	public HashMap<String, File> reset(int id) {
		head = commitIDs.get(id);
		branches.put(currentBranch, head);
		return head.files;
	}
	
	/**
	 * Returns if the branch with the given name is the current branch.
	 * @param name
	 * 			A string that is the name of the branch that should be checked.
	 * @return true if the branch with the given name is the current branch;
	 */
	public boolean isCurrent(String name) {
		return currentBranch.equals(name);
	}
	
	/**
	 * Returns if a branch with the given name exists.
	 * @param name
	 * 			A string that is the name of the branch that should be checked.
	 * @return true if a branch with the given name exists.
	 */
	public boolean hasBranch(String name) {
		return branches.containsKey(name);
	}
	
	/**
	 * Checks if the given two files are equal to each other.
	 * @param file1
	 * 			A file that should be compared to the other given file.
	 * @param file2
	 * 			A file that should be compared to the other given file.
	 * @return true if the two given files are equal to each other.
	 */
	public static boolean filesEqual(File file1, File file2) {
    	Path path1 = file1.toPath();
    	Path path2 = file2.toPath();
    	try {
        	return Arrays.equals(Files.readAllBytes(path1), Files.readAllBytes(path2)); 
        } catch (IOException e) {
        	System.err.println(e);
        	return false;
        }
	}
	
	/**
	 * Returns a HashMap from file names to files that are stored in the head node of the given branch.
	 * @param name string of the name of the given branch
	 * @return a HashMap from file names to files that are stored in the head node of the given branch
	 */
	public HashMap<String, File> branchFiles(String name) {
		return branches.get(name).files;
	}
	
	/**
	 * Returns a HashMap from file names to files that are stored in the current node.
	 * @return a HashMap from file names to files that are stored in the current node;
	 */
	public HashMap<String, File> headFiles() {
		return head.files;
	}
	
	/**
	 * Returns all the files ever added at some point in time.
	 * @return a hashset of string all the filenames
	 */
	public HashSet<String> allFiles() {
		return allFiles;
	}

	/**
	 * Returns a HashMap from file names to files that are stored in the split point of
	 * the current node and the branch with the given name.
	 * @param name
	 * 			A string that is the name of the branch that we are finding the split point with.
	 * @return a HashMap from file names to files that are stored in the split point;
	 */
	public HashMap<String, File> splitFiles(String name) {
		return getSplitPoint(name).files;
	}
	
	/**
	 * Returns the split point of the branch with the given name and the current node;
	 * @param name
	 * 			A string that is the name of the branch that we are finding the split point with;
	 * @return the split point of the branch with the given name and the current node.
	 */
	public CommitNode getSplitPoint(String name) {
		CommitNode merged = branches.get(name);
		CommitNode current = head;
		CommitNode splitPoint = null;
		if (merged.depth < current.depth) {
			splitPoint = branches.get(currentBranch);
			for (int difference = current.depth - merged.depth; difference > 0; difference--) {
				splitPoint = splitPoint.prev;
			}
			while (merged != splitPoint) {
				merged = merged.prev;
				splitPoint = splitPoint.prev;
			}
		} else {
			splitPoint = branches.get(name);
			for (int difference = merged.depth - current.depth; difference > 0; difference--) {
				splitPoint = splitPoint.prev;
			}
			while (current != splitPoint) {
				current = current.prev;
				splitPoint = splitPoint.prev;
			}
		}
		return splitPoint;
	}
	
	/**
	 * Returns if a conflicted file is generated by the merge method.
	 * @return true if a conflicted file is generated by the merge method;
	 */
	public boolean isConflicted() {
		return conflicted;
	}
	
	/**
	 * Set the boolean variable conflicted to true when merge generates a conflicted file.
	 */
	public void makeConflicted() {
		conflicted = true;
	}
	
	/**
	 * Set the boolean variable conflicted to false after the merge method ends.
	 */
	public void endConflicted() {
		conflicted = false;
	}
	
	/**
	 * Gives the name of the current branch.
	 * @return a string of the name of the current branch
	 */
	public String currentBranch() {
		return currentBranch;
	}
	
	/**
	 * Returns if the branch with the given name oldBranch is in the history of the current node. 
	 * @param oldBranch
	 * 			A string that is the name of the branch that we want to check if it is in the history of the current node.
	 * @return true if the branch with the given name oldBranch is in the history of the current node;
	 */
	public boolean isHistory(String oldBranch) {
		CommitNode older = branches.get(oldBranch);
		if (head.depth <= older.depth) {
			return false;
		}
		CommitNode temp = head;
		while (temp.depth > older.depth) {
			temp = temp.prev;
		}
		if (temp.id == older.id) {
			return true;
		}
		return false;
	}

	/**
	 * Switches the current branch to the branch with the given name.
	 * @param branchName
	 * 			A string that is the name of the branch that we want to change the head to;
	 */
	public void changeHead(String branchName) {
		head = branches.get(branchName);
	}

	/**
	 * Finds the branch with the given branch name and replay commits of the current branch 
	 * to the end of this given branch.
	 * @param branchName
	 * 			A string that is the name of the given branch to which the copy of the current branch should be attached;
	 */
	public void rebase(String branchName) {
		CommitNode splitPoint = getSplitPoint(branchName);
		CommitNode toCopy =head;
		CommitNode branchHead = branches.get(branchName);
		head = branchHead;
		replayCommit(toCopy,splitPoint);
	}

	/**
	 * Replays all the commits in the branch.
	 * @param branch
	 * 			A commitNode that is the current branch before rebase is called.
	 * @param split
	 * 			A commitNode that is the split point of the current branch and the given branch when calling rebase;
	 */
	public void replayCommit(CommitNode branch, CommitNode split){
		if (branch.prev==split) {
			replayCommitHelper(branch, split.files);
		} else {
			replayCommit(branch.prev, split);
			replayCommitHelper(branch,split.files);
		}
	}
	
	/**
	 * Replays the commit for the commitNode tail. Copies the node and attaches it to the end of the given branch.
	 * @param tail
	 * 			A commitNode that is within the current branch before calling the rebase method and needs to be replayed.
	 * @param splitFiles
	 * 			A HashMap from filenames to files stored in the split point of the current branch and the given branch in the rebase method.
	 */
	private void replayCommitHelper(CommitNode tail, HashMap<String, File> splitFiles) {
		CommitNode givenHead = head;
		commit(tail.message);
		for (String fileName: splitFiles.keySet()) {
			if (!tail.files.containsKey(fileName)) {
				continue;
			}
			File tailFile = tail.files.get(fileName);
			File split = splitFiles.get(fileName);
			if (filesEqual(tailFile, split)) {
				head.files.put(fileName, givenHead.files.get(fileName));
				File original = new File(".gitlet/Commit" + head.id + "/" + fileName);
				File copied = new File(".gitlet/Commit" + size + "/" + fileName);
				if (copied.getParentFile() != null) {
					copied.getParentFile().mkdirs();
				}
				if (original.exists()) {
					System.out.println("getHere");
					try {
						Files.copy(original.toPath(), copied.toPath(),StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				head.files.put(fileName, tailFile);
				File original = new File(".gitlet/Commit" + tail.id + "/" + fileName);
				File copied = new File(".gitlet/Commit" + size + "/" + fileName);
				if (original.exists()) {
					try {
						Files.copy(original.toPath(), copied.toPath(),StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private static class CommitNode implements Serializable {
		private final int id;
		private final String message;
		private final CommitNode prev;
		private final GregorianCalendar time;
		private final HashMap<String, File> files;
		private final int depth;
		
		/**
		 * Creates a new commit node using the msg, commitID and previous.
		 * @param msg
		 * 			A string that is the message with which the new call to commit is made.
		 * @param commitID
		 * 			An integer that is the unique id of this commit node.
		 * @param previous
		 * 			A commit node that is the old head of the list before the new call to commit.
		 */
		public CommitNode(String msg, int commitID, CommitNode previous) {
			message = msg;
			id = commitID;
			prev = previous;
			time = new GregorianCalendar();
			files = new HashMap<String, File>();
			if (id == 0) {
				depth = 0;
			} else {
				depth = prev.depth + 1;
			}
		}
		
		/**
		 * Adds the file gitlet with its name filename to the HashMap files of this node.
		 * @param filename
		 * 			A string that is the name of the file stored in this commit node.
		 * @param gitlet
		 * 			A file that is stored in this commit node.
		 */
		public void commitFile(String filename, File gitlet) {
			files.put(filename, gitlet);
		}
		
		/**
		 * Returns the set of file names stored in the previous commit code of this node.
		 * @return the set of file names stored in the previous commit node of this node;
		 */
		public Set<String> prevCommitFiles() {
			return prev.files.keySet();
		}
		
		/**
		 * Returns the file with the file name stored in the previous commit node of this node.
		 * @param filename
		 * 			A string that is the name of the file that we are looking for in the previous node.
		 * @return the file with the given name stored in the previous commit node of this node.
		 */
		public File prevCommitGitlet(String filename) {
			return prev.files.get(filename);
		}
		
		/**
		 * Returns if a file with the given file name is tracked in this node or not;
		 * @param filename
		 * 			A string that is the name of the file that we want to check if it is tracked or not;
		 * @return true if the file with the given name is tracked in this node.
		 */
		public boolean isTracked(String filename) {
			return files.containsKey(filename);
		}
	}

}
