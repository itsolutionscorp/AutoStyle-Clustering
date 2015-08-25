import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * This is the CommitTree class which constructs a commit tree out of individual
 * commit objects. It contains an iterator.
 * 
 * @author Sam: EF, Frances: BQ, Alex: BR, Chris: EZ
 */
public class CommitTree implements Serializable {

	// set the root to null
	Commit myRoot = null;

	/**
	 * Constructor for the commitTree object
	 * 
	 * @param root
	 *            - the parent node of all the commits
	 */
	public CommitTree(Commit root) {
		myRoot = root;
	}

	/**
	 * Constructor for the commitTree object. Initialize this commit to be the
	 * parent commit and have an id of 0.
	 * 
	 * @param message
	 *            - the commit's message
	 * 
	 */
	public CommitTree(String message) {
		myRoot = new Commit(message, 0, null);
	}

	/**
	 * Add a child to the commit tree
	 * 
	 * @param commitMessage
	 *            - the commit's message
	 * @param commitId
	 *            - the commit's id
	 * @param parentId
	 *            - the id of the parent of the commit
	 */
	public void addChild(String commitMessage, int commitId, int parentId) {
		if (myRoot != null) {
			myRoot.addChild(commitMessage, commitId, parentId);
		}
	}

	/**
	 * Method used for calculating the size of the commit tree
	 * 
	 * @return the size of the tree
	 */
	public int size() {
		if (myRoot != null) {
			return myRoot.size();
		}
		return 0;
	}

	/**
	 * Method used for calculating the height of the commit tree
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		if (myRoot != null) {
			return myRoot.height();
		}
		return 0;
	}

	/**
	 * Iterator for the commit tree
	 * 
	 * @return a new iterator object
	 */
	public Iterator<Commit> iterator() {
		return new CommitIterator();
	}

	public class CommitIterator implements Iterator<Commit> {
		/**
		 * Nested class for the CommitTree's iterator
		 */

		// make a queue linked list
		private Queue<Commit> fringe = new LinkedList<Commit>();

		/**
		 * Constructor for the Commit iterator. If parent node is not null, add
		 * it to the queue.
		 */
		public CommitIterator() {
			if (myRoot != null) {
				fringe.offer(myRoot);
			}
		}

		/**
		 * Determines if the linked list has a next element.
		 */
		@Override
		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		/**
		 * Get the commit that the iterator points to, and then move the
		 * iterator over by one.
		 * 
		 * @throws NoSuchElementException
		 *             if the queue is out of elements
		 */
		@Override
		public Commit next() {
			if (!hasNext()) {
				throw new NoSuchElementException(
						"your tree ran out of elements");
			}
			Commit node = (Commit) fringe.poll();
			for (Commit a : node.myChildren) {
				fringe.offer(a);
			}
			return node;
		}
	}
}
