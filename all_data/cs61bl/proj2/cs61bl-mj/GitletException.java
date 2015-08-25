
/**
 * Implements unified exception handling for Gitlet where error messages
 * need to be printed
 * 
 * @authors Nikat Patel		cs61bl-do
 * 			Rowan Sandhu	cs61bl-de
 * 			Ryan Panwar		cs61bl-dk
 * 			Shaswat Shah	cs61bl-mj 
 */

public class GitletException extends Exception {

	public GitletException() {
		super();
	}

	public GitletException(String message) {
		super(message);
	}

	public GitletException(String message, Throwable cause) {
		super(message, cause);
	}

	public GitletException(Throwable cause) {
		super(cause);
	}
}
