/**
 * 
 */
package org.pjay.exceptions;

/**
 * @author vijayk
 *
 */
public class UserDefinedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6100259342003593043L;

	public UserDefinedException() {
		super();
	}

	public UserDefinedException(String message) {
		super(message);
	}

	public UserDefinedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserDefinedException(Throwable cause) {
		super(cause);
	}

}
