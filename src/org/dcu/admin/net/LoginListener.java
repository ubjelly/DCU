package org.dcu.admin.net;

/**
 * JSON serialization for a login request.
 * @author Stephen Andrews
 */
public interface LoginListener {
	
	public void onComplete(String jsonResponse);
	
	
}
