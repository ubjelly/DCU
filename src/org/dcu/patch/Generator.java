package org.dcu.patch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.dcu.admin.ConsoleMessage;
import org.dcu.util.Key;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

/**
 * Generates a patch to be read by the loader.
 * @author Stephen Andrews
 */
public class Generator {
	
	/**
	 * Structure of our patch to generate.
	 * @author Stephen Andrews
	 */
	class Patch {
		@SerializedName("file")
		String fileName;

		@SerializedName("key")
		String key;

		public Patch(String fileName, String key) {
			this.fileName = fileName;
			this.key = key;
		}
	}
	
	/**
	 * The location of the folder to generate a patch for.
	 */
	private String folderLocation;
	
	/**
	 * A collection of all the files in the folder.
	 */
	private List<File> files;
	
	/**
	 * Construct a generator.
	 * @param folderLocation The location of the folder to generator a patch for.
	 */
	public Generator(String folderLocation) {
		this.folderLocation = folderLocation;
		files = new ArrayList<File>();
	}
	
	/**
	 * Adds all files in the selected folder to an array list.
	 */
	private void grabFiles() {
		File dir = new File(folderLocation);
		int filesFound = 0;
		for (File child : dir.listFiles()) {
			files.add(child);
			filesFound++;
		}
		ConsoleMessage.info("Found a total of " + filesFound + " items in cache.");
	}
	
	/**
	 * Generates the patch file.
	 * @return true If the generation was successful.
	 */
	public void createPatch() throws IOException {
		ConsoleMessage.info("Starting generation process...");
	    List<Patch> patchContents = new ArrayList<Patch>();
	    File file = new File(folderLocation + "\\Patch.json");
	    grabFiles();
	    
	    //Adding keys.
	    ConsoleMessage.info("Generating unique keys...");
	    for (int i = 0; i < files.size(); i++) {
	    	String fileName = files.get(i).getName();
	    	String key = Key.getKey();
	    	Patch patch = new Patch(fileName, key);
	    	patchContents.add(patch);
	    	ConsoleMessage.info("Key generated: " + key + ".");
	    }
	    ConsoleMessage.info("Done generating keys.");
	    
	    //Create the file
		try (Writer writer = new OutputStreamWriter(new FileOutputStream(file) , "UTF-8")) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Patch>>(){}.getType();
            if (file.exists()) {
            	ConsoleMessage.info("We found an existing patch, overwriting it...");
            	file.delete();
            }
            gson.toJson(patchContents, writer);
            ConsoleMessage.info("Process complete!");
            //ConsoleMessage.success("Process complete!");
            System.out.println(gson.toJson(patchContents, listType));
        }
	}
}
