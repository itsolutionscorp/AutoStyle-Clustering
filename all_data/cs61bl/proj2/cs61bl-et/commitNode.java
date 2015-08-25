import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class commitNode implements Serializable{
	private int commitID1;
	private int preNodeID1;
	private String commitMessage1;
	private String commitTime1;
	private Map<String, String> fileList = new HashMap<String, String>();


	public commitNode(int commitID, int preNode, String commitMessage, String commitTime) {
		this.commitID1 = commitID;
		this.preNodeID1 = preNode;
		this.commitMessage1 = commitMessage;
		this.commitTime1 = commitTime;
	}

	public int getCommitID() {
		return this.commitID1;
	}

	public int getPrevNodeID() {
		return this.preNodeID1;
	}

	public String getCommitMessage() {
		return this.commitMessage1;
	}

	public String getCommitTime() {
		return this.commitTime1;
	}

	public void addFile(String fileName, String filePath) {
		fileList.put(fileName, filePath);
	}


	public boolean containsFile(String fileName) {
		return fileList.containsKey(fileName);
	}

	public String getFilePath(String fileName) {
		return fileList.get(fileName);
	}

	public Map<String, String> getAllCommitedFiles() {
		return this.fileList;
	}

	public void changePreID(int a){
		preNodeID1= a;
	}

}
