import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;


public class CommitNode implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public int id;
	public String commitMessage;
	public String timeStamp;
	public HashMap<String,String> trackedFiles;
	public CommitNode myPrevious;
	
	public CommitNode(String message, int ID){
		timeStamp = new Date().toString();
		id = ID;
		commitMessage = message;	
		myPrevious = null;
		trackedFiles = new HashMap<String,String>();
	}
	
	public CommitNode(String message, CommitNode previous, int ID){
		timeStamp = new Date().toString();
		id = ID;
		commitMessage = message;
		trackedFiles = new HashMap<String,String>();
		myPrevious = previous;
	}
	
	public CommitNode(CommitNode clone, CommitNode previous, int ID){
		timeStamp = new Date().toString();
		id = ID;
		commitMessage = clone.commitMessage;
		trackedFiles = clone.trackedFiles;
		myPrevious = previous;
	}
	
	public String  getId(){
		return id +"";
	}
	
	public void printLog(){
		System.out.println("===");
		System.out.println("Commit " + id);
		System.out.println(timeStamp);
		System.out.println(commitMessage);
		System.out.println();
	}
}
