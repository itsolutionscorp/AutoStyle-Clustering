import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Commit implements Serializable {
	private static final long serialVersionUID = -3537639244716460382L;

	private int id;
	private String msg;
	private String timestamp;
	private Commit prev;
	HashMap<String, Integer> tracked; // inherited files
	
	
/**
 * Constructor 
 * @param id
 * 				Integer of Commit ID 
 * @param msg
 * 				String of Commit Message 
 * @param timestamp
 * 				String of Commit timestamp 
 * @param prev
 * 				Previous Commit Object 
 */
	public Commit(int id, String msg, String timestamp, Commit prev) {	
		// first, set id
		this.id = id;
		// then, set msg
		this.msg = msg;
		// then, set the time stamp
		this.timestamp = timestamp;
		// then, set prev to null for the very first (empty) commit
		this.prev = prev;
		// then, create commit_files
		this.tracked = new HashMap<String, Integer>();
	}
	
	
	/**
	 * Getter method for prev
	 */
	public Commit prev() {
		return prev;
	}
	
	@Override
	public String toString() {
		String rtn = "";
		rtn += "===\n";
		rtn += ("Commit " + id + "\n");
		rtn += timestamp + "\n";
		rtn += msg + "\n\n";
		return rtn;
	}
	
	/** 
	 * Getter method for id
	 */
	public int id() {
		return id;
	}
	
	/**
	 * Setter method for message
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * Getter method for message
	 */
	public String getMsg() {
		return msg; 
	}
	
	/**
	 * Setter method for time stamp
	 */
	public void setTimeStamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	/**
	 * Getter method for time stamp
	 */
	public String getTimeStamp() {
		return timestamp;
	}
	
	/**
	 * Prints items in HashMap tracked 
	 */
	
	public void printTracked() {
		for(Map.Entry<String, Integer> entry : tracked.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}
}
