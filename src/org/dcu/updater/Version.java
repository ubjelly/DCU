package org.dcu.updater;

/**
 * Serialization of remote DCU JSON.
 * @author Stephen Andrews
 */
public class Version {

	/**
	 * The build number.
	 */
	private String build;
	
	/**
	 * The information regarding the version.
	 */
	private String notes;
	
	/**
	 * Gets the build number.
	 * @return float - The build number.
	 */
	public float getBuild() {
		return Float.parseFloat(build);
	}
	
	/**
	 * Gets the version's release notes.
	 * @return The version's release notes.
	 */
	public String getReleaseNotes() {
		return notes;
	}
}
