package com.demo.mvc.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wubing
 *
 */
public class MapValueComparator implements Comparator<String> {

	Map<String, Integer> map = new HashMap<String, Integer>();

	public MapValueComparator(Map<String, Integer> map) {
		this.map.putAll(map);
	}

	@Override
	public int compare(String key1, String key2) {
		Integer value1 = map.get(key1) == null?0:map.get(key1);
		Integer value2 = map.get(key2) == null?0:map.get(key2);
		if (value1 > value2) {
			return -1;
		} else if (value1 == value2) {
			return key1.compareTo(key2);
		} else {
			return 1;
		}
	}
}
