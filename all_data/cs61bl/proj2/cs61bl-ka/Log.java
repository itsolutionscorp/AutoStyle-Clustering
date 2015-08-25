import java.io.Serializable;

/**
 * This class creates a log, which will store the information from each commit.
 * 
 * @author Kevin Wu, Cynthia Diaz, Evan Patel, Jaehyu Sim 
 *
 */
public class Log implements Serializable {
	private int myID;
	private String myMsg;
	private String timeStamp;

	/**
	 * Constructor for a log object
	 * @param ID is the Id associated with a commit
	 * @param msg is the message associated with a commit
	 * @param createdTime is the time which a commit was made
	 */
	public Log(int ID, String msg, String createdTime) {
		myID = ID;
		myMsg = msg;
		// timeStamp = dateConstructor();
		timeStamp = createdTime;
	}

	/** 
	 * Getter method for message
	 * @return the message associated with a commit
	 */
	public String getmsg() {
		return myMsg;
	}

	/**
	 * Getter method for the ID
	 * @return the ID associated with a commit
	 */
	public int getid() {
		return myID;
	}

	/**
	 * Getter method
	 * @return the time which a commit was made
	 */
	public String getTime() {
		return timeStamp;
	}

	@Override
	/**
	 * Prints a log in the specified format 
	 */
	public String toString() {
		return "=== \n" + "Commit " + myID + "\n" + timeStamp + "\n" + myMsg + "\n";
	}

}
