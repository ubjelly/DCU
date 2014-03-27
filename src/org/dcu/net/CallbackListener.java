package org.dcu.net;

/**
 * Interface for http callback.
 * @author Brendan Dodd
 */
public interface CallbackListener {
	
	public void onComplete(String jsonResponse);
	
}
