import java.io.Serializable;
import java.util.ArrayList;

public class GitletTree implements Serializable{
	
	private Commit commit; 
	private GitletTree parent; 
	private ArrayList<GitletTree> branches; 
	
	/**Constructs a GitletTree object. 
	 * 
	 */
	public GitletTree() {
		parent = null; 
		branches = new ArrayList<GitletTree>();
	}
	
	/**Constructs a GitletTree object with the given commit. 
	 * 
	 * @param c: Commit object, commit desired.  
	 */
	public GitletTree(Commit c) {
		commit = c;
		parent = null;
		branches = new ArrayList<GitletTree>();
	}
	
	/**Constructs a Gitlet Tree object with the given commit and Gitlet Tree. 
	 * 
	 * @param c: Commit object. 
	 * @param p: GitletTree object; 
	 */
	public GitletTree(Commit c, GitletTree p) {
		commit = c; 
		parent = p;
		branches = new ArrayList<GitletTree>();
	}
	
	/**Constructs a GitletTree with the given commit, Gitlet Tree, and ArrayList. 
	 * 
	 * @param c: Commit object. 
	 * @param p: GitletTree object. 
	 * @param b: ArrayList of GitletTrees. 
	 */
	public GitletTree(Commit c, GitletTree p, ArrayList<GitletTree> b) {
		commit = c;
		parent = p;
		branches = b; 
	}
	
	/**Adds a branch to the GitletTree. 
	 * 
	 * @param t: Gitlet Tree to add the branch to. 
	 */
	public void addBranch(GitletTree t) {
		if (t!= null) {
			branches.add(t); 
			t.parent = this;
		}
	}
	
	/**Checks if the GitletTree is empty. 
	 * 
	 * @return true if the GitletTree is empty; false if the GitletTree isn't. 
	 */
	public boolean isEmpty() {
		return branches.isEmpty(); 
	}
	
	/**
	 * Formats each commit's ID, date and message and displays them. 
	 */
	public void globalLogHelper() {
		System.out.println(commit.logHelper());
		for (GitletTree t : branches) {
			t.globalLogHelper(); 
		}
	}
	
	/** checks if this tree contains a commit with the given id
	 * @param id: integer, commit ID. 
	 * @return true if the tree contains the commit with the ID; false if the tree doesn't. 
	 */
	public boolean contains(int id) {
		if (commit.getId() == id) {
			return true;
		} else {
			if (this.isEmpty()) {
				return false;
			} else {
				for (GitletTree t : branches) {
					if (t.contains(id)) {
						return true;
					}
				}
				return false;
			}
		}
	}
	
	/**Prints the tree. 
	 * 
	 */
	public void printTree() {
		printTreeHelper(0);
	}
	
	/**Helps print the tree. 
	 * 
	 * @param level: integer. 
	 */
	public void printTreeHelper(int level) {
    	for (int i = 0; i < level*4; i++) {
    		System.out.print(" "); 
    	}
    	System.out.println(this.commit.getId());
    	for (GitletTree t: branches) {
    		t.printTreeHelper(level + 1);
    	}
    }
	
	/**
	 * returns a GitletTree with a commit with a matching id 
	 * @param id
	 * @return a GitletTree object with the commit with the matching ID.
	 * null if the ID cannot be found.  
	 */
	public GitletTree findById(int id) {
		if (commit.getId() == id) {
			return this; 
		} else {
			GitletTree tree;
			for (GitletTree t: branches) {
				tree = t.findById(id);
				if (tree != null) {
					return tree;
				}
			}
		}
		return null;
	}
	
	/**
	 * returns an ArrayList of GitletTrees whose messages match the given message
	 * @param message
	 * @return an ArrayList of Gitlet Trees that have the given message. 
	 */
	public ArrayList<GitletTree> findByMessage(String message) {
		ArrayList<GitletTree> trees = new ArrayList<GitletTree>(); 
		if (commit.getMessage().equals(message)) {
			trees.add(this);
		}
		for (GitletTree t: branches) {
			trees.addAll(t.findByMessage(message)); 
		}
		return trees;
	}
	
	/**
	 * finds first common ancestor given t1 and t2 are in the same tree
	 * @param t1: GitletTree object
	 * @param t2: GitletTree object
	 * @return GitletTree object that is the split pint of t1 and t2
	 */
	public static GitletTree findSplit(GitletTree t1, GitletTree t2) {
		GitletTree t1Ancestor = t1; 
		while (t1Ancestor.getBranches().size() == 1) {
			t1Ancestor = t1Ancestor.getParent();
		}
		
		GitletTree t2Ancestor = t2;
		while (t2Ancestor.getBranches().size() == 1) {
			t2Ancestor = t2Ancestor.getParent(); 
		}
		
		if (t1Ancestor == t2Ancestor) {
			return t1Ancestor; 
		} else if (t1Ancestor.getCommit().getId() < t2Ancestor.getCommit().getId()){
			return findSplit(t1Ancestor, t2Ancestor.getParent());
		} else {
			return findSplit(t2Ancestor, t1Ancestor.getParent());
		}
	}
	
	/**Checks if the GitletTree contains the given branch. 
	 * 
	 * @param branch: desired branch. 
	 * @return true if the given branch is in the GitletTree, false otherwise. 
	 */
	public boolean containsChild (GitletTree branch) {
		GitletTree temp = this;
		while(temp != null) {
			if(temp == branch) {
				return true;
			}
			temp = temp.parent;
		}
		return false;
	}
	
	public void setCommit(Commit c) {
		commit = c; 
	}
	
	public void setBranches(ArrayList<GitletTree> b) {
		branches = b; 
	}
	
	public void setParent(GitletTree t) {
		parent = t; 
		parent.branches.add(this);
	}
	
	public Commit getCommit() {
		return commit;
	}
	
	public ArrayList<GitletTree> getBranches() {
		return branches;
	}
	
	public GitletTree getParent() {
		return parent;
	}
	
}