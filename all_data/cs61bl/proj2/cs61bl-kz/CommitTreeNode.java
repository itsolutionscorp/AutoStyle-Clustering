import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/*CommitTreeNode.java*/

/**
 * Represents a Tree Node of the Commit Tree used by Gitlet. Only the initial
 * commit should be special. Every TreeNode knows its parent and contains an ArrayList of the
 * files in its version. Every CommitTreeNode should have its own
 * id number. Add additional information here.
 * 
 * @authors Alan Kwok, Pratyusha Gogulapati, Ranju Subramani, Timothy Guan
 */

public class CommitTreeNode implements Serializable{
	/**
	 * Define any variables for CommitTreeNode here. These variables should be
	 * private. Also, explanations are good. 
	 * id - A CommitTreeNode's id number.
	 * commitMessage - the commitMessage associated with the commit
	 * myParent - The CommitTreeNode's parent node. 
	 * myFiles - An ArrayList of files included in the version.
	 * timeStamp - The time of creation stored as a String. Citation -
	 * http://stackoverflow.com/
	 * questions/5175728/how-to-get-the-current-date-time-in-java
	 */
	private int id;
	private String commitMessage;
	private CommitTreeNode myParent;
	private String timeStamp;
	private HashMap<String, File> myFiles; // string getName(file) -> File

	/**
	 * Constructs a new CommitTreeNode. The id is set to ____, the instance
	 * variables are set, and a new ArrayList is created for the children.
	 * 
	 * @param parent
	 *            The parent of the CommitTreeNode.
	 * @param file
	 *            The file that is stored.
	 * @param commitMessage
	 *            The message associated with this node.
	 */
	public CommitTreeNode(CommitTreeNode parent, HashMap<String, File> files, int id, String commitMessage) {
		if (parent == null) {
			// THIS MUST THEN BE THE INITIAL COMMIT
			// Should error since overloading constructor.
		}
		this.id = id; // change
		this.commitMessage = commitMessage;
		this.myParent = parent;
		this.myFiles = files;// should contain all Files that are being tracked after the last commit
		this.timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	}
	
	/**
	 * Constructs the initial CommitTreeNode. Does not check whether one exists
	 * already. Should not be called if this is the case. The id is set to 0?,
	 * the instance variables are set to null, and a new ArrayList is created
	 * for the children. The commitMessage is "initial commit".
	 */
	public CommitTreeNode() {
		this.id = 0; //change?
		this.commitMessage = "initial commit";
		this.myParent = null;
		this.timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		this.myFiles = new HashMap<String, File>();
	}
	
	/**
	 * Returns myParent.
	 * 
	 * @return The Parent of this CommitTreeNode.
	 */
	public CommitTreeNode getParent() {
		return this.myParent;
	}

	/**
	 * Returns id.
	 * 
	 * @return The id number of this CommitTreeNode.
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Returns message.
	 * 
	 * @return The message of this CommitTreeNode.
	 */
	public String getMessage() {
		return this.commitMessage;
	}
	
	/**
	 * Returns the files.
	 * 
	 * @return The HashMap of the files of this CommitTreeNode.
	 */
	public HashMap<String, File> getFiles() {
		return this.myFiles;
	}
	
	/**
	 * Returns the time stamp.
	 * 
	 * @return The time when this commitTreeNode was created as a String.
	 */
	public String getTime() {
		String a = this.timeStamp;
		return a.substring(0, 4) + "-" + a.substring(4, 6) + "-"
				+ a.substring(6, 8) + " " + a.substring(9, 11) + ":"
				+ a.substring(11, 13) + ":" + a.substring(13, 15);
		
	}
	
	/**
	 * Returns a String with all the information. Should be printed rather than
	 * compared. NEVER COMPARE
	 * 
	 * @return "CommitPrintEnd"
	 */
	@Override
	public String toString() {
		System.out.println("~~~~~~~~~Commit Print Start~~~~~~~~~~");
		System.out.println("Commit " + this.commitMessage);
		System.out.println("ID: " + Integer.toString(id));
		if (this.myParent != null) {
			System.out.println("Parent: " + Integer.toString(this.myParent.id)
					+ " " + this.myParent.commitMessage);
		}
		System.out.println("Commit created at " + this.getTime());
		System.out.println("--- Files ---");
		for (File f : this.myFiles.values()) {
			System.out.println(f);
		}
		System.out.println("--- End Files ---");
		return "~~~~~~~~~Commit Print End~~~~~~~~~~";
	}
}


