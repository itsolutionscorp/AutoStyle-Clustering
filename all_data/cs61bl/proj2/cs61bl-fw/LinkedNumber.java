import java.io.Serializable;

public class LinkedNumber implements Serializable{
	
	private int id;
	private LinkedNumber next;
	
	/**
	 * Constructor with take one argument.
	 * 
	 * @param i  Commit id number
	 */
	public LinkedNumber(int i) {
		this.id = i;
		this.next = null;
	}
	
	/**
	 * Constructor with take two arguments.
	 * 
	 * @param i       Commit id number
	 * @param next    next pointer for next commit id number
	 */
	public LinkedNumber(int i, LinkedNumber next) {
		this.id = i;
		this.next = next;
	}
	
	/**
	 * Getter method for get next pointer
	 * 
	 * @return  next pointer
	 */
	public LinkedNumber next() {
		return this.next;
	}
	
	/**
	 * Getter method for get current id
	 * 
	 * @return   get current id
	 */
	public int getId() {
		return this.id;
	}
}
