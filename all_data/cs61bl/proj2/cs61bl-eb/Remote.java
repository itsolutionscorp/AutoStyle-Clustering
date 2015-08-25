import java.io.Serializable;

/**
 * @author CS61BL-EC, EB, ES, EJ 
 *
 */
public class Remote implements Serializable {
	private static final long serialVersionUID = 7532637254716460386L;
	private String user_name;
	private String server;
	private String locaation_on_server;

	/**
	 * Constructor 
	 * @param user_name
	 * @param server
	 * @param locaation_on_server
	 */
	public Remote(String user_name, String server, String locaation_on_server) {
		this.user_name = user_name;
		this.server = server;
		this.locaation_on_server = locaation_on_server;
	}

	/**
	 * Getter method for user name 
	 * @return username 
	 */
	public String user_name() {
		return user_name;
	}
	
	/**
	 * Getter method for server 
	 * @return server name 
	 */
	public String server() {
		return server;
	} 
	
	/**
	 * Getter method for location on server 
	 * @return location on server 
	 */
	public String locaation_on_server() {
		return locaation_on_server;
	} 
}
