package org.dcu.patch;

/**
 * Loads a dcu patch file.
 * @author Stephen Andrews
 */
public class Loader {

	/**
	 * The location of the patch file.
	 */
	private String patchLocation;
	
	/**
	 * Constructs a patch loader.
	 * @param patchLocation The location of the patch to load.
	 */
	public Loader(String patchLocation) {
		this.patchLocation = patchLocation;
	}
}
