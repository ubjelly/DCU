package org.dcu.updater;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dcu.admin.net.LoginRequest;

/**
 * Compares the local version of DCU with the remote one (most recent build).
 * @author Stephen Andrews
 */
public class VersionChecker {

	/**
	 * The buld number.
	 */
	private double build;
	
	/**
	 * The information regarding the version.
	 */
	private String info;
	
	/**
	 * Constructs a version checker.
	 */
	public VersionChecker() {
		build = -1;
		info = "";
	}
	
	/**
	 * Checks the latest version of DCU by referencing the site.
	 */
	public void checkLatestVersion() {
		final HttpUriRequest uri = new HttpUriRequest("http://www.derithium.com/dcu/version.json");
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					HttpClient client = HttpClientBuilder.create().build();
					HttpResponse response = client.execute(url);

					
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}).start();
	}
}
