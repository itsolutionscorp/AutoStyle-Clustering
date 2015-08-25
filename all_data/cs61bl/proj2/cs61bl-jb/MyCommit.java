import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class MyCommit implements Serializable {

	private ArrayList<String> filesToCommit;
	private int hashID;
	private String msg; //Commit Message
	private Date dateOfCommit; 
	private MyCommit myPrev;
	private File commitFolder;
	private HashMap<String, String> tracked; // key is file name and value is hashID
	
	/**
	 * Construcotr of MyCommit
	 * 
	 * @param files
	 * 		an Arraylist of files from the stating area to commit
	 * @param message
	 * 		the commit message provided by the user
	 * @param prev
	 * 		the current head commit
	 * @param ID
	 * 		the Commit ID
	 */
	public MyCommit(ArrayList<String> files, String message, MyCommit prev, int ID) {
		filesToCommit = files;
		msg = message;
		myPrev = prev;
		dateOfCommit = new Date();
		hashID = ID;
		
		//adding all files from given "files" to tracked
		tracked = new HashMap<String, String>();
		if (filesToCommit != null) { //adding new files
			for (String p: filesToCommit) {
				tracked.put(p, Integer.toString(hashID));
			}
		}
		
		//Making a temp HashMap of all files we have
		HashMap<String, String> temp = new HashMap<String, String>();
		for(HashMap.Entry<String, String> entry : tracked.entrySet()){ 
			temp.put(entry.getKey(), entry.getValue());
		}
		
		// For all the files tracked by myPrev that we don't have, add them to our tracked
		if (myPrev != null) { //adding tracked files
			for(HashMap.Entry<String, String> entry : myPrev.tracked.entrySet()){ 
				if (!temp.containsKey(entry.getKey())) {
					tracked.put(entry.getKey(), entry.getValue());
				}
			}
		
		}
		
		//Making the commit folder
		commitFolder = new File(".gitlet/" + hashID);
		commitFolder.mkdir();
		
		//Copy files from the staging area to our commit folder
		if (filesToCommit != null) {
			for (String p: filesToCommit) {
				Path filePath = Paths.get(p);
				Path commitPath = Paths.get(".gitlet/" + hashID + "/" + p);
				File parent = new File(".gitlet/" + hashID + "/" + p);
				parent.getParentFile().mkdirs();
				try {
					Files.copy(filePath, commitPath);
				} catch (IOException e) {
					// this should never happen
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * Untracking given file
	 * @param fileName
	 * 		files you want to untrack
	 */
	public void Untrack(String fileName){
		tracked.remove(fileName);
	}
	
	/**
	 * @return
	 * 		return the Prev of this commit
	 */
	public MyCommit getPrev() {
		return myPrev;
	}
	
	/**
	 * setting the Prev of this commit
	 * @param prev
	 * 		the previous commit
	 */
	public void setPrev(MyCommit prev) {
		myPrev = prev;
	}
	
	/**
	 * @return
	 * 		returning the Commit ID of this commit
	 */
	public int getID() {
		return hashID;
	}
	
	/**
	 * @return
	 * 		return the date and time of this commit
	 */
	public Date getDate() {
		return dateOfCommit;
	}
	
	/**
	 * @return
	 * returning the commit message
	 */
	public String getMessage() {
		return msg;
	}
	
	/**
	 * @return
	 * 		return the HashMap of all the files we track <filename, commitID>
	 */
	public HashMap<String, String> getTracked() {
		return tracked;
	}
	
	/**
	 * we overwrote to String to match the format of log / global log
	 */
	@Override 
	public String toString() {
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		String dateString = formatter.format(dateOfCommit);
		
		return hashID + "\n" + dateString + "\n" + msg;
	}

}
