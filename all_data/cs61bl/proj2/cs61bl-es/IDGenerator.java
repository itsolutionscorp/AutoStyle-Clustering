/**
 * @author CS61BL-EC, EB, ES, EJ 
 *
 */
public class IDGenerator {
	private String commit_msg;
	private String commit_timestamp;
	private Integer prev_id;
	
	/**
	 * constructor 
	 * @param commit_msg
	 * @param commit_timestamp
	 * @param prev_id
	 */
	public IDGenerator(String commit_msg, String commit_timestamp, Integer prev_id) {
		this.commit_msg = commit_msg;
		this.commit_timestamp = commit_timestamp;
		this.prev_id = prev_id;
	}
	
	/**
	 * returns commit ID 
	 * @return commitID 
	 */
	public int commitID() {
		Hashids h = new Hashids("just use the algorithm");
		String id = "";
		id = h.encode(Math.abs(commit_msg.hashCode()), Math.abs(commit_timestamp.hashCode()),
				Math.abs(prev_id));
		return id.hashCode();
	}
}
