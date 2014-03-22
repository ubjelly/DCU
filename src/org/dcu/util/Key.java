package org.dcu.util;

import java.util.Random;

/**
 * Generates unique keys 8 characters long.
 * @author Stephen Andrews
 *
 */
public class Key {

	/**
	 * All the possibly chars that a key can contain.
	 */
	private static final char[] KEY_CHARS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
							   				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
							   				's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1',
							   				'2', '3', '4', '5', '6', '7', '8', '9', '!',
							   				'@', '#', '$', '%', '^', '&', '*',};
	/**
	 * Generates a unique key comprised of letters and digits 8 characters long.
	 * @return The generated key.
	 */
	public static String getKey() {
		Random random = new Random();
		String key = "";
		while (key.length() < 8) {
			key = key + KEY_CHARS[random.nextInt(KEY_CHARS.length)];
		}
		return key;
	}
}
