import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class Commit implements Serializable {
	private static final long serialVersionUID = 19960410L;
	private int myParentId;
	private int id;
	private String myMessage;
	private String myDate;
	private HashMap<String, Integer> myFileCollection;

	public Commit(String message, Status current) {
		this.id = current.numOfCommitted();
		myParentId = -1;
		myMessage = message;
		myFileCollection = new HashMap<String, Integer>();
		this.createDate();
		current.addOneCommitTime();
		current.addCommit(this);
	}

	public Commit(String message, int parentId, Status current) {
		this.id = current.numOfCommitted();
		myParentId = parentId;
		myMessage = message;
		Commit parentCommit = (Commit) FileOperation.read(".gitlet/commit"
				+ parentId + "/commit");
		myFileCollection = new HashMap<String, Integer>();
		CreateFileCollectionFromCollection(parentCommit.getFileCollection());
		CreateFileCollectionFromCollection(current.getFilePaths());
		deleteUntrackedFile(current);
		current.emptyUntrack();
		this.createDate();
		current.addOneCommitTime();
		current.addCommit(this);
		if (current.isConflicted()) {
			current.updateIsConflicted();
		}
		current.serializeStatus();
	}
	
	public Commit(String message, int parentId, Status current, HashMap<String, Integer> filePaths) {
		this.id = current.numOfCommitted();
		myParentId = parentId;
		myMessage = message;
		Commit parentCommit = (Commit) FileOperation.read(".gitlet/commit"
				+ parentId + "/commit");
		myFileCollection = new HashMap<String, Integer>();
		CreateFileCollectionFromCollection(parentCommit.getFileCollection());
		CreateFileCollectionFromCollection(filePaths);
		this.createDate();
		current.addOneCommitTime();
		current.addCommit(this);
		if (current.isConflicted()) {
			current.updateIsConflicted();
		}
		current.serializeStatus();
		
	}

	public void deleteUntrackedFile(Status current) {
		for (String fileName : current.getUntrack()) {
			if (myFileCollection.containsKey(fileName)) {
				myFileCollection.remove(fileName);
			}
		}
	}

	public HashMap<String, Integer> getFileCollection() {
		return myFileCollection;
	}

	// public void updateFileCollection(HashMap<String, Integer> newCollection)
	// {
	// myFileCollection = newCollection;
	// }

	public int getId() {
		return id;
	}

	public String getMessage() {
		return myMessage;
	}

	public boolean doesTrack(String fileName) {
		return myFileCollection.containsKey(fileName);
	}

	public int getFileCommitId(String fileName) {
		return myFileCollection.get(fileName);
	}

	public int getParentId() {
		return myParentId;
	}

	public void updateParentId(int newParentId) {
		myParentId = newParentId;
	}

	// copy two files from the same folder
	public void CreateFileCollectionFromCollection(
			HashMap<String, Integer> toCopy) {
		for (Entry<String, Integer> entry : toCopy.entrySet()) {
			String pathName = entry.getKey();
			int id = entry.getValue();
			myFileCollection.put(pathName, id);
		}
	}

	public void createDate() {
		Date d = new Date();
		SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		myDate = a.format(d);
	}

	public String getDate() {
		return myDate;
	}

	@Override
	public String toString() {
		return "commit" + id;
	}
}
