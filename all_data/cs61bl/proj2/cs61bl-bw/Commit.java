import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.text.SimpleDateFormat;

public class Commit implements Serializable {

	private int myID;
	private String myMsg;
	private Calendar time;
	private Commit myParent;
	private HashMap<String, String> trackedfiles;
	private HashMap<String, Long> trackedLastModified;
	private File folder;
	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public Commit(String msg, Commit parent, int ID) {
		myMsg = msg;
		time = Calendar.getInstance();
		myParent = parent;
		myID = ID;
		trackedfiles = new HashMap<String, String>();
		trackedLastModified = new HashMap<String, Long>();
		folder = new File(".gitlet/commits/" + myID);
		folder.mkdirs();

	}

	public Commit(String msg) {
		myMsg = msg;
		time = Calendar.getInstance();
		myID = 0;
		trackedfiles = new HashMap<String, String>();
		trackedLastModified = new HashMap<String, Long>();
		folder = new File(".gitlet/commits/" + myID);
		folder.mkdirs();
	}

	public void add(String fileName, String filedirect) {
		trackedfiles.put(fileName, filedirect);
		trackedLastModified.put(fileName, (new File(fileName)).lastModified());
	}

	public void rm(String a) {
		trackedfiles.remove(a);
	}

	public HashMap<String, String> getTrack() {
		return trackedfiles;
	}

	public Map<String, Long> getLastModified() {
		return trackedLastModified;
	}

	public String getID() {
		return myID + "";
	}

	public int getIDnum() {
		return myID;
	}

	public Commit getParent() {
		return myParent;
	}

	public File getFolder() {
		return folder;
	}

	public String message() {
		return myMsg;
	}

	public void setTracked(HashMap<String, String> tracks) {
		trackedfiles = tracks;
	}

	public Long getTimeInMillis() {
		return time.getTimeInMillis();
	}

	public void logger() {
		System.out.println("===");
		System.out.println("Commit " + myID);
		System.out.println(sdf.format(time.getTime()));
		System.out.println(myMsg);
		System.out.println("");
		if (this.myParent != null) {
			this.myParent.logger();
		}
		return;
	}

	public void globalLogger() {
		System.out.println("===");
		System.out.println(myID);
		System.out.println(sdf.format(time.getTime()));
		System.out.println(myMsg);
		return;
	}
}
