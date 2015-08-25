import java.io.*;
import java.text.*;
import java.util.*;
import java.nio.file.*;

public class Tree implements Serializable {
	private static final long serialVersionUID = -960754896891849353L;

	public String name; // the name of the branch
	public String currentBranch; // the current branches name
	public List<Node> prevHeads; // list of all previous commits of the branch
	public ArrayList<Node> allCommits; // list of all commits
	public HashMap<String, Tree> branches; // list of all branches
	public HashMap<String, ArrayList<Node>> commitMessage; // locate files from
															// commit message
	public HashMap<String, File> trackingFiles; // locate files based on file
												// name
	public int counter; // internal counter of commits
	public Node myHead; // head of the current Branch
	public HashMap<String, File> staged; // all the files in staging
	public ArrayList<String> removal; // files marked for untracking

	/**
	 * Constructor for Tree class.
	 */
	public Tree() {
		commitMessage = new HashMap<String, ArrayList<Node>>();
		this.prevHeads = new ArrayList<Node>();
		allCommits = new ArrayList<Node>();
		branches = new HashMap<String, Tree>();
		staged = new HashMap<String, File>();
		removal = new ArrayList<String>();
		currentBranch = this.name = "master";
		branches.put(this.name, this);
		counter = 0;
		trackingFiles = new HashMap<String, File>();
	}

	/**
	 * Constructor for the Tree class that takes in two arguments.
	 */
	public Tree(Tree input, String name) {
		commitMessage = input.commitMessage;
		int newHead = input.prevHeads.indexOf(input.myHead);
		this.prevHeads = new ArrayList<Node>(
				input.prevHeads.subList(0, newHead));
		allCommits = input.allCommits;
		this.name = name;
		this.myHead = input.myHead;
		this.prevHeads.add(this.myHead);

		branches = input.branches;
		staged = input.staged;
		removal = input.removal;
		currentBranch = input.currentBranch;
		branches.put(this.name, this);
		counter = input.counter;
		trackingFiles = input.trackingFiles;

	}

	/**
	 * The name of current branch
	 * 
	 * @return-current branch name.
	 */
	public String name() {
		return this.name;
	}

	/**
	 * Gets the current branch.
	 * 
	 * @return-the current branch.
	 */
	public Tree retrieveBranch() {
		return branches.get(currentBranch);
	}

	/**
	 * Changes current branch another branch.
	 * 
	 * @param var
	 *            -other branch name.
	 */
	public void changeBranch(String var) {
		if (branches.containsKey(var)) {
			currentBranch = var;
		}
	}

	/**
	 * Creates a branch.
	 * 
	 * @param curr
	 *            - current tree.
	 * @param newName
	 *            -name of the new branch.
	 */
	public void makeBranch(Tree curr, String newName) {
		if (branches.containsKey(newName)) {
			System.out.println("A branch with that name already exists.");
		} else {
			Tree newTree = new Tree(curr, newName);
			branches.put(newName, newTree);
		}
	}

	/**
	 * Adds a Node to the tree.
	 * 
	 * @param obj
	 *            -the key
	 * @param rtn
	 *            - the value
	 */
	public void addNode(HashMap<String, File> obj, String rtn) {
		Node temp = new Node(obj, rtn);
		this.myHead = temp;
		prevHeads.add(myHead);
		allCommits.add(temp);
		counter++;
		if (commitMessage.containsKey(rtn)) {
			ArrayList currList = commitMessage.get(rtn);
			currList.add(temp);
			commitMessage.put(rtn, currList);
		} else {
			ArrayList<Node> tomagachi = new ArrayList<Node>();
			tomagachi.add(temp);
			commitMessage.put(rtn, tomagachi);
		}
	}

	/**
	 * Prints everything for the log.
	 */
	public void print() {
		for (int i = prevHeads.size() - 1; i >= 0; i--) {
			System.out.println("===");
			System.out.println("commit " + prevHeads.get(i).ID());
			System.out.println(prevHeads.get(i).date());
			System.out.println(prevHeads.get(i).commit());
		}
	}

	/**
	 * Prints everything for global-log.
	 */
	public void printAll() {
		for (int i = allCommits.size() - 1; i >= 0; i--) {
			System.out.println("===");
			System.out.println("commit " + allCommits.get(i).ID());
			System.out.println(allCommits.get(i).date());
			System.out.println(allCommits.get(i).commit());
		}
	}

	/**
	 * Prints the ID.
	 * 
	 * @param message
	 */
	public void commitPrint(String message) {
		ArrayList<Node> hitler = (this.commitMessage.get(message));
		for (Node jew : hitler) {
			System.out.print(jew.ID + "");
		}
	}

	/**
	 * Gets the Node from the tree.
	 * 
	 * @param number
	 *            -ID
	 * @param fileName
	 *            -Name of file.
	 */
	public void getNode(String number, String fileName) {
		int ID = Integer.parseInt(number);
		if (ID < 0 || ID > allCommits.size()) {
			System.out.println("No commit with that id exists.");
			return;
		}
		Node myNode = allCommits.get(ID);
		if (!myNode.getItem().containsKey(fileName)) {
			System.out.println("File does not exist in that commit.");
		} else {
			int num = myNode.ID();
			File replace = myNode.getItem().get(fileName);
			CopyOption[] temp = new CopyOption[1];
			temp[0] = StandardCopyOption.REPLACE_EXISTING;
			if (myNode.getItem().containsKey(fileName)) {
				Path one = Paths.get(".gitlet/commit" + num + "/"
						+ replace.getName());
				Path two = replace.toPath();
				try {
					Files.copy(one, two, temp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Changes the working directory files.
	 * 
	 * @param name
	 *            -fileName
	 */
	public void checkoutHead(String name) {
		HashMap<String, File> items = this.myHead.getItem();
		if (name.equals(currentBranch)) {
			System.out.println("No need to check out the current branch");
			return;
		}
		if (items.containsKey(name)) {
			File search = items.get(name);
			CopyOption[] temp = new CopyOption[1];
			temp[0] = StandardCopyOption.REPLACE_EXISTING;
			int num = this.myHead.ID();
			Path one = Paths.get(".gitlet/commit" + num + "/"
					+ search.getName());
			Path two = search.toPath();
			try {
				System.out.println(one);
				System.out.println(two);
				Files.copy(one, two, temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (branches.containsKey(name) && !name.equals(currentBranch)) {
			System.out.println("No need to check out current branch.");
			Tree nowTree = branches.get(name);
			this.currentBranch = nowTree.name;
			nowTree.currentBranch = nowTree.name;
			nowTree.counter = this.counter;
			nowTree.allCommits = this.allCommits;
			CopyOption[] temp = new CopyOption[1];
			temp[0] = StandardCopyOption.REPLACE_EXISTING;
			int num = this.myHead.ID();
			for (Object k : nowTree.myHead.getItem().keySet().toArray()) {
				File torque = nowTree.myHead.getItem().get(k);
				Path one = Paths.get(".gitlet/commit" + num + "/"
						+ torque.getName());
				Path two = torque.toPath();
				try {
					Files.copy(one, two, temp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out
					.println("File does not exist in that commit, or no such branch exists.");
		}
	}

	/**
	 * Moves the pointer.
	 * 
	 * @param number
	 *            -ID.
	 */
	public void reset(String number) {
		int temp = Integer.parseInt(number);
		Node thisNode = allCommits.get(temp);
		this.myHead = thisNode;
		for (Object a : thisNode.getItem().keySet().toArray()) {
			checkoutHead((String) a);
			System.out.println("checking out" + a);
		}
	}

	/**
	 * Copies the file from branchName into your current branch.
	 * 
	 * @param branchName
	 *            -name of branch
	 */
	public void merge(String branchName) {
		if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (currentBranch.equals(branchName)) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}
		Tree other = branches.get(branchName);
		Node splitPoint = null;
		for (Node n : other.prevHeads) {
			if (this.prevHeads.contains(n)) {
				splitPoint = n;
			}
		}
		if (splitPoint != null) {

		}

	}

	/**
	 * Attaches given branch to current branch
	 * 
	 * @param branchName
	 *            -name of Branch
	 */
	public void rebase(String branchName) {
		System.out.println("here");
		if (!branches.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (branchName.equals(currentBranch)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}
		if (prevHeads.contains(branches.get(branchName).myHead)) {
			System.out.println("Already up-to-date.");
			return;
		}
		if (branches.get(branchName).prevHeads.contains(branches
				.get(currentBranch))) {
			currentBranch = branchName;
			return;
		}
		Tree other = branches.get(branchName);
		Node splitPoint = null;
		for (Node n : other.prevHeads) {
			if (this.prevHeads.contains(n)) {
				splitPoint = n;
			}
		}
		System.out.println(splitPoint.ID());
		if (splitPoint != null) {
			this.counter = other.counter;
			int i = other.prevHeads.indexOf(splitPoint);
			List<Node> toCopy = this.prevHeads.subList(i + 1,
					other.prevHeads.size() - 1);
			Node one = other.myHead;
			other.counter = this.counter;
			for (Node n : toCopy) {
				other.addNode(new HashMap<String, File>(n.getItem()),
						new String(n.message()));
			}
			this.prevHeads = other.prevHeads;
			this.myHead = this.prevHeads.get(this.prevHeads.size() - 1);
			other.myHead = one;
		}
	}

	private class Node implements Serializable {
		private String timeDate;
		private HashMap<String, File> myItem;
		private String commitMessage;
		private int ID;
		private static final long serialVersionUID = 123456L;

		/**
		 * Constructor of Node class
		 * 
		 * @param item
		 *            -the key
		 * @param var
		 *            -the commit message.
		 */
		public Node(HashMap<String, File> item, String var) {
			timeDate = getDate();
			myItem = item;
			commitMessage = var;
			ID = counter;

		}

		/**
		 * message
		 * 
		 * @return-commit message
		 */
		public String message() {
			// TODO Auto-generated method stub
			return commitMessage;
		}

		/**
		 * commit message
		 * 
		 * @return-commit message
		 */
		public String commit() {
			return commitMessage;
		}

		/**
		 * The ID.
		 * 
		 * @return-ID
		 */
		public int ID() {
			return ID;
		}

		/**
		 * The date you committed
		 * 
		 * @return-the date you committed
		 */
		public String date() {
			return timeDate;
		}

		/**
		 * 
		 * @return
		 */
		public HashMap<String, File> getItem() {
			return myItem;
		}

		/**
		 * Gets the exact time/date of when you committed
		 * 
		 * @return- the exact time/date
		 */
		private String getDate() {
			DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			return dateformat.format(date);
		}
	}
}
