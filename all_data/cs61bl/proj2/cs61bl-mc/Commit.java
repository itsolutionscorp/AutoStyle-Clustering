import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.io.File;
import java.util.Date;
import java.sql.Timestamp;

public class Commit implements Serializable {
	private String message;
	private int iD;
	private Commit parent;
	private HashMap<String, File> fileMap;
	private String time;
	private Commit next;

	/**
	 * Constructor for a commit
	 * 
	 * @param message
	 *            - message of the commit
	 * @param iD
	 *            - unique ID of the commit
	 * @param parent
	 *            - parent commit of the commit
	 */
	public Commit(String message, int iD, Commit parent, Commit next) {
		this.message = message;
		this.iD = iD;
		this.parent = parent;
		fileMap = new HashMap<String, File>();
		java.util.Date date = new java.util.Date();
		Timestamp t = new Timestamp(date.getTime());
		time = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss").format(t);
		this.next = next;
	}

	/**
	 * Adds a file to the commit
	 * 
	 * @param fileName
	 *            - name of the file
	 * @param f
	 *            - the file
	 */
	public void addFile(String fileName, File f) {
		fileMap.put(fileName, f);

	}

	/**
	 * Returns the ID of the file
	 * 
	 * @return - ID of the commit
	 */
	public int getID() {
		return iD;
	}

	/**
	 * Returns the parent of the commit
	 * 
	 * @return - commit's parent
	 */
	public Commit getParent() {
		return parent;
	}
	
	/**
	 * Sets the commit's parent 
	 * @param p - commit to set parent to
	 */
	public void setParent(Commit p){
		parent = p;
	}
	
	/**
	 * Returns the next commit 
	 * @return - next commit 
	 */
	public Commit getNext(){
		return next;
	}

	/**
	 * Returns the time of the commit
	 * 
	 * @return - time the commit was created
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Returns the message of the commit
	 * 
	 * @return - message of the commit
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Returns a HashMap of the commit's files
	 * 
	 * @return - HashMap of the commit's files
	 */
	public HashMap<String, File> getFiles() {
		return fileMap;
	}

	/**
	 * Sets the files of the commit
	 * 
	 * @param map
	 *            - HashMap of Files to set
	 */
	public void setFiles(HashMap<String, File> map) {
		fileMap = map;
	}
}