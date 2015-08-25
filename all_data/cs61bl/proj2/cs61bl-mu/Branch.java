import java.io.Serializable;


public class Branch implements Serializable{
	
	private String name;
	private Node head;
	private Node node;
	
	/**
	 * Constructor for Branch taking two argument
	 * 
	 * @param name   The name for current Branch
	 * @param node   The current node that Branch points to.
	 */
	public Branch(String name, Node node) {
		this.name = name;
		this.head = node;
		this.node = null;
	}
	
	/**
	 * Constructor for Branch taking three argument
	 * 
	 * @param name      The name for current Branch
	 * @param node      The current node that Branch points to.
	 * @param n         Remember the split point for current branch
	 */
	public Branch(String name, Node node, int i, Node n) {
		this.name = name;
		this.head = node;
		this.node = new Node(i, n);
	}
	
	/**
	 * Getter method for getting the name of the current branch.
	 * 
	 * @return The name of the current branch
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter method for setting the current branch point to current commit
	 * 
	 * @param head   The head of current branch
	 */
	public void setPointNode(Node head) {
		this.head = head;
	}
	
	/**
	 * Getter method for getting the pointer of the current branch
	 * 
	 * @return The pointer of current branch.
	 */
	public Node getNode() {
		return head;
	}
	
	/**
	 * Getter method for getting the pointer for the split point
	 * for current branch. 
	 * 
	 * @return The pointer of the split point for current branch.
	 */
	public Node getSplitPoint() {
		return node;
	}
	
	/**
	 * Setter method for setting the current split point to current
	 * split point.
	 * 
	 * @param n  The pointer of the split point.
	 */
	public void setSplitPoint(Node n) {
		this.node = n;
	}
}
