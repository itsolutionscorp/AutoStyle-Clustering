/**
 * CS61BL - UC Berkeley Summer 2015
 * Project 2
 * Gitlet
 * 
 * Class 2/2
 * Gitlet.java
 * Commit.java <<<
 * 
 * Project description:
 * A simpler version of git.
 * Full project details at http://cs61bl.github.io
 * 
 * @authors Jessica Larson, Brian Sakhuja, Eifu Tomita
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class Commit implements Serializable {
	// Instance variables
	private Commit parent;
	private ArrayList<File> myFileList;
	private ArrayList<String> myFileNameList;
	private String date;
	protected String commitMessage;
	private int id;
	private HashSet<String> trackingSet;

	/**
	 * COMMIT 1/3
	 * 
	 * Constructor for the initial commit object.
	 * 
	 * @param idSoFar
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Commit(int idSoFar) throws FileNotFoundException, IOException {
		Date t = new Date();
		parent = null;
		myFileList = null;
		myFileNameList = null;
		date = t.toString();
		commitMessage = "initial commit";
		id = idSoFar;
		trackingSet = new HashSet<String>();

		File commit = new File(".gitlet/commit" + id);
		commit.mkdir();

	}

	/**
	 * COMMIT 2/3
	 * 
	 * Constructor for any subsequent commits after the initial commit
	 * 
	 * @param arrayofFile
	 * @param arrayofFileName
	 * @param messageToInput
	 * @param currentBranchHead
	 * @param untrackingSet
	 * @param idSoFar
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Commit(ArrayList<File> arrayofFile,
			ArrayList<String> arrayofFileName, String messageToInput,
			Commit currentBranchHead, HashSet<String> untrackingSet, int idSoFar)
			throws ClassNotFoundException, IOException {

		Date t = new Date();

		parent = currentBranchHead;
		myFileList = arrayofFile;
		myFileNameList = arrayofFileName;
		date = t.toString();
		commitMessage = messageToInput;
		id = idSoFar;

		File commit = new File(".gitlet/commit" + id);
		commit.mkdir();

		trackingSet = new HashSet<String>();
		for (String s : parent.trackingSet) {
			trackingSet.add(s);
		}
		for (String s : myFileNameList) {
			trackingSet.add(s);
		}
		for (String s : untrackingSet) {
			if (trackingSet.contains(s)) {
				trackingSet.remove(s);
			}

		}
		for (File f : myFileList) {
			File newF = new File(".gitlet/commit" + id + "/"
					+ myFileNameList.get(myFileList.indexOf(f)));
			Files.copy(f.toPath(), newF.toPath());
		}

	}

	/**
	 * COMMIT 3/3
	 * 
	 * Constructor for rebase
	 * 
	 * @param arrayofFile
	 * @param arrayofFileName
	 * @param messageToInput
	 * @param currentBranchHead
	 * @param untrackingSet
	 * @param idSoFar
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Commit(ArrayList<File> arrayofFile,
			ArrayList<String> arrayofFileName, String messageToInput,
			Commit currentBranchHead, HashSet<String> untrackingSet,
			int idSoFar, int srcIdNumber) throws ClassNotFoundException,
			IOException {

		Date t = new Date();

		parent = currentBranchHead;
		myFileList = arrayofFile;
		myFileNameList = arrayofFileName;
		date = t.toString();
		commitMessage = messageToInput;
		id = idSoFar;

		File commit = new File(".gitlet/commit" + id);
		commit.mkdir();

		trackingSet = new HashSet<String>();
		for (String s : parent.trackingSet) {
			trackingSet.add(s);
		}
		for (String s : myFileNameList) {
			trackingSet.add(s);
		}
		for (String s : untrackingSet) {
			if (trackingSet.contains(s)) {
				trackingSet.remove(s);
			}

		}
		for (File f : myFileList) {
			File src = new File(".gitlet/commit" + srcIdNumber + "/"
					+ myFileNameList.get(myFileList.indexOf(f)));
			File newF = new File(".gitlet/commit" + id + "/"
					+ myFileNameList.get(myFileList.indexOf(f)));
			Files.copy(src.toPath(), newF.toPath());
		}

	}

	/**
	 * LOG
	 * 
	 * Constructs the log message according using the specified format.
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void log() throws ClassNotFoundException, IOException {
		Commit placeHolder = this;
		while (placeHolder != null) {
			System.out.println(placeHolder.toString());
			placeHolder = placeHolder.parent;
		}

	}

	public String toString() {
		return "=== \nCommit " + id + "\n" + date + "\n" + commitMessage + "\n";
	}

	/**
	 * GETPARENT
	 * 
	 * @returns the parent of the commit object
	 */
	public Commit getParent() {
		return parent;
	}

	/**
	 * GETID
	 * 
	 * @returns the ID of the commit object
	 */
	public int getId() {
		return id;
	}

	/**
	 * GETMESSAGE
	 * 
	 * @returns the message associated with a given commit object
	 */
	public String getMessage() {
		return commitMessage;
	}

	/**
	 * SETMESSAGE
	 * 
	 * changes the message of an id.
	 */
	public void setMessage(String newMessage) {
		commitMessage = newMessage;
	}

	/**
	 * GETDATE
	 * 
	 * @returns the date a given commit object was created.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * GETFILELIST
	 * 
	 * @returns an array list of all the file objects.
	 */
	public ArrayList<File> getFileList() {
		return myFileList;
	}

	/**
	 * GETFILENAMELIST
	 * 
	 * @returns an array list of all the file names in a string format.
	 */
	public ArrayList<String> getFileNameList() {
		return myFileNameList;
	}

	/**
	 * GETTRACKINGSET
	 * 
	 * @returns the hash set of files we are tracking.
	 */
	public HashSet<String> getTrackingSet() {
		return trackingSet;
	}

	/**
	 * SERIALIZE
	 *
	 * @param o
	 * @param placeToSerialize
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void serialize(Object o, String placeToSerialize)
			throws FileNotFoundException, IOException {
		File ser = new File(placeToSerialize);
		FileOutputStream fos = new FileOutputStream(ser);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeObject(o);
	}

	/**
	 * DESERALIZE
	 * 
	 * @param path
	 * @returns the object we are deserializing.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Object deserialize(String path) throws IOException,
			ClassNotFoundException {
		FileInputStream fis = new FileInputStream(path);
		ObjectInputStream ois = new ObjectInputStream(fis);
		return ois.readObject();
	}

	/**
	 * HASHCODE
	 * 
	 * @returns the hashcode, which is just the ID number of the commit object.
	 */
	public int hashCode() {
		return id;
	}

}
