
/**
 * Implements unified exception handling for error cases where no error message
 * should be printed
 * 
 * @authors Nikat Patel		cs61bl-do
 * 			Rowan Sandhu	cs61bl-de
 * 			Ryan Panwar		cs61bl-dk
 * 			Shaswat Shah	cs61bl-mj 
 */

public class NonGitletException extends Exception {

	public NonGitletException() {
		super();
	}

	public NonGitletException(String message) {
		super(message);
	}

	public NonGitletException(String message, Throwable cause) {
		super(message, cause);
	}

	public NonGitletException(Throwable cause) {
		super(cause);
	}
}
