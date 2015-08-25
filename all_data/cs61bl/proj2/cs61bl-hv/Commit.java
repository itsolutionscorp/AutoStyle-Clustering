import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * 
 * @author Jeffrey Mak Mason Scott David Tseng
 * @version 0.0
 * @since Jul 18, 2015
 */
public class Commit implements Serializable {
	Integer commitID;
	String commitMessage;
	Date commitDateTime;
	Commit parent;
	ArrayList<Commit> children;
	HashMap<String, String> committedFilePath;
	HashMap<String, File> untracked;
	Gitlet git;

	/**
	 * Commit's constructor.
	 */
	public Commit() {

	}

	/**
	 * Other Commit constructor. This is used whenever a commit is made. It does
	 * usual procedures like setting the message, the parent, etc, but also
	 * makes sure that it inherits the files from its parent.
	 * 
	 * @param message
	 *            The message that was used to describe the commit when the
	 *            commit was first created.
	 * @param git
	 *            Refers to the Gitlet object. This way we can access methods
	 *            and variables of the Gitlet object.
	 * @param parent
	 *            The parent commit that this commit is going to inherit from.
	 */
	public Commit(String message, Gitlet git, Commit parent) {
		commitDateTime = new Date();
		untracked = new HashMap<String, File>(5);
		untracked.putAll(git.untracked);
		children = new ArrayList<Commit>(2);
		commitID = git.lastCommitID + 1;
		git.lastCommitID = commitID;
		this.git = git;
		this.parent = parent;
		commitMessage = message;
		committedFilePath = new HashMap<String, String>(5);
		if (this.parent != null) {
			Iterator iter = parent.committedFilePath.entrySet().iterator();
			while (iter.hasNext()) {
				HashMap.Entry keyValue = (HashMap.Entry) iter.next();
				if (!git.untracked.containsKey(keyValue.getKey()))
					committedFilePath.put((String) keyValue.getKey(),
							(String) keyValue.getValue());
			}
		}
	}

	/**
	 * Constructor used for rebase method. Creates a Commit object by copying
	 * another Commit object and adds the given child to its children.
	 * 
	 * @param toCopy
	 *            The Commit object that will be copied.
	 * @param git
	 *            The main Gitlet object
	 * @param child
	 *            The child to be added to itself.
	 */
	public Commit(Commit toCopy, Gitlet git, Commit child) {
		commitDateTime = new Date();
		untracked = new HashMap<String, File>(5);
		children = new ArrayList<Commit>(2);
		commitID = git.lastCommitID + 1;
		git.lastCommitID = commitID;
		this.git = git;
		commitMessage = toCopy.commitMessage;
		committedFilePath = new HashMap<String, String>(5);
		committedFilePath.putAll(toCopy.committedFilePath);
		if (child != null) {
			children.add(child);
			child.parent = this;
		}
	}

	/**
	 * It prints out the details of itself as well as the details of any child
	 * commit created. Used in globalLog() method of the Gitlet class.
	 */
	public void globalLogPrint() {
		if (parent != null)
			System.out.print("\n");
		System.out.println(toString());
		for (Commit child : children) {
			child.globalLogPrint();
		}
	}

	/**
	 * Goes back to previous commits and prints out its own log as well as its
	 * parent's log. Used in the log() method of the Gitlet class.
	 */
	public void logPrint() {
		System.out.println(this.toString());
		if (parent != null) {
			System.out.print("\n");
			parent.logPrint();
		}
	}

	/**
	 * Returns the date in the format needed for our log. We got this idea from
	 * StackOverFlow but we modified it to suit our needs.
	 * 
	 * @param date
	 *            The date that we want to format.
	 * @return A string representing the formatted date.
	 */
	public static String dateFormat(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	/**
	 * Overrides the Object toString() method. It's basically the structure of
	 * the log. It contains the date and time of the commit, the commit ID, and
	 * the message that was used when the commit was created.
	 */
	public String toString() {
		String ret_string = "===\n";
		ret_string += "Commit " + commitID + "\n";
		ret_string += dateFormat(commitDateTime);
		ret_string += "\n" + commitMessage;
		return ret_string;
	}
}
