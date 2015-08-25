import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;


public class Commit implements Serializable {
	
	private HashMap<String,File> files; 

	private int id; 
	private String message; 
	private Date date; 
	
	/**Constructs an commit. 
	 * 
	 */
	public Commit() {
		files = new HashMap<String,File>(); 
	}
	
	/**Constructs a commit with the given ID. 
	 * 
	 * @param i: integer, the ID of the commit. 
	 */
	public Commit(int i) {
		id = i;
	}
	
	/**Constructs a commit identical to the given commit. 
	 * 
	 * @param c: Commit object, commit to be copied. 
	 */
	public Commit(Commit c) {
		files = c.files;
		id = c.id;
		message = c.message;
		date = c.date; 
	}
	
	/**Constructs a commit with the given ID, message gives it a time stamp.
	 * 
	 * @param i: integer, the commit ID
	 * @param m: String, the message associated with the commit. 
	 * @param d: Date object, the date at which the commit was created. 
	 */
	public Commit(int i, String m, Date d) {
		this();
		id = i; 
		message = m;
		date = d; 
	}
	
	/**Constructs a commit with the given files, ID, message and gives it a time stamp.
	 * 
	 * @param f: A HashMap with key Strings, and values File, the files to put into the commit. 
	 * @param i: integer, the commit's ID.
	 * @param m: String, the message given to the commit. 
	 * @param d: Date object, the time when the commit was created. 
	 */
	public Commit(HashMap<String,File> f, int i, String m, Date d) {
		files = f; 
		id = i; 
		message = m; 
		date = d;
	}

	/**Checks if the commit is empty. 
	 * 
	 * @return true if the commit is empty; false if it is not. 
	 */
	public boolean isEmpty() {
		return files.isEmpty();
	}
	
	/**Creates and formats a string that displays the commit's ID, date and message. 
	 * 
	 * @return String that displays the commit's ID, date and message. 
	 */
	public String logHelper() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
		return "===" + '\n'+ 
				"Commit " + getId() + '\n' 
				+ form.format(getDate()) + '\n'
				+ getMessage()  + '\n';
	}
	
	/**Returns the files in the commit. 
	 * 
	 * @return a HashMap of files stored in the commit. 
	 */
	public HashMap<String,File> getFiles() {
		return files;
	}

	/**Places files into the commit. 
	 * 
	 * @param files: HashMap of key file names, and value File objects, files to put into the 
	 * commit. 
	 */
	public void setFiles(HashMap<String,File> files) {
		this.files = files;
	}

	/**Returns the ID of the commit. 
	 * 	
	 * @return the ID of the commit, an integer. 
	 */
	public int getId() {
		return id;
	}

	/**Sets the ID of the commit to the given. 
	 * 
	 * @param id: Integer to set the commit ID to. 
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**Retrieves the message of the commit. 
	 * 
	 * @return String, the message of the commit. 
	 */
	public String getMessage() {
		return message;
	}

	/**Sets the commit message to the given. 
	 * 
	 * @param message: String, message desired. 
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**Retrieves the date of the commit. 
	 * 
	 * @return Date object, the date of the commit. 
	 */
	public Date getDate() {
		return date;
	}

	/**Sets the date to the given date; 
	 * 
	 * @param date: Date object, the date desired. 
	 */
	public void setDate(Date date) {
		this.date = date;
	}
}
