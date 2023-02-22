package com.database;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class KeyValueStore<K, V> {
	private Map<K, V> databaseMap;
	
	/**
	 * Constructor for database
	 * Reads from a JSON file if it exists
	 * @param port
	 */
	public KeyValueStore(String port) {
		InputStream inputStream = null;
		Path path =  Paths.get("./"+port+"/database.json");
		String returnedJson = readFile(path, port);
		
		System.out.print(returnedJson);
		
		databaseMap = new HashMap<>();
	}
	
	/**
	 * Reads JSON file where the database will be stored in
	 * @param path
	 * @return
	 */
	public String readFile(Path path, String port) {
		String returnedJSON = "";
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader(path, charset)){
			String line = null;
		    while ((line = reader.readLine()) != null) {
		    	returnedJSON+=line;
		        System.out.println(line);
		    }
		} catch (IOException ex) {
			System.err.format("IOException Open File: %s%n", ex);
			// If no file, create new JSON file
			 // Convert the string to a
		    // byte array.
		    String s = "Hello World! ";
		    byte data[] = s.getBytes();
		    Path portDirectory = Paths.get(port);
		    try {
				Files.createDirectory(portDirectory);
			} catch (IOException ex1) {
				System.err.format("IOException Create Directory: %s%n", ex1);
			}
		    try (
		    		OutputStream out = new BufferedOutputStream(
		      Files.newOutputStream(path, CREATE_NEW));) {
		      out.write(data, 0, data.length);
		    } catch (IOException ex2) {
		    	System.err.format("IOException Create File: %s%n", ex2);
		    }
		}
		return returnedJSON;
	}
	
	/**
	 * Adds the key value to hashmap
	 * @param key
	 * @param value
	 * @return value
	 */
	public V put(K key, V value) {
		this.databaseMap.put(key, value);
		return value;
	}

	public static void main(String[] args) {
		KeyValueStore<String, String> example = new KeyValueStore<>("2000");
		

	}

}
