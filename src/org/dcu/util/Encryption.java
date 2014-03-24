package org.dcu.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Handles various forms of encryption used throughout the application.
 * @author Stephen Andrews
 */
public class Encryption {

	/**
	 * Converts a string to an MD5 hash.
	 * @input The string to convert.
	 * @return The converted string.
	 */
	public static String stringToMD5(String input) {
		String original = input;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(original.getBytes());
			byte messageDigest[] = md.digest();
			StringBuffer hexString = new StringBuffer();
			for (int i=0;i<messageDigest.length;i++) {
				String hex=Integer.toHexString(0xFF & messageDigest[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
