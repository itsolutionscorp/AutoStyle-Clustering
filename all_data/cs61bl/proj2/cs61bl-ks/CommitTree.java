import java.io.Serializable;
import java.util.Iterator;

/**
 * Our CommitTree is our primary data structure which will hold the commits
 * (references to files, message of commits, and the commit ID). It is an
 * advanced LinkedList so we will be implementing all of the LinkedList features
 * and more (merge, rebase).
 * 
 * @author Kevin Wu, Evan Patel, Cynthia Diaz, Jaehyun Sim
 *
 */
public class CommitTree implements Serializable, Iterable {
	// Instance Variables
	private CommitTreeNode myHead;
	private int mySize;
	private String myName;
	private CommitTreeNode parentcommit;

	// Gitlet's INIT method will call this constructor
	public CommitTree() {
		myHead = new CommitTreeNode();
		mySize = 0;
		myName = "master";
	}

	/**
	 * Gitlet's BRANCH method will call this constructor to create new branch
	 * 
	 * @param branchName
	 *            a String that is the name of the new branch
	 * @param size
	 *            an int that is the size of the current branch
	 */
	public CommitTree(String branchName, int size) {
		myHead = new CommitTreeNode();
		mySize = size;
		myName = branchName;
	}

	/********************************************
	 * ///////////////// METHODS ///////////////////
	 *****************************************/

	/**
	 * Get method for the size of the tree (how many commits are in the Tree)
	 * 
	 * @return the size of the tree
	 */
	public int getSize() {
		return mySize;
	}

	/**
	 * Returns whether the tree is empty
	 * 
	 * @return true if the tree is empty
	 */
	public boolean isEmpty() {
		return (mySize == 0);
	}

	/**
	 * Getter method to return the current branch name
	 * 
	 * @return a String with the current branch name
	 */
	public String getName() {
		return myName;
	}

	/**
	 * Getter method
	 * 
	 * @return the current Node being pointed at by Gitlet
	 */
	public CommitTreeNode getHead() {
		return myHead;
	}

	/**
	 * Setter method to change what the head pointer is pointing at.
	 * 
	 * @param oldNode
	 *            - is the node which the head will point to
	 */
	public void resetHead(CommitTreeNode oldNode) {
		myHead = oldNode;
		parentcommit = oldNode.prev();
	}

	/**
	 * Gitlet's COMMIT method will call this method to update head of branch and
	 * the parent commit Will also be called by Gitlet's RESET method
	 * 
	 * @param newHead
	 *            is the most recently committed
	 */
	public void moveHead(CommitTreeNode newHead) {
		parentcommit = myHead;
		myHead = newHead;
		mySize++;
	}

	/**
	 * Gitlet's REBASE method will call this to update the size of the current
	 * branch
	 * 
	 * @param size
	 *            and int that is the size of the branch given to rebase and the
	 *            number of nodes between the split and the current head
	 */
	public void resize(int size) {
		mySize = size;
	}

	/**
	 * Getter method for the parent commit of the current commit.
	 * 
	 * @return the parent node of the current commit
	 */
	public CommitTreeNode parentCommit() {
		return parentcommit;
	}

	/**
	 * An iterator to iterate backwards through the nodes of the tree (from the
	 * head commit).
	 */
	@Override
	public Iterator<CommitTreeNode> iterator() {
		return new NodeIterator();
	}

	private class NodeIterator implements Iterator<CommitTreeNode> {
		CommitTreeNode current;

		public void NodeIterator() {
			current = myHead;

		}

		@Override
		public boolean hasNext() {
			return (current != null);
		}

		@Override
		public CommitTreeNode next() {
			CommitTreeNode temp = current;
			current = current.prev();
			return temp;
		}

	}
}
