package com.joseclaudiosiqueira.springboot.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	public static String decodeParam(String string) {
		try {
			return URLDecoder.decode(string, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static List<Integer> decodeIntList(String string) {
		String[] vector = string.split(",");
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < vector.length; i++) {
			list.add(Integer.parseInt(vector[i]));
		}
		return list;
		// return Arrays.asList(string.split(",")).stream().map(x ->
		// Integer.parseInt(x)).collect(Collectors.toList());
	}
}
