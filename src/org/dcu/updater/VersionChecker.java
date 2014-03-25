package org.dcu.updater;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Compares the local version of DCU with the remote one (most recent build).
 * @author Stephen Andrews
 */
public class VersionChecker {
	
	Version version = new Version();
	
	/**
	 * Downloads a ur as text.
	 * @param url The url to download from.
	 * @return The contents of the url.
	 * @throws Exception
	 */
	private static String readUrl(String fromUrl) throws Exception {
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
	 * Checks the latest version of DCU by referencing a JSON file.
	 */
	public void checkLatestVersion() {
		final String url = "http://www.derithium.com/dcu/version.json";
		final Gson gson = new Gson();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					gson.fromJson(readUrl(url), version.getClass());
				} catch (JsonSyntaxException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public Version getVersion() {
		return version;
	}
}
