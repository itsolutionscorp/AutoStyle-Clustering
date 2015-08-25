import java.util.*;
import java.io.*;
import java.nio.*;
import java.text.*;

public class Commit implements Serializable {
	/**
	 * Declares instance variables
	 */
	private Integer parentID;
	private Integer myID;
	private Calendar myDate;
	private String myMessage;
	private HashMap<String, Integer> fileToCommit;

	/**
	 * Initialize a commit object with following information
	 * 
	 * @param parentID
	 *            Parent commit ID
	 * @param id
	 *            Computed self ID
	 * @param date
	 *            A Calendar object that keeps track of the date
	 * @param message
	 *            Description message for the commit provided by user
	 * @param fileToCommit
	 *            The HashMap with fileName to newest commit which contains the
	 *            modified file
	 */
	public Commit(Integer parentID, Integer id, Calendar date, String message,
			HashMap<String, Integer> fileToCommit) {
		this.parentID = parentID;
		this.myID = id;
		this.myDate = date;
		this.myMessage = message;
		this.fileToCommit = fileToCommit;
	}

	/**
	 * Return parent commit ID
	 * 
	 * @return
	 */
	public int getParentID() {
		return this.parentID;
	}

	/**
	 * Return self ID
	 * 
	 * @return
	 */
	public Integer getID() {
		return this.myID;
	}

	/**
	 * Return date in the commit
	 * 
	 * @return
	 */
	public Calendar getDate() {
		return this.myDate;
	}

	/**
	 * Return descriptive message provided by user
	 * 
	 * @return
	 */
	public String getMessage() {
		return this.myMessage;
	}

	/**
	 * Return the HashMap with fileName to newest commit which contains modified
	 * file
	 * 
	 * @return
	 */
	public HashMap<String, Integer> getFileToCommit() {
		return fileToCommit;
	}

	/**
	 * Display information for the commit
	 */
	public void printLog() {
		System.out.println("===");
		System.out.println("Commit " + Integer.toString(myID));
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(dateFormat.format(myDate.getTime()));
		System.out.println(myMessage);
		System.out.println();
	}

	/**
	 * Serialize the commit object given by the name
	 * 
	 * @param fileName
	 */
	public void commitOutput(String fileName) {
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		} catch (IOException e) {

		}
	}

	/**
	 * Deserialize the commit object given by the name and return the commit
	 * object
	 * 
	 * @param fileName
	 * @return
	 */
	public static Commit commitInput(String fileName) {
		Commit input = null;
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			input = (Commit) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException e) {

		} catch (ClassNotFoundException e) {

		}
		return input;
	}

}
