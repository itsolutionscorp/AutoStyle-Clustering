
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.io.File;
import java.nio.file.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Gitlet implements Serializable {
	/**
	 * Authors : Jiahao Huang, Cary Schwartzstein, Will Park, Bryan Lopez
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Node<HashSet<File>>, Integer> pointerTrack2;
	private Node<HashSet<File>> pointer;
	private int count_id;
	private HashMap<String, Node<HashSet<File>>> branchTrack;
	private HashMap<Integer, Node<HashSet<File>>> pointerTrack;
	private HashSet<File> addedFiles;
	private HashSet<File> removedFiles;
	private Node<HashSet<File>> commitTree;
	private String currentBranch;
	private HashSet<File> commitedFiles;
	private HashMap<String, Integer> trackingFiles;
	private ArrayList<Node<HashSet<File>>> nodes = new ArrayList<Node<HashSet<File>>>();
	private boolean ifConflict = false;
	private boolean inRebase = false;

	/**
	 * Creates a new gitlet version control system in the current directory.
	 */
	public void init() {
		File f = new File(".gitlet/");
		if (f.exists()) {
			System.out.println("A gitlet version control system already exists in the current directory.");
			return;
		}
		count_id = 0;
		f.mkdirs();
		File master = new File(".gitlet/master");
		master.mkdirs();
		String time = recordTime();
		branchTrack = new HashMap<String, Node<HashSet<File>>>();
		addedFiles = new HashSet<File>();
		removedFiles = new HashSet<File>();
		commitTree = new Node<HashSet<File>>(null, count_id, "initial commit", "master", new HashSet<File>(), time);
		pointer = commitTree;
		nodes.add(pointer);
		commitedFiles = new HashSet<File>();
		branchTrack.put("master", commitTree);
		currentBranch = "master";
		pointerTrack = new HashMap<Integer, Node<HashSet<File>>>();
		pointerTrack.put(count_id, pointer);
		pointerTrack2 = new HashMap<Node<HashSet<File>>, Integer>();
		pointerTrack2.put(pointer, count_id);
		trackingFiles = new HashMap<String, Integer>();
	}
	
	/**
	 * Records the year, month, date, hour, minute, and second of the current time.
	 * 
	 * @return a string representing the year, month, date, hour, minute, and second of the current time
	 */
	public String recordTime() {
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = ft.format(date);
		return time;
	}
	
	/**
	 * Adds a copy of the file as it currently exists to the staging area.
	 * 
	 * @param fileName
	 * 				a string denoting the file to be added 
	 */
	public void add(String fileName) {
		File f = new File(fileName);
		if (!f.exists()) {
			System.out.println("File does not exist.");
			return;
		}
		if (removedFiles.contains(f)) {
			removedFiles.remove(f);
		}
		addedFiles.add(f);
	}
	
	/**
	 * Saves a backup of certain files to be restored at a later time.
	 * 
	 * @param message
	 * 				a string describing the changes to the files in the commit
	 */
	public void commit(String message) {
		if (addedFiles.isEmpty()) {
			System.out.println("No changes added to the commit.");
		}
		String time = recordTime();
		HashSet<File> addedFiles_with_newPath = new HashSet<File>();
		for (File f : addedFiles) {
			File destFile = new File(".gitlet/" + currentBranch + "/" + (count_id + 1) + "_" + f.getName());
			if (inRebase == false) {
				try {
					Files.copy(f.toPath(), destFile.toPath());
				} catch (IOException e) {
					System.out.println("unable to copy files when committing.");
				}
			}
			trackingFiles.put(f.getName(), count_id + 1);
			addedFiles_with_newPath.add(destFile);
			commitedFiles.add(destFile);
		}
		count_id += 1;
		commitTree = new Node<HashSet<File>>(pointer, count_id, message, currentBranch, addedFiles_with_newPath, time);
		pointer = commitTree;
		nodes.add(pointer);
		addedFiles.clear();
		removedFiles.clear();
		branchTrack.put(currentBranch, commitTree);
		pointerTrack.put(count_id, commitTree);
		pointerTrack2.put(commitTree, count_id);
		ifConflict = false;
	}

	/**
	 * Marks a file for untracking, or if it had been staged, removes it from the staging area without marking
	 * it for untracking.
	 * 
	 * @param fileName
	 * 				a string denoting the file to be removed
	 */
	public void remove(String fileName) {
		File f = new File(fileName);
		if (!addedFiles.contains(f) && !trackingFiles.containsKey(fileName)) {
			System.out.println("No reason to remove the file.");
			return;
		}
		if (addedFiles.contains(f)) {
			addedFiles.remove(f);
			return;
		}
		if (trackingFiles.containsKey(fileName)) {
			trackingFiles.remove(fileName);
		}
		removedFiles.add(f);
	}
	
	/**
	 * Displays information about each commit starting from the current head commit till the initial commit.
	 */
	public void log() {
		Node<HashSet<File>> p = pointer;
		while (p != null) {
			System.out.println("===");
			System.out.println("Commit " + pointerTrack2.get(p));
			System.out.println(p.time);
			System.out.println(p.message);
			System.out.println();
			p = p.pre;
		}
	}
	
	/**
	 * Displays information about all commits ever made.
	 */
	public void globalLog() {
		for (Node<HashSet<File>> n : nodes) {
			System.out.println("===");
			System.out.println("Commit " + pointerTrack2.get(n));
			System.out.println(n.time);
			System.out.println(n.message);
			System.out.println();
		}
	}
	
	/**
	 * Prints out the id of the commit(s) that has the given commit message.
	 * 
	 * @param message
	 * 				a string denoting the message that should be searched for
	 */
	public void find(String message) {
		int count = 0;
		for (Node<HashSet<File>> n : nodes) {
			if (n.message.equals(message)) {
				System.out.println(pointerTrack2.get(n));
				count++;
			}
		}
		if (count == 0) {
			System.out.println("Foundno commit with that message");
			return;
		}

	}
	
	/**
	 * Displays the branches that currently exist and the files that have been staged or marked for untracking,
	 * marking the current branch with a *.
	 */
	public void status() {
		Set<String> branch = branchTrack.keySet();
		System.out.println("=== Branches ===");
		for (String b : branch) {
			if (b.equals(currentBranch)) {
				System.out.println("*" + b);
			} else {
				System.out.println(b);
			}
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		for (File f : addedFiles) {
			System.out.println(f.getName());
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (File f : removedFiles) {
			System.out.println(f.getName());
		}
	}

	/**
	 * Takes the version of the file as it exists in the head commit and puts it in the working directory, 
	 * overwriting the version of the file that's already there if there is one. If a branch name is given, 
	 * takes all files in the commit at the head of the given branch and puts them in the working directory, 
	 * changing the current branch to the given branch.
	 * 
	 * @param fOrBName
	 * 				a string denoting the filename or branch name to be put in the working directory
	 */
	public void checkout(String fOrBName) {
		if (!branchTrack.containsKey(fOrBName) && pointer.item == null) {
			System.out.println("File does not exist in the most recent commit, or no such branch exists.");
			return;
		}
		if (branchTrack.containsKey(fOrBName)) {
			if (fOrBName.equals(currentBranch)) {
				System.out.println("No need to checkout the current branch.");
				return;
			} else {
				pointer = branchTrack.get(fOrBName);
				currentBranch = fOrBName;
				return;
			}
		}

		String parent = "";
		if (fOrBName.contains("/")) {
			parent = fOrBName.split("/")[0];
			fOrBName = fOrBName.split("/")[1];
		}

		for (File f : pointer.item) {
			System.out.println(fOrBName);
			fOrBName = pointer.id + "_" + fOrBName;
			if (f.getName().equals(fOrBName)) {
				try {
					if (parent.equals("")) {
						String newname = fOrBName.split("_")[1];
						File n = new File(newname);
						Files.copy(f.toPath(), n.toPath(), StandardCopyOption.REPLACE_EXISTING);
					} else {
						String newname = fOrBName.split("_")[1];
						File n = new File(parent + "/" + newname);
						Files.copy(f.toPath(), n.toPath(), StandardCopyOption.REPLACE_EXISTING);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		System.out.println("File does not exist in the most recent commit, or no such branch exists.");
		return;

	}

	/**
	 * Takes the version of the file as it exists in the commit with the given id, and puts it in the working 
	 * directory, overwriting the version of the file that's already there if there is one.
	 * 
	 * @param id
	 * 		an int denoting the id of the commit to navigate
	 * @param fileName
	 * 		a string denoting the filename to be put in the working directory
	 */
	public void checkout(int id, String fileName) {
		fileName = id + "_" + fileName;
		if (pointerTrack.containsKey(id)) {
			HashSet<File> h = (HashSet<File>) pointerTrack.get(id).item;
			for (File f : h) {
				if (f.getName().equals(fileName)) {
					try {
						String newname = fileName.substring(2);
						File n = new File("./", newname);
						Files.copy(f.toPath(), n.toPath(), StandardCopyOption.REPLACE_EXISTING);
						return;
					} catch (IOException e) {
						e.printStackTrace();
					}
					return;
				}
			}
			System.out.println("File does not exist in that commit.");
			return;
		}
		System.out.println("No commit with that id exists.");
		return;
	}

	/**
	 * Creates a new branch with the given name, and points it at the current head node.
	 * 
	 * @param branchName
	 * 				a string denoting the name of the branch that will be created.
	 */
	public void branch(String branchName) {
		if (branchTrack.containsKey(branchName)) {
			System.out.println("A branch with that name already exists.");
			return;
		}
		File branchFolder = new File(".gitlet/" + branchName);
		branchFolder.mkdirs();
		Node<HashSet<File>> newPointer = pointer;
		branchTrack.put(branchName, newPointer);
		for (File f : pointer.item) {
			File to = new File(".gitlet/" + branchName + "/" + f.getName());
			try {
				Files.copy(f.toPath(), to.toPath());
			} catch (IOException e) {
				System.out.println("unable to copy files when committing.");
			}
			
		}
	}

	/**
	 * Deletes the branch with the given name.
	 * 
	 * @param branchName
	 * 				a string denoting the name of the branch that will be removed.
	 */
	public void rmBranch(String branchName) {
		if (!branchTrack.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (currentBranch.equals(branchName)) {
			System.out.println("Cannot remove the current branch.");
			return;
		}
		branchTrack.remove(branchName);
	}

	/**
	 * Checks out all the files tracked by the given commit, moving the current branch's head to that commit 
	 * node.
	 * 
	 * @param commitId
	 * 				an integer denoting the given commit
	 */
	public void reset(int commitId) {
		if (!pointerTrack.containsKey(commitId)) {
			System.out.println("No commit with that id exists.");
			return;
		}
		pointer = pointerTrack.get(commitId);
		currentBranch = pointer.branch;
		commitTree = pointer;
		for (File f : pointer.item) {
			String pre = f.getName().split("_")[1];
			checkout(pre);
		}
	}

	/**
	 * Find the split node in our commit tree.
	 * @param curr
	 * Head of current Node
	 * @param given
	 * Head of given Node
	 * @return
	 * return the commit id of the split node
	 */
	public int findSplit(Node<HashSet<File>> curr, Node<HashSet<File>> given) {
		HashSet<Node<HashSet<File>>> currTrack = new HashSet<Node<HashSet<File>>>();
		while (curr != null) {
			currTrack.add(curr);
			curr = curr.pre;
		}
		for (Node<HashSet<File>> i = given; i != null; i = i.pre) {
			if (currTrack.contains(i)) {
				return i.id;
			}
		}
		return -1;

	}
	
	/**
	 * Merges files from the given branch into the current branch
	 * 
	 * @param branchName
	 * 				a string denoting the given branch to be merged
	 */
	public void merge(String branchName) {
		if (!branchTrack.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (branchName.equals(currentBranch)) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}

		int id = findSplit(pointer, branchTrack.get(branchName));
		Node<HashSet<File>> split = pointer;
		HashSet<File> fileInCurrentBranch = new HashSet<File>();
		while (split.id != id) {
			for (File f : split.item) {
				fileInCurrentBranch.add(f);
			}
			split = split.pre;
		}
		Node<HashSet<File>> split2 = branchTrack.get(branchName);
		HashSet<File> fileInGivenBranch = new HashSet<File>();
		while (split2.id != id) {
			for (File f : split2.item) {
				fileInGivenBranch.add(f);
			}
			split2 = split2.pre;
		}
		HashSet<File> confilctFiles = new HashSet<File>();
		HashSet<File> cb = copyHashSet(fileInCurrentBranch);
		HashSet<File> gb = copyHashSet(fileInGivenBranch);
		for (File f1 : cb) {
			String name1 = f1.getName().split("_")[1];
			Path path1 = f1.toPath();
			for (File f2 : gb) {
				String name2 = f2.getName().split("_")[1];
				Path path2 = f2.toPath();
				try {
					if (name1.equals(name2) && !Arrays.equals(Files.readAllBytes(path1), Files.readAllBytes(path2))) {
						if (!addedFiles.isEmpty()) {
							for (File addedfile : addedFiles) {
								if (addedfile.getName().equals(name2)) {
									fileInCurrentBranch.remove(f1);
									fileInGivenBranch.remove(f2);
								} else {
									confilctFiles.add(f2);
									fileInCurrentBranch.remove(f1);
									fileInGivenBranch.remove(f2);
								}
							}
						} else {
							confilctFiles.add(f2);
							fileInCurrentBranch.remove(f1);
							fileInGivenBranch.remove(f2);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		for (File f : fileInGivenBranch) {
			addedFiles.add(f);
		}

		for (File f : confilctFiles) {
			File ff = new File(f.getName().split("_")[1] + ".conflicted");
			try {
				Files.copy(f.toPath(), ff.toPath(),StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (confilctFiles.isEmpty()) {
			commit("Merged " + currentBranch + " with " + branchName);
		} else {
			System.out.println("Encountered a merge conflict.");
			ifConflict = true;
		}

	}
	
	/**
	 * Create a new copy of a HashSet
	 * @param origin
	 * original HashSet
	 * @return
	 * copy of the origin
	 */
	public HashSet<File> copyHashSet(HashSet<File> origin) {
		HashSet<File> copy = new HashSet<File>();
		for (File f : origin) {
			copy.add(f);
		}
		return copy;
	}
	
	/**
	 * Finds the split point of the current branch and the given branch, snaps off the current branch at this 
	 * point, then re-attaches the current branch to the head of the given branch.
	 * 
	 * @param branchName
	 * 				a string denoting the given branch to be rebased
	 */
	public void rebase(String branchName) {
		if (!branchTrack.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (branchName.equals(currentBranch)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}
		int splitId = findSplit(pointer, branchTrack.get(branchName));
		System.out.println("Split ID here : " + splitId);
		Node<HashSet<File>> start = pointer;
		ArrayList<Node<HashSet<File>>> bArray = new ArrayList<Node<HashSet<File>>>();
		if (start.id == splitId) {
			return;
		}
		while (start.id != splitId) {
			bArray.add(start);
			start = start.pre;
		}
		Node<HashSet<File>> branchcurr = branchTrack.get(branchName);
		HashSet<File> branchFiles = new HashSet<File>();
		HashSet<Node<HashSet<File>>> branchNodes = new HashSet<Node<HashSet<File>>>();
		while (branchcurr.id != splitId) {
			for (File f : (HashSet<File>) branchcurr.item) {
				branchFiles.add(f);
			}
			branchNodes.add(branchcurr);
			branchcurr = branchcurr.pre;
		}
		if (branchNodes.contains(pointer)) {
			branchTrack.put(currentBranch, branchTrack.get(branchName));
			pointer = branchTrack.get(branchName);
			return;
		}
		for (Node<HashSet<File>> b : branchNodes) {
			for (File f : (HashSet<File>) b.item) {
				File to = new File(".gitlet/" + currentBranch + "/" + f.getName());
				try {
					Files.copy(f.toPath(), to.toPath());
				} catch (IOException e) {
					System.out.println("unable to copy files when committing.");
				}
			}
		}
		branchTrack.put(currentBranch, branchTrack.get(branchName));
		pointer = branchTrack.get(branchName);
		commitTree = branchTrack.get(branchName);
		inRebase = true;
		for (int k = bArray.size() - 1; k >= 0; k--) {
			Node<HashSet<File>> trackUp = bArray.get(k);
			for (File f : (HashSet<File>) trackUp.item) {
				if (branchFiles.contains(f)) {
					continue;
				}
				addedFiles.add(f);
			}
			commit("rebase " + trackUp.id);
		}
		inRebase = false;
		
	}

	/**
	 * Starts the program and carries out a method in accordance with the given argument, which is sent to 
	 * commandRecognize.
	 * 
	 * @param args
	 * 			a string denoting the command to be made
	 */
	public static void main(String[] args) {
		Gitlet git = null;
		try {
			git = deserialize();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (git == null) {
			git = new Gitlet();
		}
		if (args.length == 0) {
			return;
		}
		File f = new File(".gitlet/");
		if (!args[0].equals("init") && !f.exists()) {
			System.out.println("you need to initialize gitlet before doing anything!");
			return;
		}
		String key = args[0];
		String[] command = new String[args.length - 1];
		System.arraycopy(args, 1, command, 0, args.length - 1);
		commandRecognize(git, key, command);
		serialize(git);
	}

	/**
	 * Calls a method on a specific instance of gitlet, depending on the given key and command.
	 * 
	 * @param git
	 * 			a gitlet whose method will be called
	 * @param key
	 * 			a string denoting the method to be called
	 * @param command
	 * 			a string denoting the arguments to the particular method to be called
	 */
	private static void commandRecognize(Gitlet git, String key, String[] command) {
		switch (key) {
		case "init":
			git.init();
			break;
		case "add":
			git.add(command[0]);
			break;
		case "commit":
			String v = command[0];
			for (String i : Arrays.copyOfRange(command, 1, command.length)) {
				v += " " + i;
			}
			git.commit(v);
			break;
		case "rm":
			git.remove(command[0]);
			break;
		case "log":
			git.log();
			break;
		case "global-log":
			git.globalLog();
			break;
		case "find":
			git.find(command[0]);
			break;
		case "status":
			git.status();
			break;
		case "branch":
			if (git.ifConflict) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			git.branch(command[0]);
			break;
		case "checkout":
			if (command.length == 1) {
				git.checkout(command[0]);
			} else {
				git.checkout(Integer.parseInt(command[0]), command[1]);
			}
			break;
		case "rm-branch":
			if (git.ifConflict) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			git.rmBranch(command[0]);
			break;
		case "reset":
			if (git.ifConflict) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			git.reset(Integer.parseInt(command[0]));
			break;
		case "merge":
			if (git.ifConflict) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			git.merge(command[0]);
			break;
		case "rebase":
			if (git.ifConflict) {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
				break;
			}
			git.rebase(command[0]);
			break;
		default:
			System.out.println("please type in valid commands");
			break;
		}
	}

	/**
	 * Deserializes the files that have been serialized.
	 * 
	 * @return 
	 * 		a gitlet that has been loaded along with its respective deserialized files.						
	 * @throws ClassNotFoundException
	 * 		if gitlet or its serialized files cannot be found.	
	 */
	private static Gitlet deserialize() throws ClassNotFoundException {
		Gitlet git = null;
		File gitlet = new File(".gitlet/git.ser");
		if (gitlet.exists()) {
			try {
				FileInputStream file = new FileInputStream(gitlet);
				ObjectInputStream object = new ObjectInputStream(file);
				git = (Gitlet) object.readObject();
				object.close();
			} catch (IOException e) {
				System.out.println("IOException while loading Gitlet.");
			}
		}
		return git;
	}

	/**
	 * Serializes the files of a given instance of gitlet
	 * 
	 * @param gitlet
	 * 			a gitlet whose files will be serialized.
	 */
	private static void serialize(Gitlet gitlet) {
		if (gitlet == null) {
			return;
		}
		try {
			File git = new File(".gitlet/git.ser");
			FileOutputStream file = new FileOutputStream(git);
			ObjectOutputStream object = new ObjectOutputStream(file);
			object.writeObject(gitlet);
			object.close();
		} catch (IOException e) {
			String msg = "IOException while saving git.";
			System.out.println(msg);
		}
	}

}
