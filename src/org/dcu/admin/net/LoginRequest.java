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

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Handles the login communication between the application and site.
 * @author Stephen Andrews
 */
public class LoginRequest {

	/**
	 * Sends a login request with the inputed username and password.
	 * @param username The username entered.
	 * @param password The password entered.
	 */
	public static void send(String username, String password) {
		String url = "http://derithium.com/dcu/login.php";
		 
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
	 
		post.setHeader("Content-Type", "text/html");
	 
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("username", username));
		urlParameters.add(new BasicNameValuePair("password", password));
	 
		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			HttpResponse response = client.execute(post); 
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		 
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			
			isSuccessful(new URL(url));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Determines whether the request was successful or not.
	 * @param url The url to check.
	 * @return true If the login was successful.
	 */
	private static boolean isSuccessful(URL url) {
		try {
			InputStream is = url.openStream();
			String input = null;
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				input = reader.readLine();
			} finally {
				if(input != null) {
					JsonElement jElement = new JsonParser().parse(input);
				    JsonObject successObject = jElement.getAsJsonObject();
				    JsonObject messageObject = jElement.getAsJsonObject();
				    successObject = successObject.getAsJsonObject("success");
				    messageObject = messageObject.getAsJsonObject("message");
				    System.out.println(successObject + "    " + messageObject);
				}
				is.close();
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
