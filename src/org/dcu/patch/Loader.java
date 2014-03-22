package org.dcu.patch;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Loads a DCU patch file.
 * @author Stephen Andrews
 */
public class Loader {

	/**
	 * Structure of the patch to read.
	 * @author Stephen Andrews
	 */
	class Patch {
		/**
		 * The name of the file.
		 */
		private String file;
		
		/**
		 * The key.
		 */
		private String key;
		
		/**
		 * Gets the file name.
		 * @return The file name.
		 */
		public String getFileName() {
			return file;
		}
		
		/**
		 * Gets the key.
		 * @return The key.
		 */
		public String getKey() {
			return key;
		}	
	}
	
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
	
	/**
	 * Reads a patch.
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	public void readPatch() throws UnsupportedEncodingException, IOException {
		 try (Reader reader = new InputStreamReader(Loader.class.getResourceAsStream(patchLocation), "UTF-8")) {
	            Gson gson = new GsonBuilder().create();
	            Patch patch = gson.fromJson(reader, Patch.class);
	            System.out.println(patch);
	        }
	}
}
