import java.io.*;
import java.util.*;

public class bLinkedList implements Serializable {
	private bNode current;

	/**
	 * Constructor method for the bLinkedList class.
	 */
	public bLinkedList() {
		current = null;
	}

	/**
	 * Creates the initial commit when Gitlet is first initialized.
	 * 
	 * @param msg
	 *            The commit message: "initial commit"
	 * @param id
	 *            The commit ID: 0
	 * @return void
	 */
	public void initialListCommit(String msg, int id) {
		current = new bNode(null, msg, id, null);
	}

	/**
	 * Commit the given files to the bLinkedList chain.
	 * 
	 * @param theFile
	 *            The Files to be committed.
	 * @param previous
	 *            A bNode, the pointer to the previous node in the branch.
	 * @param message
	 *            A String, the commit message.
	 * @param id
	 *            An Integer, the commit ID.
	 * @param tracked
	 *            A Hashmap<String,String>, Keys are String paths to the files
	 *            in the working directory and the Values are String paths to
	 *            the backup files in .gitlet/.
	 * @return void
	 */
	public void listCommit(bNode previous, String message, int id,
			HashMap<String, String> tracked) {
		current = new bNode(previous, message, id, tracked);
	}

	/**
	 * Getter method for the instance variable: current. Current keeps track of
	 * the latest commit.
	 * 
	 * @return bNode
	 */
	public bNode getCurrent() {
		return current;
	}

}