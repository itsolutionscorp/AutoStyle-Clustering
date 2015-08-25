import java.util.*;
import java.io.Serializable;
import java.io.File;
import java.text.SimpleDateFormat;

public class Commit implements Serializable {

	/**
	 * myPrev is the Commit of the last Commit of which this Commit is an
	 * updated version.
	 * 
	 * birthday is a String of the date and time this Commit was created. It can
	 * never change.
	 * 
	 * myID is an int representing the number of Commits made, including itself.
	 * It can never change.
	 * 
	 * myMessage is a String of the message passed in when this Commit was made.
	 * It can never change.
	 * 
	 * myFiles is a HashMap of the Files included in this Commit. Its keys are
	 * the names of the of the File, and File values the actual files.
	 * 
	 * myUntracked is a HashSet of the Files marked for untracking when this
	 * Commit was made.
	 */
	
	private Commit myPrev = null;
	private final String birthday;
	private final int myID;
	private final String myMessage;
	private HashMap<String, File> myFiles;
	private HashSet<String> myUntracked;

	/**
	 * This is the constructor for each Commit object.
	 * 
	 * @param message
	 *            This is the message with which each Commit is made. It
	 *            generally describes what changes were made to the files since
	 *            the previous commit (if any), and is represented as a String.
	 * @param prev
	 *            This is the Commit which precedes this Commit.
	 * @param trackedFiles
	 *            This is a HashMap of Strings to Files of the Files which were
	 *            added for this Commit.
	 * @param commitID
	 *            This is the ID of this Commit.
	 */
	public Commit(String message, Commit prev,
			HashMap<String, File> trackedFiles, int commitID) {
		myMessage = message;
		myPrev = prev;
		myFiles = new HashMap<String, File>();
		if (trackedFiles != null) {
			myFiles.putAll(trackedFiles);
		}
		myID = commitID;
		birthday = setBirthday();
		myUntracked = new HashSet<String>();
	}

	/**
	 * This is a public getter method for this Commit's files.
	 * 
	 * @return this Commit's myFiles HashMap
	 */
	public HashMap<String, File> getFiles() {
		return myFiles;
	}

	/**
	 * This is a public getter for this Commit's untracked files.
	 * 
	 * @return this Commit's myUntracked HashMap.
	 */
	public HashSet<String> getUntracked() {
		return myUntracked;
	}
	
	/**
	 * A helper method which sets this Commit's HashSet of Files to be the one given.
	 * 
	 * @param untracked
	 * 		A HashSet<File> which will be this Commit's myUntracked.
	 */		
	public void setUntracked(HashSet<String> untracked) {
		myUntracked.addAll(untracked);
	}

	/**
	 * This method adds the given File to this Commit's untracked files HashMap
	 * myUntracked.
	 * 
	 * @param f
	 *            The File to be added to this Commit's myUntracked HashMap.
	 */
	public void addToUntracked(String s) {
		myUntracked.add(s);
	}

	/**
	 * This is a getter method for this Commit's ID.
	 * 
	 * @return the ID of this Commit, as an int.
	 */
	public int getID() {
		return myID;
	}

	/**
	 * This is a helper method to correctly format this Commit's birthday (day
	 * and time of creation). It is called upon in the constructor.
	 * 
	 * @return the String of date and time this Commit was created.
	 */
	private String setBirthday() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		Date temp = new Date();
		return dateFormat.format(temp).toString();
	}

	/**
	 * This is a getter method for this Commit's previous Commit myPrev.
	 * 
	 * @return this Commit's previous Commit.
	 */
	public Commit getMyPrev() {
		return myPrev;
	}

	/**
	 * This is a getter method for this Commit's message.
	 * 
	 * @return this Commit's message, as a String.
	 */

	public String getMessage() {
		return myMessage;
	}

	/**
	 * This helper method correctly formats this Commit's myID, birthday, and message
	 * when called upon in the log and globalLog methods of the Gitlet class.
	 */
	public void logPrint() {
		System.out.println("===");
		System.out.println("Commit " + myID);
		System.out.println(birthday);
		System.out.println(myMessage);
		System.out.println();
	}

}
