import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Gitlet implements Serializable {

	private CommitMap map;
	private boolean initialized = false;
	private boolean conflict = false;
	private ArrayList<String> untrack;
	private CommitNode current; // last commitNode
	private String currentBranch;

	/**
	 * check if Gitlet is initialized
	 * 
	 * @return
	 */
	public boolean isInitialized() {
		return initialized;
	}

	/**
	 * check if Gitlet is in Conflict state
	 * 
	 * @return
	 */
	public boolean isConflict() {
		return conflict;
	}

	/**
	 * initialize Gitlet
	 */
	public void init() {
		if (initialized == true) {
			System.err.println("A gitlet version control system already exists in the current directory.");
		} else {
			File temp = new File(".gitlet");
			temp.mkdir();
			File temp2 = new File(temp, "/staging");
			temp2.mkdir();
			File temp3 = new File(temp, "/initial commit");
			temp3.mkdir();
			map = new CommitMap();
			initialized = true;
			untrack = new ArrayList<String>();
			current = map.getfirst();
			currentBranch = "master";
		}
	}

	/**
	 * add a file to staging area and start tracking the file
	 * 
	 * @param filename
	 */
	public void add(String filename) {
		// add new file to staging area
		File temp1 = new File(filename);
		if (!temp1.exists()) {
			System.err.println("File does not exist.");
			return;
		}
		if (untrack.contains(temp1.getAbsolutePath())) {
			untrack.remove(temp1.getAbsolutePath());
			return;
		}
		File temp2 = new File(".gitlet/staging/" + filename);
		try {
			Files.copy(temp1.toPath(), temp2.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * commit a file with a message
	 * 
	 * @param message
	 */
	public void commit(String message) {
		File temp = new File(".gitlet/staging");
		File[] list = temp.listFiles();
		if (list.length == 0 && untrack.isEmpty() && initialized == true) {
			System.out.println("No changes added to the commit.");
			return;
		}
		HashSet<String> inputfilepath = new HashSet<String>();
		// avoid initial commit and empty commit node
		if (current.getId() != 0 && current.filesize() != 0) {
			inputfilepath = (HashSet<String>) current.getPath().clone();
		}
		// take out untrack
		for (String s : untrack) {
			if (inputfilepath.contains(s)) {
				inputfilepath.remove(s);
			}
		}
		// construct commitNode and its folder
		CommitNode node = new CommitNode(message, currentBranch, inputfilepath, current, map.size());
		File folder = new File(".gitlet/commit " + map.size());
		folder.mkdir();
		map.commitNode(node);
		// move stage folder contents to its folder
		for (File f : list) {
			try {
				Files.move(f.toPath(), new File(folder, "/" + f.getName()).toPath(),
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// update node filepathlist
		node.addFile(folder.listFiles());
		untrack.clear();
		current = map.getLast();
		conflict = false;
	}

	/**
	 * untrack a file
	 * 
	 * @param filename
	 */
	public void rm(String filename) {
		File temp = new File(".gitlet/staging/" + filename);
		if (temp.exists()) {
			try {
				Files.delete(temp.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		if (!current.contains(filename) && !temp.exists()) {
			System.err.println("No reason to remove the file.");
			return;
		}
		if (current.contains(filename)) {
			untrack.add(current.getFilePath(filename));
		}
	}

	/**
	 * check all commits in the current branch
	 */
	public void log() {
		current.log();
	}

	/**
	 * check all commits
	 */
	public void global_log() {
		map.log();
	}

	/**
	 * find commit with a commitmessage
	 * 
	 * @param commitmessage
	 */
	public void find(String commitmessage) {
		LinkedList<CommitNode> temp = map.getMessage(commitmessage);
		if (temp == null) {
			System.out.println("Found no commit with that message.");
		} else {
			for (CommitNode c : temp) {
				System.out.println(c.getId());
			}
		}
	}

	/**
	 * check current status
	 */
	public void status() {
		map.status(currentBranch);
		System.out.println("=== Staged Files ===");
		File temp = new File(".gitlet/staging");
		File[] list = temp.listFiles();
		if (list != null && list.length != 0) {
			for (File f : list) {
				System.out.println(f.getName());
			}
		} else {
			System.out.println("None");
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		if (untrack.size() != 0) {
			for (String s : untrack) {
				Path p = Paths.get(s);
				// untrack contains both paths and name
				System.out.println(p.getFileName().toString());
			}
		} else {
			System.out.println("None");
		}
	}

	/**
	 * checkout a file
	 * 
	 * @param name
	 *            a string which can be the name of the file in the last commit
	 *            or branchname
	 */
	public void checkout(String name) {
		if (currentBranch.equals(name)) {
			System.out.println("No need to checkout the current branch.");
			return;
		}
		// temp is the pathname that needs to be copied to the workspace
		HashSet<String> temp = new HashSet<String>();
		if (current.contains(name)) {
			// getting path of a file
			temp.add(current.getFilePath(name));
		} else if (map.getBranch(name) != null) {
			if (!map.getBranch(name).isEmpty()) {
				// getting path of the files of the node
				temp.addAll(map.getBranch(name).get(0).getPath());
			}
			// change pointer to the branch
			currentBranch = name;
		} else {
			System.out.println("File does not exist in the most recent commit, or no such branch exists");
			return;
		}

		for (String s : temp) {
			Path p = Paths.get(s);
			File to = new File(p.getFileName().toString());
			try {
				Files.copy(new File(s).toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * checkout a file in a specific commit
	 * 
	 * @param commitid
	 * @param filename
	 */
	public void checkout(String commitid, String filename) {
		if (map.getNode(Integer.parseInt(commitid)) == null) {
			System.out.println("No commit with that id exists.");
			return;
		}
		if (!map.getNode(Integer.parseInt(commitid)).contains(filename)) {
			System.out.println("File does not exist in that commit.");
		} else {
			File from = new File(map.getNode(Integer.parseInt(commitid)).getFilePath(filename));
			File to = new File(from.getName());
			try {
				Files.copy(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * make a new branch
	 * 
	 * @param branchname
	 */
	public void branch(String branchname) {
		if (map.getBranch(branchname) != null) {
			System.err.println("A branch with that name already exists.");
		} else {
			map.branch(branchname);
		}
	}

	/**
	 * remove a branch
	 * 
	 * @param branchname
	 */
	public void rm_branch(String branchname) {
		if (map.getBranch(branchname) == null) {
			System.err.println("A branch with that name does not exist.");
		} else if (currentBranch.equals(branchname)) {
			System.err.println("Cannot remove the current branch");
		} else {
			map.rmBranch(branchname);
		}

	}

	/**
	 * checkout the previous commit and change pointer to its branch
	 * 
	 * @param commitid
	 */
	public void reset(String commitid) {
		CommitNode temp = map.getNode(Integer.parseInt(commitid));
		if (temp == null) {
			System.out.println("No commit with that id exists.");
		} else {
			for (String s : temp.getPath()) {
				Path p = Paths.get(s);
				checkout(commitid, p.getFileName().toString());
			}
			current = map.getBranch(temp.getBranch()).getFirst();
			currentBranch = temp.getBranch();
		}
	}

	/**
	 * combine two branches
	 * 
	 * @param branchname
	 */
	public void merge(String branchname) {
		if (map.getBranch(branchname) == null) {
			System.err.println("A branch with that name does not exist.");
			return;
		}
		if (currentBranch.equals(branchname)) {
			System.err.println("Cannot merge a branch with itself.");
			return;
		}
		CommitNode split = map.getBranch(branchname).getLast().getPrev();
		CommitNode head = map.getBranch(branchname).getFirst();
		// files modified in the Branch
		HashSet<String> mb = new HashSet<String>();
		for (String s : head.getPath()) {
			if (!split.getPath().contains(s)) {
				mb.add(s);
			}
		}
		// filenames modified in current
		HashSet<String> nc = new HashSet<String>();
		for (String s : current.getPath()) {
			Path p = Paths.get(s);
			if (!split.getPath().contains(s)) {
				nc.add(p.getFileName().toString());
			}
		}
		// files modified in the Branch but not in current
		HashSet<String> temp = new HashSet<String>(mb);
		// files modified in both Branch
		HashSet<String> both = new HashSet<String>();
		for (String s : mb) {
			Path p = Paths.get(s);
			if (nc.contains(p.getFileName().toString())) {
				temp.remove(s);
				both.add(s);
			}
		}
		for (String s : temp) {
			Path p = Paths.get(s);
			File to = new File(p.getFileName().toString());
			File to2 = new File(".gitlet/staging/" + p.getFileName().toString());
			File from = new File(s);
			try {
				Files.copy(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
				Files.copy(from.toPath(), to2.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (both.isEmpty()) {
			commit("Merged" + currentBranch + "with" + branchname);
			return;
		}
		for (String s : both) {
			Path p = Paths.get(s);
			s = p.getFileName().toString();
			File to = new File(
					s.substring(0, s.lastIndexOf(".")) + ".conflicted" + s.substring(s.lastIndexOf("."), s.length()));
			File from = new File(s);
			try {
				Files.copy(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Encountered a merge conflict.");
		conflict = true;
	}

	/**
	 * combine two branches
	 * 
	 * @param branchname
	 */
	public void rebase(String branchname) {
		if (map.getBranch(branchname) == null) {
			System.err.println("A branch with that name does not exist.");
			return;
		}
		if (currentBranch.equals(branchname)) {
			System.err.println("Cannot rebase a branch onto itself.");
			return;
		}
		// store the current branch and change current branch
		LinkedList<CommitNode> l = new LinkedList<CommitNode>(map.getBranch(current.getBranch()));
		current = map.getBranch(branchname).getFirst();
		currentBranch = branchname;
		Iterator<CommitNode> temp = l.descendingIterator();

		while (temp.hasNext()) {
			CommitNode node = temp.next();
			CommitNode node2 = new CommitNode(node.getMessage(), branchname, current.getPath(), current, map.size());
			map.commitNode(node2);
			current = map.getLast();
			// new folder
			File folder = new File(".gitlet/commit " + node2.getId());
			folder.mkdir();
			// copy files
			File[] list = new File(".gitlet/commit " + node.getId()).listFiles();
			if (list != null && list.length != 0) {
				for (File f : list) {
					try {
						Files.copy(f.toPath(), new File(folder, "/" + f.getName()).toPath(),
								StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				// update node filepathlist
				node2.addFile(folder.listFiles());
			}
		}
		current = map.getLast();
		map.rmBranch(branchname);
	}

	/**
	 * It takes calls to the methods, and saves the object after each each call.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Gitlet a = new Gitlet();
		File b = new File(".gitlet");
		if (b.exists()) { // load file
			try {
				FileInputStream fileStream = new FileInputStream("Gitlet.ser");
				ObjectInputStream is = new ObjectInputStream(fileStream);
				a = (Gitlet) is.readObject();
				is.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		if (args.length == 0) { // make call and input
			System.out.println("Please enter a command.");
			return;
		}

		String[] input;
		if (args.length != 1) {
			input = new String[args.length - 1];
			for (int i = 1; i < args.length; i++) {
				input[i - 1] = args[i];
			}
		} else {
			input = null;
		}

		String call = args[0];
		// commit message could be a sentence
		if (call.equals("init") && input == null) {
			a.init();
		} else if (!call.equals("init") && a.isInitialized() == false) {
			System.err.println("Initialize first.");
		} else if ((call.equals("branch") || call.equals("rm-branch") || call.equals("reset") || call.equals("merge")
				|| call.equals("rebase")) && (a.isConflict() == true)) {
			System.err.println("Cannot do this command until the merge conflict has been resolved.");
		} else if (call.equals("add") && input.length == 1) {
			a.add(input[0]);
		} else if (call.equals("commit") && input == null) {
			System.out.println("Please enter a commit message.");
		} else if (call.equals("commit") && input.length == 1) {
			a.commit(input[0]);
		} else if (call.equals("rm") && input != null) {
			a.rm(input[0]);
		} else if (call.equals("log") && input == null) {
			a.log();
		} else if (call.equals("global-log")) {
			a.global_log();
		} else if (call.equals("find") && input.length == 1) {
			a.find(input[0]);
		} else if (call.equals("status")) {
			a.status();
		} else if (call.equals("checkout") && input.length == 1) {
			a.checkout(input[0]);
		} else if (call.equals("checkout") && input.length == 2) {
			a.checkout(input[0], input[1]);
		} else if (call.equals("branch") && input.length == 1) {
			a.branch(input[0]);
		} else if (call.equals("rm-branch") && input.length == 1) {
			a.rm_branch(input[0]);
		} else if (call.equals("reset") && input.length == 1) {
			a.reset(input[0]);
		} else if (call.equals("merge") && input.length == 1) {
			a.merge(input[0]);
		} else if (call.equals("rebase") && input.length == 1) {
			a.rebase(input[0]);
		} else {
			System.out.println("Invalid Method!");
		}

		try { // save file
			FileOutputStream fileStream = new FileOutputStream("Gitlet.ser");
			ObjectOutputStream os = new ObjectOutputStream(fileStream);
			os.writeObject(a);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}