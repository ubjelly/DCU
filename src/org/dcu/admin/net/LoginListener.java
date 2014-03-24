package org.dcu.admin.net;

/**
 * Interface for http callback.
 * @author Brendan Dodd
 */
public interface LoginListener {
	
	public void onComplete(String jsonResponse);
	
}
