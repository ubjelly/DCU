package org.dcu.updater;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.dcu.net.CallbackListener;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Compares the local version of DCU with the remote one (most recent build).
 * @author Stephen Andrews
 */
public class VersionChecker implements CallbackListener {
	
	/**
	 * The information pertaining to the remote version.
	 */
	Version remoteVersion;
	
	/**
	 * The information pertaining to the local version.
	 */
	Version localVersion;
	
	/**
	 * Construct a version checker.
	 */
	public VersionChecker() {
		remoteVersion = null;
		localVersion = null;
	}
	
	/**
	 * Downloads a url as text.
	 * @param url The url to download from.
	 * @return The contents of the url.
	 * @throws Exception
	 */
	private String readUrl(String fromUrl) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(fromUrl);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer sb = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1) {
				sb.append(chars, 0, read);
				return sb.toString();
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
				
		}
		return null;
	}

	/**
	 * Loads the latest version of DCU by referencing a remote JSON file.
	 */
	public void loadRemoteVersion() {
		final String url = "http://www.derithium.com/dcu/version.json";
				try {
					String jsonResponse = readUrl(url);
					VersionChecker.this.onComplete(jsonResponse);
				} catch (JsonSyntaxException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
	
	/**
	 * Loads the local version of DCU.
	 */
	public void loadLocalVersion() {
		final Gson gson = new Gson();
		new Thread(new Runnable() {

			@Override
			public void run() {
				String path = System.getProperty("user.dir") + "/config/version.json";
				File file = new File(path);
				BufferedReader reader;
				try {
					reader = new BufferedReader(new FileReader(file));
					localVersion = gson.fromJson(reader, Version.class);
					System.out.println("Loaded local version - Version: " + localVersion.getBuild()
							+ " Info: " + localVersion.getInfo());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			
		}).start();
	}
	
	/**
	 * Gets the remote version.
	 * @return The remote version.
	 */
	public Version getRemoteVersion() {
		return remoteVersion;
	}
	
	/**
	 * Gets the local version.
	 * @return The local version.
	 */
	public Version getLocalVersion() {
		return localVersion;
	}

	/**
	 * The action taken after http callback.
	 * @param jsonResponse The received JSON.
	 */
	@Override
	public void onComplete(String jsonResponse) {
		Gson gson = new Gson();
		remoteVersion = gson.fromJson(jsonResponse, Version.class);
	}
}
