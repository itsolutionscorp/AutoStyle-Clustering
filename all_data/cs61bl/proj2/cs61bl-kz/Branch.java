import java.io.Serializable;

/* Branch.java */

/**
 * Represents a branch of the gitlet version control system. Every branch knows
 * its "tail" or where it's first difference from another branch, and its
 * current head. Add additional info here.
 * 
 * @authors Alan Kwok, Pratyusha Gogulapati, Ranju Subramani, Timothy Guan
 */
public class Branch implements Serializable{
	/**
	 * Define any instance variables here. If any class variables arise, add on
	 * top of this comment.
	 */
	private String branchName;
	private CommitTreeNode head;
	private CommitTreeNode tail;
	
	/**
	 * Constructs a new branch. ADD DETAILS HERE.
	 * 
	 * @param name
	 *            Name of the branch
	 * @param start
	 *            The commitTreeNode that begins the branch.
	 */
	public Branch(String name, CommitTreeNode start){
		this.branchName = name;
		this.head = start;
		this.tail = start;
	}
	
	/**
	 * Returns the Branch Name
	 * 
	 * @return branch name
	 */
	public String getBranchName() {
		return this.branchName;
	}

	/**
	 * Returns the start of this branch (first CommitTreeNode to differ from
	 * another branch).
	 * 
	 * @return tail of this branch
	 */
	public CommitTreeNode getTail() {
		return this.tail;
	}
	
	/**
	 * Returns the current commitTreeNode of this branch. (The latest one.)
	 * 
	 * @return head of this branch
	 */
	public CommitTreeNode getHead() {
		return this.head;
	}
	
	/**
	 * Changes the current head of this branch to newHead, then returns it.
	 * 
	 * @param newHead
	 *            The new CommitTreeNode that will be the head.
	 * 
	 * @return newHead the CommitTreeNode passed in.
	 */
	public CommitTreeNode setHead(CommitTreeNode newHead) {
		this.head = newHead;
		return newHead;
	}
	
	/**
	 * Returns a String with all the information of the branch. Should be
	 * printed rather than compared. NEVER COMPARE
	 * 
	 * @return "BranchPrintEnd"
	 */
	@Override
	public String toString(){
		System.out.println("~~~~~~~~~Branch Print Start~~~~~~~~~~");
		System.out.println("Branch name : " + this.branchName);
		System.out.println("Branch head");
		System.out.println(this.head);
		System.out.println("Branch tail");
		System.out.println(this.tail);
		return "~~~~~~~~~Branch Print End~~~~~~~~~~";
	}
}
