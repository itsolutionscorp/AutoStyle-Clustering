import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.io.*;
import java.util.Date;
import java.text.*;

public class bNode implements Serializable {

	// Instance Variables for the bNode class
	private String myMessage;
	private int myID;
	private bNode myPrev;
	private String myTime;
	private HashMap<String, String> myTracked; // <Original, Backup>

	/**
	 * Constructor for a bNode.
	 * 
	 * @param prev
	 *            The previous commit bNode that this points to.
	 * @param msg
	 *            The commit message associated with each commit bNode.
	 * @param id
	 *            The commit ID associated with each commit bNode.
	 * @param tracked
	 *            A HashMap<String, String> containing the String paths of each
	 *            files. Path of it in the working directory and to the backup
	 *            stored in .gitlet.
	 */
	public bNode(bNode prev, String msg, int id, HashMap<String, String> tracked) {
		myPrev = prev;
		setDate();
		myMessage = msg;
		myID = id;
		myTracked = tracked;
	}

	/**
	 * Getter method for bNode myPrev. Points to the previous commit node in the
	 * branch.
	 * 
	 * @return bNode
	 */
	public bNode getPrev() {
		return myPrev;
	}

	/**
	 * Getter method for bNode myID. myID is the commit node's ID.
	 * 
	 * @return int
	 */
	public int getID() {
		return myID;
	}

	/**
	 * Getter method for bNode myTime. myTime is a String representation of the
	 * time and day a commit was made.
	 * 
	 * @return String
	 */
	public String getTime() {
		return myTime;
	}

	/**
	 * Setter method for bNode myTime.
	 * 
	 * @return void
	 */
	public void setDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		myTime = dateFormat.format(date);
	}

	/**
	 * Getter method for bNode myMessage. myMessage is the message associated
	 * with each commit.
	 * 
	 * @return String
	 */
	public String getMsg() {
		return myMessage;
	}

	/**
	 * Getter method for bNode myTracked. myTracked is a Hashmap<String,String>,
	 * Keys are String paths to the files in the working directory and the
	 * Values are String paths to the backup files in .gitlet/.
	 * 
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getMyTracked() {
		return myTracked;
	}

}