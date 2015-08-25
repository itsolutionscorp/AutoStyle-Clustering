import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Gitlet implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final File STAGING_FOLDER = new File(".gitlet/staging/");
	private CommitNode curCommit;
	private LinkedList<CommitNode> commitList;
	private Map<String, CommitNode> branches;
	private String curBranch;
	private Set<String> staged;
	private Set<String> untracked;
	private Map<String, MappedMethod> strToMet;
	private Map<String, List<CommitNode>> commitMessages;
	private boolean isConflicted;

	/**
	 * Constructor for a Gitlet. Only one instance is made in main
	 * The first call to Gitlet() initialize the instance variables of Gitlet as a new object
	 * The Gitlet instance is serialized and deserialized on next calls to main
	 */
	public Gitlet() {
		branches = new HashMap<String, CommitNode>();
		staged = new HashSet<String>();
		untracked = new HashSet<String>();
		commitList = new LinkedList<CommitNode>();
		commitMessages = new HashMap<String, List<CommitNode>>();
		strToMet = new HashMap<String, MappedMethod>();
		putMethods1();
		putMethods2();
	}
	
	/**
	 * Put method mappings into strToMet
	 */
	@SuppressWarnings("serial")
	public void putMethods1() {
		strToMet.put("init", new MappedMethod() {
			public void execute() {init();}
			public void execute(String arg) {}
		});
		strToMet.put("log", new MappedMethod() {
			public void execute() {log();}
			public void execute(String arg) {}
		});
		strToMet.put("global-log", new MappedMethod() {
			public void execute() {globalLog();}
			public void execute(String arg) {}
		});
		strToMet.put("commit", new MappedMethod() {
			public void execute() {}
			public void execute(String arg) {
				CommitNode thisCommit = commit(curCommit, commitList.size(), arg);
				//set current commit to newest commit
				//and change current branch's head commit to current commit
				curCommit = thisCommit;
				branches.put(curBranch, thisCommit);
			}
		});
		strToMet.put("add", new MappedMethod() {
			public void execute() {}
			public void execute(String arg) {add(arg);}
		});
		strToMet.put("rm", new MappedMethod() {
			public void execute() {}
			public void execute(String arg) {rm(arg);}
		});
		strToMet.put("find", new MappedMethod() {
			public void execute() {}
			public void execute(String arg) {find(arg);}
		});
		strToMet.put("checkout", new MappedMethod() {
			public void execute() {}
			public void execute(String arg) {checkout(arg);}
		});
	}
	
	/**
	 * Put more method mappings into strToMet
	 */
	@SuppressWarnings("serial")
	public void putMethods2() {
		strToMet.put("branch", new MappedMethod() {
			public void execute() {}
			public void execute(String arg) {
				if (isConflicted) {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
				} else {
					branch(arg);
				}
			}
		});
		strToMet.put("rm-branch", new MappedMethod() {
			public void execute() {}
			public void execute(String arg) {
				if (isConflicted) {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
				} else {
					rmBranch(arg);
				}
			}
		});
		strToMet.put("reset", new MappedMethod() {
			public void execute() {}
			public void execute(String arg) {
				if (isConflicted) {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
				} else {
					reset(arg);
				}
			}
		});
		strToMet.put("merge", new MappedMethod() {
			public void execute() {}
			public void execute(String arg) {
				if (isConflicted) {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
				} else {
					merge(arg);
				}
			}
		});
		strToMet.put("rebase", new MappedMethod() {
			public void execute() {}
			public void execute(String arg) {
				if (isConflicted) {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
				} else {
					rebase(arg);
				}
			}
		});
	}

	/**
	 * Initialize a gitlet. This method creates necessary folders (.gitlet, initial commit folder, staging area)
	 * If .gitlet already exists, it prints a message.
	 * Runtime: constant
	 */
	public void init() {
		File gitlet = new File(".gitlet");
		if (gitlet.exists() && gitlet.isDirectory()) {
			System.out.println("A gitlet version control system already exists in the current directory.");
		} else {
			//make folders
			gitlet.mkdir();
			STAGING_FOLDER.mkdir();
			curCommit = initialCommit();
			branches.put("master", curCommit);
			curBranch = "master";
		}
	}

	/**
	 * Add file to the staging area.
	 * If file is marked for untracking, just unmarks the file WITHOUT adding.
	 * Runtime: constant
	 * @param fileName name of the file to be added
	 */
	public void add( String fileName ) {
		File toBeAdded = new File(fileName);
		if (!toBeAdded.exists()) {
			System.out.println("File does not exist.");
		} else if (untracked.contains(fileName)) {
			untracked.remove(fileName);
		} else {
			//copy file from working directory to staging folder (copyCode: -1)
			copyFile(toBeAdded, STAGING_FOLDER, -1);
			staged.add(fileName);
		}
	}

	/**
	 * Commit the staged files with a message
	 * Runtime: linear relative to number of files tracked
	 * @param parent Parent commit of this commit
	 * @param id Unique integer of this commit
	 * @param message Message left by user
	 */
	public CommitNode commit(CommitNode parent, int id, String message) {
		CommitNode thisCommit = new CommitNode(parent, id, message);
		if (STAGING_FOLDER.list().length == 0 && untracked.size() == 0) {
			//staging area is empty and nothing is untracked
			System.out.println("No changes added to the commit.");
			return null;
		} else {
			//creates a new commit folder
			File commitFolder = new File(".gitlet/" + thisCommit.id());
			commitFolder.mkdir();
			//iterates and copy files and folders in staging area (copyCode: 0)
			for (File toBeCommitted : STAGING_FOLDER.listFiles()) {
				if (toBeCommitted.isDirectory() && toBeCommitted.listFiles().length != 0) {
					//if this is a non-empty folder
					copyFolder(toBeCommitted, commitFolder, 0);
					clearDirectory(toBeCommitted);
				} else if (toBeCommitted.isFile()) {
					copyFile(toBeCommitted, commitFolder, 0);
					toBeCommitted.delete();
				}
			}
			//tracking appropriate files
			trackFiles(thisCommit);
			if (commitFolder.listFiles().length == 0 && untracked.size() == 0) {
				commitFolder.delete();
				System.out.println("No changes added to the commit.");
				return null;
			}
			//clear staged HashSet and untracked HashSet
			staged.clear();
			untracked.clear();
			//add commit to commit tree 
			//and branch to set of branches commit is part of 
			//and add commit to list of parent's children
			commitList.add(thisCommit);
			thisCommit.addBranch(curBranch);
			parent.addChild(curBranch, thisCommit);
			//change conflict state
			isConflicted = false;
			//add commit to messages HashMap
			if (commitMessages.get(thisCommit.message()) == null) {
				commitMessages.put(thisCommit.message(), new ArrayList<CommitNode>());
			}
			commitMessages.get(thisCommit.message()).add(thisCommit);

			return thisCommit;
		}
	}

	public void trackFiles(CommitNode commit) {
		//track all files that parent tracks if file is not marked for untracking
		for (String fileName : commit.parent().trackedFiles().keySet()) {
			if (!untracked.contains(fileName)) {
				commit.trackFile(fileName, commit.parent().trackedFiles().get(fileName));
			}
		}
		//addtionally, track newer versions of staged files
		for (String fileName : staged) {
			commit.trackFile(fileName, commit.id());
		}
	}

	/**
	 * Specialized commit method for initial commit
	 * Runtime: constant
	 * @return CommitNode that is the first headPointer
	 */
	public CommitNode initialCommit() {
		CommitNode rtnCommit = new CommitNode(null, 0, "initial commit");
		new File(".gitlet/" + rtnCommit.id()).mkdir();
		commitList.add(rtnCommit);
		commitMessages.put(rtnCommit.message(), new ArrayList<CommitNode>());
		commitMessages.get(rtnCommit.message()).add(rtnCommit);
		return rtnCommit;
	}

	/**
	 * Method used to copy a file to a folder. It uses the method toPath built into File to copy a Path object to another Path object
	 * Runtime: constant
	 * Developed from: http://stackoverflow.com/questions/16433915/how-to-copy-file-from-one-location-to-another-location
	 * @param from File object of file to be copied
	 * @param to File object of directory to receive the file
	 * @param copyCode - 0 for commit copying, positive int for checkout copy, and else for anything else (add)
	 * @throws IOException
	 */
	public static void copyFile( File from, File to, int copyCode ) {
		if (from.getParentFile() != null) {
			File newDir;
			if (copyCode == 0) {
				newDir = new File(to.toString() + "/" + from.getParent().replace(".gitlet/staging", ""));
			} else if (copyCode > 0) {
				newDir = new File(to.toString() + "/" + from.getParent().replace(".gitlet/" + copyCode, ""));
			} else {
				newDir = new File(to.toString() + "/" + from.getParent());
			}
			newDir.mkdirs();
			to = newDir;
		}
		try {
			Files.copy( from.toPath(), to.toPath().resolve(from.toPath().getFileName()), StandardCopyOption.REPLACE_EXISTING );
		} catch (IOException i) {
			//All hail King Wug
		}

	}

	/**
	 * Copy folder method; iterates through a folder and copyFolder or copyFile accordingly
	 * Runtime: linear relative to files in the folder
	 * @param from Source folder
	 * @param to Destination folder
	 * @param copyCode - 0 indicates a commit copy, positive ints for checkout copy, and else for others (adding)
	 */
	public static void copyFolder( File from, File to, int copyCode ) {
		for (String file : from.list()) {
			File innerFile = new File(from, file);
			if (innerFile.isDirectory() && innerFile.listFiles().length != 0) {
				//non-empty folder
				copyFolder(innerFile, to, copyCode);
			} else if (innerFile.isFile()) {
				copyFile(innerFile, to, copyCode);
			}
		}
	}

	/**
	 * Clears the directory, deleting all files and directories (both empty and non-empty) inside it
	 * Runtime: linear relative to files in folder
	 * @param dirPath path of directory to be cleared
	 */
	public void clearDirectory(File dirPath) {
		if( dirPath.exists() ) {
			for(File file : dirPath.listFiles()) {
				if(file.isDirectory()) {
					clearDirectory(file);
				}
				file.delete();
			}
		}
	}

	/**
	 * Mark the file for untracking. If the file is staged, unstage it WITHOUT marking it
	 * Runtime: constant
	 * @param fileName
	 */
	public void rm(String fileName) {
		//check if file is in staging and delete (successful delete returns true)
		boolean isStaged = new File(STAGING_FOLDER, fileName).delete();
		if (!isStaged) {
			untracked.add(fileName);
		} else {
			staged.remove(fileName);
		}
	}

	/**
	 * Prints the commit log starting from the current node
	 * Note: Log does not take into account other branches
	 * Runtime: linear relative to current branch's commits
	 */
	public void log() {
		//starts from headPointer and prints commits until null 
		CommitNode curNode = curCommit;
		while (curNode != null) {
			System.out.println("===");
			System.out.println("Commit " + curNode.id());
			System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(curNode.date()));
			System.out.println(curNode.message());
			System.out.println();
			curNode = curNode.parent();
		}
	}

	/**
	 * Prints the commit log for ALL commits
	 * Global-log takes into account other branches
	 * Runtime: linear relative to total number of commits
	 */
	public void globalLog() {
		//prints all commits in linked list commitTree
		for (CommitNode node : commitList) {
			System.out.println("===");
			System.out.println("Commit " + node.id());
			System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(node.date()));
			System.out.println(node.message());
			System.out.println();
		}
	}

	/**
	 * Print the commit ID of any commit with the given message
	 * Runtime: linear relative to number of commit with given message
	 * @param commitMessage 
	 */
	public void find (String commitMessage) {
		if (commitMessages.containsKey(commitMessage)) {
			for (CommitNode commit : commitMessages.get(commitMessage)) {
				//prints all commit IDs associated with this commitMessage
				System.out.println(commit.id());
			}
		} else {
			System.out.println("Found no commit with that message.");
		}
	}

	/**
	 * Prints out status of the current session.
	 * Prints all branches, staged files, and files marked for untracking
	 * Runtime: linear relative to number of branches; linear relative to number of files in staging; linear relative to number of untracked files
	 */
	public void status() {
		System.out.println("=== Branches ===");
		for (String branch : branches.keySet()) {
			if (branch.equals(curBranch)) {
				System.out.println("*"+branch);
			} else {
				System.out.println(branch);
			}
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		for (String fileName : staged) {
			System.out.println(fileName);
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (String fileName : untracked) {
			System.out.println(fileName);
		}
	}

	/**
	 * Checkout the branch or the file given by the name as parameter
	 * If checking out file, just checks out file in current commit
	 * If checking out branch, switch to given branch and future commits will be under this branch unless another is checked out
	 * Runtime: checkout branch - linear relative to number of files in branch's head commit
	 * @param name could be fileName OR branchName
	 */
	public void checkout(String name) {
		if (name.equals(curBranch)) {
			if (isConflicted) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
				return;
			}
			System.out.println("No need to checkout the current branch.");
		} else if (branches.containsKey(name)) {
			if (isConflicted) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
				return;
			}
			//branch takes precedence; change curBranch and headPointer, also reset to headPointer of branch
			curBranch = name;
			curCommit = branches.get(name);
			reset(curCommit.id() + "");
		} else if (new File(".gitlet/" + curCommit.id() + "/" + name).exists() || curCommit.trackedFiles().containsKey(name)) {
			//not a branch, check out the file in current commit
			checkout(curCommit.id() + "", name);
		} else {
			System.out.println("File does not exist in the most recent commit, or no such branch exists.");
		}
	}

	/**
	 * Checks out file in given commit and replaces current version with commit version
	 * Runtime: linear relative to size of file
	 * @param commitID ID of commit
	 * @param fileName name of file checking out
	 */
	public void checkout(String commitID, String fileName) {
		int id = Integer.parseInt(commitID);
		File committedFile = new File(".gitlet/" + commitID + "/" + fileName);
		if (id >= commitList.size() || id < 0) {
			//commit ID not found
			System.out.println("No commit with that id exists.");
		} else if (committedFile.exists()) {
			//copy wanted file from commit folder to working directory
			copyFile(committedFile, new File("."), id);
		} else if (commitList.get(id).trackedFiles().containsKey(fileName)) {
			//if file doesn't exist but is tracked, check out from commit that has it
			int lastCommit = commitList.get(id).trackedFiles().get(fileName);
			copyFile(new File(".gitlet/" + lastCommit + "/" + fileName), new File("."), lastCommit );
		} else {
			//oh god
			if (id == curCommit.id()) {
				System.out.println("File does not exist in the most recent commit, or no such branch exists.");
			} else {
				System.out.println("File does not exist in that commit.");
			}
		}
	}

	/** Creates a new branch (does not switch into it)
	 * 	Runtime: constant
	 * @param branchName
	 */
	public void branch(String branchName) {
		if (branches.containsKey(branchName)) {
			System.out.println("A branch with that name already exists.");
		} else {
			//creates a new branch with current headPointer
			branches.put(branchName, curCommit);
			//add branch to list of branches current commit is part of
			curCommit.addBranch(branchName);
		}
	}

	/** Deletes the specified branch. 
	 *	This only means to delete the pointer associated with the branch.
	 *  Runtime: constant
	 * @param branchName
	 */
	public void rmBranch(String branchName) {
		if (branchName.equals(curBranch)) {
			//trying to remove current branch
			System.out.println("Cannot remove the current branch.");
		} else if (branches.containsKey(branchName)) {
			//remove said branch
			branches.remove(branchName);
		} else {
			//branch doesn't exist
			System.out.println("A branch with that name does not exist.");
		}
	}

	/**
	 * Revert back to a commit given by the commit ID; also changes the head pointer
	 * Runtime: linear relative to number of files in given commit
	 * @param commitID
	 */
	public void reset(String commitID) {
		//check out files 
		int id = Integer.parseInt(commitID);
		if (id < 0 || id >= commitList.size()) {
			System.out.println("No commit with that id exists.");
		} else {
			CommitNode commit = commitList.get(id);
			for (String fileName : commit.trackedFiles().keySet()) {
				//check out file in list of tracked files based on when it was last changed
				checkout(commit.trackedFiles().get(fileName)+"", fileName);
			}
			//change headPointer
			curCommit = commitList.get(id);
		}
	}

	/**
	 * Merges files from the given branch into the current branch.
	 * Runtime: linear relative to history of current branch and given branch from heads to split point
	 * @param branchName Name of branch that is being merged into current branch
	 */
	public void merge(String branchName) {
		//Failure cases
		if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (branchName.equals(curBranch)) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}
		//Finding split ID
		int splitID = findSplitID(curCommit, curBranch, branchName);
		//Merging
		CommitNode otherHead = branches.get(branchName);
		boolean conflicted = false;
		for (String fileName : otherHead.trackedFiles().keySet()) {
			//only look at files in otherHead because if something exists in current but not in other head, then do nothing anyway
			if (curCommit.trackedFiles().containsKey(fileName)) {
				//if the file in the given branch also exists in the current branch
				int curID = curCommit.trackedFiles().get(fileName);
				int otherID = otherHead.trackedFiles().get(fileName);

				if (curID <= splitID && otherID > splitID) {
					//File not modified in current branch after split point but modified in given branch after split point -> check out then stage
					checkout(otherID+"", fileName);
					add(fileName);
				} else if (curID > splitID && otherID <= splitID) {
					//File modified in current branch but not modified in argument branch since split point should stay
				} else {
					//File modified in both current and argument branches should stay as they are
					//but versions from argument branch should be copied into working directory as [fileName].conflicted
					File toBeCopied = new File(".gitlet/" + otherID + "/" + fileName);
					File destinationFile = new File(fileName + ".conflicted");
					try {
						Files.copy( toBeCopied.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING );
					} catch (IOException i) {
						//Please work
					}
					conflicted = true;
				}
			} else {
				//if file only exists in given branch's head commit
				checkout(otherHead.trackedFiles().get(fileName) + "", fileName);
				add(fileName);
			}
		}
		//Committing
		mergeCommit(conflicted, branchName);
	}

	/**
	 * Return the ID of the commit that is the split point between branch1 and branch2
	 * Runtime: linear relative to history from commit to split point of the two branches
	 * @param commit Commit to start with
	 * @param branch1 Branch #1 of the split
	 * @param branch2 Branch #2 of the split
	 * @return Integer ID of the split point commit
	 */
	public int findSplitID(CommitNode commit, String branch1, String branch2) {
		//Find split point ID between current branch and argument branch
		CommitNode curNode = commit;
		int splitID = curNode.id();
		while ( !(curNode.branches().contains(branch1) && (curNode.branches().contains(branch2))) ) {
			//while curNode's set of branches doesn't include both branches in question
			curNode = curNode.parent();
			splitID = curNode.id();
		}
		return splitID;
	}

	/**
	 * Commit after a merge. If there is a conflict, do not commit and switch on conflicted state
	 * @param conflicted Boolean indicating if merge encountered a conflicted state
	 * @param branchMerged Branch merging into curent branch
	 */
	public void mergeCommit(boolean conflicted, String branchMerged) {
		if (conflicted) {
			//If .conflicted files created, enter conflicted state
			System.out.println("Encountered a merge conflict.");
			isConflicted = true;
		} else {
			//If no .conflicted files created, automatically commit.
			CommitNode thisCommit = commit(curCommit, commitList.size(), "Merged " + curBranch + " with " + branchMerged + " .");
			curCommit = thisCommit;
			branches.put(curBranch, thisCommit);
		}
	}

	/**
	 * Takes the current branch and 'copy' it over to the head of the given branch, with copied commits with new IDs and dates
	 * @param branchName Name of branch to rebase to
	 */
	public void rebase(String branchName) {
		//special case (current branch in history of given branch; this means current commit is a split point not yet split)
		if (curCommit.branches().contains(curBranch) && curCommit.branches().contains(branchName)) {
			curCommit = branches.get(branchName);
			branches.put(curBranch, curCommit);
			return;
		}
		//failure cases
		if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (curBranch.equals(branchName)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		} else if (branches.get(branchName).branches().contains(curBranch)) {
			//head commit of given branch is a split point not yet split
			System.out.println("Already up-to-date.");
			return;
		}
		int splitID = findSplitID(curCommit, curBranch, branchName);
		//first commit to copy (child of the split point that is of the current branch)
		CommitNode commitToCopy = commitList.get(splitID).getChildren().get(curBranch);
		//first parent of the recommitting process (other branch's head)
		CommitNode otherHead = branches.get(branchName);
		//iterating through the current branch
		while (commitToCopy != null) {
			//copy commit over with parent as the current parent of the recommitting process
			CommitNode newCommit = copyCommit(otherHead, commitToCopy);
			commitMessages.get(newCommit.message()).add(newCommit);
			commitList.add(newCommit);
			//replace unmodified files in the new copied commit with modified ones in other branch
			for (String fileName : otherHead.trackedFiles().keySet()) {
				if (newCommit.trackedFiles().containsKey(fileName)) {
					int otherLastChanged = otherHead.trackedFiles().get(fileName);
					int copiedLastChanged = newCommit.trackedFiles().get(fileName);
					//used Integer object because copiedLastChanged could be null (file doesn't exist), and can't compare int to null
					if (new Integer(copiedLastChanged) == null || (otherLastChanged > splitID && copiedLastChanged <= splitID)) {
						//if the other head has a modified file after the split and the copied commit doesn't
						newCommit.trackFile(fileName, otherLastChanged);
					}
				} else {
					newCommit.trackFile(fileName, otherHead.trackedFiles().get(fileName));
				}
			}
			//increment:
			//commitToRecommit should be the next child of the current branch
			//otherHead should be the next child of the new branch that we're making (new parent)
			commitToCopy = commitToCopy.getChildren().get(curBranch);
			otherHead = otherHead.getChildren().get(curBranch);
		}
		//"Finally, after successfully replaying nodes, reset to the node at the front of the replayed branch."
		curCommit = otherHead;
		branches.put(curBranch, otherHead);
		reset(commitList.size()-1+"");
	}

	/**
	 * Copy a commit and make a new commit out of it (only ID and date is changed)
	 * Runtime: constant
	 * @param parent Parent of the new commit
	 * @param toCopy CommitNode to copy
	 * @return New commit that was just copied from toCopy
	 */
	public CommitNode copyCommit(CommitNode parent, CommitNode toCopy) {
		CommitNode newCommit = new CommitNode(parent, toCopy);
		newCommit.setID(commitList.size());
		parent.addChild(curBranch, newCommit);
		return newCommit;
	}

	/**
	 * Main method, takes in String[] args.
	 * First, it deserializes a saved object, if this is the first time, it makes a new object
	 * Then it does things based on the args
	 * Lastly, it serializes the object and overwrites the old file
	 * Developed from: http://www.tutorialspoint.com/java/java_serialization.htm
	 * @param args String array of commands and parameters
	 */
	public static void main(String[] args) {

		Gitlet g = null;

		//De-serialize here; if gitlet.ser doesn't exist, create a new object (only first call)
		File serialized = new File("gitlet.ser");
		if (serialized.exists()) {
			try {
				ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("gitlet.ser"));
				g = (Gitlet) objIn.readObject();
				objIn.close();
			} catch (IOException i) {

			} catch (ClassNotFoundException c) {

			}

		} else {
			g = new Gitlet();
		}

		//Do stuff and things
		if (args.length == 0) {
			System.out.println("Please enter a command.");
		} else if (!g.strToMet.containsKey(args[0])) {
			System.out.println("No command with that name exists.");
		} else if (args.length == 1) {
			g.strToMet.get(args[0]).execute();
		} else if ( args.length == 2 || args.length > 3) {
			g.strToMet.get(args[0]).execute(args[1]);
		} else {
			g.checkout(args[1], args[2]);
		}

		//After everything is done, serialize and overwrite previous serialized file
		try {
			ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("gitlet.ser"));
			objOut.writeObject(g);
			objOut.close();
		} catch (IOException i) {

		}
	}
}