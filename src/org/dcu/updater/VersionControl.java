package org.dcu.updater;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.dcu.Application;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Compares the local version of DCU with the remote one (most recent build).
 * @author Stephen Andrews, Brendan Dodd
 */
public class VersionControl {
	
	private static final String VERSION_URL = "http://www.derithium.com/dcu/version.php";
	
	/**
	 * The information pertaining to the remote version.
	 */
	private Version latestVersion;
	
	/**
	 * An update listener object.
	 */
	private UpdateListener listener;
	
	/**
	 * Constructs a version control object.
	 * @param listener The listen for the object.
	 */
	public VersionControl(UpdateListener listener) {
		this.listener = listener;
	}
	
	/**
	 * Checks to see if there are any updates.
	 */
	public void checkForUpdates() {	
		fetchRemoteVersion();
	}
	
	/**
	 * Fetches the remote version of DCU from a remote PHP file.
	 */
	public void fetchRemoteVersion() {
		try {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Gson gson = new Gson();
						Boolean updateRequired;
						HttpClient client = HttpClientBuilder.create().build();
						HttpGet get = new HttpGet(VERSION_URL);
						HttpResponse response = client.execute(get);
						
						String jsonResponse = EntityUtils.toString(response.getEntity());
						latestVersion = gson.fromJson(jsonResponse, Version.class);
						if(latestVersion != null) {
							updateRequired = latestVersion.getBuild() > Application.getVersion() ? true : false;
							VersionControl.this.listener.updateRequired(updateRequired);
						}
						
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the latest version of DCU.
	 * @return The latest version.
	 */
	public Version getLatestVersion() {
		return latestVersion;
	}

}
