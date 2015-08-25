
import java.io.Serializable;
public class Node<T> implements Serializable {
	/**
	 * Authors :
	 * Jiahao Huang, Cary Schwartzstein, Will Park, Bryan Lopez
	 */
	private static final long serialVersionUID = 1L;
	public String time;
	public int id = 0;
	public Node<T> pre;
	public String message;
	public String branch;
	public T item;
	public Node(Node<T> previous, int id, String message, String branch, T item, String time) {
		this.time = time;
		this.pre = previous;
		this.id = id;
		this.message = message;
		this.branch = branch;
		this.item = item;
	}
	
	public static void main(String[] args) {
	}
}
