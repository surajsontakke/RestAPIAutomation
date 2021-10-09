package com.java.qa.sccm.test.serviceDeploy;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {

	public static String reandomNumber() {
		Random random = new Random();
		int randomNumber = random.nextInt(1000);
		String id = Integer.toString(randomNumber);
		return id;
	}

	public static String RandomString() {
		String randomString = RandomStringUtils.randomAlphabetic(8);
		return randomString;
	}
}
