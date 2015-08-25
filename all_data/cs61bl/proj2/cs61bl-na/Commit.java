import java.util.HashMap;
import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

public class Commit implements Serializable {

	/**
	 * -Represents a commit in the commit Tree
	 * 
	 * Instance Variables: 
	 * parent: The "parent" of this commit 
	 * children: HashMap mapping the idNumber to Commit object for all the children of this commit node 
	 * tracking: HashSet of what files are actually stored in this commit (including files inherited from the parent) 
	 * locations: HashMap that maps the name of a file to the commit folder that it is stored in 
	 * idNumber: The unique idNumber for each commit 
	 * timestamp: The time of which the commit was created 
	 * description: Additional comments for a commit given by the user 
	 * serialVersionUID: Used for serialization, not very important
	 * 
	 * @author Johnny Le, Kevin Luong, Sijing Xin, Jia Guo
	 */

	private Commit parent;
	private HashMap<Integer, Commit> children;
	private HashMap<String, File> tracking;
	private HashMap<String, Integer> locations;
	private int idNumber;
	private String timestamp;
	private String description;
	private static final long serialVersionUID = 2L;

	public Commit(Commit parent, HashMap<String, File> toCommit,
			HashMap<String, Integer> toLocate, int id, String descript) {
		this.parent = parent;
		children = new HashMap<Integer, Commit>();
		tracking = toCommit;
		locations = toLocate;
		idNumber = id;
		timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Timestamp((new java.util.Date()).getTime()));
		description = descript;
		parent.addChild(this);
	}

	public Commit(int id, String descript) {
		parent = null;
		children = new HashMap<Integer, Commit>();
		tracking = new HashMap<String, File>();
		locations = new HashMap<String, Integer>();
		idNumber = 10000;
		timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Timestamp((new java.util.Date()).getTime()));
		description = descript;
	}

	/** 
	 * Adds a child commit to this commit node
	 * 
	 * @param child
	 * 			commit node to add as a child
	 */
	private void addChild(Commit child) {
		children.put(child.idNumber(), child);
	}

	/**
	 *  Returns the description for this commit
	 * @return
	 * 		description of this commit
	 */
	public String description() {
		return description;
	}

	/**
	 *  Returns the timestamp for this commit
	 * @return
	 * 		timestamp of this commit
	 */
	public String timestamp() {
		return timestamp;
	}

	/**
	 *  Returns the idNumber for this commit
	 * @return
	 * 		idNumber of this commit
	 */
	public int idNumber() {
		return idNumber;
	}

	/**
	 *  Returns the parent for this commit
	 * @return
	 * 		parent of this commit
	 */
	public Commit parent() {
		return parent;
	}
	
	/**
	 *  Returns the files this commit is tracking as a HashMap of filename : File object
	 * @return
	 * 		HashMap of the files this commit is tracking
	 */
	public HashMap<String, File> tracking() {
		return tracking;
	}

	/**
	 *  Returns the children of this commit
	 * @return
	 * 		HashMap of the id Number : Commit node for the children of this commit
	 */
	public HashMap<Integer, Commit> getChildren() {
		return children;
	}

	/**
	 *  Returns the files tracked by this commit mapped to the parent commit folders that actually hold the files
	 * @return
	 * 		HashMap of filename : idNumber of the location of the file in the commit history
	 */
	public HashMap<String, Integer> locations() {
		return locations;
	}

	/**
	 * Find the real location of a file stored by this commit (ie: which parent has the copy of the file)
	 * 
	 * @param filename
	 * 		The file that you want to find the location of
	 * @return
	 * 		ID number of the commit that has the specified file
	 */
	public Integer location(String filename) {
		return locations.get(filename);
	}

}
