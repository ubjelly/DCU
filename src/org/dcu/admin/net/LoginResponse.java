package org.dcu.admin.net;

/**
 * JSON serialization of a login response.
 * @author Stephen Andrews
 */
public class LoginResponse {

	/**
	 * Whether or not the login was successful.
	 */
	private boolean success;
	
	/**
	 * The message received upon logging in.
	 */
	private String message;
	
	/**
	 * Gets the success flag.
	 * @return The flag.
	 */
	public boolean isSuccess() {
		return success;
	}
	
	/**
	 * Gets the message received upon logging in.
	 * @return The message.
	 */
	public String getMessage() {
		return message;
	}
}
