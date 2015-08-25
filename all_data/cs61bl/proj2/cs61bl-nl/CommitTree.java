import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class CommitTree implements Serializable {
	/**
	 * A wrapper for the Commit class that stores the entire tree of Commits and
	 * handles operations changing the tree.
	 * 
	 * @author caseyolen
	 *
	 */
	private int currentID;
	private HashMap<String, Commit> myBranches;
	private HashMap<Integer, Commit> myCommits;
	private int size;
	private String currentBranch;

	/**
	 * Creates a new commitTree instance with one Commit and makes it the
	 * current branch.
	 */
	public CommitTree() {
		Commit root = new Commit();
		myBranches = new HashMap<String, Commit>();
		myCommits = new HashMap<Integer, Commit>();
		myBranches.put("master", root);
		myCommits.put(0, root);
		size = 1;
		currentID = 0;
		currentBranch = "master";
	}

	/**
	 * Selects currentID from the commit tree.
	 * 
	 * @return Integer stored in currentID.
	 */
	public int current() {
		return currentID;
	}

	/**
	 * Selects the name of the current branch.
	 * 
	 * @return String name of the branch
	 */
	public String currentBranch() {
		return currentBranch;
	}

	/**
	 * Selects a map of all of the Commit IDs and their corresponding commits.
	 * 
	 * @return HashMap stored in myCommits.
	 */
	public HashMap<Integer, Commit> getCommits() {
		return myCommits;
	}

	/**
	 * Moves the current branch to a new commit.
	 * 
	 * @param newcurrent
	 *            the commit that the current branch will point to
	 */
	public void setCurrent(int newcurrent) {
		myBranches.put(currentBranch, getCommit(newcurrent));
		currentID = newcurrent;
	}

	/**
	 * Sets the current branch to be the branch with name newcurrent. Does not
	 * move the branch head from its commit.
	 * 
	 * @param newcurrent
	 *            the String name of the new current branch
	 */

	public void setCurrentBranch(String newcurrent) {
		if (myBranches.containsKey(newcurrent)) {
			currentBranch = newcurrent;
			currentID = myBranches.get(currentBranch).getID();
		}

	}

	/**
	 * Gets the commit id of the branch with the given name.
	 * @param name the name of the branch.
	 * @return the commit id of the commit that this branch points to.
	 */
	public int getBranchId(String name){
		Commit branch=myBranches.get(name);
		if(branch==null){
			throw new IllegalArgumentException("A branch with that name does not exist.");
		}else{
			return branch.getID();
		}
		
	}

	/**
	 * Selects the map of branch names and their corresponding commits.
	 * 
	 * @return HashMap stored in myBranches.
	 */
	public HashMap<String, Commit> getBranches() {
		return myBranches;
	}

	/**
	 * Given the Id of the Commit, return the snapshots this Commit has
	 * 
	 * @param Id
	 * @return
	 */
	public ArrayList<File> getSnapshot(int Id) {
		return getCommit(Id).getSnapshot();
	}

	/**
	 * Gets the snapshot of files from the commit with the given branch name.
	 * 
	 * @param name
	 *            the name of the branch that is tracking the snapshot.
	 * @return
	 */
	public ArrayList<File> getBranchSnapshot(String name) {
		return getSnapshot(myBranches.get(name).getID());
	}

	/**
	 * Gets the snapshot of the most recently created commit in the current
	 * branch.
	 * 
	 * @return ArrayList of the Files tracked by this commit.
	 */
	public ArrayList<File> getHeadSnapshot() {
		for (Integer id : myCommits.keySet()) {
			if (myCommits.get(id).isLeaf()) {
				if (myCommits.get(id).inHistory(getCommit(currentID))
						|| id == currentID) {
					return getSnapshot(id);
				}
			}
		}
		return null;
	}

	/**
	 * Returns true if this branch is the current branch.
	 * 
	 * @param name String name of the branch
	 * @return true if this branch is the current branch.
	 */
	public boolean isCurrentBranch(String name) {
		return (name.equals(currentBranch));
	}

	/**
	 * return the Commit object with this given Id
	 * 
	 * @param Id
	 * @return return the Commit object with this given Id
	 */
	private Commit getCommit(int Id) {
		return myCommits.get(Id);
	}

	private class Commit implements Serializable {
		/**
		 * An instance of the Commit class tracks the files that are stored in each
		 * node of the commitTree.
		 * 
		 * @author caseyolen
		 *
		 */
		private Commit myParent;
		private ArrayList<File> myFiles;
		private String myMessage;
		private Timestamp myTime;
		private int myID;
		private static final String DIR = ".gitlet/commit#";

		/**
		 * Constructs the root Commit of the commitTree.
		 */
		public Commit() {
			myParent = null;
			myFiles = new ArrayList<File>();
			myMessage = "initial commit";
			myTime = new Timestamp(System.currentTimeMillis());
			myID = 0;
		}

		/**
		 * Get the directory of the commit
		 * 
		 * @return the directory string of this commit
		 */
		public String getDir() {
			return DIR + myID;
		}

		/**
		 * Return the directory of the collections of the files that this commit
		 * is tracking
		 * 
		 * @return the snapshot this commit was tracking.
		 */
		public ArrayList<File> getSnapshot() {
			ArrayList<File> temp = new ArrayList<File>();
			for (File f : myFiles) {
				temp.add(new File(getDir() + "/" + f.getName()));
			}
			return temp;
		}

		/**
		 * Constructs a Commit instance tracking a collection of Files.
		 * 
		 * @param parent
		 *            The parent Commit of this Commit.
		 * @param contents
		 *            The snapshot of Files that this Commit is tracking.
		 * @param message
		 *            A message describing the Commit.
		 */
		public Commit(Commit parent, ArrayList<File> contents, String message) {
			myParent = parent;
			myFiles = contents;
			myMessage = message;
			myTime = new Timestamp(System.currentTimeMillis());
			myID = size;

		}

		/**
		 * Prints a string representation of this Commit and returns its
		 * message.
		 */
		public String toString() {
			System.out.println("===");
			System.out.println("Commit " + myID);
			System.out.println(myTime.toString().substring(0, 19));
			return myMessage;
		}

		/**
		 * Gets this Commit's message.
		 * 
		 * @return String stored in myMessage.
		 */
		public String getMessage() {
			return myMessage;
		}

		/**
		 * Gets this Commit's parent Commit.
		 * 
		 * @return Commit stored in myParent.
		 */
		public Commit parent() {
			return myParent;
		}

		/**
		 * Gets this Commit's ID
		 * 
		 * @return Integer stored in myID.
		 */
		public int getID() {
			return myID;
		}

		/**
		 * Checks to see if the argument Commit is in the history of this Commit
		 * 
		 * @param ancestor
		 *            A Commit that could be in this Commit's history.
		 * @return true if the argument is in this Commit's history.
		 */
		public boolean inHistory(Commit ancestor) {
			Commit current = myParent;
			while (current != null) {
				if (current.getID() == ancestor.getID()) {
					return true;
				}
				current = current.parent();
			}
			return false;
		}

		/**
		 * Checks to see if this Commit is a leaf.
		 * 
		 * @return true if this Commit is a leaf.
		 */
		public boolean isLeaf() {
			for (int id : myCommits.keySet()) {
				if (myCommits.get(id).inHistory(this)) {
					return false;
				}
			}
			return true;
		}


		/**
		 * Recursively prints out information about the entire history of this Commit.
		 */
		public void recursivePrint() {
			System.out.println(this);
			System.out.println();
			if (myParent != null) {
				myParent.recursivePrint();
			}
		}

		/**
		 * Checks to see if this commit is a split point between two branches of the commit
		 * tree.
		 * 
		 * @return true if this commit is a split point.
		 */
		public boolean isSplitPoint() {
			for (Integer id1 : myCommits.keySet()) {
				for (Integer id2 : myCommits.keySet()) {
					if (myCommits.get(id1).parent() != null
							&& myCommits.get(id2).parent() != null) {
						if (myCommits.get(id1).parent().equals(this)
								&& myCommits.get(id2).parent().equals(this)
								&& id1 != id2) {
							return true;
						}
					}
				}
			}
			return false;
		}
	}
	// End of Commit nested class.

	/**
	 * Adds a new Commit to the commitTree that has the current Commit branch as
	 * its parent.
	 * 
	 * @param snapshot
	 *            An ArrayList<File> object that represents all of the files
	 *            that this Commit is tracking.
	 * @param message
	 *            A String message describing the Commit.
	 */
	public void addCommit(ArrayList<File> snapshot, String message) {
		for (String branch : myBranches.keySet()) {
			if (branch.equals(currentBranch)) {
				Commit newCommit = new Commit(myBranches.get(branch), snapshot,
						message);
				currentID = newCommit.getID();
				myBranches.put(branch, newCommit);
				myCommits.put(newCommit.getID(), newCommit);
			}
		}
		size++;
	}

	/**
	 * Tests if a Commit with the given ID is in the tree.
	 * 
	 * @param id
	 *            This Commit's integer ID.
	 * @return true if the Commit is in the tree.
	 */
	public boolean contains(int id) {
		return (myCommits.containsKey(id));
	}

	/**
	 * The size selector for the commit tree.
	 * 
	 * @return size of this commit tree.
	 */
	public int size() {
		return size;
	}

	/**
	 * Prints either a branch log or the global log of the Commit tree.
	 * 
	 * @param isGlobal
	 *            if true then prints the global log.
	 */
	public void printLog(boolean isGlobal) {
		if (isGlobal) {
			int index = 0;
			while (index < size) {
				System.out.println(myCommits.get(index));
				System.out.println();
				index++;
			}
		} else {
			for (String branch : myBranches.keySet()) {
				if (branch.equals(currentBranch)) {
					myBranches.get(branch).recursivePrint();
				}
			}
		}
	}

	/**
	 * Prints the names of all branch pointers and prints the current branch
	 * with a * prefix.
	 */
	public void printBranchStatus() {
		System.out.println("=== Branches ===");
		for (String branch : myBranches.keySet()) {
			if (branch.equals(currentBranch)) {
				System.out.println("*" + branch);
			} else {
				System.out.println(branch);
			}
		}
		System.out.println();
	}

	/**
	 * Prints the commit ID of the Commit with the given message. If no Commit
	 * is found then prints an error message.
	 * 
	 * @param message
	 *            String message describing the Commit.
	 */
	public void findCommitWithMessage(String message) {
		for (Integer id : myCommits.keySet()) {
			if (myCommits.get(id).getMessage().equals(message)) {
				System.out.println(id);
				return;
			}
		}
		System.out.println("Found no commit with that message.");
	}

	/**
	 * Adds a key-value pair to myBranches with name be the key and value be the
	 * current commit
	 * 
	 * @param name
	 */
	public void branch(String name) {
		if (myBranches.containsKey(name)) {
			System.out.println("A branch with that name already exists.");
		} else {
			myBranches.put(name, getCommit(currentID));
		}

	}

	/**
	 * Removes a branch if it is possible. Else prints error messages.
	 * @param name the name of the branch to be removed.
	 */
	public void removeBranch(String name) {
		if (!myBranches.containsKey(name)) {
			System.out.println("A branch with that name does not exist.");
		} else if (myBranches.get(name).getID() == currentID) {
			System.out.println("Cannot remove the current branch.");
		} else {
			myBranches.remove(name);
		}
	}

	/**
	 * Finds the split point between two branches.
	 * 
	 * @param Branch
	 *            String name of the given branch (not current)
	 * 
	 * @return The commit id of the split point commit. If branch2 is an ancestor of
	 * 			branch1, returns -1.
	 */
	public int findSplitPoint(String Branch1, String Branch2) {
		int rtn = 0;
		if (myBranches.containsKey(Branch1) && myBranches.containsKey(Branch2)) {
			if (myBranches.get(Branch1).inHistory(myBranches.get(Branch2))){
				rtn = -1;
			} else {
				ArrayList<Commit> splitPoints = new ArrayList<Commit>();
				for (Integer id : myCommits.keySet()) {
					if (myCommits.get(id).isSplitPoint()) {
						splitPoints.add(myCommits.get(id));
					}
				}
				for (Commit split : splitPoints) {
					if (myBranches.get(Branch1).inHistory(split)
							&& myBranches.get(Branch2).inHistory(split)) {
						rtn = split.getID();
					}
				}
			}
		}
		return rtn;
	}

	/**
	 * Modifies the commit tree to rebase the current branch onto a new branch, then calls 
	 * helper function in Gitlet to copy files.
	 * 
	 * @param propagatingFiles
	 * 		An ArrayList of files that have been modified in the new branch since the split point.
	 * @param branch
	 * 		The branch that current will be rebased to.
	 * @param splitID
	 * 		The commit id of the split point.
	 */
	public void rebase(ArrayList<File> propagatingFiles, String branch, int splitID) {
		if (branch.equals(currentBranch)) {
			System.out.println("Cannot rebase a branch onto itself.");
		} else if (myBranches.get(currentBranch).inHistory(myBranches.get(branch))) {
			System.out.println("Already up-to-date.");
		} else {
			int current = currentID;
			Stack<ArrayList<File>> commits = new Stack<ArrayList<File>>();
			Stack<String> messages = new Stack<String>();
			while (current != splitID) {
				ArrayList<File> newFiles = new ArrayList<File>();
				Iterator<File> currentFiles = getSnapshot(current).iterator();
				while (currentFiles.hasNext()) {
					File currentFile = currentFiles.next();
					newFiles.add(currentFile);
					ArrayList<File> oldFiles = new ArrayList<File>();
					for (File f : getSnapshot(splitID)){
						if ((f.getName().equals(currentFile.getName()) && FileProcess.equals(currentFile, f))){
							oldFiles.add(currentFile);
						}
					}
					for (File g : oldFiles){
						for (File f : propagatingFiles){
							if (g.getName().equals(f.getName())){
								newFiles.remove(currentFile);
								newFiles.add(f);
							}
						}
					}
				}
				commits.push(newFiles);
				messages.push(getCommit(current).getMessage());
				current = getCommit(current).parent().getID();
			}
			setCurrent(getBranchId(branch));
			FileProcess.save(this, new File(".gitlet/myCommitTree.obj"));
			Gitlet.rebaseHelper(commits, messages);
		}

	}

}
