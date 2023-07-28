package com.solvd.laba.carina.unsplash;

import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.laba.carina.demo.api.GetUserMethods;
import com.solvd.laba.carina.demo.api.InvalidGetUserMethods;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.utils.Configuration;

import io.restassured.response.Response;

public class APITests implements IAbstractTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private static final String query = "Eagle";
	private static final String TwoPages = "20";
	private static final String ThreePages = "20";
	private static final String page = "3";
	
	@Test()
	@MethodOwner(owner = "lucas")
	public void testSearchEagleImages() {
		GetUserMethods api = new GetUserMethods("Photo");
		api.addParameter("query", query);
		api.addParameter("per_page", TwoPages);
		api.addParameter("client_id", Configuration.getEnvArg("access_key"));
		api.callAPIExpectSuccess();
		api.validateResponseAgainstSchema("api/users/_get/rs.schema");
	}
	
	@Test()
	@MethodOwner(owner = "lucas")
	public void testSearchTopics() {
		GetUserMethods api = new GetUserMethods("photo"); 
		api.addParameter("page", "1");
		api.addParameter("query", query);
		api.addParameter("per_page", ThreePages);
		api.addParameter("client_id", Configuration.getEnvArg("access_key"));
		Response response = api.callAPIExpectSuccess();
		List<String> titles = response.jsonPath().getList("result.title");
		HashSet<String> uniqueTitles = new HashSet<String>(titles);
		LOGGER.info("Total Titles: " + titles.size());
		LOGGER.info("Total Unique Titles: " + uniqueTitles.size());
		Assert.assertTrue(titles.size() == (uniqueTitles.size()));
	}
	
	@Test()
	@MethodOwner(owner = "lucas")
	public void testInvalidCredentials() {
		InvalidGetUserMethods api = new InvalidGetUserMethods("topic"); 
		api.addParameter("client_id", "obfuscation" + Configuration.getEnvArg("access_key"));
		api.callAPIExpectSuccess();
	}
}