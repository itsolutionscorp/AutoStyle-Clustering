import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * GitNodes represent each commit. They are implemented in a tree like structure
 * with each GitNode containing a pointer to its previous commit. Each node also
 * has the instance variables necessary to identify each commit such as ID and
 * message.
 * 
 * @author Albert Pham cs61bl-bp
 * @author Henry Gong cs61bl-bk
 * @author Patrick Zhang cs61bl-bo
 */
public class GitNode implements Serializable {
	private GitNode prev;
	private HashMap<String, Integer> fileNames; // file name : commit ID
	private Date timestamp;
	private String message;
	private Integer id;

	/**
	 * Constructor for making the initial GitNode (no previous).
	 * 
	 * @param id
	 *            commit id
	 * @param message
	 *            commit message
	 */
	public GitNode(Integer id, String message) {
		this(id, null, message, new HashMap<String, Integer>());
	}

	/**
	 * Constructor to make a new GitNode. Timestamps are automatically
	 * generated.
	 * 
	 * @param id
	 *            ID of the commit.
	 * @param prev
	 *            Previous commit to this GitNode
	 * @param message
	 * @param fileNames
	 */
	public GitNode(Integer id, GitNode prev, String message,
			HashMap<String, Integer> fileNames) {
		this.id = id;
		this.prev = prev;
		this.timestamp = new Date(System.currentTimeMillis());
		this.message = message;
		this.fileNames = fileNames;
	}

	/**
	 * Prints the log to the system console.
	 */
	public void log() {
		System.out.println("===");
		System.out.println("Commit " + id);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(format.format(timestamp));
		System.out.println(message);
		if (prev != null) {
			System.out.println(); // adds extra space.
		}
	}

	/**
	 * Returns the commit message.
	 * 
	 * @return String with message of commit
	 */
	public String message() {
		return message;
	}

	/**
	 * Returns the timestamp associated with the commit.
	 * 
	 * @return Time when commit was made
	 */
	public Date timestamp() {
		return timestamp;
	}

	/**
	 * Returns the Node prior to this one.
	 * 
	 * @return GitNode representing a commit
	 */
	public GitNode prev() {
		return prev;
	}

	/**
	 * Returns the commit ID.
	 * 
	 * @return Integer for commit ID
	 */
	public Integer id() {
		return id;
	}

	/**
	 * Returns an array of file names associated with the commit.
	 * 
	 * @return ArrayList<String> of file names in .gitlet.
	 */
	public HashMap<String, Integer> fileNames() {
		return fileNames;
	}
}
