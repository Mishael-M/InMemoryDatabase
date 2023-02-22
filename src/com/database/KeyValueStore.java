package com.database;

import java.util.HashMap;
import java.util.Map;

public class KeyValueStore<K, V> {
	private Map<K, V> databaseMap;
	
	public KeyValueStore() {
		
		
		databaseMap = new HashMap<>();
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
		// TODO Auto-generated method stub

	}

}
