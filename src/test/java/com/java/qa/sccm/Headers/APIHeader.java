package com.java.qa.sccm.Headers;

import java.util.HashMap;
import java.util.Map;

import com.java.qa.sccm.test.LoginTest;



public class APIHeader {

	public static Map<String, String> defaultHeader() {
		Map<String, String> defaultHeader = new HashMap<String, String>();
		defaultHeader.put("Content-Type", "application/json");
		defaultHeader.put("Accept", "application/json");
		defaultHeader.put("Authorization", LoginTest.Accesstoken);
		return defaultHeader;
	}

	public static void main(String[] args) {
		System.out.println(defaultHeader());
	}
}
