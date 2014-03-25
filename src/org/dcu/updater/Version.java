package org.dcu.updater;

/**
 * Serialization of remote DCU JSON.
 * @author Stephen Andrews
 */
public class Version {

	/**
	 * The build number.
	 */
	private double build;
	
	/**
	 * The information regarding the version.
	 */
	private String info;
	
	/**
	 * Gets the build number.
	 * @return The build number.
	 */
	public double getBuild() {
		return build;
	}
	
	/**
	 * Gets the version's information.
	 * @return The version's information.
	 */
	public String getInfo() {
		return info;
	}
}
