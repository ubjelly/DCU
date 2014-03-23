package org.dcu.admin.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Handles the login communication between the application and site.
 * @author Stephen Andrews, Brendan Dodd
 */
public class LoginRequest implements LoginListener {
	
	private JDialog parent;
	private String username;
	private String password;
	
	/**
	 * Constructs a login request.
	 * @param parent The parent of the form.
	 * @param username The username.
	 * @param password The password.
	 */
	public LoginRequest(JDialog parent, String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Sends a login request with the inputed username and password.
	 * @param username The username entered.
	 * @param password The password entered.
	 */
	public void login() {
		final String url = "http://derithium.com/dcu/login.php";
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					HttpClient client = HttpClientBuilder.create().build();
					HttpPost post = new HttpPost(url);
				 
					post.setHeader("Content-Type", "text/html");
				 
					List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
					urlParameters.add(new BasicNameValuePair("username", username));
					urlParameters.add(new BasicNameValuePair("password", password));
					post.setEntity(new UrlEncodedFormEntity(urlParameters));
					HttpResponse response = client.execute(post);
					
					String jsonResponse = EntityUtils.toString(response.getEntity());
					LoginRequest.this.onComplete(jsonResponse);
					
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void onComplete(String jsonResponse) {
		System.out.println(jsonResponse);
		Gson gson = new Gson();
		LoginResponse login = gson.fromJson(jsonResponse, LoginResponse.class);
		if(login.isSuccess()) {
			parent.dispose();
		} else {
			
		}
	}
}
